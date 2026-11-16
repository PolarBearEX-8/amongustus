package client.setting;

import client.ClientGlobals;
import client.module.Category;
import client.module.Module;
import client.util.GsonCallback;
import client.util.ListenerTracker;
import client.util.ModeListTracker;
import client.util.RayTraceUtil;
import client.util.SoundPlayer;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import net.minecraft.client.gui.GuiScreen;

public class ModuleOrCategoryNaHelper {
   public static final ModeListTracker modeListTracker = new ModeListTracker();

   public static boolean matchesModule(short shortValue, Module module, char character, int count, SettingGroup settingGroup) {
      try {
         String text = GuiScreen.getText();
         JsonObject jsonObject = (JsonObject)GsonCallback.gson.fromJson(text, JsonObject.class);
         if (jsonObject.has("ModuleOrCategoryName") && jsonObject.has("Values") && jsonObject.get("Values").isJsonObject()) {
            if (!jsonObject.get("ModuleOrCategoryName").getAsString().equals(settingGroup.getString())) {
               return false;
            }

            jsonObject = jsonObject.getAsJsonObject("Values");
         }

         ModeListTracker modeListTracker2 = modeListTracker;
         Module[] moduleArray = new Module[]{module};
         modeListTracker2.processAction(moduleArray);
         modeListTracker2 = modeListTracker;
         Function mapper = (item -> ModuleOrCategoryNaHelper.resolveBoolean4((ModeOptionConfigurable)item));
         boolean flag = true;
         boolean flag2 = modeListTracker2.checkJsonObject(jsonObject, flag, mapper);
         if (flag2) {
            SoundPlayer.processAction(SoundPlayer.resourceLocation7);
            RayTraceUtil.applyString("Imported " + settingGroup.getText4() + " successfully from clipboard");
         }

         return flag2;
      } catch (Exception exception) {
         return false;
      }
   }

   public static boolean matchesCategory(Category category) {
      try {
         String text = GuiScreen.getText();
         JsonArray jsonArray = (JsonArray)GsonCallback.gson.fromJson(text, JsonArray.class);
         AtomicInteger atomicInteger = new AtomicInteger();
         jsonArray.forEach(element -> ModuleOrCategoryNaHelper.applyCategory(category, atomicInteger, element));
         SoundPlayer.processAction(SoundPlayer.resourceLocation7);
         RayTraceUtil.applyString("Loaded " + atomicInteger.get() + " " + category.name() + " settings successfully.");
         return true;
      } catch (Exception exception) {
         return false;
      }
   }

   private static Boolean resolveBoolean(SettingGroup settingGroup, ModeOptionConfigurable modeOptionConfigurable) {
      return modeOptionConfigurable.getModuleModeOption() instanceof Module && !(modeOptionConfigurable instanceof ListenerTracker)
         ? false
         : modeOptionConfigurable == settingGroup
            || modeOptionConfigurable.getModuleModeOption() == settingGroup
            || modeOptionConfigurable instanceof Setting && matchesSetting((Setting)modeOptionConfigurable, settingGroup)
            || modeOptionConfigurable.getModuleModeOption() instanceof Setting && matchesSetting((Setting)modeOptionConfigurable.getModuleModeOption(), settingGroup);
   }

   private static Boolean resolveBooleanByModeOptionConfig(ModeOptionConfigurable modeOptionConfigurable) {
      return !(modeOptionConfigurable.getModuleModeOption() instanceof Module)
         || modeOptionConfigurable.getString().equals("M_T") && modeOptionConfigurable.getModuleModeOption() instanceof Module
         || modeOptionConfigurable instanceof ListenerTracker;
   }

   public static boolean matchesCategory2(Category category) {
      try {
         String text = GuiScreen.getText();
         JsonObject jsonObject = (JsonObject)GsonCallback.gson.fromJson(text, JsonObject.class);
         if (jsonObject.has("ModuleOrCategoryName") && jsonObject.has("Values") && jsonObject.get("Values").isJsonObject()) {
            if (!jsonObject.get("ModuleOrCategoryName").getAsString().equals(category.name())) {
               return false;
            }

            jsonObject = jsonObject.getAsJsonObject("Values");
         }

         ModeListTracker modeListTracker2 = modeListTracker;
         List subModuleList = category.getSubModuleList();
         modeListTracker2.setList(subModuleList);
         modeListTracker2 = modeListTracker;
         Function mapper = (item -> ModuleOrCategoryNaHelper.resolveBooleanByModeOptionConfig((ModeOptionConfigurable)item));
         boolean flag = true;
         boolean flag2 = modeListTracker2.checkJsonObject(jsonObject, flag, mapper);
         if (flag2) {
            SoundPlayer.processAction(SoundPlayer.resourceLocation7);
            RayTraceUtil.applyString("Imported " + category.name() + " successfully from clipboard");
         }

         return flag2;
      } catch (Exception exception) {
         return false;
      }
   }

   private static boolean matchesSetting(Setting setting, SettingGroup settingGroup) {
      for (Setting setting2 : (Iterable<Setting>)(Iterable<?>)settingGroup.getItemList3()) {
         if (setting2 instanceof SettingGroup) {
            SettingGroup settingGroup2 = (SettingGroup)setting2;
            if (matchesSetting(setting, settingGroup2)) {
               return true;
            }
         }

         if (setting.getString().equals(setting2.getString())) {
            return true;
         }
      }

      return false;
   }

   private static Boolean resolveBoolean(ModeOptionConfigurable modeOptionConfigurable) {
      return !(modeOptionConfigurable.getModuleModeOption() instanceof Module) || modeOptionConfigurable instanceof ListenerTracker;
   }

   private static Boolean resolveBoolean2(ModeOptionConfigurable modeOptionConfigurable) {
      return !(modeOptionConfigurable.getModuleModeOption() instanceof Module)
         || modeOptionConfigurable.getString().equals("M_T") && modeOptionConfigurable.getModuleModeOption() instanceof Module
         || modeOptionConfigurable instanceof ListenerTracker;
   }

   private static Boolean resolveBoolean4(ModeOptionConfigurable modeOptionConfigurable) {
      return true;
   }

   public static boolean matchesModule(Module module) {
      try {
         String text = GuiScreen.getText();
         JsonObject jsonObject = (JsonObject)GsonCallback.gson.fromJson(text, JsonObject.class);
         if (jsonObject.has("ModuleOrCategoryName") && jsonObject.has("Values") && jsonObject.get("Values").isJsonObject()) {
            if (!jsonObject.get("ModuleOrCategoryName").getAsString().equals(module.getString())) {
               return false;
            }

            jsonObject = jsonObject.getAsJsonObject("Values");
         }

         ModeListTracker modeListTracker2 = modeListTracker;
         Module[] moduleArray = new Module[]{module};
         modeListTracker2.processAction(moduleArray);
         modeListTracker2 = modeListTracker;
         Function mapper = (item -> ModuleOrCategoryNaHelper.resolveBoolean3((ModeOptionConfigurable)item));
         boolean flag = true;
         boolean flag2 = modeListTracker2.checkJsonObject(jsonObject, flag, mapper);
         if (flag2) {
            SoundPlayer.processAction(SoundPlayer.resourceLocation7);
            RayTraceUtil.applyString("Imported " + module.getString() + " successfully from clipboard");
         }

         return flag2;
      } catch (Exception exception) {
         return false;
      }
   }

   private static Boolean resolveBoolean3(ModeOptionConfigurable modeOptionConfigurable) {
      return true;
   }

   private static void applyCategory(Category category, AtomicInteger atomicInteger, JsonElement jsonElement) {
      JsonObject asJsonObject = jsonElement.getAsJsonObject();
      String asString = asJsonObject.get("ParentName").getAsString();
      String asString2 = asJsonObject.get("Name").getAsString();

      for (Setting setting : (Iterable<Setting>)(Iterable<?>)ClientGlobals.auxiliaryCopyOnWriteArrayListEntry.getList()) {
         if (!(setting instanceof SettingGroup)
            && setting.getModule().getCategory().equals(category)
            && setting.getString().equals(asString2)
            && setting.getTextAsText().equals(asString)) {
            try {
               SettingSerializer advancedItemEntry = SettingSerializer.advancedItemEntry;
               setting.updateSupplementalListener(advancedItemEntry, asJsonObject);
            } catch (Exception exception) {
               System.err.println("Failed to load Setting from Clipboard: " + setting.getString() + " " + setting.getTextAsText());
            }

            atomicInteger.getAndIncrement();
         }
      }
   }

   public static boolean matchesCategoryByCategory(Category category) {
      ModeListTracker modeListTracker2 = modeListTracker;
      List subModuleList = category.getSubModuleList();
      modeListTracker2.setList(subModuleList);
      JsonObject jsonObject = new JsonObject();
      jsonObject.addProperty("ModuleOrCategoryName", category.name());
      modeListTracker2 = modeListTracker;
      Function mapper = (item -> ModuleOrCategoryNaHelper.resolveBoolean2((ModeOptionConfigurable)item));
      Function mapper2 = mapper;
      JsonObject jsonObject2 = modeListTracker2.resolveJsonObject(mapper2);
      if (jsonObject2 == null) {
         return false;
      } else {
         jsonObject.add("Values", jsonObject2);
         GuiScreen.applyText4(GsonCallback.gson.toJson(jsonObject));
         SoundPlayer.processAction(SoundPlayer.resourceLocation7);
         RayTraceUtil.applyString("Exported " + category.name() + " successfully to clipboard");
         return true;
      }
   }

   public static boolean matchesModule(Module module, SettingGroup settingGroup) {
      ModeListTracker modeListTracker2 = modeListTracker;
      Module[] moduleArray = new Module[]{module};
      modeListTracker2.processAction(moduleArray);
      JsonObject jsonObject = new JsonObject();
      jsonObject.addProperty("ModuleOrCategoryName", settingGroup.getString());
      modeListTracker2 = modeListTracker;
      Function mapper = (item -> ModuleOrCategoryNaHelper.resolveBoolean((ModeOptionConfigurable)item));
      Function mapper2 = mapper;
      JsonObject jsonObject2 = modeListTracker2.resolveJsonObject(mapper2);
      if (jsonObject2 == null) {
         return false;
      } else {
         jsonObject.add("Values", jsonObject2);
         GuiScreen.applyText4(GsonCallback.gson.toJson(jsonObject));
         SoundPlayer.processAction(SoundPlayer.resourceLocation7);
         RayTraceUtil.applyString("Exported " + settingGroup.getText4() + " successfully to clipboard");
         return true;
      }
   }

   private static void applyModule(Module module, AtomicInteger atomicInteger, JsonElement jsonElement) {
      JsonObject asJsonObject = jsonElement.getAsJsonObject();
      String asString = asJsonObject.get("ParentName").getAsString();
      String asString2 = asJsonObject.get("Name").getAsString();

      for (Setting setting : (Iterable<Setting>)(Iterable<?>)ClientGlobals.auxiliaryCopyOnWriteArrayListEntry.getList()) {
         if (!(setting instanceof SettingGroup)
            && setting.getTextAsText().equals(module.getDescription())
            && setting.getString().equals(asString2)
            && setting.getTextAsText().equals(asString)) {
            try {
               SettingSerializer advancedItemEntry = SettingSerializer.advancedItemEntry;
               setting.updateSupplementalListener(advancedItemEntry, asJsonObject);
            } catch (Exception exception) {
               System.err.println("Failed to load Setting from Clipboard: " + setting.getString() + " " + setting.getTextAsText());
            }

            atomicInteger.getAndIncrement();
            break;
         }
      }
   }

   public static boolean matchesModule2(Module module) {
      ModeListTracker modeListTracker2 = modeListTracker;
      Module[] moduleArray = new Module[]{module};
      modeListTracker2.processAction(moduleArray);
      JsonObject jsonObject = new JsonObject();
      jsonObject.addProperty("ModuleOrCategoryName", module.getString());
      modeListTracker2 = modeListTracker;
      Function mapper = (item -> ModuleOrCategoryNaHelper.resolveBoolean((ModeOptionConfigurable)item));
      Function mapper2 = mapper;
      JsonObject jsonObject2 = modeListTracker2.resolveJsonObject(mapper2);
      if (jsonObject2 == null) {
         return false;
      } else {
         jsonObject.add("Values", jsonObject2);
         GuiScreen.applyText4(GsonCallback.gson.toJson(jsonObject));
         SoundPlayer.processAction(SoundPlayer.resourceLocation7);
         RayTraceUtil.applyString("Exported " + module.getString() + " successfully to clipboard");
         return true;
      }
   }

   public static boolean matchesModuleByModule(Module module) {
      try {
         String text = GuiScreen.getText();
         JsonArray jsonArray = (JsonArray)GsonCallback.gson.fromJson(text, JsonArray.class);
         AtomicInteger atomicInteger = new AtomicInteger();
         jsonArray.forEach(element -> ModuleOrCategoryNaHelper.applyModule(module, atomicInteger, element));
         SoundPlayer.processAction(SoundPlayer.resourceLocation7);
         RayTraceUtil.applyString("Loaded " + atomicInteger.get() + " " + module.getString() + " settings successfully.");
         return true;
      } catch (Exception exception) {
         return false;
      }
   }
}
