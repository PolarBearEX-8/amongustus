package net.optifine.shaders;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import net.minecraft.client.renderer.texture.AbstractTexture;
import net.minecraft.client.renderer.texture.TextureUtil;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.client.resources.data.AnimationMetadataSection;
import net.minecraft.client.resources.data.AnimationMetadataSectionSerializer;
import net.minecraft.client.resources.data.FontMetadataSection;
import net.minecraft.client.resources.data.FontMetadataSectionSerializer;
import net.minecraft.client.resources.data.IMetadataSerializer;
import net.minecraft.client.resources.data.LanguageMetadataSection;
import net.minecraft.client.resources.data.LanguageMetadataSectionSerializer;
import net.minecraft.client.resources.data.PackMetadataSection;
import net.minecraft.client.resources.data.PackMetadataSectionSerializer;
import net.minecraft.client.resources.data.TextureMetadataSection;
import net.minecraft.client.resources.data.TextureMetadataSectionSerializer;
import org.apache.commons.io.IOUtils;

public class SimpleShaderTexture extends AbstractTexture {
   private String texturePath;
   private static final IMetadataSerializer METADATA_SERIALIZER = makeMetadataSerializer();

   private static IMetadataSerializer makeMetadataSerializer() {
      IMetadataSerializer metadataSerializer = new IMetadataSerializer();
      metadataSerializer.registerMetadataSectionType(new TextureMetadataSectionSerializer(), TextureMetadataSection.class);
      metadataSerializer.registerMetadataSectionType(new FontMetadataSectionSerializer(), FontMetadataSection.class);
      metadataSerializer.registerMetadataSectionType(new AnimationMetadataSectionSerializer(), AnimationMetadataSection.class);
      metadataSerializer.registerMetadataSectionType(new PackMetadataSectionSerializer(), PackMetadataSection.class);
      metadataSerializer.registerMetadataSectionType(new LanguageMetadataSectionSerializer(), LanguageMetadataSection.class);
      return metadataSerializer;
   }

   public static TextureMetadataSection loadTextureMetadataSection(String str, TextureMetadataSection textureMetadataSection) {
      String str2 = str + ".mcmeta";
      String str3 = "texture";
      InputStream in = Shaders.getShaderPackResourceStream(str2);
      if (in != null) {
         IMetadataSerializer metadataSerializer = METADATA_SERIALIZER;
         BufferedReader reader = new BufferedReader(new InputStreamReader(in));

         TextureMetadataSection textureMetadataSection2;
         try {
            TextureMetadataSection textureMetadataSection3 = (TextureMetadataSection)metadataSerializer.parseMetadataSection(str3, new JsonParser().parse(reader).getAsJsonObject());
            if (textureMetadataSection3 != null) {
               return textureMetadataSection3;
            }

            textureMetadataSection2 = textureMetadataSection;
         } catch (RuntimeException e) {
            SMCLog.warning("Error reading metadata: " + str2);
            SMCLog.warning("" + e.getClass().getName() + ": " + e.getMessage());
            return textureMetadataSection;
         } finally {
            IOUtils.closeQuietly(reader);
            IOUtils.closeQuietly(in);
         }

         return textureMetadataSection2;
      } else {
         return textureMetadataSection;
      }
   }

   public SimpleShaderTexture(String str) {
      this.texturePath = str;
   }

   @Override
   public void loadTexture(IResourceManager resourceManager) throws IOException {
      this.deleteGlTexture();
      InputStream in = Shaders.getShaderPackResourceStream(this.texturePath);
      if (in == null) {
         throw new FileNotFoundException("Shader texture not found: " + this.texturePath);
      } else {
         try {
            BufferedImage image = TextureUtil.readBufferedImage(in);
            TextureMetadataSection textureMetadataSection = loadTextureMetadataSection(this.texturePath, new TextureMetadataSection(false, false, new ArrayList()));
            TextureUtil.uploadTextureImageAllocate(this.getGlTextureId(), image, textureMetadataSection.getTextureBlur(), textureMetadataSection.getTextureClamp());
         } finally {
            IOUtils.closeQuietly(in);
         }
      }
   }
}
