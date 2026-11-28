package net.augustus.modules;

import java.util.concurrent.CopyOnWriteArrayList;

public class ModuleRegistry {
   private CopyOnWriteArrayList modules = new CopyOnWriteArrayList();
   private CopyOnWriteArrayList sortedModules = new CopyOnWriteArrayList();

   public CopyOnWriteArrayList getModules() {
      return this.modules;
   }

   public void setModules(CopyOnWriteArrayList copyOnWriteArrayList) {
      this.modules = copyOnWriteArrayList;
   }

   public CopyOnWriteArrayList getSortedModules() {
      return this.sortedModules;
   }

   public void setSortedModules(CopyOnWriteArrayList copyOnWriteArrayList) {
      this.sortedModules = copyOnWriteArrayList;
   }
}
