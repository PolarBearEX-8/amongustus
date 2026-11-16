package cc.baritone.utils;

import java.util.Collection;
import java.util.Map.Entry;

public class ArrayListImpl extends java.util.ArrayList {
   public final MinecraftSplineCalculatorImpl minecraftSplineCalculatorImpl;

   public ArrayListImpl(MinecraftSplineCalculatorImpl minecraftSplineCalculatorImpl2) {
      this.minecraftSplineCalculatorImpl = minecraftSplineCalculatorImpl2;

      for (Entry entry : (Iterable<Entry>)(Iterable<?>)LoggerEntry.getListMultimap().asMap().entrySet()) {
         this.add(new WdlGuiPreUpdateDispatcherImpl(this.minecraftSplineCalculatorImpl, (CORERECOMMENDEDManager)entry.getKey()));

         for (AltListener altListener : (Iterable<AltListener>)(Iterable<?>)(Collection)entry.getValue()) {
            this.add(new WdlGuiPreUpdateDispatcherAdapter(this.minecraftSplineCalculatorImpl, altListener, (CORERECOMMENDEDManager)entry.getKey()));
         }
      }
   }
}
