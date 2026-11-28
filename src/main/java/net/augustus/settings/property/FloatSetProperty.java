package net.augustus.settings.property;

import net.augustus.settings.property.serializer.SetPropertySerializer;
import net.augustus.utils.UnaryFunction;
import com.google.gson.JsonObject;
import java.util.Set;
import net.augustus.modules.FeatureBase;

public class FloatSetProperty extends AbstractConfigProperty {

   @Override
   public JsonObject serialize(ConfigSerializerProvider configSerializerProvider, JsonObject json, UnaryFunction unaryFunction) {
      super.serialize(configSerializerProvider, json, unaryFunction);
      configSerializerProvider.getFloatSetCodec().writeSet(this.getName(), (Set)this.getValue(), json);
      return json;
   }

   public void deserialize(ConfigSerializerProvider configSerializerProvider, JsonObject json, boolean flag, UnaryFunction unaryFunction) {
      this.applyValue(configSerializerProvider.getFloatSetCodec().readSet(this.getName(), flag ? (Set)this.getDefaultValue() : (Set)this.getValue(), json));
   }

   public FloatSetProperty(FeatureBase featureBase2, String str, Set set) {
       super(featureBase2, str, set);
   }

   public FloatSetProperty(FeatureBase featureBase2, String str, Set set, Set set2) {
       super(featureBase2, str, set, set2);
   }
}
