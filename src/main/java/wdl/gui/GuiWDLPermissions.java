package wdl.gui;

import wdl.WDL;
import wdl.WDLPluginChannels;
import java.io.IOException;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;

public class GuiWDLPermissions extends GuiScreen {
   private static final int TOP_MARGIN = 61;
   private static final int BOTTOM_MARGIN = 32;
   private GuiButton reloadButton;
   private int refreshTicks = -1;
   private final GuiScreen parent;
   private TextList list;

   @Override
   public void drawScreen(int mouseX, int mouseY, float partialTicks) {
      if (this.list != null) {
         this.list.drawScreen(mouseX, mouseY, partialTicks);
         drawCenteredString(this.fontRendererObj, "Permission info", this.width / 2, 8, 16777215);
         if (!WDLPluginChannels.hasPermissions()) {
            drawCenteredString(
               this.fontRendererObj,
               "No permissions received; defaulting to everything enabled.",
               this.width / 2,
               (this.height - 32 - 23) / 2 + 23 - this.fontRendererObj.FONT_HEIGHT / 2,
               16777215
            );
         }

         super.drawScreen(mouseX, mouseY, partialTicks);
      }
   }

   @Override
   public void onGuiClosed() {
      WDL.saveProps();
   }

   @Override
   public void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
      this.list.mouseClicked(mouseX, mouseY, mouseButton);
      super.mouseClicked(mouseX, mouseY, mouseButton);
   }

   @Override
   public void mouseReleased(int mouseX, int mouseY, int state) {
      if (!this.list.mouseReleased(mouseX, mouseY, state)) {
         super.mouseReleased(mouseX, mouseY, state);
      }
   }

   @Override
   public void actionPerformed(GuiButton button) throws IOException {
      if (button.id == 1) {
         button.enabled = false;
         button.displayString = "Refershing...";
         this.refreshTicks = 50;
      }

      if (button.id == 100) {
         GuiScreen parent2 = this.parent;
         this.mc.displayGuiScreen(parent2);
      }

      if (button.id == 200) {
      }

      if (button.id == 201) {
         this.mc.displayGuiScreen(new GuiWDLPermissionRequest(this.parent));
      }

      if (button.id == 202) {
         this.mc.displayGuiScreen(new GuiWDLChunkOverrides(this.parent));
      }
   }

   @Override
   public void updateScreen() {
      if (this.refreshTicks > 0) {
         this.refreshTicks--;
      } else if (this.refreshTicks == 0) {
         this.initGui();
         this.refreshTicks = -1;
      }
   }

   public GuiWDLPermissions(GuiScreen screen) {
      this.parent = screen;
   }

   @Override
   public void handleMouseInput() throws IOException {
      super.handleMouseInput();
      this.list.handleMouseInput();
   }

   @Override
   public void initGui() {
      this.buttonList.clear();
      this.buttonList.add(new GuiButton(100, this.width / 2 - 100, this.height - 29, I18n.format("gui.done", new Object[0])));
      this.buttonList.add(new GuiButton(200, this.width / 2 - 155, 39, 100, 20, I18n.format("wdl.gui.permissions.current", new Object[0])));
      if (WDLPluginChannels.canRequestPermissions()) {
         this.buttonList.add(new GuiButton(201, this.width / 2 - 50, 39, 100, 20, I18n.format("wdl.gui.permissions.request", new Object[0])));
         this.buttonList.add(new GuiButton(202, this.width / 2 + 55, 39, 100, 20, I18n.format("wdl.gui.permissions.overrides", new Object[0])));
      }

      this.reloadButton = new GuiButton(1, this.width / 2 + 5, 18, 150, 20, "Reload permissions");
      this.buttonList.add(this.reloadButton);
      this.list = new TextList(this.mc, this.width, this.height, 61, 32);
      this.list.addLine("§c§lThis is a work in progress.");
      if (WDLPluginChannels.hasPermissions()) {
         this.list.addBlankLine();
         if (!WDLPluginChannels.canRequestPermissions()) {
            this.list
               .addLine(
                  "§cThe serverside permission plugin is out of date and does support permission requests.  Please go ask a server administrator to update the plugin."
               );
            this.list.addBlankLine();
         }

         if (WDLPluginChannels.getRequestMessage() != null) {
            this.list.addLine("Note from the server moderators: ");
            this.list.addLine(WDLPluginChannels.getRequestMessage());
            this.list.addBlankLine();
         }

         this.list.addLine("These are your current permissions:");
         this.list.addLine("Can download: " + WDLPluginChannels.canDownloadInGeneral());
         this.list.addLine("Can save chunks as you move: " + WDLPluginChannels.canCacheChunks());
         if (!WDLPluginChannels.canCacheChunks() && WDLPluginChannels.canDownloadInGeneral()) {
            this.list.addLine("Nearby chunk save radius: " + WDLPluginChannels.getSaveRadius());
         }

         this.list.addLine("Can save entities: " + WDLPluginChannels.canSaveEntities());
         this.list.addLine("Can save tile entities: " + WDLPluginChannels.canSaveTileEntities());
         this.list.addLine("Can save containers: " + WDLPluginChannels.canSaveContainers());
         this.list.addLine("Received entity ranges: " + WDLPluginChannels.hasServerEntityRange() + " (" + WDLPluginChannels.getEntityRanges().size() + " total)");
      }
   }

}
