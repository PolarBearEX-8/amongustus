package client.util;

import client.event.AttackEntityListener;
import client.event.BlockActionListener;
import client.event.BlockPlaceListener;
import client.event.ClickListener;
import client.event.ClickTickListener;
import client.event.Event;
import client.event.EventAttackEntity;
import client.event.EventAutoSoupListener;
import client.event.EventBlockAction;
import client.event.EventBlockCollision;
import client.event.EventBlockPlace;
import client.event.EventBoundingBox;
import client.event.EventBoundingBoxCallback;
import client.event.EventCameraTransform;
import client.event.EventCameraTransformCallback;
import client.event.EventCancelAction;
import client.event.EventCancelListener;
import client.event.EventChat;
import client.event.EventChatCallback;
import client.event.EventChunkLoad;
import client.event.EventChunkLoadCallback;
import client.event.EventClick;
import client.event.EventClickGuiListener;
import client.event.EventCommand;
import client.event.EventCommandCallback;
import client.event.EventFovUpdate;
import client.event.EventFovUpdateCallback;
import client.event.EventGuiOpen;
import client.event.EventGuiOpenCallback;
import client.event.EventJump;
import client.event.EventJumpCallback;
import client.event.EventKeyInput;
import client.event.EventListener;
import client.event.EventLivingUpdate;
import client.event.EventLivingUpdateCallback;
import client.event.EventMotionUpdate;
import client.event.EventMouseClick;
import client.event.EventMove;
import client.event.EventMoveEntity;
import client.event.EventMoveInput;
import client.event.EventPacket;
import client.event.EventPhaseListener;
import client.event.EventRenderArrayListListener;
import client.event.EventRenderEntity;
import client.event.EventRenderHand;
import client.event.EventRenderHandCallback;
import client.event.EventRotation;
import client.event.EventSafeWalk;
import client.event.EventSendPacket;
import client.event.EventSilentMove;
import client.event.EventSlotClick;
import client.event.EventSlotClickCallback;
import client.event.EventSlowDown;
import client.event.EventSneak;
import client.event.EventSprint;
import client.event.EventStateUpdate;
import client.event.EventStep;
import client.event.EventStepHeight;
import client.event.EventStepHeightCallback;
import client.event.EventStrafe;
import client.event.EventTargetEntity;
import client.event.EventTargetEntityCallback;
import client.event.EventTickState;
import client.event.EventTickStateCallback;
import client.event.EventTimeUpdate;
import client.event.EventTimeUpdateCallback;
import client.event.EventTimerSpeed;
import client.event.EventTimerSpeedCallback;
import client.event.EventWindowAction;
import client.event.EventWorldLoad;
import client.event.KeyInputListener;
import client.event.MotionListener;
import client.event.MotionUpdateListener;
import client.event.MouseClickListener;
import client.event.MoveEntityListener;
import client.event.MoveInputListener;
import client.event.MoveListener;
import client.event.NetworkPacketListener;
import client.event.PacketListener;
import client.event.RenderEntityListener;
import client.event.RenderListener;
import client.event.RenderTickListener;
import client.event.RotationListener;
import client.event.SafeWalkListener;
import client.event.SilentMoveListener;
import client.event.SlowDownListener;
import client.event.SneakListener;
import client.event.SprintListener;
import client.event.StepListener;
import client.event.StrafeListener;
import client.event.TickListener;
import client.event.UpdateStateListener;
import client.event.WindowActionListener;
import client.event.WorldLoadListener;
import client.module.ModuleManager;
import client.setting.Setting;
import net.minecraft.block.BlockCollisionListener;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.item.ItemCallback;
import net.minecraft.item.SecondaryItemCallback;
import net.minecraft.item.TertiaryItemCallback;

public class TargetParser
   extends AuxiliaryModuleAdapter
   implements StepListener,
   AttackEntityListener,
   EventChatCallback,
   BlockCollisionListener,
   EventCancelListener,
   BlockPlaceListener,
   EventCommandCallback,
   WindowActionListener,
   ItemCallback,
   ClickListener,
   EventClickGuiListener,
   EventAutoSoupListener,
   EventLivingUpdateCallback,
   EventRenderArrayListListener,
   EventTargetEntityCallback,
   EventSlotClickCallback,
   ClickTickListener,
   EventStepHeightCallback,
   EventBoundingBoxCallback,
   KeyInputListener,
   SecondaryItemCallback,
   MouseClickListener,
   EventListener,
   WorldLoadListener,
   MotionUpdateListener,
   SilentMoveListener,
   MoveEntityListener,
   MoveListener,
   EventChunkLoadCallback,
   EventCameraTransformCallback,
   SmoothSynchronizer,
   StrafeListener,
   NetworkPacketListener,
   PacketListener,
   EventTimerSpeedCallback,
   EventPhaseListener,
   RotationListener,
   RenderTickListener,
   BlockActionListener,
   RenderEntityListener,
   EventGuiOpenCallback,
   RenderListener,
   SprintListener,
   MotionListener,
   SafeWalkListener,
   MoveInputListener,
   EventTickStateCallback,
   CancellableEventCallbackAdapter,
   SneakListener,
   TickListener,
   SlowDownListener,
   EventRenderHandCallback,
   UpdateStateListener,
   TertiaryItemCallback,
   EventJumpCallback,
   EventTimeUpdateCallback,
   EventFovUpdateCallback {
   public boolean enabled3 = false;
   public final TimeHelper timer = new TimeHelper();

   @Override
   public void updateState2() {
   }

   @Override
   public void onWindowAction(EventWindowAction event) {
   }

   @Override
   public void onSneak(EventSneak event) {
   }

   @Override
   public void onMouseClick(EventMouseClick event) {
   }

   @Override
   public void onNetworkPacket(EventSendPacket packet) {
      NetworkPacketListener.super.onNetworkPacket(packet);
   }

   @Override
   public void updateState6() {
      ItemCallback.super.updateState6();
   }

   @Override
   public void onCameraTransform(EventCameraTransform event) {
   }

   @Override
   public boolean isToggled() {
      return this.enabled3;
   }

   @Override
   public void onMoveInput(EventMoveInput event) {
      MoveInputListener.super.onMoveInput(event);
   }

   @Override
   public void onSprint(EventSprint event) {
   }

   @Override
   public void onChunkLoad(EventChunkLoad event) {
   }

   @Override
   public void onRotation(EventRotation event) {
   }

   @Override
   public void onStrafe(EventStrafe event) {
   }

   @Override
   public void applyIndex(int count, int count2) {
   }

   @Override
   public void onTimeUpdate(EventTimeUpdate event) {
   }

   @Override
   public void onPacketReceive(EventPacket packet) {
      PacketListener.super.onPacketReceive(packet);
   }

   @Override
   public void onJump(EventJump event) {
      EventJumpCallback.super.onJump(event);
   }

   @Override
   public void onCancelAction(EventCancelAction event) {
   }

   @Override
   public void applyCancellableEventAdapter(CancellableEventAdapter event) {
   }

   @Override
   public void onPacketSend(EventPacket packet) {
      PacketListener.super.onPacketSend(packet);
   }

   @Override
   public void onCommand(EventCommand event) {
   }

   @Override
   public void onPostRotation(EventRotation event) {
   }

   @Override
   public void onAttackEntity(EventAttackEntity event) {
   }

   @Override
   public void onRenderEntity(EventRenderEntity event) {
   }

   @Override
   public void onPostSprint(EventSprint event) {
      SprintListener.super.onPostSprint(event);
   }

   @Override
   public void onTargetEntity(EventTargetEntity event) {
   }

   @Override
   public void onClick(EventClick event) {
   }

   @Override
   public void updateState8() {
      ClickListener.super.updateState8();
   }

   @Override
   public void onLivingUpdate(EventLivingUpdate event) {
   }

   @Override
   public void onMoveEntity(EventMoveEntity event) {
      MoveEntityListener.super.onMoveEntity(event);
   }

   @Override
   public void onPostNetworkPacket(EventSendPacket packet) {
      NetworkPacketListener.super.onPostNetworkPacket(packet);
   }

   @Override
   public void onBlockAction(EventBlockAction event) {
   }

   @Override
   public void onBoundingBox(EventBoundingBox event) {
   }

   @Override
   public void onRenderHand(EventRenderHand event) {
   }

   @Override
   public void onKeyInput(EventKeyInput event) {
   }

   @Override
   public void onBlockCollision(EventBlockCollision event) {
   }

   @Override
   public void onPacketPostReceive(EventPacket packet) {
      PacketListener.super.onPacketPostReceive(packet);
   }

   @Override
   public void onRender2D(float factor, ScaledResolution scaledResolution) {
      RenderListener.super.onRender2D(factor, scaledResolution);
   }

   @Override
   public void onSafeWalk(EventSafeWalk event) {
   }

   @Override
   public void onGuiOpen(EventGuiOpen event) {
   }

   @Override
   public void onChat(EventChat event) {
      EventChatCallback.super.onChat(event);
   }

   @Override
   public void onUpdateState(EventStateUpdate event) {
      UpdateStateListener.super.onUpdateState(event);
   }

   @Override
   public void onPostAttackEntity() {
      AttackEntityListener.super.onPostAttackEntity();
   }

   @Override
   public void onMotionUpdate(EventMotionUpdate event) {
   }

   @Override
   public void onSilentMove(EventSilentMove event) {
   }

   public void setEnabledByFlag(boolean enabledByFlag) {
      this.timer.reset();
      this.onEnable();
      this.enabled3 = enabledByFlag;
   }

   @Override
   public void onPacket(EventPacket packet) {
      PacketListener.super.onPacket(packet);
   }

   @Override
   public void onStepHeight(EventStepHeight event) {
   }

   @Override
   public boolean checkCondition() {
      return false;
   }

   @Override
   public void applyAuxiliaryAbstractEventAdap(AuxiliaryAbstractEventAdapter event) {
   }

   @Override
   public void onStep(EventStep event) {
   }

   @Override
   public void onBlockPlace(EventBlockPlace event) {
   }

   @Override
   public void onSlotClick(EventSlotClick event) {
   }

   @Override
   public void onWorldEvent(EventWorldLoad event) {
   }

   @Override
   public void onTickState(EventTickState event) {
   }

   @Override
   public void onFovUpdate(EventFovUpdate event) {
   }

   @Override
   public void processAction() {
   }

   @Override
   public void applyEvent(Event event) {
   }

   @Override
   public void onRenderOverlay(float factor, ScaledResolution scaledResolution) {
      RenderListener.super.onRenderOverlay(factor, scaledResolution);
   }

   @Override
   public void onPreMoveInput(EventMoveInput event) {
      MoveInputListener.super.onPreMoveInput(event);
   }

   @Override
   public void onSlowDown(EventSlowDown event) {
   }

   @Override
   public void onPacketPostSend(EventPacket packet) {
      PacketListener.super.onPacketPostSend(packet);
   }

   @Override
   public void onPostMoveEntity(EventMoveEntity event) {
      MoveEntityListener.super.onPostMoveEntity(event);
   }

   @Override
   public void updateKillAura() {
   }

   @Override
   public void onCustomSlowDown(EventSlowDown event) {
   }

   @Override
   public void onMove(EventMove event) {
   }

   @Override
   public void onPostMoveInput(EventMoveInput event) {
      MoveInputListener.super.onPostMoveInput(event);
   }

   @Override
   public void onRender3D(float factor) {
      RenderListener.super.onRender3D(factor);
   }

   @Override
   public void onJump2(EventJump event) {
      EventJumpCallback.super.onJump2(event);
   }

   @Override
   public void onChunkLoad2(EventChunkLoad event) {
   }

   @Override
   public void onTimerSpeed(EventTimerSpeed event) {
   }

   @Override
   public void onChat2(EventChat event) {
      EventChatCallback.super.onChat2(event);
   }

   @Override
   public void applySpecialAbstractEventAdapte(SpecialAbstractEventAdapter event) {
   }

   @Override
   public void onPostMotion() {
   }

   @Override
   public void onPreUpdateState() {
      UpdateStateListener.super.onPreUpdateState();
   }

   @Override
   public void eatSoup() {
   }

   @Override
   public void onEnable() {
      this.registerEvent(0);
      this.registerEvent(1);
      this.registerEvent(2);
      this.registerEvent(3);
      this.registerEvent(4);
      this.registerEvent(55);
      this.registerEvent(5);
      this.registerEvent(56);
      this.registerEvent(6);
      this.registerEvent(7);
      this.registerEvent(8);
      this.registerEvent(9);
      this.registerEvent(10);
      this.registerEvent(11);
      this.registerEvent(50);
      this.registerEvent(51);
      this.registerEvent(52);
      this.registerEvent(12);
      this.registerEvent(13);
      this.registerEvent(14);
      this.registerEvent(15);
      this.registerEvent(16);
      this.registerEvent(53);
      this.registerEvent(17);
      this.registerEvent(18);
      this.registerEvent(19);
      this.registerEvent(20);
      this.registerEvent(21);
      this.registerEvent(22);
      this.registerEvent(23);
      this.registerEvent(49);
      this.registerEvent(24);
      this.registerEvent(25);
      this.registerEvent(26);
      this.registerEvent(48);
      this.registerEvent(27);
      this.registerEvent(28);
      this.registerEvent(29);
      this.registerEvent(30);
      this.registerEvent(31);
      this.registerEvent(54);
      this.registerEvent(32);
      this.registerEvent(33);
      this.registerEvent(34);
      this.registerEvent(35);
      this.registerEvent(36);
      this.registerEvent(37);
      this.registerEvent(38);
      this.registerEvent(39);
      this.registerEvent(40);
      this.registerEvent(41);
      this.registerEvent(42);
      this.registerEvent(43);
      this.registerEvent(47);
      this.registerEvent(44);
      this.registerEvent(45);
      this.registerEvent(46);
   }

   @Override
   public void updateState() {
   }

   @Override
   public void onPostRenderTick() {
   }

   @Override
   public void onPostUpdateState() {
      UpdateStateListener.super.onPostUpdateState();
   }

   @Override
   public void onRunTick() {
      Setting[] settingArray = ModuleManager.getSettingArray();
      TickListener.super.onRunTick();
      TargetParser targetParser = this;
      if (settingArray == null) {
         if (!this.timer.hasReached(10000L)) {
            return;
         }

         targetParser = this;
      }

      targetParser.setEnabledByFlag(false);
   }

   @Override
   public void onClientTick() {
      TickListener.super.onClientTick();
   }

   @Override
   public void onPreMotionUpdate() {
      MotionUpdateListener.super.onPreMotionUpdate();
   }

   @Override
   public void onTick() {
      TickListener.super.onTick();
   }

   @Override
   public void onPostSilentMove() {
      SilentMoveListener.super.onPostSilentMove();
   }

   @Override
   public void onPreTick() {
      TickListener.super.onPreTick();
   }

   @Override
   public void onPostTick() {
      TickListener.super.onPostTick();
   }

   @Override
   public void updateState3() {
   }

   @Override
   public void updateState9() {
      MoveListener.super.updateState9();
   }
}
