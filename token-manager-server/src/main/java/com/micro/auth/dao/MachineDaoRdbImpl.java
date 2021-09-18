package com.micro.auth.dao;

import com.micro.auth.jpa.repository.MachineRepository;
import com.micro.auth.pojo.Machine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component("machineDaoRdbImpl")
@ConditionalOnProperty(prefix = "datasource", name = "type", havingValue = "mysql")
public class MachineDaoRdbImpl implements MachineDao{

  @Autowired
  MachineRepository machineRepository;

  @Override
  public void addMachine(Machine machine) {
    machineRepository.save(machine);
  }

  @Override
  public Machine getMachine(String tenantId, String macAddress) {
    return machineRepository.findByTenantIdAndMacAddress(tenantId,macAddress);
  }

  @Override
  public Map<String, String> getMachines(String tenantId) {
    return null;
  }

  @Override
  public String updateStatus(Machine machine) {
    return null;
  }
}
