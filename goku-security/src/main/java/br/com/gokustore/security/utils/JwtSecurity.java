package br.com.gokustore.security.utils;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

public class JwtSecurity {

	private String secretSeed;
	
	public JwtSecurity(String secretSeed) {
		this.secretSeed = secretSeed;
	}
	
	public String createJWTToken(String id, String name, String email, int expiringTime) {
		Algorithm algorithm = Algorithm.HMAC256(secretSeed);
		
		String token = JWT.create()
				.withIssuer("GokuStore")
				.withExpiresAt(new Date(getEpochTime() + (expiringTime * 60 * 1000)))
				.withIssuedAt(new Date(getEpochTime()))
				.withSubject(id)
				.withClaim("name", name)
				.withClaim("email", email)
				.sign(algorithm);
		
		return token;
	}
	
	public DecodedJWT verifyToken(String token) {
		Algorithm algorithm = Algorithm.HMAC256(secretSeed);
		JWTVerifier verifier = JWT.require(algorithm)
				.withIssuer("GokuStore")
				.build();
		return verifier.verify(token);
	}
	
	public long getEpochTime(){
		TimeZone tz = TimeZone.getTimeZone("UTC");
		Calendar cal = Calendar.getInstance(tz);
		return cal.getTimeInMillis();
	}
}
