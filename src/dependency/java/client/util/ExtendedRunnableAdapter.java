package client.util;

import client.setting.CombatKeybindProperty;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.util.concurrent.GenericFutureListener;
import net.minecraft.network.NetworkManager;

public class ExtendedRunnableAdapter implements Runnable {
   public final CombatKeybindProperty combatKeybindProperty2;
   public final CombatKeybindProperty combatKeybindProperty;
   public final Packet packet;
   public final GenericFutureListener[] genericFutureListenerArray;
   public final NetworkManager networkManager;

   public ExtendedRunnableAdapter(NetworkManager networkManager2, CombatKeybindProperty combatKeybindProperty3, CombatKeybindProperty combatKeybindProperty4, Packet packet2, GenericFutureListener[] genericFutureListenerArray2) {
      this.networkManager = networkManager2;
      this.combatKeybindProperty2 = combatKeybindProperty3;
      this.combatKeybindProperty = combatKeybindProperty4;
      this.packet = packet2;
      this.genericFutureListenerArray = genericFutureListenerArray2;
   }

   @Override
   public void run() {
      if (this.combatKeybindProperty2 != this.combatKeybindProperty) {
         this.networkManager.applyCombatKeybindProperty(this.combatKeybindProperty2);
      }

      ChannelFuture channelFuture = this.networkManager.channel.writeAndFlush(this.packet);
      if (this.genericFutureListenerArray != null) {
         channelFuture.addListeners(this.genericFutureListenerArray);
      }

      channelFuture.addListener(ChannelFutureListener.FIRE_EXCEPTION_ON_FAILURE);
   }
}
