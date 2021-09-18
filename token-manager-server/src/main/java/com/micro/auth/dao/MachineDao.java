package com.micro.auth.dao;

import com.micro.auth.pojo.Machine;

import java.util.Map;

public interface MachineDao {
  public void addMachine(Machine machine);
  Machine getMachine(String tenantId, String macAddress);
  Map<String,String> getMachines(String tenantId);
  String updateStatus(Machine machine);

}
