package client.util;

import client.module.Module;

public class ModuleEntry {
   public int count;
   public final int count2;
   public final int count3;
   public final boolean enabled;
   public final Module module;

   public ModuleEntry(Module module2, int count4, int count5, int count6, boolean flag) {
      this.module = module2;
      this.count2 = count4;
      this.count = count6;
      this.count3 = count5;
      this.enabled = flag;
   }

   public static int computeCount2(ModuleEntry moduleEntry) {
      return moduleEntry.count2;
   }

   public static int computeCount(ModuleEntry moduleEntry) {
      return moduleEntry.count;
   }

   public static Module resolveModule(ModuleEntry moduleEntry) {
      return moduleEntry.module;
   }

   public static boolean matchesModuleEntry(ModuleEntry moduleEntry) {
      return moduleEntry.enabled;
   }

   public static int computeCountByModuleEntry(ModuleEntry moduleEntry) {
      return moduleEntry.count3;
   }

   public void updateState() {
      this.count--;
   }
}
