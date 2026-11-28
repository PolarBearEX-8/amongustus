package net.lenni0451.commons.httpclient.requests.impl;

import java.net.MalformedURLException;
import java.net.URL;
import net.lenni0451.commons.httpclient.requests.HttpRequest;

public class DeleteRequest extends HttpRequest {
   public DeleteRequest(String url) throws MalformedURLException {
      super("DELETE", url);
   }

   public DeleteRequest(URL url) {
      super("DELETE", url);
   }
}
