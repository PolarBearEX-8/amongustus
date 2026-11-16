package cc.baritone.utils;

public interface CompositeListener extends SimpleListener {
   void add(SimpleListener simpleListener);
}
