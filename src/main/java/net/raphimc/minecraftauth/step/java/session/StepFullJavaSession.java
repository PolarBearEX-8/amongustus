package net.raphimc.minecraftauth.step.java.session;

import com.google.gson.JsonObject;
import lombok.Generated;
import net.lenni0451.commons.httpclient.HttpClient;
import net.raphimc.minecraftauth.step.AbstractStep;
import net.raphimc.minecraftauth.step.BiMergeStep;
import net.raphimc.minecraftauth.step.SameInputBiMergeStep;
import net.raphimc.minecraftauth.step.java.StepMCProfile;
import net.raphimc.minecraftauth.step.java.StepPlayerCertificates;
import net.raphimc.minecraftauth.util.logging.ILogger;

public class StepFullJavaSession
   extends SameInputBiMergeStep<StepMCProfile.MCProfile, StepPlayerCertificates.PlayerCertificates, StepFullJavaSession.FullJavaSession> {
   public StepFullJavaSession(AbstractStep<?, StepMCProfile.MCProfile> prevStep1, AbstractStep<?, StepPlayerCertificates.PlayerCertificates> prevStep2) {
      super("fullJavaSession", prevStep1, prevStep2);
   }

   protected StepFullJavaSession.FullJavaSession execute(
      ILogger logger, HttpClient httpClient, StepMCProfile.MCProfile mcProfile, StepPlayerCertificates.PlayerCertificates playerCertificates
   ) throws Exception {
      return new StepFullJavaSession.FullJavaSession(mcProfile, playerCertificates);
   }

   public StepFullJavaSession.FullJavaSession fromUnoptimizedJson(JsonObject json) {
      StepMCProfile.MCProfile mcProfile = this.prevStep != null ? this.prevStep.fromJson(json.getAsJsonObject(this.prevStep.name)) : null;
      StepPlayerCertificates.PlayerCertificates playerCertificates = this.prevStep2 != null
         ? this.prevStep2.fromJson(json.getAsJsonObject(this.prevStep2.name))
         : null;
      return new StepFullJavaSession.FullJavaSession(mcProfile, playerCertificates);
   }

   public JsonObject toUnoptimizedJson(StepFullJavaSession.FullJavaSession fullJavaSession) {
      JsonObject json = new JsonObject();
      if (this.prevStep != null) {
         json.add(this.prevStep.name, this.prevStep.toJson(fullJavaSession.mcProfile));
      }

      if (this.prevStep2 != null) {
         json.add(this.prevStep2.name, this.prevStep2.toJson(fullJavaSession.playerCertificates));
      }

      return json;
   }

   public static final class FullJavaSession extends BiMergeStep.StepResult<StepMCProfile.MCProfile, StepPlayerCertificates.PlayerCertificates> {
      private final StepMCProfile.MCProfile mcProfile;
      private final StepPlayerCertificates.PlayerCertificates playerCertificates;

      protected StepMCProfile.MCProfile prevResult() {
         return this.mcProfile;
      }

      protected StepPlayerCertificates.PlayerCertificates prevResult2() {
         return this.playerCertificates;
      }

      @Generated
      public FullJavaSession(StepMCProfile.MCProfile mcProfile, StepPlayerCertificates.PlayerCertificates playerCertificates) {
         this.mcProfile = mcProfile;
         this.playerCertificates = playerCertificates;
      }

      @Generated
      public StepMCProfile.MCProfile getMcProfile() {
         return this.mcProfile;
      }

      @Generated
      public StepPlayerCertificates.PlayerCertificates getPlayerCertificates() {
         return this.playerCertificates;
      }

      @Generated
      @Override
      public String toString() {
         return "StepFullJavaSession.FullJavaSession(mcProfile=" + this.getMcProfile() + ", playerCertificates=" + this.getPlayerCertificates() + ")";
      }

      @Generated
      @Override
      public boolean equals(Object o) {
         if (o == this) {
            return true;
         } else if (!(o instanceof StepFullJavaSession.FullJavaSession)) {
            return false;
         } else {
            StepFullJavaSession.FullJavaSession other = (StepFullJavaSession.FullJavaSession)o;
            if (!other.canEqual(this)) {
               return false;
            } else {
               Object this$mcProfile = this.getMcProfile();
               Object other$mcProfile = other.getMcProfile();
               if (this$mcProfile == null ? other$mcProfile == null : this$mcProfile.equals(other$mcProfile)) {
                  Object this$playerCertificates = this.getPlayerCertificates();
                  Object other$playerCertificates = other.getPlayerCertificates();
                  return this$playerCertificates == null ? other$playerCertificates == null : this$playerCertificates.equals(other$playerCertificates);
               } else {
                  return false;
               }
            }
         }
      }

      @Generated
      protected boolean canEqual(Object other) {
         return other instanceof StepFullJavaSession.FullJavaSession;
      }

      @Generated
      @Override
      public int hashCode() {
         int PRIME = 59;
         int result = 1;
         Object $mcProfile = this.getMcProfile();
         result = result * 59 + ($mcProfile == null ? 43 : $mcProfile.hashCode());
         Object $playerCertificates = this.getPlayerCertificates();
         return result * 59 + ($playerCertificates == null ? 43 : $playerCertificates.hashCode());
      }
   }
}
