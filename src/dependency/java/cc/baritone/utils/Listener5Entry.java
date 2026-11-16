package cc.baritone.utils;

import java.util.Objects;

public class Listener5Entry {
   public final SecondaryListener secondaryListener;
   public final HeuristicCoordinator heuristicCoordinator;

   public Listener5Entry(SecondaryListener secondaryListener2, HeuristicCoordinator heuristicCoordinator2) {
      Objects.requireNonNull(heuristicCoordinator2);
      this.secondaryListener = secondaryListener2;
      this.heuristicCoordinator = heuristicCoordinator2;
   }

   @Override
   public String toString() {
      return this.heuristicCoordinator + " " + this.secondaryListener;
   }
}
