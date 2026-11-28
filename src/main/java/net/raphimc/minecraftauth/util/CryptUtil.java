package net.raphimc.minecraftauth.util;

import com.google.gson.JsonObject;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.interfaces.ECPrivateKey;
import java.security.interfaces.ECPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.time.Instant;
import java.util.Base64;
import java.util.Optional;
import net.lenni0451.commons.httpclient.content.HttpContent;
import net.lenni0451.commons.httpclient.model.HttpHeader;
import net.lenni0451.commons.httpclient.requests.HttpContentRequest;
import net.lenni0451.commons.httpclient.requests.HttpRequest;

public class CryptUtil {
   public static final KeyFactory RSA_KEYFACTORY;
   public static final KeyFactory EC_KEYFACTORY;

   public static <T extends PublicKey> T publicKeyEcFromBase64(String base64) {
      try {
         return (T)EC_KEYFACTORY.generatePublic(new X509EncodedKeySpec(Base64.getDecoder().decode(base64)));
      } catch (InvalidKeySpecException e) {
         throw new RuntimeException("Could not decode base64 public key", e);
      }
   }

   public static <T extends PrivateKey> T privateKeyEcFromBase64(String base64) {
      try {
         return (T)EC_KEYFACTORY.generatePrivate(new PKCS8EncodedKeySpec(Base64.getDecoder().decode(base64)));
      } catch (InvalidKeySpecException e) {
         throw new RuntimeException("Could not decode base64 private key", e);
      }
   }

   public static <T extends PublicKey> T publicKeyRsaFromBase64(String base64) {
      try {
         return (T)RSA_KEYFACTORY.generatePublic(new X509EncodedKeySpec(Base64.getDecoder().decode(base64)));
      } catch (InvalidKeySpecException e) {
         throw new RuntimeException("Could not decode base64 public key", e);
      }
   }

   public static <T extends PrivateKey> T privateKeyRsaFromBase64(String base64) {
      try {
         return (T)RSA_KEYFACTORY.generatePrivate(new PKCS8EncodedKeySpec(Base64.getDecoder().decode(base64)));
      } catch (InvalidKeySpecException e) {
         throw new RuntimeException("Could not decode base64 private key", e);
      }
   }

   public static HttpHeader getSignatureHeader(HttpRequest httpRequest, ECPrivateKey privateKey) throws IOException {
      long windowsTimestamp = (Instant.now().plus(TimeUtil.getClientTimeOffset()).getEpochSecond() + 11644473600L) * 10000000L;
      ByteArrayOutputStream signatureContent = new ByteArrayOutputStream();
      DataOutputStream data = new DataOutputStream(signatureContent);
      data.writeInt(1);
      data.writeByte(0);
      data.writeLong(windowsTimestamp);
      data.writeByte(0);
      data.write(httpRequest.getMethod().getBytes(StandardCharsets.UTF_8));
      data.writeByte(0);
      data.write(
         (httpRequest.getURL().getPath() + (httpRequest.getURL().getQuery() != null ? httpRequest.getURL().getQuery() : "")).getBytes(StandardCharsets.UTF_8)
      );
      data.writeByte(0);
      Optional<String> authorizationHeader = httpRequest.getFirstHeader("Authorization");
      if (authorizationHeader.isPresent()) {
         data.write(authorizationHeader.get().getBytes(StandardCharsets.UTF_8));
      }

      data.writeByte(0);
      if (httpRequest instanceof HttpContentRequest) {
         HttpContent content = ((HttpContentRequest)httpRequest).getContent();
         if (content != null) {
            data.write(content.getAsBytes());
         }
      }

      data.writeByte(0);
      ByteArrayOutputStream header = new ByteArrayOutputStream();
      data = new DataOutputStream(header);
      data.writeInt(1);
      data.writeLong(windowsTimestamp);

      try {
         byte[] signature;
         try {
            Signature ecdsaSignature = Signature.getInstance("SHA256withECDSAinP1363Format");
            ecdsaSignature.initSign(privateKey);
            ecdsaSignature.update(signatureContent.toByteArray());
            signature = ecdsaSignature.sign();
         } catch (NoSuchAlgorithmException e) {
            signature = JwtUtil.signES256(privateKey, signatureContent.toByteArray());
         }

         data.write(signature);
      } catch (Throwable e2) {
         throw new RuntimeException("Could not sign request", e2);
      }

      return new HttpHeader("Signature", Base64.getEncoder().encodeToString(header.toByteArray()));
   }

   public static JsonObject getProofKey(ECPublicKey publicKey) {
      JsonObject proofKey = new JsonObject();
      proofKey.addProperty("alg", "ES256");
      proofKey.addProperty("crv", "P-256");
      proofKey.addProperty("kty", "EC");
      proofKey.addProperty("use", "sig");
      proofKey.addProperty("x", encodeECCoordinate(publicKey.getParams().getCurve().getField().getFieldSize(), publicKey.getW().getAffineX()));
      proofKey.addProperty("y", encodeECCoordinate(publicKey.getParams().getCurve().getField().getFieldSize(), publicKey.getW().getAffineY()));
      return proofKey;
   }

   private static String encodeECCoordinate(int fieldSize, BigInteger coordinate) {
      byte[] notPadded = bigIntegerToByteArray(coordinate);
      int bytesToOutput = (fieldSize + 7) / 8;
      if (notPadded.length >= bytesToOutput) {
         return Base64.getUrlEncoder().withoutPadding().encodeToString(notPadded);
      } else {
         byte[] padded = new byte[bytesToOutput];
         System.arraycopy(notPadded, 0, padded, bytesToOutput - notPadded.length, notPadded.length);
         return Base64.getUrlEncoder().withoutPadding().encodeToString(padded);
      }
   }

   private static byte[] bigIntegerToByteArray(BigInteger bigInteger) {
      int bitlen = bigInteger.bitLength();
      bitlen = bitlen + 7 >> 3 << 3;
      byte[] bigBytes = bigInteger.toByteArray();
      if (bigInteger.bitLength() % 8 != 0 && bigInteger.bitLength() / 8 + 1 == bitlen / 8) {
         return bigBytes;
      } else {
         int startSrc = 0;
         int len = bigBytes.length;
         if (bigInteger.bitLength() % 8 == 0) {
            startSrc = 1;
            len--;
         }

         int startDst = bitlen / 8 - len;
         byte[] resizedBytes = new byte[bitlen / 8];
         System.arraycopy(bigBytes, startSrc, resizedBytes, startDst, len);
         return resizedBytes;
      }
   }

   static {
      try {
         RSA_KEYFACTORY = KeyFactory.getInstance("RSA");
      } catch (NoSuchAlgorithmException e) {
         throw new RuntimeException("Could not create RSA KeyFactory", e);
      }

      try {
         EC_KEYFACTORY = KeyFactory.getInstance("EC");
      } catch (NoSuchAlgorithmException e2) {
         throw new RuntimeException("Could not create EllipticCurve KeyFactory", e2);
      }
   }
}
