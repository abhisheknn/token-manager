package com.micro.auth.client;

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

  private final TokenManagerServiceClient tokenManagerServiceClient;
  public TokenManagerServiceClientConfiguration( DiscoveryClient discoveryClient) {
    TokenManagerLBClientFactory tokenManagerRibbonClient=new TokenManagerLBClientFactory(discoveryClient);
    tokenManagerRibbonClient.create("TOKEN-MANAGER-SERVICE");
    RibbonClient ribbonClient=RibbonClient.builder().lbClientFactory(tokenManagerRibbonClient).build();
    tokenManagerServiceClient = Feign.builder()
              .client(ribbonClient)
              .decoder(new JacksonDecoder())
              .encoder(new JacksonEncoder())
              .logLevel(Logger.Level.FULL)
              .options(new Request.Options(30000, 30000))
              .target(TokenManagerServiceClient.class,"http://TOKEN-MANAGER-SERVICE");
  }

  public TokenManagerServiceClient getTokenManagerServiceClient() {
    return tokenManagerServiceClient;
  }

private class TokenManagerLBClientFactory implements LBClientFactory {
  DiscoveryClient discoveryClient;
  TokenManagerLBClientFactory(DiscoveryClient discoveryClient){
      this.discoveryClient=discoveryClient;
    }

  @Override
  public LBClient create(String s) {

    List<ServiceInstance> instances = discoveryClient.getInstances(s);
    IClientConfig iClientConfig= ClientFactory.getNamedConfig(s);
    IRule iRule= new RoundRobinRule();
    IPing iPing= new NoOpPing();
    Server[] serverArray = new Server[instances.size()];
    int index=0;
    for (ServiceInstance serverInstance:instances){
      serverArray[index++]=new Server(serverInstance.getHost(),serverInstance.getPort());
    }
    StaticServerList serverList= new StaticServerList(serverArray);
    DynamicServerListLoadBalancer dynamicServerListLoadBalancer = new DynamicServerListLoadBalancer(iClientConfig, iRule, iPing, serverList, null);

    LBClient lbClient= LBClient.create(dynamicServerListLoadBalancer,iClientConfig);
    return  lbClient;
  }
 }
}
