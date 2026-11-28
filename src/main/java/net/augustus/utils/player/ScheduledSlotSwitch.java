package net.augustus.utils.player;

import net.augustus.modules.Module;

public class ScheduledSlotSwitch {
   private final boolean silentMode;
   private int remainingTicks;
   private final int targetSlot;
   private final Module ownerModule;
   private final int priority;

   public static int getPriority(ScheduledSlotSwitch scheduledSlotSwitch) {
      return scheduledSlotSwitch.priority;
   }

   public static Module getOwnerModule(ScheduledSlotSwitch scheduledSlotSwitch) {
      return scheduledSlotSwitch.ownerModule;
   }

   public ScheduledSlotSwitch(Module module, int n, int n2, int n3, boolean flag) {
      this.ownerModule = module;
      this.priority = n;
      this.remainingTicks = n3;
      this.targetSlot = n2;
      this.silentMode = flag;
   }

   public static boolean isSilentMode(ScheduledSlotSwitch scheduledSlotSwitch) {
      return scheduledSlotSwitch.silentMode;
   }

   public void decrementRemainingTicks() {
      this.remainingTicks--;
   }

   public static int getRemainingTicks(ScheduledSlotSwitch scheduledSlotSwitch) {
      return scheduledSlotSwitch.remainingTicks;
   }

   public static int getTargetSlot(ScheduledSlotSwitch scheduledSlotSwitch) {
      return scheduledSlotSwitch.targetSlot;
   }
}
