package net.augustus.config;

import net.augustus.utils.UnaryFunction;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.util.Arrays;
import java.util.List;
import net.augustus.modules.FeatureBase;

public abstract class AbstractFeatureConfigFile extends JsonConfigFile {
   private final FeatureListJsonCodec codec;

   @Override
   public JsonElement serialize() {
      return this.serializeObject();
   }

   public UnaryFunction getSaveFilter() {
      return obj -> true;
   }

   @Override
   public Class getJsonType() {
      return JsonObject.class;
   }

   public AbstractFeatureConfigFile(List list) {
      this.codec = new FeatureListJsonCodec(list);
   }

   public AbstractFeatureConfigFile(FeatureBase[] featureBases) {
      this.codec = new FeatureListJsonCodec(Arrays.asList(featureBases));
   }

   public FeatureListJsonCodec getCodec() {
      return this.codec;
   }

   public void deserializeObject(JsonObject json) {
      this.codec.deserializeFromObject(json, false, this.getLoadFilter());
   }

   public UnaryFunction getLoadFilter() {
      return obj -> true;
   }

   @Override
   public void deserialize(JsonElement elem) {
      this.deserializeObject((JsonObject)elem);
   }

   public JsonObject serializeObject() {
      return this.codec.serializeToObject(this.getSaveFilter());
   }
}
