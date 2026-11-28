package net.minecraft.client.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.util.MathHelper;

public class ScaledResolution {
   private final double scaledwidthDouble;
   private final double scaledheightDouble;
   private int scaledWidth;
   private int scaledHeight;
   private int scaleFactor;

   public double getScaledHeight_double() {
      return this.scaledheightDouble;
   }

   public int getScaledWidth() {
      return this.scaledWidth;
   }

   public double getScaledWidth_double() {
      return this.scaledwidthDouble;
   }

   public int getScaledHeight() {
      return this.scaledHeight;
   }

   public ScaledResolution(Minecraft mc) {
      this.scaledWidth = mc.displayWidth;
      this.scaledHeight = mc.displayHeight;
      this.scaleFactor = 1;
      boolean isUnicode = mc.isUnicode();
      int guiScale = mc.gameSettings.guiScale;
      if (guiScale == 0) {
         guiScale = 1000;
      }

      while (this.scaleFactor < guiScale && this.scaledWidth / (this.scaleFactor + 1) >= 320 && this.scaledHeight / (this.scaleFactor + 1) >= 240) {
         this.scaleFactor++;
      }

      if (isUnicode && this.scaleFactor % 2 != 0 && this.scaleFactor != 1) {
         this.scaleFactor--;
      }

      this.scaledwidthDouble = (double)this.scaledWidth / (double)this.scaleFactor;
      this.scaledheightDouble = (double)this.scaledHeight / (double)this.scaleFactor;
      this.scaledWidth = MathHelper.ceiling_double_int(this.scaledwidthDouble);
      this.scaledHeight = MathHelper.ceiling_double_int(this.scaledheightDouble);
   }

   public int getScaleFactor() {
      return this.scaleFactor;
   }

   public ScaledResolution(double d, double d2, int n) {
      this.scaledwidthDouble = d;
      this.scaledheightDouble = d2;
      this.scaleFactor = n;
      this.scaledWidth = MathHelper.ceiling_double_int(this.scaledwidthDouble);
      this.scaledHeight = MathHelper.ceiling_double_int(this.scaledheightDouble);
   }
}
