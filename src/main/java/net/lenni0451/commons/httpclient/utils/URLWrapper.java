package net.lenni0451.commons.httpclient.utils;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Map.Entry;

public class URLWrapper {
   private String protocol;
   private String host;
   private int port = -1;
   private String path;
   private String query;
   private String userInfo;
   private String reference;

   public static URLWrapper empty() {
      return new URLWrapper();
   }

   public static URLWrapper of(String url) throws MalformedURLException {
      return new URLWrapper(url);
   }

   public static URLWrapper of(URL url) {
      return new URLWrapper(url);
   }

   public static URLWrapper of(URI uri) {
      return new URLWrapper(uri);
   }

   public URLWrapper() {
   }

   public URLWrapper(String url) throws MalformedURLException {
      this(new URL(url));
   }

   public URLWrapper(URL url) {
      this.protocol = url.getProtocol();
      this.host = url.getHost();
      this.port = url.getPort();
      this.path = url.getPath();
      this.query = url.getQuery();
      this.userInfo = url.getUserInfo();
      this.reference = url.getRef();
   }

   public URLWrapper(URI uri) {
      this.protocol = uri.getScheme();
      this.host = uri.getHost();
      this.port = uri.getPort();
      this.path = uri.getPath();
      this.query = uri.getQuery();
      this.userInfo = uri.getUserInfo();
      this.reference = uri.getFragment();
   }

   public String getProtocol() {
      return this.protocol;
   }

   public URLWrapper setProtocol(String protocol) {
      this.protocol = protocol;
      return this;
   }

   public String getHost() {
      return this.host;
   }

   public URLWrapper setHost(String host) {
      this.host = host;
      return this;
   }

   public int getPort() {
      return this.port;
   }

   public URLWrapper setPort(int port) {
      this.port = port;
      return this;
   }

   public String getPath() {
      return this.path;
   }

   public URLWrapper setPath(String path) {
      this.path = path;
      return this;
   }

   public String getQuery() {
      return this.query;
   }

   public URLWrapper setQuery(String query) {
      this.query = query;
      return this;
   }

   public URLWrapper.QueryWrapper wrapQuery() {
      return new URLWrapper.QueryWrapper();
   }

   public String getUserInfo() {
      return this.userInfo;
   }

   public URLWrapper setUserInfo(String userInfo) {
      this.userInfo = userInfo;
      return this;
   }

   public String getReference() {
      return this.reference;
   }

   public URLWrapper setReference(String reference) {
      this.reference = reference;
      return this;
   }

   public URL toURL() throws MalformedURLException {
      return new URL(this.toString());
   }

   public URI toURI() {
      return URI.create(this.toString());
   }

   @Override
   public String toString() {
      String url = this.protocol + "://";
      if (this.userInfo != null) {
         url = url + this.userInfo + "@";
      }

      url = url + this.host;
      if (this.port >= 0) {
         url = url + ":" + this.port;
      }

      if (this.path != null) {
         url = url + this.path;
      }

      if (this.query != null) {
         url = url + "?" + this.query;
      }

      if (this.reference != null) {
         url = url + "#" + this.reference;
      }

      return url;
   }

   public class QueryWrapper {
      private final Map<String, String> queries = new HashMap<>();

      private QueryWrapper() {
         String query = URLWrapper.this.getQuery();
         if (query != null) {
            for (String queryPart : query.split("&")) {
               String[] split = queryPart.split("=", 2);
               if (split.length == 2) {
                  this.queries.put(URLCoder.decode(split[0]), URLCoder.decode(split[1]));
               } else {
                  this.queries.put(URLCoder.decode(split[0]), "");
               }
            }
         }
      }

      public Map<String, String> getQueries() {
         return Collections.unmodifiableMap(this.queries);
      }

      public Optional<String> getQuery(String key) {
         return Optional.ofNullable(this.queries.get(key));
      }

      public URLWrapper.QueryWrapper setQuery(String key, String value) {
         this.queries.put(key, value);
         return this;
      }

      public URLWrapper.QueryWrapper addQueries(Map<String, String> queries) {
         this.queries.putAll(queries);
         return this;
      }

      public URLWrapper.QueryWrapper removeQuery(String key) {
         this.queries.remove(key);
         return this;
      }

      public boolean hasQuery(String key) {
         return this.queries.containsKey(key);
      }

      public URLWrapper apply() {
         StringBuilder query = new StringBuilder();

         for (Entry<String, String> entry : this.queries.entrySet()) {
            query.append(URLCoder.encode(entry.getKey())).append("=").append(URLCoder.encode(entry.getValue())).append("&");
         }

         if (query.length() > 0) {
            query.deleteCharAt(query.length() - 1);
         }

         URLWrapper.this.setQuery(query.toString());
         return URLWrapper.this;
      }

      public URLWrapper discard() {
         return URLWrapper.this;
      }
   }
}
