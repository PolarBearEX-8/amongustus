package net.augustus.modules.player.crafter;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.client.gui.inventory.GuiCrafting;

public class CraftingStepExecutor {
   private List steps = new ArrayList();
   private int currentStepIndex = -1;

   public void setSteps(List list) {
      this.steps = list;
      this.currentStepIndex = 0;
   }

   public boolean isFinished() {
      return this.currentStepIndex < 0 || this.currentStepIndex >= this.steps.size();
   }

   public boolean executeNextStep(CraftingInventoryActions craftingInventoryActions, GuiCrafting guiCrafting) {
      if (this.isFinished()) {
         return false;
      } else {
         boolean flag = ((CraftingStep)this.steps.get(this.currentStepIndex)).execute(craftingInventoryActions, guiCrafting);
         if (flag) {
            this.currentStepIndex++;
         }

         return flag;
      }
   }

   public void reset() {
      this.steps = new ArrayList();
      this.currentStepIndex = -1;
   }
}
