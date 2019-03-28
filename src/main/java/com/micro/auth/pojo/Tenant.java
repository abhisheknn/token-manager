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
import com.micro.auth.constant.AppConstants;

import javax.validation.constraints.Pattern;

@Table(keyspace = AppConstants.DOCKERKEYSPACE, name = AppConstants.TENANTTABLE, readConsistency = "QUORUM", writeConsistency = "QUORUM", caseSensitiveKeyspace = false, caseSensitiveTable = false)
public class Tenant {

	@PartitionKey
	private String tenantId;
	private String JWToken;
	private String status;
	private String license;
	private String cassandraKeySpace;

	@Transient
	private Map<String, Object> controls;

	public Map<String, Object> getControls() {
		return controls;
	}

	public void setControls(Map<String, Object> controls) {
		this.controls = controls;
	}

	
	public String getTenantId() {
		return tenantId;
	}

	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}

	public String getJWToken() {
		return JWToken;
	}

	public void setJWToken(String jWToken) {
		JWToken = jWToken;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getLicense() {
		return license;
	}

	public void setLicense(String license) {
		this.license = license;
	}

	public String getCassandraKeySpace() {
		return cassandraKeySpace;
	}

	public void setCassandraKeySpace(String cassandraKeySpace) {
		this.cassandraKeySpace = cassandraKeySpace;
	}

	
	public static Map<String, String> getColoumnsForTenantTable() {
		Map<String, String> columnConf = new HashMap<>();
		columnConf.put("tenantId", "text");
		columnConf.put("JWToken", "text");
		columnConf.put("status", "text");
		columnConf.put("license", "text");
		//columnConf.put("controls", "map<text,text>");
		columnConf.put("cassandraKeySpace", "text");
		columnConf.put("PRIMARY KEY", "(tenantId)");
		return columnConf;
	}

}
