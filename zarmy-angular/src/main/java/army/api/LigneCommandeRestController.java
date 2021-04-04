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

import army.dao.LigneCommandeDao;
import army.model.LigneCommande;
import army.model.Views;


@RestController
public class LigneCommandeRestController {

	@Autowired
	private LigneCommandeDao daoLigneCommande;

	@RequestMapping(value = "/lignecommande", method = RequestMethod.GET)
	@JsonView(Views.LigneCommande.class)
	public ResponseEntity<List<LigneCommande>> list() {
		return new ResponseEntity<>(daoLigneCommande.findAll(), HttpStatus.OK);
	}

	@RequestMapping(value = "/lignecommande/{id}", method = RequestMethod.GET)
	@JsonView(Views.LigneCommande.class)
	public ResponseEntity<LigneCommande> find(@PathVariable("id") Long id) {
		LigneCommande tmp = (LigneCommande) daoLigneCommande.find(id);
		if (tmp == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<LigneCommande>(tmp, HttpStatus.OK);
		}
	}

	@RequestMapping(value = "/lignecommande", method = RequestMethod.POST)
	public ResponseEntity<Void> create(@RequestBody LigneCommande ligneCommande, UriComponentsBuilder uCB) {

		daoLigneCommande.create(ligneCommande);
		URI uri = uCB.path("/ligneCommande/{id}").buildAndExpand(ligneCommande.getId()).toUri();
		HttpHeaders header = new HttpHeaders();
		header.setLocation(uri);
		return new ResponseEntity<>(header, HttpStatus.CREATED);

	}

	@RequestMapping(value = "/lignecommande/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@PathVariable("id") Long id, @RequestBody LigneCommande ligneCommande) {
		LigneCommande tmp = (LigneCommande) daoLigneCommande.find(id);
		if (tmp == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			daoLigneCommande.update(ligneCommande);
			return new ResponseEntity<>(HttpStatus.OK);
		}
	}

	@RequestMapping(value = "/lignecommande/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
		LigneCommande tmp = (LigneCommande) daoLigneCommande.find(id);
		if (tmp == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			daoLigneCommande.delete(tmp);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}

}
