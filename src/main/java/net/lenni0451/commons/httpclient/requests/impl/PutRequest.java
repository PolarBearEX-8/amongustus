package net.lenni0451.commons.httpclient.requests.impl;

import java.net.MalformedURLException;
import java.net.URL;
import net.lenni0451.commons.httpclient.requests.HttpContentRequest;

public class PutRequest extends HttpContentRequest {
   public PutRequest(String url) throws MalformedURLException {
      super("PUT", url);
   }

   public PutRequest(URL url) {
      super("PUT", url);
   }
}
