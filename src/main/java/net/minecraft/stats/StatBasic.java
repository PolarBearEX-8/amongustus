package net.minecraft.stats;

import net.minecraft.util.IChatComponent;

public class StatBasic extends StatBase {
   public StatBasic(String str, IChatComponent component, IStatType statType) {
      super(str, component, statType);
   }

   @Override
   public StatBase registerStat() {
      super.registerStat();
      StatList.generalStats.add(this);
      return this;
   }

   public StatBasic(String str, IChatComponent component) {
      super(str, component);
   }
}
