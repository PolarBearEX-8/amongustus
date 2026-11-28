package net.lenni0451.commons.httpclient.requests.impl;

import java.net.MalformedURLException;
import java.net.URL;
import net.lenni0451.commons.httpclient.requests.HttpContentRequest;

public class PostRequest extends HttpContentRequest {
   public PostRequest(String url) throws MalformedURLException {
      super("POST", url);
   }

   public PostRequest(URL url) {
      super("POST", url);
   }
}
