package client.util;

import net.minecraft.client.Minecraft;
import net.minecraft.util.MathHelper;

public class StringEntryProvider extends ServiceStringEntry {
   public double value2;
   public double value;

   @Override
   public void updateState3() {
      if (!this.list.isEmpty()) {
         Minecraft mc = Minecraft.getMinecraft();
         double offset = 0.0;
         if (mc.theWorld != null && mc.thePlayer != null) {
            offset = (double)mc.theWorld.computeFactor(1.0F);
            if (!mc.theWorld.secondaryFloatArrayEntry.isActive2()) {
               offset = Math.random();
            }
         }

         double offset2 = offset - this.value2;

         while (offset2 < -0.5) {
            offset2++;
         }

         while (offset2 >= 0.5) {
            offset2--;
         }

         double doubleItem = MathHelper.getDouble7(offset2, -1.0, 1.0);
         this.value += doubleItem * 0.1;
         this.value *= 0.8;
         this.value2 = this.value2 + this.value;
         int count = (int)((this.value2 + 1.0) * (double)this.list.size()) % this.list.size();

         while (count < 0) {
            count = (count + this.list.size()) % this.list.size();
         }

         if (count != this.count10) {
            this.count10 = count;
            SpecialLoggerEntry.applyIndexArray((int[][])this.list.get(this.count10), this.count, this.count2, this.count3, this.count6, false, false);
         }
      }
   }

   public StringEntryProvider(String text) {
      super(text);
   }
}
