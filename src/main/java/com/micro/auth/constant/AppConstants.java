package com.micro.auth.constant;

public class AppConstants {
public static final String SIGNINGALGO = "SHA256withRSA";
public static final String ENTITYNAME="name";
public static final String CERTALIAS="jwtsigningkey";
public static final String keyStorePassword="wintermore";
public static final String keyStoreName="keyserverstore.keystore";
public static final String JWTOKEN = "jwtoken";
public static final String CASSANDRA_HOST=System.getenv("CASSANDRA_HOST");
public static final String CASSANDRA_PORT=System.getenv("CASSANDRA_PORT");
public static final String DOCKERKEYSPACE="dockerx";
public static final String DOCKERENV = "dockerenv";
}
