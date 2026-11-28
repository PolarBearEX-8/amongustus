package net.raphimc.vialegacy.protocol.release.r1_7_6_10tor1_8.storage;

import com.viaversion.viaversion.api.connection.StoredObject;
import com.viaversion.viaversion.api.connection.UserConnection;
import com.viaversion.viaversion.api.minecraft.entities.EntityTypes1_8.EntityType;
import com.viaversion.viaversion.api.minecraft.entitydata.EntityData;
import com.viaversion.viaversion.libs.fastutil.ints.Int2ObjectMap;
import com.viaversion.viaversion.libs.fastutil.ints.Int2ObjectOpenHashMap;
import com.viaversion.viaversion.libs.fastutil.objects.ObjectIterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ThreadLocalRandom;
import net.raphimc.vialegacy.api.model.Location;
import net.raphimc.vialegacy.protocol.release.r1_7_6_10tor1_8.data.EntityDataIndex1_7_6;
import net.raphimc.vialegacy.protocol.release.r1_7_6_10tor1_8.model.HologramPartEntity;

public class EntityTracker extends StoredObject {
   private final Map<Integer, EntityType> entityMap = new ConcurrentHashMap<>();
   private final Map<Integer, Boolean> groundMap = new ConcurrentHashMap<>();
   private final Int2ObjectMap<HologramPartEntity> hologramParts = new Int2ObjectOpenHashMap();
   private final Int2ObjectMap<HologramPartEntity> virtualHolograms = new Int2ObjectOpenHashMap();
   private int playerID;

   public EntityTracker(UserConnection user) {
      super(user);
   }

   public int getPlayerID() {
      return this.playerID;
   }

   public void setPlayerID(int playerID) {
      this.playerID = playerID;
   }

   public Map<Integer, EntityType> getTrackedEntities() {
      return this.entityMap;
   }

   public Map<Integer, Boolean> getGroundMap() {
      return this.groundMap;
   }

   public Int2ObjectMap<HologramPartEntity> getVirtualHolograms() {
      return this.virtualHolograms;
   }

   public void trackEntity(int entityId, EntityType entityType) {
      if (this.virtualHolograms.containsKey(entityId)) {
         int newMappedEntityId = this.getNextMappedEntityId();
         HologramPartEntity hologramPartEntity = (HologramPartEntity)this.virtualHolograms.remove(entityId);
         hologramPartEntity.relocate(newMappedEntityId);
         this.hologramParts.put(newMappedEntityId, hologramPartEntity);
      }

      if (this.entityMap.containsKey(entityId)) {
         this.removeEntity(entityId);
      }

      this.entityMap.put(entityId, entityType);
      if (entityType == EntityType.HORSE || entityType == EntityType.WITHER_SKULL) {
         this.hologramParts.put(entityId, new HologramPartEntity(this.user(), entityId, entityType));
      }
   }

   public void removeEntity(int entityId) {
      this.entityMap.remove(entityId);
      this.groundMap.remove(entityId);
      HologramPartEntity removedEntity = (HologramPartEntity)this.hologramParts.remove(entityId);
      if (removedEntity != null) {
         if (removedEntity.getRiderEntity() != null) {
            removedEntity.getRiderEntity().setVehicleEntity(null);
         }

         if (removedEntity.getVehicleEntity() != null) {
            removedEntity.setVehicleEntity(null);
         }

         removedEntity.onRemove();
      }
   }

   public void updateEntityLocation(int entityId, int x, int y, int z, boolean relative) {
      HologramPartEntity entity = (HologramPartEntity)this.hologramParts.get(entityId);
      if (entity != null) {
         Location oldLoc = entity.getLocation();
         double xPos = (double)x / 32.0;
         double yPos = (double)y / 32.0;
         double zPos = (double)z / 32.0;
         Location newLoc;
         if (relative) {
            newLoc = new Location(oldLoc.getX() + xPos, oldLoc.getY() + yPos, oldLoc.getZ() + zPos);
         } else {
            newLoc = new Location(xPos, yPos, zPos);
         }

         entity.setLocation(newLoc);
      }
   }

   public void updateEntityData(int entityId, List<EntityData> entityDataList) {
      HologramPartEntity entity = (HologramPartEntity)this.hologramParts.get(entityId);
      if (entity != null) {
         for (EntityData entityData : entityDataList) {
            EntityDataIndex1_7_6 entityDataIndex = EntityDataIndex1_7_6.searchIndex(entity.getEntityType(), entityData.id());
            if (entityDataIndex != null) {
               try {
                  entityData.setTypeAndValue(entityDataIndex.getOldType(), entityData.getValue());
               } catch (Throwable e) {
                  continue;
               }

               entity.setEntityData(entityDataIndex, entityData.getValue());
            }
         }

         entity.onChange();
      }
   }

   public void updateEntityAttachState(int ridingId, int vehicleId) {
      HologramPartEntity ridingEntity = (HologramPartEntity)this.hologramParts.get(ridingId);
      if (ridingEntity != null) {
         ridingEntity.setVehicleEntity((HologramPartEntity)this.hologramParts.get(vehicleId));
      }
   }

   public void clear() {
      this.entityMap.clear();
      this.groundMap.clear();
      ObjectIterator it = this.hologramParts.values().iterator();

      while (it.hasNext()) {
         ((HologramPartEntity)it.next()).onRemove();
      }

      this.virtualHolograms.clear();
   }

   public int getNextMappedEntityId() {
      int id;
      do {
         id = ThreadLocalRandom.current().nextInt(1000000000, Integer.MAX_VALUE);
      } while (this.entityMap.containsKey(id) || this.virtualHolograms.containsKey(id));

      return id;
   }
}
