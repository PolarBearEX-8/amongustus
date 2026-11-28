package net.minecraft.entity;

import java.util.concurrent.Callable;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import java.util.ArrayList;
import java.util.Set;
import net.minecraft.crash.CrashReport;
import net.minecraft.crash.CrashReportCategory;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.entity.boss.EntityWither;
import net.minecraft.entity.item.EntityArmorStand;
import net.minecraft.entity.item.EntityBoat;
import net.minecraft.entity.item.EntityEnderCrystal;
import net.minecraft.entity.item.EntityEnderEye;
import net.minecraft.entity.item.EntityEnderPearl;
import net.minecraft.entity.item.EntityExpBottle;
import net.minecraft.entity.item.EntityFallingBlock;
import net.minecraft.entity.item.EntityFireworkRocket;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.entity.item.EntityTNTPrimed;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.passive.EntityBat;
import net.minecraft.entity.passive.EntitySquid;
import net.minecraft.entity.passive.IAnimals;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.entity.projectile.EntityEgg;
import net.minecraft.entity.projectile.EntityFireball;
import net.minecraft.entity.projectile.EntityFishHook;
import net.minecraft.entity.projectile.EntityPotion;
import net.minecraft.entity.projectile.EntitySmallFireball;
import net.minecraft.entity.projectile.EntitySnowball;
import net.minecraft.network.Packet;
import net.minecraft.util.IntHashMap;
import net.minecraft.util.ReportedException;
import net.minecraft.world.WorldServer;
import net.minecraft.world.chunk.Chunk;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class EntityTracker {
   private static final Logger LOGGER = LogManager.getLogger();
   private final WorldServer theWorld;
   private Set trackedEntities = Sets.newHashSet();
   private IntHashMap trackedEntityHashTable = new IntHashMap();
   private int maxTrackingDistanceThreshold;

   public void sendToTrackingAndSelf(Entity entity, Packet packet) {
      EntityTrackerEntry entityTrackerEntry = (EntityTrackerEntry)this.trackedEntityHashTable.lookup(entity.getEntityId());
      if (entityTrackerEntry != null) {
         entityTrackerEntry.sendToTrackingAndSelf(packet);
      }
   }

   public void removePlayerFromTrackers(EntityPlayerMP entityPlayerMP) {
      for (EntityTrackerEntry entityTrackerEntry : (Iterable<EntityTrackerEntry>)(this.trackedEntities)) {
         entityTrackerEntry.removeTrackedPlayerSymmetric(entityPlayerMP);
      }
   }

   public void sendToAllTrackingEntity(Entity entity, Packet packet) {
      EntityTrackerEntry entityTrackerEntry = (EntityTrackerEntry)this.trackedEntityHashTable.lookup(entity.getEntityId());
      if (entityTrackerEntry != null) {
         entityTrackerEntry.sendPacketToTrackedPlayers(packet);
      }
   }

   public void sendLeashedEntitiesInChunk(EntityPlayerMP entityPlayerMP, Chunk chunk) {
      for (EntityTrackerEntry entityTrackerEntry : (Iterable<EntityTrackerEntry>)(this.trackedEntities)) {
         if (entityTrackerEntry.trackedEntity != entityPlayerMP && entityTrackerEntry.trackedEntity.chunkCoordX == chunk.xPosition && entityTrackerEntry.trackedEntity.chunkCoordZ == chunk.zPosition) {
            entityTrackerEntry.updatePlayerEntity(entityPlayerMP);
         }
      }
   }

   public void updateVisibility(EntityPlayerMP entityPlayerMP) {
      for (EntityTrackerEntry entityTrackerEntry : (Iterable<EntityTrackerEntry>)(this.trackedEntities)) {
         if (entityTrackerEntry.trackedEntity == entityPlayerMP) {
            entityTrackerEntry.updatePlayerEntities(this.theWorld.playerEntities);
         } else {
            entityTrackerEntry.updatePlayerEntity(entityPlayerMP);
         }
      }
   }

   public void untrackEntity(Entity entity) {
      if (entity instanceof EntityPlayerMP) {
         EntityPlayerMP entityPlayerMP = (EntityPlayerMP)entity;

         for (EntityTrackerEntry entityTrackerEntry : (Iterable<EntityTrackerEntry>)(this.trackedEntities)) {
            entityTrackerEntry.removeFromTrackedPlayers(entityPlayerMP);
         }
      }

      EntityTrackerEntry entityTrackerEntry2 = (EntityTrackerEntry)this.trackedEntityHashTable.removeObject(entity.getEntityId());
      if (entityTrackerEntry2 != null) {
         this.trackedEntities.remove(entityTrackerEntry2);
         entityTrackerEntry2.sendDestroyEntityPacketToTrackedPlayers();
      }
   }

   public void trackEntity(Entity entity) {
      if (entity instanceof EntityPlayerMP) {
         this.trackEntity(entity, 512, 2);
         EntityPlayerMP entityPlayerMP = (EntityPlayerMP)entity;

         for (EntityTrackerEntry entityTrackerEntry : (Iterable<EntityTrackerEntry>)(this.trackedEntities)) {
            if (entityTrackerEntry.trackedEntity != entityPlayerMP) {
               entityTrackerEntry.updatePlayerEntity(entityPlayerMP);
            }
         }
      } else if (entity instanceof EntityFishHook) {
         this.addEntityToTracker(entity, 64, 5, true);
      } else if (entity instanceof EntityArrow) {
         this.addEntityToTracker(entity, 64, 20, false);
      } else if (entity instanceof EntitySmallFireball) {
         this.addEntityToTracker(entity, 64, 10, false);
      } else if (entity instanceof EntityFireball) {
         this.addEntityToTracker(entity, 64, 10, false);
      } else if (entity instanceof EntitySnowball) {
         this.addEntityToTracker(entity, 64, 10, true);
      } else if (entity instanceof EntityEnderPearl) {
         this.addEntityToTracker(entity, 64, 10, true);
      } else if (entity instanceof EntityEnderEye) {
         this.addEntityToTracker(entity, 64, 4, true);
      } else if (entity instanceof EntityEgg) {
         this.addEntityToTracker(entity, 64, 10, true);
      } else if (entity instanceof EntityPotion) {
         this.addEntityToTracker(entity, 64, 10, true);
      } else if (entity instanceof EntityExpBottle) {
         this.addEntityToTracker(entity, 64, 10, true);
      } else if (entity instanceof EntityFireworkRocket) {
         this.addEntityToTracker(entity, 64, 10, true);
      } else if (entity instanceof EntityItem) {
         this.addEntityToTracker(entity, 64, 20, true);
      } else if (entity instanceof EntityMinecart) {
         this.addEntityToTracker(entity, 80, 3, true);
      } else if (entity instanceof EntityBoat) {
         this.addEntityToTracker(entity, 80, 3, true);
      } else if (entity instanceof EntitySquid) {
         this.addEntityToTracker(entity, 64, 3, true);
      } else if (entity instanceof EntityWither) {
         this.addEntityToTracker(entity, 80, 3, false);
      } else if (entity instanceof EntityBat) {
         this.addEntityToTracker(entity, 80, 3, false);
      } else if (entity instanceof EntityDragon) {
         this.addEntityToTracker(entity, 160, 3, true);
      } else if (entity instanceof IAnimals) {
         this.addEntityToTracker(entity, 80, 3, true);
      } else if (entity instanceof EntityTNTPrimed) {
         this.addEntityToTracker(entity, 160, 10, true);
      } else if (entity instanceof EntityFallingBlock) {
         this.addEntityToTracker(entity, 160, 20, true);
      } else if (entity instanceof EntityHanging) {
         this.addEntityToTracker(entity, 160, Integer.MAX_VALUE, false);
      } else if (entity instanceof EntityArmorStand) {
         this.addEntityToTracker(entity, 160, 3, true);
      } else if (entity instanceof EntityXPOrb) {
         this.addEntityToTracker(entity, 160, 20, true);
      } else if (entity instanceof EntityEnderCrystal) {
         this.addEntityToTracker(entity, 256, Integer.MAX_VALUE, false);
      }
   }

   public EntityTracker(WorldServer world) {
      this.theWorld = world;
      this.maxTrackingDistanceThreshold = world.getMinecraftServer().getConfigurationManager().getEntityViewDistance();
   }

   public void addEntityToTracker(Entity entity, int n, int n2, boolean flag) {
      if (n > this.maxTrackingDistanceThreshold) {
         n = this.maxTrackingDistanceThreshold;
      }

      try {
         if (this.trackedEntityHashTable.containsItem(entity.getEntityId())) {
            throw new IllegalStateException("Entity is already tracked!");
         }

         EntityTrackerEntry entityTrackerEntry = new EntityTrackerEntry(entity, n, n2, flag);
         this.trackedEntities.add(entityTrackerEntry);
         this.trackedEntityHashTable.addKey(entity.getEntityId(), entityTrackerEntry);
         entityTrackerEntry.updatePlayerEntities(this.theWorld.playerEntities);
      } catch (Throwable e) {
         CrashReport crashReport = CrashReport.makeCrashReport(e, "Adding entity to track");
         CrashReportCategory crashReportCategory = crashReport.makeCategory("Entity To Track");
         crashReportCategory.addCrashSection("Tracking range", n + " blocks");
         crashReportCategory.addCrashSectionCallable("Update interval", new EntityTracker.ValueCallable(this, n2));
         entity.addEntityCrashInfo(crashReportCategory);
         CrashReportCategory crashReportCategory2 = crashReport.makeCategory("Entity That Is Already Tracked");
         ((EntityTrackerEntry)this.trackedEntityHashTable.lookup(entity.getEntityId())).trackedEntity.addEntityCrashInfo(crashReportCategory2);

         try {
            throw new ReportedException(crashReport);
         } catch (ReportedException e2) {
            LOGGER.error("\"Silently\" catching entity tracking error.", e2);
         }
      }
   }

   public void updateTrackedEntities() {
      ArrayList list = Lists.newArrayList();

      for (EntityTrackerEntry entityTrackerEntry : (Iterable<EntityTrackerEntry>)(this.trackedEntities)) {
         entityTrackerEntry.updatePlayerList(this.theWorld.playerEntities);
         if (entityTrackerEntry.playerEntitiesUpdated && entityTrackerEntry.trackedEntity instanceof EntityPlayerMP) {
            list.add((EntityPlayerMP)entityTrackerEntry.trackedEntity);
         }
      }

      for (int i = 0; i < list.size(); i++) {
         EntityPlayerMP entityPlayerMP = (EntityPlayerMP)list.get(i);

         for (EntityTrackerEntry entityTrackerEntry2 : (Iterable<EntityTrackerEntry>)(this.trackedEntities)) {
            if (entityTrackerEntry2.trackedEntity != entityPlayerMP) {
               entityTrackerEntry2.updatePlayerEntity(entityPlayerMP);
            }
         }
      }
   }

   public void trackEntity(Entity entity, int n, int n2) {
      this.addEntityToTracker(entity, n, n2, false);
   }


   public static class ValueCallable implements Callable {
      public final int updateFrequency;
      public final EntityTracker tracker;

      public String getUpdateFrequencyInfo() throws Exception {
         String str = "Once per " + this.updateFrequency + " ticks";
         if (this.updateFrequency == Integer.MAX_VALUE) {
            str = "Maximum (" + str + ")";
         }

         return str;
      }

      @Override
      public Object call() throws Exception {
         return this.getUpdateFrequencyInfo();
      }

      public ValueCallable(EntityTracker entityTracker, int n) {
         this.tracker = entityTracker;
         this.updateFrequency = n;
      }
   }
}
