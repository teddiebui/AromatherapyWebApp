package authentication.util;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;

public class RSAKeyVault {
	
	
private static volatile KeyPair refreshKey;
	
	private static volatile KeyPair cachedRefreshKey;
	
	private static volatile KeyPair accessKey;
	
	private static volatile KeyPair cachedAccessKey;
	
	static {
		try {
			rotateRefreshKey((short) 2024);
			rotateAccessKey((short) 2024);
		} catch (NoSuchAlgorithmException exception) {
			exception.printStackTrace();
		}
	}
	
	public static synchronized void rotateRefreshKey(short bit) throws NoSuchAlgorithmException {
		cachedRefreshKey = refreshKey;
		KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
		keyPairGenerator.initialize(bit);
		refreshKey = keyPairGenerator.generateKeyPair();
	}
	
	public static synchronized void rotateAccessKey(short bit) throws NoSuchAlgorithmException {
		cachedAccessKey = accessKey;
		KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
		keyPairGenerator.initialize(bit);
		accessKey = keyPairGenerator.generateKeyPair();
	}

	public static KeyPair getRefreshKey() {
		return refreshKey;
	}

	public static KeyPair getCachedRefreshKey() {
		return cachedRefreshKey;
	}

	public static KeyPair getAccessKey() {
		return accessKey;
	}

	public static KeyPair getCachedAccessKey() {
		return cachedAccessKey;
	}
}
