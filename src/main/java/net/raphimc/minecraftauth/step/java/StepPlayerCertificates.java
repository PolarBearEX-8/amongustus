package net.raphimc.minecraftauth.step.java;

import com.google.gson.JsonObject;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.time.Instant;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Base64;
import lombok.Generated;
import net.lenni0451.commons.httpclient.HttpClient;
import net.lenni0451.commons.httpclient.constants.ContentTypes;
import net.lenni0451.commons.httpclient.content.impl.StringContent;
import net.lenni0451.commons.httpclient.requests.impl.PostRequest;
import net.raphimc.minecraftauth.responsehandler.MinecraftResponseHandler;
import net.raphimc.minecraftauth.step.AbstractStep;
import net.raphimc.minecraftauth.util.CryptUtil;
import net.raphimc.minecraftauth.util.logging.ILogger;

public class StepPlayerCertificates extends AbstractStep<StepMCToken.MCToken, StepPlayerCertificates.PlayerCertificates> {
   public static final String PLAYER_CERTIFICATES_URL = "https://api.minecraftservices.com/player/certificates";

   public StepPlayerCertificates(AbstractStep<?, StepMCToken.MCToken> prevStep) {
      super("playerCertificates", prevStep);
   }

   protected StepPlayerCertificates.PlayerCertificates execute(ILogger logger, HttpClient httpClient, StepMCToken.MCToken mcToken) throws Exception {
      logger.info(this, "Getting player certificates...");
      PostRequest postRequest = new PostRequest("https://api.minecraftservices.com/player/certificates");
      postRequest.setContent(new StringContent(ContentTypes.APPLICATION_JSON, ""));
      postRequest.setHeader("Authorization", mcToken.getTokenType() + " " + mcToken.getAccessToken());
      JsonObject obj = httpClient.execute(postRequest, new MinecraftResponseHandler());
      JsonObject keyPair = obj.getAsJsonObject("keyPair");
      PKCS8EncodedKeySpec encodedPrivateKey = new PKCS8EncodedKeySpec(
         Base64.getMimeDecoder()
            .decode(keyPair.get("privateKey").getAsString().replace("-----BEGIN RSA PRIVATE KEY-----", "").replace("-----END RSA PRIVATE KEY-----", ""))
      );
      RSAPrivateKey privateKey = (RSAPrivateKey)CryptUtil.RSA_KEYFACTORY.generatePrivate(encodedPrivateKey);
      X509EncodedKeySpec encodedPublicKey = new X509EncodedKeySpec(
         Base64.getMimeDecoder()
            .decode(keyPair.get("publicKey").getAsString().replace("-----BEGIN RSA PUBLIC KEY-----", "").replace("-----END RSA PUBLIC KEY-----", ""))
      );
      RSAPublicKey publicKey = (RSAPublicKey)CryptUtil.RSA_KEYFACTORY.generatePublic(encodedPublicKey);
      StepPlayerCertificates.PlayerCertificates playerCertificates = new StepPlayerCertificates.PlayerCertificates(
         Instant.parse(obj.get("expiresAt").getAsString()).toEpochMilli(),
         publicKey,
         privateKey,
         Base64.getMimeDecoder().decode(obj.get("publicKeySignatureV2").getAsString()),
         obj.has("publicKeySignature") ? Base64.getMimeDecoder().decode(obj.get("publicKeySignature").getAsString()) : new byte[0],
         mcToken
      );
      logger.info(this, "Got player certificates, expires: " + Instant.ofEpochMilli(playerCertificates.getExpireTimeMs()).atZone(ZoneId.systemDefault()));
      return playerCertificates;
   }

   public StepPlayerCertificates.PlayerCertificates fromJson(JsonObject json) {
      StepMCToken.MCToken mcToken = this.prevStep != null ? this.prevStep.fromJson(json.getAsJsonObject(this.prevStep.name)) : null;
      return new StepPlayerCertificates.PlayerCertificates(
         json.get("expireTimeMs").getAsLong(),
         CryptUtil.publicKeyRsaFromBase64(json.get("publicKey").getAsString()),
         CryptUtil.privateKeyRsaFromBase64(json.get("privateKey").getAsString()),
         Base64.getDecoder().decode(json.get("publicKeySignature").getAsString()),
         Base64.getDecoder().decode(json.get("legacyPublicKeySignature").getAsString()),
         mcToken
      );
   }

   public JsonObject toJson(StepPlayerCertificates.PlayerCertificates playerCertificates) {
      JsonObject json = new JsonObject();
      json.addProperty("expireTimeMs", playerCertificates.expireTimeMs);
      json.addProperty("publicKey", Base64.getEncoder().encodeToString(playerCertificates.publicKey.getEncoded()));
      json.addProperty("privateKey", Base64.getEncoder().encodeToString(playerCertificates.privateKey.getEncoded()));
      json.addProperty("publicKeySignature", Base64.getEncoder().encodeToString(playerCertificates.publicKeySignature));
      json.addProperty("legacyPublicKeySignature", Base64.getEncoder().encodeToString(playerCertificates.legacyPublicKeySignature));
      if (this.prevStep != null) {
         json.add(this.prevStep.name, this.prevStep.toJson(playerCertificates.mcToken));
      }

      return json;
   }

   public static final class PlayerCertificates extends AbstractStep.StepResult<StepMCToken.MCToken> {
      private final long expireTimeMs;
      private final RSAPublicKey publicKey;
      private final RSAPrivateKey privateKey;
      private final byte[] publicKeySignature;
      private final byte[] legacyPublicKeySignature;
      private final StepMCToken.MCToken mcToken;

      protected StepMCToken.MCToken prevResult() {
         return this.mcToken;
      }

      @Override
      public boolean isExpired() {
         return this.expireTimeMs <= System.currentTimeMillis() || this.prevResult().isExpired();
      }

      @Generated
      public PlayerCertificates(
         long expireTimeMs,
         RSAPublicKey publicKey,
         RSAPrivateKey privateKey,
         byte[] publicKeySignature,
         byte[] legacyPublicKeySignature,
         StepMCToken.MCToken mcToken
      ) {
         this.expireTimeMs = expireTimeMs;
         this.publicKey = publicKey;
         this.privateKey = privateKey;
         this.publicKeySignature = publicKeySignature;
         this.legacyPublicKeySignature = legacyPublicKeySignature;
         this.mcToken = mcToken;
      }

      @Generated
      public long getExpireTimeMs() {
         return this.expireTimeMs;
      }

      @Generated
      public RSAPublicKey getPublicKey() {
         return this.publicKey;
      }

      @Generated
      public RSAPrivateKey getPrivateKey() {
         return this.privateKey;
      }

      @Generated
      public byte[] getPublicKeySignature() {
         return this.publicKeySignature;
      }

      @Generated
      public byte[] getLegacyPublicKeySignature() {
         return this.legacyPublicKeySignature;
      }

      @Generated
      public StepMCToken.MCToken getMcToken() {
         return this.mcToken;
      }

      @Generated
      @Override
      public String toString() {
         return "StepPlayerCertificates.PlayerCertificates(expireTimeMs="
            + this.getExpireTimeMs()
            + ", publicKey="
            + this.getPublicKey()
            + ", privateKey="
            + this.getPrivateKey()
            + ", publicKeySignature="
            + Arrays.toString(this.getPublicKeySignature())
            + ", legacyPublicKeySignature="
            + Arrays.toString(this.getLegacyPublicKeySignature())
            + ", mcToken="
            + this.getMcToken()
            + ")";
      }

      @Generated
      @Override
      public boolean equals(Object o) {
         if (o == this) {
            return true;
         } else if (!(o instanceof StepPlayerCertificates.PlayerCertificates)) {
            return false;
         } else {
            StepPlayerCertificates.PlayerCertificates other = (StepPlayerCertificates.PlayerCertificates)o;
            if (!other.canEqual(this)) {
               return false;
            } else if (this.getExpireTimeMs() != other.getExpireTimeMs()) {
               return false;
            } else {
               Object this$publicKey = this.getPublicKey();
               Object other$publicKey = other.getPublicKey();
               if (this$publicKey == null ? other$publicKey == null : this$publicKey.equals(other$publicKey)) {
                  Object this$privateKey = this.getPrivateKey();
                  Object other$privateKey = other.getPrivateKey();
                  if (this$privateKey == null ? other$privateKey == null : this$privateKey.equals(other$privateKey)) {
                     if (!Arrays.equals(this.getPublicKeySignature(), other.getPublicKeySignature())) {
                        return false;
                     } else if (!Arrays.equals(this.getLegacyPublicKeySignature(), other.getLegacyPublicKeySignature())) {
                        return false;
                     } else {
                        Object this$mcToken = this.getMcToken();
                        Object other$mcToken = other.getMcToken();
                        return this$mcToken == null ? other$mcToken == null : this$mcToken.equals(other$mcToken);
                     }
                  } else {
                     return false;
                  }
               } else {
                  return false;
               }
            }
         }
      }

      @Generated
      protected boolean canEqual(Object other) {
         return other instanceof StepPlayerCertificates.PlayerCertificates;
      }

      @Generated
      @Override
      public int hashCode() {
         int PRIME = 59;
         int result = 1;
         long $expireTimeMs = this.getExpireTimeMs();
         result = result * 59 + (int)($expireTimeMs >>> 32 ^ $expireTimeMs);
         Object $publicKey = this.getPublicKey();
         result = result * 59 + ($publicKey == null ? 43 : $publicKey.hashCode());
         Object $privateKey = this.getPrivateKey();
         result = result * 59 + ($privateKey == null ? 43 : $privateKey.hashCode());
         result = result * 59 + Arrays.hashCode(this.getPublicKeySignature());
         result = result * 59 + Arrays.hashCode(this.getLegacyPublicKeySignature());
         Object $mcToken = this.getMcToken();
         return result * 59 + ($mcToken == null ? 43 : $mcToken.hashCode());
      }
   }
}
