package client.util;

import client.event.EventRenderHand;
import client.event.EventRenderHandCallback;
import net.minecraft.client.Minecraft;
import net.minecraft.util.MathHelper;

public class MinecraftTimer {
   public float scaleFactor;
   public double coordOffset;
   public int indexOffset;
   public float scaleFactor6;
   public float scaleFactor2 = 1.0F;
   public float scaleFactor3;
   public long epochMillis;
   public long epochMillis1;
   public long epochMillis5;
   public double coordOffset4 = 1.0;

   public void updateState() {
      long timestamp = Minecraft.getTimestamp();
      long value = timestamp - this.epochMillis;
      long value2 = System.nanoTime() / 1000000L;
      double offset = (double)value2 / 1000.0;
      if (value <= 1000L && value >= 0L) {
         this.epochMillis5 += value;
         if (this.epochMillis5 > 1000L) {
            long value3 = value2 - this.epochMillis1;
            double offset2 = (double)this.epochMillis5 / (double)value3;
            this.coordOffset4 = this.coordOffset4 + (offset2 - this.coordOffset4) * 0.2F;
            this.epochMillis1 = value2;
            this.epochMillis5 = 0L;
         }

         if (this.epochMillis5 < 0L) {
            this.epochMillis1 = value2;
         }
      } else {
         this.coordOffset = offset;
      }

      this.epochMillis = timestamp;
      double offset3 = (offset - this.coordOffset) * this.coordOffset4;
      this.coordOffset = offset;
      double doubleItem = MathHelper.getDouble7(offset3, 0.0, 1.0);
      this.scaleFactor3 = (float)((double)this.scaleFactor3 + doubleItem * (double)this.scaleFactor2 * (double)this.scaleFactor);
      EventRenderHand event = EventRenderHandCallback.resolveEventRenderHand(this.scaleFactor3, this.indexOffset);
      this.scaleFactor3 = event.getFactor();
      this.indexOffset = event.getIndex();
      this.indexOffset = (int)this.scaleFactor3;
      this.scaleFactor3 = this.scaleFactor3 - (float)this.indexOffset;
      if (this.indexOffset > 10) {
         this.indexOffset = 10;
      }

      this.scaleFactor6 = this.scaleFactor3;
   }

   public void processAction(MinecraftTimer minecraftTimer) {
      this.scaleFactor = minecraftTimer.scaleFactor;
      this.indexOffset = minecraftTimer.indexOffset;
      this.scaleFactor6 = minecraftTimer.scaleFactor6;
      this.scaleFactor3 = minecraftTimer.scaleFactor3;
      this.coordOffset = minecraftTimer.coordOffset;
      this.epochMillis1 = minecraftTimer.epochMillis1;
      this.epochMillis = minecraftTimer.epochMillis;
      this.epochMillis5 = minecraftTimer.epochMillis5;
      this.scaleFactor2 = minecraftTimer.scaleFactor2;
      this.coordOffset4 = minecraftTimer.coordOffset4;
   }

   public MinecraftTimer(float factor) {
      this.scaleFactor = factor;
      this.epochMillis = Minecraft.getTimestamp();
      this.epochMillis1 = System.nanoTime() / 1000000L;
   }
}
