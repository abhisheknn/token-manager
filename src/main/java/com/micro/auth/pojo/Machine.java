package com.micro.auth.pojo;

import java.util.Map;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;
import javax.ws.rs.DefaultValue;
import javax.validation.constraints.Pattern;

public class Machine {

private String hostName;
private String uuid;
private String JWToken;
private String macAddress;
private Map<String, Object> controls;

public Map<String, Object> getControls() {
	return controls;
}
public void setControls(Map<String, Object> controls) {
	this.controls = controls;
}
public String getUuid() {
	return uuid;
}
public void setUuid(String uuid) {
	this.uuid = uuid;
}
public String getMacAddress() {
	return macAddress;
}
public void setMacAddress(String macAddress) {
	this.macAddress = macAddress;
}
public String getJWToken() {
	return JWToken;
}
public void setJWToken(String JWToken) {
	this.JWToken = JWToken;
}

public String getName() {
	return hostName;
}
public void setName(String name) {
	this.hostName = name;
}
}
