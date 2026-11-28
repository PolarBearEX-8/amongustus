package net.augustus.modules.misc.helper;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import net.augustus.modules.misc.RotationRecorder;

public class RotationRecorderExclusionStrategy implements ExclusionStrategy {
   public final RotationRecorder rotationRecorder;

   public RotationRecorderExclusionStrategy(RotationRecorder rotationRecorder2) {
      this.rotationRecorder = rotationRecorder2;
   }

   public boolean shouldSkipField(FieldAttributes fieldAttributes) {
      return false;
   }

   public boolean shouldSkipClass(Class clazz) {
      return clazz.getName().equals("net.49pz");
   }
}
