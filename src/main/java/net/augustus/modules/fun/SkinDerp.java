package net.augustus.modules.fun;

import java.awt.Color;
import java.io.File;
import java.util.concurrent.CompletableFuture;
import java.util.function.BiFunction;
import javax.swing.JFileChooser;
import net.augustus.modules.Categorys;
import net.augustus.modules.Module;
import net.augustus.modules.fun.skinderp.PngSkinFileFilter;
import net.augustus.utils.file.ClientFileUtil;
import net.augustus.utils.file.ClientPaths;
import net.augustus.settings.ModeSetting;
import net.augustus.settings.ActionSetting;
import net.augustus.modules.fun.skinderp.MinecraftSkinUploader;
import net.augustus.modules.modes.skinderp.SequentialSkinDerpMode;
import net.augustus.modules.modes.skinderp.DiscoSkinDerpMode;
import net.augustus.modules.modes.skinderp.RandomSkinDerpMode;
import net.augustus.modules.modes.skinderp.GlitchSkinDerpMode;
import net.augustus.modules.modes.skinderp.WaveSkinDerpMode;
import net.augustus.modules.modes.skinderp.NormalSkinDerpMode;
import net.augustus.Augustus;
import net.augustus.events.listeners.TickListener;
import net.augustus.settings.BooleanValue;
import net.augustus.settings.StringValue;
import net.augustus.settings.SettingGroup;
import net.augustus.settings.DoubleValue;
import net.minecraft.entity.player.EnumPlayerModelParts;

public class SkinDerp extends Module implements TickListener {
   private final String skinDirectoryPath;
   public final ModeSetting mode;
   private final DoubleValue delay;
   private final BooleanValue showWarnings;
   private final BooleanValue blinkCape;
   private final BooleanValue blinkJacket;
   private final BooleanValue blinkLeftSleeve;
   private final BooleanValue blinkRightSleeve;
   private final BooleanValue blinkLeftPants;
   private final BooleanValue blinkRightPants;
   private final BooleanValue blinkHat;
   private final SettingGroup skinchange;
   private final StringValue skinModel;
   private final ActionSetting selectAndUploadSkin;
   private CompletableFuture uploadDialogFuture;

   public SkinDerp() {
       super("SkinDerp", "Skin Derp", new Color(255, 182, 193), Categorys.FUN);
      this.skinDirectoryPath = ClientPaths.CLIENT_DIR + File.separator + "skinderp";
      BiFunction[] biFunctions = new BiFunction[]{
         NormalSkinDerpMode.factory(),
         RandomSkinDerpMode.factory(),
         WaveSkinDerpMode.factory(),
         GlitchSkinDerpMode.factory(),
         DiscoSkinDerpMode.factory(),
         SequentialSkinDerpMode.factory()
      };
      this.mode = new ModeSetting(this, "Mode", "Normal", biFunctions);
      this.delay = new DoubleValue(this, "Delay", 150.0, 0.0, 5000.0, 0);
      this.showWarnings = new BooleanValue(this, "ShowWarnings", true);
      this.blinkCape = new BooleanValue(this, "BlinkCape", true);
      this.blinkJacket = new BooleanValue(this, "BlinkJacket", true);
      this.blinkLeftSleeve = new BooleanValue(this, "BlinkLeftSleeve", true);
      this.blinkRightSleeve = new BooleanValue(this, "BlinkRightSleeve", true);
      this.blinkLeftPants = new BooleanValue(this, "BlinkLeftPants", true);
      this.blinkRightPants = new BooleanValue(this, "BlinkRightPants", true);
      this.blinkHat = new BooleanValue(this, "BlinkHat", true);
      this.skinchange = new SettingGroup(this, "Skinchange", true);
      this.skinModel = new StringValue(this, "SkinModel", "Classic", new String[]{"Classic", "Slim"}, this.skinchange);
      this.selectAndUploadSkin = new ActionSetting(this, "Select and upload skin", () -> this.openSkinUploadDialogAsync(), this.skinchange);
      this.uploadDialogFuture = null;
      this.setDescription("Automatically switches skins or blinks skin layers");
      ClientFileUtil.ensureDirectory(this.skinDirectoryPath);
      String str2 = Augustus.getInstance().getName().toLowerCase() + File.separator + "skinderp";
      ClientFileUtil.extractResource("assets/augustus/skinderp/Default.png", str2, true);
   }

   @Override
   public void onEnable() {
      if (mc.thePlayer != null) {
         if (this.showWarnings.getValue() && this.delay.getValue() < 200.0) {
            this.sendChatMessage("§eWarning: Fast blinking may cause discomfort or trigger seizures!");
         }
      }
   }

   @Override
   public void onDisable() {
      if (mc.thePlayer != null) {
         this.enableAllModelParts();
      }
   }

   @Override
   public void updateEventSubscriptions() {
      this.toggleEventSubscription(40);
   }

   @Override
   public void onPreTick() {
      this.setSuffix(this.mode.getSelectedValue());
   }

   private void enableAllModelParts() {
      mc.gameSettings.setModelPartEnabled(EnumPlayerModelParts.CAPE, true);
      mc.gameSettings.setModelPartEnabled(EnumPlayerModelParts.JACKET, true);
      mc.gameSettings.setModelPartEnabled(EnumPlayerModelParts.LEFT_SLEEVE, true);
      mc.gameSettings.setModelPartEnabled(EnumPlayerModelParts.RIGHT_SLEEVE, true);
      mc.gameSettings.setModelPartEnabled(EnumPlayerModelParts.LEFT_PANTS_LEG, true);
      mc.gameSettings.setModelPartEnabled(EnumPlayerModelParts.RIGHT_PANTS_LEG, true);
      mc.gameSettings.setModelPartEnabled(EnumPlayerModelParts.HAT, true);
   }

   public BooleanValue getBlinkCape() {
      return this.blinkCape;
   }

   public BooleanValue getBlinkJacket() {
      return this.blinkJacket;
   }

   public BooleanValue getBlinkLeftSleeve() {
      return this.blinkLeftSleeve;
   }

   public BooleanValue getBlinkRightSleeve() {
      return this.blinkRightSleeve;
   }

   public BooleanValue getBlinkLeftPants() {
      return this.blinkLeftPants;
   }

   public BooleanValue getBlinkRightPants() {
      return this.blinkRightPants;
   }

   public BooleanValue getBlinkHat() {
      return this.blinkHat;
   }

   public DoubleValue getDelay() {
      return this.delay;
   }

   private void openSkinUploadDialogAsync() {
      if (this.uploadDialogFuture == null || this.uploadDialogFuture.isDone()) {
         this.uploadDialogFuture = CompletableFuture.runAsync(() -> this.runSkinUploadDialog());
      }
   }

   private void runSkinUploadDialog() {

      try {
         JFileChooser jFileChooser = new JFileChooser();
         jFileChooser.setDialogTitle("Select Skin File");
         jFileChooser.setFileFilter(new PngSkinFileFilter(this));
         File file = new File(this.skinDirectoryPath);
         if (file.exists() && file.isDirectory()) {
            jFileChooser.setCurrentDirectory(file);
         } else {
            File file2 = new File("skins");
            if (file2.exists() && file2.isDirectory()) {
               jFileChooser.setCurrentDirectory(file2);
            }
         }

         if (jFileChooser.showOpenDialog(null) == 0) {
            File selectedFile = jFileChooser.getSelectedFile();
            this.sendChatMessage("§eUploading skin: " + selectedFile.getName() + "...");
            if (MinecraftSkinUploader.uploadSkin(selectedFile, this.skinModel.getSelectedValue().toLowerCase())) {
               this.sendChatMessage("§aSkin uploaded successfully to the mojang servers!");
               this.sendChatMessage("§aIt may take a few seconds to appear.");
               int remainingUploads = MinecraftSkinUploader.getRemainingUploads();
               this.sendChatMessage("§7Remaining uploads this minute: " + remainingUploads);
            } else {
               this.sendChatMessage("§cFailed to upload skin! Check console for details.");
               if (MinecraftSkinUploader.getRemainingUploads() == 0) {
                  long rateLimitResetMs = MinecraftSkinUploader.getRateLimitResetMs();
                  this.sendChatMessage("§cRate limit reached! Wait " + rateLimitResetMs / 1000L + " seconds.");
               }
            }
         } else {
            this.sendChatMessage("§eSkin upload cancelled.");
         }
      } catch (Exception e) {
         System.err.println("[SkinDerp] Error in skin upload dialog: " + e.getMessage());
         e.printStackTrace();
         this.sendChatMessage("§cError opening file dialog: " + e.getMessage());
      }
   }

}
