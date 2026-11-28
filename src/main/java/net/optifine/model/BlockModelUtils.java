package net.optifine.model;

import net.minecraft.block.Block;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.BlockFaceUV;
import net.minecraft.client.renderer.block.model.BlockPartFace;
import net.minecraft.client.renderer.block.model.BlockPartRotation;
import net.minecraft.client.renderer.block.model.BreakingFour;
import net.minecraft.client.renderer.block.model.FaceBakery;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.resources.model.IBakedModel;
import net.minecraft.client.resources.model.ModelManager;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.client.resources.model.ModelRotation;
import net.minecraft.client.resources.model.SimpleBakedModel;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.optifine.config.Config;
import org.lwjgl.util.vector.Vector3f;

public class BlockModelUtils {
   private static final float VERTEX_COORD_ACCURACY = 1.0E-6F;

   public static IBakedModel makeModel2(String str, String str2, String str3) {
      TextureMap textureMapBlocks = Config.getMc().getTextureMapBlocks();
      return makeModel(str, textureMapBlocks.getSpriteSafe(str2), textureMapBlocks.getSpriteSafe(str3));
   }

   public static AxisAlignedBB getOffsetBoundingBox(AxisAlignedBB box, Block.EnumOffsetType enumOffsetType, BlockPos pos) {
      long l = (long)(pos.getX() * 3129871) ^ (long)pos.getZ() * 116129781L;
      l = l * l * 42317861L + l * 11L;
      double d = ((double)((float)(l >> 16 & 15L) / 15.0F) - 0.5) * 0.5;
      double d2 = ((double)((float)(l >> 24 & 15L) / 15.0F) - 0.5) * 0.5;
      double d3 = 0.0;
      if (enumOffsetType == Block.EnumOffsetType.XYZ) {
         d3 = ((double)((float)(l >> 20 & 15L) / 15.0F) - 1.0) * 0.2;
      }

      return box.offset(d, d3, d2);
   }

   public static IBakedModel makeModelCube2(String str, int n) {
      return makeModelCube(Config.getMc().getTextureMapBlocks().getAtlasSprite(str), n);
   }

   public static BakedQuad makeBakedQuad(EnumFacing facing, TextureAtlasSprite textureAtlasSprite, int n) {
      Vector3f vector3f = new Vector3f(0.0F, 0.0F, 0.0F);
      Vector3f vector3f2 = new Vector3f(16.0F, 16.0F, 16.0F);
      BlockFaceUV blockFaceUV = new BlockFaceUV(new float[]{0.0F, 0.0F, 16.0F, 16.0F}, 0);
      BlockPartFace blockPartFace = new BlockPartFace(facing, n, "#" + facing.getName(), blockFaceUV);
      ModelRotation x0Y0 = ModelRotation.X0_Y0;
      return new FaceBakery().makeBakedQuad(vector3f, vector3f2, blockPartFace, textureAtlasSprite, facing, x0Y0, (BlockPartRotation)null, false, true);
   }

   public static void snapVertexPosition(Vector3f vector3f) {
      vector3f.setX(snapVertexCoord(vector3f.getX()));
      vector3f.setY(snapVertexCoord(vector3f.getY()));
      vector3f.setZ(snapVertexCoord(vector3f.getZ()));
   }

   public static IBakedModel joinModelsCube(IBakedModel bakedModel, IBakedModel bakedModel2) {
      ArrayList list = new ArrayList();
      list.addAll(bakedModel.getGeneralQuads());
      list.addAll(bakedModel2.getGeneralQuads());
      EnumFacing[] values = EnumFacing.VALUES;
      ArrayList list2 = new ArrayList();

      for (int i = 0; i < values.length; i++) {
         EnumFacing facing = values[i];
         ArrayList list3 = new ArrayList();
         list3.addAll(bakedModel.getFaceQuads(facing));
         list3.addAll(bakedModel2.getFaceQuads(facing));
         list2.add(list3);
      }

      boolean isAmbientOcclusion = bakedModel.isAmbientOcclusion();
      boolean isBuiltInRenderer = bakedModel.isBuiltInRenderer();
      TextureAtlasSprite particleTexture = bakedModel.getParticleTexture();
      ItemCameraTransforms itemCameraTransforms = bakedModel.getItemCameraTransforms();
      return new SimpleBakedModel(list, list2, isAmbientOcclusion, isBuiltInRenderer, particleTexture, itemCameraTransforms);
   }

   public static IBakedModel makeModel(String str, TextureAtlasSprite textureAtlasSprite, TextureAtlasSprite textureAtlasSprite2) {
      if (textureAtlasSprite != null && textureAtlasSprite2 != null) {
         ModelManager modelManager = Config.getModelManager();
         if (modelManager == null) {
            return null;
         } else {
            IBakedModel model = modelManager.getModel(new ModelResourceLocation(str, "normal"));
            if (model != null && model != modelManager.getMissingModel()) {
               IBakedModel bakedModel = ModelUtils.duplicateModel(model);
               EnumFacing[] values = EnumFacing.VALUES;

               for (int i = 0; i < values.length; i++) {
                  replaceTexture(bakedModel.getFaceQuads(values[i]), textureAtlasSprite, textureAtlasSprite2);
               }

               replaceTexture(bakedModel.getGeneralQuads(), textureAtlasSprite, textureAtlasSprite2);
               return bakedModel;
            } else {
               return null;
            }
         }
      } else {
         return null;
      }
   }

   private static void replaceTexture(List list, TextureAtlasSprite textureAtlasSprite, TextureAtlasSprite textureAtlasSprite2) {
      ArrayList list2 = new ArrayList();

      for (Object obj : list) {
         if (((BakedQuad)obj).getSprite() == textureAtlasSprite) {
            obj = new BreakingFour((BakedQuad)obj, textureAtlasSprite2);
         }

         list2.add(obj);
      }

      list.clear();
      list.addAll(list2);
   }

   private static float snapVertexCoord(float f) {
      return f > -1.0E-6F && f < 1.0E-6F ? 0.0F : (f > 0.999999F && f < 1.000001F ? 1.0F : f);
   }

   public static IBakedModel makeModelCube(TextureAtlasSprite textureAtlasSprite, int n) {
      ArrayList list = new ArrayList();
      EnumFacing[] values = EnumFacing.VALUES;
      ArrayList list2 = new ArrayList();

      for (int i = 0; i < values.length; i++) {
         EnumFacing facing = values[i];
         ArrayList list3 = new ArrayList();
         list3.add(makeBakedQuad(facing, textureAtlasSprite, n));
         list2.add(list3);
      }

      return new SimpleBakedModel(list, list2, true, true, textureAtlasSprite, ItemCameraTransforms.DEFAULT);
   }
}
