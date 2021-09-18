package com.micro.auth.configuration;

import com.micro.auth.dao.MachineDao;
import com.micro.auth.dao.MachineDaoCassandraImpl;
import com.micro.auth.dao.MachineDaoRdbImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class AppConfiguration {

//  @ConditionalOnProperty(prefix = "datasource", name = "type", havingValue = "mysql")
//  @Bean
//  @Primary
//  public MachineDao getMachineDaoRdb(){
//    return new MachineDaoRdbImpl();
//  }
//
//  @ConditionalOnProperty(prefix = "datasource", name = "type", havingValue = "cassandra")
//  public MachineDao getMachineDaoCassandra(){
//    return new MachineDaoCassandraImpl();
//  }

}
