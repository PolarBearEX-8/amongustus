package net.lenni0451.commons.httpclient.requests.impl;

import java.net.MalformedURLException;
import java.net.URL;
import net.lenni0451.commons.httpclient.requests.HttpRequest;

public class GetRequest extends HttpRequest {
   public GetRequest(String url) throws MalformedURLException {
      super("GET", url);
   }

   public GetRequest(URL url) {
      super("GET", url);
   }
}
