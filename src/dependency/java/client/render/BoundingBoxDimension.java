package client.render;

import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Vec3;

public class BoundingBoxDimension {
   public float factor3;
   public int indexOffset;
   public float factor4;
   public float factor2;
   public boolean enabled4;
   public boolean enabled5;
   public boolean enabled;
   public float factor;
   public Vec3 vec3;
   public AxisAlignedBB axisAlignedBB;
   public Vec3 positionVec2;
   public boolean enabled2;

   public BoundingBoxDimension(Vec3 vec, int count) {
      this.positionVec2 = vec;
      this.indexOffset = count;
   }

   private BoundingBoxDimension() {
   }

   public BoundingBoxDimension(EntityPlayerSP player) {
      this.positionVec2 = player.getVec32(1.0F);
      this.vec3 = new Vec3(player.motionX, player.motionY, player.motionZ);
      this.factor4 = player.rotationYaw;
      this.factor2 = player.secondaryFloatEntry.moveForward;
      this.factor3 = player.secondaryFloatEntry.moveStrafe;
      this.enabled4 = player.onGround;
      this.enabled5 = player.isRiding();
      this.enabled2 = player.secondaryFloatEntry.state2;
      this.enabled = player.secondaryFloatEntry.state;
      this.indexOffset = player.ticksExisted;
      this.factor = player.getScaleFactor6();
      this.axisAlignedBB = player.getBoundingBox2();
   }
}
