package client.util;

import client.event.NativeListener;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;
import net.minecraft.crash.CrashReport;
import net.minecraft.crash.CrashReportCategory;
import net.minecraft.entity.CustomEntityAdapter;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityArmorstand;
import net.minecraft.entity.EntityArrow;
import net.minecraft.entity.EntityBat;
import net.minecraft.entity.EntityBoat;
import net.minecraft.entity.EntityEndercrystal;
import net.minecraft.entity.EntityEnderdragon;
import net.minecraft.entity.EntityEyeofendersignal;
import net.minecraft.entity.EntityFallingsand;
import net.minecraft.entity.EntityFireworksrocketentity;
import net.minecraft.entity.EntityItem;
import net.minecraft.entity.EntityPrimedtnt;
import net.minecraft.entity.EntitySmallfireball;
import net.minecraft.entity.EntitySnowball;
import net.minecraft.entity.EntitySquid;
import net.minecraft.entity.EntityThrownegg;
import net.minecraft.entity.EntityThrownenderpearl;
import net.minecraft.entity.EntityThrownexpbottle;
import net.minecraft.entity.EntityThrownpotion;
import net.minecraft.entity.EntityWitherboss;
import net.minecraft.entity.EntityXporb;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.entity.player.EntityPlayerAdapter;
import net.minecraft.entity.projectile.EntityFireball;
import net.minecraft.entity.projectile.EntityFishHook;
import net.minecraft.item.AltItemArrayEntry;
import net.minecraft.world.WorldServer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SecondaryLoggerEntry {
   public static final Logger logger = LogManager.getLogger();
   public final WorldServer worldServer;
   public Set set = Sets.newHashSet();
   public AltItemArrayEntry altItemArrayEntry = new AltItemArrayEntry();
   public int count;

   public SecondaryLoggerEntry(WorldServer worldServer2) {
      this.worldServer = worldServer2;
      this.count = worldServer2.getVanillaRunnable().getMovingManager().getCount();
   }

   public void applyEntityPlayerAdapterByEntityPlayerAdap(EntityPlayerAdapter entityPlayerAdapter) {
      Iterator iterator = this.set.iterator();

      while (iterator.hasNext()) {
         ((CoreLoggerEntry)iterator.next()).applyEntityPlayerAdapter2(entityPlayerAdapter);
      }
   }

   public void applyEntity(Entity entity, int count2, int count3, boolean flag) {
      int count4 = count2;
      if (count2 > this.count) {
         count4 = this.count;
      }

      try {
         if (this.altItemArrayEntry.checkIndex(entity.getEntityId())) {
            throw new IllegalStateException("Entity is already tracked!");
         }

         CoreLoggerEntry coreLoggerEntry = new CoreLoggerEntry(entity, count4, count3, flag);
         this.set.add(coreLoggerEntry);
         this.altItemArrayEntry.applyTarget(entity.getEntityId(), coreLoggerEntry);
         coreLoggerEntry.applyList(this.worldServer.itemList7);
      } catch (Throwable throwable) {
         CrashReport crashReport = CrashReport.resolveCrashReport(throwable, "Adding entity to track");
         CrashReportCategory crashReportCategory = crashReport.resolveCrashReportCategory("Entity To Track");
         crashReportCategory.add("Tracking range", count4 + " blocks");
         crashReportCategory.applyText("Update interval", new OncePerTask(this, count3));
         entity.applyCrashReportCategory(crashReportCategory);
         CrashReportCategory crashReportCategory2 = crashReport.resolveCrashReportCategory("Entity That Is Already Tracked");
         ((CoreLoggerEntry)this.altItemArrayEntry.resolveTargetByIndex(entity.getEntityId())).entity.applyCrashReportCategory(crashReportCategory2);

         try {
            throw new SecondaryRuntimeExceptionAdapter(crashReport);
         } catch (SecondaryRuntimeExceptionAdapter exception) {
            logger.error("\"Silently\" catching entity tracking error.", exception);
         }
      }
   }

   public void applyEntityPlayerAdapter(EntityPlayerAdapter entityPlayerAdapter, AlertNotificationSound alertNotificationSound) {
      for (CoreLoggerEntry coreLoggerEntry : (Iterable<CoreLoggerEntry>)(Object)(this.set)) {
         if (coreLoggerEntry.entity != entityPlayerAdapter && coreLoggerEntry.entity.count7 == alertNotificationSound.indexOffset1 && coreLoggerEntry.entity.count8 == alertNotificationSound.indexOffset) {
            coreLoggerEntry.applyEntityPlayerAdapter(entityPlayerAdapter);
         }
      }
   }

   public void applyEntity2(Entity entity) {
      if (entity instanceof EntityPlayerAdapter) {
         EntityPlayerAdapter entityPlayerAdapter = (EntityPlayerAdapter)entity;
         Iterator iterator = this.set.iterator();

         while (iterator.hasNext()) {
            ((CoreLoggerEntry)iterator.next()).applyEntityPlayerAdapterByEntityPlayerAdap(entityPlayerAdapter);
         }
      }

      CoreLoggerEntry coreLoggerEntry = (CoreLoggerEntry)this.altItemArrayEntry.resolveTarget(entity.getEntityId());
      if (coreLoggerEntry != null) {
         this.set.remove(coreLoggerEntry);
         coreLoggerEntry.updateState();
      }
   }

   public void applyEntity3(Entity entity, Packet packet) {
      CoreLoggerEntry coreLoggerEntry = (CoreLoggerEntry)this.altItemArrayEntry.resolveTargetByIndex(entity.getEntityId());
      if (coreLoggerEntry != null) {
         coreLoggerEntry.applyPacketByPacket(packet);
      }
   }

   public void applyEntityPlayerAdapter(EntityPlayerAdapter entityPlayerAdapter) {
      for (CoreLoggerEntry coreLoggerEntry : (Iterable<CoreLoggerEntry>)(Object)(this.set)) {
         if (coreLoggerEntry.entity == entityPlayerAdapter) {
            coreLoggerEntry.applyList(this.worldServer.itemList7);
         } else {
            coreLoggerEntry.applyEntityPlayerAdapter(entityPlayerAdapter);
         }
      }
   }

   public void applyEntity(Entity entity) {
      if (entity instanceof EntityPlayerAdapter) {
         this.applyEntity(entity, 512, 2);
         EntityPlayerAdapter entityPlayerAdapter = (EntityPlayerAdapter)entity;

         for (CoreLoggerEntry coreLoggerEntry : (Iterable<CoreLoggerEntry>)(Object)(this.set)) {
            if (coreLoggerEntry.entity != entityPlayerAdapter) {
               coreLoggerEntry.applyEntityPlayerAdapter(entityPlayerAdapter);
            }
         }
      } else if (entity instanceof EntityFishHook) {
         this.applyEntity(entity, 64, 5, true);
      } else if (entity instanceof EntityArrow) {
         this.applyEntity(entity, 64, 20, false);
      } else if (entity instanceof EntitySmallfireball) {
         this.applyEntity(entity, 64, 10, false);
      } else if (entity instanceof EntityFireball) {
         this.applyEntity(entity, 64, 10, false);
      } else if (entity instanceof EntitySnowball) {
         this.applyEntity(entity, 64, 10, true);
      } else if (entity instanceof EntityThrownenderpearl) {
         this.applyEntity(entity, 64, 10, true);
      } else if (entity instanceof EntityEyeofendersignal) {
         this.applyEntity(entity, 64, 4, true);
      } else if (entity instanceof EntityThrownegg) {
         this.applyEntity(entity, 64, 10, true);
      } else if (entity instanceof EntityThrownpotion) {
         this.applyEntity(entity, 64, 10, true);
      } else if (entity instanceof EntityThrownexpbottle) {
         this.applyEntity(entity, 64, 10, true);
      } else if (entity instanceof EntityFireworksrocketentity) {
         this.applyEntity(entity, 64, 10, true);
      } else if (entity instanceof EntityItem) {
         this.applyEntity(entity, 64, 20, true);
      } else if (entity instanceof EntityMinecart) {
         this.applyEntity(entity, 80, 3, true);
      } else if (entity instanceof EntityBoat) {
         this.applyEntity(entity, 80, 3, true);
      } else if (entity instanceof EntitySquid) {
         this.applyEntity(entity, 64, 3, true);
      } else if (entity instanceof EntityWitherboss) {
         this.applyEntity(entity, 80, 3, false);
      } else if (entity instanceof EntityBat) {
         this.applyEntity(entity, 80, 3, false);
      } else if (entity instanceof EntityEnderdragon) {
         this.applyEntity(entity, 160, 3, true);
      } else if (entity instanceof NativeListener) {
         this.applyEntity(entity, 80, 3, true);
      } else if (entity instanceof EntityPrimedtnt) {
         this.applyEntity(entity, 160, 10, true);
      } else if (entity instanceof EntityFallingsand) {
         this.applyEntity(entity, 160, 20, true);
      } else if (entity instanceof CustomEntityAdapter) {
         this.applyEntity(entity, 160, Integer.MAX_VALUE, false);
      } else if (entity instanceof EntityArmorstand) {
         this.applyEntity(entity, 160, 3, true);
      } else if (entity instanceof EntityXporb) {
         this.applyEntity(entity, 160, 20, true);
      } else if (entity instanceof EntityEndercrystal) {
         this.applyEntity(entity, 256, Integer.MAX_VALUE, false);
      }
   }

   public void applyEntity2(Entity entity, Packet packet) {
      CoreLoggerEntry coreLoggerEntry = (CoreLoggerEntry)this.altItemArrayEntry.resolveTargetByIndex(entity.getEntityId());
      if (coreLoggerEntry != null) {
         coreLoggerEntry.applyPacket(packet);
      }
   }

   public void applyEntity(Entity entity, int count2, int count3) {
      this.applyEntity(entity, count2, count3, false);
   }

   public void updateState() {
      ArrayList list = Lists.newArrayList();

      for (CoreLoggerEntry coreLoggerEntry : (Iterable<CoreLoggerEntry>)(Object)(this.set)) {
         coreLoggerEntry.applyListByList(this.worldServer.itemList7);
         if (coreLoggerEntry.enabled5 && coreLoggerEntry.entity instanceof EntityPlayerAdapter) {
            list.add((EntityPlayerAdapter)coreLoggerEntry.entity);
         }
      }

      for (int index = 0; index < list.size(); index++) {
         EntityPlayerAdapter entityPlayerAdapter = (EntityPlayerAdapter)list.get(index);

         for (CoreLoggerEntry coreLoggerEntry2 : (Iterable<CoreLoggerEntry>)(Object)(this.set)) {
            if (coreLoggerEntry2.entity != entityPlayerAdapter) {
               coreLoggerEntry2.applyEntityPlayerAdapter(entityPlayerAdapter);
            }
         }
      }
   }
}
