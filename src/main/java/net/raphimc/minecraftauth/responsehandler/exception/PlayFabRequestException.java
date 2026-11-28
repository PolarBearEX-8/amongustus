package net.raphimc.minecraftauth.responsehandler.exception;

import net.lenni0451.commons.httpclient.HttpResponse;

public class PlayFabRequestException extends ApiHttpRequestException {
   public PlayFabRequestException(HttpResponse response, String error, String errorMessage) {
      super(response, error, errorMessage);
   }
}
