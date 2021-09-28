package com.micro.auth.dao;

import com.micro.auth.jpa.repository.TenantRepository;
import com.micro.auth.pojo.Tenant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("tenantDaoRdbImpl")
@ConditionalOnProperty(prefix = "datasource", name = "type", havingValue = "mysql")
public class TenantDaoRdbImpl implements TenantDao{

  @Autowired
  TenantRepository tenantRepository;

  @Override
  public void create(Tenant tenant) {
    tenantRepository.save(tenant);
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
