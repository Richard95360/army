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

import army.metier.IAdminGuerrier;
import army.model.User;
import army.model.Views;


@RestController
public class UserRestController {

	@Autowired
	private IAdminGuerrier adminGuerrier;
//	private UserDao daoUser;

	@RequestMapping(value = "/user", method = RequestMethod.GET)
	@JsonView(Views.User.class)
	public ResponseEntity<List<User>> list() {
//		return new ResponseEntity<>(daoUser.findAll(), HttpStatus.OK);
		return new ResponseEntity<>(adminGuerrier.listUsers(), HttpStatus.OK);
	}

	@RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
	@JsonView(Views.User.class)
	public ResponseEntity<User> find(@PathVariable("id") Long id) {
//		User tmp = (User) daoUser.find(id);
		User tmp = (User) adminGuerrier.getUser(id);
		if (tmp == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<User>(tmp, HttpStatus.OK);
		}
	}

	@RequestMapping(value = "/user/username/{username}", method = RequestMethod.GET)
	@JsonView(Views.User.class)
	public ResponseEntity<User> find(@PathVariable("username") String username) {
//		User tmp = (User) daoUser.find(username);
		User tmp = (User) adminGuerrier.getUser(username);
		if (tmp == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<User>(tmp, HttpStatus.OK);
		}
	}

	@RequestMapping(value = "/user", method = RequestMethod.POST)
	public ResponseEntity<Void> create(@RequestBody User user, UriComponentsBuilder uCB) {

//		daoUser.create(user);
		adminGuerrier.ajouterUser(user);
		URI uri = uCB.path("/user/{id}").buildAndExpand(user.getId()).toUri();
		HttpHeaders header = new HttpHeaders();
		header.setLocation(uri);
		return new ResponseEntity<>(header, HttpStatus.CREATED);

	}

	@RequestMapping(value = "/user/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@PathVariable("id") Long id, @RequestBody User user) {
//		User tmp = (User) daoUser.find(id);
		User tmp = (User) adminGuerrier.getUser(id);
		if (tmp == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
//			daoUser.update(user);
			adminGuerrier.modifierUser(user);
			return new ResponseEntity<>(HttpStatus.OK);
		}
	}

	@RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
//		User tmp = (User) daoUser.find(id);
		User tmp = (User) adminGuerrier.getUser(id);
		if (tmp == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
//			daoUser.delete(tmp);
			adminGuerrier.supprimerUser(tmp);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}

//	@RequestMapping(value = "/role", method = RequestMethod.GET)
//	@JsonView(Views.User.class)
//	public ResponseEntity<Role[]> listRoles() {
//		return new ResponseEntity<>(Role.values(), HttpStatus.OK);
//	}

}
