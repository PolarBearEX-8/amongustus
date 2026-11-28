package net.minecraft.client.gui;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import java.util.function.Consumer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ChatAllowedCharacters;
import net.minecraft.util.MathHelper;

public class GuiTextField extends Gui {
   private final int id;
   private final FontRenderer fontRendererInstance;
   public int xPosition;
   public int yPosition;
   private final int width;
   private final int height;
   private String text = "";
   private int maxStringLength = 32;
   private int cursorCounter;
   private boolean enableBackgroundDrawing = true;
   private boolean canLoseFocus = true;
   private boolean isFocused;
   private boolean isEnabled = true;
   private int lineScrollOffset;
   private int cursorPosition;
   private int selectionEnd;
   private int textColor = 14737632;
   private int disabledTextColour = 7368816;
   private boolean visible = true;
   private GuiPageButtonList.GuiResponder guiResponder;
   private Predicate validator = Predicates.alwaysTrue();
   private Consumer textChangeListener;

   public int getMaxStringLength() {
      return this.maxStringLength;
   }

   public void writeText(String str) {
      String str2 = "";
      String str3 = ChatAllowedCharacters.filterAllowedCharacters(str);
      int n = this.cursorPosition < this.selectionEnd ? this.cursorPosition : this.selectionEnd;
      int n2 = this.cursorPosition < this.selectionEnd ? this.selectionEnd : this.cursorPosition;
      int n3 = this.maxStringLength - this.text.length() - (n - n2);
      int n4 = 0;
      if (this.text.length() > 0) {
         str2 = str2 + this.text.substring(0, n);
      }

      if (n3 < str3.length()) {
         str2 = str2 + str3.substring(0, n3);
         n4 = n3;
      } else {
         str2 = str2 + str3;
         n4 = str3.length();
      }

      if (this.text.length() > 0 && n2 < this.text.length()) {
         str2 = str2 + this.text.substring(n2);
      }

      if (this.validator.apply(str2)) {
         this.text = str2;
         this.moveCursorBy(n - this.selectionEnd + n4);
         if (this.guiResponder != null) {
            this.guiResponder.setEntryValue(this.id, this.text);
         }
      }
   }

   public int getWidth() {
      return this.getEnableBackgroundDrawing() ? this.width - 8 : this.width;
   }

   public String getText() {
      return this.text;
   }

   public void deleteWords(int n) {
      if (this.text.length() != 0) {
         if (this.selectionEnd != this.cursorPosition) {
            this.writeText("");
         } else {
            this.deleteFromCursor(this.getNthWordFromCursor(n) - this.cursorPosition);
         }
      }
   }

   public void setTextChangeListener(Consumer consumer) {
      this.textChangeListener = consumer;
   }

   public void setCursorPositionEnd() {
      this.setCursorPosition(this.text.length());
   }

   public void updateCursorCounter() {
      this.cursorCounter++;
   }

   public String getSelectedText() {
      int n = this.cursorPosition < this.selectionEnd ? this.cursorPosition : this.selectionEnd;
      int n2 = this.cursorPosition < this.selectionEnd ? this.selectionEnd : this.cursorPosition;
      return this.text.substring(n, n2);
   }

   public void setMaxStringLength(int n) {
      this.maxStringLength = n;
      if (this.text.length() > n) {
         this.text = this.text.substring(0, n);
      }
   }

   public void setTextColor(int n) {
      this.textColor = n;
   }

   public GuiTextField(int n, FontRenderer fontRenderer, int n2, int n3, int n4, int n5) {
      this.id = n;
      this.fontRendererInstance = fontRenderer;
      this.xPosition = n2;
      this.yPosition = n3;
      this.width = n4;
      this.height = n5;
      GuiScreen.NO_TOGGLE_SCREENS.add(Minecraft.getMinecraft().currentScreen);
   }

   public boolean isFocused() {
      return this.isFocused;
   }

   public void setCanLoseFocus(boolean flag) {
      this.canLoseFocus = flag;
   }

   public void mouseClicked(int mouseX, int mouseY, int mouseButton) {
      boolean flag = mouseX >= this.xPosition && mouseX < this.xPosition + this.width && mouseY >= this.yPosition && mouseY < this.yPosition + this.height;
      if (this.canLoseFocus) {
         this.setFocused(flag);
      }

      if (this.isFocused && flag && mouseButton == 0) {
         int n = mouseX - this.xPosition;
         if (this.enableBackgroundDrawing) {
            n -= 4;
         }

         String str = this.fontRendererInstance.trimStringToWidth(this.text.substring(this.lineScrollOffset), this.getWidth());
         this.setCursorPosition(this.fontRendererInstance.trimStringToWidth(str, n).length() + this.lineScrollOffset);
      }
   }

   public void setText(String str) {
      if (this.validator.apply(str)) {
         if (this.textChangeListener != null) {
            this.textChangeListener.accept(str);
         }

         if (str.length() > this.maxStringLength) {
            this.text = str.substring(0, this.maxStringLength);
         } else {
            this.text = str;
         }

         this.setCursorPositionEnd();
      }
   }

   public void setCursorPositionZero() {
      this.setCursorPosition(0);
   }

   public int getSelectionEnd() {
      return this.selectionEnd;
   }

   public void drawTextBox() {
      this.drawTextBoxWithShadowOption(true);
   }

   public void setFocused(boolean flag) {
      if (flag && !this.isFocused) {
         this.cursorCounter = 0;
      }

      this.isFocused = flag;
   }

   public void setSelectionPos(int n) {
      int length = this.text.length();
      if (n > length) {
         n = length;
      }

      if (n < 0) {
         n = 0;
      }

      this.selectionEnd = n;
      if (this.fontRendererInstance != null) {
         if (this.lineScrollOffset > length) {
            this.lineScrollOffset = length;
         }

         int width2 = this.getWidth();
         int n2 = this.fontRendererInstance.trimStringToWidth(this.text.substring(this.lineScrollOffset), width2).length() + this.lineScrollOffset;
         if (n == this.lineScrollOffset) {
            this.lineScrollOffset = this.lineScrollOffset - this.fontRendererInstance.trimStringToWidth(this.text, width2, true).length();
         }

         if (n > n2) {
            this.lineScrollOffset += n - n2;
         } else if (n <= this.lineScrollOffset) {
            this.lineScrollOffset = this.lineScrollOffset - (this.lineScrollOffset - n);
         }

         this.lineScrollOffset = MathHelper.clamp_int(this.lineScrollOffset, 0, length);
      }
   }

   public int getNthWordFromCursor(int n) {
      return this.getNthWordFromPos(n, this.getCursorPosition());
   }

   public void deleteFromCursor(int n) {
      if (this.text.length() != 0) {
         if (this.selectionEnd != this.cursorPosition) {
            this.writeText("");
         } else {
            boolean flag = n < 0;
            int n2 = flag ? this.cursorPosition + n : this.cursorPosition;
            int n3 = flag ? this.cursorPosition : this.cursorPosition + n;
            String str = "";
            if (n2 >= 0) {
               str = this.text.substring(0, n2);
            }

            if (n3 < this.text.length()) {
               str = str + this.text.substring(n3);
            }

            if (this.validator.apply(str)) {
               this.text = str;
               if (flag) {
                  this.moveCursorBy(n);
               }

               if (this.guiResponder != null) {
                  this.guiResponder.setEntryValue(this.id, this.text);
               }
            }
         }
      }
   }

   public void setEnableBackgroundDrawing(boolean flag) {
      this.enableBackgroundDrawing = flag;
   }

   public boolean textboxKeyTyped(char ch, int n) {
      if (!this.isFocused) {
         return false;
      } else if (GuiScreen.isKeyComboCtrlA(n)) {
         this.setCursorPositionEnd();
         this.setSelectionPos(0);
         return true;
      } else if (GuiScreen.isKeyComboCtrlC(n)) {
         GuiScreen.setClipboardString(this.getSelectedText());
         return true;
      } else if (GuiScreen.isKeyComboCtrlV(n)) {
         if (this.isEnabled) {
            this.writeText(GuiScreen.getClipboardString());
         }

         return true;
      } else if (GuiScreen.isKeyComboCtrlX(n)) {
         GuiScreen.setClipboardString(this.getSelectedText());
         if (this.isEnabled) {
            this.writeText("");
         }

         return true;
      } else {
         switch (n) {
            case 14:
               if (GuiScreen.isCtrlKeyDown()) {
                  if (this.isEnabled) {
                     this.deleteWords(-1);
                  }
               } else if (this.isEnabled) {
                  this.deleteFromCursor(-1);
               }

               return true;
            case 199:
               if (GuiScreen.isShiftKeyDown()) {
                  this.setSelectionPos(0);
               } else {
                  this.setCursorPositionZero();
               }

               return true;
            case 203:
               if (GuiScreen.isShiftKeyDown()) {
                  if (GuiScreen.isCtrlKeyDown()) {
                     this.setSelectionPos(this.getNthWordFromPos(-1, this.getSelectionEnd()));
                  } else {
                     this.setSelectionPos(this.getSelectionEnd() - 1);
                  }
               } else if (GuiScreen.isCtrlKeyDown()) {
                  this.setCursorPosition(this.getNthWordFromCursor(-1));
               } else {
                  this.moveCursorBy(-1);
               }

               return true;
            case 205:
               if (GuiScreen.isShiftKeyDown()) {
                  if (GuiScreen.isCtrlKeyDown()) {
                     this.setSelectionPos(this.getNthWordFromPos(1, this.getSelectionEnd()));
                  } else {
                     this.setSelectionPos(this.getSelectionEnd() + 1);
                  }
               } else if (GuiScreen.isCtrlKeyDown()) {
                  this.setCursorPosition(this.getNthWordFromCursor(1));
               } else {
                  this.moveCursorBy(1);
               }

               return true;
            case 207:
               if (GuiScreen.isShiftKeyDown()) {
                  this.setSelectionPos(this.text.length());
               } else {
                  this.setCursorPositionEnd();
               }

               return true;
            case 211:
               if (GuiScreen.isCtrlKeyDown()) {
                  if (this.isEnabled) {
                     this.deleteWords(1);
                  }
               } else if (this.isEnabled) {
                  this.deleteFromCursor(1);
               }

               return true;
            default:
               if (ChatAllowedCharacters.isAllowedCharacter(ch)) {
                  if (this.isEnabled) {
                     this.writeText(Character.toString(ch));
                  }

                  return true;
               } else {
                  return false;
               }
         }
      }
   }

   public boolean getEnableBackgroundDrawing() {
      return this.enableBackgroundDrawing;
   }

   public void setVisible(boolean flag) {
      this.visible = flag;
   }

   public void setValidator(Predicate predicate) {
      this.validator = predicate;
   }

   public int getCursorPosition() {
      return this.cursorPosition;
   }

   public void setGuiResponder(GuiPageButtonList.GuiResponder guiResponder2) {
      this.guiResponder = guiResponder2;
   }

   public void setEnabled(boolean flag) {
      this.isEnabled = flag;
   }

   public void drawTextBoxWithShadowOption(boolean flag) {
      if (this.getVisible()) {
         if (this.getEnableBackgroundDrawing()) {
            drawRect(this.xPosition - 1, this.yPosition - 1, this.xPosition + this.width + 1, this.yPosition + this.height + 1, -6250336);
            drawRect(this.xPosition, this.yPosition, this.xPosition + this.width, this.yPosition + this.height, -16777216);
         }

         int n = this.isEnabled ? this.textColor : this.disabledTextColour;
         int n2 = this.cursorPosition - this.lineScrollOffset;
         int n3 = this.selectionEnd - this.lineScrollOffset;
         String str = this.fontRendererInstance.trimStringToWidth(this.text.substring(this.lineScrollOffset), this.getWidth());
         boolean flag2 = n2 >= 0 && n2 <= str.length();
         boolean flag3 = this.isFocused && this.cursorCounter / 6 % 2 == 0 && flag2;
         int n4 = this.enableBackgroundDrawing ? this.xPosition + 4 : this.xPosition;
         int n5 = this.enableBackgroundDrawing ? this.yPosition + (this.height - 8) / 2 : this.yPosition;
         int n6 = n4;
         if (n3 > str.length()) {
            n3 = str.length();
         }

         if (str.length() > 0) {
            String str2 = flag2 ? str.substring(0, n2) : str;
            n6 = this.fontRendererInstance.drawString(str2, (float)n4, (float)n5, n, flag);
         }

         boolean flag4 = this.cursorPosition < this.text.length() || this.text.length() >= this.getMaxStringLength();
         int n7 = n6;
         if (!flag2) {
            n7 = n2 > 0 ? n4 + this.width : n4;
         } else if (flag4) {
            n7 = n6 - 1;
            n6--;
         }

         if (str.length() > 0 && flag2 && n2 < str.length()) {
            n6 = this.fontRendererInstance.drawString(str.substring(n2), (float)n6, (float)n5, n, flag);
         }

         if (flag3) {
            if (flag4) {
               Gui.drawRect(n7, n5 - 1, n7 + 1, n5 + 1 + this.fontRendererInstance.FONT_HEIGHT, -3092272);
            } else {
               this.fontRendererInstance.drawString("_", (float)n7, (float)n5, n, flag);
            }
         }

         if (n3 != n2) {
            this.drawCursorVertical(n7, n5 - 1, (n4 + this.fontRendererInstance.getStringWidth(str.substring(0, n3))) - 1, n5 + 1 + this.fontRendererInstance.FONT_HEIGHT);
         }
      }
   }

   public void setDisabledTextColour(int n) {
      this.disabledTextColour = n;
   }

   public void setCursorPosition(int n) {
      this.cursorPosition = n;
      int length = this.text.length();
      this.cursorPosition = MathHelper.clamp_int(this.cursorPosition, 0, length);
      this.setSelectionPos(this.cursorPosition);
   }

   public int getNthWordFromPosWS(int n, int n2, boolean flag) {
      int n3 = n2;
      boolean flag2 = n < 0;
      int n4 = Math.abs(n);

      for (int i = 0; i < n4; i++) {
         if (!flag2) {
            int length = this.text.length();
            n3 = this.text.indexOf(32, n3);
            if (n3 == -1) {
               n3 = length;
            } else {
               while (flag && n3 < length && this.text.charAt(n3) == ' ') {
                  n3++;
               }
            }
         } else {
            while (flag && n3 > 0 && this.text.charAt(n3 - 1) == ' ') {
               n3--;
            }

            while (n3 > 0 && this.text.charAt(n3 - 1) != ' ') {
               n3--;
            }
         }
      }

      return n3;
   }

   private void drawCursorVertical(int n, int n2, int n3, int n4) {
      if (n < n3) {
         int n5 = n;
         n = n3;
         n3 = n5;
      }

      if (n2 < n4) {
         int n6 = n2;
         n2 = n4;
         n4 = n6;
      }

      if (n3 > this.xPosition + this.width) {
         n3 = this.xPosition + this.width;
      }

      if (n > this.xPosition + this.width) {
         n = this.xPosition + this.width;
      }

      Tessellator instance = Tessellator.getInstance();
      WorldRenderer worldRenderer = instance.getWorldRenderer();
      GlStateManager.color(0.0F, 0.0F, 255.0F, 255.0F);
      GlStateManager.disableTexture2D();
      GlStateManager.enableColorLogic();
      GlStateManager.colorLogicOp(5387);
      worldRenderer.begin(7, DefaultVertexFormats.POSITION);
      worldRenderer.pos((double)n, (double)n4, 0.0).endVertex();
      worldRenderer.pos((double)n3, (double)n4, 0.0).endVertex();
      worldRenderer.pos((double)n3, (double)n2, 0.0).endVertex();
      worldRenderer.pos((double)n, (double)n2, 0.0).endVertex();
      instance.draw();
      GlStateManager.disableColorLogic();
      GlStateManager.enableTexture2D();
   }

   public void moveCursorBy(int n) {
      this.setCursorPosition(this.selectionEnd + n);
   }

   public boolean getVisible() {
      return this.visible;
   }

   public int getId() {
      return this.id;
   }

   public int getHeight() {
      return this.height;
   }

   public int getNthWordFromPos(int n, int n2) {
      return this.getNthWordFromPosWS(n, n2, true);
   }
}
