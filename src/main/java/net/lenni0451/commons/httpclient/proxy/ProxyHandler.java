package net.lenni0451.commons.httpclient.proxy;

import java.lang.reflect.Method;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.SocketAddress;
import java.net.Proxy.Type;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class ProxyHandler {
   private ProxyType proxyType;
   private SocketAddress address;
   private String username;
   private String password;

   public ProxyHandler() {
   }

   public ProxyHandler(ProxyType proxyType, String host, int port) {
      this(proxyType, host, port, null, null);
   }

   public ProxyHandler(ProxyType proxyType, String host, int port, @Nullable String username, @Nullable String password) {
      this(proxyType, new InetSocketAddress(host, port), username, password);
   }

   public ProxyHandler(ProxyType proxyType, SocketAddress address) {
      this(proxyType, address, null, null);
   }

   public ProxyHandler(ProxyType proxyType, SocketAddress address, @Nullable String username, @Nullable String password) {
      this.proxyType = proxyType;
      this.address = address;
      this.username = username;
      this.password = password;
   }

   public ProxyHandler setProxy(ProxyType type, String host, int port) {
      return this.setProxy(type, new InetSocketAddress(host, port));
   }

   public ProxyHandler setProxy(ProxyType type, SocketAddress address) {
      this.proxyType = type;
      this.address = address;
      return this;
   }

   public ProxyHandler unsetProxy() {
      this.proxyType = null;
      this.address = null;
      return this;
   }

   public boolean isProxySet() {
      return this.proxyType != null && this.address != null;
   }

   @Nullable
   public ProxyType getProxyType() {
      return this.proxyType;
   }

   public ProxyHandler setProxyType(@Nonnull ProxyType type) {
      this.proxyType = type;
      return this;
   }

   @Nullable
   public SocketAddress getAddress() {
      return this.address;
   }

   public ProxyHandler setAddress(@Nonnull SocketAddress address) {
      this.address = address;
      return this;
   }

   public boolean isAuthenticationSet() {
      return this.username != null && this.password != null;
   }

   @Nullable
   public String getUsername() {
      return this.username;
   }

   public ProxyHandler setUsername(@Nullable String username) {
      this.username = username;
      return this;
   }

   @Nullable
   public String getPassword() {
      return this.password;
   }

   public ProxyHandler setPassword(@Nullable String password) {
      this.password = password;
      return this;
   }

   public SingleProxySelector getProxySelector() {
      if (!this.isProxySet()) {
         throw new IllegalStateException("Proxy is not set");
      } else {
         return new SingleProxySelector(this.toJavaProxy(), this.username, this.password);
      }
   }

   public SingleProxyAuthenticator getProxyAuthenticator() {
      if (!this.isProxySet()) {
         throw new IllegalStateException("Proxy is not set");
      } else if (!this.isAuthenticationSet()) {
         throw new IllegalStateException("Username or password is not set");
      } else {
         return new SingleProxyAuthenticator(this.username, this.password);
      }
   }

   public Proxy toJavaProxy() {
      switch (this.proxyType) {
         case HTTP:
            return new Proxy(Type.HTTP, this.address);
         case SOCKS4:
            try {
               Class<?> clazz = Class.forName("sun.net.SocksProxy");
               return (Proxy)clazz.getDeclaredMethod("create", SocketAddress.class, int.class).invoke(null, this.address, 4);
            } catch (Throwable e) {
               throw new UnsupportedOperationException("SOCKS4 proxy type is not supported", e);
            }
         case SOCKS5:
            return new Proxy(Type.SOCKS, this.address);
         default:
            throw new IllegalStateException("Unknown proxy type: " + this.proxyType.name());
      }
   }
}
