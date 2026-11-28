package net.raphimc.minecraftauth.step.msa;

import com.google.gson.JsonObject;
import java.time.Instant;
import java.time.ZoneId;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;
import lombok.Generated;
import net.lenni0451.commons.httpclient.HttpClient;
import net.lenni0451.commons.httpclient.content.impl.URLEncodedFormContent;
import net.lenni0451.commons.httpclient.requests.impl.PostRequest;
import net.raphimc.minecraftauth.responsehandler.MsaResponseHandler;
import net.raphimc.minecraftauth.step.AbstractStep;
import net.raphimc.minecraftauth.util.OAuthEnvironment;
import net.raphimc.minecraftauth.util.logging.ILogger;

public class StepMsaDeviceCode extends InitialPreparationStep<StepMsaDeviceCode.MsaDeviceCodeCallback, StepMsaDeviceCode.MsaDeviceCode> {
   public StepMsaDeviceCode(AbstractStep.ApplicationDetails applicationDetails) {
      super("msaDeviceCode", applicationDetails);
   }

   protected StepMsaDeviceCode.MsaDeviceCode execute(ILogger logger, HttpClient httpClient, StepMsaDeviceCode.MsaDeviceCodeCallback msaDeviceCodeCallback) throws Exception {
      logger.info(this, "Getting device code for MSA login...");
      if (msaDeviceCodeCallback == null) {
         throw new IllegalStateException("Missing StepMsaDeviceCode.MsaDeviceCodeCallback input");
      } else {
         Map<String, String> postData = new HashMap<>();
         postData.put("client_id", this.applicationDetails.getClientId());
         postData.put("scope", this.applicationDetails.getScope());
         if (this.applicationDetails.getOAuthEnvironment() == OAuthEnvironment.LIVE) {
            postData.put("response_type", "device_code");
         }

         PostRequest postRequest = new PostRequest(this.applicationDetails.getOAuthEnvironment().getDeviceCodeUrl());
         postRequest.setContent(new URLEncodedFormContent(postData));
         JsonObject obj = httpClient.execute(postRequest, new MsaResponseHandler());
         StepMsaDeviceCode.MsaDeviceCode msaDeviceCode = new StepMsaDeviceCode.MsaDeviceCode(
            System.currentTimeMillis() + obj.get("expires_in").getAsLong() * 1000L,
            obj.get("interval").getAsLong() * 1000L,
            obj.get("device_code").getAsString(),
            obj.get("user_code").getAsString(),
            obj.get("verification_uri").getAsString()
         );
         logger.info(this, "Got MSA device code, expires: " + Instant.ofEpochMilli(msaDeviceCode.getExpireTimeMs()).atZone(ZoneId.systemDefault()));
         msaDeviceCodeCallback.callback.accept(msaDeviceCode);
         return msaDeviceCode;
      }
   }

   public static final class MsaDeviceCode extends AbstractStep.InitialInput {
      private final long expireTimeMs;
      private final long intervalMs;
      private final String deviceCode;
      private final String userCode;
      private final String verificationUri;

      @Override
      public boolean isExpired() {
         return this.expireTimeMs <= System.currentTimeMillis();
      }

      public String getDirectVerificationUri() {
         return this.verificationUri + "?otc=" + this.userCode;
      }

      @Generated
      public MsaDeviceCode(long expireTimeMs, long intervalMs, String deviceCode, String userCode, String verificationUri) {
         this.expireTimeMs = expireTimeMs;
         this.intervalMs = intervalMs;
         this.deviceCode = deviceCode;
         this.userCode = userCode;
         this.verificationUri = verificationUri;
      }

      @Generated
      public long getExpireTimeMs() {
         return this.expireTimeMs;
      }

      @Generated
      public long getIntervalMs() {
         return this.intervalMs;
      }

      @Generated
      public String getDeviceCode() {
         return this.deviceCode;
      }

      @Generated
      public String getUserCode() {
         return this.userCode;
      }

      @Generated
      public String getVerificationUri() {
         return this.verificationUri;
      }

      @Generated
      @Override
      public String toString() {
         return "StepMsaDeviceCode.MsaDeviceCode(expireTimeMs="
            + this.getExpireTimeMs()
            + ", intervalMs="
            + this.getIntervalMs()
            + ", deviceCode="
            + this.getDeviceCode()
            + ", userCode="
            + this.getUserCode()
            + ", verificationUri="
            + this.getVerificationUri()
            + ")";
      }

      @Generated
      @Override
      public boolean equals(Object o) {
         if (o == this) {
            return true;
         } else if (!(o instanceof StepMsaDeviceCode.MsaDeviceCode)) {
            return false;
         } else {
            StepMsaDeviceCode.MsaDeviceCode other = (StepMsaDeviceCode.MsaDeviceCode)o;
            if (!other.canEqual(this)) {
               return false;
            } else if (this.getExpireTimeMs() != other.getExpireTimeMs()) {
               return false;
            } else if (this.getIntervalMs() != other.getIntervalMs()) {
               return false;
            } else {
               Object this$deviceCode = this.getDeviceCode();
               Object other$deviceCode = other.getDeviceCode();
               if (this$deviceCode == null ? other$deviceCode == null : this$deviceCode.equals(other$deviceCode)) {
                  Object this$userCode = this.getUserCode();
                  Object other$userCode = other.getUserCode();
                  if (this$userCode == null ? other$userCode == null : this$userCode.equals(other$userCode)) {
                     Object this$verificationUri = this.getVerificationUri();
                     Object other$verificationUri = other.getVerificationUri();
                     return this$verificationUri == null ? other$verificationUri == null : this$verificationUri.equals(other$verificationUri);
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
         return other instanceof StepMsaDeviceCode.MsaDeviceCode;
      }

      @Generated
      @Override
      public int hashCode() {
         int PRIME = 59;
         int result = 1;
         long $expireTimeMs = this.getExpireTimeMs();
         result = result * 59 + (int)($expireTimeMs >>> 32 ^ $expireTimeMs);
         long $intervalMs = this.getIntervalMs();
         result = result * 59 + (int)($intervalMs >>> 32 ^ $intervalMs);
         Object $deviceCode = this.getDeviceCode();
         result = result * 59 + ($deviceCode == null ? 43 : $deviceCode.hashCode());
         Object $userCode = this.getUserCode();
         result = result * 59 + ($userCode == null ? 43 : $userCode.hashCode());
         Object $verificationUri = this.getVerificationUri();
         return result * 59 + ($verificationUri == null ? 43 : $verificationUri.hashCode());
      }
   }

   public static final class MsaDeviceCodeCallback extends AbstractStep.InitialInput {
      private final Consumer<StepMsaDeviceCode.MsaDeviceCode> callback;

      @Generated
      public MsaDeviceCodeCallback(Consumer<StepMsaDeviceCode.MsaDeviceCode> callback) {
         this.callback = callback;
      }

      @Generated
      public Consumer<StepMsaDeviceCode.MsaDeviceCode> getCallback() {
         return this.callback;
      }

      @Generated
      @Override
      public String toString() {
         return "StepMsaDeviceCode.MsaDeviceCodeCallback(callback=" + this.getCallback() + ")";
      }

      @Generated
      @Override
      public boolean equals(Object o) {
         if (o == this) {
            return true;
         } else if (!(o instanceof StepMsaDeviceCode.MsaDeviceCodeCallback)) {
            return false;
         } else {
            StepMsaDeviceCode.MsaDeviceCodeCallback other = (StepMsaDeviceCode.MsaDeviceCodeCallback)o;
            if (!other.canEqual(this)) {
               return false;
            } else {
               Object this$callback = this.getCallback();
               Object other$callback = other.getCallback();
               return this$callback == null ? other$callback == null : this$callback.equals(other$callback);
            }
         }
      }

      @Generated
      protected boolean canEqual(Object other) {
         return other instanceof StepMsaDeviceCode.MsaDeviceCodeCallback;
      }

      @Generated
      @Override
      public int hashCode() {
         int PRIME = 59;
         Object $callback = this.getCallback();
         return 1 * 59 + ($callback == null ? 43 : $callback.hashCode());
      }
   }
}
