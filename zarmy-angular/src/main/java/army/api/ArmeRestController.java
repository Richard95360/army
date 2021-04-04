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

import army.metier.AdminArme;
import army.metier.IAdminArme;
import army.model.Arme;
import army.model.Views;



@RestController
public class ArmeRestController {

	@Autowired
	private IAdminArme adminArme;
//	private ProduitDao daoProduit;

	@RequestMapping(value = "/arme", method = RequestMethod.GET)
	@JsonView(Views.Arme.class)
	public ResponseEntity<List<Arme>> list() {
//		return new ResponseEntity<>(daoProduit.findAll(), HttpStatus.OK);
		return new ResponseEntity<>(adminArme.listArmes(), HttpStatus.OK);
	}

	@RequestMapping(value = "/arme/selectionne", method = RequestMethod.GET)
	@JsonView(Views.Arme.class)
	public ResponseEntity<List<Arme>> armesSelectionnes() {
		return new ResponseEntity<>(adminArme.armesSelectionnes(), HttpStatus.OK);
	}

	@RequestMapping(value = "/arme/{id}", method = RequestMethod.GET)
	@JsonView(Views.Arme.class)
	public ResponseEntity<Arme> find(@PathVariable("id") Long id) {
//		Produit tmp = (Produit) daoProduit.find(id);
		Arme tmp = (Arme) adminArme.getArme(id);
		if (tmp == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<Arme>(tmp, HttpStatus.OK);
		}
	}

	@RequestMapping(value = "/arme/{id}/photo/{nomPhoto}",
			produces = MediaType.IMAGE_JPEG_VALUE,
			method = RequestMethod.GET)
	@JsonView(Views.ArmeAvecPhoto.class)
	public ResponseEntity<byte[]> getPhoto(@PathVariable("id") Long id, @PathVariable("nomPhoto") String n) throws IOException {
		Arme tmp = (Arme) adminArme.getArme(id);
		if (tmp == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else if (tmp.getPhoto() == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		else {
			return new ResponseEntity<byte[]>(IOUtils.toByteArray(new ByteArrayInputStream(tmp.getPhoto())), HttpStatus.OK);
		}
	}

	@RequestMapping(value = "/arme",
			consumes = ("multipart/form-data"),
			method = RequestMethod.POST)
//	public ResponseEntity<Void> create(@RequestBody Produit produit, UriComponentsBuilder uCB) {
	public ResponseEntity<Void> create(
			@RequestPart(value = "arme") Arme arme,
			@RequestPart(value = "file", required=false) MultipartFile file,
			UriComponentsBuilder uCB) throws IOException {
		if ((file!=null) && (!file.isEmpty())) {
			BufferedImage bi = ImageIO.read(file.getInputStream());
			arme.setPhoto(file.getBytes());
			arme.setNomPhoto(file.getOriginalFilename());
		}
//		daoProduit.create(produit);
		adminArme.ajouterArme(arme);
		URI uri = uCB.path("/arme/{id}").buildAndExpand(arme.getId()).toUri();
		HttpHeaders header = new HttpHeaders();
		header.setLocation(uri);
		return new ResponseEntity<>(header, HttpStatus.CREATED);

	}

//	@RequestMapping(value = "/produit/{id}", method = RequestMethod.PUT)
	@RequestMapping(value = "/arme/{id}",
			consumes = ("multipart/form-data"),
			method = RequestMethod.POST)
//	public ResponseEntity<Void> update(@PathVariable("id") Long id, @RequestBody Produit produit) {
	public ResponseEntity<Void> update(
			@PathVariable("id") Long id,
			@RequestPart(value = "arme") Arme arme,
			@RequestPart(value = "file", required=false) MultipartFile file
			) throws IOException {
//		Produit tmp = (Produit) daoProduit.find(id);
		Arme tmp = (Arme) adminArme.getArme(id);
		if (tmp == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			if ((file!=null) && (!file.isEmpty())) {
				BufferedImage bi = ImageIO.read(file.getInputStream());
				arme.setPhoto(file.getBytes());
				arme.setNomPhoto(file.getOriginalFilename());
			}
			else { // aucune nouvelle photo uploadée
				arme.setPhoto(tmp.getPhoto()); // on conserve l'ancienne photo
				arme.setNomPhoto(tmp.getNomPhoto()); // et son nom
			}
//			daoProduit.update(produit);
			adminArme.modifierArme(arme);
			return new ResponseEntity<>(HttpStatus.OK);
		}
	}

	@RequestMapping(value = "/arme/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
//		Produit tmp = (Produit) daoProduit.find(id);
		Arme tmp = (Arme) adminArme.getArme(id);
		if (tmp == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
//			daoProduit.delete(tmp);
			adminArme.supprimerArme(tmp);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}

}
