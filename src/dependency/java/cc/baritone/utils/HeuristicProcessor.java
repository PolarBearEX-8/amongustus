package cc.baritone.utils;

import client.util.Packet;
import net.minecraft.network.NetworkManager;

public final class HeuristicProcessor {
   public final NetworkManager networkManager;
   public final SecondaryOptionType secondaryOptionType;
   public final Packet packet;

   public final Packet getPacket() {
      return this.packet;
   }

   public final SecondaryOptionType getSecondaryOptionType() {
      return this.secondaryOptionType;
   }

   public final NetworkManager getNetworkManager() {
      return this.networkManager;
   }

   public HeuristicProcessor(NetworkManager networkManager2, SecondaryOptionType secondaryOptionType2, Packet packet2) {
      this.networkManager = networkManager2;
      this.secondaryOptionType = secondaryOptionType2;
      this.packet = packet2;
   }

   public final Packet getPacketAsPacket() {
      return this.packet;
   }
}
