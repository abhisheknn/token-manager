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
			if(e.getMessage().contains("Keyspace")) {
				createKeySpace();
				createMachineTable();
			}
			if(e.getMessage().contains("Table")) {
				createMachineTable();
			}
		}
	}

	public Machine getMachine(String macAddress) {
		MappingManager manager = new MappingManager(cassandraConnector.getSession());
		MachineAccessor machineAccessor=manager.createAccessor(MachineAccessor.class);
		Machine machine =machineAccessor.getMachine(macAddress);
		return machine;
	}
	
	private void createKeySpace() {
	Cassandra.createKeySpace(cassandraConnector.getSession(),AppConstants.DOCKERKEYSPACE , ReplicationStrategy.SimpleStrategy, 1);
		
	}

	private void createMachineTable() {
		Cassandra.createTable(cassandraConnector.getSession(),AppConstants.DOCKERKEYSPACE , AppConstants.DOCKERENV, Machine.getColoumnsForMachinesTable());
		
	}
}
