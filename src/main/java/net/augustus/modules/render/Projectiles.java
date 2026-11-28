package net.augustus.modules.render;

import java.awt.Color;
import net.augustus.modules.Categorys;
import net.augustus.modules.Module;
import net.augustus.utils.render.ShaderRenderPass;
import net.augustus.events.listeners.RenderListener;
import net.augustus.settings.BlurShadowSettings;
import net.augustus.settings.MultiOptionSetting;
import net.augustus.settings.ColorSetting;
import net.augustus.render.RenderUtil;
import net.augustus.utils.render.ColorUtil;
import net.augustus.utils.render.BlurShadowManager;
import net.augustus.utils.simulation.ProjectileSimulator;
import net.augustus.events.listeners.TickListener;
import net.augustus.settings.options.ProjectilesItemOption;
import net.augustus.settings.BooleanValue;
import net.augustus.settings.DoubleValue;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityEnderPearl;
import net.minecraft.entity.item.EntityExpBottle;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.entity.projectile.EntityEgg;
import net.minecraft.entity.projectile.EntityFireball;
import net.minecraft.entity.projectile.EntityPotion;
import net.minecraft.entity.projectile.EntitySnowball;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import org.lwjgl.opengl.GL11;

public class Projectiles extends Module implements RenderListener, TickListener {
   public final ColorSetting lineColor;
   public final BooleanValue calculateColor;
   public final DoubleValue lineWidth;
   public final BlurShadowSettings blurShadowSettings;
   private final MultiOptionSetting items;
   private final java.util.ArrayList simulatedTrajectories;

   public Projectiles() {
       super("Projectiles", "Projektile", new Color(80, 107, 91), Categorys.RENDER);
      this.lineColor = new ColorSetting(this, "Color", new Color(21, 121, 230, 180));
      this.calculateColor = new BooleanValue(this, "CalculateColor", true);
      this.lineWidth = new DoubleValue(this, "LineWidth", 4.0, 1.0, 12.0, 0);
      this.blurShadowSettings = new BlurShadowSettings(this).withCustomShadowColor(Color.black).disableBothByDefault();
      this.items = new MultiOptionSetting(this, "Items", ProjectilesItemOption.values());
      this.simulatedTrajectories = new java.util.ArrayList();
   }

   @Override
   public void onEnable() {
   }

   @Override
   public void onDisable() {
      this.simulatedTrajectories.clear();
   }

   @Override
   public void updateEventSubscriptions() {
      this.toggleEventSubscription(32);
      this.toggleEventSubscription(40);
   }

   @Override
   public void onTick() {
      this.simulatedTrajectories.clear();
      double negativeinfinity = Double.NEGATIVE_INFINITY;
      simulateLoadedProjectiles(this.simulatedTrajectories, negativeinfinity);
   }

   public static void simulateLoadedProjectiles(java.util.ArrayList list, double d) {

      for (Entity entity : (Iterable<Entity>)(mc.theWorld.loadedEntityList)) {
         if (entity.ticksExisted >= 0
            && !entity.onGround
            && (
               entity instanceof EntityArrow && ProjectilesItemOption.ARROW.isSelected()
                  || entity instanceof EntitySnowball && ProjectilesItemOption.SNOW_BALL.isSelected()
                  || entity instanceof EntityEgg && ProjectilesItemOption.EGG.isSelected()
                  || entity instanceof EntityEnderPearl && ProjectilesItemOption.ENDER_PEARL.isSelected()
                  || entity instanceof EntityExpBottle && ProjectilesItemOption.XP_BOTTLE.isSelected()
                  || entity instanceof EntityPotion && ProjectilesItemOption.POTION.isSelected()
                  || entity instanceof EntityFireball && ProjectilesItemOption.FIRE_BALL.isSelected()
            )) {
            ProjectileSimulator projectileSimulator = new ProjectileSimulator();
            if (entity instanceof EntityThrowable) {
               projectileSimulator.initFromThrowable((EntityThrowable)entity);
               list.add(projectileSimulator.simulateThrowableTrajectory((short)300));
            } else if (entity instanceof EntityArrow) {
               projectileSimulator.initFromArrow((EntityArrow)entity);
               list.add(projectileSimulator.simulateArrowTrajectory((short)300));
            } else {
               boolean flag = true;
               int n = 0;
               double posX = entity.posX;
               double posY = entity.posY;
               double posZ = entity.posZ;
               double motionX = entity.motionX;
               double motionY = entity.motionY;
               double motionZ = entity.motionZ;
               float rotationYaw = entity.rotationYaw;
               float rotationPitch = entity.rotationPitch;
               float prevRotationPitch = entity.prevRotationPitch;
               float prevRotationYaw = entity.prevRotationYaw;
               java.util.ArrayList list4 = new java.util.ArrayList();
               Object obj = null;

               while (flag) {
                  if (n > 300 || posY < d) {
                     flag = false;
                  }

                  n++;
                  if (mc.theWorld.rayTraceBlocks(posX, posY, posZ, posX + motionX, posY + motionY, posZ + motionZ) != null) {
                     flag = false;
                  }

                  posX += motionX;
                  posY += motionY;
                  posZ += motionZ;
                  float f = 0.95F;
                  EntityFireball entityFireball = (EntityFireball)entity;
                  motionX += entityFireball.accelerationX;
                  motionY += entityFireball.accelerationY;
                  motionZ += entityFireball.accelerationZ;
                  motionX *= (double)f;
                  motionY *= (double)f;
                  motionZ *= (double)f;
                  list4.add(new Vec3(posX, posY, posZ));
               }

               list.add(list4);
            }
         }
      }
   }

   @Override
   public void onRender3D(float partialTicks) {
      RenderUtil.begin3DRender();
      GL11.glLineWidth((float)this.lineWidth.getValue() / 2.0F);
      this.renderTrajectories();
      GL11.glLineWidth(1.0F);
      RenderUtil.end3DRender();
   }

   public void renderTrajectories() {

      for (int i = 0; i < this.simulatedTrajectories.size(); i++) {
         java.util.ArrayList list = (java.util.ArrayList)this.simulatedTrajectories.get(i);
         if (list.size() > 1) {
            int colorInt = this.lineColor.getColorInt((byte)0);
            if (this.calculateColor.getValue()) {
               Vec3 vec = (Vec3)list.get(1);
               double d = Math.min(Math.max(mc.thePlayer.getDistance(vec.xCoord, vec.yCoord, vec.zCoord), 6.0), 36.0) - 6.0;
               float[] floats = ColorUtil.toHsb(colorInt);
               colorInt = Color.HSBtoRGB((float)(0.011415525F * d), floats[1], floats[2]);
            }

            GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
            BlurShadowManager.enableOneShotPass(this.blurShadowSettings.isBlurActive(), this.blurShadowSettings.isShadowActive());
            int n = colorInt;
            BlurShadowManager.submitRenderTask(false, shaderRenderPass -> this.renderTrajectoryPass(n, list, shaderRenderPass));
         }
      }
   }

   private void renderTrajectoryPass(int n, java.util.ArrayList list, ShaderRenderPass shaderRenderPass) {
      RenderUtil.setGlColor(this.blurShadowSettings.resolvePassColor(shaderRenderPass, ColorUtil.resolvePassColor(shaderRenderPass, n)));
      Tessellator instance = Tessellator.getInstance();
      WorldRenderer worldRenderer = instance.getWorldRenderer();
      worldRenderer.begin(3, DefaultVertexFormats.POSITION);

      for (Vec3 vec : (Iterable<Vec3>)(list)) {
         worldRenderer.pos(
               (double)((float)vec.xCoord) - mc.getRenderManager().getRenderPosX(),
               (double)((float)vec.yCoord) - mc.getRenderManager().getRenderPosY(),
               (double)((float)vec.zCoord) - mc.getRenderManager().getRenderPosZ()
            )
            .endVertex();
      }

      instance.draw();
   }

}
