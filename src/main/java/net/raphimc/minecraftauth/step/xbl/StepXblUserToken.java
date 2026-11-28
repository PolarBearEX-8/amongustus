package net.raphimc.minecraftauth.step.xbl;

import com.google.gson.JsonObject;
import java.time.Instant;
import java.time.ZoneId;
import lombok.Generated;
import net.lenni0451.commons.httpclient.HttpClient;
import net.lenni0451.commons.httpclient.model.HttpHeader;
import net.lenni0451.commons.httpclient.requests.impl.PostRequest;
import net.raphimc.minecraftauth.responsehandler.XblResponseHandler;
import net.raphimc.minecraftauth.step.AbstractStep;
import net.raphimc.minecraftauth.step.xbl.session.StepInitialXblSession;
import net.raphimc.minecraftauth.util.CryptUtil;
import net.raphimc.minecraftauth.util.JsonContent;
import net.raphimc.minecraftauth.util.logging.ILogger;
import org.jetbrains.annotations.ApiStatus.Internal;

public class StepXblUserToken extends AbstractStep<StepInitialXblSession.InitialXblSession, StepXblUserToken.XblUserToken> {
   public static final String XBL_USER_URL = "https://user.auth.xboxlive.com/user/authenticate";

   public StepXblUserToken(AbstractStep<?, StepInitialXblSession.InitialXblSession> prevStep) {
      super("xblUserToken", prevStep);
   }

   protected StepXblUserToken.XblUserToken execute(ILogger logger, HttpClient httpClient, StepInitialXblSession.InitialXblSession initialXblSession) throws Exception {
      logger.info(this, "Authenticating user with Xbox Live...");
      JsonObject postData = new JsonObject();
      JsonObject properties = new JsonObject();
      properties.addProperty("AuthMethod", "RPS");
      properties.addProperty("SiteName", "user.auth.xboxlive.com");
      properties.addProperty("RpsTicket", (this.applicationDetails.isTitleClientId() ? "t=" : "d=") + initialXblSession.getMsaToken().getAccessToken());
      if (initialXblSession.getXblDeviceToken() != null) {
         properties.add("ProofKey", CryptUtil.getProofKey(initialXblSession.getXblDeviceToken().getPublicKey()));
      }

      postData.add("Properties", properties);
      postData.addProperty("RelyingParty", "http://auth.xboxlive.com");
      postData.addProperty("TokenType", "JWT");
      PostRequest postRequest = new PostRequest("https://user.auth.xboxlive.com/user/authenticate");
      postRequest.setContent(new JsonContent(postData));
      postRequest.setHeader("x-xbl-contract-version", "1");
      if (initialXblSession.getXblDeviceToken() != null) {
         postRequest.setHeader(new HttpHeader[]{CryptUtil.getSignatureHeader(postRequest, initialXblSession.getXblDeviceToken().getPrivateKey())});
      }

      JsonObject obj = httpClient.execute(postRequest, new XblResponseHandler());
      StepXblUserToken.XblUserToken xblUserToken = StepXblUserToken.XblUserToken.fromMicrosoftJson(obj, initialXblSession);
      logger.info(this, "Got XBL User Token, expires: " + Instant.ofEpochMilli(xblUserToken.getExpireTimeMs()).atZone(ZoneId.systemDefault()));
      return xblUserToken;
   }

   public StepXblUserToken.XblUserToken fromJson(JsonObject json) {
      return StepXblUserToken.XblUserToken.fromJson(json, this.prevStep != null
         ? this.prevStep.fromJson(json.getAsJsonObject(this.prevStep.name))
         : null);
   }

   public JsonObject toJson(StepXblUserToken.XblUserToken xblUserToken) {
      JsonObject json = StepXblUserToken.XblUserToken.toJson(xblUserToken);
      if (this.prevStep != null) {
         json.add(this.prevStep.name, this.prevStep.toJson(xblUserToken.initialXblSession));
      }

      return json;
   }

   public static final class XblUserToken extends AbstractStep.StepResult<StepInitialXblSession.InitialXblSession> {
      private final long expireTimeMs;
      private final String token;
      private final String userHash;
      private final StepInitialXblSession.InitialXblSession initialXblSession;

      @Internal
      public static StepXblUserToken.XblUserToken fromMicrosoftJson(JsonObject obj, StepInitialXblSession.InitialXblSession initialXblSession) {
         return new StepXblUserToken.XblUserToken(
            Instant.parse(obj.get("NotAfter").getAsString()).toEpochMilli(),
            obj.get("Token").getAsString(),
            obj.getAsJsonObject("DisplayClaims").getAsJsonArray("xui").get(0).getAsJsonObject().get("uhs").getAsString(),
            initialXblSession
         );
      }

      @Internal
      public static StepXblUserToken.XblUserToken fromJson(JsonObject json, StepInitialXblSession.InitialXblSession initialXblSession) {
         return new StepXblUserToken.XblUserToken(
            json.get("expireTimeMs").getAsLong(), json.get("token").getAsString(), json.get("userHash").getAsString(), initialXblSession
         );
      }

      @Internal
      public static JsonObject toJson(StepXblUserToken.XblUserToken xblUserToken) {
         JsonObject json = new JsonObject();
         json.addProperty("expireTimeMs", xblUserToken.expireTimeMs);
         json.addProperty("token", xblUserToken.token);
         json.addProperty("userHash", xblUserToken.userHash);
         return json;
      }

      protected StepInitialXblSession.InitialXblSession prevResult() {
         return this.initialXblSession;
      }

      @Override
      public boolean isExpired() {
         return this.expireTimeMs <= System.currentTimeMillis() || this.prevResult().isExpired();
      }

      @Generated
      public XblUserToken(long expireTimeMs, String token, String userHash, StepInitialXblSession.InitialXblSession initialXblSession) {
         this.expireTimeMs = expireTimeMs;
         this.token = token;
         this.userHash = userHash;
         this.initialXblSession = initialXblSession;
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
      public String getUserHash() {
         return this.userHash;
      }

      @Generated
      public StepInitialXblSession.InitialXblSession getInitialXblSession() {
         return this.initialXblSession;
      }

      @Generated
      @Override
      public String toString() {
         return "StepXblUserToken.XblUserToken(expireTimeMs="
            + this.getExpireTimeMs()
            + ", token="
            + this.getToken()
            + ", userHash="
            + this.getUserHash()
            + ", initialXblSession="
            + this.getInitialXblSession()
            + ")";
      }

      @Generated
      @Override
      public boolean equals(Object o) {
         if (o == this) {
            return true;
         } else if (!(o instanceof StepXblUserToken.XblUserToken)) {
            return false;
         } else {
            StepXblUserToken.XblUserToken other = (StepXblUserToken.XblUserToken)o;
            if (!other.canEqual(this)) {
               return false;
            } else if (this.getExpireTimeMs() != other.getExpireTimeMs()) {
               return false;
            } else {
               Object this$token = this.getToken();
               Object other$token = other.getToken();
               if (this$token == null ? other$token == null : this$token.equals(other$token)) {
                  Object this$userHash = this.getUserHash();
                  Object other$userHash = other.getUserHash();
                  if (this$userHash == null ? other$userHash == null : this$userHash.equals(other$userHash)) {
                     Object this$initialXblSession = this.getInitialXblSession();
                     Object other$initialXblSession = other.getInitialXblSession();
                     return this$initialXblSession == null ? other$initialXblSession == null : this$initialXblSession.equals(other$initialXblSession);
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
         return other instanceof StepXblUserToken.XblUserToken;
      }

      @Generated
      @Override
      public int hashCode() {
         int PRIME = 59;
         int result = 1;
         long $expireTimeMs = this.getExpireTimeMs();
         result = result * 59 + (int)($expireTimeMs >>> 32 ^ $expireTimeMs);
         Object $token = this.getToken();
         result = result * 59 + ($token == null ? 43 : $token.hashCode());
         Object $userHash = this.getUserHash();
         result = result * 59 + ($userHash == null ? 43 : $userHash.hashCode());
         Object $initialXblSession = this.getInitialXblSession();
         return result * 59 + ($initialXblSession == null ? 43 : $initialXblSession.hashCode());
      }
   }
}
