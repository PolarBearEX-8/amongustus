package net.optifine.model;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.resources.model.IBakedModel;
import net.minecraft.client.resources.model.SimpleBakedModel;
import net.minecraft.util.EnumFacing;
import net.optifine.config.Config;

public class ModelUtils {

   public static void dbgQuad(String str, BakedQuad bakedQuad, String str2) {
      Config.dbg(
         str2
            + "Quad: "
            + bakedQuad.getClass().getName()
            + ", type: "
            + str
            + ", face: "
            + bakedQuad.getFace()
            + ", tint: "
            + bakedQuad.getTintIndex()
            + ", sprite: "
            + bakedQuad.getSprite()
      );
      dbgVertexData(bakedQuad.getVertexData(), "  " + str2);
   }

   private static void dbgQuads(String str, List list, String str2) {
      for (Object obj : list) {
         dbgQuad(str, (BakedQuad)obj, str2);
      }
   }

   public static void dbgVertexData(int[] ints, String str) {
      int n = ints.length / 4;
      Config.dbg(str + "Length: " + ints.length + ", step: " + n);

      for (int i = 0; i < 4; i++) {
         int n2 = i * n;
         float f = Float.intBitsToFloat(ints[n2 + 0]);
         float f2 = Float.intBitsToFloat(ints[n2 + 1]);
         float f3 = Float.intBitsToFloat(ints[n2 + 2]);
         int n3 = ints[n2 + 3];
         float f4 = Float.intBitsToFloat(ints[n2 + 4]);
         float f5 = Float.intBitsToFloat(ints[n2 + 5]);
         Config.dbg(str + i + " xyz: " + f + "," + f2 + "," + f3 + " col: " + n3 + " u,v: " + f4 + "," + f5);
      }
   }

   public static List duplicateQuadList(List list) {
      ArrayList list2 = new ArrayList();

      for (Object obj : list) {
         list2.add(duplicateQuad((BakedQuad)obj));
      }

      return list2;
   }

   public static void dbgModel(IBakedModel bakedModel) {
      if (bakedModel != null) {
         Config.dbg(
            "Model: "
               + bakedModel
               + ", ao: "
               + bakedModel.isAmbientOcclusion()
               + ", gui3d: "
               + bakedModel.isGui3d()
               + ", builtIn: "
               + bakedModel.isBuiltInRenderer()
               + ", particle: "
               + bakedModel.getParticleTexture()
         );
         EnumFacing[] values = EnumFacing.VALUES;

         for (int i = 0; i < values.length; i++) {
            EnumFacing facing = values[i];
            List faceQuads = bakedModel.getFaceQuads(facing);
            dbgQuads(facing.getName(), faceQuads, "  ");
         }

         List generalQuads = bakedModel.getGeneralQuads();
         dbgQuads("General", generalQuads, "  ");
      }
   }

   public static BakedQuad duplicateQuad(BakedQuad bakedQuad) {
      return new BakedQuad((int[])bakedQuad.getVertexData().clone(), bakedQuad.getTintIndex(), bakedQuad.getFace(), bakedQuad.getSprite());
   }

   public static IBakedModel duplicateModel(IBakedModel bakedModel) {
      List list = duplicateQuadList(bakedModel.getGeneralQuads());
      EnumFacing[] values = EnumFacing.VALUES;
      ArrayList list2 = new ArrayList();

      for (int i = 0; i < values.length; i++) {
         list2.add(duplicateQuadList(bakedModel.getFaceQuads(values[i])));
      }

      return new SimpleBakedModel(list, list2, bakedModel.isAmbientOcclusion(), bakedModel.isGui3d(), bakedModel.getParticleTexture(), bakedModel.getItemCameraTransforms());
   }
}
