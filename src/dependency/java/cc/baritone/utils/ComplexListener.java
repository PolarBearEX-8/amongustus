package cc.baritone.utils;

import java.lang.reflect.Type;

public interface ComplexListener {
   String apply(AStarPolicy aStarPolicy, Object entry);

   Object resolveTarget(AStarPolicy aStarPolicy, String text);

   boolean isAssignableFrom(Type type);
}
