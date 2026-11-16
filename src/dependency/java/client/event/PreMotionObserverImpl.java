package client.event;

import client.util.OptionsShadersExtendedMinecraftEntry;
import client.util.SplineDispatcher;

public class PreMotionObserverImpl implements PreMotionObserver {
   public final int count;
   public final OptionsShadersExtendedMinecraftEntry optionsShadersExtendedMinecraftEntry;

   public PreMotionObserverImpl(OptionsShadersExtendedMinecraftEntry optionsShadersExtendedMinecraftEntry2, int count2) {
      this.optionsShadersExtendedMinecraftEntry = optionsShadersExtendedMinecraftEntry2;
      this.count = count2;
   }

   @Override
   public void applyFlag(boolean flag, int count2) {
      if (flag) {
         OptionsShadersExtendedMinecraftEntry.applyOptionsShadersExtendedMine(this.optionsShadersExtendedMinecraftEntry, this.count);
      }

      SplineDispatcher splineDispatcher = this.optionsShadersExtendedMinecraftEntry.splineDispatcher;
      OptionsShadersExtendedMinecraftEntry.resolveMinecraft(this.optionsShadersExtendedMinecraftEntry).displayGuiScreen(splineDispatcher);
   }
}
