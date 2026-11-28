package net.augustus.modules.combat;

import java.awt.Color;
import net.augustus.modules.Categorys;
import net.augustus.modules.Module;
import net.augustus.events.EventPacket;
import net.augustus.utils.input.KeyboardInputUtil;
import net.augustus.events.listeners.RenderListener;
import net.augustus.ClientManagers;
import net.augustus.events.listeners.MotionListener;
import net.augustus.utils.math.DefaultRandomGenerator;
import net.augustus.events.listeners.MoveInputListener;
import net.augustus.events.EventMoveInput;
import net.augustus.events.listeners.TickListener;
import net.augustus.events.listeners.PacketListener;
import net.augustus.events.EventMotion;
import net.augustus.settings.BooleanValue;
import net.augustus.settings.StringValue;
import net.augustus.settings.DoubleValue;
import net.augustus.utils.TimeHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.C0DPacketCloseWindow;
import net.minecraft.network.play.client.C16PacketClientStatus;
import net.minecraft.network.play.server.S2DPacketOpenWindow;

public class GommeMode2 extends Module implements TickListener, MoveInputListener, MotionListener, RenderListener, PacketListener {
   private final TimeHelper activationDelayTimer;
   private final TimeHelper windowListenTimer;
   public StringValue mode;
   public DoubleValue startDelay;
   public BooleanValue sameStack;
   public BooleanValue noStopMove;
   public BooleanValue debugMessage;
   private boolean activated;
   private int lastHotbarSlot;
   private ItemStack lastHeldItem;
   private WorldClient lastWorld;
   private int pendingWindowId;

   public GommeMode2() {
       super("GommeMode", "GommeModus", Color.RED, Categorys.COMBAT);
      this.activationDelayTimer = new TimeHelper();
      this.windowListenTimer = new TimeHelper();
      this.mode = new StringValue(this, "Mode", "New", new String[]{"NewNew", "New", "Old"});
      this.startDelay = new DoubleValue(this, "StartDelay", 2000.0, 0.0, 10000.0, 0);
      this.sameStack = new BooleanValue(this, "SameStack", true);
      this.noStopMove = new BooleanValue(this, "NoStopMove", true);
      this.debugMessage = new BooleanValue(this, "DebugMessage", true);
   }

   public void onEnable() {
      this.activated = false;
      this.lastWorld = null;
   }

   public void onDisable() {
   }

   public void updateEventSubscriptions() {
      this.toggleEventSubscription(40);
      this.toggleEventSubscription(21);
      this.toggleEventSubscription(18);
      this.toggleEventSubscription(32);
      this.toggleEventSubscription(26);
   }

   public void onPreMotion(EventMotion event) {
      long l;
      ItemStack currentEquippedItem = mc.thePlayer.getCurrentEquippedItem();
      if (this.activated
         && (
            mc.thePlayer.inventory.currentItem != this.lastHotbarSlot
               || mc.thePlayer.isBlocking()
               || mc.theWorld != this.lastWorld
               || this.lastHeldItem != mc.thePlayer.getCurrentEquippedItem() && this.sameStack.getValue()
         )
         && currentEquippedItem != null
         && currentEquippedItem.getItem() instanceof ItemSword) {
         this.activated = false;
         this.activationDelayTimer.reset();
      }

      if (!this.mode.getSelectedValue().equals("NewNew") && currentEquippedItem != null && currentEquippedItem.getItem() instanceof ItemSword && !this.activated) {
         TimeHelper activationDelayTimer2 = this.activationDelayTimer;
         if (this.startDelay.getValue() == 0.0) {
            l = 0L;
         } else {
            l = (long)(this.startDelay.getValue() + (double)DefaultRandomGenerator.instance.nextLong(0L, 200L));
         }

         if (activationDelayTimer2.reached(l) && mc.isNotInGui()) {
            mc.playerController.sendUseItem(mc.thePlayer, mc.theWorld, currentEquippedItem);
            mc.thePlayer.setItemInUse(null, 0);
            mc.thePlayer.sendChatMessage("/language");
            this.activated = true;
            this.windowListenTimer.reset();
            if (this.debugMessage.getValue()) {
               this.sendChatMessage("Gomme mode activated");
            }
         }
      }

      if (this.mode.getSelectedValue().equals("NewNew") && currentEquippedItem != null && currentEquippedItem.getItem() instanceof ItemSword && !this.activated) {
         TimeHelper activationDelayTimer3 = this.activationDelayTimer;
         if (this.startDelay.getValue() == 0.0) {
            l = 0L;
         } else {
            l = (long)(this.startDelay.getValue() + (double)DefaultRandomGenerator.instance.nextLong(0L, 200L));
         }

         if (activationDelayTimer3.reached(l) && mc.isNotInGui()) {
            mc.playerController.sendUseItem(mc.thePlayer, mc.theWorld, currentEquippedItem);
            mc.thePlayer.sendQueue.addToSendQueueUnchecked(new C16PacketClientStatus(C16PacketClientStatus.EnumState.OPEN_INVENTORY_ACHIEVEMENT));
            mc.displayGuiScreen(new GuiInventory(mc.thePlayer));
            mc.thePlayer.closeScreen();
            this.activated = true;
            this.windowListenTimer.reset();
            if (this.debugMessage.getValue()) {
               this.sendChatMessage("Gomme mode activated");
            }
         }
      }

      this.lastHeldItem = mc.thePlayer.getCurrentEquippedItem();
      this.lastHotbarSlot = mc.thePlayer.inventory.currentItem;
      this.lastWorld = mc.theWorld;
   }

   public void onPreMoveInput(EventMoveInput event) {
      if (mc.isNotInGui() && this.noStopMove.getValue() && this.mode.getSelectedValue().equals("New")) {
         KeyBinding.setKeyBindState(mc.gameSettings.keyBindForward.getKeyCode(), KeyboardInputUtil.isKeyDown(mc.gameSettings.keyBindForward.getKeyCode()));
         KeyBinding.setKeyBindState(mc.gameSettings.keyBindBack.getKeyCode(), KeyboardInputUtil.isKeyDown(mc.gameSettings.keyBindBack.getKeyCode()));
         KeyBinding.setKeyBindState(mc.gameSettings.keyBindRight.getKeyCode(), KeyboardInputUtil.isKeyDown(mc.gameSettings.keyBindRight.getKeyCode()));
         KeyBinding.setKeyBindState(mc.gameSettings.keyBindLeft.getKeyCode(), KeyboardInputUtil.isKeyDown(mc.gameSettings.keyBindLeft.getKeyCode()));
         KeyBinding.setKeyBindState(mc.gameSettings.keyBindSneak.getKeyCode(), KeyboardInputUtil.isKeyDown(mc.gameSettings.keyBindSneak.getKeyCode()));
         KeyBinding.setKeyBindState(mc.gameSettings.keyBindJump.getKeyCode(), KeyboardInputUtil.isKeyDown(mc.gameSettings.keyBindJump.getKeyCode()));
         KeyBinding.setKeyBindState(mc.gameSettings.keyBindSprint.getKeyCode(), KeyboardInputUtil.isKeyDown(mc.gameSettings.keyBindSprint.getKeyCode()));
         if (ClientManagers.moduleManager.sprint.isToggled()) {
            KeyBinding.setKeyBindState(mc.gameSettings.keyBindSprint.getKeyCode(), true);
         }
      }
   }

   public void onEarlyTick() {
      if (this.mode.getSelectedValue().equals("New") && mc.thePlayer.openContainer.windowId == this.pendingWindowId) {
         mc.displayGuiScreen((GuiScreen)null);
         mc.thePlayer.sendQueue.addToSendQueue(new C0DPacketCloseWindow(mc.thePlayer.openContainer.windowId));
         this.pendingWindowId = -1337;
      }
   }

   public void onRender3D(float partialTicks) {
      if (mc.thePlayer.openContainer.windowId == this.pendingWindowId && this.mode.getSelectedValue().equals("New")) {
         mc.displayGuiScreen((GuiScreen)null);
         mc.thePlayer.sendQueue.addToSendQueue(new C0DPacketCloseWindow(mc.thePlayer.openContainer.windowId));
         this.pendingWindowId = -1337;
      }
   }

   public void onPacketReceive(EventPacket event) {
      Packet packet = event.getPacket();
      if (packet instanceof S2DPacketOpenWindow) {
         if (!this.windowListenTimer.reached(5000L)) {
            S2DPacketOpenWindow packet2 = (S2DPacketOpenWindow)packet;
            if (packet2.getSlotCount() == 45) {
               this.pendingWindowId = packet2.getWindowId();
               if (this.mode.getSelectedValue().equals("Old")) {
                  event.cancel();
                  mc.thePlayer.sendQueue.addToSendQueue(new C0DPacketCloseWindow(packet2.getWindowId()));
               }
            }
         }
      }
   }

}
