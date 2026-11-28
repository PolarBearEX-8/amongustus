package net.raphimc.minecraftauth.step.bedrock;

import com.google.gson.JsonObject;
import java.time.Instant;
import java.time.ZoneId;
import lombok.Generated;
import net.lenni0451.commons.httpclient.HttpClient;
import net.lenni0451.commons.httpclient.requests.impl.PostRequest;
import net.raphimc.minecraftauth.responsehandler.PlayFabResponseHandler;
import net.raphimc.minecraftauth.step.AbstractStep;
import net.raphimc.minecraftauth.step.xbl.StepXblXstsToken;
import net.raphimc.minecraftauth.util.JsonContent;
import net.raphimc.minecraftauth.util.logging.ILogger;

public class StepPlayFabToken extends AbstractStep<StepXblXstsToken.XblXsts<?>, StepPlayFabToken.PlayFabToken> {
   public static final String PLAY_FAB_URL = "https://" + "20CA2".toLowerCase() + ".playfabapi.com/Client/LoginWithXbox";

   public StepPlayFabToken(AbstractStep<?, ? extends StepXblXstsToken.XblXsts<?>> prevStep) {
      super("playFabToken", (AbstractStep<?, StepXblXstsToken.XblXsts<?>>)prevStep);
   }

   protected StepPlayFabToken.PlayFabToken execute(ILogger logger, HttpClient httpClient, StepXblXstsToken.XblXsts<?> xblXsts) throws Exception {
      logger.info(this, "Authenticating with PlayFab...");
      JsonObject postData = new JsonObject();
      postData.addProperty("CreateAccount", true);
      postData.add("EncryptedRequest", null);
      JsonObject infoRequestParameters = new JsonObject();
      infoRequestParameters.addProperty("GetCharacterInventories", false);
      infoRequestParameters.addProperty("GetCharacterList", false);
      infoRequestParameters.addProperty("GetPlayerProfile", true);
      infoRequestParameters.addProperty("GetPlayerStatistics", false);
      infoRequestParameters.addProperty("GetTitleData", false);
      infoRequestParameters.addProperty("GetUserAccountInfo", true);
      infoRequestParameters.addProperty("GetUserData", false);
      infoRequestParameters.addProperty("GetUserInventory", false);
      infoRequestParameters.addProperty("GetUserReadOnlyData", false);
      infoRequestParameters.addProperty("GetUserVirtualCurrency", false);
      infoRequestParameters.add("PlayerStatisticNames", null);
      infoRequestParameters.add("ProfileConstraints", null);
      infoRequestParameters.add("TitleDataKeys", null);
      infoRequestParameters.add("UserDataKeys", null);
      infoRequestParameters.add("UserReadOnlyDataKeys", null);
      postData.add("InfoRequestParameters", infoRequestParameters);
      postData.add("PlayerSecret", null);
      postData.addProperty("TitleId", "20CA2");
      postData.addProperty("XboxToken", "XBL3.0 x=" + xblXsts.getServiceToken());
      PostRequest postRequest = new PostRequest(PLAY_FAB_URL);
      postRequest.setContent(new JsonContent(postData));
      JsonObject obj = httpClient.execute(postRequest, new PlayFabResponseHandler());
      JsonObject data = obj.getAsJsonObject("data");
      JsonObject entityToken = data.getAsJsonObject("EntityToken");
      StepPlayFabToken.PlayFabToken playFabToken = new StepPlayFabToken.PlayFabToken(
         Instant.parse(entityToken.get("TokenExpiration").getAsString()).toEpochMilli(),
         entityToken.get("EntityToken").getAsString(),
         entityToken.get("Entity").getAsJsonObject().get("Id").getAsString(),
         data.get("SessionTicket").getAsString(),
         data.get("PlayFabId").getAsString(),
         xblXsts
      );
      logger.info(this, "Got PlayFab Token, expires: " + Instant.ofEpochMilli(playFabToken.getExpireTimeMs()).atZone(ZoneId.systemDefault()));
      return playFabToken;
   }

   public StepPlayFabToken.PlayFabToken fromJson(JsonObject json) {
      StepXblXstsToken.XblXsts<?> xblXsts = this.prevStep != null ? this.prevStep.fromJson(json.getAsJsonObject(this.prevStep.name)) : null;
      return new StepPlayFabToken.PlayFabToken(
         json.get("expireTimeMs").getAsLong(),
         json.get("entityToken").getAsString(),
         json.get("entityId").getAsString(),
         json.get("sessionTicket").getAsString(),
         json.get("playFabId").getAsString(),
         xblXsts
      );
   }

   public JsonObject toJson(StepPlayFabToken.PlayFabToken playFabToken) {
      JsonObject json = new JsonObject();
      json.addProperty("expireTimeMs", playFabToken.expireTimeMs);
      json.addProperty("entityToken", playFabToken.entityToken);
      json.addProperty("entityId", playFabToken.entityId);
      json.addProperty("sessionTicket", playFabToken.sessionTicket);
      json.addProperty("playFabId", playFabToken.playFabId);
      if (this.prevStep != null) {
         json.add(this.prevStep.name, this.prevStep.toJson(playFabToken.xblXsts));
      }

      return json;
   }

   public static final class PlayFabToken extends AbstractStep.StepResult<StepXblXstsToken.XblXsts<?>> {
      private final long expireTimeMs;
      private final String entityToken;
      private final String entityId;
      private final String sessionTicket;
      private final String playFabId;
      private final StepXblXstsToken.XblXsts<?> xblXsts;

      protected StepXblXstsToken.XblXsts<?> prevResult() {
         return this.xblXsts;
      }

      @Override
      public boolean isExpired() {
         return this.expireTimeMs <= System.currentTimeMillis() || this.prevResult().isExpired();
      }

      @Generated
      public PlayFabToken(long expireTimeMs, String entityToken, String entityId, String sessionTicket, String playFabId, StepXblXstsToken.XblXsts<?> xblXsts) {
         this.expireTimeMs = expireTimeMs;
         this.entityToken = entityToken;
         this.entityId = entityId;
         this.sessionTicket = sessionTicket;
         this.playFabId = playFabId;
         this.xblXsts = xblXsts;
      }

      @Generated
      public long getExpireTimeMs() {
         return this.expireTimeMs;
      }

      @Generated
      public String getEntityToken() {
         return this.entityToken;
      }

      @Generated
      public String getEntityId() {
         return this.entityId;
      }

      @Generated
      public String getSessionTicket() {
         return this.sessionTicket;
      }

      @Generated
      public String getPlayFabId() {
         return this.playFabId;
      }

      @Generated
      public StepXblXstsToken.XblXsts<?> getXblXsts() {
         return this.xblXsts;
      }

      @Generated
      @Override
      public String toString() {
         return "StepPlayFabToken.PlayFabToken(expireTimeMs="
            + this.getExpireTimeMs()
            + ", entityToken="
            + this.getEntityToken()
            + ", entityId="
            + this.getEntityId()
            + ", sessionTicket="
            + this.getSessionTicket()
            + ", playFabId="
            + this.getPlayFabId()
            + ", xblXsts="
            + this.getXblXsts()
            + ")";
      }

      @Generated
      @Override
      public boolean equals(Object o) {
         if (o == this) {
            return true;
         } else if (!(o instanceof StepPlayFabToken.PlayFabToken)) {
            return false;
         } else {
            StepPlayFabToken.PlayFabToken other = (StepPlayFabToken.PlayFabToken)o;
            if (!other.canEqual(this)) {
               return false;
            } else if (this.getExpireTimeMs() != other.getExpireTimeMs()) {
               return false;
            } else {
               Object this$entityToken = this.getEntityToken();
               Object other$entityToken = other.getEntityToken();
               if (this$entityToken == null ? other$entityToken == null : this$entityToken.equals(other$entityToken)) {
                  Object this$entityId = this.getEntityId();
                  Object other$entityId = other.getEntityId();
                  if (this$entityId == null ? other$entityId == null : this$entityId.equals(other$entityId)) {
                     Object this$sessionTicket = this.getSessionTicket();
                     Object other$sessionTicket = other.getSessionTicket();
                     if (this$sessionTicket == null ? other$sessionTicket == null : this$sessionTicket.equals(other$sessionTicket)) {
                        Object this$playFabId = this.getPlayFabId();
                        Object other$playFabId = other.getPlayFabId();
                        if (this$playFabId == null ? other$playFabId == null : this$playFabId.equals(other$playFabId)) {
                           Object this$xblXsts = this.getXblXsts();
                           Object other$xblXsts = other.getXblXsts();
                           return this$xblXsts == null ? other$xblXsts == null : this$xblXsts.equals(other$xblXsts);
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
         return other instanceof StepPlayFabToken.PlayFabToken;
      }

      @Generated
      @Override
      public int hashCode() {
         int PRIME = 59;
         int result = 1;
         long $expireTimeMs = this.getExpireTimeMs();
         result = result * 59 + (int)($expireTimeMs >>> 32 ^ $expireTimeMs);
         Object $entityToken = this.getEntityToken();
         result = result * 59 + ($entityToken == null ? 43 : $entityToken.hashCode());
         Object $entityId = this.getEntityId();
         result = result * 59 + ($entityId == null ? 43 : $entityId.hashCode());
         Object $sessionTicket = this.getSessionTicket();
         result = result * 59 + ($sessionTicket == null ? 43 : $sessionTicket.hashCode());
         Object $playFabId = this.getPlayFabId();
         result = result * 59 + ($playFabId == null ? 43 : $playFabId.hashCode());
         Object $xblXsts = this.getXblXsts();
         return result * 59 + ($xblXsts == null ? 43 : $xblXsts.hashCode());
      }
   }
}
