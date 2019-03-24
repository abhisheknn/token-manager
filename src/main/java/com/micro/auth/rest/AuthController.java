package com.micro.auth.rest;

import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.micro.auth.pojo.Machine;


public interface AuthController {

public ResponseEntity register(Machine machine);
public ResponseEntity create(Machine machine);
public Response refreshToken(Machine machine);
public ResponseEntity getMachines(String tenantid);
public ResponseEntity updateStatus(Machine machine );

}
