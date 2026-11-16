package net.minecraft.client.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.util.MathHelper;

public class ScaledResolution {
   public final double coordOffset;
   public final double coordOffset3;
   public int indexOffset;
   public int indexOffset1;
   public int indexOffset2;

   public int getScaledWidth() {
      return this.indexOffset;
   }

   public int getScaleFactor() {
      return this.indexOffset2;
   }

   public double getCoordOffset3() {
      return this.coordOffset3;
   }

   public double getCoordOffset() {
      return this.coordOffset;
   }

   public int getScaledHeight() {
      return this.indexOffset1;
   }

   public ScaledResolution(Minecraft mc) {
      this.indexOffset = mc.displayWidth;
      this.indexOffset1 = mc.displayHeight;
      this.indexOffset2 = 1;
      boolean active = mc.isActive3();
      int indexOffset3 = mc.gameSettings.indexOffset109;
      if (indexOffset3 == 0) {
         indexOffset3 = 1000;
      }

      while (this.indexOffset2 < indexOffset3 && this.indexOffset / (this.indexOffset2 + 1) >= 320 && this.indexOffset1 / (this.indexOffset2 + 1) >= 240) {
         this.indexOffset2++;
      }

      if (active && this.indexOffset2 % 2 != 0 && this.indexOffset2 != 1) {
         this.indexOffset2--;
      }

      this.coordOffset = (double)this.indexOffset / (double)this.indexOffset2;
      this.coordOffset3 = (double)this.indexOffset1 / (double)this.indexOffset2;
      this.indexOffset = MathHelper.truncateDoubleToInt(this.coordOffset);
      this.indexOffset1 = MathHelper.truncateDoubleToInt(this.coordOffset3);
   }

   public ScaledResolution(double offset, double offset2, int count) {
      this.coordOffset = offset;
      this.coordOffset3 = offset2;
      this.indexOffset2 = count;
      this.indexOffset = MathHelper.truncateDoubleToInt(this.coordOffset);
      this.indexOffset1 = MathHelper.truncateDoubleToInt(this.coordOffset3);
   }
}
