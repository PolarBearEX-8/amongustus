package augustus.modes.movement;

import augustus.modules.movement.Speed;
import client.event.EventMotionUpdate;
import client.event.EventMove;
import client.event.EventMoveEntity;
import client.util.MinecraftAccessor;
import client.util.MoveUtil;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;

public class SpeedEntitySpeedMode extends SpeedModeHandler {
   @Override
   public void onMove(EventMove event) {
   }

   @Override
   public void updateState2() {
   }

   @Override
   public void processAction() {
      for (Entity entity : (Iterable<Entity>)(Iterable)(MinecraftAccessor.minecraft.theWorld.loadedEntityList)) {
         if (entity instanceof EntityLivingBase
            && !(entity instanceof EntityPlayerSP)
            && entity.getBoundingBox2().intersectsWith(MinecraftAccessor.minecraft.thePlayer.getBoundingBox2())) {
            MoveUtil.processAction(super.speed.settingAdditionalSpeed.getValue(), true);
            return;
         }
      }
   }

   @Override
   public void processChar() {
   }

   @Override
   public void onMoveEntity(EventMoveEntity event) {
   }

   @Override
   public void updateState3() {
   }

   @Override
   public void onMotionUpdate(EventMotionUpdate event) {
   }

   public SpeedEntitySpeedMode(String text, Speed speed) {
      super(text, speed);
   }
}
