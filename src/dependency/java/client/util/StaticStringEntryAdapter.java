package client.util;

import client.ClientGlobals;
import client.module.Module;
import client.setting.ModeOptionConfigurable;
import client.setting.Setting;
import client.setting.SettingSerializer;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;

public class StaticStringEntryAdapter extends AuxiliaryStringEntry {
   public static final ModeListTracker modeListTracker = new ModeListTracker();
   public CompletableFuture completableFuture;
   public final String labelText2 = OnlineConfigsManager.text + File.separator + "motionmodifier" + File.separator + "motionmodifier";

   private void applyList(List list, String text) {
      if (!Files.exists(Paths.get(this.labelText2))) {
         try {
            Files.createDirectories(Paths.get(this.labelText2));
         } catch (IOException ioException) {
            ioException.printStackTrace();
         }
      }

      File file = new File(this.labelText2 + "/" + text + ".json");
      if (!file.exists()) {
         String text2 = "The file §c" + text + "§7 doesn't exist!";
         this.applyText(text2);
      } else {
         String text3 = text + ".json";
         JsonArray jsonArray = ConfigManager.getJsonArray(this.labelText2, text3, (Runnable)null);
         if (jsonArray == null) {
            String labelText = this.labelText2;
            String name = file.getName();
            JsonObject jsonObject = ConfigManager.getJsonObject(labelText, name, (Runnable)null);
            if (jsonObject != null) {
               ModeListTracker modeListTracker2 = modeListTracker;
               Module[] moduleArray = new Module[]{ClientGlobals.moduleManager.motionModifier};
               modeListTracker2.processAction(moduleArray);
               ModeListTracker modeListTracker3 = modeListTracker;
               Function mapper = (item -> StaticStringEntryAdapter.resolveBooleanByModeOptionConfig((ModeOptionConfigurable)item));
               boolean flag = true;
               if (!modeListTracker3.checkJsonObject(jsonObject, flag, mapper)) {
                  String text4 = "Failed to load motion modifier configuration";
                  this.applyText(text4);
               } else {
                  String text5 = "The file §c" + text + "§7 loaded successfully!";
                  this.applyText(text5);
               }
            }
         } else {
            jsonArray.forEach(element -> StaticStringEntryAdapter.applyList(list, element));
            String text6 = "The file §c" + text + "§7 loaded successfully!";
            this.applyText(text6);
         }
      }
   }

   private static void applyList(List list, JsonElement jsonElement) {
      JsonObject asJsonObject = jsonElement.getAsJsonObject();
      String asString = asJsonObject.get("ParentName").getAsString();
      String asString2 = asJsonObject.get("Name").getAsString();

      for (Setting setting : (Iterable<Setting>)(Iterable)list) {
         if (setting.getString().equals(asString2) && setting.getTextAsText().equals(asString)) {
            try {
               SettingSerializer advancedItemEntry = SettingSerializer.advancedItemEntry;
               setting.updateSupplementalListener(advancedItemEntry, asJsonObject);
            } catch (Exception exception) {
               System.err.println("Failed to load Setting: " + setting.getString() + " " + setting.getTextAsText());
            }
            break;
         }
      }
   }

   private boolean matchesFile(File file) {
      return file != null && file.isFile() && file.getName().endsWith(".json");
   }

   private void updateState2() {
      ConfigManager.applyString2(this.labelText2);
      File file = new File(this.labelText2);
      File[] fileArray = file.listFiles(this::matchesFile);
      if (fileArray != null && fileArray.length != 0) {
         String text = "Motion modifier scripts:";
         this.applyText(text);
         int length = fileArray.length;

         for (int index = 0; index < length; index++) {
            String name = fileArray[index].getName();
            String text2 = name.substring(0, name.length() - 5);
            this.applyText(text2);
         }
      } else {
         String text3 = "No motion modifier scripts found!";
         this.applyText(text3);
      }
   }

   public void applyTextByText(String text) {
      ModeListTracker modeListTracker2 = modeListTracker;
      Module[] moduleArray = new Module[]{ClientGlobals.moduleManager.motionModifier};
      modeListTracker2.processAction(moduleArray);
      ModeListTracker modeListTracker3 = modeListTracker;
      Function mapper = (item -> StaticStringEntryAdapter.resolveBoolean((ModeOptionConfigurable)item));
      Function mapper2 = mapper;
      JsonObject jsonObject = modeListTracker3.resolveJsonObject(mapper2);
      if (jsonObject == null) {
         String text2 = "Failed to save the file §c" + text + "§7!";
         this.applyText(text2);
      } else {
         String text3 = text + ".json";
         Gson gson = GsonCallback.gson;
         String text4 = text3;
         ConfigManager.applyText(this.labelText2, text4, jsonObject, gson);
         String text5 = "The file §c" + text + "§7 saved successfully!";
         this.applyText(text5);
      }
   }

   @Override
   public void updateState() {
      String text = "§c" + this.getString() + "§7 (Saves / loads / list motion modifier configs)";
      this.applyText(text);
   }

   private static Boolean resolveBooleanByModeOptionConfig(ModeOptionConfigurable modeOptionConfigurable) {
      return !(modeOptionConfigurable.getModuleModeOption() instanceof Module) || modeOptionConfigurable instanceof ListenerTracker;
   }

   public void updateState3() {
      String text = "§7" + this.getString() + " [§csave/load/list§7] [§2fileName§7]";
      this.applyText(text);
   }

   private void applyTextArray(String[] textArray) {
      List list = ClientGlobals.auxiliaryCopyOnWriteArrayListEntry.getList();
      String text = textArray[2];
      List list2 = list;
      this.applyList(list2, text);
   }

   private static Boolean resolveBoolean(ModeOptionConfigurable modeOptionConfigurable) {
      return !(modeOptionConfigurable.getModuleModeOption() instanceof Module) || modeOptionConfigurable instanceof ListenerTracker;
   }

   @Override
   public void printStackTrace(String[] textArray, String text) {
      if (textArray.length == 3) {
         if (textArray[1].equalsIgnoreCase("load")) {
            if (this.completableFuture == null || this.completableFuture.isDone()) {
               this.completableFuture = CompletableFuture.runAsync(() -> this.applyTextArray(textArray));
            }

            return;
         }

         if (textArray[1].equalsIgnoreCase("save")) {
            String text2 = textArray[2];
            this.applyTextByText(text2);
            return;
         }
      } else if (textArray.length == 2 && textArray[1].equalsIgnoreCase("list")) {
         this.updateState2();
         return;
      }

      this.updateState3();
   }

   public StaticStringEntryAdapter() {
      super("mm");
      this.completableFuture = null;
      if (!Files.exists(Paths.get(this.labelText2))) {
         try {
            Files.createDirectories(Paths.get(this.labelText2));
         } catch (IOException ioException) {
            ioException.printStackTrace();
         }
      }
   }
}
