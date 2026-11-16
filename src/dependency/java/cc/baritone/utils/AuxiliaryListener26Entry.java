package cc.baritone.utils;

public abstract class AuxiliaryListener26Entry implements Listener9Extension {
   public final StaticListener staticListener;
   public final DoubleEntry doubleEntry;
   public double value2;
   public double value;

   @Override
   public DefaultCustom resolveDefaultCustomByDefaultCustom(DefaultCustom defaultCustom) {
      DefaultCustom defaultCustom2 = this.resolveDefaultCustom(defaultCustom);
      this.updateState();
      return defaultCustom2;
   }

   private int computeCount(float factor) {
      float factor2 = this.computeFactor(1);
      return Math.round(factor / factor2);
   }

   @Override
   public final DefaultCustom resolveDefaultCustom(DefaultCustom defaultCustom) {
      return defaultCustom;
   }

   private float computeFactor(float factor) {
      if (factor < -20.0F) {
         return factor + 1.0F;
      } else {
         return factor > 10.0F ? factor - 1.0F : factor;
      }
   }

   private float computeFactor(int count) {
      float factor = this.staticListener.getMc().gameSettings.scaleFactor * 0.6F + 0.2F;
      return (float)count * factor * factor * factor * 8.0F * 0.15F;
   }

   @Override
   public final void updateState() {
      this.value2 = (this.doubleEntry.getValue() - 0.5) * (Double)BaritoneManager.getSettings().randomLooking.dataRef;
      this.value = (this.doubleEntry.getValue() - 0.5) * (Double)BaritoneManager.getSettings().randomLooking.dataRef;
   }

   @Override
   public final void applyIndex(int count) {
      for (int index = 0; index < count; index++) {
         this.updateState();
      }
   }

   public abstract DefaultCustom getDefaultCustom();

   private float computeFactor(float factor, float factor2) {
      float factor3 = factor2 - factor;
      int count = this.computeCount(factor3);
      return factor + this.computeFactor(count);
   }

   @Override
   public final Listener9Extension getListener9Extension() {
      return new HeuristicFilter(this, this);
   }

   public AuxiliaryListener26Entry(StaticListener staticListener2) {
      this.staticListener = staticListener2;
      this.doubleEntry = new DoubleEntry();
   }

   private AuxiliaryListener26Entry(AuxiliaryListener26Entry auxiliaryListener26Entry) {
      this.staticListener = auxiliaryListener26Entry.staticListener;
      this.doubleEntry = auxiliaryListener26Entry.doubleEntry.copyOf();
      this.value2 = auxiliaryListener26Entry.value2;
      this.value = auxiliaryListener26Entry.value;
   }

   public AuxiliaryListener26Entry(AuxiliaryListener26Entry auxiliaryListener26Entry, BreadthFirstEvaluator breadthFirstEvaluator) {
      this(auxiliaryListener26Entry);
   }
}
