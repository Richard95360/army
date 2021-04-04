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

import army.dao.CommandeDao;
import army.model.Commande;
import army.model.Views;



@RestController
public class CommandeRestController {

	@Autowired
	private CommandeDao daoCommande;

	@RequestMapping(value = "/commande", method = RequestMethod.GET)
	@JsonView(Views.Commande.class)
	public ResponseEntity<List<Commande>> list() {
		return new ResponseEntity<>(daoCommande.findAll(), HttpStatus.OK);
	}

	@RequestMapping(value = "/commande/{id}", method = RequestMethod.GET)
	@JsonView(Views.Commande.class)
	public ResponseEntity<Commande> find(@PathVariable("id") Long id) {
		Commande tmp = (Commande) daoCommande.find(id);
		if (tmp == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<Commande>(tmp, HttpStatus.OK);
		}
	}

	@RequestMapping(value = "/commande", method = RequestMethod.POST)
	public ResponseEntity<Void> create(@RequestBody Commande commande, UriComponentsBuilder uCB) {

		daoCommande.create(commande);
		URI uri = uCB.path("/commande/{id}").buildAndExpand(commande.getId()).toUri();
		HttpHeaders header = new HttpHeaders();
		header.setLocation(uri);
		return new ResponseEntity<>(header, HttpStatus.CREATED);

	}

	@RequestMapping(value = "/commande/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@PathVariable("id") Long id, @RequestBody Commande commande) {
		Commande tmp = (Commande) daoCommande.find(id);
		if (tmp == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			daoCommande.update(commande);
			return new ResponseEntity<>(HttpStatus.OK);
		}
	}

	@RequestMapping(value = "/commande/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
		Commande tmp = (Commande) daoCommande.find(id);
		if (tmp == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			daoCommande.delete(tmp);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}

}
