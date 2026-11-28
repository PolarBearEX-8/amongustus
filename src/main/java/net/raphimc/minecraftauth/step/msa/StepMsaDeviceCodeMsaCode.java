package net.raphimc.minecraftauth.step.msa;

import com.google.gson.JsonObject;
import java.time.Instant;
import java.time.ZoneId;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeoutException;
import net.lenni0451.commons.httpclient.HttpClient;
import net.lenni0451.commons.httpclient.content.impl.URLEncodedFormContent;
import net.lenni0451.commons.httpclient.requests.impl.PostRequest;
import net.raphimc.minecraftauth.responsehandler.MsaResponseHandler;
import net.raphimc.minecraftauth.responsehandler.exception.MsaRequestException;
import net.raphimc.minecraftauth.step.AbstractStep;
import net.raphimc.minecraftauth.util.JsonUtil;
import net.raphimc.minecraftauth.util.logging.ILogger;

public class StepMsaDeviceCodeMsaCode extends MsaCodeStep<StepMsaDeviceCode.MsaDeviceCode> {
   private final int timeout;

   public StepMsaDeviceCodeMsaCode(AbstractStep<?, StepMsaDeviceCode.MsaDeviceCode> prevStep, int timeout) {
      super(prevStep);
      this.timeout = timeout;
   }

   protected MsaCodeStep.MsaCode execute(ILogger logger, HttpClient httpClient, StepMsaDeviceCode.MsaDeviceCode msaDeviceCode) throws Exception {
      logger.info(this, "Waiting for MSA login via device code...");
      long start = System.currentTimeMillis();

      while (!msaDeviceCode.isExpired() && System.currentTimeMillis() - start <= (long)this.timeout) {
         Map<String, String> postData = new HashMap<>();
         postData.put("client_id", this.applicationDetails.getClientId());
         postData.put("grant_type", "device_code");
         postData.put("device_code", msaDeviceCode.getDeviceCode());
         PostRequest postRequest = new PostRequest(this.applicationDetails.getOAuthEnvironment().getTokenUrl());
         postRequest.setContent(new URLEncodedFormContent(postData));

         try {
            JsonObject obj = httpClient.execute(postRequest, new MsaResponseHandler());
            MsaCodeStep.MsaCode msaCode = new MsaCodeStep.MsaCode(null);
            msaCode.msaToken = new StepMsaToken.MsaToken(
               System.currentTimeMillis() + obj.get("expires_in").getAsLong() * 1000L,
               obj.get("access_token").getAsString(),
               JsonUtil.getStringOr(obj, "refresh_token", null),
               msaCode
            );
            logger.info(this, "Got MSA Token, expires: " + Instant.ofEpochMilli(msaCode.msaToken.getExpireTimeMs()).atZone(ZoneId.systemDefault()));
            return msaCode;
         } catch (MsaRequestException e) {
            if (e.getResponse().getStatusCode() != 400 || !e.getError().equals("authorization_pending")) {
               throw e;
            }

            Thread.sleep(msaDeviceCode.getIntervalMs());
         }
      }

      throw new TimeoutException("Failed to get MSA Code. Login timed out");
   }
}
