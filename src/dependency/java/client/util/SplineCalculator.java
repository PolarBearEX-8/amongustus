package client.util;

import client.event.PreUpdateDispatcher;
import net.minecraft.client.Minecraft;

public abstract class SplineCalculator extends ExtendedMinecraftEntry {
   @Override
   public boolean checkIndex(int count) {
      return false;
   }

   @Override
   public void applyIndex(int count, int count2, int count3) {
      this.get(count).applyIndex(count, count2, count3);
   }

   public boolean checkIndexByIndex(int count, int count2, int count3) {
      for (int index = 0; index < this.size(); index++) {
         int count4 = this.count14 + this.count9 / 2 - this.getCount20() / 2 + 2;
         int count5 = this.count10 + 4 - this.getCountAsIndex() + index * this.count5 + this.count13;
         int count6 = count - count4;
         int count7 = count2 - count5;
         this.get(index).applyIndex(index, count, count2, count3, count6, count7);
      }

      this.setEnabled3(true);
      return false;
   }

   @Override
   public void applyIndex(int count, int count2, int count3, int count4, int count5, int count6) {
      this.get(count).applyIndex(count, count2, count3, this.getCount20(), count4, count5, count6, this.computeCount(count5, count6) == count);
   }

   @Override
   public void updateState2() {
   }

   public boolean checkIndex(int count, int count2, int count3) {
      if (this.checkIndexByIndex(count2)) {
         int count4 = this.computeCount(count, count2);
         if (count4 >= 0) {
            int count5 = this.count14 + this.count9 / 2 - this.getCount20() / 2 + 2;
            int count6 = this.count10 + 4 - this.getCountAsIndex() + count4 * this.count5 + this.count13;
            int count7 = count - count5;
            int count8 = count2 - count6;
            if (this.get(count4).checkIndex(count4, count, count2, count3, count7, count8)) {
               this.setEnabled3(false);
               return true;
            }
         }
      }

      return false;
   }

   @Override
   public void applyIndex(int count, boolean flag, int count2, int count3) {
   }

   public abstract PreUpdateDispatcher get(int count);

   public SplineCalculator(Minecraft mc, int count, int count2, int count3, int count4, int count5) {
      super(mc, count, count2, count3, count4, count5);
   }
}
