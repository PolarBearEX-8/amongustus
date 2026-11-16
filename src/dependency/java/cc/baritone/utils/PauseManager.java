package cc.baritone.utils;

import cc.baritone.api.ExtendedItemEntry;
import cc.baritone.command.PauseResumeCommandsINACTIVEHandler;

public class PauseManager {
   public ExtendedItemEntry extendedItemEntry2;
   public ExtendedItemEntry extendedItemEntry4;
   public ExtendedItemEntry extendedItemEntry;
   public ExtendedItemEntry extendedItemEntry3;

   public PauseManager(ActiveListener activeListener) {
      boolean[] flagArray = new boolean[]{false};
      activeListener.getLocalListener().applyINACTIVEHandler(new PauseResumeCommandsINACTIVEHandler(this, flagArray, activeListener));
      this.extendedItemEntry2 = new InternalUsageManager(this, activeListener, new String[]{"pause", "p", "paws"}, flagArray);
      this.extendedItemEntry4 = new AuxiliaryUsageManager(this, activeListener, new String[]{"resume", "r", "unpause", "unpaws"}, flagArray);
      this.extendedItemEntry = new UsageManager(this, activeListener, new String[]{"paused"}, flagArray);
      this.extendedItemEntry3 = new ExtendedUsageManager(this, activeListener, new String[]{"cancel", "c", "stop"}, flagArray);
   }
}
