package com.micro.auth.services;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import com.micro.auth.pojo.Machine;
import com.micro.auth.pojo.Tenant;

public interface AuthService {
	public String getPublicKey();
	public String refreshToken(String entityName);
	public String register(Machine machine);
	public Map<String,String> getMachines(String tenantId);
	public String updateStatus(Machine machine);
	public String createMachine(Machine machine);
	public String createTenant(Tenant tenant);
	public List<Tenant> getTenant(String tenant);
	
}
