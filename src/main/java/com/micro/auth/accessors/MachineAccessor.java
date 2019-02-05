package com.micro.auth.accessors;

import com.datastax.driver.mapping.annotations.Accessor;
import com.datastax.driver.mapping.annotations.Param;
import com.datastax.driver.mapping.annotations.Query;
import com.micro.auth.pojo.Machine;

@Accessor
public interface MachineAccessor {
	@Query("SELECT * FROM dockerx.dockerenv WHERE macAddress=:macAddress")
	public Machine getMachine(@Param("macAddress") String macAddress);
}
