package baritone.cache;

import baritone.Baritone;
import baritone.api.cache.ICachedWorld;
import baritone.api.cache.IWaypointCollection;
import baritone.api.cache.IWorldData;
import java.nio.file.Path;

public class WorldData implements IWorldData {
   public final CachedWorld cache;
   private final WaypointCollection waypoints;
   public final Path directory;
   public final int dimension;

   @Override
   public ICachedWorld getCachedWorld() {
      return this.cache;
   }

   public void saveAsync() {
      Baritone.getThreadPool().execute(() -> {
         System.out.println("Started saving the world in a new thread");
         this.cache.save();
      });
   }

   public WorldData(Path path, int n) {
      this.directory = path;
      this.cache = new CachedWorld(path.resolve("cache"), n);
      this.waypoints = new WaypointCollection(path.resolve("waypoints"));
      this.dimension = n;
   }

   @Override
   public IWaypointCollection getWaypoints() {
      return this.waypoints;
   }

}
