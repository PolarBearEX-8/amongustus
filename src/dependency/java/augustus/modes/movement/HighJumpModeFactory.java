package augustus.modes.movement;

import augustus.modules.movement.HighJump;
import client.event.EventMove;
import client.event.EventMoveEntity;
import client.event.EventPacket;
import client.event.EventSendPacket;
import client.event.MoveEntityListener;
import client.event.MoveListener;
import client.event.NetworkPacketListener;
import client.event.PacketListener;
import client.event.PostMotionSubscriber;
import client.event.TriFunction;
import client.event.UpdateStateListener;
import client.setting.MotionSettingGroup;
import client.setting.SettingGroup;
import client.util.AuxiliaryModuleAdapter;
import client.util.BaseUpdateStateListenerAdapter;

public class HighJumpModeFactory
   extends BaseUpdateStateListenerAdapter
   implements PostMotionSubscriber,
   UpdateStateListener,
   MoveEntityListener,
   MoveListener,
   PacketListener,
   NetworkPacketListener {
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
   public void onPostNetworkPacket(EventSendPacket packet) {
      this.motionSettingGroup.onSendPacket(packet);
   }

   @Override
   public AuxiliaryModuleAdapter getAuxiliaryModuleAdapter() {
      return this.auxiliaryModuleAdapter;
   }

   public static TriFunction getAltListener() {
      return (item, group, name) -> new HighJumpModeFactory((HighJump)item, (SettingGroup)group, (String)name);
   }

   @Override
   public void clear() {
      this.motionSettingGroup.processAction();
   }

   @Override
   public MotionSettingGroup getMotionSettingGroup() {
      return this.motionSettingGroup;
   }

   @Override
   public void onMove(EventMove event) {
      this.motionSettingGroup.onMove(event);
   }

   @Override
   public void onPostMoveEntity(EventMoveEntity event) {
      this.motionSettingGroup.onMoveEntity(event);
   }

   @Override
   public void updateState2() {
      super.updateState2();
      byte byteValue = 20;
      this.applyIndex(byteValue);
      byte byteValue2 = 21;
      this.applyIndex(byteValue2);
      byte byteValue3 = 26;
      this.applyIndex(byteValue3);
      byte byteValue4 = 25;
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

   public HighJumpModeFactory(HighJump highJump, SettingGroup settingGroup, String text) {
      super(highJump, settingGroup, text);
      this.motionSettingGroup.updateState3();
   }
}
