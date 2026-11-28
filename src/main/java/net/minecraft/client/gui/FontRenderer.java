package net.minecraft.client.gui;

import com.ibm.icu.text.ArabicShaping;
import com.ibm.icu.text.ArabicShapingException;
import com.ibm.icu.text.Bidi;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.Random;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.regex.Pattern;
import net.augustus.font.IFontRenderer;
import net.augustus.ClientManagers;
import net.augustus.Augustus;
import net.augustus.utils.interfaces.MC;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.renderer.texture.TextureUtil;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.client.resources.IResourceManagerReloadListener;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.util.ResourceLocation;
import net.optifine.CustomColors;
import net.optifine.config.Config;
import net.optifine.render.GlBlendState;
import net.optifine.util.FontUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.lwjgl.opengl.GL11;

public class FontRenderer implements MC, IFontRenderer, IResourceManagerReloadListener {
   private static final int[] COLOR_CODE_LOOKUP = new int[128];
   private static final int[] ASCII_CHAR_LOOKUP;
   private static final String ASCII_CHARS = "ÀÁÂÈÊËÍÓÔÕÚßãõğİıŒœŞşŴŵžȇ\u0000\u0000\u0000\u0000\u0000\u0000\u0000 !\"#$%&'()*+,-./0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[\\]^_`abcdefghijklmnopqrstuvwxyz{|}~\u0000ÇüéâäàåçêëèïîìÄÅÉæÆôöòûùÿÖÜø£Ø×ƒáíóúñÑªº¿®¬½¼¡«»░▒▓│┤╡╢╖╕╣║╗╝╜╛┐└┴┬├─┼╞╟╚╔╩╦╠═╬╧╨╤╥╙╘╒╓╫╪┘┌█▄▌▐▀αβΓπΣσμτΦΘΩδ∞∅∈∩≡±≥≤⌠⌡÷≈°∙·√ⁿ²■\u0000";
   private static final ResourceLocation[] unicodePageLocations;
   private final TextureManager renderEngine;
   public int FONT_HEIGHT = 9;
   public Random fontRandom = new Random();
   private ResourceLocation locationFontTextureBase;
   private float offsetBold = 1.0F;
   private byte[] glyphWidth = new byte[65536];
   private int[] colorCode = new int[32];
   private float posX;
   private float posY;
   private boolean randomStyle;
   private boolean boldStyle;
   private boolean italicStyle;
   private boolean underlineStyle;
   private boolean strikethroughStyle;
   private final int[] charWidthInt = new int[256];
   private ResourceLocation locationFontTexture;
   private boolean unicodeFlag;
   private boolean bidiFlag;
   private float red;
   private float blue;
   private float green;
   private float alpha;
   private int textColor;
   private float[] charWidth = new float[256];
   private boolean blend = false;
   private GlBlendState oldBlendState = new GlBlendState();
   private Pattern serverIpPattern;
   private Pattern customDomainPattern;
   private Pattern altDomainPattern;
   private Pattern playerNamePattern;
   private Pattern altPlayerNamePattern;
   private String lastServerIp;
   private String lastCustomDomain;
   private String lastAltDomain;
   private String lastPlayerName;
   private String lastAltPlayerName;
   private static char[] REGEX_SPECIAL_CHARS;

   private static String replaceWithCachedPattern(String str, String str2, String str3, Supplier supplier, Supplier supplier2, Consumer<Pattern> consumer, boolean flag) {
      Pattern pattern = (Pattern)supplier.get();
      if (pattern == null || !((String)supplier2.get()).equals(str2)) {
         byte b = 82;
         if (flag) {
            String str4 = "(?:§(?:[0-9]|a|b|c|d|e|f|k|l|m|n|o|r|)|-)*";
            StringBuilder sb = new StringBuilder(str2.length() * (str4.length() + 1));

            for (int i = 0; i < str2.length(); i++) {
               char ch = str2.charAt(i);

               for (char ch2 : REGEX_SPECIAL_CHARS) {
                  if (ch2 == ch) {
                     sb.append("\\");
                     break;
                  }
               }

               sb.append(str2.charAt(i));
               if (ch == '-') {
                  sb.append('?');
               }

               sb.append(str4);
            }

            str2 = sb.substring(0, sb.length() - str4.length()).toString();
            b = 66;
         }

         consumer.accept(pattern = Pattern.compile(str2, b));
      }

      return pattern.matcher(str).replaceAll(str3);
   }

   private int getFormatCodeIndex(String str, int n) {
      char ch = Character.toLowerCase(str.charAt(n));
      return ch < COLOR_CODE_LOOKUP.length ? COLOR_CODE_LOOKUP[ch] : -1;
   }

   public String trimStringToWidth(String str, int n, boolean flag) {
      StringBuilder sb = new StringBuilder();
      float f = 0.0F;
      int n2 = flag ? str.length() - 1 : 0;
      int n3 = flag ? -1 : 1;
      boolean flag2 = false;
      boolean flag3 = false;

      for (int i = n2; i >= 0 && i < str.length() && f < (float)n; i += n3) {
         char ch = str.charAt(i);
         float charWidthFloat = this.getCharWidthFloat(ch);
         if (flag2) {
            flag2 = false;
            if (ch == 'l' || ch == 'L') {
               flag3 = true;
            } else if (ch == 'r' || ch == 'R') {
               flag3 = false;
            }
         } else if (charWidthFloat < 0.0F) {
            flag2 = true;
         } else {
            f += charWidthFloat;
            if (flag3) {
               f++;
            }
         }

         if (f > (float)n) {
            break;
         }

         if (flag) {
            sb.insert(0, ch);
         } else {
            sb.append(ch);
         }
      }

      return sb.toString();
   }

   private float getCharWidthFloat(char ch) {
      if (ch == 167) {
         return -1.0F;
      } else if (ch != ' ' && ch != 160) {
         int n = ch < ASCII_CHAR_LOOKUP.length ? ASCII_CHAR_LOOKUP[ch] : -1;
         if (ch > 0 && n != -1 && !this.unicodeFlag) {
            return this.charWidth[n];
         } else if (this.glyphWidth[ch] != 0) {
            int n2 = this.glyphWidth[ch] >>> 4 & 15;
            int n3 = this.glyphWidth[ch] & 15;
            n3++;
            return (float)(n3 - n2) / 2.0F + 1.0F;
         } else {
            return 0.0F;
         }
      } else {
         return this.unicodeFlag ? 4.0F : this.charWidth[32];
      }
   }

   private InputStream getResourceInputStream(ResourceLocation location) throws IOException {
      return Minecraft.getMinecraft().getResourceManager().getResource(location).getInputStream();
   }

   private void resetStyles() {
      this.randomStyle = false;
      this.boldStyle = false;
      this.italicStyle = false;
      this.underlineStyle = false;
      this.strikethroughStyle = false;
   }

   public int drawStringWithShadowOffset(String str, float f, float f2, int n, boolean flag, float f3) {
      return this.drawStringInternal(str, f, f2, n, flag, true, true, false, f3);
   }

   private void setColor(float f, float f2, float f3, float f4) {
      GlStateManager.color(f, f2, f3, f4);
   }

   @Override
   public float getFontHeight() {
      return (float)this.FONT_HEIGHT;
   }

   public boolean getBidiFlag() {
      return this.bidiFlag;
   }

   public String filterText(String str, boolean flag) {
      if (mc.thePlayer == null) {
         return str;
      } else {
         if (!flag) {
         }

         if (ClientManagers.moduleManager.protector.isToggled()) {
            for (String str3 : (Iterable<String>)(Augustus.getInstance().getProtectorManager().trackedNames)) {
               if (str.contains(str3)) {
                  String str4 = (String)Augustus.getInstance().getProtectorManager().nameMappings.get(str3);
                  if (str4 != null) {
                     str = StringUtils.replace(str, str3, str4);
                  }
               }
            }
         }

         if (ClientManagers.moduleManager.antiStrike.isToggled() && ClientManagers.moduleManager.antiStrike.replaceServerName.getValue()) {
            boolean value = ClientManagers.moduleManager.antiStrike.useAdvancedFilter.getValue();
            if (mc.getCurrentServerData() != null && mc.getCurrentServerData().serverIP != null && !mc.getCurrentServerData().serverIP.isEmpty()) {
               String serverIP = mc.getCurrentServerData().serverIP;
               str = replaceWithCachedPattern(str, serverIP, "Augustus.top", () -> this.serverIpPattern, () -> this.lastServerIp, pattern -> this.serverIpPattern = pattern, value);
               this.lastServerIp = serverIP;
            }

            if (!ClientManagers.moduleManager.antiStrike.getScoreboardTitleServerIp().isEmpty()) {
               String str5 = ClientManagers.moduleManager.antiStrike.getScoreboardTitleServerIp();
               str = replaceWithCachedPattern(str, str5, "Augustus.top", () -> this.customDomainPattern, () -> this.lastCustomDomain, pattern2 -> this.customDomainPattern = pattern2, value);
               this.lastCustomDomain = str5;
            }

            if (!ClientManagers.moduleManager.antiStrike.getScoreboardLineServerIp().isEmpty()) {
               String scoreboardLineServerIp = ClientManagers.moduleManager.antiStrike.getScoreboardLineServerIp();
               str = replaceWithCachedPattern(str, ClientManagers.moduleManager.antiStrike.getScoreboardLineServerIp(), "Augustus.top", () -> this.altDomainPattern, () -> this.lastAltDomain, pattern3 -> this.altDomainPattern = pattern3, value);
               this.lastAltDomain = scoreboardLineServerIp;
            }

            if (!ClientManagers.moduleManager.antiStrike.getScoreboardTitleServerName().isEmpty()) {
               String str6 = ClientManagers.moduleManager.antiStrike.getScoreboardTitleServerName();
               str = replaceWithCachedPattern(str, ClientManagers.moduleManager.antiStrike.getScoreboardTitleServerName(), "Augustus", () -> this.playerNamePattern, () -> this.lastPlayerName, pattern4 -> this.playerNamePattern = pattern4, value);
               this.lastPlayerName = str6;
            }

            if (!ClientManagers.moduleManager.antiStrike.getScoreboardLineServerName().isEmpty()) {
               String str7 = ClientManagers.moduleManager.antiStrike.getScoreboardLineServerName();
               str = replaceWithCachedPattern(str, ClientManagers.moduleManager.antiStrike.getScoreboardLineServerName(), "Augustus", () -> this.altPlayerNamePattern, () -> this.lastAltPlayerName, pattern5 -> this.altPlayerNamePattern = pattern5, value);
               this.lastAltPlayerName = str7;
            }
         }

         return str;
      }
   }

   public String applyTextFilters(String str) {
      return this.filterText(str, false);
   }

   @Override
   public int getStringWidth(String str) {
      return str == null ? 0 : (int)Math.round(this.getStringWidthFloat(this.applyTextFilters(str)));
   }

   public void setUnicodeFlag(boolean flag) {
      this.unicodeFlag = flag;
   }

   private void renderStringAtPos(String str, WorldRenderer worldRenderer, float f, float f2, float f3, float f4, boolean flag) {
      boolean flag2 = ClientManagers.moduleManager.antiStrike.isToggled() && ClientManagers.moduleManager.antiStrike.removeColorCodes.getValue() && Minecraft.getMinecraft().thePlayer != null && !flag;

      for (int i = 0; i < str.length(); i++) {
         char ch = str.charAt(i);
         if (ch == 167 && i + 1 < str.length()) {
            int formatCodeIndex = this.getFormatCodeIndex(str, i + 1);
            if (formatCodeIndex < 16) {
               this.randomStyle = false;
               this.boldStyle = false;
               this.strikethroughStyle = false;
               this.underlineStyle = false;
               this.italicStyle = false;
               if (formatCodeIndex < 0 || formatCodeIndex > 15) {
                  formatCodeIndex = 15;
               }

               int n = this.colorCode[formatCodeIndex];
               if (Config.isCustomColors()) {
                  n = CustomColors.getTextColor(formatCodeIndex, n);
               }

               this.textColor = n;
               if (!flag2) {
                  this.red = (float)(n >> 16) / 255.0F;
                  this.green = (float)(n >> 8 & 0xFF) / 255.0F;
                  this.blue = (float)(n & 0xFF) / 255.0F;
               }
            } else if (formatCodeIndex == 16) {
               this.randomStyle = !flag2;
            } else if (formatCodeIndex == 17) {
               this.boldStyle = !flag2;
            } else if (formatCodeIndex == 18) {
               this.strikethroughStyle = !flag2;
            } else if (formatCodeIndex == 19) {
               this.underlineStyle = !flag2;
            } else if (formatCodeIndex == 20) {
               this.italicStyle = !flag2;
            } else if (formatCodeIndex == 21) {
               this.randomStyle = false;
               this.boldStyle = false;
               this.strikethroughStyle = false;
               this.underlineStyle = false;
               this.italicStyle = false;
               this.red = f;
               this.blue = f3;
               this.green = f2;
               this.alpha = f4;
            }

            i++;
         } else {
            int n2 = ch < ASCII_CHAR_LOOKUP.length ? ASCII_CHAR_LOOKUP[ch] : -1;
            if (this.randomStyle && n2 != -1) {
               int charWidth2 = this.getCharWidth(ch);

               char ch2;
               do {
                  n2 = this.fontRandom
                     .nextInt(
                        "ÀÁÂÈÊËÍÓÔÕÚßãõğİıŒœŞşŴŵžȇ\u0000\u0000\u0000\u0000\u0000\u0000\u0000 !\"#$%&'()*+,-./0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[\\]^_`abcdefghijklmnopqrstuvwxyz{|}~\u0000ÇüéâäàåçêëèïîìÄÅÉæÆôöòûùÿÖÜø£Ø×ƒáíóúñÑªº¿®¬½¼¡«»░▒▓│┤╡╢╖╕╣║╗╝╜╛┐└┴┬├─┼╞╟╚╔╩╦╠═╬╧╨╤╥╙╘╒╓╫╪┘┌█▄▌▐▀αβΓπΣσμτΦΘΩδ∞∅∈∩≡±≥≤⌠⌡÷≈°∙·√ⁿ²■\u0000"
                           .length()
                     );
                  ch2 = "ÀÁÂÈÊËÍÓÔÕÚßãõğİıŒœŞşŴŵžȇ\u0000\u0000\u0000\u0000\u0000\u0000\u0000 !\"#$%&'()*+,-./0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[\\]^_`abcdefghijklmnopqrstuvwxyz{|}~\u0000ÇüéâäàåçêëèïîìÄÅÉæÆôöòûùÿÖÜø£Ø×ƒáíóúñÑªº¿®¬½¼¡«»░▒▓│┤╡╢╖╕╣║╗╝╜╛┐└┴┬├─┼╞╟╚╔╩╦╠═╬╧╨╤╥╙╘╒╓╫╪┘┌█▄▌▐▀αβΓπΣσμτΦΘΩδ∞∅∈∩≡±≥≤⌠⌡÷≈°∙·√ⁿ²■\u0000"
                     .charAt(n2);
               } while (charWidth2 != this.getCharWidth(ch2));

               ch = ch2;
            }

            float f5 = n2 != -1 && !this.unicodeFlag ? this.offsetBold : 0.5F;
            float f6 = this.renderChar(ch, this.italicStyle, worldRenderer, n2);
            if (this.boldStyle) {
               this.posX += f5;
               this.renderChar(ch, this.italicStyle, worldRenderer, n2);
               this.posX -= f5;
               f6 += f5;
            }

            if (this.strikethroughStyle) {
               float posX2 = this.posX;
               float f7 = this.posY + (float)(this.FONT_HEIGHT / 2);
               float f8 = this.posX + f6;
               float f9 = this.posY + (float)(this.FONT_HEIGHT / 2) - 1.0F;
               GlStateManager.disableTexture2D();
               this.setColor(this.red, this.green, this.blue, this.alpha);
               GL11.glBegin(7);
               GL11.glVertex2d((double)posX2, (double)f7);
               GL11.glVertex2d((double)f8, (double)f7);
               GL11.glVertex2d((double)f8, (double)f9);
               GL11.glVertex2d((double)posX2, (double)f9);
               GL11.glEnd();
               GlStateManager.enableTexture2D();
            }

            if (this.underlineStyle) {
               float f10 = this.posX - 1.0F;
               float f11 = this.posY + (float)this.FONT_HEIGHT;
               float f12 = this.posX + f6;
               float f13 = this.posY + (float)this.FONT_HEIGHT - 1.0F;
               GlStateManager.disableTexture2D();
               this.setColor(this.red, this.green, this.blue, this.alpha);
               GL11.glBegin(7);
               GL11.glVertex2d((double)f10, (double)f11);
               GL11.glVertex2d((double)f12, (double)f11);
               GL11.glVertex2d((double)f12, (double)f13);
               GL11.glVertex2d((double)f10, (double)f13);
               GL11.glEnd();
               GlStateManager.enableTexture2D();
            }

            this.posX += f6;
         }
      }
   }

   private void readFontTexture() {
      BufferedImage image;
      try {
         image = TextureUtil.readBufferedImage(this.getResourceInputStream(this.locationFontTexture));
      } catch (IOException e) {
         throw new RuntimeException(e);
      }

      Properties properties = FontUtils.readFontProperties(this.locationFontTexture);
      this.blend = FontUtils.readBoolean(properties, "blend", false);
      int width = image.getWidth();
      int height = image.getHeight();
      int n = width / 16;
      int n2 = height / 16;
      float f = (float)width / 128.0F;
      float f2 = Config.limit3(f, 1.0F, 2.0F);
      this.offsetBold = 1.0F / f2;
      float f3 = FontUtils.readFloat(properties, "offsetBold", -1.0F);
      if (f3 >= 0.0F) {
         this.offsetBold = f3;
      }

      int[] ints = new int[width * height];
      image.getRGB(0, 0, width, height, ints, 0, width);

      for (int i = 0; i < 256; i++) {
         int n3 = i % 16;
         int n4 = i / 16;
         int n5 = 0;

         for (n5 = n - 1; n5 >= 0; n5--) {
            int n6 = n3 * n + n5;
            boolean flag = true;

            for (int j = 0; j < n2 && flag; j++) {
               if ((ints[n6 + ((n4 * n2 + j) * width)] >> 24 & 0xFF) > 16) {
                  flag = false;
               }
            }

            if (!flag) {
               break;
            }
         }

         if (i == 65) {
            i = i;
         }

         if (i == 32) {
            if (n <= 8) {
               n5 = (int)(2.0F * f);
            } else {
               n5 = (int)(1.5F * f);
            }
         }

         this.charWidth[i] = (float)(n5 + 1) / f + 1.0F;
      }

      FontUtils.readCustomCharWidths(properties, this.charWidth);

      for (int k = 0; k < this.charWidthInt.length; k++) {
         this.charWidthInt[k] = Math.round(this.charWidth[k]);
      }
   }

   private int sizeStringToWidth(String str, int n) {
      int length = str.length();
      float f = 0.0F;
      int n2 = 0;
      int n3 = -1;

      for (boolean i = false; n2 < length; n2++) {
         char ch = str.charAt(n2);
         switch (ch) {
            case '\n':
               n2--;
               break;
            case ' ':
               n3 = n2;
            default:
               f += (float)this.getCharWidth(ch);
               if (i) {
                  f++;
               }
               break;
            case '§':
               if (n2 < length - 1) {
                  char ch2 = str.charAt(++n2);
                  if (ch2 == 'l' || ch2 == 'L') {
                     i = true;
                  } else if (ch2 == 'r' || ch2 == 'R' || isFormatColor(ch2)) {
                     i = false;
                  }
               }
         }

         if (ch == '\n') {
            n3 = ++n2;
            break;
         }

         if (Math.round(f) > n) {
            break;
         }
      }

      return n2 != length && n3 != -1 && n3 < n2 ? n3 : n2;
   }

   public int renderString(String str, float f, float f2, int n, boolean flag) {
      return this.drawStringInternal(str, f, f2, n, true, true, true, flag, 1.0F);
   }

   private void enableAlpha() {
      GlStateManager.enableAlpha();
   }

   private void renderStringShadowAtPos(String str, WorldRenderer worldRenderer, float f, float f2, float f3, float f4, boolean flag) {
      boolean flag2 = ClientManagers.moduleManager.antiStrike.isToggled() && ClientManagers.moduleManager.antiStrike.removeColorCodes.getValue() && Minecraft.getMinecraft().thePlayer != null && !flag;

      for (int i = 0; i < str.length(); i++) {
         char ch = str.charAt(i);
         if (ch == 167 && i + 1 < str.length()) {
            int formatCodeIndex = this.getFormatCodeIndex(str, i + 1);
            if (formatCodeIndex < 16) {
               this.randomStyle = false;
               this.boldStyle = false;
               this.strikethroughStyle = false;
               this.underlineStyle = false;
               this.italicStyle = false;
               if (formatCodeIndex < 0 || formatCodeIndex > 15) {
                  formatCodeIndex = 15;
               }

               formatCodeIndex += 16;
               int n = this.colorCode[formatCodeIndex];
               if (Config.isCustomColors()) {
                  n = CustomColors.getTextColor(formatCodeIndex, n);
               }

               this.textColor = n;
               if (!flag2) {
                  this.red = (float)(n >> 16) / 255.0F;
                  this.green = (float)(n >> 8 & 0xFF) / 255.0F;
                  this.blue = (float)(n & 0xFF) / 255.0F;
               }
            } else if (formatCodeIndex == 16) {
               this.randomStyle = !flag2;
            } else if (formatCodeIndex == 17) {
               this.boldStyle = !flag2;
            } else if (formatCodeIndex == 18) {
               this.strikethroughStyle = !flag2;
            } else if (formatCodeIndex == 19) {
               this.underlineStyle = !flag2;
            } else if (formatCodeIndex == 20) {
               this.italicStyle = !flag2;
            } else if (formatCodeIndex == 21) {
               this.randomStyle = false;
               this.boldStyle = false;
               this.strikethroughStyle = false;
               this.underlineStyle = false;
               this.italicStyle = false;
               this.red = f;
               this.green = f2;
               this.blue = f3;
               this.alpha = f4;
            }

            i++;
         } else {
            int n2 = ch < ASCII_CHAR_LOOKUP.length ? ASCII_CHAR_LOOKUP[ch] : -1;
            if (this.randomStyle && n2 != -1) {
               int charWidth2 = this.getCharWidth(ch);

               char ch2;
               do {
                  n2 = this.fontRandom
                     .nextInt(
                        "ÀÁÂÈÊËÍÓÔÕÚßãõğİıŒœŞşŴŵžȇ\u0000\u0000\u0000\u0000\u0000\u0000\u0000 !\"#$%&'()*+,-./0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[\\]^_`abcdefghijklmnopqrstuvwxyz{|}~\u0000ÇüéâäàåçêëèïîìÄÅÉæÆôöòûùÿÖÜø£Ø×ƒáíóúñÑªº¿®¬½¼¡«»░▒▓│┤╡╢╖╕╣║╗╝╜╛┐└┴┬├─┼╞╟╚╔╩╦╠═╬╧╨╤╥╙╘╒╓╫╪┘┌█▄▌▐▀αβΓπΣσμτΦΘΩδ∞∅∈∩≡±≥≤⌠⌡÷≈°∙·√ⁿ²■\u0000"
                           .length()
                     );
                  ch2 = "ÀÁÂÈÊËÍÓÔÕÚßãõğİıŒœŞşŴŵžȇ\u0000\u0000\u0000\u0000\u0000\u0000\u0000 !\"#$%&'()*+,-./0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[\\]^_`abcdefghijklmnopqrstuvwxyz{|}~\u0000ÇüéâäàåçêëèïîìÄÅÉæÆôöòûùÿÖÜø£Ø×ƒáíóúñÑªº¿®¬½¼¡«»░▒▓│┤╡╢╖╕╣║╗╝╜╛┐└┴┬├─┼╞╟╚╔╩╦╠═╬╧╨╤╥╙╘╒╓╫╪┘┌█▄▌▐▀αβΓπΣσμτΦΘΩδ∞∅∈∩≡±≥≤⌠⌡÷≈°∙·√ⁿ²■\u0000"
                     .charAt(n2);
               } while (charWidth2 != this.getCharWidth(ch2));

               ch = ch2;
            }

            float f5 = n2 != -1 && !this.unicodeFlag ? this.offsetBold : 0.5F;
            boolean flag3 = ch == 0 || n2 == -1 || this.unicodeFlag;
            if (flag3) {
               this.posX -= f5;
               this.posY -= f5;
            }

            float f6 = this.renderChar(ch, this.italicStyle, worldRenderer, n2);
            if (flag3) {
               this.posX += f5;
               this.posY += f5;
            }

            if (this.boldStyle) {
               this.posX += f5;
               if (flag3) {
                  this.posX -= f5;
                  this.posY -= f5;
               }

               this.renderChar(ch, this.italicStyle, worldRenderer, n2);
               this.posX -= f5;
               if (flag3) {
                  this.posX += f5;
                  this.posY += f5;
               }

               f6 += f5;
            }

            if (this.strikethroughStyle) {
               float posX2 = this.posX;
               float f7 = this.posY + (float)(this.FONT_HEIGHT / 2);
               float f8 = this.posX + f6;
               float f9 = this.posY + (float)(this.FONT_HEIGHT / 2) - 1.0F;
               GlStateManager.disableTexture2D();
               this.setColor(this.red, this.green, this.blue, this.alpha);
               GL11.glBegin(7);
               GL11.glVertex2d((double)posX2, (double)f7);
               GL11.glVertex2d((double)f8, (double)f7);
               GL11.glVertex2d((double)f8, (double)f9);
               GL11.glVertex2d((double)posX2, (double)f9);
               GL11.glEnd();
               GlStateManager.enableTexture2D();
            }

            if (this.underlineStyle) {
               float f10 = this.posX - 1.0F;
               float f11 = this.posY + (float)this.FONT_HEIGHT;
               float f12 = this.posX + f6;
               float f13 = this.posY + (float)this.FONT_HEIGHT - 1.0F;
               GlStateManager.disableTexture2D();
               this.setColor(this.red, this.green, this.blue, this.alpha);
               GL11.glBegin(7);
               GL11.glVertex2d((double)f10, (double)f11);
               GL11.glVertex2d((double)f12, (double)f11);
               GL11.glVertex2d((double)f12, (double)f13);
               GL11.glVertex2d((double)f10, (double)f13);
               GL11.glEnd();
               GlStateManager.enableTexture2D();
            }

            this.posX += f6;
         }
      }
   }

   public int drawString(String str, int n, int n2, int n3) {
      return this.drawStringInternal(str, (float)n, (float)n2, n3, false, true, true, false, 1.0F);
   }

   private void readGlyphSizes() {
      InputStream in = null;

      try {
         in = this.getResourceInputStream(new ResourceLocation("font/glyph_sizes.bin"));
         in.read(this.glyphWidth);
      } catch (IOException e) {
         throw new RuntimeException(e);
      } finally {
         IOUtils.closeQuietly(in);
      }
   }

   public int getColorCode(char ch) {
      int n = "0123456789abcdef".indexOf(ch);
      if (n >= 0 && n < this.colorCode.length) {
         int n2 = this.colorCode[n];
         if (Config.isCustomColors()) {
            n2 = CustomColors.getTextColor(n, n2);
         }

         return n2;
      } else {
         return 16777215;
      }
   }

   public static String getFormatFromString(String str) {
      StringBuilder sb = new StringBuilder();
      int n = -1;
      int length = str.length();

      while ((n = str.indexOf(167, n + 1)) != -1) {
         if (n < length - 1) {
            char ch = str.charAt(n + 1);
            if (isFormatColor(ch)) {
               sb = new StringBuilder("§" + ch);
            } else if (isFormatSpecial(ch)) {
               sb.append("§").append(ch);
            }
         }
      }

      return sb.toString();
   }

   static {
      Arrays.fill(COLOR_CODE_LOOKUP, -1);
      String str = "0123456789abcdefklmnor";
      int n = 0;

      while (n < str.length()) {
         char ch = str.charAt(n);
         COLOR_CODE_LOOKUP[ch] = n;
         COLOR_CODE_LOOKUP[Character.toLowerCase(ch)] = n++;
      }

      char ch2 = 0;

      for (int i = 0;
         i
            < "ÀÁÂÈÊËÍÓÔÕÚßãõğİıŒœŞşŴŵžȇ\u0000\u0000\u0000\u0000\u0000\u0000\u0000 !\"#$%&'()*+,-./0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[\\]^_`abcdefghijklmnopqrstuvwxyz{|}~\u0000ÇüéâäàåçêëèïîìÄÅÉæÆôöòûùÿÖÜø£Ø×ƒáíóúñÑªº¿®¬½¼¡«»░▒▓│┤╡╢╖╕╣║╗╝╜╛┐└┴┬├─┼╞╟╚╔╩╦╠═╬╧╨╤╥╙╘╒╓╫╪┘┌█▄▌▐▀αβΓπΣσμτΦΘΩδ∞∅∈∩≡±≥≤⌠⌡÷≈°∙·√ⁿ²■\u0000"
               .length();
         i++
      ) {
         char ch3 = "ÀÁÂÈÊËÍÓÔÕÚßãõğİıŒœŞşŴŵžȇ\u0000\u0000\u0000\u0000\u0000\u0000\u0000 !\"#$%&'()*+,-./0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[\\]^_`abcdefghijklmnopqrstuvwxyz{|}~\u0000ÇüéâäàåçêëèïîìÄÅÉæÆôöòûùÿÖÜø£Ø×ƒáíóúñÑªº¿®¬½¼¡«»░▒▓│┤╡╢╖╕╣║╗╝╜╛┐└┴┬├─┼╞╟╚╔╩╦╠═╬╧╨╤╥╙╘╒╓╫╪┘┌█▄▌▐▀αβΓπΣσμτΦΘΩδ∞∅∈∩≡±≥≤⌠⌡÷≈°∙·√ⁿ²■\u0000"
            .charAt(i);
         if (ch3 > ch2) {
            ch2 = ch3;
         }
      }

      ASCII_CHAR_LOOKUP = new int[ch2 + 1];
      Arrays.fill(ASCII_CHAR_LOOKUP, -1);

      for (int j = 0;
         j
            < "ÀÁÂÈÊËÍÓÔÕÚßãõğİıŒœŞşŴŵžȇ\u0000\u0000\u0000\u0000\u0000\u0000\u0000 !\"#$%&'()*+,-./0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[\\]^_`abcdefghijklmnopqrstuvwxyz{|}~\u0000ÇüéâäàåçêëèïîìÄÅÉæÆôöòûùÿÖÜø£Ø×ƒáíóúñÑªº¿®¬½¼¡«»░▒▓│┤╡╢╖╕╣║╗╝╜╛┐└┴┬├─┼╞╟╚╔╩╦╠═╬╧╨╤╥╙╘╒╓╫╪┘┌█▄▌▐▀αβΓπΣσμτΦΘΩδ∞∅∈∩≡±≥≤⌠⌡÷≈°∙·√ⁿ²■\u0000"
               .length();
         j++
      ) {
         char ch4 = "ÀÁÂÈÊËÍÓÔÕÚßãõğİıŒœŞşŴŵžȇ\u0000\u0000\u0000\u0000\u0000\u0000\u0000 !\"#$%&'()*+,-./0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[\\]^_`abcdefghijklmnopqrstuvwxyz{|}~\u0000ÇüéâäàåçêëèïîìÄÅÉæÆôöòûùÿÖÜø£Ø×ƒáíóúñÑªº¿®¬½¼¡«»░▒▓│┤╡╢╖╕╣║╗╝╜╛┐└┴┬├─┼╞╟╚╔╩╦╠═╬╧╨╤╥╙╘╒╓╫╪┘┌█▄▌▐▀αβΓπΣσμτΦΘΩδ∞∅∈∩≡±≥≤⌠⌡÷≈°∙·√ⁿ²■\u0000"
            .charAt(j);
         if (ASCII_CHAR_LOOKUP[ch4] == -1) {
            ASCII_CHAR_LOOKUP[ch4] = j;
         }
      }

      unicodePageLocations = new ResourceLocation[256];
      REGEX_SPECIAL_CHARS = new char[]{'\\', '^', '$', '.', '|', '?', '*', '+', '(', ')', '[', ']', '{', '}'};
   }

   private static boolean isFormatColor(char ch) {
      return ch >= '0' && ch <= '9' || ch >= 'a' && ch <= 'f' || ch >= 'A' && ch <= 'F';
   }

   public int drawStringInt(String str, int n, int n2, int n3, boolean flag) {
      return this.drawStringInternal(str, (float)n, (float)n2, n3, flag, true, true, false, 1.0F);
   }

   @Override
   public void onResourceManagerReload(IResourceManager resourceManager) {
      this.locationFontTexture = FontUtils.getHdFontLocation(this.locationFontTextureBase);

      for (int i = 0; i < unicodePageLocations.length; i++) {
         unicodePageLocations[i] = null;
      }

      this.readFontTexture();
      this.readGlyphSizes();
   }

   private void renderSplitString(String str, int n, int n2, int n3, boolean flag) {
      for (String str2 : (Iterable<String>)(this.listFormattedStringToWidth(str, n3))) {
         this.renderStringAligned(str2, n, n2, n3, this.textColor, flag);
         n2 += this.FONT_HEIGHT;
      }
   }

   private String wrapFormattedStringToWidth(String str, int n) {
      if (str.length() <= 1) {
         return str;
      } else {
         int n2 = this.sizeStringToWidth(str, n);
         if (str.length() <= n2) {
            return str;
         } else {
            String str2 = str.substring(0, n2);
            char ch = str.charAt(n2);
            boolean flag = ch == ' ' || ch == '\n';
            String str3 = getFormatFromString(str2) + str.substring(n2 + (flag ? 1 : 0));
            return str2 + "\n" + this.wrapFormattedStringToWidth(str3, n);
         }
      }
   }

   private int renderStringAligned(String str, int n, int n2, int n3, int n4, boolean flag) {
      if (this.bidiFlag) {
         n = n + n3 - this.getStringWidth(this.bidiReorder(str));
      }

      str = this.applyTextFilters(str);
      this.bindTexture(this.locationFontTexture);
      Tessellator instance = Tessellator.getInstance();
      WorldRenderer worldRenderer = instance.getWorldRenderer();
      worldRenderer.begin(7, DefaultVertexFormats.POSITION_TEX_COLOR);
      int n5 = flag ? this.renderStringShadow(str, (float)n, (float)n2, n4, worldRenderer, false) : this.renderStringForeground(str, (float)n, (float)n2, n4, worldRenderer, false);
      instance.draw();
      return n5;
   }

   private float renderDefaultChar(int n, boolean flag, WorldRenderer worldRenderer) {
      int n2 = n % 16 * 8;
      int n3 = n / 16 * 8;
      int n4 = flag ? 1 : 0;
      float f = this.charWidth[n];
      float f2 = 7.99F;
      worldRenderer.pos((double)(this.posX + (float)n4), (double)this.posY, 0.0)
         .tex((double)((float)n2 / 128.0F), (double)((float)n3 / 128.0F))
         .color(this.red, this.green, this.blue, this.alpha)
         .endVertex();
      worldRenderer.pos((double)(this.posX - (float)n4), (double)(this.posY + 7.99F), 0.0)
         .tex((double)((float)n2 / 128.0F), (double)(((float)n3 + 7.99F) / 128.0F))
         .color(this.red, this.green, this.blue, this.alpha)
         .endVertex();
      worldRenderer.pos((double)(this.posX + f2 - 1.0F - (float)n4), (double)(this.posY + 7.99F), 0.0)
         .tex((double)(((float)n2 + f2 - 1.0F) / 128.0F), (double)(((float)n3 + 7.99F) / 128.0F))
         .color(this.red, this.green, this.blue, this.alpha)
         .endVertex();
      worldRenderer.pos((double)(this.posX + f2 - 1.0F + (float)n4), (double)this.posY, 0.0)
         .tex((double)(((float)n2 + f2 - 1.0F) / 128.0F), (double)((float)n3 / 128.0F))
         .color(this.red, this.green, this.blue, this.alpha)
         .endVertex();
      return f;
   }

   public int drawStringWithShadow(String str, float f, float f2, int n) {
      return this.drawStringInternal(str, f, f2, n, true, true, true, false, 1.0F);
   }

   public int drawString(String str, float f, float f2, int n, boolean flag) {
      return this.drawStringInternal(str, f, f2, n, flag, true, true, false, 1.0F);
   }

   private int getCharWidth(char ch) {
      return Math.round(this.getCharWidthFloat(ch));
   }

   private void bindTexture(ResourceLocation location) {
      this.renderEngine.bindTexture(location);
   }

   public int drawStringUnfiltered(String str, float f, float f2, int n) {
      return this.drawStringInternal(str, f, f2, n, false, true, true, true, 1.0F);
   }

   private int renderStringForeground(String str, float f, float f2, int n, WorldRenderer worldRenderer, boolean flag) {
      if (str == null) {
         return 0;
      } else {
         if (this.bidiFlag) {
            str = this.bidiReorder(str);
         }

         if ((n & -67108864) == 0) {
            n |= -16777216;
         }

         this.red = (float)(n >> 16 & 0xFF) / 255.0F;
         this.green = (float)(n >> 8 & 0xFF) / 255.0F;
         this.blue = (float)(n & 0xFF) / 255.0F;
         this.alpha = (float)(n >> 24 & 0xFF) / 255.0F;
         this.posX = f;
         this.posY = f2;
         this.renderStringAtPos(str, worldRenderer, this.red, this.green, this.blue, this.alpha, flag);
         return (int)this.posX;
      }
   }

   private float renderUnicodeChar(char ch, boolean flag) {
      if (this.glyphWidth[ch] == 0) {
         return 0.0F;
      } else {
         this.loadGlyphTexture(ch / 256);
         int n2 = this.glyphWidth[ch] >>> 4;
         int n3 = this.glyphWidth[ch] & 15;
         n2 &= 15;
         float f = (float)n2;
         float f2 = (float)(n3 + 1);
         float f3 = (float)(ch % 16 * 16) + f;
         float f4 = (float)((ch & 255) / 16 * 16);
         float f5 = f2 - f - 0.02F;
         float f6 = flag ? 1.0F : 0.0F;
         this.setColor(this.red, this.green, this.blue, this.alpha);
         GL11.glBegin(5);
         GL11.glTexCoord2f(f3 / 256.0F, f4 / 256.0F);
         GL11.glVertex3f(this.posX + f6, this.posY, 0.0F);
         GL11.glTexCoord2f(f3 / 256.0F, (f4 + 15.98F) / 256.0F);
         GL11.glVertex3f(this.posX - f6, this.posY + 7.99F, 0.0F);
         GL11.glTexCoord2f((f3 + f5) / 256.0F, f4 / 256.0F);
         GL11.glVertex3f(this.posX + f5 / 2.0F + f6, this.posY, 0.0F);
         GL11.glTexCoord2f((f3 + f5) / 256.0F, (f4 + 15.98F) / 256.0F);
         GL11.glVertex3f(this.posX + f5 / 2.0F - f6, this.posY + 7.99F, 0.0F);
         GL11.glEnd();
         this.bindTexture(this.locationFontTexture);
         return (f2 - f) / 2.0F + 1.0F;
      }
   }

   public int drawStringFiltered(String str, float f, float f2, int n, boolean flag, boolean flag2, boolean flag3) {
      return this.drawStringInternal(str, f, f2, n, flag, flag2, true, flag3, 1.0F);
   }

   public void setBidiFlag(boolean flag) {
      this.bidiFlag = flag;
   }

   public int drawStringInternal(String str, float f, float f2, int n, boolean flag, boolean flag2, boolean flag3, boolean flag4, float f3) {
      this.enableAlpha();
      if (this.blend) {
         GlStateManager.getBlendState(this.oldBlendState);
         GlStateManager.enableBlend();
         GlStateManager.blendFunc(770, 771);
      }

      this.resetStyles();
      if (flag2) {
         str = this.filterText(str, !flag3);
      }

      this.bindTexture(this.locationFontTexture);
      Tessellator instance = Tessellator.getInstance();
      WorldRenderer worldRenderer = instance.getWorldRenderer();
      worldRenderer.begin(7, DefaultVertexFormats.POSITION_TEX_COLOR);
      int n2;
      if (flag) {
         n2 = this.renderStringShadow(str, f + f3, f2 + f3, n, worldRenderer, flag4);
         n2 = Math.max(n2, this.renderStringForeground(str, f, f2, n, worldRenderer, flag4));
      } else {
         n2 = this.renderStringForeground(str, f, f2, n, worldRenderer, flag4);
      }

      instance.draw();
      if (this.blend) {
         GlStateManager.setBlendState(this.oldBlendState);
      }

      return n2;
   }

   public boolean getUnicodeFlag() {
      return this.unicodeFlag;
   }

   public int drawStringNoColorFilter(String str, int n, int n2, int n3) {
      return this.drawStringInternal(str, (float)n, (float)n2, n3, false, true, true, true, 1.0F);
   }

   private static boolean isFormatSpecial(char ch) {
      return ch >= 'k' && ch <= 'o' || ch >= 'K' && ch <= 'O' || ch == 'r' || ch == 'R';
   }

   public double getStringWidthFloat(String str) {
      if (str == null) {
         return 0.0;
      } else {
         float f = 0.0F;
         boolean flag = false;

         for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            float charWidthFloat = this.getCharWidthFloat(ch);
            if (charWidthFloat < 0.0F && i < str.length() - 1) {
               ch = str.charAt(++i);
               if (ch == 'l' || ch == 'L') {
                  flag = true;
               } else if (ch == 'r' || ch == 'R') {
                  flag = false;
               }

               charWidthFloat = 0.0F;
            }

            f += charWidthFloat;
            if (flag && charWidthFloat > 0.0F) {
               f += this.unicodeFlag ? 0.5F : this.offsetBold;
            }
         }

         return (double)f;
      }
   }

   private int renderStringShadow(String str, float f, float f2, int n, WorldRenderer worldRenderer, boolean flag) {
      if (str == null) {
         return 0;
      } else {
         if (this.bidiFlag) {
            str = this.bidiReorder(str);
         }

         if ((n & -67108864) == 0) {
            n |= -16777216;
         }

         n = (n & 16579836) >> 2 | n & 0xFF000000;
         this.red = (float)(n >> 16 & 0xFF) / 255.0F;
         this.green = (float)(n >> 8 & 0xFF) / 255.0F;
         this.blue = (float)(n & 0xFF) / 255.0F;
         this.alpha = (float)(n >> 24 & 0xFF) / 255.0F;
         this.setColor(this.red, this.green, this.blue, this.alpha);
         this.posX = f;
         this.posY = f2;
         this.renderStringShadowAtPos(str, worldRenderer, this.red, this.green, this.blue, this.alpha, flag);
         return (int)this.posX;
      }
   }

   public int drawStringWithOptions(String str, float f, float f2, int n, boolean flag, boolean flag2) {
      return this.drawStringInternal(str, f, f2, n, flag, true, true, flag2, 1.0F);
   }

   private ResourceLocation getUnicodePageLocation(int n) {
      if (unicodePageLocations[n] == null) {
         unicodePageLocations[n] = new ResourceLocation(String.format("textures/font/unicode_page_%02x.png", n));
         unicodePageLocations[n] = FontUtils.getHdFontLocation(unicodePageLocations[n]);
      }

      return unicodePageLocations[n];
   }

   @Override
   public int drawString(String str, float f, float f2, int n) {
      return this.drawStringInternal(str, f, f2, n, false, true, true, false, 1.0F);
   }

   public void drawSplitString(String str, int n, int n2, int n3, int n4) {
      if (this.blend) {
         GlStateManager.getBlendState(this.oldBlendState);
         GlStateManager.enableBlend();
         GlStateManager.blendFunc(770, 771);
      }

      this.resetStyles();
      this.textColor = n4;
      str = this.trimStringNewline(str);
      this.renderSplitString(str, n, n2, n3, false);
      if (this.blend) {
         GlStateManager.setBlendState(this.oldBlendState);
      }
   }

   public FontRenderer(GameSettings gameSettings, ResourceLocation location, TextureManager textureManager, boolean flag) {
      this.locationFontTextureBase = location;
      this.locationFontTexture = location;
      this.renderEngine = textureManager;
      this.unicodeFlag = flag;
      this.locationFontTexture = FontUtils.getHdFontLocation(this.locationFontTextureBase);
      this.bindTexture(this.locationFontTexture);

      for (int i = 0; i < 32; i++) {
         int n = (i >> 3 & 1) * 85;
         int n2 = (i >> 2 & 1) * 170 + n;
         int n3 = (i >> 1 & 1) * 170 + n;
         int n4 = (i >> 0 & 1) * 170 + n;
         if (i == 6) {
            n2 += 85;
         }

         if (gameSettings.anaglyph) {
            int n5 = (n2 * 30 + n3 * 59 + n4 * 11) / 100;
            int n6 = (n2 * 30 + n3 * 70) / 100;
            int n7 = (n2 * 30 + n4 * 70) / 100;
            n2 = n5;
            n3 = n6;
            n4 = n7;
         }

         if (i >= 16) {
            n2 /= 4;
            n3 /= 4;
            n4 /= 4;
         }

         this.colorCode[i] = (n2 & 0xFF) << 16 | (n3 & 0xFF) << 8 | n4 & 0xFF;
      }

      this.readGlyphSizes();
   }

   public List listFormattedStringToWidth(String str, int n) {
      return Arrays.asList(this.wrapFormattedStringToWidth(str, n).split("\n"));
   }

   public void clearFilterPatterns() {
      this.serverIpPattern = null;
      this.customDomainPattern = null;
      this.altDomainPattern = null;
      this.playerNamePattern = null;
      this.altPlayerNamePattern = null;
   }

   public int drawStringExtended(String str, float f, float f2, int n, boolean flag, boolean flag2, boolean flag3, boolean flag4) {
      return this.drawStringInternal(str, f, f2, n, flag, flag2, flag3, flag4, 1.0F);
   }

   private String trimStringNewline(String str) {
      while (str != null && str.endsWith("\n")) {
         str = str.substring(0, str.length() - 1);
      }

      return str;
   }

   public String trimStringToWidth(String str, int n) {
      return this.trimStringToWidth(str, n, false);
   }

   public int splitStringWidth(String str, int n) {
      return this.FONT_HEIGHT * this.listFormattedStringToWidth(str, n).size();
   }

   private float renderChar(char ch, boolean flag, WorldRenderer worldRenderer, int n) {
      if (ch != ' ' && ch != 160) {
         return n != -1 && !this.unicodeFlag ? this.renderDefaultChar(n, flag, worldRenderer) : this.renderUnicodeChar(ch, flag);
      } else {
         return !this.unicodeFlag ? this.charWidth[ch] : 4.0F;
      }
   }

   private String bidiReorder(String str) {
      try {
         Bidi bidi = new Bidi(new ArabicShaping(8).shape(str), 127);
         bidi.setReorderingMode(0);
         return bidi.writeReordered(2);
      } catch (ArabicShapingException e) {
         return str;
      }
   }

   private void loadGlyphTexture(int n) {
      this.bindTexture(this.getUnicodePageLocation(n));
   }

}
