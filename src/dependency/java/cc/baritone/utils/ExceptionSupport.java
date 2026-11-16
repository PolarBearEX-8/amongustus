package cc.baritone.utils;

public class ExceptionSupport extends DynamicListenerException {
   public ExceptionSupport(Class clazz) {
      super(String.format("Could not find a handler for type %s", clazz.getSimpleName()));
   }
}
