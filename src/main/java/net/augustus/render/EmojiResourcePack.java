package net.augustus.render;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.Collections;
import java.util.Set;
import net.minecraft.client.resources.IResourcePack;
import net.minecraft.client.resources.data.IMetadataSection;
import net.minecraft.client.resources.data.IMetadataSerializer;
import net.minecraft.util.ResourceLocation;

public class EmojiResourcePack implements IResourcePack {
   private File baseDirectory;

   public IMetadataSection getPackMetadata(IMetadataSerializer metadataSerializer, String str) throws IOException {
      return null;
   }

   public InputStream getInputStream(ResourceLocation location) throws IOException {
      return Files.newInputStream(new File(this.baseDirectory, location.getResourcePath()).toPath());
   }

   public EmojiResourcePack(File file) {
      this.baseDirectory = file;
   }

   public BufferedImage getPackImage() throws IOException {
      return null;
   }

   public boolean resourceExists(ResourceLocation location) {
      try {
         return this.getInputStream(location) != null;
      } catch (IOException e) {
         return false;
      }
   }

   public Set getResourceDomains() {
      return Collections.singleton("emojis");
   }

   public String getPackName() {
      return "emojify";
   }

}
