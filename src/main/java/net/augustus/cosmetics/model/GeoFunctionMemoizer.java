package net.augustus.cosmetics.model;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

public class GeoFunctionMemoizer {
   private final Map cache = new ConcurrentHashMap();

   public static Function memoize(Function function) {
      return (new GeoFunctionMemoizer()).doMemoize(function);
   }

   private Function doMemoize(Function function) {
      return obj -> this.cache.computeIfAbsent(obj, function);
   }



}
