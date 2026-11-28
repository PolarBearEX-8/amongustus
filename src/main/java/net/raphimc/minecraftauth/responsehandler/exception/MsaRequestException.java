package net.raphimc.minecraftauth.responsehandler.exception;

import net.lenni0451.commons.httpclient.HttpResponse;

public class MsaRequestException extends ApiHttpRequestException {
   public MsaRequestException(HttpResponse response, String error, String errorMessage) {
      super(response, error, errorMessage);
   }
}
