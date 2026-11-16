package client.util;

import client.event.PostUpdateFilter;
import client.setting.DynamicOptionType;
import net.minecraft.network.play.server.S44PacketWorldBorder;
import net.minecraft.world.storage.WorldInfo;

public class ListenerAdapter13 implements PostUpdateFilter {
   public final MovingManager movingManager;

   @Override
   public void applyWorldInfo(WorldInfo worldInfo, double offset, double offset2) {
      this.movingManager.applyPacket(new S44PacketWorldBorder(worldInfo, DynamicOptionType.SET_CENTER));
   }

   @Override
   public void applyWorldInfo2(WorldInfo worldInfo, double offset) {
   }

   @Override
   public void applyWorldInfo(WorldInfo worldInfo, double offset, double offset2, long value) {
      this.movingManager.applyPacket(new S44PacketWorldBorder(worldInfo, DynamicOptionType.LERP_SIZE));
   }

   @Override
   public void applyWorldInfoByWorldInfo(WorldInfo worldInfo, int count) {
      this.movingManager.applyPacket(new S44PacketWorldBorder(worldInfo, DynamicOptionType.SET_WARNING_BLOCKS));
   }

   @Override
   public void applyWorldInfoByWorldInfo(WorldInfo worldInfo, double offset) {
   }

   public ListenerAdapter13(MovingManager movingManager2) {
      this.movingManager = movingManager2;
   }

   @Override
   public void applyWorldInfo(WorldInfo worldInfo, int count) {
      this.movingManager.applyPacket(new S44PacketWorldBorder(worldInfo, DynamicOptionType.SET_WARNING_TIME));
   }

   @Override
   public void applyWorldInfo(WorldInfo worldInfo, double offset) {
      this.movingManager.applyPacket(new S44PacketWorldBorder(worldInfo, DynamicOptionType.SET_SIZE));
   }
}
