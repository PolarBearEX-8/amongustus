package cc.baritone.utils;

import client.gui.AuxiliaryTexturesGuiIconsPngManagerAdapter;
import client.gui.InternalTexturesGuiIconsPngManagerAdapter;
import java.io.IOException;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ClassicFlatGuiScreen;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.OptionsCustomizeTitleGuiScreen;
import net.minecraft.util.StatCollector;

public class RandomSeedMapGeneratorGuiScreen extends GuiScreen {
   public String labelText;
   public GuiScreen guiScreen2;
   public InternalTexturesGuiIconsPngManagerAdapter internalTexturesGuiIconsPngManagerAdapter;
   public AuxiliaryTexturesGuiIconsPngManagerAdapter auxiliaryTexturesGuiIconsPngManagerAdapter2;
   public AuxiliaryTexturesGuiIconsPngManagerAdapter auxiliaryTexturesGuiIconsPngManagerAdapter3;
   public AuxiliaryTexturesGuiIconsPngManagerAdapter auxiliaryTexturesGuiIconsPngManagerAdapter4;
   public String labelText2;

   @Override
   public void enableRepeatEvents() {
      Pokechu22WorldDownloaderManager.properties2.setProperty("RandomSeed", this.internalTexturesGuiIconsPngManagerAdapter.getLabelText());
      Pokechu22WorldDownloaderManager.updateState2();
   }

   private void updateState5() {
      if (Pokechu22WorldDownloaderManager.properties2.getProperty("MapGenerator", "").equals("flat")) {
         this.auxiliaryTexturesGuiIconsPngManagerAdapter4.enabled2 = true;
         this.auxiliaryTexturesGuiIconsPngManagerAdapter4.labelText = StatCollector.getText("wdl.gui.generator.flatSettings", new Object[0]);
      } else if (Pokechu22WorldDownloaderManager.properties2.getProperty("MapGenerator", "").equals("custom")) {
         this.auxiliaryTexturesGuiIconsPngManagerAdapter4.enabled2 = true;
         this.auxiliaryTexturesGuiIconsPngManagerAdapter4.labelText = StatCollector.getText("wdl.gui.generator.customSettings", new Object[0]);
      } else {
         this.auxiliaryTexturesGuiIconsPngManagerAdapter4.enabled2 = false;
      }
   }

   private String getTextAsText() {
      return StatCollector.getText("wdl.gui.generator.generator." + Pokechu22WorldDownloaderManager.properties2.getProperty("MapGenerator"), new Object[0]);
   }

   @Override
   public void applyAuxiliaryTexturesGuiIconsP(AuxiliaryTexturesGuiIconsPngManagerAdapter auxiliaryTexturesGuiIconsPngManagerAdapter) {
      if (auxiliaryTexturesGuiIconsPngManagerAdapter.enabled3) {
         if (auxiliaryTexturesGuiIconsPngManagerAdapter.count2 == 1) {
            this.updateState();
         } else if (auxiliaryTexturesGuiIconsPngManagerAdapter.count2 == 2) {
            this.updateState6();
         } else if (auxiliaryTexturesGuiIconsPngManagerAdapter.count2 == 3) {
            if (Pokechu22WorldDownloaderManager.properties2.getProperty("MapGenerator", "").equals("flat")) {
               Minecraft mc = this.mc;
               ClassicFlatGuiScreen classicFlatGuiScreen = new ClassicFlatGuiScreen(new GeneratorOptionsCreateWorldCustomizeGu(this));
               mc.displayGuiScreen(classicFlatGuiScreen);
            } else if (Pokechu22WorldDownloaderManager.properties2.getProperty("MapGenerator", "").equals("custom")) {
               Minecraft mc2 = this.mc;
               OptionsCustomizeTitleGuiScreen optionsCustomizeTitleGuiScreen = new OptionsCustomizeTitleGuiScreen(
                  new RuntimeComponent6((byte)0, 927407, this, 11166012), Pokechu22WorldDownloaderManager.properties2.getProperty("GeneratorOptions", "")
               );
               mc2.displayGuiScreen(optionsCustomizeTitleGuiScreen);
            }
         } else if (auxiliaryTexturesGuiIconsPngManagerAdapter.count2 == 100) {
            GuiScreen guiScreen = this.guiScreen2;
            this.mc.displayGuiScreen(guiScreen);
         }
      }
   }

   private String getText2() {
      return StatCollector.getText(
         "wdl.gui.generator.generateStructures." + Pokechu22WorldDownloaderManager.properties2.getProperty("MapFeatures"), new Object[0]
      );
   }

   private void updateState6() {
      if (Pokechu22WorldDownloaderManager.properties2.getProperty("MapFeatures").equals("true")) {
         Pokechu22WorldDownloaderManager.properties2.setProperty("MapFeatures", "false");
      } else {
         Pokechu22WorldDownloaderManager.properties2.setProperty("MapFeatures", "true");
      }

      this.auxiliaryTexturesGuiIconsPngManagerAdapter3.labelText = this.getText2();
   }

   private void updateState() {
      String property = Pokechu22WorldDownloaderManager.properties2.getProperty("MapGenerator");
      if (property.equals("void")) {
         Pokechu22WorldDownloaderManager.properties2.setProperty("MapGenerator", "default");
         Pokechu22WorldDownloaderManager.properties2.setProperty("GeneratorName", "default");
         Pokechu22WorldDownloaderManager.properties2.setProperty("GeneratorVersion", "1");
         Pokechu22WorldDownloaderManager.properties2.setProperty("GeneratorOptions", "");
      } else if (property.equals("default")) {
         Pokechu22WorldDownloaderManager.properties2.setProperty("MapGenerator", "flat");
         Pokechu22WorldDownloaderManager.properties2.setProperty("GeneratorName", "flat");
         Pokechu22WorldDownloaderManager.properties2.setProperty("GeneratorVersion", "0");
         Pokechu22WorldDownloaderManager.properties2.setProperty("GeneratorOptions", "");
      } else if (property.equals("flat")) {
         Pokechu22WorldDownloaderManager.properties2.setProperty("MapGenerator", "largeBiomes");
         Pokechu22WorldDownloaderManager.properties2.setProperty("GeneratorName", "largeBiomes");
         Pokechu22WorldDownloaderManager.properties2.setProperty("GeneratorVersion", "0");
         Pokechu22WorldDownloaderManager.properties2.setProperty("GeneratorOptions", "");
      } else if (property.equals("largeBiomes")) {
         Pokechu22WorldDownloaderManager.properties2.setProperty("MapGenerator", "amplified");
         Pokechu22WorldDownloaderManager.properties2.setProperty("GeneratorName", "amplified");
         Pokechu22WorldDownloaderManager.properties2.setProperty("GeneratorVersion", "0");
         Pokechu22WorldDownloaderManager.properties2.setProperty("GeneratorOptions", "");
      } else if (property.equals("amplified")) {
         Pokechu22WorldDownloaderManager.properties2.setProperty("MapGenerator", "custom");
         Pokechu22WorldDownloaderManager.properties2.setProperty("GeneratorName", "custom");
         Pokechu22WorldDownloaderManager.properties2.setProperty("GeneratorVersion", "0");
         Pokechu22WorldDownloaderManager.properties2.setProperty("GeneratorOptions", "");
      } else if (property.equals("custom")) {
         Pokechu22WorldDownloaderManager.properties2.setProperty("MapGenerator", "legacy");
         Pokechu22WorldDownloaderManager.properties2.setProperty("GeneratorName", "default_1_1");
         Pokechu22WorldDownloaderManager.properties2.setProperty("GeneratorVersion", "0");
         Pokechu22WorldDownloaderManager.properties2.setProperty("GeneratorOptions", "");
      } else {
         Pokechu22WorldDownloaderManager.properties2.setProperty("MapGenerator", "void");
         Pokechu22WorldDownloaderManager.properties2.setProperty("GeneratorName", "flat");
         Pokechu22WorldDownloaderManager.properties2.setProperty("GeneratorVersion", "0");
         Pokechu22WorldDownloaderManager.properties2.setProperty("GeneratorOptions", ";0");
      }

      this.auxiliaryTexturesGuiIconsPngManagerAdapter2.labelText = this.getTextAsText();
      this.updateState5();
   }

   @Override
   public void processAction(int count, int count2, int count3) throws IOException {
      super.processAction(count, count2, count3);
      this.internalTexturesGuiIconsPngManagerAdapter.applyIndex(count, count2, count3);
   }

   @Override
   public void applyIndex(int count, int count2, float factor) {
      CouldnOpenHelper.applyIndex(23, 32, 0, 0, this.height, this.width);
      applyFontRenderer4(this.fontRenderer, this.labelText, this.width / 2, 8, 16777215);
      this.applyFontRenderer(this.fontRenderer, this.labelText2, this.width / 2 - 100, this.height / 4 - 10, 16777215);
      this.internalTexturesGuiIconsPngManagerAdapter.updateState();
      super.applyIndex(count, count2, factor);
      String text = null;
      if (CouldnOpenHelper.matchesInternalTexturesGuiIconsPn(count, count2, this.internalTexturesGuiIconsPngManagerAdapter)) {
         text = StatCollector.getText("wdl.gui.generator.seed.description", new Object[0]);
      } else if (this.auxiliaryTexturesGuiIconsPngManagerAdapter2.isEnabled()) {
         text = StatCollector.getText("wdl.gui.generator.generator.description", new Object[0]);
      } else if (this.auxiliaryTexturesGuiIconsPngManagerAdapter3.isEnabled()) {
         text = StatCollector.getText("wdl.gui.generator.generateStructures.description", new Object[0]);
      }

      CouldnOpenHelper.applyText(text, this.width, this.height, 48);
   }

   @Override
   public void updateState4() {
      this.internalTexturesGuiIconsPngManagerAdapter.renderElement();
      super.updateState4();
   }

   @Override
   public void updateState2() {
      this.labelText2 = StatCollector.getText("wdl.gui.generator.seed", new Object[0]);
      int index = this.fontRenderer.getIndex(this.labelText2 + " ");
      this.list.clear();
      this.labelText = StatCollector.getText("wdl.gui.generator.title", new Object[]{Pokechu22WorldDownloaderManager.labelText2.replace('@', ':')});
      int count = this.height / 4 - 15;
      this.internalTexturesGuiIconsPngManagerAdapter = new InternalTexturesGuiIconsPngManagerAdapter(
         40, this.fontRenderer, this.width / 2 - (100 - index), count, 200 - index, 18
      );
      this.internalTexturesGuiIconsPngManagerAdapter.applyText(Pokechu22WorldDownloaderManager.properties2.getProperty("RandomSeed"));
      count += 22;
      this.auxiliaryTexturesGuiIconsPngManagerAdapter2 = new AuxiliaryTexturesGuiIconsPngManagerAdapter(1, this.width / 2 - 100, count, this.getTextAsText());
      this.list.add(this.auxiliaryTexturesGuiIconsPngManagerAdapter2);
      count += 22;
      this.auxiliaryTexturesGuiIconsPngManagerAdapter3 = new AuxiliaryTexturesGuiIconsPngManagerAdapter(2, this.width / 2 - 100, count, this.getText2());
      this.list.add(this.auxiliaryTexturesGuiIconsPngManagerAdapter3);
      count += 22;
      this.auxiliaryTexturesGuiIconsPngManagerAdapter4 = new AuxiliaryTexturesGuiIconsPngManagerAdapter(3, this.width / 2 - 100, count, "");
      this.updateState5();
      this.list.add(this.auxiliaryTexturesGuiIconsPngManagerAdapter4);
      this.list
         .add(new AuxiliaryTexturesGuiIconsPngManagerAdapter(100, this.width / 2 - 100, this.height - 29, StatCollector.getText("gui.done", new Object[0])));
   }

   public RandomSeedMapGeneratorGuiScreen(GuiScreen screen) {
      this.guiScreen2 = screen;
   }

   @Override
   public void applyChar(char character, int count) throws IOException {
      super.applyChar(character, count);
      this.internalTexturesGuiIconsPngManagerAdapter.checkChar(character, count);
   }
}
