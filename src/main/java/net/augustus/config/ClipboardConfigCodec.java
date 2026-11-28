package net.augustus.config;

import net.augustus.modules.AbstractFeature;
import net.augustus.modules.Categorys;
import net.augustus.modules.Module;
import net.augustus.settings.Setting;
import net.augustus.settings.SettingGroup;
import net.augustus.settings.property.ConfigProperty;
import net.augustus.utils.ChatUtil;
import net.augustus.utils.UnaryFunction;
import net.augustus.utils.audio.ClientSounds;
import com.google.gson.JsonObject;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import net.minecraft.client.gui.GuiScreen;

public class ClipboardConfigCodec {
   private static final FeatureListJsonCodec CODEC = new FeatureListJsonCodec();

   public static boolean importSettingGroup(Module module, SettingGroup group, String payload) {
      String decoded = decodePayload(payload);
      JsonObject json = GsonConstants.GSON.fromJson(decoded, JsonObject.class);
      if (json.has("ModuleOrCategoryName") && json.has("Values") && json.get("Values").isJsonObject()) {
         if (!json.get("ModuleOrCategoryName").getAsString().equals(group.getName())) {
            return false;
         }
         json = json.getAsJsonObject("Values");
      }

      CODEC.setFeatures2(new Module[]{module});
      boolean ok = CODEC.deserializeFromObject(json, true, obj -> true);
      if (ok) {
         ClientSounds.playSound(ClientSounds.AUGUSTUS_TEXTURE2);
         ChatUtil.sendChat("Imported " + group.getDisplayName() + " successfully from clipboard");
      }
      return ok;
   }

   public static boolean importCategory(Categorys category) {
      try {
         String decoded = decodePayload(GuiScreen.getClipboardString());
         JsonObject json = GsonConstants.GSON.fromJson(decoded, JsonObject.class);
         if (json.has("ModuleOrCategoryName") && json.has("Values") && json.get("Values").isJsonObject()) {
            if (!json.get("ModuleOrCategoryName").getAsString().equals(category.name())) {
               return false;
            }
            json = json.getAsJsonObject("Values");
         }

         CODEC.setFeatures(category.getDisplayModules());
         boolean ok = CODEC.deserializeFromObject(json, true, obj -> shouldImportCategoryProperty((ConfigProperty)obj));
         if (ok) {
            ClientSounds.playSound(ClientSounds.AUGUSTUS_TEXTURE2);
            ChatUtil.sendChat("Imported " + category.name() + " successfully from clipboard");
         }
         return ok;
      } catch (Exception e) {
         return false;
      }
   }

   private static String decodePayload(String input) {
      if (input == null) {
         return "";
      }
      byte[] bytes = null;
      if (input.startsWith("Name_")) {
         int underscores = 0;
         for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == '_' && ++underscores >= 2) {
               bytes = Base64.getDecoder().decode(input.substring(i + 1));
               break;
            }
         }
      }
      if (bytes == null) {
         bytes = input.getBytes(StandardCharsets.UTF_8);
      }
      return ModuleConfigStorage.decryptConfigBytes(bytes);
   }

   private static String encodePayload(String name, String json) {
      String b64 = Base64.getEncoder().encodeToString(json.getBytes(StandardCharsets.UTF_8));
      return "Name_" + name + "_" + b64;
   }

   public static boolean exportCategory(Categorys category) {
      CODEC.setFeatures(category.getDisplayModules());
      JsonObject json = new JsonObject();
      String name = category.name();
      json.addProperty("ModuleOrCategoryName", name);
      JsonObject values = CODEC.serializeToObject(obj -> shouldExportCategoryProperty((ConfigProperty)obj));
      if (values == null) {
         return false;
      }
      json.add("Values", values);
      GuiScreen.setClipboardString(encodePayload(name, GsonConstants.GSON.toJson(json)));
      ClientSounds.playSound(ClientSounds.AUGUSTUS_TEXTURE2);
      ChatUtil.sendChat("Exported " + category.name() + " successfully to clipboard");
      return true;
   }

   private static Boolean shouldExportCategoryProperty(ConfigProperty prop) {
      return !(prop.getFeatureBase() instanceof Module)
         || (prop.getName().equals("M_T") && prop.getFeatureBase() instanceof Module)
         || prop instanceof AbstractFeature;
   }

   public static String encodeSettingGroup(Module module, SettingGroup group) {
      CODEC.setFeatures2(new Module[]{module});
      JsonObject json = new JsonObject();
      String name = group.getName();
      json.addProperty("ModuleOrCategoryName", name);
      JsonObject values = CODEC.serializeToObject(obj -> belongsToSettingGroup(group, (ConfigProperty)obj));
      if (values == null) {
         return null;
      }
      json.add("Values", values);
      return encodePayload(name, GsonConstants.GSON.toJson(json));
   }

   public static boolean importSettingGroupFromClipboard(Module module, SettingGroup group) {
      try {
         return importSettingGroup(module, group, GuiScreen.getClipboardString());
      } catch (Exception e) {
         return false;
      }
   }

   private static Boolean belongsToSettingGroup(SettingGroup group, ConfigProperty prop) {
      if (prop.getFeatureBase() instanceof Module && !(prop instanceof AbstractFeature)) {
         return false;
      }
      return prop == group
         || prop.getFeatureBase() == group
         || (prop instanceof Setting && isNestedSetting((Setting)prop, group))
         || (prop.getFeatureBase() instanceof Setting && isNestedSetting((Setting)prop.getFeatureBase(), group));
   }

   private static boolean isNestedSetting(Setting setting, SettingGroup group) {
      for (Setting child : (Iterable<Setting>)group.getChildren()) {
         if (child instanceof SettingGroup && isNestedSetting(setting, (SettingGroup)child)) {
            return true;
         }
         if (setting.getName().equals(child.getName())) {
            return true;
         }
      }
      return false;
   }

   private static Boolean shouldImportCategoryProperty(ConfigProperty prop) {
      return !(prop.getFeatureBase() instanceof Module)
         || (prop.getName().equals("M_T") && prop.getFeatureBase() instanceof Module)
         || prop instanceof AbstractFeature;
   }

   public static boolean exportSettingGroup(Module module, SettingGroup group) {
      String encoded = encodeSettingGroup(module, group);
      if (encoded == null) {
         return false;
      }
      GuiScreen.setClipboardString(encoded);
      ClientSounds.playSound(ClientSounds.AUGUSTUS_TEXTURE2);
      ChatUtil.sendChat("Exported " + group.getDisplayName() + " successfully to clipboard");
      return true;
   }

   public static boolean importModule(Module module) {
      try {
         String decoded = decodePayload(GuiScreen.getClipboardString());
         JsonObject json = GsonConstants.GSON.fromJson(decoded, JsonObject.class);
         if (json.has("ModuleOrCategoryName") && json.has("Values") && json.get("Values").isJsonObject()) {
            if (!json.get("ModuleOrCategoryName").getAsString().equals(module.getName())) {
               return false;
            }
            json = json.getAsJsonObject("Values");
         }

         CODEC.setFeatures2(new Module[]{module});
         boolean ok = CODEC.deserializeFromObject(json, true, obj -> true);
         if (ok) {
            ClientSounds.playSound(ClientSounds.AUGUSTUS_TEXTURE2);
            ChatUtil.sendChat("Imported " + module.getName() + " successfully from clipboard");
         }
         return ok;
      } catch (Exception e) {
         return false;
      }
   }

   public static boolean exportModule(Module module) {
      CODEC.setFeatures2(new Module[]{module});
      JsonObject json = new JsonObject();
      String name = module.getName();
      json.addProperty("ModuleOrCategoryName", name);
      UnaryFunction filter = obj -> !(((ConfigProperty)obj).getFeatureBase() instanceof Module) || obj instanceof AbstractFeature;
      JsonObject values = CODEC.serializeToObject(filter);
      if (values == null) {
         return false;
      }
      json.add("Values", values);
      GuiScreen.setClipboardString(encodePayload(name, GsonConstants.GSON.toJson(json)));
      ClientSounds.playSound(ClientSounds.AUGUSTUS_TEXTURE2);
      ChatUtil.sendChat("Exported " + module.getName() + " successfully to clipboard");
      return true;
   }
}
