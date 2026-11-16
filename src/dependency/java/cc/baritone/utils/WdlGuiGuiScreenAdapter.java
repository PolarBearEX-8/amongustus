package cc.baritone.utils;

import client.gui.AuxiliaryTexturesGuiIconsPngManagerAdapter;
import java.io.IOException;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.StatCollector;

public class WdlGuiGuiScreenAdapter extends GuiScreen {
   public final RuntimeComponent7 runtimeComponent7;
   public final ReleaseURLController releaseURLController;
   public PreUpdateDispatcherSplineCalculator preUpdateDispatcherSplineCalculator;
   public final RuntimeComponent7 runtimeComponent2;

   @Override
   public void applyIndex(int count, int count2, int count3) {
      if (!this.preUpdateDispatcherSplineCalculator.checkIndexByIndex(count, count2, count3)) {
         super.applyIndex(count, count2, count3);
      }
   }

   @Override
   public void processAction(int count, int count2, int count3) throws IOException {
      this.preUpdateDispatcherSplineCalculator.checkIndex(count, count2, count3);
      super.processAction(count, count2, count3);
   }

   @Override
   public void updateState3() throws IOException {
      super.updateState3();
      this.preUpdateDispatcherSplineCalculator.updateState();
   }

   @Override
   public void applyIndex(int count, int count2, float factor) {
      if (this.preUpdateDispatcherSplineCalculator != null) {
         this.preUpdateDispatcherSplineCalculator.applyIndex(count, count2, factor);
         applyFontRenderer4(
            this.fontRenderer, RuntimeComponent7.resolveText(0x1d0768b80492L, this.runtimeComponent2, this.releaseURLController), this.width / 2, 8, 16777215
         );
         super.applyIndex(count, count2, factor);
      }
   }

   @Override
   public void applyAuxiliaryTexturesGuiIconsP(AuxiliaryTexturesGuiIconsPngManagerAdapter auxiliaryTexturesGuiIconsPngManagerAdapter) throws IOException {
      if (auxiliaryTexturesGuiIconsPngManagerAdapter.count2 == 0) {
         CouldnOpenHelper.applyText(this.releaseURLController.labelText2);
      }

      if (auxiliaryTexturesGuiIconsPngManagerAdapter.count2 == 1) {
         CouldnOpenHelper.applyText(this.releaseURLController.minecraftMinecraftCompaController.labelText3);
      }

      if (auxiliaryTexturesGuiIconsPngManagerAdapter.count2 == 100) {
         RuntimeComponent7 runtimeComponent = this.runtimeComponent7;
         this.mc.displayGuiScreen((GuiScreen)runtimeComponent);
      }
   }

   public WdlGuiGuiScreenAdapter(RuntimeComponent7 runtimeComponent, RuntimeComponent7 runtimeComponent3, ReleaseURLController releaseURLController2) {
      this.runtimeComponent2 = runtimeComponent;
      this.runtimeComponent7 = runtimeComponent3;
      this.releaseURLController = releaseURLController2;
   }

   @Override
   public void updateState2() {
      this.list
         .add(
            new AuxiliaryTexturesGuiIconsPngManagerAdapter(
               0, this.width / 2 - 155, 18, 150, 20, StatCollector.getText("wdl.gui.updates.update.viewOnline", new Object[0])
            )
         );
      if (this.releaseURLController.minecraftMinecraftCompaController != null) {
         this.list
            .add(
               new AuxiliaryTexturesGuiIconsPngManagerAdapter(
                  1, this.width / 2 + 5, 18, 150, 20, StatCollector.getText("wdl.gui.updates.update.viewForumPost", new Object[0])
               )
            );
      }

      this.list
         .add(new AuxiliaryTexturesGuiIconsPngManagerAdapter(100, this.width / 2 - 100, this.height - 29, StatCollector.getText("gui.done", new Object[0])));
      this.preUpdateDispatcherSplineCalculator = new PreUpdateDispatcherSplineCalculator(this.mc, this.width, this.height, 39, 32);
      this.preUpdateDispatcherSplineCalculator.applyText(RuntimeComponent7.resolveText(0x1d0768b80492L, this.runtimeComponent2, this.releaseURLController));
      this.preUpdateDispatcherSplineCalculator
         .applyText(StatCollector.getText("wdl.gui.updates.update.releaseDate", new Object[]{this.releaseURLController.labelText4}));
      this.preUpdateDispatcherSplineCalculator
         .applyText(RuntimeComponent7.resolveText((short)0, 1133285932, this.runtimeComponent2, this.releaseURLController, (char)61021));
      this.preUpdateDispatcherSplineCalculator.add();
      this.preUpdateDispatcherSplineCalculator.applyText(this.releaseURLController.labelText6);
   }
}
