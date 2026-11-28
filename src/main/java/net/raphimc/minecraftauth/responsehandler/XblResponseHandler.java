package net.raphimc.minecraftauth.responsehandler;

import com.google.gson.JsonObject;
import java.io.IOException;
import java.util.Optional;
import net.lenni0451.commons.httpclient.HttpResponse;
import net.raphimc.minecraftauth.responsehandler.exception.XblRequestException;

public class XblResponseHandler extends JsonHttpResponseHandler {
   @Override
   public JsonObject handle(HttpResponse response) throws IOException {
      if (response.getStatusCode() >= 300) {
         Optional<String> xErrHeader = response.getFirstHeader("X-Err");
         if (xErrHeader.isPresent()) {
            throw new XblRequestException(response, Long.parseLong(xErrHeader.get()));
         }
      }

      return super.handle(response);
   }

   @Override
   protected void handleJsonError(HttpResponse response, JsonObject obj) throws IOException {
      if (obj.has("XErr")) {
         throw new XblRequestException(response, obj.get("XErr").getAsLong());
      }
   }
}
