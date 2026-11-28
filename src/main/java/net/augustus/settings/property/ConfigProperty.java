package net.augustus.settings.property;

import net.augustus.config.NamedConfigComponent;
import net.augustus.utils.UnaryFunction;
import com.google.gson.JsonObject;
import net.augustus.modules.FeatureBase;

public interface ConfigProperty extends NamedConfigComponent {
   FeatureBase getFeatureBase();

   void deserialize(ConfigSerializerProvider configSerializerProvider, JsonObject json, boolean flag, UnaryFunction unaryFunction);

   JsonObject serialize(ConfigSerializerProvider configSerializerProvider, JsonObject json, UnaryFunction unaryFunction);

   void setFeatureBase(FeatureBase featureBase);
}
