package net.augustus.ui.account;

import net.augustus.render.RenderUtil;
import net.augustus.settings.property.codec.JsonCodecRegistryHolder;
import net.augustus.settings.property.codec.JsonPropertyCodec;
import net.augustus.ui.UiIconResources;
import net.augustus.utils.MinotarAvatarCache;
import net.augustus.utils.TuplePair;
import net.augustus.utils.animation.AnimationUtil;
import net.augustus.utils.animation.EasingType;
import net.augustus.utils.input.KeyboardInputUtil;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.awt.Color;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.concurrent.CompletableFuture;
import net.augustus.utils.TimeHelper;
import net.augustus.utils.interfaces.MC;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class AccountEntry implements MC {
   private long lastLoginTime;
   private final HashMap bannedServerIcons;
   private boolean loggedIn;
   private final AnimationUtil hoverAnimation = new AnimationUtil(0.0F, 0.0F, 0.0F, 0.0F);
   private final TimeHelper timeHelper = new TimeHelper();
   private final HashMap unbannedServerIcons;
   private long playTimeMillis;
   private boolean hovered;
   private JsonObject accountData = null;
   private AccountLoginType loginType = AccountLoginType.CRACKED;
   private HashMap unbannedServers;
   private String username = "";
   private long sessionStartTime;
   private boolean working;
   private HashMap bannedServers;
   private int renderY;
   private int renderHeight;
   private int renderWidth;
   private int renderX;
   private boolean marked = false;

   public boolean isBannedOnServer(String str) {
      return this.bannedServers.get(str) != null;
   }

   public int getRenderHeight() {
      return this.renderHeight;
   }

   private void loginAsCracked() {
      AccountAuthenticator.loginSavedCrackedAccount(this);
   }

   public AccountLoginType getLoginType() {
      return this.loginType;
   }

   public int getRenderWidth() {
      return this.renderWidth;
   }

   public void setWorking(boolean flag) {
      this.working = flag;
      if (!flag && (this.loginType == AccountLoginType.MICROSOFT_ACCESS_TOKEN || (Boolean)AccountManagerScreen.instance.removeInvalidAccounts.getValue())) {
         AccountManagerScreen.accounts.remove(this);
      }
   }

   public String getUsername() {
      return this.username;
   }

   public JsonObject getAccountData() {
      return this.accountData;
   }

   public AccountEntry() {
      this.working = true;
      this.lastLoginTime = 0L;
      this.playTimeMillis = 0L;
      this.sessionStartTime = 0L;
      this.bannedServers = new HashMap();
      this.unbannedServers = new HashMap();
      this.bannedServerIcons = new HashMap();
      this.unbannedServerIcons = new HashMap();
      this.loggedIn = false;
   }

   public HashMap getBannedServerIcons() {
      return this.bannedServerIcons;
   }

   public JsonObject toJson() {
      JsonObject json = new JsonObject();
      JsonPropertyCodec stringCodec = JsonCodecRegistryHolder.GSON_SERIALIZER_PROVIDER.getStringCodec();
      String username2 = this.username;
      stringCodec.write("Name", username2, json);
      stringCodec = JsonCodecRegistryHolder.GSON_SERIALIZER_PROVIDER.getStringCodec();
      String str2 = this.loginType.name();
      stringCodec.write("Type", str2, json);
      stringCodec = JsonCodecRegistryHolder.GSON_SERIALIZER_PROVIDER.getLongCodec();
      Long lastLoginTime2 = this.lastLoginTime;
      stringCodec.write("LastLoginTime", lastLoginTime2, json);
      stringCodec = JsonCodecRegistryHolder.GSON_SERIALIZER_PROVIDER.getLongCodec();
      Long playTimeMillis2 = this.playTimeMillis;
      stringCodec.write("PlayTime", playTimeMillis2, json);
      stringCodec = JsonCodecRegistryHolder.GSON_SERIALIZER_PROVIDER.getBooleanCodec();
      Boolean marked2 = this.marked;
      stringCodec.write("Marked", marked2, json);
      stringCodec = JsonCodecRegistryHolder.GSON_SERIALIZER_PROVIDER.getBooleanCodec();
      Boolean working2 = this.working;
      stringCodec.write("Working", working2, json);
      stringCodec = JsonCodecRegistryHolder.GSON_SERIALIZER_PROVIDER.getBooleanCodec();
      Boolean loggedIn2 = this.loggedIn;
      stringCodec.write("LoggedIn", loggedIn2, json);
      stringCodec = JsonCodecRegistryHolder.GSON_SERIALIZER_PROVIDER.getJsonObjectCodec();
      JsonObject accountData2 = this.accountData;
      stringCodec.write("AccountData", accountData2, json);
      stringCodec = JsonCodecRegistryHolder.GSON_SERIALIZER_PROVIDER.getJsonArrayCodec();
      JsonArray array = this.serializeServerMap(this.bannedServers);
      stringCodec.write("Banned", array, json);
      stringCodec = JsonCodecRegistryHolder.GSON_SERIALIZER_PROVIDER.getJsonArrayCodec();
      JsonArray array2 = this.serializeServerMap(this.unbannedServers);
      stringCodec.write("Unbanned", array2, json);
      return json;
   }

   public void drawEntry(int n, int n2, int n3, int n4, int n5, int n6, boolean flag, List list) {
      this.renderX = n;
      this.renderY = n2;
      this.renderWidth = n3;
      this.renderHeight = n4;
      GL11.glPushMatrix();
      FontRenderer fontRendererObj = mc.fontRendererObj;
      float f = (float)n3 / 2.0F;
      float f2 = (float)n4 / 2.0F;
      float f3 = (float)n + f;
      float f4 = (float)n2 + f2;
      boolean isHovered = RenderUtil.isHovered((float)n, (float)n2, (float)n3, (float)n4, n5, n6);
      if (this.hovered != isHovered) {
         this.hoverAnimation.start(100, EasingType.easeInSine, isHovered ? 1 : 0, false);
      }

      float f5 = (float)(1.0 - this.hoverAnimation.getEasedProgress() * 0.02F);
      GL11.glScaled((double)f5, (double)f5, 0.0);
      f3 /= f5;
      f4 /= f5;
      this.hovered = isHovered;
      Gui.drawRect((int)(f3 - f), (int)(f4 - f2), (int)(f3 + f), (int)(f4 + f2), new Color(0, 0, 0, flag ? 200 : 100).getRGB());
      GL11.glScaled((double)(1.0F / f5), (double)(1.0F / f5), 0.0);
      int n7 = 10;
      this.loggedIn = mc.getSession().getUsername().equals(this.username) && mc.getSession().getAccountEntry() != null && mc.getSession().getAccountEntry().equals(this);
      if (this.isMarked()) {
         String str = mc.getSession() != null && mc.getSession().getUsername().equals(this.username) ? "§7Name: §a" + this.username : "§7Name: §6" + this.username;
         n7 += fontRendererObj.getStringWidth(str);
         fontRendererObj.drawString(str, (float)n + 5.5F, (float)n2 + 3.5F, Color.white.getRGB());
         fontRendererObj.drawString("§7Login type: §6" + this.loginType.getDisplayName(), (float)n + 5.5F, (float)n2 + 7.0F + (float)fontRendererObj.FONT_HEIGHT, Color.white.getRGB());
         StringBuilder sb = new StringBuilder();
         sb = sb.append("§7Account state: §6");
         String str2;
         if (this.working) {
            str2 = "Functional";
         } else {
            str2 = "§cInvalid";
         }

         fontRendererObj.drawString(sb.append(str2).toString(), (float)n + 5.5F, (float)n2 + 10.5F + (float)(fontRendererObj.FONT_HEIGHT * 2), Color.white.getRGB());
      } else {
         String str3 = mc.getSession() != null && mc.getSession().getUsername().equals(this.username) ? "§7Name: §a" + this.username : "§7Name: §f" + this.username;
         n7 += fontRendererObj.getStringWidth(str3);
         fontRendererObj.drawString(str3, (float)n + 5.5F, (float)n2 + 3.5F, Color.white.getRGB());
         fontRendererObj.drawString("§7Login type: §f" + this.loginType.getDisplayName(), (float)n + 5.5F, (float)n2 + 7.0F + (float)fontRendererObj.FONT_HEIGHT, Color.white.getRGB());
         StringBuilder sb2 = new StringBuilder();
         short s = 1245;
         sb2 = sb2.append("§7Account state: §f");
         String str4;
         if (this.working) {
            str4 = "Functional";
         } else {
            s = 16287;
            str4 = "§cInvalid";
         }

         fontRendererObj.drawString(sb2.append(str4).toString(), (float)n + 5.5F, (float)n2 + 10.5F + (float)(fontRendererObj.FONT_HEIGHT * 2), Color.white.getRGB());
      }

      int n8 = 0;

      for (TuplePair tuplePair : (Iterable<TuplePair>)(this.bannedServerIcons.values())) {
         if (tuplePair.getSecond() != null) {
            GlStateManager.color(1.0F, 1.0F, 1.0F);
            this.drawServerIcon(n + n7 + n8, n2 + 2, (ResourceLocation)tuplePair.getFirst());
            n8 += 12;
         }
      }

      GL11.glScaled((double)f5, (double)f5, 0.0);
      ResourceLocation location = (ResourceLocation)MinotarAvatarCache.avatarCache.get(this.username);
      if (location != null) {
         UiIconResources.drawIcon(location, (int)(f3 - f + (float)n3 - (float)n4), (int)(f4 - f2), n4, n4, 1.0);
      }

      GL11.glPopMatrix();
   }

   public boolean isWorking() {
      return this.working;
   }

   public void refreshServerIcons(HashMap map) {
      this.unbannedServerIcons.clear();

      for (String str : (Iterable<String>)(this.unbannedServers.keySet())) {
         TuplePair tuplePair = (TuplePair)map.get(str);
         if (tuplePair != null) {
            this.unbannedServerIcons.put(str, tuplePair);
         }
      }

      this.bannedServerIcons.clear();

      for (String str2 : (Iterable<String>)(this.bannedServers.keySet())) {
         TuplePair tuplePair2 = (TuplePair)map.get(str2);
         if (tuplePair2 != null) {
            this.bannedServerIcons.put(str2, tuplePair2);
         }
      }
   }

   public void fromJson(JsonObject json) {
      JsonPropertyCodec stringCodec = JsonCodecRegistryHolder.GSON_SERIALIZER_PROVIDER.getStringCodec();
      String username2 = this.username;
      this.username = (String)stringCodec.read("Name", username2, json);
      JsonPropertyCodec stringCodec2 = JsonCodecRegistryHolder.GSON_SERIALIZER_PROVIDER.getStringCodec();
      String str3 = this.loginType.name();
      this.loginType = AccountLoginType.valueOf((String)stringCodec2.read("Type", str3, json));
      JsonPropertyCodec longCodec = JsonCodecRegistryHolder.GSON_SERIALIZER_PROVIDER.getLongCodec();
      Long lastLoginTime2 = this.lastLoginTime;
      this.lastLoginTime = (Long)longCodec.read("LastLoginTime", lastLoginTime2, json);
      longCodec = JsonCodecRegistryHolder.GSON_SERIALIZER_PROVIDER.getLongCodec();
      Long playTimeMillis2 = this.playTimeMillis;
      this.playTimeMillis = (Long)longCodec.read("PlayTime", playTimeMillis2, json);
      JsonPropertyCodec booleanCodec = JsonCodecRegistryHolder.GSON_SERIALIZER_PROVIDER.getBooleanCodec();
      Boolean marked2 = this.marked;
      this.marked = (Boolean)booleanCodec.read("Marked", marked2, json);
      JsonPropertyCodec booleanCodec2 = JsonCodecRegistryHolder.GSON_SERIALIZER_PROVIDER.getBooleanCodec();
      Boolean working2 = this.working;
      this.working = (Boolean)booleanCodec2.read("Working", working2, json);
      JsonPropertyCodec booleanCodec3 = JsonCodecRegistryHolder.GSON_SERIALIZER_PROVIDER.getBooleanCodec();
      Boolean loggedIn2 = this.loggedIn;
      this.loggedIn = (Boolean)booleanCodec3.read("LoggedIn", loggedIn2, json);
      JsonPropertyCodec jsonObjectCodec = JsonCodecRegistryHolder.GSON_SERIALIZER_PROVIDER.getJsonObjectCodec();
      JsonObject accountData2 = this.accountData;
      this.accountData = (JsonObject)jsonObjectCodec.read("AccountData", accountData2, json);
      longCodec = JsonCodecRegistryHolder.GSON_SERIALIZER_PROVIDER.getJsonArrayCodec();
      this.bannedServers = this.deserializeServerMap((JsonArray)longCodec.read("Banned", (Object)null, json));
      longCodec = JsonCodecRegistryHolder.GSON_SERIALIZER_PROVIDER.getJsonArrayCodec();
      this.unbannedServers = this.deserializeServerMap((JsonArray)longCodec.read("Unbanned", (Object)null, json));
      if (this.loggedIn) {
         this.authenticateAsync();
      }
   }

   public boolean isUnbannedOnServer(String str) {
      return this.unbannedServers.get(str) != null;
   }

   public synchronized void updateAccountData(JsonObject json, String str) {
      this.accountData = json;
      this.username = str;
      this.lastLoginTime = System.currentTimeMillis();
   }

   public int getRenderY() {
      return this.renderY;
   }

   private void loginWithCredentials() {
      AccountAuthenticator.refreshCredentialsAccount(this);
   }

   public JsonArray serializeServerMap(HashMap map) {
      JsonArray array = new JsonArray();

      for (Entry entry : (Iterable<Entry>)(map.entrySet())) {
         JsonObject json = new JsonObject();
         JsonPropertyCodec stringCodec = JsonCodecRegistryHolder.GSON_SERIALIZER_PROVIDER.getStringCodec();
         Object key = entry.getKey();
         stringCodec.write("Key", key, json);
         stringCodec = JsonCodecRegistryHolder.GSON_SERIALIZER_PROVIDER.getLongCodec();
         Object value = entry.getValue();
         stringCodec.write("Value", value, json);
         array.add(json);
      }

      return array;
   }

   public long getSessionStartTime() {
      return this.sessionStartTime;
   }

   private void loginWithDeviceCode() {
      AccountAuthenticator.refreshDeviceCodeAccount(this);
   }

   public void addPlayTime(long l) {
      this.playTimeMillis += l;
   }

   public void markUnbannedOnServer(String str) {
      this.bannedServers.remove(str);
      this.bannedServerIcons.remove(str);
      if (((Long)this.unbannedServers.get(str)) == null) {
         this.unbannedServers.put(str, System.currentTimeMillis());
         ResourceLocation location = new ResourceLocation("servers/" + str + "/icon");
         this.unbannedServerIcons.put(str, new TuplePair(location, (DynamicTexture)mc.getTextureManager().getTexture(location)));
      } else {
         this.unbannedServers.remove(str);
         this.unbannedServers.put(str, System.currentTimeMillis());
      }
   }

   public HashMap getBannedServers() {
      return this.bannedServers;
   }

   public int getRenderX() {
      return this.renderX;
   }

   public HashMap getUnbannedServers() {
      return this.unbannedServers;
   }

   public void markBannedOnServer(String str, Long val) {
      this.unbannedServers.remove(str);
      this.unbannedServerIcons.remove(str);
      if (((Long)this.bannedServers.get(str)) == null) {
         this.bannedServers.put(str, val);
         ResourceLocation location = new ResourceLocation("servers/" + str + "/icon");
         this.bannedServerIcons.put(str, new TuplePair(location, (DynamicTexture)mc.getTextureManager().getTexture(location)));
      } else {
         this.bannedServers.remove(str);
         this.bannedServers.put(str, val);
      }
   }

   public long getLastLoginTime() {
      return this.lastLoginTime;
   }

   public HashMap getUnbannedServerIcons() {
      return this.unbannedServerIcons;
   }

   public void setSessionStartTime(long l) {
      this.sessionStartTime = l;
   }

   public void mouseClicked(int mouseX, int mouseY, int mouseButton) {
      if (mouseButton == 0 && RenderUtil.isHovered((float)this.renderX, (float)this.renderY, (float)this.renderWidth, (float)this.renderHeight, mouseX, mouseY)) {
         if (!KeyboardInputUtil.isKeyDown(157) && !KeyboardInputUtil.isKeyDown(29)) {
            if (!this.timeHelper.reached(250L) && (AccountManagerScreen.instance.getActiveLoginTask() == null || AccountManagerScreen.instance.getActiveLoginTask().isDone())) {
               this.authenticateAsync();
            }

            this.timeHelper.reset();
         } else {
            this.marked = !this.marked;
         }
      }
   }

   private void loginWithExternalBrowser() {
      AccountAuthenticator.refreshExternalBrowserAccount(this);
   }

   public long getPlayTimeMillis() {
      return this.playTimeMillis;
   }

   public AccountEntry(String str, JsonObject json, AccountLoginType accountLoginType) {
      this.working = true;
      this.lastLoginTime = 0L;
      this.playTimeMillis = 0L;
      this.sessionStartTime = 0L;
      this.bannedServers = new HashMap();
      this.unbannedServers = new HashMap();
      this.bannedServerIcons = new HashMap();
      this.unbannedServerIcons = new HashMap();
      this.loggedIn = false;
      this.username = str;
      this.accountData = json;
      this.loginType = accountLoginType;
      this.lastLoginTime = System.currentTimeMillis();
   }

   public boolean isMarked() {
      return this.marked;
   }

   private void authenticateAsync() {
      switch (this.loginType) {
         case MICROSOFT_CREDENTIALS:
            AccountManagerScreen.instance.setActiveLoginTask(CompletableFuture.runAsync(() -> this.loginWithCredentials()));
            break;
         case MICROSOFT_DEVICE_CODE:
            AccountManagerScreen.instance.setActiveLoginTask(CompletableFuture.runAsync(() -> this.loginWithDeviceCode()));
            break;
         case MICROSOFT_EXTERNAL_BROWSER:
            AccountManagerScreen.instance.setActiveLoginTask(CompletableFuture.runAsync(() -> this.loginWithExternalBrowser()));
            break;
         case MICROSOFT_ACCESS_TOKEN:
            AccountManagerScreen.instance.setActiveLoginTask(CompletableFuture.runAsync(() -> {
               AccountAuthenticator.loginWithSavedAccessToken(this);
            }));
            break;
         case CRACKED:
            AccountManagerScreen.instance.setActiveLoginTask(CompletableFuture.runAsync(() -> this.loginAsCracked()));
            break;
         case THE_ALTENING:
            AccountManagerScreen.instance.setActiveLoginTask(CompletableFuture.runAsync(() -> {
               AccountAuthenticator.loginSavedTheAlteningAccount(this);
            }));
      }
   }

   private void drawServerIcon(int n, int n2, ResourceLocation location) {
      mc.getTextureManager().bindTexture(location);
      GlStateManager.enableBlend();
      Gui.drawModalRectWithCustomSizedTexture(n, n2, 0.0F, 0.0F, 10, 10, 10.0F, 10.0F);
      GlStateManager.disableBlend();
   }

   public HashMap deserializeServerMap(JsonArray array) {
      if (array == null) {
         return new HashMap();
      } else {
         HashMap map = new HashMap();

         for (JsonElement elem : array) {
            if (elem instanceof JsonObject) {
               try {
                  JsonObject json = (JsonObject)elem;
                  JsonPropertyCodec stringCodec = JsonCodecRegistryHolder.GSON_SERIALIZER_PROVIDER.getStringCodec();
                  String str = "";
                  String str3 = (String)stringCodec.read("Key", str, json);
                  stringCodec = JsonCodecRegistryHolder.GSON_SERIALIZER_PROVIDER.getLongCodec();
                  Long minvalue = Long.MIN_VALUE;
                  Long val2 = (Long)stringCodec.read("Value", minvalue, json);
                  if (!str3.isEmpty() && val2 != Long.MIN_VALUE) {
                     map.put(str3, val2);
                  }
               } catch (Exception e) {
               }
            }
         }

         return map;
      }
   }

}
