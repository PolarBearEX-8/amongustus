package net.raphimc.vialegacy.protocol.release.r1_2_4_5tor1_3_1_2.model;

import com.viaversion.viaversion.api.minecraft.entities.EntityTypes1_8.EntityType;
import net.raphimc.vialegacy.api.model.Location;

public class TrackedEntity extends AbstractTrackedEntity {
   public TrackedEntity(int entityId, Location location, EntityType entityType) {
      super(entityId, location, entityType);
   }
}
