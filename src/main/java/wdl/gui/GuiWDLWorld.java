package wdl.gui;

import wdl.WDL;
import java.io.IOException;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;

public class GuiWDLWorld extends GuiScreen {
   private String title;
   private GuiScreen parent;
   private GuiButton allowCheatsBtn;
   private GuiButton gameModeBtn;
   private GuiButton timeBtn;
   private GuiButton weatherBtn;
   private GuiButton spawnBtn;
   private GuiButton pickSpawnBtn;
   private boolean showSpawnFields = false;
   private GuiNumericTextField spawnX;
   private GuiNumericTextField spawnY;
   private GuiNumericTextField spawnZ;
   private int spawnTextY;

   private String getWeatherText() {
      return I18n.format("wdl.gui.world.weather." + WDL.baseProps.getProperty("Weather"), new Object[0]);
   }

   @Override
   public void onGuiClosed() {
      if (this.showSpawnFields) {
         WDL.worldProps.setProperty("SpawnX", this.spawnX.getText());
         WDL.worldProps.setProperty("SpawnY", this.spawnY.getText());
         WDL.worldProps.setProperty("SpawnZ", this.spawnZ.getText());
      }

      WDL.saveProps();
   }

   public GuiWDLWorld(GuiScreen screen) {
      this.parent = screen;
   }

   @Override
   public void updateScreen() {
      this.spawnX.updateCursorCounter();
      this.spawnY.updateCursorCounter();
      this.spawnZ.updateCursorCounter();
      super.updateScreen();
   }

   private String getAllowCheatsText() {
      return I18n.format("wdl.gui.world.allowCheats." + WDL.baseProps.getProperty("AllowCheats"), new Object[0]);
   }

   @Override
   public void keyTyped(char typedChar, int keyCode) throws IOException {
      super.keyTyped(typedChar, keyCode);
      this.spawnX.textboxKeyTyped(typedChar, keyCode);
      this.spawnY.textboxKeyTyped(typedChar, keyCode);
      this.spawnZ.textboxKeyTyped(typedChar, keyCode);
   }

   private String getGameModeText() {
      return I18n.format("wdl.gui.world.gamemode." + WDL.baseProps.getProperty("GameType"), new Object[0]);
   }

   @Override
   public void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
      super.mouseClicked(mouseX, mouseY, mouseButton);
      if (this.showSpawnFields) {
         this.spawnX.mouseClicked(mouseX, mouseY, mouseButton);
         this.spawnY.mouseClicked(mouseX, mouseY, mouseButton);
         this.spawnZ.mouseClicked(mouseX, mouseY, mouseButton);
      }
   }

   private String getSpawnText() {
      return I18n.format("wdl.gui.world.spawn." + WDL.worldProps.getProperty("Spawn"), new Object[0]);
   }

   private void updateSpawnTextBoxVisibility() {
      boolean flag = WDL.worldProps.getProperty("Spawn").equals("xyz");
      this.showSpawnFields = flag;
      this.pickSpawnBtn.visible = flag;
   }

   @Override
   public void initGui() {
      this.buttonList.clear();
      this.title = I18n.format("wdl.gui.world.title", new Object[]{WDL.baseFolderName.replace('@', ':')});
      int n = this.height / 4 - 15;
      this.gameModeBtn = new GuiButton(1, this.width / 2 - 100, n, this.getGameModeText());
      this.buttonList.add(this.gameModeBtn);
      n += 22;
      this.allowCheatsBtn = new GuiButton(6, this.width / 2 - 100, n, this.getAllowCheatsText());
      this.buttonList.add(this.allowCheatsBtn);
      n += 22;
      this.timeBtn = new GuiButton(2, this.width / 2 - 100, n, this.getTimeText());
      this.buttonList.add(this.timeBtn);
      n += 22;
      this.weatherBtn = new GuiButton(3, this.width / 2 - 100, n, this.getWeatherText());
      this.buttonList.add(this.weatherBtn);
      n += 22;
      this.spawnBtn = new GuiButton(4, this.width / 2 - 100, n, this.getSpawnText());
      this.buttonList.add(this.spawnBtn);
      n += 22;
      this.spawnTextY = n + 4;
      this.spawnX = new GuiNumericTextField(40, this.fontRendererObj, this.width / 2 - 87, n, 50, 16);
      this.spawnY = new GuiNumericTextField(41, this.fontRendererObj, this.width / 2 - 19, n, 50, 16);
      this.spawnZ = new GuiNumericTextField(42, this.fontRendererObj, this.width / 2 + 48, n, 50, 16);
      this.spawnX.setText(WDL.worldProps.getProperty("SpawnX"));
      this.spawnY.setText(WDL.worldProps.getProperty("SpawnY"));
      this.spawnZ.setText(WDL.worldProps.getProperty("SpawnZ"));
      this.spawnX.setMaxStringLength(7);
      this.spawnY.setMaxStringLength(7);
      this.spawnZ.setMaxStringLength(7);
      n += 18;
      this.pickSpawnBtn = new GuiButton(5, this.width / 2, n, 100, 20, I18n.format("wdl.gui.world.setSpawnToCurrentPosition", new Object[0]));
      this.buttonList.add(this.pickSpawnBtn);
      this.updateSpawnTextBoxVisibility();
      this.buttonList.add(new GuiButton(100, this.width / 2 - 100, this.height - 29, I18n.format("gui.done", new Object[0])));
   }

   private void cycleTime() {
      String property = WDL.baseProps.getProperty("Time");
      if (property.equals("keep")) {
         WDL.baseProps.setProperty("Time", "23000");
      } else if (property.equals("23000")) {
         WDL.baseProps.setProperty("Time", "0");
      } else if (property.equals("0")) {
         WDL.baseProps.setProperty("Time", "6000");
      } else if (property.equals("6000")) {
         WDL.baseProps.setProperty("Time", "11500");
      } else if (property.equals("11500")) {
         WDL.baseProps.setProperty("Time", "12500");
      } else if (property.equals("12500")) {
         WDL.baseProps.setProperty("Time", "18000");
      } else {
         WDL.baseProps.setProperty("Time", "keep");
      }

      this.timeBtn.displayString = this.getTimeText();
   }

   private void cycleAllowCheats() {
      if (WDL.baseProps.getProperty("AllowCheats").equals("true")) {
         WDL.baseProps.setProperty("AllowCheats", "false");
      } else {
         WDL.baseProps.setProperty("AllowCheats", "true");
      }

      this.allowCheatsBtn.displayString = this.getAllowCheatsText();
   }

   @Override
   public void actionPerformed(GuiButton button) {
      if (button.enabled) {
         if (button.id == 1) {
            this.cycleGameMode();
         } else if (button.id == 2) {
            this.cycleTime();
         } else if (button.id == 3) {
            this.cycleWeather();
         } else if (button.id == 4) {
            this.cycleSpawn();
         } else if (button.id == 5) {
            this.setSpawnToPlayerPosition();
         } else if (button.id == 6) {
            this.cycleAllowCheats();
         } else if (button.id == 100) {
            GuiScreen parent2 = this.parent;
            this.mc.displayGuiScreen(parent2);
         }
      }
   }

   private void cycleGameMode() {
      String property = WDL.baseProps.getProperty("GameType");
      if (property.equals("keep")) {
         WDL.baseProps.setProperty("GameType", "creative");
      } else if (property.equals("creative")) {
         WDL.baseProps.setProperty("GameType", "survival");
      } else if (property.equals("survival")) {
         WDL.baseProps.setProperty("GameType", "hardcore");
      } else if (property.equals("hardcore")) {
         WDL.baseProps.setProperty("GameType", "keep");
      }

      this.gameModeBtn.displayString = this.getGameModeText();
   }

   private void cycleWeather() {
      String property = WDL.baseProps.getProperty("Weather");
      if (property.equals("keep")) {
         WDL.baseProps.setProperty("Weather", "sunny");
      } else if (property.equals("sunny")) {
         WDL.baseProps.setProperty("Weather", "rain");
      } else if (property.equals("rain")) {
         WDL.baseProps.setProperty("Weather", "thunderstorm");
      } else if (property.equals("thunderstorm")) {
         WDL.baseProps.setProperty("Weather", "keep");
      }

      this.weatherBtn.displayString = this.getWeatherText();
   }

   private String getTimeText() {
      String str = I18n.format("wdl.gui.world.time." + WDL.baseProps.getProperty("Time"), new Object[0]);
      if (str.startsWith("wdl.gui.world.time.")) {
         str = I18n.format("wdl.gui.world.time.custom", new Object[]{WDL.baseProps.getProperty("Time")});
      }

      return str;
   }

   @Override
   public void drawScreen(int mouseX, int mouseY, float partialTicks) {
      Utils.drawListBackground(23, 32, 0, 0, this.height, this.width);
      drawCenteredString(this.fontRendererObj, this.title, this.width / 2, 8, 16777215);
      if (this.showSpawnFields) {
         this.drawString(this.fontRendererObj, "X:", this.width / 2 - 99, this.spawnTextY, 16777215);
         this.drawString(this.fontRendererObj, "Y:", this.width / 2 - 31, this.spawnTextY, 16777215);
         this.drawString(this.fontRendererObj, "Z:", this.width / 2 + 37, this.spawnTextY, 16777215);
         this.spawnX.drawTextBox();
         this.spawnY.drawTextBox();
         this.spawnZ.drawTextBox();
      }

      super.drawScreen(mouseX, mouseY, partialTicks);
      String str = null;
      if (this.allowCheatsBtn.isMouseOver()) {
         str = I18n.format("wdl.gui.world.allowCheats.description", new Object[0]);
      } else if (this.gameModeBtn.isMouseOver()) {
         str = I18n.format("wdl.gui.world.gamemode.description", new Object[0]);
      } else if (this.timeBtn.isMouseOver()) {
         str = I18n.format("wdl.gui.world.time.description", new Object[0]);
      } else if (this.weatherBtn.isMouseOver()) {
         str = I18n.format("wdl.gui.world.weather.description", new Object[0]);
      } else if (this.spawnBtn.isMouseOver()) {
         str = I18n.format("wdl.gui.world.spawn.description", new Object[0]);
      } else if (this.pickSpawnBtn.isMouseOver()) {
         str = I18n.format("wdl.gui.world.setSpawnToCurrentPosition.description", new Object[0]);
      }

      if (this.showSpawnFields) {
         if (Utils.isMouseOverTextBox(mouseX, mouseY, this.spawnX)) {
            str = I18n.format("wdl.gui.world.spawnPos.description", new Object[]{"X"});
         } else if (Utils.isMouseOverTextBox(mouseX, mouseY, this.spawnY)) {
            str = I18n.format("wdl.gui.world.spawnPos.description", new Object[]{"Y"});
         } else if (Utils.isMouseOverTextBox(mouseX, mouseY, this.spawnZ)) {
            str = I18n.format("wdl.gui.world.spawnPos.description", new Object[]{"Z"});
         }
      }

      Utils.drawGuiInfoBox(str, this.width, this.height, 48);
   }

   private void cycleSpawn() {
      String property = WDL.worldProps.getProperty("Spawn");
      if (property.equals("auto")) {
         WDL.worldProps.setProperty("Spawn", "player");
      } else if (property.equals("player")) {
         WDL.worldProps.setProperty("Spawn", "xyz");
      } else if (property.equals("xyz")) {
         WDL.worldProps.setProperty("Spawn", "auto");
      }

      this.spawnBtn.displayString = this.getSpawnText();
      this.updateSpawnTextBoxVisibility();
   }

   private void setSpawnToPlayerPosition() {
      this.spawnX.setValue((int)WDL.thePlayer.posX);
      this.spawnY.setValue((int)WDL.thePlayer.posY);
      this.spawnZ.setValue((int)WDL.thePlayer.posZ);
   }

}
