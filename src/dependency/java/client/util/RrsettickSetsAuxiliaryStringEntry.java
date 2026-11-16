package client.util;

import augustus.modules.misc.RotationRenderer;
import client.Client;
import client.module.Module;

public class RrsettickSetsAuxiliaryStringEntry extends AuxiliaryStringEntry {
   public RrsettickSetsAuxiliaryStringEntry() {
      super("rrsettick");
   }

   @Override
   public void updateState() {
      RayTraceUtil.applyString("§c" + this.getString() + "§7 (sets the tick inside the rotationrenderer)");
   }

   @Override
   public void printStackTrace(String[] textArray, String text) {
      if (textArray.length < 2) {
         RayTraceUtil.applyString(".rrsettick [tick]");
      } else {
         RotationRenderer rotationRenderer = Client.getClient().getModuleManager().rotationRenderer;
         if (!((Module)rotationRenderer).isToggled()) {
            RayTraceUtil.applyString("RotationRenderer isn't enabled");
         } else {
            try {
               int count = Integer.parseInt(textArray[1]);
               if (rotationRenderer.itemList4.size() <= count) {
                  RayTraceUtil.applyString("There are only " + rotationRenderer.itemList4.size() + " ticks available");
                  return;
               }

               rotationRenderer.reachDistance = (double)count;
               RayTraceUtil.applyString("Set tick to " + count);
            } catch (NumberFormatException numberFormatException) {
               RayTraceUtil.applyString("Tick must be an integer!");
            }
         }
      }
   }
}
