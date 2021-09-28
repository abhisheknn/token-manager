package com.micro.auth.client;


import com.micro.auth.pojo.Machine;
import feign.Headers;
import feign.Param;
import feign.RequestLine;
import feign.Response;
import org.apache.http.client.protocol.ResponseContentEncoding;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface TokenManagerServiceClient {
  @RequestLine("GET /v1/auth/machine/pojo/{tenantId}/{macAddress}")
  Machine getMachine(@Param("tenantId")  String tenantId, @Param("macAddress") String macAddress);

  @RequestLine("GET /v1/auth/getPublicKey")
  @Headers("Content-Type: application/json")
  Map<String,String> getPublicKey();
}
