package com.micro.auth.client;


import com.micro.auth.pojo.Machine;
import feign.Param;
import feign.RequestLine;
import org.apache.http.client.protocol.ResponseContentEncoding;

public interface TokenManagerServiceClient {
  @RequestLine("GET /v1/auth/machine/pojo/{tenantId}/{macAddress}")
  Machine getMachine(@Param("tenantId")  String tenantId, @Param("macAddress") String macAddress);
}
