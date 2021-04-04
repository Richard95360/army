package army.api;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URI;
import java.util.List;

import javax.imageio.ImageIO;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.annotation.JsonView;

import army.metier.IAdminGuerrier;
import army.model.Guerrier;
import army.model.Views;


@RestController
public class GuerrierRestController {

	@Autowired
	private IAdminGuerrier adminGuerrier;
	

	@RequestMapping(value = "/guerrier", method = RequestMethod.GET)
	@JsonView(Views.Guerrier.class)
	public ResponseEntity<List<Guerrier>> list() {
//		return new ResponseEntity<>(daoCategorie.findAll(), HttpStatus.OK);
		return new ResponseEntity<>(adminGuerrier.listGuerriers(), HttpStatus.OK);
	}

	@RequestMapping(value = "/guerrier/{id}", method = RequestMethod.GET)
	@JsonView(Views.Guerrier.class)
	public ResponseEntity<Guerrier> find(@PathVariable("id") Long id) {
//		Categorie tmp = (Categorie) daoCategorie.find(id);
		Guerrier tmp = (Guerrier) adminGuerrier.getGuerrier(id);
		if (tmp == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<Guerrier>(tmp, HttpStatus.OK);
		}
	}

//	@RequestMapping(value = "/categorie/{id}/photo",
	@RequestMapping(value = "/guerrier/{id}/photo/{nomPhoto}",
			produces = MediaType.IMAGE_JPEG_VALUE,
			method = RequestMethod.GET)
	@JsonView(Views.GuerrierAvecPhoto.class)
//	public ResponseEntity<byte[]> getPhoto(@PathVariable("id") Long id) throws IOException {
	public ResponseEntity<byte[]> getPhoto(@PathVariable("id") Long id, @PathVariable("nomPhoto") String n) throws IOException {
		Guerrier tmp = (Guerrier) adminGuerrier.getGuerrier(id);
		if (tmp == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else if (tmp.getPhoto() == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		else {
			return new ResponseEntity<byte[]>(IOUtils.toByteArray(new ByteArrayInputStream(tmp.getPhoto())), HttpStatus.OK);
		}
	}

	@RequestMapping(value = "/guerrier",
			consumes = ("multipart/form-data"),
			method = RequestMethod.POST)
	public ResponseEntity<Void> create(
			@RequestPart(value = "guerrier") Guerrier guerrier,
			@RequestPart(value = "file", required=false) MultipartFile file,
			UriComponentsBuilder uCB) throws IOException {
		if ((file!=null) && (!file.isEmpty())) {
			BufferedImage bi = ImageIO.read(file.getInputStream());
			guerrier.setPhoto(file.getBytes());
			guerrier.setNomPhoto(file.getOriginalFilename());
		}
//		daoCategorie.create(categorie);
		adminGuerrier.ajouterGuerrier(guerrier);
		URI uri = uCB.path("/guerrier/{id}").buildAndExpand(guerrier.getId()).toUri();
		HttpHeaders header = new HttpHeaders();
		header.setLocation(uri);
		return new ResponseEntity<>(header, HttpStatus.CREATED);

	}

	@RequestMapping(value = "/guerrier{id}",
			consumes = ("multipart/form-data"),
			method = RequestMethod.POST)
	public ResponseEntity<Void> update(
			@PathVariable("id") Long id,
			@RequestPart(value = "guerrier") Guerrier guerrier,
			@RequestPart(value = "file", required=false) MultipartFile file
			) throws IOException {
//		Categorie tmp = (Categorie) daoCategorie.find(id);
		Guerrier tmp = (Guerrier) adminGuerrier.getGuerrier(id);
		if (tmp == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			if ((file!=null) && (!file.isEmpty())) {
				BufferedImage bi = ImageIO.read(file.getInputStream());
				guerrier.setPhoto(file.getBytes());
				guerrier.setNomPhoto(file.getOriginalFilename());
			}
			else { // aucune nouvelle photo uploadée
				guerrier.setPhoto(tmp.getPhoto()); // on conserve l'ancienne photo
				guerrier.setNomPhoto(tmp.getNomPhoto()); // et son nom
			}
//			daoCategorie.update(categorie);
			adminGuerrier.modifierGuerrier(guerrier);
			return new ResponseEntity<>(HttpStatus.OK);
		}
	}
	

	@RequestMapping(value = "/categorie/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
//		Categorie tmp = (Categorie) daoCategorie.find(id);
		Guerrier tmp = (Guerrier) adminGuerrier.getGuerrier(id);
		if (tmp == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
//			daoCategorie.delete(tmp);
			adminGuerrier.supprimerGuerrier(tmp);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}

}
