package net.raphimc.minecraftauth.step.msa;

import com.google.gson.JsonObject;
import java.time.Instant;
import java.time.ZoneId;
import java.util.HashMap;
import java.util.Map;
import lombok.Generated;
import net.lenni0451.commons.httpclient.HttpClient;
import net.lenni0451.commons.httpclient.content.impl.URLEncodedFormContent;
import net.lenni0451.commons.httpclient.requests.impl.PostRequest;
import net.raphimc.minecraftauth.responsehandler.MsaResponseHandler;
import net.raphimc.minecraftauth.step.AbstractStep;
import net.raphimc.minecraftauth.util.JsonUtil;
import net.raphimc.minecraftauth.util.logging.ILogger;

public class StepMsaToken extends AbstractStep<MsaCodeStep.MsaCode, StepMsaToken.MsaToken> {
   public StepMsaToken(AbstractStep<?, MsaCodeStep.MsaCode> prevStep) {
      super("msaToken", prevStep);
   }

   protected StepMsaToken.MsaToken execute(ILogger logger, HttpClient httpClient, MsaCodeStep.MsaCode msaCode) throws Exception {
      return msaCode.msaToken != null ? msaCode.msaToken : this.execute(logger, httpClient, "authorization_code", msaCode.getCode(), msaCode);
   }

   public StepMsaToken.MsaToken refresh(ILogger logger, HttpClient httpClient, StepMsaToken.MsaToken msaToken) throws Exception {
      if (!msaToken.isExpired()) {
         return msaToken;
      } else {
         return msaToken.getRefreshToken() != null
            ? this.execute(logger, httpClient, "refresh_token", msaToken.getRefreshToken(), msaToken.getMsaCode())
            : super.refresh(logger, httpClient, msaToken);
      }
   }

   public StepMsaToken.MsaToken getFromInput(ILogger logger, HttpClient httpClient, AbstractStep.InitialInput input) throws Exception {
      if (input instanceof StepMsaToken.RefreshToken) {
         StepMsaToken.RefreshToken refreshToken = (StepMsaToken.RefreshToken)input;
         return this.execute(logger, httpClient, "refresh_token", refreshToken.getRefreshToken(), new MsaCodeStep.MsaCode(null));
      } else {
         return (StepMsaToken.MsaToken)super.getFromInput(logger, httpClient, input);
      }
   }

   public StepMsaToken.MsaToken fromJson(JsonObject json) {
      MsaCodeStep.MsaCode msaCode = this.prevStep != null ? this.prevStep.fromJson(json.getAsJsonObject(this.prevStep.name)) : null;
      return new StepMsaToken.MsaToken(
         json.get("expireTimeMs").getAsLong(), json.get("accessToken").getAsString(), JsonUtil.getStringOr(json, "refreshToken", null), msaCode
      );
   }

   public JsonObject toJson(StepMsaToken.MsaToken msaToken) {
      JsonObject json = new JsonObject();
      json.addProperty("expireTimeMs", msaToken.expireTimeMs);
      json.addProperty("accessToken", msaToken.accessToken);
      json.addProperty("refreshToken", msaToken.refreshToken);
      if (this.prevStep != null) {
         json.add(this.prevStep.name, this.prevStep.toJson(msaToken.msaCode));
      }

      return json;
   }

   private StepMsaToken.MsaToken execute(ILogger logger, HttpClient httpClient, String type, String codeOrRefreshToken, MsaCodeStep.MsaCode msaCode) throws Exception {
      logger.info(this, "Getting MSA Token...");
      Map<String, String> postData = new HashMap<>();
      postData.put("client_id", this.applicationDetails.getClientId());
      postData.put("scope", this.applicationDetails.getScope());
      if (this.applicationDetails.getClientSecret() != null) {
         postData.put("client_secret", this.applicationDetails.getClientSecret());
      }

      postData.put("grant_type", type);
      if (type.equals("refresh_token")) {
         postData.put("refresh_token", codeOrRefreshToken);
      } else {
         if (!type.equals("authorization_code")) {
            throw new IllegalArgumentException("Invalid type: " + type);
         }

         postData.put("code", codeOrRefreshToken);
         if (msaCode.customRedirectUri != null) {
            postData.put("redirect_uri", msaCode.customRedirectUri);
         } else {
            postData.put("redirect_uri", this.applicationDetails.getRedirectUri());
         }
      }

      PostRequest postRequest = new PostRequest(this.applicationDetails.getOAuthEnvironment().getTokenUrl());
      postRequest.setContent(new URLEncodedFormContent(postData));
      JsonObject obj = httpClient.execute(postRequest, new MsaResponseHandler());
      StepMsaToken.MsaToken msaToken = new StepMsaToken.MsaToken(
         System.currentTimeMillis() + obj.get("expires_in").getAsLong() * 1000L,
         obj.get("access_token").getAsString(),
         JsonUtil.getStringOr(obj, "refresh_token", null),
         msaCode
      );
      logger.info(this, "Got MSA Token, expires: " + Instant.ofEpochMilli(msaToken.getExpireTimeMs()).atZone(ZoneId.systemDefault()));
      return msaToken;
   }

   public static final class MsaToken extends AbstractStep.StepResult<MsaCodeStep.MsaCode> {
      private final long expireTimeMs;
      private final String accessToken;
      private final String refreshToken;
      private final MsaCodeStep.MsaCode msaCode;

      protected MsaCodeStep.MsaCode prevResult() {
         return this.msaCode;
      }

      @Override
      public boolean isExpired() {
         return this.expireTimeMs <= System.currentTimeMillis();
      }

      @Generated
      public MsaToken(long expireTimeMs, String accessToken, String refreshToken, MsaCodeStep.MsaCode msaCode) {
         this.expireTimeMs = expireTimeMs;
         this.accessToken = accessToken;
         this.refreshToken = refreshToken;
         this.msaCode = msaCode;
      }

      @Generated
      public long getExpireTimeMs() {
         return this.expireTimeMs;
      }

      @Generated
      public String getAccessToken() {
         return this.accessToken;
      }

      @Generated
      public String getRefreshToken() {
         return this.refreshToken;
      }

      @Generated
      public MsaCodeStep.MsaCode getMsaCode() {
         return this.msaCode;
      }

      @Generated
      @Override
      public String toString() {
         return "StepMsaToken.MsaToken(expireTimeMs="
            + this.getExpireTimeMs()
            + ", accessToken="
            + this.getAccessToken()
            + ", refreshToken="
            + this.getRefreshToken()
            + ", msaCode="
            + this.getMsaCode()
            + ")";
      }

      @Generated
      @Override
      public boolean equals(Object o) {
         if (o == this) {
            return true;
         } else if (!(o instanceof StepMsaToken.MsaToken)) {
            return false;
         } else {
            StepMsaToken.MsaToken other = (StepMsaToken.MsaToken)o;
            if (!other.canEqual(this)) {
               return false;
            } else if (this.getExpireTimeMs() != other.getExpireTimeMs()) {
               return false;
            } else {
               Object this$accessToken = this.getAccessToken();
               Object other$accessToken = other.getAccessToken();
               if (this$accessToken == null ? other$accessToken == null : this$accessToken.equals(other$accessToken)) {
                  Object this$refreshToken = this.getRefreshToken();
                  Object other$refreshToken = other.getRefreshToken();
                  if (this$refreshToken == null ? other$refreshToken == null : this$refreshToken.equals(other$refreshToken)) {
                     Object this$msaCode = this.getMsaCode();
                     Object other$msaCode = other.getMsaCode();
                     return this$msaCode == null ? other$msaCode == null : this$msaCode.equals(other$msaCode);
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
         return other instanceof StepMsaToken.MsaToken;
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
         Object $refreshToken = this.getRefreshToken();
         result = result * 59 + ($refreshToken == null ? 43 : $refreshToken.hashCode());
         Object $msaCode = this.getMsaCode();
         return result * 59 + ($msaCode == null ? 43 : $msaCode.hashCode());
      }
   }

   public static final class RefreshToken extends AbstractStep.InitialInput {
      private final String refreshToken;

      @Generated
      public RefreshToken(String refreshToken) {
         this.refreshToken = refreshToken;
      }

      @Generated
      public String getRefreshToken() {
         return this.refreshToken;
      }

      @Generated
      @Override
      public String toString() {
         return "StepMsaToken.RefreshToken(refreshToken=" + this.getRefreshToken() + ")";
      }

      @Generated
      @Override
      public boolean equals(Object o) {
         if (o == this) {
            return true;
         } else if (!(o instanceof StepMsaToken.RefreshToken)) {
            return false;
         } else {
            StepMsaToken.RefreshToken other = (StepMsaToken.RefreshToken)o;
            if (!other.canEqual(this)) {
               return false;
            } else {
               Object this$refreshToken = this.getRefreshToken();
               Object other$refreshToken = other.getRefreshToken();
               return this$refreshToken == null ? other$refreshToken == null : this$refreshToken.equals(other$refreshToken);
            }
         }
      }

      @Generated
      protected boolean canEqual(Object other) {
         return other instanceof StepMsaToken.RefreshToken;
      }

      @Generated
      @Override
      public int hashCode() {
         int PRIME = 59;
         Object $refreshToken = this.getRefreshToken();
         return 1 * 59 + ($refreshToken == null ? 43 : $refreshToken.hashCode());
      }
   }
}
