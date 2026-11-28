package net.augustus.utils.combat;

import net.augustus.Augustus;
import net.augustus.ClientManagers;
import net.augustus.commands.impl.AlertCommand;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.Entity;
import net.minecraft.entity.boss.EntityWither;
import net.minecraft.entity.item.EntityArmorStand;
import net.minecraft.entity.monster.EntityIronGolem;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.EntitySnowman;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntitySquid;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;

public class EntityTypeUtil {

   public static boolean isVillager(Entity entity) {
      return entity instanceof EntityVillager;
   }

   public static boolean isPlayer(Entity entity) {
      return entity instanceof EntityPlayer;
   }

   public static boolean isAnimal(Entity entity) {
      return entity instanceof EntityAnimal;
   }

   public static boolean isLocalPlayer(Entity entity) {
      return entity instanceof EntityPlayerSP;
   }

   public static boolean isShopNpc(Entity entity) {
      if (!isPlayer(entity) && !isVillager(entity) && !isMob(entity)) {
         return false;
      } else {
         String name = entity.getName();
         return name.equals("§aShop")
            || name.equals("§e§lITEM SHOP")
            || name.equals("SHOP")
            || name.equals("UPGRADES")
            || name.contains("§lUpgrades")
            || name.contains("§lShop");
      }
   }

   public static boolean isArmorStand(Entity entity) {
      return entity instanceof EntityArmorStand;
   }

   public static boolean isSleepingPlayer(Entity entity) {
      return isPlayer(entity) && ((EntityPlayer)entity).isPlayerSleeping();
   }

   public static boolean isTeammate(Entity entity) {
      return isPlayer(entity) && !isLocalPlayer(entity) && ClientManagers.moduleManager.teams.isTeammate(entity);
   }

   public static boolean isInvisible(Entity entity) {
      return entity.isInvisible();
   }

   public static boolean isAlertTarget(Entity entity) {
      if (isPlayer(entity)) {
         if (Augustus.getInstance().getCommandManager().alertCommand.hasAlert(entity.getName())) {
            return true;
         }
      }

      return false;
   }

   public static boolean isDead(Entity entity) {
      return entity.isDead;
   }

   public static boolean isFriend(Entity entity) {
      return isPlayer(entity) && !isLocalPlayer(entity) && !ClientManagers.moduleManager.midClick.autoUnfriendTriggered && ClientManagers.moduleManager.midClick.isFriend(entity);
   }

   public static boolean isMob(Entity entity) {
      return (entity instanceof EntityMob || entity instanceof EntityIronGolem || entity instanceof EntitySnowman || entity instanceof EntitySquid)
         && !(entity instanceof EntityWither);
   }

   public static boolean isBot(Entity entity) {
      return ClientManagers.moduleManager.antiBot.isBot(entity);
   }

   public static boolean isFocusedTarget(Entity entity) {
      if (ClientManagers.moduleManager.targetArea.isToggled()) {
         if (!ClientManagers.moduleManager.targetArea.isEntityInArea(entity)) {
            return false;
         }
      }

      return true;
   }

}
