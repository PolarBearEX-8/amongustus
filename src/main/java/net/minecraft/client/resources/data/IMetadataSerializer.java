package net.minecraft.client.resources.data;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import net.minecraft.util.ChatStyle;
import net.minecraft.util.EnumTypeAdapterFactory;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.IRegistry;
import net.minecraft.util.RegistrySimple;

public class IMetadataSerializer {
   private final IRegistry metadataSectionSerializerRegistry = new RegistrySimple();
   private final GsonBuilder gsonBuilder = new GsonBuilder();
   private Gson gson;

   public IMetadataSerializer() {
      this.gsonBuilder.registerTypeHierarchyAdapter(IChatComponent.class, new IChatComponent.Serializer());
      this.gsonBuilder.registerTypeHierarchyAdapter(ChatStyle.class, new ChatStyle.Serializer());
      this.gsonBuilder.registerTypeAdapterFactory(new EnumTypeAdapterFactory());
   }

   public void registerMetadataSectionType(IMetadataSectionSerializer serializer, Class clazz) {
      this.metadataSectionSerializerRegistry.putObject(serializer.getSectionName(), new IMetadataSerializer.Registration(this, serializer, clazz, null));
      this.gsonBuilder.registerTypeAdapter(clazz, serializer);
      this.gson = null;
   }

   public IMetadataSection parseMetadataSection(String str, JsonObject json) {
      if (str == null) {
         throw new IllegalArgumentException("Metadata section name cannot be null");
      } else if (!json.has(str)) {
         return (IMetadataSection)null;
      } else if (!json.get(str).isJsonObject()) {
         throw new IllegalArgumentException("Invalid metadata for '" + str + "' - expected object, found " + json.get(str));
      } else {
         IMetadataSerializer.Registration object = (IMetadataSerializer.Registration)this.metadataSectionSerializerRegistry.getObject(str);
         if (object == null) {
            throw new IllegalArgumentException("Don't know how to handle metadata section '" + str + "'");
         } else {
            return (IMetadataSection)this.getGson().fromJson(json.getAsJsonObject(str), object.clazz);
         }
      }
   }

   private Gson getGson() {
      if (this.gson == null) {
         this.gson = this.gsonBuilder.create();
      }

      return this.gson;
   }


   public static class InnerEntry {
   }

   public static class Registration {
      public final IMetadataSectionSerializer section;
      public final Class clazz;
      public final IMetadataSerializer metadataSerializer;

      private Registration(IMetadataSerializer metadataSerializer2, IMetadataSectionSerializer serializer, Class clazz2) {
         this.metadataSerializer = metadataSerializer2;
         this.section = serializer;
         this.clazz = clazz2;
      }

      public Registration(IMetadataSerializer metadataSerializer2, IMetadataSectionSerializer serializer, Class clazz2, IMetadataSerializer.InnerEntry obj) {
         this(metadataSerializer2, serializer, clazz2);
      }
   }
}
