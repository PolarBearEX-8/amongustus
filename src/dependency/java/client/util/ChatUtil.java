package client.util;

import client.Client;
import client.ClientGlobals;
import client.module.Module;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityArmorstand;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EntityMonster;
import net.minecraft.entity.EntitySnowman;
import net.minecraft.entity.EntitySquid;
import net.minecraft.entity.EntityVillager;
import net.minecraft.entity.EntityVillagergolem;
import net.minecraft.entity.EntityWitherboss;
import net.minecraft.entity.player.EntityPlayer;

public class ChatUtil {
   public static boolean checkEntity(
      Entity entity, boolean flag, boolean flag2, boolean flag3, boolean flag4, boolean flag5, boolean flag6, boolean flag7, boolean flag8, boolean flag9, boolean flag10
   ) {
      boolean flag11 = false;
      boolean flag12 = false;
      boolean flag13 = false;
      boolean flag14 = true;
      boolean flag15 = false;
      return matchesEntity(entity, flag, flag2, flag3, flag4, flag5, flag6, flag7, flag8, flag9, (short)0, flag15, flag14, flag13, 1506698276, flag12, flag11, (short)18481, flag10, 0);
   }

   public static boolean checkEntity2(
      Entity entity,
      boolean flag,
      boolean flag2,
      boolean flag3,
      boolean flag4,
      boolean flag5,
      boolean flag6,
      boolean flag7,
      boolean flag8,
      boolean flag9,
      boolean flag10,
      boolean flag11,
      boolean flag12,
      int count
   ) {
      boolean flag13 = true;
      boolean flag14 = false;
      return matchesEntity(entity, flag, flag2, flag3, flag4, flag5, flag6, flag7, flag8, flag9, (short)0, flag10, flag14, flag11, 1506698276, flag13, flag12, (short)18481, false, count);
   }

   public static EntityLivingBase getEntityLivingBase() {
      return getEntityLivingBase2((EntityLivingBase)null);
   }

   public static boolean checkEntity3(
      Entity entity,
      boolean flag,
      boolean flag2,
      boolean flag3,
      boolean flag4,
      boolean flag5,
      boolean flag6,
      boolean flag7,
      boolean flag8,
      boolean flag9,
      boolean flag10,
      boolean flag11
   ) {
      boolean flag12 = true;
      boolean flag13 = true;
      boolean flag14 = false;
      return matchesEntity(entity, flag, flag2, flag3, flag4, flag5, flag6, flag7, flag8, flag9, (short)0, flag10, flag14, flag11, 1506698276, flag13, flag12, (short)18481, false, 0);
   }

   public static EntityLivingBase getEntityLivingBase2(EntityLivingBase livingEntity) {
      return ((Module)ClientGlobals.moduleManager.killAura).isToggled() && ClientGlobals.moduleManager.killAura.target != null
         ? ClientGlobals.moduleManager.killAura.target
         : (
            ((Module)ClientGlobals.moduleManager.aimBot).isToggled() && ClientGlobals.moduleManager.aimBot.target != null
               ? ClientGlobals.moduleManager.aimBot.target
               : (
                  ((Module)ClientGlobals.moduleManager.aimAssist).isToggled() && ClientGlobals.moduleManager.aimAssist.target != null
                     ? ClientGlobals.moduleManager.aimAssist.target
                     : livingEntity
               )
         );
   }

   public static boolean checkEntityPlayer(EntityPlayer player) {
      if (player.getGameProfile() == null) {
         return false;
      } else {
         byte byteValue = 2;
         return SpecialHashMapEntry.resolveNativeStringEntryByEntityPlayer(player, byteValue) != null;
      }
   }

   private static boolean matchesEntity(
      Entity entity,
      boolean flag,
      boolean flag2,
      boolean flag3,
      boolean flag4,
      boolean flag5,
      boolean flag6,
      boolean flag7,
      boolean flag8,
      boolean flag9,
      short shortValue,
      boolean flag10,
      boolean flag11,
      boolean flag12,
      int count,
      boolean flag13,
      boolean flag14,
      short shortValue2,
      boolean flag15,
      int count2
   ) {
      if (!(entity instanceof EntityLivingBase)) {
         return false;
      } else {
         EntityLivingBase livingEntity;
         boolean flag16;
         checkBlock: {
            livingEntity = (EntityLivingBase)entity;
            String text = entity.getText();
            guardBlock:
            if ((flag6 || !livingEntity.isActive12())
               && (flag11 || livingEntity.indexOffset29 <= count2 && livingEntity.ticksExisted > 1)
               && (!flag10 || !entity.isDead)
               && (
                  !flag12
                     || !text.equals("§aShop")
                        && !text.equals("§e§lITEM SHOP")
                        && !text.equals("SHOP")
                        && !text.equals("UPGRADES")
                        && !text.contains("§lUpgrades")
                        && !text.contains("§lShop")
               )) {
               searchBlock:
               if (!(livingEntity instanceof EntityPlayer)
                  || !flag
                  || !flag14 && livingEntity.isState5()
                  || flag7 && ClientGlobals.moduleManager.teams.checkEntity2(livingEntity)
                  || flag8 && !ClientGlobals.moduleManager.midClick.enabled2 && ClientGlobals.moduleManager.midClick.checkEntity(livingEntity)) {
                  if (flag15) {
                     StringEntryHandler stringEntryHandler = Client.getClient().getEventCommandCallbackAdapter().stringEntryHandler;
                     String text2 = livingEntity.getText();
                     if (stringEntryHandler.containsKey(text2)) {
                        break searchBlock;
                     }
                  }

                  if ((
                        !(livingEntity instanceof EntityMonster)
                              && !(livingEntity instanceof EntityVillagergolem)
                              && !(livingEntity instanceof EntitySnowman)
                              && !(livingEntity instanceof EntitySquid)
                           || livingEntity instanceof EntityWitherboss
                           || !flag2
                     )
                     && (!(livingEntity instanceof HitBoxBuffer) || !flag3)
                     && (!(livingEntity instanceof EntityVillager) || !flag4)
                     && (!(livingEntity instanceof EntityArmorstand) || !flag5)) {
                     break guardBlock;
                  }
               }

               if ((!flag9 || !ClientGlobals.moduleManager.antiBot.checkEntity2(entity))
                  && (!flag13 || !((Module)ClientGlobals.moduleManager.targetArea).isToggled() || ClientGlobals.moduleManager.targetArea.checkEntity(entity))) {
                  flag16 = true;
                  break checkBlock;
               }
            }

            flag16 = false;
         }

         boolean flag17 = flag16;
         return flag17 && livingEntity instanceof EntityPlayer && checkEntityPlayer((EntityPlayer)livingEntity) ? false : flag17;
      }
   }

   public static boolean checkEntity5(
      Entity entity, boolean flag, boolean flag2, boolean flag3, boolean flag4, boolean flag5, boolean flag6, boolean flag7, boolean flag8, boolean flag9
   ) {
      boolean flag10 = true;
      boolean flag11 = false;
      boolean flag12 = false;
      boolean flag13 = true;
      boolean flag14 = false;
      return matchesEntity(entity, flag, flag2, flag3, flag4, flag5, flag6, flag7, flag8, flag9, (short)0, flag14, flag13, flag12, 1506698276, flag11, flag10, (short)18481, false, 0);
   }

   public static boolean checkEntity6(
      Entity entity,
      boolean flag,
      boolean flag2,
      boolean flag3,
      boolean flag4,
      boolean flag5,
      boolean flag6,
      boolean flag7,
      boolean flag8,
      boolean flag9,
      boolean flag10,
      boolean flag11,
      int count
   ) {
      boolean flag12 = true;
      boolean flag13 = true;
      boolean flag14 = false;
      return matchesEntity(entity, flag, flag2, flag3, flag4, flag5, flag6, flag7, flag8, flag9, (short)0, flag10, flag14, flag11, 1506698276, flag13, flag12, (short)18481, false, count);
   }
}
