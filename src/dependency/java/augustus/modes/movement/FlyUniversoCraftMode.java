package augustus.modes.movement;

import augustus.modules.movement.Fly;
import client.event.EventBlockCollision;
import client.event.EventMotionUpdate;
import client.event.EventMoveEntity;
import client.util.MinecraftAccessor;
import net.minecraft.block.BlockAir;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;

public class FlyUniversoCraftMode extends FlyModeHandler {
   public double value = 0.0;

   @Override
   public void onBlockCollision(EventBlockCollision event) {
      if (event.getBlock() instanceof BlockAir && (double)event.getBlockPos().getY() < this.value) {
         BlockPos blockPos = event.getBlockPos();
         AxisAlignedBB boundingBox = new AxisAlignedBB(
            (double)blockPos.getX(), (double)blockPos.getY(), (double)blockPos.getZ(), (double)(blockPos.getX() + 1), (double)(blockPos.getY() + 1), (double)(blockPos.getZ() + 1)
         );
         event.setBoundingBox(boundingBox);
      }
   }

   @Override
   public void processChar() {
   }

   @Override
   public void onMotionUpdate(EventMotionUpdate event) {
      BlockPos blockPos = new BlockPos(
         MinecraftAccessor.minecraft.thePlayer.posX, MinecraftAccessor.minecraft.thePlayer.posY - 0.9, MinecraftAccessor.minecraft.thePlayer.posZ
      );
      if (!MinecraftAccessor.minecraft.theWorld.getBlockState(blockPos).getBlock().isState18()) {
         event.setState(false);
      }
   }

   @Override
   public void updateState2() {
      this.value = MinecraftAccessor.minecraft.thePlayer.posY - 0.9;
   }

   @Override
   public void updateState3() {
   }

   @Override
   public void onMoveEntity(EventMoveEntity event) {
   }

   public FlyUniversoCraftMode(String text, Fly fly) {
      super(text, fly);
   }
}
