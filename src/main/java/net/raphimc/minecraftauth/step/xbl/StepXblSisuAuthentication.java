package net.raphimc.minecraftauth.step.xbl;

import com.google.gson.JsonObject;
import java.time.Instant;
import java.time.ZoneId;
import java.util.Map;
import lombok.Generated;
import net.lenni0451.commons.httpclient.HttpClient;
import net.lenni0451.commons.httpclient.model.HttpHeader;
import net.lenni0451.commons.httpclient.requests.impl.PostRequest;
import net.raphimc.minecraftauth.responsehandler.XblResponseHandler;
import net.raphimc.minecraftauth.step.AbstractStep;
import net.raphimc.minecraftauth.step.xbl.session.StepFullXblSession;
import net.raphimc.minecraftauth.step.xbl.session.StepInitialXblSession;
import net.raphimc.minecraftauth.util.CryptUtil;
import net.raphimc.minecraftauth.util.JsonContent;
import net.raphimc.minecraftauth.util.logging.ILogger;

public class StepXblSisuAuthentication extends AbstractStep<StepInitialXblSession.InitialXblSession, StepXblSisuAuthentication.XblSisuTokens> {
   public static final String XBL_SISU_URL = "https://sisu.xboxlive.com/authorize";
   private final String relyingParty;

   public StepXblSisuAuthentication(AbstractStep<?, StepInitialXblSession.InitialXblSession> prevStep, String relyingParty) {
      super("xblSisuAuthentication", prevStep);
      this.relyingParty = relyingParty;
   }

   protected StepXblSisuAuthentication.XblSisuTokens execute(ILogger logger, HttpClient httpClient, StepInitialXblSession.InitialXblSession initialXblSession) throws Exception {
      logger.info(this, "Authenticating with Xbox Live using SISU...");
      if (initialXblSession.getXblDeviceToken() == null) {
         throw new IllegalStateException("An XBL Device Token is needed for SISU authentication");
      } else if (!this.applicationDetails.isTitleClientId()) {
         throw new IllegalStateException("A Title Client ID is needed for SISU authentication");
      } else {
         JsonObject postData = new JsonObject();
         postData.addProperty("AccessToken", "t=" + initialXblSession.getMsaToken().getAccessToken());
         postData.addProperty("DeviceToken", initialXblSession.getXblDeviceToken().getToken());
         postData.addProperty("AppId", this.applicationDetails.getClientId());
         postData.add("ProofKey", CryptUtil.getProofKey(initialXblSession.getXblDeviceToken().getPublicKey()));
         postData.addProperty("SiteName", "user.auth.xboxlive.com");
         postData.addProperty("RelyingParty", this.relyingParty);
         postData.addProperty("Sandbox", "RETAIL");
         postData.addProperty("UseModernGamertag", true);
         PostRequest postRequest = new PostRequest("https://sisu.xboxlive.com/authorize");
         postRequest.setContent(new JsonContent(postData));
         postRequest.setHeader(new HttpHeader[]{CryptUtil.getSignatureHeader(postRequest, initialXblSession.getXblDeviceToken().getPrivateKey())});
         JsonObject obj = httpClient.execute(postRequest, new XblResponseHandler());
         StepXblTitleToken.XblTitleToken xblTitleToken = StepXblTitleToken.XblTitleToken.fromMicrosoftJson(obj.getAsJsonObject("TitleToken"), initialXblSession);
         StepXblUserToken.XblUserToken xblUserToken = StepXblUserToken.XblUserToken.fromMicrosoftJson(obj.getAsJsonObject("UserToken"), initialXblSession);
         StepFullXblSession.FullXblSession fullXblSession = new StepFullXblSession.FullXblSession(xblUserToken, xblTitleToken);
         StepXblXstsToken.XblXstsToken xblXstsToken = StepXblXstsToken.XblXstsToken.fromMicrosoftJson(obj.getAsJsonObject("AuthorizationToken"), fullXblSession);
         StepXblSisuAuthentication.XblSisuTokens xblSisuTokens = new StepXblSisuAuthentication.XblSisuTokens(
            xblUserToken, xblTitleToken, xblXstsToken, initialXblSession
         );
         logger.info(this, "Got XBL User+Title+XSTS Token, expires: " + Instant.ofEpochMilli(xblSisuTokens.getExpireTimeMs()).atZone(ZoneId.systemDefault()));
         return xblSisuTokens;
      }
   }

   public StepXblSisuAuthentication.XblSisuTokens fromJson(JsonObject json) {
      StepInitialXblSession.InitialXblSession initialXblSession = this.prevStep != null
         ? this.prevStep.fromJson(json.getAsJsonObject(this.prevStep.name))
         : null;
      StepXblUserToken.XblUserToken xblUserToken = StepXblUserToken.XblUserToken.fromJson(json.getAsJsonObject("userToken"), initialXblSession);
      StepXblTitleToken.XblTitleToken xblTitleToken = StepXblTitleToken.XblTitleToken.fromJson(json.getAsJsonObject("titleToken"), initialXblSession);
      StepFullXblSession.FullXblSession fullXblSession = new StepFullXblSession.FullXblSession(xblUserToken, xblTitleToken);
      StepXblXstsToken.XblXstsToken xblXstsToken = StepXblXstsToken.XblXstsToken.fromJson(json.getAsJsonObject("xstsToken"), fullXblSession);
      return new StepXblSisuAuthentication.XblSisuTokens(xblUserToken, xblTitleToken, xblXstsToken, initialXblSession);
   }

   public JsonObject toJson(StepXblSisuAuthentication.XblSisuTokens xblSisuTokens) {
      JsonObject json = new JsonObject();
      json.add("userToken", StepXblUserToken.XblUserToken.toJson(xblSisuTokens.userToken));
      json.add("titleToken", StepXblTitleToken.XblTitleToken.toJson(xblSisuTokens.titleToken));
      json.add("xstsToken", StepXblXstsToken.XblXstsToken.toJson(xblSisuTokens.xstsToken));
      if (this.prevStep != null) {
         json.add(this.prevStep.name, this.prevStep.toJson(xblSisuTokens.initialXblSession));
      }

      return json;
   }

   public static final class XblSisuTokens extends StepXblXstsToken.XblXsts<StepInitialXblSession.InitialXblSession> {
      private final StepXblUserToken.XblUserToken userToken;
      private final StepXblTitleToken.XblTitleToken titleToken;
      private final StepXblXstsToken.XblXstsToken xstsToken;
      private final StepInitialXblSession.InitialXblSession initialXblSession;

      @Override
      public long getExpireTimeMs() {
         return Math.min(Math.min(this.userToken.getExpireTimeMs(), this.titleToken.getExpireTimeMs()), this.xstsToken.getExpireTimeMs());
      }

      @Override
      public String getToken() {
         return this.xstsToken.getToken();
      }

      @Override
      public String getUserHash() {
         return this.xstsToken.getUserHash();
      }

      @Override
      public Map<String, String> getDisplayClaims() {
         return this.xstsToken.getDisplayClaims();
      }

      @Override
      public StepFullXblSession.FullXblSession getFullXblSession() {
         return this.xstsToken.getFullXblSession();
      }

      protected StepInitialXblSession.InitialXblSession prevResult() {
         return this.initialXblSession;
      }

      @Override
      public boolean isExpired() {
         return this.userToken.isExpired() || this.titleToken.isExpired() || this.xstsToken.isExpired() || this.prevResult().isExpired();
      }

      @Override
      public boolean isExpiredOrOutdated() {
         return this.userToken.isExpiredOrOutdated()
            || this.titleToken.isExpiredOrOutdated()
            || this.xstsToken.isExpiredOrOutdated()
            || this.prevResult().isExpiredOrOutdated();
      }

      @Generated
      public XblSisuTokens(
         StepXblUserToken.XblUserToken userToken,
         StepXblTitleToken.XblTitleToken titleToken,
         StepXblXstsToken.XblXstsToken xstsToken,
         StepInitialXblSession.InitialXblSession initialXblSession
      ) {
         this.userToken = userToken;
         this.titleToken = titleToken;
         this.xstsToken = xstsToken;
         this.initialXblSession = initialXblSession;
      }

      @Generated
      public StepXblUserToken.XblUserToken getUserToken() {
         return this.userToken;
      }

      @Generated
      public StepXblTitleToken.XblTitleToken getTitleToken() {
         return this.titleToken;
      }

      @Generated
      public StepXblXstsToken.XblXstsToken getXstsToken() {
         return this.xstsToken;
      }

      @Generated
      @Override
      public StepInitialXblSession.InitialXblSession getInitialXblSession() {
         return this.initialXblSession;
      }

      @Generated
      @Override
      public String toString() {
         return "StepXblSisuAuthentication.XblSisuTokens(userToken="
            + this.getUserToken()
            + ", titleToken="
            + this.getTitleToken()
            + ", xstsToken="
            + this.getXstsToken()
            + ", initialXblSession="
            + this.getInitialXblSession()
            + ")";
      }

      @Generated
      @Override
      public boolean equals(Object o) {
         if (o == this) {
            return true;
         } else if (!(o instanceof StepXblSisuAuthentication.XblSisuTokens)) {
            return false;
         } else {
            StepXblSisuAuthentication.XblSisuTokens other = (StepXblSisuAuthentication.XblSisuTokens)o;
            if (!other.canEqual(this)) {
               return false;
            } else {
               Object this$userToken = this.getUserToken();
               Object other$userToken = other.getUserToken();
               if (this$userToken == null ? other$userToken == null : this$userToken.equals(other$userToken)) {
                  Object this$titleToken = this.getTitleToken();
                  Object other$titleToken = other.getTitleToken();
                  if (this$titleToken == null ? other$titleToken == null : this$titleToken.equals(other$titleToken)) {
                     Object this$xstsToken = this.getXstsToken();
                     Object other$xstsToken = other.getXstsToken();
                     if (this$xstsToken == null ? other$xstsToken == null : this$xstsToken.equals(other$xstsToken)) {
                        Object this$initialXblSession = this.getInitialXblSession();
                        Object other$initialXblSession = other.getInitialXblSession();
                        return this$initialXblSession == null ? other$initialXblSession == null : this$initialXblSession.equals(other$initialXblSession);
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
         return other instanceof StepXblSisuAuthentication.XblSisuTokens;
      }

      @Generated
      @Override
      public int hashCode() {
         int PRIME = 59;
         int result = 1;
         Object $userToken = this.getUserToken();
         result = result * 59 + ($userToken == null ? 43 : $userToken.hashCode());
         Object $titleToken = this.getTitleToken();
         result = result * 59 + ($titleToken == null ? 43 : $titleToken.hashCode());
         Object $xstsToken = this.getXstsToken();
         result = result * 59 + ($xstsToken == null ? 43 : $xstsToken.hashCode());
         Object $initialXblSession = this.getInitialXblSession();
         return result * 59 + ($initialXblSession == null ? 43 : $initialXblSession.hashCode());
      }
   }
}
