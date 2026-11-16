package net.minecraft.client.resources;

import client.event.PreUpdateProcessor;
import client.event.SSSListener;
import client.util.InvalidMetadataForManager;
import client.util.SpecialLoggerEntry;
import com.google.common.collect.ImmutableSet;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Set;
import net.minecraft.client.resources.data.IMetadataSection;
import net.minecraft.util.ResourceLocation;

public class IReloadableResourceManager implements PreUpdateProcessor {
   public static final Set set = ImmutableSet.of("minecraft", "realms", "wdl", "augustus");
   public final Map map;

   @Override
   public boolean matchesResourceLocation(ResourceLocation location) {
      return this.resolveInputStream3(location) != null || this.map.containsKey(location.toString());
   }

   private InputStream resolveInputStream3(ResourceLocation location) {
      new StringBuilder().append("/assets/").append(location.getText()).append("/").append(location.getText1()).toString();
      return IReloadableResourceManager.class.getResourceAsStream("/assets/" + location.getText() + "/" + location.getText1());
   }

   @Override
   public IMetadataSection resolveIMetadataSection(InvalidMetadataForManager invalidMetadataForManager, String text) throws IOException {
      try {
         FileInputStream inputStream = new FileInputStream((File)this.map.get("pack.mcmeta"));
         return SSSListener.resolveIMetadataSection(invalidMetadataForManager, inputStream, text);
      } catch (RuntimeException exception) {
         return null;
      } catch (FileNotFoundException fileNotFoundException) {
         return null;
      }
   }

   @Override
   public BufferedImage getBufferedImage() throws IOException {
      return SpecialLoggerEntry.resolveBufferedImage(IReloadableResourceManager.class.getResourceAsStream("/" + new ResourceLocation("pack.png").getText1()));
   }

   @Override
   public Set getSet() {
      return set;
   }

   public InputStream resolveInputStream5(ResourceLocation location) throws IOException {
      File file = (File)this.map.get(location.toString());
      return file != null && file.isFile() ? new FileInputStream(file) : null;
   }

   @Override
   public String getName() {
      return "Default";
   }

   @Override
   public InputStream resolveInputStream(ResourceLocation location) throws IOException {
      InputStream inputStream = this.resolveInputStream3(location);
      if (inputStream != null) {
         return inputStream;
      } else {
         InputStream inputStream2 = this.resolveInputStream5(location);
         if (inputStream2 != null) {
            return inputStream2;
         } else {
            throw new FileNotFoundException(location.getText1());
         }
      }
   }

   public IReloadableResourceManager(Map map2) {
      this.map = map2;
   }
}
