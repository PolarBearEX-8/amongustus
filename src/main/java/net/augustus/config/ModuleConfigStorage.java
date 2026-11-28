package net.augustus.config;

import net.augustus.Augustus;
import net.augustus.settings.options.NotificationCategoryOption;
import net.augustus.ui.notifications.NotificationLevel;
import net.augustus.ui.notifications.NotificationManager;
import net.augustus.utils.ChatUtil;
import net.augustus.utils.audio.ClientSounds;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.HashMap;
import net.minecraft.client.Minecraft;
import net.minecraft.network.PacketThreadUtil;

public class ModuleConfigStorage extends JsonConfigFile {
   private static final byte[] ENCRYPTED_MAGIC_HEADER = new byte[]{-82, 83, 1, 0};
   private static final byte[] AES_KEY = new byte[]{
      114, -27, 95, -64, 18, -101, 73, -38, 126, -9, 44, -87, 61, -122, 7, -55,
      89, -16, 35, -78, 103, -41, 52, -94, 67, -128, 21, -63, 98, -11, 76, -49
   };
   private static final Aes256Ecb CONFIG_CIPHER = new Aes256Ecb(AES_KEY);
   private final HashMap<Integer, ModuleConfigCodec> formatCodecs;

   public ModuleConfigStorage() {
      super(
         text -> ((String)text).getBytes(StandardCharsets.UTF_8),
         bytes -> decryptConfigBytes((byte[])bytes)
      );
      this.formatCodecs = new HashMap<>();
      this.formatCodecs.put(2, new ConfigFormatV2());
   }

   public static boolean hasEncryptedHeader(byte[] bytes) {
      if (bytes == null || bytes.length < ENCRYPTED_MAGIC_HEADER.length) {
         return false;
      }
      for (int i = 0; i < ENCRYPTED_MAGIC_HEADER.length; i++) {
         if (bytes[i] != ENCRYPTED_MAGIC_HEADER[i]) {
            return false;
         }
      }
      return true;
   }

   public static String decryptConfigBytes(byte[] bytes) {
      if (bytes == null || bytes.length == 0) {
         return "";
      }
      if (!hasEncryptedHeader(bytes)) {
         return new String(bytes, StandardCharsets.UTF_8);
      }
      try {
         byte[] decrypted = CONFIG_CIPHER.decryptPkcs7(bytes, ENCRYPTED_MAGIC_HEADER.length, bytes.length - ENCRYPTED_MAGIC_HEADER.length);
         return new String(decrypted, StandardCharsets.UTF_8);
      } catch (Exception e) {
         System.err.println("Failed to decrypt AES-256-ECB config payload (" + bytes.length + " bytes):");
         e.printStackTrace();
         return "";
      }
   }

   @Override
   public Class getJsonType() {
      return JsonObject.class;
   }

   @Override
   public void deserialize(JsonElement elem) {
      this.deserializeEmpty((JsonObject)elem);
   }

   public void loadConfig(String configName, String subDir) {
      this.loadConfig(configName, subDir, true);
   }

   public void loadConfig(String configName, String subDir, boolean notify) {
      JsonObject json = this.readConfigJson(subDir, configName);
      if (json == null) {
         if (notify) {
            File file = new File(this.getDirectoryPath() + subDir, configName + ".json");
            String message;
            if (!file.exists()) {
               message = "Config not found: §c" + configName;
            } else {
               boolean encrypted = false;
               try {
                  encrypted = hasEncryptedHeader(Files.readAllBytes(file.toPath()));
               } catch (Exception ignored) {
               }
               message = encrypted
                  ? "Failed to decrypt config: §c" + configName
                  : "Failed to load config: §c" + configName;
            }
            this.notifyConfigStatus(message, NotificationLevel.ERROR);
         }
         return;
      }
      this.applyConfigJson(json, configName, notify);
   }

   public JsonObject buildConfigJson(String configName, boolean editable) {
      return this.formatCodecs.get(2).encodeConfig(configName, Augustus.getInstance().getUsername(), editable);
   }

   public JsonObject readConfigJson(String subDir, String configName) {
      File file = new File(this.getDirectoryPath() + subDir, configName + ".json");
      if (!file.exists()) {
         return null;
      }
      return (JsonObject)this.readJsonFile(file);
   }

   @Override
   public boolean isBackupEnabled() {
      return false;
   }

   public void applyConfigJson(JsonObject json, String configName, boolean notify) {
      if (json == null) {
         return;
      }
      Integer version = this.readConfigVersion(json);
      if (version == null) {
         System.err.println("Config missing Config_Version in Config_Header: " + configName);
         if (notify) {
            this.notifyConfigStatus("Invalid config header: §c" + configName, NotificationLevel.ERROR);
         }
         return;
      }
      ModuleConfigCodec codec = this.formatCodecs.get(version);
      if (codec == null) {
         System.err.println("Unsupported Config_Version " + version + " in config: " + configName);
         if (notify) {
            this.notifyConfigStatus("Unsupported config version (" + version + "): §c" + configName, NotificationLevel.ERROR);
         }
         return;
      }
      try {
         long startTime = System.currentTimeMillis();
         codec.applyConfig(json);
         if (notify) {
            long elapsed = System.currentTimeMillis() - startTime;
            this.notifyConfigStatus("Config loaded: §c" + configName + " (" + elapsed + "ms)", NotificationLevel.INFO);
            Augustus.getInstance().getModuleManager().sortModules();
            ClientSounds.playSound(ClientSounds.AUGUSTUS_TEXTURE2);
         }
      } catch (Exception e) {
         e.printStackTrace();
      }
   }

   public void saveConfig(String configName, String subDir, boolean editable) {
      try {
         JsonObject json = this.buildConfigJson(configName, editable);
         File file = new File(this.getDirectoryPath() + subDir, configName + ".json");
         long startTime = System.currentTimeMillis();
         this.writeJsonFile(file, json);
         long elapsed = System.currentTimeMillis() - startTime;
         ClientSounds.playSound(ClientSounds.AUGUSTUS_TEXTURE2);
         this.notifyConfigStatus("Config saved: §c" + configName + " (" + elapsed + "ms)", NotificationLevel.INFO);
      } catch (Exception e) {
         e.printStackTrace();
      }
   }

   public JsonObject serializeEmpty() {
      return null;
   }

   @Override
   public JsonElement serialize() {
      return this.serializeEmpty();
   }

   private void notifyConfigStatus(String message, NotificationLevel level) {
      ChatUtil.sendChat(message);
      if (!Augustus.getInstance().getModuleManager().notifications.isToggled()) {
         return;
      }
      if (!NotificationCategoryOption.CONFIG_SYSTEM.isSelected()) {
         return;
      }
      if (Minecraft.getMinecraft().isCallingFromMinecraftThread()) {
         NotificationManager.pushNotification("Configsystem", message, level);
         return;
      }
      PacketThreadUtil.runOnMainThreadImmediate(() -> showNotification(message, level));
   }

   @Override
   public String getName() {
      return "";
   }

   @Override
   public String getSubDirectory() {
      return "";
   }

   public void deserializeEmpty(JsonObject json) {
   }

   @Override
   public boolean isAutoManaged() {
      return false;
   }

   private static void showNotification(String message, NotificationLevel level) {
      NotificationManager.pushNotification("Configsystem", message, level);
   }

   public String[] getConfigHeaderInfo(String configName, String subDir) {
      JsonObject json = this.readConfigJson(subDir, configName);
      if (json == null) {
         return null;
      }
      Integer version = this.readConfigVersion(json);
      if (version == null) {
         return null;
      }
      ModuleConfigCodec codec = this.formatCodecs.get(version);
      if (codec == null) {
         return null;
      }
      return codec.readHeaderInfo(json);
   }

   private Integer readConfigVersion(JsonObject json) {
      if (json.has("Config_Header") && json.get("Config_Header").isJsonObject()) {
         JsonObject header = json.getAsJsonObject("Config_Header");
         if (header.has("Config_Version")) {
            return header.get("Config_Version").getAsInt();
         }
         if (header.has("ConfigVersion")) {
            return header.get("ConfigVersion").getAsInt();
         }
      }
      return null;
   }
}
