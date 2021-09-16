package com.micro.auth.jpa.repository;

import com.micro.auth.pojo.Tenant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TenantRepository extends JpaRepository<Tenant,String>{
}
