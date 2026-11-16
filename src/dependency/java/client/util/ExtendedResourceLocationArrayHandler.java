package client.util;

import client.ClientGlobals;
import client.module.Module;
import client.render.RenderUtils;
import java.util.Calendar;
import net.minecraft.block.Block;
import net.minecraft.block.BlockTrappedChest;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.item.DiamondCrown;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class ExtendedResourceLocationArrayHandler extends ResourceLocationArrayHolder {
   public static final ResourceLocation resourceLocation5 = new ResourceLocation("textures/entity/chest/trapped_double.png");
   public static final ResourceLocation resourceLocation6 = new ResourceLocation("textures/entity/chest/christmas_double.png");
   public static final ResourceLocation resourceLocation4 = new ResourceLocation("textures/entity/chest/normal_double.png");
   public static final ResourceLocation resourceLocation7 = new ResourceLocation("textures/entity/chest/trapped.png");
   public static final ResourceLocation resourceLocation3 = new ResourceLocation("textures/entity/chest/christmas.png");
   public static final ResourceLocation resourceLocation2 = new ResourceLocation("textures/entity/chest/normal.png");
   public DiamondCrown diamondCrown2 = new DiamondCrown();
   public DiamondCrown diamondCrown = new AimCounter();
   public boolean enabled;

   @Override
   public void applyTileEntity(TileEntity tileEntity, double posX, double posY, double posZ, float factor, int count) {
      this.applyTileEntityChest((TileEntityChest)tileEntity, posX, posY, posZ, factor, count);
   }

   public void applyTileEntityChest(TileEntityChest tileEntityChest, double posX, double posY, double posZ, float factor, int count) {
      GlStateManager.disableDepth();
      GlStateManager.glDepthFunc(515);
      GlStateManager.depthMask(true);
      boolean flag = false;
      int count2;
      if (!tileEntityChest.isActive()) {
         count2 = 0;
         flag = true;
      } else {
         Block block = tileEntityChest.getBlock();
         count2 = tileEntityChest.getCount();
         if (block instanceof BlockTrappedChest && count2 == 0) {
            ((BlockTrappedChest)block).resolveIBlockStateByWorld(tileEntityChest.getWorld(), tileEntityChest.getBlockPos(), tileEntityChest.getWorld().getBlockState(tileEntityChest.getBlockPos()));
            count2 = tileEntityChest.getCount();
         }

         tileEntityChest.updateState6();
      }

      if (tileEntityChest.tileEntityChest2 == null && tileEntityChest.tileEntityChest == null) {
         DiamondCrown diamondCrown3;
         if (tileEntityChest.tileEntityChest3 == null && tileEntityChest.tileEntityChest1 == null) {
            diamondCrown3 = this.diamondCrown2;
            if (count >= 0) {
               this.applyResourceLocation2(resourceLocationArray[count]);
               GlStateManager.matrixMode(5890);
               GlStateManager.pushMatrix();
               GlStateManager.translate3f3(4.0F, 4.0F, 1.0F);
               GlStateManager.translate(0.0625F, 0.0625F, 0.0625F);
               GlStateManager.matrixMode(5888);
            } else if (this.enabled) {
               this.applyResourceLocation2(resourceLocation3);
            } else if (tileEntityChest.getCountAsIndex() == 1) {
               this.applyResourceLocation2(resourceLocation7);
            } else {
               this.applyResourceLocation2(resourceLocation2);
            }
         } else {
            diamondCrown3 = this.diamondCrown;
            if (count >= 0) {
               this.applyResourceLocation2(resourceLocationArray[count]);
               GlStateManager.matrixMode(5890);
               GlStateManager.pushMatrix();
               GlStateManager.translate3f3(8.0F, 4.0F, 1.0F);
               GlStateManager.translate(0.0625F, 0.0625F, 0.0625F);
               GlStateManager.matrixMode(5888);
            } else if (this.enabled) {
               this.applyResourceLocation2(resourceLocation6);
            } else if (tileEntityChest.getCountAsIndex() == 1) {
               this.applyResourceLocation2(resourceLocation5);
            } else {
               this.applyResourceLocation2(resourceLocation4);
            }
         }

         GlStateManager.pushMatrix();
         GlStateManager.disableLighting();
         if (count < 0) {
            GlStateManager.color4f2(1.0F, 1.0F, 1.0F, 1.0F);
         }

         GlStateManager.translate((float)posX, (float)posY + 1.0F, (float)posZ + 1.0F);
         GlStateManager.translate3f3(1.0F, -1.0F, -1.0F);
         GlStateManager.translate(0.5F, 0.5F, 0.5F);
         short shortValue = 0;
         if (count2 == 2) {
            shortValue = 180;
         }

         if (count2 == 3) {
            shortValue = 0;
         }

         if (count2 == 4) {
            shortValue = 90;
         }

         if (count2 == 5) {
            shortValue = -90;
         }

         if (count2 == 2 && tileEntityChest.tileEntityChest3 != null) {
            GlStateManager.translate(1.0F, 0.0F, 0.0F);
         }

         if (count2 == 5 && tileEntityChest.tileEntityChest1 != null) {
            GlStateManager.translate(0.0F, 0.0F, -1.0F);
         }

         GlStateManager.rotate((float)shortValue, 0.0F, 1.0F, 0.0F);
         GlStateManager.translate(-0.5F, -0.5F, -0.5F);
         float factor2 = tileEntityChest.factor2 + (tileEntityChest.factor - tileEntityChest.factor2) * factor;
         if (tileEntityChest.tileEntityChest2 != null) {
            float factor3 = tileEntityChest.tileEntityChest2.factor2 + (tileEntityChest.tileEntityChest2.factor - tileEntityChest.tileEntityChest2.factor2) * factor;
            if (factor3 > factor2) {
               factor2 = factor3;
            }
         }

         if (tileEntityChest.tileEntityChest != null) {
            float factor4 = tileEntityChest.tileEntityChest.factor2 + (tileEntityChest.tileEntityChest.factor - tileEntityChest.tileEntityChest.factor2) * factor;
            if (factor4 > factor2) {
               factor2 = factor4;
            }
         }

         float factor5 = 1.0F - factor2;
         float factor6 = 1.0F - factor5 * factor5 * factor5;
         diamondCrown3.boxesManager.factor = -(factor6 * (float) Math.PI / 2.0F);
         if (!((Module)ClientGlobals.moduleManager.chams).isToggled() || !ClientGlobals.moduleManager.chams.settingChests.isEnabled() || flag) {
            diamondCrown3.updateState();
         } else if (ClientGlobals.moduleManager.chams.settingColoredChestChams.isEnabled()) {
            if (ClientGlobals.moduleManager.chams.settingRenderTexture.isEnabled() && !ClientGlobals.moduleManager.chams.settingOnlyThroughWalls.isEnabled()) {
               diamondCrown3.updateState();
            }

            boolean flag2 = GL11.glIsEnabled(2896);
            boolean flag3 = GL11.glIsEnabled(2929);
            boolean flag4 = GL11.glIsEnabled(3553);
            GlStateManager.disableBlend();
            GlStateManager.tryBlendFuncSeparate(770, 771, 0, 0);
            GL11.glDisable(2896);
            GL11.glDisable(2929);
            RenderUtils.setColor(ClientGlobals.moduleManager.chams.settingColorChest.getInt12(0));
            GL11.glDisable(3553);
            diamondCrown3.updateState();
            GL11.glEnable(2929);
            if (flag2) {
               GL11.glEnable(2896);
            }

            if (!flag3) {
               GL11.glDisable(2929);
            }

            if (flag4) {
               GL11.glEnable(3553);
            }

            GlStateManager.color4f2(1.0F, 1.0F, 1.0F, 1.0F);
            if (ClientGlobals.moduleManager.chams.settingOnlyThroughWalls.isEnabled()) {
               diamondCrown3.updateState();
            }
         } else {
            GL11.glEnable(32823);
            GL11.glPolygonOffset(1.0F, -1100000.0F);
            diamondCrown3.updateState();
            GL11.glEnable(32823);
            GL11.glPolygonOffset(1.0F, 1100000.0F);
         }

         GlStateManager.applyState8();
         GlStateManager.popMatrix();
         GlStateManager.color4f2(1.0F, 1.0F, 1.0F, 1.0F);
         if (count >= 0) {
            GlStateManager.matrixMode(5890);
            GlStateManager.popMatrix();
            GlStateManager.matrixMode(5888);
         }
      }
   }

   public ExtendedResourceLocationArrayHandler() {
      Calendar instance = Calendar.getInstance();
      if (instance.get(2) + 1 == 12 && instance.get(5) >= 24 && instance.get(5) <= 26) {
         this.enabled = true;
      }
   }
}
