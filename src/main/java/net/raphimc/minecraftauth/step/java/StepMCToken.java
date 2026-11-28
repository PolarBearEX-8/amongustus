package net.raphimc.minecraftauth.step.java;

import com.google.gson.JsonObject;
import java.time.Instant;
import java.time.ZoneId;
import lombok.Generated;
import net.lenni0451.commons.httpclient.HttpClient;
import net.lenni0451.commons.httpclient.requests.impl.PostRequest;
import net.raphimc.minecraftauth.responsehandler.MinecraftResponseHandler;
import net.raphimc.minecraftauth.step.AbstractStep;
import net.raphimc.minecraftauth.step.xbl.StepXblXstsToken;
import net.raphimc.minecraftauth.util.JsonContent;
import net.raphimc.minecraftauth.util.logging.ILogger;

public class StepMCToken extends AbstractStep<StepXblXstsToken.XblXsts<?>, StepMCToken.MCToken> {
   public static final String MINECRAFT_LOGIN_URL = "https://api.minecraftservices.com/launcher/login";

   public StepMCToken(AbstractStep<?, ? extends StepXblXstsToken.XblXsts<?>> prevStep) {
      super("mcToken", (AbstractStep<?, StepXblXstsToken.XblXsts<?>>)prevStep);
   }

   protected StepMCToken.MCToken execute(ILogger logger, HttpClient httpClient, StepXblXstsToken.XblXsts<?> xblXsts) throws Exception {
      logger.info(this, "Authenticating with Minecraft Services...");
      JsonObject postData = new JsonObject();
      postData.addProperty("platform", "PC_LAUNCHER");
      postData.addProperty("xtoken", "XBL3.0 x=" + xblXsts.getServiceToken());
      PostRequest postRequest = new PostRequest("https://api.minecraftservices.com/launcher/login");
      postRequest.setContent(new JsonContent(postData));
      JsonObject obj = httpClient.execute(postRequest, new MinecraftResponseHandler());
      StepMCToken.MCToken mcToken = new StepMCToken.MCToken(
         obj.get("access_token").getAsString(),
         obj.get("token_type").getAsString(),
         System.currentTimeMillis() + obj.get("expires_in").getAsLong() * 1000L,
         xblXsts
      );
      logger.info(this, "Got MC Token, expires: " + Instant.ofEpochMilli(mcToken.getExpireTimeMs()).atZone(ZoneId.systemDefault()));
      return mcToken;
   }

   public StepMCToken.MCToken fromJson(JsonObject json) {
      StepXblXstsToken.XblXsts<?> xblXsts = this.prevStep != null ? this.prevStep.fromJson(json.getAsJsonObject(this.prevStep.name)) : null;
      return new StepMCToken.MCToken(json.get("accessToken").getAsString(), json.get("tokenType").getAsString(), json.get("expireTimeMs").getAsLong(), xblXsts);
   }

   public JsonObject toJson(StepMCToken.MCToken mcToken) {
      JsonObject json = new JsonObject();
      json.addProperty("accessToken", mcToken.accessToken);
      json.addProperty("tokenType", mcToken.tokenType);
      json.addProperty("expireTimeMs", mcToken.expireTimeMs);
      if (this.prevStep != null) {
         json.add(this.prevStep.name, this.prevStep.toJson(mcToken.xblXsts));
      }

      return json;
   }

   public static final class MCToken extends AbstractStep.StepResult<StepXblXstsToken.XblXsts<?>> {
      private final String accessToken;
      private final String tokenType;
      private final long expireTimeMs;
      private final StepXblXstsToken.XblXsts<?> xblXsts;

      protected StepXblXstsToken.XblXsts<?> prevResult() {
         return this.xblXsts;
      }

      @Override
      public boolean isExpired() {
         return this.expireTimeMs <= System.currentTimeMillis() || this.prevResult().isExpired();
      }

      @Generated
      public MCToken(String accessToken, String tokenType, long expireTimeMs, StepXblXstsToken.XblXsts<?> xblXsts) {
         this.accessToken = accessToken;
         this.tokenType = tokenType;
         this.expireTimeMs = expireTimeMs;
         this.xblXsts = xblXsts;
      }

      @Generated
      public String getAccessToken() {
         return this.accessToken;
      }

      @Generated
      public String getTokenType() {
         return this.tokenType;
      }

      @Generated
      public long getExpireTimeMs() {
         return this.expireTimeMs;
      }

      @Generated
      public StepXblXstsToken.XblXsts<?> getXblXsts() {
         return this.xblXsts;
      }

      @Generated
      @Override
      public String toString() {
         return "StepMCToken.MCToken(accessToken="
            + this.getAccessToken()
            + ", tokenType="
            + this.getTokenType()
            + ", expireTimeMs="
            + this.getExpireTimeMs()
            + ", xblXsts="
            + this.getXblXsts()
            + ")";
      }

      @Generated
      @Override
      public boolean equals(Object o) {
         if (o == this) {
            return true;
         } else if (!(o instanceof StepMCToken.MCToken)) {
            return false;
         } else {
            StepMCToken.MCToken other = (StepMCToken.MCToken)o;
            if (!other.canEqual(this)) {
               return false;
            } else if (this.getExpireTimeMs() != other.getExpireTimeMs()) {
               return false;
            } else {
               Object this$accessToken = this.getAccessToken();
               Object other$accessToken = other.getAccessToken();
               if (this$accessToken == null ? other$accessToken == null : this$accessToken.equals(other$accessToken)) {
                  Object this$tokenType = this.getTokenType();
                  Object other$tokenType = other.getTokenType();
                  if (this$tokenType == null ? other$tokenType == null : this$tokenType.equals(other$tokenType)) {
                     Object this$xblXsts = this.getXblXsts();
                     Object other$xblXsts = other.getXblXsts();
                     return this$xblXsts == null ? other$xblXsts == null : this$xblXsts.equals(other$xblXsts);
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
         return other instanceof StepMCToken.MCToken;
      }

      @Generated
      @Override
      public int hashCode() {
         int PRIME = 59;
         int result = 1;
         long $expireTimeMs = this.getExpireTimeMs();
         result = result * 59 + (int)($expireTimeMs >>> 32 ^ $expireTimeMs);
         Object $accessToken = this.getAccessToken();
         result = result * 59 + ($accessToken == null ? 43 : $accessToken.hashCode());
         Object $tokenType = this.getTokenType();
         result = result * 59 + ($tokenType == null ? 43 : $tokenType.hashCode());
         Object $xblXsts = this.getXblXsts();
         return result * 59 + ($xblXsts == null ? 43 : $xblXsts.hashCode());
      }
   }
}
