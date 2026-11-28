package net.raphimc.vialegacy.protocol.release.r1_2_4_5tor1_3_1_2.model;

import com.viaversion.viaversion.api.minecraft.entities.EntityTypes1_8.EntityType;
import net.raphimc.vialegacy.api.model.Location;

public abstract class AbstractTrackedEntity {
   private int entityId;
   private Location location;
   private EntityType entityType;
   private boolean isRiding;

   public AbstractTrackedEntity(int entityId, Location location, EntityType entityType) {
      this.entityId = entityId;
      this.location = location;
      this.entityType = entityType;
   }

   public int getEntityId() {
      return this.entityId;
   }

   public void setEntityId(int entityId) {
      this.entityId = entityId;
   }

   public Location getLocation() {
      return this.location;
   }

   public void setLocation(Location location) {
      this.location = location;
   }

   public EntityType getEntityType() {
      return this.entityType;
   }

   public void setEntityType(EntityType entityType) {
      this.entityType = entityType;
   }

   public boolean isRiding() {
      return this.isRiding;
   }

   public void setRiding(boolean riding) {
      this.isRiding = riding;
   }
}
