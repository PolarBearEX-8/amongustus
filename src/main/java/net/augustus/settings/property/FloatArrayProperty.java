package net.augustus.settings.property;

import net.augustus.settings.property.serializer.ArrayPropertySerializer;
import net.augustus.utils.UnaryFunction;
import com.google.gson.JsonObject;
import net.augustus.modules.FeatureBase;

public class FloatArrayProperty extends AbstractConfigProperty {

   public void deserialize(ConfigSerializerProvider configSerializerProvider, JsonObject json, boolean flag, UnaryFunction unaryFunction) {
      this.applyValue(configSerializerProvider.getFloatArrayCodec().readArray(this.getName(), flag ? (Float[])this.getDefaultValue() : (Float[])this.getValue(), json));
   }

   @Override
   public JsonObject serialize(ConfigSerializerProvider configSerializerProvider, JsonObject json, UnaryFunction unaryFunction) {
      super.serialize(configSerializerProvider, json, unaryFunction);
      configSerializerProvider.getFloatArrayCodec().writeArray(this.getName(), (Object[])this.getValue(), json);
      return json;
   }

   public FloatArrayProperty(FeatureBase featureBase2, String str, Float[] floats, Float[] floats2) {
       super(featureBase2, str, floats, floats2);
   }

   public FloatArrayProperty(FeatureBase featureBase2, String str, Float[] floats) {
       super(featureBase2, str, floats);
   }
}
