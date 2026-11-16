package augustus.modes.movement;

import augustus.modules.movement.Fly;
import client.event.EventMotionUpdate;
import client.event.EventMove;
import client.event.EventMoveEntity;
import client.event.EventPacket;
import client.event.EventSendPacket;
import client.setting.MotionSettingGroup;
import client.util.AuxiliaryModuleAdapter;

public class FlyCustomMode extends FlyModeHandler {
   public final MotionSettingGroup motionSettingGroup;
   public final AuxiliaryModuleAdapter auxiliaryModuleAdapter = new AuxiliaryModuleAdapter();

   @Override
   public void processChar() {
      this.motionSettingGroup.updateState();
   }

   public MotionSettingGroup getBooleanSettingEntry() {
      return this.motionSettingGroup;
   }

   @Override
   public void onSendPacket2(EventSendPacket packet) {
      this.motionSettingGroup.onSendPacket(packet);
   }

   @Override
   public void onMove(EventMove event) {
      this.motionSettingGroup.onMove(event);
   }

   @Override
   public void onMotionUpdate(EventMotionUpdate event) {
   }

   @Override
   public void updateState2() {
      this.motionSettingGroup.updateState2();
   }

   @Override
   public void processAction(int count, char character, char character2) {
      this.motionSettingGroup.updateState4();
   }

   @Override
   public void onMoveEntity(EventMoveEntity event) {
      this.motionSettingGroup.onMoveEntity(event);
   }

   @Override
   public void updateState3() {
      this.motionSettingGroup.processAction();
   }

   @Override
   public void onPacket2(EventPacket packet) {
      this.motionSettingGroup.onPacket(packet);
   }

   public FlyCustomMode(String text, Fly fly) {
      super(text, fly);
      this.motionSettingGroup = new MotionSettingGroup(this.auxiliaryModuleAdapter);
      this.motionSettingGroup.updateState3();
   }
}
