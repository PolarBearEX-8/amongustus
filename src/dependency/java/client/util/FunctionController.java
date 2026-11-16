package client.util;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

public class FunctionController {
   public final Map map = new ConcurrentHashMap();

   private Function resolveFunction(Function mapper) {
      return entry -> this.computeIfAbsent(mapper, entry);
   }

   public static Function resolveFunctionByFunction(Function mapper) {
      return (new FunctionController()).resolveFunction(mapper);
   }

   private Object computeIfAbsent(Function mapper, Object entry) {
      return this.map.computeIfAbsent(entry, mapper);
   }
}
