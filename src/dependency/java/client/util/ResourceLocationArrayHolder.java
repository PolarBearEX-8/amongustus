package client.util;

import client.event.PreRenderDispatcher;
import client.render.WorldRenderer;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public abstract class ResourceLocationArrayHolder implements PreRenderDispatcher {
   public static final ResourceLocation[] resourceLocationArray = new ResourceLocation[]{
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
   public StandardMapEntry standardMapEntry;
   public Class memberRef = null;
   public ResourceLocation resourceLocation = null;

   @Override
   public Class getMemberRef() {
      return this.memberRef;
   }

   public World getWorld() {
      return this.standardMapEntry.world;
   }

   @Override
   public void setResourceLocation(ResourceLocation resourceLocation2) {
      this.resourceLocation = resourceLocation2;
   }

   public void applyResourceLocation2(ResourceLocation location) {
      TextureManager textureManager = this.standardMapEntry.textureManager;
      if (textureManager != null) {
         textureManager.applyResourceLocation3(location);
      }
   }

   public abstract void applyTileEntity(TileEntity tileEntity, double posX, double posY, double posZ, float factor, int count);

   public void applyTileEntity(TileEntity tileEntity, double posX, double posY, double posZ, float factor, int count, WorldRenderer worldRenderer) {
   }

   @Override
   public void setMemberRef(Class memberRef2) {
      this.memberRef = memberRef2;
   }

   public boolean isActive() {
      return false;
   }

   public FontRenderer getFontRendererAsFontRenderer() {
      return this.standardMapEntry.getFontRendererAsFontRenderer();
   }

   @Override
   public ResourceLocation getResourceLocation() {
      return this.resourceLocation;
   }

   public void setStandardMapEntry(StandardMapEntry standardMapEntry2) {
      this.standardMapEntry = standardMapEntry2;
   }
}
