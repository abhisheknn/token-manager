package com.micro.auth.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.mapping.Mapper;
import com.datastax.driver.mapping.MappingManager;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import com.micro.auth.pojo.Machine;
import com.micro.cassandra.Cassandra;
import com.micro.cassandra.CassandraConnector;
import com.micro.kafka.KafkaProducer;
import com.micro.auth.accessors.MachineAccessor;
import com.micro.auth.constant.AppConstants;


@Component
public class MachineDaoCassandraImpl implements MachineDao {

	@Autowired
	CassandraConnector cassandraConnector;
	Gson gson= new GsonBuilder().disableHtmlEscaping().create();

	@Override
	public void addMachine(Machine machine) {
		try {
		MappingManager manager = new MappingManager(cassandraConnector.getSession());
		Mapper<Machine> mapper = manager.mapper(Machine.class);
		mapper.save(machine);
		sendToKafka(machine);
		}catch(IllegalArgumentException  e ) {
			e.printStackTrace();
		}
	}

  @Override
	public Machine getMachine(String tenantId,String macAddress) {
		MappingManager manager = new MappingManager(cassandraConnector.getSession());
		MachineAccessor machineAccessor=manager.createAccessor(MachineAccessor.class);
		Machine machine =machineAccessor.getMachine(tenantId,macAddress);
		return machine;
	}

  @Override
	public Map<String,String> getMachines(String tenantId){

		ResultSet resultSet=Cassandra.select(cassandraConnector.getSession(), AppConstants.DOCKERKEYSPACE, AppConstants.MACHINETABLE, "macAddress,status", "tenantid='"+tenantId+"'");
		List<Row> rows= resultSet.all();
		Map<String,String> machines= new HashMap<>();
		for(Row row:rows) {
			machines.put(row.getString("macaddress"),row.getString("status"));
		}
		return machines;
	}

  @Override
	public  String updateStatus(Machine machine) {
		try {
			Cassandra.update(cassandraConnector.getSession(), AppConstants.DOCKERKEYSPACE, AppConstants.MACHINETABLE,"status="+"'"+machine.getStatus()+"'","tenantid='"+machine.getTenantId()+"'"+" AND "+"macaddress='"+machine.getMacAddress()+"'" );
			sendToKafka(machine);
		}catch(IllegalArgumentException  e ) {
				e.printStackTrace();
			}
		catch(Exception  e ) {
			e.printStackTrace();
		}
		return machine.getStatus();
	}

	private void sendToKafka(Machine machine) {
		Properties producerConfig = new Properties();
		producerConfig.put("bootstrap.servers", System.getenv("KAFKABROKERS"));
		KafkaProducer.build().withConfig(producerConfig).produce("machine-status",machine.getTenantId()+"_"+machine.getMacAddress(),machine.getStatus());
	}
}
