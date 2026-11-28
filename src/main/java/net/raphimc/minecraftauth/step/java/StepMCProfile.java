package net.raphimc.minecraftauth.step.java;

import com.google.gson.JsonObject;
import java.io.IOException;
import java.util.UUID;
import lombok.Generated;
import net.lenni0451.commons.httpclient.HttpClient;
import net.lenni0451.commons.httpclient.HttpResponse;
import net.lenni0451.commons.httpclient.requests.impl.GetRequest;
import net.raphimc.minecraftauth.responsehandler.MinecraftResponseHandler;
import net.raphimc.minecraftauth.responsehandler.exception.MinecraftRequestException;
import net.raphimc.minecraftauth.step.AbstractStep;
import net.raphimc.minecraftauth.util.UuidUtil;
import net.raphimc.minecraftauth.util.logging.ILogger;

public class StepMCProfile extends AbstractStep<StepMCToken.MCToken, StepMCProfile.MCProfile> {
   public static final String MINECRAFT_PROFILE_URL = "https://api.minecraftservices.com/minecraft/profile";

   public StepMCProfile(AbstractStep<?, StepMCToken.MCToken> prevStep) {
      super("mcProfile", prevStep);
   }

   protected StepMCProfile.MCProfile execute(ILogger logger, HttpClient httpClient, StepMCToken.MCToken mcToken) throws Exception {
      logger.info(this, "Getting profile...");
      GetRequest getRequest = new GetRequest("https://api.minecraftservices.com/minecraft/profile");
      getRequest.setHeader("Authorization", mcToken.getTokenType() + " " + mcToken.getAccessToken());
      JsonObject obj = httpClient.execute(getRequest, new MinecraftResponseHandler() {
         @Override
         public void handleJsonError(HttpResponse response, JsonObject obj) throws IOException {
            try {
               super.handleJsonError(response, obj);
            } catch (MinecraftRequestException e) {
               if (e.getResponse().getStatusCode() == 404) {
                  throw new StepMCProfile.ProfileNotFoundException(e);
               } else {
                  throw e;
               }
            }
         }
      });
      StepMCProfile.MCProfile mcProfile = new StepMCProfile.MCProfile(
         UuidUtil.fromLenientString(obj.get("id").getAsString()),
         obj.get("name").getAsString(),
         obj.get("skins").getAsJsonArray().get(0).getAsJsonObject().get("url").getAsString(),
         mcToken
      );
      logger.info(this, "Got MC Profile, name: " + mcProfile.name + ", uuid: " + mcProfile.id);
      return mcProfile;
   }

   public StepMCProfile.MCProfile fromJson(JsonObject json) {
      StepMCToken.MCToken mcToken = this.prevStep != null ? this.prevStep.fromJson(json.getAsJsonObject(this.prevStep.name)) : null;
      return new StepMCProfile.MCProfile(
         UUID.fromString(json.get("id").getAsString()), json.get("name").getAsString(), json.get("skinUrl").getAsString(), mcToken
      );
   }

   public JsonObject toJson(StepMCProfile.MCProfile mcProfile) {
      JsonObject json = new JsonObject();
      json.addProperty("id", mcProfile.id.toString());
      json.addProperty("name", mcProfile.name);
      json.addProperty("skinUrl", mcProfile.skinUrl);
      if (this.prevStep != null) {
         json.add(this.prevStep.name, this.prevStep.toJson(mcProfile.mcToken));
      }

      return json;
   }

   public static final class MCProfile extends AbstractStep.StepResult<StepMCToken.MCToken> {
      private final UUID id;
      private final String name;
      private final String skinUrl;
      private final StepMCToken.MCToken mcToken;

      protected StepMCToken.MCToken prevResult() {
         return this.mcToken;
      }

      @Override
      public boolean isExpired() {
         return this.prevResult().isExpired();
      }

      @Override
      public boolean isExpiredOrOutdated() {
         return true;
      }

      @Generated
      public MCProfile(UUID id, String name, String skinUrl, StepMCToken.MCToken mcToken) {
         this.id = id;
         this.name = name;
         this.skinUrl = skinUrl;
         this.mcToken = mcToken;
      }

      @Generated
      public UUID getId() {
         return this.id;
      }

      @Generated
      public String getName() {
         return this.name;
      }

      @Generated
      public String getSkinUrl() {
         return this.skinUrl;
      }

      @Generated
      public StepMCToken.MCToken getMcToken() {
         return this.mcToken;
      }

      @Generated
      @Override
      public String toString() {
         return "StepMCProfile.MCProfile(id="
            + this.getId()
            + ", name="
            + this.getName()
            + ", skinUrl="
            + this.getSkinUrl()
            + ", mcToken="
            + this.getMcToken()
            + ")";
      }

      @Generated
      @Override
      public boolean equals(Object o) {
         if (o == this) {
            return true;
         } else if (!(o instanceof StepMCProfile.MCProfile)) {
            return false;
         } else {
            StepMCProfile.MCProfile other = (StepMCProfile.MCProfile)o;
            if (!other.canEqual(this)) {
               return false;
            } else {
               Object this$id = this.getId();
               Object other$id = other.getId();
               if (this$id == null ? other$id == null : this$id.equals(other$id)) {
                  Object this$name = this.getName();
                  Object other$name = other.getName();
                  if (this$name == null ? other$name == null : this$name.equals(other$name)) {
                     Object this$skinUrl = this.getSkinUrl();
                     Object other$skinUrl = other.getSkinUrl();
                     if (this$skinUrl == null ? other$skinUrl == null : this$skinUrl.equals(other$skinUrl)) {
                        Object this$mcToken = this.getMcToken();
                        Object other$mcToken = other.getMcToken();
                        return this$mcToken == null ? other$mcToken == null : this$mcToken.equals(other$mcToken);
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
         return other instanceof StepMCProfile.MCProfile;
      }

      @Generated
      @Override
      public int hashCode() {
         int PRIME = 59;
         int result = 1;
         Object $id = this.getId();
         result = result * 59 + ($id == null ? 43 : $id.hashCode());
         Object $name = this.getName();
         result = result * 59 + ($name == null ? 43 : $name.hashCode());
         Object $skinUrl = this.getSkinUrl();
         result = result * 59 + ($skinUrl == null ? 43 : $skinUrl.hashCode());
         Object $mcToken = this.getMcToken();
         return result * 59 + ($mcToken == null ? 43 : $mcToken.hashCode());
      }
   }

   public static class ProfileNotFoundException extends MinecraftRequestException {
      public ProfileNotFoundException(MinecraftRequestException exception) {
         super(
            exception.getResponse(), exception.getError(), "Your account doesn't have a Minecraft profile. Please login to Minecraft and set up your profile."
         );
      }
   }
}
