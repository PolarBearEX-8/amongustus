package net.augustus.modules.render;

import java.awt.Color;
import net.augustus.modules.Categorys;
import net.augustus.modules.Module;
import net.augustus.utils.render.ShaderRenderPass;
import net.augustus.events.EventPacket;
import net.augustus.events.listeners.RenderListener;
import net.augustus.settings.BlurShadowSettings;
import net.augustus.font.CustomFontUtil;
import net.augustus.font.FontManager;
import net.augustus.render.RenderUtil;
import net.augustus.utils.render.BlurShadowManager;
import net.augustus.ui.GuiIngameHook;
import net.augustus.viaversion.packet.C0APacketSwingHand;
import net.augustus.events.listeners.TickListener;
import net.augustus.utils.player.SilentSlotManager;
import net.augustus.events.listeners.PacketListener;
import net.augustus.settings.SettingVisibilityCondition;
import net.augustus.settings.BooleanValue;
import net.augustus.settings.StringValue;
import net.augustus.settings.DoubleValue;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ContainerPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.C03PacketPlayer;
import net.minecraft.network.play.client.C08PacketPlayerBlockPlacement;
import net.minecraft.network.play.client.C0APacketAnimation;
import org.lwjgl.opengl.GL11;

public class Hotbar extends Module implements RenderListener, PacketListener, TickListener {
   public final DoubleValue hotbarScale;
   public final BooleanValue armor;
   public final BooleanValue armorDurability;
   public final BooleanValue armorShadow;
   public final StringValue mode;
   public final BlurShadowSettings blurShadowSettings;
   public final BooleanValue silentSlot;
   public final BooleanValue inventoryItems;
   public final BooleanValue itemRectangle;
   public final BooleanValue debugInformation;
   public final BooleanValue otherPingCalculation;
   private final java.util.ArrayList clicksPerTickHistory;
   private final java.util.ArrayList speedHistory;
   private int clicksThisTick;
   public int cps;
   private double lastPosX;
   private double lastPosZ;
   public float blocksPerSecond;

   public Hotbar() {
       super("Hotbar", "Schnellzugriffsleiste", new Color(235, 64, 52), Categorys.RENDER);
      this.hotbarScale = new DoubleValue(this, "HotbarScale", 2.0, 1.0, 4.0, 2);
      this.armor = new BooleanValue(this, "Armor", true);
      this.armorDurability = (BooleanValue)new BooleanValue(this, "ArmorDurability", true).withVisibility(() -> this.isArmorDurabilityVisible());
      this.armorShadow = new BooleanValue(this, "ArmorShadow", true);
      this.mode = new StringValue(this, "Mode", "FullBar", new String[]{"Vanilla", "MinimalBar", "FullBar"});
      this.blurShadowSettings = (BlurShadowSettings)new BlurShadowSettings(this).withCustomShadowColor(Color.black).withVisibility(() -> this.isCustomHotbarActive());
      this.silentSlot = (BooleanValue)new BooleanValue(this, "SilentSlot", false).withVisibility(() -> this.isCustomHotbarActive());
      this.inventoryItems = (BooleanValue)new BooleanValue(this, "InventoryItems", false).withVisibility(() -> this.isCustomHotbarActive());
      this.itemRectangle = (BooleanValue)new BooleanValue(this, "ItemRectangle", true).withVisibility(() -> this.isFullBarMode());
      this.debugInformation = (BooleanValue)new BooleanValue(this, "DebugInformation", true).withVisibility(() -> this.isFullBarMode());
      this.otherPingCalculation = (BooleanValue)new BooleanValue(this, "OtherPingCalculation", true).withVisibility(() -> this.isOtherPingCalculationVisible());
      this.clicksPerTickHistory = new java.util.ArrayList();
      this.speedHistory = new java.util.ArrayList();
      this.clicksThisTick = 0;
      this.cps = 0;
   }

   @Override
   public void onEnable() {
   }

   @Override
   public void onDisable() {
   }

   @Override
   public void updateEventSubscriptions() {
      this.toggleEventSubscription(32);
      this.toggleEventSubscription(26);
      this.toggleEventSubscription(40);
   }

   @Override
   public void onTick() {
      this.clicksPerTickHistory.add(this.clicksThisTick);

      while (this.clicksPerTickHistory.size() > 20) {
         this.clicksPerTickHistory.remove(0);
      }

      this.cps = 0;

      for (int n : (Iterable<Integer>)(this.clicksPerTickHistory)) {
         this.cps += n;
      }

      this.clicksThisTick = 0;
   }

   @Override
   public void onPacketSend(EventPacket event) {
      Packet packet = event.getPacket();
      if ((packet instanceof C0APacketAnimation || packet instanceof C0APacketSwingHand || packet instanceof C08PacketPlayerBlockPlacement)
         && !mc.playerController.isHittingBlock()
         && (!(packet instanceof C08PacketPlayerBlockPlacement) || ((C08PacketPlayerBlockPlacement)packet).getPlacedBlockDirection() == 255)) {
         this.clicksThisTick++;
      }

      if (packet instanceof C03PacketPlayer) {
         if (!(packet instanceof C03PacketPlayer.C04PacketPlayerPosition) && !(packet instanceof C03PacketPlayer.C06PacketPlayerPosLook)) {
            this.updateBlocksPerSecond(0.0F);
         } else {
            C03PacketPlayer packet2 = (C03PacketPlayer)packet;
            double d = Math.abs(packet2.getPositionX() - this.lastPosX);
            this.updateBlocksPerSecond((float)(Math.hypot(Math.abs(packet2.getPositionZ() - this.lastPosZ), d) * 20.0));
            this.lastPosX = packet2.getPositionX();
            this.lastPosZ = packet2.getPositionZ();
         }
      }
   }

   @Override
   public void onRender2DPost(float partialTicks, ScaledResolution sr) {
      if (this.isCustomHotbarActive()) {
         GlStateManager.scale(1.0F / (float)sr.getScaleFactor(), 1.0F / (float)sr.getScaleFactor(), 1.0F);
         double value = this.hotbarScale.getValue(true);
         int n = (int)Math.round((double)mc.displayWidth / value);
         int n2 = (int)Math.round((double)mc.displayHeight / value);
         GlStateManager.scale(value, value, 1.0);
         EntityPlayer renderViewEntity = (EntityPlayer)mc.getRenderViewEntity();
         int n3 = n / 2;
         boolean isBlurActive = this.blurShadowSettings.isBlurActive();
         BlurShadowManager.enableOneShotPass(isBlurActive, this.blurShadowSettings.isShadowActive());
         if (this.isFullBarMode()) {
            BlurShadowManager.submitRenderTask(false, shaderRenderPass -> this.renderFullBarBackgroundPass(n2, n, shaderRenderPass));
            if (this.itemRectangle.getValue()) {
               RenderUtil.drawRect(
                  (float)n / 2.0F - 91.0F, (float)(n2 - 22), (float)n / 2.0F + 91.0F, (float)n2, 20, 20, 20, 185
               );
            }
         } else if (this.isMinimalBarMode()) {
            BlurShadowManager.submitRenderTask(false, shaderRenderPass2 -> this.renderMinimalBarBackgroundPass(n, n2, shaderRenderPass2));
         }

         if (mc.thePlayer.inventory.currentItem != SilentSlotManager.realHotbarSlot && this.silentSlot.getValue()) {
            RenderUtil.drawRect(
               (float)(n3 - 91 + mc.thePlayer.inventory.currentItem * 20),
               (float)(n2 - 22),
               (float)(n3 - 69 + mc.thePlayer.inventory.currentItem * 20),
               (float)n2,
               154,
               206,
               243,
               185
            );
         }

         RenderUtil.drawRect(
            (float)(n3 - 91 + SilentSlotManager.realHotbarSlot * 20),
            (float)(n2 - 22),
            (float)(n3 - 69 + SilentSlotManager.realHotbarSlot * 20),
            (float)n2,
            222,
            222,
            222,
            185
         );
         if (this.isFullBarMode()) {
            if (this.debugInformation.getValue()) {
               RenderUtil.drawRect((float)(n - 5), (float)(n2 - 22), (float)n, (float)n2, 255, 255, 255, 200);
            }

            GuiIngameHook.drawHudStats(n, n2);
         }

         GlStateManager.enableRescaleNormal();
         GlStateManager.enableBlend();
         GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
         RenderHelper.enableGUIStandardItemLighting();

         for (int i = 0; i < 9; i++) {
            int n4 = n / 2 - 90 + i * 20 + 2;
            int n5 = n2 - 16 - 3;
            mc.ingameGUI.renderHotbarItem(i, n4, n5, mc.getTimer().renderPartialTicks, renderViewEntity);
         }

         if (this.inventoryItems.getValue() && mc.thePlayer != null && mc.thePlayer.inventory != null) {
            ContainerPlayer containerPlayer = new ContainerPlayer(mc.thePlayer.inventory, false, mc.thePlayer, true);
            int n6 = (int)((float)n / 2.0F) - 96;
            int n7 = n2 - 165;
            float f2 = (float)n6 + 5.0F;
            int n8 = n7 + 83;
            isBlurActive = this.blurShadowSettings.isBlurActive();
            BlurShadowManager.enableOneShotPass(isBlurActive, this.blurShadowSettings.isShadowActive());
            BlurShadowManager.submitRenderTask(false, shaderRenderPass3 -> this.renderInventoryBackgroundPass(f2, n8, shaderRenderPass3));

            for (int j = 0; j < containerPlayer.inventorySlots.size(); j++) {
               this.renderInventorySlot((Slot)containerPlayer.inventorySlots.get(j), n6, n7);
            }
         }

         RenderHelper.disableStandardItemLighting();
         GlStateManager.disableRescaleNormal();
         GlStateManager.disableBlend();
         GlStateManager.blendFunc(770, 771);
         if (this.isFullBarMode()) {
            CustomFontUtil esp44 = FontManager.esp44;
            double d = (double)(n2 - 20);
            int rgb = Color.white.getRGB();
            double d3 = 1.5;
            esp44.drawStringWithShadow("A", d3, d, rgb);
         }

         GL11.glScaled(1.0 / value, 1.0 / value, 1.0);
         GL11.glScaled((double)sr.getScaleFactor(), (double)sr.getScaleFactor(), 1.0);
         RenderUtil.resetGlColorState();
      }
   }

   public boolean isCustomHotbarActive() {
      return !this.mode.getSelectedValue().equals("Vanilla");
   }

   private boolean isFullBarMode() {
      return this.mode.getSelectedValue().equals("FullBar");
   }

   private boolean isMinimalBarMode() {
      return this.mode.getSelectedValue().equals("MinimalBar");
   }

   private void renderInventorySlot(Slot slot, int n, int n2) {
      int n3 = slot.xDisplayPosition + n;
      int n4 = slot.yDisplayPosition + n2;
      ItemStack stack = slot.getStack();
      GlStateManager.enableDepth();
      mc.getRenderItem().renderItemAndEffectIntoGUI(stack, n3, n4);
      mc.getRenderItem().renderItemOverlayIntoGUI(mc.fontRendererObj, stack, n3, n4, null);
   }

   public int getInventoryHudYOffset() {
      return this.isCustomHotbarActive() && this.inventoryItems.getValue() ? -62 : 0;
   }

   private void updateBlocksPerSecond(float f) {
      this.speedHistory.add(f);

      while (this.speedHistory.size() > 5) {
         this.speedHistory.remove(0);
      }

      float f2 = 0.0F;

      for (float f3 : (Iterable<Float>)(this.speedHistory)) {
         f2 += f3;
      }

      this.blocksPerSecond = (float)((double)Math.round(f2 / (float)this.speedHistory.size() * 100.0F) / 100.0);
   }

   private void renderInventoryBackgroundPass(float f, int n, ShaderRenderPass shaderRenderPass) {
      if (shaderRenderPass == ShaderRenderPass.SHADOW) {
         RenderUtil.drawRect(f, (float)n, f + 182.0F, (float)(n + 60), this.blurShadowSettings.resolvePassColor(shaderRenderPass, Color.black.getRGB()));
      } else {
         RenderUtil.drawRect(f, (float)n, f + 182.0F, (float)(n + 60), 0, 0, 0, 185);
      }
   }

   private void renderMinimalBarBackgroundPass(int n, int n2, ShaderRenderPass shaderRenderPass) {
      if (shaderRenderPass == ShaderRenderPass.SHADOW) {
         RenderUtil.drawRect((float)n / 2.0F - 91.0F, (float)(n2 - 22), (float)n / 2.0F + 91.0F, (float)n2, this.blurShadowSettings.resolveShadowColor(Color.black.getRGB()));
      } else {
         RenderUtil.drawRect(
            (float)n / 2.0F - 91.0F, (float)(n2 - 22), (float)n / 2.0F + 91.0F, (float)n2, 0, 0, 0, 185
         );
      }
   }

   private void renderFullBarBackgroundPass(int n, int n2, ShaderRenderPass shaderRenderPass) {
      if (shaderRenderPass == ShaderRenderPass.SHADOW) {
         float f = (float)(n + 1);
         float f2 = (float)(n2 + 1);
         float f3 = (float)(n - 22);
         BlurShadowSettings blurShadowSettings2 = this.blurShadowSettings;
         int rgb = Color.black.getRGB();
         RenderUtil.drawRect(0.0F, f, f2, f3, blurShadowSettings2.resolveShadowColor(rgb));
      } else {
         RenderUtil.drawRect(0.0F, (float)(n + 1), (float)(n2 + 1), (float)(n - 22), 0, 0, 0, 185);
      }
   }

   private boolean isOtherPingCalculationVisible() {
      return this.debugInformation.getValue() && this.debugInformation.isVisible();
   }

   private boolean isArmorDurabilityVisible() {
      return this.armor.isVisible() && this.armor.getValue();
   }

}
