package cc.baritone.utils;

import client.event.PreUpdateDispatcher;
import client.gui.TexturesGuiIconsPngManager;
import java.util.List;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;

public class GuiButtonPreUpdateDispatcher implements PreUpdateDispatcher {
   public final ReleaseURLController releaseURLController;
   public String labelText;
   public String labelText2;
   public String labelText3;
   public String labelText4;
   public String labelText5;
   public String labelText6;
   public final int count;
   public final Beta2SplineCalculator beta2SplineCalculator;

   @Override
   public void applyIndex(int count2, int count3, int count4) {
   }

   public static ReleaseURLController resolveReleaseURLController(GuiButtonPreUpdateDispatcher guiButtonPreUpdateDispatcher) {
      return guiButtonPreUpdateDispatcher.releaseURLController;
   }

   @Override
   public boolean checkIndex(int count2, int count3, int count4, int count5, int count6, int count7) {
      if (count7 > 0 && count7 < Beta2SplineCalculator.computeCount(this.beta2SplineCalculator)) {
         WdlGuiGuiScreenAdapter wdlGuiGuiScreenAdapter = new WdlGuiGuiScreenAdapter(
            this.beta2SplineCalculator.runtimeComponent7, this.beta2SplineCalculator.runtimeComponent7, this.releaseURLController
         );
         Beta2SplineCalculator.resolveMinecraftByBeta2SplineCalcu(this.beta2SplineCalculator).displayGuiScreen(wdlGuiGuiScreenAdapter);
         Beta2SplineCalculator.resolveMinecraft(this.beta2SplineCalculator)
            .getMcSoundHandler()
            .applyISound2(PositionedSoundRecord.createButtonPress(new ResourceLocation("gui.button.press"), 1.0F));
         return true;
      } else {
         return false;
      }
   }

   public GuiButtonPreUpdateDispatcher(Beta2SplineCalculator beta2SplineCalculator2, ReleaseURLController releaseURLController2) {
      super();
      this.beta2SplineCalculator = beta2SplineCalculator2;
      this.releaseURLController = releaseURLController2;
      this.count = RuntimeComponent7.resolveFontRendererByRuntimeComponent(beta2SplineCalculator2.runtimeComponent7).indexOffset + 1;
      this.labelText = RuntimeComponent7.resolveText(0x1d0768b80492L, beta2SplineCalculator2.runtimeComponent7, releaseURLController2);
      this.labelText2 = RuntimeComponent7.resolveText((short)0, 1133285932, beta2SplineCalculator2.runtimeComponent7, releaseURLController2, (char)61021);
      List list = CouldnOpenHelper.resolveList(releaseURLController2.labelText6, beta2SplineCalculator2.getCount20());
      this.labelText3 = list.size() >= 1 ? (String)list.get(0) : "";
      this.labelText4 = list.size() >= 2 ? (String)list.get(1) : "";
      this.labelText5 = list.size() >= 3 ? (String)list.get(2) : "";
      this.labelText6 = StatCollector.getText("wdl.gui.updates.update.releaseDate", new Object[]{releaseURLController2.labelText4});
   }

   @Override
   public void applyIndex(int count2, int count3, int count4, int count5, int count6, int count7) {
   }

   @Override
   public void applyIndex(int count2, int count3, int count4, int count5, int count6, int count7, int count8, boolean flag) {
      String text;
      if (this.beta2SplineCalculator.checkIndex(count2)) {
         text = StatCollector.getText("wdl.gui.updates.currentVersion", new Object[]{this.labelText});
      } else if (this.releaseURLController == Beta2SplineCalculator.resolveReleaseURLController(this.beta2SplineCalculator)) {
         text = StatCollector.getText("wdl.gui.updates.recomendedVersion", new Object[]{this.labelText});
      } else {
         text = this.labelText;
      }

      RuntimeComponent7.resolveFontRenderer4(this.beta2SplineCalculator.runtimeComponent7).getIndex(text, count3, count4 + this.count * 0, 16777215);
      RuntimeComponent7.resolveFontRenderer5(this.beta2SplineCalculator.runtimeComponent7).getIndex(this.labelText2, count3, count4 + this.count * 1, 8421504);
      RuntimeComponent7.resolveFontRenderer2(this.beta2SplineCalculator.runtimeComponent7).getIndex(this.labelText3, count3, count4 + this.count * 2, 16777215);
      RuntimeComponent7.resolveFontRenderer6(this.beta2SplineCalculator.runtimeComponent7).getIndex(this.labelText4, count3, count4 + this.count * 3, 16777215);
      RuntimeComponent7.resolveFontRenderer3(this.beta2SplineCalculator.runtimeComponent7).getIndex(this.labelText5, count3, count4 + this.count * 4, 16777215);
      RuntimeComponent7.resolveFontRenderer(this.beta2SplineCalculator.runtimeComponent7).getIndex(this.labelText6, count3, count4 + this.count * 5, 8421504);
      if (count7 > count3 && count7 < count3 + count5 && count8 > count4 && count8 < count4 + count6) {
         TexturesGuiIconsPngManager.processAction(count3 - 2, count4 - 2, count3 + count5 - 3, count4 + count6 + 2, 536870911);
      }
   }
}
