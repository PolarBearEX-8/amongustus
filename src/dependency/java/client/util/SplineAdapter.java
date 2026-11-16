package client.util;

import net.minecraft.item.GoldenArtifact;
import net.minecraft.item.GoldenFragment;
import net.minecraft.item.GoldenStaff;
import net.minecraft.item.IronSword;

public class SplineAdapter {
   public static void updateState() {
      EnhancedLoggerEntry.applyClassByClass(GoldenStaff.class, "OMB");
      EnhancedLoggerEntry.applyClassByClass(GoldenArtifact.class, "OMCR");
      EnhancedLoggerEntry.applyClassByClass(GoldenFragment.class, "OMDXR");
      EnhancedLoggerEntry.applyClassByClass(FovQueue.class, "OMDXYR");
      EnhancedLoggerEntry.applyClassByClass(HitBoxFilter.class, "OMDYR");
      EnhancedLoggerEntry.applyClassByClass(FovPipeline.class, "OMDYZR");
      EnhancedLoggerEntry.applyClassByClass(HitBoxChecker.class, "OMDZR");
      EnhancedLoggerEntry.applyClassByClass(HitBoxMatcher.class, "OMEntry");
      EnhancedLoggerEntry.applyClassByClass(HitBoxCalculator.class, "OMPenthouse");
      EnhancedLoggerEntry.applyClassByClass(IronSword.class, "OMSimple");
      EnhancedLoggerEntry.applyClassByClass(FovStack.class, "OMSimpleT");
   }
}
