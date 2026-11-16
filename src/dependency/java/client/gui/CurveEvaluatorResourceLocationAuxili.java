package client.gui;

import client.util.CurveEvaluator;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.potion.Potion;
import net.minecraft.util.StatCollector;

public class CurveEvaluatorResourceLocationAuxili extends ResourceLocationAuxiliaryTexturesGuiIc {
   public final int count8;
   public final int count9;
   public final CurveEvaluator curveEvaluator;

   @Override
   public void applyIndexByIndex(int count, int count2) {
      String text = StatCollector.getText(Potion.potionArray[this.count8].getText(), new Object[0]);
      if (this.count9 >= 3 && this.count8 != Potion.potion11.indexOffset3) {
         text = text + " II";
      }

      CurveEvaluator.applyCurveEvaluator2(this.curveEvaluator, text, count, count2);
   }

   public CurveEvaluatorResourceLocationAuxili(CurveEvaluator curveEvaluator2, int count, int count2, int count3, int count4, int count5) {
      super(
         count,
         count2,
         count3,
         GuiContainer.resourceLocation4,
         0 + Potion.potionArray[count4].getIndexOffset() % 8 * 18,
         198 + Potion.potionArray[count4].getIndexOffset() / 8 * 18
      );
      this.curveEvaluator = curveEvaluator2;
      this.count8 = count4;
      this.count9 = count5;
   }
}
