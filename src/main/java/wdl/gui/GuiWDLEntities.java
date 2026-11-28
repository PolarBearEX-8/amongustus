package wdl.gui;

import wdl.WDL;
import wdl.WDLPluginChannels;
import java.io.IOException;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;

public class GuiWDLEntities extends GuiScreen {
   private GuiEntityList entityList;
   private GuiScreen parent;
   private GuiButton rangeModeButton;
   private GuiButton presetsButton;
   private String mode;

   private String getRangeModeText() {
      String property = WDL.worldProps.getProperty("Entity.TrackDistanceMode");
      return I18n.format("wdl.gui.entities.trackDistanceMode." + property, new Object[0]);
   }

   private void cycleRangeMode() {
      if (this.mode.equals("default")) {
         if (WDLPluginChannels.hasServerEntityRange()) {
            this.mode = "server";
         } else {
            this.mode = "user";
         }
      } else if (this.mode.equals("server")) {
         this.mode = "user";
      } else {
         this.mode = "default";
      }

      WDL.worldProps.setProperty("Entity.TrackDistanceMode", this.mode);
      this.rangeModeButton.displayString = this.getRangeModeText();
      this.presetsButton.enabled = this.shouldEnablePresetsButton();
   }

   @Override
   public void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
      if (!this.entityList.mouseClicked(mouseX, mouseY, mouseButton)) {
         super.mouseClicked(mouseX, mouseY, mouseButton);
      }
   }

   @Override
   public void handleMouseInput() throws IOException {
      super.handleMouseInput();
      this.entityList.handleMouseInput();
   }

   @Override
   public void actionPerformed(GuiButton button) throws IOException {
      if (button.id == 100) {
         this.cycleRangeMode();
      }

      if (button.id == 101 && button.enabled) {
         this.mc.displayGuiScreen(new GuiWDLEntityRangePresets(this));
      }

      if (button.id == 200) {
         GuiScreen parent2 = this.parent;
         this.mc.displayGuiScreen(parent2);
      }
   }

   @Override
   public void initGui() {
      this.buttonList.add(new GuiButton(200, this.width / 2 - 100, this.height - 29, "OK"));
      this.rangeModeButton = new GuiButton(100, this.width / 2 - 155, 18, 150, 20, this.getRangeModeText());
      this.presetsButton = new GuiButton(101, this.width / 2 + 5, 18, 150, 20, I18n.format("wdl.gui.entities.rangePresets", new Object[0]));
      this.mode = WDL.worldProps.getProperty("Entity.TrackDistanceMode");
      this.presetsButton.enabled = this.shouldEnablePresetsButton();
      this.buttonList.add(this.rangeModeButton);
      this.buttonList.add(this.presetsButton);
      this.entityList = new GuiEntityList(this);
   }

   public static Minecraft getMinecraft(GuiWDLEntities guiWDLEntities) {
      return guiWDLEntities.mc;
   }

   public GuiWDLEntities(GuiScreen screen) {
      this.parent = screen;
   }

   private boolean shouldEnablePresetsButton() {
      return this.mode.equals("user");
   }

   @Override
   public void onGuiClosed() {
      WDL.saveProps();
   }

   public static FontRenderer getFontRenderer(GuiWDLEntities guiWDLEntities) {
      return guiWDLEntities.fontRendererObj;
   }

   @Override
   public void mouseReleased(int mouseX, int mouseY, int state) {
      if (!this.entityList.mouseReleased(mouseX, mouseY, state)) {
         super.mouseReleased(mouseX, mouseY, state);
      }
   }

   public static String getMode(GuiWDLEntities guiWDLEntities) {
      return guiWDLEntities.mode;
   }

   @Override
   public void drawScreen(int mouseX, int mouseY, float partialTicks) {
      this.drawDefaultBackground();
      this.entityList.drawScreen(mouseX, mouseY, partialTicks);
      drawCenteredString(this.fontRendererObj, I18n.format("wdl.gui.entities.title", new Object[0]), this.width / 2, 8, 16777215);
      super.drawScreen(mouseX, mouseY, partialTicks);
   }

}
