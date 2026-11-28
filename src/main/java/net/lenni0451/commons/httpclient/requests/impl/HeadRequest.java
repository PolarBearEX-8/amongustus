package net.lenni0451.commons.httpclient.requests.impl;

import java.net.MalformedURLException;
import java.net.URL;
import net.lenni0451.commons.httpclient.requests.HttpRequest;

public class HeadRequest extends HttpRequest {
   public HeadRequest(String url) throws MalformedURLException {
      super("HEAD", url);
   }

   public HeadRequest(URL url) {
      super("HEAD", url);
   }
}
