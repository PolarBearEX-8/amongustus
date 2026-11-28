package net.augustus.clickgui.components;

import net.augustus.ClientManagers;
import net.augustus.font.CustomFontUtil;
import net.augustus.font.FontManager;
import net.augustus.font.MeasuredStringBuilder;
import net.augustus.render.RenderUtil;
import net.augustus.settings.StringInputMode;
import net.augustus.utils.input.KeyboardInputUtil;
import net.augustus.utils.render.ColorUtil;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.io.IOException;
import org.lwjgl.input.Keyboard;

public class TextFieldComponent extends GuiComponent {
   private int selectionAnchor;
   private MeasuredStringBuilder textBuffer;
   private long keyHoldStartTime;
   private CustomFontUtil textFont;
   private int cursorIndex;
   private long keyRepeatTime;
   private final StringInputMode inputMode;
   private long lastClickTime;
   private long backspaceHoldStartTime;
   private boolean focused;
   public static boolean anyFieldFocused = false;
   private long backspaceRepeatTime;
   private long cursorBlinkTime;

   public void setText(String text) {
      this.rebuildTextBuffer(text);
      this.cursorIndex = text.length();
      this.selectionAnchor = this.cursorIndex;
   }

   private void insertFilteredText(String str) {
      StringBuilder sb = new StringBuilder(this.textBuffer.getCachedText());
      int n = Math.min(this.cursorIndex, this.selectionAnchor);
      int n2 = Math.max(this.cursorIndex, this.selectionAnchor);
      if (n != n2) {
         sb.delete(n, n2);
         this.cursorIndex = n;
         this.selectionAnchor = n;
      }

      StringBuilder sb2 = new StringBuilder();

      for (char ch : str.toCharArray()) {
         if (this.inputMode.isCharAllowed(ch)
            && (
               !this.inputMode.numeric
                  || (ch != '-' || this.cursorIndex == 0 && !sb.toString().contains("-"))
                     && (ch != '.' || !sb.toString().contains("."))
                     && (ch != '.' || sb.length() != 0 && !"-".equals(sb.toString()))
            )) {
            sb2.append(ch);
         }
      }

      str = sb2.toString();
      sb.insert(this.cursorIndex, str);
      this.rebuildTextBuffer(sb.toString());
      this.cursorIndex = this.cursorIndex + str.length();
      this.selectionAnchor = this.cursorIndex;
      this.cursorBlinkTime = System.currentTimeMillis();
   }

   private String getDisplayText() {
      return this.inputMode.hidden ? this.textBuffer.getCachedText().replaceAll(".", "*") : this.textBuffer.getCachedText();
   }

   public void drawTextField(int n, int n2, int n3, int n4, CustomFontUtil customFontUtil, int n5) {
      super.updateLayout(n, n2, n3, n4, customFontUtil);
      this.textFont = customFontUtil;
      if (this.textBuffer.length() > 2 && !this.isFocused()) {
         if (this.textBuffer.substring(this.textBuffer.length() - 2, this.textBuffer.length()).equals(".0") && this.inputMode.numeric) {
            this.rebuildTextBuffer(this.textBuffer.getCachedText().substring(0, this.textBuffer.length() - 2));
         }
      }

      String displayText = this.getDisplayText();
      if (this.focused && this.cursorIndex != this.selectionAnchor) {
         int n8 = Math.min(this.cursorIndex, this.selectionAnchor);
         int n9 = Math.max(this.cursorIndex, this.selectionAnchor);
         n8 = Math.max(0, Math.min(n8, displayText.length()));
         n9 = Math.max(0, Math.min(n9, displayText.length()));
         String str3 = displayText.substring(0, n8);
         String str4 = displayText.substring(n8, n9);
         double d = (double)(((float)n) + customFontUtil.getStringWidth(str3));
         RenderUtil.drawRect((int)d, n2, (int)(d + ((double)customFontUtil.getStringWidth(str4))), n2 + (int)customFontUtil.getFontHeight(), new Color(0, 0, 255, 100).getRGB());
      }

      customFontUtil.drawString(displayText, (double)n, (double)n2, n5);
      if (this.focused) {
         anyFieldFocused = true;
         if (!hasElapsed(this.cursorBlinkTime, 500)) {
            double d5 = (double)(((float)n) + customFontUtil.getStringWidth(displayText.substring(0, Math.max(0, Math.min(this.cursorIndex, displayText.length())))));
            float[] floats = ColorUtil.toHsb(ClientManagers.moduleManager.clickGui.textColor.getColorInt((byte)0));
            float f3 = (float)((double)floats[2] + -0.2);
            if ((double)floats[2] + -0.2 > 1.0) {
               f3 = 1.0F;
            } else if ((double)floats[2] + -0.2 < 0.0) {
               f3 = 0.0F;
            }

            int n11 = Color.HSBtoRGB(floats[0], floats[1], f3);
            RenderUtil.drawRect(
               (double)((int)d5 + 1), (double)n2, (double)((int)d5 + 2), (double)((float)n2 + customFontUtil.getFontHeight() - 2.0F), n11
            );
         } else if (hasElapsed(this.cursorBlinkTime, 1000)) {
            this.cursorBlinkTime = System.currentTimeMillis();
         }

         this.handleKeyRepeat();
      }
   }

   public TextFieldComponent(String str, StringInputMode stringInputMode) {
       super();
      this.focused = false;
      this.cursorBlinkTime = 0L;
      this.lastClickTime = 0L;
      this.backspaceRepeatTime = 0L;
      this.backspaceHoldStartTime = 0L;
      this.keyHoldStartTime = 0L;
      this.keyRepeatTime = 0L;
      this.inputMode = stringInputMode;
      this.textBuffer = new MeasuredStringBuilder(FontManager.consolas30TextField, str);
      this.textBuffer.updateCache();
      this.cursorIndex = str.length();
      this.selectionAnchor = this.cursorIndex;
   }

   public void setFocused(boolean flag) {
      this.focused = flag;
      if (!flag) {
         this.selectionAnchor = this.cursorIndex;
      }
   }

   public void mouseReleased(int mouseX2, int mouseY2, int state) {
   }

   public void keyTyped(char typedChar, int keyCode) throws IOException {
      if ((keyCode == 28 || keyCode == 1) && this.focused) {
         this.focused = false;
         this.selectionAnchor = this.cursorIndex;
      } else {
         if (this.focused) {
            if (keyCode == 203) {
               if (this.isShiftDown()) {
                  if (this.cursorIndex > 0) {
                     this.cursorIndex--;
                  }
               } else {
                  if (this.cursorIndex != this.selectionAnchor) {
                     this.cursorIndex = Math.min(this.cursorIndex, this.selectionAnchor);
                  } else if (this.cursorIndex > 0) {
                     this.cursorIndex--;
                  }

                  this.selectionAnchor = this.cursorIndex;
               }

               this.cursorBlinkTime = System.currentTimeMillis();
               return;
            }

            if (keyCode == 205) {
               if (this.isShiftDown()) {
                  if (this.cursorIndex < this.textBuffer.length()) {
                     this.cursorIndex++;
                  }
               } else {
                  if (this.cursorIndex != this.selectionAnchor) {
                     this.cursorIndex = Math.max(this.cursorIndex, this.selectionAnchor);
                  } else if (this.cursorIndex < this.textBuffer.length()) {
                     this.cursorIndex++;
                  }

                  this.selectionAnchor = this.cursorIndex;
               }

               this.cursorBlinkTime = System.currentTimeMillis();
               return;
            }

            if (this.isControlDown() && keyCode == 30) {
               this.cursorIndex = this.textBuffer.length();
               this.selectionAnchor = 0;
               return;
            }

            if (this.isControlDown() && keyCode == 46) {
               String selectedText = this.getSelectedText();
               if (!selectedText.isEmpty()) {
                  this.setClipboardText(selectedText);
               }

               return;
            }

            if (this.isControlDown() && keyCode == 47) {
               String clipboardText = this.getClipboardText();
               if (!clipboardText.isEmpty()) {
                  this.insertFilteredText(clipboardText);
               }

               return;
            }

            if (this.isControlDown() && keyCode == 45) {
               String selectedText2 = this.getSelectedText();
               if (!selectedText2.isEmpty()) {
                  this.setClipboardText(selectedText2);
                  this.insertFilteredText("");
               }

               return;
            }

            if (keyCode == 14) {
               this.deleteBackward();
            } else {
               if (this.inputMode.isCharAllowed(typedChar)) {
                  if (!this.isControlDown()) {
                     this.insertFilteredText(String.valueOf(typedChar));
                  }
               }
            }
         }
      }
   }

   private void setClipboardText(String clipboardText) {
      StringSelection stringSelection = new StringSelection(clipboardText);
      Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
   }

   private boolean isShiftDown() {
      return Keyboard.isKeyDown(42) || Keyboard.isKeyDown(54);
   }

   private void handleKeyRepeat() {
      if (KeyboardInputUtil.isKeyDown(14)) {
         if (hasElapsed(this.backspaceHoldStartTime, 500) && hasElapsed(this.backspaceRepeatTime, 40)) {
            this.deleteBackward();
            this.cursorBlinkTime = System.currentTimeMillis();
            this.backspaceRepeatTime = System.currentTimeMillis();
         }
      } else {
         this.backspaceHoldStartTime = System.currentTimeMillis();
      }

      boolean flag = false;
      char eventCharacter = Keyboard.getEventCharacter();
      int eventKey = Keyboard.getEventKey();
      if (this.inputMode.isCharAllowed(eventCharacter) && Keyboard.getEventKeyState()) {
         if (!this.isControlDown() && eventKey != 28 && eventKey != 15 && eventKey != 14) {
            flag = true;
            if (hasElapsed(this.keyHoldStartTime, 500) && hasElapsed(this.keyRepeatTime, 40)) {
               this.insertFilteredText(String.valueOf(eventCharacter));
               this.keyRepeatTime = System.currentTimeMillis();
               this.cursorBlinkTime = System.currentTimeMillis();
            }
         }
      }

      if (!flag) {
         this.keyHoldStartTime = System.currentTimeMillis();
      }
   }

   private boolean isControlDown() {
      return Keyboard.isKeyDown(29) || Keyboard.isKeyDown(157);
   }

   public boolean isFocused() {
      return this.focused;
   }

   private String getClipboardText() {

      try {
         Transferable contents = Toolkit.getDefaultToolkit().getSystemClipboard().getContents(null);
         if (contents != null && contents.isDataFlavorSupported(DataFlavor.stringFlavor)) {
            return (String)contents.getTransferData(DataFlavor.stringFlavor);
         }
      } catch (Exception e) {
         e.printStackTrace();
      }

      return "";
   }

   private static boolean hasElapsed(long l, int n) {
      return System.currentTimeMillis() - l >= (long)n;
   }

   public void mouseClickMove(int mouseX2, int mouseY2, int clickedMouseButton, long timeSinceLastClick) {
   }

   public String getText() {
      return this.textBuffer.getCachedText();
   }

   private String getSelectedText() {
      int n = Math.min(this.cursorIndex, this.selectionAnchor);
      int n2 = Math.max(this.cursorIndex, this.selectionAnchor);
      return n == n2 ? "" : this.textBuffer.getCachedText().substring(n, n2);
   }

   private void rebuildTextBuffer(String str) {
      this.textBuffer = new MeasuredStringBuilder(this.textFont == null ? FontManager.consolas30Default : this.textFont, str);
      this.textBuffer.updateCache();
   }

   public void mouseClicked(int mouseX2, int mouseY2, int mouseButton) throws IOException {
      if (this.textFont != null) {
         int n = this.textBuffer.isEmpty() ? 40 : this.getTextWidth();
         double fontHeight = (double)this.textFont.getFontHeight();
         if (RenderUtil.isHovered((float)(this.x - 4), (float)this.y, (float)(n + 8), (float)((int)fontHeight + 1), mouseX2, mouseY2)) {
            if (mouseButton == 0) {
               long currentTimeMs = System.currentTimeMillis();
               boolean flag = currentTimeMs - this.lastClickTime < 500L;
               this.lastClickTime = currentTimeMs;
               if (this.focused || flag) {
                  boolean focused2 = this.focused;
                  this.focused = true;
                  String displayText = this.getDisplayText();
                  if (!focused2) {
                     this.cursorIndex = displayText.length();
                     this.selectionAnchor = this.cursorIndex;
                  } else {
                     double d = (double)(mouseX2 - this.x);
                     int length = displayText.length();
                     if (d <= 0.0) {
                        length = 0;
                     } else {
                        for (int i = 0; i < displayText.length(); i++) {
                           String str = displayText.substring(0, i);
                           String str2 = displayText.substring(0, i + 1);
                           double stringWidth = (double)this.textFont.getStringWidth(str);
                           double stringWidth2 = (double)this.textFont.getStringWidth(str2);
                           if (d >= stringWidth && d < stringWidth2) {
                              double d2 = stringWidth2 - stringWidth;
                              if (d - stringWidth < d2 / 2.0) {
                                 length = i;
                              } else {
                                 length = i + 1;
                              }
                              break;
                           }
                        }
                     }

                     this.cursorIndex = length;
                     if (!this.isShiftDown()) {
                        this.selectionAnchor = this.cursorIndex;
                     }
                  }

                  this.cursorBlinkTime = System.currentTimeMillis();
               }
            }
         } else if (this.focused) {
            this.focused = false;
            this.selectionAnchor = this.cursorIndex;
         }
      }
   }

   private void deleteBackward() {
      if (this.cursorIndex == this.selectionAnchor && this.cursorIndex > 0) {
         StringBuilder sb = new StringBuilder(this.textBuffer.getCachedText());
         sb.deleteCharAt(this.cursorIndex - 1);
         this.rebuildTextBuffer(sb.toString());
         this.cursorIndex--;
         this.selectionAnchor = this.cursorIndex;
      } else if (this.cursorIndex != this.selectionAnchor) {
         this.insertFilteredText("");
      }
   }

   public int getTextWidth() {
      if (this.textFont == null) {
         return this.textBuffer.getCachedWidth();
      } else {
         int n;
         if (!this.focused && this.inputMode.hidden) {
            String str = "**************";
            n = (int)this.textFont.getStringWidth(str);
         } else {
            n = this.textBuffer.getCachedWidth();
         }

         return n;
      }
   }


}
