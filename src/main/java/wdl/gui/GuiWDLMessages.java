package wdl.gui;

import wdl.WDL;
import wdl.WDLMessages;
import java.io.IOException;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiYesNo;
import net.minecraft.client.resources.I18n;

public class GuiWDLMessages extends GuiScreen {
   private String hoveredButtonDescription = null;
   private GuiScreen parent;
   private GuiMessageTypeList list;
   private GuiButton enableAllButton;
   private GuiButton resetButton;

   public static String setHoveredButtonDescription(GuiWDLMessages guiWDLMessages, String str) {
      return guiWDLMessages.hoveredButtonDescription = str;
   }

   @Override
   public void confirmClicked(boolean flag, int n) {
      if (flag && n == 101) {
         WDLMessages.resetEnabledToDefaults();
      }

      this.mc.displayGuiScreen(this);
   }

   @Override
   public void drawScreen(int mouseX, int mouseY, float partialTicks) {
      this.hoveredButtonDescription = null;
      this.drawDefaultBackground();
      this.list.drawScreen(mouseX, mouseY, partialTicks);
      drawCenteredString(this.fontRendererObj, I18n.format("wdl.gui.messages.message.title", new Object[0]), this.width / 2, 8, 16777215);
      super.drawScreen(mouseX, mouseY, partialTicks);
      if (this.hoveredButtonDescription != null) {
         Utils.drawGuiInfoBox(this.hoveredButtonDescription, this.width, this.height, 48);
      } else if (this.enableAllButton.isMouseOver()) {
         Utils.drawGuiInfoBox(I18n.format("wdl.gui.messages.all.description", new Object[0]), this.width, this.height, 48);
      } else if (this.resetButton.isMouseOver()) {
         Utils.drawGuiInfoBox(I18n.format("wdl.gui.messages.reset.description", new Object[0]), this.width, this.height, 48);
      }
   }

   @Override
   public void initGui() {
      this.enableAllButton = new GuiButton(100, this.width / 2 - 155, 18, 150, 20, this.getAllEnabledText());
      this.buttonList.add(this.enableAllButton);
      this.resetButton = new GuiButton(101, this.width / 2 + 5, 18, 150, 20, I18n.format("wdl.gui.messages.reset", new Object[0]));
      this.buttonList.add(this.resetButton);
      this.list = new GuiMessageTypeList(this);
      this.buttonList.add(new GuiButton(102, this.width / 2 - 100, this.height - 29, I18n.format("gui.done", new Object[0])));
   }

   @Override
   public void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
      if (!this.list.mouseClicked(mouseX, mouseY, mouseButton)) {
         super.mouseClicked(mouseX, mouseY, mouseButton);
      }
   }

   private String getAllEnabledText() {
      return I18n.format("wdl.gui.messages.all." + WDLMessages.enableAllMessages, new Object[0]);
   }

   @Override
   public void actionPerformed(GuiButton button) throws IOException {
      if (button.enabled) {
         if (button.id == 100) {
            WDLMessages.enableAllMessages ^= true;
            WDL.baseProps.setProperty("Messages.enableAll", Boolean.toString(WDLMessages.enableAllMessages));
            button.displayString = this.getAllEnabledText();
         } else if (button.id == 101) {
            Minecraft mc2 = this.mc;
            GuiYesNo guiYesNo = new GuiYesNo(
               this,
               I18n.format("wdl.gui.messages.reset.confirm.title", new Object[0]),
               I18n.format("wdl.gui.messages.reset.confirm.subtitle", new Object[0]),
               101
            );
            mc2.displayGuiScreen(guiYesNo);
         } else if (button.id == 102) {
            GuiScreen parent2 = this.parent;
            this.mc.displayGuiScreen(parent2);
         }
      }
   }

   public static FontRenderer getFontRenderer(GuiWDLMessages guiWDLMessages) {
      return guiWDLMessages.fontRendererObj;
   }

   @Override
   public void onGuiClosed() {
      WDL.saveProps();
   }

   public GuiWDLMessages(GuiScreen screen) {
      this.parent = screen;
   }

   public static Minecraft getMinecraft(GuiWDLMessages guiWDLMessages) {
      return guiWDLMessages.mc;
   }

   @Override
   public void handleMouseInput() throws IOException {
      super.handleMouseInput();
      this.list.handleMouseInput();
   }

   @Override
   public void mouseReleased(int mouseX, int mouseY, int state) {
      if (!this.list.mouseReleased(mouseX, mouseY, state)) {
         super.mouseReleased(mouseX, mouseY, state);
      }
   }

}
