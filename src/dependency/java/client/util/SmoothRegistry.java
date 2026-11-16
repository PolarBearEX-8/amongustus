package client.util;

import client.font.FontJsonDeserializerExtensionAdapter;
import client.render.ShadersManager;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import net.minecraft.client.resources.IResourceManager;
import org.apache.commons.io.IOUtils;

public class SmoothRegistry extends ListenerAdapter26 {
   public String labelText;
   public static final InvalidMetadataForManager invalidMetadataForManager = getInvalidMetadataForManager();

   public SmoothRegistry(String text) {
      this.labelText = text;
   }

   public static SupplementalListEntry resolveSupplementalListEntry(String text, SupplementalListEntry supplementalListEntry) {
      String text2 = text + ".mcmeta";
      String text3 = "texture";
      InputStream inputStream = PreEndHandManager.resolveInputStream(text2);
      if (inputStream != null) {
         InvalidMetadataForManager invalidMetadataForManager2 = invalidMetadataForManager;
         BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

         SupplementalListEntry supplementalListEntry2;
         try {
            JsonObject asJsonObject = new JsonParser().parse(reader).getAsJsonObject();
            supplementalListEntry2 = (SupplementalListEntry)invalidMetadataForManager2.resolveIMetadataSection(text3, asJsonObject);
            if (supplementalListEntry2 == null) {
               return supplementalListEntry;
            }
         } catch (RuntimeException exception) {
            ShadersManager.applyText3("Error reading metadata: " + text2);
            ShadersManager.applyText3("" + exception.getClass().getName() + ": " + exception.getMessage());
            return supplementalListEntry;
         } finally {
            IOUtils.closeQuietly(reader);
            IOUtils.closeQuietly(inputStream);
         }

         return supplementalListEntry2;
      } else {
         return supplementalListEntry;
      }
   }

   @Override
   public void applyIResourceManager(IResourceManager iResourceManager) throws IOException {
      this.updateState3();
      InputStream inputStream = PreEndHandManager.resolveInputStream(this.labelText);
      if (inputStream == null) {
         throw new FileNotFoundException("Shader texture not found: " + this.labelText);
      } else {
         try {
            BufferedImage bufferedImage = SpecialLoggerEntry.resolveBufferedImage(inputStream);
            SupplementalListEntry supplementalListEntry = resolveSupplementalListEntry(this.labelText, new SupplementalListEntry(false, false, new ArrayList()));
            SpecialLoggerEntry.computeCount(this.getCount(), bufferedImage, supplementalListEntry.isEnabled(), supplementalListEntry.isEnabled2());
         } finally {
            IOUtils.closeQuietly(inputStream);
         }
      }
   }

   private static InvalidMetadataForManager getInvalidMetadataForManager() {
      InvalidMetadataForManager invalidMetadataForManager2 = new InvalidMetadataForManager();
      invalidMetadataForManager2.applyJsonDeserializerExtension(new BlurJsonDeserializerExtensionAdapter(), SupplementalListEntry.class);
      invalidMetadataForManager2.applyJsonDeserializerExtension(new FontJsonDeserializerExtensionAdapter(), ListenerAdapter11.class);
      invalidMetadataForManager2.applyJsonDeserializerExtension(new MetadataSectionJsonDeserializerExtensionAdapter(), BaseListEntry.class);
      invalidMetadataForManager2.applyJsonDeserializerExtension(new PackFormatJsonDeserializerExtensionAdapter(), AuxiliaryIChatComponentEntry.class);
      invalidMetadataForManager2.applyJsonDeserializerExtension(new LanguageJsonDeserializerExtensionAdapter(), AuxiliaryListenerAdapter7.class);
      return invalidMetadataForManager2;
   }
}
