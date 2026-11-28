package net.minecraft.client.resources.model;

import java.util.List;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.util.EnumFacing;

public interface IBakedModel {
   boolean isAmbientOcclusion();

   List getGeneralQuads();

   boolean isBuiltInRenderer();

   List getFaceQuads(EnumFacing facing);

   boolean isGui3d();

   TextureAtlasSprite getParticleTexture();

   ItemCameraTransforms getItemCameraTransforms();
}
