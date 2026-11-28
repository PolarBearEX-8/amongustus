package net.augustus.utils.player;

import java.util.ArrayList;
import net.augustus.modules.Module;
import net.augustus.utils.TimeHelper;
import net.minecraft.item.ItemStack;

public class SilentSlotManager {
   private static Module activeModule = null;
   private static int activePriority = 0;
   private static ArrayList scheduledSwitches = new ArrayList();
   private static int serverSlot;
   private static boolean silentMode = false;
   public static int clientHotbarSlot = 0;
   public static int realHotbarSlot = 0;
   private static int lastResolvedSlot = 0;
   public static ItemStack heldItemStack = null;

   public static int getActivePriority() {
      return activePriority;
   }

   public static int getServerSlot() {
      return serverSlot;
   }

   public static int getLastResolvedSlot() {
      return lastResolvedSlot;
   }

   public static void scheduleSlotSwitch(Module module, int n, int n2, int n3, boolean flag) {

      for (int i = scheduledSwitches.size() - 1; i > 0; i--) {
         if (ScheduledSlotSwitch.getOwnerModule((ScheduledSlotSwitch)scheduledSwitches.get(i)) == module) {
            scheduledSwitches.remove(i);
         }
      }

      scheduledSwitches.add(new ScheduledSlotSwitch(module, n, n2, n3, flag));
   }

   public static boolean isOwnedBy(Module module) {
      return activeModule != null && activeModule.equals(module);
   }

   public static void requestSlot(Module module, int n, int n2, boolean flag) {
      if (n < activePriority && n2 >= 0 && n2 < 9) {
         silentMode = flag;
         serverSlot = n2;
         activePriority = n;
         activeModule = module;
      }
   }

   public static void resetSlot(int n) {
      serverSlot = n;
      activePriority = 1000;
      activeModule = null;
   }

   public static boolean isSilentMode() {
      return silentMode;
   }

   public static int tickAndResolveSlot() {

      for (int i = scheduledSwitches.size() - 1; i > 0; i--) {
         ScheduledSlotSwitch scheduledSlotSwitch = (ScheduledSlotSwitch)scheduledSwitches.get(i);
         if (ScheduledSlotSwitch.getRemainingTicks(scheduledSlotSwitch) <= 0) {
            scheduledSwitches.remove(i);
         } else {
            scheduledSlotSwitch.decrementRemainingTicks();
            Module ownerModule = ScheduledSlotSwitch.getOwnerModule(scheduledSlotSwitch);
            int priority = ScheduledSlotSwitch.getPriority(scheduledSlotSwitch);
            boolean isSilentMode = ScheduledSlotSwitch.isSilentMode(scheduledSlotSwitch);
            requestSlot(ownerModule, priority, ScheduledSlotSwitch.getTargetSlot(scheduledSlotSwitch), isSilentMode);
         }
      }

      if (activeModule == null) {
         serverSlot = realHotbarSlot;
         clientHotbarSlot = realHotbarSlot;
      }

      lastResolvedSlot = serverSlot;
      return serverSlot;
   }

   public static void requestSlot(Module module, int n, int n2) {
      requestSlot(module, n, n2, true);
   }

   public static boolean getActiveModule() {
      return activeModule != null;
   }

   public static void scheduleSlotSwitch(Module module, int n, int n2, int n3) {
      scheduleSlotSwitch(module, n, n2, n3, true);
   }
}
