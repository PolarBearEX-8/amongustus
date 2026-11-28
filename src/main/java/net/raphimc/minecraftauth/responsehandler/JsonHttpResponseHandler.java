package net.raphimc.minecraftauth.responsehandler;

import com.google.gson.JsonObject;
import java.io.IOException;
import net.lenni0451.commons.httpclient.HttpResponse;
import net.lenni0451.commons.httpclient.constants.ContentTypes;
import net.lenni0451.commons.httpclient.handler.HttpResponseHandler;
import net.raphimc.minecraftauth.responsehandler.exception.InformativeHttpRequestException;
import net.raphimc.minecraftauth.util.JsonUtil;

public abstract class JsonHttpResponseHandler implements HttpResponseHandler<JsonObject> {
   public JsonObject handle(HttpResponse response) throws IOException {
      if (response.getContentAsString().isEmpty() && response.getStatusCode() == 204) {
         return null;
      } else if (response.getContentAsString().isEmpty() && response.getStatusCode() >= 300) {
         throw new InformativeHttpRequestException(response, "Empty response");
      } else if (!response.getContentType().orElse(ContentTypes.TEXT_PLAIN).getMimeType().equals(ContentTypes.APPLICATION_JSON.getMimeType())) {
         throw new InformativeHttpRequestException(response, "Wrong content type");
      } else {
         JsonObject obj = (JsonObject)JsonUtil.parseString(response.getContentAsString());
         if (response.getStatusCode() >= 300) {
            this.handleJsonError(response, obj);
            throw new InformativeHttpRequestException(response, response.getContentAsString());
         } else {
            return obj;
         }
      }
   }

   protected abstract void handleJsonError(HttpResponse httpResponse, JsonObject json) throws IOException;
}
