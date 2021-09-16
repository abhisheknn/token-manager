package com.micro.auth.jpa.repository;

import com.micro.auth.pojo.Machine;
import com.micro.auth.pojo.Tenant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MachineRepository extends JpaRepository<Machine,String>{
}
