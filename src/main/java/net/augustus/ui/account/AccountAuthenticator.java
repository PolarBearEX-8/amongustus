package net.augustus.ui.account;

import net.augustus.Augustus;
import net.augustus.utils.MinotarAvatarCache;
import net.augustus.utils.audio.ClientSounds;
import net.augustus.utils.input.KeyboardInputUtil;
import com.thealtening.auth.TheAlteningAuthentication;
import com.thealtening.auth.service.AlteningServiceType;
import com.thealtening.api.TheAltening;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.mojang.authlib.Agent;
import com.mojang.authlib.exceptions.AuthenticationException;
import com.mojang.authlib.yggdrasil.YggdrasilAuthenticationService;
import com.mojang.authlib.yggdrasil.YggdrasilUserAuthentication;
import java.awt.Color;
import java.awt.Desktop;
import java.io.File;
import java.net.HttpURLConnection;
import java.net.Proxy;
import java.net.URI;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import net.augustus.utils.interfaces.MC;
import net.lenni0451.commons.httpclient.HttpClient;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.network.PacketThreadUtil;
import net.minecraft.util.Session;
import net.raphimc.minecraftauth.MinecraftAuth;
import net.raphimc.minecraftauth.step.AbstractStep;
import net.raphimc.minecraftauth.step.java.StepMCProfile;
import net.raphimc.minecraftauth.step.java.StepPlayerCertificates;
import net.raphimc.minecraftauth.step.java.session.StepFullJavaSession;
import net.raphimc.minecraftauth.step.msa.StepCredentialsMsaCode;
import net.raphimc.minecraftauth.step.msa.StepLocalWebServer;
import net.raphimc.minecraftauth.step.msa.StepMsaDeviceCode;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class AccountAuthenticator implements MC {
   private static final TheAlteningAuthentication ALTENING_AUTH;
   private static String lastLoggedInUsername;
   public static final AbstractStep EXTERNAL_BROWSER_LOGIN_STEP;
   private static String lastConnectedUsername;

   private static void openLocalWebServerAuthUrl(StepLocalWebServer.LocalWebServer localWebServer) {
      if (!openUrlInBrowserOrClipboard(localWebServer.getAuthenticationUrl(), true)) {
         scheduleLoginFailure((AccountEntry)null);
      }
   }

   private static void switchAuthService(AlteningServiceType alteningServiceType) {
      ALTENING_AUTH.updateService(alteningServiceType);
      PacketThreadUtil.runOnMainThread(() -> updateAuthServiceAndStatus(alteningServiceType));
   }

   public static void refreshMicrosoftAccount(AbstractStep step, AccountEntry accountEntry) {
      switchAuthService(AlteningServiceType.MOJANG);
      HttpClient httpClient = MinecraftAuth.createHttpClient();

      try {
         StepFullJavaSession.FullJavaSession fullJavaSession2 = (StepFullJavaSession.FullJavaSession)step.refresh(httpClient, (StepFullJavaSession.FullJavaSession)step.fromJson(accountEntry.getAccountData()));
         accountEntry.setWorking(true);
         scheduleJavaSessionLogin(fullJavaSession2, accountEntry);
         accountEntry.updateAccountData(step.toJson(fullJavaSession2), fullJavaSession2.getMcProfile().getName());
      } catch (Exception e) {
         e.printStackTrace();
         scheduleLoginFailure(accountEntry);
      }
   }

   public static void loginCracked(String str, boolean flag) {
      switchAuthService(AlteningServiceType.MOJANG);
      EasyMcAuthenticator.restoreMojangSession(mc.getSession());
      AccountEntry accountEntry = null;
      if (flag) {
         accountEntry = findOrCreateAccountEntry(str, createTokenJson(str), AccountLoginType.CRACKED);
      }

      scheduleSessionUpdate(str, "", "", (StepPlayerCertificates.PlayerCertificates)null, accountEntry);
   }

   public static void loginWithSavedAccessToken(AccountEntry accountEntry) {
      switchAuthService(AlteningServiceType.MOJANG);
      JsonObject accountData = accountEntry.getAccountData();
      if (accountData != null && accountData.has("Token") && accountData.has("username") && accountData.has("uuid")) {
         String asString = accountData.get("Token").getAsString();
         String asString2 = accountData.get("username").getAsString();
         String asString3 = accountData.get("uuid").getAsString();

         try {
            HttpURLConnection conn = (HttpURLConnection)new URL("https://api.minecraftservices.com/minecraft/profile").openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Authorization", "Bearer " + asString);
            conn.setConnectTimeout(5000);
            conn.setReadTimeout(5000);
            int responseCode = conn.getResponseCode();
            conn.disconnect();
            if (responseCode == 200) {
               scheduleSessionUpdate(asString2, asString3, asString, (StepPlayerCertificates.PlayerCertificates)null, accountEntry);
               accountEntry.setWorking(true);
               return;
            }
         } catch (Exception e) {
         }

         scheduleLoginFailure(accountEntry);
      } else {
         scheduleLoginFailure(accountEntry);
      }
   }

   private static String extractToken(JsonObject json) {
      return json.get("Token").getAsString();
   }

   private static void applySession(String str, String str2, String str3, StepPlayerCertificates.PlayerCertificates playerCertificates, AccountEntry accountEntry) {
      mc.setSession(new Session(str, str2, str3, "msn", playerCertificates, accountEntry));
      recordLoggedInUsername(str);
      AccountManagerScreen.instance.setStatus("Logged in successfully!", Color.GREEN);
      playSuccessSound();
   }

   public static void loginMicrosoftCredentials(String str, String str2) {
      switchAuthService(AlteningServiceType.MOJANG);
      HttpClient httpClient = MinecraftAuth.createHttpClient();

      try {
         StepFullJavaSession.FullJavaSession fromInput = (StepFullJavaSession.FullJavaSession)MinecraftAuth.JAVA_CREDENTIALS_LOGIN.getFromInput(
            httpClient, new StepCredentialsMsaCode.MsaCredentials(str, str2)
         );
         scheduleJavaSessionLogin(fromInput, findOrCreateAccountEntry(
            fromInput.getMcProfile().getName(), MinecraftAuth.JAVA_CREDENTIALS_LOGIN.toJson(fromInput), AccountLoginType.MICROSOFT_CREDENTIALS
         ));
      } catch (Exception e) {
         scheduleLoginFailure((AccountEntry)null);
      }
   }

   private static synchronized AccountEntry findOrCreateAccountEntry(String str, JsonObject json, AccountLoginType accountLoginType) {
      Iterator it = AccountManagerScreen.accounts.iterator();

      while (it.hasNext()) {
         AccountEntry accountEntry = (AccountEntry)it.next();
         if (accountEntry.getUsername().equals(str) && accountLoginType == accountEntry.getLoginType()) {
            return accountEntry;
         }
      }

      AccountEntry accountEntry2 = new AccountEntry(str, json, accountLoginType);
      AccountManagerScreen.accounts.add(0, accountEntry2);
      AccountManagerScreen.scrollBar.setItemCount(AccountManagerScreen.accounts.size());
      MinotarAvatarCache.getAvatar(str, 40);
      return accountEntry2;
   }

   public static void loginWithAccessToken(String str) {
      switchAuthService(AlteningServiceType.MOJANG);
      if (str != null && !str.trim().isEmpty()) {
         try {
            JsonObject json = fetchMinecraftProfileByToken(str);
            String asString = json.get("username").getAsString();
            String asString2 = json.get("uuid").getAsString();
            AccountEntry accountEntry = findOrCreateAccountEntry(asString, json, AccountLoginType.MICROSOFT_ACCESS_TOKEN);
            scheduleSessionUpdate(asString, asString2, str, (StepPlayerCertificates.PlayerCertificates)null, accountEntry);
         } catch (Exception e) {
            scheduleLoginFailure((AccountEntry)null);
         }
      } else {
         scheduleLoginFailure((AccountEntry)null);
      }
   }

   public static void refreshExternalBrowserAccount(AccountEntry accountEntry) {
      refreshMicrosoftAccount(EXTERNAL_BROWSER_LOGIN_STEP, accountEntry);
   }

   public static void generateAndLoginTheAltening(String str) {
      loginTheAlteningToken(TheAltening.newBasicRetriever(str).getAccount().getToken());
   }

   private static void handleEasyMcResponse(Object obj) {
      if (obj instanceof String) {
         scheduleLoginFailure((AccountEntry)null);
      } else {
         if (EasyMcAuthenticator.previousSession == null) {
            EasyMcAuthenticator.previousSession = mc.getSession();
         }

         EasyMcSessionInfo easyMcSessionInfo = (EasyMcSessionInfo)obj;
         EasyMcAuthenticator.currentSessionInfo = easyMcSessionInfo;
         EasyMcAuthenticator.applyEasyMcSession(easyMcSessionInfo);
      }
   }

   public static void loginEasyMc(String str) {
      switchAuthService(AlteningServiceType.MOJANG);
      if (str.isEmpty() && !openUrlInBrowserOrClipboard("https://easymc.io/", false)) {
         scheduleLoginFailure((AccountEntry)null);
      } else {
         EasyMcAuthenticator.redeemTokenAsync(str, obj -> handleEasyMcResponse(obj));
      }
   }

   private static void handleLoginFailureOnMainThread(AccountEntry accountEntry) {
      if (accountEntry != null) {
         accountEntry.setWorking(false);
      }

      AccountManagerScreen.instance.setStatus("Login Failed", Color.RED);
      playFailureSound();
   }

   public static void loginExternalBrowser() {
      switchAuthService(AlteningServiceType.MOJANG);
      HttpClient httpClient = MinecraftAuth.createHttpClient();

      try {
         StepFullJavaSession.FullJavaSession fromInput = (StepFullJavaSession.FullJavaSession)EXTERNAL_BROWSER_LOGIN_STEP.getFromInput(
            httpClient, new StepLocalWebServer.LocalWebServerCallback(localWebServer -> openLocalWebServerAuthUrl(localWebServer))
         );
         scheduleJavaSessionLogin(fromInput, findOrCreateAccountEntry(fromInput.getMcProfile().getName(), EXTERNAL_BROWSER_LOGIN_STEP.toJson(fromInput), AccountLoginType.MICROSOFT_EXTERNAL_BROWSER));
      } catch (Exception e) {
         scheduleLoginFailure((AccountEntry)null);
      }
   }

   private static void openDeviceCodeVerificationUrl(StepMsaDeviceCode.MsaDeviceCode msaDeviceCode) {
      if (!openUrlInBrowserOrClipboard(msaDeviceCode.getDirectVerificationUri(), false)) {
         scheduleLoginFailure((AccountEntry)null);
      }
   }

   private static void scheduleJavaSessionLogin(StepFullJavaSession.FullJavaSession fullJavaSession, AccountEntry accountEntry) {
      StepMCProfile.MCProfile mcProfile = fullJavaSession.getMcProfile();
      scheduleSessionUpdate(mcProfile.getName(), mcProfile.getId().toString(), mcProfile.getMcToken().getAccessToken(), fullJavaSession.getPlayerCertificates(), accountEntry);
   }

   public static void scheduleYggdrasilSessionLogin(YggdrasilUserAuthentication authentication, AccountEntry accountEntry) {
      scheduleSessionUpdate(
         authentication.getSelectedProfile().getName(),
         authentication.getSelectedProfile().getId().toString(),
         authentication.getAuthenticatedToken(),
         (StepPlayerCertificates.PlayerCertificates)null,
         accountEntry
      );
   }

   public static AbstractStep createLocalWebServerLoginStep(String str, String str2, String str3, String str4) {
      return MinecraftAuth.builder()
         .withClientId(str)
         .withScope(str2)
         .withRedirectUri(str3)
         .withClientSecret(str4)
         .localWebServer()
         .withoutDeviceToken()
         .regularAuthentication("rp://api.minecraftservices.com/")
         .buildMinecraftJavaProfileStep(false);
   }

   public static void loginDeviceCode() {
      switchAuthService(AlteningServiceType.MOJANG);
      HttpClient httpClient = MinecraftAuth.createHttpClient();

      try {
         StepFullJavaSession.FullJavaSession fromInput = (StepFullJavaSession.FullJavaSession)MinecraftAuth.JAVA_DEVICE_CODE_LOGIN.getFromInput(
            httpClient, new StepMsaDeviceCode.MsaDeviceCodeCallback(msaDeviceCode -> openDeviceCodeVerificationUrl(msaDeviceCode))
         );
         scheduleJavaSessionLogin(fromInput, findOrCreateAccountEntry(
            fromInput.getMcProfile().getName(), MinecraftAuth.JAVA_DEVICE_CODE_LOGIN.toJson(fromInput), AccountLoginType.MICROSOFT_DEVICE_CODE
         ));
      } catch (Exception e) {
         scheduleLoginFailure((AccountEntry)null);
      }
   }

   public static void recordLoggedInUsername(String str) {
      if (!lastLoggedInUsername.equals(str)) {
      }

      lastLoggedInUsername = str;
   }

   private static JsonObject createTokenJson(String str) {
      JsonObject json = new JsonObject();
      json.addProperty("Token", str);
      return json;
   }

   public static boolean openUrlInBrowserOrClipboard(String str, boolean flag) {

      try {
         if (KeyboardInputUtil.isCtrlDown()) {
            GuiScreen.setClipboardString(str);
            return true;
         } else {
            if (!str.toLowerCase().startsWith("https://") && !str.toLowerCase().startsWith("http://")) {
               str = "http://" + str;
            }

            URI uri = (new URL(str)).toURI();
            Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;
            if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE)) {
               File file = new File("C:\\Program Files\\Mozilla Firefox\\firefox.exe");
               if (flag && file.exists()) {
                  (new ProcessBuilder(new String[]{file.getAbsolutePath(), uri.toString()})).start();
                  return true;
               } else {
                  try {
                     desktop.browse(uri);
                  } catch (Exception e) {
                     e.printStackTrace();
                  }

                  return true;
               }
            } else {
               GuiScreen.setClipboardString(str);
               return true;
            }
         }
      } catch (Exception e2) {
         e2.printStackTrace();
         return false;
      }
   }

   public static JsonObject fetchMinecraftProfileByToken(String str) throws Exception {
      RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(5000).setConnectionRequestTimeout(5000).setSocketTimeout(5000).build();

      try (CloseableHttpClient closeableHttpClient = HttpClients.custom().setDefaultRequestConfig(requestConfig).build()) {
         HttpGet httpGet = new HttpGet("https://api.minecraftservices.com/minecraft/profile");
         httpGet.setHeader("Authorization", "Bearer " + str);

         try (CloseableHttpResponse closeableHttpResponse = closeableHttpClient.execute(httpGet)) {
            JsonObject asJsonObject = JsonParser.parseString(EntityUtils.toString(closeableHttpResponse.getEntity(), StandardCharsets.UTF_8)).getAsJsonObject();
            String asString = asJsonObject.get("name").getAsString();
            String asString2 = asJsonObject.get("id").getAsString();
            JsonObject json = new JsonObject();
            json.addProperty("Token", str);
            json.addProperty("username", asString);
            json.addProperty("uuid", asString2);
            return json;
         }
      }
   }

   public static void loginSavedTheAlteningAccount(AccountEntry accountEntry) {
      switchAuthService(AlteningServiceType.THEALTENING);
      YggdrasilUserAuthentication authentication = (YggdrasilUserAuthentication)new YggdrasilAuthenticationService(Proxy.NO_PROXY, "").createUserAuthentication(Agent.MINECRAFT);
      String str = extractToken(accountEntry.getAccountData());
      authentication.setUsername(str);
      authentication.setPassword("password1");

      try {
         authentication.logIn();
         scheduleYggdrasilSessionLogin(authentication, accountEntry);
         accountEntry.updateAccountData(createTokenJson(str), authentication.getSelectedProfile().getName());
         accountEntry.setWorking(true);
      } catch (AuthenticationException e) {
         scheduleLoginFailure(accountEntry);
      }
   }

   public static void playSuccessSound() {
      switch (Augustus.getInstance().getModuleManager().clientSettings.mode.getSelectedValue()) {
         case "Vanilla":
            mc.getSoundHandler().playSound(PositionedSoundRecord.create(ClientSounds.GUI_TEXTURE, 1.0F));
            break;
         case "Piano":
         case "Sigma":
            ClientSounds.playSound(ClientSounds.AUGUSTUS_TEXTURE2);
      }
   }

   public static void loginSavedCrackedAccount(AccountEntry accountEntry) {
      switchAuthService(AlteningServiceType.MOJANG);
      EasyMcAuthenticator.restoreMojangSession(mc.getSession());
      scheduleSessionUpdate(extractToken(accountEntry.getAccountData()), "", "", (StepPlayerCertificates.PlayerCertificates)null, accountEntry);
   }

   public static void recordServerConnection(String str, String str2) {
      if (!lastConnectedUsername.equals(str)) {
      }

      lastConnectedUsername = str;
   }

   public static void playFailureSound() {
      switch (Augustus.getInstance().getModuleManager().clientSettings.mode.getSelectedValue()) {
         case "Vanilla":
            mc.getSoundHandler().playSound(PositionedSoundRecord.create(ClientSounds.GUI_TEXTURE, 1.0F));
            break;
         case "Piano":
         case "Sigma":
            ClientSounds.playSound(ClientSounds.AUGUSTUS_TEXTURE6);
      }
   }

   private static void updateAuthServiceAndStatus(AlteningServiceType alteningServiceType) {
      AccountManagerScreen.instance.setStatus("Logging in...", Color.ORANGE);
      ALTENING_AUTH.updateService(alteningServiceType);
   }

   private static void scheduleLoginFailure(AccountEntry accountEntry) {
      PacketThreadUtil.runOnMainThread(() -> handleLoginFailureOnMainThread(accountEntry));
   }

   public static void loginTheAlteningToken(String str) {
      switchAuthService(AlteningServiceType.THEALTENING);
      YggdrasilUserAuthentication authentication = (YggdrasilUserAuthentication)new YggdrasilAuthenticationService(Proxy.NO_PROXY, "").createUserAuthentication(Agent.MINECRAFT);
      authentication.setUsername(str);
      authentication.setPassword("password1");

      try {
         authentication.logIn();
         scheduleYggdrasilSessionLogin(authentication, findOrCreateAccountEntry(authentication.getSelectedProfile().getName(), createTokenJson(str), AccountLoginType.THE_ALTENING));
      } catch (AuthenticationException e) {
         scheduleLoginFailure((AccountEntry)null);
      }
   }

   public static void scheduleEasyMcSessionLogin(EasyMcSessionInfo easyMcSessionInfo) {
      scheduleSessionUpdate(easyMcSessionInfo.mcName, easyMcSessionInfo.uuid, easyMcSessionInfo.sessionToken, (StepPlayerCertificates.PlayerCertificates)null, (AccountEntry)null);
   }

   public static void refreshCredentialsAccount(AccountEntry accountEntry) {
      refreshMicrosoftAccount(MinecraftAuth.JAVA_CREDENTIALS_LOGIN, accountEntry);
   }

   public static void refreshDeviceCodeAccount(AccountEntry accountEntry) {
      refreshMicrosoftAccount(MinecraftAuth.JAVA_DEVICE_CODE_LOGIN, accountEntry);
   }

   public static void scheduleSessionUpdate(
      String str, String str2, String str3, StepPlayerCertificates.PlayerCertificates playerCertificates, AccountEntry accountEntry
   ) {
      PacketThreadUtil.runOnMainThread(() -> applySession(str, str2, str3, playerCertificates, accountEntry));
   }



   static {
      ALTENING_AUTH = TheAlteningAuthentication.mojang();
      lastLoggedInUsername = "";
      lastConnectedUsername = "";
      EXTERNAL_BROWSER_LOGIN_STEP = createLocalWebServerLoginStep(
         "27843883-6e3b-42cb-9e51-4f55a700601e",
         "XboxLive.signin offline_access",
         "http://localhost",
         (String)null
      );
   }
}
