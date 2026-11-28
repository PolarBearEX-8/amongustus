package net.raphimc.minecraftauth.responsehandler;

import com.google.gson.JsonObject;
import java.io.IOException;
import net.lenni0451.commons.httpclient.HttpResponse;
import net.raphimc.minecraftauth.responsehandler.exception.MsaRequestException;

public class MsaResponseHandler extends JsonHttpResponseHandler {
   @Override
   protected void handleJsonError(HttpResponse response, JsonObject obj) throws IOException {
      if (obj.has("error") && obj.has("error_description")) {
         throw new MsaRequestException(response, obj.get("error").getAsString(), obj.get("error_description").getAsString());
      }
   }
}
