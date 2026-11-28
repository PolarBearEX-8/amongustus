package net.augustus.settings.property;

import net.augustus.settings.property.codec.JsonPropertyCodec;
import net.augustus.utils.UnaryFunction;
import com.google.gson.JsonObject;
import net.augustus.modules.FeatureBase;

public class StringProperty extends AbstractConfigProperty {

   @Override
   public JsonObject serialize(ConfigSerializerProvider configSerializerProvider, JsonObject json, UnaryFunction unaryFunction) {
      super.serialize(configSerializerProvider, json, unaryFunction);
      configSerializerProvider.getStringCodec().write(this.getName(), this.getValue(), json);
      return json;
   }

   public StringProperty(FeatureBase featureBase2, String str, String str2, String str3) {
       super(featureBase2, str, str2, str3);
   }

   public StringProperty(FeatureBase featureBase2, String str, String str2) {
       super(featureBase2, str, str2);
   }

   public void deserialize(ConfigSerializerProvider configSerializerProvider, JsonObject json, boolean flag, UnaryFunction unaryFunction) {
      this.applyValue(configSerializerProvider.getStringCodec().read(this.getName(), flag ? (String)this.getDefaultValue() : (String)this.getValue(), json));
   }
}
