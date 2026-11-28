package net.minecraft.client.resources;

import com.google.common.collect.Maps;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Map;
import net.minecraft.client.resources.data.IMetadataSection;
import net.minecraft.client.resources.data.IMetadataSerializer;
import net.minecraft.util.ResourceLocation;
import org.apache.commons.io.IOUtils;

public class SimpleResource implements IResource {
   private final Map mapMetadataSections = Maps.newHashMap();
   private final String resourcePackName;
   private final ResourceLocation srResourceLocation;
   private final InputStream resourceInputStream;
   private final InputStream mcmetaInputStream;
   private final IMetadataSerializer srMetadataSerializer;
   private boolean mcmetaJsonChecked;
   private JsonObject mcmetaJson;

   @Override
   public IMetadataSection getMetadata(String str) {
      if (!this.hasMetadata()) {
         return (IMetadataSection)null;
      } else {
         if (this.mcmetaJson == null && !this.mcmetaJsonChecked) {
            this.mcmetaJsonChecked = true;
            BufferedReader reader = null;

            try {
               reader = new BufferedReader(new InputStreamReader(this.mcmetaInputStream));
               this.mcmetaJson = new JsonParser().parse(reader).getAsJsonObject();
            } finally {
               IOUtils.closeQuietly(reader);
            }
         }

         IMetadataSection metadataSection = (IMetadataSection)this.mapMetadataSections.get(str);
         if (metadataSection == null) {
            metadataSection = this.srMetadataSerializer.parseMetadataSection(str, this.mcmetaJson);
         }

         return metadataSection;
      }
   }

   @Override
   public ResourceLocation getResourceLocation() {
      return this.srResourceLocation;
   }

   @Override
   public int hashCode() {
      return 31 * (this.resourcePackName != null ? this.resourcePackName.hashCode() : 0) + (this.srResourceLocation != null ? this.srResourceLocation.hashCode() : 0);
   }

   public SimpleResource(String str, ResourceLocation location, InputStream in, InputStream in2, IMetadataSerializer metadataSerializer) {
      this.resourcePackName = str;
      this.srResourceLocation = location;
      this.resourceInputStream = in;
      this.mcmetaInputStream = in2;
      this.srMetadataSerializer = metadataSerializer;
   }

   @Override
   public boolean hasMetadata() {
      return this.mcmetaInputStream != null;
   }

   @Override
   public boolean equals(Object obj) {
      if (this == obj) {
         return true;
      } else if (!(obj instanceof SimpleResource)) {
         return false;
      } else {
         SimpleResource simpleResource = (SimpleResource)obj;
         if (this.srResourceLocation != null) {
            if (!this.srResourceLocation.equals(simpleResource.srResourceLocation)) {
               return false;
            }
         } else if (simpleResource.srResourceLocation != null) {
            return false;
         }

         if (this.resourcePackName != null) {
            if (!this.resourcePackName.equals(simpleResource.resourcePackName)) {
               return false;
            }
         } else if (simpleResource.resourcePackName != null) {
            return false;
         }

         return true;
      }
   }

   @Override
   public String getResourcePackName() {
      return this.resourcePackName;
   }

   @Override
   public InputStream getInputStream() {
      return this.resourceInputStream;
   }
}
