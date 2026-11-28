package net.minecraft.world;

import net.minecraft.world.border.IBorderListener;
import net.minecraft.world.border.WorldBorder;
import net.minecraft.profiler.Profiler;
import net.minecraft.server.MinecraftServer;
import net.minecraft.village.VillageCollection;
import net.minecraft.world.storage.DerivedWorldInfo;
import net.minecraft.world.storage.ISaveHandler;

public class WorldServerMulti extends WorldServer {
   private WorldServer delegate;

   @Override
   public void saveLevel() throws MinecraftException {
   }

   @Override
   public World init() {
      this.mapStorage = this.delegate.getMapStorage();
      this.worldScoreboard = this.delegate.getScoreboard();
      String str = VillageCollection.fileNameForProvider(this.provider);
      VillageCollection villageCollection = (VillageCollection)this.mapStorage.loadData(VillageCollection.class, str);
      if (villageCollection == null) {
         this.villageCollectionObj = new VillageCollection(this);
         this.mapStorage.setData(str, this.villageCollectionObj);
      } else {
         this.villageCollectionObj = villageCollection;
         this.villageCollectionObj.setWorldsForAll(this);
      }

      return this;
   }

   public WorldServerMulti(MinecraftServer minecraftServer, ISaveHandler saveHandler2, int n, WorldServer world, Profiler profiler) {
      super(minecraftServer, saveHandler2, new DerivedWorldInfo(world.getWorldInfo()), n, profiler);
      this.delegate = world;
      world.getWorldBorder().addListener(new WorldServerMulti.DefaultBorderListener(this));
   }


   public static class DefaultBorderListener implements IBorderListener {
      public final WorldServerMulti worldServerMulti;

      @Override
      public void onDamageBufferChanged(WorldBorder worldBorder, double d) {
         this.worldServerMulti.getWorldBorder().setDamageBuffer(d);
      }

      @Override
      public void onSizeChanged(WorldBorder worldBorder, double d) {
         this.worldServerMulti.getWorldBorder().setTransition(d);
      }

      @Override
      public void onCenterChanged(WorldBorder worldBorder, double d, double d2) {
         this.worldServerMulti.getWorldBorder().setCenter(d, d2);
      }

      public DefaultBorderListener(WorldServerMulti worldServerMulti2) {
         this.worldServerMulti = worldServerMulti2;
      }

      @Override
      public void onWarningDistanceChanged(WorldBorder worldBorder, int n) {
         this.worldServerMulti.getWorldBorder().setWarningDistance(n);
      }

      @Override
      public void onDamageAmountChanged(WorldBorder worldBorder, double d) {
         this.worldServerMulti.getWorldBorder().setDamageAmount(d);
      }

      @Override
      public void onWarningTimeChanged(WorldBorder worldBorder, int n) {
         this.worldServerMulti.getWorldBorder().setWarningTime(n);
      }

      @Override
      public void onTransitionStarted(WorldBorder worldBorder, double d, double d2, long l) {
         this.worldServerMulti.getWorldBorder().setTransition(d, d2, l);
      }
   }
}
