package net.augustus.modules.render;

import java.awt.Color;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.BiFunction;
import net.augustus.modules.Categorys;
import net.augustus.modules.Module;
import net.augustus.events.listeners.RenderListener;
import net.augustus.settings.BlurShadowSettings;
import net.augustus.settings.options.TargetEspModuleOption;
import net.augustus.utils.rotation.RotationUtil;
import net.augustus.ClientManagers;
import net.augustus.settings.MultiOptionSetting;
import net.augustus.settings.ColorSetting;
import net.augustus.settings.ModeSetting;
import net.augustus.modules.render.helper.HitMarkerPopup;
import net.augustus.modules.modes.targetesp.NoneTargetEspMode;
import net.augustus.modules.modes.targetesp.BoxTargetEspMode;
import net.augustus.modules.modes.targetesp.SigmaTargetEspMode;
import net.augustus.modules.modes.targetesp.HitBoxTargetEspMode;
import net.augustus.modules.modes.targetesp.AbstractTargetEspMode;
import net.augustus.utils.combat.CombatTargetUtil;
import net.augustus.utils.rotation.RayTraceUtil;
import net.augustus.render.RenderUtil;
import net.augustus.events.EventAttackEntity;
import net.augustus.events.listeners.AttackEntityListener;
import net.augustus.settings.SettingVisibilityCondition;
import net.augustus.utils.rotation.Rotation;
import net.augustus.settings.BooleanValue;
import net.augustus.settings.SettingGroup;
import net.augustus.settings.DoubleValue;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import org.lwjgl.opengl.GL11;

public class TargetESP extends Module implements RenderListener, AttackEntityListener {
   private final MultiOptionSetting modules;
   private final ModeSetting mode;
   public final BlurShadowSettings blurShadowSettings;
   private final BooleanValue critVisualizer;
   private final ColorSetting critVisualizerColor;
   private final BooleanValue bestAimPoint;
   private final DoubleValue bestAimPointSize;
   public final ColorSetting bestAimPointColor;
   private final DoubleValue duration;
   private final CopyOnWriteArrayList hitMarkerPopups;

   public TargetESP() {
       super("TargetESP", "ZielAußersinnlicheWahrnehmung", new Color(212, 133, 76), Categorys.RENDER);
      this.modules = new MultiOptionSetting(this, "Modules", TargetEspModuleOption.values());
      BiFunction[] biFunctions = new BiFunction[]{
         (obj, obj2) -> new NoneTargetEspMode((TargetESP)obj, (SettingGroup)obj2),
         (obj3, obj4) -> new BoxTargetEspMode((TargetESP)obj3, (SettingGroup)obj4),
         (obj5, obj6) -> new HitBoxTargetEspMode((TargetESP)obj5, (SettingGroup)obj6),
         (obj7, obj8) -> new SigmaTargetEspMode((TargetESP)obj7, (SettingGroup)obj8)
      };
      this.mode = new ModeSetting(this, "Mode", "Box", biFunctions);
      this.blurShadowSettings = ((BlurShadowSettings)new BlurShadowSettings(this).withCustomShadowColor(Color.black).withVisibility(() -> this.isBlurShadowVisible())).disableBlurByDefault();
      this.critVisualizer = new BooleanValue(this, "CritVisualizer", true);
      ColorSetting colorSetting3 = new ColorSetting(this, "CritVisualizerColor", new Color(255, 164, 75)).withRainbowDefault(true).withDefaultRainbowSpeed((byte)50);
      BooleanValue critVisualizer2 = this.critVisualizer;
      BooleanValue booleanValue = critVisualizer2;
      this.critVisualizerColor = (ColorSetting)colorSetting3.withVisibility(() -> booleanValue.getValue());
      this.bestAimPoint = new BooleanValue(this, "BestAimPoint", false);
      DoubleValue bestAimPointSizeSetting = new DoubleValue(this, "BestAimPointSize", 0.3, 0.01, 1.0, 2);
      critVisualizer2 = this.bestAimPoint;
      BooleanValue booleanValue2 = critVisualizer2;
      this.bestAimPointSize = bestAimPointSizeSetting.visibleWhen(() -> booleanValue2.getValue());
      ColorSetting colorSetting4 = new ColorSetting(this, "bestAimPointColor", new Color(41, 146, 222));
      critVisualizer2 = this.bestAimPoint;
      BooleanValue booleanValue3 = critVisualizer2;
      this.bestAimPointColor = (ColorSetting)colorSetting4.withVisibility(() -> booleanValue3.getValue());
      DoubleValue durationSetting = new DoubleValue(this, "Duration", 1000.0, 100.0, 4000.0, 0);
      critVisualizer2 = this.critVisualizer;
      BooleanValue booleanValue4 = critVisualizer2;
      this.duration = durationSetting.visibleWhen(() -> booleanValue4.getValue());
      this.hitMarkerPopups = new CopyOnWriteArrayList();
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
      this.toggleEventSubscription(1);
   }

   @Override
   public void onAttackEntity(EventAttackEntity event) {
      if (this.getActiveTarget() != null) {
         boolean flag = mc.thePlayer.fallDistance > 0.0F
            && !mc.thePlayer.onGround
            && !mc.thePlayer.isOnLadder()
            && !mc.thePlayer.isInWater()
            && !mc.thePlayer.isPotionActive(Potion.blindness)
            && mc.thePlayer.ridingEntity == null
            && mc.objectMouseOver.entityHit instanceof EntityLivingBase;
         this.hitMarkerPopups.add(new HitMarkerPopup(flag ? "Crit!" : "Hit!", mc.objectMouseOver.hitVec, System.currentTimeMillis()));
      }
   }

   @Override
   public void onRender3D(float partialTicks) {
      EntityLivingBase activeTarget = this.getActiveTarget();
      if (activeTarget != null && this.mode.getAbstractSubMode() != null) {
         RenderUtil.begin3DRender();
         ((AbstractTargetEspMode)this.mode.getAbstractSubMode()).renderTargetEsp(activeTarget);
         RenderUtil.end3DRender();
      }

      if (this.critVisualizer.getValue()) {
         GL11.glEnable(32823);
         GL11.glPolygonOffset(1.0F, -1100000.0F);

         for (int i = 0; i < this.hitMarkerPopups.size(); i++) {
            HitMarkerPopup hitMarkerPopup = (HitMarkerPopup)this.hitMarkerPopups.get(i);
            int colorInt = this.critVisualizerColor.getColorInt(i);
            if ((double)HitMarkerPopup.getCreatedAtMs(hitMarkerPopup) + this.duration.getValue() < (double)System.currentTimeMillis()) {
               this.hitMarkerPopups.remove(hitMarkerPopup);
            } else {
               double d = HitMarkerPopup.getPosition(hitMarkerPopup).xCoord - mc.getRenderManager().getRenderPosX();
               double d2 = HitMarkerPopup.getPosition(hitMarkerPopup).yCoord - mc.getRenderManager().getRenderPosY();
               double d3 = HitMarkerPopup.getPosition(hitMarkerPopup).zCoord - mc.getRenderManager().getRenderPosZ();
               GlStateManager.pushMatrix();
               GlStateManager.translate(d, d2, d3);
               GlStateManager.rotate(-mc.getRenderManager().playerViewY, 0.0F, 1.0F, 0.0F);
               Minecraft minecraft = Minecraft.getMinecraft();
               float f2;
               if (minecraft.gameSettings.thirdPersonView == 2) {
                  f2 = -mc.getRenderManager().playerViewX;
               } else {
                  f2 = mc.getRenderManager().playerViewX;
               }

               GlStateManager.rotate(f2, 1.0F, 0.0F, 0.0F);
               GlStateManager.scale(-0.02, -0.02, 0.02);
               GlStateManager.depthMask(true);
               GlStateManager.disableCull();
               GlStateManager.enableTexture2D();
               mc.fontRendererObj.drawStringWithShadow(HitMarkerPopup.getText(hitMarkerPopup), (float)(-mc.fontRendererObj.getStringWidth(HitMarkerPopup.getText(hitMarkerPopup))) / 2.0F, -4.0F, colorInt);
               GlStateManager.popMatrix();
            }
         }

         GL11.glDisable(32823);
         GL11.glPolygonOffset(1.0F, 1100000.0F);
      }

      if (this.bestAimPoint.getValue() && activeTarget != null) {
         RenderUtil.begin3DRender();
         Vec3 vec = RotationUtil.getInterpolatedBestHitVec(activeTarget, partialTicks, (byte)100, (byte)80).addVector(-mc.getRenderManager().getRenderPosX(), -mc.getRenderManager().getRenderPosY(), -mc.getRenderManager().getRenderPosZ());
         float f3 = (float)this.bestAimPointSize.getValue() / 10.0F;
         RenderUtil.setGlColor(this.bestAimPointColor.getColorInt((byte)0));
         RenderUtil.drawFilledBox(
            vec.xCoord - (double)f3,
            vec.yCoord - (double)f3,
            vec.zCoord - (double)f3,
            vec.xCoord + (double)f3,
            vec.yCoord + (double)f3,
            vec.zCoord + (double)f3
         );
         RenderUtil.end3DRender();
      }
   }

   public EntityLivingBase getActiveTarget() {
      EntityLivingBase entity;
      if (ClientManagers.moduleManager.killAura.isToggled() && ClientManagers.moduleManager.killAura.target != null && TargetEspModuleOption.KILL_AURA.isSelected()) {
         entity = ClientManagers.moduleManager.killAura.target;
      } else if (ClientManagers.moduleManager.aimBot.isToggled() && ClientManagers.moduleManager.aimBot.target != null && TargetEspModuleOption.AIM_BOT.isSelected()) {
         entity = ClientManagers.moduleManager.aimBot.target;
      } else if (ClientManagers.moduleManager.aimAssist.isToggled() && ClientManagers.moduleManager.aimAssist.target != null && TargetEspModuleOption.AIM_ASSIST.isSelected()) {
         entity = ClientManagers.moduleManager.aimAssist.target;
      } else if (TargetEspModuleOption.AUTO_ROD.isSelected() && ClientManagers.moduleManager.autoRod.isToggled() && ClientManagers.moduleManager.autoRod.getTarget() != null) {
         entity = ClientManagers.moduleManager.autoRod.getTarget();
      } else if (TargetEspModuleOption.AUTO_ROD.isSelected() && ClientManagers.moduleManager.autoRodNew.isToggled() && ClientManagers.moduleManager.autoRodNew.getTarget() != null) {
         entity = ClientManagers.moduleManager.autoRodNew.getTarget();
      } else {
         entity = this.getCrosshairTarget();
      }

      return entity;
   }

   private EntityLivingBase getCrosshairTarget() {
      if (!TargetEspModuleOption.LEGIT.isSelected()) {
         return null;
      } else {
         Rotation rotation = new Rotation(mc.thePlayer.rotationYaw, mc.thePlayer.rotationPitch);
         double d = 0.5;
         double d2 = 6.0;
         MovingObjectPosition hit = RayTraceUtil.rayTraceEntity(1.0F, rotation, d2, d);
         return hit == null
               || !(hit.entityHit instanceof EntityLivingBase)
               || hit.entityHit instanceof EntityPlayer && CombatTargetUtil.hasProtectionEffect((EntityPlayer)hit.entityHit)
            ? null
            : (EntityLivingBase)hit.entityHit;
      }
   }

   private boolean isBlurShadowVisible() {
      return !this.mode.getSelectedValue().equals("None");
   }


}
