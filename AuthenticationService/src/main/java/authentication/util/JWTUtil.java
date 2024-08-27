package authentication.util;

import java.security.KeyPair;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.Base64;
import java.util.Date;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;


public class JWTUtil {

	private KeyPair refreshKey;
	private KeyPair accessKey;

	private JWTUtil() {
		refreshKey = RSAKeyVault.getRefreshKey();
		accessKey = RSAKeyVault.getAccessKey();
	}

	private static class SingletonHelper {
		private static final JWTUtil INSTANCE = new JWTUtil();
	}

	public static JWTUtil getInstance() {
		return SingletonHelper.INSTANCE;
	}

	public String generateRefreshToken(String subject, String[] permissions, boolean isAdmin,
			Long expireTimeInMilisecond) {
		
		try {
			RSAPrivateKey privateKey = (RSAPrivateKey) RSAKeyVault.getRefreshKey().getPrivate();
			Algorithm algorithm = Algorithm.RSA256(null, privateKey);

			return JWT.create().withSubject(subject).withIssuedAt(new Date())
					.withExpiresAt(new Date(
							System.currentTimeMillis() + expireTimeInMilisecond)) // 12
																				// hours
					.withClaim("admin", isAdmin)
					.withArrayClaim("permissions", permissions)
					.sign(algorithm);
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		return null;
	}
	
	public String generateAccessToken(String subject, String[] permissionList, boolean isAdmin,
			Long expireTimeInMilisecond, Long goodBefore) {
		try {
			RSAPrivateKey privateKey = (RSAPrivateKey) accessKey.getPrivate();
			Algorithm algorithm = Algorithm.RSA256(null, privateKey);

			return JWT.create().withSubject(subject).withIssuedAt(new Date())
					.withExpiresAt(new Date(
							System.currentTimeMillis() + expireTimeInMilisecond))
					.withClaim("admin", isAdmin)
					.withArrayClaim("permissions", permissionList)
					.withClaim("eat", new Date(
							System.currentTimeMillis() + goodBefore))
					.sign(algorithm);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	

	public DecodedJWT verifyRefreshToken(String token) {
		try {
			RSAPublicKey publicKey = (RSAPublicKey) refreshKey.getPublic();
			Algorithm algorithm = Algorithm.RSA256(publicKey, null);
			JWTVerifier verifier = JWT.require(algorithm).build();
			return verifier.verify(token);
		} catch (Exception exception) {
			exception.printStackTrace();
			return null;
		}

	}
	
	public DecodedJWT verifyAccessToken(String token) {

		try {
			RSAPublicKey publicKey = (RSAPublicKey) accessKey.getPublic();
			Algorithm algorithm = Algorithm.RSA256(publicKey, null);
			JWTVerifier verifier = JWT.require(algorithm).build();
			return verifier.verify(token);
		} catch (Exception exception) {
			exception.printStackTrace();
			return null;
		}

	}
	
	

	public String invalidateToken(String subject) {
		try {
			RSAPrivateKey privateKey = (RSAPrivateKey) accessKey.getPrivate();
			Algorithm algorithm = Algorithm.RSA256(null, privateKey);

			return JWT.create().withSubject(subject).withIssuedAt(new Date())
					.withExpiresAt(new Date(System.currentTimeMillis() - 1000L))
					.sign(algorithm);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}



	public String getPublicKeyAsBase64(KeyPair keyPair) {
		RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
		return Base64.getEncoder().encodeToString(publicKey.getEncoded());
	}

	public String getPublicKeyAsPEM(KeyPair keyPair) {
		String base64PublicKey = getPublicKeyAsBase64(keyPair);
		return "-----BEGIN PUBLIC KEY-----\n" + base64PublicKey
				+ "\n-----END PUBLIC KEY-----";
	}

	public String getPrivateKeyAsBase64(KeyPair keyPair) {
		RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
		return Base64.getEncoder().encodeToString(privateKey.getEncoded());
	}

	public String getPrivateKeyAsPEM(KeyPair keyPair) {
		String base64PublicKey = getPrivateKeyAsBase64(keyPair);
		return "-----BEGIN PRIVATE KEY-----\n" + base64PublicKey
				+ "\n-----END PRIVATE KEY-----";
	}

	public void checkTokenInfo(String token) {
		byte[] length = token.getBytes();

		String print = String.format(
				"Token length in byte: %d\npublic key: %s\nprivate key: %s",
				length.length, getPublicKeyAsPEM(refreshKey), getPrivateKeyAsPEM(refreshKey));
		
		System.out.println(print);
	}
}
