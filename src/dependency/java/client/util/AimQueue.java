package client.util;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.server.S34PacketMaps;

public class AimQueue {
   public final EntityPlayer entityPlayer;
   public boolean enabled;
   public int count2;
   public int count3;
   public int count4;
   public int count5;
   public int count6;
   public int count;
   public final DimensionStringEntry dimensionStringEntry;

   public AimQueue(DimensionStringEntry dimensionStringEntry2, EntityPlayer player) {
      this.dimensionStringEntry = dimensionStringEntry2;
      this.enabled = true;
      this.count2 = 0;
      this.count3 = 0;
      this.count4 = 127;
      this.count5 = 127;
      this.entityPlayer = player;
   }

   public Packet resolvePacket(ItemStack stack) {
      if (this.enabled) {
         this.enabled = false;
         return new S34PacketMaps(
            stack.getIndexOffset(),
            this.dimensionStringEntry.byteFlag2,
            this.dimensionStringEntry.map.values(),
            this.dimensionStringEntry.byteFlagArray,
            this.count2,
            this.count3,
            this.count4 + 1 - this.count2,
            this.count5 + 1 - this.count3
         );
      } else {
         return this.count6++ % 5 == 0
            ? new S34PacketMaps(
               stack.getIndexOffset(),
               this.dimensionStringEntry.byteFlag2,
               this.dimensionStringEntry.map.values(),
               this.dimensionStringEntry.byteFlagArray,
               0,
               0,
               0,
               0
            )
            : null;
      }
   }

   public void applyIndex(int count7, int count8) {
      if (this.enabled) {
         this.count2 = Math.min(this.count2, count7);
         this.count3 = Math.min(this.count3, count8);
         this.count4 = Math.max(this.count4, count7);
         this.count5 = Math.max(this.count5, count8);
      } else {
         this.enabled = true;
         this.count2 = count7;
         this.count3 = count8;
         this.count4 = count7;
         this.count5 = count8;
      }
   }
}
