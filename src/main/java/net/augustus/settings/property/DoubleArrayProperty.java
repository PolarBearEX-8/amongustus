package net.augustus.settings.property;

import net.augustus.settings.property.serializer.ArrayPropertySerializer;
import net.augustus.utils.UnaryFunction;
import com.google.gson.JsonObject;
import net.augustus.modules.FeatureBase;

public class DoubleArrayProperty extends AbstractConfigProperty {

   public DoubleArrayProperty(FeatureBase featureBase2, String str, Double[] doubles, Double[] doubles2) {
       super(featureBase2, str, doubles, doubles2);
   }

   public DoubleArrayProperty(FeatureBase featureBase2, String str, Double[] doubles) {
       super(featureBase2, str, doubles);
   }

   @Override
   public JsonObject serialize(ConfigSerializerProvider configSerializerProvider, JsonObject json, UnaryFunction unaryFunction) {
      super.serialize(configSerializerProvider, json, unaryFunction);
      configSerializerProvider.getDoubleArrayCodec().writeArray(this.getName(), (Object[])this.getValue(), json);
      return json;
   }

   public void deserialize(ConfigSerializerProvider configSerializerProvider, JsonObject json, boolean flag, UnaryFunction unaryFunction) {
      this.applyValue(configSerializerProvider.getDoubleArrayCodec().readArray(this.getName(), flag ? (Double[])this.getDefaultValue() : (Double[])this.getValue(), json));
   }
}
