package net.augustus.modules;

import java.util.ArrayList;
import java.util.List;

public enum Categorys {
   LEGIT,
   MOVEMENT,
   RENDER,
   MISC,

   COMBAT,
   LATENCY,
   PLAYER,
   WORLD,
   FUN;

private final List modules = new ArrayList();
   private final List displayModules;
   public final boolean special;
   public final boolean hidden;

   private Categorys() {
      this.displayModules = new ArrayList();
      this.special = false;
      this.hidden = false;
   }

   private Categorys(int n, boolean flag) {
      this.displayModules = new ArrayList();
      this.special = flag;
      this.hidden = flag;
   }

   public List getModules() {
      return this.modules;
   }

   public List getDisplayModules() {
      return this.displayModules;
   }

}
