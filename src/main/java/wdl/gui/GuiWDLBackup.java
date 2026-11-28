package wdl.gui;

import wdl.WDL;
import wdl.WorldBackupType;
import java.io.IOException;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;

public class GuiWDLBackup extends GuiScreen {
   private GuiScreen parent;
   private String description;
   private WorldBackupType backupType;

   @Override
   public void onGuiClosed() {
      WDL.baseProps.setProperty("Backup", this.backupType.name());
      WDL.saveProps();
   }

   public GuiWDLBackup(GuiScreen screen) {
      this.parent = screen;
      this.description = I18n.format("wdl.gui.backup.description1", new Object[0])
         + "\n\n"
         + I18n.format("wdl.gui.backup.description2", new Object[0])
         + "\n\n"
         + I18n.format("wdl.gui.backup.description3", new Object[0]);
   }

   @Override
   public void initGui() {
      this.backupType = WorldBackupType.match(WDL.baseProps.getProperty("Backup", "ZIP"));
      this.buttonList.add(new GuiButton(0, this.width / 2 - 100, 32, this.getBackupButtonText()));
      this.buttonList.add(new GuiButton(100, this.width / 2 - 100, this.height - 29, I18n.format("gui.done", new Object[0])));
   }

   @Override
   public void actionPerformed(GuiButton button) throws IOException {
      if (button.enabled) {
         if (button.id == 0) {
            switch (GuiWDLBackupTypeSwitch.SWITCH_MAP[this.backupType.ordinal()]) {
               case 1:
                  this.backupType = WorldBackupType.FOLDER;
                  break;
               case 2:
                  this.backupType = WorldBackupType.ZIP;
                  break;
               case 3:
                  this.backupType = WorldBackupType.NONE;
            }

            button.displayString = this.getBackupButtonText();
         } else if (button.id == 100) {
            GuiScreen parent2 = this.parent;
            this.mc.displayGuiScreen(parent2);
         }
      }
   }

   private String getBackupButtonText() {
      return I18n.format("wdl.gui.backup.backupMode", new Object[]{this.backupType.getDescription()});
   }

   @Override
   public void drawScreen(int mouseX, int mouseY, float partialTicks) {
      Utils.drawListBackground(23, 32, 0, 0, this.height, this.width);
      drawCenteredString(this.fontRendererObj, I18n.format("wdl.gui.backup.title", new Object[0]), this.width / 2, 8, 16777215);
      super.drawScreen(mouseX, mouseY, partialTicks);
      Utils.drawGuiInfoBox(this.description, this.width - 50, 3 * this.height / 5, this.width, this.height, 48);
   }

}
