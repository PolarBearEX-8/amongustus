package cc.baritone.utils;

public enum InstanceTypeAdapter implements BreadthFirstScheduleClassCallback {
   INSTANCE;

   public static final InstanceTypeAdapter[] ENUM_VALUES = values();

   @Override
   public Class getClassAsClass() {
      return Double.class;
   }

   public Double resolveDouble(BreadthFirstScheduler breadthFirstScheduler) throws RuntimeException {
      String labelText = breadthFirstScheduler.getLabelText();
      if (!labelText.matches("^([+-]?(?:\\d+(?:\\.\\d*)?|\\.\\d+)|)$")) {
         throw new IllegalArgumentException("failed double format check");
      } else {
         return Double.parseDouble(labelText);
      }
   }

   @Override
   public Object resolveTarget(BreadthFirstScheduler breadthFirstScheduler) throws Exception {
      return this.resolveDouble(breadthFirstScheduler);
   }
}
