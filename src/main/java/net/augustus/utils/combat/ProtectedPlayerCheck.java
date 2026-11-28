package net.augustus.utils.combat;

import java.util.HashSet;
import net.augustus.utils.interfaces.MC;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;

public class ProtectedPlayerCheck {
   private static final HashSet<String> PROTECTED_NAMES = new HashSet<>();

   public static boolean isProtectedPlayer(Entity entity) {
      return entity instanceof EntityPlayer
         && ((EntityPlayer)entity).getGameProfile() != null
         && PROTECTED_NAMES.contains(((EntityPlayer)entity).getGameProfile().getName())
         && (double)MC.mc.thePlayer.getDistanceToEntityBox(entity) >= 2.55;
   }
}
