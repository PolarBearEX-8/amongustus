package client.util;

import client.ClientGlobals;
import client.module.Module;
import client.render.RenderUtils;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.item.DiamondCrown;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityEnderchest;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class AdvancedResourceLocationArrayHandler extends ResourceLocationArrayHolder {
   public static final ResourceLocation resourceLocation2 = new ResourceLocation("textures/entity/chest/ender.png");
   public DiamondCrown diamondCrown = new DiamondCrown();

   @Override
   public void applyTileEntity(TileEntity tileEntity, double posX, double posY, double posZ, float factor, int count) {
      this.applyTileEntityEnderchest((TileEntityEnderchest)tileEntity, posX, posY, posZ, factor, count);
   }

   public void applyTileEntityEnderchest(TileEntityEnderchest tileEntityEnderchest, double posX, double posY, double posZ, float factor, int count) {
      int count2 = 0;
      boolean flag = true;
      if (tileEntityEnderchest.isActive()) {
         count2 = tileEntityEnderchest.getCount();
         flag = false;
      }

      if (count >= 0) {
         this.applyResourceLocation2(resourceLocationArray[count]);
         GlStateManager.matrixMode(5890);
         GlStateManager.pushMatrix();
         GlStateManager.translate3f3(4.0F, 4.0F, 1.0F);
         GlStateManager.translate(0.0625F, 0.0625F, 0.0625F);
         GlStateManager.matrixMode(5888);
      } else {
         this.applyResourceLocation2(resourceLocation2);
      }

      GlStateManager.pushMatrix();
      GlStateManager.disableLighting();
      GlStateManager.color4f2(1.0F, 1.0F, 1.0F, 1.0F);
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

      GlStateManager.rotate((float)shortValue, 0.0F, 1.0F, 0.0F);
      GlStateManager.translate(-0.5F, -0.5F, -0.5F);
      float factor2 = tileEntityEnderchest.factor + (tileEntityEnderchest.factor2 - tileEntityEnderchest.factor) * factor;
      float factor3 = 1.0F - factor2;
      float factor4 = 1.0F - factor3 * factor3 * factor3;
      this.diamondCrown.boxesManager.factor = -(factor4 * (float) Math.PI / 2.0F);
      if (!((Module)ClientGlobals.moduleManager.chams).isToggled() || !ClientGlobals.moduleManager.chams.settingChests.isEnabled() || flag) {
         this.diamondCrown.updateState();
      } else if (ClientGlobals.moduleManager.chams.settingColoredChestChams.isEnabled()) {
         if (ClientGlobals.moduleManager.chams.settingRenderTexture.isEnabled() && !ClientGlobals.moduleManager.chams.settingOnlyThroughWalls.isEnabled()) {
            this.diamondCrown.updateState();
         }

         boolean flag2 = GL11.glIsEnabled(2896);
         boolean flag3 = GL11.glIsEnabled(2929);
         boolean flag4 = GL11.glIsEnabled(3553);
         GlStateManager.disableBlend();
         GlStateManager.tryBlendFuncSeparate(770, 771, 0, 0);
         GL11.glDisable(2896);
         GL11.glDisable(2929);
         RenderUtils.setColor(ClientGlobals.moduleManager.chams.settingColorEnderChest.getInt12(0));
         GL11.glDisable(3553);
         this.diamondCrown.updateState();
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
            this.diamondCrown.updateState();
         }
      } else {
         GL11.glEnable(32823);
         GL11.glPolygonOffset(1.0F, -1100000.0F);
         this.diamondCrown.updateState();
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
