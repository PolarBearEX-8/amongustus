package net.minecraft.world.demo;

import net.minecraft.profiler.Profiler;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.WorldServer;
import net.minecraft.world.WorldSettings;
import net.minecraft.world.WorldType;
import net.minecraft.world.storage.ISaveHandler;
import net.minecraft.world.storage.WorldInfo;

public class DemoWorldServer extends WorldServer {
   private static final long demoWorldSeed;
   public static final WorldSettings demoWorldSettings;

   static {
      demoWorldSeed = (long)"North Carolina".hashCode();
      demoWorldSettings = new WorldSettings(demoWorldSeed, WorldSettings.GameType.SURVIVAL, true, false, WorldType.DEFAULT).enableBonusChest();
   }

   public DemoWorldServer(MinecraftServer minecraftServer, ISaveHandler saveHandler2, WorldInfo worldInfo2, int n, Profiler profiler) {
      super(minecraftServer, saveHandler2, worldInfo2, n, profiler);
      this.worldInfo.populateFromWorldSettings(demoWorldSettings);
   }
}
