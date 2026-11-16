package client.render;

import client.event.PreUpdateProcessor;
import client.setting.ClientSettingsBlackType;
import client.util.AuxiliaryIChatComponentEntry;
import client.util.AuxiliarySSSListenerAdapter;
import client.util.SSSListenerAdapter;
import client.util.ZipFileFilter;
import java.awt.image.BufferedImage;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.resources.ResourceLanguageManager;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ResourceLocation;
import org.apache.commons.io.IOUtils;

public class TexturepackiconManager {
   public final File file;
   public PreUpdateProcessor preUpdateProcessor;
   public AuxiliaryIChatComponentEntry auxiliaryIChatComponentEntry;
   public BufferedImage bufferedImage;
   public ResourceLocation resourceLocation;
   public final ResourceLanguageManager resourceLanguageManager;

   public String getTextAsText() {
      return this.auxiliaryIChatComponentEntry == null
         ? ClientSettingsBlackType.RED + "Invalid pack.mcmeta (or missing 'pack' section)"
         : this.auxiliaryIChatComponentEntry.getIChatComponent().getString2();
   }

   public int getCount() {
      return this.auxiliaryIChatComponentEntry.getCount();
   }

   public String getText() {
      return this.preUpdateProcessor.getName();
   }

   public void closeQuietly() {
      if (this.preUpdateProcessor instanceof Closeable) {
         IOUtils.closeQuietly((Closeable)this.preUpdateProcessor);
      }
   }

   public void applyTextureManager(TextureManager textureManager) {
      if (this.resourceLocation == null) {
         this.resourceLocation = textureManager.resolveResourceLocationByText("texturepackicon", new DynamicTexture(this.bufferedImage));
      }

      textureManager.applyResourceLocation3(this.resourceLocation);
   }

   public PreUpdateProcessor getPreUpdateProcessor() {
      return this.preUpdateProcessor;
   }

   public void renderElement() throws IOException {
      this.preUpdateProcessor = (PreUpdateProcessor)(this.file.isDirectory() ? new AuxiliarySSSListenerAdapter(this.file) : new SSSListenerAdapter(this.file));

      try {
         this.auxiliaryIChatComponentEntry = (AuxiliaryIChatComponentEntry)this.preUpdateProcessor
            .resolveIMetadataSection(this.resourceLanguageManager.invalidMetadataForManager, "pack");
      } catch (Exception exception) {
         this.auxiliaryIChatComponentEntry = new AuxiliaryIChatComponentEntry(
            new ChatComponentText("Failed to load the real message caused by unicode characters. Please update your pack.mcmeta"), 1
         );
      }

      try {
         this.bufferedImage = this.preUpdateProcessor.getBufferedImage();
      } catch (IOException ioException) {
      }

      if (this.bufferedImage == null) {
         this.bufferedImage = this.resourceLanguageManager.preUpdateProcessor2.getBufferedImage();
      }

      this.closeQuietly();
   }

   public TexturepackiconManager(ResourceLanguageManager resourceLanguageManager2, File file2, ZipFileFilter zipFileFilter) {
      this(resourceLanguageManager2, file2);
   }

   private TexturepackiconManager(ResourceLanguageManager resourceLanguageManager2, File file2) {
      this.resourceLanguageManager = resourceLanguageManager2;
      this.file = file2;
   }

   @Override
   public boolean equals(Object other) {
      return this == other ? true : (other instanceof TexturepackiconManager ? this.toString().equals(other.toString()) : false);
   }

   @Override
   public String toString() {
      return String.format("%s:%s:%d", this.file.getName(), this.file.isDirectory() ? "folder" : "zip", this.file.lastModified());
   }

   @Override
   public int hashCode() {
      return this.toString().hashCode();
   }
}
