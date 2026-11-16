package client.util;

import client.render.EntitySkinLocation;
import client.render.RenderManager;
import client.render.WorldRenderer;
import client.setting.ItemPhysicsNoneType;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.resources.model.IBakedModel;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityItem;
import net.minecraft.entity.EntityItemframe;
import net.minecraft.item.GoldenCrystal;
import net.minecraft.item.IronDust;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFilledMap;
import net.minecraft.item.ItemSkull;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class SupplementalResourceLocationEntryAdapter extends EntitySkinLocation {
   public static final ResourceLocation resourceLocation3 = new ResourceLocation("textures/map/map_background.png");
   public final Minecraft mc = Minecraft.getMinecraft();
   public final ResourceLocationAdapter resourceLocationAdapter2 = new ResourceLocationAdapter("item_frame", "normal");
   public final ResourceLocationAdapter resourceLocationAdapter = new ResourceLocationAdapter("item_frame", "map");
   public RenderItem renderItem;
   public static double value = 4096.0;

   @Override
   public void applyEntity(Entity entity, double posX, double posY, double posZ) {
      this.applyEntityItemframe((EntityItemframe)entity, posX, posY, posZ);
   }

   public void applyEntityItemframe(EntityItemframe entityItemframe, double posX, double posY, double posZ) {
      if (Minecraft.isActive2() && entityItemframe.getItemStack() != null && entityItemframe.getItemStack().isActive() && this.renderManager.entity2 == entityItemframe) {
         float factor = 1.6F;
         float factor2 = 0.016666668F * factor;
         double coordinate = entityItemframe.getCoordinate(this.renderManager.entity);
         float factor3 = entityItemframe.isSprinting() ? 32.0F : 64.0F;
         if (coordinate < (double)(factor3 * factor3)) {
            String string = entityItemframe.getItemStack().getString();
            if (entityItemframe.isSprinting()) {
               FontRenderer fontRendererAsFontRenderer = this.getFontRendererAsFontRenderer();
               GlStateManager.pushMatrix();
               GlStateManager.translate((float)posX + 0.0F, (float)posY + entityItemframe.height + 0.5F, (float)posZ);
               GL11.glNormal3f(0.0F, 1.0F, 0.0F);
               GlStateManager.rotate(-this.renderManager.scaleFactor, 0.0F, 1.0F, 0.0F);
               GlStateManager.rotate(this.renderManager.scaleFactor7, 1.0F, 0.0F, 0.0F);
               GlStateManager.translate3f3(-factor2, -factor2, factor2);
               GlStateManager.applyState3();
               GlStateManager.translate(0.0F, 0.25F / factor2, 0.0F);
               GlStateManager.depthMask(false);
               GlStateManager.disableBlend();
               GlStateManager.glStateAction3(770, 771);
               Tessellator tessellator = Tessellator.getTessellator();
               WorldRenderer worldRenderer = tessellator.getWorldRenderer();
               int count = fontRendererAsFontRenderer.getIndex(string) / 2;
               GlStateManager.enableTexture2D();
               worldRenderer.updateAuxiliaryLoggerEntry(7, DefaultVertexFormats.auxiliaryLoggerEntry3);
               worldRenderer.getWorldRenderer((double)(-count - 1), -1.0, 0.0).resolveWorldRenderer(0.0F, 0.0F, 0.0F, 0.25F).updateState();
               worldRenderer.getWorldRenderer((double)(-count - 1), 8.0, 0.0).resolveWorldRenderer(0.0F, 0.0F, 0.0F, 0.25F).updateState();
               worldRenderer.getWorldRenderer((double)(count + 1), 8.0, 0.0).resolveWorldRenderer(0.0F, 0.0F, 0.0F, 0.25F).updateState();
               worldRenderer.getWorldRenderer((double)(count + 1), -1.0, 0.0).resolveWorldRenderer(0.0F, 0.0F, 0.0F, 0.25F).updateState();
               tessellator.updateState();
               GlStateManager.disableTexture2D();
               GlStateManager.depthMask(true);
               fontRendererAsFontRenderer.getIndex(string, -fontRendererAsFontRenderer.getIndex(string) / 2, 0, 553648127);
               GlStateManager.applyState13();
               GlStateManager.enableBlend();
               GlStateManager.color4f2(1.0F, 1.0F, 1.0F, 1.0F);
               GlStateManager.popMatrix();
            } else {
               this.applyEntity(entityItemframe, string, posX, posY, posZ, 64);
            }
         }
      }
   }

   public static void updateState() {
      Minecraft mc2 = BuildManager.getMc();
      double offset = (double)BuildManager.computeFactor(mc2.gameSettings.scaleFactor100, 1.0F, 120.0F);
      double offset2 = Math.max(6.0 * (double)mc2.displayHeight / offset, 16.0);
      value = offset2 * offset2;
   }

   private void applyEntityItemframe(EntityItemframe entityItemframe) {
      ItemStack stack = entityItemframe.getItemStack();
      if (stack != null) {
         if (!this.matchesEntityItemframe(entityItemframe)) {
            return;
         }

         if (!BuildManager.enabled10) {
            EntityPlayerSP player = this.mc.thePlayer;
            if (entityItemframe.getDouble3(player.posX, player.posY, player.posZ) > 4096.0) {
               return;
            }
         }

         EntityItem entityItem = new EntityItem(entityItemframe.worldObj, 0.0, 0.0, 0.0, stack);
         Item item = entityItem.getItemStack().getItem();
         entityItem.getItemStack().animationsToGo = 1;
         entityItem.rotationPitch2 = 0.0F;
         GlStateManager.pushMatrix();
         GlStateManager.applyState3();
         int count = entityItemframe.getCount6();
         if (item instanceof ItemFilledMap) {
            count = count % 4 * 2;
         }

         GlStateManager.rotate((float)count * 360.0F / 8.0F, 0.0F, 0.0F, 1.0F);
         if (item instanceof ItemFilledMap) {
            this.renderManager.textureManager.applyResourceLocation3(resourceLocation3);
            GlStateManager.rotate(180.0F, 0.0F, 0.0F, 1.0F);
            float factor = 0.0078125F;
            GlStateManager.translate3f3(factor, factor, factor);
            GlStateManager.translate(-64.0F, -64.0F, 0.0F);
            DimensionStringEntry dimensionStringEntry = Items.filledMap.resolveDimensionStringEntryByItemStack(entityItem.getItemStack(), entityItemframe.worldObj);
            GlStateManager.translate(0.0F, 0.0F, -1.0F);
            if (dimensionStringEntry != null) {
               this.mc.entityRenderer.getTexturesMapIconsManager().applyDimensionStringEntry(dimensionStringEntry, true);
            }
         } else {
            ServiceStringEntry serviceStringEntry = null;
            if (item == Items.compass) {
               serviceStringEntry = this.mc.getListenerAdapter27().get(AuxiliaryStringEntryDispatcher.labelText4);
               this.mc.getTextureManager().applyResourceLocation3(ListenerAdapter27.resourceLocation);
               if (serviceStringEntry instanceof AuxiliaryStringEntryDispatcher) {
                  AuxiliaryStringEntryDispatcher auxiliaryStringEntryDispatcher = (AuxiliaryStringEntryDispatcher)serviceStringEntry;
                  double value2 = auxiliaryStringEntryDispatcher.value2;
                  double value3 = auxiliaryStringEntryDispatcher.value;
                  auxiliaryStringEntryDispatcher.value2 = 0.0;
                  auxiliaryStringEntryDispatcher.value = 0.0;
                  auxiliaryStringEntryDispatcher.applyWorldByWorld(
                     entityItemframe.worldObj, entityItemframe.posX, entityItemframe.posZ, (double)MathHelper.sqrt_float((float)(180 + entityItemframe.enumFacing2.getIndexOffset() * 90)), false, true
                  );
                  auxiliaryStringEntryDispatcher.value2 = value2;
                  auxiliaryStringEntryDispatcher.value = value3;
               } else {
                  serviceStringEntry = null;
               }
            }

            GlStateManager.translate3f3(0.5F, 0.5F, 0.5F);
            if (!this.renderItem.matchesItemStack(entityItem.getItemStack()) || item instanceof ItemSkull) {
               GlStateManager.rotate(180.0F, 0.0F, 1.0F, 0.0F);
            }

            GlStateManager.pushAttrib();
            RenderHelper.enableStandardItemLighting();
            this.renderItem.applyItemStack(entityItem.getItemStack(), ItemPhysicsNoneType.FIXED);
            RenderHelper.disableStandardItemLighting();
            GlStateManager.popAttrib();
            if (serviceStringEntry != null && serviceStringEntry.size() > 0) {
               serviceStringEntry.updateState3();
            }
         }

         GlStateManager.applyState13();
         GlStateManager.popMatrix();
      }
   }

   private boolean matchesEntityItemframe(EntityItemframe entityItemframe) {
      if (PreEndHandManager.enabled12) {
         return false;
      } else {
         if (!BuildManager.enabled10) {
            Entity renderViewEntity = this.mc.getRenderViewEntity();
            if (entityItemframe.getDouble3(renderViewEntity.posX, renderViewEntity.posY, renderViewEntity.posZ) > value) {
               return false;
            }
         }

         return true;
      }
   }

   public void applyEntityItemframe(EntityItemframe entityItemframe, double posX, double posY, double posZ, float factor, float factor2) {
      GlStateManager.pushMatrix();
      BlockPos blockPos = entityItemframe.getBlockPos2();
      double offset = (double)blockPos.getX() - entityItemframe.posX + posX;
      double offset2 = (double)blockPos.getY() - entityItemframe.posY + posY;
      double offset3 = (double)blockPos.getZ() - entityItemframe.posZ + posZ;
      GlStateManager.translate3d2(offset + 0.5, offset2 + 0.5, offset3 + 0.5);
      GlStateManager.rotate(180.0F - entityItemframe.rotationYaw, 0.0F, 1.0F, 0.0F);
      this.renderManager.textureManager.applyResourceLocation3(ListenerAdapter27.resourceLocation);
      GoldenCrystal goldenCrystal = this.mc.getGoldenCrystal();
      IronDust ironDust = goldenCrystal.getCommonMapEntry().getIronDust();
      IBakedModel iBakedModel;
      if (entityItemframe.getItemStack() != null && entityItemframe.getItemStack().getItem() == Items.filledMap) {
         iBakedModel = ironDust.resolveIBakedModel(this.resourceLocationAdapter);
      } else {
         iBakedModel = ironDust.resolveIBakedModel(this.resourceLocationAdapter2);
      }

      GlStateManager.pushMatrix();
      GlStateManager.translate(-0.5F, -0.5F, -0.5F);
      goldenCrystal.getSharedFloatEntry().applyIBakedModel(iBakedModel, 1.0F, 1.0F, 1.0F, 1.0F);
      GlStateManager.popMatrix();
      GlStateManager.translate(0.0F, 0.0F, 0.4375F);
      this.applyEntityItemframe(entityItemframe);
      GlStateManager.popMatrix();
      this.applyEntityItemframe(
         entityItemframe, posX + (double)((float)entityItemframe.enumFacing2.getInt2() * 0.3F), posY - 0.25, posZ + (double)((float)entityItemframe.enumFacing2.getInt() * 0.3F)
      );
   }

   @Override
   public void applyEntity(Entity entity, double posX, double posY, double posZ, float factor, float factor2) {
      this.applyEntityItemframe((EntityItemframe)entity, posX, posY, posZ, factor, factor2);
   }

   public ResourceLocation resolveResourceLocation(EntityItemframe entityItemframe) {
      return null;
   }

   @Override
   public ResourceLocation resolveResourceLocation(Entity entity) {
      return this.resolveResourceLocation((EntityItemframe)entity);
   }

   public SupplementalResourceLocationEntryAdapter(RenderManager renderManager, RenderItem renderItem2) {
      super(renderManager);
      this.renderItem = renderItem2;
   }
}
