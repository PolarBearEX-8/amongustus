package net.raphimc.minecraftauth.responsehandler;

import com.google.gson.JsonObject;
import java.io.IOException;
import net.lenni0451.commons.httpclient.HttpResponse;
import net.raphimc.minecraftauth.responsehandler.exception.PlayFabRequestException;

public class PlayFabResponseHandler extends JsonHttpResponseHandler {
   @Override
   protected void handleJsonError(HttpResponse response, JsonObject obj) throws IOException {
      if (obj.has("error") && obj.has("errorMessage")) {
         throw new PlayFabRequestException(response, obj.get("error").getAsString(), obj.get("errorMessage").getAsString());
      }
   }
}
