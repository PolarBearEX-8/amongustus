package net.minecraft.tileentity;

import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.util.ITickable;

public class TileEntityMobSpawner extends TileEntity implements ITickable {
   private final MobSpawnerBaseLogic spawnerLogic = new TileEntityMobSpawner.DefaultMobSpawnerBaseLogic(this);

   @Override
   public void update() {
      this.spawnerLogic.updateSpawner();
   }

   @Override
   public Packet getDescriptionPacket() {
      NBTTagCompound tag = new NBTTagCompound();
      this.writeToNBT(tag);
      tag.removeTag("SpawnPotentials");
      return new S35PacketUpdateTileEntity(this.pos, 1, tag);
   }

   public MobSpawnerBaseLogic getSpawnerBaseLogic() {
      return this.spawnerLogic;
   }

   @Override
   public void readFromNBT(NBTTagCompound tag) {
      super.readFromNBT(tag);
      this.spawnerLogic.readFromNBT(tag);
   }

   @Override
   public void writeToNBT(NBTTagCompound tag) {
      super.writeToNBT(tag);
      this.spawnerLogic.writeToNBT(tag);
   }

   @Override
   public boolean receiveClientEvent(int n, int n2) {
      return this.spawnerLogic.setDelayToMin(n) ? true : super.receiveClientEvent(n, n2);
   }

   @Override
   public boolean onlyOpsCanSetNbt() {
      return true;
   }


   public static class DefaultMobSpawnerBaseLogic extends MobSpawnerBaseLogic {
      public final TileEntityMobSpawner tileEntity;

      @Override
      public BlockPos getSpawnerPosition() {
         return this.tileEntity.pos;
      }

      @Override
      public void broadcastEvent(int n) {
         this.tileEntity.worldObj.addBlockEvent(this.tileEntity.pos, Blocks.mob_spawner, n, 0);
      }

      @Override
      public void setRandomEntity(MobSpawnerBaseLogic.WeightedRandomMinecart weightedRandomMinecart) {
         super.setRandomEntity(weightedRandomMinecart);
         if (this.getSpawnerWorld() != null) {
            this.getSpawnerWorld().markBlockForUpdate(this.tileEntity.pos);
         }
      }

      @Override
      public World getSpawnerWorld() {
         return this.tileEntity.worldObj;
      }

      public DefaultMobSpawnerBaseLogic(TileEntityMobSpawner tileEntityMobSpawner) {
         this.tileEntity = tileEntityMobSpawner;
      }
   }
}
