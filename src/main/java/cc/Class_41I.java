package cc;

import java.lang.invoke.MethodHandles;
import java.util.Arrays;
import java.util.HashSet;
import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import net.Class_4f;

enum Class_41I {
   LIST(new String[] {"list", "get", "l"}),
   CLEAR(new String[] {"clear", "c"}),
   SAVE(new String[] {"save", "s"}),
   INFO(new String[] {"info", "show", "i"}),
   DELETE(new String[] {"delete", "d"}),
   RESTORE(new String[] {"restore"}),
   GOAL(new String[] {"goal", "g"}),
   GOTO(new String[] {"goto"});
   private final String[] field_45;
   private static final Class_41I[] field_40;

   public static String[] method_47() {
      HashSet<String> var0 = new HashSet<>();

      for (Class_41I var4 : values()) {
         var0.addAll(Arrays.asList(var4.field_45));
      }

      return var0.toArray(new String[0]);
   }

   Class_41I(String[] var3) {
      this.field_45 = var3;
   }

   static String[] method_40(Class_41I var0) {
      return var0.field_45;
   }

   private static RuntimeException rethrowEnumInitializationException(Exception exception) {
      Class_41I.<RuntimeException>throwUnchecked(exception);
      throw new AssertionError();
   }

   @SuppressWarnings("unchecked")
   private static <T extends Throwable> void throwUnchecked(Throwable throwable) throws T {
      throw (T)throwable;
   }

   static {
      try {
      long var9 = Class_4f.a(-5812989727212747257L, 8527885256562941415L, MethodHandles.lookup().lookupClass()).a(65389293571640L) ^ 108226628916012L;
      Cipher var1;
      Cipher var10000 = var1 = Cipher.getInstance("DES/CBC/PKCS5Padding");
      SecretKeyFactory var10002 = SecretKeyFactory.getInstance("DES");
      byte[] var10003 = new byte[]{(byte)(var9 >>> 56), 0, 0, 0, 0, 0, 0, 0};
      int var2 = 1;

      try {
         while (var2 < 8) {
            var10003[var2] = (byte)(var9 << var2 * 8 >>> 56);
            var2++;
         }
      } catch (Class_40Z var11) {
         throw a(var11);
      }

      var10000.init(2, var10002.generateSecret(new DESKeySpec(var10003)), new IvParameterSpec(new byte[8]));
      String[] var0 = new String[18];
      int var6 = 0;
      String var5 = "Tz\u001b¿èxÐ½\b¾\u001b@±\\¼©\"\b\"oS\u0013ÅÖG\u0006\b¤\u0011°ý5ì¨²\b»¡\\Z2,\u0095½\b °åk=\u0000j×\bë\u008e\u0016\u009e!Lc¼\b¤ûs\u0090 <ÓO\bÑö¶EG\u009bx¯\bO¢\u008b*&aº\u0087\b¤\n¿µ»¾\u00adþ\b\"·\u001fgF\u0084ä\u0099\brÊ\u0004\u0084\u009bÆ\u009f'\b{º2\u008dx^é{\bu1üÓéSÅ]\bú|<9Ê2½=";
      int var7 = "Tz\u001b¿èxÐ½\b¾\u001b@±\\¼©\"\b\"oS\u0013ÅÖG\u0006\b¤\u0011°ý5ì¨²\b»¡\\Z2,\u0095½\b °åk=\u0000j×\bë\u008e\u0016\u009e!Lc¼\b¤ûs\u0090 <ÓO\bÑö¶EG\u009bx¯\bO¢\u008b*&aº\u0087\b¤\n¿µ»¾\u00adþ\b\"·\u001fgF\u0084ä\u0099\brÊ\u0004\u0084\u009bÆ\u009f'\b{º2\u008dx^é{\bu1üÓéSÅ]\bú|<9Ê2½="
         .length();
      char var4 = '\b';
      int var13 = -1;

      label31:
      while (true) {
         String var14 = var5.substring(++var13, var13 + var4);
         byte var10001 = -1;

         while (true) {
            byte[] var8 = var1.doFinal(var14.getBytes("ISO-8859-1"));
            String var20 = a(var8).intern();
            switch (var10001) {
               case 0:
                  var0[var6++] = var20;
                  if ((var13 += var4) >= var7) {
                     field_40 = new Class_41I[]{LIST, CLEAR, SAVE, INFO, DELETE, RESTORE, GOAL, GOTO};
                     break label31;
                  }

                  var4 = var5.charAt(var13);
                  break;
               default:
                  var0[var6++] = var20;
                  if ((var13 += var4) < var7) {
                     var4 = var5.charAt(var13);
                     continue label31;
                  }

                  var5 = "a\r\u0000ë\u0000\u0001\u0012z\bÙ\u0099\u009dVz¾ÉR";
                  var7 = "a\r\u0000ë\u0000\u0001\u0012z\bÙ\u0099\u009dVz¾ÉR".length();
                  var4 = '\b';
                  var13 = -1;
            }

            var14 = var5.substring(++var13, var13 + var4);
            var10001 = 0;
         }
      }

      } catch (Exception exception) {
         throw rethrowEnumInitializationException(exception);
      }
   }

   public static Class_41I method_42(String var0) {
      for (Class_41I var4 : values()) {
         for (String var8 : var4.field_45) {
            try {
               if (var8.equalsIgnoreCase(var0)) {
                  return var4;
               }
            } catch (Class_40Z var9) {
               throw a(var9);
            }
         }
      }

      return null;
   }

   private static String a(byte[] var0) {
      int var1 = 0;
      int var2;
      char[] var3 = new char[var2 = var0.length];

      for (int var4 = 0; var4 < var2; var4++) {
         int var5;
         int var10000 = var5 = 255 & var0[var4];

         try {
            if (var10000 < 192) {
               var3[var1++] = (char)var5;
               continue;
            }
         } catch (Class_40Z var7) {
            throw a(var7);
         }

         if (var5 < 224) {
            char var6 = (char)((char)(var5 & 31) << 6);
            byte var9 = var0[++var4];
            var6 = (char)(var6 | (char)(var9 & 63));
            var3[var1++] = var6;
         } else if (var4 < var2 - 2) {
            char var13 = (char)((char)(var5 & 15) << '\f');
            byte var10 = var0[++var4];
            var13 = (char)(var13 | (char)(var10 & 63) << 6);
            var10 = var0[++var4];
            var13 = (char)(var13 | (char)(var10 & 63));
            var3[var1++] = var13;
         }
      }

      return new String(var3, 0, var1);
   }

   private static Class_40Z a(Class_40Z var0) {
      return var0;
   }
}
