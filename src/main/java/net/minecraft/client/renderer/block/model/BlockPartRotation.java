package net.minecraft.client.renderer.block.model;

import net.minecraft.util.EnumFacing;
import org.lwjgl.util.vector.Vector3f;

public class BlockPartRotation {
   public final Vector3f origin;
   public final EnumFacing.Axis axis;
   public final float angle;
   public final boolean rescale;

   public BlockPartRotation(Vector3f vector3f, EnumFacing.Axis axis2, float f, boolean flag) {
      this.origin = vector3f;
      this.axis = axis2;
      this.angle = f;
      this.rescale = flag;
   }
}
