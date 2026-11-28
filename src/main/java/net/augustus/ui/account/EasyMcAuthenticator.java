package net.augustus.ui.account;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.mojang.authlib.yggdrasil.YggdrasilMinecraftSessionService;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Consumer;
import javax.net.ssl.HttpsURLConnection;
import net.augustus.utils.TimeHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.util.Session;

public class EasyMcAuthenticator {
   public static EasyMcSessionInfo currentSessionInfo;
   public static Session previousSession;
   private static final Gson GSON = new Gson();
   public static final ExecutorService EXECUTOR = Executors.newCachedThreadPool();
   private static final String LEGACY_API_KEY = null;

   private static void setSessionJoinUrl(String url) {
      try {
         Field field = YggdrasilMinecraftSessionService.class.getDeclaredField("JOIN_URL");
         field.setAccessible(true);
         Field modifiersField = Field.class.getDeclaredField("modifiers");
         modifiersField.setAccessible(true);
         modifiersField.setInt(field, field.getModifiers() & ~Modifier.FINAL);
         field.set(null, new URL(url));
      } catch (Exception e) {
         e.printStackTrace();
      }
   }

   public static void restoreMojangSession(Session session) {
      Minecraft.getMinecraft().setSession(session);
      setSessionJoinUrl("https://sessionserver.mojang.com/session/minecraft/join");
   }

   private static void redeemTokenSync(String str, Consumer consumer) {
      String str2 = "{\"token\":\"" + str + "\",\"client\":\"mod-1.8.9\"}";
      HttpsURLConnection httpsURLConnection = postJsonRequest("https://api.easymc.io/v1/token/redeem", str2);
      if (httpsURLConnection == null) {
         consumer.accept("Could not create Connection. Please try again later.");
      } else {
         Object obj = parseRedeemResponse(httpsURLConnection);
         if (obj instanceof String) {
            consumer.accept(obj);
         } else {
            JsonObject json = (JsonObject)obj;
            EasyMcSessionInfo easyMcSessionInfo = new EasyMcSessionInfo();
            easyMcSessionInfo.sessionToken = json.get("session").getAsString();
            easyMcSessionInfo.mcName = json.get("mcName").getAsString();
            easyMcSessionInfo.uuid = json.get("uuid").getAsString();
            consumer.accept(easyMcSessionInfo);
         }
      }
   }

   public static void redeemTokenAsync(String str, Consumer consumer) {
      EXECUTOR.execute(() -> redeemTokenSync(str, consumer));
   }

   public static Object parseRedeemResponse(HttpsURLConnection httpsURLConnection) {

      try {
         InputStreamReader inputStreamReader = httpsURLConnection.getResponseCode() != 200 ? new InputStreamReader(httpsURLConnection.getErrorStream()) : new InputStreamReader(httpsURLConnection.getInputStream());
         BufferedReader reader = new BufferedReader(inputStreamReader);
         StringBuilder sb = new StringBuilder();

         String str;
         while ((str = reader.readLine()) != null) {
            sb.append(str);
         }

         reader.close();
         JsonElement elem = (JsonElement)GSON.fromJson(sb.toString(), JsonElement.class);
         if (!elem.isJsonObject()) {
            return "Could not parse response.";
         } else if (elem.getAsJsonObject().has("error")) {
            return elem.getAsJsonObject().get("error").getAsString();
         } else {
            if (elem.getAsJsonObject().has("session") && elem.getAsJsonObject().has("uuid")) {
               if (elem.getAsJsonObject().has("mcName")) {
                  return elem.getAsJsonObject();
               }
            }

            return "Response is invalid.";
         }
      } catch (Exception e) {
         e.printStackTrace();
         return "Error: " + e.getMessage();
      }
   }

   public static void applyEasyMcSession(EasyMcSessionInfo easyMcSessionInfo) {
      AccountAuthenticator.scheduleEasyMcSessionLogin(easyMcSessionInfo);
      setSessionJoinUrl("https://sessionserver.easymc.io/session/minecraft/join");
   }

   public static HttpsURLConnection postJsonRequest(String str, String str2) {
      try {
         URL url = new URL(str);
         if (!"https".equalsIgnoreCase(url.getProtocol()) || !"api.easymc.io".equalsIgnoreCase(url.getHost())) {
            throw new IllegalArgumentException("EasyMC token redemption must use https://api.easymc.io");
         }

         HttpsURLConnection httpsURLConnection = (HttpsURLConnection)url.openConnection();
         httpsURLConnection.setInstanceFollowRedirects(false);
         httpsURLConnection.setConnectTimeout(10000);
         httpsURLConnection.setReadTimeout(10000);
         httpsURLConnection.setRequestMethod("POST");
         httpsURLConnection.setRequestProperty("Content-Type", "application/json");
         httpsURLConnection.setDoOutput(true);
         DataOutputStream out = new DataOutputStream(httpsURLConnection.getOutputStream());
         out.write(str2.getBytes("UTF-8"));
         out.flush();
         out.close();
         return httpsURLConnection;
      } catch (Exception e) {
         e.printStackTrace();
         return null;
      }
   }

}
