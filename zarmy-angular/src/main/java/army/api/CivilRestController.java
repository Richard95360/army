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

import army.dao.CivilDao;
import army.model.Civil;
import army.model.Views;



@RestController
public class CivilRestController {
	
	@Autowired
	private CivilDao daoCivil;

	@RequestMapping(value = "/civil", method = RequestMethod.GET)
	@JsonView(Views.Civil.class)
	public ResponseEntity<List<Civil>> list() {
		return new ResponseEntity<>(daoCivil.findAll(), HttpStatus.OK);
	}

	@RequestMapping(value = "/civil/{id}", method = RequestMethod.GET)
	@JsonView(Views.Medecin.class)
	public ResponseEntity<Civil> find(@PathVariable("id") Long id) {
		Civil tmp = (Civil) daoCivil.find(id);
		if (tmp == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<Civil>(tmp, HttpStatus.OK);
		}
	}
	

	@RequestMapping(value = "/civil", method = RequestMethod.POST)
	public ResponseEntity<Void> create(@RequestBody Civil civil, UriComponentsBuilder uCB) {
		daoCivil.create(civil);
		URI uri = uCB.path("/civil/{id}").buildAndExpand(civil.getId()).toUri();
		HttpHeaders header = new HttpHeaders();
		header.setLocation(uri);
		return new ResponseEntity<>(header, HttpStatus.CREATED);

	}

	@RequestMapping(value = "/civil/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@PathVariable("id") Long id, @RequestBody Civil civil) {
		Civil tmp = (Civil) daoCivil.find(id);
		if (tmp == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			daoCivil.update(civil);
			return new ResponseEntity<>(HttpStatus.OK);
		}
	}

	@RequestMapping(value = "/civil/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
		Civil tmp = (Civil) daoCivil.find(id);
		if (tmp == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			daoCivil.delete(tmp);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}

}



