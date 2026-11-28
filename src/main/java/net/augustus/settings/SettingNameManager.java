package net.augustus.settings;

import net.augustus.Augustus;
import net.augustus.ClientManagers;
import net.augustus.utils.file.ClientFileUtil;
import net.augustus.utils.file.ClientPaths;
import net.augustus.utils.file.DirectoryWatchService;
import net.augustus.utils.file.DirectoryWatcher;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Locale;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public class SettingNameManager {
   public static String SETTINGS_NAMES_DIR = ClientPaths.CLIENT_DIR + File.separator + "settings" + File.separator + "settingsnames" + File.separator;

   public void initialize() {
      String str = Augustus.getInstance().getName().toLowerCase() + "/settings/settingsnames";
      String str2 = str;
      ClientFileUtil.extractResource("assets/augustus/English_New.txt", str2, true);
      this.loadLanguageFile("English_New");
      this.exportDefaultNames();
      StringValue settingNames = Augustus.getInstance().getModuleManager().clickGui.settingNames;
      settingNames.setOptions(Augustus.getInstance().getSettingNameManager().listLanguageFiles());
      SettingNameManager settingNameManager = Augustus.getInstance().getSettingNameManager();
      str = settingNames.getSelectedValue();
      settingNames.setSelectedValue(settingNameManager.resolveValidLanguage(str, settingNames.getOptions()));
      DirectoryWatcher directoryWatcher = DirectoryWatchService.watch(SETTINGS_NAMES_DIR);
      directoryWatcher = directoryWatcher.onCreate(obj -> onNameFileCreated(settingNames, (Path)obj));
      directoryWatcher = directoryWatcher.onModify(obj2 -> onNameFileModified(settingNames, (Path)obj2));
      directoryWatcher.onDelete(obj3 -> onNameFileDeleted(settingNames, (Path)obj3)).register();
   }

   private static void onNameFileDeleted(StringValue booleansSetting, Path path) {
      booleansSetting.setOptions(Augustus.getInstance().getSettingNameManager().listLanguageFiles());
      booleansSetting.setSelectedValue(Augustus.getInstance().getSettingNameManager().resolveValidLanguage(booleansSetting.getSelectedValue(), booleansSetting.getOptions()));
   }

   private static void onNameFileModified(StringValue booleansSetting, Path path) {
      String name = path.toFile().getName();
      if (name.substring(0, name.lastIndexOf(46)).equals(booleansSetting.getSelectedValue())) {
         CompletableFuture.runAsync(() -> {
            Augustus.getInstance().getSettingNameManager().loadLanguageFile(booleansSetting.getSelectedValue());
         });
      }
   }

   public void exportDefaultNames() {
      ClientFileUtil.ensureDirectory(SETTINGS_NAMES_DIR);
      String str = Augustus.getInstance().getName().toLowerCase() + "/settings/settingsnames";
      ClientFileUtil.extractResource("assets/augustus/English_New.txt", str, false);

      try (BufferedWriter writer = new BufferedWriter(new FileWriter(SETTINGS_NAMES_DIR + "English_New.txt"))) {
         for (Setting setting : (Iterable<Setting>)(ClientManagers.settingManager.getSettings())) {
            if (!setting.getParentModule().equals(ClientManagers.moduleManager.motionModifier) && !setting.getParentModule().equals(ClientManagers.moduleManager.clientAntiCheat)) {

               for (String str3 : setting.getTranslationKeys()) {
                  String str4 = setting.getParentModule().getCategorys().name().toLowerCase(Locale.ROOT)
                     + "."
                     + setting.getParentModuleName().toLowerCase(Locale.ROOT)
                     + "."
                     + str3.toLowerCase(Locale.ROOT);
                  String str5 = setting.getTranslatedName() + ";";
                  writer.write(str4 + "::" + str5);
                  writer.newLine();
               }
            }
         }
      } catch (IOException e) {
         e.printStackTrace();
      }
   }

   public synchronized void loadLanguageFile(String str) {
      if (str.equals("English_Default")) {
         for (Setting setting : (Iterable<Setting>)(ClientManagers.settingManager.getSettings())) {
            setting.applyTranslation("", str);
         }
      } else {
         String str3 = SETTINGS_NAMES_DIR + str + ".txt";
         ClientFileUtil.ensureDirectory(SETTINGS_NAMES_DIR);
         if (!new File(str3).exists()) {
            this.exportDefaultNames();
         }

         try {
            BufferedReader reader = new BufferedReader(new FileReader(str3));

            String str4;
            while ((str4 = reader.readLine()) != null) {
               String[] parts = str4.split("::");
               if (parts.length > 1) {
                  String[] strings2 = parts[0].split("\\.");
                  if (strings2.length > 1) {
                     for (Setting setting2 : (Iterable<Setting>)(ClientManagers.settingManager.getSettings())) {
                        if (strings2[0].equalsIgnoreCase(setting2.getParentModule().getCategorys().name()) && strings2[1].equalsIgnoreCase(setting2.getParentModule().getName())) {
                           boolean flag = false;

                           for (String str5 : setting2.getTranslationKeys()) {
                              if (strings2[2].equalsIgnoreCase(str5)) {
                                 setting2.applyTranslation(strings2[2], this.joinTranslationValue(parts));
                                 flag = true;
                                 break;
                              }
                           }

                           if (flag) {
                              break;
                           }
                        }
                     }
                  }
               }
            }

            reader.close();
         } catch (IOException e) {
            e.printStackTrace();
         }
      }
   }

   private static void onNameFileCreated(StringValue booleansSetting, Path path) {
      booleansSetting.setOptions(Augustus.getInstance().getSettingNameManager().listLanguageFiles());
      booleansSetting.setSelectedValue(Augustus.getInstance().getSettingNameManager().resolveValidLanguage(booleansSetting.getSelectedValue(), booleansSetting.getOptions()));
   }

   private String joinTranslationValue(String[] strings) {
      StringBuilder sb = new StringBuilder();

      for (int i = 1; i < strings.length; i++) {
         sb.append(strings[i]);
         if (i > 1 && i < strings.length - 1) {
            sb.append("::");
         }
      }

      return sb.deleteCharAt(sb.length() - 1).toString();
   }

   public String[] listLanguageFiles() {
      ArrayList list = new ArrayList();
      File file = new File(SETTINGS_NAMES_DIR);
      ClientFileUtil.ensureDirectory(SETTINGS_NAMES_DIR);
      File[] files = file.listFiles();
      list.add("English_Default");
      if (files != null) {
         for (File file2 : files) {
            String name = file2.getName();
            if (name.endsWith(".txt")) {
               list.add(name.substring(0, Math.max(0, name.length() - 4)));
            }
         }
      }

      return (String[])list.toArray(new String[0]);
   }

   public String resolveValidLanguage(String str, String[] strings) {
      if (strings.length < 1) {
         return "";
      } else {
         for (String str2 : strings) {
            if (str2.equals(str)) {
               return str;
            }
         }

         return strings[0];
      }
   }

}
