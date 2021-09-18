package com.micro.auth.cassandra;

import org.springframework.stereotype.Component;

import com.micro.auth.constant.AppConstants;
import com.micro.auth.pojo.Machine;
import com.micro.auth.pojo.Tenant;
import com.micro.cassandra.Cassandra;
import com.micro.constant.AppConstants.ReplicationStrategy;

@Component
public class CassandraConnector

  extends com.micro.cassandra.CassandraConnector
  //
  {
	CassandraConnector(){
//		String[] nodes=AppConstants.CASSANDRA_HOST.split(",");
//	super.connect(nodes, Integer.parseInt(AppConstants.CASSANDRA_PORT));
//	Cassandra.createKeySpace(this.getSession(),AppConstants.DOCKERKEYSPACE , ReplicationStrategy.SimpleStrategy, 1);
//	Cassandra.createTable(this.getSession(),AppConstants.DOCKERKEYSPACE , AppConstants.MACHINETABLE, Machine.getColoumnsForMachinesTable());
//	Cassandra.createTable(this.getSession(),AppConstants.DOCKERKEYSPACE , AppConstants.TENANTTABLE, Tenant.getColoumnsForTenantTable());
	}
}
