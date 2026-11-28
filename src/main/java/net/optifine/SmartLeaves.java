package net.optifine;

import net.minecraft.block.BlockPlanks;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.BlockNewLeaf;
import net.minecraft.block.BlockOldLeaf;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.resources.model.IBakedModel;
import net.minecraft.client.resources.model.ModelManager;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.optifine.config.Config;
import net.optifine.model.ModelUtils;

public class SmartLeaves {
   private static IBakedModel modelLeavesCullAcacia = null;
   private static IBakedModel modelLeavesCullBirch = null;
   private static IBakedModel modelLeavesCullDarkOak = null;
   private static IBakedModel modelLeavesCullJungle = null;
   private static IBakedModel modelLeavesCullOak = null;
   private static IBakedModel modelLeavesCullSpruce = null;
   private static List generalQuadsCullAcacia = null;
   private static List generalQuadsCullBirch = null;
   private static List generalQuadsCullDarkOak = null;
   private static List generalQuadsCullJungle = null;
   private static List generalQuadsCullOak = null;
   private static List generalQuadsCullSpruce = null;
   private static IBakedModel modelLeavesDoubleAcacia = null;
   private static IBakedModel modelLeavesDoubleBirch = null;
   private static IBakedModel modelLeavesDoubleDarkOak = null;
   private static IBakedModel modelLeavesDoubleJungle = null;
   private static IBakedModel modelLeavesDoubleOak = null;
   private static IBakedModel modelLeavesDoubleSpruce = null;

   private static List getGeneralQuadsSafe(IBakedModel bakedModel) {
      return bakedModel == null ? null : bakedModel.getGeneralQuads();
   }

   private static IBakedModel getModelDoubleFace(IBakedModel bakedModel) {
      if (bakedModel == null) {
         return null;
      } else if (bakedModel.getGeneralQuads().size() > 0) {
         Config.warn("SmartLeaves: Model is not cube, general quads: " + bakedModel.getGeneralQuads().size() + ", model: " + bakedModel);
         return bakedModel;
      } else {
         EnumFacing[] values = EnumFacing.VALUES;

         for (int i = 0; i < values.length; i++) {
            EnumFacing facing = values[i];
            List faceQuads = bakedModel.getFaceQuads(facing);
            if (faceQuads.size() != 1) {
               Config.warn("SmartLeaves: Model is not cube, side: " + facing + ", quads: " + faceQuads.size() + ", model: " + bakedModel);
               return bakedModel;
            }
         }

         IBakedModel bakedModel2 = ModelUtils.duplicateModel(bakedModel);
         List[] lists = new List[values.length];

         for (int j = 0; j < values.length; j++) {
            List faceQuads2 = bakedModel2.getFaceQuads(values[j]);
            BakedQuad bakedQuad = (BakedQuad)faceQuads2.get(0);
            BakedQuad bakedQuad2 = new BakedQuad((int[])bakedQuad.getVertexData().clone(), bakedQuad.getTintIndex(), bakedQuad.getFace(), bakedQuad.getSprite());
            int[] vertexData = bakedQuad2.getVertexData();
            int[] ints = (int[])vertexData.clone();
            int n = vertexData.length / 4;
            System.arraycopy(vertexData, 0 * n, ints, 3 * n, n);
            System.arraycopy(vertexData, 1 * n, ints, 2 * n, n);
            System.arraycopy(vertexData, 2 * n, ints, 1 * n, n);
            System.arraycopy(vertexData, 3 * n, ints, 0 * n, n);
            System.arraycopy(ints, 0, vertexData, 0, ints.length);
            faceQuads2.add(bakedQuad2);
         }

         return bakedModel2;
      }
   }

   public static void updateLeavesModels() {
      ArrayList list = new ArrayList();
      modelLeavesCullAcacia = getModelCull("acacia", list);
      modelLeavesCullBirch = getModelCull("birch", list);
      modelLeavesCullDarkOak = getModelCull("dark_oak", list);
      modelLeavesCullJungle = getModelCull("jungle", list);
      modelLeavesCullOak = getModelCull("oak", list);
      modelLeavesCullSpruce = getModelCull("spruce", list);
      generalQuadsCullAcacia = getGeneralQuadsSafe(modelLeavesCullAcacia);
      generalQuadsCullBirch = getGeneralQuadsSafe(modelLeavesCullBirch);
      generalQuadsCullDarkOak = getGeneralQuadsSafe(modelLeavesCullDarkOak);
      generalQuadsCullJungle = getGeneralQuadsSafe(modelLeavesCullJungle);
      generalQuadsCullOak = getGeneralQuadsSafe(modelLeavesCullOak);
      generalQuadsCullSpruce = getGeneralQuadsSafe(modelLeavesCullSpruce);
      modelLeavesDoubleAcacia = getModelDoubleFace(modelLeavesCullAcacia);
      modelLeavesDoubleBirch = getModelDoubleFace(modelLeavesCullBirch);
      modelLeavesDoubleDarkOak = getModelDoubleFace(modelLeavesCullDarkOak);
      modelLeavesDoubleJungle = getModelDoubleFace(modelLeavesCullJungle);
      modelLeavesDoubleOak = getModelDoubleFace(modelLeavesCullOak);
      modelLeavesDoubleSpruce = getModelDoubleFace(modelLeavesCullSpruce);
      if (list.size() > 0) {
         Config.dbg("Enable face culling: " + Config.arrayToString3(list.toArray()));
      }
   }

   public static boolean isSameLeaves(IBlockState state, IBlockState state2) {
      if (state == state2) {
         return true;
      } else {
         Block block = state.getBlock();
         return block != state2.getBlock()
            ? false
            : (
               block instanceof BlockOldLeaf
                  ? ((BlockPlanks.EnumType)state.getValue(BlockOldLeaf.VARIANT)).equals(state2.getValue(BlockOldLeaf.VARIANT))
                  : (
                     block instanceof BlockNewLeaf
                        ? ((BlockPlanks.EnumType)state.getValue(BlockNewLeaf.VARIANT)).equals(state2.getValue(BlockNewLeaf.VARIANT))
                        : false
                  )
            );
      }
   }

   public static IBakedModel getLeavesModel(IBakedModel bakedModel, IBlockState state) {
      if (!Config.isTreesSmart()) {
         return bakedModel;
      } else {
         List generalQuads = bakedModel.getGeneralQuads();
         return generalQuads == generalQuadsCullAcacia
            ? modelLeavesDoubleAcacia
            : (generalQuads == generalQuadsCullBirch ? modelLeavesDoubleBirch : (generalQuads == generalQuadsCullDarkOak ? modelLeavesDoubleDarkOak : (generalQuads == generalQuadsCullJungle ? modelLeavesDoubleJungle : (generalQuads == generalQuadsCullOak ? modelLeavesDoubleOak : (generalQuads == generalQuadsCullSpruce ? modelLeavesDoubleSpruce : bakedModel)))));
      }
   }

   public static IBakedModel getModelCull(String str, List list) {
      ModelManager modelManager = Config.getModelManager();
      if (modelManager == null) {
         return null;
      } else {
         if (Config.getDefiningResourcePack(new ResourceLocation("blockstates/" + str + "_leaves.json")) != Config.getDefaultResourcePack()) {
            return null;
         } else {
            if (Config.getDefiningResourcePack(new ResourceLocation("models/block/" + str + "_leaves.json")) != Config.getDefaultResourcePack()) {
               return null;
            } else {
               IBakedModel model = modelManager.getModel(new ModelResourceLocation(str + "_leaves", "normal"));
               if (model != null && model != modelManager.getMissingModel()) {
                  List generalQuads = model.getGeneralQuads();
                  if (generalQuads.size() == 0) {
                     return model;
                  } else if (generalQuads.size() != 6) {
                     return null;
                  } else {
                     for (Object obj : generalQuads) {
                        BakedQuad bakedQuad = (BakedQuad)obj;
                        List faceQuads = model.getFaceQuads(bakedQuad.getFace());
                        if (faceQuads.size() > 0) {
                           return null;
                        }

                        faceQuads.add(bakedQuad);
                     }

                     generalQuads.clear();
                     list.add(str + "_leaves");
                     return model;
                  }
               } else {
                  return null;
               }
            }
         }
      }
   }
}
