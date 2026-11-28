package net.augustus.modules.render;

import java.awt.Color;
import java.util.function.BiFunction;
import net.augustus.modules.Categorys;
import net.augustus.modules.Module;
import net.augustus.events.listeners.RenderListener;
import net.augustus.settings.BlurShadowSettings;
import net.augustus.ClientManagers;
import net.augustus.settings.ModeSetting;
import net.augustus.modules.modes.targethud.ModernTargetHudMode;
import net.augustus.modules.modes.targethud.MinecraftTargetHudMode;
import net.augustus.modules.modes.targethud.AbstractTargetHudMode;
import net.augustus.modules.modes.targethud.LiquidGlassTargetHudMode;
import net.augustus.render.RenderUtil;
import net.augustus.render.ProjectionUtil;
import net.augustus.events.listeners.TickListener;
import net.augustus.settings.SettingVisibilityCondition;
import net.augustus.utils.animation.AnimationUtil;
import net.augustus.settings.BooleanValue;
import net.augustus.settings.StringValue;
import net.augustus.settings.DoubleValue;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.util.Vec3;

public class TargetHud extends Module implements RenderListener, TickListener {
   public final ModeSetting modes;
   public final StringValue healthMode;
   public final BooleanValue trackEntity;
   public final DoubleValue trackY;
   public final DoubleValue trackX;
   public final DoubleValue xCoord;
   public final DoubleValue yCoord;
   public final StringValue toggleAnimationMode;
   public final DoubleValue animationTime;
   public final BlurShadowSettings blurShadowSettings;
   private AbstractClientPlayer displayedTarget;
   private AbstractClientPlayer lastActiveTarget;
   public Vec3 projectedTargetPos;

   public TargetHud() {
       super("TargetHud", "Angriffszieloberfläche", new Color(50, 168, 158), Categorys.RENDER);
      BiFunction[] biFunctions = new BiFunction[]{
         MinecraftTargetHudMode.factory(), ModernTargetHudMode.factory(), LiquidGlassTargetHudMode.factory()
      };
      this.modes = new ModeSetting(this, "Modes", "LiquidGlass", biFunctions);
      this.healthMode = new StringValue(this, "HealthMode", "Entity", new String[]{"Entity", "Scoreboard", "NameTags"});
      this.trackEntity = new BooleanValue(this, "TrackEntity", true);
      DoubleValue trackYSetting = new DoubleValue(this, "TrackY", 0.0, -2.0, 2.0, 2);
      BooleanValue trackEntity2 = this.trackEntity;
      BooleanValue booleanValue = trackEntity2;
      this.trackY = trackYSetting.visibleWhen(() -> booleanValue.getValue());
      DoubleValue trackXSetting = new DoubleValue(this, "TrackX", 0.0, -200.0, 200.0, 2);
      trackEntity2 = this.trackEntity;
      BooleanValue booleanValue2 = trackEntity2;
      this.trackX = trackXSetting.visibleWhen(() -> booleanValue2.getValue());
      this.xCoord = new DoubleValue(this, "XCoord", 60.0, 0.0, 100.0, 2).visibleWhen(() -> this.isXCoordVisible());
      this.yCoord = new DoubleValue(this, "YCoord", 50.0, 0.0, 100.0, 2).visibleWhen(() -> this.isYCoordVisible());
      this.toggleAnimationMode = new StringValue(this, "ToggleAnimation", "easeOutBack", AnimationUtil.getEasingNames(true));
      this.animationTime = new DoubleValue(this, "AnimationTime", 500.0, 0.0, 2000.0, 0).visibleWhen(() -> this.isAnimationTimeVisible());
      this.blurShadowSettings = new BlurShadowSettings(this).withCustomShadowColor(Color.black);
      this.displayedTarget = null;
      this.lastActiveTarget = null;
      this.projectedTargetPos = new Vec3(0.0, 0.0, 0.0);
   }

   @Override
   public void onEnable() {
   }

   @Override
   public void onDisable() {
      this.displayedTarget = null;
      this.lastActiveTarget = null;
   }

   @Override
   public void updateEventSubscriptions() {
      this.toggleEventSubscription(32);
      this.toggleEventSubscription(40);
   }

   @Override
   public void onEarlyTick() {
      this.setSuffix(this.modes.getSelectedValue());
   }

   @Override
   public void onRender2DPost(float partialTicks, ScaledResolution sr) {
      AbstractClientPlayer activeTarget = this.getActiveTarget();
      if (activeTarget != this.lastActiveTarget && this.modes.getAbstractSubMode() != null) {
         ((AbstractTargetHudMode)this.modes.getAbstractSubMode()).onTargetChanged(activeTarget);
      }

      if (activeTarget != null) {
         this.displayedTarget = activeTarget;
      }

      if ((!this.trackEntity.getValue() || this.projectedTargetPos.zCoord >= 0.0 && this.projectedTargetPos.zCoord <= 1.0) && this.displayedTarget != null) {
         AbstractTargetHudMode abstractSubMode = (AbstractTargetHudMode)this.modes.getAbstractSubMode();
         if (abstractSubMode != null) {
            abstractSubMode.renderTargetHud(this.displayedTarget, activeTarget, sr);
            if (this.displayedTarget != null) {
               abstractSubMode.recordLastTarget(this.displayedTarget);
            }
         }
      }

      this.lastActiveTarget = activeTarget;
      RenderUtil.resetGlColorState();
   }

   @Override
   public void onRender3D(float partialTicks) {
      if (this.trackEntity.getValue()
         && this.displayedTarget != null
         && this.modes.getAbstractSubMode() != null
         && (((AbstractTargetHudMode)this.modes.getAbstractSubMode()).toggleAnimation.getLinearProgress() != 1.0 || this.lastActiveTarget != null)) {
         Vec3 vec = new Vec3(
            this.displayedTarget.lastTickPosX + (this.displayedTarget.posX - this.displayedTarget.lastTickPosX) * (double)mc.getTimer().renderPartialTicks - mc.getRenderManager().getRenderPosX(),
            this.displayedTarget.lastTickPosY + (this.displayedTarget.posY - this.displayedTarget.lastTickPosY) * (double)mc.getTimer().renderPartialTicks - mc.getRenderManager().getRenderPosY(),
            this.displayedTarget.lastTickPosZ + (this.displayedTarget.posZ - this.displayedTarget.lastTickPosZ) * (double)mc.getTimer().renderPartialTicks - mc.getRenderManager().getRenderPosZ()
         );
         this.projectedTargetPos = ProjectionUtil.project3DTo2D(
            vec.xCoord, vec.yCoord + (double)(this.displayedTarget.height / 2.0F) + this.trackY.getValue(), vec.zCoord, new ScaledResolution(mc)
         );
      }
   }

   private AbstractClientPlayer getActiveTarget() {
      if (ClientManagers.moduleManager.killAura.target instanceof AbstractClientPlayer) {
         return (AbstractClientPlayer)ClientManagers.moduleManager.killAura.target;
      } else if (ClientManagers.moduleManager.aimBot.target instanceof AbstractClientPlayer) {
         return (AbstractClientPlayer)ClientManagers.moduleManager.aimBot.target;
      } else {
         return ClientManagers.moduleManager.aimAssist.target instanceof AbstractClientPlayer ? (AbstractClientPlayer)ClientManagers.moduleManager.aimAssist.target : null;
      }
   }

   private boolean isAnimationTimeVisible() {
      return !this.toggleAnimationMode.getSelectedValue().equals("None");
   }

   private boolean isYCoordVisible() {
      return !this.trackEntity.getValue();
   }

   private boolean isXCoordVisible() {
      return !this.trackEntity.getValue();
   }

}
