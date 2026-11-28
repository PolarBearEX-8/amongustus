package net.augustus.settings.property;

import net.augustus.config.NamedConfigComponent;
import net.augustus.utils.UnaryFunction;
import com.google.gson.JsonObject;
import java.util.List;

public interface ConfigurableFeature extends NamedConfigComponent {
   List getProperties();

   void deserialize(ConfigSerializerProvider configSerializerProvider, JsonObject json, boolean flag, UnaryFunction unaryFunction);

   void addProperty(ConfigProperty configProperty);

   JsonObject serialize(ConfigSerializerProvider configSerializerProvider, JsonObject json, UnaryFunction unaryFunction);
}
