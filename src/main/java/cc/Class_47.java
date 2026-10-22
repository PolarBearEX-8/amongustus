package cc;

import java.lang.invoke.MethodHandles;
import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import net.Class_47qc;
import net.Class_4f;

public enum Class_47 {
   NONE("wdl.backup.none", ""),
   FOLDER("wdl.backup.folder", "wdl.saveProgress.backingUp.title.folder"),
   ZIP("wdl.backup.zip", "wdl.saveProgress.backingUp.title.zip");
   public final String descriptionKey;
   public final String titleKey;
   private static final Class_47[] field_44;

   public String Class_47() {
      return Class_47qc.method_44(this.titleKey, new Object[0]);
   }

   public static Class_47 method_46(String var0) {
      for (Class_47 var4 : values()) {
         try {
            if (var4.name().equalsIgnoreCase(var0)) {
               return var4;
            }
         } catch (Class_40Z var5) {
            throw a(var5);
         }
      }

      return NONE;
   }

   Class_47(String var3, String var4) {
      this.descriptionKey = var3;
      this.titleKey = var4;
   }

   public String method_44() {
      return Class_47qc.method_44(this.descriptionKey, new Object[0]);
   }

   private static RuntimeException rethrowEnumInitializationException(Exception exception) {
      Class_47.<RuntimeException>throwUnchecked(exception);
      throw new AssertionError();
   }

   @SuppressWarnings("unchecked")
   private static <T extends Throwable> void throwUnchecked(Throwable throwable) throws T {
      throw (T)throwable;
   }

   static {
      try {
      long var9 = Class_4f.a(-2984771605336616372L, -3321724196259427225L, MethodHandles.lookup().lookupClass()).a(188970109488092L) ^ 77768564904938L;
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
      String[] var0 = new String[8];
      int var6 = 0;
      String var5 = "Q=;,\u0018Á\u0013L\u0010AËÙ\"\u0087âM!ôµ1²r\u001dÇ&\b~(\u00900©ª¨½\u0010AËÙ\"\u0087âM!r8s\u0003áÛú\u0006\b¶ºÞ¥\u0000á\u0094¼\u0018AËÙ\"\u0087âM!g`âñ|k6rì~Ð§»\u0010äg";
      int var7 = "Q=;,\u0018Á\u0013L\u0010AËÙ\"\u0087âM!ôµ1²r\u001dÇ&\b~(\u00900©ª¨½\u0010AËÙ\"\u0087âM!r8s\u0003áÛú\u0006\b¶ºÞ¥\u0000á\u0094¼\u0018AËÙ\"\u0087âM!g`âñ|k6rì~Ð§»\u0010äg"
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
                     field_44 = new Class_47[]{NONE, FOLDER, ZIP};
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

                  var5 = "\u0091¢UµX¨¦[6\u0095\u007f¿ÑÐÑø'\u0015±\u0097\u0081H¨ä\u0084Ðß\u009dHi+\u008dnÚ<q£\u000bOÞ(\u0091¢UµX¨¦[6\u0095\u007f¿ÑÐÑø'\u0015±\u0097\u0081H¨ä\u0084Ðß\u009dHi+\u008dÈYH\u0007\u001cdq\u0007";
                  var7 = "\u0091¢UµX¨¦[6\u0095\u007f¿ÑÐÑø'\u0015±\u0097\u0081H¨ä\u0084Ðß\u009dHi+\u008dnÚ<q£\u000bOÞ(\u0091¢UµX¨¦[6\u0095\u007f¿ÑÐÑø'\u0015±\u0097\u0081H¨ä\u0084Ðß\u009dHi+\u008dÈYH\u0007\u001cdq\u0007"
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
