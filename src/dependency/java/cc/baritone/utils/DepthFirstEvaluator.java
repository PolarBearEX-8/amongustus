package cc.baritone.utils;

import java.nio.file.Path;

public class DepthFirstEvaluator implements NativeListener {
   public final CachedWorldBreadthFirstPipeline cachedWorldBreadthFirstPipeline;
   public final Mp4BadHeuristicHandler mp4BadHeuristicHandler;
   public final Path path;
   public final int count;

   @Override
   public HeuristicHandler getHeuristicHandler() {
      return this.mp4BadHeuristicHandler;
   }

   public void updateState() {
      BaritoneManager.getExecutor().execute(this::updateState2);
   }

   public DepthFirstEvaluator(Path path2, int count2) {
      this.path = path2;
      this.cachedWorldBreadthFirstPipeline = new CachedWorldBreadthFirstPipeline(path2.resolve("cache"), count2);
      this.mp4BadHeuristicHandler = new Mp4BadHeuristicHandler(path2.resolve("waypoints"));
      this.count = count2;
   }

   private void updateState2() {
      System.out.println("Started saving the world in a new thread");
      this.cachedWorldBreadthFirstPipeline.updateState();
   }

   @Override
   public BreadthFirstPipeline getBreadthFirstPipeline() {
      return this.cachedWorldBreadthFirstPipeline;
   }
}
