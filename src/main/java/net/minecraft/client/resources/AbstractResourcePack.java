package net.minecraft.client.resources;

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
import net.minecraft.client.renderer.texture.TextureUtil;
import net.minecraft.client.resources.data.IMetadataSection;
import net.minecraft.client.resources.data.IMetadataSerializer;
import net.minecraft.util.ResourceLocation;
import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class AbstractResourcePack implements IResourcePack {
   private static final Logger LOGGER = LogManager.getLogger();
   private static final Gson GSON = new GsonBuilder().create();
   public final File resourcePackFile;

   @Override
   public String getPackName() {
      return this.resourcePackFile.getName();
   }

   public abstract InputStream getInputStreamByName(String str) throws IOException;

   private static String locationToName(ResourceLocation location) {
      return String.format("%s/%s/%s", "assets", location.getResourceDomain(), location.getResourcePath());
   }

   public static IMetadataSection readMetadata(IMetadataSerializer metadataSerializer, InputStream in, String str) {
      Object obj = null;
      BufferedReader reader = null;

      try {
         reader = new BufferedReader(new InputStreamReader(in, StandardCharsets.UTF_8));
         obj = (JsonObject)GSON.fromJson(reader, JsonObject.class);
      } catch (RuntimeException e) {
         throw new JsonParseException(e);
      } finally {
         IOUtils.closeQuietly(reader);
      }

      return metadataSerializer.parseMetadataSection(str, (JsonObject)obj);
   }

   @Override
   public boolean resourceExists(ResourceLocation location) {
      return this.hasResourceName(locationToName(location));
   }

   @Override
   public IMetadataSection getPackMetadata(IMetadataSerializer metadataSerializer, String str) throws IOException {
      return readMetadata(metadataSerializer, this.getInputStreamByName("pack.mcmeta"), str);
   }

   public void logNameNotLowercase(String str) {
      LOGGER.warn("ResourcePack: ignored non-lowercase namespace: {} in {}", new Object[]{str, this.resourcePackFile});
   }

   public abstract boolean hasResourceName(String str);

   public AbstractResourcePack(File file) {
      this.resourcePackFile = file;
   }

   @Override
   public BufferedImage getPackImage() throws IOException {
      return TextureUtil.readBufferedImage(this.getInputStreamByName("pack.png"));
   }

   @Override
   public InputStream getInputStream(ResourceLocation location) throws IOException {
      return this.getInputStreamByName(locationToName(location));
   }

   public static String getRelativeName(File file, File file2) {
      return file.toURI().relativize(file2.toURI()).getPath();
   }
}
