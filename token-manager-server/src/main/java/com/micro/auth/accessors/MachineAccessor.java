package com.micro.auth.accessors;

import com.datastax.driver.mapping.annotations.Accessor;
import com.datastax.driver.mapping.annotations.Param;
import com.datastax.driver.mapping.annotations.Query;
import com.micro.auth.constant.AppConstants;
import com.micro.auth.pojo.Machine;

@Accessor
public interface MachineAccessor {
	@Query("SELECT * FROM "+ AppConstants.DOCKERKEYSPACE+"."+AppConstants.MACHINETABLE+" WHERE tenantId=:tenantId and macAddress=:macAddress")
	public Machine getMachine(@Param("tenantId") String tenantId,@Param("macAddress") String macAddress);
}
