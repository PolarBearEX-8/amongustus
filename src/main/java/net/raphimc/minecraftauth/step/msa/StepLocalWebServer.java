package net.raphimc.minecraftauth.step.msa;

import java.net.ServerSocket;
import java.net.URL;
import java.util.function.Consumer;
import lombok.Generated;
import net.lenni0451.commons.httpclient.HttpClient;
import net.lenni0451.commons.httpclient.utils.URLWrapper;
import net.raphimc.minecraftauth.step.AbstractStep;
import net.raphimc.minecraftauth.util.logging.ILogger;

public class StepLocalWebServer extends InitialPreparationStep<StepLocalWebServer.LocalWebServerCallback, StepLocalWebServer.LocalWebServer> {
   public StepLocalWebServer(AbstractStep.ApplicationDetails applicationDetails) {
      super("localWebServer", applicationDetails);
      if (applicationDetails.getRedirectUri().endsWith("/")) {
         throw new IllegalArgumentException("Redirect URI must not end with a slash");
      }
   }

   protected StepLocalWebServer.LocalWebServer execute(ILogger logger, HttpClient httpClient, StepLocalWebServer.LocalWebServerCallback localWebServerCallback) throws Exception {
      logger.info(this, "Creating URL for MSA login via local webserver...");
      if (localWebServerCallback == null) {
         throw new IllegalStateException("Missing StepLocalWebServer.LocalWebServerCallback input");
      } else {
         StepLocalWebServer.LocalWebServer localWebServer2;
         try (ServerSocket localServer = new ServerSocket(0)) {
            int localPort = localServer.getLocalPort();
            String customRedirectUri = this.applicationDetails.getRedirectUri() + ":" + localPort;
            URL authenticationUrl = new URLWrapper(this.applicationDetails.getOAuthEnvironment().getAuthorizeUrl())
               .wrapQuery()
               .addQueries(this.applicationDetails.getOAuthParameters())
               .setQuery("redirect_uri", customRedirectUri)
               .setQuery("prompt", "select_account")
               .apply()
               .toURL();
            StepLocalWebServer.LocalWebServer localWebServer = new StepLocalWebServer.LocalWebServer(authenticationUrl.toString(), localPort, customRedirectUri);
            logger.info(this, "Created local webserver MSA authentication URL: " + localWebServer.getAuthenticationUrl());
            localWebServerCallback.callback.accept(localWebServer);
            localWebServer2 = localWebServer;
         }

         return localWebServer2;
      }
   }

   public static final class LocalWebServer extends AbstractStep.InitialInput {
      private final String authenticationUrl;
      private final int port;
      private final String customRedirectUri;

      @Generated
      public LocalWebServer(String authenticationUrl, int port, String customRedirectUri) {
         this.authenticationUrl = authenticationUrl;
         this.port = port;
         this.customRedirectUri = customRedirectUri;
      }

      @Generated
      public String getAuthenticationUrl() {
         return this.authenticationUrl;
      }

      @Generated
      public int getPort() {
         return this.port;
      }

      @Generated
      public String getCustomRedirectUri() {
         return this.customRedirectUri;
      }

      @Generated
      @Override
      public String toString() {
         return "StepLocalWebServer.LocalWebServer(authenticationUrl="
            + this.getAuthenticationUrl()
            + ", port="
            + this.getPort()
            + ", customRedirectUri="
            + this.getCustomRedirectUri()
            + ")";
      }

      @Generated
      @Override
      public boolean equals(Object o) {
         if (o == this) {
            return true;
         } else if (!(o instanceof StepLocalWebServer.LocalWebServer)) {
            return false;
         } else {
            StepLocalWebServer.LocalWebServer other = (StepLocalWebServer.LocalWebServer)o;
            if (!other.canEqual(this)) {
               return false;
            } else if (this.getPort() != other.getPort()) {
               return false;
            } else {
               Object this$authenticationUrl = this.getAuthenticationUrl();
               Object other$authenticationUrl = other.getAuthenticationUrl();
               if (this$authenticationUrl == null ? other$authenticationUrl == null : this$authenticationUrl.equals(other$authenticationUrl)) {
                  Object this$customRedirectUri = this.getCustomRedirectUri();
                  Object other$customRedirectUri = other.getCustomRedirectUri();
                  return this$customRedirectUri == null ? other$customRedirectUri == null : this$customRedirectUri.equals(other$customRedirectUri);
               } else {
                  return false;
               }
            }
         }
      }

      @Generated
      protected boolean canEqual(Object other) {
         return other instanceof StepLocalWebServer.LocalWebServer;
      }

      @Generated
      @Override
      public int hashCode() {
         int PRIME = 59;
         int result = 1;
         result = result * 59 + this.getPort();
         Object $authenticationUrl = this.getAuthenticationUrl();
         result = result * 59 + ($authenticationUrl == null ? 43 : $authenticationUrl.hashCode());
         Object $customRedirectUri = this.getCustomRedirectUri();
         return result * 59 + ($customRedirectUri == null ? 43 : $customRedirectUri.hashCode());
      }
   }

   public static final class LocalWebServerCallback extends AbstractStep.InitialInput {
      private final Consumer<StepLocalWebServer.LocalWebServer> callback;

      @Generated
      public LocalWebServerCallback(Consumer<StepLocalWebServer.LocalWebServer> callback) {
         this.callback = callback;
      }

      @Generated
      public Consumer<StepLocalWebServer.LocalWebServer> getCallback() {
         return this.callback;
      }

      @Generated
      @Override
      public String toString() {
         return "StepLocalWebServer.LocalWebServerCallback(callback=" + this.getCallback() + ")";
      }

      @Generated
      @Override
      public boolean equals(Object o) {
         if (o == this) {
            return true;
         } else if (!(o instanceof StepLocalWebServer.LocalWebServerCallback)) {
            return false;
         } else {
            StepLocalWebServer.LocalWebServerCallback other = (StepLocalWebServer.LocalWebServerCallback)o;
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
         return other instanceof StepLocalWebServer.LocalWebServerCallback;
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
