package net.augustus.modules.modes.speed;

import net.augustus.events.EventMotion;
import net.augustus.events.EventMoveEntity;
import net.augustus.events.EventMoveInput;
import net.augustus.utils.MoveUtil;
import net.augustus.modules.movement.Speed;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;

public class EntitySpeedMode extends SpeedMode {
   public EntitySpeedMode(String str, Speed speed2) {
      super(str, speed2);
   }

   @Override
   public void onEnable() {
   }

   @Override
   public void onDisable() {
   }

   @Override
   public void onPreUpdate() {
   }

   @Override
   public void onPostUpdate() {

      for (Entity entity : (Iterable<Entity>)(mc.theWorld.loadedEntityList)) {
         if (entity instanceof EntityLivingBase
            && !(entity instanceof EntityPlayerSP)
            && entity.getEntityBoundingBox().intersectsWith(mc.thePlayer.getEntityBoundingBox())) {
            MoveUtil.addMotion(this.speed.additionalSpeed.getValue(), true);
            return;
         }
      }
   }

   @Override
   public void onMotion(EventMotion event) {
   }

   @Override
   public void onMoveEntity(EventMoveEntity event) {
   }

   @Override
   public void onPreMoveInput(EventMoveInput event) {
   }
}
