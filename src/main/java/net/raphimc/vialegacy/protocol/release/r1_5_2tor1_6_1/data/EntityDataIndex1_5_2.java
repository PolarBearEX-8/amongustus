package net.raphimc.vialegacy.protocol.release.r1_5_2tor1_6_1.data;

import com.viaversion.viaversion.api.minecraft.entities.EntityTypes1_8.EntityType;
import com.viaversion.viaversion.util.Pair;
import java.util.HashMap;
import java.util.Optional;
import net.raphimc.vialegacy.protocol.release.r1_6_4tor1_7_2_5.types.EntityDataTypes1_6_4;

public enum EntityDataIndex1_5_2 {
   ENTITY_LIVING_POTION_EFFECT_COLOR(EntityType.LIVING_ENTITY_BASE, 8, EntityDataTypes1_6_4.INT, 7, EntityDataTypes1_6_4.INT),
   ENTITY_LIVING_IS_POTION_EFFECT_AMBIENT(EntityType.LIVING_ENTITY_BASE, 9, EntityDataTypes1_6_4.BYTE, 8, EntityDataTypes1_6_4.BYTE),
   ENTITY_LIVING_ARROWS(EntityType.LIVING_ENTITY_BASE, 10, EntityDataTypes1_6_4.BYTE, 9, EntityDataTypes1_6_4.BYTE),
   ENTITY_LIVING_NAME_TAG(EntityType.LIVING_ENTITY_BASE, 5, EntityDataTypes1_6_4.STRING, 10, EntityDataTypes1_6_4.STRING),
   ENTITY_LIVING_NAME_TAG_VISIBILITY(EntityType.LIVING_ENTITY_BASE, 6, EntityDataTypes1_6_4.BYTE, 11, EntityDataTypes1_6_4.BYTE),
   HUMAN_ABSORPTION_HEARTS(EntityType.PLAYER, 17, EntityDataTypes1_6_4.BYTE, EntityDataTypes1_6_4.FLOAT),
   BOAT_DAMAGE_TAKEN(EntityType.BOAT, 19, EntityDataTypes1_6_4.INT, EntityDataTypes1_6_4.FLOAT),
   MINECART_DAMAGE_TAKEN(EntityType.ABSTRACT_MINECART, 19, EntityDataTypes1_6_4.INT, EntityDataTypes1_6_4.FLOAT),
   WITHER_HEALTH(EntityType.WITHER, 16, EntityDataTypes1_6_4.INT, 6, EntityDataTypes1_6_4.FLOAT),
   ENDER_DRAGON_HEALTH(EntityType.ENDER_DRAGON, 16, EntityDataTypes1_6_4.INT, 6, EntityDataTypes1_6_4.FLOAT),
   WOLF_HEALTH(EntityType.WOLF, 18, EntityDataTypes1_6_4.INT, EntityDataTypes1_6_4.FLOAT);

   private static final HashMap<Pair<EntityType, Integer>, EntityDataIndex1_5_2> ENTITY_DATA_REWRITES = new HashMap<>();
   private final EntityType entityType;
   private final int oldIndex;
   private final int newIndex;
   private final EntityDataTypes1_6_4 oldType;
   private final EntityDataTypes1_6_4 newType;

   private EntityDataIndex1_5_2(EntityType entityType, int oldIndex, EntityDataTypes1_6_4 oldType, EntityDataTypes1_6_4 newType) {
      this.entityType = entityType;
      this.oldIndex = oldIndex;
      this.newIndex = oldIndex;
      this.oldType = oldType;
      this.newType = newType;
   }

   private EntityDataIndex1_5_2(EntityType entityType, int oldIndex, EntityDataTypes1_6_4 oldType, int newIndex, EntityDataTypes1_6_4 newType) {
      this.entityType = entityType;
      this.oldIndex = oldIndex;
      this.oldType = oldType;
      this.newIndex = newIndex;
      this.newType = newType;
   }

   public EntityType getEntityType() {
      return this.entityType;
   }

   public int getOldIndex() {
      return this.oldIndex;
   }

   public int getNewIndex() {
      return this.newIndex;
   }

   public EntityDataTypes1_6_4 getOldType() {
      return this.oldType;
   }

   public EntityDataTypes1_6_4 getNewType() {
      return this.newType;
   }

   private static Optional<EntityDataIndex1_5_2> getIndex(EntityType type, int index) {
      Pair<EntityType, Integer> pair = new Pair(type, index);
      return Optional.ofNullable(ENTITY_DATA_REWRITES.get(pair));
   }

   public static EntityDataIndex1_5_2 searchIndex(EntityType type, int index) {
      EntityType currentType = type;

      do {
         Optional<EntityDataIndex1_5_2> optMeta = getIndex(currentType, index);
         if (optMeta.isPresent()) {
            return optMeta.get();
         }

         currentType = currentType.getParent();
      } while (currentType != null);

      return null;
   }

   static {
      for (EntityDataIndex1_5_2 index : values()) {
         ENTITY_DATA_REWRITES.put(new Pair(index.getEntityType(), index.getOldIndex()), index);
      }
   }
}
