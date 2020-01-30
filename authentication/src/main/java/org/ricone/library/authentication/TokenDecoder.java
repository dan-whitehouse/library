package org.ricone.library.authentication;

import com.auth0.jwt.JWT;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.ricone.library.authentication.oneroster.OneRosterDecodedToken;
import org.ricone.library.authentication.xPress.XPressDecodedToken;

import java.util.Base64;

/**
 * @author Dan Whitehouse <daniel.whitehouse@neric.org>
 * @version 2020.1
 * @since 2020-01-16
 */

public abstract class TokenDecoder {
	static <T> T decodeToken(String token, Class<T> clazz) throws JWTVerificationException {
		try {
			DecodedJWT jwt = JWT.decode(token);
			ObjectMapper mapper = new ObjectMapper();
			return mapper.readValue(base64Decode(jwt.getPayload()), clazz);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new JWTVerificationException("Invalid Authorization Token provided");
		}
	}

	private static String base64Decode(String input) {
		byte[] decodedBytes = Base64.getDecoder().decode(input);
		return new String(decodedBytes);
	}

	static DecodedToken getDecodedToken(XPressDecodedToken dt, String token) {
		return new DecodedToken(dt.getApplicationId(), "", "", (int)dt.getIat().toInstant().toEpochMilli(), (int)dt.getExp().toInstant().toEpochMilli(), dt.getIss(), token);
	}

	static DecodedToken getDecodedToken(OneRosterDecodedToken dt, String token) {
		return new DecodedToken(dt.getAppId(), dt.getProviderId(), dt.getHref(), dt.getIat(), dt.getExp(), dt.getIss(), token);
	}
}
