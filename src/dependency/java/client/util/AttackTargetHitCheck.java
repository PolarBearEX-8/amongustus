package client.util;

import client.Client;
import java.util.HashSet;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;

public class AttackTargetHitCheck {
   public static final TimeHelper timer = new TimeHelper();
   public static final HashSet hashSet = new HashSet();

   public static boolean checkEntity(Entity entity) {
      if (entity instanceof EntityPlayer) {
         EntityPlayer player = (EntityPlayer)entity;
         if (SpecialHashMapEntry.resolveNativeStringEntryByEntityPlayer(player, 1) != null) {
            if (timer.hasReached(1000L)) {
               RayTraceUtil.applyString("§4ERROR: This person is in GOD mode. Unable to attack.");
               timer.reset();
            }

            return true;
         }
      }

      Client.getClient().getClass();
      return entity instanceof EntityPlayer
         && ((EntityPlayer)entity).getGameProfile() != null
         && hashSet.contains(((EntityPlayer)entity).getGameProfile().getName())
         && (double)MinecraftAccessor.minecraft.thePlayer.getDistanceToEntity(entity) >= 2.55;
   }
}
