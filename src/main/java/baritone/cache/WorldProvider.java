package baritone.cache;

import baritone.Baritone;
import baritone.api.cache.IWorldData;
import baritone.api.cache.IWorldProvider;
import baritone.api.utils.IPlayerContext;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import net.minecraft.client.multiplayer.ServerData;
import net.minecraft.util.Tuple;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraft.world.chunk.storage.AnvilChunkLoader;
import net.minecraft.world.gen.ChunkProviderServer;
import org.apache.commons.lang3.SystemUtils;

public class WorldProvider implements IWorldProvider {
   private static final Map worldCache = new HashMap();
   private final Baritone baritone;
   private final IPlayerContext ctx;
   private WorldData currentWorld;
   private World currentMcWorld;

   public final void closeWorld() {
      WorldData currentWorld2 = this.currentWorld;
      this.currentWorld = null;
      this.currentMcWorld = null;
      if (currentWorld2 != null) {
         currentWorld2.saveAsync();
      }
   }

   @Override
   public IWorldData getCurrentWorld() {
      return this.getCurrentWorld2();
   }

   private void validateWorld() {
      if (this.currentMcWorld != this.ctx.world()) {
         if (this.currentWorld != null) {
            System.out.println("mc.world unloaded unnoticed! Unloading Baritone cache now.");
            this.closeWorld();
         }

         if (this.ctx.world() != null) {
            System.out.println("mc.world loaded unnoticed! Loading Baritone cache now.");
            this.loadWorld(this.ctx.world());
         }
      } else if (this.currentWorld == null && this.ctx.world() != null && (this.ctx.getMc().isSingleplayer() || this.ctx.getMc().getCurrentServerData() != null)) {
         System.out.println("Retrying to load Baritone cache");
         this.loadWorld(this.ctx.world());
      }
   }

   public WorldProvider(Baritone baritone2) {
      this.baritone = baritone2;
      this.ctx = baritone2.getPlayerContext();
   }

   private Optional getWorldDirectory(World world) {
      Path path;
      Path path2;
      if (this.ctx.getMc().isSingleplayer()) {
         int dimensionId = world.provider.getDimensionId();
         path2 = ((AnvilChunkLoader)((ChunkProviderServer)this.ctx.getMc().getIntegratedServer().worldServerForDimension(dimensionId).getChunkProvider()).chunkLoader).chunkSaveLocation.toPath();
         if (path2.relativize(this.ctx.getMc().mcDataDir.toPath()).getNameCount() != 2) {
            path2 = path2.getParent();
         }

         path2 = path2.resolve("baritone");
         path = path2;
      } else {
         ServerData currentServerData = this.ctx.getMc().getCurrentServerData();
         if (currentServerData == null) {
            System.out.println("World seems to be a replay. Not loading Baritone cache.");
            this.currentWorld = null;
            this.currentMcWorld = this.ctx.world();
            return Optional.empty();
         }

         String serverIP = currentServerData.serverIP;
         if (SystemUtils.IS_OS_WINDOWS) {
            serverIP = serverIP.replace(":", "_");
         }

         path2 = this.baritone.getDirectory().resolve(serverIP);
         path = this.baritone.getDirectory();
      }

      return Optional.of(new Tuple(path2, path));
   }

   public final WorldData getCurrentWorld2() {
      this.validateWorld();
      return this.currentWorld;
   }

   private Path getDimensionDirectory(Path path, World world) {
      return path.resolve("DIM" + world.provider.getDimensionId());
   }

   public final void loadWorld(World world) {
      this.getWorldDirectory(world).ifPresent(obj -> {
         Path first = (Path)((Tuple)obj).getFirst();
         Path second = (Path)((Tuple)obj).getSecond();

         try {
            Files.createDirectories(second);
            Files.write(second.resolve("readme.txt"), "https://github.com/cabaletta/baritone\n".getBytes(StandardCharsets.US_ASCII));
         } catch (IOException e) {
         }

         Path dimensionDirectory = this.getDimensionDirectory(first, world);

         try {
            Files.createDirectories(dimensionDirectory);
         } catch (IOException e2) {
         }

         System.out.println("Baritone world data dir: " + dimensionDirectory);
         synchronized (worldCache) {
            int dimensionId = world.provider.getDimensionId();
            this.currentWorld = (WorldData)worldCache.computeIfAbsent(dimensionDirectory, obj2 -> new WorldData((Path)obj2, dimensionId));
         }

         this.currentMcWorld = this.ctx.world();
      });
   }

}
