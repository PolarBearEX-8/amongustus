package net.augustus.settings.property;

import net.augustus.utils.UnaryFunction;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.util.Map.Entry;
import java.util.function.Consumer;
import java.util.function.Supplier;
import net.augustus.modules.FeatureBase;

public abstract class NestedFeatureProperty extends FeatureBase implements ConfigProperty {
   private FeatureBase featureBase;
   private Supplier childFactory;
   private Consumer onChildAdded;

   public abstract Supplier getEntryFactory();

   public NestedFeatureProperty(String str) {
      super(str);
      this.onChildAdded = null;
      this.childFactory = null;
      this.featureBase = null;
   }

   public NestedFeatureProperty() {
      super(null);
      this.onChildAdded = null;
      this.childFactory = null;
   }

   private static void serializeFilteredProperty(UnaryFunction unaryFunction, ConfigSerializerProvider configSerializerProvider, JsonObject json, ConfigProperty configProperty) {
      if ((Boolean)unaryFunction.apply(configProperty)) {
         configProperty.serialize(configSerializerProvider, json, unaryFunction);
      }
   }

   public void setChildFactory(Supplier supplier) {
      this.childFactory = supplier;
   }

   @Override
   public void deserialize(ConfigSerializerProvider configSerializerProvider, JsonObject json, boolean flag, UnaryFunction unaryFunction) {
      JsonObject targetJson;
      if (this.getName() != null) {
         JsonElement elem = json.get(this.getName());
         if (elem == null || !elem.isJsonObject()) {
            return;
         }
         targetJson = elem.getAsJsonObject();
      } else {
         targetJson = json;
      }

      for (Entry entry : targetJson.entrySet()) {
         JsonElement value = (JsonElement)entry.getValue();
         if (!value.isJsonObject()) {
            continue;
         }
         ConfigProperty configProperty = (ConfigProperty)this.getEntryFactory().get();
         configProperty.deserialize(configSerializerProvider, value.getAsJsonObject(), flag, unaryFunction);
         this.registerEntry(() -> configProperty);
      }
   }

   @Override
   public FeatureBase getFeatureBase() {
      return this.featureBase;
   }

   public void setOnChildAdded(Consumer consumer) {
      this.onChildAdded = consumer;
   }

   public abstract void registerEntry(Supplier supplier);

   @Override
   public JsonObject serialize(ConfigSerializerProvider configSerializerProvider, JsonObject json, UnaryFunction unaryFunction) {
      JsonObject serialized = new JsonObject();
      this.getProperties().forEach(obj -> serializeFilteredProperty(unaryFunction, configSerializerProvider, serialized, (ConfigProperty)obj));
      if (this.getName() == null) {
         return serialized;
      }
      json.add(this.getName(), serialized);
      return json;
   }

   public NestedFeatureProperty(long l, FeatureBase featureBase2, String str) {
      super(str);
      this.onChildAdded = null;
      this.childFactory = null;
      this.featureBase = featureBase2;
      if (featureBase2 != null) {
         featureBase2.addProperty(this);
      }
   }

   @Override
   public void setFeatureBase(FeatureBase featureBase2) {
      this.featureBase = featureBase2;
   }
}
