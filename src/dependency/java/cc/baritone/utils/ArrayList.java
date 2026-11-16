package cc.baritone.utils;

public class ArrayList extends java.util.ArrayList {
   public final MinecraftSplineCalculatorAdapter minecraftSplineCalculatorAdapter;

   public ArrayList(MinecraftSplineCalculatorAdapter minecraftSplineCalculatorAdapter2) {
      this.minecraftSplineCalculatorAdapter = minecraftSplineCalculatorAdapter2;

      for (StringEntry stringEntry : (java.lang.Iterable<StringEntry>) (HologramManager.copyOf().values())) {
         this.add(new WdlGuiPreUpdateDispatcher(this.minecraftSplineCalculatorAdapter, stringEntry));
      }
   }
}
