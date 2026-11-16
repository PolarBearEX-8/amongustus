package cc.baritone.utils;

public final class BreadthFirstValidatoHeuristicSampler implements HeuristicSampler {
   public final BreadthFirstValidator breadthFirstValidator;

   @Override
   public DepthFirstManager getDepthFirstManager() {
      return this.getBreadthFirstValidator();
   }

   public final BreadthFirstValidator getBreadthFirstValidator() {
      return this.breadthFirstValidator;
   }

   @Override
   public final ActiveListener getActiveListener() {
      return BreadthFirstValidator.resolveGenericListener(this.breadthFirstValidator).getActiveListener();
   }

   private BreadthFirstValidatoHeuristicSampler(BreadthFirstValidator breadthFirstValidator2) {
      this.breadthFirstValidator = breadthFirstValidator2;
   }

   public BreadthFirstValidatoHeuristicSampler(BreadthFirstValidator breadthFirstValidator2, Controller5 controller) {
      this(breadthFirstValidator2);
   }
}
