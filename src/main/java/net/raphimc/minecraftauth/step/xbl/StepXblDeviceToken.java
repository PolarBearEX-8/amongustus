package net.raphimc.minecraftauth.step.xbl;

import com.google.gson.JsonObject;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.interfaces.ECPrivateKey;
import java.security.interfaces.ECPublicKey;
import java.security.spec.ECGenParameterSpec;
import java.time.Instant;
import java.time.ZoneId;
import java.util.Base64;
import java.util.UUID;
import lombok.Generated;
import net.lenni0451.commons.httpclient.HttpClient;
import net.lenni0451.commons.httpclient.model.HttpHeader;
import net.lenni0451.commons.httpclient.requests.impl.PostRequest;
import net.raphimc.minecraftauth.responsehandler.XblResponseHandler;
import net.raphimc.minecraftauth.step.AbstractStep;
import net.raphimc.minecraftauth.util.CryptUtil;
import net.raphimc.minecraftauth.util.JsonContent;
import net.raphimc.minecraftauth.util.logging.ILogger;

public class StepXblDeviceToken extends AbstractStep<AbstractStep.StepResult<?>, StepXblDeviceToken.XblDeviceToken> {
   public static final String XBL_DEVICE_URL = "https://device.auth.xboxlive.com/device/authenticate";
   private final String deviceType;

   public StepXblDeviceToken(String deviceType) {
      super("xblDeviceToken");
      this.deviceType = deviceType;
   }

   protected StepXblDeviceToken.XblDeviceToken execute(ILogger logger, HttpClient httpClient, AbstractStep.StepResult<?> prevResult) throws Exception {
      logger.info(this, "Authenticating device with Xbox Live...");
      UUID id = UUID.randomUUID();
      KeyPairGenerator secp256r1 = KeyPairGenerator.getInstance("EC");
      secp256r1.initialize(new ECGenParameterSpec("secp256r1"));
      KeyPair ecdsa256KeyPair = secp256r1.generateKeyPair();
      ECPublicKey publicKey = (ECPublicKey)ecdsa256KeyPair.getPublic();
      ECPrivateKey privateKey = (ECPrivateKey)ecdsa256KeyPair.getPrivate();
      JsonObject postData = new JsonObject();
      JsonObject properties = new JsonObject();
      properties.addProperty("AuthMethod", "ProofOfPossession");
      properties.addProperty("DeviceType", this.deviceType);
      properties.addProperty("Id", "{" + id + "}");
      properties.add("ProofKey", CryptUtil.getProofKey(publicKey));
      properties.addProperty("Version", "0.0.0");
      postData.add("Properties", properties);
      postData.addProperty("RelyingParty", "http://auth.xboxlive.com");
      postData.addProperty("TokenType", "JWT");
      PostRequest postRequest = new PostRequest("https://device.auth.xboxlive.com/device/authenticate");
      postRequest.setContent(new JsonContent(postData));
      postRequest.setHeader("x-xbl-contract-version", "1");
      postRequest.setHeader(new HttpHeader[]{CryptUtil.getSignatureHeader(postRequest, privateKey)});
      JsonObject obj = httpClient.execute(postRequest, new XblResponseHandler());
      StepXblDeviceToken.XblDeviceToken xblDeviceToken = new StepXblDeviceToken.XblDeviceToken(
         publicKey,
         privateKey,
         id,
         Instant.parse(obj.get("NotAfter").getAsString()).toEpochMilli(),
         obj.get("Token").getAsString(),
         obj.getAsJsonObject("DisplayClaims").getAsJsonObject("xdi").get("did").getAsString()
      );
      logger.info(this, "Got XBL Device Token, expires: " + Instant.ofEpochMilli(xblDeviceToken.getExpireTimeMs()).atZone(ZoneId.systemDefault()));
      return xblDeviceToken;
   }

   public StepXblDeviceToken.XblDeviceToken fromJson(JsonObject json) {
      return new StepXblDeviceToken.XblDeviceToken(
         CryptUtil.publicKeyEcFromBase64(json.get("publicKey").getAsString()),
         CryptUtil.privateKeyEcFromBase64(json.get("privateKey").getAsString()),
         UUID.fromString(json.get("id").getAsString()),
         json.get("expireTimeMs").getAsLong(),
         json.get("token").getAsString(),
         json.get("deviceId").getAsString()
      );
   }

   public JsonObject toJson(StepXblDeviceToken.XblDeviceToken xblDeviceToken) {
      JsonObject json = new JsonObject();
      json.addProperty("publicKey", Base64.getEncoder().encodeToString(xblDeviceToken.publicKey.getEncoded()));
      json.addProperty("privateKey", Base64.getEncoder().encodeToString(xblDeviceToken.privateKey.getEncoded()));
      json.addProperty("id", xblDeviceToken.id.toString());
      json.addProperty("expireTimeMs", xblDeviceToken.expireTimeMs);
      json.addProperty("token", xblDeviceToken.token);
      json.addProperty("deviceId", xblDeviceToken.deviceId);
      return json;
   }

   public static final class XblDeviceToken extends AbstractStep.FirstStepResult {
      private final ECPublicKey publicKey;
      private final ECPrivateKey privateKey;
      private final UUID id;
      private final long expireTimeMs;
      private final String token;
      private final String deviceId;

      @Override
      public boolean isExpired() {
         return this.expireTimeMs <= System.currentTimeMillis();
      }

      @Generated
      public XblDeviceToken(ECPublicKey publicKey, ECPrivateKey privateKey, UUID id, long expireTimeMs, String token, String deviceId) {
         this.publicKey = publicKey;
         this.privateKey = privateKey;
         this.id = id;
         this.expireTimeMs = expireTimeMs;
         this.token = token;
         this.deviceId = deviceId;
      }

      @Generated
      public ECPublicKey getPublicKey() {
         return this.publicKey;
      }

      @Generated
      public ECPrivateKey getPrivateKey() {
         return this.privateKey;
      }

      @Generated
      public UUID getId() {
         return this.id;
      }

      @Generated
      public long getExpireTimeMs() {
         return this.expireTimeMs;
      }

      @Generated
      public String getToken() {
         return this.token;
      }

      @Generated
      public String getDeviceId() {
         return this.deviceId;
      }

      @Generated
      @Override
      public String toString() {
         return "StepXblDeviceToken.XblDeviceToken(publicKey="
            + this.getPublicKey()
            + ", privateKey="
            + this.getPrivateKey()
            + ", id="
            + this.getId()
            + ", expireTimeMs="
            + this.getExpireTimeMs()
            + ", token="
            + this.getToken()
            + ", deviceId="
            + this.getDeviceId()
            + ")";
      }

      @Generated
      @Override
      public boolean equals(Object o) {
         if (o == this) {
            return true;
         } else if (!(o instanceof StepXblDeviceToken.XblDeviceToken)) {
            return false;
         } else {
            StepXblDeviceToken.XblDeviceToken other = (StepXblDeviceToken.XblDeviceToken)o;
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
                     Object this$id = this.getId();
                     Object other$id = other.getId();
                     if (this$id == null ? other$id == null : this$id.equals(other$id)) {
                        Object this$token = this.getToken();
                        Object other$token = other.getToken();
                        if (this$token == null ? other$token == null : this$token.equals(other$token)) {
                           Object this$deviceId = this.getDeviceId();
                           Object other$deviceId = other.getDeviceId();
                           return this$deviceId == null ? other$deviceId == null : this$deviceId.equals(other$deviceId);
                        } else {
                           return false;
                        }
                     } else {
                        return false;
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
         return other instanceof StepXblDeviceToken.XblDeviceToken;
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
         Object $id = this.getId();
         result = result * 59 + ($id == null ? 43 : $id.hashCode());
         Object $token = this.getToken();
         result = result * 59 + ($token == null ? 43 : $token.hashCode());
         Object $deviceId = this.getDeviceId();
         return result * 59 + ($deviceId == null ? 43 : $deviceId.hashCode());
      }
   }
}
