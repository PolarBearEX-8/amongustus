package client.gui;

import client.render.WorldRenderer;
import client.util.CharArrayHolder;
import client.util.DefaultVertexFormats;
import client.util.InterpolationValidator;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.MathHelper;

public class TexturesGuiIconsPngManagerAdapter extends TexturesGuiIconsPngManager {
   public Predicate predicate;
   public final int count8;
   public boolean enabled2;
   public final int count6;
   public boolean enabled;
   public String labelText;
   public int count9;
   public int count;
   public int count3;
   public boolean enabled4;
   public final int count11;
   public int count2;
   public boolean enabled3;
   public int count7;
   public int count4;
   public int count5;
   public int count12;
   public InterpolationValidator interpolationValidator;
   public final FontRenderer fontRenderer;
   public int count10;
   public boolean enabled5;
   public String labelText2 = "";

   public boolean isEnabled4() {
      return this.enabled4;
   }

   public void setCount12(int count13) {
      this.count12 = count13;
   }

   public void setEnabled3(boolean enabled6) {
      this.enabled3 = enabled6;
   }

   public boolean checkChar(char character, int count13) {
      if (!this.enabled5) {
         return false;
      } else if (GuiScreen.checkIndexByIndex(count13)) {
         this.renderElement2();
         this.applyIndex2(0);
         return true;
      } else if (GuiScreen.checkIndex(count13)) {
         GuiScreen.applyText4(this.getText());
         return true;
      } else if (GuiScreen.checkIndex3(count13)) {
         if (this.enabled3) {
            String text = GuiScreen.getText();
            String text2 = text;
            this.applyTextByText(text2);
         }

         return true;
      } else if (GuiScreen.checkIndex2(count13)) {
         GuiScreen.applyText4(this.getText());
         if (this.enabled3) {
            String text3 = "";
            this.applyTextByText(text3);
         }

         return true;
      } else {
         switch (count13) {
            case 14:
               if (GuiScreen.isActive3()) {
                  if (this.enabled3) {
                     this.applyIndex(-1);
                  }
               } else if (this.enabled3) {
                  this.applyIndex5(-1);
               }

               return true;
            case 199:
               if (GuiScreen.isActiveAsFlag()) {
                  this.applyIndex2(0);
               } else {
                  this.renderElement();
               }

               return true;
            case 203:
               if (GuiScreen.isActiveAsFlag()) {
                  if (GuiScreen.isActive3()) {
                     int count16 = this.getCount7();
                     byte byteValue = -1;
                     int count17 = this.computeCount(byteValue, count16);
                     int count18 = count17;
                     this.applyIndex2(count18);
                  } else {
                     int count19 = this.getCount7() - 1;
                     int count20 = count19;
                     this.applyIndex2(count20);
                  }
               } else if (GuiScreen.isActive3()) {
                  int count21 = this.computeCount(-1);
                  this.applyIndexByIndex(count21);
               } else {
                  byte byteValue2 = -1;
                  this.applyIndex4(byteValue2);
               }

               return true;
            case 205:
               if (GuiScreen.isActiveAsFlag()) {
                  if (GuiScreen.isActive3()) {
                     int count22 = this.getCount7();
                     byte byteValue3 = 1;
                     int count23 = this.computeCount(byteValue3, count22);
                     int count24 = count23;
                     this.applyIndex2(count24);
                  } else {
                     int count25 = this.getCount7() + 1;
                     int count26 = count25;
                     this.applyIndex2(count26);
                  }
               } else if (GuiScreen.isActive3()) {
                  int count27 = this.computeCount(1);
                  this.applyIndexByIndex(count27);
               } else {
                  byte byteValue4 = 1;
                  this.applyIndex4(byteValue4);
               }

               return true;
            case 207:
               if (GuiScreen.isActiveAsFlag()) {
                  int length = this.labelText2.length();
                  int count28 = length;
                  this.applyIndex2(count28);
               } else {
                  this.renderElement2();
               }

               return true;
            case 211:
               if (GuiScreen.isActive3()) {
                  if (this.enabled3) {
                     this.applyIndex(1);
                  }
               } else if (this.enabled3) {
                  this.applyIndex5(1);
               }

               return true;
            default:
               if (CharArrayHolder.checkChar(character)) {
                  if (this.enabled3) {
                     String text4 = Character.toString(character);
                     String text5 = text4;
                     this.applyTextByText(text5);
                  }

                  return true;
               } else {
                  return false;
               }
         }
      }
   }

   public void applyIndexByIndex(int count13) {
      this.count9 = count13;
      int length = this.labelText2.length();
      this.count9 = MathHelper.getInt12(this.count9, 0, length);
      int count15 = this.count9;
      this.applyIndex2(count15);
   }

   public void renderElement() {
      this.applyIndexByIndex(0);
   }

   public int getCount6() {
      return this.count6;
   }

   public String getText() {
      int count13 = this.count9 < this.count7 ? this.count9 : this.count7;
      int count14 = this.count9 < this.count7 ? this.count7 : this.count9;
      return this.labelText2.substring(count13, count14);
   }

   public void setPredicate(Predicate predicate2) {
      this.predicate = predicate2;
   }

   public void applyIndex(int count13, int count14, int count15) {
      boolean flag = count13 >= this.count2 && count13 < this.count2 + this.count11 && count14 >= this.count3 && count14 < this.count3 + this.count8;
      if (this.enabled) {
         this.setEnabledByFlag(flag);
      }

      if (this.enabled5 && flag && count15 == 0) {
         int count16 = count13 - this.count2;
         if (this.enabled4) {
            count16 -= 4;
         }

         String text = this.fontRenderer.resolveTextByText(this.labelText2.substring(this.count5), this.getCount());
         int count17 = this.fontRenderer.resolveTextByText(text, count16).length() + this.count5;
         this.applyIndexByIndex(count17);
      }
   }

   public int computeCount(int count13, int count14) {
      return this.computeCount(count13, count14, true);
   }

   private void applyIndex2(int count13, int count14, int count15, int count16) {
      int count17 = count13;
      int count18 = count14;
      int count19 = count15;
      int count20 = count16;
      if (count13 < count15) {
         count17 = count15;
         count19 = count13;
      }

      if (count14 < count16) {
         count18 = count16;
         count20 = count14;
      }

      if (count19 > this.count2 + this.count11) {
         count19 = this.count2 + this.count11;
      }

      if (count17 > this.count2 + this.count11) {
         count17 = this.count2 + this.count11;
      }

      Tessellator tessellator = Tessellator.getTessellator();
      WorldRenderer worldRenderer = tessellator.getWorldRenderer();
      GlStateManager.color4f2(0.0F, 0.0F, 255.0F, 255.0F);
      GlStateManager.enableTexture2D();
      GlStateManager.applyState9();
      GlStateManager.glLogicOp(5387);
      worldRenderer.updateAuxiliaryLoggerEntry(7, DefaultVertexFormats.auxiliaryLoggerEntry1);
      worldRenderer.getWorldRenderer((double)count17, (double)count20, 0.0).updateState();
      worldRenderer.getWorldRenderer((double)count19, (double)count20, 0.0).updateState();
      worldRenderer.getWorldRenderer((double)count19, (double)count18, 0.0).updateState();
      worldRenderer.getWorldRenderer((double)count17, (double)count18, 0.0).updateState();
      tessellator.updateState();
      GlStateManager.applyState31();
      GlStateManager.disableTexture2D();
   }

   public boolean isEnabled2() {
      return this.enabled2;
   }

   public int getCount() {
      return this.isEnabled4() ? this.count11 - 8 : this.count11;
   }

   public void applyIndex(int count13) {
      if (this.labelText2.length() != 0) {
         if (this.count7 != this.count9) {
            String text = "";
            this.applyTextByText(text);
         } else {
            int count15 = this.computeCount(count13) - this.count9;
            int count16 = count15;
            this.applyIndex5(count16);
         }
      }
   }

   public void applyIndex3(int count13) {
      this.count = count13;
      if (this.labelText2.length() > count13) {
         this.labelText2 = this.labelText2.substring(0, count13);
         this.labelText = this.labelText.substring(0, count13);
      }
   }

   public void setEnabled(boolean enabled6) {
      this.enabled = enabled6;
   }

   public void renderElement2() {
      int length = this.labelText2.length();
      this.applyIndexByIndex(length);
   }

   public boolean isEnabled5() {
      return this.enabled5;
   }

   public int getCount9() {
      return this.count9;
   }

   public void setEnabled2(boolean enabled6) {
      this.enabled2 = enabled6;
   }

   public void applyTextByText(String text) {
      String text2 = "";
      String text3 = CharArrayHolder.resolveText(text);
      int count14 = this.count9 < this.count7 ? this.count9 : this.count7;
      int count15 = this.count9 < this.count7 ? this.count7 : this.count9;
      int count16 = this.count - this.labelText2.length() - (count14 - count15);
      if (this.labelText2.length() > 0) {
         text2 = text2 + this.labelText2.substring(0, count14);
      }

      String text4;
      if (count16 < text3.length()) {
         text4 = text2 + text3.substring(0, count16);
      } else {
         text4 = text2 + text3;
         count16 = text3.length();
      }

      if (this.labelText2.length() > 0 && count15 < this.labelText2.length()) {
         text4 = text4 + this.labelText2.substring(count15);
      }

      if (this.predicate.apply(text4)) {
         this.labelText2 = text4;
         this.labelText = text4;
         int count17 = count14 - this.count7 + count16;
         int count18 = count17;
         this.applyIndex4(count18);
         if (this.interpolationValidator != null) {
            this.interpolationValidator.applyText(this.count6, this.labelText2);
         }
      }
   }

   public String getLabelText2() {
      return this.labelText2;
   }

   public void setCount10(int count13) {
      this.count10 = count13;
   }

   public int getCountAsIndex() {
      return this.count;
   }

   public void setEnabledByFlag(boolean enabledByFlag) {
      if (enabledByFlag && !this.enabled5) {
         this.count4 = 0;
      }

      this.enabled5 = enabledByFlag;
   }

   public void applyIndex4(int count13) {
      int count14 = this.count7 + count13;
      this.applyIndexByIndex(count14);
   }

   public int computeCount(int count13, int count14, boolean flag) {
      int count15 = count14;
      boolean flag2 = count13 < 0;
      int count16 = Math.abs(count13);

      for (int index = 0; index < count16; index++) {
         if (!flag2) {
            int length = this.labelText2.length();
            count15 = this.labelText2.indexOf(32, count15);
            if (count15 == -1) {
               count15 = length;
            } else {
               while (flag && count15 < length && this.labelText2.charAt(count15) == ' ') {
                  count15++;
               }
            }
         } else {
            while (flag && count15 > 0 && this.labelText2.charAt(count15 - 1) == ' ') {
               count15--;
            }

            while (count15 > 0 && this.labelText2.charAt(count15 - 1) != ' ') {
               count15--;
            }
         }
      }

      return count15;
   }

   public void renderElement3() {
      this.labelText2 = "";

      for (int index = 0; index < this.labelText.length(); index++) {
         this.labelText2 = this.labelText2 + "*";
      }

      if (this.isEnabled2()) {
         if (this.isEnabled4()) {
            processAction(this.count2 - 1, this.count3 - 1, this.count2 + this.count11 + 1, this.count3 + this.count8 + 1, -6250336);
            processAction(this.count2, this.count3, this.count2 + this.count11, this.count3 + this.count8, -16777216);
         }

         int count13 = this.enabled3 ? this.count10 : this.count12;
         int count14 = this.count9 - this.count5;
         int count15 = this.count7 - this.count5;
         String text = this.fontRenderer.resolveTextByText(this.labelText2.substring(this.count5), this.getCount());
         boolean flag = count14 >= 0 && count14 <= text.length();
         boolean flag2 = this.enabled5 && this.count4 / 6 % 2 == 0 && flag;
         int count16 = this.enabled4 ? this.count2 + 4 : this.count2;
         int count17 = this.enabled4 ? this.count3 + (this.count8 - 8) / 2 : this.count3;
         int count18 = count16;
         if (count15 > text.length()) {
            count15 = text.length();
         }

         if (text.length() > 0) {
            String text2 = flag ? text.substring(0, count14) : text;
            count18 = this.fontRenderer.getIndex2(text2, (float)count16, (float)count17, count13);
         }

         boolean flag3 = this.count9 < this.labelText2.length() || this.labelText2.length() >= this.getCountAsIndex();
         int count19 = count18;
         if (!flag) {
            count19 = count14 > 0 ? count16 + this.count11 : count16;
         } else if (flag3) {
            count19 = count18 - 1;
            count18--;
         }

         if (text.length() > 0 && flag && count14 < text.length()) {
            this.fontRenderer.getIndex2(text.substring(count14), (float)count18, (float)count17, count13);
         }

         if (flag2) {
            if (flag3) {
               TexturesGuiIconsPngManager.processAction(count19, count17 - 1, count19 + 1, count17 + 1 + this.fontRenderer.indexOffset, -3092272);
            } else {
               this.fontRenderer.getIndex2("_", (float)count19, (float)count17, count13);
            }
         }

         if (count15 != count14) {
            int count20 = count16 + this.fontRenderer.getIndex(text.substring(0, count15));
            int count21 = count17 - 1;
            int count22 = count20 - 1;
            int count23 = count17 + 1 + this.fontRenderer.indexOffset;
            int count24 = count22;
            int count25 = count21;
            this.applyIndex2(count19, count25, count24, count23);
         }
      }

      this.labelText2 = this.labelText;
   }

   public int getCount7() {
      return this.count7;
   }

   public void applyIndex5(int count13) {
      if (this.labelText2.length() != 0) {
         if (this.count7 != this.count9) {
            String text = "";
            this.applyTextByText(text);
         } else {
            boolean flag = count13 < 0;
            int count14 = flag ? this.count9 + count13 : this.count9;
            int count15 = flag ? this.count9 : this.count9 + count13;
            String text2 = "";
            if (count14 >= 0) {
               text2 = this.labelText2.substring(0, count14);
            }

            if (count15 < this.labelText2.length()) {
               text2 = text2 + this.labelText2.substring(count15);
            }

            if (this.predicate.apply(text2)) {
               this.labelText2 = text2;
               this.labelText = text2;
               if (flag) {
                  this.applyIndex4(count13);
               }

               if (this.interpolationValidator != null) {
                  this.interpolationValidator.applyText(this.count6, this.labelText2);
               }
            }
         }
      }
   }

   public void renderElement4() {
      this.count4++;
   }

   public void applyText(String text) {
      if (this.predicate.apply(text)) {
         if (text.length() > this.count) {
            this.labelText2 = text.substring(0, this.count);
            this.labelText = text.substring(0, this.count);
         } else {
            this.labelText2 = text;
            this.labelText = text;
         }

         this.renderElement2();
      }
   }

   public void setInterpolationValidator(InterpolationValidator interpolationValidator2) {
      this.interpolationValidator = interpolationValidator2;
   }

   public void setEnabled4(boolean enabled6) {
      this.enabled4 = enabled6;
   }

   public int computeCount(int count13) {
      int count14 = this.getCount9();
      return this.computeCount(count13, count14);
   }

   public void applyIndex2(int count13) {
      int count14 = count13;
      int length = this.labelText2.length();
      if (count13 > length) {
         count14 = length;
      }

      if (count14 < 0) {
         count14 = 0;
      }

      this.count7 = count14;
      if (this.fontRenderer != null) {
         if (this.count5 > length) {
            this.count5 = length;
         }

         int count15 = this.getCount();
         int count16 = this.fontRenderer.resolveTextByText(this.labelText2.substring(this.count5), count15).length() + this.count5;
         if (count14 == this.count5) {
            this.count5 = this.count5 - this.fontRenderer.resolveText(this.labelText2, count15, true).length();
         }

         if (count14 > count16) {
            this.count5 += count14 - count16;
         } else if (count14 <= this.count5) {
            this.count5 = this.count5 - (this.count5 - count14);
         }

         this.count5 = MathHelper.getInt12(this.count5, 0, length);
      }
   }

   public TexturesGuiIconsPngManagerAdapter(int count13, FontRenderer fontRenderer2, int count14, int count15, int count16, int count17) {
      this.labelText = "";
      this.count = 32;
      this.enabled4 = true;
      this.enabled = true;
      this.enabled3 = true;
      this.count10 = 14737632;
      this.count12 = 7368816;
      this.enabled2 = true;
      this.predicate = Predicates.alwaysTrue();
      this.count6 = count13;
      this.fontRenderer = fontRenderer2;
      this.count2 = count14;
      this.count3 = count15;
      this.count11 = count16;
      this.count8 = count17;
   }
}
