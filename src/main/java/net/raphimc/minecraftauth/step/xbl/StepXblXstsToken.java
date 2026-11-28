package net.raphimc.minecraftauth.step.xbl;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import java.time.Instant;
import java.time.ZoneId;
import java.util.Collections;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;
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
import net.raphimc.minecraftauth.util.JsonUtil;
import net.raphimc.minecraftauth.util.logging.ILogger;
import org.jetbrains.annotations.ApiStatus.Internal;

public class StepXblXstsToken extends AbstractStep<StepFullXblSession.FullXblSession, StepXblXstsToken.XblXstsToken> {
   public static final String XBL_XSTS_URL = "https://xsts.auth.xboxlive.com/xsts/authorize";
   private final String relyingParty;

   public StepXblXstsToken(AbstractStep<?, StepFullXblSession.FullXblSession> prevStep, String relyingParty) {
      this("xblXstsToken", prevStep, relyingParty);
   }

   public StepXblXstsToken(String name, AbstractStep<?, StepFullXblSession.FullXblSession> prevStep, String relyingParty) {
      super(name, prevStep);
      this.relyingParty = relyingParty;
   }

   protected StepXblXstsToken.XblXstsToken execute(ILogger logger, HttpClient httpClient, StepFullXblSession.FullXblSession fullXblSession) throws Exception {
      logger.info(this, "Requesting XSTS Token...");
      JsonObject postData = new JsonObject();
      JsonObject properties = new JsonObject();
      properties.addProperty("SandboxId", "RETAIL");
      JsonArray userTokens = new JsonArray();
      userTokens.add(new JsonPrimitive(fullXblSession.getXblUserToken().getToken()));
      properties.add("UserTokens", userTokens);
      if (fullXblSession.getXblTitleToken() != null) {
         properties.addProperty("TitleToken", fullXblSession.getXblTitleToken().getToken());
         properties.addProperty("DeviceToken", fullXblSession.getXblTitleToken().getInitialXblSession().getXblDeviceToken().getToken());
      }

      postData.add("Properties", properties);
      postData.addProperty("RelyingParty", this.relyingParty);
      postData.addProperty("TokenType", "JWT");
      PostRequest postRequest = new PostRequest("https://xsts.auth.xboxlive.com/xsts/authorize");
      postRequest.setContent(new JsonContent(postData));
      postRequest.setHeader("x-xbl-contract-version", "1");
      if (fullXblSession.getXblTitleToken() != null) {
         postRequest.setHeader(
            new HttpHeader[]{
               CryptUtil.getSignatureHeader(postRequest, fullXblSession.getXblTitleToken().getInitialXblSession().getXblDeviceToken().getPrivateKey())
            }
         );
      }

      JsonObject obj = httpClient.execute(postRequest, new XblResponseHandler());
      StepXblXstsToken.XblXstsToken xblXstsToken = StepXblXstsToken.XblXstsToken.fromMicrosoftJson(obj, fullXblSession);
      logger.info(this, "Got XSTS Token, expires: " + Instant.ofEpochMilli(xblXstsToken.getExpireTimeMs()).atZone(ZoneId.systemDefault()));
      return xblXstsToken;
   }

   public StepXblXstsToken.XblXstsToken fromJson(JsonObject json) {
      return StepXblXstsToken.XblXstsToken.fromJson(json, this.prevStep != null ? this.prevStep.fromJson(json.getAsJsonObject(this.prevStep.name)) : null);
   }

   public JsonObject toJson(StepXblXstsToken.XblXstsToken xblXstsToken) {
      JsonObject json = StepXblXstsToken.XblXstsToken.toJson(xblXstsToken);
      if (this.prevStep != null) {
         json.add(this.prevStep.name, this.prevStep.toJson(xblXstsToken.fullXblSession));
      }

      return json;
   }

   public abstract static class XblXsts<P extends AbstractStep.StepResult<?>> extends AbstractStep.StepResult<P> {
      public abstract long getExpireTimeMs();

      public abstract String getToken();

      public abstract String getUserHash();

      public abstract Map<String, String> getDisplayClaims();

      public String getServiceToken() {
         return this.getUserHash() + ';' + this.getToken();
      }

      public abstract StepFullXblSession.FullXblSession getFullXblSession();

      public StepInitialXblSession.InitialXblSession getInitialXblSession() {
         return this.getFullXblSession().getXblUserToken().getInitialXblSession();
      }
   }

   public static final class XblXstsToken extends StepXblXstsToken.XblXsts<StepFullXblSession.FullXblSession> {
      private final long expireTimeMs;
      private final String token;
      private final String userHash;
      private final Map<String, String> displayClaims;
      private final StepFullXblSession.FullXblSession fullXblSession;

      @Internal
      public static StepXblXstsToken.XblXstsToken fromMicrosoftJson(JsonObject obj, StepFullXblSession.FullXblSession fullXblSession) {
         JsonObject displayClaims = obj.getAsJsonObject("DisplayClaims").getAsJsonArray("xui").get(0).getAsJsonObject();
         Map<String, String> displayClaimsMap = displayClaims.entrySet()
            .stream()
            .collect(Collectors.toMap(Entry::getKey, e -> ((JsonElement)e.getValue()).getAsString()));
         return new StepXblXstsToken.XblXstsToken(
            Instant.parse(obj.get("NotAfter").getAsString()).toEpochMilli(),
            obj.get("Token").getAsString(),
            displayClaims.get("uhs").getAsString(),
            Collections.unmodifiableMap(displayClaimsMap),
            fullXblSession
         );
      }

      @Internal
      public static StepXblXstsToken.XblXstsToken fromJson(JsonObject json, StepFullXblSession.FullXblSession fullXblSession) {
         JsonObject displayClaims = JsonUtil.getObjectNonNull(json, "displayClaims");
         return new StepXblXstsToken.XblXstsToken(
            json.get("expireTimeMs").getAsLong(),
            json.get("token").getAsString(),
            json.get("userHash").getAsString(),
            Collections.unmodifiableMap(
               displayClaims.entrySet().stream().collect(Collectors.toMap(Entry::getKey, e -> ((JsonElement)e.getValue()).getAsString()))
            ),
            fullXblSession
         );
      }

      @Internal
      public static JsonObject toJson(StepXblXstsToken.XblXstsToken xblXstsToken) {
         JsonObject json = new JsonObject();
         json.addProperty("expireTimeMs", xblXstsToken.expireTimeMs);
         json.addProperty("token", xblXstsToken.token);
         json.addProperty("userHash", xblXstsToken.userHash);
         json.add("displayClaims", JsonUtil.GSON.toJsonTree(xblXstsToken.displayClaims));
         return json;
      }

      protected StepFullXblSession.FullXblSession prevResult() {
         return this.fullXblSession;
      }

      @Override
      public boolean isExpired() {
         return this.expireTimeMs <= System.currentTimeMillis() || this.prevResult().isExpired();
      }

      @Generated
      public XblXstsToken(long expireTimeMs, String token, String userHash, Map<String, String> displayClaims, StepFullXblSession.FullXblSession fullXblSession) {
         this.expireTimeMs = expireTimeMs;
         this.token = token;
         this.userHash = userHash;
         this.displayClaims = displayClaims;
         this.fullXblSession = fullXblSession;
      }

      @Generated
      @Override
      public long getExpireTimeMs() {
         return this.expireTimeMs;
      }

      @Generated
      @Override
      public String getToken() {
         return this.token;
      }

      @Generated
      @Override
      public String getUserHash() {
         return this.userHash;
      }

      @Generated
      @Override
      public Map<String, String> getDisplayClaims() {
         return this.displayClaims;
      }

      @Generated
      @Override
      public StepFullXblSession.FullXblSession getFullXblSession() {
         return this.fullXblSession;
      }

      @Generated
      @Override
      public String toString() {
         return "StepXblXstsToken.XblXstsToken(expireTimeMs="
            + this.getExpireTimeMs()
            + ", token="
            + this.getToken()
            + ", userHash="
            + this.getUserHash()
            + ", displayClaims="
            + this.getDisplayClaims()
            + ", fullXblSession="
            + this.getFullXblSession()
            + ")";
      }

      @Generated
      @Override
      public boolean equals(Object o) {
         if (o == this) {
            return true;
         } else if (!(o instanceof StepXblXstsToken.XblXstsToken)) {
            return false;
         } else {
            StepXblXstsToken.XblXstsToken other = (StepXblXstsToken.XblXstsToken)o;
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
                     Object this$displayClaims = this.getDisplayClaims();
                     Object other$displayClaims = other.getDisplayClaims();
                     if (this$displayClaims == null ? other$displayClaims == null : this$displayClaims.equals(other$displayClaims)) {
                        Object this$fullXblSession = this.getFullXblSession();
                        Object other$fullXblSession = other.getFullXblSession();
                        return this$fullXblSession == null ? other$fullXblSession == null : this$fullXblSession.equals(other$fullXblSession);
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
         return other instanceof StepXblXstsToken.XblXstsToken;
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
         Object $displayClaims = this.getDisplayClaims();
         result = result * 59 + ($displayClaims == null ? 43 : $displayClaims.hashCode());
         Object $fullXblSession = this.getFullXblSession();
         return result * 59 + ($fullXblSession == null ? 43 : $fullXblSession.hashCode());
      }
   }
}
