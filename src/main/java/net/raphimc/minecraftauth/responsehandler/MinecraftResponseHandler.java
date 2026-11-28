package net.raphimc.minecraftauth.responsehandler;

import com.google.gson.JsonObject;
import java.io.IOException;
import net.lenni0451.commons.httpclient.HttpResponse;
import net.raphimc.minecraftauth.responsehandler.exception.InformativeHttpRequestException;
import net.raphimc.minecraftauth.responsehandler.exception.MinecraftRequestException;

public class MinecraftResponseHandler extends JsonHttpResponseHandler {
   @Override
   public void handleJsonError(HttpResponse response, JsonObject obj) throws IOException {
      if (obj.has("error") && obj.has("errorMessage")) {
         throw new MinecraftRequestException(response, obj.get("error").getAsString(), obj.get("errorMessage").getAsString());
      } else if (obj.has("errorMessage")) {
         throw new InformativeHttpRequestException(response, obj.get("errorMessage").getAsString());
      }
   }
}
