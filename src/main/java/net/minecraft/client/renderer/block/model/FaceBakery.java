package net.minecraft.client.renderer.block.model;

import net.minecraft.client.renderer.EnumFaceDirection;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.model.ModelRotation;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3i;
import net.optifine.config.Config;
import net.optifine.model.BlockModelUtils;
import net.optifine.shaders.Shaders;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;
import org.lwjgl.util.vector.Vector4f;

public class FaceBakery {
   private static final float SCALE_ROTATION_22_5 = 1.0F / (float)Math.cos((float) (Math.PI / 8)) - 1.0F;
   private static final float SCALE_ROTATION_GENERAL = 1.0F / (float)Math.cos(Math.PI / 4) - 1.0F;

   public int rotateVertex(Vector3f vector3f, EnumFacing facing, int n, ModelRotation modelRotation) {
      if (modelRotation == ModelRotation.X0_Y0) {
         return n;
      } else {
         this.rotateScale(vector3f, new Vector3f(0.5F, 0.5F, 0.5F), modelRotation.getMatrix4d(), new Vector3f(1.0F, 1.0F, 1.0F));
         return modelRotation.rotate(facing, n);
      }
   }

   private void rotateScale(Vector3f vector3f, Vector3f vector3f2, Matrix4f matrix4f, Vector3f vector3f3) {
      Vector4f vector4f = new Vector4f(vector3f.x - vector3f2.x, vector3f.y - vector3f2.y, vector3f.z - vector3f2.z, 1.0F);
      Matrix4f.transform(matrix4f, vector4f, vector4f);
      vector4f.x = vector4f.x * vector3f3.x;
      vector4f.y = vector4f.y * vector3f3.y;
      vector4f.z = vector4f.z * vector3f3.z;
      vector3f.set(vector4f.x + vector3f2.x, vector4f.y + vector3f2.y, vector4f.z + vector3f2.z);
   }

   private void storeVertexData(int[] ints, int n, int n2, Vector3f vector3f, int n3, TextureAtlasSprite textureAtlasSprite, BlockFaceUV blockFaceUV) {
      int n5 = n * (ints.length / 4);
      ints[n5] = Float.floatToRawIntBits(vector3f.x);
      ints[n5 + 1] = Float.floatToRawIntBits(vector3f.y);
      ints[n5 + 2] = Float.floatToRawIntBits(vector3f.z);
      ints[n5 + 3] = n3;
      ints[n5 + 4] = Float.floatToRawIntBits(
         textureAtlasSprite.getInterpolatedU((double)blockFaceUV.getVertexU(n2) * 0.999 + (double)blockFaceUV.getVertexU((n2 + 2) % 4) * 0.001)
      );
      ints[n5 + 4 + 1] = Float.floatToRawIntBits(
         textureAtlasSprite.getInterpolatedV((double)blockFaceUV.getVertexV(n2) * 0.999 + (double)blockFaceUV.getVertexV((n2 + 2) % 4) * 0.001)
      );
   }

   private Matrix4f getMatrixIdentity() {
      Matrix4f matrix4f = new Matrix4f();
      matrix4f.setIdentity();
      return matrix4f;
   }

   private void rotatePart(Vector3f vector3f, BlockPartRotation blockPartRotation) {
      if (blockPartRotation != null) {
         Matrix4f matrixIdentity = this.getMatrixIdentity();
         Vector3f vector3f2 = new Vector3f(0.0F, 0.0F, 0.0F);
         switch (blockPartRotation.axis) {
            case X:
               Matrix4f.rotate(blockPartRotation.angle * (float) (Math.PI / 180.0), new Vector3f(1.0F, 0.0F, 0.0F), matrixIdentity, matrixIdentity);
               vector3f2.set(0.0F, 1.0F, 1.0F);
               break;
            case Y:
               Matrix4f.rotate(blockPartRotation.angle * (float) (Math.PI / 180.0), new Vector3f(0.0F, 1.0F, 0.0F), matrixIdentity, matrixIdentity);
               vector3f2.set(1.0F, 0.0F, 1.0F);
               break;
            case Z:
               Matrix4f.rotate(blockPartRotation.angle * (float) (Math.PI / 180.0), new Vector3f(0.0F, 0.0F, 1.0F), matrixIdentity, matrixIdentity);
               vector3f2.set(1.0F, 1.0F, 0.0F);
         }

         if (blockPartRotation.rescale) {
            if (Math.abs(blockPartRotation.angle) == 22.5F) {
               vector3f2.scale(SCALE_ROTATION_22_5);
            } else {
               vector3f2.scale(SCALE_ROTATION_GENERAL);
            }

            Vector3f.add(vector3f2, new Vector3f(1.0F, 1.0F, 1.0F), vector3f2);
         } else {
            vector3f2.set(1.0F, 1.0F, 1.0F);
         }

         this.rotateScale(vector3f, new Vector3f(blockPartRotation.origin), matrixIdentity, vector3f2);
      }
   }

   private int getFaceShadeColor(EnumFacing facing) {
      int n = MathHelper.clamp_int((int)(getFaceBrightness(facing) * 255.0F), 0, 255);
      return 0xFF000000 | n << 16 | n << 8 | n;
   }

   public void lockUv(int[] ints, EnumFacing facing, BlockFaceUV blockFaceUV, TextureAtlasSprite textureAtlasSprite) {
      for (int i = 0; i < 4; i++) {
         this.lockUvVertex(i, ints, facing, blockFaceUV, textureAtlasSprite);
      }
   }

   private void lockUvVertex(int n, int[] ints, EnumFacing facing, BlockFaceUV blockFaceUV, TextureAtlasSprite textureAtlasSprite) {
      int n2 = ints.length / 4;
      int n3 = n2 * n;
      float f = Float.intBitsToFloat(ints[n3]);
      float f2 = Float.intBitsToFloat(ints[n3 + 1]);
      float f3 = Float.intBitsToFloat(ints[n3 + 2]);
      if (f < -0.1F || f >= 1.1F) {
         f -= (float)MathHelper.floor_float(f);
      }

      if (f2 < -0.1F || f2 >= 1.1F) {
         f2 -= (float)MathHelper.floor_float(f2);
      }

      if (f3 < -0.1F || f3 >= 1.1F) {
         f3 -= (float)MathHelper.floor_float(f3);
      }

      float f4 = 0.0F;
      float f5 = 0.0F;
      switch (facing) {
         case DOWN:
            f4 = f * 16.0F;
            f5 = (1.0F - f3) * 16.0F;
            break;
         case UP:
            f4 = f * 16.0F;
            f5 = f3 * 16.0F;
            break;
         case NORTH:
            f4 = (1.0F - f) * 16.0F;
            f5 = (1.0F - f2) * 16.0F;
            break;
         case SOUTH:
            f4 = f * 16.0F;
            f5 = (1.0F - f2) * 16.0F;
            break;
         case WEST:
            f4 = f3 * 16.0F;
            f5 = (1.0F - f2) * 16.0F;
            break;
         case EAST:
            f4 = (1.0F - f3) * 16.0F;
            f5 = (1.0F - f2) * 16.0F;
      }

      int n4 = blockFaceUV.getVertexRotatedRev(n) * n2;
      ints[n4 + 4] = Float.floatToRawIntBits(textureAtlasSprite.getInterpolatedU((double)f4));
      ints[n4 + 4 + 1] = Float.floatToRawIntBits(textureAtlasSprite.getInterpolatedV((double)f5));
   }

   private void applyFacing(int[] ints, EnumFacing facing) {
      int[] ints2 = new int[ints.length];
      System.arraycopy(ints, 0, ints2, 0, ints.length);
      float[] floats = new float[EnumFacing.getValues().length];
      floats[EnumFaceDirection.Constants.WEST_INDEX] = 999.0F;
      floats[EnumFaceDirection.Constants.DOWN_INDEX] = 999.0F;
      floats[EnumFaceDirection.Constants.NORTH_INDEX] = 999.0F;
      floats[EnumFaceDirection.Constants.EAST_INDEX] = -999.0F;
      floats[EnumFaceDirection.Constants.UP_INDEX] = -999.0F;
      floats[EnumFaceDirection.Constants.SOUTH_INDEX] = -999.0F;
      int n = ints.length / 4;

      for (int i = 0; i < 4; i++) {
         int n2 = n * i;
         float f = Float.intBitsToFloat(ints2[n2]);
         float f2 = Float.intBitsToFloat(ints2[n2 + 1]);
         float f3 = Float.intBitsToFloat(ints2[n2 + 2]);
         if (f < floats[EnumFaceDirection.Constants.WEST_INDEX]) {
            floats[EnumFaceDirection.Constants.WEST_INDEX] = f;
         }

         if (f2 < floats[EnumFaceDirection.Constants.DOWN_INDEX]) {
            floats[EnumFaceDirection.Constants.DOWN_INDEX] = f2;
         }

         if (f3 < floats[EnumFaceDirection.Constants.NORTH_INDEX]) {
            floats[EnumFaceDirection.Constants.NORTH_INDEX] = f3;
         }

         if (f > floats[EnumFaceDirection.Constants.EAST_INDEX]) {
            floats[EnumFaceDirection.Constants.EAST_INDEX] = f;
         }

         if (f2 > floats[EnumFaceDirection.Constants.UP_INDEX]) {
            floats[EnumFaceDirection.Constants.UP_INDEX] = f2;
         }

         if (f3 > floats[EnumFaceDirection.Constants.SOUTH_INDEX]) {
            floats[EnumFaceDirection.Constants.SOUTH_INDEX] = f3;
         }
      }

      EnumFaceDirection facing2 = EnumFaceDirection.getFacing(facing);

      for (int j = 0; j < 4; j++) {
         int n3 = n * j;
         EnumFaceDirection.VertexInformation vertexInformation = facing2.getVertexInformation(j);
         float f4 = floats[vertexInformation.xIndex];
         float f5 = floats[vertexInformation.yIndex];
         float f6 = floats[vertexInformation.zIndex];
         ints[n3] = Float.floatToRawIntBits(f4);
         ints[n3 + 1] = Float.floatToRawIntBits(f5);
         ints[n3 + 2] = Float.floatToRawIntBits(f6);

         for (int k = 0; k < 4; k++) {
            int n4 = n * k;
            float f7 = Float.intBitsToFloat(ints2[n4]);
            float f8 = Float.intBitsToFloat(ints2[n4 + 1]);
            float f9 = Float.intBitsToFloat(ints2[n4 + 2]);
            if (MathHelper.epsilonEquals(f4, f7) && MathHelper.epsilonEquals(f5, f8) && MathHelper.epsilonEquals(f6, f9)) {
               ints[n3 + 4] = ints2[n4 + 4];
               ints[n3 + 4 + 1] = ints2[n4 + 4 + 1];
            }
         }
      }
   }

   private float[] getPositionsDiv16(Vector3f vector3f, Vector3f vector3f2) {
      float[] floats = new float[EnumFacing.getValues().length];
      floats[EnumFaceDirection.Constants.WEST_INDEX] = vector3f.x / 16.0F;
      floats[EnumFaceDirection.Constants.DOWN_INDEX] = vector3f.y / 16.0F;
      floats[EnumFaceDirection.Constants.NORTH_INDEX] = vector3f.z / 16.0F;
      floats[EnumFaceDirection.Constants.EAST_INDEX] = vector3f2.x / 16.0F;
      floats[EnumFaceDirection.Constants.UP_INDEX] = vector3f2.y / 16.0F;
      floats[EnumFaceDirection.Constants.SOUTH_INDEX] = vector3f2.z / 16.0F;
      return floats;
   }

   public static EnumFacing getFacingFromVertexData(int[] ints) {
      int n = ints.length / 4;
      int n2 = n * 2;
      Vector3f vector3f = new Vector3f(Float.intBitsToFloat(ints[0]), Float.intBitsToFloat(ints[1]), Float.intBitsToFloat(ints[2]));
      Vector3f vector3f2 = new Vector3f(Float.intBitsToFloat(ints[n]), Float.intBitsToFloat(ints[n + 1]), Float.intBitsToFloat(ints[n + 2]));
      Vector3f vector3f3 = new Vector3f(Float.intBitsToFloat(ints[n2]), Float.intBitsToFloat(ints[n2 + 1]), Float.intBitsToFloat(ints[n2 + 2]));
      Vector3f vector3f4 = new Vector3f();
      Vector3f vector3f5 = new Vector3f();
      Vector3f vector3f6 = new Vector3f();
      Vector3f.sub(vector3f, vector3f2, vector3f4);
      Vector3f.sub(vector3f3, vector3f2, vector3f5);
      Vector3f.cross(vector3f5, vector3f4, vector3f6);
      float f = (float)Math.sqrt((double)(vector3f6.x * vector3f6.x + vector3f6.y * vector3f6.y + vector3f6.z * vector3f6.z));
      vector3f6.x /= f;
      vector3f6.y /= f;
      vector3f6.z /= f;
      EnumFacing facing = null;
      float f2 = 0.0F;

      for (EnumFacing facing2 : EnumFacing.getValues()) {
         Vec3i directionVec = facing2.getDirectionVec();
         float f3 = Vector3f.dot(vector3f6, new Vector3f((float)directionVec.getX(), (float)directionVec.getY(), (float)directionVec.getZ()));
         if (f3 >= 0.0F && f3 > f2) {
            f2 = f3;
            facing = facing2;
         }
      }

      return facing == null ? EnumFacing.UP : facing;
   }

   private int[] makeQuadVertexData(
      BlockPartFace blockPartFace, TextureAtlasSprite textureAtlasSprite, EnumFacing facing, float[] floats, ModelRotation modelRotation, BlockPartRotation blockPartRotation, boolean flag, boolean flag2
   ) {
      byte b = 28;
      if (Config.isShaderPackLoaded()) {
         b = 56;
      }

      int[] ints = new int[b];

      for (int i = 0; i < 4; i++) {
         this.fillVertexData(ints, i, facing, blockPartFace, floats, textureAtlasSprite, modelRotation, blockPartRotation, flag, flag2);
      }

      return ints;
   }

   public static float getFaceBrightness(EnumFacing facing) {
      switch (facing) {
         case DOWN:
            if (Config.isShaderPackLoaded()) {
               return Shaders.blockLightLevel05;
            }

            return 0.5F;
         case UP:
            return 1.0F;
         case NORTH:
         case SOUTH:
            if (Config.isShaderPackLoaded()) {
               return Shaders.blockLightLevel08;
            }

            return 0.8F;
         case WEST:
         case EAST:
            if (Config.isShaderPackLoaded()) {
               return Shaders.blockLightLevel06;
            }

            return 0.6F;
         default:
            return 1.0F;
      }
   }

   public BakedQuad makeBakedQuad(
      Vector3f vector3f,
      Vector3f vector3f2,
      BlockPartFace blockPartFace,
      TextureAtlasSprite textureAtlasSprite,
      EnumFacing facing,
      ModelRotation modelRotation,
      BlockPartRotation blockPartRotation,
      boolean flag,
      boolean flag2
   ) {
      int[] ints = this.makeQuadVertexData(blockPartFace, textureAtlasSprite, facing, this.getPositionsDiv16(vector3f, vector3f2), modelRotation, blockPartRotation, flag, flag2);
      EnumFacing facingFromVertexData = getFacingFromVertexData(ints);
      if (flag) {
         this.lockUv(ints, facingFromVertexData, blockPartFace.blockFaceUV, textureAtlasSprite);
      }

      if (blockPartRotation == null) {
         this.applyFacing(ints, facingFromVertexData);
      }

      return new BakedQuad(ints, blockPartFace.tintIndex, facingFromVertexData);
   }

   private void fillVertexData(
      int[] ints,
      int n,
      EnumFacing facing,
      BlockPartFace blockPartFace,
      float[] floats,
      TextureAtlasSprite textureAtlasSprite,
      ModelRotation modelRotation,
      BlockPartRotation blockPartRotation,
      boolean flag,
      boolean flag2
   ) {
      EnumFacing facing2 = modelRotation.rotate(facing);
      int n2 = flag2 ? this.getFaceShadeColor(facing2) : -1;
      EnumFaceDirection.VertexInformation vertexInformation = EnumFaceDirection.getFacing(facing).getVertexInformation(n);
      Vector3f vector3f = new Vector3f(floats[vertexInformation.xIndex], floats[vertexInformation.yIndex], floats[vertexInformation.zIndex]);
      this.rotatePart(vector3f, blockPartRotation);
      int n3 = this.rotateVertex(vector3f, facing, n, modelRotation);
      BlockModelUtils.snapVertexPosition(vector3f);
      this.storeVertexData(ints, n3, n, vector3f, n2, textureAtlasSprite, blockPartFace.blockFaceUV);
   }

}
