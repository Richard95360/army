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

import army.dao.PacifisteDao;
import army.model.Pacifiste;
import army.model.Views;
@RestController
public class PacifisteRestController {
	
	@Autowired
	private PacifisteDao daoPacifiste;

	@RequestMapping(value = "/pacifiste", method = RequestMethod.GET)
	@JsonView(Views.Pacifiste.class)
	public ResponseEntity<List<Pacifiste>> list() {
		return new ResponseEntity<>(daoPacifiste.findAll(), HttpStatus.OK);
	}

	@RequestMapping(value = "/pacifiste/{id}", method = RequestMethod.GET)
	@JsonView(Views.Pacifiste.class)
	public ResponseEntity<Pacifiste> find(@PathVariable("id") Long id) {
		Pacifiste tmp = (Pacifiste) daoPacifiste.find(id);
		if (tmp == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<Pacifiste>(tmp, HttpStatus.OK);
		}
	}
	

	@RequestMapping(value = "/pacifiste", method = RequestMethod.POST)
	public ResponseEntity<Void> create(@RequestBody Pacifiste pacifiste, UriComponentsBuilder uCB) {
		daoPacifiste.create(pacifiste);
		URI uri = uCB.path("/pacifiste/{id}").buildAndExpand(pacifiste.getId()).toUri();
		HttpHeaders header = new HttpHeaders();
		header.setLocation(uri);
		return new ResponseEntity<>(header, HttpStatus.CREATED);

	}

	@RequestMapping(value = "/pacifiste/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@PathVariable("id") Long id, @RequestBody Pacifiste pacifiste) {
		Pacifiste tmp = (Pacifiste) daoPacifiste.find(id);
		if (tmp == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			daoPacifiste.update(pacifiste);
			return new ResponseEntity<>(HttpStatus.OK);
		}
	}

	@RequestMapping(value = "/pacifiste/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
		Pacifiste tmp = (Pacifiste) daoPacifiste.find(id);
		if (tmp == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			daoPacifiste.delete(tmp);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}

}






