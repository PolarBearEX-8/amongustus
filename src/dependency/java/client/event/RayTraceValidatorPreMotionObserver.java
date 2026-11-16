package client.event;

import client.util.GeometryPool;
import client.util.RayTraceValidator;
import java.util.List;
import net.minecraft.client.gui.GuiScreen;

public class RayTraceValidatorPreMotionObserver implements PreMotionObserver {
   public final RayTraceValidator rayTraceValidator;

   @Override
   public void applyFlag(boolean flag, int count) {
      List list = this.rayTraceValidator.geometryPool.resolveList(this.rayTraceValidator);
      GeometryPool geometryPool = this.rayTraceValidator.geometryPool;
      this.rayTraceValidator.mc.displayGuiScreen((GuiScreen)geometryPool);
      if (flag) {
         list.remove(this.rayTraceValidator);
         this.rayTraceValidator.geometryPool.getList().add(0, this.rayTraceValidator);
      }
   }

   public RayTraceValidatorPreMotionObserver(RayTraceValidator rayTraceValidator2) {
      this.rayTraceValidator = rayTraceValidator2;
   }
}
