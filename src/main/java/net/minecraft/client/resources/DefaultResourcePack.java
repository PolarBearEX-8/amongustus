package net.minecraft.client.resources;

import com.google.common.collect.ImmutableSet;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Set;
import net.minecraft.client.renderer.texture.TextureUtil;
import net.minecraft.client.resources.data.IMetadataSection;
import net.minecraft.client.resources.data.IMetadataSerializer;
import net.minecraft.util.ResourceLocation;

public class DefaultResourcePack implements IResourcePack {
   public static final Set defaultResourceDomains = ImmutableSet.of("minecraft", "realms", "wdl", "augustus");
   private final Map mapAssets;

   @Override
   public BufferedImage getPackImage() throws IOException {
      return TextureUtil.readBufferedImage(DefaultResourcePack.class.getResourceAsStream("/" + new ResourceLocation("pack.png").getResourcePath()));
   }

   private InputStream getResourceStream(ResourceLocation location) {
      String str = "/assets/" + location.getResourceDomain() + "/" + location.getResourcePath();
      return DefaultResourcePack.class.getResourceAsStream("/assets/" + location.getResourceDomain() + "/" + location.getResourcePath());
   }

   public InputStream getInputStreamAssets(ResourceLocation location) throws IOException {
      File file = (File)this.mapAssets.get(location.toString());
      return file != null && file.isFile() ? new FileInputStream(file) : null;
   }

   @Override
   public boolean resourceExists(ResourceLocation location) {
      return this.getResourceStream(location) != null || this.mapAssets.containsKey(location.toString());
   }

   public DefaultResourcePack(Map map) {
      this.mapAssets = map;
   }

   @Override
   public InputStream getInputStream(ResourceLocation location) throws IOException {
      InputStream resourceStream = this.getResourceStream(location);
      if (resourceStream != null) {
         return resourceStream;
      } else {
         InputStream inputStreamAssets = this.getInputStreamAssets(location);
         if (inputStreamAssets != null) {
            return inputStreamAssets;
         } else {
            throw new FileNotFoundException(location.getResourcePath());
         }
      }
   }

   @Override
   public IMetadataSection getPackMetadata(IMetadataSerializer metadataSerializer, String str) throws IOException {
      try {
         return AbstractResourcePack.readMetadata(metadataSerializer, new FileInputStream((File)this.mapAssets.get("pack.mcmeta")), str);
      } catch (RuntimeException e) {
         return null;
      } catch (FileNotFoundException e2) {
         return null;
      }
   }

   @Override
   public Set getResourceDomains() {
      return defaultResourceDomains;
   }

   @Override
   public String getPackName() {
      return "Default";
   }
}
