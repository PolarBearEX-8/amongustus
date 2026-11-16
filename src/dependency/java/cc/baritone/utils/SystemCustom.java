package cc.baritone.utils;

import java.util.Comparator;
import java.util.stream.Stream;

public enum SystemCustom implements AuxiliaryListener10Extension {
    INSTANCE;

   public static final SystemCustom[] ENUM_VALUES = new SystemCustom[]{INSTANCE};

   private static ModularListener[] resolveModularListenerArrayByIndex(int count) {
      return new ModularListener[count];
   }

   @Override
   public Stream empty(HeuristicSampler heuristicSampler) throws ExceptionAdapter {
      return new HeuristicEstimator()
         .resolveHeuristicEstimatorByTextArray(resolveTextArray(heuristicSampler.getActiveListener()))
         .getHeuristicEstimator2()
         .resolveHeuristicEstimator(BreadthFirstSystem.getTextArray())
         .resolveHeuristicEstimatorByText(heuristicSampler.getDepthFirstManager().getText())
         .getStream();
   }

   private static String[] resolveTextArray(int count) {
      return new String[count];
   }

   private static ModularListener[] resolveModularListenerArray(int count) {
      return new ModularListener[count];
   }

   public static String[] resolveTextArray(ActiveListener activeListener) {
      return Stream.of(resolveModularListenerArray(activeListener))
         .map(ModularListener::getLabelText)
         .filter(SystemCustom::isEmpty)
         .toArray(SystemCustom::resolveTextArray);
   }

   public ModularListener[] resolveModularListenerArray(HeuristicSampler heuristicSampler) throws ExceptionAdapter {
      String text = heuristicSampler.getDepthFirstManager().getText();
      BreadthFirstSystem breadthFirstSystem = BreadthFirstSystem.resolveBreadthFirstSystem(text);
      return breadthFirstSystem == null ? resolveModularListenerArray(heuristicSampler.getActiveListener(), text) : resolveModularListenerArray(heuristicSampler.getActiveListener(), breadthFirstSystem);
   }

   public static ModularListener[] resolveModularListenerArray(ActiveListener activeListener) {
      return ((Stream<ModularListener>)resolveHeuristicHandler(activeListener)
         .getSet()
         .stream())
         .sorted(Comparator.comparingLong(ModularListener::getTimestamp).reversed())
         .toArray(ModularListener[]::new);
   }

   private static ModularListener[] resolveModularListenerArray2(int count) {
      return new ModularListener[count];
   }

   public static ModularListener[] resolveModularListenerArray(ActiveListener activeListener, String text) {
      return Stream.of(resolveModularListenerArray(activeListener)).filter(m -> matchesText(text, m)).toArray(ModularListener[]::new);
   }

   private static boolean matchesText(String text, ModularListener modularListener) {
      return modularListener.getLabelText().equalsIgnoreCase(text);
   }

   @Override
   public Object resolveTarget(HeuristicSampler heuristicSampler) throws ExceptionAdapter {
      return this.resolveModularListenerArray(heuristicSampler);
   }

   public static ModularListener[] resolveModularListenerArray(ActiveListener activeListener, BreadthFirstSystem breadthFirstSystem) {
      return ((Stream<ModularListener>)resolveHeuristicHandler(activeListener)
         .resolveSet(breadthFirstSystem)
         .stream())
         .sorted(Comparator.comparingLong(ModularListener::getTimestamp).reversed())
         .toArray(SystemCustom::resolveModularListenerArray2);
   }



   public static HeuristicHandler resolveHeuristicHandler(ActiveListener activeListener) {
      return activeListener.getSupplementalListener().getNativeListener().getHeuristicHandler();
   }

   private static boolean isEmpty(String text) {
      return !text.isEmpty();
   }
}
