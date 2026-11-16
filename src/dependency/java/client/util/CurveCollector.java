package client.util;

public class CurveCollector extends CurveAccumulator {
   public int count5 = 1;

   public void applyCurveAccumulator2(CurveAccumulator curveAccumulator) {
      long value = 0x5a33bcdd27da7ebaL;

      while (true) {
         if ((int)(value % 4L) != 2) {
            this.applyCurveAccumulator(curveAccumulator);
            break;
         }

         if (this.count5 != 1) {
            this.count5 = this.count5 * this.computeCount2(curveAccumulator);
            break;
         }

         value = (value ^ -0x8a57ebb77b65258L) + 0x7081a77f5aeedc62L;
      }
   }

   public void applyCurveCollectorByCurveCollector(CurveCollector curveCollector) {
      long value = 0x2e1fddd06432d297L;

      while (true) {
         if ((int)(value % 4L) == 2) {
            this.applyCurveAccumulator(curveCollector);
            break;
         }

         if (this.count5 != curveCollector.count5) {
            this.count5 = this.count5 * this.computeCount2(curveCollector);
            break;
         }

         value = (value ^ 0x2d63281c26521a55L) + 0x5560b4b7c96da7d8L;
      }
   }

   public void applyCurveCollector(CurveCollector curveCollector) {
      if (this.count5 != curveCollector.count5) {
         this.applyCurveAccumulator(curveCollector);
      } else {
         this.count5 = this.count5 * this.computeCount2(curveCollector);
      }
   }

   public void applyCurveAccumulator3(CurveAccumulator curveAccumulator) {
      long value = 0x739e597644c6f918L;
      long value2 = -0x4b31ce1bd005532eL;

      while (true) {
         int count = (int)(value % 6L);
         if (count == 5) {
            this.count5 = this.count5 * this.computeCount2(curveAccumulator);
            value = (value ^ -0x62fc415712914dd8L) + 0x26673b1ad3698202L;
            value2 = (value2 ^ -0xf08b5c130ab6856L) - 0x2ccb5b09cbc39071L;
         } else if (count != 2) {
            if (count != 0) {
               if (count != 1) {
                  this.count5 = 1;
                  break;
               }

               value = (value ^ 0x663dda4c1e8e326cL) - 0xe60dac7c23405b7L;
               value2 = (value2 ^ 0x602c729bb1d576e5L) + 0x572bcef9e8683925L;
            }

            if (this.count4 != 0) {
               break;
            }

            value = (value ^ -0x4d284b7a7f654fd4L) - 0x320ea890d2680bf0L;
            value2 = (value2 ^ 0x5fdcbec6f424064L) - 0x21181bad9a0d3c8fL;
         } else if (this.count5 != 1) {
            long value3 = (value ^ -0x1d786dedb5f13b7L) - 0x6bf47ba61758048eL;
            long value4 = (value2 ^ 0x4db8dd981f82bd3cL) - 0x417597f6223870f2L;
            this.applyCurveAccumulator(curveAccumulator);
            value = (value3 ^ 0x1435357b4df1b222L) + 0x3a7126b904c7a17L;
            value2 = (value4 ^ 0x7fe337aada62ad99L) + 0x677691ead3f601d9L;
         } else {
            value = (value ^ 0x3c8133ec57c8b07dL) + 0x173a6ca485bbf384L;
            value2 = (value2 ^ -0xa2a3d54249c83b5L) - 0x2ce00d9edf4229d2L;
         }
      }
   }

   public CurveCollector(CurveAccumulator curveAccumulator) {
      super(curveAccumulator);
   }

   public CurveCollector(int count) {
      super(count);
   }

   public CurveCollector() {
   }

   @Override
   public String toString() {
      int count = this.count5;
      return this.resolveSignumComparableByIndex(count).toString();
   }
}
