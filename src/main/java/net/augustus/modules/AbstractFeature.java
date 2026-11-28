package net.augustus.modules;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.util.List;
import net.augustus.settings.property.ConfigProperty;
import net.augustus.settings.property.ConfigSerializerProvider;
import net.augustus.utils.UnaryFunction;

public class AbstractFeature extends FeatureBase implements ConfigProperty {
   private FeatureBase featureBase = null;

   @Override
   public JsonObject serialize(ConfigSerializerProvider configSerializerProvider, JsonObject json, UnaryFunction unaryFunction) {
      this.onBeforeSerialize();
      JsonObject json2 = new JsonObject();
      this.getProperties().forEach(obj -> {
         if ((Boolean)unaryFunction.apply(obj)) {
            ((ConfigProperty)obj).serialize(configSerializerProvider, json2, unaryFunction);
         }
      });
      if (this.getName() != null && json != null) {
         json.add(this.getName(), json2);
         return json;
      } else {
         return json2;
      }
   }

   public void onAfterDeserialize() {
   }

   public void onBeforeSerialize() {
   }

   @Override
   public void deserialize(ConfigSerializerProvider configSerializerProvider, JsonObject json, boolean flag, UnaryFunction unaryFunction) {
      JsonObject json2;
      if (this.getName() != null) {
         JsonElement elem = json.get(this.getName());
         if (elem == null) {
            json2 = json;
         } else {
            if (!elem.isJsonObject()) {
               return;
            }

            json2 = elem.getAsJsonObject();
         }
      } else {
         json2 = json;
      }

      for (int i = this.getProperties().size() - 1; i >= 0; i--) {
         ConfigProperty configProperty = (ConfigProperty)this.getProperties().get(i);
         if ((Boolean)unaryFunction.apply(configProperty)) {
            configProperty.deserialize(configSerializerProvider, json2, flag, unaryFunction);
         }
      }

      this.onAfterDeserialize();
   }

   @Override
   public FeatureBase getFeatureBase() {
      return this.featureBase;
   }

   public AbstractFeature(FeatureBase featureBase2, String str) {
      super(str);
      this.featureBase = featureBase2;
      if (featureBase2 != null) {
         featureBase2.addProperty(this);
      }
   }

   @Override
   public void setFeatureBase(FeatureBase featureBase2) {
      this.featureBase = featureBase2;
   }

   public AbstractFeature(String str) {
      super(str);
   }
}
