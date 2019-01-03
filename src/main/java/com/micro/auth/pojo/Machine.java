package com.micro.auth.pojo;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;
import javax.ws.rs.DefaultValue;

import com.datastax.driver.mapping.annotations.PartitionKey;
import com.datastax.driver.mapping.annotations.Table;
import com.datastax.driver.mapping.annotations.Transient;

import javax.validation.constraints.Pattern;


@Table(keyspace = "dockerx", name = "dockerenv",
readConsistency = "QUORUM",
writeConsistency = "QUORUM",
caseSensitiveKeyspace = false,
caseSensitiveTable = false)

public class Machine {
@PartitionKey 
private String macAddress;
private String hostName;
private String uuid;
private String JWToken;

@Transient 
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


public String getHostName() {
	return hostName;
}
public void setHostName(String hostName) {
	this.hostName = hostName;
}
public static Map<String,String>  getColoumnsForMachinesTable(){
	Map<String, String> columnConf= new HashMap<>();
	columnConf.put("macAddress", "text PRIMARY KEY");
	columnConf.put("hostName", "text");
	columnConf.put("uuid", "text");
	columnConf.put("JWToken", "text");
	return columnConf;
}

}
