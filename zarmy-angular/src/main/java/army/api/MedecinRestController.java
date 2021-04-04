package army.api;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.annotation.JsonView;

import army.dao.MedecinDao;
import army.model.Medecin;
import army.model.Views;

@RestController
public class MedecinRestController {
	
	@Autowired
	private MedecinDao daoMedecin;

	@RequestMapping(value = "/medecin", method = RequestMethod.GET)
	@JsonView(Views.Medecin.class)
	public ResponseEntity<List<Medecin>> list() {
		return new ResponseEntity<>(daoMedecin.findAll(), HttpStatus.OK);
	}

	@RequestMapping(value = "/medecin/{id}", method = RequestMethod.GET)
	@JsonView(Views.Medecin.class)
	public ResponseEntity<Medecin> find(@PathVariable("id") Long id) {
		Medecin tmp = (Medecin) daoMedecin.find(id);
		if (tmp == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<Medecin>(tmp, HttpStatus.OK);
		}
	}
	

	@RequestMapping(value = "/medecin", method = RequestMethod.POST)
	public ResponseEntity<Void> create(@RequestBody Medecin medecin, UriComponentsBuilder uCB) {
		daoMedecin.create(medecin);
		URI uri = uCB.path("/medecin/{id}").buildAndExpand(medecin.getId()).toUri();
		HttpHeaders header = new HttpHeaders();
		header.setLocation(uri);
		return new ResponseEntity<>(header, HttpStatus.CREATED);

	}

	@RequestMapping(value = "/medecin/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@PathVariable("id") Long id, @RequestBody Medecin medecin) {
		Medecin tmp = (Medecin) daoMedecin.find(id);
		if (tmp == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			daoMedecin.update(medecin);
			return new ResponseEntity<>(HttpStatus.OK);
		}
	}

	@RequestMapping(value = "/medecin/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
		Medecin tmp = (Medecin) daoMedecin.find(id);
		if (tmp == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			daoMedecin.delete(tmp);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}
}
	