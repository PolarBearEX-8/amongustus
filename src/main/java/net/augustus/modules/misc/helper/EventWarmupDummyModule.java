package net.augustus.modules.misc.helper;

import net.augustus.events.EventAIMoveSpeed;
import net.augustus.events.EventAttackEntity;
import net.augustus.events.EventAttackSlowdown;
import net.augustus.events.EventBlockBoundingBox;
import net.augustus.events.EventBlockChange;
import net.augustus.events.EventBlockOverlay;
import net.augustus.events.EventChat;
import net.augustus.events.EventChunkLoad;
import net.augustus.events.EventClick;
import net.augustus.events.EventEntityColor;
import net.augustus.events.EventEntityHurt;
import net.augustus.events.EventGameLoop;
import net.augustus.events.EventInventoryClick;
import net.augustus.events.EventInventoryClose;
import net.augustus.events.EventItemGlint;
import net.augustus.events.EventItemPos;
import net.augustus.events.EventKey;
import net.augustus.events.EventLadder;
import net.augustus.events.EventLeftClick;
import net.augustus.events.EventLoadWorld;
import net.augustus.events.EventMotion;
import net.augustus.events.EventMouseMove;
import net.augustus.events.EventMove;
import net.augustus.events.EventMoveEntity;
import net.augustus.events.EventMoveInput;
import net.augustus.events.EventNightVision;
import net.augustus.events.EventOrientCamera;
import net.augustus.events.EventPacket;
import net.augustus.events.EventPacketDelay;
import net.augustus.events.EventPerspective;
import net.augustus.events.EventRenderBlock;
import net.augustus.events.EventRenderEntity;
import net.augustus.events.EventRenderGuiBackground;
import net.augustus.events.EventSafeWalk;
import net.augustus.events.EventSlowDown;
import net.augustus.events.EventSound;
import net.augustus.events.EventSprint;
import net.augustus.events.EventStep;
import net.augustus.events.EventSwim;
import net.augustus.events.EventSwing;
import net.augustus.events.EventTimer;
import net.augustus.events.EventTimerUpdate;
import net.augustus.events.EventUpdate;
import net.augustus.events.EventWebSlowDown;
import net.augustus.events.EventWorldColor;
import net.augustus.events.EventWorldTime;
import net.augustus.events.EventZoom;
import net.augustus.events.listeners.AIMoveSpeedListener;
import net.augustus.events.listeners.AttackEntityListener;
import net.augustus.events.listeners.AttackSlowdownListener;
import net.augustus.events.listeners.BlockBoundingBoxListener;
import net.augustus.events.listeners.BlockChangeListener;
import net.augustus.events.listeners.BlockOverlayListener;
import net.augustus.events.listeners.ChatListener;
import net.augustus.events.listeners.ChunkLoadListener;
import net.augustus.events.listeners.ClickGuiListener;
import net.augustus.events.listeners.ClickListener;
import net.augustus.events.listeners.DownloadTerrainListener;
import net.augustus.events.listeners.DropItemListener;
import net.augustus.events.listeners.EntityColorListener;
import net.augustus.events.listeners.EntityHurtListener;
import net.augustus.events.listeners.GameLoopListener;
import net.augustus.events.listeners.GuiScaleListener;
import net.augustus.events.listeners.InventoryClickListener;
import net.augustus.events.listeners.InventoryCloseListener;
import net.augustus.events.listeners.ItemGlintListener;
import net.augustus.events.listeners.ItemPosListener;
import net.augustus.events.listeners.KeyListener;
import net.augustus.events.listeners.LadderListener;
import net.augustus.events.listeners.LeftClickListener;
import net.augustus.events.listeners.LoadWorldListener;
import net.augustus.events.listeners.MotionListener;
import net.augustus.events.listeners.MouseMoveListener;
import net.augustus.events.listeners.MoveEntityListener;
import net.augustus.events.listeners.MoveInputListener;
import net.augustus.events.listeners.MoveListener;
import net.augustus.events.listeners.NightVisionListener;
import net.augustus.events.listeners.NoClipListener;
import net.augustus.events.listeners.OrientCameraListener;
import net.augustus.events.listeners.PacketDelayListener;
import net.augustus.events.listeners.PacketListener;
import net.augustus.events.listeners.PerspectiveListener;
import net.augustus.events.listeners.RayCastListener;
import net.augustus.events.listeners.RenderBlockListener;
import net.augustus.events.listeners.RenderEntityListener;
import net.augustus.events.listeners.RenderGuiBackgroundListener;
import net.augustus.events.listeners.RenderListener;
import net.augustus.events.listeners.SafeWalkListener;
import net.augustus.events.listeners.SilentSlotListener;
import net.augustus.events.listeners.SlowDownListener;
import net.augustus.events.listeners.SoundListener;
import net.augustus.events.listeners.SprintListener;
import net.augustus.events.listeners.StepListener;
import net.augustus.events.listeners.SwimListener;
import net.augustus.events.listeners.SwingListener;
import net.augustus.events.listeners.TickListener;
import net.augustus.events.listeners.TimerListener;
import net.augustus.events.listeners.TimerUpdateListener;
import net.augustus.events.listeners.UpdateListener;
import net.augustus.events.listeners.WebSlowDownListener;
import net.augustus.events.listeners.WindowResizeListener;
import net.augustus.events.listeners.WorldColorListener;
import net.augustus.events.listeners.WorldTimeListener;
import net.augustus.events.listeners.ZoomListener;
import net.augustus.modules.Module;
import net.augustus.modules.misc.Dummy;
import net.augustus.utils.TimeHelper;
import net.minecraft.client.gui.ScaledResolution;

public class EventWarmupDummyModule
   extends Dummy
   implements AIMoveSpeedListener,
   AttackEntityListener,
   AttackSlowdownListener,
   BlockBoundingBoxListener,
   BlockOverlayListener,
   BlockChangeListener,
   ChatListener,
   ChunkLoadListener,
   ClickGuiListener,
   ClickListener,
   DownloadTerrainListener,
   DropItemListener,
   EntityColorListener,
   GuiScaleListener,
   EntityHurtListener,
   InventoryClickListener,
   InventoryCloseListener,
   ItemGlintListener,
   ItemPosListener,
   KeyListener,
   LadderListener,
   GameLoopListener,
   LeftClickListener,
   LoadWorldListener,
   MotionListener,
   MouseMoveListener,
   MoveEntityListener,
   MoveInputListener,
   NightVisionListener,
   SlowDownListener,
   WebSlowDownListener,
   OrientCameraListener,
   PacketDelayListener,
   PacketListener,
   PerspectiveListener,
   NoClipListener,
   MoveListener,
   RayCastListener,
   RenderBlockListener,
   RenderEntityListener,
   RenderGuiBackgroundListener,
   RenderListener,
   SafeWalkListener,
   SilentSlotListener,
   SoundListener,
   SprintListener,
   StepListener,
   SwimListener,
   SwingListener,
   TickListener,
   TimerListener,
   TimerUpdateListener,
   UpdateListener,
   WindowResizeListener,
   WorldColorListener,
   WorldTimeListener,
   ZoomListener {
   private boolean warmupActive;
   private final TimeHelper timeHelper;

   public EventWarmupDummyModule() {
       super();
      this.warmupActive = false;
      this.timeHelper = new TimeHelper();
   }

   public void setWarmupActive(boolean flag) {
      this.timeHelper.reset();
      this.updateEventSubscriptions();
      this.warmupActive = flag;
   }

   public void updateEventSubscriptions() {
      ((Module)this).toggleEventSubscription(0);
      ((Module)this).toggleEventSubscription(1);
      ((Module)this).toggleEventSubscription(2);
      ((Module)this).toggleEventSubscription(3);
      ((Module)this).toggleEventSubscription(4);
      ((Module)this).toggleEventSubscription(55);
      ((Module)this).toggleEventSubscription(5);
      ((Module)this).toggleEventSubscription(56);
      ((Module)this).toggleEventSubscription(6);
      ((Module)this).toggleEventSubscription(7);
      ((Module)this).toggleEventSubscription(8);
      ((Module)this).toggleEventSubscription(9);
      ((Module)this).toggleEventSubscription(10);
      ((Module)this).toggleEventSubscription(11);
      ((Module)this).toggleEventSubscription(50);
      ((Module)this).toggleEventSubscription(51);
      ((Module)this).toggleEventSubscription(52);
      ((Module)this).toggleEventSubscription(12);
      ((Module)this).toggleEventSubscription(13);
      ((Module)this).toggleEventSubscription(14);
      ((Module)this).toggleEventSubscription(15);
      ((Module)this).toggleEventSubscription(16);
      ((Module)this).toggleEventSubscription(53);
      ((Module)this).toggleEventSubscription(17);
      ((Module)this).toggleEventSubscription(18);
      ((Module)this).toggleEventSubscription(19);
      ((Module)this).toggleEventSubscription(20);
      ((Module)this).toggleEventSubscription(21);
      ((Module)this).toggleEventSubscription(22);
      ((Module)this).toggleEventSubscription(23);
      ((Module)this).toggleEventSubscription(49);
      ((Module)this).toggleEventSubscription(24);
      ((Module)this).toggleEventSubscription(25);
      ((Module)this).toggleEventSubscription(26);
      ((Module)this).toggleEventSubscription(48);
      ((Module)this).toggleEventSubscription(27);
      ((Module)this).toggleEventSubscription(28);
      ((Module)this).toggleEventSubscription(29);
      ((Module)this).toggleEventSubscription(30);
      ((Module)this).toggleEventSubscription(31);
      ((Module)this).toggleEventSubscription(54);
      ((Module)this).toggleEventSubscription(32);
      ((Module)this).toggleEventSubscription(33);
      ((Module)this).toggleEventSubscription(34);
      ((Module)this).toggleEventSubscription(35);
      ((Module)this).toggleEventSubscription(36);
      ((Module)this).toggleEventSubscription(37);
      ((Module)this).toggleEventSubscription(38);
      ((Module)this).toggleEventSubscription(39);
      ((Module)this).toggleEventSubscription(40);
      ((Module)this).toggleEventSubscription(41);
      ((Module)this).toggleEventSubscription(42);
      ((Module)this).toggleEventSubscription(43);
      ((Module)this).toggleEventSubscription(47);
      ((Module)this).toggleEventSubscription(44);
      ((Module)this).toggleEventSubscription(45);
      ((Module)this).toggleEventSubscription(46);
   }

   public boolean isToggled() {
      return this.warmupActive;
   }

   public void onAIMoveSpeed(EventAIMoveSpeed event) {
   }

   public void onAttackEntity(EventAttackEntity event) {
   }

   public void onPostAttackEntity() {
      AttackEntityListener.super.onPostAttackEntity();
   }

   public void onBlockBoundingBox(EventBlockBoundingBox event) {
   }

   public void onBlockOverlay(EventBlockOverlay event) {
   }

   public void onBlockChange(EventBlockChange event) {
   }

   public void onChat(EventChat event) {
   }

   public void onChunkLoad(EventChunkLoad event) {
   }

   public void onClickGuiUpdate() {
   }

   public void onClickGuiRender() {
      ClickGuiListener.super.onClickGuiRender();
   }

   public void onClick(EventClick event) {
   }

   public void onMiddleClick() {
      ClickListener.super.onMiddleClick();
   }

   public void onDownloadTerrain() {
   }

   public void onDropItem() {
   }

   public void onEntityColor(EventEntityColor event) {
   }

   public void onGuiScale() {
   }

   public void onEntityHurt(EventEntityHurt event) {
   }

   public void onInventoryClick(EventInventoryClick event) {
   }

   @Override
   public boolean onPreInventoryClose() {
      return false;
   }

   @Override
   public void onPostInventoryClose(EventInventoryClose event) {
   }

   public void onItemGlint(EventItemGlint event) {
   }

   public void onItemPos(EventItemPos event) {
   }

   public void onToggleKey(EventKey event) {
   }

   public void onLadder(EventLadder event) {
   }

   public void onGameLoop(EventGameLoop event) {
   }

   public void onLeftClick(EventLeftClick event) {
   }

   public void onLoadWorld(EventLoadWorld event) {
   }

   public void onPreMotion(EventMotion event) {
   }

   public void onPostMotion() {
      MotionListener.super.onPostMotion();
   }

   public void onMouseMove(EventMouseMove event) {
   }

   public void onPostMouseMove() {
      MouseMoveListener.super.onPostMouseMove();
   }

   public void onPreMoveInput(EventMoveInput event) {
   }

   public void onPostMoveInput(EventMoveInput event) {
   }

   public void onNightVision(EventNightVision event) {
   }

   public void onFogNightVision(EventNightVision event) {
   }

   public void onSlowDown(EventSlowDown event) {
   }

   public void onWebSlowDown(EventWebSlowDown event) {
   }

   public void onOrientCamera(EventOrientCamera event) {
   }

   public void onPerspective(EventPerspective event) {
   }

   public void onNoClip() {
   }

   public void onMoveFlying(EventMove event) {
   }

   public void onJumpMove(EventMove event) {
   }

   public void onRayCast() {
   }

   public void onRenderBlock(EventRenderBlock event) {
   }

   public void onRenderEntity(EventRenderEntity event) {
   }

   public void onRenderGuiBackground(EventRenderGuiBackground event) {
   }

   public void onPreSafeWalk(EventSafeWalk event) {
   }

   public void onPostSafeWalk(EventSafeWalk event) {
      SafeWalkListener.super.onPostSafeWalk(event);
   }

   public void onPostSilentSlot() {
   }

   public void onSound(EventSound event) {
   }

   public void onPreStep(EventStep event) {
   }

   public void onPostStep() {
   }

   public void onSwim(EventSwim event) {
   }

   public void onSwing(EventSwing event) {
   }

   public void onTimerSpeed(EventTimer event) {
   }

   public void onTimerTick(EventTimer event) {
   }

   public void onTimerUpdate(EventTimerUpdate event) {
   }

   public void onWindowResize(int width, int height) {
   }

   public void onWorldTime(EventWorldTime event) {
   }

   public void onZoom(EventZoom event) {
   }

   public void onAttackSlowdownFirst(EventAttackSlowdown event) {
      AttackSlowdownListener.super.onAttackSlowdownFirst(event);
   }

   public void onAttackSlowdownSecond(EventAttackSlowdown event) {
      AttackSlowdownListener.super.onAttackSlowdownSecond(event);
   }

   public void onPreMoveEntity(EventMoveEntity event) {
      MoveEntityListener.super.onPreMoveEntity(event);
   }

   public void onPostMoveEntity(EventMoveEntity event) {
      MoveEntityListener.super.onPostMoveEntity(event);
   }

   public void onIncomingPacketDelay(EventPacketDelay event) {
      PacketDelayListener.super.onIncomingPacketDelay(event);
   }

   public void onOutgoingPacketDelay(EventPacketDelay event) {
      PacketDelayListener.super.onOutgoingPacketDelay(event);
   }

   public void onPacketSend(EventPacket event) {
      PacketListener.super.onPacketSend(event);
   }

   public void onPacketSendNetty(EventPacket event) {
      PacketListener.super.onPacketSendNetty(event);
   }

   public void onPacketSendNettyDelay(EventPacket event) {
      PacketListener.super.onPacketSendNettyDelay(event);
   }

   public void onPacketReceive(EventPacket event) {
      PacketListener.super.onPacketReceive(event);
   }

   public void onPacketReceiveNetty(EventPacket event) {
      PacketListener.super.onPacketReceiveNetty(event);
   }

   public void onRender3D(float partialTicks) {
      RenderListener.super.onRender3D(partialTicks);
   }

   public void onRender2DPre(float partialTicks, ScaledResolution sr) {
      RenderListener.super.onRender2DPre(partialTicks, sr);
   }

   public void onRender2DPost(float partialTicks, ScaledResolution sr) {
      RenderListener.super.onRender2DPost(partialTicks, sr);
   }

   public void onMotionSprint(EventSprint event) {
      SprintListener.super.onMotionSprint(event);
   }

   public void onJumpSprint(EventSprint event) {
      SprintListener.super.onJumpSprint(event);
   }

   public void onSprint(EventSprint event) {
      SprintListener.super.onSprint(event);
   }

   public void onEarlyTick() {
      TickListener.super.onEarlyTick();
   }

   public void onTick() {
      TickListener.super.onTick();
      if (this.timeHelper.reached(10000L)) {
         this.setWarmupActive(false);
      }
   }

   public void onLateTick() {
      TickListener.super.onLateTick();
   }

   public void onPreTick() {
      TickListener.super.onPreTick();
   }

   public void onPostTick() {
      TickListener.super.onPostTick();
   }

   public void onPreUpdate() {
      UpdateListener.super.onPreUpdate();
   }

   public void onPreUpdate(EventUpdate event) {
      UpdateListener.super.onPreUpdate(event);
   }

   public void onPostUpdate() {
      UpdateListener.super.onPostUpdate();
   }

   public void onWorldColor(EventWorldColor event) {
      WorldColorListener.super.onWorldColor(event);
   }

   public void onFogColor(EventWorldColor event) {
      WorldColorListener.super.onFogColor(event);
   }
}
