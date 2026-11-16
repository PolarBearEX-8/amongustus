package cc.baritone.api;

import cc.baritone.utils.ActiveListener;
import cc.baritone.utils.BaritoneManager;
import cc.baritone.utils.BreadthFirstSampler;
import cc.baritone.utils.BreadthFirstValidator;
import cc.baritone.utils.DequeEntry;
import cc.baritone.utils.DynamicListener;
import cc.baritone.utils.GenericListener;
import cc.baritone.utils.GlobalCustom;
import cc.baritone.utils.HeuristicEstimator;
import cc.baritone.utils.ModifiedManager;
import client.util.AimObserver;
import java.util.List;
import java.util.Locale;
import java.util.stream.Stream;

public class ItemEntry implements GenericListener {
   public final DequeEntry dequeEntry = new DequeEntry();
   public final BaritoneManager baritoneManager;

   @Override
   public boolean matchesAimObserver(AimObserver aimObserver) {
      GlobalCustom globalCustom = this.resolveGlobalCustom(aimObserver);
      if (globalCustom != null) {
         GlobalCustom.applyGlobalCustom(globalCustom);
      }

      return globalCustom != null;
   }

   @Override
   public boolean matchesText(String text) {
      return this.matchesAimObserver(resolveAimObserver(text));
   }

   @Override
   public Stream resolveStream(String text) {
      AimObserver aimObserver = resolveAimObserver(text, true);
      String dataRef = (String)aimObserver.getDataRef2();
      return ((List)aimObserver.getDataRef()).isEmpty()
         ? new HeuristicEstimator().resolveHeuristicEstimator(this.baritoneManager.getItemEntry()).resolveHeuristicEstimatorByText(dataRef).getStream()
         : this.resolveStream(aimObserver);
   }

   @Override
   public DequeEntry getDequeEntry() {
      return this.dequeEntry;
   }

   @Override
   public DynamicListener resolveDynamicListener(String text) {
      for (DynamicListener dynamicListener : (Iterable<DynamicListener>)(Iterable)(this.dequeEntry.collection)) {
         if (dynamicListener.getListAsList().contains(text.toLowerCase(Locale.US))) {
            return dynamicListener;
         }
      }

      return null;
   }

   private GlobalCustom resolveGlobalCustom(AimObserver aimObserver) {
      String dataRef = (String)aimObserver.getDataRef2();
      BreadthFirstValidator breadthFirstValidator = new BreadthFirstValidator(this, (List)aimObserver.getDataRef());
      DynamicListener dynamicListener = this.resolveDynamicListener(dataRef);
      return dynamicListener == null ? null : new GlobalCustom(dynamicListener, dataRef, breadthFirstValidator, null);
   }

   @Override
   public ActiveListener getActiveListener() {
      return this.baritoneManager;
   }

   private static AimObserver resolveAimObserver(String text, boolean flag) {
      String text2 = text.split("\\s", 2)[0];
      List list = BreadthFirstSampler.resolveList(text.substring(text2.length()), flag);
      return new AimObserver(text2, list);
   }

   @Override
   public Stream resolveStream(AimObserver aimObserver) {
      GlobalCustom globalCustom = this.resolveGlobalCustom(aimObserver);
      return globalCustom == null ? Stream.empty() : GlobalCustom.resolveStream(globalCustom);
   }

   public static AimObserver resolveAimObserver(String text) {
      return resolveAimObserver(text, false);
   }

   public ItemEntry(BaritoneManager baritoneManager2) {
      this.baritoneManager = baritoneManager2;
      ModifiedManager.resolveList(baritoneManager2).forEach(this.dequeEntry::matchesTarget);
   }
}
