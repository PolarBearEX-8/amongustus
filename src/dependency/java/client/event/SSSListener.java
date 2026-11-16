package client.event;

import client.util.InvalidMetadataForManager;
import client.util.SpecialLoggerEntry;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import net.minecraft.client.resources.data.IMetadataSection;
import net.minecraft.util.ResourceLocation;
import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class SSSListener implements PreUpdateProcessor {
   public static final Logger logger = LogManager.getLogger();
   public static final Gson gson = new GsonBuilder().create();
   public final File file;

   public void warn(String text) {
      logger.warn("ResourcePack: ignored non-lowercase namespace: {} in {}", new Object[]{text, this.file});
   }

   public abstract InputStream resolveInputStream(String text) throws IOException;

   private static String resolveTextByResourceLocation(ResourceLocation location) {
      return String.format("%s/%s/%s", "assets", location.getText(), location.getText1());
   }

   @Override
   public boolean matchesResourceLocation(ResourceLocation location) {
      return this.isFile(resolveTextByResourceLocation(location));
   }

   public static String resolveText(File file2, File file3) {
      return file2.toURI().relativize(file3.toURI()).getPath();
   }

   public abstract boolean isFile(String text);

   @Override
   public IMetadataSection resolveIMetadataSection(InvalidMetadataForManager invalidMetadataForManager, String text) throws IOException {
      return resolveIMetadataSection(invalidMetadataForManager, this.resolveInputStream("pack.mcmeta"), text);
   }

   @Override
   public BufferedImage getBufferedImage() throws IOException {
      return SpecialLoggerEntry.resolveBufferedImage(this.resolveInputStream("pack.png"));
   }

   public static IMetadataSection resolveIMetadataSection(InvalidMetadataForManager invalidMetadataForManager, InputStream inputStream, String text) {
      BufferedReader reader = null;

      JsonObject jsonObject;
      try {
         reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
         jsonObject = (JsonObject)gson.fromJson(reader, JsonObject.class);
      } catch (RuntimeException exception) {
         throw new JsonParseException(exception);
      } finally {
         IOUtils.closeQuietly(reader);
      }

      return invalidMetadataForManager.resolveIMetadataSection(text, jsonObject);
   }

   @Override
   public String getName() {
      return this.file.getName();
   }

   @Override
   public InputStream resolveInputStream(ResourceLocation location) throws IOException {
      return this.resolveInputStream(resolveTextByResourceLocation(location));
   }

   public SSSListener(File file2) {
      this.file = file2;
   }
}
