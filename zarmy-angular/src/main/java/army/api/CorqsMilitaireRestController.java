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

import army.dao.CorqsMilitaireDao;
import army.model.CorqsMilitaire;
import army.model.Views;

@RestController
public class CorqsMilitaireRestController {
	
	@Autowired
	private CorqsMilitaireDao daoCorqsMilitaire;

	@RequestMapping(value = "/corqsmilitaire", method = RequestMethod.GET)
	@JsonView(Views.CorqsMilitaire.class)
	public ResponseEntity<List<CorqsMilitaire>> list() {
		return new ResponseEntity<>(daoCorqsMilitaire.findAll(), HttpStatus.OK);
	}

	@RequestMapping(value = "/corqsmilitaire/{id}", method = RequestMethod.GET)
	@JsonView(Views.CorqsMilitaire.class)
	public ResponseEntity<CorqsMilitaire> find(@PathVariable("id") Long id) {
		CorqsMilitaire tmp = (CorqsMilitaire) daoCorqsMilitaire.find(id);
		if (tmp == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<CorqsMilitaire>(tmp, HttpStatus.OK);
		}
	}
	

	@RequestMapping(value = "/corqsmilitaire", method = RequestMethod.POST)
	public ResponseEntity<Void> create(@RequestBody CorqsMilitaire corqsmilitaire, UriComponentsBuilder uCB) {
		daoCorqsMilitaire.create(corqsmilitaire);
		URI uri = uCB.path("/corqsmilitaire/{id}").buildAndExpand(corqsmilitaire.getId()).toUri();
		HttpHeaders header = new HttpHeaders();
		header.setLocation(uri);
		return new ResponseEntity<>(header, HttpStatus.CREATED);

	}

	@RequestMapping(value = "/corqsmilitaire/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@PathVariable("id") Long id, @RequestBody CorqsMilitaire corqsmilitaire) {
		CorqsMilitaire tmp = (CorqsMilitaire) daoCorqsMilitaire.find(id);
		if (tmp == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			daoCorqsMilitaire.update(corqsmilitaire);
			return new ResponseEntity<>(HttpStatus.OK);
		}
	}

	@RequestMapping(value = "/corqsmilitaire/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
		CorqsMilitaire tmp = (CorqsMilitaire) daoCorqsMilitaire.find(id);
		if (tmp == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			daoCorqsMilitaire.delete(tmp);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}

}






