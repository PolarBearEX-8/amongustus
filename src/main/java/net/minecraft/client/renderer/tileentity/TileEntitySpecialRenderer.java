package net.minecraft.client.renderer.tileentity;

import net.optifine.entity.model.IEntityRenderer;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public abstract class TileEntitySpecialRenderer implements IEntityRenderer {
   public static final ResourceLocation[] DESTROY_STAGES = new ResourceLocation[]{
      new ResourceLocation("textures/blocks/destroy_stage_0.png"),
      new ResourceLocation("textures/blocks/destroy_stage_1.png"),
      new ResourceLocation("textures/blocks/destroy_stage_2.png"),
      new ResourceLocation("textures/blocks/destroy_stage_3.png"),
      new ResourceLocation("textures/blocks/destroy_stage_4.png"),
      new ResourceLocation("textures/blocks/destroy_stage_5.png"),
      new ResourceLocation("textures/blocks/destroy_stage_6.png"),
      new ResourceLocation("textures/blocks/destroy_stage_7.png"),
      new ResourceLocation("textures/blocks/destroy_stage_8.png"),
      new ResourceLocation("textures/blocks/destroy_stage_9.png")
   };
   public TileEntityRendererDispatcher rendererDispatcher;
   private Class tileEntityClass = null;
   private ResourceLocation locationTextureCustom = null;

   @Override
   public void setLocationTextureCustom(ResourceLocation location) {
      this.locationTextureCustom = location;
   }

   public boolean forceTileEntityRender() {
      return false;
   }

   public World getWorld() {
      return this.rendererDispatcher.worldObj;
   }

   @Override
   public Class getEntityClass() {
      return this.tileEntityClass;
   }

   public void renderTileEntityFast(TileEntity tileEntity, double d, double d2, double d3, float f, int n, WorldRenderer worldRenderer) {
   }

   public FontRenderer getFontRenderer() {
      return this.rendererDispatcher.getFontRenderer();
   }

   public void bindTexture(ResourceLocation location) {
      TextureManager renderEngine = this.rendererDispatcher.renderEngine;
      if (renderEngine != null) {
         renderEngine.bindTexture(location);
      }
   }

   public abstract void renderTileEntityAt(TileEntity tileEntity, double d, double d2, double d3, float f, int n);

   @Override
   public ResourceLocation getLocationTextureCustom() {
      return this.locationTextureCustom;
   }

   public void setRendererDispatcher(TileEntityRendererDispatcher dispatcher) {
      this.rendererDispatcher = dispatcher;
   }

   @Override
   public void setEntityClass(Class clazz) {
      this.tileEntityClass = clazz;
   }
}
