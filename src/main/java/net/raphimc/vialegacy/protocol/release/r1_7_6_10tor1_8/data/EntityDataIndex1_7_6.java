package net.raphimc.vialegacy.protocol.release.r1_7_6_10tor1_8.data;

import com.viaversion.viaversion.api.minecraft.entities.EntityTypes1_8.EntityType;
import com.viaversion.viaversion.api.minecraft.entitydata.types.EntityDataTypes1_8;
import com.viaversion.viaversion.util.Pair;
import java.util.HashMap;
import java.util.Optional;
import net.raphimc.vialegacy.protocol.release.r1_7_6_10tor1_8.types.EntityDataTypes1_7_6;

public enum EntityDataIndex1_7_6 {
   ENTITY_FLAGS(EntityType.ENTITY, 0, EntityDataTypes1_7_6.BYTE, EntityDataTypes1_8.BYTE),
   ENTITY_AIR(EntityType.ENTITY, 1, EntityDataTypes1_7_6.SHORT, EntityDataTypes1_8.SHORT),
   ENTITY_LIVING_HEALTH(EntityType.LIVING_ENTITY_BASE, 6, EntityDataTypes1_7_6.FLOAT, EntityDataTypes1_8.FLOAT),
   ENTITY_LIVING_POTION_EFFECT_COLOR(EntityType.LIVING_ENTITY_BASE, 7, EntityDataTypes1_7_6.INT, EntityDataTypes1_8.INT),
   ENTITY_LIVING_IS_POTION_EFFECT_AMBIENT(EntityType.LIVING_ENTITY_BASE, 8, EntityDataTypes1_7_6.BYTE, EntityDataTypes1_8.BYTE),
   ENTITY_LIVING_ARROWS(EntityType.LIVING_ENTITY_BASE, 9, EntityDataTypes1_7_6.BYTE, EntityDataTypes1_8.BYTE),
   ENTITY_LIVING_NAME_TAG(EntityType.LIVING_ENTITY_BASE, 10, EntityDataTypes1_7_6.STRING, 2, EntityDataTypes1_8.STRING),
   ENTITY_LIVING_NAME_TAG_VISIBILITY(EntityType.LIVING_ENTITY_BASE, 11, EntityDataTypes1_7_6.BYTE, 3, EntityDataTypes1_8.BYTE),
   ENTITY_AGEABLE_AGE(EntityType.ABSTRACT_AGEABLE, 12, EntityDataTypes1_7_6.INT, EntityDataTypes1_8.BYTE),
   HUMAN_SKIN_FLAGS(EntityType.PLAYER, 16, EntityDataTypes1_7_6.BYTE, 10, EntityDataTypes1_8.BYTE),
   HUMAN_ABSORPTION_HEARTS(EntityType.PLAYER, 17, EntityDataTypes1_7_6.FLOAT, EntityDataTypes1_8.FLOAT),
   HUMAN_SCORE(EntityType.PLAYER, 18, EntityDataTypes1_7_6.INT, EntityDataTypes1_8.INT),
   HORSE_FLAGS(EntityType.HORSE, 16, EntityDataTypes1_7_6.INT, EntityDataTypes1_8.INT),
   HORSE_TYPE(EntityType.HORSE, 19, EntityDataTypes1_7_6.BYTE, EntityDataTypes1_8.BYTE),
   HORSE_COLOR(EntityType.HORSE, 20, EntityDataTypes1_7_6.INT, EntityDataTypes1_8.INT),
   HORSE_OWNER(EntityType.HORSE, 21, EntityDataTypes1_7_6.STRING, EntityDataTypes1_8.STRING),
   HORSE_ARMOR(EntityType.HORSE, 22, EntityDataTypes1_7_6.INT, EntityDataTypes1_8.INT),
   BAT_HANGING(EntityType.BAT, 16, EntityDataTypes1_7_6.BYTE, EntityDataTypes1_8.BYTE),
   TAMEABLE_FLAGS(EntityType.TAMABLE_ANIMAL, 16, EntityDataTypes1_7_6.BYTE, EntityDataTypes1_8.BYTE),
   TAMEABLE_OWNER(EntityType.TAMABLE_ANIMAL, 17, EntityDataTypes1_7_6.STRING, EntityDataTypes1_8.STRING),
   OCELOT_TYPE(EntityType.OCELOT, 18, EntityDataTypes1_7_6.BYTE, EntityDataTypes1_8.BYTE),
   WOLF_HEALTH(EntityType.WOLF, 18, EntityDataTypes1_7_6.FLOAT, EntityDataTypes1_8.FLOAT),
   WOLF_BEGGING(EntityType.WOLF, 19, EntityDataTypes1_7_6.BYTE, EntityDataTypes1_8.BYTE),
   WOLF_COLLAR_COLOR(EntityType.WOLF, 20, EntityDataTypes1_7_6.BYTE, EntityDataTypes1_8.BYTE),
   PIG_SADDLE(EntityType.PIG, 16, EntityDataTypes1_7_6.BYTE, EntityDataTypes1_8.BYTE),
   SHEEP_COLOR_OR_SHEARED(EntityType.SHEEP, 16, EntityDataTypes1_7_6.BYTE, EntityDataTypes1_8.BYTE),
   VILLAGER_TYPE(EntityType.VILLAGER, 16, EntityDataTypes1_7_6.INT, EntityDataTypes1_8.INT),
   ENDERMAN_CARRIED_BLOCK(EntityType.ENDERMAN, 16, EntityDataTypes1_7_6.BYTE, EntityDataTypes1_8.SHORT),
   ENDERMAN_CARRIED_BLOCK_DATA(EntityType.ENDERMAN, 17, EntityDataTypes1_7_6.BYTE, EntityDataTypes1_8.BYTE),
   ENDERMAN_IS_SCREAMING(EntityType.ENDERMAN, 18, EntityDataTypes1_7_6.BYTE, EntityDataTypes1_8.BYTE),
   ZOMBIE_CHILD(EntityType.ZOMBIE, 12, EntityDataTypes1_7_6.BYTE, EntityDataTypes1_8.BYTE),
   ZOMBIE_VILLAGER(EntityType.ZOMBIE, 13, EntityDataTypes1_7_6.BYTE, EntityDataTypes1_8.BYTE),
   ZOMBIE_CONVERTING(EntityType.ZOMBIE, 14, EntityDataTypes1_7_6.BYTE, EntityDataTypes1_8.BYTE),
   BLAZE_ON_FIRE(EntityType.BLAZE, 16, EntityDataTypes1_7_6.BYTE, EntityDataTypes1_8.BYTE),
   SPIDER_CLIMBING(EntityType.SPIDER, 16, EntityDataTypes1_7_6.BYTE, EntityDataTypes1_8.BYTE),
   CREEPER_STATE(EntityType.CREEPER, 16, EntityDataTypes1_7_6.BYTE, EntityDataTypes1_8.BYTE),
   CREEPER_POWERED(EntityType.CREEPER, 17, EntityDataTypes1_7_6.BYTE, EntityDataTypes1_8.BYTE),
   CREEPER_ISIGNITED(EntityType.CREEPER, 18, EntityDataTypes1_7_6.BYTE, EntityDataTypes1_8.BYTE),
   GHAST_STATE(EntityType.GHAST, 16, EntityDataTypes1_7_6.BYTE, EntityDataTypes1_8.BYTE),
   GHAST_IS_POWERED(EntityType.GHAST, 17, null, EntityDataTypes1_8.BYTE),
   SLIME_SIZE(EntityType.SLIME, 16, EntityDataTypes1_7_6.BYTE, EntityDataTypes1_8.BYTE),
   SKELETON_TYPE(EntityType.SKELETON, 13, EntityDataTypes1_7_6.BYTE, EntityDataTypes1_8.BYTE),
   WITCH_AGRESSIVE(EntityType.WITCH, 21, EntityDataTypes1_7_6.BYTE, EntityDataTypes1_8.BYTE),
   IRON_GOLEM_IS_PLAYER_CREATED(EntityType.IRON_GOLEM, 16, EntityDataTypes1_7_6.BYTE, EntityDataTypes1_8.BYTE),
   WITHER_WATCHED_TAGRET_1(EntityType.WITHER, 17, EntityDataTypes1_7_6.INT, EntityDataTypes1_8.INT),
   WITHER_WATCHED_TAGRET_2(EntityType.WITHER, 18, EntityDataTypes1_7_6.INT, EntityDataTypes1_8.INT),
   WITHER_WATCHED_TAGRET_3(EntityType.WITHER, 19, EntityDataTypes1_7_6.INT, EntityDataTypes1_8.INT),
   WITHER_INVULNERABLE_TIME(EntityType.WITHER, 20, EntityDataTypes1_7_6.INT, EntityDataTypes1_8.INT),
   WITHER_SKULL_ISINVULNERABLE(EntityType.WITHER_SKULL, 10, EntityDataTypes1_7_6.BYTE, EntityDataTypes1_8.BYTE),
   GUARDIAN_FLAGS(EntityType.GUARDIAN, 16, null, EntityDataTypes1_8.BYTE),
   GUARDIAN_TARGET(EntityType.GUARDIAN, 17, null, EntityDataTypes1_8.INT),
   BOAT_TIME_SINCE_HIT(EntityType.BOAT, 17, EntityDataTypes1_7_6.INT, EntityDataTypes1_8.INT),
   BOAT_FORWARD_DIRECTION(EntityType.BOAT, 18, EntityDataTypes1_7_6.INT, EntityDataTypes1_8.INT),
   BOAT_DAMAGE_TAKEN(EntityType.BOAT, 19, EntityDataTypes1_7_6.FLOAT, EntityDataTypes1_8.FLOAT),
   MINECART_SHAKING_POWER(EntityType.ABSTRACT_MINECART, 17, EntityDataTypes1_7_6.INT, EntityDataTypes1_8.INT),
   MINECART_SHAKING_DIRECTION(EntityType.ABSTRACT_MINECART, 18, EntityDataTypes1_7_6.INT, EntityDataTypes1_8.INT),
   MINECART_DAMAGE_TAKEN(EntityType.ABSTRACT_MINECART, 19, EntityDataTypes1_7_6.FLOAT, EntityDataTypes1_8.FLOAT),
   MINECART_BLOCK_INSIDE(EntityType.ABSTRACT_MINECART, 20, EntityDataTypes1_7_6.INT, EntityDataTypes1_8.INT),
   MINECART_BLOCK_Y(EntityType.ABSTRACT_MINECART, 21, EntityDataTypes1_7_6.INT, EntityDataTypes1_8.INT),
   MINECART_SHOW_BLOCK(EntityType.ABSTRACT_MINECART, 22, EntityDataTypes1_7_6.BYTE, EntityDataTypes1_8.BYTE),
   FURNACE_MINECART_IS_POWERED(EntityType.FURNACE_MINECART, 16, EntityDataTypes1_7_6.BYTE, EntityDataTypes1_8.BYTE),
   ITEM_ITEM(EntityType.ITEM, 10, EntityDataTypes1_7_6.ITEM, EntityDataTypes1_8.ITEM),
   ARROW_IS_CRITICAL(EntityType.ARROW, 16, EntityDataTypes1_7_6.BYTE, EntityDataTypes1_8.BYTE),
   FIREWORK_INFO(EntityType.FIREWORK_ROCKET, 8, EntityDataTypes1_7_6.ITEM, EntityDataTypes1_8.ITEM),
   ITEM_FRAME_ITEM(EntityType.ITEM_FRAME, 2, EntityDataTypes1_7_6.ITEM, 8, EntityDataTypes1_8.ITEM),
   ITEM_FRAME_ROTATION(EntityType.ITEM_FRAME, 3, EntityDataTypes1_7_6.BYTE, 9, EntityDataTypes1_8.BYTE),
   ENDER_CRYSTAL_HEALTH(EntityType.END_CRYSTAL, 8, EntityDataTypes1_7_6.INT, 8, EntityDataTypes1_8.INT);

   private static final HashMap<Pair<EntityType, Integer>, EntityDataIndex1_7_6> ENTITY_DATA_REWRITES = new HashMap<>();
   private final EntityType entityType;
   private final int oldIndex;
   private final int newIndex;
   private final EntityDataTypes1_7_6 oldType;
   private final EntityDataTypes1_8 newType;

   private EntityDataIndex1_7_6(EntityType entityType, int oldIndex, EntityDataTypes1_7_6 oldType, EntityDataTypes1_8 newType) {
      this.entityType = entityType;
      this.oldIndex = oldIndex;
      this.newIndex = oldIndex;
      this.oldType = oldType;
      this.newType = newType;
   }

   private EntityDataIndex1_7_6(EntityType entityType, int oldIndex, EntityDataTypes1_7_6 oldType, int newIndex, EntityDataTypes1_8 newType) {
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

   public EntityDataTypes1_7_6 getOldType() {
      return this.oldType;
   }

   public EntityDataTypes1_8 getNewType() {
      return this.newType;
   }

   private static Optional<EntityDataIndex1_7_6> getIndex(EntityType type, int index) {
      Pair<EntityType, Integer> pair = new Pair(type, index);
      return Optional.ofNullable(ENTITY_DATA_REWRITES.get(pair));
   }

   public static EntityDataIndex1_7_6 searchIndex(EntityType type, int index) {
      EntityType currentType = type;

      do {
         Optional<EntityDataIndex1_7_6> optMeta = getIndex(currentType, index);
         if (optMeta.isPresent()) {
            return optMeta.get();
         }

         currentType = currentType.getParent();
      } while (currentType != null);

      return null;
   }

   static {
      for (EntityDataIndex1_7_6 index : values()) {
         ENTITY_DATA_REWRITES.put(new Pair(index.getEntityType(), index.getOldIndex()), index);
      }
   }
}
