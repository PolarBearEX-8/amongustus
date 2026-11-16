package augustus.anticheat.checks;

import client.Client;
import client.event.EventMove;
import client.setting.DirectOptionType;
import client.util.CompositeDoubleEntry;
import client.util.Packet;
import client.util.RayTraceUtil;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.block.material.Material;
import net.minecraft.potion.Potion;
import net.minecraft.util.BlockPos;
import net.minecraft.util.Vec3;

public class Intave14TimeBlockPlaceCheck extends AntiCheatCheck {
   public int count;
   public final CompositeDoubleEntry compositeDoubleEntry;
   public long timestamp4;
   public final List list;
   public int count2;
   public long timestamp2;
   public final List list2 = new ArrayList();
   public long timestamp3;
   public final boolean enabled;
   public long timestamp;
   public final boolean enabled2;

   @Override
   public void updateState2() {
   }

   public double getCoordinate(BlockPos blockPos, BlockPos blockPos2) {
      double offset = 0.0;
      if (this.matchesBlockPos(blockPos) && this.computeCount(blockPos) < 2 && !minecraft.thePlayer.checkPotion(Potion.potion2)) {
         ArrayList list3 = new ArrayList(this.list2);
         if (list3.size() >= 8) {
            list3.remove(0);
         }

         if (blockPos.getY() == blockPos2.getY()) {
            list3.add(System.currentTimeMillis() - this.timestamp2);
         } else {
            list3.add(System.currentTimeMillis() - this.timestamp2 + 1000L);
         }

         if (list3.size() >= 8) {
            double offset2 = list3.stream().mapToDouble((item -> Intave14TimeBlockPlaceCheck.longValue((Long)item))).average().orElse(500.0);
            List list4 = this.list;
            boolean flag = this.matchesList(list4);
            boolean flag2 = System.currentTimeMillis() - this.timestamp4 > 8000L;
            boolean flag3 = System.currentTimeMillis() - this.timestamp3 > 8000L;
            boolean flag4 = System.currentTimeMillis() - this.timestamp < 750L;
            double offset3 = flag ? (double)(flag4 ? 450 : (flag2 ? (flag3 ? 500 : 300) : (flag3 ? 350 : 200))) : 150.0;
            int count5 = minecraft.thePlayer.checkPotion(Potion.potion2) ? minecraft.thePlayer.getPotionEffect(Potion.potion2).getIndexOffset2() + 1 : 0;
            double offset4 = offset3 / (0.15 * (double)count5 * (double)count5 + 1.0);
            if (offset2 < offset4) {
               offset = offset4 - offset2;
            }
         }
      }

      return offset;
   }

   private static double longValue(Long value) {
      return (double)value.longValue();
   }

   private boolean matchesBlockPos(BlockPos blockPos) {
      return new Vec3((double)blockPos.getX(), (double)blockPos.getY(), (double)blockPos.getZ())
            .getVec312(0.0, 1.0, 0.0)
            .getDouble2(minecraft.thePlayer.posX, minecraft.thePlayer.posY, minecraft.thePlayer.posZ)
         < 1.3;
   }

   private int computeCount(BlockPos blockPos) {
      int count3 = 0;
      if (minecraft.theWorld.getBlockState(blockPos.getBlockPos2()).getBlock().getMaterial() != Material.coreItemEntry) {
         count3++;
      }

      if (minecraft.theWorld.getBlockState(blockPos.getBlockPos3()).getBlock().getMaterial() != Material.coreItemEntry) {
         count3++;
      }

      if (minecraft.theWorld.getBlockState(blockPos.getBlockPosAsBlockPos()).getBlock().getMaterial() != Material.coreItemEntry) {
         count3++;
      }

      if (minecraft.theWorld.getBlockState(blockPos.getBlockPos()).getBlock().getMaterial() != Material.coreItemEntry) {
         count3++;
      }

      return count3;
   }

   @Override
   public void onMove(EventMove event) {
      super.onMove(event);
      if (event.isState4()) {
         this.count++;
         if (this.count > 1) {
            this.timestamp3 = System.currentTimeMillis();
         }
      } else {
         if (this.enabled && this.count > 0) {
            RayTraceUtil.applyString("Sneakticks: " + this.count);
         }

         this.count = 0;
      }
   }

   @Override
   public void handlePacket(Packet packet) {
      Client.getClient().getClass();
   }

   private boolean matchesList(List list3) {
      int count3 = 0;
      int count4 = 0;
      int count5 = 0;
      boolean flag = false;
      boolean flag2 = false;
      boolean flag3 = true;

      for (BlockPos blockPos : (Iterable<BlockPos>) (Iterable) (list3)) {
         if (!flag3) {
            if ((double)count4 != (double)blockPos.getY()) {
               return false;
            }

            if ((double)count3 == (double)blockPos.getX()) {
               flag = true;
            } else if (flag) {
               return false;
            }

            if ((double)count5 == (double)blockPos.getZ()) {
               flag2 = true;
            } else if (flag2) {
               return false;
            }
         }

         count3 = blockPos.getX();
         count4 = blockPos.getY();
         count5 = blockPos.getZ();
         flag3 = false;
      }

      return flag || flag2;
   }

   @Override
   public void updateState() {
      this.timestamp = System.currentTimeMillis();
   }

   public Intave14TimeBlockPlaceCheck(String text, AntiCheatManager antiCheatManager, boolean flag) {
      super(text, antiCheatManager);
      this.count = 0;
      this.timestamp2 = 0L;
      this.list = new ArrayList();
      this.compositeDoubleEntry = new CompositeDoubleEntry(1337, "suspicious block-placement", DirectOptionType.SCAFFOLD);
      this.enabled2 = false;
      this.enabled = flag;
   }
}
