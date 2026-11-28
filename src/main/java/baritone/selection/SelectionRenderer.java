package baritone.selection;

import baritone.Baritone;
import baritone.api.event.events.RenderEvent;
import baritone.api.event.listener.AbstractGameEventListener;
import baritone.api.selection.ISelection;
import baritone.utils.IRenderer;
import java.awt.Color;
import net.minecraft.util.AxisAlignedBB;

public class SelectionRenderer implements AbstractGameEventListener, IRenderer {
   public static final double SELECTION_BOX_EXPANSION = 0.005;
   private final SelectionManager manager;

   @Override
   public void onRenderPass(RenderEvent renderEvent) {
      renderSelections(this.manager.getSelections());
   }

   public SelectionRenderer(Baritone baritone, SelectionManager selectionManager) {
      this.manager = selectionManager;
      baritone.getGameEventHandler().registerEventListener(this);
   }

   public static void renderSelections(ISelection[] selections) {
      float value = (Float)settings.selectionOpacity.value;
      boolean value2 = (Boolean)settings.renderSelectionIgnoreDepth.value;
      float value3 = (Float)settings.selectionLineWidth.value;
      if ((Boolean)settings.renderSelection.value && selections.length != 0) {
         IRenderer.startLines((Color)settings.colorSelection.value, value, value3, value2);

         for (ISelection selection : selections) {
            IRenderer.emitAABB(selection.aabb(), 0.005);
         }

         if ((Boolean)settings.renderSelectionCorners.value) {
            IRenderer.glColor((Color)settings.colorSelectionPos1.value, value);

            for (ISelection selection2 : selections) {
               IRenderer.emitAABB(new AxisAlignedBB(selection2.pos1(), selection2.pos1().add(1, 1, 1)));
            }

            IRenderer.glColor((Color)settings.colorSelectionPos2.value, value);

            for (ISelection selection3 : selections) {
               IRenderer.emitAABB(new AxisAlignedBB(selection3.pos2(), selection3.pos2().add(1, 1, 1)));
            }
         }

         IRenderer.endLines(value2);
      }
   }
}
