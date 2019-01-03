package com.micro.auth.services;

import com.micro.auth.pojo.Machine;

public interface AuthService {
	public String create(Machine machine);
	public String getPublicKey();
	public String refreshToken(String entityName);
	public String register(Machine machine);
}
