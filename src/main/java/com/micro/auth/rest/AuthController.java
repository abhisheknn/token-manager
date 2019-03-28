package com.micro.auth.rest;

import java.util.List;

import javax.validation.Valid;
import javax.ws.rs.core.Response;

import org.springframework.http.ResponseEntity;

import com.micro.auth.pojo.Machine;
import com.micro.auth.pojo.Tenant;


public interface AuthController {

public ResponseEntity register(Machine machine);
public ResponseEntity createMachine(Machine machine);
public ResponseEntity createTenant(Tenant machine);
public Response refreshToken(Machine machine);
public ResponseEntity getMachines(String tenantid);
public ResponseEntity<List<Tenant>> getTenant(String tenantid);
public ResponseEntity updateStatus(Machine machine );

}
