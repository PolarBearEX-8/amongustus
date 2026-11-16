package cc.baritone.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import it.unimi.dsi.fastutil.longs.LongOpenHashSet;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;

public class AStarNavigator implements SystemListener {
   public final boolean enabled;
   public final LongOpenHashSet longOpenHashSet;
   public final RecordNodeHandler[] recordNodeHandlerArray;
   public final ExploringAroundINACTIVEHandlerAdapter exploringAroundINACTIVEHandlerAdapter;

   public AStarNavigator(ExploringAroundINACTIVEHandlerAdapter exploringAroundINACTIVEHandlerAdapter2, Path path, boolean flag, HeuristicPolicy heuristicPolicy) throws Exception {
      this(exploringAroundINACTIVEHandlerAdapter2, path, flag);
   }

   @Override
   public DepthFirstPlanner resolveDepthFirstPlanner(int count, int count2) {
      return this.longOpenHashSet.contains(AStarVerifier.computeTimestamp(count, count2)) ^ this.enabled ? DepthFirstPlanner.EXPLORED : DepthFirstPlanner.UNKNOWN;
   }

   private AStarNavigator(ExploringAroundINACTIVEHandlerAdapter exploringAroundINACTIVEHandlerAdapter2, Path path, boolean flag) throws Exception {
      this.exploringAroundINACTIVEHandlerAdapter = exploringAroundINACTIVEHandlerAdapter2;
      this.enabled = flag;
      Gson gson = new GsonBuilder().create();
      this.recordNodeHandlerArray = (RecordNodeHandler[])gson.fromJson(new InputStreamReader(Files.newInputStream(path)), RecordNodeHandler[].class);
      exploringAroundINACTIVEHandlerAdapter2.applyText("Loaded " + this.recordNodeHandlerArray.length + " positions");
      this.longOpenHashSet = new LongOpenHashSet();

      for (RecordNodeHandler recordNodeHandler : this.recordNodeHandlerArray) {
         this.longOpenHashSet.add(AStarVerifier.computeTimestamp(recordNodeHandler.count, recordNodeHandler.count2));
      }
   }

   @Override
   public int getCount() {
      if (!this.enabled) {
         return Integer.MAX_VALUE;
      } else {
         int count = 0;
         AStarMonitor aStarMonitor = new AStarMonitor(this.exploringAroundINACTIVEHandlerAdapter, null);

         for (RecordNodeHandler recordNodeHandler : this.recordNodeHandlerArray) {
            if (aStarMonitor.resolveDepthFirstPlanner(recordNodeHandler.count, recordNodeHandler.count2) != DepthFirstPlanner.EXPLORED) {
               if (++count >= (Integer)BaritoneManager.getSettings().exploreChunkSetMinimumSize.dataRef) {
                  return count;
               }
            }
         }

         return count;
      }
   }
}
