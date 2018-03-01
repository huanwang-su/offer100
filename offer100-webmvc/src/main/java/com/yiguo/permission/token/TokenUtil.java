package com.yiguo.permission.token;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Base64;

/**
 * Token生成解析器
 *
 * @author wanghuan
 * @date 2018-01-09
 */
public class TokenUtil {
	private static final byte[] API_KEY_SECRET_BYTES = DatatypeConverter.parseBase64Binary("secret");
	private static final SignatureAlgorithm SIGNATURE_ALGORITHM = SignatureAlgorithm.HS256;

	public static String generatorES256Token(String username) {
		Key signingKey = new SecretKeySpec(API_KEY_SECRET_BYTES, SIGNATURE_ALGORITHM.getJcaName());
		JwtBuilder builder = Jwts.builder().setSubject(Base64.getEncoder().encodeToString(username.getBytes()))
				.signWith(SIGNATURE_ALGORITHM, signingKey);
		return builder.compact();
	}

	public static String parseES256Token(String token) {
		token = token.trim();
		String username = Jwts.parser().setSigningKey(API_KEY_SECRET_BYTES).parseClaimsJws(token).getBody()
				.getSubject();
		return new String(Base64.getDecoder().decode(username));
	}

	public static void main(String args[]) {
		System.out.println(generatorES256Token("你好呀"));
		System.out.println(generatorES256Token("你好呀"));
		System.out.println(parseES256Token(generatorES256Token("你好呀")));
	}

}
