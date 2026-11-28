package net.augustus.settings.property;

import net.augustus.settings.property.serializer.ArrayPropertySerializer;
import net.augustus.utils.UnaryFunction;
import com.google.gson.JsonObject;
import net.augustus.modules.FeatureBase;

public class BooleanArrayProperty extends AbstractConfigProperty {

   public void deserialize(ConfigSerializerProvider configSerializerProvider, JsonObject json, boolean flag, UnaryFunction unaryFunction) {
      this.applyValue(configSerializerProvider.getBooleanArrayCodec().readArray(this.getName(), flag ? (Boolean[])this.getDefaultValue() : (Boolean[])this.getValue(), json));
   }

   public BooleanArrayProperty(FeatureBase featureBase2, String str, Boolean[] bools) {
       super(featureBase2, str, bools);
   }

   public BooleanArrayProperty(FeatureBase featureBase2, String str, Boolean[] bools, Boolean[] bools2) {
       super(featureBase2, str, bools, bools2);
   }

   @Override
   public JsonObject serialize(ConfigSerializerProvider configSerializerProvider, JsonObject json, UnaryFunction unaryFunction) {
      super.serialize(configSerializerProvider, json, unaryFunction);
      configSerializerProvider.getBooleanArrayCodec().writeArray(this.getName(), (Object[])this.getValue(), json);
      return json;
   }
}
