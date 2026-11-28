package net.augustus.settings.property;

import net.augustus.settings.property.serializer.ArrayPropertySerializer;
import net.augustus.utils.UnaryFunction;
import com.google.gson.JsonObject;
import net.augustus.modules.FeatureBase;

public class IntegerArrayProperty extends AbstractConfigProperty {

   public void deserialize(ConfigSerializerProvider configSerializerProvider, JsonObject json, boolean flag, UnaryFunction unaryFunction) {
      this.applyValue(configSerializerProvider.getIntegerArrayCodec().readArray(this.getName(), flag ? (Integer[])this.getDefaultValue() : (Integer[])this.getValue(), json));
   }

   public IntegerArrayProperty(FeatureBase featureBase2, String str, Integer[] nums, Integer[] nums2) {
       super(featureBase2, str, nums, nums2);
   }

   public IntegerArrayProperty(FeatureBase featureBase2, String str, Integer[] nums) {
       super(featureBase2, str, nums);
   }

   @Override
   public JsonObject serialize(ConfigSerializerProvider configSerializerProvider, JsonObject json, UnaryFunction unaryFunction) {
      super.serialize(configSerializerProvider, json, unaryFunction);
      configSerializerProvider.getIntegerArrayCodec().writeArray(this.getName(), (Object[])this.getValue(), json);
      return json;
   }
}
