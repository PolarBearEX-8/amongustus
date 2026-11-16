package cc.baritone.utils;

public enum INSTANCEEnum implements BreadthFirstScheduleClassCallback {
    INSTANCE;

   public static final INSTANCEEnum[] ENUM_VALUES = new INSTANCEEnum[]{INSTANCE};

   @Override
   public Class getClassAsClass() {
      return Long.class;
   }



   public Long resolveLong(BreadthFirstScheduler breadthFirstScheduler) throws RuntimeException {
      return Long.parseLong(breadthFirstScheduler.getLabelText());
   }

   @Override
   public Object resolveTarget(BreadthFirstScheduler breadthFirstScheduler) throws Exception {
      return this.resolveLong(breadthFirstScheduler);
   }
}
