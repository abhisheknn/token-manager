package com.micro.auth.services;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.security.Key;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.ws.rs.core.Response;
import javax.xml.bind.DatatypeConverter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.micro.auth.constant.AppConstants;
import com.micro.auth.dao.MachineDao;
import com.micro.auth.dao.TenantDao;
import com.micro.auth.pojo.Machine;
import com.micro.auth.pojo.Tenant;
import com.micro.auth.util.JWTProvider;
import com.micro.auth.util.KeyProvider;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;

@Component
public class AuthServiceImpl implements AuthService {

	@Autowired
	JWTProvider jwtProvider;

	@Autowired
	KeyProvider keyProvider;

	@Autowired
	MachineDao machinedao; 
	
	@Autowired
	TenantDao tenantDao; 

	@Override
	public String createMachine(Machine machine) {
		String token ="";
		// For the new users roles will not available
		if (machine.getControls() == null) {
			Map<String, Object> defaultAccess = new HashMap<>();
			defaultAccess.put("macaddress", machine.getMacAddress());
			defaultAccess.put("tenantid", machine.getTenantId());
			defaultAccess.put("uuid", machine.getUuid());
			machine.setControls(defaultAccess);
		}
		if (machine.getJWToken() == null) {
			//jwtProvider.setKeyProvider(keyProvider);
			 token = jwtProvider.getToken(machine.getHostName(), machine.getControls(),keyProvider);
			machine.setJWToken(token);
		}
		
		// store in cassandra
		machinedao.addMachine(machine);
		
		return token;
	}
	
	
	@Override
	public String createTenant(Tenant tenant) {
		String token ="";
		// For the new users roles will not available
		if (tenant.getControls() == null) {
			Map<String, Object> defaultAccess = new HashMap<>();
			defaultAccess.put("tenantid", tenant.getTenantId());
			tenant.setControls(defaultAccess);
		}
		if (tenant.getJWToken() == null) {
			//jwtProvider.setKeyProvider(keyProvider);
			 token = jwtProvider.getToken(tenant.getTenantId(), tenant.getControls(),keyProvider);
			 tenant.setJWToken(token);
		}
		
		// store in cassandra
		tenantDao.create(tenant);
		return token;
	}



	private Object convertMaptoObject(Map<String, String> ctrlMap,Class clazz) {
		Set<String> keys=ctrlMap.keySet();
		Class userClass= clazz;
		Object dummy = null;
		try {
			dummy = clazz.newInstance();
		} catch (Exception e1) {
			// ignore this
		}
		for(String key :keys) {
			try {
				Method method = userClass.getMethod("set"+key.replaceFirst(key.charAt(0)+"",(key.charAt(0)+"").toUpperCase()), String.class);
				Object returnValue= method.invoke(dummy, ctrlMap.get(key));
			} catch (Exception e) {
				// ignore this
			}
		}
		return dummy;
	}


	private Key getKey() {
		Key k = keyProvider.getPublicKey(AppConstants.CERTALIAS);
		if (k == null) {
			keyProvider.createAndStoreCert("cn=unknown", AppConstants.CERTALIAS); // get this from configuration service
			k = keyProvider.getPublicKey(AppConstants.CERTALIAS); // get this from configuration service
		}
		return k;
	}

	@Override
	public String getPublicKey() {
		Key k = getKey();
		String stringKey = Base64.getEncoder().encodeToString(k.getEncoded());
		return stringKey;
	}

	private Map<String, Object> convertStringToMap(String userClaims) {
		Map<String, Object> userClaimsMap = new HashMap<>();
		userClaims = userClaims.substring(1, userClaims.length() - 1);
		String[] keyValuePairs = userClaims.split(",");
		for (String keyValue : keyValuePairs) {
			String[] entry = keyValue.split("=");
			userClaimsMap.put(entry[0], entry[1]);
		}
		return userClaimsMap;
	}

	

	@Override
	public String refreshToken(String entityName) {
		Map<String, String> entytiMap = new HashMap<>();
		String jwToken = null;
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		if (entytiMap.get(AppConstants.ENTITYNAME).equals(entityName)) {
			Key k = getKey();
			jwToken = entytiMap.get(AppConstants.JWTOKEN);
			String userClaims = entytiMap.get("roles");
			String token = jwtProvider.getToken(entytiMap.get(AppConstants.ENTITYNAME), convertStringToMap(userClaims),keyProvider);
			entytiMap.put(AppConstants.JWTOKEN, token);
		}
		return entytiMap.get(AppConstants.JWTOKEN);
	}

	@Override
	public String register(Machine machine) {
		Machine m=machinedao.getMachine(machine.getTenantId(),machine.getMacAddress());
		return m.getJWToken();
	}



	@Override
	public Map<String,String> getMachines(String tenantId) {
		return machinedao.getMachines(tenantId);
	}
	


	@Override
	public String updateStatus(Machine machine) {
		return machinedao.updateStatus(machine);
	}


	@Override
	public List<Tenant> getTenant(String tenantId) {
		return tenantDao.getTenant(tenantId);
	}


}
