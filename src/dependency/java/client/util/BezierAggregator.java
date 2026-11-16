package client.util;

import java.util.concurrent.Callable;
import net.minecraft.client.renderer.RenderGlobal;
import net.minecraft.crash.CrashReportCategory;

public class BezierAggregator implements Callable {
   public final double value;
   public final double value2;
   public final double value3;
   public final RenderGlobal renderGlobal;

   public BezierAggregator(RenderGlobal renderGlobal2, double posX, double posY, double posZ) {
      this.renderGlobal = renderGlobal2;
      this.value = posX;
      this.value2 = posY;
      this.value3 = posZ;
   }

   public String getText() throws Exception {
      return CrashReportCategory.resolveText(this.value, this.value2, this.value3);
   }

   @Override
   public Object call() throws Exception {
      return this.getText();
   }
}
