package net.augustus.commands.impl;

import net.augustus.Augustus;
import net.augustus.ClientManagers;
import net.augustus.commands.Command;
import net.augustus.config.FeatureListJsonCodec;
import net.augustus.config.GsonConstants;
import net.augustus.settings.property.ConfigProperty;
import net.augustus.utils.UnaryFunction;
import net.augustus.utils.file.ClientFileUtil;
import net.augustus.utils.file.ClientPaths;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import net.augustus.modules.AbstractFeature;
import net.augustus.modules.Module;

public class MotionModifierCommand extends Command {
   private final String configDirectory = ClientPaths.CLIENT_DIR + File.separator + "motionmodifier" + File.separator + "motionmodifier";
   private CompletableFuture loadTask;
   private static final FeatureListJsonCodec CONFIG_CODEC = new FeatureListJsonCodec();

   private void listConfigs() {
      ClientFileUtil.ensureDirectory(this.configDirectory);
      File[] files = new File(this.configDirectory).listFiles(file2 -> {
         return file2 != null && file2.isFile() && file2.getName().endsWith(".json");
      });
      if (files != null && files.length != 0) {
         this.sendChat("Motion modifier scripts:");

         for (File file3 : files) {
            String name = file3.getName();
            this.sendChat(name.substring(0, name.length() - 5));
         }
      } else {
         this.sendChat("No motion modifier scripts found!");
      }
   }

   @Override
   public void helpMessage() {
      this.sendChat("§c" + this.getCommand() + "§7 (Saves, loads, or lists motion modifier configs)");
   }

   private void loadConfigAsync(String[] strings) {
      this.loadConfig(ClientManagers.settingManager.getSettings(), strings[2]);
   }

   @Override
   public void commandAction(String[] args, String rawMessage) {
      if (args.length == 3) {
         if (args[1].equalsIgnoreCase("load")) {
            if (this.loadTask == null || this.loadTask.isDone()) {
               this.loadTask = CompletableFuture.runAsync(() -> this.loadConfigAsync(args));
            }

            return;
         }

         if (args[1].equalsIgnoreCase("save")) {
            if (!Augustus.getInstance().isConfigEditingEnabled()) {
               this.sendChat("You are not allowed to use this command in a protected config. Sorry :/");
               return;
            }

            this.saveConfig(args[2]);
            return;
         }
      } else if (args.length == 2 && args[1].equalsIgnoreCase("list")) {
         this.listConfigs();
         return;
      }

      this.sendUsageMessage();
   }

   public MotionModifierCommand() {
      super("mm");
      this.loadTask = null;
      if (!Files.exists(Paths.get(this.configDirectory))) {
         try {
            Files.createDirectories(Paths.get(this.configDirectory));
         } catch (IOException e) {
            e.printStackTrace();
         }
      }
   }

   public void sendUsageMessage() {
      this.sendChat("§7" + this.getCommand() + " [§csave/load/list§7] [§2fileName§7]");
   }

   public void saveConfig(String str) {
      FeatureListJsonCodec featureListJsonCodec = CONFIG_CODEC;
      featureListJsonCodec.setFeatures2(new Module[]{ClientManagers.moduleManager.motionModifier});
      featureListJsonCodec = CONFIG_CODEC;
      UnaryFunction unaryFunction = obj -> {
         return !(((ConfigProperty)obj).getFeatureBase() instanceof Module) || obj instanceof AbstractFeature;
      };
      JsonObject json = featureListJsonCodec.serializeToObject(unaryFunction);
      if (json == null) {
         this.sendChat("Failed to save the file §c" + str + "§7!");
      } else {
         String str3 = str + ".json";
         Gson gson = GsonConstants.GSON;
         ClientFileUtil.writeJsonObject(this.configDirectory, str3, json, gson);
         this.sendChat("The file §c" + str + "§7 saved successfully!");
      }
   }

   private void loadConfig(List list, String str) {
      if (!Files.exists(Paths.get(this.configDirectory))) {
         try {
            Files.createDirectories(Paths.get(this.configDirectory));
         } catch (IOException e) {
            e.printStackTrace();
         }
      }

      File file = new File(this.configDirectory + "/" + str + ".json");
      if (!file.exists()) {
         this.sendChat("The file §c" + str + "§7 doesn't exist!");
      } else {
         JsonObject json = ClientFileUtil.readJsonObject(this.configDirectory, file.getName(), (Runnable)null);
         if (json != null) {
            CONFIG_CODEC.setFeatures2(new Module[]{ClientManagers.moduleManager.motionModifier});
            FeatureListJsonCodec featureListJsonCodec2 = CONFIG_CODEC;
            UnaryFunction unaryFunction = obj2 -> {
               return !(((ConfigProperty)obj2).getFeatureBase() instanceof Module) || obj2 instanceof AbstractFeature;
            };
            if (!featureListJsonCodec2.deserializeFromObject(json, true, unaryFunction)) {
               this.sendChat("Failed to load motion modifier configuration");
            } else {
               this.sendChat("The file §c" + str + "§7 loaded successfully!");
            }
         }
      }
   }

}
