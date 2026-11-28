package net.minecraft.client.gui;

import net.minecraft.util.MovingObjectPosition;
import baritone.api.BaritoneAPI;
import baritone.api.IBaritone;
import baritone.api.event.events.TabCompleteEvent;
import com.google.common.collect.Lists;
import java.io.IOException;
import java.util.List;
import net.augustus.modules.Module;
import net.augustus.modules.render.Chat;
import net.augustus.Augustus;
import net.minecraft.network.play.client.C14PacketTabComplete;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.MathHelper;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

public class GuiChat extends GuiScreen {
   private static final Logger logger = LogManager.getLogger();
   private String historyBuffer = "";
   private int sentHistoryCursor = -1;
   private boolean playerNamesFound;
   private boolean waitingOnAutocomplete;
   private int autocompleteIndex;
   private List foundPlayerNames = Lists.newArrayList();
   public GuiTextField inputField;
   private String defaultInputFieldText = "";

   public void autocompletePlayerNames() {
      if (this.playerNamesFound) {
         this.inputField.deleteFromCursor(this.inputField.getNthWordFromPosWS(-1, this.inputField.getCursorPosition(), false) - this.inputField.getCursorPosition());
         if (this.autocompleteIndex >= this.foundPlayerNames.size()) {
            this.autocompleteIndex = 0;
         }
      } else {
         int nthWordFromPosWS = this.inputField.getNthWordFromPosWS(-1, this.inputField.getCursorPosition(), false);
         this.foundPlayerNames.clear();
         this.autocompleteIndex = 0;
         String str = this.inputField.getText().substring(nthWordFromPosWS).toLowerCase();
         this.sendAutocompleteRequest(this.inputField.getText().substring(0, this.inputField.getCursorPosition()), str);
         if (this.foundPlayerNames.isEmpty()) {
            return;
         }

         this.playerNamesFound = true;
         this.inputField.deleteFromCursor(nthWordFromPosWS - this.inputField.getCursorPosition());
      }

      if (this.foundPlayerNames.size() > 1) {
         StringBuilder sb = new StringBuilder();

         for (String str3 : (Iterable<String>)(this.foundPlayerNames)) {
            if (sb.length() > 0) {
               sb.append(", ");
            }

            sb.append(str3);
         }

         this.mc.ingameGUI.getChatGUI().printChatMessageWithOptionalDeletion(new ChatComponentText(sb.toString()), 1);
      }

      if (this.autocompleteIndex >= this.foundPlayerNames.size()) {
         this.autocompleteIndex = 0;
      }

      if (!this.foundPlayerNames.isEmpty()) {
         this.inputField.writeText((String)this.foundPlayerNames.get(this.autocompleteIndex++));
      }
   }

   @Override
   public void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
      if (mouseButton == 0) {
         if (this.handleComponentClick(this.mc.ingameGUI.getChatGUI().getChatComponent(Mouse.getX(), Mouse.getY()))) {
            return;
         }
      }

      this.inputField.mouseClicked(mouseX, mouseY, mouseButton);
      super.mouseClicked(mouseX, mouseY, mouseButton);
   }

   @Override
   public void onGuiClosed() {
      Keyboard.enableRepeatEvents(false);
      this.mc.ingameGUI.getChatGUI().resetScroll();
   }

   private void sendAutocompleteRequest(String str, String str2) {
      if (((Module)Augustus.getInstance().getModuleManager().baritone).isToggled()) {
         IBaritone primaryBaritone = BaritoneAPI.getProvider().getPrimaryBaritone();
         TabCompleteEvent tabCompleteEvent = new TabCompleteEvent(str);
         primaryBaritone.getGameEventHandler().onPreTabComplete(tabCompleteEvent);
         if (tabCompleteEvent.isCancelled()) {
            return;
         }

         if (tabCompleteEvent.completions != null) {
            this.waitingOnAutocomplete = true;
            this.onAutocompleteResponse(tabCompleteEvent.completions);
            return;
         }
      }

      if (str.length() >= 1) {
         BlockPos pos = null;
         if (this.mc.objectMouseOver != null && this.mc.objectMouseOver.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK) {
            pos = this.mc.objectMouseOver.getBlockPos();
         }

         this.mc.thePlayer.sendQueue.addToSendQueue(new C14PacketTabComplete(str, pos));
         this.waitingOnAutocomplete = true;
      }
   }

   public void onAutocompleteResponse(String[] strings) {
      if (this.waitingOnAutocomplete) {
         this.playerNamesFound = false;
         this.foundPlayerNames.clear();

         for (String str : strings) {
            if (str.length() > 0) {
               this.foundPlayerNames.add(str);
            }
         }

         String str2 = this.inputField.getText().substring(this.inputField.getNthWordFromPosWS(-1, this.inputField.getCursorPosition(), false));
         String commonPrefix = StringUtils.getCommonPrefix(strings);
         if (commonPrefix.length() > 0 && !str2.equalsIgnoreCase(commonPrefix)) {
            this.inputField
               .deleteFromCursor(this.inputField.getNthWordFromPosWS(-1, this.inputField.getCursorPosition(), false) - this.inputField.getCursorPosition());
            this.inputField.writeText(commonPrefix);
         } else if (this.foundPlayerNames.size() > 0) {
            this.playerNamesFound = true;
            this.autocompletePlayerNames();
         }
      }
   }

   @Override
   public void initGui() {
      Keyboard.enableRepeatEvents(true);
      this.sentHistoryCursor = this.mc.ingameGUI.getChatGUI().getSentMessages().size();
      this.inputField = new GuiTextField(0, this.fontRendererObj, 4, this.height - 12, this.width - 4, 12);
      this.inputField.setMaxStringLength(100);
      this.inputField.setEnableBackgroundDrawing(false);
      this.inputField.setFocused(true);
      this.inputField.setText(this.defaultInputFieldText);
      this.inputField.setCanLoseFocus(false);
   }

   @Override
   public void updateScreen() {
      this.inputField.updateCursorCounter();
   }

   @Override
   public void setText(String str, boolean flag) {
      if (flag) {
         this.inputField.setText(str);
      } else {
         this.inputField.writeText(str);
      }
   }

   @Override
   public void drawScreen(int mouseX, int mouseY, float partialTicks) {
      drawRect(2, this.height - 14, this.width - 2, this.height - 2, -2147483648);
      Chat chat = Augustus.getInstance().getModuleManager().chat;
      this.inputField.drawTextBoxWithShadowOption(!chat.isToggled() || chat.fontShadow.getValue());
      IChatComponent chatComponent = this.mc.ingameGUI.getChatGUI().getChatComponent(Mouse.getX(), Mouse.getY());
      if (chatComponent != null && chatComponent.getChatStyle().getChatHoverEvent() != null) {
         this.handleComponentHover(chatComponent, mouseX, mouseY);
      }

      super.drawScreen(mouseX, mouseY, partialTicks);
   }

   @Override
   public boolean doesGuiPauseGame() {
      return false;
   }

   @Override
   public void handleMouseInput() throws IOException {
      super.handleMouseInput();
      int eventDWheel = Mouse.getEventDWheel();
      if (eventDWheel != 0) {
         if (eventDWheel > 1) {
            eventDWheel = 1;
         }

         if (eventDWheel < -1) {
            eventDWheel = -1;
         }

         if (!isShiftKeyDown()) {
            eventDWheel *= 7;
         }

         this.mc.ingameGUI.getChatGUI().scroll(eventDWheel);
      }
   }

   public GuiChat(String str) {
      this.defaultInputFieldText = str;
   }

   public void getSentHistory(int n) {
      int n2 = this.sentHistoryCursor + n;
      int size = this.mc.ingameGUI.getChatGUI().getSentMessages().size();
      n2 = MathHelper.clamp_int(n2, 0, size);
      if (n2 != this.sentHistoryCursor) {
         if (n2 == size) {
            this.sentHistoryCursor = size;
            this.inputField.setText(this.historyBuffer);
         } else {
            if (this.sentHistoryCursor == size) {
               this.historyBuffer = this.inputField.getText();
            }

            this.inputField.setText((String)this.mc.ingameGUI.getChatGUI().getSentMessages().get(n2));
            this.sentHistoryCursor = n2;
         }
      }
   }

   public GuiChat() {
   }

   @Override
   public void keyTyped(char typedChar, int keyCode) throws IOException {
      this.waitingOnAutocomplete = false;
      if (keyCode == 15) {
         this.autocompletePlayerNames();
      } else {
         this.playerNamesFound = false;
      }

      if (keyCode == 1) {
         GuiScreen screen = (GuiScreen)null;
         this.mc.displayGuiScreen(screen);
      } else if (keyCode == 28 || keyCode == 156) {
         String str = this.inputField.getText().trim();
         if (str.length() > 0) {
            this.sendChatMessage(str);
         }

         GuiScreen screen2 = (GuiScreen)null;
         this.mc.displayGuiScreen(screen2);
      } else if (keyCode == 200) {
         this.getSentHistory(-1);
      } else if (keyCode == 208) {
         this.getSentHistory(1);
      } else if (keyCode == 201) {
         this.mc.ingameGUI.getChatGUI().scroll(this.mc.ingameGUI.getChatGUI().getLineCount() - 1);
      } else if (keyCode == 209) {
         this.mc.ingameGUI.getChatGUI().scroll(-this.mc.ingameGUI.getChatGUI().getLineCount() + 1);
      } else {
         this.inputField.textboxKeyTyped(typedChar, keyCode);
      }
   }

}
