package net.optifine.model;

import com.google.common.collect.ImmutableList;
import java.util.List;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.resources.model.IBakedModel;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumWorldBlockLayer;
import net.minecraft.world.IBlockAccess;
import net.optifine.BetterGrass;
import net.optifine.ConnectedTextures;
import net.optifine.NaturalTextures;
import net.optifine.SmartLeaves;
import net.optifine.config.Config;
import net.optifine.render.RenderEnv;

public class BlockModelCustomizer {
   private static final List NO_QUADS = ImmutableList.of();

   public static List getRenderQuads(List list, IBlockAccess world, IBlockState state, BlockPos pos, EnumFacing facing, EnumWorldBlockLayer enumWorldBlockLayer, long l, RenderEnv renderEnv) {
      if (facing != null) {
         if (renderEnv.isSmartLeaves() && SmartLeaves.isSameLeaves(world.getBlockState(pos.offset(facing)), state)) {
            return NO_QUADS;
         }

         if (!renderEnv.isBreakingAnimation2(list) && Config.isBetterGrass()) {
            list = BetterGrass.getFaceQuads(world, state, pos, facing, list);
         }
      }

      List listQuadsCustomizer = renderEnv.getListQuadsCustomizer();
      listQuadsCustomizer.clear();

      for (int i = 0; i < list.size(); i++) {
         BakedQuad bakedQuad = (BakedQuad)list.get(i);
         BakedQuad[] renderQuads = getRenderQuads(bakedQuad, world, state, pos, facing, l, renderEnv);
         if (i == 0 && list.size() == 1 && renderQuads.length == 1 && renderQuads[0] == bakedQuad && bakedQuad.getQuadEmissive() == null) {
            return list;
         }

         for (int j = 0; j < renderQuads.length; j++) {
            BakedQuad bakedQuad2 = renderQuads[j];
            listQuadsCustomizer.add(bakedQuad2);
            if (bakedQuad2.getQuadEmissive() != null) {
               renderEnv.getListQuadsOverlay(getOverlayLayer(enumWorldBlockLayer)).addQuad(bakedQuad2.getQuadEmissive(), state);
               renderEnv.setOverlaysRendered(true);
            }
         }
      }

      return listQuadsCustomizer;
   }

   public static IBakedModel getRenderModel(IBakedModel bakedModel, IBlockState state, RenderEnv renderEnv) {
      if (renderEnv.isSmartLeaves()) {
         bakedModel = SmartLeaves.getLeavesModel(bakedModel, state);
      }

      return bakedModel;
   }

   private static EnumWorldBlockLayer getOverlayLayer(EnumWorldBlockLayer enumWorldBlockLayer) {
      return enumWorldBlockLayer != null && enumWorldBlockLayer != EnumWorldBlockLayer.SOLID ? enumWorldBlockLayer : EnumWorldBlockLayer.CUTOUT_MIPPED;
   }

   private static BakedQuad[] getRenderQuads(BakedQuad bakedQuad, IBlockAccess world, IBlockState state, BlockPos pos, EnumFacing facing, long l, RenderEnv renderEnv) {
      if (renderEnv.isBreakingAnimation(bakedQuad)) {
         return renderEnv.getArrayQuadsCtm(bakedQuad);
      } else {
         BakedQuad bakedQuad2 = bakedQuad;
         if (Config.isConnectedTextures()) {
            BakedQuad[] connectedTexture = ConnectedTextures.getConnectedTexture(world, state, pos, bakedQuad, renderEnv);
            if (connectedTexture.length != 1 || connectedTexture[0] != bakedQuad) {
               return connectedTexture;
            }
         }

         if (Config.isNaturalTextures()) {
            bakedQuad = NaturalTextures.getNaturalTexture(pos, bakedQuad);
            if (bakedQuad != bakedQuad2) {
               return renderEnv.getArrayQuadsCtm(bakedQuad);
            }
         }

         return renderEnv.getArrayQuadsCtm(bakedQuad);
      }
   }
}
