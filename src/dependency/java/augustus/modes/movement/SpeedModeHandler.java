package augustus.modes.movement;

import augustus.modules.movement.Speed;
import client.event.EventMotionUpdate;
import client.event.EventMove;
import client.event.EventMoveEntity;
import client.event.EventPacket;
import client.event.EventRotation;
import client.event.EventSendPacket;
import client.event.EventSlowDown;
import client.event.EventStep;
import client.util.MinecraftAccessor;

public abstract class SpeedModeHandler implements MinecraftAccessor {
   public final String labelText;
   public final Speed speed;

   public abstract void onMove(EventMove event);

   public void onStep(EventStep event) {
   }

   public void updateState() {
   }

   public void onRotation(EventRotation event) {
   }

   public void onPacket(EventPacket packet) {
   }

   public boolean isActive() {
      return this.isActiveAsFlag() && minecraft.thePlayer.onGround;
   }

   public abstract void updateState2();

   public boolean isActiveAsFlag() {
      return minecraft.thePlayer.scaleFactor3 != 0.0F || minecraft.thePlayer.scaleFactor24 != 0.0F && !minecraft.thePlayer.isCollidedHorizontally;
   }

   public void onRotation2(EventRotation event) {
   }

   public abstract void processAction();

   public abstract void processChar();

   public abstract void onMoveEntity(EventMoveEntity event);

   public void onSlowDown(EventSlowDown event) {
   }

   public void onSendPacket(EventSendPacket packet) {
   }

   public abstract void updateState3();

   public String getText() {
      return this.labelText;
   }

   public abstract void onMotionUpdate(EventMotionUpdate event);

   public SpeedModeHandler(String text, Speed speed2) {
      this.labelText = text;
      this.speed = speed2;
   }
}
