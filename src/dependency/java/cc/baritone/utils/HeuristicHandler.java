package cc.baritone.utils;

import java.util.Set;

public interface HeuristicHandler {
   void applyModularListenerByModularListener(ModularListener modularListener);

   void applyModularListener(ModularListener modularListener);

   Set getSet();

   Set resolveSet(BreadthFirstSystem breadthFirstSystem);

   ModularListener resolveModularListener(BreadthFirstSystem breadthFirstSystem);
}
