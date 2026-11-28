package net.raphimc.minecraftauth.step.msa;

import com.google.gson.JsonObject;
import com.google.gson.stream.JsonReader;
import java.io.IOException;
import java.io.StringReader;
import java.net.CookieManager;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import lombok.Generated;
import net.lenni0451.commons.httpclient.HttpClient;
import net.lenni0451.commons.httpclient.HttpResponse;
import net.lenni0451.commons.httpclient.constants.ContentTypes;
import net.lenni0451.commons.httpclient.content.impl.URLEncodedFormContent;
import net.lenni0451.commons.httpclient.exceptions.HttpRequestException;
import net.lenni0451.commons.httpclient.requests.HttpRequest;
import net.lenni0451.commons.httpclient.requests.impl.GetRequest;
import net.lenni0451.commons.httpclient.requests.impl.PostRequest;
import net.lenni0451.commons.httpclient.utils.URLWrapper;
import net.raphimc.minecraftauth.responsehandler.exception.InformativeHttpRequestException;
import net.raphimc.minecraftauth.responsehandler.exception.MsaRequestException;
import net.raphimc.minecraftauth.step.AbstractStep;
import net.raphimc.minecraftauth.util.JsonUtil;
import net.raphimc.minecraftauth.util.logging.ILogger;

public class StepCredentialsMsaCode extends MsaCodeStep<StepCredentialsMsaCode.MsaCredentials> {
   public StepCredentialsMsaCode(AbstractStep.ApplicationDetails applicationDetails) {
      super(applicationDetails);
   }

   protected MsaCodeStep.MsaCode execute(ILogger logger, HttpClient httpClient, StepCredentialsMsaCode.MsaCredentials msaCredentials) throws Exception {
      logger.info(this, "Trying to get MSA Code using email and password...");
      if (msaCredentials == null) {
         throw new IllegalStateException("Missing StepCredentialsMsaCode.MsaCredentials input");
      } else {
         Optional<String> locationHeader = this.sendLoginRequest(httpClient, this.prepareLoginPostRequest(httpClient, msaCredentials, new CookieManager())).getFirstHeader("Location");
         if (!locationHeader.isPresent()) {
            throw new IllegalStateException("Could not get redirect url");
         } else {
            Map<String, String> parameters = new URLWrapper(locationHeader.get()).wrapQuery().getQueries();
            if (!parameters.containsKey("code")) {
               throw new IllegalStateException("Could not extract MSA Code from redirect url");
            } else {
               MsaCodeStep.MsaCode msaCode = new MsaCodeStep.MsaCode(parameters.get("code"));
               logger.info(this, "Got MSA Code");
               return msaCode;
            }
         }
      }
   }

   private PostRequest prepareLoginPostRequest(HttpClient httpClient, StepCredentialsMsaCode.MsaCredentials msaCredentials, CookieManager cookieManager) throws IOException, URISyntaxException {
      URL authenticationUrl = new URLWrapper(this.applicationDetails.getOAuthEnvironment().getAuthorizeUrl())
         .wrapQuery()
         .addQueries(this.applicationDetails.getOAuthParameters())
         .apply()
         .toURL();
      GetRequest getRequest = new GetRequest(authenticationUrl);
      getRequest.setCookieManager(cookieManager);
      getRequest.setHeader("Accept", ContentTypes.TEXT_HTML.getMimeType());
      JsonObject config = httpClient.execute(getRequest, response -> {
         if (response.getStatusCode() >= 300) {
            Optional<String> locationHeader = response.getFirstHeader("Location");
            if (locationHeader.isPresent()) {
               Map<String, String> parameters = new URLWrapper(locationHeader.get()).wrapQuery().getQueries();
               if (parameters.containsKey("error") && parameters.containsKey("error_description")) {
                  throw new MsaRequestException(response, parameters.get("error"), parameters.get("error_description"));
               }
            }

            throw new HttpRequestException(response);
         } else {
            return this.extractConfig(response.getContentAsString());
         }
      });
      Map<String, String> postData = new HashMap<>();
      String urlPost;
      switch (this.applicationDetails.getOAuthEnvironment()) {
         case LIVE: {
            urlPost = config.get("urlPost").getAsString();
            String sFTTag = config.get("sFTTag").getAsString();
            String sFT = sFTTag.substring(sFTTag.indexOf("value=\"") + 7);
            sFT = sFT.substring(0, sFT.indexOf("\""));
            String sFTName = sFTTag.substring(sFTTag.indexOf("name=\"") + 6);
            sFTName = sFTName.substring(0, sFTName.indexOf("\""));
            postData.put("login", msaCredentials.email);
            postData.put("loginfmt", msaCredentials.email);
            postData.put("passwd", msaCredentials.password);
            postData.put(sFTName, sFT);
            break;
         }
         case MICROSOFT_ONLINE_COMMON:
         case MICROSOFT_ONLINE_CONSUMERS: {
            urlPost = config.get("urlPost").getAsString();
            urlPost = new URLWrapper(new URI(urlPost)).setProtocol(authenticationUrl.getProtocol()).setHost(authenticationUrl.getHost()).toURL().toString();
            String sFT = config.get("sFT").getAsString();
            String sFTName = config.get("sFTName").getAsString();
            String sCtx = config.get("sCtx").getAsString();
            postData.put("login", msaCredentials.email);
            postData.put("loginfmt", msaCredentials.email);
            postData.put("passwd", msaCredentials.password);
            postData.put("ctx", sCtx);
            postData.put(sFTName, sFT);
            break;
         }
         default:
            throw new IllegalStateException("Unsupported OAuthEnvironment: " + this.applicationDetails.getOAuthEnvironment());
      }

      PostRequest postRequest = new PostRequest(urlPost);
      postRequest.setCookieManager(cookieManager);
      postRequest.setHeader("Accept", ContentTypes.TEXT_HTML.getMimeType());
      postRequest.setContent(new URLEncodedFormContent(postData));
      return postRequest;
   }

   private HttpResponse sendLoginRequest(HttpClient httpClient, HttpRequest request) throws IOException {
      HttpResponse loginResponse = httpClient.execute(request);
      if (loginResponse.getStatusCode() != 302) {
         if (!loginResponse.getContentType().orElse(ContentTypes.TEXT_PLAIN).getMimeType().equals(ContentTypes.TEXT_HTML.getMimeType())) {
            throw new InformativeHttpRequestException(loginResponse, "Wrong content type");
         } else {
            String responseString = loginResponse.getContentAsString();
            if (responseString.contains("<body onload=\"javascript:DoSubmit();\">")) {
               String actionUrl = responseString.substring(responseString.indexOf("action=\"") + 8);
               actionUrl = actionUrl.substring(0, actionUrl.indexOf("\""));
               String returnUrl = new URLWrapper(actionUrl).wrapQuery().getQuery("ru").get();
               GetRequest getRequest = new GetRequest(returnUrl);
               getRequest.setCookieManager(request.getCookieManager());
               getRequest.setHeader("Accept", ContentTypes.TEXT_HTML.getMimeType());
               return this.sendLoginRequest(httpClient, getRequest);
            } else {
               JsonObject errorConfig = this.extractConfig(loginResponse.getContentAsString());
               switch (this.applicationDetails.getOAuthEnvironment()) {
                  case LIVE:
                     if (errorConfig.has("sErrorCode") && errorConfig.has("sErrTxt")) {
                        throw new MsaRequestException(loginResponse, errorConfig.get("sErrorCode").getAsString(), errorConfig.get("sErrTxt").getAsString());
                     }
                     break;
                  case MICROSOFT_ONLINE_COMMON:
                  case MICROSOFT_ONLINE_CONSUMERS:
                     if (errorConfig.has("iErrorCode") && errorConfig.has("strServiceExceptionMessage")) {
                        throw new MsaRequestException(
                           loginResponse, errorConfig.get("iErrorCode").getAsString(), errorConfig.get("strServiceExceptionMessage").getAsString()
                        );
                     }
                     break;
                  default:
                     throw new IllegalStateException("Unsupported OAuthEnvironment: " + this.applicationDetails.getOAuthEnvironment());
               }

               throw new IllegalStateException("Could not extract config from html. This most likely indicates that the account does not exist");
            }
         }
      } else {
         return loginResponse;
      }
   }

   private JsonObject extractConfig(String html) {
      switch (this.applicationDetails.getOAuthEnvironment()) {
         case LIVE:
            try {
               JsonReader jsonReader = new JsonReader(new StringReader(html.substring(html.indexOf("var ServerData = ") + 17)));
               jsonReader.setLenient(true);
               return (JsonObject)JsonUtil.GSON.fromJson(jsonReader, JsonObject.class);
            } catch (Throwable e) {
               throw new IllegalStateException("Could not extract config from html. This most likely indicates that the login was not successful", e);
            }
         case MICROSOFT_ONLINE_COMMON:
         case MICROSOFT_ONLINE_CONSUMERS:
            try {
               JsonReader jsonReader = new JsonReader(new StringReader(html.substring(html.indexOf("$Config=") + 8)));
               jsonReader.setLenient(true);
               return (JsonObject)JsonUtil.GSON.fromJson(jsonReader, JsonObject.class);
            } catch (Throwable e2) {
               throw new IllegalStateException("Could not extract config from html. This most likely indicates that the login was not successful", e2);
            }
         default:
            throw new IllegalStateException("Unsupported OAuthEnvironment: " + this.applicationDetails.getOAuthEnvironment());
      }
   }

   public static final class MsaCredentials extends AbstractStep.InitialInput {
      private final String email;
      private final String password;

      public static StepCredentialsMsaCode.MsaCredentials fromJson(JsonObject json) {
         return new StepCredentialsMsaCode.MsaCredentials(json.get("email").getAsString(), json.get("password").getAsString());
      }

      public static JsonObject toJson(StepCredentialsMsaCode.MsaCredentials msaCredentials) {
         JsonObject json = new JsonObject();
         json.addProperty("email", msaCredentials.email);
         json.addProperty("password", msaCredentials.password);
         return json;
      }

      @Generated
      public MsaCredentials(String email, String password) {
         this.email = email;
         this.password = password;
      }

      @Generated
      public String getEmail() {
         return this.email;
      }

      @Generated
      public String getPassword() {
         return this.password;
      }

      @Generated
      @Override
      public String toString() {
         return "StepCredentialsMsaCode.MsaCredentials(email=" + this.getEmail() + ", password=" + this.getPassword() + ")";
      }

      @Generated
      @Override
      public boolean equals(Object o) {
         if (o == this) {
            return true;
         } else if (!(o instanceof StepCredentialsMsaCode.MsaCredentials)) {
            return false;
         } else {
            StepCredentialsMsaCode.MsaCredentials other = (StepCredentialsMsaCode.MsaCredentials)o;
            if (!other.canEqual(this)) {
               return false;
            } else {
               Object this$email = this.getEmail();
               Object other$email = other.getEmail();
               if (this$email == null ? other$email == null : this$email.equals(other$email)) {
                  Object this$password = this.getPassword();
                  Object other$password = other.getPassword();
                  return this$password == null ? other$password == null : this$password.equals(other$password);
               } else {
                  return false;
               }
            }
         }
      }

      @Generated
      protected boolean canEqual(Object other) {
         return other instanceof StepCredentialsMsaCode.MsaCredentials;
      }

      @Generated
      @Override
      public int hashCode() {
         int PRIME = 59;
         int result = 1;
         Object $email = this.getEmail();
         result = result * 59 + ($email == null ? 43 : $email.hashCode());
         Object $password = this.getPassword();
         return result * 59 + ($password == null ? 43 : $password.hashCode());
      }
   }
}
