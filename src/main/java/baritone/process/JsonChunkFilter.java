package baritone.process;

import baritone.utils.ChunkPos;
import baritone.Baritone;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import it.unimi.dsi.fastutil.longs.LongOpenHashSet;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;

public class JsonChunkFilter implements IChunkFilter {
   private final boolean invert;
   private final LongOpenHashSet inFilter;
   private final ExploreJsonChunk[] myChunkPositions;
   public final ExploreProcess exploreProcess;

   @Override
   public int countRemain() {
      if (!this.invert) {
         return Integer.MAX_VALUE;
      } else {
         int n = 0;
         BaritoneChunkFilter baritoneChunkFilter = new BaritoneChunkFilter(this.exploreProcess, null);

         for (ExploreJsonChunk exploreJsonChunk : this.myChunkPositions) {
            if (baritoneChunkFilter.isAlreadyExplored(exploreJsonChunk.x, exploreJsonChunk.z) != ExploreProcess.Status.EXPLORED) {
               if (++n >= (Integer)Baritone.settings().exploreChunkSetMinimumSize.value) {
                  return n;
               }
            }
         }

         return n;
      }
   }

   @Override
   public ExploreProcess.Status isAlreadyExplored(int n, int n2) {
      return this.inFilter.contains(ChunkPos.asLong(n, n2)) ^ this.invert ? ExploreProcess.Status.EXPLORED : ExploreProcess.Status.UNKNOWN;
   }

   private JsonChunkFilter(ExploreProcess exploreProcess2, Path path, boolean flag) throws Exception {
      this.exploreProcess = exploreProcess2;
      this.invert = flag;
      this.myChunkPositions = (ExploreJsonChunk[])new GsonBuilder().create().fromJson(new InputStreamReader(Files.newInputStream(path)), ExploreJsonChunk[].class);
      exploreProcess2.logDirect2("Loaded " + this.myChunkPositions.length + " positions");
      this.inFilter = new LongOpenHashSet();

      for (ExploreJsonChunk exploreJsonChunk : this.myChunkPositions) {
         this.inFilter.add(ChunkPos.asLong(exploreJsonChunk.x, exploreJsonChunk.z));
      }
   }

   public JsonChunkFilter(ExploreProcess exploreProcess2, Path path, boolean flag, ExploreProcess.DefaultGoalXZ obj) throws Exception {
      this(exploreProcess2, path, flag);
   }
}
