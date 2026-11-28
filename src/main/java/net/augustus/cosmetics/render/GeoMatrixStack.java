package net.augustus.cosmetics.render;

import net.augustus.cosmetics.model.GeoBone;
import net.augustus.cosmetics.model.GeoCube;
import net.augustus.cosmetics.model.GeoMatrix3f;
import net.augustus.cosmetics.model.GeoMatrix4f;
import net.augustus.cosmetics.model.GeoQuad;
import net.augustus.cosmetics.model.GeoVector3f;
import net.augustus.cosmetics.model.GeoVertex;
import java.util.Stack;

public class GeoMatrixStack implements MatrixTransformBridge {
   private GeoMatrix3f tempNormalMatrix;
   private GeoMatrix4f tempModelMatrix;
   private Stack modelStack = new Stack();
   private GeoMatrix4f tempMulMatrix4f;
   private GeoMatrix3f tempMulMatrix3f;
   private Stack normalStack = new Stack();

   public void transformNormal(GeoQuad geoQuad) {
      GeoMatrix3f geoMatrix3f = (GeoMatrix3f)this.normalStack.peek();
      geoQuad.transformedNormal.x = geoMatrix3f.m00 * (float)geoQuad.normal.getX() + geoMatrix3f.m01 * (float)geoQuad.normal.getY() + geoMatrix3f.m02 * (float)geoQuad.normal.getZ();
      geoQuad.transformedNormal.y = geoMatrix3f.m10 * (float)geoQuad.normal.getX() + geoMatrix3f.m11 * (float)geoQuad.normal.getY() + geoMatrix3f.m12 * (float)geoQuad.normal.getZ();
      geoQuad.transformedNormal.z = geoMatrix3f.m20 * (float)geoQuad.normal.getX() + geoMatrix3f.m21 * (float)geoQuad.normal.getY() + geoMatrix3f.m22 * (float)geoQuad.normal.getZ();
   }

   public void push() {
      this.modelStack.add(new GeoMatrix4f((GeoMatrix4f)this.modelStack.peek()));
      this.normalStack.add(new GeoMatrix3f((GeoMatrix3f)this.normalStack.peek()));
   }

   public void moveToPivot(GeoBone geoBone) {
      this.translate(geoBone.rotationPointX / 16.0F, geoBone.rotationPointY / 16.0F, geoBone.rotationPointZ / 16.0F);
   }

   public void moveBackFromPivot(GeoBone geoBone) {
      this.translate(-geoBone.rotationPointX / 16.0F, -geoBone.rotationPointY / 16.0F, -geoBone.rotationPointZ / 16.0F);
   }

   public void pop() {
      if (this.modelStack.size() == 1) {
         throw new IllegalStateException("A one level stack can't be popped!");
      } else {
         this.modelStack.pop();
         this.normalStack.pop();
      }
   }

   public void scale(GeoBone geoBone) {
      float scaleX = geoBone.getScaleX();
      float scaleZ = geoBone.getScaleZ();
      this.scale(scaleX, geoBone.getScaleY(), scaleZ);
   }

   public void moveBackFromPivot(GeoCube geoCube) {
      GeoVector3f pivot = geoCube.pivot;
      float f = -pivot.getX() / 16.0F;
      float f2 = -pivot.getZ() / 16.0F;
      this.translate(f, -pivot.getY() / 16.0F, f2);
   }

   public void rotate(GeoBone geoBone) {
      if (geoBone.getRotationZ() != 0.0F) {
         this.rotateZ(geoBone.getRotationZ());
      }

      if (geoBone.getRotationY() != 0.0F) {
         this.rotateY(geoBone.getRotationY());
      }

      if (geoBone.getRotationX() != 0.0F) {
         this.rotateX(geoBone.getRotationX());
      }
   }

   public GeoMatrixStack() {
      this.tempModelMatrix = new GeoMatrix4f();
      this.tempNormalMatrix = new GeoMatrix3f();
      this.tempMulMatrix4f = new GeoMatrix4f();
      this.tempMulMatrix3f = new GeoMatrix3f();
      GeoMatrix4f geoMatrix4f = new GeoMatrix4f();
      GeoMatrix3f geoMatrix3f = new GeoMatrix3f();
      geoMatrix4f.setIdentity();
      geoMatrix3f.setIdentity();
      this.modelStack.add(geoMatrix4f);
      this.normalStack.add(geoMatrix3f);
   }

   public void rotate(GeoCube geoCube) {
      GeoVector3f rotation = geoCube.rotation;
      this.tempModelMatrix.setIdentity();
      GeoMatrix4f tempMulMatrix4f2 = this.tempMulMatrix4f;
      tempMulMatrix4f2.rotZ(rotation.getZ());
      GeoMatrix4f tempMulMatrix4f3 = this.tempMulMatrix4f;
      this.tempModelMatrix.mul(tempMulMatrix4f3);
      tempMulMatrix4f2 = this.tempMulMatrix4f;
      tempMulMatrix4f2.rotY(rotation.getY());
      GeoMatrix4f tempMulMatrix4f4 = this.tempMulMatrix4f;
      this.tempModelMatrix.mul(tempMulMatrix4f4);
      tempMulMatrix4f2 = this.tempMulMatrix4f;
      tempMulMatrix4f2.rotX(rotation.getX());
      GeoMatrix4f tempMulMatrix4f5 = this.tempMulMatrix4f;
      this.tempModelMatrix.mul(tempMulMatrix4f5);
      this.tempNormalMatrix.setIdentity();
      this.tempMulMatrix3f.rotZ(rotation.getZ());
      GeoMatrix3f tempMulMatrix3f3 = this.tempMulMatrix3f;
      this.tempNormalMatrix.mul(tempMulMatrix3f3);
      this.tempMulMatrix3f.rotY(rotation.getY());
      GeoMatrix3f tempMulMatrix3f5 = this.tempMulMatrix3f;
      this.tempNormalMatrix.mul(tempMulMatrix3f5);
      this.tempMulMatrix3f.rotX(rotation.getX());
      GeoMatrix3f tempMulMatrix3f7 = this.tempMulMatrix3f;
      this.tempNormalMatrix.mul(tempMulMatrix3f7);
      tempMulMatrix4f2 = (GeoMatrix4f)this.modelStack.peek();
      tempMulMatrix4f2.mul(this.tempModelMatrix);
      ((GeoMatrix3f)this.normalStack.peek()).mul(this.tempNormalMatrix);
   }

   public void rotateZ(float f) {
      this.tempModelMatrix.setIdentity();
      this.tempModelMatrix.rotZ(f);
      this.tempNormalMatrix.setIdentity();
      this.tempNormalMatrix.rotZ(f);
      ((GeoMatrix4f)this.modelStack.peek()).mul(this.tempModelMatrix);
      ((GeoMatrix3f)this.normalStack.peek()).mul(this.tempNormalMatrix);
   }

   public void scale(float f, float f2, float f3) {
      this.tempModelMatrix.setIdentity();
      this.tempModelMatrix.setM00(f);
      this.tempModelMatrix.setM11(f2);
      this.tempModelMatrix.setM22(f3);
      ((GeoMatrix4f)this.modelStack.peek()).mul(this.tempModelMatrix);
      if (f < 0.0F || f2 < 0.0F || f3 < 0.0F) {
         this.tempNormalMatrix.setIdentity();
         float f4 = f < 0.0F ? -1.0F : 1.0F;
         this.tempNormalMatrix.setM00(f4);
         float f5 = f2 < 0.0F ? -1.0F : 1.0F;
         this.tempNormalMatrix.setM11(f5);
         float f6 = f3 < 0.0F ? -1.0F : 1.0F;
         this.tempNormalMatrix.setM22(f6);
         ((GeoMatrix3f)this.normalStack.peek()).mul(this.tempNormalMatrix);
      }
   }

   public void rotate(float f, float f2, float f3) {
      if (f3 != 0.0F) {
         this.rotateZ(f);
      }

      if (f2 != 0.0F) {
         this.rotateY(f);
      }

      if (f != 0.0F) {
         this.rotateX(f);
      }
   }

   public GeoMatrix4f getModelMatrix() {
      return (GeoMatrix4f)this.modelStack.peek();
   }

   public void moveToPivot(GeoCube geoCube) {
      GeoVector3f pivot = geoCube.pivot;
      float f = pivot.getX() / 16.0F;
      float f2 = pivot.getZ() / 16.0F;
      this.translate(f, pivot.getY() / 16.0F, f2);
   }

   public void transformVertex(GeoVertex geoVertex) {
      GeoMatrix4f geoMatrix4f = (GeoMatrix4f)this.modelStack.peek();
      geoVertex.transformedPosition.x = geoMatrix4f.m00 * geoVertex.position.getX() + geoMatrix4f.m01 * geoVertex.position.getY() + geoMatrix4f.m02 * geoVertex.position.getZ() + geoMatrix4f.m03;
      geoVertex.transformedPosition.y = geoMatrix4f.m10 * geoVertex.position.getX() + geoMatrix4f.m11 * geoVertex.position.getY() + geoMatrix4f.m12 * geoVertex.position.getZ() + geoMatrix4f.m13;
      geoVertex.transformedPosition.z = geoMatrix4f.m20 * geoVertex.position.getX() + geoMatrix4f.m21 * geoVertex.position.getY() + geoMatrix4f.m22 * geoVertex.position.getZ() + geoMatrix4f.m23;
   }

   public GeoMatrix3f getNormalMatrix() {
      return (GeoMatrix3f)this.normalStack.peek();
   }

   public void translate(GeoBone geoBone) {
      float f = -geoBone.getPositionX() / 16.0F;
      float f2 = geoBone.getPositionZ() / 16.0F;
      this.translate(f, geoBone.getPositionY() / 16.0F, f2);
   }

   public void rotateX(float f) {
      this.tempModelMatrix.setIdentity();
      this.tempModelMatrix.rotX(f);
      this.tempNormalMatrix.setIdentity();
      this.tempNormalMatrix.rotX(f);
      ((GeoMatrix4f)this.modelStack.peek()).mul(this.tempModelMatrix);
      ((GeoMatrix3f)this.normalStack.peek()).mul(this.tempNormalMatrix);
   }

   public void rotateY(float f) {
      this.tempModelMatrix.setIdentity();
      this.tempModelMatrix.rotY(f);
      this.tempNormalMatrix.setIdentity();
      this.tempNormalMatrix.rotY(f);
      ((GeoMatrix4f)this.modelStack.peek()).mul(this.tempModelMatrix);
      ((GeoMatrix3f)this.normalStack.peek()).mul(this.tempNormalMatrix);
   }

   public void translate(float f, float f2, float f3) {
      this.tempModelMatrix.setIdentity();
      this.tempModelMatrix.m03 = f;
      this.tempModelMatrix.m13 = f2;
      this.tempModelMatrix.m23 = f3;
      ((GeoMatrix4f)this.modelStack.peek()).mul(this.tempModelMatrix);
   }

}
