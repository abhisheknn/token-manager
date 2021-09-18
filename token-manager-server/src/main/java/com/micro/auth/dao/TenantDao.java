package com.micro.auth.dao;

import java.util.List;

import com.datastax.driver.core.ResultSet;
import com.micro.auth.pojo.Tenant;

public interface TenantDao {
	public void create(Tenant tenant);
	public List<Tenant> getTenant(String tenantId);
	public boolean updateTenantStatus(Tenant tenant);
}
