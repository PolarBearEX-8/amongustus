package net.minecraft.client.main;

import client.util.FovEvaluator;
import java.util.Arrays;

public class Main {
   public static Object[] resolveTargetArray(Object[] entryArray, Object[] entryArray2) {
      Object[] entryArray3 = Arrays.copyOf(entryArray, entryArray.length + entryArray2.length);
      System.arraycopy(entryArray2, 0, entryArray3, entryArray.length, entryArray2.length);
      return entryArray3;
   }

   public static void main(String[] textArray) {
      FovEvaluator.main(
         (String[])resolveTargetArray(
            new String[]{"--version", "beta", "--accessToken", "0", "--assetsDir", "assets", "--assetIndex", "1.8", "--userProperties", "{}"}, textArray
         )
      );
   }
}
