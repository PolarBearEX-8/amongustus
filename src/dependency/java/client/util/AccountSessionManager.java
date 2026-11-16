package client.util;

import augustus.modules.misc.ClientSettings;
import client.Client;
import client.network.CosmeticsNetworkHandler;
import client.network.InternalChannelInitializerAdapter;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.epoll.Epoll;
import io.netty.channel.epoll.EpollEventLoopGroup;
import io.netty.channel.epoll.EpollSocketChannel;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.Security;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Supplier;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.TrustManagerFactory;
import recovery.privacy.BlockedControlBootstrap;

public class AccountSessionManager {
   public final AtomicInteger atomicInteger;
   public static final AccountSessionManager kGBfUoUg0L8Manager = new AccountSessionManager();
   public EventLoopGroup eventLoopGroup;
   public static volatile boolean enabled = false;
   public final List list;
   public static final boolean enabled3 = Epoll.isAvailable();
   public final String labelText2;
   public Channel channel;
   public final String labelText;
   public volatile boolean enabled2;
   public volatile boolean enabled4;
   public final HashMap hashMap;
   public Bootstrap bootstrap;
   public final List list2;

   public static boolean matchesKGBfUoUg0L8Manager(AccountSessionManager accountSessionManager) {
      return accountSessionManager.enabled4;
   }

   private void updateState3() {
      int count = 0;
      long value = 0L;
      long value2 = 0x8697eb70e09b01aL;
      long value3 = 0x16a11400cb524e45L;

      while (true) {
         int count2 = (int)(value2 % 13L);
         if (count2 == 10) {
            enabled = true;
            value2 = (value2 ^ -0x282178052f154021L) - 0x7a6170d2611777f2L;
            value3 = (value3 ^ 0x4f313420d8517629L) - 0x3a8ec995c88d4fefL;
         } else if (count2 == 9) {
            count = Math.min(this.atomicInteger.incrementAndGet(), 10);
            value2 = (value2 ^ 0x76778de2bc5862e6L) - 0x13dacffc3a26ba4fL;
            value3 = (value3 ^ 0x474713a992a27437L) - 0x62a35b482ce5159eL;
         } else if (count2 == 4) {
            this.eventLoopGroup.shutdownGracefully();
            value2 = (value2 ^ 0x76cf022fb4b6a6eL) - 0x1e271d11829d22f4L;
            value3 = (value3 ^ -0x33d63463a68b0ee0L) - 0x5069c42144d2c823L;
         } else if (count2 == 1) {
            value = Math.min((value3 % 331644L ^ -138738L) << count - 1, 5L);
            value2 = (value2 ^ -0x582c74bbe5a44608L) - 0x794b355d554320d2L;
            value3 = (value3 ^ -0x149a076b3fff567cL) + 0x74a857d79fd9f8d4L;
         } else if (count2 == 11) {
            this.bootstrap = this.getBootstrap();
            value2 = (value2 ^ 0x357d207588d9e2d6L) - 0x4f4f3c7786e0d2b1L;
            value3 = (value3 ^ -0x317c4903fc81687L) - 0x12e9a1d783376e80L;
         } else if (count2 != 6) {
            if (count2 != 7) {
               if (count2 != 0) {
                  if (count2 == 12) {
                     return;
                  }

                  if (count2 == 2) {
                     if (this.eventLoopGroup == null) {
                        value2 = (value2 ^ 0x2454bfead312a0f0L) - 0x2938ea4d5128616fL;
                        value3 = (value3 ^ -0x47fd432a1badc36bL) + 0x5bd1b35c73293b03L;
                     } else {
                        value2 = (value2 ^ 0x6612a52f1df29ac9L) + 0x313b29229c099530L;
                        value3 = (value3 ^ -0x266f6064aa4ae1f2L) - 0x7a204bcf7cca91L;
                     }
                     continue;
                  }

                  if (count2 != 5) {
                     if (count2 != 3) {
                        CosmeticsNetworkHandler.updateState();
                        this.enabled4 = true;
                        value2 = (value2 ^ -0x583b2671b94173a8L) - 0x79dff6b87eea2769L;
                        value3 = (value3 ^ 0x48cde165fa0bc781L) - 0x7370a9bc86743228L;
                        continue;
                     }

                     this.eventLoopGroup.schedule(this::updateState4, value, TimeUnit.SECONDS);
                     return;
                  }

                  if (!this.enabled2) {
                     long value4 = (value2 ^ 0x57970037b0c5fc28L) - 0x5a5d836bb439adb1L;
                     long value5 = (value3 ^ -0x7d9d1adfa4cd88c0L) + 0x40d747b4e45758c9L;
                     ClientSettings.processAction(0);
                     value2 = (value4 ^ -0x651a94e4b4305756L) - 0x70e0b027a3f02234L;
                     value3 = (value5 ^ -0x117b2e4d51b1d755L) + 0x4010eeec2bbd69b4L;
                     continue;
                  }
               }

               return;
            } else {
               SupplementalLongEntry.timestamp = System.currentTimeMillis();
               value2 = (value2 ^ -0x4c5d9a01e99be7e1L) + 0x221a12edacf2cfecL;
               value3 = (value3 ^ 0x717ea4f695417b12L) + 0x57c878984c977576L;
            }
         } else {
            this.eventLoopGroup = this.getEventLoopGroup();
            value2 = (value2 ^ -0x2b77a28abed149b2L) + 0x71892b85dd4af72dL;
            value3 = (value3 ^ -0x6490cc066f72aff1L) + 0x26bf865f554b7208L;
         }
      }
   }

   public void updateState2() {
      this.enabled2 = true;
      this.updateState();
      if (this.eventLoopGroup != null) {
         this.eventLoopGroup.shutdownGracefully();
      }
   }

   private void applyChannelFutureByChannelFuture(ChannelFuture channelFuture) throws Exception {
      if (!channelFuture.isSuccess()) {
         this.updateState3();
      } else {
         this.channel = channelFuture.channel();
         this.atomicInteger.set(0);
         this.channel.closeFuture().addListener(future -> this.applyChannelFuture((ChannelFuture)future));
      }
   }

   private EventLoopGroup getEventLoopGroup() {
      return (EventLoopGroup)(enabled3 ? new EpollEventLoopGroup(1) : new NioEventLoopGroup(1));
   }

   private void applyChannelFuture(ChannelFuture channelFuture) throws Exception {
      this.updateState3();
   }

   public void updateState() {
      SupplementalLongEntry.timestamp = System.currentTimeMillis();
      if (!this.isActive2()) {
         this.updateState3();
      } else {
         this.channel.eventLoop().execute(this.channel::close);
      }
   }

   public static AccountSessionManager getKGBfUoUg0L8Manager() {
      return kGBfUoUg0L8Manager;
   }

   public boolean isActive2() {
      return this.channel != null && this.channel.isActive();
   }

   private void applyList(List list3, Map map) {
      Object entry = null;
      map.clear();

      for (int index = 0; index < list3.size(); index++) {
         entry = (Class)list3.get(index);
         map.put(entry, index);
      }
   }

   public void updateState4() {
   }

   private void updateState5() {
      ConfigManager.applyString2(this.labelText);
      File file = new File(this.labelText, "proxy.txt");
      System.out.println(file);
      if (!file.exists()) {
         String text = "# ===============================================\n#        proxy.txt - SOCKS4/SOCKS5 Proxy Config #\n# ===============================================\n#\n# This file lets you specify an OPTIONAL SOCKS4 or SOCKS5 proxy\n# for your Netty client. If the file is empty or only contains\n# comments, NO proxy will be used.\n# To check your free proxies you can use this tool: https://proxyscrape.com/proxy-checker\n# If you need 5 free proxies: https://oxylabs.io/products/datacenter-proxies  (I tested the proxy integration with that)\n# Rotating proxies are not supported.\n#\n# USAGE:\n#\n# For SOCKS5 (no authentication):\n#   socks5:host:port\n# For SOCKS5 (with username and password):\n#   socks5:host:port:username:password\n#\n# For SOCKS4 (no authentication):\n#   socks4:host:port\n# For SOCKS4 (with username):\n#   socks4:host:port:userid\n#\n# EXAMPLES:\n#   socks5:127.0.0.1:1080\n#   socks5:myproxy.example.com:1080:myuser:mypass\n#   socks4:127.0.0.1:1080\n#   socks4:proxy.example.org:1080:userid123\n#\n# Only the FIRST valid (non-comment, non-empty) line will be used.\n# Lines starting with # are ignored.\n#\n# To disable proxying, leave this file empty or only with comments.\n#\n";

         try {
            FileWriter writer = new FileWriter(file);
            Throwable throwable = null;
            boolean flag = false ;

            try {
               flag = true;
               writer.write(text);
               writer.flush();
               flag = false;
            } catch (Throwable throwable2) {
               throwable = throwable2;
               throw throwable2;
            } finally {
               if (flag) {
                  if (throwable != null) {
                     try {
                        writer.close();
                     } catch (Throwable throwable3) {
                        throwable.addSuppressed(throwable3);
                     }
                  } else {
                     writer.close();
                  }
               }
            }

            writer.close();
         } catch (IOException ioException) {
         }
      }
   }

   private Bootstrap getBootstrap() {

      try {
         KeyStore instance = KeyStore.getInstance("PKCS12");
         InputStream resourceAsStream = AccountSessionManager.class.getClassLoader().getResourceAsStream("assets/augustus/certificates/client.p12");
         instance.load(resourceAsStream, "XNqTn785wCMbsXzFHLrm5ChMDdWBh8WM7nhpU3jJuvn8atqaf3mxJzFm37GxWdYW".toCharArray());
         KeyManagerFactory instance2 = KeyManagerFactory.getInstance("SunX509");
         instance2.init(instance, "XNqTn785wCMbsXzFHLrm5ChMDdWBh8WM7nhpU3jJuvn8atqaf3mxJzFm37GxWdYW".toCharArray());
         KeyStore instance3 = KeyStore.getInstance("JKS");
         InputStream resourceAsStream2 = AccountSessionManager.class.getClassLoader().getResourceAsStream("assets/augustus/certificates/truststore.jks");
         instance3.load(resourceAsStream2, "XNqTn785wCMbsXzFHLrm5ChMDdWBh8WM7nhpU3jJuvn8atqaf3mxJzFm37GxWdYW".toCharArray());
         TrustManagerFactory instance4 = TrustManagerFactory.getInstance("SunX509");
         instance4.init(instance3);
         Security.setProperty("ocsp.enable", "true");
         SslContext sslContext = SslContextBuilder.forClient().keyManager(instance2).trustManager(instance4).build();
         Bootstrap bootstrap2 = (Bootstrap)((Bootstrap)new BlockedControlBootstrap().group(this.eventLoopGroup))
            .channel(enabled3 ? EpollSocketChannel.class : NioSocketChannel.class);
         Optional optional = Optional.empty();
         bootstrap2.handler(new InternalChannelInitializerAdapter(this, optional, sslContext));
         return bootstrap2;
      } catch (Exception exception) {
         ProfileConfigManager profileConfigManager = new ProfileConfigManager();
         String text = Client.getClient().getString7() + " Bootstrap initialization failed";
         profileConfigManager.applyText2(text, 0x480462dfa2f4L);
         throw new RuntimeException(exception);
      }
   }

   public AccountSessionManager() {
        super();
      
      this.enabled4 = false;
      this.enabled2 = false;
      this.list = Arrays.<Supplier<?>>asList(
         SecondaryByteArrayEntry::new,
         LongHolder::new,
         ListenerAdapter12::new,
         ExtraListenerAdapter::new,
         ExtraListenerAdapter::new,
         CoreListenerAdapter::new,
         AuxiliaryListenerProcessor::new,
         AngleSorter::new,
         BaseListenerAdapter::new,
         LongPair::new,
         RayTraceBuffer::new,
         BaseByteArrayEntry::new,
         DefaultStringEntry::new,
         NativeListenerAdapter::new,
         InterpolationReader::new,
         AltStringEntry::new,
         PrimaryListenerAdapter::new,
         GenericStringEntry::new,
         DirectStringEntry::new,
         TargetReader::new,
         ExtraListenerAdapter::new,
         ExtraLongEntry::new,
         StaticClientManager::new,
         AimFilter::new,
         CosmeticsDataManager::new,
         CosmeticsItemSetting::new,
         CommonListenerAdapter::new,
         CustomLongEntry::new,
         ExtendedListenerRegistry::new,
         BaseLongEntry::new,
         SupplementalListenerAdapter::new
      );
      this.list2 = Arrays.asList(
         CommonByteArrayEntry.class,
         SpecialListenerAdapter.class,
         SharedListenerAdapter.class,
         ExtraListenerAdapter.class,
         ExtraListenerAdapter.class,
         ExtraListenerAdapter.class,
         TertiaryByteArrayEntry.class,
         AltByteArrayEntry.class,
         SecondaryListenerBridge.class,
         ExtraListenerAdapter.class,
         EnhancedListenerAdapter.class,
         InternalByteArrayEntry.class,
         AdvancedByteArrayEntry.class,
         AdvancedListenerAdapter.class,
         ExtendedByteArrayEntry.class,
         StandardListenerAdapter.class,
         ListenerDispatcher.class,
         InternalListenerProcessor.class,
         ManagedStringEntry.class,
         ManagedListenerAdapter.class,
         SupplementalByteArrayEntry.class,
         ActiveStringEntry.class,
         CommonLongEntry.class,
         ExtraListenerAdapter.class,
         AltListenerAdapter.class,
         ExtraStringHolder.class,
         CustomListenerAdapter.class,
         AuxiliaryByteArrayHolder.class,
         DefaultListenerAdapter.class,
         AltLongEntry.class,
         IndirectStringEntry.class,
         TertiaryListenerAdapter.class
      );
      this.hashMap = new HashMap();
      this.atomicInteger = new AtomicInteger();
      this.labelText = System.getenv("APPDATA") + File.separator + "ElectricLauncher";
      this.labelText2 = "proxy.txt";
      HashMap hashMap2 = this.hashMap;
      List list3 = this.list2;
      this.applyList(list3, hashMap2);
      this.eventLoopGroup = this.getEventLoopGroup();
      this.bootstrap = this.getBootstrap();
      CosmeticsNetworkHandler.updateState();
      enabled = true;
      this.updateState5();
   }

   static {
      AmIUGWbLR6cManager.updateState();
   }
}
