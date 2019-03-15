package com.micro.auth.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.datastax.driver.mapping.Mapper;
import com.datastax.driver.mapping.MappingManager;
import static com.micro.constant.AppConstants.ReplicationStrategy;
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

	public Machine getMachine(String macAddress) {
		MappingManager manager = new MappingManager(cassandraConnector.getSession());
		MachineAccessor machineAccessor=manager.createAccessor(MachineAccessor.class);
		Machine machine =machineAccessor.getMachine(macAddress);
		return machine;
	}
	
}
