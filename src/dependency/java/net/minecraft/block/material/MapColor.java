package net.minecraft.block.material;

public class MapColor {
   public static final MapColor[] mapColorArray = new MapColor[64];
   public static final MapColor mapColor19 = new MapColor(0, 0);
   public static final MapColor mapColor17 = new MapColor(1, 8368696);
   public static final MapColor mapColor20 = new MapColor(2, 16247203);
   public static final MapColor mapColor22 = new MapColor(3, 13092807);
   public static final MapColor mapColor8 = new MapColor(4, 16711680);
   public static final MapColor mapColor32 = new MapColor(5, 10526975);
   public static final MapColor mapColor34 = new MapColor(6, 10987431);
   public static final MapColor mapColor26 = new MapColor(7, 31744);
   public static final MapColor mapColor6 = new MapColor(8, 16777215);
   public static final MapColor mapColor28 = new MapColor(9, 10791096);
   public static final MapColor mapColor4 = new MapColor(10, 9923917);
   public static final MapColor mapColor10 = new MapColor(11, 7368816);
   public static final MapColor mapColor9 = new MapColor(12, 4210943);
   public static final MapColor mapColor18 = new MapColor(13, 9402184);
   public static final MapColor mapColor30 = new MapColor(14, 16776437);
   public static final MapColor mapColor36 = new MapColor(15, 14188339);
   public static final MapColor mapColor12 = new MapColor(16, 11685080);
   public static final MapColor mapColor31 = new MapColor(17, 6724056);
   public static final MapColor mapColor3 = new MapColor(18, 15066419);
   public static final MapColor mapColor14 = new MapColor(19, 8375321);
   public static final MapColor mapColor2 = new MapColor(20, 15892389);
   public static final MapColor mapColor16 = new MapColor(21, 5000268);
   public static final MapColor mapColor11 = new MapColor(22, 10066329);
   public static final MapColor mapColor24 = new MapColor(23, 5013401);
   public static final MapColor mapColor35 = new MapColor(24, 8339378);
   public static final MapColor mapColor21 = new MapColor(25, 3361970);
   public static final MapColor mapColor13 = new MapColor(26, 6704179);
   public static final MapColor mapColor25 = new MapColor(27, 6717235);
   public static final MapColor mapColor5 = new MapColor(28, 10040115);
   public static final MapColor mapColor29 = new MapColor(29, 1644825);
   public static final MapColor mapColor23 = new MapColor(30, 16445005);
   public static final MapColor mapColor = new MapColor(31, 6085589);
   public static final MapColor mapColor15 = new MapColor(32, 4882687);
   public static final MapColor mapColor27 = new MapColor(33, 55610);
   public static final MapColor mapColor7 = new MapColor(34, 8476209);
   public static final MapColor mapColor33 = new MapColor(35, 7340544);
   public int indexOffset;
   public final int count;

   public int computeCount(int count2) {
      short shortValue = 220;
      if (count2 == 3) {
         shortValue = 135;
      }

      if (count2 == 2) {
         shortValue = 255;
      }

      if (count2 == 1) {
         shortValue = 220;
      }

      if (count2 == 0) {
         shortValue = 180;
      }

      int count3 = (this.indexOffset >> 16 & 0xFF) * shortValue / 255;
      int count4 = (this.indexOffset >> 8 & 0xFF) * shortValue / 255;
      int count5 = (this.indexOffset & 0xFF) * shortValue / 255;
      return 0xFF000000 | count3 << 16 | count4 << 8 | count5;
   }

   private MapColor(int count2, int count3) {
      if (count2 >= 0 && count2 <= 63) {
         this.count = count2;
         this.indexOffset = count3;
         mapColorArray[count2] = this;
      } else {
         throw new IndexOutOfBoundsException("Map colour ID must be between 0 and 63 (inclusive)");
      }
   }
}
