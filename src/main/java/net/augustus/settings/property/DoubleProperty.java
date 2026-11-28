package net.augustus.settings.property;

import net.augustus.settings.property.codec.JsonPropertyCodec;
import net.augustus.utils.UnaryFunction;
import com.google.gson.JsonObject;
import net.augustus.modules.FeatureBase;

public class DoubleProperty extends AbstractConfigProperty {

   public void deserialize(ConfigSerializerProvider configSerializerProvider, JsonObject json, boolean flag, UnaryFunction unaryFunction) {
      this.applyValue(configSerializerProvider.getDoubleCodec().read(this.getName(), flag ? (Double)this.getDefaultValue() : (Double)this.getValue(), json));
   }

   public DoubleProperty(FeatureBase featureBase2, String str, double d, double d2) {
       super(featureBase2, str, d, d2);
   }

   public DoubleProperty(FeatureBase featureBase2, String str, double d) {
       super(featureBase2, str, d);
   }

   @Override
   public JsonObject serialize(ConfigSerializerProvider configSerializerProvider, JsonObject json, UnaryFunction unaryFunction) {
      super.serialize(configSerializerProvider, json, unaryFunction);
      configSerializerProvider.getDoubleCodec().write(this.getName(), this.getValue(), json);
      return json;
   }
}
