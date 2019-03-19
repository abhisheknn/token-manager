package com.micro.auth.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.mapping.Mapper;
import com.datastax.driver.mapping.MappingManager;
import static com.micro.constant.AppConstants.ReplicationStrategy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.micro.auth.pojo.Machine;
import com.micro.cassandra.Cassandra;
import com.micro.cassandra.CassandraConnector;
import com.micro.auth.accessors.MachineAccessor;
import com.micro.auth.constant.AppConstants;


@Component
public class MachineDao {

	@Autowired
	CassandraConnector cassandraConnector;
	
	public void addMachine(Machine machine) {
		try {
		MappingManager manager = new MappingManager(cassandraConnector.getSession());	
		Mapper<Machine> mapper = manager.mapper(Machine.class);
		mapper.save(machine);
		}catch(IllegalArgumentException  e ) {
			e.printStackTrace();
		}
	}

	public Machine getMachine(String tenantId,String macAddress) {
		MappingManager manager = new MappingManager(cassandraConnector.getSession());
		MachineAccessor machineAccessor=manager.createAccessor(MachineAccessor.class);
		Machine machine =machineAccessor.getMachine(tenantId,macAddress);
		return machine;
	}
	
	public Map<String,String> getMachines(String tenantId){
		
		ResultSet resultSet=Cassandra.select(cassandraConnector.getSession(), AppConstants.DOCKERKEYSPACE, AppConstants.MACHINETABLE, "macAddress,status", "tenantid='"+tenantId+"'");
		List<Row> rows= resultSet.all();
		Map<String,String> machines= new HashMap<>();
		for(Row row:rows) {
			machines.put(row.getString("macaddress"),row.getString("status"));
		}
		return machines;
	}
}
