package net.minecraft.client.resources.model;

import com.google.common.collect.Maps;
import java.util.Map;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MathHelper;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;

public enum ModelRotation {
   X0_Y0(0, 0),
   X0_Y90(0, 90),
   X0_Y180(0, 180),
   X0_Y270(0, 270),
   X90_Y0(90, 0),
   X90_Y90(90, 90),
   X90_Y180(90, 180),
   X90_Y270(90, 270),
   X180_Y0(180, 0),
   X180_Y90(180, 90),
   X180_Y180(180, 180),
   X180_Y270(180, 270),
   X270_Y0(270, 0),
   X270_Y90(270, 90),
   X270_Y180(270, 180),
   X270_Y270(270, 270);

private static final Map mapRotations = Maps.newHashMap();
   private final int combinedXY;
   private final Matrix4f matrix4d;
   private final int quartersX;
   private final int quartersY;
static {
      for (ModelRotation modelRotation : values()) {
         mapRotations.put(modelRotation.combinedXY, modelRotation);
      }
   }

   private static int combineXY(int n, int n2) {
      return n * 360 + n2;
   }

   public int rotateVertex(EnumFacing facing, int n) {
      int n2 = n;
      if (facing.getAxis() == EnumFacing.Axis.X) {
         n2 = (n + this.quartersX) % 4;
      }

      EnumFacing facing2 = facing;

      for (int i = 0; i < this.quartersX; i++) {
         facing2 = facing2.rotateAround(EnumFacing.Axis.X);
      }

      if (facing2.getAxis() == EnumFacing.Axis.Y) {
         n2 = (n2 + this.quartersY) % 4;
      }

      return n2;
   }

   public static ModelRotation getModelRotation(int n, int n2) {
      return (ModelRotation)mapRotations.get(combineXY(MathHelper.normalizeAngle(n, 360), MathHelper.normalizeAngle(n2, 360)));
   }

   public int rotate(EnumFacing facing, int n) {
      return this.rotateVertex(facing, n);
   }

   public Matrix4f getMatrix4d() {
      return this.matrix4d;
   }

   private ModelRotation(int n, int n2) {
      this.combinedXY = combineXY(n, n2);
      this.matrix4d = new Matrix4f();
      Matrix4f matrix4f = new Matrix4f();
      matrix4f.setIdentity();
      Matrix4f.rotate((float)(-n) * (float) (Math.PI / 180.0), new Vector3f(1.0F, 0.0F, 0.0F), matrix4f, matrix4f);
      this.quartersX = MathHelper.abs_int(n / 90);
      Matrix4f matrix4f2 = new Matrix4f();
      matrix4f2.setIdentity();
      Matrix4f.rotate((float)(-n2) * (float) (Math.PI / 180.0), new Vector3f(0.0F, 1.0F, 0.0F), matrix4f2, matrix4f2);
      this.quartersY = MathHelper.abs_int(n2 / 90);
      Matrix4f.mul(matrix4f2, matrix4f, this.matrix4d);
   }

   public EnumFacing rotateFace(EnumFacing facing) {
      EnumFacing facing2 = facing;

      for (int i = 0; i < this.quartersX; i++) {
         facing2 = facing2.rotateAround(EnumFacing.Axis.X);
      }

      if (facing2.getAxis() != EnumFacing.Axis.Y) {
         for (int j = 0; j < this.quartersY; j++) {
            facing2 = facing2.rotateAround(EnumFacing.Axis.Y);
         }
      }

      return facing2;
   }

   public javax.vecmath.Matrix4f getMatrix() {
      return new javax.vecmath.Matrix4f();
   }

   public EnumFacing rotate(EnumFacing facing) {
      return this.rotateFace(facing);
   }
}
