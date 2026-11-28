package net.augustus.modules.modes.movementrecorder;

import java.util.List;

public class MovementRecording {
   private final String name;
   private final List inputs;
   private final List blockSignature;
   public final MovementReplayMode movementReplayMode;

   public MovementRecording(MovementReplayMode movementReplayMode2, String str, List list, List list2) {
      this.movementReplayMode = movementReplayMode2;
      this.name = str;
      this.inputs = list;
      this.blockSignature = list2;
   }

   public static List accessBlockSignature(MovementRecording movementRecording) {
      return movementRecording.blockSignature;
   }

   public static String accessName(MovementRecording movementRecording) {
      return movementRecording.name;
   }

   public static List accessInputs(MovementRecording movementRecording) {
      return movementRecording.inputs;
   }
}
