package com.micro.auth.rest;

import java.net.URI;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.micro.auth.pojo.Machine;
import com.micro.auth.services.AuthService;

@RestController
@RequestMapping("/auth")
public class AuthControllerImpl implements AuthController {
	
	@Autowired
	AuthService authService;
	
	@CrossOrigin(origins = "*")
	@RequestMapping(value="/create",method = RequestMethod.POST,consumes=MediaType.APPLICATION_JSON_VALUE)
	@Override
	public ResponseEntity create( @Valid @RequestBody Machine entity) {
		authService.create(entity);
		URI location =ServletUriComponentsBuilder.fromCurrentRequest().path("{name}").buildAndExpand(entity.getHostName()).toUri();
		return ResponseEntity.created(location).build();
	}
	
	@RequestMapping(value="/refreshToken",method = RequestMethod.POST,consumes=MediaType.APPLICATION_JSON_VALUE)
	@Override
	public Response refreshToken(@RequestBody Machine entity) {
		return Response.ok(authService.refreshToken(entity.getHostName())).build();
	}
	
	@CrossOrigin(origins = "*")
	@RequestMapping(value="/getPublicKey",method = RequestMethod.GET,consumes=MediaType.APPLICATION_JSON_VALUE)
	
	public ResponseEntity<String> getPublicKey() {
		return ResponseEntity.ok(authService.getPublicKey());
	}

	@Override
	@CrossOrigin(origins = "*")
	@RequestMapping(value="/register",method = RequestMethod.POST,consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity register(@Valid @RequestBody Machine machine) {
	return ResponseEntity.ok(authService.register(machine));	
	}
}
