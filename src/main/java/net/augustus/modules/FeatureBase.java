package net.augustus.modules;

import java.util.ArrayList;
import java.util.List;
import net.augustus.settings.property.ConfigProperty;
import net.augustus.settings.property.ConfigurableFeature;

public abstract class FeatureBase implements ConfigurableFeature {
   private final String configKey;
   private final List properties = new ArrayList();

   @Override
   public List getProperties() {
      return this.properties;
   }

   @Override
   public String getName() {
      return this.configKey;
   }

   @Override
   public void addProperty(ConfigProperty configProperty) {
      this.properties.add(configProperty);
   }

   public FeatureBase(String str) {
      this.configKey = str;
   }
}
