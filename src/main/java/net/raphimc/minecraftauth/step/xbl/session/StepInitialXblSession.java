package net.raphimc.minecraftauth.step.xbl.session;

import com.google.gson.JsonObject;
import lombok.Generated;
import net.lenni0451.commons.httpclient.HttpClient;
import net.raphimc.minecraftauth.step.AbstractStep;
import net.raphimc.minecraftauth.step.BiMergeStep;
import net.raphimc.minecraftauth.step.msa.StepMsaToken;
import net.raphimc.minecraftauth.step.xbl.StepXblDeviceToken;
import net.raphimc.minecraftauth.util.logging.ILogger;

public class StepInitialXblSession extends BiMergeStep<StepMsaToken.MsaToken, StepXblDeviceToken.XblDeviceToken, StepInitialXblSession.InitialXblSession> {
   public StepInitialXblSession(AbstractStep<?, StepMsaToken.MsaToken> prevStep1, AbstractStep<?, StepXblDeviceToken.XblDeviceToken> prevStep2) {
      super("initialXblSession", prevStep1, prevStep2);
   }

   protected StepInitialXblSession.InitialXblSession execute(
      ILogger logger, HttpClient httpClient, StepMsaToken.MsaToken msaToken, StepXblDeviceToken.XblDeviceToken xblDeviceToken
   ) throws Exception {
      return new StepInitialXblSession.InitialXblSession(msaToken, xblDeviceToken);
   }

   public StepInitialXblSession.InitialXblSession fromJson(JsonObject json) {
      StepMsaToken.MsaToken msaToken = this.prevStep != null ? this.prevStep.fromJson(json.getAsJsonObject(this.prevStep.name)) : null;
      StepXblDeviceToken.XblDeviceToken xblDeviceToken = this.prevStep2 != null ? this.prevStep2.fromJson(json.getAsJsonObject(this.prevStep2.name)) : null;
      return new StepInitialXblSession.InitialXblSession(msaToken, xblDeviceToken);
   }

   public JsonObject toJson(StepInitialXblSession.InitialXblSession initialXblSession) {
      JsonObject json = new JsonObject();
      if (this.prevStep != null) {
         json.add(this.prevStep.name, this.prevStep.toJson(initialXblSession.msaToken));
      }

      if (this.prevStep2 != null) {
         json.add(this.prevStep2.name, this.prevStep2.toJson(initialXblSession.xblDeviceToken));
      }

      return json;
   }

   public static final class InitialXblSession extends BiMergeStep.StepResult<StepMsaToken.MsaToken, StepXblDeviceToken.XblDeviceToken> {
      private final StepMsaToken.MsaToken msaToken;
      private final StepXblDeviceToken.XblDeviceToken xblDeviceToken;

      protected StepMsaToken.MsaToken prevResult() {
         return this.msaToken;
      }

      protected StepXblDeviceToken.XblDeviceToken prevResult2() {
         return this.xblDeviceToken;
      }

      @Generated
      public InitialXblSession(StepMsaToken.MsaToken msaToken, StepXblDeviceToken.XblDeviceToken xblDeviceToken) {
         this.msaToken = msaToken;
         this.xblDeviceToken = xblDeviceToken;
      }

      @Generated
      public StepMsaToken.MsaToken getMsaToken() {
         return this.msaToken;
      }

      @Generated
      public StepXblDeviceToken.XblDeviceToken getXblDeviceToken() {
         return this.xblDeviceToken;
      }

      @Generated
      @Override
      public String toString() {
         return "StepInitialXblSession.InitialXblSession(msaToken=" + this.getMsaToken() + ", xblDeviceToken=" + this.getXblDeviceToken() + ")";
      }

      @Generated
      @Override
      public boolean equals(Object o) {
         if (o == this) {
            return true;
         } else if (!(o instanceof StepInitialXblSession.InitialXblSession)) {
            return false;
         } else {
            StepInitialXblSession.InitialXblSession other = (StepInitialXblSession.InitialXblSession)o;
            if (!other.canEqual(this)) {
               return false;
            } else {
               Object this$msaToken = this.getMsaToken();
               Object other$msaToken = other.getMsaToken();
               if (this$msaToken == null ? other$msaToken == null : this$msaToken.equals(other$msaToken)) {
                  Object this$xblDeviceToken = this.getXblDeviceToken();
                  Object other$xblDeviceToken = other.getXblDeviceToken();
                  return this$xblDeviceToken == null ? other$xblDeviceToken == null : this$xblDeviceToken.equals(other$xblDeviceToken);
               } else {
                  return false;
               }
            }
         }
      }

      @Generated
      protected boolean canEqual(Object other) {
         return other instanceof StepInitialXblSession.InitialXblSession;
      }

      @Generated
      @Override
      public int hashCode() {
         int PRIME = 59;
         int result = 1;
         Object $msaToken = this.getMsaToken();
         result = result * 59 + ($msaToken == null ? 43 : $msaToken.hashCode());
         Object $xblDeviceToken = this.getXblDeviceToken();
         return result * 59 + ($xblDeviceToken == null ? 43 : $xblDeviceToken.hashCode());
      }
   }
}
