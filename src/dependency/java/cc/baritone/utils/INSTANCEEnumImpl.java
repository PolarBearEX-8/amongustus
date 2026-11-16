package cc.baritone.utils;

public enum INSTANCEEnumImpl implements BreadthFirstScheduleClassCallback {
    INSTANCE;

   public static final INSTANCEEnumImpl[] ENUM_VALUES = new INSTANCEEnumImpl[]{INSTANCE};

   @Override
   public Object resolveTarget(BreadthFirstScheduler breadthFirstScheduler) throws Exception {
      return this.resolveInteger(breadthFirstScheduler);
   }

   @Override
   public Class getClassAsClass() {
      return Integer.class;
   }

   public Integer resolveInteger(BreadthFirstScheduler breadthFirstScheduler) throws RuntimeException {
      return Integer.parseInt(breadthFirstScheduler.getLabelText());
   }
}
