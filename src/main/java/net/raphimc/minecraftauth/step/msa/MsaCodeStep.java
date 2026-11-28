package net.raphimc.minecraftauth.step.msa;

import com.google.gson.JsonObject;
import lombok.Generated;
import net.lenni0451.commons.httpclient.HttpClient;
import net.raphimc.minecraftauth.step.AbstractStep;
import net.raphimc.minecraftauth.util.JsonUtil;
import net.raphimc.minecraftauth.util.logging.ILogger;
import org.jetbrains.annotations.ApiStatus.Internal;

public abstract class MsaCodeStep<I extends AbstractStep.InitialInput> extends AbstractStep<I, MsaCodeStep.MsaCode> {
   public MsaCodeStep(AbstractStep<?, I> prevStep) {
      super("msaCode", prevStep);
   }

   public MsaCodeStep(AbstractStep.ApplicationDetails applicationDetails) {
      super("msaCode", applicationDetails);
   }

   public final MsaCodeStep.MsaCode refresh(ILogger logger, HttpClient httpClient, MsaCodeStep.MsaCode result) {
      throw new UnsupportedOperationException("Cannot refresh MsaCodeStep");
   }

   public final MsaCodeStep.MsaCode fromJson(JsonObject json) {
      return new MsaCodeStep.MsaCode(JsonUtil.getStringOr(json, "code", null));
   }

   public final JsonObject toJson(MsaCodeStep.MsaCode msaCode) {
      JsonObject json = new JsonObject();
      json.addProperty("code", msaCode.code);
      return json;
   }

   public static final class MsaCode extends AbstractStep.FirstStepResult {
      private final String code;
      @Internal
      StepMsaToken.MsaToken msaToken;
      @Internal
      String customRedirectUri;

      @Override
      public boolean isExpired() {
         return true;
      }

      @Generated
      public String getCode() {
         return this.code;
      }

      @Generated
      @Override
      public String toString() {
         return "MsaCodeStep.MsaCode(code=" + this.getCode() + ")";
      }

      @Generated
      public MsaCode(String code) {
         this.code = code;
      }

      @Generated
      @Override
      public boolean equals(Object o) {
         if (o == this) {
            return true;
         } else if (!(o instanceof MsaCodeStep.MsaCode)) {
            return false;
         } else {
            MsaCodeStep.MsaCode other = (MsaCodeStep.MsaCode)o;
            if (!other.canEqual(this)) {
               return false;
            } else {
               Object this$code = this.getCode();
               Object other$code = other.getCode();
               return this$code == null ? other$code == null : this$code.equals(other$code);
            }
         }
      }

      @Generated
      protected boolean canEqual(Object other) {
         return other instanceof MsaCodeStep.MsaCode;
      }

      @Generated
      @Override
      public int hashCode() {
         int PRIME = 59;
         Object $code = this.getCode();
         return 1 * 59 + ($code == null ? 43 : $code.hashCode());
      }
   }
}
