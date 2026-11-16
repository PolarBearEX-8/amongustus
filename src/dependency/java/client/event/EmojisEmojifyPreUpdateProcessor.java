package client.event;

import client.util.InvalidMetadataForManager;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.Collections;
import java.util.Set;
import net.minecraft.client.resources.data.IMetadataSection;
import net.minecraft.util.ResourceLocation;

public class EmojisEmojifyPreUpdateProcessor implements PreUpdateProcessor {
   public File file;

   @Override
   public Set getSet() {
      return Collections.singleton("emojis");
   }

   @Override
   public boolean matchesResourceLocation(ResourceLocation location) {
      try {
         return this.resolveInputStream(location) != null;
      } catch (IOException ioException) {
         return false;
      }
   }

   @Override
   public IMetadataSection resolveIMetadataSection(InvalidMetadataForManager invalidMetadataForManager, String text) throws IOException {
      return null;
   }

   @Override
   public String getName() {
      return "emojify";
   }

   public EmojisEmojifyPreUpdateProcessor(File file2) {
      this.file = file2;
   }

   @Override
   public BufferedImage getBufferedImage() throws IOException {
      return null;
   }

   @Override
   public InputStream resolveInputStream(ResourceLocation location) throws IOException {
      return Files.newInputStream(new File(this.file, location.getText1()).toPath());
   }
}
