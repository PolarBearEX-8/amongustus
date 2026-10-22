package cc;

import java.lang.invoke.CallSite;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.invoke.MutableCallSite;
import java.lang.invoke.MethodHandles.Lookup;
import java.util.HashMap;
import java.util.Map;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import net.Class_47Pf;
import net.Class_47qc;
import net.Class_4f;

public enum Class_415 implements Class_44 {
   INFO("wdl.messages.message.info.text", Class_47Pf.RED, Class_47Pf.GOLD, "wdl.messages.message.info.description", true),
   ERROR("wdl.messages.message.error.text", Class_47Pf.DARK_GREEN, Class_47Pf.DARK_RED, "wdl.messages.message.error.description", true),
   UPDATES("wdl.messages.message.updates.text", Class_47Pf.RED, Class_47Pf.GOLD, "wdl.messages.message.updates.description", true),
   LOAD_TILE_ENTITY("wdl.messages.message.loadingTileEntity.text", Class_47Pf.DARK_GREEN, Class_47Pf.GOLD, "wdl.messages.message.loadingTileEntity.description", false),
   ON_WORLD_LOAD("wdl.messages.message.onWorldLoad.text", Class_47Pf.DARK_GREEN, Class_47Pf.GOLD, "wdl.messages.message.onWorldLoad.description", false),
   ON_BLOCK_EVENT("wdl.messages.message.blockEvent.text", Class_47Pf.DARK_GREEN, Class_47Pf.GOLD, "wdl.messages.message.blockEvent.description", true),
   ON_MAP_SAVED("wdl.messages.message.mapDataSaved.text", Class_47Pf.DARK_GREEN, Class_47Pf.GOLD, "wdl.messages.message.mapDataSaved.description", false),
   ON_CHUNK_NO_LONGER_NEEDED("wdl.messages.message.chunkUnloaded.text", Class_47Pf.DARK_GREEN, Class_47Pf.GOLD, "wdl.messages.message.chunkUnloaded.description", false),
   ON_GUI_CLOSED_INFO("wdl.messages.message.guiClosedInfo.text", Class_47Pf.DARK_GREEN, Class_47Pf.GOLD, "wdl.messages.message.guiClosedInfo.description", true),
   ON_GUI_CLOSED_WARNING("wdl.messages.message.guiClosedWarning.text", Class_47Pf.DARK_GREEN, Class_47Pf.GOLD, "wdl.messages.message.guiClosedWarning.description", true),
   SAVING("wdl.messages.message.saving.text", Class_47Pf.DARK_GREEN, Class_47Pf.GOLD, "wdl.messages.message.saving.description", true),
   REMOVE_ENTITY("wdl.messages.message.entityRemoved.text", Class_47Pf.DARK_GREEN, Class_47Pf.GOLD, "wdl.messages.message.entityRemoved.description", false),
   PLUGIN_CHANNEL_MESSAGE("wdl.messages.message.pluginChannel.text", Class_47Pf.DARK_GREEN, Class_47Pf.GOLD, "wdl.messages.message.pluginChannel.description", false),
   UPDATE_DEBUG("wdl.messages.message.updateDebug.text", Class_47Pf.DARK_GREEN, Class_47Pf.GOLD, "wdl.messages.message.updateDebug.description", false);
   private final String field_43;
   private final Class_47Pf field_40;
   private final Class_47Pf field_44;
   private final String field_49;
   private final boolean field_42;
   private static final long a = Class_4f.a(-2281064886110927557L, 4661871756925410125L, MethodHandles.lookup().lookupClass()).a(195843066807278L);
   private static final String[] b;
   private static final String[] c;
   private static final Map d = new HashMap(13);

   public String method_46() {
      return Class_47qc.method_44(this.field_49, new Object[0]);
   }

   private static Object a(Lookup var0, MutableCallSite var1, String var2, Object[] var3) {
      int var4 = (Integer)var3[0];
      long var5 = (Long)var3[1];
      String var7 = a(var4, var5);
      MethodHandle var8 = MethodHandles.constant(String.class, var7);
      var1.setTarget(MethodHandles.dropArguments(var8, 0, int.class, long.class));
      return var7;
   }

   private static String a(int var0, long var1) {
      try {
      int var5 = var0 ^ (int)(var1 & 32767L) ^ 14463;
      if (c[var5] == null) {
         Object[] var4;
         try {
            Long var3 = Thread.currentThread().getId();
            var4 = (Object[])d.get(var3);
            if (var4 == null) {
               var4 = new Object[]{Cipher.getInstance("DES/CBC/PKCS5Padding"), SecretKeyFactory.getInstance("DES"), new IvParameterSpec(new byte[8])};
               d.put(var3, var4);
            }
         } catch (Exception var11) {
            throw new RuntimeException("cc/415", var11);
         }

         byte[] var6 = new byte[8];
         var6[0] = (byte)(var1 >>> 56);
         int var7 = 1;

         try {
            while (var7 < 8) {
               var6[var7] = (byte)(var1 << var7 * 8 >>> 56);
               var7++;
            }
         } catch (Exception var10) {
            throw a(var10);
         }

         DESKeySpec var12 = new DESKeySpec(var6);
         SecretKey var8 = ((SecretKeyFactory)var4[1]).generateSecret(var12);
         ((Cipher)var4[0]).init(2, var8, (IvParameterSpec)var4[2]);
         byte[] var9 = b[var5].getBytes("ISO-8859-1");
         c[var5] = a(((Cipher)var4[0]).doFinal(var9));
      }

      return c[var5];

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
         } catch (RuntimeException var7) {
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

   private static CallSite a(Lookup var0, String var1, MethodType var2) {
      MutableCallSite var3 = new MutableCallSite(var2);

      try {
         var3.setTarget(
            MethodHandles.explicitCastArguments(
               MethodHandles.insertArguments(MethodHandles.lookup().findStatic(Class_415.class, "a", MethodType.methodType(Object.class, Lookup.class, MutableCallSite.class, String.class, Object[].class)).asCollector(Object[].class, var2.parameterCount()), 0, var0, var3, var1), var2
            )
         );
         return var3;
      } catch (Exception var5) {
         throw new RuntimeException("cc/415" + " : " + var1 + " : " + var2.toString(), var5);
      }
   }

   private static RuntimeException a(Exception exception) {
      Class_415.<RuntimeException>throwUnchecked(exception);
      throw new AssertionError();
   }

   public String method_41() {
      return Class_47qc.method_44(this.field_43, new Object[0]);
   }

   public Class_47Pf method_40() {
      return this.field_40;
   }

   public Class_47Pf method_48() {
      return this.field_44;
   }

   public boolean method_47() {
      return this.field_42;
   }
   private Class_415(String messageKey, Class_47Pf messageColor, Class_47Pf descriptionColor,
                     String descriptionKey, boolean enabled) {
      this.field_43 = messageKey;
      this.field_40 = messageColor;
      this.field_44 = descriptionColor;
      this.field_49 = descriptionKey;
      this.field_42 = enabled;
   }

   private static RuntimeException rethrowEnumInitializationException(Exception exception) {
      Class_415.<RuntimeException>throwUnchecked(exception);
      throw new AssertionError();
   }

   @SuppressWarnings("unchecked")
   private static <T extends Throwable> void throwUnchecked(Throwable throwable) throws T {
      throw (T)throwable;
   }



   static {
      try {
      long var9 = a ^ 2124227511733L;
      int var11 = (int)((var9 ^ 111176618759106L) >>> 56);
      long var12 = (var9 ^ 111176618759106L) << 8 >>> 8;
      int var14 = (int)((var9 ^ 121689313964378L) >>> 48);
      long var15 = (var9 ^ 121689313964378L) << 16 >>> 16;
      Cipher var0;
      Cipher var10000 = var0 = Cipher.getInstance("DES/CBC/PKCS5Padding");
      SecretKeyFactory var10002 = SecretKeyFactory.getInstance("DES");
      byte[] var10003 = new byte[]{(byte)(var9 >>> 56), 0, 0, 0, 0, 0, 0, 0};
      int var1 = 1;

      try {
         while (var1 < 8) {
            var10003[var1] = (byte)(var9 << var1 * 8 >>> 56);
            var1++;
         }
      } catch (RuntimeException var17) {
         throw a(var17);
      }

      var10000.init(2, var10002.generateSecret(new DESKeySpec(var10003)), new IvParameterSpec(new byte[8]));
      String[] var7 = new String[30];
      int var5 = 0;
      String var4 = "\u001d\ty\u0083N¨é97ÙÜ¹\u0005\u001f\nkO\u0094\u0084º«ÏxØ¸3½âR\u0017Ù§µ!¤c\f}{|8\u0007~\u008d\u0080éã~\u0088\u0089áÙÆ!BµÚüh\u0089\u0011âÞ©\u00104\u0002M\u009b©\u0099\u001f\u0098Sú_¾\u0081\u0001Dè\u0010\u0010\u0010\u000fé¿\u0010¼\u0002\u0087\u0095\u0089oá¸äí(\u0088Õ\u0001(ÌÄ¹ûke\u0089 ºTùE=Â¶\u000f~u½R\u0016 Ú1#w\u009cÙzT\u001d'gÌÄ\u0005\u0018r\u001b 9»J\u001d\u001e\u000fR\u0006k\u0095'¨\u0010©Þåò\u0005_\u0006f8L\u0003Ï\u0098Áöh>Óh¡Ûò´\u0080\u0094bó>\u007fÐ\u0082\u000eË\u0088ul\u0084\u0018ã~k\u009c£ÇaÜ\u0099ÆÛ~\"CÜ6Ì\u0013\u009a¢î\u0011ç)$u÷\u0010.«\u001d\u001a\náÐßV&Òi\nåh$8}\u0087±îRëx'B5nW\u0086²\u0015ÒÄ\u0089\u0011\u0002<\u0015<9\u007fD\u0089.\u001d8\u0098Pû\u0090\u009e\u0012ÀØè\u009f\rs\u0085\fÛ9,©N\u0091_¿\u008d\fbÞ01\u0089Ï·,\u0019Üêî\u0081\u0085^ØÙ\u008d^Â\u0098\u0004®&\u0017\u001eðü-\u000ei\u009c`\bÊ\u0003¹+Î\u00adã¶¼\u0001\u008dêã%LW\u0084@i\u0091YNXJhÙMØ*ï\u000b'ßõ£IR#l8h\u009e¢Û\"Y2jj\u0019ÿäeübpH©\u008d\u009f\u0018!¾³¢Ù¨$RÂPl×ªÁÙÌ+\u0094\u0004\u0094\u00910÷ýM¿T\r\u000e\u0006r\u001b²gÛ\u008c\u0088´\u000eo3\u0097`\u007f¸¹\u0085l±ÂèÜ¶\"ÜÏê0\u001f\u0090£¹áD\u0012T7\u0091\u0092¬ \u0007WÕ¹@2«\u0012\u001fÐ\u000f>¬3Eþö#\u009bS\u0004\u0010^³*Ï1ÉÅKáÑ@cÜ;\u0017]£êöu\u001e!ÛÏñ§_spIëW¶ÔtT÷ô\u0088 oûÙ´¿}ó²ö3¨\u009d\u0094%m¥óµÅóU\u0086Qf÷\u009aDîª|^b\u009fu_8F1Ô\u009cqÿC\u009d»\u001a\u0010X\u008f\u0007´\u000eÊ\u00159uÚgC\u008d\u000fÚà¤\u008c`ÿ\u008cÿt«\u009dT²GÂ¥\u0099Rq\u0093HLd7U\u0090¦Î´f\t8\u0092ë\u009dß \u001c£²\ncê[v\u000fáróùßèò2ù\u0098\"Oû´´Ýr\u0090[~.\u001bïfÜ\u0085\u008b>lzÖ2ë£~²ÉûøSe\\@¯\u0007@\u0096þhÜ«º¶~²Ð\b3¬\fcò\u008b¾\u0094\u0002ã7\b\fª8ú)Ä¼²lÙ\u0019¿ þ2\u0005:ë\u00ad*\u0087\u000e\u009dbaÜV\u0090Êø8]áã\u001e³Gþ\u0018\r`.@ü\u000eôRÕáÞ\u0086ÔêfG|Vó\u001a^\u0012\u0091p@#\u0019\u0002ö\u000füÏÓÃßAyæ(xz\u001f2ÁÇ±\t' ;À\u0017\u0092±\u009aJD\u0097C¯à²ú\u007fIçÒ\u0090%[à_²¿=åà)ù\u0085§rü['£7ù\u0083\u0018 @ñ+' \r£\u0099·ÍÇu\f\u0088EF\u0005èôõ\u008evl\u0010Õ\u0006 (V?ý\u0097[NùBh\u009c´Û(½G-¸\u0019µk¤}\u0080\u0098·§*Öøt\u0007Ö\u0096¿Eí@\u0089B\u0018\u001cõ+\u0086wO\u0015ßê¾«ÊF@K\u0082\u0080\u0093\u008fdVÙÐáÒ;\u0004ævÀ/EÓ¾1¹5\\<\u001bÍØË>>¬\u0095\u009eT3e\u001bkP\u0007¿\u0001,\u0019z\u0085:× \u0090î§Û\u001dhÎ\u0082E,ÄÕâR@UwÁ. ½Âþì=s ÿ²i^/R¶\u0003E]¹æ(Ü±¨\u0096¨Ëe9ìBÖ7úL\nqè&âZ\u0001½·½ck\b/9HÖ´Ø-S\u0091(\u0094¿(\u0091\u0015Kjöãã\u008cÎJ\"u:Òf},ì\u0082pV7\u0001\u000bYÞ-Xsùcì\f)ïUr\u001a\u0089\u0090@|\u0084öe\u001aSW@¹¿<\u0011ß\u000e û\u0004l ÆÀ\tZw×PK\\\u009a\u0083Ä,îøZuYy\u0002YÞ=\u0080\u001ebT·é\u0086µ\u008bÀüz\u008fT\u007f[ÁÔ\u0092\u0017Ê!@\u0087\u0010\u0098/Èv³Ø$×4\u001bÆ°9\u001e^+ø±A(\u0011\u0007\u0001bHp´atöB¥·þùm\u009cYU0\u0096\u000e\"wÎ\u0084\u001c\u0089Â\u0005üE\u0097¿Ô9=ï¯=\u0095\u00060æv¾o¤{áæ\u001aÃVTÉh*»\u0092\u0082\u008cBàà\u000b|\u0001Ø\u008f`ÿ4O\u0005òM\u000bæH´\u0004Éý\u0081oõCÏKç\u0010é\u0014\u008b\u000e\u0010óB4\u0016\u001d\u00909N\u0091@Ç";
      int var6 = "\u001d\ty\u0083N¨é97ÙÜ¹\u0005\u001f\nkO\u0094\u0084º«ÏxØ¸3½âR\u0017Ù§µ!¤c\f}{|8\u0007~\u008d\u0080éã~\u0088\u0089áÙÆ!BµÚüh\u0089\u0011âÞ©\u00104\u0002M\u009b©\u0099\u001f\u0098Sú_¾\u0081\u0001Dè\u0010\u0010\u0010\u000fé¿\u0010¼\u0002\u0087\u0095\u0089oá¸äí(\u0088Õ\u0001(ÌÄ¹ûke\u0089 ºTùE=Â¶\u000f~u½R\u0016 Ú1#w\u009cÙzT\u001d'gÌÄ\u0005\u0018r\u001b 9»J\u001d\u001e\u000fR\u0006k\u0095'¨\u0010©Þåò\u0005_\u0006f8L\u0003Ï\u0098Áöh>Óh¡Ûò´\u0080\u0094bó>\u007fÐ\u0082\u000eË\u0088ul\u0084\u0018ã~k\u009c£ÇaÜ\u0099ÆÛ~\"CÜ6Ì\u0013\u009a¢î\u0011ç)$u÷\u0010.«\u001d\u001a\náÐßV&Òi\nåh$8}\u0087±îRëx'B5nW\u0086²\u0015ÒÄ\u0089\u0011\u0002<\u0015<9\u007fD\u0089.\u001d8\u0098Pû\u0090\u009e\u0012ÀØè\u009f\rs\u0085\fÛ9,©N\u0091_¿\u008d\fbÞ01\u0089Ï·,\u0019Üêî\u0081\u0085^ØÙ\u008d^Â\u0098\u0004®&\u0017\u001eðü-\u000ei\u009c`\bÊ\u0003¹+Î\u00adã¶¼\u0001\u008dêã%LW\u0084@i\u0091YNXJhÙMØ*ï\u000b'ßõ£IR#l8h\u009e¢Û\"Y2jj\u0019ÿäeübpH©\u008d\u009f\u0018!¾³¢Ù¨$RÂPl×ªÁÙÌ+\u0094\u0004\u0094\u00910÷ýM¿T\r\u000e\u0006r\u001b²gÛ\u008c\u0088´\u000eo3\u0097`\u007f¸¹\u0085l±ÂèÜ¶\"ÜÏê0\u001f\u0090£¹áD\u0012T7\u0091\u0092¬ \u0007WÕ¹@2«\u0012\u001fÐ\u000f>¬3Eþö#\u009bS\u0004\u0010^³*Ï1ÉÅKáÑ@cÜ;\u0017]£êöu\u001e!ÛÏñ§_spIëW¶ÔtT÷ô\u0088 oûÙ´¿}ó²ö3¨\u009d\u0094%m¥óµÅóU\u0086Qf÷\u009aDîª|^b\u009fu_8F1Ô\u009cqÿC\u009d»\u001a\u0010X\u008f\u0007´\u000eÊ\u00159uÚgC\u008d\u000fÚà¤\u008c`ÿ\u008cÿt«\u009dT²GÂ¥\u0099Rq\u0093HLd7U\u0090¦Î´f\t8\u0092ë\u009dß \u001c£²\ncê[v\u000fáróùßèò2ù\u0098\"Oû´´Ýr\u0090[~.\u001bïfÜ\u0085\u008b>lzÖ2ë£~²ÉûøSe\\@¯\u0007@\u0096þhÜ«º¶~²Ð\b3¬\fcò\u008b¾\u0094\u0002ã7\b\fª8ú)Ä¼²lÙ\u0019¿ þ2\u0005:ë\u00ad*\u0087\u000e\u009dbaÜV\u0090Êø8]áã\u001e³Gþ\u0018\r`.@ü\u000eôRÕáÞ\u0086ÔêfG|Vó\u001a^\u0012\u0091p@#\u0019\u0002ö\u000füÏÓÃßAyæ(xz\u001f2ÁÇ±\t' ;À\u0017\u0092±\u009aJD\u0097C¯à²ú\u007fIçÒ\u0090%[à_²¿=åà)ù\u0085§rü['£7ù\u0083\u0018 @ñ+' \r£\u0099·ÍÇu\f\u0088EF\u0005èôõ\u008evl\u0010Õ\u0006 (V?ý\u0097[NùBh\u009c´Û(½G-¸\u0019µk¤}\u0080\u0098·§*Öøt\u0007Ö\u0096¿Eí@\u0089B\u0018\u001cõ+\u0086wO\u0015ßê¾«ÊF@K\u0082\u0080\u0093\u008fdVÙÐáÒ;\u0004ævÀ/EÓ¾1¹5\\<\u001bÍØË>>¬\u0095\u009eT3e\u001bkP\u0007¿\u0001,\u0019z\u0085:× \u0090î§Û\u001dhÎ\u0082E,ÄÕâR@UwÁ. ½Âþì=s ÿ²i^/R¶\u0003E]¹æ(Ü±¨\u0096¨Ëe9ìBÖ7úL\nqè&âZ\u0001½·½ck\b/9HÖ´Ø-S\u0091(\u0094¿(\u0091\u0015Kjöãã\u008cÎJ\"u:Òf},ì\u0082pV7\u0001\u000bYÞ-Xsùcì\f)ïUr\u001a\u0089\u0090@|\u0084öe\u001aSW@¹¿<\u0011ß\u000e û\u0004l ÆÀ\tZw×PK\\\u009a\u0083Ä,îøZuYy\u0002YÞ=\u0080\u001ebT·é\u0086µ\u008bÀüz\u008fT\u007f[ÁÔ\u0092\u0017Ê!@\u0087\u0010\u0098/Èv³Ø$×4\u001bÆ°9\u001e^+ø±A(\u0011\u0007\u0001bHp´atöB¥·þùm\u009cYU0\u0096\u000e\"wÎ\u0084\u001c\u0089Â\u0005üE\u0097¿Ô9=ï¯=\u0095\u00060æv¾o¤{áæ\u001aÃVTÉh*»\u0092\u0082\u008cBàà\u000b|\u0001Ø\u008f`ÿ4O\u0005òM\u000bæH´\u0004Éý\u0081oõCÏKç\u0010é\u0014\u008b\u000e\u0010óB4\u0016\u001d\u00909N\u0091@Ç"
         .length();
      char var3 = '@';
      int var19 = -1;

      label31:
      while (true) {
         String var20 = var4.substring(++var19, var19 + var3);
         byte var10001 = -1;

         while (true) {
            byte[] var8 = var0.doFinal(var20.getBytes("ISO-8859-1"));
            String var26 = a(var8).intern();
            switch (var10001) {
               case 0:
                  var7[var5++] = var26;
                  if ((var19 += var3) >= var6) {
                     b = var7;
                     c = new String[30];
                     break label31;
                  }

                  var3 = var4.charAt(var19);
                  break;
               default:
                  var7[var5++] = var26;
                  if ((var19 += var3) < var6) {
                     var3 = var4.charAt(var19);
                     continue label31;
                  }

                  var4 = "êdd\bÒ\u001dõÿé$aÛîÝ\u0084y\u0086¢\u0001p×FÈ¾ \u0096eqçò\u0000\u0095¦<\u009f¯q´d\u0017\u0016Ï\f\u0090;%LD#ÿnïN+¯\u008f_";
                  var6 = "êdd\bÒ\u001dõÿé$aÛîÝ\u0084y\u0086¢\u0001p×FÈ¾ \u0096eqçò\u0000\u0095¦<\u009f¯q´d\u0017\u0016Ï\f\u0090;%LD#ÿnïN+¯\u008f_".length();
                  var3 = 24;
                  var19 = -1;
            }

            var20 = var4.substring(++var19, var19 + var3);
            var10001 = 0;
         }
      }

      } catch (Exception exception) {
         throw rethrowEnumInitializationException(exception);
      }
   }
}
