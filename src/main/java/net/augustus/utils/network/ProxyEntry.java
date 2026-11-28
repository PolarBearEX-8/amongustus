package net.augustus.utils.network;

import net.augustus.proxy.ProxyProtocolType;
import net.augustus.settings.property.BooleanProperty;
import net.augustus.settings.property.IntegerProperty;
import net.augustus.settings.property.StringProperty;
import com.mojang.authlib.minecraft.MinecraftSessionService;
import com.mojang.authlib.yggdrasil.YggdrasilAuthenticationService;
import io.netty.channel.Channel;
import io.netty.handler.proxy.Socks4ProxyHandler;
import io.netty.handler.proxy.Socks5ProxyHandler;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.Proxy.Type;
import java.util.UUID;
import net.augustus.modules.AbstractFeature;

public class ProxyEntry extends AbstractFeature {
   private final StringProperty proxyType;
   private final BooleanProperty marked;
   private final IntegerProperty port;
   private MinecraftSessionService sessionService;
   private InetSocketAddress socketAddress;
   private final StringProperty ip;
   private final StringProperty username;
   private final StringProperty password;
   private final IntegerProperty ping;

   public ProxyEntry(ProxyEntry proxyEntry) {
       this(proxyEntry.getHost(), proxyEntry.getPort(), proxyEntry.getUsername(), proxyEntry.getPassword());
      this.setPing(proxyEntry.getPing());
      this.proxyType.setValue(proxyEntry.getProxyType());
   }

   public boolean matches(ProxyEntry proxyEntry) {
      if (proxyEntry != null) {
         if (proxyEntry.getHost() == null) {
if (this.getHost() != null) {
if (proxyEntry.getHost() == null || this.getHost() == null || !proxyEntry.getHost().equals(this.getHost())) {
               return false;
            }
}
} else if (proxyEntry.getHost() == null || this.getHost() == null || !proxyEntry.getHost().equals(this.getHost())) {
               return false;
            }

         if (proxyEntry.getPort() == this.getPort()
            && (
               proxyEntry.getUsername() == null && this.getUsername() == null
                  || proxyEntry.getUsername() != null && this.getUsername() != null && proxyEntry.getUsername().equals(this.getUsername())
            )
            && (
               proxyEntry.getPassword() == null && this.getPassword() == null
                  || proxyEntry.getPassword() != null && this.getPassword() != null && proxyEntry.getPassword().equals(this.getPassword())
            )
            && (
               proxyEntry.getProxyType() == null && this.getProxyType() == null
                  || proxyEntry.getProxyType() != null && this.getProxyType() != null && proxyEntry.getProxyType().equals(this.getProxyType())
            )) {
            return true;
         }
      }

      return false;
   }

   public int getPing() {
      return (Integer)this.ping.getValue();
   }

   public boolean isMarked() {
      return (Boolean)this.marked.getValue();
   }

   public String getHost() {
      return (String)this.ip.getValue();
   }

   public MinecraftSessionService getSessionService() {
      if (this.sessionService == null) {
         Proxy proxy = this.toJavaProxy();
         if (proxy == null) {
            return null;
         }

         this.sessionService = new YggdrasilAuthenticationService(proxy, UUID.randomUUID().toString()).createMinecraftSessionService();
      }

      return this.sessionService;
   }

   public String getPassword() {
      return (String)this.password.getValue();
   }

   public ProxyEntry() {
       super(null);
      this.marked = new BooleanProperty(this, "Marked", false);
      this.ip = new StringProperty(this, "IP", "");
      this.port = new IntegerProperty(this, "Port", 0);
      this.ping = new IntegerProperty(this, "Ping", 0);
      this.username = new StringProperty(this, "Username", "");
      this.password = new StringProperty(this, "Password", "");
      this.proxyType = new StringProperty(this, "ProxyType", "");
      this.socketAddress = null;
      this.sessionService = null;
   }

   public ProxyEntry(String str, int n, String str2, String str3, ProxyProtocolType proxyProtocolType) {
       this(str, n, str2, str3);
      this.proxyType.setValue(proxyProtocolType.getDisplayName());
   }

   public String getProxyType() {
      return (String)this.proxyType.getValue();
   }

   private ProxyEntry(String str, int n, String str2, String str3) {
       super(str + ":" + n);
      this.marked = new BooleanProperty(this, "Marked", false);
      this.ip = new StringProperty(this, "IP", "");
      this.port = new IntegerProperty(this, "Port", 0);
      this.ping = new IntegerProperty(this, "Ping", 0);
      this.username = new StringProperty(this, "Username", "");
      this.password = new StringProperty(this, "Password", "");
      this.proxyType = new StringProperty(this, "ProxyType", "");
      this.socketAddress = null;
      this.sessionService = null;
      this.ip.setValue(str);
      IntegerProperty port2 = this.port;
      Integer num = n;
      port2.setValue(num);
      this.username.setValue(str2);
      this.password.setValue(str3);

      try {
         this.socketAddress = new InetSocketAddress(this.getHost(), this.getPort());
      } catch (Exception e) {
      }
   }

   public String getUsername() {
      return (String)this.username.getValue();
   }

   public void setMarked(boolean flag) {
      BooleanProperty marked2 = this.marked;
      Boolean bool = flag;
      marked2.setValue(bool);
   }

   private boolean hasCredentials() {
      return this.getUsername() != null && !this.getUsername().isEmpty();
   }

   public void applyToChannel(Channel channel) {
      if (this.socketAddress != null) {
         switch (this.getProxyType()) {
            case "Socks 4":
               channel.pipeline()
                  .addFirst(
                     "socks4-proxy-handler", this.hasCredentials() ? new Socks4ProxyHandler(this.socketAddress, this.getUsername()) : new Socks4ProxyHandler(this.socketAddress)
                  );
               break;
            case "Socks 5":
               channel.pipeline()
                  .addFirst(
                     "socks5-proxy-handler",
                     this.hasCredentials() ? new Socks5ProxyHandler(this.socketAddress, this.getUsername(), this.getPassword()) : new Socks5ProxyHandler(this.socketAddress)
                  );
         }
      }
   }

   public void setPing(int n) {
      IntegerProperty ping2 = this.ping;
      Integer num = n;
      ping2.setValue(num);
   }

   public boolean equals(Object obj) {
      if (obj instanceof ProxyEntry) {
         return this.matches((ProxyEntry)obj);
      } else {
         return super.equals(obj);
      }
   }

   public int getPort() {
      return (Integer)this.port.getValue();
   }

   public InetSocketAddress getSocketAddress() {
      return this.socketAddress;
   }

   public Proxy toJavaProxy() {
      return this.socketAddress == null ? null : new Proxy(Type.SOCKS, this.socketAddress);
   }

}
