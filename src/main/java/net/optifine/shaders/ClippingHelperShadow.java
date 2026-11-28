package net.optifine.shaders;

import net.minecraft.client.renderer.culling.ClippingHelper;
import net.minecraft.util.MathHelper;

public class ClippingHelperShadow extends ClippingHelper {
   private static ClippingHelperShadow instance = new ClippingHelperShadow();
   public float[] frustumTest = new float[6];
   public float[][] shadowClipPlanes = new float[10][4];
   public int shadowClipPlaneCount;
   public float[] matInvMP = new float[16];
   public float[] vecIntersection = new float[4];

   public static ClippingHelper getInstance() {
      instance.init();
      return instance;
   }

   private double dot4(float[] floats, double d, double d2, double d3) {
      return (double)floats[0] * d + (double)floats[1] * d2 + (double)floats[2] * d3 + (double)floats[3];
   }

   private void assignPlane(float[] floats, float f, float f2, float f3, float f4) {
      float f5 = (float)Math.sqrt((double)(f * f + f2 * f2 + f3 * f3));
      floats[0] = f / f5;
      floats[1] = f2 / f5;
      floats[2] = f3 / f5;
      floats[3] = f4 / f5;
   }

   private void copyPlane(float[] floats, float[] floats2) {
      floats[0] = floats2[0];
      floats[1] = floats2[1];
      floats[2] = floats2[2];
      floats[3] = floats2[3];
   }

   private float distance(float f, float f2, float f3, float f4, float f5, float f6) {
      return this.length(f - f4, f2 - f5, f3 - f6);
   }

   @Override
   public boolean isBoxInFrustum(double d, double d2, double d3, double d4, double d5, double d6) {
      for (int i = 0; i < this.shadowClipPlaneCount; i++) {
         float[] floats = this.shadowClipPlanes[i];
         if (this.dot4(floats, d, d2, d3) <= 0.0
            && this.dot4(floats, d4, d2, d3) <= 0.0
            && this.dot4(floats, d, d5, d3) <= 0.0
            && this.dot4(floats, d4, d5, d3) <= 0.0
            && this.dot4(floats, d, d2, d6) <= 0.0
            && this.dot4(floats, d4, d2, d6) <= 0.0
            && this.dot4(floats, d, d5, d6) <= 0.0
            && this.dot4(floats, d4, d5, d6) <= 0.0) {
            return false;
         }
      }

      return true;
   }

   private void cross3(float[] floats, float[] floats2, float[] floats3) {
      floats[0] = floats2[1] * floats3[2] - floats2[2] * floats3[1];
      floats[1] = floats2[2] * floats3[0] - floats2[0] * floats3[2];
      floats[2] = floats2[0] * floats3[1] - floats2[1] * floats3[0];
   }

   public void init() {
      float[] projectionMatrix2 = this.projectionMatrix;
      float[] modelviewMatrix2 = this.modelviewMatrix;
      float[] clippingMatrix2 = this.clippingMatrix;
      System.arraycopy(Shaders.faProjection, 0, projectionMatrix2, 0, 16);
      System.arraycopy(Shaders.faModelView, 0, modelviewMatrix2, 0, 16);
      SMath.multiplyMat4xMat4(clippingMatrix2, modelviewMatrix2, projectionMatrix2);
      this.assignPlane(this.frustum[0], clippingMatrix2[3] - clippingMatrix2[0], clippingMatrix2[7] - clippingMatrix2[4], clippingMatrix2[11] - clippingMatrix2[8], clippingMatrix2[15] - clippingMatrix2[12]);
      this.assignPlane(this.frustum[1], clippingMatrix2[3] + clippingMatrix2[0], clippingMatrix2[7] + clippingMatrix2[4], clippingMatrix2[11] + clippingMatrix2[8], clippingMatrix2[15] + clippingMatrix2[12]);
      this.assignPlane(this.frustum[2], clippingMatrix2[3] + clippingMatrix2[1], clippingMatrix2[7] + clippingMatrix2[5], clippingMatrix2[11] + clippingMatrix2[9], clippingMatrix2[15] + clippingMatrix2[13]);
      this.assignPlane(this.frustum[3], clippingMatrix2[3] - clippingMatrix2[1], clippingMatrix2[7] - clippingMatrix2[5], clippingMatrix2[11] - clippingMatrix2[9], clippingMatrix2[15] - clippingMatrix2[13]);
      this.assignPlane(this.frustum[4], clippingMatrix2[3] - clippingMatrix2[2], clippingMatrix2[7] - clippingMatrix2[6], clippingMatrix2[11] - clippingMatrix2[10], clippingMatrix2[15] - clippingMatrix2[14]);
      this.assignPlane(this.frustum[5], clippingMatrix2[3] + clippingMatrix2[2], clippingMatrix2[7] + clippingMatrix2[6], clippingMatrix2[11] + clippingMatrix2[10], clippingMatrix2[15] + clippingMatrix2[14]);
      float[] floats = Shaders.shadowLightPositionVector;
      float f = (float)this.dot3(this.frustum[0], floats);
      float f2 = (float)this.dot3(this.frustum[1], floats);
      float f3 = (float)this.dot3(this.frustum[2], floats);
      float f4 = (float)this.dot3(this.frustum[3], floats);
      float f5 = (float)this.dot3(this.frustum[4], floats);
      float f6 = (float)this.dot3(this.frustum[5], floats);
      this.shadowClipPlaneCount = 0;
      if (f >= 0.0F) {
         this.copyPlane(this.shadowClipPlanes[this.shadowClipPlaneCount++], this.frustum[0]);
         if (f > 0.0F) {
            if (f3 < 0.0F) {
               this.makeShadowPlane(this.shadowClipPlanes[this.shadowClipPlaneCount++], this.frustum[0], this.frustum[2], floats);
            }

            if (f4 < 0.0F) {
               this.makeShadowPlane(this.shadowClipPlanes[this.shadowClipPlaneCount++], this.frustum[0], this.frustum[3], floats);
            }

            if (f5 < 0.0F) {
               this.makeShadowPlane(this.shadowClipPlanes[this.shadowClipPlaneCount++], this.frustum[0], this.frustum[4], floats);
            }

            if (f6 < 0.0F) {
               this.makeShadowPlane(this.shadowClipPlanes[this.shadowClipPlaneCount++], this.frustum[0], this.frustum[5], floats);
            }
         }
      }

      if (f2 >= 0.0F) {
         this.copyPlane(this.shadowClipPlanes[this.shadowClipPlaneCount++], this.frustum[1]);
         if (f2 > 0.0F) {
            if (f3 < 0.0F) {
               this.makeShadowPlane(this.shadowClipPlanes[this.shadowClipPlaneCount++], this.frustum[1], this.frustum[2], floats);
            }

            if (f4 < 0.0F) {
               this.makeShadowPlane(this.shadowClipPlanes[this.shadowClipPlaneCount++], this.frustum[1], this.frustum[3], floats);
            }

            if (f5 < 0.0F) {
               this.makeShadowPlane(this.shadowClipPlanes[this.shadowClipPlaneCount++], this.frustum[1], this.frustum[4], floats);
            }

            if (f6 < 0.0F) {
               this.makeShadowPlane(this.shadowClipPlanes[this.shadowClipPlaneCount++], this.frustum[1], this.frustum[5], floats);
            }
         }
      }

      if (f3 >= 0.0F) {
         this.copyPlane(this.shadowClipPlanes[this.shadowClipPlaneCount++], this.frustum[2]);
         if (f3 > 0.0F) {
            if (f < 0.0F) {
               this.makeShadowPlane(this.shadowClipPlanes[this.shadowClipPlaneCount++], this.frustum[2], this.frustum[0], floats);
            }

            if (f2 < 0.0F) {
               this.makeShadowPlane(this.shadowClipPlanes[this.shadowClipPlaneCount++], this.frustum[2], this.frustum[1], floats);
            }

            if (f5 < 0.0F) {
               this.makeShadowPlane(this.shadowClipPlanes[this.shadowClipPlaneCount++], this.frustum[2], this.frustum[4], floats);
            }

            if (f6 < 0.0F) {
               this.makeShadowPlane(this.shadowClipPlanes[this.shadowClipPlaneCount++], this.frustum[2], this.frustum[5], floats);
            }
         }
      }

      if (f4 >= 0.0F) {
         this.copyPlane(this.shadowClipPlanes[this.shadowClipPlaneCount++], this.frustum[3]);
         if (f4 > 0.0F) {
            if (f < 0.0F) {
               this.makeShadowPlane(this.shadowClipPlanes[this.shadowClipPlaneCount++], this.frustum[3], this.frustum[0], floats);
            }

            if (f2 < 0.0F) {
               this.makeShadowPlane(this.shadowClipPlanes[this.shadowClipPlaneCount++], this.frustum[3], this.frustum[1], floats);
            }

            if (f5 < 0.0F) {
               this.makeShadowPlane(this.shadowClipPlanes[this.shadowClipPlaneCount++], this.frustum[3], this.frustum[4], floats);
            }

            if (f6 < 0.0F) {
               this.makeShadowPlane(this.shadowClipPlanes[this.shadowClipPlaneCount++], this.frustum[3], this.frustum[5], floats);
            }
         }
      }

      if (f5 >= 0.0F) {
         this.copyPlane(this.shadowClipPlanes[this.shadowClipPlaneCount++], this.frustum[4]);
         if (f5 > 0.0F) {
            if (f < 0.0F) {
               this.makeShadowPlane(this.shadowClipPlanes[this.shadowClipPlaneCount++], this.frustum[4], this.frustum[0], floats);
            }

            if (f2 < 0.0F) {
               this.makeShadowPlane(this.shadowClipPlanes[this.shadowClipPlaneCount++], this.frustum[4], this.frustum[1], floats);
            }

            if (f3 < 0.0F) {
               this.makeShadowPlane(this.shadowClipPlanes[this.shadowClipPlaneCount++], this.frustum[4], this.frustum[2], floats);
            }

            if (f4 < 0.0F) {
               this.makeShadowPlane(this.shadowClipPlanes[this.shadowClipPlaneCount++], this.frustum[4], this.frustum[3], floats);
            }
         }
      }

      if (f6 >= 0.0F) {
         this.copyPlane(this.shadowClipPlanes[this.shadowClipPlaneCount++], this.frustum[5]);
         if (f6 > 0.0F) {
            if (f < 0.0F) {
               this.makeShadowPlane(this.shadowClipPlanes[this.shadowClipPlaneCount++], this.frustum[5], this.frustum[0], floats);
            }

            if (f2 < 0.0F) {
               this.makeShadowPlane(this.shadowClipPlanes[this.shadowClipPlaneCount++], this.frustum[5], this.frustum[1], floats);
            }

            if (f3 < 0.0F) {
               this.makeShadowPlane(this.shadowClipPlanes[this.shadowClipPlaneCount++], this.frustum[5], this.frustum[2], floats);
            }

            if (f4 < 0.0F) {
               this.makeShadowPlane(this.shadowClipPlanes[this.shadowClipPlaneCount++], this.frustum[5], this.frustum[3], floats);
            }
         }
      }
   }

   private void normalize3(float[] floats) {
      float f = MathHelper.sqrt_float(floats[0] * floats[0] + floats[1] * floats[1] + floats[2] * floats[2]);
      if (f == 0.0F) {
         f = 1.0F;
      }

      floats[0] /= f;
      floats[1] /= f;
      floats[2] /= f;
   }

   private void addShadowClipPlane(float[] floats) {
      this.copyPlane(this.shadowClipPlanes[this.shadowClipPlaneCount++], floats);
   }

   private void normalizePlane(float[] floats) {
      float f = MathHelper.sqrt_float(floats[0] * floats[0] + floats[1] * floats[1] + floats[2] * floats[2]);
      floats[0] /= f;
      floats[1] /= f;
      floats[2] /= f;
      floats[3] /= f;
   }

   private float length(float f, float f2, float f3) {
      return (float)Math.sqrt((double)(f * f + f2 * f2 + f3 * f3));
   }

   private double dot3(float[] floats, float[] floats2) {
      return (double)floats[0] * (double)floats2[0] + (double)floats[1] * (double)floats2[1] + (double)floats[2] * (double)floats2[2];
   }

   private void makeShadowPlane(float[] floats, float[] floats2, float[] floats3, float[] floats4) {
      this.cross3(this.vecIntersection, floats2, floats3);
      this.cross3(floats, this.vecIntersection, floats4);
      this.normalize3(floats);
      float f = (float)this.dot3(floats2, floats3);
      float f2 = (float)this.dot3(floats, floats3);
      float f5 = this.distance(floats[0], floats[1], floats[2], floats3[0] * f2, floats3[1] * f2, floats3[2] * f2) / this.distance(floats2[0], floats2[1], floats2[2], floats3[0] * f, floats3[1] * f, floats3[2] * f);
      float f6 = (float)this.dot3(floats, floats2);
      float f9 = this.distance(floats[0], floats[1], floats[2], floats2[0] * f6, floats2[1] * f6, floats2[2] * f6) / this.distance(floats3[0], floats3[1], floats3[2], floats2[0] * f, floats2[1] * f, floats2[2] * f);
      floats[3] = floats2[3] * f5 + floats3[3] * f9;
   }
}
