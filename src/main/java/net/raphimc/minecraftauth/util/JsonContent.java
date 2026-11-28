package net.raphimc.minecraftauth.util;

import com.google.gson.JsonObject;
import net.lenni0451.commons.httpclient.constants.ContentTypes;
import net.lenni0451.commons.httpclient.content.impl.StringContent;

public class JsonContent extends StringContent {
   public JsonContent(JsonObject jsonObject) {
      super(ContentTypes.APPLICATION_JSON, jsonObject.toString());
   }
}
