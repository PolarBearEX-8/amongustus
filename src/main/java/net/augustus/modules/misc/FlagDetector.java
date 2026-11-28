package net.augustus.modules.misc;

import net.minecraft.network.play.client.C03PacketPlayer;
import java.awt.Color;
import net.augustus.modules.Categorys;
import net.augustus.modules.Module;
import net.augustus.events.EventPacket;
import net.augustus.events.listeners.ClickListener;
import net.augustus.events.listeners.RenderListener;
import net.augustus.events.EventClick;
import net.augustus.render.RenderUtil;
import net.augustus.events.listeners.PacketListener;
import net.augustus.settings.SettingVisibilityCondition;
import net.augustus.settings.BooleanValue;
import net.augustus.settings.DoubleValue;
import net.augustus.utils.TimeHelper;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.gui.inventory.GuiCrafting;
import net.minecraft.item.ItemEnderPearl;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.C0EPacketClickWindow;
import net.minecraft.network.play.server.S08PacketPlayerPosLook;
import net.minecraft.network.play.server.S30PacketWindowItems;
import net.minecraft.util.Vec3;

public class FlagDetector extends Module implements ClickListener, PacketListener, RenderListener {
   private final BooleanValue teleportFix;
   private final BooleanValue flagCounter;
   private final BooleanValue sendChatMessage;
   private final BooleanValue showOverHotbar;
   private final BooleanValue inventoryFlags;
   private final BooleanValue textShadow;
   private final DoubleValue xCoord;
   private final DoubleValue yCoord;
   private Vec3 lastSentPosition;
   public final TimeHelper enderPearlTimer;
   private long flagCount;
   private final TimeHelper windowClickTimer;

   public FlagDetector() {
       super("FlagDetector", "Flaggendetektor", new Color(232, 180, 135), Categorys.MISC);
      this.teleportFix = (BooleanValue)(new BooleanValue(this, "TeleportFix", true)).markVisualOnly();
      this.flagCounter = (BooleanValue)(new BooleanValue(this, "FlagCounter", true)).markVisualOnly();
      this.sendChatMessage = (BooleanValue)(new BooleanValue(this, "SendChatMessage", true)).markVisualOnly();
      this.showOverHotbar = (BooleanValue)(new BooleanValue(this, "ShowOverHotbar", false)).markVisualOnly();
      this.inventoryFlags = (BooleanValue)(new BooleanValue(this, "InventoryFlags", false)).markVisualOnly();
      this.textShadow = (BooleanValue)(new BooleanValue(this, "TextShadow", false)).markVisualOnly();
      DoubleValue xCoordSetting = new DoubleValue(this, "X-Coord", 50.0, 0.0, 100.0, 2);
      BooleanValue showOverHotbar2 = this.showOverHotbar;
      BooleanValue booleanValue = showOverHotbar2;
      this.xCoord = (DoubleValue)xCoordSetting.visibleWhen(() -> booleanValue.getValue()).markVisualOnly();
      DoubleValue yCoordSetting = new DoubleValue(this, "Y-Coord", 0.0, 0.0, 100.0, 2);
      showOverHotbar2 = this.showOverHotbar;
      BooleanValue booleanValue2 = showOverHotbar2;
      this.yCoord = (DoubleValue)yCoordSetting.visibleWhen(() -> booleanValue2.getValue()).markVisualOnly();
      this.lastSentPosition = new Vec3(0.0, 0.0, 0.0);
      this.enderPearlTimer = new TimeHelper();
      this.flagCount = 0L;
      this.windowClickTimer = new TimeHelper();
   }

   @Override
   public void onEnable() {
   }

   @Override
   public void onDisable() {
   }

   @Override
   public void updateEventSubscriptions() {
      this.toggleEventSubscription(7);
      this.toggleEventSubscription(26);
      this.toggleEventSubscription(32);
   }

   @Override
   public void onPacketReceive(EventPacket event) {
      Packet packet;
      packet = event.getPacket();
      if (packet instanceof S08PacketPlayerPosLook) {
         S08PacketPlayerPosLook packet2 = (S08PacketPlayerPosLook)packet;
         boolean isTeleportIgnored = this.teleportFix.getValue()
            && (this.lastSentPosition.distanceTo(new Vec3(packet2.getX(), packet2.getY(), packet2.getZ())) >= 40.0
               || !this.enderPearlTimer.reached(4000L));
         if (!isTeleportIgnored) {
            this.flagCount++;
            if (this.sendChatMessage.getValue()) {
               this.sendChatMessage("§cFlag Detected" + (this.flagCounter.getValue() ? ": §7" + this.flagCount : ""));
            }
         }
      }

      if (packet instanceof S30PacketWindowItems && this.inventoryFlags.getValue()) {
         if (!this.windowClickTimer.reached(150L) && mc.currentScreen != null && !(mc.currentScreen instanceof GuiCrafting)) {
            this.flagCount++;
            if (this.sendChatMessage.getValue()) {
               StringBuilder sb2 = new StringBuilder();
               short s = 16752;
               this.sendChatMessage(sb2.append("§cInventory flag detected").append(this.flagCounter.getValue() ? ": §7" + this.flagCount : "").toString());
            }
         }
      }
   }

   @Override
   public void onClick(EventClick event) {
      if (this.teleportFix.getValue()
         && mc.thePlayer.getCurrentEquippedItem() != null
         && mc.thePlayer.getCurrentEquippedItem().getItem() instanceof ItemEnderPearl
         && mc.gameSettings.keyBindUseItem.getPressTime() > 0) {
         this.enderPearlTimer.reset();
      }
   }

   @Override
   public void onRender2DPost(float partialTicks, ScaledResolution sr) {
      if (this.showOverHotbar.getValue()) {
         ScaledResolution sr2 = new ScaledResolution(mc);
         String str = new StringBuilder().append("§cFlag Detected").append(this.flagCounter.getValue() ? ": §7" + this.flagCount : "").toString();
         int n = (int)((double)(sr2.getScaledHeight() - mc.fontRendererObj.FONT_HEIGHT) / 100.0 * this.yCoord.getValue());
         int n2 = (int)((double)(sr2.getScaledWidth() - mc.fontRendererObj.getStringWidth(str)) / 100.0 * this.xCoord.getValue());
         mc.fontRendererObj.drawStringInt(str, n2, n, Color.white.getRGB(), this.textShadow.getValue());
      }

      RenderUtil.resetGlColorState();
   }

   @Override
   public void onPacketSend(EventPacket event) {
      Packet packet = event.getPacket();
      if (packet instanceof C03PacketPlayer.C04PacketPlayerPosition) {
         C03PacketPlayer.C04PacketPlayerPosition packet2 = (C03PacketPlayer.C04PacketPlayerPosition)packet;
         this.lastSentPosition = new Vec3(packet2.getPositionX(), packet2.getPositionY(), packet2.getPositionZ());
      }

      if (packet instanceof C03PacketPlayer.C06PacketPlayerPosLook) {
         C03PacketPlayer.C06PacketPlayerPosLook packet3 = (C03PacketPlayer.C06PacketPlayerPosLook)packet;
         this.lastSentPosition = new Vec3(packet3.getPositionX(), packet3.getPositionY(), packet3.getPositionZ());
      }

      if (packet instanceof C0EPacketClickWindow) {
         this.windowClickTimer.reset();
      }
   }

}
