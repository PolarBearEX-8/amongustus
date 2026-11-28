package net.minecraft.client.model;

import net.minecraft.util.Vec3;

public class PositionTextureVertex {
   public Vec3 vector3D;
   public float texturePositionX;
   public float texturePositionY;

   public PositionTextureVertex(Vec3 vec, float f, float f2) {
      this.vector3D = vec;
      this.texturePositionX = f;
      this.texturePositionY = f2;
   }

   public PositionTextureVertex(PositionTextureVertex positionTextureVertex, float f, float f2) {
      this.vector3D = positionTextureVertex.vector3D;
      this.texturePositionX = f;
      this.texturePositionY = f2;
   }

   public PositionTextureVertex setTexturePosition(float f, float f2) {
      return new PositionTextureVertex(this, f, f2);
   }

   public PositionTextureVertex(float f, float f2, float f3, float f4, float f5) {
      this(new Vec3((double)f, (double)f2, (double)f3), f4, f5);
   }
}
