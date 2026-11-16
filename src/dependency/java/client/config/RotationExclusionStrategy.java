package client.config;

import augustus.modules.misc.RotationRecorder;
import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;

public class RotationExclusionStrategy implements ExclusionStrategy {
   public final RotationRecorder rotationRecorder;

   public boolean shouldSkipField(FieldAttributes fieldAttributes) {
      return false;
   }

   public boolean shouldSkipClass(Class clazz) {
      return clazz.getName().equals("net.47oo");
   }

   public RotationExclusionStrategy(RotationRecorder rotationRecorder2) {
      this.rotationRecorder = rotationRecorder2;
   }
}
