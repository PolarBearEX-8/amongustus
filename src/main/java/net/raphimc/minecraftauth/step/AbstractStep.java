package net.raphimc.minecraftauth.step;

import com.google.gson.JsonObject;
import java.util.HashMap;
import java.util.Map;
import lombok.Generated;
import net.lenni0451.commons.httpclient.HttpClient;
import net.raphimc.minecraftauth.MinecraftAuth;
import net.raphimc.minecraftauth.util.JsonUtil;
import net.raphimc.minecraftauth.util.OAuthEnvironment;
import net.raphimc.minecraftauth.util.UuidUtil;
import net.raphimc.minecraftauth.util.logging.ILogger;

public abstract class AbstractStep<I extends AbstractStep.StepResult<?>, O extends AbstractStep.StepResult<?>> {
   public final String name;
   public final AbstractStep.ApplicationDetails applicationDetails;
   public final AbstractStep<?, I> prevStep;

   public AbstractStep(String name) {
      this.name = name;
      this.applicationDetails = null;
      this.prevStep = null;
   }

   public AbstractStep(String name, AbstractStep<?, I> prevStep) {
      this.name = name;
      this.applicationDetails = prevStep.applicationDetails;
      this.prevStep = prevStep;
   }

   public AbstractStep(String name, AbstractStep.ApplicationDetails applicationDetails) {
      this.name = name;
      this.applicationDetails = applicationDetails;
      this.prevStep = null;
   }

   protected abstract O execute(ILogger logger, HttpClient httpClient, I stepResult) throws Exception;

   public final O refresh(HttpClient httpClient, O result) throws Exception {
      return this.refresh(MinecraftAuth.LOGGER, httpClient, result);
   }

   public O refresh(ILogger logger, HttpClient httpClient, O result) throws Exception {
      return !result.isExpiredOrOutdated()
         ? result
         : this.execute(logger, httpClient, this.prevStep != null ? this.prevStep.refresh(logger, httpClient, (I)result.prevResult()) : null);
   }

   public final O getFromInput(HttpClient httpClient, AbstractStep.InitialInput input) throws Exception {
      return this.getFromInput(MinecraftAuth.LOGGER, httpClient, input);
   }

   public O getFromInput(ILogger logger, HttpClient httpClient, AbstractStep.InitialInput input) throws Exception {
      return this.execute(logger, httpClient, (I)(this.prevStep != null ? this.prevStep.getFromInput(logger, httpClient, input) : input));
   }

   public abstract O fromJson(JsonObject json);

   public abstract JsonObject toJson(O stepResult);

   public static final class ApplicationDetails {
      private final String clientId;
      private final String scope;
      private final String clientSecret;
      private final String redirectUri;
      private final OAuthEnvironment oAuthEnvironment;

      public static AbstractStep.ApplicationDetails fromJson(JsonObject json) {
         return new AbstractStep.ApplicationDetails(
            json.get("clientId").getAsString(),
            json.get("scope").getAsString(),
            JsonUtil.getStringOr(json, "clientSecret", null),
            JsonUtil.getStringOr(json, "redirectUri", null),
            OAuthEnvironment.valueOf(JsonUtil.getStringOr(json, "oAuthEnvironment", "LIVE"))
         );
      }

      public static JsonObject toJson(AbstractStep.ApplicationDetails applicationDetails) {
         JsonObject json = new JsonObject();
         json.addProperty("clientId", applicationDetails.clientId);
         json.addProperty("scope", applicationDetails.scope);
         json.addProperty("clientSecret", applicationDetails.clientSecret);
         json.addProperty("redirectUri", applicationDetails.redirectUri);
         json.addProperty("oAuthEnvironment", applicationDetails.oAuthEnvironment.name());
         return json;
      }

      public boolean isTitleClientId() {
         return !UuidUtil.isDashedUuid(this.clientId);
      }

      public Map<String, String> getOAuthParameters() {
         Map<String, String> parameters = new HashMap<>();
         parameters.put("client_id", this.clientId);
         parameters.put("scope", this.scope);
         parameters.put("redirect_uri", this.redirectUri);
         parameters.put("response_type", "code");
         parameters.put("response_mode", "query");
         return parameters;
      }

      @Generated
      public ApplicationDetails(String clientId, String scope, String clientSecret, String redirectUri, OAuthEnvironment oAuthEnvironment) {
         this.clientId = clientId;
         this.scope = scope;
         this.clientSecret = clientSecret;
         this.redirectUri = redirectUri;
         this.oAuthEnvironment = oAuthEnvironment;
      }

      @Generated
      public String getClientId() {
         return this.clientId;
      }

      @Generated
      public String getScope() {
         return this.scope;
      }

      @Generated
      public String getClientSecret() {
         return this.clientSecret;
      }

      @Generated
      public String getRedirectUri() {
         return this.redirectUri;
      }

      @Generated
      public OAuthEnvironment getOAuthEnvironment() {
         return this.oAuthEnvironment;
      }

      @Generated
      @Override
      public String toString() {
         return "AbstractStep.ApplicationDetails(clientId="
            + this.getClientId()
            + ", scope="
            + this.getScope()
            + ", clientSecret="
            + this.getClientSecret()
            + ", redirectUri="
            + this.getRedirectUri()
            + ", oAuthEnvironment="
            + this.getOAuthEnvironment()
            + ")";
      }

      @Generated
      public AbstractStep.ApplicationDetails withClientId(String clientId) {
         return this.clientId == clientId
            ? this
            : new AbstractStep.ApplicationDetails(clientId, this.scope, this.clientSecret, this.redirectUri, this.oAuthEnvironment);
      }

      @Generated
      public AbstractStep.ApplicationDetails withScope(String scope) {
         return this.scope == scope
            ? this
            : new AbstractStep.ApplicationDetails(this.clientId, scope, this.clientSecret, this.redirectUri, this.oAuthEnvironment);
      }

      @Generated
      public AbstractStep.ApplicationDetails withClientSecret(String clientSecret) {
         return this.clientSecret == clientSecret
            ? this
            : new AbstractStep.ApplicationDetails(this.clientId, this.scope, clientSecret, this.redirectUri, this.oAuthEnvironment);
      }

      @Generated
      public AbstractStep.ApplicationDetails withRedirectUri(String redirectUri) {
         return this.redirectUri == redirectUri
            ? this
            : new AbstractStep.ApplicationDetails(this.clientId, this.scope, this.clientSecret, redirectUri, this.oAuthEnvironment);
      }

      @Generated
      public AbstractStep.ApplicationDetails withOAuthEnvironment(OAuthEnvironment oAuthEnvironment) {
         return this.oAuthEnvironment == oAuthEnvironment
            ? this
            : new AbstractStep.ApplicationDetails(this.clientId, this.scope, this.clientSecret, this.redirectUri, oAuthEnvironment);
      }

      @Generated
      @Override
      public boolean equals(Object o) {
         if (o == this) {
            return true;
         } else if (!(o instanceof AbstractStep.ApplicationDetails)) {
            return false;
         } else {
            AbstractStep.ApplicationDetails other = (AbstractStep.ApplicationDetails)o;
            Object this$clientId = this.getClientId();
            Object other$clientId = other.getClientId();
            if (this$clientId == null ? other$clientId == null : this$clientId.equals(other$clientId)) {
               Object this$scope = this.getScope();
               Object other$scope = other.getScope();
               if (this$scope == null ? other$scope == null : this$scope.equals(other$scope)) {
                  Object this$clientSecret = this.getClientSecret();
                  Object other$clientSecret = other.getClientSecret();
                  if (this$clientSecret == null ? other$clientSecret == null : this$clientSecret.equals(other$clientSecret)) {
                     Object this$redirectUri = this.getRedirectUri();
                     Object other$redirectUri = other.getRedirectUri();
                     if (this$redirectUri == null ? other$redirectUri == null : this$redirectUri.equals(other$redirectUri)) {
                        Object this$oAuthEnvironment = this.getOAuthEnvironment();
                        Object other$oAuthEnvironment = other.getOAuthEnvironment();
                        return this$oAuthEnvironment == null ? other$oAuthEnvironment == null : this$oAuthEnvironment.equals(other$oAuthEnvironment);
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

      @Generated
      @Override
      public int hashCode() {
         int PRIME = 59;
         int result = 1;
         Object $clientId = this.getClientId();
         result = result * 59 + ($clientId == null ? 43 : $clientId.hashCode());
         Object $scope = this.getScope();
         result = result * 59 + ($scope == null ? 43 : $scope.hashCode());
         Object $clientSecret = this.getClientSecret();
         result = result * 59 + ($clientSecret == null ? 43 : $clientSecret.hashCode());
         Object $redirectUri = this.getRedirectUri();
         result = result * 59 + ($redirectUri == null ? 43 : $redirectUri.hashCode());
         Object $oAuthEnvironment = this.getOAuthEnvironment();
         return result * 59 + ($oAuthEnvironment == null ? 43 : $oAuthEnvironment.hashCode());
      }
   }

   public abstract static class FirstStepResult extends AbstractStep.StepResult<AbstractStep.StepResult<?>> {
      @Override
      protected final AbstractStep.StepResult<?> prevResult() {
         return null;
      }
   }

   public abstract static class InitialInput extends AbstractStep.StepResult<AbstractStep.StepResult<?>> {
      @Override
      protected final AbstractStep.StepResult<?> prevResult() {
         throw new UnsupportedOperationException();
      }

      @Override
      public boolean isExpired() {
         throw new UnsupportedOperationException();
      }
   }

   public abstract static class StepResult<P extends AbstractStep.StepResult<?>> {
      protected abstract P prevResult();

      public abstract boolean isExpired();

      public boolean isExpiredOrOutdated() {
         return this.isExpired();
      }
   }
}
