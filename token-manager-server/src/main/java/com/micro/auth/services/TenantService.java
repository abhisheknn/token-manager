package com.micro.auth.services;

import com.micro.auth.pojo.Tenant;

import java.util.List;

public interface TenantService {
  public void create(Tenant tenant);
  public List<Tenant> getTenant(String tenantId);
  public boolean updateTenantStatus(Tenant tenant);
}

