package net.augustus.modules.render;

import java.awt.Color;
import net.augustus.modules.Categorys;
import net.augustus.modules.Module;
import net.augustus.modules.combat.BowAimbot;
import net.augustus.modules.legit.AutoRod;
import net.augustus.modules.legit.AutoRodNew;
import net.augustus.utils.render.ShaderRenderPass;
import net.augustus.events.listeners.RenderListener;
import net.augustus.settings.BlurShadowSettings;
import net.augustus.settings.options.TrajectoriesItemOption;
import net.augustus.ClientManagers;
import net.augustus.settings.MultiOptionSetting;
import net.augustus.settings.ColorSetting;
import net.augustus.utils.rotation.RayTraceUtil;
import net.augustus.render.RenderUtil;
import net.augustus.utils.render.ColorUtil;
import net.augustus.utils.render.BlurShadowManager;
import net.augustus.utils.simulation.ProjectileSimulator;
import net.augustus.settings.SettingVisibilityCondition;
import net.augustus.settings.BooleanValue;
import net.augustus.settings.DoubleValue;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.Entity;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemEgg;
import net.minecraft.item.ItemEnderPearl;
import net.minecraft.item.ItemFireball;
import net.minecraft.item.ItemFishingRod;
import net.minecraft.item.ItemPotion;
import net.minecraft.item.ItemSnowball;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import org.lwjgl.opengl.GL11;

public class Trajectories extends Module implements RenderListener {
   public final BooleanValue calculateColor;
   public final ColorSetting color2;
   public final DoubleValue lineWidth;
   public final BooleanValue noLine;
   public final BlurShadowSettings blurShadowSettings;
   private final MultiOptionSetting items;
   private java.util.ArrayList trajectoryPoints;

   public Trajectories() {
       super("Trajectories", "Flugbahnen", new Color(29, 202, 103), Categorys.RENDER);
      this.calculateColor = new BooleanValue(this, "CalculateColor", false);
      ColorSetting colorSetting = new ColorSetting(this, "Color", new Color(21, 121, 230));
      SettingVisibilityCondition condition = () -> {
         return !this.calculateColor.getValue();
      };
      this.color2 = (ColorSetting)colorSetting.withVisibility(condition);
      this.lineWidth = new DoubleValue(this, "LineWidth", 5.0, 1.0, 12.0, 0);
      this.noLine = new BooleanValue(this, "NoLine", false);
      this.blurShadowSettings = new BlurShadowSettings(this).withCustomShadowColor(Color.black).disableBothByDefault();
      this.items = new MultiOptionSetting(this, "Items", TrajectoriesItemOption.values());
      this.trajectoryPoints = new java.util.ArrayList();
   }

   @Override
   public void onEnable() {
   }

   @Override
   public void onDisable() {
      this.trajectoryPoints.clear();
   }

   @Override
   public void updateEventSubscriptions() {
      this.toggleEventSubscription(32);
   }

   @Override
   public void onRender3D(float partialTicks) {
      this.trajectoryPoints.clear();
      ItemStack currentEquippedItem = mc.thePlayer.getCurrentEquippedItem();
      MovingObjectPosition hit = null;
      if (currentEquippedItem != null) {
         Item item = currentEquippedItem.getItem();
         if (item instanceof ItemSnowball && TrajectoriesItemOption.SNOW_BALL.isSelected()
            || item instanceof ItemEgg && TrajectoriesItemOption.EGG.isSelected()
            || item instanceof ItemBow && TrajectoriesItemOption.BOW.isSelected()
            || item instanceof ItemEnderPearl && TrajectoriesItemOption.ENDER_PEARL.isSelected()
            || item.equals(Items.experience_bottle) && TrajectoriesItemOption.XP_BOTTLE.isSelected()
            || item.equals(Items.potionitem) && ItemPotion.isSplash(currentEquippedItem.getMetadata()) && TrajectoriesItemOption.POTION.isSelected()
            || item instanceof ItemFishingRod && TrajectoriesItemOption.ROD.isSelected()
            || item instanceof ItemFireball && TrajectoriesItemOption.FIREBALL.isSelected()) {
            ProjectileSimulator projectileSimulator = new ProjectileSimulator();
            projectileSimulator.initFromShooter(mc.thePlayer, currentEquippedItem);
            if (item instanceof ItemBow) {
               this.trajectoryPoints = projectileSimulator.simulateArrowTrajectory((short)300);
            } else if (item instanceof ItemFishingRod) {
               this.trajectoryPoints = projectileSimulator.simulateFishingHookTrajectory((short)300);
            } else if (!(item instanceof ItemFireball)) {
               this.trajectoryPoints = projectileSimulator.simulateThrowableTrajectory((short)300);
            } else {
               double d;
               double d2;
               double d3;
               float f2;
               this.trajectoryPoints = new java.util.ArrayList();
               d = mc.thePlayer.lastTickPosX + (mc.thePlayer.posX - mc.thePlayer.lastTickPosX) * (double)mc.getTimer().renderPartialTicks;
               d2 = mc.thePlayer.lastTickPosY + (double)mc.thePlayer.getEyeHeight() + (mc.thePlayer.posY - mc.thePlayer.lastTickPosY) * (double)mc.getTimer().renderPartialTicks;
               d3 = mc.thePlayer.lastTickPosZ + (mc.thePlayer.posZ - mc.thePlayer.lastTickPosZ) * (double)mc.getTimer().renderPartialTicks;
               f2 = mc.thePlayer.prevRotationYaw + (mc.thePlayer.rotationYaw - mc.thePlayer.prevRotationYaw) * mc.getTimer().renderPartialTicks;
               boolean useControllerYaw =
                  (ClientManagers.moduleManager.bowAimbot.isToggled() && ClientManagers.moduleManager.bowAimbot.getTarget() != null && ClientManagers.rotationController.isOwnedBy(ClientManagers.moduleManager.bowAimbot))
                  || (ClientManagers.moduleManager.autoRod.isToggled() && ClientManagers.moduleManager.autoRod.getTarget() != null && ClientManagers.rotationController.isOwnedBy(ClientManagers.moduleManager.autoRod))
                  || (ClientManagers.moduleManager.autoRodNew.isToggled() && ClientManagers.moduleManager.autoRodNew.getTarget() != null && ClientManagers.rotationController.isOwnedBy(ClientManagers.moduleManager.autoRodNew));
               if (useControllerYaw) {
                  f2 = ClientManagers.rotationController.getPreviousRotation().yaw + (ClientManagers.rotationController.getCurrentRotation().yaw - ClientManagers.rotationController.getPreviousRotation().yaw) * mc.getTimer().renderPartialTicks;
               }

               d += (double)(MathHelper.cos(f2 / 180.0F * (float) Math.PI) * 0.16F * -1.0F);
               d2 -= 0.1F;
               d3 += (double)(MathHelper.sin(f2 / 180.0F * (float) Math.PI) * 0.16F * -1.0F);
               this.trajectoryPoints.add(new Vec3(d, d2, d3));
               MovingObjectPosition hit2 = RayTraceUtil.rayTrace(
                  partialTicks, mc.thePlayer.rotationYaw, mc.thePlayer.rotationPitch, 200.0, 200.0, 0.0
               );
               if (hit2 != null && hit2.typeOfHit != MovingObjectPosition.MovingObjectType.MISS && hit2.hitVec != null) {
                  this.trajectoryPoints.add(hit2.hitVec);
               } else {
                  this.trajectoryPoints.add(mc.thePlayer.getInterpolatedPos(partialTicks).add(mc.thePlayer.getLook(partialTicks).scale(200.0)));
               }

               projectileSimulator = null;
               hit = hit2;
            }

            if (projectileSimulator != null) {
               hit = projectileSimulator.getHitResult();
            }

            if (this.trajectoryPoints.size() > 1) {
               this.renderTrajectory(hit);
            }
         }
      }
   }

   public void renderTrajectory(MovingObjectPosition hit) {
      RenderUtil.begin3DRender();
      GL11.glLineWidth((float)this.lineWidth.getValue() / 2.0F);
      GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
      int n;
      if (this.calculateColor.getValue()) {
         Vec3 vec = (Vec3)this.trajectoryPoints.get(this.trajectoryPoints.size() - 1);
         double d = Math.min(Math.max(mc.thePlayer.getDistance(vec.xCoord, vec.yCoord, vec.zCoord), 6.0), 36.0) - 6.0;
         float[] floats = ColorUtil.toHsb(this.color2.getColorInt((byte)0));
         n = Color.HSBtoRGB((float)(0.011415525F * d), floats[1], floats[2]);
      } else {
         n = this.color2.getColorInt((byte)0);
      }

      BlurShadowManager.enableOneShotPass(this.blurShadowSettings.isBlurActive(), this.blurShadowSettings.isShadowActive());
      int n2 = n;
      BlurShadowManager.submitRenderTask(false, shaderRenderPass -> this.renderTrajectoryPass(n2, hit, shaderRenderPass));
      GL11.glLineWidth(1.0F);
      GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
      RenderUtil.end3DRender();
   }

   private void renderBlockImpactMarker(WorldRenderer worldRenderer, int n, MovingObjectPosition hit, Tessellator tessellator) {
      RenderUtil.setGlColorWithAlpha(n, 0.3F);
      Vec3 hitVec = hit.hitVec;
      EnumFacing sideHit = hit.sideHit;
      float f = (float)(hitVec.xCoord - mc.getRenderManager().getRenderPosX());
      float f2 = (float)(hitVec.xCoord - mc.getRenderManager().getRenderPosX());
      float f3 = (float)(hitVec.yCoord - mc.getRenderManager().getRenderPosY());
      float f4 = (float)(hitVec.yCoord - mc.getRenderManager().getRenderPosY());
      float f5 = (float)(hitVec.zCoord - mc.getRenderManager().getRenderPosZ());
      float f6 = (float)(hitVec.zCoord - mc.getRenderManager().getRenderPosZ());
      if (sideHit == EnumFacing.SOUTH) {
         f -= 0.4F;
         f2 += 0.4F;
         f3 -= 0.4F;
         f4 += 0.4F;
         f6 += 0.02F;
         f5 += 0.05F;
      } else if (sideHit == EnumFacing.NORTH) {
         f -= 0.4F;
         f2 += 0.4F;
         f3 -= 0.4F;
         f4 += 0.4F;
         f6 -= 0.02F;
         f5 -= 0.05F;
      } else if (sideHit == EnumFacing.EAST) {
         f2 += 0.02F;
         f += 0.05F;
         f3 -= 0.4F;
         f4 += 0.4F;
         f5 -= 0.4F;
         f6 += 0.4F;
      } else if (sideHit == EnumFacing.WEST) {
         f2 -= 0.02F;
         f -= 0.05F;
         f3 -= 0.4F;
         f4 += 0.4F;
         f5 -= 0.4F;
         f6 += 0.4F;
      } else if (sideHit == EnumFacing.UP) {
         f -= 0.4F;
         f2 += 0.4F;
         f4 += 0.02F;
         f3 += 0.05F;
         f5 -= 0.4F;
         f6 += 0.4F;
      } else if (sideHit == EnumFacing.DOWN) {
         f -= 0.4F;
         f2 += 0.4F;
         f4 -= 0.02F;
         f3 -= 0.05F;
         f5 -= 0.4F;
         f6 += 0.4F;
      }

      worldRenderer.begin(7, DefaultVertexFormats.POSITION);
      worldRenderer.pos((double)f, (double)f3, (double)f5).endVertex();
      worldRenderer.pos((double)f, (double)f3, (double)f6).endVertex();
      worldRenderer.pos((double)f, (double)f4, (double)f6).endVertex();
      worldRenderer.pos((double)f, (double)f4, (double)f5).endVertex();
      worldRenderer.pos((double)f, (double)f3, (double)f6).endVertex();
      worldRenderer.pos((double)f2, (double)f3, (double)f6).endVertex();
      worldRenderer.pos((double)f2, (double)f4, (double)f6).endVertex();
      worldRenderer.pos((double)f, (double)f4, (double)f6).endVertex();
      worldRenderer.pos((double)f2, (double)f3, (double)f6).endVertex();
      worldRenderer.pos((double)f2, (double)f3, (double)f5).endVertex();
      worldRenderer.pos((double)f2, (double)f4, (double)f5).endVertex();
      worldRenderer.pos((double)f2, (double)f4, (double)f6).endVertex();
      worldRenderer.pos((double)f2, (double)f3, (double)f5).endVertex();
      worldRenderer.pos((double)f, (double)f3, (double)f5).endVertex();
      worldRenderer.pos((double)f, (double)f4, (double)f5).endVertex();
      worldRenderer.pos((double)f2, (double)f4, (double)f5).endVertex();
      worldRenderer.pos((double)f, (double)f3, (double)f5).endVertex();
      worldRenderer.pos((double)f, (double)f3, (double)f6).endVertex();
      worldRenderer.pos((double)f2, (double)f3, (double)f6).endVertex();
      worldRenderer.pos((double)f2, (double)f3, (double)f5).endVertex();
      worldRenderer.pos((double)f, (double)f4, (double)f5).endVertex();
      worldRenderer.pos((double)f, (double)f4, (double)f6).endVertex();
      worldRenderer.pos((double)f2, (double)f4, (double)f6).endVertex();
      worldRenderer.pos((double)f2, (double)f4, (double)f5).endVertex();
      worldRenderer.endVertex();
      tessellator.draw();
      GL11.glLineWidth(2.0F);
      worldRenderer.begin(3, DefaultVertexFormats.POSITION);
      worldRenderer.pos((double)f, (double)f3, (double)f5).endVertex();
      worldRenderer.pos((double)f, (double)f3, (double)f6).endVertex();
      worldRenderer.pos((double)f, (double)f4, (double)f6).endVertex();
      worldRenderer.pos((double)f, (double)f4, (double)f5).endVertex();
      worldRenderer.pos((double)f, (double)f3, (double)f5).endVertex();
      worldRenderer.pos((double)f2, (double)f3, (double)f5).endVertex();
      worldRenderer.pos((double)f2, (double)f4, (double)f5).endVertex();
      worldRenderer.pos((double)f2, (double)f4, (double)f6).endVertex();
      worldRenderer.pos((double)f2, (double)f3, (double)f6).endVertex();
      worldRenderer.pos((double)f2, (double)f3, (double)f5).endVertex();
      worldRenderer.pos((double)f2, (double)f3, (double)f6).endVertex();
      worldRenderer.pos((double)f, (double)f3, (double)f6).endVertex();
      worldRenderer.pos((double)f, (double)f4, (double)f6).endVertex();
      worldRenderer.pos((double)f2, (double)f4, (double)f6).endVertex();
      worldRenderer.pos((double)f2, (double)f4, (double)f5).endVertex();
      worldRenderer.pos((double)f, (double)f4, (double)f5).endVertex();
      worldRenderer.endVertex();
      tessellator.draw();
   }

   private void renderTrajectoryPass(int n, MovingObjectPosition hit, ShaderRenderPass shaderRenderPass) {
      Tessellator instance = Tessellator.getInstance();
      WorldRenderer worldRenderer = instance.getWorldRenderer();
      int n3 = this.blurShadowSettings.resolvePassColor(shaderRenderPass, ColorUtil.resolvePassColor(shaderRenderPass, n));
      RenderUtil.setGlColorWithAlpha(n3, 0.85F);
      if (!this.noLine.getValue()) {
         worldRenderer.begin(3, DefaultVertexFormats.POSITION);

         for (Vec3 vec : (Iterable<Vec3>)(this.trajectoryPoints)) {
            worldRenderer.pos(
                  (double)((float)vec.xCoord) - mc.getRenderManager().getRenderPosX(),
                  (double)((float)vec.yCoord) - mc.getRenderManager().getRenderPosY(),
                  (double)((float)vec.zCoord) - mc.getRenderManager().getRenderPosZ()
               )
               .endVertex();
         }

         instance.draw();
      }

      if (hit != null) {
         if (hit.typeOfHit == MovingObjectPosition.MovingObjectType.ENTITY) {
            Entity entityHit = hit.entityHit;
            float f = (float)(entityHit.lastTickPosX + (entityHit.posX - entityHit.lastTickPosX) * (double)mc.getTimer().renderPartialTicks - mc.getRenderManager().getRenderPosX());
            float f2 = (float)(entityHit.lastTickPosY + (entityHit.posY - entityHit.lastTickPosY) * (double)mc.getTimer().renderPartialTicks - mc.getRenderManager().getRenderPosY());
            float f3 = (float)(entityHit.lastTickPosZ + (entityHit.posZ - entityHit.lastTickPosZ) * (double)mc.getTimer().renderPartialTicks - mc.getRenderManager().getRenderPosZ());
            RenderUtil.setGlColorWithAlpha(n3, 0.3F);
            double d = (double)(entityHit.width / 1.5F);
            RenderUtil.drawRotatedEntityBox(entityHit, f, f2, f3, d, (double)entityHit.height);
            GL11.glLineWidth(2.5F);
            RenderUtil.setGlColorWithAlpha(n3, 1.0F);
            d = (double)(entityHit.width / 1.5F);
            RenderUtil.drawRotatedEntityBoxOutline(entityHit, f, f2, f3, d, (double)entityHit.height);
            GL11.glLineWidth(1.0F);
         } else {
            this.renderBlockImpactMarker(worldRenderer, n3, hit, instance);
         }
      }
   }

}
