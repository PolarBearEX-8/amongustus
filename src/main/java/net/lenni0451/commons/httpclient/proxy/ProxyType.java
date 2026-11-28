package net.lenni0451.commons.httpclient.proxy;

import java.net.Proxy.Type;

public enum ProxyType {
   HTTP,
   SOCKS4,
   SOCKS5;

   public static ProxyType from(Type type) {
      switch (type) {
         case HTTP:
            return HTTP;
         case SOCKS:
            return SOCKS5;
         default:
            throw new IllegalArgumentException("Unknown proxy type: " + type.name());
      }
   }
}
