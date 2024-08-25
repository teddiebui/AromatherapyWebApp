package authentication.util;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;

public class RSAKeyVault {
	
	
	public static synchronized KeyPair rotateKeys(short bit) throws NoSuchAlgorithmException {
		KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
		keyPairGenerator.initialize(bit);
		return keyPairGenerator.generateKeyPair();
	}
}
