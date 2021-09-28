package com.micro.auth.pojo;

import com.datastax.driver.mapping.annotations.PartitionKey;
import com.datastax.driver.mapping.annotations.Table;
import com.datastax.driver.mapping.annotations.Transient;
import com.micro.auth.constant.AppConstants;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.HashMap;
import java.util.Map;


@Table(keyspace = AppConstants.DOCKERKEYSPACE, name = AppConstants.TENANTTABLE, readConsistency = "QUORUM", writeConsistency = "QUORUM", caseSensitiveKeyspace = false, caseSensitiveTable = false)
@javax.persistence.Table(name = AppConstants.TENANTTABLE)
@Entity
public class Tenant {

	@PartitionKey
  @Id
  //@GeneratedValue( strategy = GenerationType.IDENTITY)
	private String tenantId;
	private String JWToken;
	private String status;
	private String license;
	@javax.persistence.Transient
	private String cassandraKeySpace;

	@Transient
  @javax.persistence.Transient
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

