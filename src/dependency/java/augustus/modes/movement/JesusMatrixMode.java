package augustus.modes.movement;

import augustus.modules.movement.Jesus;
import client.event.EventMove;
import client.event.EventMoveEntity;
import client.event.EventPacket;
import client.event.EventSendPacket;
import client.event.MoveEntityListener;
import client.event.MoveListener;
import client.event.NetworkPacketListener;
import client.event.PacketListener;
import client.event.PostMotionSubscriber;
import client.event.UpdateStateListener;
import client.setting.MotionSettingGroup;
import client.setting.SettingGroup;
import client.util.AuxiliaryModuleAdapter;
import client.util.CoreStringEntry;

public class JesusMatrixMode
   extends CoreStringEntry
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

   @Override
   public void clear() {
      this.motionSettingGroup.processAction();
   }

   @Override
   public void onPostMoveEntity(EventMoveEntity event) {
      this.motionSettingGroup.onMoveEntity(event);
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
   public void updateState2() {
      byte byteValue = 20;
      this.applyIndex(byteValue);
      byte byteValue2 = 43;
      this.applyIndex(byteValue2);
      byte byteValue3 = 21;
      this.applyIndex(byteValue3);
      byte byteValue4 = 26;
      this.applyIndex(byteValue4);
      byte byteValue5 = 25;
      this.applyIndex(byteValue5);
   }

   @Override
   public void onPreUpdateState() {
      this.motionSettingGroup.updateState4();
   }

   @Override
   public void onPostUpdateState() {
      this.motionSettingGroup.updateState();
   }

   public JesusMatrixMode(Jesus jesus, SettingGroup settingGroup, String text) {
      super(jesus, settingGroup, text);
      this.motionSettingGroup.updateState3();
   }
}
