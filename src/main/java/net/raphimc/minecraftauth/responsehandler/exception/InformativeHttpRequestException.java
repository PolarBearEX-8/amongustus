package net.raphimc.minecraftauth.responsehandler.exception;

import net.lenni0451.commons.httpclient.HttpResponse;
import net.lenni0451.commons.httpclient.exceptions.HttpRequestException;

public class InformativeHttpRequestException extends HttpRequestException {
   public InformativeHttpRequestException(HttpResponse response, String message) {
      super(response, "status: " + response.getStatusCode() + " " + response.getStatusMessage() + ", message: " + message);
   }
}
