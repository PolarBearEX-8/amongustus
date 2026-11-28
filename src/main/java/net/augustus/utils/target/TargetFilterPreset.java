package net.augustus.utils.target;

import net.augustus.settings.options.EntityTargetFilterOption;
import net.augustus.utils.combat.EntityTypeUtil;
import java.util.function.Function;
import net.minecraft.entity.Entity;

public enum TargetFilterPreset {
   MYSELF(obj -> createMyselfOption((Boolean)obj)),
   PLAYER(obj -> createPlayerOption((Boolean)obj)),
   MOB(obj -> createMobOption((Boolean)obj)),
   ANIMAL(obj -> createAnimalOption((Boolean)obj)),
   VILLAGER(obj -> createVillagerOption((Boolean)obj)),
   ARMOR_STAND(obj -> {
      boolean flag = (Boolean)obj;
      return new EntityTargetFilterOption("ArmorStand", flag, TargetConditionOperator.OR, (obj2, obj3) -> {
         return (Boolean)obj3 && EntityTypeUtil.isArmorStand((Entity)obj2);
      });
   }),
   INVISIBLE(obj -> createInvisibleOption((Boolean)obj)),
   DEAD(obj -> createDeadOption((Boolean)obj)),
   TEAMMATE(obj -> createTeammateOption((Boolean)obj)),
   FRIEND(obj -> createFriendOption((Boolean)obj)),
   ANTI_BOT(obj -> createAntiBotOption((Boolean)obj)),
   SHOP(obj -> createShopOption((Boolean)obj)),
   SLEEPING(obj -> createSleepingOption((Boolean)obj));

   private final Function optionFactory;

   private static EntityTargetFilterOption createShopOption(Boolean bool) {
      return new EntityTargetFilterOption("Shop", bool, TargetConditionOperator.AND, (obj, obj2) -> matchesShop((Entity)obj, (Boolean)obj2));
   }

   private static EntityTargetFilterOption createMobOption(Boolean bool) {
      boolean flag = bool;
      return new EntityTargetFilterOption("Mob", flag, TargetConditionOperator.OR, (obj, obj2) -> {
         return (Boolean)obj2 && EntityTypeUtil.isMob((Entity)obj);
      });
   }

   private static boolean matchesSleeping(Entity entity, Boolean bool) {
      return bool || !EntityTypeUtil.isSleepingPlayer(entity);
   }

   private static EntityTargetFilterOption createPlayerOption(Boolean bool) {
      return new EntityTargetFilterOption("Player", bool, TargetConditionOperator.OR, (obj, obj2) -> {
         return (Boolean)obj2 && EntityTypeUtil.isPlayer((Entity)obj) && !EntityTypeUtil.isLocalPlayer((Entity)obj);
      });
   }

   private static EntityTargetFilterOption createFriendOption(Boolean bool) {
      boolean flag = bool;
      return new EntityTargetFilterOption("Friend", flag, TargetConditionOperator.AND, (obj, obj2) -> {
         return (Boolean)obj2 || !EntityTypeUtil.isFriend((Entity)obj);
      });
   }

   private TargetFilterPreset(Function function) {
      this.optionFactory = function;
   }

   private static EntityTargetFilterOption createAnimalOption(Boolean bool) {
      return new EntityTargetFilterOption("Animal", bool, TargetConditionOperator.OR, (obj, obj2) -> {
         return (Boolean)obj2 && EntityTypeUtil.isAnimal((Entity)obj);
      });
   }

   private static EntityTargetFilterOption createAntiBotOption(Boolean bool) {
      return new EntityTargetFilterOption("AntiBot", bool, TargetConditionOperator.AND, (obj, obj2) -> matchesAntiBot((Entity)obj, (Boolean)obj2));
   }

   private static boolean matchesAntiBot(Entity entity, Boolean bool) {
      return bool || !EntityTypeUtil.isBot(entity);
   }

   private static EntityTargetFilterOption createDeadOption(Boolean bool) {
      return new EntityTargetFilterOption("Dead", bool, TargetConditionOperator.AND, (obj, obj2) -> {
         return (Boolean)obj2 || !EntityTypeUtil.isDead((Entity)obj);
      });
   }

   private static EntityTargetFilterOption createTeammateOption(Boolean bool) {
      return new EntityTargetFilterOption("Teammate", bool, TargetConditionOperator.AND, (obj, obj2) -> matchesTeammate((Entity)obj, (Boolean)obj2));
   }

   private static boolean matchesShop(Entity entity, Boolean bool) {
      return bool || !EntityTypeUtil.isShopNpc(entity);
   }

   private static boolean matchesTeammate(Entity entity, Boolean bool) {
      return bool || !EntityTypeUtil.isTeammate(entity);
   }

   public EntityTargetFilterOption createOption(boolean flag) {
      return (EntityTargetFilterOption)this.optionFactory.apply(flag);
   }

   private static EntityTargetFilterOption createMyselfOption(Boolean bool) {
      return new EntityTargetFilterOption("Myself", bool, TargetConditionOperator.OR, (obj, obj2) -> {
         return (Boolean)obj2 && EntityTypeUtil.isLocalPlayer((Entity)obj);
      }).withVisible(false);
   }

   private static EntityTargetFilterOption createSleepingOption(Boolean bool) {
      return new EntityTargetFilterOption("Sleeping", bool, TargetConditionOperator.AND, (obj, obj2) -> matchesSleeping((Entity)obj, (Boolean)obj2));
   }

   private static EntityTargetFilterOption createInvisibleOption(Boolean bool) {
      return new EntityTargetFilterOption("Invisible", bool, TargetConditionOperator.AND, (obj, obj2) -> {
         return (Boolean)obj2 || !EntityTypeUtil.isInvisible((Entity)obj);
      });
   }

   private static EntityTargetFilterOption createVillagerOption(Boolean bool) {
      boolean flag = bool;
      return new EntityTargetFilterOption("Villager", flag, TargetConditionOperator.OR, (obj, obj2) -> {
         return (Boolean)obj2 && EntityTypeUtil.isVillager((Entity)obj);
      });
   }

}
