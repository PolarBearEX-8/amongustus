package cc;

import java.lang.invoke.MethodHandles;
import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import net.Class_4f;

public enum Class_45O {
   MOJANG("https://authserver.mojang.com/", "https://sessionserver.mojang.com/"),
   THEALTENING("http://authserver.thealtening.com/", "http://sessionserver.thealtening.com/");
   private final String field_41;
   private final String field_40;

   private static Class_40Z a(Class_40Z var0) {
      return var0;
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

   public String method_42() {
      return this.field_40;
   }

   public String method_43() {
      return this.field_41;
   }

   Class_45O(String var3, String var4) {
      this.field_41 = var3;
      this.field_40 = var4;
   }

   private static RuntimeException rethrowEnumInitializationException(Exception exception) {
      Class_45O.<RuntimeException>throwUnchecked(exception);
      throw new AssertionError();
   }

   @SuppressWarnings("unchecked")
   private static <T extends Throwable> void throwUnchecked(Throwable throwable) throws T {
      throw (T)throwable;
   }

   static {
      try {
      long var9 = Class_4f.a(-4981413626769580295L, 550830026839245821L, MethodHandles.lookup().lookupClass()).a(60626565876701L) ^ 60375114715068L;
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
      String[] var0 = new String[6];
      int var6 = 0;
      String var5 = "\u0002\u0091q~\u0007\u008eè¡2mDÕ:ù\u001e¤¶qK\u001eä( Èç\u0085\u0088Õ}SyH\u0096VTõ\u007fú\u000eS\u0010\u009dòà]Ný¿®ìÆ¿\u0015\u009d\u0082ð.(k;TÆ¢Ñ\u0014\u008e@+ì/h\u000ef¦\u008bõ\u008c«¦¦£áí\u0016\u0083[¶t\u0094÷r\u001fþ\u0019}wup\b¨«ÌO\u009f\u0084\u009b\u001e";
      int var7 = "\u0002\u0091q~\u0007\u008eè¡2mDÕ:ù\u001e¤¶qK\u001eä( Èç\u0085\u0088Õ}SyH\u0096VTõ\u007fú\u000eS\u0010\u009dòà]Ný¿®ìÆ¿\u0015\u009d\u0082ð.(k;TÆ¢Ñ\u0014\u008e@+ì/h\u000ef¦\u008bõ\u008c«¦¦£áí\u0016\u0083[¶t\u0094÷r\u001fþ\u0019}wup\b¨«ÌO\u009f\u0084\u009b\u001e"
         .length();
      char var4 = '(';
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

                  var5 = "M:q3kýE?)|\u001el¨³\u008bå#Ü\u0093\u0014W¨±î\u0001\u008eSSzE\\\u000eàÀ4Æ\u0019·PÏ k;TÆ¢Ñ\u0014\u008e2·{;\u0015\u0019è¹½¡\u00129±ált\u0000åÕå|ðµ\u00ad";
                  var7 = "M:q3kýE?)|\u001el¨³\u008bå#Ü\u0093\u0014W¨±î\u0001\u008eSSzE\\\u000eàÀ4Æ\u0019·PÏ k;TÆ¢Ñ\u0014\u008e2·{;\u0015\u0019è¹½¡\u00129±ált\u0000åÕå|ðµ\u00ad"
                     .length();
                  var4 = '(';
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
}
