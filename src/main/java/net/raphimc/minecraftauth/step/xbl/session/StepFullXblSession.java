package net.raphimc.minecraftauth.step.xbl.session;

import com.google.gson.JsonObject;
import lombok.Generated;
import net.lenni0451.commons.httpclient.HttpClient;
import net.raphimc.minecraftauth.step.AbstractStep;
import net.raphimc.minecraftauth.step.BiMergeStep;
import net.raphimc.minecraftauth.step.SameInputBiMergeStep;
import net.raphimc.minecraftauth.step.xbl.StepXblTitleToken;
import net.raphimc.minecraftauth.step.xbl.StepXblUserToken;
import net.raphimc.minecraftauth.util.logging.ILogger;

public class StepFullXblSession extends SameInputBiMergeStep<StepXblUserToken.XblUserToken, StepXblTitleToken.XblTitleToken, StepFullXblSession.FullXblSession> {
   public StepFullXblSession(AbstractStep<?, StepXblUserToken.XblUserToken> prevStep1, AbstractStep<?, StepXblTitleToken.XblTitleToken> prevStep2) {
      super("fullXblSession", prevStep1, prevStep2);
   }

   protected StepFullXblSession.FullXblSession execute(
      ILogger logger, HttpClient httpClient, StepXblUserToken.XblUserToken xblUserToken, StepXblTitleToken.XblTitleToken xblTitleToken
   ) throws Exception {
      return new StepFullXblSession.FullXblSession(xblUserToken, xblTitleToken);
   }

   public StepFullXblSession.FullXblSession fromUnoptimizedJson(JsonObject json) {
      StepXblUserToken.XblUserToken xblUserToken = this.prevStep != null ? this.prevStep.fromJson(json.getAsJsonObject(this.prevStep.name)) : null;
      StepXblTitleToken.XblTitleToken xblTitleToken = this.prevStep2 != null ? this.prevStep2.fromJson(json.getAsJsonObject(this.prevStep2.name)) : null;
      return new StepFullXblSession.FullXblSession(xblUserToken, xblTitleToken);
   }

   public JsonObject toUnoptimizedJson(StepFullXblSession.FullXblSession fullXblSession) {
      JsonObject json = new JsonObject();
      if (this.prevStep != null) {
         json.add(this.prevStep.name, this.prevStep.toJson(fullXblSession.xblUserToken));
      }

      if (this.prevStep2 != null) {
         json.add(this.prevStep2.name, this.prevStep2.toJson(fullXblSession.xblTitleToken));
      }

      return json;
   }

   public static class FullXblSession extends BiMergeStep.StepResult<StepXblUserToken.XblUserToken, StepXblTitleToken.XblTitleToken> {
      private final StepXblUserToken.XblUserToken xblUserToken;
      private final StepXblTitleToken.XblTitleToken xblTitleToken;

      public FullXblSession(StepFullXblSession.FullXblSession fullXblSession) {
         this.xblUserToken = fullXblSession.xblUserToken;
         this.xblTitleToken = fullXblSession.xblTitleToken;
      }

      protected StepXblUserToken.XblUserToken prevResult() {
         return this.xblUserToken;
      }

      protected StepXblTitleToken.XblTitleToken prevResult2() {
         return this.xblTitleToken;
      }

      @Generated
      public StepXblUserToken.XblUserToken getXblUserToken() {
         return this.xblUserToken;
      }

      @Generated
      public StepXblTitleToken.XblTitleToken getXblTitleToken() {
         return this.xblTitleToken;
      }

      @Generated
      @Override
      public String toString() {
         return "StepFullXblSession.FullXblSession(xblUserToken=" + this.getXblUserToken() + ", xblTitleToken=" + this.getXblTitleToken() + ")";
      }

      @Generated
      public FullXblSession(StepXblUserToken.XblUserToken xblUserToken, StepXblTitleToken.XblTitleToken xblTitleToken) {
         this.xblUserToken = xblUserToken;
         this.xblTitleToken = xblTitleToken;
      }

      @Generated
      @Override
      public boolean equals(Object o) {
         if (o == this) {
            return true;
         } else if (!(o instanceof StepFullXblSession.FullXblSession)) {
            return false;
         } else {
            StepFullXblSession.FullXblSession other = (StepFullXblSession.FullXblSession)o;
            if (!other.canEqual(this)) {
               return false;
            } else {
               Object this$xblUserToken = this.getXblUserToken();
               Object other$xblUserToken = other.getXblUserToken();
               if (this$xblUserToken == null ? other$xblUserToken == null : this$xblUserToken.equals(other$xblUserToken)) {
                  Object this$xblTitleToken = this.getXblTitleToken();
                  Object other$xblTitleToken = other.getXblTitleToken();
                  return this$xblTitleToken == null ? other$xblTitleToken == null : this$xblTitleToken.equals(other$xblTitleToken);
               } else {
                  return false;
               }
            }
         }
      }

      @Generated
      protected boolean canEqual(Object other) {
         return other instanceof StepFullXblSession.FullXblSession;
      }

      @Generated
      @Override
      public int hashCode() {
         int PRIME = 59;
         int result = 1;
         Object $xblUserToken = this.getXblUserToken();
         result = result * 59 + ($xblUserToken == null ? 43 : $xblUserToken.hashCode());
         Object $xblTitleToken = this.getXblTitleToken();
         return result * 59 + ($xblTitleToken == null ? 43 : $xblTitleToken.hashCode());
      }
   }
}
