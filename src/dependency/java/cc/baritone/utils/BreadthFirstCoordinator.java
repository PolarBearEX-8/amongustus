package cc.baritone.utils;

import java.awt.Color;
import net.minecraft.util.AxisAlignedBB;

public class BreadthFirstCoordinator implements TessellatorCallback, ExtendedListener {
   public static final double value = 0.005;
   public final ListenerBridge listenerBridge;

   public static void applyManagedListenerArray(ManagedListener[] managedListenerArray) {
      float dataRef = (Float)settings.selectionOpacity.dataRef;
      boolean dataRef2 = (Boolean)settings.renderSelectionIgnoreDepth.dataRef;
      float dataRef3 = (Float)settings.selectionLineWidth.dataRef;
      if ((Boolean)settings.renderSelection.dataRef && managedListenerArray.length != 0) {
         TessellatorCallback.applyColor((Color)settings.colorSelection.dataRef, dataRef, dataRef3, dataRef2);
         ManagedListener[] managedListenerArray2 = managedListenerArray;
         int length = managedListenerArray.length;

         for (int index = 0; index < length; index++) {
            TessellatorCallback.applyAxisAlignedBB(managedListenerArray2[index].getAxisAlignedBB(), 0.005);
         }

         if ((Boolean)settings.renderSelectionCorners.dataRef) {
            TessellatorCallback.applyColor((Color)settings.colorSelectionPos1.dataRef, dataRef);

            for (ManagedListener managedListener : managedListenerArray) {
               TessellatorCallback.applyAxisAlignedBBByAxisAlignedBB(
                  new AxisAlignedBB(managedListener.getBetterBlockPosXSYSZSBlockPos(), managedListener.getBetterBlockPosXSYSZSBlockPos().add(1, 1, 1))
               );
            }

            TessellatorCallback.applyColor((Color)settings.colorSelectionPos2.dataRef, dataRef);

            for (ManagedListener managedListener2 : managedListenerArray) {
               TessellatorCallback.applyAxisAlignedBBByAxisAlignedBB(
                  new AxisAlignedBB(managedListener2.getBetterBlockPosXSYSZSBlockPos2(), managedListener2.getBetterBlockPosXSYSZSBlockPos2().add(1, 1, 1))
               );
            }
         }

         TessellatorCallback.setEnabled(dataRef2);
      }
   }

   @Override
   public void forEach(DepthFirstTracker depthFirstTracker) {
      applyManagedListenerArray(this.listenerBridge.getManagedListenerArrayAsC44OArray());
   }

   public BreadthFirstCoordinator(BaritoneManager baritoneManager, ListenerBridge listenerBridge2) {
      this.listenerBridge = listenerBridge2;
      baritoneManager.getCompositeListener().add(this);
   }
}
