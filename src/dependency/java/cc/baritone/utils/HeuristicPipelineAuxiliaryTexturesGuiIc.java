package cc.baritone.utils;

import client.gui.AuxiliaryTexturesGuiIconsPngManagerAdapter;
import net.minecraft.client.Minecraft;

public class HeuristicPipelineAuxiliaryTexturesGuiIc extends AuxiliaryTexturesGuiIconsPngManagerAdapter {
   public final HeuristicPipeline heuristicPipeline;

   @Override
   public void applyMinecraftByMinecraft(Minecraft mc, int count, int count2) {
      DefinedDimensionsRecord definedDimensionsRecord = this.getDefinedDimensionsRecord();
      if (definedDimensionsRecord == null) {
         this.labelText = "";
         this.enabled3 = false;
      } else {
         this.labelText = definedDimensionsRecord.labelText2;
         this.enabled3 = true;
      }

      if (definedDimensionsRecord != null && definedDimensionsRecord == HeuristicPipeline.resolveDefinedDimensionsRecord(this.heuristicPipeline)) {
         processAction(this.count - 2, this.count3 - 2, this.count + this.count4 + 2, this.count3 + this.count5 + 2, -16744704);
      }

      super.applyMinecraftByMinecraft(mc, count, count2);
   }

   public HeuristicPipelineAuxiliaryTexturesGuiIc(HeuristicPipeline heuristicPipeline2, int count, int count2, int count3, int count4, int count5) {
      super(count, count2, count3, count4, count5, "");
      this.heuristicPipeline = heuristicPipeline2;
   }

   public DefinedDimensionsRecord getDefinedDimensionsRecord() {
      int count = HeuristicPipeline.computeCount(this.heuristicPipeline) + this.count2;
      if (count < 0) {
         return null;
      } else {
         return count >= HeuristicPipeline.resolveList(this.heuristicPipeline).size()
            ? null
            : (DefinedDimensionsRecord)HeuristicPipeline.resolveList(this.heuristicPipeline).get(count);
      }
   }

   public HeuristicPipelineAuxiliaryTexturesGuiIc(HeuristicPipeline heuristicPipeline2, int count, int count2, int count3, String text, String text2) {
      super(count, count2, count3, "");
      this.heuristicPipeline = heuristicPipeline2;
   }
}
