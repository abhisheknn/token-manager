package com.micro.auth.services;

import java.util.Map;

import com.micro.auth.pojo.Machine;

public interface AuthService {
	public String create(Machine machine);
	public String getPublicKey();
	public String refreshToken(String entityName);
	public String register(Machine machine);
	public Map<String,String> getMachines(String tenantId);
	public String updateStatus(Machine machine);
}
