package com.micro.auth.cassandra;

import org.springframework.stereotype.Component;

import com.micro.auth.constant.AppConstants;


@Component
public class CassandraConnector extends com.micro.cassandra.CassandraConnector {
	CassandraConnector(){
	super.connect(AppConstants.CASSANDRA_HOST, Integer.parseInt(AppConstants.CASSANDRA_PORT));	
	}
}
