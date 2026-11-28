package net.minecraft.client.renderer.culling;

import net.minecraft.util.AxisAlignedBB;

public class Frustum implements ICamera {
   private ClippingHelper clippingHelper;
   private double xPosition;
   private double yPosition;
   private double zPosition;

   @Override
   public boolean isBoundingBoxInFrustum(AxisAlignedBB box) {
      return this.isBoxInFrustum(box.minX, box.minY, box.minZ, box.maxX, box.maxY, box.maxZ);
   }

   @Override
   public boolean isBoxInFrustum(double d, double d2, double d3, double d4, double d5, double d6) {
      return this.clippingHelper
         .isBoxInFrustum(
            d - this.xPosition, d2 - this.yPosition, d3 - this.zPosition, d4 - this.xPosition, d5 - this.yPosition, d6 - this.zPosition
         );
   }

   public Frustum() {
      this(ClippingHelperImpl.getInstance());
   }

   public Frustum(ClippingHelper clippingHelper2) {
      this.clippingHelper = clippingHelper2;
   }

   public boolean isBoxInFrustumFully(double d, double d2, double d3, double d4, double d5, double d6) {
      return this.clippingHelper
         .isBoxInFrustumFully(d - this.xPosition, d2 - this.yPosition, d3 - this.zPosition, d4 - this.xPosition, d5 - this.yPosition, d6 - this.zPosition);
   }

   @Override
   public void setPosition(double d, double d2, double d3) {
      this.xPosition = d;
      this.yPosition = d2;
      this.zPosition = d3;
   }
}
