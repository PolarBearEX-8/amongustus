package cc.baritone.utils;

import cc.baritone.command.SelectionCommand;
import java.awt.Color;
import net.minecraft.util.AxisAlignedBB;

public class DepthFirstSystem implements ExtendedListener {
   public final SelectionCommand selectionCommand;

   @Override
   public void forEach(DepthFirstTracker depthFirstTracker) {
      if ((Boolean)BaritoneManager.getSettings().renderSelectionCorners.dataRef
         && SelectionCommand.resolveBetterBlockPosXSYSZSBlockPos(this.selectionCommand) != null) {
         Color dataRef = (Color)BaritoneManager.getSettings().colorSelectionPos1.dataRef;
         float dataRef2 = (Float)BaritoneManager.getSettings().selectionOpacity.dataRef;
         float dataRef3 = (Float)BaritoneManager.getSettings().selectionLineWidth.dataRef;
         boolean dataRef4 = (Boolean)BaritoneManager.getSettings().renderSelectionIgnoreDepth.dataRef;
         TessellatorCallback.applyColor(dataRef, dataRef2, dataRef3, dataRef4);
         TessellatorCallback.applyAxisAlignedBBByAxisAlignedBB(
            new AxisAlignedBB(
               SelectionCommand.resolveBetterBlockPosXSYSZSBlockPos(this.selectionCommand),
               SelectionCommand.resolveBetterBlockPosXSYSZSBlockPos(this.selectionCommand).add(1, 1, 1)
            )
         );
         TessellatorCallback.setEnabled(dataRef4);
      }
   }

   public DepthFirstSystem(SelectionCommand selectionCommand2) {
      this.selectionCommand = selectionCommand2;
   }
}
