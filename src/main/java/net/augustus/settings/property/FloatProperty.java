package net.augustus.settings.property;

import net.augustus.settings.property.codec.JsonPropertyCodec;
import net.augustus.utils.UnaryFunction;
import com.google.gson.JsonObject;
import net.augustus.modules.FeatureBase;

public class FloatProperty extends AbstractConfigProperty {

   public FloatProperty(FeatureBase featureBase2, String str, Float f) {
       super(featureBase2, str, f);
   }

   @Override
   public JsonObject serialize(ConfigSerializerProvider configSerializerProvider, JsonObject json, UnaryFunction unaryFunction) {
      super.serialize(configSerializerProvider, json, unaryFunction);
      configSerializerProvider.getFloatCodec().write(this.getName(), this.getValue(), json);
      return json;
   }

   public FloatProperty(FeatureBase featureBase2, String str, Float f, Float f2) {
       super(featureBase2, str, f, f2);
   }

   public void deserialize(ConfigSerializerProvider configSerializerProvider, JsonObject json, boolean flag, UnaryFunction unaryFunction) {
      this.applyValue(configSerializerProvider.getFloatCodec().read(this.getName(), flag ? (Float)this.getDefaultValue() : (Float)this.getValue(), json));
   }
}
