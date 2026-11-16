package client.util;

import client.event.PostUpdateNotifier;
import client.event.PreRenderObserver;
import com.google.gson.Gson;
import com.google.gson.JsonParser;
import java.util.ArrayList;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import net.minecraft.item.ItemEntry20Entry;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MoreCosmeticsManager {
   public static MoreCosmeticsManager moreCosmeticsManager;
   private static int[] countArray;
   public ArrayList arrayList;
   public static final JsonParser jsonParser = new JsonParser();
   public static final int count = 1200;
   public PostUpdateNotifier postUpdateNotifier;
   public boolean enabled;
   public static final Logger logger = LogManager.getLogger("MoreCosmetics");
   public FailedToLoadMaskFromManagerEntry failedToLoadMaskFromManagerEntry;
   public static final Gson gson = new Gson();
   public ArrayList arrayList2 = new ArrayList();
   public static final ExecutorService executorService = Executors.newFixedThreadPool(3);
   public FailedToLoadMaskFromManager failedToLoadMaskFromManager;
   public CosmeticItemRegistry cosmeticItemRegistry;

   private void updateState() {
      JsonArrayEntry.updateState2();

      try {
         ItemEntry20Entry.updateState();
         PostUpdateNotifier postUpdateNotifier2 = this.postUpdateNotifier = this.getPostUpdateNotifierAsPostUpdateNotifi();
         this.add(postUpdateNotifier2);
         UUID uuid = this.postUpdateNotifier.resolveUUID(false);
         CosmeticItemRegistry cosmeticItemRegistry2 = this.cosmeticItemRegistry = new CosmeticItemRegistry(uuid);
         this.add(cosmeticItemRegistry2);
         this.failedToLoadMaskFromManager = this.postUpdateNotifier.getFailedToLoadMaskFromManager();
         this.failedToLoadMaskFromManagerEntry = this.postUpdateNotifier.getFailedToLoadMaskFromManagerE();
         JsonArrayEntry.updateState();
      } catch (Exception exception) {
         exception.printStackTrace();
      }
   }

   public FailedToLoadMaskFromManager getFailedToLoadMaskFromManager() {
      return this.failedToLoadMaskFromManager;
   }

   public static void applyThrowable(Throwable throwable) {
      logger.catching(throwable);
      String text = SSLSocketFactoryEntry.resolveText(throwable);
      SplineAggregator splineAggregator = SplineAggregator.ERROR;
      DebugConsoleJFrame.applyText(text, splineAggregator);
   }

   public static void applyText(String text) {
      logger.info("[MoreCosmetics] " + text);
      SplineAggregator splineAggregator = SplineAggregator.LOG;
      DebugConsoleJFrame.applyText(text, splineAggregator);
   }

   public PostUpdateNotifier getPostUpdateNotifier() {
      return this.postUpdateNotifier;
   }

   public CosmeticItemRegistry getHashMapEntry() {
      return this.cosmeticItemRegistry;
   }

   public static MoreCosmeticsManager getMoreCosmeticsManager() {
      if (moreCosmeticsManager == null) {
         moreCosmeticsManager = new MoreCosmeticsManager();
         moreCosmeticsManager.updateState();
      }

      return moreCosmeticsManager;
   }

   public static int[] getCountArray() {
      return null;
   }

   public void add(PreRenderObserver preRenderObserver) {
      this.arrayList2.add(preRenderObserver);
   }

   private PostUpdateNotifier getPostUpdateNotifierAsPostUpdateNotifi() {
      return new DynamicClientManager();
   }

   public static MoreCosmeticsManager resolveMoreCosmeticsManager(int count2) {
      if (moreCosmeticsManager == null) {
         moreCosmeticsManager = new MoreCosmeticsManager();
         moreCosmeticsManager.updateState();
      }

      return moreCosmeticsManager;
   }

   public FailedToLoadMaskFromManagerEntry getFailedToLoadMaskFromManagerEntry() {
      return this.failedToLoadMaskFromManagerEntry;
   }

   public void execute(Runnable task) {
      executorService.execute(task);
   }

   public static void applyTextByText(String text) {
      SplineAggregator splineAggregator = SplineAggregator.DEBUG;
      DebugConsoleJFrame.applyText(text, splineAggregator);
   }

   public void updateState2() {

      try {
         if (!this.enabled) {
            this.enabled = true;
            this.failedToLoadMaskFromManagerEntry.updateState();
         }

         for (PreRenderObserver preRenderObserver : (Iterable<PreRenderObserver>)(Iterable<?>)this.arrayList2) {
            int count2 = ExtraFloatEntry.count;
            preRenderObserver.applyIndex(count2);
         }

         ExtraFloatEntry.count++;
         if (!this.arrayList.isEmpty()) {
            this.arrayList.forEach(task -> ((Runnable)task).run());
            this.arrayList.clear();
         }
      } catch (Exception exception) {
         applyThrowable(exception);
      }
   }

   private MoreCosmeticsManager() {
      this.arrayList = new ArrayList();
   }
}
