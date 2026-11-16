package cc.baritone.utils;

public class AStarPolicy {
   public final AuxiliaryStringEntry auxiliaryStringEntry;

   private AuxiliaryStringEntry getAuxiliaryStringEntry() {
      return this.auxiliaryStringEntry;
   }

   public AStarPolicy(AuxiliaryStringEntry auxiliaryStringEntry2, BreadthFirstVerifier breadthFirstVerifier) {
      this(auxiliaryStringEntry2);
   }

   private AStarPolicy(AuxiliaryStringEntry auxiliaryStringEntry2) {
      this.auxiliaryStringEntry = auxiliaryStringEntry2;
   }
}
