package net.lenni0451.commons.httpclient.executor;

import java.lang.reflect.Constructor;
import net.lenni0451.commons.httpclient.HttpClient;

public enum ExecutorType {
   AUTO {
      @Override
      public RequestExecutor initExecutor(HttpClient client) {
         for (int i = values().length - 1; i >= 0; i--) {
            ExecutorType type = values()[i];
            if (!AUTO.equals(type) && type.isAvailable()) {
               RequestExecutor executor = type.makeExecutor(client);
               if (executor != null) {
                  return executor;
               }
            }
         }

         throw new IllegalStateException("Failed to find a suitable executor. This should never happen. Please report this to the developer.");
      }
   },
   URL_CONNECTION {
      @Override
      public RequestExecutor initExecutor(HttpClient client) {
         return new URLConnectionExecutor(client);
      }
   },
   REACTOR_NETTY {
      private Constructor<?> constructor;

      @Override
      protected void init() throws Throwable {
         Class.forName("reactor.netty.http.client.HttpClient");
         Class<?> executorClass = Class.forName("net.lenni0451.commons.httpclient.executor.extra.ReactorNettyExecutor");
         this.constructor = executorClass.getDeclaredConstructor(HttpClient.class);
      }

      @Override
      protected RequestExecutor initExecutor(HttpClient client) throws Throwable {
         return (RequestExecutor)this.constructor.newInstance(client);
      }
   },
   HTTP_CLIENT {
      private Constructor<?> constructor;

      @Override
      protected void init() throws Throwable {
         Class.forName("java.net.http.HttpClient");
         Class<?> executorClass = Class.forName("net.lenni0451.commons.httpclient.executor.HttpClientExecutor");
         this.constructor = executorClass.getDeclaredConstructor(HttpClient.class);
      }

      @Override
      protected RequestExecutor initExecutor(HttpClient client) throws Throwable {
         return (RequestExecutor)this.constructor.newInstance(client);
      }
   };

   private boolean available;

   private ExecutorType() {
      try {
         this.init();
         this.available = true;
      } catch (Throwable e) {
         this.available = false;
      }
   }

   public final boolean isAvailable() {
      return this.available;
   }

   public final RequestExecutor makeExecutor(HttpClient client) {
      try {
         return this.initExecutor(client);
      } catch (Throwable e) {
         return null;
      }
   }

   protected void init() throws Throwable {
   }

   protected abstract RequestExecutor initExecutor(HttpClient httpClient) throws Throwable;
}
