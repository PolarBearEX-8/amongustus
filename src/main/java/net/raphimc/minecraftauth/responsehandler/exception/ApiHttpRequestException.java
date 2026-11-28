package net.raphimc.minecraftauth.responsehandler.exception;

import lombok.Generated;
import net.lenni0451.commons.httpclient.HttpResponse;
import net.lenni0451.commons.httpclient.exceptions.HttpRequestException;

public class ApiHttpRequestException extends HttpRequestException {
   private final String error;
   private final String errorMessage;

   public ApiHttpRequestException(HttpResponse response, String error, String errorMessage) {
      super(response, "status: " + response.getStatusCode() + " " + response.getStatusMessage() + ", error: " + error + ", error message: " + errorMessage);
      this.error = error;
      this.errorMessage = errorMessage;
   }

   @Generated
   public String getError() {
      return this.error;
   }

   @Generated
   public String getErrorMessage() {
      return this.errorMessage;
   }
}
