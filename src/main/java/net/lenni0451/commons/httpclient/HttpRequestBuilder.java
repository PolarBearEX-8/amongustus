package net.lenni0451.commons.httpclient;

import java.net.MalformedURLException;
import java.net.URL;
import net.lenni0451.commons.httpclient.requests.HttpContentRequest;
import net.lenni0451.commons.httpclient.requests.HttpRequest;
import net.lenni0451.commons.httpclient.requests.impl.DeleteRequest;
import net.lenni0451.commons.httpclient.requests.impl.GetRequest;
import net.lenni0451.commons.httpclient.requests.impl.HeadRequest;
import net.lenni0451.commons.httpclient.requests.impl.PostRequest;
import net.lenni0451.commons.httpclient.requests.impl.PutRequest;

public interface HttpRequestBuilder {
   default <T extends HttpRequest> T bind(T request) {
      return request;
   }

   default GetRequest get(String url) throws MalformedURLException {
      return this.bind(new GetRequest(url));
   }

   default GetRequest get(URL url) {
      return this.bind(new GetRequest(url));
   }

   default HeadRequest head(String url) throws MalformedURLException {
      return this.bind(new HeadRequest(url));
   }

   default HeadRequest head(URL url) {
      return this.bind(new HeadRequest(url));
   }

   default DeleteRequest delete(String url) throws MalformedURLException {
      return this.bind(new DeleteRequest(url));
   }

   default DeleteRequest delete(URL url) {
      return this.bind(new DeleteRequest(url));
   }

   default PostRequest post(String url) throws MalformedURLException {
      return this.bind(new PostRequest(url));
   }

   default PostRequest post(URL url) {
      return this.bind(new PostRequest(url));
   }

   default PutRequest put(String url) throws MalformedURLException {
      return this.bind(new PutRequest(url));
   }

   default PutRequest put(URL url) {
      return this.bind(new PutRequest(url));
   }

   default HttpRequest request(String method, String url) throws MalformedURLException {
      return this.bind(new HttpRequest(method, url));
   }

   default HttpRequest request(String method, URL url) {
      return this.bind(new HttpRequest(method, url));
   }

   default HttpContentRequest contentRequest(String method, String url) throws MalformedURLException {
      return this.bind(new HttpContentRequest(method, url));
   }

   default HttpContentRequest contentRequest(String method, URL url) {
      return this.bind(new HttpContentRequest(method, url));
   }
}
