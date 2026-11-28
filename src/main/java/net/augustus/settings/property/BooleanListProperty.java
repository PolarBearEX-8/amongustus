package net.augustus.settings.property;

import net.augustus.settings.property.codec.JsonListPropertyCodec;
import net.augustus.utils.UnaryFunction;
import com.google.gson.JsonObject;
import java.util.List;
import net.augustus.modules.FeatureBase;

public class BooleanListProperty extends AbstractConfigProperty {

   public BooleanListProperty(FeatureBase featureBase2, String str, List list) {
       super(featureBase2, str, list);
   }

   public BooleanListProperty(FeatureBase featureBase2, String str, List list, List list2) {
       super(featureBase2, str, list, list2);
   }

   @Override
   public JsonObject serialize(ConfigSerializerProvider configSerializerProvider, JsonObject json, UnaryFunction unaryFunction) {
      super.serialize(configSerializerProvider, json, unaryFunction);
      configSerializerProvider.getBooleanListCodec().writeList(this.getName(), (List)this.getValue(), json);
      return json;
   }

   public void deserialize(ConfigSerializerProvider configSerializerProvider, JsonObject json, boolean flag, UnaryFunction unaryFunction) {
      this.applyValue(configSerializerProvider.getBooleanListCodec().readList(this.getName(), flag ? (List)this.getDefaultValue() : (List)this.getValue(), json));
   }
}
