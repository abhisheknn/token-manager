package com.micro.auth.jpa.repository;

import com.micro.auth.pojo.Machine;
import com.micro.auth.pojo.Tenant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface MachineRepository extends CrudRepository<Machine,String> {

  Machine findByTenantIdAndMacAddress(String tenantId,String macAddress);

}
