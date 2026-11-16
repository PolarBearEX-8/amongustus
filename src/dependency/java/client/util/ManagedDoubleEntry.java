package client.util;

import client.Client;
import client.render.RenderUtils;
import java.awt.Color;
import java.io.IOException;
import net.minecraft.util.MathHelper;

public class ManagedDoubleEntry {
   public boolean enabled2;
   public boolean enabled3;
   public double value2 = 0.0;
   public int count;
   public boolean enabled;
   public double value;
   public int count5;
   public int count4;
   public int count6;
   public long timestamp;
   public int count2;
   public double value3;
   public int count3;
   public boolean enabled4 = false;

   public void applyIndex(int count7, int count8, int count9) {
      this.enabled = false;
      if (this.enabled2) {
         ;
      }
   }

   public int computeCount(int count7, int count8, int count9, int count10, int count11, int count12, int count13, int count14, int count15, int count16) {
      int count17 = count9 - count8;
      if (count7 <= count17) {
         this.value3 = 0.0;
         return 0;
      } else {
         int count18 = Math.round((float)Client.getClient().getInternalRenderListenerAdapte().count / 3.0F);
         if (!RenderUtils.checkCondition((float)count11, (float)count12, (float)count13, (float)count14, count15, count16) || KeyUtils.isActive()) {
            count18 = 0;
         }

         if (this.enabled != this.enabled3) {
            this.value2 = 0.0;
         }

         if (this.enabled) {
            if (this.enabled4) {
               double offset = (double)(this.count3 - count16) * ((double)count7 / (double)count17);
               count18 = (int)(offset + this.value2);
               this.value2 += offset - (double)count18;
               this.count3 = count16;
            } else {
               double offset2 = (double)(this.count5 - count15) * ((double)count7 / (double)count17);
               count18 = (int)(offset2 + this.value2);
               this.value2 += offset2 - (double)count18;
               this.count5 = count15;
            }
         }

         this.value3 += (double)count18;
         if (count18 != 0) {
            this.value = Math.abs(this.value3 / 200.0);
         }

         long value4 = System.nanoTime() - this.timestamp;
         double doubleItem = MathHelper.getDouble7((double)((long)this.value3), -this.value * ((double)value4 / 1000000.0), this.value * ((double)value4 / 1000000.0));
         long value5 = (long)(doubleItem + this.value2);
         if (!this.enabled) {
            this.value2 += doubleItem - (double)value5;
         }

         this.timestamp = System.nanoTime();
         int count19 = count7 - count17;
         if (this.enabled) {
            value5 = (long)this.value3;
            doubleItem = this.value3;
         }

         int count20 = (int)value5;
         long value6 = (long)(MathHelper.getInt12((int)((long)count10 + value5), -count19, 0) - count10);
         this.value3 = (long)count20 != value6 ? 0.0 : this.value3 - doubleItem;
         if (value6 != (long)count20 && count18 != 0) {
            this.updateState();
         }

         this.enabled3 = this.enabled;
         return (int)((long)count10 + value6);
      }
   }

   public boolean matchesColorByColor(int count7, int count8, int count9, int count10, int count11, int count12, Color color) {
      this.enabled4 = false;
      this.count = count7;
      this.count2 = count8;
      this.count6 = count9;
      this.count4 = count10;
      int count13 = count9 - count7;
      if (count12 <= count13) {
         this.enabled2 = false;
         this.enabled = false;
         return false;
      } else {
         double offset = (double)count13 / 100.0;
         int count14 = (int)((double)(count11 - count7) / (double)count12 * 100.0 * offset) + count7;
         int count15 = (int)((double)count13 / (double)count12 * 100.0 * offset);
         this.count = count14;
         this.count6 = count14 + count15;
         RenderUtils.processAction((float)this.count, (float)this.count2, (float)this.count6, (float)this.count4, 4.0F, 1, color);
         this.enabled2 = true;
         return true;
      }
   }

   public void applyIndexByIndex(int count7, int count8, int count9) throws IOException {
      if (this.enabled2 && count9 == 0) {
         if (RenderUtils.checkCondition(count7, count8, (double)this.count, (double)this.count2, (double)this.count6, (double)this.count4)) {
            this.enabled = true;
            this.count5 = count7;
            this.count3 = count8;
         }
      }
   }

   public void updateState() {
      this.value3 = 0.0;
   }

   public boolean matchesColor(int count7, int count8, int count9, int count10, int count11, int count12, Color color) {
      this.enabled4 = true;
      int count13 = count10 - count8;
      if (count12 <= count13) {
         this.enabled2 = false;
         this.enabled = false;
         return false;
      } else {
         double offset = (double)count13 / 100.0;
         int count14 = (int)((double)(count11 - count8) / (double)count12 * 100.0 * offset) + count8;
         int count15 = (int)((double)count13 / (double)count12 * 100.0 * offset);
         this.count = count7;
         this.count2 = count14;
         this.count6 = count9;
         this.count4 = count14 + count15;
         RenderUtils.processAction((float)this.count, (float)this.count2, (float)this.count6, (float)this.count4, 4.0F, 1, color);
         this.enabled2 = true;
         return true;
      }
   }
}
