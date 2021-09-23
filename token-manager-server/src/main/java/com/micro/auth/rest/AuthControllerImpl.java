package com.micro.auth.rest;

import java.net.URI;
import java.util.List;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.datastax.driver.mapping.annotations.QueryParameters;
import com.micro.auth.pojo.Machine;
import com.micro.auth.pojo.Tenant;
import com.micro.auth.services.AuthService;

@RestController
@RequestMapping("v1/auth")
public class AuthControllerImpl implements AuthController {

	@Autowired
	AuthService authService;


	@RequestMapping(value="/create/machine",method = RequestMethod.POST,consumes=MediaType.APPLICATION_JSON_VALUE)
	@Override
	public ResponseEntity createMachine( @Valid @RequestBody Machine entity) {
		authService.createMachine(entity);
		URI location =ServletUriComponentsBuilder.fromCurrentRequest().path("{name}").buildAndExpand(entity.getHostName()).toUri();
		return ResponseEntity.created(location).build();
	}


	@RequestMapping(value="/create/tenant",method = RequestMethod.POST,consumes=MediaType.APPLICATION_JSON_VALUE)
	@Override
	public ResponseEntity createTenant( @Valid @RequestBody Tenant tenant) {
		authService.createTenant(tenant);
		URI location =ServletUriComponentsBuilder.fromCurrentRequest().path("{name}").buildAndExpand(tenant.getTenantId()).toUri();
		return ResponseEntity.created(location).build();
	}

	@RequestMapping(value="/refreshToken",method = RequestMethod.POST,consumes=MediaType.APPLICATION_JSON_VALUE)
  public Response refreshToken(@RequestBody Machine entity) {
		return Response.ok(authService.refreshToken(entity.getHostName())).build();
	}


	@RequestMapping(value="/getPublicKey",method = RequestMethod.GET,consumes=MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<String> getPublicKey() {
		return ResponseEntity.ok(authService.getPublicKey());
	}

	@Override
  @RequestMapping(value="/register",method = RequestMethod.POST,consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity register(@Valid @RequestBody Machine machine) {
	return ResponseEntity.ok(authService.register(machine));
	}

  @Override
  @RequestMapping(value="/machine",method = RequestMethod.GET,consumes=MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity getMachine(@Valid @RequestBody Machine machine) {
    return ResponseEntity.ok(authService.getMachine(machine));
  }

  //@Override
  @RequestMapping(value="/machine/pojo/{tenantId}/{macAddress}",method = RequestMethod.GET)
  public Machine getMachinePojo(@PathVariable(name="tenantId") String tenantId, @PathVariable(name="macAddress") String macAddress) {
    return authService.getMachine(tenantId,macAddress);
  }


  @Override
  @RequestMapping(value="/getMachines",method = RequestMethod.GET)
	public ResponseEntity getMachines(@RequestParam("tenantid") String tenantId) {
	return ResponseEntity.ok(authService.getMachines(tenantId));
	}

	@Override
  @RequestMapping(value="/update/status",method = RequestMethod.POST,consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity updateStatus(@Valid @RequestBody Machine machine) {
		return ResponseEntity.ok(authService.updateStatus(machine));
	}

	@Override
  @RequestMapping(value="/get/tenant",method = RequestMethod.GET)
	public ResponseEntity<List<Tenant>> getTenant(@RequestParam(value="tenantid",required=false) String tenantid) {
		return ResponseEntity.ok(authService.getTenant(tenantid));
	}
}
