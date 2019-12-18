package cn.ekgc.itrip.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.SignatureGenerationException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;
import org.springframework.mail.MailParseException;

import java.util.HashMap;
import java.util.Map;

/**
 * 用于生成和解析Token工具类
 * @author wang
 * @version 3.1.0
 * @since 2019-12-16
 */
public class JWTUtil {
	/**
	 * 根据用户信息userId，生成相应的Token JSON
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public static String createToken(Long userId) throws Exception{
		// 加密时用的密码
		Algorithm algorithm = Algorithm.HMAC256(ConstantUtil.AUTH_SECRET);
		JWTCreator.Builder builder = JWT.create();
		Map<String,Object> headerMap = new HashMap<String,Object>();
		headerMap.put("alg", "hmac256");
		headerMap.put("type", "jwt");
		builder.withHeader(headerMap);
		builder.withClaim("id", userId);

		String jwtJSON = builder.sign(algorithm);
		return jwtJSON;
	}

	/**
	 * 对于给定的Token进行校验和解密，获得用户的userId
	 * @param token
	 * @return
	 * @throws Exception
	 */
	public static Long decryptToken(String token) throws Exception {
		// 加密时用的密码
		Algorithm algorithm = Algorithm.HMAC256(ConstantUtil.AUTH_SECRET);
		try {
			JWTVerifier jwtVerifier = JWT.require(algorithm).build();
			DecodedJWT decodedJWT = JWT.decode(token);
			Long id = decodedJWT.getClaim("id").asLong();
			if (id != null && id > 0) {
				return id;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1L;
	}

	public static void main(String[] args) throws Exception {
		System.out.println(decryptToken("eyJ0eXBlIjoiand0IiwiYWxnIjoiSFMyNTYiLCJ0eXAiOiJKV1QifQ.eyJpZCI6MTIzfQ.IeOnuDvyDRxKjtjYYz6p1nj0tvNTTnlCZx0-hQuj1VE"));
	}
}
