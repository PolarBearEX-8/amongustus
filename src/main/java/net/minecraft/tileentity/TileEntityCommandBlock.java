package net.minecraft.tileentity;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.Entity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraft.command.CommandResultStats;
import net.minecraft.command.server.CommandBlockLogic;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;

public class TileEntityCommandBlock extends TileEntity {
   private final CommandBlockLogic commandBlockLogic = new TileEntityCommandBlock.DefaultCommandBlockLogic(this);

   public CommandResultStats getCommandResultStats() {
      return this.commandBlockLogic.getCommandResultStats();
   }

   @Override
   public void readFromNBT(NBTTagCompound tag) {
      super.readFromNBT(tag);
      this.commandBlockLogic.readDataFromNBT(tag);
   }

   @Override
   public void writeToNBT(NBTTagCompound tag) {
      super.writeToNBT(tag);
      this.commandBlockLogic.writeDataToNBT(tag);
   }

   @Override
   public boolean onlyOpsCanSetNbt() {
      return true;
   }

   @Override
   public Packet getDescriptionPacket() {
      NBTTagCompound tag = new NBTTagCompound();
      this.writeToNBT(tag);
      return new S35PacketUpdateTileEntity(this.pos, 2, tag);
   }

   public CommandBlockLogic getCommandBlockLogic() {
      return this.commandBlockLogic;
   }


   public static class DefaultCommandBlockLogic extends CommandBlockLogic {
      public final TileEntityCommandBlock tileEntity;

      @Override
      public World getEntityWorld() {
         return this.tileEntity.getWorldObj();
      }

      @Override
      public Entity getCommandSenderEntity() {
         return null;
      }

      @Override
      public BlockPos getPosition() {
         return this.tileEntity.pos;
      }

      public DefaultCommandBlockLogic(TileEntityCommandBlock tileEntityCommandBlock) {
         this.tileEntity = tileEntityCommandBlock;
      }

      @Override
      public void updateCommand() {
         this.tileEntity.getWorldObj().markBlockForUpdate(this.tileEntity.pos);
      }

      @Override
      public Vec3 getPositionVector() {
         return new Vec3((double)this.tileEntity.pos.getX() + 0.5, (double)this.tileEntity.pos.getY() + 0.5, (double)this.tileEntity.pos.getZ() + 0.5);
      }

      @Override
      public int getCommandBlockType() {
         return 0;
      }

      @Override
      public void setCommand(String str) {
         super.setCommand(str);
         this.tileEntity.markDirty();
      }

      @Override
      public void fillInInfo(ByteBuf buf) {
         buf.writeInt(this.tileEntity.pos.getX());
         buf.writeInt(this.tileEntity.pos.getY());
         buf.writeInt(this.tileEntity.pos.getZ());
      }
   }
}
