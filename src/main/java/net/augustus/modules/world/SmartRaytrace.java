package net.augustus.modules.world;

import java.awt.Color;
import java.util.ArrayList;
import net.augustus.modules.Categorys;
import net.augustus.modules.Module;
import net.augustus.utils.math.RandomNumberGenerator;
import net.augustus.utils.math.DefaultRandomGenerator;
import net.augustus.utils.player.PlayerUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MovingObjectPosition;

public class SmartRaytrace extends Module {

   public SmartRaytrace() {
       super("SmartRaytrace", "SchlauerRaytrace", Color.black, Categorys.WORLD);
   }

   public void onEnable() {
   }

   public void onDisable() {
   }

   public void updateEventSubscriptions() {
   }

   public void updateSmartRaytrace() {
      if (mc.thePlayer != null && mc.entityRenderer != null) {
         ItemStack currentEquippedItem = mc.thePlayer.getCurrentEquippedItem();
         if (!PlayerUtil.canPlaceHeldItemAtMouseOver(currentEquippedItem) && (mc.objectMouseOver == null || mc.objectMouseOver.typeOfHit != MovingObjectPosition.MovingObjectType.ENTITY)) {
            boolean flag = false;
            ArrayList list = new ArrayList();
            MovingObjectPosition hit = null;

            for (float i = 0.05F; i < 1.0F; i += 0.05F) {
               mc.entityRenderer.getMouseOver(i, false);
               currentEquippedItem = mc.thePlayer.getCurrentEquippedItem();
               if (PlayerUtil.canPlaceHeldItemAtMouseOver(currentEquippedItem)) {
                  if (hit == null) {
                     hit = mc.objectMouseOver;
                  }

                  if (mc.objectMouseOver.sideHit != EnumFacing.UP) {
                     flag = true;
                     list.add(mc.objectMouseOver);
                  }
               }
            }

            if (flag) {
               mc.objectMouseOver = (MovingObjectPosition)list.get(DefaultRandomGenerator.instance.nextInt((byte)0, list.size() - 1));
            }

            if (hit != null && !flag) {
               mc.objectMouseOver = hit;
            }

            if (!flag && hit == null) {
               mc.entityRenderer.getMouseOver(1.0F, false);
            }
         }
      }
   }


}
