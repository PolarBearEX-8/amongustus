package net.augustus.ui;

import net.minecraft.client.gui.GuiPageButtonList;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ChatAllowedCharacters;
import net.minecraft.util.MathHelper;

public class GuiPasswordField extends Gui {
   private boolean isFocused;
   private boolean canLoseFocus;
   private boolean visible;
   private boolean enableBackgroundDrawing;
   private final int height;
   private boolean isEnabled;
   private int disabledColor;
   public int yPosition;
   private int lineScrollOffset;
   private final FontRenderer fontRenderer;
   private final int id;
   private int selectionEnd;
   private int enabledColor;
   private int cursorCounter;
   private String rawPasswordText;
   private Predicate validator;
   private int maxStringLength;
   private GuiPageButtonList.GuiResponder guiResponder;
   private final int width;
   private int cursorPosition;
   public int xPosition;
   private String text = "";

   public String getText() {
      return this.text;
   }

   public void setGuiResponder(GuiPageButtonList.GuiResponder guiResponder2) {
      this.guiResponder = guiResponder2;
   }

   public void setCursorPositionEnd() {
      this.setCursorPosition(this.text.length());
   }

   public void setFocused(boolean focused) {
      if (focused && !this.isFocused) {
         this.cursorCounter = 0;
      }

      this.isFocused = focused;
   }

   public void setCursorPosition(int n) {
      this.cursorPosition = n;
      int length = this.text.length();
      this.cursorPosition = MathHelper.clamp_int(this.cursorPosition, 0, length);
      this.setSelectionPos(this.cursorPosition);
   }

   public String getSelectedText() {
      int n = this.cursorPosition < this.selectionEnd ? this.cursorPosition : this.selectionEnd;
      int n2 = this.cursorPosition < this.selectionEnd ? this.selectionEnd : this.cursorPosition;
      return this.text.substring(n, n2);
   }

   public int getNthWordFromPos(int n, int n2, boolean flag) {
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
         this.rawPasswordText = str2;
         this.moveCursorBy(n - this.selectionEnd + n4);
         if (this.guiResponder != null) {
            this.guiResponder.setEntryValue(this.id, this.text);
         }
      }
   }

   public boolean textboxKeyTyped(char ch, int n) {
      if (!this.isFocused) {
         return false;
      } else if (GuiScreen.isKeyComboCtrlA(n)) {
         this.setCursorPositionEnd();
         this.setSelectionPos((byte)0);
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
                     this.deleteWords((byte)-1);
                  }
               } else if (this.isEnabled) {
                  this.deleteFromCursor((byte)-1);
               }

               return true;
            case 199:
               if (GuiScreen.isShiftKeyDown()) {
                  this.setSelectionPos((byte)0);
               } else {
                  this.setCursorPositionZero();
               }

               return true;
            case 203:
               if (GuiScreen.isShiftKeyDown()) {
                  if (GuiScreen.isCtrlKeyDown()) {
                     this.setSelectionPos(this.getNthWordFromPos((byte)-1, this.getSelectionEnd()));
                  } else {
                     this.setSelectionPos(this.getSelectionEnd() - 1);
                  }
               } else if (GuiScreen.isCtrlKeyDown()) {
                  this.setCursorPosition(this.getNthWordFromCursor((byte)-1));
               } else {
                  this.moveCursorBy((byte)-1);
               }

               return true;
            case 205:
               if (GuiScreen.isShiftKeyDown()) {
                  if (GuiScreen.isCtrlKeyDown()) {
                     this.setSelectionPos(this.getNthWordFromPos((byte)1, this.getSelectionEnd()));
                  } else {
                     this.setSelectionPos(this.getSelectionEnd() + 1);
                  }
               } else if (GuiScreen.isCtrlKeyDown()) {
                  this.setCursorPosition(this.getNthWordFromCursor((byte)1));
               } else {
                  this.moveCursorBy((byte)1);
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
                     this.deleteWords((byte)1);
                  }
               } else if (this.isEnabled) {
                  this.deleteFromCursor((byte)1);
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

   public boolean isFocused() {
      return this.isFocused;
   }

   public void deleteFromCursor(int n) {
      if (this.text.length() != 0) {
         if (this.selectionEnd != this.cursorPosition) {
            this.writeText("");
         } else {
            boolean flag = n < 0;
            int n2 = flag ? this.cursorPosition + n : this.cursorPosition;
            int n3 = flag ? this.cursorPosition : this.cursorPosition + n;
            String str2 = "";
            if (n2 >= 0) {
               str2 = this.text.substring(0, n2);
            }

            if (n3 < this.text.length()) {
               str2 = str2 + this.text.substring(n3);
            }

            if (this.validator.apply(str2)) {
               this.text = str2;
               this.rawPasswordText = str2;
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

   public boolean isVisible() {
      return this.visible;
   }

   public void setCanLoseFocus(boolean flag) {
      this.canLoseFocus = flag;
   }

   public void setEnableBackgroundDrawing(boolean flag) {
      this.enableBackgroundDrawing = flag;
   }

   public int getId() {
      return this.id;
   }

   public int getCursorPosition() {
      return this.cursorPosition;
   }

   public void setSelectionPos(int selectionPos) {
      int length = this.text.length();
      if (selectionPos > length) {
         selectionPos = length;
      }

      if (selectionPos < 0) {
         selectionPos = 0;
      }

      this.selectionEnd = selectionPos;
      if (this.fontRenderer != null) {
         if (this.lineScrollOffset > length) {
            this.lineScrollOffset = length;
         }

         int width2 = this.getWidth();
         int n2 = this.fontRenderer.trimStringToWidth(this.text.substring(this.lineScrollOffset), width2).length() + this.lineScrollOffset;
         if (selectionPos == this.lineScrollOffset) {
            this.lineScrollOffset = this.lineScrollOffset - this.fontRenderer.trimStringToWidth(this.text, width2, true).length();
         }

         if (selectionPos > n2) {
            this.lineScrollOffset += selectionPos - n2;
         } else if (selectionPos <= this.lineScrollOffset) {
            this.lineScrollOffset = this.lineScrollOffset - (this.lineScrollOffset - selectionPos);
         }

         this.lineScrollOffset = MathHelper.clamp_int(this.lineScrollOffset, 0, length);
      }
   }

   public void setMaxStringLength(int n) {
      this.maxStringLength = n;
      if (this.text.length() > n) {
         this.text = this.text.substring(0, n);
         this.rawPasswordText = this.rawPasswordText.substring(0, n);
      }
   }

   public void setCursorPositionZero() {
      this.setCursorPosition((byte)0);
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

   public void drawTextBox() {
      this.text = "";

      for (int i = 0; i < this.rawPasswordText.length(); i++) {
         this.text = this.text + "*";
      }

      if (this.isVisible()) {
         if (this.isEnableBackgroundDrawing()) {
            drawRect(this.xPosition - 1, this.yPosition - 1, this.xPosition + this.width + 1, this.yPosition + this.height + 1, -6250336);
            drawRect(this.xPosition, this.yPosition, this.xPosition + this.width, this.yPosition + this.height, -16777216);
         }

         int n = this.isEnabled ? this.enabledColor : this.disabledColor;
         int n2 = this.cursorPosition - this.lineScrollOffset;
         int n3 = this.selectionEnd - this.lineScrollOffset;
         FontRenderer fontRenderer2 = this.fontRenderer;
         String str = this.text.substring(this.lineScrollOffset);
         int n4;
         String str2 = fontRenderer2.trimStringToWidth(str, this.getWidth());
         boolean flag = n2 >= 0 && n2 <= str2.length();
         boolean flag2 = this.isFocused && this.cursorCounter / 6 % 2 == 0 && flag;
         int n5 = this.enableBackgroundDrawing ? this.xPosition + 4 : this.xPosition;
         int n6 = this.enableBackgroundDrawing ? this.yPosition + (this.height - 8) / 2 : this.yPosition;
         int n7 = n5;
         if (n3 > str2.length()) {
            n3 = str2.length();
         }

         if (str2.length() > 0) {
            String str3 = flag ? str2.substring(0, n2) : str2;
            n7 = this.fontRenderer.drawStringWithShadow(str3, (float)n5, (float)n6, n);
         }

         boolean flag3 = this.cursorPosition < this.text.length() || this.text.length() >= this.getMaxStringLength();
         int n8 = n7;
         if (!flag) {
            n8 = n2 > 0 ? n5 + this.width : n5;
         } else if (flag3) {
            n8 = n7 - 1;
            n7--;
         }

         if (str2.length() > 0 && flag && n2 < str2.length()) {
            n7 = this.fontRenderer.drawStringWithShadow(str2.substring(n2), (float)n7, (float)n6, n);
         }

         if (flag2) {
            if (flag3) {
               Gui.drawRect(n8, n6 - 1, n8 + 1, n6 + 1 + this.fontRenderer.FONT_HEIGHT, -3092272);
            } else {
               this.fontRenderer.drawStringWithShadow("_", (float)n8, (float)n6, n);
            }
         }

         if (n3 != n2) {
            int n9 = n5 + this.fontRenderer.getStringWidth(str2.substring(0, n3));
            int n10 = n6 - 1;
            n4 = n9 - 1;
            this.drawCursorVertical(n8, n10, n4, n6 + 1 + this.fontRenderer.FONT_HEIGHT);
         }
      }

      this.text = this.rawPasswordText;
   }

   public int getNthWordFromPos(int n, int n2) {
      return this.getNthWordFromPos(n, n2, true);
   }

   public int getSelectionEnd() {
      return this.selectionEnd;
   }

   public void setDisabledColor(int n) {
      this.disabledColor = n;
   }

   public void setVisible(boolean flag) {
      this.visible = flag;
   }

   public void setText(String str) {
      if (this.validator.apply(str)) {
         if (str.length() > this.maxStringLength) {
            this.text = str.substring(0, this.maxStringLength);
            this.rawPasswordText = str.substring(0, this.maxStringLength);
         } else {
            this.text = str;
            this.rawPasswordText = str;
         }

         this.setCursorPositionEnd();
      }
   }

   public void moveCursorBy(int n) {
      this.setCursorPosition(this.selectionEnd + n);
   }

   public void setValidator(Predicate predicate) {
      this.validator = predicate;
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

         String str2 = this.fontRenderer.trimStringToWidth(this.text.substring(this.lineScrollOffset), this.getWidth());
         this.setCursorPosition(this.fontRenderer.trimStringToWidth(str2, n).length() + this.lineScrollOffset);
      }
   }

   public GuiPasswordField(int n, FontRenderer fontRenderer2, int n2, int n3, int n4, int n5) {
      this.rawPasswordText = "";
      this.maxStringLength = 32;
      this.enableBackgroundDrawing = true;
      this.canLoseFocus = true;
      this.isEnabled = true;
      this.enabledColor = 14737632;
      this.disabledColor = 7368816;
      this.visible = true;
      this.validator = Predicates.alwaysTrue();
      this.id = n;
      this.fontRenderer = fontRenderer2;
      this.xPosition = n2;
      this.yPosition = n3;
      this.width = n4;
      this.height = n5;
   }

   public int getNthWordFromCursor(int n) {
      return this.getNthWordFromPos(n, this.getCursorPosition());
   }

   public void setIsEnabled(boolean flag) {
      this.isEnabled = flag;
   }

   public int getWidth() {
      return this.isEnableBackgroundDrawing() ? this.width - 8 : this.width;
   }

   public void updateCursorCounter() {
      this.cursorCounter++;
   }

   public boolean isEnableBackgroundDrawing() {
      return this.enableBackgroundDrawing;
   }

   public int getMaxStringLength() {
      return this.maxStringLength;
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

   public void setEnabledColor(int n) {
      this.enabledColor = n;
   }
}
