package augustus.modes.movement;

import augustus.modules.movement.Speed;
import client.event.EventMotionUpdate;
import client.event.EventMove;
import client.event.EventMoveEntity;
import client.event.EventPacket;
import client.event.EventSendPacket;
import client.setting.MotionSettingGroup;
import client.util.AuxiliaryModuleAdapter;

public class SpeedCustomMode extends SpeedModeHandler {
   public final MotionSettingGroup motionSettingGroup;
   public final AuxiliaryModuleAdapter auxiliaryModuleAdapter = new AuxiliaryModuleAdapter();

   @Override
   public void onMove(EventMove event) {
      this.motionSettingGroup.onMove(event);
   }

   @Override
   public void onPacket(EventPacket packet) {
      this.motionSettingGroup.onPacket(packet);
   }

   public MotionSettingGroup getBooleanSettingEntry() {
      return this.motionSettingGroup;
   }

   @Override
   public void updateState2() {
      this.motionSettingGroup.processAction();
   }

   @Override
   public void processAction() {
      this.motionSettingGroup.updateState4();
   }

   @Override
   public void processChar() {
      this.motionSettingGroup.updateState();
   }

   @Override
   public void onMoveEntity(EventMoveEntity event) {
      this.motionSettingGroup.onMoveEntity(event);
   }

   @Override
   public void onSendPacket(EventSendPacket packet) {
      this.motionSettingGroup.onSendPacket(packet);
   }

   @Override
   public void updateState3() {
      this.motionSettingGroup.updateState2();
   }

   @Override
   public void onMotionUpdate(EventMotionUpdate event) {
   }

   public SpeedCustomMode(String text, Speed speed) {
      super(text, speed);
      this.motionSettingGroup = new MotionSettingGroup(this.auxiliaryModuleAdapter);
      this.motionSettingGroup.updateState3();
   }
}
