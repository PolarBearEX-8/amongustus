package net.raphimc.viaproxy;

import java.math.BigInteger;

public class OamDataRecord {
   public static final String labelText;
   public static final byte[] byteFlagArray = new byte[]{2, 20, 12, 3};
   public static final int count = -new BigInteger(byteFlagArray).intValueExact();
   public static final String labelText2;
   public static final String labelText3;

   static {
      String[] textArray = new String[]{"oam:", "oam:data", "oam:join"};
      labelText = textArray[0];
      labelText2 = textArray[2];
      labelText3 = textArray[1];
   }
}
