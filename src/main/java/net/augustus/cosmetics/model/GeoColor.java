package net.augustus.cosmetics.model;

public final class GeoColor {
   private final int color;

   private GeoColor(int n) {
      this.color = n;
   }

   public GeoColor darker(double d) {
      return ofRGBA(
         Math.max((int)((double)this.getRed() * (1.0 / d)), 0),
         Math.max((int)((double)this.getGreen() * (1.0 / d)), 0),
         Math.max((int)((double)this.getBlue() * (1.0 / d)), 0),
         this.getAlpha()
      );
   }

   public int getAlpha() {
      return this.color >> 24 & 0xFF;
   }

   public int getBlue() {
      return this.color & 0xFF;
   }

   public GeoColor brighter(double d) {
      int red = this.getRed();
      int green = this.getGreen();
      int blue = this.getBlue();
      int n = (int)(1.0 / (1.0 - 1.0 / d));
      if (red == 0 && green == 0 && blue == 0) {
         return ofRGBA(n, n, n, this.getAlpha());
      } else {
         if (red > 0 && red < n) {
            red = n;
         }

         if (green > 0 && green < n) {
            green = n;
         }

         if (blue > 0 && blue < n) {
            blue = n;
         }

         return ofRGBA(
            Math.min((int)((double)red / (1.0 / d)), 255),
            Math.min((int)((double)green / (1.0 / d)), 255),
            Math.min((int)((double)blue / (1.0 / d)), 255),
            this.getAlpha()
         );
      }
   }

   public static GeoColor ofRGB(int n, int n2, int n3) {
      return ofRGBA(n, n2, n3, 255);
   }

   public static GeoColor ofHSB(float f, float f2, float f3) {
      return ofOpaque(HSBtoRGB(f, f2, f3));
   }

   public static GeoColor ofRGBA(int n, int n2, int n3, int n4) {
      return new GeoColor((n4 & 0xFF) << 24 | (n & 0xFF) << 16 | (n2 & 0xFF) << 8 | n3 & 0xFF);
   }

   @Override
   public boolean equals(Object obj) {
      if (this == obj) {
         return true;
      } else {
         return obj != null && this.getClass() == obj.getClass() ? this.color == ((GeoColor)obj).color : false;
      }
   }

   public int getGreen() {
      return this.color >> 8 & 0xFF;
   }

   @Override
   public String toString() {
      return String.valueOf(this.color);
   }

   public static int HSBtoRGB(float f, float f2, float f3) {
      int n = 0;
      int n2 = 0;
      int n3 = 0;
      if (f2 == 0.0F) {
         n = n2 = n3 = (int)(f3 * 255.0F + 0.5F);
      } else {
         float f4 = (f - (float)Math.floor((double)f)) * 6.0F;
         float f5 = f4 - (float)Math.floor((double)f4);
         float f6 = f3 * (1.0F - f2);
         float f7 = f3 * (1.0F - f2 * f5);
         float f8 = f3 * (1.0F - f2 * (1.0F - f5));
         switch ((int)f4) {
            case 0:
               n = (int)(f3 * 255.0F + 0.5F);
               n2 = (int)(f8 * 255.0F + 0.5F);
               n3 = (int)(f6 * 255.0F + 0.5F);
               break;
            case 1:
               n = (int)(f7 * 255.0F + 0.5F);
               n2 = (int)(f3 * 255.0F + 0.5F);
               n3 = (int)(f6 * 255.0F + 0.5F);
               break;
            case 2:
               n = (int)(f6 * 255.0F + 0.5F);
               n2 = (int)(f3 * 255.0F + 0.5F);
               n3 = (int)(f8 * 255.0F + 0.5F);
               break;
            case 3:
               n = (int)(f6 * 255.0F + 0.5F);
               n2 = (int)(f7 * 255.0F + 0.5F);
               n3 = (int)(f3 * 255.0F + 0.5F);
               break;
            case 4:
               n = (int)(f8 * 255.0F + 0.5F);
               n2 = (int)(f6 * 255.0F + 0.5F);
               n3 = (int)(f3 * 255.0F + 0.5F);
               break;
            case 5:
               n = (int)(f3 * 255.0F + 0.5F);
               n2 = (int)(f6 * 255.0F + 0.5F);
               n3 = (int)(f7 * 255.0F + 0.5F);
         }
      }

      return 0xFF000000 | n << 16 | n2 << 8 | n3;
   }

   public static GeoColor ofRGB(float f, float f2, float f3) {
      return ofRGBA(f, f2, f3, 1.0F);
   }

   public int getRed() {
      return this.color >> 16 & 0xFF;
   }

   public int getColor() {
      return this.color;
   }

   public static GeoColor ofOpaque(int n) {
      return new GeoColor(0xFF000000 | n);
   }

   public static GeoColor ofTransparent(int n) {
      return new GeoColor(n);
   }

   public static GeoColor ofRGBA(float f, float f2, float f3, float f4) {
      return ofRGBA(
         (int)((double)(f * 255.0F) + 0.5), (int)((double)(f2 * 255.0F) + 0.5), (int)((double)(f3 * 255.0F) + 0.5), (int)((double)(f4 * 255.0F) + 0.5)
      );
   }

   @Override
   public int hashCode() {
      return this.color;
   }
}
