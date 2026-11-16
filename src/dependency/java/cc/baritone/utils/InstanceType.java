package cc.baritone.utils;

public enum InstanceType implements BreadthFirstScheduleClassCallback {
   INSTANCE;

   public static final InstanceType[] ENUM_VALUES = values();

   public Float resolveFloat(BreadthFirstScheduler breadthFirstScheduler) throws RuntimeException {
      String labelText = breadthFirstScheduler.getLabelText();
      if (!labelText.matches("^([+-]?(?:\\d+(?:\\.\\d*)?|\\.\\d+)|)$")) {
         throw new IllegalArgumentException("failed float format check");
      } else {
         return Float.parseFloat(labelText);
      }
   }

   @Override
   public Object resolveTarget(BreadthFirstScheduler breadthFirstScheduler) throws Exception {
      return this.resolveFloat(breadthFirstScheduler);
   }

   @Override
   public Class getClassAsClass() {
      return Float.class;
   }
}
