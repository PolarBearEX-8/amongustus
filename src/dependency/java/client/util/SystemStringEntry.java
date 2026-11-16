package client.util;

import augustus.modes.movement.MovementReplayMode;
import java.util.List;

public class SystemStringEntry {
   public final String labelText;
   public final List list;
   public final List list2;
   public final MovementReplayMode movementReplayMode;

   public static List resolveList(SystemStringEntry systemStringEntry) {
      return systemStringEntry.list;
   }

   public static String resolveText(SystemStringEntry systemStringEntry) {
      return systemStringEntry.labelText;
   }

   public static List resolveListBySystemStringEntr(SystemStringEntry systemStringEntry) {
      return systemStringEntry.list2;
   }

   public SystemStringEntry(MovementReplayMode movementReplayMode2, String text, List list3, List list4) {
      this.movementReplayMode = movementReplayMode2;
      this.labelText = text;
      this.list = list3;
      this.list2 = list4;
   }
}
