package net.augustus.settings.property;

import net.augustus.settings.property.serializer.ArrayPropertySerializer;
import net.augustus.utils.UnaryFunction;
import com.google.gson.JsonObject;
import net.augustus.modules.FeatureBase;

public class StringArrayProperty extends AbstractConfigProperty {

   public void deserialize(ConfigSerializerProvider configSerializerProvider, JsonObject json, boolean flag, UnaryFunction unaryFunction) {
      this.applyValue(configSerializerProvider.getStringArrayCodec().readArray(this.getName(), flag ? (String[])this.getDefaultValue() : (String[])this.getValue(), json));
   }

   public StringArrayProperty(FeatureBase featureBase2, String str, String[] strings, String[] strings2) {
       super(featureBase2, str, strings, strings2);
   }

   public StringArrayProperty(FeatureBase featureBase2, String str, String[] strings) {
       super(featureBase2, str, strings);
   }

   @Override
   public JsonObject serialize(ConfigSerializerProvider configSerializerProvider, JsonObject json, UnaryFunction unaryFunction) {
      super.serialize(configSerializerProvider, json, unaryFunction);
      configSerializerProvider.getStringArrayCodec().writeArray(this.getName(), (Object[])this.getValue(), json);
      return json;
   }
}
