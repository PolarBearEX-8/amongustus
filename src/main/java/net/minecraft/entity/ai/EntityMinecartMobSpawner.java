package net.minecraft.entity.ai;

import net.minecraft.util.BlockPos;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.MobSpawnerBaseLogic;
import net.minecraft.world.World;

public class EntityMinecartMobSpawner extends EntityMinecart {
   private final MobSpawnerBaseLogic mobSpawnerLogic = new EntityMinecartMobSpawner.DefaultMobSpawnerBaseLogic(this);

   public MobSpawnerBaseLogic getMobSpawnerLogic() {
      return this.mobSpawnerLogic;
   }

   @Override
   public IBlockState getDefaultDisplayTile() {
      return Blocks.mob_spawner.getDefaultState();
   }

   @Override
   public void onUpdate() {
      super.onUpdate();
      this.mobSpawnerLogic.updateSpawner();
   }

   @Override
   public void readEntityFromNBT(NBTTagCompound tag) {
      super.readEntityFromNBT(tag);
      this.mobSpawnerLogic.readFromNBT(tag);
   }

   public EntityMinecartMobSpawner(World world, double d, double d2, double d3) {
      super(world, d, d2, d3);
   }

   @Override
   public void writeEntityToNBT(NBTTagCompound tag) {
      super.writeEntityToNBT(tag);
      this.mobSpawnerLogic.writeToNBT(tag);
   }

   @Override
   public EntityMinecart.EnumMinecartType getMinecartType() {
      return EntityMinecart.EnumMinecartType.SPAWNER;
   }

   public EntityMinecartMobSpawner(World world) {
      super(world);
   }

   @Override
   public void handleStatusUpdate(byte b) {
      this.mobSpawnerLogic.setDelayToMin(b);
   }


   public static class DefaultMobSpawnerBaseLogic extends MobSpawnerBaseLogic {
      public final EntityMinecartMobSpawner minecartSpawner;

      public DefaultMobSpawnerBaseLogic(EntityMinecartMobSpawner entityMinecartMobSpawner) {
         this.minecartSpawner = entityMinecartMobSpawner;
      }

      @Override
      public void broadcastEvent(int n) {
         this.minecartSpawner.entityWorld.setEntityState(this.minecartSpawner, (byte)n);
      }

      @Override
      public World getSpawnerWorld() {
         return this.minecartSpawner.entityWorld;
      }

      @Override
      public BlockPos getSpawnerPosition() {
         return new BlockPos(this.minecartSpawner);
      }
   }
}
