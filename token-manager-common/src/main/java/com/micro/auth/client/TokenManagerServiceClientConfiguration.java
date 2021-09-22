package com.micro.auth.client;

import feign.Client;
import feign.Feign;
import feign.Logger;
import feign.Request;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import feign.okhttp.OkHttpClient;
import feign.ribbon.RibbonClient;

public class TokenManagerServiceClientConfiguration {

  private final TokenManagerServiceClient tokenManagerServiceClient;


  public TokenManagerServiceClientConfiguration() {

    RibbonClient ribbonClient=RibbonClient.builder().build();
    tokenManagerServiceClient = Feign.builder()
              //.client(ribbonClient)
                .client(new OkHttpClient())
              .decoder(new JacksonDecoder())
              .encoder(new JacksonEncoder())
              .logLevel(Logger.Level.FULL)
              .options(new Request.Options(30000, 30000))
              .target(TokenManagerServiceClient.class,"http://localhost:8004");
  }

  public TokenManagerServiceClient getTokenManagerServiceClient() {
    return tokenManagerServiceClient;
  }

}
