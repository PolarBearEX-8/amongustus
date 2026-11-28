package net.augustus.config;

import net.augustus.utils.UnaryFunction;
import com.google.gson.JsonElement;

public interface FeatureConfigSerializer {
   JsonElement serialize(UnaryFunction unaryFunction);

   boolean deserialize(JsonElement elem, boolean flag, UnaryFunction unaryFunction);
}
