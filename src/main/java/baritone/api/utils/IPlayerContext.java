package baritone.api.utils;

import baritone.api.cache.IWorldData;
import java.util.Optional;
import net.minecraft.block.BlockSlab;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public interface IPlayerContext {
   default Vec3 playerHead() {
      return new Vec3(this.player().posX, this.player().posY + (double)this.player().getEyeHeight(), this.player().posZ);
   }

   static double eyeHeight(boolean flag) {
      return flag ? 1.54 : 1.62;
   }

   default Vec3 playerMotion() {
      return new Vec3(this.player().motionX, this.player().motionY, this.player().motionZ);
   }

   BetterBlockPos viewerPos();

   default Vec3 playerFeetAsVec() {
      return new Vec3(this.player().posX, this.player().posY, this.player().posZ);
   }

   MovingObjectPosition objectMouseOver();

   default Rotation playerRotations() {
      return new Rotation(this.player().rotationYaw, this.player().rotationPitch);
   }

   IWorldData worldData();

   World world();

   default Optional getSelectedBlock() {
      MovingObjectPosition hit = this.objectMouseOver();
      return hit != null && hit.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK ? Optional.of(hit.getBlockPos()) : Optional.empty();
   }

   EntityPlayerSP player();

   Minecraft getMc();

   IPlayerController playerController();

   default boolean isLookingAt(BlockPos pos) {
      return this.getSelectedBlock().equals(Optional.of(pos));
   }

   default BetterBlockPos playerFeet() {
      BetterBlockPos betterBlockPos = new BetterBlockPos(this.player().posX, this.player().posY + 0.1251, this.player().posZ);

      try {
         if (this.world().getBlockState(betterBlockPos).getBlock() instanceof BlockSlab) {
            return betterBlockPos.up2();
         }
      } catch (NullPointerException e) {
      }

      return betterBlockPos;
   }
}
