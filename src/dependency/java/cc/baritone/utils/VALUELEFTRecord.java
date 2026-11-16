package cc.baritone.utils;

public class VALUELEFTRecord {
   public static final int count2 = 0;
   public static final int count3 = 1;
   public static final int count4 = 2;
   public static final int count5 = 3;
   public static final int count6 = 4;
   public static final int count7 = 5;
   public static final int count8 = 6;
   public static final int count9 = -1;
   public int count = 0;
   public Object dataRef = null;

   @Override
   public String toString() {
      StringBuffer builder = new StringBuffer();
      switch (this.count) {
         case -1:
            builder.append("END OF FILE");
            break;
         case 0:
            builder.append("VALUE(").append(this.dataRef).append(")");
            break;
         case 1:
            builder.append("LEFT BRACE({)");
            break;
         case 2:
            builder.append("RIGHT BRACE(})");
            break;
         case 3:
            builder.append("LEFT SQUARE([)");
            break;
         case 4:
            builder.append("RIGHT SQUARE(])");
            break;
         case 5:
            builder.append("COMMA(,)");
            break;
         case 6:
            builder.append("COLON(:)");
      }

      return builder.toString();
   }

   public VALUELEFTRecord(int count10, Object entry) {
      this.count = count10;
      this.dataRef = entry;
   }
}
