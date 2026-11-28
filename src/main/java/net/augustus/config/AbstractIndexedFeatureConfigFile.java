package net.augustus.config;

import net.augustus.settings.Setting;
import net.augustus.settings.property.ConfigProperty;
import net.augustus.settings.property.codec.JsonCodecRegistryHolder;
import net.augustus.settings.property.serializer.GsonConfigSerializerProvider;
import net.augustus.utils.UnaryFunction;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.util.List;
import java.util.Map.Entry;
import java.util.function.Supplier;
import net.augustus.modules.FeatureBase;

public abstract class AbstractIndexedFeatureConfigFile extends JsonConfigFile {
   public final List features;

   public UnaryFunction getLoadFilter() {
      return obj -> acceptLoadProperty((ConfigProperty)obj);
   }

   private static Boolean acceptLoadProperty(ConfigProperty configProperty) {
      return true;
   }

   private static Boolean acceptSaveProperty(ConfigProperty configProperty) {
      return true;
   }

   @Override
   public void deserialize(JsonElement elem) {
      this.deserializeIndexed((JsonObject)elem);
   }

   public JsonObject serializeIndexed() {
      JsonObject json = new JsonObject();
      for (int i = 0; i < this.features.size(); i++) {
         FeatureBase featureBase = (FeatureBase)this.features.get(i);
         JsonObject serialized = featureBase.serialize(JsonCodecRegistryHolder.GSON_SERIALIZER_PROVIDER, null, this.getSaveFilter());
         json.add("E_" + i, serialized);
      }
      return json;
   }

   public AbstractIndexedFeatureConfigFile(List list) {
      this.features = list;
   }

   public void deserializeIndexed(JsonObject json) {
      for (Entry entry : json.entrySet()) {
         JsonElement value = (JsonElement)entry.getValue();
         if (!value.isJsonObject()) {
            continue;
         }
         FeatureBase featureBase = (FeatureBase)this.createEntrySupplier().get();
         featureBase.deserialize(JsonCodecRegistryHolder.GSON_SERIALIZER_PROVIDER, value.getAsJsonObject(), false, this.getLoadFilter());
         this.onEntryLoaded(() -> featureBase);
      }
   }

   public abstract Supplier createEntrySupplier();

   public abstract void onEntryLoaded(Supplier supplier);

   @Override
   public JsonElement serialize() {
      return this.serializeIndexed();
   }

   @Override
   public Class getJsonType() {
      return JsonObject.class;
   }

   public UnaryFunction getSaveFilter() {
      return obj -> acceptSaveProperty((ConfigProperty)obj);
   }

}
