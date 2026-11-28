package net.minecraft.entity.effect;

import java.util.List;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;

public class EntityLightningBolt extends EntityWeatherEffect {
   private int lightningState;
   public long boltVertex;
   private int boltLivingTime;

   @Override
   public void onUpdate() {
      super.onUpdate();
      if (this.lightningState == 2) {
         this.entityWorld.playSoundEffect(this.posX, this.posY, this.posZ, "ambient.weather.thunder", 10000.0F, 0.8F + this.rand.nextFloat() * 0.2F);
         this.entityWorld.playSoundEffect(this.posX, this.posY, this.posZ, "random.explode", 2.0F, 0.5F + this.rand.nextFloat() * 0.2F);
      }

      this.lightningState--;
      if (this.lightningState < 0) {
         if (this.boltLivingTime == 0) {
            this.setDead();
         } else if (this.lightningState < -this.rand.nextInt(10)) {
            this.boltLivingTime--;
            this.lightningState = 1;
            this.boltVertex = this.rand.nextLong();
            BlockPos pos = new BlockPos(this);
            if (!this.entityWorld.isRemote
               && this.entityWorld.getGameRules().getBoolean("doFireTick")
               && this.entityWorld.isAreaLoaded(pos.getX(), pos.getY(), pos.getZ(), 10)
               && this.entityWorld.getBlockState(pos).getBlock().getMaterial() == Material.air
               && Blocks.fire.canPlaceBlockAt(this.entityWorld, pos)) {
               this.entityWorld.setBlockState(pos, Blocks.fire.getDefaultState());
            }
         }
      }

      if (this.lightningState >= 0) {
         if (this.entityWorld.isRemote) {
            this.entityWorld.setLastLightningBolt(2);
         } else {
            double d = 3.0;
            List list = this.entityWorld
               .getEntitiesWithinAABBExcludingEntity(
                  this, new AxisAlignedBB(this.posX - d, this.posY - d, this.posZ - d, this.posX + d, this.posY + 6.0 + d, this.posZ + d)
               );

            for (int i = 0; i < list.size(); i++) {
               ((Entity)list.get(i)).onStruckByLightning(this);
            }
         }
      }
   }

   @Override
   public void entityInit() {
   }

   public EntityLightningBolt(World world, double d, double d2, double d3) {
      super(world);
      this.setLocationAndAngles(d, d2, d3, 0.0F, 0.0F);
      this.lightningState = 2;
      this.boltVertex = this.rand.nextLong();
      this.boltLivingTime = this.rand.nextInt(3) + 1;
      BlockPos pos = new BlockPos(this);
      if (!world.isRemote
         && world.getGameRules().getBoolean("doFireTick")
         && (world.getDifficulty() == EnumDifficulty.NORMAL || world.getDifficulty() == EnumDifficulty.HARD)
         && world.isAreaLoaded(pos.getX(), pos.getY(), pos.getZ(), 10)) {
         if (world.getBlockState(pos).getBlock().getMaterial() == Material.air && Blocks.fire.canPlaceBlockAt(world, pos)) {
            world.setBlockState(pos, Blocks.fire.getDefaultState());
         }

         for (int i = 0; i < 4; i++) {
            BlockPos pos2 = pos.add(this.rand.nextInt(3) - 1, this.rand.nextInt(3) - 1, this.rand.nextInt(3) - 1);
            if (world.getBlockState(pos2).getBlock().getMaterial() == Material.air && Blocks.fire.canPlaceBlockAt(world, pos2)) {
               world.setBlockState(pos2, Blocks.fire.getDefaultState());
            }
         }
      }
   }

   @Override
   public void writeEntityToNBT(NBTTagCompound tag) {
   }

   @Override
   public void readEntityFromNBT(NBTTagCompound tag) {
   }
}
