package net.raphimc.minecraftauth.responsehandler;

import com.google.gson.JsonObject;
import java.io.IOException;
import net.lenni0451.commons.httpclient.HttpResponse;
import net.raphimc.minecraftauth.responsehandler.exception.InformativeHttpRequestException;

public class MinecraftEduServicesResponseHandler extends JsonHttpResponseHandler {
   @Override
   protected void handleJsonError(HttpResponse response, JsonObject obj) throws IOException {
      if (obj.has("reason")) {
         throw new InformativeHttpRequestException(response, obj.get("reason").getAsString());
      }
   }
}
