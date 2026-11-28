package wdl.gui;

import wdl.WDL;
import java.io.IOException;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;

public class GuiWDLPlayer extends GuiScreen {
   private String title;
   private GuiScreen parent;
   private GuiButton healthBtn;
   private GuiButton hungerBtn;
   private GuiButton playerPosBtn;
   private GuiButton pickPosToPlayerPosBtn;
   private boolean showPosFields = false;
   private GuiNumericTextField posX;
   private GuiNumericTextField posY;
   private GuiNumericTextField posZ;
   private int posTextY;

   @Override
   public void drawScreen(int mouseX, int mouseY, float partialTicks) {
      Utils.drawListBackground(23, 32, 0, 0, this.height, this.width);
      drawCenteredString(this.fontRendererObj, this.title, this.width / 2, 8, 16777215);
      String str = null;
      if (this.showPosFields) {
         this.drawString(this.fontRendererObj, "X:", this.width / 2 - 99, this.posTextY, 16777215);
         this.drawString(this.fontRendererObj, "Y:", this.width / 2 - 31, this.posTextY, 16777215);
         this.drawString(this.fontRendererObj, "Z:", this.width / 2 + 37, this.posTextY, 16777215);
         this.posX.drawTextBox();
         this.posY.drawTextBox();
         this.posZ.drawTextBox();
         if (Utils.isMouseOverTextBox(mouseX, mouseY, this.posX)) {
            str = I18n.format("wdl.gui.player.positionTextBox.description", new Object[]{"X"});
         } else if (Utils.isMouseOverTextBox(mouseX, mouseY, this.posY)) {
            str = I18n.format("wdl.gui.player.positionTextBox.description", new Object[]{"Y"});
         } else if (Utils.isMouseOverTextBox(mouseX, mouseY, this.posZ)) {
            str = I18n.format("wdl.gui.player.positionTextBox.description", new Object[]{"Z"});
         }

         if (this.pickPosToPlayerPosBtn.isMouseOver()) {
            str = I18n.format("wdl.gui.player.setPositionToCurrentPosition.description", new Object[0]);
         }
      }

      if (this.healthBtn.isMouseOver()) {
         str = I18n.format("wdl.gui.player.health.description", new Object[0]);
      }

      if (this.hungerBtn.isMouseOver()) {
         str = I18n.format("wdl.gui.player.hunger.description", new Object[0]);
      }

      if (this.playerPosBtn.isMouseOver()) {
         str = I18n.format("wdl.gui.player.position.description", new Object[0]);
      }

      super.drawScreen(mouseX, mouseY, partialTicks);
      if (str != null) {
         Utils.drawGuiInfoBox(str, this.width, this.height, 48);
      }
   }

   private void cycleHealth() {
      String property = WDL.baseProps.getProperty("PlayerHealth");
      if (property.equals("keep")) {
         WDL.baseProps.setProperty("PlayerHealth", "20");
      } else if (property.equals("20")) {
         WDL.baseProps.setProperty("PlayerHealth", "keep");
      }

      this.healthBtn.displayString = this.getHealthText();
   }

   @Override
   public void onGuiClosed() {
      if (this.showPosFields) {
         WDL.worldProps.setProperty("PlayerX", this.posX.getText());
         WDL.worldProps.setProperty("PlayerY", this.posY.getText());
         WDL.worldProps.setProperty("PlayerZ", this.posZ.getText());
      }

      WDL.saveProps();
   }

   private void setPlayerPosToPlayerPosition() {
      this.posX.setValue((int)WDL.thePlayer.posX);
      this.posY.setValue((int)WDL.thePlayer.posY);
      this.posZ.setValue((int)WDL.thePlayer.posZ);
   }

   private String getHungerText() {
      String str = I18n.format("wdl.gui.player.hunger." + WDL.baseProps.getProperty("PlayerFood"), new Object[0]);
      if (str.startsWith("wdl.gui.player.hunger.")) {
         str = I18n.format("wdl.gui.player.hunger.custom", new Object[]{WDL.baseProps.getProperty("PlayerFood")});
      }

      return str;
   }

   private void cyclePlayerPos() {
      String property = WDL.worldProps.getProperty("PlayerPos");
      if (property.equals("keep")) {
         WDL.worldProps.setProperty("PlayerPos", "xyz");
      } else if (property.equals("xyz")) {
         WDL.worldProps.setProperty("PlayerPos", "keep");
      }

      this.playerPosBtn.displayString = this.getPlayerPosText();
      this.updatePlayerPosVisibility();
   }

   @Override
   public void updateScreen() {
      this.posX.updateCursorCounter();
      this.posY.updateCursorCounter();
      this.posZ.updateCursorCounter();
      super.updateScreen();
   }

   private void cycleHunger() {
      String property = WDL.baseProps.getProperty("PlayerFood");
      if (property.equals("keep")) {
         WDL.baseProps.setProperty("PlayerFood", "20");
      } else if (property.equals("20")) {
         WDL.baseProps.setProperty("PlayerFood", "keep");
      }

      this.hungerBtn.displayString = this.getHungerText();
   }

   private void updatePlayerPosVisibility() {
      this.showPosFields = WDL.worldProps.getProperty("PlayerPos").equals("xyz");
      this.pickPosToPlayerPosBtn.visible = this.showPosFields;
   }

   public GuiWDLPlayer(GuiScreen screen) {
      this.parent = screen;
   }

   private String getHealthText() {
      String str = I18n.format("wdl.gui.player.health." + WDL.baseProps.getProperty("PlayerHealth"), new Object[0]);
      if (str.startsWith("wdl.gui.player.health.")) {
         str = I18n.format("wdl.gui.player.health.custom", new Object[]{WDL.baseProps.getProperty("PlayerHealth")});
      }

      return str;
   }

   @Override
   public void actionPerformed(GuiButton button) {
      if (button.enabled) {
         if (button.id == 1) {
            this.cycleHealth();
         } else if (button.id == 2) {
            this.cycleHunger();
         } else if (button.id == 3) {
            this.cyclePlayerPos();
         } else if (button.id == 4) {
            this.setPlayerPosToPlayerPosition();
         } else if (button.id == 100) {
            GuiScreen parent2 = this.parent;
            this.mc.displayGuiScreen(parent2);
         }
      }
   }

   @Override
   public void initGui() {
      this.buttonList.clear();
      this.title = I18n.format("wdl.gui.player.title", new Object[]{WDL.baseFolderName.replace('@', ':')});
      int n = this.height / 4 - 15;
      this.healthBtn = new GuiButton(1, this.width / 2 - 100, n, this.getHealthText());
      this.buttonList.add(this.healthBtn);
      n += 22;
      this.hungerBtn = new GuiButton(2, this.width / 2 - 100, n, this.getHungerText());
      this.buttonList.add(this.hungerBtn);
      n += 22;
      this.playerPosBtn = new GuiButton(3, this.width / 2 - 100, n, this.getPlayerPosText());
      this.buttonList.add(this.playerPosBtn);
      n += 22;
      this.posTextY = n + 4;
      this.posX = new GuiNumericTextField(40, this.fontRendererObj, this.width / 2 - 87, n, 50, 16);
      this.posY = new GuiNumericTextField(41, this.fontRendererObj, this.width / 2 - 19, n, 50, 16);
      this.posZ = new GuiNumericTextField(42, this.fontRendererObj, this.width / 2 + 48, n, 50, 16);
      this.posX.setText(WDL.worldProps.getProperty("PlayerX"));
      this.posY.setText(WDL.worldProps.getProperty("PlayerY"));
      this.posZ.setText(WDL.worldProps.getProperty("PlayerZ"));
      this.posX.setMaxStringLength(7);
      this.posY.setMaxStringLength(7);
      this.posZ.setMaxStringLength(7);
      n += 18;
      this.pickPosToPlayerPosBtn = new GuiButton(4, this.width / 2 - 0, n, 100, 20, I18n.format("wdl.gui.player.setPositionToCurrentPosition", new Object[0]));
      this.buttonList.add(this.pickPosToPlayerPosBtn);
      this.updatePlayerPosVisibility();
      this.buttonList.add(new GuiButton(100, this.width / 2 - 100, this.height - 29, I18n.format("gui.done", new Object[0])));
   }

   @Override
   public void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
      super.mouseClicked(mouseX, mouseY, mouseButton);
      if (this.showPosFields) {
         this.posX.mouseClicked(mouseX, mouseY, mouseButton);
         this.posY.mouseClicked(mouseX, mouseY, mouseButton);
         this.posZ.mouseClicked(mouseX, mouseY, mouseButton);
      }
   }

   private String getPlayerPosText() {
      return I18n.format("wdl.gui.player.position." + WDL.worldProps.getProperty("PlayerPos"), new Object[0]);
   }

   @Override
   public void keyTyped(char typedChar, int keyCode) throws IOException {
      super.keyTyped(typedChar, keyCode);
      this.posX.textboxKeyTyped(typedChar, keyCode);
      this.posY.textboxKeyTyped(typedChar, keyCode);
      this.posZ.textboxKeyTyped(typedChar, keyCode);
   }

}
