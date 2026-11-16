package client.gui;

import client.render.WorldRenderer;
import client.util.CharArrayHolder;
import client.util.DefaultVertexFormats;
import client.util.InterpolationValidator;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import java.util.function.Consumer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.MathHelper;

public class InternalTexturesGuiIconsPngManagerAdapter extends TexturesGuiIconsPngManager {
   public final int count12;
   public final FontRenderer fontRenderer;
   public int count7;
   public int count10;
   public final int count3;
   public final int count11;
   public String labelText = "";
   public int count5 = 32;
   public int count9;
   public boolean enabled4 = true;
   public boolean enabled = true;
   public boolean enabled2;
   public boolean enabled5 = true;
   public int count;
   public int count2;
   public int count4;
   public int count8 = 14737632;
   public int count6 = 7368816;
   public boolean enabled3 = true;
   public InterpolationValidator interpolationValidator;
   public Predicate predicate = Predicates.alwaysTrue();
   public Consumer consumer;

   public void setEnabled(boolean enabled6) {
      this.enabled = enabled6;
   }

   public void updateState() {
      if (this.isEnabled3()) {
         if (this.isEnabled4()) {
            processAction(this.count7 - 1, this.count10 - 1, this.count7 + this.count3 + 1, this.count10 + this.count11 + 1, -6250336);
            processAction(this.count7, this.count10, this.count7 + this.count3, this.count10 + this.count11, -16777216);
         }

         int count13 = this.enabled5 ? this.count8 : this.count6;
         int count14 = this.count2 - this.count;
         int count15 = this.count4 - this.count;
         String text = this.fontRenderer.resolveTextByText(this.labelText.substring(this.count), this.getCount());
         boolean flag = count14 >= 0 && count14 <= text.length();
         boolean flag2 = this.enabled2 && this.count9 / 6 % 2 == 0 && flag;
         int count16 = this.enabled4 ? this.count7 + 4 : this.count7;
         int count17 = this.enabled4 ? this.count10 + (this.count11 - 8) / 2 : this.count10;
         int count18 = count16;
         if (count15 > text.length()) {
            count15 = text.length();
         }

         if (text.length() > 0) {
            String text2 = flag ? text.substring(0, count14) : text;
            count18 = this.fontRenderer.getIndex2(text2, (float)count16, (float)count17, count13);
         }

         boolean flag3 = this.count2 < this.labelText.length() || this.labelText.length() >= this.getCount5();
         int count19 = count18;
         if (!flag) {
            count19 = count14 > 0 ? count16 + this.count3 : count16;
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
            this.applyIndex2(count19, count17 - 1, count20 - 1, count17 + 1 + this.fontRenderer.indexOffset);
         }
      }
   }

   public int getCount12() {
      return this.count12;
   }

   public boolean isEnabled4() {
      return this.enabled4;
   }

   public void setInterpolationValidator(InterpolationValidator interpolationValidator2) {
      this.interpolationValidator = interpolationValidator2;
   }

   public int computeCount(int count13, int count14, boolean flag) {
      int count15 = count14;
      boolean flag2 = count13 < 0;
      int count16 = Math.abs(count13);

      for (int index = 0; index < count16; index++) {
         if (!flag2) {
            int length = this.labelText.length();
            count15 = this.labelText.indexOf(32, count15);
            if (count15 == -1) {
               count15 = length;
            } else {
               while (flag && count15 < length && this.labelText.charAt(count15) == ' ') {
                  count15++;
               }
            }
         } else {
            while (flag && count15 > 0 && this.labelText.charAt(count15 - 1) == ' ') {
               count15--;
            }

            while (count15 > 0 && this.labelText.charAt(count15 - 1) != ' ') {
               count15--;
            }
         }
      }

      return count15;
   }

   public void renderElement2() {
      this.applyIndexByIndex(this.labelText.length());
   }

   public void applyIndexByIndex(int count13) {
      this.count2 = count13;
      int length = this.labelText.length();
      this.count2 = MathHelper.getInt12(this.count2, 0, length);
      this.applyIndex4(this.count2);
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

      if (count19 > this.count7 + this.count3) {
         count19 = this.count7 + this.count3;
      }

      if (count17 > this.count7 + this.count3) {
         count17 = this.count7 + this.count3;
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

   public void setConsumer(Consumer consumer2) {
      this.consumer = consumer2;
   }

   public void applyIndex(int count13) {
      this.applyIndexByIndex(this.count4 + count13);
   }

   public void setCount6(int count13) {
      this.count6 = count13;
   }

   public void setEnabled3(boolean enabled6) {
      this.enabled3 = enabled6;
   }

   public int getCount11() {
      return this.count11;
   }

   public void renderElement() {
      this.count9++;
   }

   public boolean isEnabled3() {
      return this.enabled3;
   }

   public void applyIndex3(int count13) {
      this.count5 = count13;
      if (this.labelText.length() > count13) {
         this.labelText = this.labelText.substring(0, count13);
      }
   }

   public void setPredicate(Predicate predicate2) {
      this.predicate = predicate2;
   }

   public void setCount8(int count13) {
      this.count8 = count13;
   }

   public void setEnabled5(boolean enabled6) {
      this.enabled5 = enabled6;
   }

   public void applyText(String text) {
      if (this.predicate.apply(text)) {
         if (this.consumer != null) {
            this.consumer.accept(text);
         }

         if (text.length() > this.count5) {
            this.labelText = text.substring(0, this.count5);
         } else {
            this.labelText = text;
         }

         this.renderElement2();
      }
   }

   public String getLabelText() {
      return this.labelText;
   }

   public int computeCount(int count13, int count14) {
      return this.computeCount(count13, count14, true);
   }

   public int getCount4() {
      return this.count4;
   }

   public void setEnabled4(boolean enabled6) {
      this.enabled4 = enabled6;
   }

   public int getCount() {
      return this.isEnabled4() ? this.count3 - 8 : this.count3;
   }

   public void applyIndex4(int count13) {
      int count14 = count13;
      int length = this.labelText.length();
      if (count13 > length) {
         count14 = length;
      }

      if (count14 < 0) {
         count14 = 0;
      }

      this.count4 = count14;
      if (this.fontRenderer != null) {
         if (this.count > length) {
            this.count = length;
         }

         int count15 = this.getCount();
         int count16 = this.fontRenderer.resolveTextByText(this.labelText.substring(this.count), count15).length() + this.count;
         if (count14 == this.count) {
            this.count = this.count - this.fontRenderer.resolveText(this.labelText, count15, true).length();
         }

         if (count14 > count16) {
            this.count += count14 - count16;
         } else if (count14 <= this.count) {
            this.count = this.count - (this.count - count14);
         }

         this.count = MathHelper.getInt12(this.count, 0, length);
      }
   }

   public boolean checkChar(char character, int count13) {
      if (!this.enabled2) {
         return false;
      } else if (GuiScreen.checkIndexByIndex(count13)) {
         this.renderElement2();
         this.applyIndex4(0);
         return true;
      } else if (GuiScreen.checkIndex(count13)) {
         GuiScreen.applyText4(this.getText());
         return true;
      } else if (GuiScreen.checkIndex3(count13)) {
         if (this.enabled5) {
            this.applyTextByText(GuiScreen.getText());
         }

         return true;
      } else if (GuiScreen.checkIndex2(count13)) {
         GuiScreen.applyText4(this.getText());
         if (this.enabled5) {
            this.applyTextByText("");
         }

         return true;
      } else {
         switch (count13) {
            case 14:
               if (GuiScreen.isActive3()) {
                  if (this.enabled5) {
                     this.applyIndex5(-1);
                  }
               } else if (this.enabled5) {
                  this.applyIndex2(-1);
               }

               return true;
            case 199:
               if (GuiScreen.isActiveAsFlag()) {
                  this.applyIndex4(0);
               } else {
                  this.renderElement3();
               }

               return true;
            case 203:
               if (GuiScreen.isActiveAsFlag()) {
                  if (GuiScreen.isActive3()) {
                     this.applyIndex4(this.computeCount(-1, this.getCount4()));
                  } else {
                     this.applyIndex4(this.getCount4() - 1);
                  }
               } else if (GuiScreen.isActive3()) {
                  this.applyIndexByIndex(this.computeCount(-1));
               } else {
                  this.applyIndex(-1);
               }

               return true;
            case 205:
               if (GuiScreen.isActiveAsFlag()) {
                  if (GuiScreen.isActive3()) {
                     this.applyIndex4(this.computeCount(1, this.getCount4()));
                  } else {
                     this.applyIndex4(this.getCount4() + 1);
                  }
               } else if (GuiScreen.isActive3()) {
                  this.applyIndexByIndex(this.computeCount(1));
               } else {
                  this.applyIndex(1);
               }

               return true;
            case 207:
               if (GuiScreen.isActiveAsFlag()) {
                  this.applyIndex4(this.labelText.length());
               } else {
                  this.renderElement2();
               }

               return true;
            case 211:
               if (GuiScreen.isActive3()) {
                  if (this.enabled5) {
                     this.applyIndex5(1);
                  }
               } else if (this.enabled5) {
                  this.applyIndex2(1);
               }

               return true;
            default:
               if (CharArrayHolder.checkChar(character)) {
                  if (this.enabled5) {
                     this.applyTextByText(Character.toString(character));
                  }

                  return true;
               } else {
                  return false;
               }
         }
      }
   }

   public int computeCount(int count13) {
      return this.computeCount(count13, this.getCount2());
   }

   public boolean isEnabled2() {
      return this.enabled2;
   }

   public int getCount5() {
      return this.count5;
   }

   public void setEnabledByFlag(boolean enabledByFlag) {
      if (enabledByFlag && !this.enabled2) {
         this.count9 = 0;
      }

      this.enabled2 = enabledByFlag;
   }

   public void applyIndex(int count13, int count14, int count15) {
      boolean flag = count13 >= this.count7 && count13 < this.count7 + this.count3 && count14 >= this.count10 && count14 < this.count10 + this.count11;
      if (this.enabled) {
         this.setEnabledByFlag(flag);
      }

      if (this.enabled2 && flag && count15 == 0) {
         int count16 = count13 - this.count7;
         if (this.enabled4) {
            count16 -= 4;
         }

         String text = this.fontRenderer.resolveTextByText(this.labelText.substring(this.count), this.getCount());
         this.applyIndexByIndex(this.fontRenderer.resolveTextByText(text, count16).length() + this.count);
      }
   }

   public void applyIndex5(int count13) {
      if (this.labelText.length() != 0) {
         if (this.count4 != this.count2) {
            this.applyTextByText("");
         } else {
            this.applyIndex2(this.computeCount(count13) - this.count2);
         }
      }
   }

   public String getText() {
      int count13 = this.count2 < this.count4 ? this.count2 : this.count4;
      int count14 = this.count2 < this.count4 ? this.count4 : this.count2;
      return this.labelText.substring(count13, count14);
   }

   public int getCount2() {
      return this.count2;
   }

   public void applyIndex2(int count13) {
      if (this.labelText.length() != 0) {
         if (this.count4 != this.count2) {
            this.applyTextByText("");
         } else {
            boolean flag = count13 < 0;
            int count14 = flag ? this.count2 + count13 : this.count2;
            int count15 = flag ? this.count2 : this.count2 + count13;
            String text = "";
            if (count14 >= 0) {
               text = this.labelText.substring(0, count14);
            }

            if (count15 < this.labelText.length()) {
               text = text + this.labelText.substring(count15);
            }

            if (this.predicate.apply(text)) {
               this.labelText = text;
               if (flag) {
                  this.applyIndex(count13);
               }

               if (this.interpolationValidator != null) {
                  this.interpolationValidator.applyText(this.count12, this.labelText);
               }
            }
         }
      }
   }

   public void renderElement3() {
      this.applyIndexByIndex(0);
   }

   public void applyTextByText(String text) {
      String text2 = "";
      String text3 = CharArrayHolder.resolveText(text);
      int count13 = this.count2 < this.count4 ? this.count2 : this.count4;
      int count14 = this.count2 < this.count4 ? this.count4 : this.count2;
      int count15 = this.count5 - this.labelText.length() - (count13 - count14);
      if (this.labelText.length() > 0) {
         text2 = text2 + this.labelText.substring(0, count13);
      }

      String text4;
      if (count15 < text3.length()) {
         text4 = text2 + text3.substring(0, count15);
      } else {
         text4 = text2 + text3;
         count15 = text3.length();
      }

      if (this.labelText.length() > 0 && count14 < this.labelText.length()) {
         text4 = text4 + this.labelText.substring(count14);
      }

      if (this.predicate.apply(text4)) {
         this.labelText = text4;
         this.applyIndex(count13 - this.count4 + count15);
         if (this.interpolationValidator != null) {
            this.interpolationValidator.applyText(this.count12, this.labelText);
         }
      }
   }

   public InternalTexturesGuiIconsPngManagerAdapter(int count13, FontRenderer fontRenderer2, int count14, int count15, int count16, int count17) {
      this.count12 = count13;
      this.fontRenderer = fontRenderer2;
      this.count7 = count14;
      this.count10 = count15;
      this.count3 = count16;
      this.count11 = count17;
      GuiScreen.set.add(Minecraft.getMinecraft().currentScreen);
   }
}
