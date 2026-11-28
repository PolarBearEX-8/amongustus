package net.minecraft.client.renderer.block.model;

import com.google.common.collect.Lists;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Map.Entry;
import net.minecraft.client.resources.model.ModelRotation;
import net.minecraft.util.JsonUtils;
import net.minecraft.util.ResourceLocation;
import com.google.common.collect.Maps;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.Reader;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class ModelBlockDefinition {
   public static final Gson GSON = new GsonBuilder()
      .registerTypeAdapter(ModelBlockDefinition.class, new ModelBlockDefinition.Deserializer())
      .registerTypeAdapter(ModelBlockDefinition.Variant.class, new ModelBlockDefinition.Variant.Deserializer())
      .create();
   private final Map mapVariants = Maps.newHashMap();

   public static ModelBlockDefinition parseFromReader(Reader reader) {
      return (ModelBlockDefinition)GSON.fromJson(reader, ModelBlockDefinition.class);
   }

   public ModelBlockDefinition(Collection items) {
      for (ModelBlockDefinition.Variants variants : (Iterable<ModelBlockDefinition.Variants>)(items)) {
         this.mapVariants.put(ModelBlockDefinition.Variants.accessName(variants), variants);
      }
   }

   public ModelBlockDefinition.Variants getVariants(String str) {
      ModelBlockDefinition.Variants variants = (ModelBlockDefinition.Variants)this.mapVariants.get(str);
      if (variants == null) {
         throw new ModelBlockDefinition.MissingVariantException(this);
      } else {
         return variants;
      }
   }

   public ModelBlockDefinition(List list) {
      for (ModelBlockDefinition modelBlockDefinition : (Iterable<ModelBlockDefinition>)(list)) {
         this.mapVariants.putAll(modelBlockDefinition.mapVariants);
      }
   }

   @Override
   public int hashCode() {
      return this.mapVariants.hashCode();
   }

   @Override
   public boolean equals(Object obj) {
      if (this == obj) {
         return true;
      } else if (obj instanceof ModelBlockDefinition) {
         ModelBlockDefinition modelBlockDefinition = (ModelBlockDefinition)obj;
         return this.mapVariants.equals(modelBlockDefinition.mapVariants);
      } else {
         return false;
      }
   }


   public static class Variant {
      private final ResourceLocation modelLocation;
      private final ModelRotation modelRotation;
      private final boolean uvLock;
      private final int weight;

      @Override
      public int hashCode() {
         int n = this.modelLocation.hashCode();
         n = 31 * n + (this.modelRotation != null ? this.modelRotation.hashCode() : 0);
         return 31 * n + (this.uvLock ? 1 : 0);
      }

      @Override
      public boolean equals(Object obj) {
         if (this == obj) {
            return true;
         } else if (!(obj instanceof Variant)) {
            return false;
         } else {
            Variant variant = (Variant)obj;
            return this.modelLocation.equals(variant.modelLocation) && this.modelRotation == variant.modelRotation && this.uvLock == variant.uvLock;
         }
      }

      public int getWeight() {
         return this.weight;
      }

      public ModelRotation getRotation() {
         return this.modelRotation;
      }

      public boolean isUvLocked() {
         return this.uvLock;
      }

      public ResourceLocation getModelLocation() {
         return this.modelLocation;
      }

      public Variant(ResourceLocation location, ModelRotation modelRotation2, boolean flag, int n) {
         this.modelLocation = location;
         this.modelRotation = modelRotation2;
         this.uvLock = flag;
         this.weight = n;
      }


      public static class Deserializer implements JsonDeserializer {
         public int parseWeight(JsonObject json) {
            return JsonUtils.getInt(json, "weight", 1);
         }

         public ModelBlockDefinition.Variant deserializeVariant(JsonElement elem, Type type, JsonDeserializationContext context) throws JsonParseException {
            JsonObject asJsonObject = elem.getAsJsonObject();
            String str = this.parseModel(asJsonObject);
            ModelRotation modelRotation = this.parseRotation(asJsonObject);
            boolean flag = this.parseUvLock(asJsonObject);
            int n = this.parseWeight(asJsonObject);
            return new ModelBlockDefinition.Variant(this.makeModelLocation(str), modelRotation, flag, n);
         }

         private boolean parseUvLock(JsonObject json) {
            return JsonUtils.getBoolean(json, "uvlock", false);
         }

         public String parseModel(JsonObject json) {
            return JsonUtils.getString(json, "model");
         }

         public ModelRotation parseRotation(JsonObject json) {
            int n = JsonUtils.getInt(json, "x", 0);
            int n2 = JsonUtils.getInt(json, "y", 0);
            ModelRotation modelRotation = ModelRotation.getModelRotation(n, n2);
            if (modelRotation == null) {
               throw new JsonParseException("Invalid BlockModelRotation x: " + n + ", y: " + n2);
            } else {
               return modelRotation;
            }
         }

         private ResourceLocation makeModelLocation(String str) {
            ResourceLocation location = new ResourceLocation(str);
            return new ResourceLocation(location.getResourceDomain(), "block/" + location.getResourcePath());
         }

         public Object deserialize(JsonElement elem, Type type, JsonDeserializationContext context) throws JsonParseException {
            return this.deserializeVariant(elem, type, context);
         }
      }
   }

   public static class Variants {
      private final String name;
      private final List listVariants;

      @Override
      public boolean equals(Object obj) {
         if (this == obj) {
            return true;
         } else if (!(obj instanceof Variants)) {
            return false;
         } else {
            Variants variants = (Variants)obj;
            return !this.name.equals(variants.name) ? false : this.listVariants.equals(variants.listVariants);
         }
      }

      @Override
      public int hashCode() {
         return 31 * this.name.hashCode() + this.listVariants.hashCode();
      }

      public Variants(String str, List list) {
         this.name = str;
         this.listVariants = list;
      }

      public static String accessName(Variants variants) {
         return variants.name;
      }

      public List getVariants() {
         return this.listVariants;
      }
   }

   public static class Deserializer implements JsonDeserializer {
      public ModelBlockDefinition deserializeModelBlockDefinition(JsonElement elem, Type type, JsonDeserializationContext context) throws JsonParseException {
         List list = this.parseVariantsList(context, elem.getAsJsonObject());
         return new ModelBlockDefinition((Collection)list);
      }

      public Object deserialize(JsonElement elem, Type type, JsonDeserializationContext context) throws JsonParseException {
         return this.deserializeModelBlockDefinition(elem, type, context);
      }

      public List parseVariantsList(JsonDeserializationContext context, JsonObject json) {
         JsonObject jsonObject = JsonUtils.getJsonObject(json, "variants");
         ArrayList list = Lists.newArrayList();

         for (Entry entry : jsonObject.entrySet()) {
            list.add(this.parseVariants(context, entry));
         }

         return list;
      }

      public ModelBlockDefinition.Variants parseVariants(JsonDeserializationContext context, Entry entry) {
         String key = (String)entry.getKey();
         ArrayList list = Lists.newArrayList();
         JsonElement value = (JsonElement)entry.getValue();
         if (value.isJsonArray()) {
            for (JsonElement elem : value.getAsJsonArray()) {
               list.add((ModelBlockDefinition.Variant)context.deserialize(elem, ModelBlockDefinition.Variant.class));
            }
         } else {
            list.add((ModelBlockDefinition.Variant)context.deserialize(value, ModelBlockDefinition.Variant.class));
         }

         return new ModelBlockDefinition.Variants(key, list);
      }
   }

   public static class MissingVariantException extends RuntimeException {
      public final ModelBlockDefinition modelBlockDefinition;

      public MissingVariantException(ModelBlockDefinition modelBlockDefinition2) {
         this.modelBlockDefinition = modelBlockDefinition2;
      }
   }
}
