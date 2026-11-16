package cc.baritone.utils;

import client.event.PreUpdateDispatcher;
import client.util.SplineCalculator;
import java.util.List;
import net.minecraft.client.Minecraft;

public class Beta2SplineCalculator extends SplineCalculator {
   public List list;
   public ReleaseURLController releaseURLController;
   public final RuntimeComponent7 runtimeComponent7;

   public GuiButtonPreUpdateDispatcher getByIndex(int count) {
      return (GuiButtonPreUpdateDispatcher)this.list.get(count);
   }

   @Override
   public int getCount22() {
      return this.count9 - 10;
   }

   public static int computeCount(Beta2SplineCalculator beta2SplineCalculator) {
      return beta2SplineCalculator.count5;
   }

   @Override
   public boolean checkIndex(int count) {
      GuiButtonPreUpdateDispatcher byIndex = this.getByIndex(count);
      return "1.8.9a-beta2".equals(GuiButtonPreUpdateDispatcher.resolveReleaseURLController(byIndex).labelText);
   }

   public static void applyBeta2SplineCalculator(Beta2SplineCalculator beta2SplineCalculator) {
      beta2SplineCalculator.updateState4();
   }

   public static ReleaseURLController resolveReleaseURLController(Beta2SplineCalculator beta2SplineCalculator) {
      return beta2SplineCalculator.releaseURLController;
   }

   private void updateState4() {
      this.list = new java.util.ArrayList();
      if (WorldDownloaderUpdateThread.isActive()) {
         this.releaseURLController = WorldDownloaderUpdateThread.getReleaseURLController();
      } else {
         this.releaseURLController = null;
      }

      List list2 = WorldDownloaderUpdateThread.getList();
      if (list2 != null) {
         for (ReleaseURLController releaseURLController2 : (Iterable<ReleaseURLController>)(Iterable<?>)list2) {
            this.list.add(new GuiButtonPreUpdateDispatcher(this, releaseURLController2));
         }
      }
   }

   @Override
   public PreUpdateDispatcher get(int count) {
      return this.getByIndex(count);
   }

   public static Minecraft resolveMinecraftByBeta2SplineCalcu(Beta2SplineCalculator beta2SplineCalculator) {
      return beta2SplineCalculator.mc;
   }

   public Beta2SplineCalculator(RuntimeComponent7 runtimeComponent) {
      super(
         RuntimeComponent7.resolveMinecraft(runtimeComponent),
         runtimeComponent.count,
         runtimeComponent.count2,
         39,
         runtimeComponent.count2 - 32,
         (RuntimeComponent7.resolveFontRenderer7(runtimeComponent).indexOffset + 1) * 6 + 2
      );
      this.runtimeComponent7 = runtimeComponent;
      this.enabled2 = true;
   }

   @Override
   public int getCount20() {
      return this.count9 - 30;
   }

   @Override
   public int size() {
      return this.list.size();
   }

   public static Minecraft resolveMinecraft(Beta2SplineCalculator beta2SplineCalculator) {
      return beta2SplineCalculator.mc;
   }
}
