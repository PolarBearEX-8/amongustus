package client.network;

import client.Client;
import client.util.AccountSessionManager;
import client.util.AuxiliaryLengthWiderThan21BitByteToMessageDecoder;
import client.util.EMfDf9lTUMessageToMessageDecoder;
import client.util.InternalMessageToByteEncoderAdapter;
import client.util.InvalidProxyConfigLineManager;
import client.util.MessageToByteEncoderAdapter;
import client.util.PacketPayloadEncoder;
import client.util.RotationSorter;
import client.util.SpecialLongEntry;
import client.util.VectorBridge;
import io.netty.channel.Channel;
import io.netty.channel.ChannelException;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.handler.proxy.Socks4ProxyHandler;
import io.netty.handler.proxy.Socks5ProxyHandler;
import io.netty.handler.ssl.SslContext;
import java.net.InetSocketAddress;
import java.util.Optional;

public class InternalChannelInitializerAdapter extends ChannelInitializer {
   public final Optional optional;
   public final AccountSessionManager kGBfUoUg0L8Manager;
   public final SslContext sslContext;

   public void initChannel(Channel channel) {
      try {
         channel.config().setOption(ChannelOption.TCP_NODELAY, Boolean.TRUE);
      } catch (ChannelException channelException) {
      }

      ChannelPipeline channelPipeline = channel.pipeline();
      SpecialLongEntry specialLongEntry = new SpecialLongEntry();
      SpecialLongEntry specialLongEntry2 = new SpecialLongEntry();
      EMfDf9lTUMessageToMessageDecoder eMfDf9lTUMessageToMessageDecoder = new EMfDf9lTUMessageToMessageDecoder(specialLongEntry);
      PacketPayloadEncoder packet = new PacketPayloadEncoder(specialLongEntry2);
      if (this.optional.isPresent()) {
         InvalidProxyConfigLineManager invalidProxyConfigLineManager = (InvalidProxyConfigLineManager)this.optional.get();
         if (invalidProxyConfigLineManager.vectorBridge == VectorBridge.SOCKS4) {
            if (invalidProxyConfigLineManager.isEmpty()) {
               channelPipeline.addFirst(new ChannelHandler[]{new Socks4ProxyHandler(new InetSocketAddress(invalidProxyConfigLineManager.labelText, invalidProxyConfigLineManager.count), invalidProxyConfigLineManager.labelText3)});
            } else {
               channelPipeline.addFirst(new ChannelHandler[]{new Socks4ProxyHandler(new InetSocketAddress(invalidProxyConfigLineManager.labelText, invalidProxyConfigLineManager.count))});
            }
         } else if (invalidProxyConfigLineManager.vectorBridge == VectorBridge.SOCKS5) {
            if (invalidProxyConfigLineManager.isEmpty()) {
               channelPipeline.addFirst(
                  new ChannelHandler[]{new Socks5ProxyHandler(new InetSocketAddress(invalidProxyConfigLineManager.labelText, invalidProxyConfigLineManager.count), invalidProxyConfigLineManager.labelText3, invalidProxyConfigLineManager.labelText2)}
               );
            } else {
               channelPipeline.addFirst(new ChannelHandler[]{new Socks5ProxyHandler(new InetSocketAddress(invalidProxyConfigLineManager.labelText, invalidProxyConfigLineManager.count))});
            }
         }
      }

      ChannelPipeline channelPipeline2 = channelPipeline.addLast(new ChannelHandler[]{this.sslContext.newHandler(channel.alloc())})
         .addLast(new ChannelHandler[]{new AuxiliaryLengthWiderThan21BitByteToMessageDecoder(specialLongEntry)})
         .addLast(new ChannelHandler[]{eMfDf9lTUMessageToMessageDecoder})
         .addLast(new ChannelHandler[]{new RotationSorter(specialLongEntry)})
         .addLast(new ChannelHandler[]{new MessageToByteEncoderAdapter(specialLongEntry2)})
         .addLast(new ChannelHandler[]{packet})
         .addLast(new ChannelHandler[]{new InternalMessageToByteEncoderAdapter(specialLongEntry2)});
      ChannelHandler[] channelHandlerArray = new ChannelHandler[1];
      Client.getClient().getClass();
      boolean flag = AccountSessionManager.matchesKGBfUoUg0L8Manager(this.kGBfUoUg0L8Manager);
      channelHandlerArray[0] = new CosmeticsNetworkHandler(packet, eMfDf9lTUMessageToMessageDecoder, specialLongEntry, flag);
      channelPipeline2.addLast(channelHandlerArray);
   }

   public InternalChannelInitializerAdapter(AccountSessionManager accountSessionManager, Optional optional2, SslContext sslContext2) {
      this.kGBfUoUg0L8Manager = accountSessionManager;
      this.optional = optional2;
      this.sslContext = sslContext2;
   }
}
