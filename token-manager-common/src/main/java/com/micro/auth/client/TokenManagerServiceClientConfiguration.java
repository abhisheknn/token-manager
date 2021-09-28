package com.micro.auth.client;

import com.micro.discovery.FeignClientProvider;
import com.micro.discovery.RibbonLBClientFactory;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.client.ClientFactory;
import com.netflix.client.IClient;
import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.*;
import feign.Client;
import feign.Feign;
import feign.Logger;
import feign.Request;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import feign.okhttp.OkHttpClient;
import feign.ribbon.LBClient;
import feign.ribbon.LBClientFactory;
import feign.ribbon.RibbonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.netflix.ribbon.StaticServerList;

import java.util.List;

public class TokenManagerServiceClientConfiguration {
  public static final String TOKEN_MANAGER_SERVICE = "TOKEN-MANAGER-SERVICE";
  private final TokenManagerServiceClient tokenManagerServiceClient;
  public TokenManagerServiceClientConfiguration( DiscoveryClient discoveryClient) {
    FeignClientProvider feignClientProvider= new FeignClientProvider();
    tokenManagerServiceClient = (TokenManagerServiceClient) feignClientProvider.getServiceClient(discoveryClient,TokenManagerServiceClient.class, TOKEN_MANAGER_SERVICE);
  }
  public TokenManagerServiceClient getTokenManagerServiceClient() {
    return tokenManagerServiceClient;
  }
}
