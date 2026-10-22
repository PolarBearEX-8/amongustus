package cc;

import java.lang.invoke.MethodHandles;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import net.Class_4f;

public enum Class_45S {
   HOME(new String[] {"home", "base"}),
   DEATH(new String[] {"death"}),
   BED(new String[] {"bed", "spawn"}),
   USER(new String[] {"user"});
   private static final List field_49;
   public final String[] names;
   private static final Class_45S[] field_40;

   Class_45S(String[] var3) {
      this.names = var3;
   }

   public String method_40() {
      return this.names[0];
   }

   private static RuntimeException rethrowEnumInitializationException(Exception exception) {
      Class_45S.<RuntimeException>throwUnchecked(exception);
      throw new AssertionError();
   }

   @SuppressWarnings("unchecked")
   private static <T extends Throwable> void throwUnchecked(Throwable throwable) throws T {
      throw (T)throwable;
   }

   static {
      try {
      long var9 = Class_4f.a(4957837575814902413L, -7093148920567392483L, MethodHandles.lookup().lookupClass()).a(138069025272486L) ^ 33105708982991L;
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
      String[] var0 = new String[10];
      int var6 = 0;
      String var5 = "#1r\u0010ä\u001e\u0012!\b\u000f®2ç>\u0090¡»\bÊÅ$ÿ\"T¢8\bKáB§³\u0011\u0092Þ\b\u0012ë©ñ\u0012~õk\bæ\u0000\u009a\u0017%J%1\b\u0015¥5nýmñå\b\u009a\u000fñ\u001aªLÀD";
      int var7 = "#1r\u0010ä\u001e\u0012!\b\u000f®2ç>\u0090¡»\bÊÅ$ÿ\"T¢8\bKáB§³\u0011\u0092Þ\b\u0012ë©ñ\u0012~õk\bæ\u0000\u009a\u0017%J%1\b\u0015¥5nýmñå\b\u009a\u000fñ\u001aªLÀD"
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
                     field_40 = new Class_45S[]{HOME, DEATH, BED, USER};
                     field_49 = Collections.unmodifiableList(Arrays.asList(values()));
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

                  var5 = "P?LcÒs¶!\bèÚÛQÞ\u0090\"t";
                  var7 = "P?LcÒs¶!\bèÚÛQÞ\u0090\"t".length();
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

   public static Class_45S method_48(String var0) {
      for (Class_45S var4 : values()) {
         for (String var8 : var4.names) {
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

   public static String[] method_47() {
      HashSet<String> var0 = new HashSet<>();

      for (Class_45S var4 : values()) {
         var0.addAll(Arrays.asList(var4.names));
      }

      return var0.toArray(new String[0]);
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
