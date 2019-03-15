package com.micro.auth.cassandra;

import org.springframework.stereotype.Component;

import com.micro.auth.constant.AppConstants;

@Component
public class CassandraConnector extends com.micro.cassandra.CassandraConnector {
	CassandraConnector(){
		String[] nodes=AppConstants.CASSANDRA_HOST.split(",");
	super.connect(nodes, Integer.parseInt(AppConstants.CASSANDRA_PORT));	
	}
}
