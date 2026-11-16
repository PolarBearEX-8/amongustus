package client.util;

import net.minecraft.client.Minecraft;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class AuxiliaryStringEntryDispatcher extends ServiceStringEntry {
   public double value2;
   public double value;
   public static String labelText4;

   public void applyWorldByWorld(World world, double posX, double posY, double posZ, boolean flag, boolean flag2) {
      if (!this.list.isEmpty()) {
         double offset = 0.0;
         if (world != null && !flag) {
            BlockPos blockPos = world.getBlockPos();
            double offset2 = (double)blockPos.getX() - posX;
            double offset3 = (double)blockPos.getZ() - posY;
            double offset4 = posZ % 360.0;
            offset = -((offset4 - 90.0) * Math.PI / 180.0 - Math.atan2(offset3, offset2));
            if (!world.secondaryFloatArrayEntry.isActive2()) {
               offset = Math.random() * Math.PI * 2.0;
            }
         }

         if (flag2) {
            this.value2 = offset;
         } else {
            double offset5 = offset - this.value2;

            while (offset5 < -Math.PI) {
               offset5 += Math.PI * 2;
            }

            while (offset5 >= Math.PI) {
               offset5 -= Math.PI * 2;
            }

            double doubleItem = MathHelper.getDouble7(offset5, -1.0, 1.0);
            this.value += doubleItem * 0.1;
            this.value *= 0.8;
            this.value2 = this.value2 + this.value;
         }

         int count = (int)((this.value2 / (Math.PI * 2) + 1.0) * (double)this.list.size()) % this.list.size();

         while (count < 0) {
            count = (count + this.list.size()) % this.list.size();
         }

         if (count != this.count10) {
            this.count10 = count;
            SpecialLoggerEntry.applyIndexArray((int[][])this.list.get(this.count10), this.count, this.count2, this.count3, this.count6, false, false);
         }
      }
   }

   @Override
   public void updateState3() {
      Minecraft mc = Minecraft.getMinecraft();
      if (mc.theWorld != null && mc.thePlayer != null) {
         this.applyWorldByWorld(mc.theWorld, mc.thePlayer.posX, mc.thePlayer.posZ, (double)mc.thePlayer.rotationYaw, false, false);
      } else {
         this.applyWorldByWorld((World)null, 0.0, 0.0, 0.0, true, false);
      }
   }

   public AuxiliaryStringEntryDispatcher(String text) {
      super(text);
      labelText4 = text;
   }
}
