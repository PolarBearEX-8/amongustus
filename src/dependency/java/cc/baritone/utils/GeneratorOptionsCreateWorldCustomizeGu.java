package cc.baritone.utils;

import client.gui.AuxiliaryTexturesGuiIconsPngManagerAdapter;
import java.io.IOException;
import net.minecraft.client.gui.CreateWorldCustomizeGuiScreen;

public class GeneratorOptionsCreateWorldCustomizeGu extends CreateWorldCustomizeGuiScreen {
   public final RandomSeedMapGeneratorGuiScreen randomSeedMapGeneratorGuiScreen;

   @Override
   public void setProperty(String property) {
      String text = property;
      if (property == null) {
         text = "";
      }

      Pokechu22WorldDownloaderManager.properties2.setProperty("GeneratorOptions", text);
   }

   public GeneratorOptionsCreateWorldCustomizeGu(RandomSeedMapGeneratorGuiScreen randomSeedMapGeneratorGuiScreen2) {
      super(null, Pokechu22WorldDownloaderManager.properties2.getProperty("GeneratorOptions", ""));
      this.randomSeedMapGeneratorGuiScreen = randomSeedMapGeneratorGuiScreen2;
   }

   @Override
   public String getProperty() {
      return Pokechu22WorldDownloaderManager.properties2.getProperty("GeneratorOptions", "");
   }

   @Override
   public void updateState2() {
      RandomSeedMapGeneratorGuiScreen randomSeedMapGeneratorGuiScreen2 = this.randomSeedMapGeneratorGuiScreen;
      this.mc.displayGuiScreen(randomSeedMapGeneratorGuiScreen2);
   }

   @Override
   public void applyIndex(int count, int count2, float factor) {
   }

   @Override
   public void applyAuxiliaryTexturesGuiIconsP(AuxiliaryTexturesGuiIconsPngManagerAdapter auxiliaryTexturesGuiIconsPngManagerAdapter) throws IOException {
   }
}
