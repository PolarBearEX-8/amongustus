package cc.baritone.utils;

public final class CustomClientManager extends AuxiliaryListener26Entry {
   @Override
   public DefaultCustom getDefaultCustom() {
      return this.staticListener.getDefaultCustom();
   }

   public CustomClientManager(StaticListener staticListener) {
      super(staticListener);
   }
}
