package augustus.modes.movement;

import augustus.modules.movement.Fly;
import client.event.EventBlockCollision;
import client.event.EventClick;
import client.event.EventMotionUpdate;
import client.event.EventMove;
import client.event.EventMoveEntity;
import client.event.EventPacket;
import client.event.EventRotation;
import client.event.EventSendPacket;
import client.util.MinecraftAccessor;

public abstract class FlyModeHandler implements MinecraftAccessor {
   public final String labelText;
   public final Fly fly;

   public void updateState() {
   }

   public void onPacket(EventPacket packet) {
   }

   public void onSendPacket(EventSendPacket packet) {
   }

   public void processAction() {
   }

   public void onRotation(EventRotation event) {
   }

   public String getText() {
      return this.labelText;
   }

   public void onSendPacket2(EventSendPacket packet) {
   }

   public abstract void processChar();

   public void onBlockCollision(EventBlockCollision event) {
   }

   public void onMove(EventMove event) {
   }

   public abstract void onMotionUpdate(EventMotionUpdate event);

   public abstract void updateState2();

   public void onRotation2(EventRotation event) {
   }

   public void processAction(int count, char character, char character2) {
   }

   public void onClick(EventClick event) {
   }

   public abstract void onMoveEntity(EventMoveEntity event);

   public abstract void updateState3();

   public void onPacket2(EventPacket packet) {
   }

   public FlyModeHandler(String text, Fly fly2) {
      this.labelText = text;
      this.fly = fly2;
   }
}
