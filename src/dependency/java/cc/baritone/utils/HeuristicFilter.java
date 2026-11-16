package cc.baritone.utils;

public class HeuristicFilter extends AuxiliaryListener26Entry {
   public DefaultCustom defaultCustom;
   public final AuxiliaryListener26Entry auxiliaryListener26Entry;

   @Override
   public DefaultCustom resolveDefaultCustomByDefaultCustom(DefaultCustom defaultCustom2) {
      return this.defaultCustom = super.resolveDefaultCustomByDefaultCustom(defaultCustom2);
   }

   @Override
   public DefaultCustom getDefaultCustom() {
      return this.defaultCustom;
   }

   public HeuristicFilter(AuxiliaryListener26Entry auxiliaryListener26Entry2, AuxiliaryListener26Entry auxiliaryListener26Entry3) {
      super(auxiliaryListener26Entry3, null);
      this.auxiliaryListener26Entry = auxiliaryListener26Entry2;
      this.defaultCustom = this.auxiliaryListener26Entry.getDefaultCustom();
   }
}
