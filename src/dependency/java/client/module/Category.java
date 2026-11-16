package client.module;

import java.util.ArrayList;
import java.util.List;

public enum Category {
    COMBAT,
    LEGIT,
    LATENCY,
    MOVEMENT,
    PLAYER,
    RENDER,
    WORLD,
    MISC,
    CRASHER(true),
    EXPLOIT(true),
    FUN;

   public final List moduleList = new ArrayList();
   public final List subModuleList = new ArrayList();
   public final boolean special;
   public final boolean hidden;

   public List getModuleList() {
      return this.moduleList;
   }

   public List getSubModuleList() {
      return this.subModuleList;
   }

   private Category() {
      this.special = false;
      this.hidden = false;
   }

   private Category(boolean flag) {
      this.special = flag;
      this.hidden = flag;
   }


}
