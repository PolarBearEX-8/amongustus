package cc.baritone.utils;

import client.gui.AuxiliaryTexturesGuiIconsPngManagerAdapter;
import java.io.IOException;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.StatCollector;

public class WdlGuiGuiScreenImpl extends GuiScreen {
   public String labelText;
   public GuiScreen guiScreen2;
   public AuxiliaryTexturesGuiIconsPngManagerAdapter auxiliaryTexturesGuiIconsPngManagerAdapter2;
   public AuxiliaryTexturesGuiIconsPngManagerAdapter auxiliaryTexturesGuiIconsPngManagerAdapter3;
   public AuxiliaryTexturesGuiIconsPngManagerAdapter auxiliaryTexturesGuiIconsPngManagerAdapter4;
   public AuxiliaryTexturesGuiIconsPngManagerAdapter auxiliaryTexturesGuiIconsPngManagerAdapter5;
   public AuxiliaryTexturesGuiIconsPngManagerAdapter auxiliaryTexturesGuiIconsPngManagerAdapter6;
   public AuxiliaryTexturesGuiIconsPngManagerAdapter auxiliaryTexturesGuiIconsPngManagerAdapter7;
   public boolean enabled6 = false;
   public InternalTexturesGuiIco internalTexturesGuiIco;
   public InternalTexturesGuiIco internalTexturesGuiIco2;
   public InternalTexturesGuiIco internalTexturesGuiIco3;
   public int count2;

   @Override
   public void applyIndex(int count, int count3, float factor) {
      CouldnOpenHelper.applyIndex(23, 32, 0, 0, this.height, this.width);
      applyFontRenderer4(this.fontRenderer, this.labelText, this.width / 2, 8, 16777215);
      if (this.enabled6) {
         this.applyFontRenderer(this.fontRenderer, "X:", this.width / 2 - 99, this.count2, 16777215);
         this.applyFontRenderer(this.fontRenderer, "Y:", this.width / 2 - 31, this.count2, 16777215);
         this.applyFontRenderer(this.fontRenderer, "Z:", this.width / 2 + 37, this.count2, 16777215);
         this.internalTexturesGuiIco.updateState();
         this.internalTexturesGuiIco2.updateState();
         this.internalTexturesGuiIco3.updateState();
      }

      super.applyIndex(count, count3, factor);
      String text = null;
      if (this.auxiliaryTexturesGuiIconsPngManagerAdapter2.isEnabled()) {
         text = StatCollector.getText("wdl.gui.world.allowCheats.description", new Object[0]);
      } else if (this.auxiliaryTexturesGuiIconsPngManagerAdapter3.isEnabled()) {
         text = StatCollector.getText("wdl.gui.world.gamemode.description", new Object[0]);
      } else if (this.auxiliaryTexturesGuiIconsPngManagerAdapter4.isEnabled()) {
         text = StatCollector.getText("wdl.gui.world.time.description", new Object[0]);
      } else if (this.auxiliaryTexturesGuiIconsPngManagerAdapter5.isEnabled()) {
         text = StatCollector.getText("wdl.gui.world.weather.description", new Object[0]);
      } else if (this.auxiliaryTexturesGuiIconsPngManagerAdapter6.isEnabled()) {
         text = StatCollector.getText("wdl.gui.world.spawn.description", new Object[0]);
      } else if (this.auxiliaryTexturesGuiIconsPngManagerAdapter7.isEnabled()) {
         text = StatCollector.getText("wdl.gui.world.setSpawnToCurrentPosition.description", new Object[0]);
      }

      if (this.enabled6) {
         if (CouldnOpenHelper.matchesInternalTexturesGuiIconsPn(count, count3, this.internalTexturesGuiIco)) {
            text = StatCollector.getText("wdl.gui.world.spawnPos.description", new Object[]{"X"});
         } else if (CouldnOpenHelper.matchesInternalTexturesGuiIconsPn(count, count3, this.internalTexturesGuiIco2)) {
            text = StatCollector.getText("wdl.gui.world.spawnPos.description", new Object[]{"Y"});
         } else if (CouldnOpenHelper.matchesInternalTexturesGuiIconsPn(count, count3, this.internalTexturesGuiIco3)) {
            text = StatCollector.getText("wdl.gui.world.spawnPos.description", new Object[]{"Z"});
         }
      }

      CouldnOpenHelper.applyText(text, this.width, this.height, 48);
   }

   @Override
   public void applyAuxiliaryTexturesGuiIconsP(AuxiliaryTexturesGuiIconsPngManagerAdapter auxiliaryTexturesGuiIconsPngManagerAdapter) {
      if (auxiliaryTexturesGuiIconsPngManagerAdapter.enabled3) {
         if (auxiliaryTexturesGuiIconsPngManagerAdapter.count2 == 1) {
            this.updateState9();
         } else if (auxiliaryTexturesGuiIconsPngManagerAdapter.count2 == 2) {
            this.updateState10();
         } else if (auxiliaryTexturesGuiIconsPngManagerAdapter.count2 == 3) {
            this.updateState7();
         } else if (auxiliaryTexturesGuiIconsPngManagerAdapter.count2 == 4) {
            this.updateState();
         } else if (auxiliaryTexturesGuiIconsPngManagerAdapter.count2 == 5) {
            this.updateState5();
         } else if (auxiliaryTexturesGuiIconsPngManagerAdapter.count2 == 6) {
            this.updateState6();
         } else if (auxiliaryTexturesGuiIconsPngManagerAdapter.count2 == 100) {
            GuiScreen guiScreen = this.guiScreen2;
            this.mc.displayGuiScreen(guiScreen);
         }
      }
   }

   private void updateState6() {
      if (Pokechu22WorldDownloaderManager.properties4.getProperty("AllowCheats").equals("true")) {
         Pokechu22WorldDownloaderManager.properties4.setProperty("AllowCheats", "false");
      } else {
         Pokechu22WorldDownloaderManager.properties4.setProperty("AllowCheats", "true");
      }

      this.auxiliaryTexturesGuiIconsPngManagerAdapter2.labelText = this.getTextAsText();
   }

   private void updateState7() {
      String property = Pokechu22WorldDownloaderManager.properties4.getProperty("Weather");
      if (property.equals("keep")) {
         Pokechu22WorldDownloaderManager.properties4.setProperty("Weather", "sunny");
      } else if (property.equals("sunny")) {
         Pokechu22WorldDownloaderManager.properties4.setProperty("Weather", "rain");
      } else if (property.equals("rain")) {
         Pokechu22WorldDownloaderManager.properties4.setProperty("Weather", "thunderstorm");
      } else if (property.equals("thunderstorm")) {
         Pokechu22WorldDownloaderManager.properties4.setProperty("Weather", "keep");
      }

      this.auxiliaryTexturesGuiIconsPngManagerAdapter5.labelText = this.getText3();
   }

   @Override
   public void updateState2() {
      this.list.clear();
      this.labelText = StatCollector.getText("wdl.gui.world.title", new Object[]{Pokechu22WorldDownloaderManager.labelText2.replace('@', ':')});
      int count = this.height / 4 - 15;
      this.auxiliaryTexturesGuiIconsPngManagerAdapter3 = new AuxiliaryTexturesGuiIconsPngManagerAdapter(1, this.width / 2 - 100, count, this.getText2());
      this.list.add(this.auxiliaryTexturesGuiIconsPngManagerAdapter3);
      count += 22;
      this.auxiliaryTexturesGuiIconsPngManagerAdapter2 = new AuxiliaryTexturesGuiIconsPngManagerAdapter(6, this.width / 2 - 100, count, this.getTextAsText());
      this.list.add(this.auxiliaryTexturesGuiIconsPngManagerAdapter2);
      count += 22;
      this.auxiliaryTexturesGuiIconsPngManagerAdapter4 = new AuxiliaryTexturesGuiIconsPngManagerAdapter(2, this.width / 2 - 100, count, this.getText4());
      this.list.add(this.auxiliaryTexturesGuiIconsPngManagerAdapter4);
      count += 22;
      this.auxiliaryTexturesGuiIconsPngManagerAdapter5 = new AuxiliaryTexturesGuiIconsPngManagerAdapter(3, this.width / 2 - 100, count, this.getText3());
      this.list.add(this.auxiliaryTexturesGuiIconsPngManagerAdapter5);
      count += 22;
      this.auxiliaryTexturesGuiIconsPngManagerAdapter6 = new AuxiliaryTexturesGuiIconsPngManagerAdapter(4, this.width / 2 - 100, count, this.getText5());
      this.list.add(this.auxiliaryTexturesGuiIconsPngManagerAdapter6);
      count += 22;
      this.count2 = count + 4;
      this.internalTexturesGuiIco = new InternalTexturesGuiIco(40, this.fontRenderer, this.width / 2 - 87, count, 50, 16);
      this.internalTexturesGuiIco2 = new InternalTexturesGuiIco(41, this.fontRenderer, this.width / 2 - 19, count, 50, 16);
      this.internalTexturesGuiIco3 = new InternalTexturesGuiIco(42, this.fontRenderer, this.width / 2 + 48, count, 50, 16);
      this.internalTexturesGuiIco.applyText(Pokechu22WorldDownloaderManager.properties2.getProperty("SpawnX"));
      this.internalTexturesGuiIco2.applyText(Pokechu22WorldDownloaderManager.properties2.getProperty("SpawnY"));
      this.internalTexturesGuiIco3.applyText(Pokechu22WorldDownloaderManager.properties2.getProperty("SpawnZ"));
      this.internalTexturesGuiIco.applyIndex3(7);
      this.internalTexturesGuiIco2.applyIndex3(7);
      this.internalTexturesGuiIco3.applyIndex3(7);
      count += 18;
      this.auxiliaryTexturesGuiIconsPngManagerAdapter7 = new AuxiliaryTexturesGuiIconsPngManagerAdapter(
         5, this.width / 2, count, 100, 20, StatCollector.getText("wdl.gui.world.setSpawnToCurrentPosition", new Object[0])
      );
      this.list.add(this.auxiliaryTexturesGuiIconsPngManagerAdapter7);
      this.updateState8();
      this.list
         .add(new AuxiliaryTexturesGuiIconsPngManagerAdapter(100, this.width / 2 - 100, this.height - 29, StatCollector.getText("gui.done", new Object[0])));
   }

   @Override
   public void processAction(int count, int count3, int count4) throws IOException {
      super.processAction(count, count3, count4);
      if (this.enabled6) {
         this.internalTexturesGuiIco.applyIndex(count, count3, count4);
         this.internalTexturesGuiIco2.applyIndex(count, count3, count4);
         this.internalTexturesGuiIco3.applyIndex(count, count3, count4);
      }
   }

   private void updateState8() {
      boolean flag = Pokechu22WorldDownloaderManager.properties2.getProperty("Spawn").equals("xyz");
      this.enabled6 = flag;
      this.auxiliaryTexturesGuiIconsPngManagerAdapter7.enabled2 = flag;
   }

   private void updateState9() {
      String property = Pokechu22WorldDownloaderManager.properties4.getProperty("GameType");
      if (property.equals("keep")) {
         Pokechu22WorldDownloaderManager.properties4.setProperty("GameType", "creative");
      } else if (property.equals("creative")) {
         Pokechu22WorldDownloaderManager.properties4.setProperty("GameType", "survival");
      } else if (property.equals("survival")) {
         Pokechu22WorldDownloaderManager.properties4.setProperty("GameType", "hardcore");
      } else if (property.equals("hardcore")) {
         Pokechu22WorldDownloaderManager.properties4.setProperty("GameType", "keep");
      }

      this.auxiliaryTexturesGuiIconsPngManagerAdapter3.labelText = this.getText2();
   }

   private void updateState10() {
      String property = Pokechu22WorldDownloaderManager.properties4.getProperty("Time");
      if (property.equals("keep")) {
         Pokechu22WorldDownloaderManager.properties4.setProperty("Time", "23000");
      } else if (property.equals("23000")) {
         Pokechu22WorldDownloaderManager.properties4.setProperty("Time", "0");
      } else if (property.equals("0")) {
         Pokechu22WorldDownloaderManager.properties4.setProperty("Time", "6000");
      } else if (property.equals("6000")) {
         Pokechu22WorldDownloaderManager.properties4.setProperty("Time", "11500");
      } else if (property.equals("11500")) {
         Pokechu22WorldDownloaderManager.properties4.setProperty("Time", "12500");
      } else if (property.equals("12500")) {
         Pokechu22WorldDownloaderManager.properties4.setProperty("Time", "18000");
      } else {
         Pokechu22WorldDownloaderManager.properties4.setProperty("Time", "keep");
      }

      this.auxiliaryTexturesGuiIconsPngManagerAdapter4.labelText = this.getText4();
   }

   @Override
   public void applyChar(char character, int count) throws IOException {
      super.applyChar(character, count);
      this.internalTexturesGuiIco.checkChar(character, count);
      this.internalTexturesGuiIco2.checkChar(character, count);
      this.internalTexturesGuiIco3.checkChar(character, count);
   }

   private String getText3() {
      return StatCollector.getText("wdl.gui.world.weather." + Pokechu22WorldDownloaderManager.properties4.getProperty("Weather"), new Object[0]);
   }

   public WdlGuiGuiScreenImpl(GuiScreen screen) {
      this.guiScreen2 = screen;
   }

   @Override
   public void enableRepeatEvents() {
      if (this.enabled6) {
         Pokechu22WorldDownloaderManager.properties2.setProperty("SpawnX", this.internalTexturesGuiIco.getLabelText());
         Pokechu22WorldDownloaderManager.properties2.setProperty("SpawnY", this.internalTexturesGuiIco2.getLabelText());
         Pokechu22WorldDownloaderManager.properties2.setProperty("SpawnZ", this.internalTexturesGuiIco3.getLabelText());
      }

      Pokechu22WorldDownloaderManager.updateState2();
   }

   private String getText5() {
      return StatCollector.getText("wdl.gui.world.spawn." + Pokechu22WorldDownloaderManager.properties2.getProperty("Spawn"), new Object[0]);
   }

   private void updateState() {
      String property = Pokechu22WorldDownloaderManager.properties2.getProperty("Spawn");
      if (property.equals("auto")) {
         Pokechu22WorldDownloaderManager.properties2.setProperty("Spawn", "player");
      } else if (property.equals("player")) {
         Pokechu22WorldDownloaderManager.properties2.setProperty("Spawn", "xyz");
      } else if (property.equals("xyz")) {
         Pokechu22WorldDownloaderManager.properties2.setProperty("Spawn", "auto");
      }

      this.auxiliaryTexturesGuiIconsPngManagerAdapter6.labelText = this.getText5();
      this.updateState8();
   }

   private String getText4() {
      String text = StatCollector.getText("wdl.gui.world.time." + Pokechu22WorldDownloaderManager.properties4.getProperty("Time"), new Object[0]);
      if (text.startsWith("wdl.gui.world.time.")) {
         text = StatCollector.getText("wdl.gui.world.time.custom", new Object[]{Pokechu22WorldDownloaderManager.properties4.getProperty("Time")});
      }

      return text;
   }

   private void updateState5() {
      this.internalTexturesGuiIco.applyIndex6((int)Pokechu22WorldDownloaderManager.entityPlayerSP.posX);
      this.internalTexturesGuiIco2.applyIndex6((int)Pokechu22WorldDownloaderManager.entityPlayerSP.posY);
      this.internalTexturesGuiIco3.applyIndex6((int)Pokechu22WorldDownloaderManager.entityPlayerSP.posZ);
   }

   private String getTextAsText() {
      return StatCollector.getText("wdl.gui.world.allowCheats." + Pokechu22WorldDownloaderManager.properties4.getProperty("AllowCheats"), new Object[0]);
   }

   private String getText2() {
      return StatCollector.getText("wdl.gui.world.gamemode." + Pokechu22WorldDownloaderManager.properties4.getProperty("GameType"), new Object[0]);
   }

   @Override
   public void updateState4() {
      this.internalTexturesGuiIco.renderElement();
      this.internalTexturesGuiIco2.renderElement();
      this.internalTexturesGuiIco3.renderElement();
      super.updateState4();
   }
}
