package net.augustus.settings.property;

import net.augustus.settings.property.codec.JsonPropertyCodec;
import net.augustus.utils.UnaryFunction;
import com.google.gson.JsonObject;
import net.augustus.modules.FeatureBase;

public class IntegerProperty extends AbstractConfigProperty {

   public IntegerProperty(FeatureBase featureBase2, String str, Integer num, Integer num2) {
       super(featureBase2, str, num, num2);
   }

   public IntegerProperty(FeatureBase featureBase2, String str, Integer num) {
       super(featureBase2, str, num);
   }

   @Override
   public JsonObject serialize(ConfigSerializerProvider configSerializerProvider, JsonObject json, UnaryFunction unaryFunction) {
      super.serialize(configSerializerProvider, json, unaryFunction);
      configSerializerProvider.getIntegerCodec().write(this.getName(), this.getValue(), json);
      return json;
   }

   public void deserialize(ConfigSerializerProvider configSerializerProvider, JsonObject json, boolean flag, UnaryFunction unaryFunction) {
      this.applyValue(configSerializerProvider.getIntegerCodec().read(this.getName(), flag ? (Integer)this.getDefaultValue() : (Integer)this.getValue(), json));
   }
}
