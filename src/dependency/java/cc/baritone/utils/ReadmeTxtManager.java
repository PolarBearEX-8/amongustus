package cc.baritone.utils;

import client.util.AimObserver;
import client.util.ListenerAdapter21;
import client.util.RotationDeserializer;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import net.minecraft.client.multiplayer.ServerData;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import org.apache.commons.lang3.SystemUtils;

public class ReadmeTxtManager implements SupplementalListener {
   public static final Map map = new HashMap();
   public final BaritoneManager baritoneManager;
   public final StaticListener staticListener;
   public DepthFirstEvaluator depthFirstEvaluator;
   public World world;

   public final void updateState() {
      DepthFirstEvaluator depthFirstEvaluator2 = this.depthFirstEvaluator;
      this.depthFirstEvaluator = null;
      this.world = null;
      if (depthFirstEvaluator2 != null) {
         depthFirstEvaluator2.updateState();
      }
   }

   public final void applyWorldByWorld(World world2) {
      this.resolveOptional(world2).ifPresent(observer -> this.applyWorld(world2, (AimObserver)observer));
   }

   public final DepthFirstEvaluator getDepthFirstEvaluator() {
      this.updateState2();
      return this.depthFirstEvaluator;
   }

   private void applyWorld(World world2, AimObserver aimObserver) {
      Path dataRef = (Path)aimObserver.getDataRef2();
      Path dataRef2 = (Path)aimObserver.getDataRef();

      try {
         Files.createDirectories(dataRef2);
         Files.write(dataRef2.resolve("readme.txt"), "https://github.com/cabaletta/baritone\n".getBytes(StandardCharsets.US_ASCII));
      } catch (IOException ioException) {
      }

      dataRef = this.resolvePathByPath(dataRef, world2);

      try {
         Files.createDirectories(dataRef);
      } catch (IOException ioException2) {
      }

      System.out.println("Baritone world data dir: " + dataRef);
      synchronized (map) {
         int countAsIndex = world2.secondaryFloatArrayEntry.getCountAsIndex();
         this.depthFirstEvaluator = (DepthFirstEvaluator)map.computeIfAbsent(dataRef, dir -> ReadmeTxtManager.resolveDepthFirstEvaluator(countAsIndex, (Path)dir));
      }

      this.world = this.staticListener.getWorld();
   }

   private void updateState2() {
      if (this.world != this.staticListener.getWorld()) {
         if (this.depthFirstEvaluator != null) {
            System.out.println("mc.world unloaded unnoticed! Unloading Baritone cache now.");
            this.updateState();
         }

         if (this.staticListener.getWorld() != null) {
            System.out.println("mc.world loaded unnoticed! Loading Baritone cache now.");
            this.applyWorldByWorld(this.staticListener.getWorld());
         }
      } else if (this.depthFirstEvaluator == null
         && this.staticListener.getWorld() != null
         && (this.staticListener.getMc().isFullScreen() || this.staticListener.getMc().getServerData() != null)) {
         System.out.println("Retrying to load Baritone cache");
         this.applyWorldByWorld(this.staticListener.getWorld());
      }
   }

   private Optional resolveOptional(World world2) {
      Path path;
      Path path2;
      if (this.staticListener.getMc().isFullScreen()) {
         int countAsIndex = world2.secondaryFloatArrayEntry.getCountAsIndex();
         WorldServer worldServer = this.staticListener.getMc().getIntegratedServer().resolveWorldServer(countAsIndex);
         path2 = ((RotationDeserializer)((ListenerAdapter21)worldServer.getCommonListener()).genericListener).file.toPath();
         if (path2.relativize(this.staticListener.getMc().mcDataDir.toPath()).getNameCount() != 2) {
            path2 = path2.getParent();
         }

         path2 = path2.resolve("baritone");
         path = path2;
      } else {
         ServerData serverData = this.staticListener.getMc().getServerData();
         if (serverData == null) {
            System.out.println("World seems to be a replay. Not loading Baritone cache.");
            this.depthFirstEvaluator = null;
            this.world = this.staticListener.getWorld();
            return Optional.empty();
         }

         String text = serverData.text;
         if (SystemUtils.IS_OS_WINDOWS) {
            text = text.replace(":", "_");
         }

         path2 = this.baritoneManager.getPath().resolve(text);
         path = this.baritoneManager.getPath();
      }

      return Optional.of(new AimObserver(path2, path));
   }

   @Override
   public NativeListener getNativeListener() {
      return this.getDepthFirstEvaluator();
   }

   private static DepthFirstEvaluator resolveDepthFirstEvaluator(int count, Path path) {
      return new DepthFirstEvaluator(path, count);
   }

   private Path resolvePathByPath(Path path, World world2) {
      return path.resolve("DIM" + world2.secondaryFloatArrayEntry.getCountAsIndex());
   }

   public ReadmeTxtManager(BaritoneManager baritoneManager2) {
      this.baritoneManager = baritoneManager2;
      this.staticListener = baritoneManager2.getStaticListener();
   }
}
