package com.vily.collect.utils;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;


/**
 * token工具类
 */
public class TokenUtil {


	private static final String TOKEN_SECRET = "aria_token_secret";

	private static final long EXPIRE_TIME = 1000*60*60*24;


	/**
	 * 生成默认签名，默认时间
	 * @param data
	 * @return
	 */
	public static String sign(String data) {

		return sign(TOKEN_SECRET,data);
	}

	/**
	 * 生成签名 默认1天 24h
	 * @param tokenSecret
	 * @param data
	 */
	public static String sign(String tokenSecret,String data) {
		return sign(tokenSecret,data,EXPIRE_TIME);
	}

	/**
	 * 生成签名
	 * @param tokenSecret  私钥
	 * @param data		   数据
	 * @param expire       过期时间，单位毫秒
	 */
	public static String sign(String tokenSecret,String data,long expire) {
	    try {
	        // 设置过期时间
	        Date date = new Date(System.currentTimeMillis() + expire);
	        // 私钥和加密算法
	        Algorithm algorithm = Algorithm.HMAC256(tokenSecret);
	        // 设置头部信息
	        Map<String, Object> header = new HashMap<>(2);
	        header.put("Type", "Jwt");
	        header.put("alg", "HS256");
	        // 返回token字符串 附带token_msg信息 生成签名
	        return JWT.create()
	                .withHeader(header)
	                .withClaim("token_msg", data)
	                .withExpiresAt(date)
	                .sign(algorithm);
	    } catch (Exception e) {
	        e.printStackTrace();
	        return null;
	    }
	}

	/**
	 * 检验token是否正确
	 * @param tokenSecret  私钥
	 * @param token        token字符串
	 */
	public static boolean verify(String tokenSecret,String token){
	    try {
	        Algorithm algorithm = Algorithm.HMAC256(tokenSecret);
	        JWTVerifier verifier = JWT.require(algorithm).build();
	        verifier.verify(token);
	        return true;
	    } catch (Exception e){
	        return false;
	    }
	}

	/**
	 * 从token中获取no信息
	 * @param token  token字符串
	 */
	public static String getGtInfoMemberNo(String token){
	    try {
	        DecodedJWT jwt = JWT.decode(token);
	        return jwt.getClaim("token_msg").asString();
	    } catch (JWTDecodeException e){
	        e.printStackTrace();
	        return null;
	    }
	}
	
}
