package client.util;

import net.minecraft.network.play.server.S22PacketMultiBlockChange;
import net.minecraft.util.BlockPos;

public class ShortEntry {
   public final short shortCode;
   public final IBlockState iBlockState;
   public final S22PacketMultiBlockChange s22PacketMultiBlockChange;

   public IBlockState getIBlockState() {
      return this.iBlockState;
   }

   public short getShortCode() {
      return this.shortCode;
   }

   public BlockPos getBlockPos() {
      return new BlockPos(
         S22PacketMultiBlockChange.resolveInterpolationCollector(this.s22PacketMultiBlockChange)
            .resolveBlockPos(this.shortCode >> 12 & 15, this.shortCode & 255, this.shortCode >> 8 & 15)
      );
   }

   public ShortEntry(S22PacketMultiBlockChange packet, short shortValue, IBlockState blockState) {
      this.s22PacketMultiBlockChange = packet;
      this.shortCode = shortValue;
      this.iBlockState = blockState;
   }

   public ShortEntry(S22PacketMultiBlockChange packet, short shortValue, AlertNotificationSound alertNotificationSound) {
      this.s22PacketMultiBlockChange = packet;
      this.shortCode = shortValue;
      this.iBlockState = alertNotificationSound.resolveIBlockState(this.getBlockPos());
   }
}
