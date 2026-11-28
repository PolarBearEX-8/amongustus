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

public class StepXblTitleToken extends AbstractStep<StepInitialXblSession.InitialXblSession, StepXblTitleToken.XblTitleToken> {
   public static final String XBL_TITLE_URL = "https://title.auth.xboxlive.com/title/authenticate";

   public StepXblTitleToken(AbstractStep<?, StepInitialXblSession.InitialXblSession> prevStep) {
      super("titleToken", prevStep);
   }

   protected StepXblTitleToken.XblTitleToken execute(ILogger logger, HttpClient httpClient, StepInitialXblSession.InitialXblSession initialXblSession) throws Exception {
      logger.info(this, "Authenticating title with Xbox Live...");
      if (initialXblSession.getXblDeviceToken() == null) {
         throw new IllegalStateException("An XBL Device Token is needed for Title authentication");
      } else if (!this.applicationDetails.isTitleClientId()) {
         throw new IllegalStateException("A Title Client ID is needed for Title authentication");
      } else {
         JsonObject postData = new JsonObject();
         JsonObject properties = new JsonObject();
         properties.addProperty("AuthMethod", "RPS");
         properties.addProperty("SiteName", "user.auth.xboxlive.com");
         properties.addProperty("RpsTicket", "t=" + initialXblSession.getMsaToken().getAccessToken());
         properties.addProperty("DeviceToken", initialXblSession.getXblDeviceToken().getToken());
         properties.add("ProofKey", CryptUtil.getProofKey(initialXblSession.getXblDeviceToken().getPublicKey()));
         postData.add("Properties", properties);
         postData.addProperty("RelyingParty", "http://auth.xboxlive.com");
         postData.addProperty("TokenType", "JWT");
         PostRequest postRequest = new PostRequest("https://title.auth.xboxlive.com/title/authenticate");
         postRequest.setContent(new JsonContent(postData));
         postRequest.setHeader("x-xbl-contract-version", "1");
         postRequest.setHeader(new HttpHeader[]{CryptUtil.getSignatureHeader(postRequest, initialXblSession.getXblDeviceToken().getPrivateKey())});
         JsonObject obj = httpClient.execute(postRequest, new XblResponseHandler());
         StepXblTitleToken.XblTitleToken xblTitleToken = StepXblTitleToken.XblTitleToken.fromMicrosoftJson(obj, initialXblSession);
         logger.info(this, "Got XBL Title Token, expires: " + Instant.ofEpochMilli(xblTitleToken.getExpireTimeMs()).atZone(ZoneId.systemDefault()));
         return xblTitleToken;
      }
   }

   public StepXblTitleToken.XblTitleToken fromJson(JsonObject json) {
      return StepXblTitleToken.XblTitleToken.fromJson(json, this.prevStep != null
         ? this.prevStep.fromJson(json.getAsJsonObject(this.prevStep.name))
         : null);
   }

   public JsonObject toJson(StepXblTitleToken.XblTitleToken xblTitleToken) {
      JsonObject json = StepXblTitleToken.XblTitleToken.toJson(xblTitleToken);
      if (this.prevStep != null) {
         json.add(this.prevStep.name, this.prevStep.toJson(xblTitleToken.initialXblSession));
      }

      return json;
   }

   public static final class XblTitleToken extends AbstractStep.StepResult<StepInitialXblSession.InitialXblSession> {
      private final long expireTimeMs;
      private final String token;
      private final String titleId;
      private final StepInitialXblSession.InitialXblSession initialXblSession;

      @Internal
      public static StepXblTitleToken.XblTitleToken fromMicrosoftJson(JsonObject obj, StepInitialXblSession.InitialXblSession initialXblSession) {
         return new StepXblTitleToken.XblTitleToken(
            Instant.parse(obj.get("NotAfter").getAsString()).toEpochMilli(),
            obj.get("Token").getAsString(),
            obj.getAsJsonObject("DisplayClaims").getAsJsonObject("xti").get("tid").getAsString(),
            initialXblSession
         );
      }

      @Internal
      public static StepXblTitleToken.XblTitleToken fromJson(JsonObject json, StepInitialXblSession.InitialXblSession initialXblSession) {
         return new StepXblTitleToken.XblTitleToken(
            json.get("expireTimeMs").getAsLong(), json.get("token").getAsString(), json.get("titleId").getAsString(), initialXblSession
         );
      }

      @Internal
      public static JsonObject toJson(StepXblTitleToken.XblTitleToken xblTitleToken) {
         JsonObject json = new JsonObject();
         json.addProperty("expireTimeMs", xblTitleToken.expireTimeMs);
         json.addProperty("token", xblTitleToken.token);
         json.addProperty("titleId", xblTitleToken.titleId);
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
      public XblTitleToken(long expireTimeMs, String token, String titleId, StepInitialXblSession.InitialXblSession initialXblSession) {
         this.expireTimeMs = expireTimeMs;
         this.token = token;
         this.titleId = titleId;
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
      public String getTitleId() {
         return this.titleId;
      }

      @Generated
      public StepInitialXblSession.InitialXblSession getInitialXblSession() {
         return this.initialXblSession;
      }

      @Generated
      @Override
      public String toString() {
         return "StepXblTitleToken.XblTitleToken(expireTimeMs="
            + this.getExpireTimeMs()
            + ", token="
            + this.getToken()
            + ", titleId="
            + this.getTitleId()
            + ", initialXblSession="
            + this.getInitialXblSession()
            + ")";
      }

      @Generated
      @Override
      public boolean equals(Object o) {
         if (o == this) {
            return true;
         } else if (!(o instanceof StepXblTitleToken.XblTitleToken)) {
            return false;
         } else {
            StepXblTitleToken.XblTitleToken other = (StepXblTitleToken.XblTitleToken)o;
            if (!other.canEqual(this)) {
               return false;
            } else if (this.getExpireTimeMs() != other.getExpireTimeMs()) {
               return false;
            } else {
               Object this$token = this.getToken();
               Object other$token = other.getToken();
               if (this$token == null ? other$token == null : this$token.equals(other$token)) {
                  Object this$titleId = this.getTitleId();
                  Object other$titleId = other.getTitleId();
                  if (this$titleId == null ? other$titleId == null : this$titleId.equals(other$titleId)) {
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
         return other instanceof StepXblTitleToken.XblTitleToken;
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
         Object $titleId = this.getTitleId();
         result = result * 59 + ($titleId == null ? 43 : $titleId.hashCode());
         Object $initialXblSession = this.getInitialXblSession();
         return result * 59 + ($initialXblSession == null ? 43 : $initialXblSession.hashCode());
      }
   }
}
