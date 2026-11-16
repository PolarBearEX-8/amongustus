package augustus.modes.movement;

import augustus.modules.movement.FastLadder;
import client.event.EventMove;
import client.event.EventMoveEntity;
import client.event.EventPacket;
import client.event.MoveEntityListener;
import client.event.MoveListener;
import client.event.PacketListener;
import client.event.PostMotionSubscriber;
import client.event.UpdateStateListener;
import client.setting.MotionSettingGroup;
import client.setting.SettingGroup;
import client.util.AuxiliaryModuleAdapter;
import client.util.VectorSorter;

public class FastLadderModeFactory extends VectorSorter implements PostMotionSubscriber, UpdateStateListener, MoveEntityListener, MoveListener, PacketListener {
   public final AuxiliaryModuleAdapter auxiliaryModuleAdapter = new AuxiliaryModuleAdapter();
   public final MotionSettingGroup motionSettingGroup = new MotionSettingGroup(this.auxiliaryModuleAdapter);

   @Override
   public void updateState() {
      this.motionSettingGroup.updateState2();
   }

   @Override
   public void onPacketSend(EventPacket packet) {
      this.motionSettingGroup.onPacket(packet);
   }

   @Override
   public AuxiliaryModuleAdapter getAuxiliaryModuleAdapter() {
      return this.auxiliaryModuleAdapter;
   }

   @Override
   public void clear() {
      this.motionSettingGroup.processAction();
   }

   @Override
   public void onMove(EventMove event) {
      this.motionSettingGroup.onMove(event);
   }

   @Override
   public MotionSettingGroup getMotionSettingGroup() {
      return this.motionSettingGroup;
   }

   @Override
   public void onPostMoveEntity(EventMoveEntity event) {
      this.motionSettingGroup.onMoveEntity(event);
   }

   @Override
   public void updateState2() {
      byte byteValue = 20;
      this.applyIndex(byteValue);
      byte byteValue2 = 43;
      this.applyIndex(byteValue2);
      byte byteValue3 = 21;
      this.applyIndex(byteValue3);
      byte byteValue4 = 26;
      this.applyIndex(byteValue4);
   }

   @Override
   public void onPreUpdateState() {
      this.motionSettingGroup.updateState4();
   }

   @Override
   public void onPostUpdateState() {
      this.motionSettingGroup.updateState();
   }

   public FastLadderModeFactory(FastLadder fastLadder, SettingGroup settingGroup, String text) {
      super(fastLadder, settingGroup, text);
      this.motionSettingGroup.updateState3();
   }
}
