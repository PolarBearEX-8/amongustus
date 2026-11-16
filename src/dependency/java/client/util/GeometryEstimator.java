package client.util;

import client.gui.AuxiliaryTexturesGuiIconsPngManagerAdapter;
import client.gui.GuiButtonAuxiliaryTexturesGuiIc;
import client.setting.AutoPlayTargetEntity;
import client.setting.GameSettings;
import java.io.IOException;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.StatCollector;

public class GeometryEstimator extends GuiScreen {
   public final GameSettings gameSettings;
   public String labelText = "Options";
   public String labelText2;

   @Override
   public void updateState2() {
      this.labelText = StatCollector.getText("options.sounds.title", new Object[0]);
      this.labelText2 = StatCollector.getText("options.off", new Object[0]);
      this.list
         .add(
            new GuiButtonAuxiliaryTexturesGuiIc(
               this, AutoPlayTargetEntity.MASTER.getCount(), this.width / 2 - 155 + 0 % 2 * 160, this.height / 6 - 12 + 0, AutoPlayTargetEntity.MASTER, true
            )
         );
      int count = 2;

      for (AutoPlayTargetEntity autoPlayTargetEntity : AutoPlayTargetEntity.values()) {
         if (autoPlayTargetEntity != AutoPlayTargetEntity.MASTER && autoPlayTargetEntity != AutoPlayTargetEntity.AUGUSTUS) {
            this.list
               .add(
                  new GuiButtonAuxiliaryTexturesGuiIc(
                     this, autoPlayTargetEntity.getCount(), this.width / 2 - 155 + count % 2 * 160, this.height / 6 - 12 + 24 * (count >> 1), autoPlayTargetEntity, false
                  )
               );
            count++;
         }
      }

      this.list
         .add(
            new GuiButtonAuxiliaryTexturesGuiIc(
               this,
               AutoPlayTargetEntity.AUGUSTUS.getCount(),
               this.width / 2 - 155 + count % 2 * 160,
               this.height / 6 - 12 + 24 * (count >> 1),
               AutoPlayTargetEntity.AUGUSTUS,
               true
            )
         );
      this.list
         .add(
            new AuxiliaryTexturesGuiIconsPngManagerAdapter(200, this.width / 2 - 100, this.height / 6 + 168, StatCollector.getText("gui.done", new Object[0]))
         );
   }

   @Override
   public void applyIndex(int count, int count2, float factor) {
      this.renderElement3();
      applyFontRenderer4(this.fontRenderer, this.labelText, this.width / 2, 15, 16777215);
      super.applyIndex(count, count2, factor);
   }

   @Override
   public void applyAuxiliaryTexturesGuiIconsP(AuxiliaryTexturesGuiIconsPngManagerAdapter auxiliaryTexturesGuiIconsPngManagerAdapter) throws IOException {
      if (auxiliaryTexturesGuiIconsPngManagerAdapter.enabled3 && auxiliaryTexturesGuiIconsPngManagerAdapter.count2 == 200) {
         this.mc.gameSettings.updateState();
         GuiScreen guiScreen = this.guiScreen;
         this.mc.displayGuiScreen(guiScreen);
      }
   }

   public static GameSettings resolveGameSettings(GeometryEstimator geometryEstimator) {
      return geometryEstimator.gameSettings;
   }

   public GeometryEstimator(GuiScreen screen, GameSettings gameSettings2) {
      super(screen);
      this.gameSettings = gameSettings2;
      GuiScreen.set.add(this);
   }

   public String resolveText(AutoPlayTargetEntity autoPlayTargetEntity) {
      float scaleFactor = this.gameSettings.getScaleFactor(autoPlayTargetEntity);
      return scaleFactor == 0.0F ? this.labelText2 : (int)(scaleFactor * 100.0F) + "%";
   }
}
