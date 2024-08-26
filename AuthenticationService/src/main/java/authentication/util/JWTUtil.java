package authentication.util;

import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
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
		rotateRefreshKey();
		rotateAccessKey();
	}

	private static class SingletonHelper {
		private static final JWTUtil INSTANCE = new JWTUtil();
	}

	public static JWTUtil getInstance() {
		return SingletonHelper.INSTANCE;
	}

	public void rotateRefreshKey() {
		try {
			this.refreshKey = RSAKeyVault.rotateKeys((short) 2024);
		} catch (NoSuchAlgorithmException exception) {
			// TODO Auto-generated catch block
			exception.printStackTrace();
		}
	}

	public void rotateAccessKey() {
		try {
			this.accessKey = RSAKeyVault.rotateKeys((short) 2024);
		} catch (NoSuchAlgorithmException exception) {
			// TODO Auto-generated catch block
			exception.printStackTrace();
		}
	}

	public KeyPair getRefreshKey() {
		return refreshKey;
	}

	public KeyPair getAccessKey() {
		return accessKey;
	}

	public String generateRefreshToken(String subject, String[] permissions, boolean isAdmin) {
		try {
			RSAPrivateKey privateKey = (RSAPrivateKey) refreshKey.getPrivate();
			Algorithm algorithm = Algorithm.RSA256(null, privateKey);

			return JWT.create().withSubject(subject).withIssuedAt(new Date())
					.withExpiresAt(new Date(
							System.currentTimeMillis() + 12 * 60 * 60 * 1000L)) // 12
																				// hours
					.withClaim("admin", true)
					.withArrayClaim("permissions", permissions)
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
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public String generateAccessToken(String subject, String[] permissionList, boolean isAdmin) {
		try {
			RSAPrivateKey privateKey = (RSAPrivateKey) accessKey.getPrivate();
			Algorithm algorithm = Algorithm.RSA256(null, privateKey);

			return JWT.create().withSubject(subject).withIssuedAt(new Date())
					.withExpiresAt(new Date(
							System.currentTimeMillis() + 15 * 60 * 1000L)) // 15
																			// minutes
					.withClaim("admin", true)
					.withArrayClaim("permissions", permissionList)
					.sign(algorithm);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public boolean isTokenExpired(DecodedJWT jwt) {
		return jwt.getExpiresAt().before(new Date());
	}

	public String invalidateToken(String subject) {
		try {
			RSAPrivateKey privateKey = (RSAPrivateKey) accessKey.getPrivate();
			Algorithm algorithm = Algorithm.RSA256(null, privateKey);

			return JWT.create().withSubject(subject).withIssuedAt(new Date())
					.withExpiresAt(new Date(System.currentTimeMillis() - 1000L)) // Set
																					// expiry
																					// to
																					// 1
																					// second
																					// in
																					// the
																					// past
					.sign(algorithm);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public DecodedJWT verifyAccessToken(String token) {
		try {
			RSAPublicKey publicKey = (RSAPublicKey) accessKey.getPublic();
			Algorithm algorithm = Algorithm.RSA256(publicKey, null);
			JWTVerifier verifier = JWT.require(algorithm).build();
			return verifier.verify(token);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// Method to get the public key as a Base64 encoded string
	public String getPublicKeyAsBase64(KeyPair keyPair) {
		RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
		return Base64.getEncoder().encodeToString(publicKey.getEncoded());
	}

	// Method to get the public key in PEM format
	public String getPublicKeyAsPEM(KeyPair keyPair) {
		String base64PublicKey = getPublicKeyAsBase64(keyPair);
		return "-----BEGIN PUBLIC KEY-----\n" + base64PublicKey
				+ "\n-----END PUBLIC KEY-----";
	}

	// Method to get the public key as a Base64 encoded string
	public String getPrivateKeyAsBase64(KeyPair keyPair) {
		RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
		return Base64.getEncoder().encodeToString(privateKey.getEncoded());
	}

	// Method to get the public key in PEM format
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
