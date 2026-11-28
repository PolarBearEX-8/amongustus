package wdl.gui;

import wdl.WDL;
import java.io.IOException;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.resources.I18n;

public class GuiWDL extends GuiScreen {
   private String displayedTooltip = null;
   private String title = "";
   private GuiScreen parent;
   private GuiTextField worldname;
   private GuiWDLButtonList list;

   public static Minecraft getMinecraftForButtonList(GuiWDL guiWDL) {
      return guiWDL.mc;
   }

   public static Minecraft getMinecraftForMultiworldSelect(GuiWDL guiWDL) {
      return guiWDL.mc;
   }

   @Override
   public void keyTyped(char typedChar, int keyCode) throws IOException {
      super.keyTyped(typedChar, keyCode);
      this.worldname.textboxKeyTyped(typedChar, keyCode);
   }

   @Override
   public void onGuiClosed() {
      if (this.worldname != null) {
         WDL.baseProps.setProperty("ServerName", this.worldname.getText());
         WDL.saveProps();
      }
   }

   public static String setDisplayedTooltip(GuiWDL guiWDL, String str) {
      return guiWDL.displayedTooltip = str;
   }

   @Override
   public void mouseReleased(int mouseX, int mouseY, int state) {
      if (!this.list.mouseReleased(mouseX, mouseY, state)) {
         super.mouseReleased(mouseX, mouseY, state);
      }
   }

   public GuiWDL(GuiScreen screen) {
      this.parent = screen;
   }

   @Override
   public void updateScreen() {
      this.worldname.updateCursorCounter();
      super.updateScreen();
   }

   public static Minecraft getMinecraftForMultiworldCancel(GuiWDL guiWDL) {
      return guiWDL.mc;
   }

   public static Minecraft getMinecraftForSetupSelected(GuiWDL guiWDL) {
      return guiWDL.mc;
   }

   public static Minecraft getMinecraftForSetupCancel(GuiWDL guiWDL) {
      return guiWDL.mc;
   }

   @Override
   public void initGui() {
      if (WDL.isMultiworld && WDL.worldName.isEmpty()) {
         this.mc.displayGuiScreen((GuiScreen)new GuiWDLMultiworldSelect(I18n.format("wdl.gui.multiworldSelect.title.changeOptions", new Object[0]), new GuiWDLOptionsMultiworldSelectCallback(this)));
      } else if (!WDL.propsFound) {
         this.mc.displayGuiScreen((GuiScreen)new GuiWDLMultiworld(new GuiWDLOptionsMultiworldCallback(this)));
      } else {
         this.buttonList.clear();
         this.title = I18n.format("wdl.gui.wdl.title", new Object[]{WDL.baseFolderName.replace('@', ':')});
         if (WDL.baseProps.getProperty("ServerName").isEmpty()) {
            WDL.baseProps.setProperty("ServerName", WDL.getServerName());
         }

         this.worldname = new GuiTextField(42, this.fontRendererObj, this.width / 2 - 155, 19, 150, 18);
         this.worldname.setText(WDL.baseProps.getProperty("ServerName"));
         this.buttonList.add(new GuiButton(100, this.width / 2 - 100, this.height - 29, I18n.format("gui.done", new Object[0])));
         this.list = new GuiWDLButtonList(this);
      }
   }

   @Override
   public void actionPerformed(GuiButton button) {
      if (button.enabled) {
         if (button.id == 100) {
            GuiScreen parent2 = this.parent;
            this.mc.displayGuiScreen(parent2);
         }
      }
   }

   @Override
   public void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
      this.list.mouseClicked(mouseX, mouseY, mouseButton);
      super.mouseClicked(mouseX, mouseY, mouseButton);
      this.worldname.mouseClicked(mouseX, mouseY, mouseButton);
   }

   public static Minecraft getMinecraftForWorldSelected(GuiWDL guiWDL) {
      return guiWDL.mc;
   }

   @Override
   public void drawScreen(int mouseX, int mouseY, float partialTicks) {
      this.drawDefaultBackground();
      this.displayedTooltip = null;
      this.list.drawScreen(mouseX, mouseY, partialTicks);
      drawCenteredString(this.fontRendererObj, this.title, this.width / 2, 8, 16777215);
      String str = I18n.format("wdl.gui.wdl.worldname", new Object[0]);
      this.drawString(this.fontRendererObj, str, this.worldname.xPosition - this.fontRendererObj.getStringWidth(str + " "), 26, 16777215);
      this.worldname.drawTextBox();
      super.drawScreen(mouseX, mouseY, partialTicks);
      Utils.drawGuiInfoBox(this.displayedTooltip, this.width, this.height, 48);
   }

   public static Minecraft getMinecraftForSelectCancel(GuiWDL guiWDL) {
      return guiWDL.mc;
   }

   public static Minecraft getMinecraftForReturnToOptions(GuiWDL guiWDL) {
      return guiWDL.mc;
   }

   @Override
   public void handleMouseInput() throws IOException {
      super.handleMouseInput();
      this.list.handleMouseInput();
   }

}
