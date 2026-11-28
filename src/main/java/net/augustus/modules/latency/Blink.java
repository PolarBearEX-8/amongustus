package net.augustus.modules.latency;

import java.awt.Color;
import java.util.ArrayList;
import java.util.function.Consumer;
import net.augustus.modules.Categorys;
import net.augustus.modules.Module;
import net.augustus.modules.misc.Debug;
import net.augustus.utils.render.ShaderRenderPass;
import net.augustus.events.EventPacket;
import net.augustus.events.listeners.RenderListener;
import net.augustus.settings.BlurShadowSettings;
import net.augustus.events.EventRenderEntity;
import net.augustus.ClientManagers;
import net.augustus.settings.ColorSetting;
import net.augustus.events.EventPacketDelay;
import net.augustus.utils.combat.EntityTargetFilter;
import net.augustus.events.listeners.PacketDelayListener;
import net.augustus.utils.combat.TargetFilterPresets;
import net.augustus.utils.combat.EntityTargetSelector;
import net.augustus.render.RenderUtil;
import net.augustus.utils.render.BlurShadowManager;
import net.augustus.events.listeners.RenderEntityListener;
import net.augustus.Augustus;
import net.augustus.events.listeners.TickListener;
import net.augustus.settings.TargetSelectionSettings;
import net.augustus.events.listeners.PacketListener;
import net.augustus.settings.SettingVisibilityCondition;
import net.augustus.settings.BooleanValue;
import net.augustus.settings.StringValue;
import net.augustus.settings.SettingGroup;
import net.augustus.settings.DoubleValue;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.renderer.entity.RendererLivingEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketThreadUtil;
import net.minecraft.network.play.client.C02PacketUseEntity;
import net.minecraft.network.play.client.C03PacketPlayer;
import net.minecraft.network.play.server.S08PacketPlayerPosLook;
import net.minecraft.util.Vec3;

public class Blink extends Module implements PacketDelayListener, PacketListener, RenderListener, RenderEntityListener, TickListener {
   public final StringValue packetDirection;
   public final DoubleValue autoDisable;
   public final BooleanValue disableOnAttack;
   public final BooleanValue disableKillAura;
   private final BooleanValue disableAtSetback;
   public SettingGroup targetESPs;
   public final StringValue targetESP;
   private final ColorSetting classicColor;
   private final DoubleValue lineWidthTarget;
   private final BooleanValue smoothRendering;
   public final BlurShadowSettings target;
   public final TargetSelectionSettings targetSelection;
   public SettingGroup playerESPs;
   public final StringValue playerESP;
   private final ColorSetting classicColor2;
   private final DoubleValue lineWidthMe;
   public final BlurShadowSettings player;
   private final ArrayList renderedTargets;
   private int queuedMovePackets;
   public long enableTimestampMs;

   public Blink() {
       super("Blink", "Laag", new Color(75, 5, 161), Categorys.LATENCY);
      this.packetDirection = new StringValue(this, "PacketDirection", "OnlyOutgoing", new String[]{"OnlyIncoming", "OnlyOutgoing", "Both"});
      this.autoDisable = new DoubleValue(this, "AutoDisable", 0.0, 0.0, 100.0, 0);
      this.disableOnAttack = new BooleanValue(this, "DisableOnAttack", false);
      this.disableKillAura = new BooleanValue(this, "DisableKillAura", false);
      this.disableAtSetback = new BooleanValue(this, "DisableAtSetback", false);
      this.targetESPs = new SettingGroup(this, "TargetESPs");
      this.targetESP = new StringValue(this, "TargetESP", "Classic", new String[]{"None", "Classic", "PlayerModel", "PlayerModelArmor"}, this.targetESPs);
      Color color2 = new Color(0, 255, 0, 255);
      SettingGroup targetESPs2 = this.targetESPs;
      Color color3 = color2;
      String str = "ClassicColorTarget";
      this.classicColor = (ColorSetting)((ColorSetting)new ColorSetting(this, str, color3, targetESPs2).withDisplayName("ClassicColor")).withVisibility(() -> this.isTargetClassicColorVisible());
      this.lineWidthTarget = new DoubleValue(this, "LineWidthTarget", 3.0, 0.0, 10.0, 0, this.targetESPs).named("LineWidth").visibleWhen(() -> this.isTargetLineWidthVisible());
      BooleanValue booleanValue = new BooleanValue(this, "SmoothRendering", false, this.targetESPs);
      SettingVisibilityCondition condition3 = () -> {
         return !this.targetESP.getSelectedValue().equals("None");
      };
      this.smoothRendering = (BooleanValue)booleanValue.withVisibility(condition3);
      BlurShadowSettings blurShadowSettings = new BlurShadowSettings(this, "Target", this.targetESPs);
      SettingVisibilityCondition condition4 = () -> {
         return !this.targetESP.getSelectedValue().equals("None");
      };
      this.target = ((BlurShadowSettings)blurShadowSettings.withVisibility(condition4)).disableBlurByDefault();
      this.targetSelection = new TargetSelectionSettings(this, TargetFilterPresets.createRenderFilter(), this.targetESPs).withVisibility(() -> this.isTargetSelectionVisible());
      this.playerESPs = new SettingGroup(this, "PlayerESPs");
      this.playerESP = new StringValue(this, "PlayerESP", "Classic", new String[]{"None", "Classic", "PlayerModel", "PlayerModelArmor"}, this.playerESPs);
      color2 = new Color(0, 255, 0, 255);
      targetESPs2 = this.playerESPs;
      color3 = color2;
      str = "ClassicColorMe";
      this.classicColor2 = (ColorSetting)((ColorSetting)new ColorSetting(this, str, color3, targetESPs2).withDisplayName("ClassicColor")).withVisibility(() -> this.isPlayerClassicColorVisible());
      this.lineWidthMe = new DoubleValue(this, "LineWidthMe", 3.0, 0.0, 10.0, 0, this.playerESPs).named("LineWidth").visibleWhen(() -> this.isPlayerLineWidthVisible());
      this.player = ((BlurShadowSettings)new BlurShadowSettings(this, "Player", this.playerESPs).withVisibility(() -> this.isPlayerBlurShadowVisible())).disableBlurByDefault();
      this.renderedTargets = new ArrayList();
      this.queuedMovePackets = 0;
      this.enableTimestampMs = 0L;
   }

   @Override
   public void onEnable() {
      this.queuedMovePackets = 0;
      this.enableTimestampMs = System.currentTimeMillis();
   }

   @Override
   public void onDisable() {
      this.queuedMovePackets = 0;
      if (Augustus.getInstance().getModuleManager() != null && ClientManagers.moduleManager.debug.isToggled() && ClientManagers.moduleManager.debug.blink.getValue()) {
         ClientManagers.moduleManager.debug.sendChatMessage(System.currentTimeMillis() - this.enableTimestampMs + " ");
      }

      this.renderedTargets.clear();
   }

   @Override
   public void updateEventSubscriptions() {
      this.toggleEventSubscription(25);
      this.toggleEventSubscription(26);
      this.toggleEventSubscription(32);
      this.toggleEventSubscription(31);
      this.toggleEventSubscription(40);
   }

   @Override
   public void onTick() {
      this.renderedTargets.clear();
      EntityTargetSelector entityTargetSelector = EntityTargetSelector.of(mc.theWorld.loadedEntityList);
      entityTargetSelector = entityTargetSelector.withTargetFilter(this.targetSelection.getTargetFilter());
      entityTargetSelector.forEach(obj -> this.renderedTargets.add((EntityLivingBase)obj));
   }

   @Override
   public void onIncomingPacketDelay(EventPacketDelay event) {
      if (mc.theWorld == null || mc.thePlayer == null) {
         this.setToggled(false);
      }

      if (this.disableAtSetback.getValue() && event.getPacket() instanceof S08PacketPlayerPosLook) {
         PacketThreadUtil.runOnMainThreadImmediate(() -> this.disableOnSetback());
      }

      if (this.packetDirection.getSelectedValue().equals("OnlyIncoming") || this.packetDirection.getSelectedValue().equals("Both")) {
         event.setDelayed(true);
      }
   }

   @Override
   public void onOutgoingPacketDelay(EventPacketDelay event) {
      if (this.packetDirection.getSelectedValue().equals("OnlyOutgoing") || this.packetDirection.getSelectedValue().equals("Both")) {
         if (true) {
            event.setDelayed(true);
         }
      }
   }

   @Override
   public void onPacketSend(EventPacket event) {
      Packet packet = event.getPacket();
      this.setSuffix("" + this.queuedMovePackets);
      if (this.autoDisable.getValue() > 0.0 && (double)this.queuedMovePackets > this.autoDisable.getValue()) {
         this.queuedMovePackets = 0;
         this.toggle();
      }

      if (packet instanceof C03PacketPlayer) {
         this.queuedMovePackets++;
      }

      if (this.disableOnAttack.getValue() && packet instanceof C02PacketUseEntity && ((C02PacketUseEntity)packet).getAction() == C02PacketUseEntity.Action.ATTACK) {
         this.setToggled(false);
      }
   }

   @Override
   public void onRender3D(float partialTicks) {
      RenderUtil.begin3DRender();
      if (ClientManagers.moduleManager.packetHandler.isDelayingIncoming() || ClientManagers.moduleManager.packetHandler.hasQueuedOutgoingPackets()) {
         if (this.targetESP.getSelectedValue().equals("Classic") && ClientManagers.moduleManager.packetHandler.isDelayingIncoming()) {
            BlurShadowManager.enableOneShotPass(this.target.isBlurActive(), this.target.isShadowActive());
            BlurShadowManager.submitRenderTask(false, shaderRenderPass -> this.renderClassicTargetBoxes(shaderRenderPass));
         }

         if (ClientManagers.moduleManager.packetHandler.hasQueuedOutgoingPackets() && this.playerESP.getSelectedValue().equals("Classic") && mc.thePlayer != null) {
            BlurShadowManager.enableOneShotPass(this.player.isBlurActive(), this.player.isShadowActive());
            BlurShadowManager.submitRenderTask(false, shaderRenderPass2 -> this.renderClassicPlayerBox(shaderRenderPass2));
         }
      }

      RenderUtil.end3DRender();
   }

   @Override
   public void onRenderEntity(EventRenderEntity event) {
      EntityLivingBase entity = event.getEntity();
      if (ClientManagers.moduleManager.packetHandler.isDelayingIncoming() && this.isValidEspTarget(entity)) {
         if (this.isPlayerModelEspMode(this.targetESP)) {
            this.renderTargetModel(event, entity, this.targetESP.getSelectedValue().equals("PlayerModelArmor"));
         }
      }

      if (mc.thePlayer != null && ClientManagers.moduleManager.packetHandler.hasQueuedOutgoingPackets() && mc.thePlayer.equals(event.getEntity())) {
         if (this.isPlayerModelEspMode(this.playerESP)) {
            this.renderPlayerModel(event, mc.thePlayer.getServerPos(), this.playerESP.getSelectedValue().equals("PlayerModelArmor"));
         }
      }
   }

   private boolean isValidEspTarget(Entity entity) {
      return entity instanceof EntityLivingBase && this.targetSelection.getTargetFilter().matches(entity);
   }

   private boolean isPlayerModelEspMode(StringValue booleansSetting) {
      return booleansSetting.getSelectedValue().equals("PlayerModel") || booleansSetting.getSelectedValue().equals("PlayerModelArmor");
   }

   public void renderTargetModel(EventRenderEntity event, EntityLivingBase entity, boolean flag) {
      double d = entity.prevInterpolatedServerPos.xCoord + (entity.interpolatedServerPos.xCoord - entity.prevInterpolatedServerPos.xCoord) * (double)mc.getTimer().renderPartialTicks;
      double d2 = entity.prevInterpolatedServerPos.yCoord + (entity.interpolatedServerPos.yCoord - entity.prevInterpolatedServerPos.yCoord) * (double)mc.getTimer().renderPartialTicks;
      double d3 = entity.prevInterpolatedServerPos.zCoord + (entity.interpolatedServerPos.zCoord - entity.prevInterpolatedServerPos.zCoord) * (double)mc.getTimer().renderPartialTicks;
      BlurShadowManager.enableOneShotPass(this.target.isBlurActive(), this.target.isShadowActive());
      RendererLivingEntity renderer = event.getRenderer();
      BlurShadowManager.submitRenderTask(false, shaderRenderPass -> renderTargetModelPass(event, entity, renderer, d, d2, d3, flag, shaderRenderPass));
   }

   public void renderPlayerModel(EventRenderEntity event, Vec3 vec, boolean flag) {
      BlurShadowManager.enableOneShotPass(this.player.isBlurActive(), this.player.isShadowActive());
      RendererLivingEntity renderer = event.getRenderer();
      BlurShadowManager.submitRenderTask(false, shaderRenderPass -> renderPlayerModelPass(event, renderer, vec, flag, shaderRenderPass));
   }

   private static void renderPlayerModelPass(EventRenderEntity event, RendererLivingEntity rendererLivingEntity, Vec3 vec, boolean flag, ShaderRenderPass shaderRenderPass) {
      double zCoord = vec.zCoord;
      double yCoord = vec.yCoord;
      double xCoord = vec.xCoord;
      event.renderEntityModel(mc.thePlayer, rendererLivingEntity, xCoord, yCoord, zCoord, flag);
   }

   private static void renderTargetModelPass(
      EventRenderEntity event, EntityLivingBase entity, RendererLivingEntity rendererLivingEntity, double d, double d2, double d3, boolean flag, ShaderRenderPass shaderRenderPass
   ) {
      event.renderEntityModel(entity, rendererLivingEntity, d, d2, d3, flag);
   }

   private void renderClassicPlayerBox(ShaderRenderPass shaderRenderPass) {
      RenderUtil.drawEntityEspBox(mc.thePlayer, this.classicColor2, (float)this.lineWidthMe.getValue(), (short)255, false);
   }

   private void renderClassicTargetBoxes(ShaderRenderPass shaderRenderPass) {

      for (EntityLivingBase entity : (Iterable<EntityLivingBase>)(this.renderedTargets)) {
         if (!(entity instanceof EntityPlayerSP)) {
            RenderUtil.drawEntityEspBox(entity, this.classicColor, (float)this.lineWidthTarget.getValue(), (short)255, this.smoothRendering.getValue());
            BlurShadowManager.enableOneShotPass(this.target.isBlurActive(), this.target.isShadowActive());
         }
      }
   }

   private void disableOnSetback() {
      this.setToggled(false);
   }

   private boolean isPlayerBlurShadowVisible() {
      return !this.playerESP.getSelectedValue().equals("None");
   }

   private boolean isPlayerLineWidthVisible() {
      return this.playerESP.getSelectedValue().equals("Classic");
   }

   private boolean isPlayerClassicColorVisible() {
      return this.playerESP.getSelectedValue().equals("Classic");
   }

   private boolean isTargetSelectionVisible() {
      return !this.targetESP.getSelectedValue().equals("None");
   }

   private boolean isTargetLineWidthVisible() {
      return this.targetESP.getSelectedValue().equals("Classic");
   }

   private boolean isTargetClassicColorVisible() {
      return this.targetESP.getSelectedValue().equals("Classic");
   }


}
