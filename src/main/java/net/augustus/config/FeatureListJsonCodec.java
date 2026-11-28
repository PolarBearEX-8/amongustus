package net.augustus.config;

import net.augustus.settings.property.codec.JsonCodecRegistryHolder;
import net.augustus.settings.property.serializer.GsonConfigSerializerProvider;
import net.augustus.utils.UnaryFunction;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.util.Arrays;
import java.util.List;
import net.augustus.modules.FeatureBase;

public class FeatureListJsonCodec implements FeatureConfigSerializer {
   private List features;

   @Override
   public boolean deserialize(JsonElement elem, boolean flag, UnaryFunction unaryFunction) {
      return this.deserializeFromObject((JsonObject)elem, flag, unaryFunction);
   }

   public void setFeatures2(FeatureBase[] featureBases) {
      this.features = Arrays.asList(featureBases);
   }

   public void setFeatures(List list) {
      this.features = list;
   }

   private static void deserializeFeature(JsonObject json, boolean flag, UnaryFunction unaryFunction, FeatureBase featureBase) {
      JsonElement elem = json.get(featureBase.getName());
      if (elem != null && elem.isJsonObject()) {
         JsonObject asJsonObject = elem.getAsJsonObject();
         featureBase.deserialize(JsonCodecRegistryHolder.GSON_SERIALIZER_PROVIDER, asJsonObject, flag, unaryFunction);
      }
   }

   public JsonObject serializeToObject(UnaryFunction unaryFunction) {

      try {
         JsonObject json;
         if (this.features.size() == 1) {
            json = ((FeatureBase)this.features.get(0)).serialize(JsonCodecRegistryHolder.GSON_SERIALIZER_PROVIDER, (JsonObject)null, unaryFunction);
         } else {
            json = new JsonObject();
            this.features.forEach(obj2 -> serializeFeature(unaryFunction, json, (FeatureBase)obj2));
         }

         return json;
      } catch (Exception e) {
         e.printStackTrace();
         return null;
      }
   }

   public boolean deserializeFromObject(JsonObject json, boolean flag, UnaryFunction unaryFunction) {

      try {
         if (this.features.size() == 1) {
            ((FeatureBase)this.features.get(0)).deserialize(JsonCodecRegistryHolder.GSON_SERIALIZER_PROVIDER, json, false, unaryFunction);
         } else {
            this.features.forEach(obj -> deserializeFeature(json, flag, unaryFunction, (FeatureBase)obj));
         }

         return true;
      } catch (Exception e) {
         e.printStackTrace();
         return false;
      }
   }

   private static void serializeFeature(UnaryFunction unaryFunction, JsonObject json, FeatureBase featureBase) {
      JsonObject json2 = featureBase.serialize(JsonCodecRegistryHolder.GSON_SERIALIZER_PROVIDER, (JsonObject)null, unaryFunction);
      json.add(featureBase.getName(), json2);
   }

   public FeatureListJsonCodec(List list) {
      this.features = list;
   }

   public FeatureListJsonCodec(FeatureBase[] featureBases) {
      this.features = Arrays.asList(featureBases);
   }

   public FeatureListJsonCodec() {
   }

   @Override
   public JsonElement serialize(UnaryFunction unaryFunction) {
      return this.serializeToObject(unaryFunction);
   }




}
