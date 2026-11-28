package net.augustus.config;

import com.google.gson.JsonObject;

public interface ModuleConfigCodec {
   JsonObject encodeConfig(String str, String str2, boolean flag);

   String[] readHeaderInfo(JsonObject json);

   void applyConfig(JsonObject json);
}
