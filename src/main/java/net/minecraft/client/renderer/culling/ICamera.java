package net.minecraft.client.renderer.culling;

import net.minecraft.util.AxisAlignedBB;

public interface ICamera {
   boolean isBoundingBoxInFrustum(AxisAlignedBB box);

   void setPosition(double d, double d2, double d3);

   boolean isBoxInFrustum(double d, double d2, double d3, double d4, double d5, double d6);
}
