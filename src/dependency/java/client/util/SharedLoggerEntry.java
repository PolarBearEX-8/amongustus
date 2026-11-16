package client.util;

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SharedLoggerEntry {
   public static final Logger logger = LogManager.getLogger();

   public static byte[] resolveByteArray(String text, PublicKey publicKey, SecretKey secretKey) {
      try {
         return resolveByteArray("SHA-1", new byte[][]{text.getBytes("ISO_8859_1"), secretKey.getEncoded(), publicKey.getEncoded()});
      } catch (UnsupportedEncodingException unsupportedEncodingException) {
         unsupportedEncodingException.printStackTrace();
         return null;
      }
   }

   private static byte[] resolveByteArray(int count, Key key, byte[] byteValueArray) {
      try {
         return resolveCipher(count, key.getAlgorithm(), key).doFinal(byteValueArray);
      } catch (IllegalBlockSizeException illegalBlockSizeException) {
         illegalBlockSizeException.printStackTrace();
      } catch (BadPaddingException badPaddingException) {
         badPaddingException.printStackTrace();
      }

      logger.error("Cipher data failed!");
      return null;
   }

   public static PublicKey resolvePublicKey(byte[] byteValueArray) {
      try {
         X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(byteValueArray);
         return KeyFactory.getInstance("RSA").generatePublic(x509EncodedKeySpec);
      } catch (NoSuchAlgorithmException noSuchAlgorithmException) {
      } catch (InvalidKeySpecException invalidKeySpecException) {
      }

      logger.error("Public key reconstitute failed!");
      return null;
   }

   private static Cipher resolveCipher(int count, String text, Key key) {
      try {
         Cipher instance = Cipher.getInstance(text);
         instance.init(count, key);
         return instance;
      } catch (InvalidKeyException invalidKeyException) {
         invalidKeyException.printStackTrace();
      } catch (NoSuchAlgorithmException noSuchAlgorithmException) {
         noSuchAlgorithmException.printStackTrace();
      } catch (NoSuchPaddingException noSuchPaddingException) {
         noSuchPaddingException.printStackTrace();
      }

      logger.error("Cipher creation failed!");
      return null;
   }

   public static byte[] resolveByteArrayByKey(Key key, byte[] byteValueArray) {
      return resolveByteArray(2, key, byteValueArray);
   }

   public static SecretKey resolveSecretKey(PrivateKey privateKey, byte[] byteValueArray) {
      return new SecretKeySpec(resolveByteArrayByKey(privateKey, byteValueArray), "AES");
   }

   public static KeyPair getKeyPair() {
      try {
         KeyPairGenerator instance = KeyPairGenerator.getInstance("RSA");
         instance.initialize(1024);
         return instance.generateKeyPair();
      } catch (NoSuchAlgorithmException noSuchAlgorithmException) {
         noSuchAlgorithmException.printStackTrace();
         logger.error("Key pair generation failed!");
         return null;
      }
   }

   public static byte[] resolveByteArray(Key key, byte[] byteValueArray) {
      return resolveByteArray(1, key, byteValueArray);
   }

   public static SecretKey getSecretKey() {
      try {
         KeyGenerator instance = KeyGenerator.getInstance("AES");
         instance.init(128);
         return instance.generateKey();
      } catch (NoSuchAlgorithmException noSuchAlgorithmException) {
         throw new Error(noSuchAlgorithmException);
      }
   }

   private static byte[] resolveByteArray(String text, byte[][] byteValueMatrix) {
      try {
         MessageDigest instance = MessageDigest.getInstance(text);

         for (byte[] byteValueArray : byteValueMatrix) {
            instance.update(byteValueArray);
         }

         return instance.digest();
      } catch (NoSuchAlgorithmException noSuchAlgorithmException) {
         noSuchAlgorithmException.printStackTrace();
         return null;
      }
   }

   public static Cipher resolveCipher(int count, Key key) {
      try {
         Cipher instance = Cipher.getInstance("AES/CFB8/NoPadding");
         instance.init(count, key, new IvParameterSpec(key.getEncoded()));
         return instance;
      } catch (GeneralSecurityException generalSecurityException) {
         throw new RuntimeException(generalSecurityException);
      }
   }
}
