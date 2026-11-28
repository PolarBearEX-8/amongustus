package net.raphimc.minecraftauth.step.edu;

import com.google.gson.JsonObject;
import lombok.Generated;
import net.lenni0451.commons.httpclient.HttpClient;
import net.lenni0451.commons.httpclient.requests.impl.PostRequest;
import net.raphimc.minecraftauth.responsehandler.MinecraftEduServicesResponseHandler;
import net.raphimc.minecraftauth.step.AbstractStep;
import net.raphimc.minecraftauth.step.msa.StepMsaToken;
import net.raphimc.minecraftauth.util.JsonContent;
import net.raphimc.minecraftauth.util.logging.ILogger;

public class StepEduJWT extends AbstractStep<StepMsaToken.MsaToken, StepEduJWT.EduJWT> {
   public static final String MINECRAFT_LOGIN_URL = "https://login.minecrafteduservices.com/v2/signin";
   private final String version;
   private final int buildNumber;
   private final int protocolVersion;

   public StepEduJWT(AbstractStep<?, StepMsaToken.MsaToken> prevStep, String version, int protocolVersion) {
      super("eduJwt", prevStep);
      String[] versionParts = version.split("\\.");
      if (versionParts.length != 3) {
         throw new IllegalArgumentException("Invalid version: " + version);
      } else {
         this.version = version;
         this.buildNumber = Integer.parseInt(versionParts[0]) * 10000000
            + Integer.parseInt(versionParts[1]) * 100000
            + Integer.parseInt(versionParts[2]) * 1000;
         this.protocolVersion = protocolVersion;
      }
   }

   protected StepEduJWT.EduJWT execute(ILogger logger, HttpClient httpClient, StepMsaToken.MsaToken msaToken) throws Exception {
      logger.info(this, "Authenticating with Minecraft Education Services...");
      JsonObject postData = new JsonObject();
      postData.addProperty("accessToken", msaToken.getAccessToken());
      postData.addProperty("build", this.buildNumber);
      postData.addProperty("clientVersion", this.protocolVersion);
      postData.addProperty("displayVersion", this.version);
      postData.addProperty("identityToken", msaToken.getAccessToken());
      postData.addProperty("locale", "en_US");
      postData.addProperty("osVersion", "10.0");
      postData.addProperty("platform", "Windows Desktop Build (Win32)(x64)");
      postData.addProperty("platformCategory", "desktop");
      PostRequest postRequest = new PostRequest("https://login.minecrafteduservices.com/v2/signin");
      postRequest.setContent(new JsonContent(postData));
      JsonObject obj = httpClient.execute(postRequest, new MinecraftEduServicesResponseHandler());
      StepEduJWT.EduJWT eduJwt = new StepEduJWT.EduJWT(obj.get("response").getAsString(), msaToken);
      logger.info(this, "Got Edu JWT");
      return eduJwt;
   }

   public StepEduJWT.EduJWT fromJson(JsonObject json) {
      StepMsaToken.MsaToken msaToken = this.prevStep != null ? this.prevStep.fromJson(json.getAsJsonObject(this.prevStep.name)) : null;
      return new StepEduJWT.EduJWT(json.get("jwt").getAsString(), msaToken);
   }

   public JsonObject toJson(StepEduJWT.EduJWT eduJWT) {
      JsonObject json = new JsonObject();
      json.addProperty("jwt", eduJWT.jwt);
      if (this.prevStep != null) {
         json.add(this.prevStep.name, this.prevStep.toJson(eduJWT.msaToken));
      }

      return json;
   }

   public static final class EduJWT extends AbstractStep.StepResult<StepMsaToken.MsaToken> {
      private final String jwt;
      private final StepMsaToken.MsaToken msaToken;

      protected StepMsaToken.MsaToken prevResult() {
         return this.msaToken;
      }

      @Override
      public boolean isExpired() {
         return true;
      }

      @Override
      public boolean isExpiredOrOutdated() {
         return true;
      }

      @Generated
      public EduJWT(String jwt, StepMsaToken.MsaToken msaToken) {
         this.jwt = jwt;
         this.msaToken = msaToken;
      }

      @Generated
      public String getJwt() {
         return this.jwt;
      }

      @Generated
      public StepMsaToken.MsaToken getMsaToken() {
         return this.msaToken;
      }

      @Generated
      @Override
      public String toString() {
         return "StepEduJWT.EduJWT(jwt=" + this.getJwt() + ", msaToken=" + this.getMsaToken() + ")";
      }

      @Generated
      @Override
      public boolean equals(Object o) {
         if (o == this) {
            return true;
         } else if (!(o instanceof StepEduJWT.EduJWT)) {
            return false;
         } else {
            StepEduJWT.EduJWT other = (StepEduJWT.EduJWT)o;
            if (!other.canEqual(this)) {
               return false;
            } else {
               Object this$jwt = this.getJwt();
               Object other$jwt = other.getJwt();
               if (this$jwt == null ? other$jwt == null : this$jwt.equals(other$jwt)) {
                  Object this$msaToken = this.getMsaToken();
                  Object other$msaToken = other.getMsaToken();
                  return this$msaToken == null ? other$msaToken == null : this$msaToken.equals(other$msaToken);
               } else {
                  return false;
               }
            }
         }
      }

      @Generated
      protected boolean canEqual(Object other) {
         return other instanceof StepEduJWT.EduJWT;
      }

      @Generated
      @Override
      public int hashCode() {
         int PRIME = 59;
         int result = 1;
         Object $jwt = this.getJwt();
         result = result * 59 + ($jwt == null ? 43 : $jwt.hashCode());
         Object $msaToken = this.getMsaToken();
         return result * 59 + ($msaToken == null ? 43 : $msaToken.hashCode());
      }
   }
}
