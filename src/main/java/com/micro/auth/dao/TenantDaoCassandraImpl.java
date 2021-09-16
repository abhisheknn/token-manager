package com.micro.auth.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.mapping.Mapper;
import com.datastax.driver.mapping.MappingManager;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.micro.auth.constant.AppConstants;
import com.micro.auth.pojo.Machine;
import com.micro.auth.pojo.Tenant;
import com.micro.cassandra.Cassandra;
import com.micro.cassandra.CassandraConnector;
import com.micro.constant.AppConstants.ReplicationStrategy;

@Component
public class TenantDaoCassandraImpl implements TenantDao {

	@Autowired
	CassandraConnector cassandraConnector;

	Gson gson = new GsonBuilder().disableHtmlEscaping().create();


	@Override
	public void create(Tenant tenant) {
		saveTenant(tenant);
	}

	private void saveTenant(Tenant tenant) {
		try {
			MappingManager manager = new MappingManager(cassandraConnector.getSession());
			Mapper<Tenant> mapper = manager.mapper(Tenant.class);
			mapper.save(tenant);
			createKeySpace(tenant.getCassandraKeySpace());
			}catch(IllegalArgumentException  e ) {
				e.printStackTrace();
			}
	}

	private void createKeySpace(String keySpace) {
		Cassandra.createKeySpace(cassandraConnector.getSession(), keySpace, ReplicationStrategy.SimpleStrategy, 1);
	}

	@Override
	public List<Tenant> getTenant(String tenantId) {
		ResultSet resultSet = null;
		List<Tenant> tenants= new ArrayList<>();
		try {
			if(null!=tenantId)
			resultSet=Cassandra.select(cassandraConnector.getSession(), AppConstants.DOCKERKEYSPACE, AppConstants.TENANTTABLE, "*", "tenantid='"+tenantId+"'");
			else {
				resultSet=Cassandra.select(cassandraConnector.getSession(), AppConstants.DOCKERKEYSPACE, AppConstants.TENANTTABLE, "*");
			}

			List<Row> rows= resultSet.all();
			for(Row row:rows) {
				Tenant tenant= new Tenant();
				tenant.setTenantId(row.getString("tenantId"));
				tenant.setJWToken(row.getString("JWToken"));
				tenant.setStatus(row.getString("status"));
				tenant.setLicense(row.getString("license"));
				tenant.setCassandraKeySpace(row.getString("cassandraKeySpace"));
				tenants.add(tenant);

			}
		}catch (Exception e) {
				e.printStackTrace();
			}
		return tenants;
	}

	@Override
	public boolean updateTenantStatus(Tenant tenant) {
		saveTenant(tenant);
		return true;
	}

}
