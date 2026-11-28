package net.raphimc.minecraftauth.step.bedrock.session;

import com.google.gson.JsonObject;
import lombok.Generated;
import net.lenni0451.commons.httpclient.HttpClient;
import net.raphimc.minecraftauth.step.AbstractStep;
import net.raphimc.minecraftauth.step.SameInputTriMergeStep;
import net.raphimc.minecraftauth.step.TriMergeStep;
import net.raphimc.minecraftauth.step.bedrock.StepMCChain;
import net.raphimc.minecraftauth.step.bedrock.StepPlayFabToken;
import net.raphimc.minecraftauth.step.xbl.StepXblXstsToken;
import net.raphimc.minecraftauth.util.logging.ILogger;

public class StepFullBedrockSession
   extends SameInputTriMergeStep<StepMCChain.MCChain, StepPlayFabToken.PlayFabToken, StepXblXstsToken.XblXsts<?>, StepFullBedrockSession.FullBedrockSession> {
   public StepFullBedrockSession(
      AbstractStep<?, StepMCChain.MCChain> prevStep1,
      AbstractStep<?, StepPlayFabToken.PlayFabToken> prevStep2,
      AbstractStep<?, ? extends StepXblXstsToken.XblXsts<?>> prevStep3
   ) {
      super("fullBedrockSession", prevStep1, prevStep2, (AbstractStep<?, StepXblXstsToken.XblXsts<?>>)prevStep3);
   }

   protected StepFullBedrockSession.FullBedrockSession execute(
      ILogger logger, HttpClient httpClient, StepMCChain.MCChain mcChain, StepPlayFabToken.PlayFabToken playFabToken, StepXblXstsToken.XblXsts<?> realmsXsts
   ) throws Exception {
      return new StepFullBedrockSession.FullBedrockSession(mcChain, playFabToken, realmsXsts);
   }

   public StepFullBedrockSession.FullBedrockSession fromUnoptimizedJson(JsonObject json) {
      StepMCChain.MCChain mcChain = this.prevStep != null ? this.prevStep.fromJson(json.getAsJsonObject(this.prevStep.name)) : null;
      StepPlayFabToken.PlayFabToken playFabToken = this.prevStep2 != null ? this.prevStep2.fromJson(json.getAsJsonObject(this.prevStep2.name)) : null;
      StepXblXstsToken.XblXsts<?> realmsXsts = this.prevStep3 != null ? this.prevStep3.fromJson(json.getAsJsonObject(this.prevStep3.name)) : null;
      return new StepFullBedrockSession.FullBedrockSession(mcChain, playFabToken, realmsXsts);
   }

   public JsonObject toUnoptimizedJson(StepFullBedrockSession.FullBedrockSession fullBedrockSession) {
      JsonObject json = new JsonObject();
      if (this.prevStep != null) {
         json.add(this.prevStep.name, this.prevStep.toJson(fullBedrockSession.mcChain));
      }

      if (this.prevStep2 != null) {
         json.add(this.prevStep2.name, this.prevStep2.toJson(fullBedrockSession.playFabToken));
      }

      if (this.prevStep3 != null) {
         json.add(this.prevStep3.name, this.prevStep3.toJson(fullBedrockSession.realmsXsts));
      }

      return json;
   }

   public static final class FullBedrockSession
      extends TriMergeStep.StepResult<StepMCChain.MCChain, StepPlayFabToken.PlayFabToken, StepXblXstsToken.XblXsts<?>> {
      private final StepMCChain.MCChain mcChain;
      private final StepPlayFabToken.PlayFabToken playFabToken;
      private final StepXblXstsToken.XblXsts<?> realmsXsts;

      protected StepMCChain.MCChain prevResult() {
         return this.mcChain;
      }

      protected StepPlayFabToken.PlayFabToken prevResult2() {
         return this.playFabToken;
      }

      protected StepXblXstsToken.XblXsts<?> prevResult3() {
         return this.realmsXsts;
      }

      @Generated
      public FullBedrockSession(StepMCChain.MCChain mcChain, StepPlayFabToken.PlayFabToken playFabToken, StepXblXstsToken.XblXsts<?> realmsXsts) {
         this.mcChain = mcChain;
         this.playFabToken = playFabToken;
         this.realmsXsts = realmsXsts;
      }

      @Generated
      public StepMCChain.MCChain getMcChain() {
         return this.mcChain;
      }

      @Generated
      public StepPlayFabToken.PlayFabToken getPlayFabToken() {
         return this.playFabToken;
      }

      @Generated
      public StepXblXstsToken.XblXsts<?> getRealmsXsts() {
         return this.realmsXsts;
      }

      @Generated
      @Override
      public String toString() {
         return "StepFullBedrockSession.FullBedrockSession(mcChain="
            + this.getMcChain()
            + ", playFabToken="
            + this.getPlayFabToken()
            + ", realmsXsts="
            + this.getRealmsXsts()
            + ")";
      }

      @Generated
      @Override
      public boolean equals(Object o) {
         if (o == this) {
            return true;
         } else if (!(o instanceof StepFullBedrockSession.FullBedrockSession)) {
            return false;
         } else {
            StepFullBedrockSession.FullBedrockSession other = (StepFullBedrockSession.FullBedrockSession)o;
            if (!other.canEqual(this)) {
               return false;
            } else {
               Object this$mcChain = this.getMcChain();
               Object other$mcChain = other.getMcChain();
               if (this$mcChain == null ? other$mcChain == null : this$mcChain.equals(other$mcChain)) {
                  Object this$playFabToken = this.getPlayFabToken();
                  Object other$playFabToken = other.getPlayFabToken();
                  if (this$playFabToken == null ? other$playFabToken == null : this$playFabToken.equals(other$playFabToken)) {
                     Object this$realmsXsts = this.getRealmsXsts();
                     Object other$realmsXsts = other.getRealmsXsts();
                     return this$realmsXsts == null ? other$realmsXsts == null : this$realmsXsts.equals(other$realmsXsts);
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
         return other instanceof StepFullBedrockSession.FullBedrockSession;
      }

      @Generated
      @Override
      public int hashCode() {
         int PRIME = 59;
         int result = 1;
         Object $mcChain = this.getMcChain();
         result = result * 59 + ($mcChain == null ? 43 : $mcChain.hashCode());
         Object $playFabToken = this.getPlayFabToken();
         result = result * 59 + ($playFabToken == null ? 43 : $playFabToken.hashCode());
         Object $realmsXsts = this.getRealmsXsts();
         return result * 59 + ($realmsXsts == null ? 43 : $realmsXsts.hashCode());
      }
   }
}
