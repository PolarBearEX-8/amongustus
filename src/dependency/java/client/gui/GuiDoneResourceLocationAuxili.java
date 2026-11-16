package client.gui;

import client.util.CurveEvaluator;
import net.minecraft.util.StatCollector;

public class GuiDoneResourceLocationAuxili extends ResourceLocationAuxiliaryTexturesGuiIc {
   public final CurveEvaluator curveEvaluator;

   public GuiDoneResourceLocationAuxili(CurveEvaluator curveEvaluator2, int count, int count2, int count3) {
      super(count, count2, count3, CurveEvaluator.getResourceLocation5(), 90, 220);
      this.curveEvaluator = curveEvaluator2;
   }

   @Override
   public void applyIndexByIndex(int count, int count2) {
      CurveEvaluator.applyCurveEvaluator(this.curveEvaluator, StatCollector.getText("gui.done", new Object[0]), count, count2);
   }
}
