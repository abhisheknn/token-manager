package com.micro.auth.services;

import com.micro.auth.dao.MachineDao;
import com.micro.auth.pojo.Machine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class MachineServiceImpl implements MachineService{

  @Autowired
  MachineDao machineDao;

  @Override
  public void addMachine(Machine machine) {
    machineDao.addMachine(machine);
  }

  @Override
  public Machine getMachine(String tenantId, String macAddress) {
   return machineDao.getMachine(tenantId,macAddress);
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
