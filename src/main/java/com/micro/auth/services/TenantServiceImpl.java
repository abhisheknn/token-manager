package com.micro.auth.services;

import com.micro.auth.dao.TenantDao;
import com.micro.auth.pojo.Tenant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TenantServiceImpl implements TenantService{
  @Autowired
  TenantDao tenantDao;

  @Override
  public void create(Tenant tenant) {
    tenantDao.create(tenant);
  }

  @Override
  public List<Tenant> getTenant(String tenantId) {
    return null;
  }

  @Override
  public boolean updateTenantStatus(Tenant tenant) {
    return false;
  }
}
