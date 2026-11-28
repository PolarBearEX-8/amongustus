package net.augustus.modules.render;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;
import javax.imageio.ImageIO;
import net.augustus.modules.Categorys;
import net.augustus.modules.Module;
import net.augustus.utils.file.DirectoryWatchService;
import net.augustus.utils.file.ClientFileUtil;
import net.augustus.utils.file.DirectoryWatcher;
import net.augustus.utils.file.ClientPaths;
import net.augustus.settings.ActionSetting;
import net.augustus.settings.SettingChangeListener;
import net.augustus.settings.StringValue;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.util.ResourceLocation;

public class CustomSkin extends Module {
   private final String customSkinsDir;
   public final StringValue skinMode;
   public final StringValue skins;
   public final ActionSetting openFolder;
   public final HashMap skinTextures;

   public CustomSkin() {
       super("CustomSkin", "KundenspezifischeHaut", new Color(67, 209, 174), Categorys.RENDER);
      this.customSkinsDir = ClientPaths.CLIENT_DIR + File.separator + "customskins";
      this.skinMode = new StringValue(this, "SkinMode", "default", new String[]{"default", "slim"});
      this.skins = (StringValue)new StringValue(this, "Skins", "", new String[0]).withChangeListener(() -> this.reloadPlayerTextures());
      this.openFolder = new ActionSetting(this, "OpenFolder", () -> this.openCustomSkinsFolder());
      this.skinTextures = new HashMap();
      ClientFileUtil.ensureDirectory(this.customSkinsDir);
      this.loadCustomSkins();
      DirectoryWatcher directoryWatcher = DirectoryWatchService.watch(this.customSkinsDir);
      directoryWatcher = directoryWatcher.onCreate(obj -> this.onSkinFileCreated((Path)obj));
      directoryWatcher = directoryWatcher.onModify(obj2 -> this.onSkinFileModified((Path)obj2));
      directoryWatcher.onDelete(obj3 -> this.onSkinFileDeleted((Path)obj3)).register();
   }

   @Override
   public void onEnable() {
      this.reloadPlayerTextures();
   }

   @Override
   public void onDisable() {
      if (mc.thePlayer != null && mc.thePlayer.getPlayerInfo() != null) {
         mc.thePlayer.getPlayerInfo().playerTexturesLoaded = false;
         mc.thePlayer.getPlayerInfo().loadPlayerTextures();
      }
   }

   @Override
   public void updateEventSubscriptions() {
   }

   private void reloadPlayerTextures() {
      if (mc.thePlayer != null && mc.thePlayer.getPlayerInfo() != null) {
         mc.thePlayer.getPlayerInfo().playerTexturesLoaded = false;
         mc.thePlayer.getPlayerInfo().loadPlayerTextures();
      }
   }

   public void loadCustomSkins() {
      this.skinTextures.clear();
      File[] files = new File(this.customSkinsDir).listFiles();
      if (files != null) {
         for (File file2 : files) {
            this.loadSkinFile(file2);
         }
      }

      this.updateSkinOptions();
   }

   private void loadSkinFile(File file) {
      String name2 = file.getName();
      if (name2.endsWith(".png")) {
         String str = name2.substring(0, name2.length() - 4);
         if (!str.isEmpty()) {
            try {
               BufferedImage image = ImageIO.read(file.getAbsoluteFile());
               if (image != null && image.getWidth() == 64 && (image.getHeight() == 64 || image.getHeight() == 32)) {
                  ResourceLocation location = new ResourceLocation("customskins/" + str);
                  DynamicTexture dynamicTexture = new DynamicTexture(image);
                  Minecraft.getMinecraft().getTextureManager().loadTexture(location, dynamicTexture);
                  this.skinTextures.put(str, location);
               }
            } catch (IOException e) {
               e.printStackTrace();
            }
         }
      }
   }

   private void updateSkinOptions() {
      if (!this.skinTextures.keySet().stream().anyMatch(obj -> this.isSelectedSkin((String)obj)) && !this.skinTextures.isEmpty()) {
         this.skins.setSelectedValue((String)this.skinTextures.keySet().iterator().next());
      }

      StringValue skins3 = this.skins;
      String[] strings = ((Map<String, ?>)this.skinTextures).keySet().stream().sorted(Comparator.comparing(String::toLowerCase)).toArray(String[]::new);
      skins3.setOptions(strings);
   }

   public ResourceLocation getSelectedSkinTexture() {
      return (ResourceLocation)this.skinTextures.get(this.skins.getSelectedValue());
   }

   public String getSkinModelType() {
      return this.skinMode.getSelectedValue();
   }

   private boolean isSelectedSkin(String str) {
      return this.skins.getSelectedValue().equals(str);
   }

   private void onSkinFileDeleted(Path path) {
      String name2 = path.toFile().getName();
      if (name2.endsWith(".png")) {
         String str = name2.substring(0, name2.length() - 4);
         this.skinTextures.remove(str);
         this.updateSkinOptions();
      }
   }

   private void onSkinFileModified(Path path) {
      this.loadSkinFile(path.toFile());
      this.updateSkinOptions();
   }

   private void onSkinFileCreated(Path path) {
      this.loadSkinFile(path.toFile());
      this.updateSkinOptions();
   }

   private void openCustomSkinsFolder() {
      ClientFileUtil.openFolder(this.customSkinsDir);
   }

}
