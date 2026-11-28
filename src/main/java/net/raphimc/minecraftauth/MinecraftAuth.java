package net.raphimc.minecraftauth;

import java.lang.reflect.Constructor;
import java.util.function.Function;
import net.lenni0451.commons.httpclient.HttpClient;
import net.lenni0451.commons.httpclient.RetryHandler;
import net.lenni0451.commons.httpclient.constants.ContentTypes;
import net.raphimc.minecraftauth.step.AbstractStep;
import net.raphimc.minecraftauth.step.BiMergeStep;
import net.raphimc.minecraftauth.step.bedrock.StepMCChain;
import net.raphimc.minecraftauth.step.bedrock.StepPlayFabToken;
import net.raphimc.minecraftauth.step.bedrock.session.StepFullBedrockSession;
import net.raphimc.minecraftauth.step.edu.StepEduJWT;
import net.raphimc.minecraftauth.step.java.StepMCProfile;
import net.raphimc.minecraftauth.step.java.StepMCToken;
import net.raphimc.minecraftauth.step.java.StepPlayerCertificates;
import net.raphimc.minecraftauth.step.java.session.StepFullJavaSession;
import net.raphimc.minecraftauth.step.msa.MsaCodeStep;
import net.raphimc.minecraftauth.step.msa.StepCredentialsMsaCode;
import net.raphimc.minecraftauth.step.msa.StepJfxWebViewMsaCode;
import net.raphimc.minecraftauth.step.msa.StepLocalWebServer;
import net.raphimc.minecraftauth.step.msa.StepLocalWebServerMsaCode;
import net.raphimc.minecraftauth.step.msa.StepMsaDeviceCode;
import net.raphimc.minecraftauth.step.msa.StepMsaDeviceCodeMsaCode;
import net.raphimc.minecraftauth.step.msa.StepMsaToken;
import net.raphimc.minecraftauth.step.xbl.StepXblDeviceToken;
import net.raphimc.minecraftauth.step.xbl.StepXblSisuAuthentication;
import net.raphimc.minecraftauth.step.xbl.StepXblTitleToken;
import net.raphimc.minecraftauth.step.xbl.StepXblUserToken;
import net.raphimc.minecraftauth.step.xbl.StepXblXstsToken;
import net.raphimc.minecraftauth.step.xbl.adapter.StepXblXstsToFullXblSession;
import net.raphimc.minecraftauth.step.xbl.session.StepFullXblSession;
import net.raphimc.minecraftauth.step.xbl.session.StepInitialXblSession;
import net.raphimc.minecraftauth.util.OAuthEnvironment;
import net.raphimc.minecraftauth.util.logging.ILogger;
import net.raphimc.minecraftauth.util.logging.LazyLogger;
import net.raphimc.minecraftauth.util.logging.Slf4jConsoleLogger;
import org.jetbrains.annotations.ApiStatus.Experimental;

public class MinecraftAuth {
   public static final String VERSION = "4.1.2";
   public static final String IMPL_VERSION = "4.1.2+9349f94";
   public static ILogger LOGGER = new LazyLogger(Slf4jConsoleLogger::new);
   public static String USER_AGENT = "MinecraftAuth/4.1.2";
   public static final AbstractStep<?, StepFullJavaSession.FullJavaSession> JAVA_DEVICE_CODE_LOGIN = builder()
      .withClientId("00000000402b5328")
      .withScope("service::user.auth.xboxlive.com::MBI_SSL")
      .deviceCode()
      .withDeviceToken("Win32")
      .sisuTitleAuthentication("rp://api.minecraftservices.com/")
      .buildMinecraftJavaProfileStep(true);
   @Experimental
   public static final AbstractStep<?, StepFullJavaSession.FullJavaSession> ALT_JAVA_DEVICE_CODE_LOGIN = builder()
      .withClientId("00000000402b5328")
      .withScope("service::user.auth.xboxlive.com::MBI_SSL")
      .deviceCode()
      .withoutDeviceToken()
      .regularAuthentication("rp://api.minecraftservices.com/")
      .buildMinecraftJavaProfileStep(true);
   public static final AbstractStep<?, StepFullJavaSession.FullJavaSession> JAVA_CREDENTIALS_LOGIN = builder()
      .withClientId("00000000402b5328")
      .withScope("service::user.auth.xboxlive.com::MBI_SSL")
      .credentials()
      .withDeviceToken("Win32")
      .sisuTitleAuthentication("rp://api.minecraftservices.com/")
      .buildMinecraftJavaProfileStep(true);
   @Experimental
   public static final AbstractStep<?, StepFullJavaSession.FullJavaSession> ALT_JAVA_CREDENTIALS_LOGIN = builder()
      .withClientId("00000000402b5328")
      .withScope("service::user.auth.xboxlive.com::MBI_SSL")
      .credentials()
      .withoutDeviceToken()
      .regularAuthentication("rp://api.minecraftservices.com/")
      .buildMinecraftJavaProfileStep(true);
   public static final AbstractStep<?, StepFullBedrockSession.FullBedrockSession> BEDROCK_DEVICE_CODE_LOGIN = builder()
      .withClientId("0000000048183522")
      .withScope("service::user.auth.xboxlive.com::MBI_SSL")
      .deviceCode()
      .withDeviceToken("Android")
      .sisuTitleAuthentication("https://multiplayer.minecraft.net/")
      .buildMinecraftBedrockChainStep(true, false);
   public static final AbstractStep<?, StepFullBedrockSession.FullBedrockSession> BEDROCK_CREDENTIALS_LOGIN = builder()
      .withClientId("0000000048183522")
      .withScope("service::user.auth.xboxlive.com::MBI_SSL")
      .credentials()
      .withDeviceToken("Android")
      .sisuTitleAuthentication("https://multiplayer.minecraft.net/")
      .buildMinecraftBedrockChainStep(true, false);
   @Experimental
   public static final AbstractStep<?, StepEduJWT.EduJWT> EDU_DEVICE_CODE_LOGIN = new StepEduJWT(
      builder()
         .withClientId("b36b1432-1a1c-4c82-9b76-24de1cab42f2")
         .withScope("https://meeservices.minecraft.net/.default offline_access")
         .withOAuthEnvironment(OAuthEnvironment.MICROSOFT_ONLINE_COMMON)
         .deviceCode()
         .msaTokenStep,
      "1.20.13",
      594
   );
   @Experimental
   @SuppressWarnings("unchecked")
   public static final AbstractStep<?, StepXblSisuAuthentication.XblSisuTokens> BEDROCK_XBL_DEVICE_CODE_LOGIN = (AbstractStep<?, StepXblSisuAuthentication.XblSisuTokens>)(AbstractStep)builder()
      .withClientId("0000000048183522")
      .withScope("service::user.auth.xboxlive.com::MBI_SSL")
      .deviceCode()
      .withDeviceToken("Android")
      .sisuTitleAuthentication("http://xboxlive.com")
      .xblXstsTokenStep;

   public static MinecraftAuth.MsaTokenBuilder builder() {
      return new MinecraftAuth.MsaTokenBuilder();
   }

   public static HttpClient createHttpClient() {
      int timeout = 5000;
      return new HttpClient()
         .setConnectTimeout(5000)
         .setReadTimeout(10000)
         .setCookieManager(null)
         .setFollowRedirects(false)
         .setRetryHandler(new RetryHandler(0, 50))
         .setHeader("Accept", ContentTypes.APPLICATION_JSON.toString())
         .setHeader("Accept-Language", "en-US,en")
         .setHeader("User-Agent", USER_AGENT);
   }

   public static class InitialXblSessionBuilder {
      private final AbstractStep<MsaCodeStep.MsaCode, StepMsaToken.MsaToken> msaTokenStep;
      private BiMergeStep<StepMsaToken.MsaToken, StepXblDeviceToken.XblDeviceToken, StepInitialXblSession.InitialXblSession> initialXblSessionStep;

      private InitialXblSessionBuilder(MinecraftAuth.MsaTokenBuilder parent) {
         this.msaTokenStep = parent.build();
      }

      public MinecraftAuth.XblXstsTokenBuilder withDeviceToken(String deviceType) {
         this.initialXblSessionStep = new StepInitialXblSession(this.msaTokenStep, new StepXblDeviceToken(deviceType));
         return new MinecraftAuth.XblXstsTokenBuilder(this);
      }

      public MinecraftAuth.XblXstsTokenBuilder withoutDeviceToken() {
         this.initialXblSessionStep = new StepInitialXblSession(this.msaTokenStep, null);
         return new MinecraftAuth.XblXstsTokenBuilder(this);
      }

      public BiMergeStep<StepMsaToken.MsaToken, StepXblDeviceToken.XblDeviceToken, StepInitialXblSession.InitialXblSession> build() {
         return this.initialXblSessionStep;
      }
   }

   public static class MinecraftBuilder {
      private final AbstractStep<?, ? extends StepXblXstsToken.XblXsts<?>> xblXstsTokenStep;

      private MinecraftBuilder(MinecraftAuth.XblXstsTokenBuilder parent) {
         this.xblXstsTokenStep = parent.build();
      }

      public StepFullJavaSession buildMinecraftJavaProfileStep(boolean playerCertificates) {
         StepMCToken mcTokenStep = new StepMCToken(this.xblXstsTokenStep);
         StepPlayerCertificates playerCertificatesStep = playerCertificates ? new StepPlayerCertificates(mcTokenStep) : null;
         return new StepFullJavaSession(new StepMCProfile(mcTokenStep), playerCertificatesStep);
      }

      public StepFullBedrockSession buildMinecraftBedrockChainStep(boolean playFabToken, boolean realmsXsts) {
         StepPlayFabToken playFabTokenStep = new StepPlayFabToken(
            new StepXblXstsToken(new StepXblXstsToFullXblSession(this.xblXstsTokenStep), "https://b980a380.minecraft.playfabapi.com/")
         );
         StepXblXstsToken realmsXstsStep = new StepXblXstsToken(
            "realmsXsts", new StepXblXstsToFullXblSession(this.xblXstsTokenStep), "https://pocket.realms.minecraft.net/"
         );
         return new StepFullBedrockSession(new StepMCChain(this.xblXstsTokenStep), playFabToken ? playFabTokenStep : null, realmsXsts ? realmsXstsStep : null);
      }
   }

   public static class MsaTokenBuilder {
      private AbstractStep.ApplicationDetails applicationDetails = new AbstractStep.ApplicationDetails(
         "00000000402b5328", "XboxLive.signin XboxLive.offline_access", null, null, OAuthEnvironment.LIVE
      );
      private int timeout = 120;
      private AbstractStep<?, MsaCodeStep.MsaCode> msaCodeStep;

      public MinecraftAuth.MsaTokenBuilder withClientId(String clientId) {
         this.applicationDetails = this.applicationDetails.withClientId(clientId);
         return this;
      }

      public MinecraftAuth.MsaTokenBuilder withScope(String scope) {
         this.applicationDetails = this.applicationDetails.withScope(scope);
         return this;
      }

      public MinecraftAuth.MsaTokenBuilder withClientSecret(String clientSecret) {
         this.applicationDetails = this.applicationDetails.withClientSecret(clientSecret);
         return this;
      }

      public MinecraftAuth.MsaTokenBuilder withRedirectUri(String redirectUri) {
         this.applicationDetails = this.applicationDetails.withRedirectUri(redirectUri);
         return this;
      }

      public MinecraftAuth.MsaTokenBuilder withOAuthEnvironment(OAuthEnvironment oAuthEnvironment) {
         this.applicationDetails = this.applicationDetails.withOAuthEnvironment(oAuthEnvironment);
         return this;
      }

      public MinecraftAuth.MsaTokenBuilder withApplicationDetails(AbstractStep.ApplicationDetails applicationDetails) {
         this.applicationDetails = applicationDetails;
         return this;
      }

      public MinecraftAuth.MsaTokenBuilder withTimeout(int timeout) {
         this.timeout = timeout;
         return this;
      }

      public MinecraftAuth.InitialXblSessionBuilder deviceCode() {
         this.msaCodeStep = new StepMsaDeviceCodeMsaCode(new StepMsaDeviceCode(this.applicationDetails), this.timeout * 1000);
         return new MinecraftAuth.InitialXblSessionBuilder(this);
      }

      public MinecraftAuth.InitialXblSessionBuilder credentials() {
         if (this.applicationDetails.getRedirectUri() == null) {
            this.applicationDetails = this.applicationDetails.withRedirectUri(this.applicationDetails.getOAuthEnvironment().getNativeClientUrl());
         }

         this.msaCodeStep = new StepCredentialsMsaCode(this.applicationDetails);
         return new MinecraftAuth.InitialXblSessionBuilder(this);
      }

      public MinecraftAuth.InitialXblSessionBuilder javaFxWebView() {
         try {
            if (this.applicationDetails.getRedirectUri() == null) {
               this.applicationDetails = this.applicationDetails.withRedirectUri(this.applicationDetails.getOAuthEnvironment().getNativeClientUrl());
            }

            Constructor<?> constructor = StepJfxWebViewMsaCode.class.getConstructor(AbstractStep.ApplicationDetails.class, int.class);
            this.msaCodeStep = (AbstractStep<?, MsaCodeStep.MsaCode>)constructor.newInstance(this.applicationDetails, this.timeout * 1000);
            return new MinecraftAuth.InitialXblSessionBuilder(this);
         } catch (Throwable e) {
         throw MinecraftAuth.<RuntimeException>sneakyThrow(e);
      }
      }

      public MinecraftAuth.InitialXblSessionBuilder localWebServer() {
         if (this.applicationDetails.getRedirectUri() == null) {
            this.applicationDetails = this.applicationDetails.withRedirectUri("http://localhost");
         }

         this.msaCodeStep = new StepLocalWebServerMsaCode(new StepLocalWebServer(this.applicationDetails), this.timeout * 1000);
         return new MinecraftAuth.InitialXblSessionBuilder(this);
      }

      public MinecraftAuth.InitialXblSessionBuilder customMsaCodeStep(
         Function<AbstractStep.ApplicationDetails, AbstractStep<?, MsaCodeStep.MsaCode>> msaCodeStepProvider
      ) {
         this.msaCodeStep = msaCodeStepProvider.apply(this.applicationDetails);
         return new MinecraftAuth.InitialXblSessionBuilder(this);
      }

      @Deprecated
      public MinecraftAuth.InitialXblSessionBuilder customMsaCodeStep(AbstractStep<?, MsaCodeStep.MsaCode> msaCodeStep) {
         this.msaCodeStep = msaCodeStep;
         return new MinecraftAuth.InitialXblSessionBuilder(this);
      }

      public AbstractStep<MsaCodeStep.MsaCode, StepMsaToken.MsaToken> build() {
         return new StepMsaToken(this.msaCodeStep);
      }
   }

   public static class XblXstsTokenBuilder {
      private final BiMergeStep<StepMsaToken.MsaToken, StepXblDeviceToken.XblDeviceToken, StepInitialXblSession.InitialXblSession> initialXblSessionStep;
      private AbstractStep<?, ? extends StepXblXstsToken.XblXsts<?>> xblXstsTokenStep;

      private XblXstsTokenBuilder(MinecraftAuth.InitialXblSessionBuilder parent) {
         this.initialXblSessionStep = parent.build();
      }

      public MinecraftAuth.MinecraftBuilder sisuTitleAuthentication(String relyingParty) {
         this.xblXstsTokenStep = new StepXblSisuAuthentication(this.initialXblSessionStep, relyingParty);
         return new MinecraftAuth.MinecraftBuilder(this);
      }

      public MinecraftAuth.MinecraftBuilder titleAuthentication(String relyingParty) {
         this.xblXstsTokenStep = new StepXblXstsToken(
            new StepFullXblSession(new StepXblUserToken(this.initialXblSessionStep), new StepXblTitleToken(this.initialXblSessionStep)), relyingParty
         );
         return new MinecraftAuth.MinecraftBuilder(this);
      }

      public MinecraftAuth.MinecraftBuilder regularAuthentication(String relyingParty) {
         this.xblXstsTokenStep = new StepXblXstsToken(new StepFullXblSession(new StepXblUserToken(this.initialXblSessionStep), null), relyingParty);
         return new MinecraftAuth.MinecraftBuilder(this);
      }

      public AbstractStep<?, ? extends StepXblXstsToken.XblXsts<?>> build() {
         return this.xblXstsTokenStep;
      }
   }

   @SuppressWarnings("unchecked")
   private static <E extends Throwable> RuntimeException sneakyThrow(Throwable t) throws E {
      throw (E)t;
   }
}
