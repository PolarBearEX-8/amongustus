package net.augustus.modules.render;

import java.awt.Color;
import java.util.concurrent.CopyOnWriteArrayList;
import net.augustus.modules.Categorys;
import net.augustus.modules.Module;
import net.augustus.events.listeners.RenderListener;
import net.augustus.settings.ColorSetting;
import net.augustus.utils.MoveUtil;
import net.augustus.events.listeners.UpdateListener;
import net.augustus.render.RenderUtil;
import net.augustus.settings.SettingVisibilityCondition;
import net.augustus.utils.animation.AnimationUtil;
import net.augustus.settings.BooleanValue;
import net.augustus.settings.StringValue;
import net.augustus.settings.DoubleValue;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.MathHelper;
import org.lwjgl.opengl.GL11;

public class MoveCircles extends Module implements RenderListener, UpdateListener {
   private final DoubleValue circleRadius;
   private final DoubleValue circleWidth;
   private final ColorSetting circleColor;
   private final StringValue circleAnimation;
   private final DoubleValue animationTime;
   private final BooleanValue fadeOut;
   private final StringValue fadeOutAnimation;
   private final DoubleValue fadeOutTime;
   private final DoubleValue ticksBetweenCircles;
   private final BooleanValue onlyAfterJump;
   private final CopyOnWriteArrayList activeCircles;
   private boolean wasOnGround;
   private int circleCounter;
   private int ticksSinceLastCircle;

   public MoveCircles() {
       super("MoveCircles", "BewegungsKreise", new Color(245, 152, 217), Categorys.RENDER);
      this.circleRadius = new DoubleValue(this, "CircleRadius", 1.5, 0.1, 3.0, 2);
      this.circleWidth = new DoubleValue(this, "CircleWidth", 1.0, 0.1, 7.0, 2);
      this.circleColor = new ColorSetting(this, "CircleColor", new Color(75, 209, 242));
      this.circleAnimation = new StringValue(this, "CircleAnimation", "easeInSine", AnimationUtil.getEasingNames(false));
      this.animationTime = new DoubleValue(this, "AnimationTime", 500.0, 50.0, 1500.0, 0);
      this.fadeOut = new BooleanValue(this, "FadeOut", true);
      StringValue setting = new StringValue(this, "FadeOutAnimation", "easeInSine", AnimationUtil.getEasingNames(false));
      BooleanValue fadeOut2 = this.fadeOut;
      BooleanValue booleanValue = fadeOut2;
      this.fadeOutAnimation = (StringValue)setting.withVisibility(() -> booleanValue.getValue());
      DoubleValue fadeOutTimeSetting = new DoubleValue(this, "FadeOutTime", 500.0, 50.0, 1500.0, 0);
      fadeOut2 = this.fadeOut;
      BooleanValue booleanValue2 = fadeOut2;
      this.fadeOutTime = fadeOutTimeSetting.visibleWhen(() -> booleanValue2.getValue());
      this.ticksBetweenCircles = new DoubleValue(this, "TicksBetweenCircles", 5.0, 0.0, 10.0, 0);
      this.onlyAfterJump = new BooleanValue(this, "OnlyAfterJump", false);
      this.activeCircles = new CopyOnWriteArrayList();
      this.circleCounter = 0;
      this.ticksSinceLastCircle = 0;
   }

   @Override
   public void onEnable() {
      this.circleCounter = 0;
      this.ticksSinceLastCircle = 0;
   }

   @Override
   public void onDisable() {
   }

   @Override
   public void updateEventSubscriptions() {
      this.toggleEventSubscription(32);
      this.toggleEventSubscription(43);
   }

   @Override
   public void onPostUpdate() {
      if (mc.thePlayer.onGround && (double)this.ticksSinceLastCircle >= this.ticksBetweenCircles.getValue() && (!this.onlyAfterJump.getValue() || this.wasOnGround != mc.thePlayer.onGround)) {
         this.ticksSinceLastCircle = 0;
         CopyOnWriteArrayList activeCircles2 = this.activeCircles;
         double posX = mc.thePlayer.posX;
         double posY = mc.thePlayer.posY;
         double posZ = mc.thePlayer.posZ;
         float value = (float)this.circleWidth.getValue();
         float value2 = (float)this.circleRadius.getValue();
         int circleCounter2 = this.circleCounter;
         activeCircles2.add(new MoveCircleEffect(this, posX, posY, posZ, value, value2, this.circleColor.getColorInt(circleCounter2)));
         this.circleCounter++;
      }

      this.wasOnGround = mc.thePlayer.onGround;
      if (MoveUtil.hasHorizontalMotion()) {
         this.ticksSinceLastCircle++;
      }
   }

   @Override
   public void onRender3D(float partialTicks) {
      if (!this.activeCircles.isEmpty()) {
         RenderUtil.begin3DRender();

         for (MoveCircleEffect moveCircleEffect : (Iterable<MoveCircleEffect>)(this.activeCircles)) {
            if (moveCircleEffect.expandAnimation.getLinearProgress() >= 1.0) {
               if (this.fadeOut.getValue()) {
                  if (moveCircleEffect.lastExpandProgress < 1.0) {
                     moveCircleEffect.fadeOutAnimation.start((int)this.fadeOutTime.getValue(), AnimationUtil.parseEasingType(this.fadeOutAnimation.getSelectedValue()), 0, false);
                  }

                  moveCircleEffect.alpha = MathHelper.clamp_double(moveCircleEffect.alpha * moveCircleEffect.fadeOutAnimation.getEasedProgress(), 0.0, 255.0);
               }

               if (!this.fadeOut.getValue() || moveCircleEffect.fadeOutAnimation.getLinearProgress() >= 1.0) {
                  this.activeCircles.remove(moveCircleEffect);
                  continue;
               }
            }

            moveCircleEffect.lastExpandProgress = moveCircleEffect.expandAnimation.getLinearProgress();
            GlStateManager.pushMatrix();
            GlStateManager.translate(
               moveCircleEffect.posX - mc.getRenderManager().getRenderPosX(), moveCircleEffect.posY - mc.getRenderManager().getRenderPosY(), moveCircleEffect.posZ - mc.getRenderManager().getRenderPosZ()
            );
            GlStateManager.rotate(90.0F, 1.0F, 0.0F, 0.0F);
            GL11.glLineWidth(moveCircleEffect.lineWidth);
            RenderUtil.setGlColor(moveCircleEffect.color);
            GL11.glBegin(3);
            float f2 = (float)((double)moveCircleEffect.maxRadius * moveCircleEffect.expandAnimation.getEasedProgress());

            for (int i = 0; i <= 360; i++) {
               GL11.glVertex2f(
                  (float)((double)f2 * Math.cos((double)i * Math.PI / 180.0)), (float)((double)f2 * Math.sin((double)i * Math.PI / 180.0))
               );
            }

            GL11.glEnd();
            GlStateManager.popMatrix();
         }

         RenderUtil.end3DRender();
      }
   }

   public static DoubleValue getAnimationTimeSetting(MoveCircles moveCircles) {
      return moveCircles.animationTime;
   }

   public static StringValue getCircleAnimationSetting(MoveCircles moveCircles) {
      return moveCircles.circleAnimation;
   }

}
