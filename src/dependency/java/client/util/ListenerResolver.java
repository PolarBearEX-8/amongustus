package client.util;

import com.google.common.collect.Maps;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Map;
import net.minecraft.client.resources.IResource;
import net.minecraft.client.resources.data.IMetadataSection;
import net.minecraft.util.ResourceLocation;
import org.apache.commons.io.IOUtils;

public class ListenerResolver implements IResource {
   public final Map map = Maps.newHashMap();
   public final String labelText;
   public final ResourceLocation resourceLocation;
   public final InputStream inputStream;
   public final InputStream inputStream2;
   public final InvalidMetadataForManager invalidMetadataForManager;
   public boolean enabled;
   public JsonObject jsonObject;

   @Override
   public IMetadataSection resolveIMetadataSection(String text) {
      if (!this.isActive()) {
         return (IMetadataSection)null;
      } else {
         if (this.jsonObject == null && !this.enabled) {
            this.enabled = true;
            BufferedReader reader = null;

            try {
               reader = new BufferedReader(new InputStreamReader(this.inputStream2));
               this.jsonObject = new JsonParser().parse(reader).getAsJsonObject();
            } finally {
               IOUtils.closeQuietly(reader);
            }
         }

         IMetadataSection iMetadataSection = (IMetadataSection)this.map.get(text);
         if (iMetadataSection == null) {
            iMetadataSection = this.invalidMetadataForManager.resolveIMetadataSection(text, this.jsonObject);
         }

         return iMetadataSection;
      }
   }

   @Override
   public InputStream getInputStream() {
      return this.inputStream;
   }

   @Override
   public ResourceLocation getResourceLocation() {
      return this.resourceLocation;
   }

   @Override
   public String getLabelText() {
      return this.labelText;
   }

   @Override
   public boolean isActive() {
      return this.inputStream2 != null;
   }

   public ListenerResolver(String text, ResourceLocation location, InputStream inputStream3, InputStream inputStream4, InvalidMetadataForManager invalidMetadataForManager2) {
      this.labelText = text;
      this.resourceLocation = location;
      this.inputStream = inputStream3;
      this.inputStream2 = inputStream4;
      this.invalidMetadataForManager = invalidMetadataForManager2;
   }

   @Override
   public boolean equals(Object other) {
      if (this == other) {
         return true;
      } else if (!(other instanceof ListenerResolver)) {
         return false;
      } else {
         ListenerResolver listenerResolver = (ListenerResolver)other;
         if (this.resourceLocation != null) {
            if (!this.resourceLocation.equals(listenerResolver.resourceLocation)) {
               return false;
            }
         } else if (listenerResolver.resourceLocation != null) {
            return false;
         }

         if (this.labelText != null) {
            if (!this.labelText.equals(listenerResolver.labelText)) {
               return false;
            }
         } else if (listenerResolver.labelText != null) {
            return false;
         }

         return true;
      }
   }

   @Override
   public int hashCode() {
      int count = this.labelText != null ? this.labelText.hashCode() : 0;
      return 31 * count + (this.resourceLocation != null ? this.resourceLocation.hashCode() : 0);
   }
}
