package client.util;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntityControl;
import net.minecraft.util.BlockPos;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class VectorBuilder extends VectorDispatcher {
   public final TileEntityControl tileEntityControl;

   @Override
   public void applyText(String text) {
      super.applyText(text);
      this.tileEntityControl.processAction5();
   }

   @Override
   public void updateState() {
      this.tileEntityControl.getWorld().applyBlockPos2(this.tileEntityControl.blockPos);
   }

   @Override
   public Vec3 getVec3() {
      return new Vec3(
         (double)this.tileEntityControl.blockPos.getX() + 0.5,
         (double)this.tileEntityControl.blockPos.getY() + 0.5,
         (double)this.tileEntityControl.blockPos.getZ() + 0.5
      );
   }

   @Override
   public BlockPos getBlockPos() {
      return this.tileEntityControl.blockPos;
   }

   public VectorBuilder(TileEntityControl tileEntityControl2) {
      this.tileEntityControl = tileEntityControl2;
   }

   @Override
   public void applyByteBuf(ByteBuf byteBuf) {
      byteBuf.writeInt(this.tileEntityControl.blockPos.getX());
      byteBuf.writeInt(this.tileEntityControl.blockPos.getY());
      byteBuf.writeInt(this.tileEntityControl.blockPos.getZ());
   }

   @Override
   public World getWorldObj() {
      return this.tileEntityControl.getWorld();
   }

   @Override
   public int getCountAsIndex() {
      return 0;
   }

   @Override
   public Entity getEntity() {
      return null;
   }
}
