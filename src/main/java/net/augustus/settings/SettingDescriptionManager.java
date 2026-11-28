package net.augustus.settings;

import net.augustus.Augustus;
import net.augustus.config.DescriptionYamlManager;
import net.augustus.utils.file.ClientFileUtil;
import net.augustus.utils.file.ClientPaths;
import net.augustus.utils.file.DirectoryWatchService;
import net.augustus.utils.file.DirectoryWatcher;
import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public class SettingDescriptionManager {
   public static final String DESCRIPTIONS_DIR = ClientPaths.CLIENT_DIR + File.separator + "settings" + File.separator + "descriptions" + File.separator;
   private final DescriptionYamlManager descriptionYamlManager = new DescriptionYamlManager();

   public DescriptionYamlManager getDescriptionYamlManager() {
      return this.descriptionYamlManager;
   }

   private static void reloadSelectedDescriptionFile(StringValue booleansSetting) {
      Augustus.getInstance().getSettingDescriptionManager().loadDescriptions(booleansSetting.getSelectedValue());
   }

   public void writePresetDescriptions() {
      ClientFileUtil.ensureDirectory(DESCRIPTIONS_DIR);
      String str = DESCRIPTIONS_DIR + "Preset.yml";
      File file = new File(str);
      if (file.exists()) {
         System.out.println(file.delete());
      }

      try {
         this.descriptionYamlManager.saveToFile(str);
      } catch (Exception e) {
         System.err.println("Failed to save descriptions to: " + str);
         e.printStackTrace();
      }
   }

   private static void onDescriptionFileDeleted(StringValue booleansSetting, Path path) {
      booleansSetting.setOptions(Augustus.getInstance().getSettingDescriptionManager().listDescriptionFiles());
      booleansSetting.setSelectedValue(Augustus.getInstance().getSettingDescriptionManager().resolveValidOption(booleansSetting.getSelectedValue(), booleansSetting.getOptions()));
   }

   public synchronized void loadDescriptions(String str) {
      String str2 = DESCRIPTIONS_DIR + str + ".yml";
      ClientFileUtil.ensureDirectory(DESCRIPTIONS_DIR);

      try {
         this.descriptionYamlManager.loadAndApply(str2);
      } catch (Exception e) {
         System.err.println("Failed to read descriptions from: " + str2);
         e.printStackTrace();
      }
   }

   public String[] listDescriptionFiles() {
      ArrayList list = new ArrayList();
      File file = new File(DESCRIPTIONS_DIR);
      ClientFileUtil.ensureDirectory(DESCRIPTIONS_DIR);
      File[] files = file.listFiles();
      if (files != null) {
         for (File file2 : files) {
            String name = file2.getName();
            if (name.endsWith(".yml")) {
               if (!name.equals("Preset.yml")) {
                  list.add(name.substring(0, Math.max(0, name.length() - 4)));
               }
            }
         }
      }

      return (String[])list.toArray(new String[0]);
   }

   public String resolveValidOption(String str, String[] strings) {
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

   public void initialize() {
      String str = Augustus.getInstance().getName().toLowerCase() + "/settings/descriptions";
      String str2 = str;
      ClientFileUtil.extractResource("assets/augustus/Default.yml", str2, true);
      this.loadDescriptions("Default");
      this.writePresetDescriptions();
      StringValue descriptions = Augustus.getInstance().getModuleManager().clickGui.descriptions;
      descriptions.setOptions(Augustus.getInstance().getSettingDescriptionManager().listDescriptionFiles());
      SettingDescriptionManager manager = Augustus.getInstance().getSettingDescriptionManager();
      str = descriptions.getSelectedValue();
      descriptions.setSelectedValue(manager.resolveValidOption(str, descriptions.getOptions()));
      DirectoryWatcher directoryWatcher = DirectoryWatchService.watch(DESCRIPTIONS_DIR);
      Consumer consumer = obj -> {
         descriptions.setOptions(Augustus.getInstance().getSettingDescriptionManager().listDescriptionFiles());
         descriptions.setSelectedValue(Augustus.getInstance().getSettingDescriptionManager().resolveValidOption(descriptions.getSelectedValue(), descriptions.getOptions()));
      };
      directoryWatcher = directoryWatcher.onCreate(consumer);
      Consumer consumer2 = obj2 -> {
         String name = ((Path)obj2).toFile().getName();
         if (name.substring(0, name.lastIndexOf(46)).equals(descriptions.getSelectedValue())) {
            CompletableFuture.runAsync(() -> reloadSelectedDescriptionFile(descriptions));
         }
      };
      directoryWatcher = directoryWatcher.onModify(consumer2);
      directoryWatcher.onDelete(obj3 -> onDescriptionFileDeleted(descriptions, (Path)obj3)).register();
   }

}
