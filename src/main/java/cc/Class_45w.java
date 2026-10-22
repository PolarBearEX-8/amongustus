package cc;

import java.lang.invoke.MethodHandles;
import java.util.Arrays;
import java.util.HashSet;
import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import net.Class_4f;

enum Class_45w {
   POS1(new String[] {"pos1", "p1", "1"}),
   POS2(new String[] {"pos2", "p2", "2"}),
   CLEAR(new String[] {"clear", "c"}),
   UNDO(new String[] {"undo", "u"}),
   SET(new String[] {"set", "fill", "s", "f"}),
   WALLS(new String[] {"walls", "w"}),
   SHELL(new String[] {"shell", "shl"}),
   SPHERE(new String[] {"sphere", "sph"}),
   HSPHERE(new String[] {"hsphere", "hsph"}),
   CYLINDER(new String[] {"cylinder", "cyl"}),
   HCYLINDER(new String[] {"hcylinder", "hcyl"}),
   CLEARAREA(new String[] {"cleararea", "ca"}),
   REPLACE(new String[] {"replace", "r"}),
   EXPAND(new String[] {"expand", "ex"}),
   COPY(new String[] {"copy", "cp"}),
   PASTE(new String[] {"paste", "p"}),
   CONTRACT(new String[] {"contract", "ct"}),
   SHIFT(new String[] {"shift", "sh"});
   private final String[] field_44;
   private static final Class_45w[] field_46;

   public static String[] method_47() {
      HashSet<String> var0 = new HashSet<>();

      for (Class_45w var4 : values()) {
         var0.addAll(Arrays.asList(var4.field_44));
      }

      return var0.toArray(new String[0]);
   }

   private static RuntimeException rethrowEnumInitializationException(Exception exception) {
      Class_45w.<RuntimeException>throwUnchecked(exception);
      throw new AssertionError();
   }

   @SuppressWarnings("unchecked")
   private static <T extends Throwable> void throwUnchecked(Throwable throwable) throws T {
      throw (T)throwable;
   }

   static {
      try {
      long var9 = Class_4f.a(8302247608435246504L, 2834985420075555305L, MethodHandles.lookup().lookupClass()).a(89540489522141L) ^ 132714910693429L;
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
      String[] var0 = new String[49];
      int var6 = 0;
      String var5 = "9&Â\u0082pyF¶\b\u0011aðþf\u0091«\u0088\b>,ró;ºZ\u0085\b`*\u0085ad;Aó\b\"+eØªr\u0011f\u0010Û\u00ad-Å¦õ4»\u0017\u001c\u0080þ\u0088\u0087\u0090\u000e\bDm\u001eãg,Î´\bòË\u0081-¸Ö03\b¡kÆeñ\u0081\u000ba\bDó\u0094ã¾Úål\bHx3¼Ðqa\u009d\b\u009fCÎ\nOÿ¶!\boE\u0097o)Ý\u0091\u0096\bç\"\u0080\u00adÃ¯\u0083w\b\u0003.:Ûb\u009cÃz\b\u0001\u0083õ%\u0011P\u0016ú\u0010\u001aè\b\u00986\u008eEäO\u0000Ñ\u0017ÆÎ\u0014á\bÚ%ùÍ\u0006\u00046ñ\bnkæÏ*íßû\bJ÷]âì4 ±\bÐ5Cü<2@:\b\u0007±\nb\u0085\u0006Ì÷\bs\u0088llôÝ2Ý\b?Ó\u0015À\u0010\u0010\u0003´\bÿÜ·\u001fÚj/\u001b\b°\u0014ÀD×\u0016-\u0094\bV\u0081aã6Ãi\u0014\b\u0006ô\u0003·\u0011ú°±\u0010\u009a¦\u0095`9Åaµû\u0095é\u009bÑ^}\u0006\bD&ø\u0088¨¦\u007fØ\b<÷!6Æ*\r_\u0010\u0095)gý±9?â\n@OXàItØ\bO\u0019µ`Ò¹\u009e\u000b\btÀQ¾\u0085V\u009c¿\b\u0002ÑR}½8EK\bE\u001e´»Þ¤^ý\bh>\u008dQûv\u0004Å\bu±l}ÿ\u008cª_\u0010Ø©²\u0005ê%Ï[#`ä\u008d±³\u008bë\b\u000fÌä#2ã\u0001Ê\bþró´\u0001»\u0094ç\u0010ÿ½ÉÈµ\u0011\u008c}1\u0090¯8¶\u0013s[\bd\u009cRû\u0000ðÌ9\b\u001b\f\u0090) Ö§é\bêÕhõ\u008f¨å\"\u0010ð¼nEZ8î\u0095½\u0014Ô\fÓO\u008cÏ\u0010gÂ£\"\u001fÄ\u000b\u00869$\u0007#ub§Õ";
      int var7 = "9&Â\u0082pyF¶\b\u0011aðþf\u0091«\u0088\b>,ró;ºZ\u0085\b`*\u0085ad;Aó\b\"+eØªr\u0011f\u0010Û\u00ad-Å¦õ4»\u0017\u001c\u0080þ\u0088\u0087\u0090\u000e\bDm\u001eãg,Î´\bòË\u0081-¸Ö03\b¡kÆeñ\u0081\u000ba\bDó\u0094ã¾Úål\bHx3¼Ðqa\u009d\b\u009fCÎ\nOÿ¶!\boE\u0097o)Ý\u0091\u0096\bç\"\u0080\u00adÃ¯\u0083w\b\u0003.:Ûb\u009cÃz\b\u0001\u0083õ%\u0011P\u0016ú\u0010\u001aè\b\u00986\u008eEäO\u0000Ñ\u0017ÆÎ\u0014á\bÚ%ùÍ\u0006\u00046ñ\bnkæÏ*íßû\bJ÷]âì4 ±\bÐ5Cü<2@:\b\u0007±\nb\u0085\u0006Ì÷\bs\u0088llôÝ2Ý\b?Ó\u0015À\u0010\u0010\u0003´\bÿÜ·\u001fÚj/\u001b\b°\u0014ÀD×\u0016-\u0094\bV\u0081aã6Ãi\u0014\b\u0006ô\u0003·\u0011ú°±\u0010\u009a¦\u0095`9Åaµû\u0095é\u009bÑ^}\u0006\bD&ø\u0088¨¦\u007fØ\b<÷!6Æ*\r_\u0010\u0095)gý±9?â\n@OXàItØ\bO\u0019µ`Ò¹\u009e\u000b\btÀQ¾\u0085V\u009c¿\b\u0002ÑR}½8EK\bE\u001e´»Þ¤^ý\bh>\u008dQûv\u0004Å\bu±l}ÿ\u008cª_\u0010Ø©²\u0005ê%Ï[#`ä\u008d±³\u008bë\b\u000fÌä#2ã\u0001Ê\bþró´\u0001»\u0094ç\u0010ÿ½ÉÈµ\u0011\u008c}1\u0090¯8¶\u0013s[\bd\u009cRû\u0000ðÌ9\b\u001b\f\u0090) Ö§é\bêÕhõ\u008f¨å\"\u0010ð¼nEZ8î\u0095½\u0014Ô\fÓO\u008cÏ\u0010gÂ£\"\u001fÄ\u000b\u00869$\u0007#ub§Õ"
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
                     field_46 = new Class_45w[]{
                        POS1,
                        POS2,
                        CLEAR,
                        UNDO,
                        SET,
                        WALLS,
                        SHELL,
                        SPHERE,
                        HSPHERE,
                        CYLINDER,
                        HCYLINDER,
                        CLEARAREA,
                        REPLACE,
                        EXPAND,
                        COPY,
                        PASTE,
                        CONTRACT,
                        SHIFT
                     };
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

                  var5 = ",\u001cm\fÐP\u009e.\b\u009aÇ\u001cº»IFL";
                  var7 = ",\u001cm\fÐP\u009e.\b\u009aÇ\u001cº»IFL".length();
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
   public final boolean method_45() {
      return this == SET || this == WALLS || this == SHELL || this == SPHERE
         || this == HSPHERE || this == CYLINDER || this == HCYLINDER
         || this == CLEARAREA || this == REPLACE;
   }


   Class_45w(String[] var3) {
      this.field_44 = var3;
   }

   public static Class_45w method_49(String var0) {
      for (Class_45w var4 : values()) {
         for (String var8 : var4.field_44) {
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
