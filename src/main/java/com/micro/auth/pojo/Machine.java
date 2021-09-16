package com.micro.auth.pojo;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;
import javax.ws.rs.DefaultValue;

import com.datastax.driver.mapping.annotations.PartitionKey;
import com.datastax.driver.mapping.annotations.Table;
import com.datastax.driver.mapping.annotations.Transient;
import com.micro.auth.constant.AppConstants;

import javax.validation.constraints.Pattern;


@Table(keyspace = AppConstants.DOCKERKEYSPACE, name = AppConstants.MACHINETABLE, readConsistency = "QUORUM", writeConsistency = "QUORUM", caseSensitiveKeyspace = false, caseSensitiveTable = false)
@javax.persistence.Table(name = AppConstants.MACHINETABLE)
@Entity
public class Machine {
	@PartitionKey
  @Id
	private String macAddress;
	private String hostName;
	private String uuid;
	private String JWToken;
	private String tenantId;
	private String status;

	@Transient
  @javax.persistence.Transient
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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

	public String getTenantId() {
		return tenantId;
	}

	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}

	public static Map<String, String> getColoumnsForMachinesTable() {
		Map<String, String> columnConf = new HashMap<>();
		columnConf.put("macAddress", "text");
		columnConf.put("hostName", "text");
		columnConf.put("uuid", "text");
		columnConf.put("JWToken", "text");
		columnConf.put("tenantId", "text");
		columnConf.put("status", "text");
		columnConf.put("PRIMARY KEY", "(tenantId , macAddress)");
		return columnConf;
	}

}
