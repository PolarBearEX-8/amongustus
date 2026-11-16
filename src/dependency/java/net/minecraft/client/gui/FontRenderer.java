package net.minecraft.client.gui;

import client.Client;
import client.ClientGlobals;
import client.module.Module;
import client.render.AuxiliaryTexturesManager;
import client.render.WorldRenderer;
import client.setting.GameSettings;
import client.util.AutoReportCache;
import client.util.BuildManager;
import client.util.DefaultVertexFormats;
import client.util.EnabledManager;
import client.util.LoadingManager;
import client.util.MinecraftAccessor;
import client.util.RandomUtil;
import client.util.SpecialLoggerEntry;
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
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.client.resources.IResourceManagerReloadListener;
import net.minecraft.item.SharedItemEntry;
import net.minecraft.util.ResourceLocation;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.lwjgl.opengl.GL11;

public class FontRenderer implements MinecraftAccessor, IResourceManagerReloadListener {
   public static final int[] countArray4 = new int[128];
   public static final int[] countArray2;
   public static final String labelText = "ÀÁÂÈÊËÍÓÔÕÚßãõğİıŒœŞşŴŵžȇ\u0000\u0000\u0000\u0000\u0000\u0000\u0000 !\"#$%&'()*+,-./0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[\\]^_`abcdefghijklmnopqrstuvwxyz{|}~\u0000ÇüéâäàåçêëèïîìÄÅÉæÆôöòûùÿÖÜø£Ø×ƒáíóúñÑªº¿®¬½¼¡«»░▒▓│┤╡╢╖╕╣║╗╝╜╛┐└┴┬├─┼╞╟╚╔╩╦╠═╬╧╨╤╥╙╘╒╓╫╪┘┌█▄▌▐▀αβΓπΣσμτΦΘΩδ∞∅∈∩≡±≥≤⌠⌡÷≈°∙·√ⁿ²■\u0000";
   public static final ResourceLocation[] resourceLocationArray;
   public final TextureManager textureManager;
   public int indexOffset = 9;
   public Random random = new Random();
   public GameSettings gameSettings;
   public ResourceLocation resourceLocation;
   public float factor = 1.0F;
   public byte[] byteFlagArray = new byte[65536];
   public int[] countArray = new int[32];
   public float factor4;
   public float factor6;
   public boolean enabled6;
   public boolean enabled4;
   public boolean enabled5;
   public boolean enabled2;
   public boolean enabled;
   public final int[] countArray3 = new int[256];
   public ResourceLocation resourceLocation2;
   public boolean state;
   public boolean state1;
   public float factor3;
   public float factor7;
   public float factor2;
   public float factor5;
   public int count;
   public float[] factorArray = new float[256];
   public boolean enabled3 = false;
   public EnabledManager enabledManager = new EnabledManager();

   public int computeCountByChar(char character) {
      int count2 = "0123456789abcdef".indexOf(character);
      if (count2 >= 0 && count2 < this.countArray.length) {
         int count3 = this.countArray[count2];
         if (BuildManager.isActive33()) {
            count3 = LoadingManager.computeCountByIndex(count2, count3);
         }

         return count3;
      } else {
         return 16777215;
      }
   }

   private int computeCountByText(String text, float factor8, float factor9, int count2, WorldRenderer worldRenderer, boolean flag) {
      String text2 = text;
      int count3 = count2;
      if (text == null) {
         return 0;
      } else {
         if (this.state1) {
            text2 = this.resolveText3(text);
         }

         if ((count2 & -67108864) == 0) {
            count3 = count2 | 0xFF000000;
         }

         this.factor3 = (float)(count3 >> 16 & 0xFF) / 255.0F;
         this.factor2 = (float)(count3 >> 8 & 0xFF) / 255.0F;
         this.factor7 = (float)(count3 & 0xFF) / 255.0F;
         this.factor5 = (float)(count3 >> 24 & 0xFF) / 255.0F;
         this.factor4 = factor8;
         this.factor6 = factor9;
         this.applyTextByText(text2, worldRenderer, this.factor3, this.factor2, this.factor7, this.factor5, flag);
         return (int)this.factor4;
      }
   }

   public boolean isState() {
      return this.state;
   }

   private String resolveText3(String text) {
      try {
         Bidi bidi = new Bidi(new ArabicShaping(8).shape(text), 127);
         bidi.setReorderingMode(0);
         return bidi.writeReordered(2);
      } catch (ArabicShapingException arabicShapingException) {
         return text;
      }
   }

   public int computeCount(String text, float factor8, float factor9, int count2, int count3, float factor10, boolean flag) {
      this.renderElement2();
      this.renderElement();
      Tessellator tessellator = Tessellator.getTessellator();
      WorldRenderer worldRenderer = tessellator.getWorldRenderer();
      worldRenderer.updateAuxiliaryLoggerEntry(7, DefaultVertexFormats.auxiliaryLoggerEntry6);
      int count4;
      if (flag) {
         count4 = Math.max(
            this.computeCount(worldRenderer, text, factor8 + 1.0F, factor9 + 1.0F, count2, count3, factor10, false), this.computeCount(worldRenderer, text, factor8, factor9, count2, count3, factor10)
         );
      } else {
         count4 = this.computeCount(worldRenderer, text, factor8, factor9, count2, count3, factor10);
      }

      tessellator.updateState();
      return count4;
   }

   public void renderElement2() {
      GlStateManager.applyState14();
   }

   public int getIndex(String text, float factor8, float factor9, int count2) {
      return this.computeCount(text, factor8, factor9, count2, false, true);
   }

   private void applyText(String text, WorldRenderer worldRenderer, int count2, int count3, float factor8, boolean flag) {
      boolean flag2 = ((Module)ClientGlobals.moduleManager.antiStrike).isToggled()
         && ClientGlobals.moduleManager.antiStrike.settingRemoveColorCodes.isEnabled()
         && Minecraft.getMinecraft().thePlayer != null
         && !flag;

      for (int index = 0; index < text.length(); index++) {
         char character = text.charAt(index);
         if (character == 167 && index + 1 < text.length()) {
            int count4 = this.computeCountByText(text, index + 1);
            if (count4 < 16) {
               this.enabled6 = false;
               this.enabled4 = false;
               this.enabled = false;
               this.enabled2 = false;
               this.enabled5 = false;
               if (count4 < 0 || count4 > 15) {
                  count4 = 15;
               }

               count4 += 16;
               int count5 = this.countArray[count4];
               if (BuildManager.isActive33()) {
                  count5 = LoadingManager.computeCountByIndex(count4, count5);
               }

               this.count = count5;
               if (!flag2) {
                  this.factor3 = (float)(count5 >> 16) / 255.0F;
                  this.factor2 = (float)(count5 >> 8 & 0xFF) / 255.0F;
                  this.factor7 = (float)(count5 & 0xFF) / 255.0F;
               }
            } else if (count4 == 16) {
               this.enabled6 = !flag2;
            } else if (count4 == 17) {
               this.enabled4 = !flag2;
            } else if (count4 == 18) {
               this.enabled = !flag2;
            } else if (count4 == 19) {
               this.enabled2 = !flag2;
            } else if (count4 == 20) {
               this.enabled5 = !flag2;
            } else if (count4 == 21) {
               this.enabled6 = false;
               this.enabled4 = false;
               this.enabled = false;
               this.enabled2 = false;
               this.enabled5 = false;
            }

            index++;
         } else {
            float factor9 = ((float)index * 2.0F / (float)text.length() + factor8) % 2.0F;
            if (factor9 > 1.0F) {
               factor9 = 1.0F - factor9 % 1.0F;
            }

            int count6 = RandomUtil.processAction(count2, count3, factor9);
            this.factor3 = (float)(count6 >> 16 & 0xFF) / 255.0F;
            this.factor2 = (float)(count6 >> 8 & 0xFF) / 255.0F;
            this.factor7 = (float)(count6 & 0xFF) / 255.0F;
            this.factor5 = (float)(count6 >> 24 & 0xFF) / 255.0F;
            int count7 = character < countArray2.length ? countArray2[character] : -1;
            if (this.enabled6 && count7 != -1) {
               int count8 = this.computeCount(character);

               char character2;
               do {
                  count7 = this.random
                     .nextInt(
                        "ÀÁÂÈÊËÍÓÔÕÚßãõğİıŒœŞşŴŵžȇ\u0000\u0000\u0000\u0000\u0000\u0000\u0000 !\"#$%&'()*+,-./0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[\\]^_`abcdefghijklmnopqrstuvwxyz{|}~\u0000ÇüéâäàåçêëèïîìÄÅÉæÆôöòûùÿÖÜø£Ø×ƒáíóúñÑªº¿®¬½¼¡«»░▒▓│┤╡╢╖╕╣║╗╝╜╛┐└┴┬├─┼╞╟╚╔╩╦╠═╬╧╨╤╥╙╘╒╓╫╪┘┌█▄▌▐▀αβΓπΣσμτΦΘΩδ∞∅∈∩≡±≥≤⌠⌡÷≈°∙·√ⁿ²■\u0000"
                           .length()
                     );
                  character2 = "ÀÁÂÈÊËÍÓÔÕÚßãõğİıŒœŞşŴŵžȇ\u0000\u0000\u0000\u0000\u0000\u0000\u0000 !\"#$%&'()*+,-./0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[\\]^_`abcdefghijklmnopqrstuvwxyz{|}~\u0000ÇüéâäàåçêëèïîìÄÅÉæÆôöòûùÿÖÜø£Ø×ƒáíóúñÑªº¿®¬½¼¡«»░▒▓│┤╡╢╖╕╣║╗╝╜╛┐└┴┬├─┼╞╟╚╔╩╦╠═╬╧╨╤╥╙╘╒╓╫╪┘┌█▄▌▐▀αβΓπΣσμτΦΘΩδ∞∅∈∩≡±≥≤⌠⌡÷≈°∙·√ⁿ²■\u0000"
                     .charAt(count7);
               } while (count8 != this.computeCount(character2));

               character = character2;
            }

            float factor10 = count7 != -1 && !this.state ? this.factor : 0.5F;
            boolean flag3 = character == 0 || count7 == -1 || this.state;
            if (flag3) {
               this.factor4 -= factor10;
               this.factor6 -= factor10;
            }

            float factor11 = this.computeFactor(character, this.enabled5, worldRenderer, count7);
            if (flag3) {
               this.factor4 += factor10;
               this.factor6 += factor10;
            }

            if (this.enabled4) {
               this.factor4 += factor10;
               if (flag3) {
                  this.factor4 -= factor10;
                  this.factor6 -= factor10;
               }

               this.computeFactor(character, this.enabled5, worldRenderer, count7);
               this.factor4 -= factor10;
               if (flag3) {
                  this.factor4 += factor10;
                  this.factor6 += factor10;
               }

               factor11 += factor10;
            }

            if (this.enabled) {
               float factor12 = this.factor4;
               float factor13 = this.factor6 + (float)(this.indexOffset / 2);
               float factor14 = this.factor4 + factor11;
               float factor15 = this.factor6 + (float)(this.indexOffset / 2) - 1.0F;
               GL11.glDisable(3553);
               this.applyFloat(this.factor3, this.factor2, this.factor7, this.factor5);
               GL11.glBegin(7);
               GL11.glVertex2d((double)factor12, (double)factor13);
               GL11.glVertex2d((double)factor14, (double)factor13);
               GL11.glVertex2d((double)factor14, (double)factor15);
               GL11.glVertex2d((double)factor12, (double)factor15);
               GL11.glEnd();
               GL11.glEnable(3553);
            }

            if (this.enabled2) {
               float factor16 = this.factor4 - 1.0F;
               float factor17 = this.factor6 + (float)this.indexOffset;
               float factor18 = this.factor4 + factor11;
               float factor19 = this.factor6 + (float)this.indexOffset - 1.0F;
               GL11.glDisable(3553);
               this.applyFloat(this.factor3, this.factor2, this.factor7, this.factor5);
               GL11.glBegin(7);
               GL11.glVertex2d((double)factor16, (double)factor17);
               GL11.glVertex2d((double)factor18, (double)factor17);
               GL11.glVertex2d((double)factor18, (double)factor19);
               GL11.glVertex2d((double)factor16, (double)factor19);
               GL11.glEnd();
               GL11.glEnable(3553);
            }

            this.factor4 += factor11;
         }
      }
   }

   private String resolveText(String text) {
      String text2 = text;

      while (text2 != null && text2.endsWith("\n")) {
         text2 = text2.substring(0, text2.length() - 1);
      }

      return text2;
   }

   private void applyText(String text, int count2, int count3, int count4, boolean flag) {
      int count5 = count3;

      for (String text2 : (Iterable<String>)(Iterable<?>)this.getList(text, count4)) {
         this.computeCount(text2, count2, count5, count4, this.count, flag);
         count5 += this.indexOffset;
      }
   }

   public int getIndex(String text, float factor8, float factor9, int count2, boolean flag, float factor10) {
      this.renderElement2();
      this.renderElement();
      String text2 = this.resolveText2(text);
      this.applyResourceLocationByResourceLocation(this.resourceLocation2);
      Tessellator tessellator = Tessellator.getTessellator();
      WorldRenderer worldRenderer = tessellator.getWorldRenderer();
      worldRenderer.updateAuxiliaryLoggerEntry(7, DefaultVertexFormats.auxiliaryLoggerEntry6);
      int count3;
      if (flag) {
         count3 = Math.max(this.computeCount(text2, factor8 + factor10, factor9 + factor10, count2, worldRenderer, true), this.computeCountByText(text2, factor8, factor9, count2, worldRenderer, true));
      } else {
         count3 = this.computeCountByText(text2, factor8, factor9, count2, worldRenderer, true);
      }

      tessellator.updateState();
      return count3;
   }

   public InputStream resolveInputStream(ResourceLocation location) throws IOException {
      return Minecraft.getMinecraft().getIResourceManager().resolveIResourceByResourceLocation(location).getInputStream();
   }

   private static boolean checkChar(char character) {
      return character >= 'k' && character <= 'o' || character >= 'K' && character <= 'O' || character == 'r' || character == 'R';
   }

   public int computeCount(char character) {
      return Math.round(this.computeFactor(character));
   }

   private float computeFactor(char character, boolean flag, WorldRenderer worldRenderer, int count2) {
      if (character != ' ' && character != 160) {
         return count2 != -1 && !this.state ? this.computeFactor(count2, flag, worldRenderer) : this.computeFactor(character, flag);
      } else {
         return !this.state ? this.factorArray[character] : 4.0F;
      }
   }

   private void applyText(String text, WorldRenderer worldRenderer, float posX, float posX2, float posY, float posZ, boolean flag) {
      boolean flag2 = ((Module)ClientGlobals.moduleManager.antiStrike).isToggled()
         && ClientGlobals.moduleManager.antiStrike.settingRemoveColorCodes.isEnabled()
         && Minecraft.getMinecraft().thePlayer != null
         && !flag;

      for (int index = 0; index < text.length(); index++) {
         char character = text.charAt(index);
         if (character == 167 && index + 1 < text.length()) {
            int count2 = this.computeCountByText(text, index + 1);
            if (count2 < 16) {
               this.enabled6 = false;
               this.enabled4 = false;
               this.enabled = false;
               this.enabled2 = false;
               this.enabled5 = false;
               if (count2 < 0 || count2 > 15) {
                  count2 = 15;
               }

               count2 += 16;
               int count3 = this.countArray[count2];
               if (BuildManager.isActive33()) {
                  count3 = LoadingManager.computeCountByIndex(count2, count3);
               }

               this.count = count3;
               if (!flag2) {
                  this.factor3 = (float)(count3 >> 16) / 255.0F;
                  this.factor2 = (float)(count3 >> 8 & 0xFF) / 255.0F;
                  this.factor7 = (float)(count3 & 0xFF) / 255.0F;
               }
            } else if (count2 == 16) {
               this.enabled6 = !flag2;
            } else if (count2 == 17) {
               this.enabled4 = !flag2;
            } else if (count2 == 18) {
               this.enabled = !flag2;
            } else if (count2 == 19) {
               this.enabled2 = !flag2;
            } else if (count2 == 20) {
               this.enabled5 = !flag2;
            } else if (count2 == 21) {
               this.enabled6 = false;
               this.enabled4 = false;
               this.enabled = false;
               this.enabled2 = false;
               this.enabled5 = false;
               this.factor3 = posX;
               this.factor2 = posX2;
               this.factor7 = posY;
               this.factor5 = posZ;
            }

            index++;
         } else {
            int count4 = character < countArray2.length ? countArray2[character] : -1;
            if (this.enabled6 && count4 != -1) {
               int count5 = this.computeCount(character);

               char character2;
               do {
                  count4 = this.random
                     .nextInt(
                        "ÀÁÂÈÊËÍÓÔÕÚßãõğİıŒœŞşŴŵžȇ\u0000\u0000\u0000\u0000\u0000\u0000\u0000 !\"#$%&'()*+,-./0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[\\]^_`abcdefghijklmnopqrstuvwxyz{|}~\u0000ÇüéâäàåçêëèïîìÄÅÉæÆôöòûùÿÖÜø£Ø×ƒáíóúñÑªº¿®¬½¼¡«»░▒▓│┤╡╢╖╕╣║╗╝╜╛┐└┴┬├─┼╞╟╚╔╩╦╠═╬╧╨╤╥╙╘╒╓╫╪┘┌█▄▌▐▀αβΓπΣσμτΦΘΩδ∞∅∈∩≡±≥≤⌠⌡÷≈°∙·√ⁿ²■\u0000"
                           .length()
                     );
                  character2 = "ÀÁÂÈÊËÍÓÔÕÚßãõğİıŒœŞşŴŵžȇ\u0000\u0000\u0000\u0000\u0000\u0000\u0000 !\"#$%&'()*+,-./0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[\\]^_`abcdefghijklmnopqrstuvwxyz{|}~\u0000ÇüéâäàåçêëèïîìÄÅÉæÆôöòûùÿÖÜø£Ø×ƒáíóúñÑªº¿®¬½¼¡«»░▒▓│┤╡╢╖╕╣║╗╝╜╛┐└┴┬├─┼╞╟╚╔╩╦╠═╬╧╨╤╥╙╘╒╓╫╪┘┌█▄▌▐▀αβΓπΣσμτΦΘΩδ∞∅∈∩≡±≥≤⌠⌡÷≈°∙·√ⁿ²■\u0000"
                     .charAt(count4);
               } while (count5 != this.computeCount(character2));

               character = character2;
            }

            float factor8 = count4 != -1 && !this.state ? this.factor : 0.5F;
            boolean flag3 = character == 0 || count4 == -1 || this.state;
            if (flag3) {
               this.factor4 -= factor8;
               this.factor6 -= factor8;
            }

            float factor9 = this.computeFactor(character, this.enabled5, worldRenderer, count4);
            if (flag3) {
               this.factor4 += factor8;
               this.factor6 += factor8;
            }

            if (this.enabled4) {
               this.factor4 += factor8;
               if (flag3) {
                  this.factor4 -= factor8;
                  this.factor6 -= factor8;
               }

               this.computeFactor(character, this.enabled5, worldRenderer, count4);
               this.factor4 -= factor8;
               if (flag3) {
                  this.factor4 += factor8;
                  this.factor6 += factor8;
               }

               factor9 += factor8;
            }

            if (this.enabled) {
               float factor10 = this.factor4;
               float factor11 = this.factor6 + (float)(this.indexOffset / 2);
               float factor12 = this.factor4 + factor9;
               float factor13 = this.factor6 + (float)(this.indexOffset / 2) - 1.0F;
               GL11.glDisable(3553);
               this.applyFloat(this.factor3, this.factor2, this.factor7, this.factor5);
               GL11.glBegin(7);
               GL11.glVertex2d((double)factor10, (double)factor11);
               GL11.glVertex2d((double)factor12, (double)factor11);
               GL11.glVertex2d((double)factor12, (double)factor13);
               GL11.glVertex2d((double)factor10, (double)factor13);
               GL11.glEnd();
               GL11.glEnable(3553);
            }

            if (this.enabled2) {
               float factor14 = this.factor4 - 1.0F;
               float factor15 = this.factor6 + (float)this.indexOffset;
               float factor16 = this.factor4 + factor9;
               float factor17 = this.factor6 + (float)this.indexOffset - 1.0F;
               GL11.glDisable(3553);
               this.applyFloat(this.factor3, this.factor2, this.factor7, this.factor5);
               GL11.glBegin(7);
               GL11.glVertex2d((double)factor14, (double)factor15);
               GL11.glVertex2d((double)factor16, (double)factor15);
               GL11.glVertex2d((double)factor16, (double)factor17);
               GL11.glVertex2d((double)factor14, (double)factor17);
               GL11.glEnd();
               GL11.glEnable(3553);
            }

            this.factor4 += factor9;
         }
      }
   }

   public int getIndex(String text, int count2, int count3, int count4, boolean flag) {
      this.renderElement2();
      if (this.enabled3) {
         GlStateManager.applyEnabledManager(this.enabledManager);
         GlStateManager.disableBlend();
         GlStateManager.glStateAction3(770, 771);
      }

      this.renderElement();
      String text2 = this.resolveText2(text);
      this.applyResourceLocationByResourceLocation(this.resourceLocation2);
      Tessellator tessellator = Tessellator.getTessellator();
      WorldRenderer worldRenderer = tessellator.getWorldRenderer();
      worldRenderer.updateAuxiliaryLoggerEntry(7, DefaultVertexFormats.auxiliaryLoggerEntry6);
      int count5;
      if (flag) {
         count5 = Math.max(
            this.computeCount(text2, (float)count2 + 1.0F, (float)count3 + 1.0F, count4, worldRenderer, false),
            this.computeCountByText(text2, (float)count2, (float)count3, count4, worldRenderer, false)
         );
      } else {
         count5 = this.computeCountByText(text2, (float)count2, (float)count3, count4, worldRenderer, false);
      }

      tessellator.updateState();
      if (this.enabled3) {
         GlStateManager.applyEnabledManagerByEnabledManager(this.enabledManager);
      }

      return count5;
   }

   public int getIndex2(String text, float factor8, float factor9, int count2) {
      return this.computeCount(text, factor8, factor9, count2, true, false);
   }

   private void renderElement() {
      this.enabled6 = false;
      this.enabled4 = false;
      this.enabled5 = false;
      this.enabled2 = false;
      this.enabled = false;
   }

   public float computeFactor(char character, boolean flag) {
      if (this.byteFlagArray[character] == 0) {
         return 0.0F;
      } else {
         int count2 = character / 256;
         this.applyIndex(count2);
         int count3 = this.byteFlagArray[character] >>> 4;
         int count4 = this.byteFlagArray[character] & 15;
         float factor8 = (float)(count3 & 15);
         float factor9 = (float)(count4 + 1);
         float factor10 = (float)(character % 16 * 16) + factor8;
         float factor11 = (float)((character & 255) / 16 * 16);
         float factor12 = factor9 - factor8 - 0.02F;
         float factor13 = flag ? 1.0F : 0.0F;
         this.applyFloat(this.factor3, this.factor2, this.factor7, this.factor5);
         GL11.glBegin(5);
         GL11.glTexCoord2f(factor10 / 256.0F, factor11 / 256.0F);
         GL11.glVertex3f(this.factor4 + factor13, this.factor6, 0.0F);
         GL11.glTexCoord2f(factor10 / 256.0F, (factor11 + 15.98F) / 256.0F);
         GL11.glVertex3f(this.factor4 - factor13, this.factor6 + 7.99F, 0.0F);
         GL11.glTexCoord2f((factor10 + factor12) / 256.0F, factor11 / 256.0F);
         GL11.glVertex3f(this.factor4 + factor12 / 2.0F + factor13, this.factor6, 0.0F);
         GL11.glTexCoord2f((factor10 + factor12) / 256.0F, (factor11 + 15.98F) / 256.0F);
         GL11.glVertex3f(this.factor4 + factor12 / 2.0F - factor13, this.factor6 + 7.99F, 0.0F);
         GL11.glEnd();
         this.applyResourceLocationByResourceLocation(this.resourceLocation2);
         return (factor9 - factor8) / 2.0F + 1.0F;
      }
   }

   private void applyIndex(int count2) {
      this.applyResourceLocationByResourceLocation(this.resolveResourceLocation(count2));
   }

   public int computeCountByText(String text, int count2) {
      char character = Character.toLowerCase(text.charAt(count2));
      return character < countArray4.length ? countArray4[character] : -1;
   }

   private static boolean checkCharByChar(char character) {
      return character >= '0' && character <= '9' || character >= 'a' && character <= 'f' || character >= 'A' && character <= 'F';
   }

   public String resolveText2(String text) {
      if (minecraft.thePlayer == null) {
         return text;
      } else {
         String text2 = AutoReportCache.resolveText(text);
         String text3 = SharedItemEntry.resolveText2(text2);
         if (text3 != null) {
            text2 = text3;
         }

         if (((Module)ClientGlobals.moduleManager.protector).isToggled()) {
            for (String text4 : (Iterable<String>)(Iterable<?>)Client.getClient().getTertiaryHashMapEntry().copyOnWriteArrayList) {
               if (text2.contains(text4)) {
                  String text5 = (String)Client.getClient().getTertiaryHashMapEntry().hashMap2.get(text4);
                  if (text5 != null) {
                     text2 = StringUtils.replace(text2, text4, text5);
                  }
               }
            }
         }

         if (((Module)ClientGlobals.moduleManager.antiStrike).isToggled() && ClientGlobals.moduleManager.antiStrike.settingReplaceServerName.isEnabled()) {
            if (minecraft.getServerData() != null && minecraft.getServerData().text != null && !minecraft.getServerData().text.isEmpty()) {
               text2 = Pattern.compile(minecraft.getServerData().text, 82).matcher(text2).replaceAll(Matcher.quoteReplacement("Augustus.top"));
            }

            if (!ClientGlobals.moduleManager.antiStrike.getDisplayName2().isEmpty()) {
               text2 = Pattern.compile(ClientGlobals.moduleManager.antiStrike.getDisplayName2(), 82)
                  .matcher(text2)
                  .replaceAll(Matcher.quoteReplacement("Augustus.top"));
            }

            if (!ClientGlobals.moduleManager.antiStrike.getText2().isEmpty()) {
               text2 = Pattern.compile(ClientGlobals.moduleManager.antiStrike.getText2(), 82)
                  .matcher(text2)
                  .replaceAll(Matcher.quoteReplacement("Augustus.top"));
            }

            if (!ClientGlobals.moduleManager.antiStrike.getName2().isEmpty()) {
               text2 = Pattern.compile(ClientGlobals.moduleManager.antiStrike.getName2(), 82).matcher(text2).replaceAll(Matcher.quoteReplacement("Augustus"));
            }

            if (!ClientGlobals.moduleManager.antiStrike.getText3().isEmpty()) {
               text2 = Pattern.compile(ClientGlobals.moduleManager.antiStrike.getText3(), 82).matcher(text2).replaceAll(Matcher.quoteReplacement("Augustus"));
            }
         }

         return text2;
      }
   }

   public int computeCount(String text, float factor8, float factor9, int count2, boolean flag, boolean flag2) {
      return this.getIndex(text, factor8, factor9, count2, flag, true, flag2);
   }

   public int computeCount(String text, float factor8, float factor9, int count2) {
      return this.computeCount(text, factor8, factor9, count2, true, true);
   }

   public int getIndex(String text) {
      return text == null ? 0 : this.computeCount(this.resolveText2(text));
   }

   private void renderElement4() {
      BufferedImage bufferedImage;
      try {
         bufferedImage = SpecialLoggerEntry.resolveBufferedImage(this.resolveInputStream(this.resourceLocation2));
      } catch (IOException ioException) {
         throw new RuntimeException(ioException);
      }

      Properties properties = AuxiliaryTexturesManager.resolvePropertiesByResourceLocation(this.resourceLocation2);
      this.enabled3 = AuxiliaryTexturesManager.matchesProperties(properties, "blend", false);
      int width = bufferedImage.getWidth();
      int height = bufferedImage.getHeight();
      int count2 = width / 16;
      int count3 = height / 16;
      float factor8 = (float)width / 128.0F;
      float factor9 = BuildManager.computeFactor(factor8, 1.0F, 2.0F);
      this.factor = 1.0F / factor9;
      float factor10 = AuxiliaryTexturesManager.computeFactor(properties, "offsetBold", -1.0F);
      if (factor10 >= 0.0F) {
         this.factor = factor10;
      }

      int[] countArray5 = new int[width * height];
      bufferedImage.getRGB(0, 0, width, height, countArray5, 0, width);

      for (int index = 0; index < 256; index++) {
         int count4 = index % 16;
         int count5 = index / 16;

         int count6;
         for (count6 = count2 - 1; count6 >= 0; count6--) {
            int count7 = count4 * count2 + count6;
            boolean flag = true;

            for (int index2 = 0; index2 < count3 && flag; index2++) {
               int count8 = (count5 * count3 + index2) * width;
               if ((countArray5[count7 + count8] >> 24 & 0xFF) > 16) {
                  flag = false;
               }
            }

            if (!flag) {
               break;
            }
         }

         if (index == 32) {
            if (count2 <= 8) {
               count6 = (int)(2.0F * factor8);
            } else {
               count6 = (int)(1.5F * factor8);
            }
         }

         this.factorArray[index] = (float)(count6 + 1) / factor8 + 1.0F;
      }

      AuxiliaryTexturesManager.applyProperties(properties, this.factorArray);

      for (int index3 = 0; index3 < this.countArray3.length; index3++) {
         this.countArray3[index3] = Math.round(this.factorArray[index3]);
      }
   }

   public String resolveText(String text, int count2, boolean flag) {
      StringBuilder builder = new StringBuilder();
      float factor8 = 0.0F;
      int count3 = flag ? text.length() - 1 : 0;
      int count4 = flag ? -1 : 1;
      boolean flag2 = false;

      for (boolean flag3 = false; count3 >= 0 && count3 < text.length() && factor8 < (float)count2; count3 += count4) {
         char character = text.charAt(count3);
         float factor9 = this.computeFactor(character);
         if (flag2) {
            flag2 = false;
            if (character == 'l' || character == 'L') {
               flag3 = true;
            } else if (character == 'r' || character == 'R') {
               flag3 = false;
            }
         } else if (factor9 < 0.0F) {
            flag2 = true;
         } else {
            factor8 += factor9;
            if (flag3) {
               factor8++;
            }
         }

         if (factor8 > (float)count2) {
            break;
         }

         if (flag) {
            builder.insert(0, character);
         } else {
            builder.append(character);
         }
      }

      return builder.toString();
   }

   public void setState1(boolean state2) {
      this.state1 = state2;
   }

   public int computeCountByText(String text, float factor8, float factor9, int count2) {
      return this.computeCount(text, factor8, factor9, count2, false, false);
   }

   public void applyResourceLocationByResourceLocation(ResourceLocation location) {
      this.textureManager.applyResourceLocation3(location);
   }

   @Override
   public void applyIResourceManager(IResourceManager iResourceManager) {
      this.resourceLocation2 = AuxiliaryTexturesManager.resolveResourceLocation(this.resourceLocation);

      for (int index = 0; index < resourceLocationArray.length; index++) {
         resourceLocationArray[index] = null;
      }

      this.renderElement4();
      this.renderElement3();
   }

   private ResourceLocation resolveResourceLocation(int count2) {
      if (resourceLocationArray[count2] == null) {
         resourceLocationArray[count2] = new ResourceLocation(String.format("textures/font/unicode_page_%02x.png", count2));
         resourceLocationArray[count2] = AuxiliaryTexturesManager.resolveResourceLocation(resourceLocationArray[count2]);
      }

      return resourceLocationArray[count2];
   }

   public int computeCount(String text, int count2, int count3, int count4) {
      return this.computeCount(text, (float)count2, (float)count3, count4, false, true);
   }

   private void applyTextByText(String text, WorldRenderer worldRenderer, float posX, float posX2, float posY, float posZ, boolean flag) {
      boolean flag2 = ((Module)ClientGlobals.moduleManager.antiStrike).isToggled()
         && ClientGlobals.moduleManager.antiStrike.settingRemoveColorCodes.isEnabled()
         && Minecraft.getMinecraft().thePlayer != null
         && !flag;

      for (int index = 0; index < text.length(); index++) {
         char character = text.charAt(index);
         if (character == 167 && index + 1 < text.length()) {
            int count2 = this.computeCountByText(text, index + 1);
            if (count2 < 16) {
               this.enabled6 = false;
               this.enabled4 = false;
               this.enabled = false;
               this.enabled2 = false;
               this.enabled5 = false;
               if (count2 < 0 || count2 > 15) {
                  count2 = 15;
               }

               int count3 = this.countArray[count2];
               if (BuildManager.isActive33()) {
                  count3 = LoadingManager.computeCountByIndex(count2, count3);
               }

               this.count = count3;
               if (!flag2) {
                  this.factor3 = (float)(count3 >> 16) / 255.0F;
                  this.factor2 = (float)(count3 >> 8 & 0xFF) / 255.0F;
                  this.factor7 = (float)(count3 & 0xFF) / 255.0F;
               }
            } else if (count2 == 16) {
               this.enabled6 = !flag2;
            } else if (count2 == 17) {
               this.enabled4 = !flag2;
            } else if (count2 == 18) {
               this.enabled = !flag2;
            } else if (count2 == 19) {
               this.enabled2 = !flag2;
            } else if (count2 == 20) {
               this.enabled5 = !flag2;
            } else if (count2 == 21) {
               this.enabled6 = false;
               this.enabled4 = false;
               this.enabled = false;
               this.enabled2 = false;
               this.enabled5 = false;
               this.factor3 = posX;
               this.factor7 = posY;
               this.factor2 = posX2;
               this.factor5 = posZ;
            }

            index++;
         } else {
            int count4 = character < countArray2.length ? countArray2[character] : -1;
            if (this.enabled6 && count4 != -1) {
               int count5 = this.computeCount(character);

               do {
                  count4 = this.random
                     .nextInt(
                        "ÀÁÂÈÊËÍÓÔÕÚßãõğİıŒœŞşŴŵžȇ\u0000\u0000\u0000\u0000\u0000\u0000\u0000 !\"#$%&'()*+,-./0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[\\]^_`abcdefghijklmnopqrstuvwxyz{|}~\u0000ÇüéâäàåçêëèïîìÄÅÉæÆôöòûùÿÖÜø£Ø×ƒáíóúñÑªº¿®¬½¼¡«»░▒▓│┤╡╢╖╕╣║╗╝╜╛┐└┴┬├─┼╞╟╚╔╩╦╠═╬╧╨╤╥╙╘╒╓╫╪┘┌█▄▌▐▀αβΓπΣσμτΦΘΩδ∞∅∈∩≡±≥≤⌠⌡÷≈°∙·√ⁿ²■\u0000"
                           .length()
                     );
                  character = "ÀÁÂÈÊËÍÓÔÕÚßãõğİıŒœŞşŴŵžȇ\u0000\u0000\u0000\u0000\u0000\u0000\u0000 !\"#$%&'()*+,-./0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[\\]^_`abcdefghijklmnopqrstuvwxyz{|}~\u0000ÇüéâäàåçêëèïîìÄÅÉæÆôöòûùÿÖÜø£Ø×ƒáíóúñÑªº¿®¬½¼¡«»░▒▓│┤╡╢╖╕╣║╗╝╜╛┐└┴┬├─┼╞╟╚╔╩╦╠═╬╧╨╤╥╙╘╒╓╫╪┘┌█▄▌▐▀αβΓπΣσμτΦΘΩδ∞∅∈∩≡±≥≤⌠⌡÷≈°∙·√ⁿ²■\u0000"
                     .charAt(count4);
               } while (count5 != this.computeCount(character));
            }

            float factor8 = count4 != -1 && !this.state ? this.factor : 0.5F;
            float factor9 = this.computeFactor(character, this.enabled5, worldRenderer, count4);
            if (this.enabled4) {
               this.factor4 += factor8;
               this.computeFactor(character, this.enabled5, worldRenderer, count4);
               this.factor4 -= factor8;
               factor9 += factor8;
            }

            if (this.enabled) {
               float factor10 = this.factor4;
               float factor11 = this.factor6 + (float)(this.indexOffset / 2);
               float factor12 = this.factor4 + factor9;
               float factor13 = this.factor6 + (float)(this.indexOffset / 2) - 1.0F;
               GL11.glDisable(3553);
               this.applyFloat(this.factor3, this.factor2, this.factor7, this.factor5);
               GL11.glBegin(7);
               GL11.glVertex2d((double)factor10, (double)factor11);
               GL11.glVertex2d((double)factor12, (double)factor11);
               GL11.glVertex2d((double)factor12, (double)factor13);
               GL11.glVertex2d((double)factor10, (double)factor13);
               GL11.glEnd();
               GL11.glEnable(3553);
            }

            if (this.enabled2) {
               float factor14 = this.factor4 - 1.0F;
               float factor15 = this.factor6 + (float)this.indexOffset;
               float factor16 = this.factor4 + factor9;
               float factor17 = this.factor6 + (float)this.indexOffset - 1.0F;
               GL11.glDisable(3553);
               this.applyFloat(this.factor3, this.factor2, this.factor7, this.factor5);
               GL11.glBegin(7);
               GL11.glVertex2d((double)factor14, (double)factor15);
               GL11.glVertex2d((double)factor16, (double)factor15);
               GL11.glVertex2d((double)factor16, (double)factor17);
               GL11.glVertex2d((double)factor14, (double)factor17);
               GL11.glEnd();
               GL11.glEnable(3553);
            }

            this.factor4 += factor9;
         }
      }
   }

   private int computeCount(String text, int count2, int count3, int count4, int count5, boolean flag) {
      int count6 = count2;
      if (this.state1) {
         int index = this.getIndex(this.resolveText3(text));
         count6 = count2 + count4 - index;
      }

      String text2 = this.resolveText2(text);
      this.applyResourceLocationByResourceLocation(this.resourceLocation2);
      Tessellator tessellator = Tessellator.getTessellator();
      WorldRenderer worldRenderer = tessellator.getWorldRenderer();
      worldRenderer.updateAuxiliaryLoggerEntry(7, DefaultVertexFormats.auxiliaryLoggerEntry6);
      int count7 = flag
         ? this.computeCount(text2, (float)count6, (float)count3, count5, worldRenderer, false)
         : this.computeCountByText(text2, (float)count6, (float)count3, count5, worldRenderer, false);
      tessellator.updateState();
      return count7;
   }

   public boolean isState1() {
      return this.state1;
   }

   private int computeCount2(String text, int count2) {
      int length = text.length();
      float factor8 = 0.0F;
      int count3 = 0;
      int count4 = -1;

      for (boolean flag = false; count3 < length; count3++) {
         char character = text.charAt(count3);
         switch (character) {
            case '\n':
               count3--;
               break;
            case ' ':
               count4 = count3;
            default:
               factor8 += (float)this.computeCount(character);
               if (flag) {
                  factor8++;
               }
               break;
            case '§':
               if (count3 < length - 1) {
                  char character2 = text.charAt(++count3);
                  if (character2 == 'l' || character2 == 'L') {
                     flag = true;
                  } else if (character2 == 'r' || character2 == 'R' || checkCharByChar(character2)) {
                     flag = false;
                  }
               }
         }

         if (character == '\n') {
            count4 = ++count3;
            break;
         }

         if (Math.round(factor8) > count2) {
            break;
         }
      }

      return count3 != length && count4 != -1 && count4 < count3 ? count4 : count3;
   }

   private int computeCount(String text, float factor8, float factor9, int count2, WorldRenderer worldRenderer, boolean flag) {
      String text2 = text;
      int count3 = count2;
      if (text == null) {
         return 0;
      } else {
         if (this.state1) {
            text2 = this.resolveText3(text);
         }

         if ((count2 & -67108864) == 0) {
            count3 = count2 | 0xFF000000;
         }

         count3 = (count3 & 16579836) >> 2 | count3 & 0xFF000000;
         this.factor3 = (float)(count3 >> 16 & 0xFF) / 255.0F;
         this.factor2 = (float)(count3 >> 8 & 0xFF) / 255.0F;
         this.factor7 = (float)(count3 & 0xFF) / 255.0F;
         this.factor5 = (float)(count3 >> 24 & 0xFF) / 255.0F;
         this.applyFloat(this.factor3, this.factor2, this.factor7, this.factor5);
         this.factor4 = factor8;
         this.factor6 = factor9;
         this.applyText(text2, worldRenderer, this.factor3, this.factor2, this.factor7, this.factor5, flag);
         return (int)this.factor4;
      }
   }

   private int computeCount(WorldRenderer worldRenderer, String text, float factor8, float factor9, int count2, int count3, float factor10) {
      if (text == null) {
         return 0;
      } else {
         this.factor4 = factor8;
         this.factor6 = factor9;
         this.applyTextByText(text, worldRenderer, count2, count3, factor10, false);
         return (int)this.factor4;
      }
   }

   private void applyTextByText(String text, WorldRenderer worldRenderer, int count2, int count3, float factor8, boolean flag) {
      boolean flag2 = ((Module)ClientGlobals.moduleManager.antiStrike).isToggled()
         && ClientGlobals.moduleManager.antiStrike.settingRemoveColorCodes.isEnabled()
         && Minecraft.getMinecraft().thePlayer != null
         && !flag;

      for (int index = 0; index < text.length(); index++) {
         char character = text.charAt(index);
         if (character == 167 && index + 1 < text.length()) {
            int count4 = this.computeCountByText(text, index + 1);
            if (count4 < 16) {
               this.enabled6 = false;
               this.enabled4 = false;
               this.enabled = false;
               this.enabled2 = false;
               this.enabled5 = false;
               if (count4 < 0 || count4 > 15) {
                  count4 = 15;
               }

               int count5 = this.countArray[count4];
               if (BuildManager.isActive33()) {
                  count5 = LoadingManager.computeCountByIndex(count4, count5);
               }

               this.count = count5;
               if (!flag2) {
                  this.factor3 = (float)(count5 >> 16) / 255.0F;
                  this.factor2 = (float)(count5 >> 8 & 0xFF) / 255.0F;
                  this.factor7 = (float)(count5 & 0xFF) / 255.0F;
               }
            } else if (count4 == 16) {
               this.enabled6 = !flag2;
            } else if (count4 == 17) {
               this.enabled4 = !flag2;
            } else if (count4 == 18) {
               this.enabled = !flag2;
            } else if (count4 == 19) {
               this.enabled2 = !flag2;
            } else if (count4 == 20) {
               this.enabled5 = !flag2;
            } else if (count4 == 21) {
               this.enabled6 = false;
               this.enabled4 = false;
               this.enabled = false;
               this.enabled2 = false;
               this.enabled5 = false;
            }

            index++;
         } else {
            float factor9 = 20.0F;
            float factor10 = ((float)index / factor9 / 2.0F % factor9 + factor8) % 2.0F;
            if (factor10 > 1.0F) {
               factor10 = 1.0F - factor10 % 1.0F;
            }

            int count6 = RandomUtil.processAction(count2, count3, factor10);
            this.factor3 = (float)(count6 >> 16 & 0xFF) / 255.0F;
            this.factor2 = (float)(count6 >> 8 & 0xFF) / 255.0F;
            this.factor7 = (float)(count6 & 0xFF) / 255.0F;
            this.factor5 = (float)(count6 >> 24 & 0xFF) / 255.0F;
            int count7 = character < countArray2.length ? countArray2[character] : -1;
            if (this.enabled6 && count7 != -1) {
               int count8 = this.computeCount(character);

               do {
                  count7 = this.random
                     .nextInt(
                        "ÀÁÂÈÊËÍÓÔÕÚßãõğİıŒœŞşŴŵžȇ\u0000\u0000\u0000\u0000\u0000\u0000\u0000 !\"#$%&'()*+,-./0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[\\]^_`abcdefghijklmnopqrstuvwxyz{|}~\u0000ÇüéâäàåçêëèïîìÄÅÉæÆôöòûùÿÖÜø£Ø×ƒáíóúñÑªº¿®¬½¼¡«»░▒▓│┤╡╢╖╕╣║╗╝╜╛┐└┴┬├─┼╞╟╚╔╩╦╠═╬╧╨╤╥╙╘╒╓╫╪┘┌█▄▌▐▀αβΓπΣσμτΦΘΩδ∞∅∈∩≡±≥≤⌠⌡÷≈°∙·√ⁿ²■\u0000"
                           .length()
                     );
                  character = "ÀÁÂÈÊËÍÓÔÕÚßãõğİıŒœŞşŴŵžȇ\u0000\u0000\u0000\u0000\u0000\u0000\u0000 !\"#$%&'()*+,-./0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[\\]^_`abcdefghijklmnopqrstuvwxyz{|}~\u0000ÇüéâäàåçêëèïîìÄÅÉæÆôöòûùÿÖÜø£Ø×ƒáíóúñÑªº¿®¬½¼¡«»░▒▓│┤╡╢╖╕╣║╗╝╜╛┐└┴┬├─┼╞╟╚╔╩╦╠═╬╧╨╤╥╙╘╒╓╫╪┘┌█▄▌▐▀αβΓπΣσμτΦΘΩδ∞∅∈∩≡±≥≤⌠⌡÷≈°∙·√ⁿ²■\u0000"
                     .charAt(count7);
               } while (count8 != this.computeCount(character));
            }

            float factor11 = count7 != -1 && !this.state ? this.factor : 0.5F;
            float factor12 = this.computeFactor(character, this.enabled5, worldRenderer, count7);
            if (this.enabled4) {
               this.factor4 += factor11;
               this.computeFactor(character, this.enabled5, worldRenderer, count7);
               this.factor4 -= factor11;
               factor12 += factor11;
            }

            if (this.enabled) {
               float factor13 = this.factor4;
               float factor14 = this.factor6 + (float)(this.indexOffset / 2);
               float factor15 = this.factor4 + factor12;
               float factor16 = this.factor6 + (float)(this.indexOffset / 2) - 1.0F;
               GL11.glDisable(3553);
               this.applyFloat(this.factor3, this.factor2, this.factor7, this.factor5);
               GL11.glBegin(7);
               GL11.glVertex2d((double)factor13, (double)factor14);
               GL11.glVertex2d((double)factor15, (double)factor14);
               GL11.glVertex2d((double)factor15, (double)factor16);
               GL11.glVertex2d((double)factor13, (double)factor16);
               GL11.glEnd();
               GL11.glEnable(3553);
            }

            if (this.enabled2) {
               float factor17 = this.factor4 - 1.0F;
               float factor18 = this.factor6 + (float)this.indexOffset;
               float factor19 = this.factor4 + factor12;
               float factor20 = this.factor6 + (float)this.indexOffset - 1.0F;
               GL11.glDisable(3553);
               this.applyFloat(this.factor3, this.factor2, this.factor7, this.factor5);
               GL11.glBegin(7);
               GL11.glVertex2d((double)factor17, (double)factor18);
               GL11.glVertex2d((double)factor19, (double)factor18);
               GL11.glVertex2d((double)factor19, (double)factor20);
               GL11.glVertex2d((double)factor17, (double)factor20);
               GL11.glEnd();
               GL11.glEnable(3553);
            }

            this.factor4 += factor12;
         }
      }
   }

   public String resolveTextByText(String text, int count2) {
      return this.resolveText(text, count2, false);
   }

   private float computeFactor(char character) {
      if (character == 167) {
         return -1.0F;
      } else if (character != ' ' && character != 160) {
         int count2 = character < countArray2.length ? countArray2[character] : -1;
         if (character > 0 && count2 != -1 && !this.state) {
            return this.factorArray[count2];
         } else if (this.byteFlagArray[character] != 0) {
            int count3 = this.byteFlagArray[character] >>> 4;
            int count4 = this.byteFlagArray[character] & 15;
            if (count4 > 7) {
               count4 = 15;
               count3 = 0;
            }

            count4++;
            return (float)((count4 - count3) / 2 + 1);
         } else {
            return 0.0F;
         }
      } else {
         return this.factorArray[32];
      }
   }

   public String resolveText(String text, int count2) {
      if (text.length() <= 1) {
         return text;
      } else {
         int count3 = this.computeCount2(text, count2);
         if (text.length() <= count3) {
            return text;
         } else {
            String text2 = text.substring(0, count3);
            char character = text.charAt(count3);
            boolean flag = character == ' ' || character == '\n';
            String text3 = resolveTextByText(text2) + text.substring(count3 + (flag ? 1 : 0));
            return text2 + "\n" + this.resolveText(text3, count2);
         }
      }
   }

   public void setState(boolean state2) {
      this.state = state2;
   }

   private int computeCount(WorldRenderer worldRenderer, String text, float factor8, float factor9, int count2, int count3, float factor10, boolean flag) {
      String text2 = text;
      if (text == null) {
         return 0;
      } else {
         if (this.state1) {
            text2 = this.resolveText3(text);
         }

         int count4 = (count2 & 16579836) >> 2 | count2 & 0xFF000000;
         int count5 = (count3 & 16579836) >> 2 | count3 & 0xFF000000;
         this.applyFloat(this.factor3, this.factor2, this.factor7, this.factor5);
         this.factor4 = factor8;
         this.factor6 = factor9;
         this.applyText(text2, worldRenderer, count4, count5, factor10, flag);
         return (int)this.factor4;
      }
   }

   public int computeCount(String text, int count2) {
      return this.indexOffset * this.getList(text, count2).size();
   }

   private void renderElement3() {
      InputStream inputStream = null;

      try {
         inputStream = this.resolveInputStream(new ResourceLocation("font/glyph_sizes.bin"));
         inputStream.read(this.byteFlagArray);
      } catch (IOException ioException) {
         throw new RuntimeException(ioException);
      } finally {
         IOUtils.closeQuietly(inputStream);
      }
   }

   public void applyText(String text, int count2, int count3, int count4, int count5) {
      if (this.enabled3) {
         GlStateManager.applyEnabledManager(this.enabledManager);
         GlStateManager.disableBlend();
         GlStateManager.glStateAction3(770, 771);
      }

      this.renderElement();
      this.count = count5;
      String text2 = this.resolveText(text);
      this.applyText(text2, count2, count3, count4, false);
      if (this.enabled3) {
         GlStateManager.applyEnabledManagerByEnabledManager(this.enabledManager);
      }
   }

   public int getIndex(String text, float factor8, float factor9, int count2, boolean flag, boolean flag2, boolean flag3) {
      String text2 = text;
      this.renderElement2();
      this.renderElement();
      if (flag2) {
         text2 = this.resolveText2(text);
      }

      this.applyResourceLocationByResourceLocation(this.resourceLocation2);
      Tessellator tessellator = Tessellator.getTessellator();
      WorldRenderer worldRenderer = tessellator.getWorldRenderer();
      worldRenderer.updateAuxiliaryLoggerEntry(7, DefaultVertexFormats.auxiliaryLoggerEntry6);
      int count3;
      if (flag) {
         count3 = Math.max(this.computeCount(text2, factor8 + 1.0F, factor9 + 1.0F, count2, worldRenderer, flag3), this.computeCountByText(text2, factor8, factor9, count2, worldRenderer, flag3));
      } else {
         count3 = this.computeCountByText(text2, factor8, factor9, count2, worldRenderer, flag3);
      }

      tessellator.updateState();
      return count3;
   }

   public static String resolveTextByText(String text) {
      StringBuilder builder = new StringBuilder();
      int count2 = -1;
      int length = text.length();

      while ((count2 = text.indexOf(167, count2 + 1)) != -1) {
         if (count2 < length - 1) {
            char character = text.charAt(count2 + 1);
            if (checkCharByChar(character)) {
               builder = new StringBuilder("§" + character);
            } else if (checkChar(character)) {
               builder.append("§").append(character);
            }
         }
      }

      return builder.toString();
   }

   public void applyFloat(float posX, float posX2, float posY, float posZ) {
      GlStateManager.color4f2(posX, posX2, posY, posZ);
   }

   private float computeFactor(int count2, boolean flag, WorldRenderer worldRenderer) {
      int count3 = count2 % 16 * 8;
      int count4 = count2 / 16 * 8;
      int count5 = flag ? 1 : 0;
      float factor8 = this.factorArray[count2];
      float factor9 = 7.99F;
      worldRenderer.getWorldRenderer((double)(this.factor4 + (float)count5), (double)this.factor6, 0.0)
         .getWorldRenderer6((double)((float)count3 / 128.0F), (double)((float)count4 / 128.0F))
         .resolveWorldRenderer(this.factor3, this.factor2, this.factor7, this.factor5)
         .updateState();
      worldRenderer.getWorldRenderer((double)(this.factor4 - (float)count5), (double)(this.factor6 + 7.99F), 0.0)
         .getWorldRenderer6((double)((float)count3 / 128.0F), (double)(((float)count4 + 7.99F) / 128.0F))
         .resolveWorldRenderer(this.factor3, this.factor2, this.factor7, this.factor5)
         .updateState();
      worldRenderer.getWorldRenderer((double)(this.factor4 + factor9 - 1.0F - (float)count5), (double)(this.factor6 + 7.99F), 0.0)
         .getWorldRenderer6((double)(((float)count3 + factor9 - 1.0F) / 128.0F), (double)(((float)count4 + 7.99F) / 128.0F))
         .resolveWorldRenderer(this.factor3, this.factor2, this.factor7, this.factor5)
         .updateState();
      worldRenderer.getWorldRenderer((double)(this.factor4 + factor9 - 1.0F + (float)count5), (double)this.factor6, 0.0)
         .getWorldRenderer6((double)(((float)count3 + factor9 - 1.0F) / 128.0F), (double)((float)count4 / 128.0F))
         .resolveWorldRenderer(this.factor3, this.factor2, this.factor7, this.factor5)
         .updateState();
      return factor8;
   }

   public int computeCount(String text, float factor8, float factor9, int count2, boolean flag) {
      return this.getIndex(text, factor8, factor9, count2, flag, true, false);
   }

   public List getList(String text, int count2) {
      return Arrays.asList(this.resolveText(text, count2).split("\n"));
   }

   public int computeCount(String text) {
      if (text == null) {
         return 0;
      } else {
         float factor8 = 0.0F;
         boolean flag = false;

         for (int index = 0; index < text.length(); index++) {
            char character = text.charAt(index);
            float factor9 = this.computeFactor(character);
            if (factor9 < 0.0F && index < text.length() - 1) {
               char character2 = text.charAt(++index);
               if (character2 == 'l' || character2 == 'L') {
                  flag = true;
               } else if (character2 == 'r' || character2 == 'R') {
                  flag = false;
               }

               factor9 = 0.0F;
            }

            factor8 += factor9;
            if (flag && factor9 > 0.0F) {
               factor8 += this.state ? 1.0F : this.factor;
            }
         }

         return Math.round(factor8);
      }
   }

   public int getIndex(String text, int count2, int count3, int count4) {
      return this.computeCount(text, (float)count2, (float)count3, count4, false, false);
   }

   public FontRenderer(GameSettings gameSettings2, ResourceLocation location, TextureManager textureManager2, boolean flag) {
      this.gameSettings = gameSettings2;
      this.resourceLocation = location;
      this.resourceLocation2 = location;
      this.textureManager = textureManager2;
      this.state = flag;
      this.resourceLocation2 = AuxiliaryTexturesManager.resolveResourceLocation(this.resourceLocation);
      this.applyResourceLocationByResourceLocation(this.resourceLocation2);

      for (int index = 0; index < 32; index++) {
         int count2 = (index >> 3 & 1) * 85;
         int count3 = (index >> 2 & 1) * 170 + count2;
         int count4 = (index >> 1 & 1) * 170 + count2;
         int count5 = (index >> 0 & 1) * 170 + count2;
         if (index == 6) {
            count3 += 85;
         }

         if (gameSettings2.state105) {
            int count6 = (count3 * 30 + count4 * 59 + count5 * 11) / 100;
            count4 = (count3 * 30 + count4 * 70) / 100;
            count5 = (count3 * 30 + count5 * 70) / 100;
            count3 = count6;
         }

         if (index >= 16) {
            count3 /= 4;
            count4 /= 4;
            count5 /= 4;
         }

         this.countArray[index] = (count3 & 0xFF) << 16 | (count4 & 0xFF) << 8 | count5 & 0xFF;
      }

      this.renderElement3();
   }

   static {
      Arrays.fill(countArray4, -1);
      String text = "0123456789abcdefklmnor";
      int count2 = 0;

      while (count2 < text.length()) {
         char character = text.charAt(count2);
         countArray4[character] = count2;
         countArray4[Character.toLowerCase(character)] = count2++;
      }

      char character2 = 0;

      for (int index = 0;
         index
            < "ÀÁÂÈÊËÍÓÔÕÚßãõğİıŒœŞşŴŵžȇ\u0000\u0000\u0000\u0000\u0000\u0000\u0000 !\"#$%&'()*+,-./0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[\\]^_`abcdefghijklmnopqrstuvwxyz{|}~\u0000ÇüéâäàåçêëèïîìÄÅÉæÆôöòûùÿÖÜø£Ø×ƒáíóúñÑªº¿®¬½¼¡«»░▒▓│┤╡╢╖╕╣║╗╝╜╛┐└┴┬├─┼╞╟╚╔╩╦╠═╬╧╨╤╥╙╘╒╓╫╪┘┌█▄▌▐▀αβΓπΣσμτΦΘΩδ∞∅∈∩≡±≥≤⌠⌡÷≈°∙·√ⁿ²■\u0000"
               .length();
         index++
      ) {
         char character3 = "ÀÁÂÈÊËÍÓÔÕÚßãõğİıŒœŞşŴŵžȇ\u0000\u0000\u0000\u0000\u0000\u0000\u0000 !\"#$%&'()*+,-./0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[\\]^_`abcdefghijklmnopqrstuvwxyz{|}~\u0000ÇüéâäàåçêëèïîìÄÅÉæÆôöòûùÿÖÜø£Ø×ƒáíóúñÑªº¿®¬½¼¡«»░▒▓│┤╡╢╖╕╣║╗╝╜╛┐└┴┬├─┼╞╟╚╔╩╦╠═╬╧╨╤╥╙╘╒╓╫╪┘┌█▄▌▐▀αβΓπΣσμτΦΘΩδ∞∅∈∩≡±≥≤⌠⌡÷≈°∙·√ⁿ²■\u0000"
            .charAt(index);
         if (character3 > character2) {
            character2 = character3;
         }
      }

      countArray2 = new int[character2 + 1];
      Arrays.fill(countArray2, -1);

      for (int index2 = 0;
         index2
            < "ÀÁÂÈÊËÍÓÔÕÚßãõğİıŒœŞşŴŵžȇ\u0000\u0000\u0000\u0000\u0000\u0000\u0000 !\"#$%&'()*+,-./0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[\\]^_`abcdefghijklmnopqrstuvwxyz{|}~\u0000ÇüéâäàåçêëèïîìÄÅÉæÆôöòûùÿÖÜø£Ø×ƒáíóúñÑªº¿®¬½¼¡«»░▒▓│┤╡╢╖╕╣║╗╝╜╛┐└┴┬├─┼╞╟╚╔╩╦╠═╬╧╨╤╥╙╘╒╓╫╪┘┌█▄▌▐▀αβΓπΣσμτΦΘΩδ∞∅∈∩≡±≥≤⌠⌡÷≈°∙·√ⁿ²■\u0000"
               .length();
         index2++
      ) {
         char character4 = "ÀÁÂÈÊËÍÓÔÕÚßãõğİıŒœŞşŴŵžȇ\u0000\u0000\u0000\u0000\u0000\u0000\u0000 !\"#$%&'()*+,-./0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[\\]^_`abcdefghijklmnopqrstuvwxyz{|}~\u0000ÇüéâäàåçêëèïîìÄÅÉæÆôöòûùÿÖÜø£Ø×ƒáíóúñÑªº¿®¬½¼¡«»░▒▓│┤╡╢╖╕╣║╗╝╜╛┐└┴┬├─┼╞╟╚╔╩╦╠═╬╧╨╤╥╙╘╒╓╫╪┘┌█▄▌▐▀αβΓπΣσμτΦΘΩδ∞∅∈∩≡±≥≤⌠⌡÷≈°∙·√ⁿ²■\u0000"
            .charAt(index2);
         if (countArray2[character4] == -1) {
            countArray2[character4] = index2;
         }
      }

      resourceLocationArray = new ResourceLocation[256];
   }
}
