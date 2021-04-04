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

import army.dao.RoleDao;
import army.model.Role;
import army.model.RoleEnum;
import army.model.Views;



@RestController
public class RoleRestController {

	@Autowired
//	private IAdminCategorie adminCategorie;
		private RoleDao daoRole;

	@RequestMapping(value = "/role", method = RequestMethod.GET)
	@JsonView(Views.Role.class)
	public ResponseEntity<List<Role>> list() {
		return new ResponseEntity<>(daoRole.findAll(), HttpStatus.OK);
	}

	@RequestMapping(value = "/role/{id}", method = RequestMethod.GET)
	@JsonView(Views.Role.class)
	public ResponseEntity<Role> find(@PathVariable("id") Long id) {
		Role tmp = (Role) daoRole.find(id);
		if (tmp == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<Role>(tmp, HttpStatus.OK);
		}
	}

	@RequestMapping(value = "/role", method = RequestMethod.POST)
	public ResponseEntity<Void> create(@RequestBody Role role, UriComponentsBuilder uCB) {
		daoRole.create(role);
		URI uri = uCB.path("/role/{id}").buildAndExpand(role.getId()).toUri();
		HttpHeaders header = new HttpHeaders();
		header.setLocation(uri);
		return new ResponseEntity<>(header, HttpStatus.CREATED);
	}
	
//	@RequestMapping(value = "/role/{user}", method = RequestMethod.POST)
//	public ResponseEntity<Void> create(@RequestBody Role role, @PathVariable("user") Long user, UriComponentsBuilder uCB) {
//		adminCategorie.attribuerRole(role, user);
//		URI uri = uCB.path("/role/{id}").buildAndExpand(role.getId()).toUri();
//		HttpHeaders header = new HttpHeaders();
//		header.setLocation(uri);
//		return new ResponseEntity<>(header, HttpStatus.CREATED);
//	}

	@RequestMapping(value = "/role/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@PathVariable("id") Long id, @RequestBody Role role) {
		Role tmp = (Role) daoRole.find(id);
		if (tmp == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			daoRole.update(role);
			return new ResponseEntity<>(HttpStatus.OK);
		}
	}

	@RequestMapping(value = "/role/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
		Role tmp = (Role) daoRole.find(id);
		if (tmp == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			daoRole.delete(tmp);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}

	@RequestMapping(value = "/role/enum", method = RequestMethod.GET)
	@JsonView(Views.Role.class)
	public ResponseEntity<RoleEnum[]> listRoles() {
		return new ResponseEntity<>(RoleEnum.values(), HttpStatus.OK);
	}

}
