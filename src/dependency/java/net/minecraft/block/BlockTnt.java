package net.minecraft.block;

import client.util.IBlockState;
import client.util.InternalImmutableSetEntry;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityArrow;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EntityPrimedtnt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroupManager;
import net.minecraft.item.Items;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class BlockTnt extends Block {
   public static final InternalImmutableSetEntry internalImmutableSetEntry = InternalImmutableSetEntry.resolveInternalImmutableSetEntry("explode");

   @Override
   public void breakBlock(World world, BlockPos blockPos, IBlockState blockState) {
      this.applyWorldByWorld(world, blockPos, blockState, (EntityLivingBase)null);
   }

   @Override
   public boolean processAction(BlockBreakState blockBreakState) {
      return false;
   }

   @Override
   public boolean processAction(World world, BlockPos blockPos, IBlockState blockState, EntityPlayer player, EnumFacing enumFacing, float posX, float posY, float posZ) {
      if (player.getHeldItem() != null) {
         Item item = player.getHeldItem().getItem();
         if (item == Items.flintAndSteel || item == Items.fireCharge) {
            return true;
         }
      }

      return super.onBlockActivated(world, blockPos, blockState, player, enumFacing, posX, posY, posZ);
   }

   @Override
   public IBlockState processAction(int count) {
      return this.getDefaultState().resolveIBlockState(internalImmutableSetEntry, (count & 1) > 0);
   }

   @Override
   public void onEntityCollidedWithBlock(World world, BlockPos blockPos, IBlockState blockState, Entity entity) {
      if (!world.state && entity instanceof EntityArrow) {
         EntityArrow entityArrow = (EntityArrow)entity;
         if (entityArrow.canExecute2()) {
            this.applyWorldByWorld(
               world,
               blockPos,
               world.getBlockState(blockPos).resolveIBlockState(internalImmutableSetEntry, Boolean.TRUE),
               entityArrow.entity2 instanceof EntityLivingBase ? (EntityLivingBase)entityArrow.entity2 : null
            );
            world.matchesBlockPos8(blockPos);
         }
      }
   }

   @Override
   public void processAction(World world, BlockPos blockPos, IBlockState blockState, Block block) {
      if (world.matchesBlockPos7(blockPos)) {
         this.breakBlock(world, blockPos, blockState.resolveIBlockState(internalImmutableSetEntry, Boolean.TRUE));
         world.matchesBlockPos8(blockPos);
      }
   }

   @Override
   public boolean onBlockActivated(World world, BlockPos blockPos, IBlockState blockState, EntityPlayer player, EnumFacing enumFacing, float posX, float posY, float posZ) {
      if (player.getHeldItem() != null) {
         Item item = player.getHeldItem().getItem();
         if (item == Items.flintAndSteel || item == Items.fireCharge) {
            this.applyWorldByWorld(world, blockPos, blockState.resolveIBlockState(internalImmutableSetEntry, Boolean.TRUE), player);
            world.matchesBlockPos8(blockPos);
            if (item == Items.flintAndSteel) {
               player.getHeldItem().applyEntityLivingBaseByEntityLivingBase(1, player);
            } else if (!player.invulnerableManager.enabled2) {
               player.getHeldItem().animationsToGo--;
            }

            return true;
         }
      }

      return super.onBlockActivated(world, blockPos, blockState, player, enumFacing, posX, posY, posZ);
   }

   @Override
   public void onNeighborBlockChange(World world, BlockPos blockPos, BlockBreakState blockBreakState) {
      if (!world.state) {
         EntityPrimedtnt entityPrimedtnt = new EntityPrimedtnt(
            world, (double)((float)blockPos.getX() + 0.5F), (double)blockPos.getY(), (double)((float)blockPos.getZ() + 0.5F), blockBreakState.getEntityLivingBase()
         );
         entityPrimedtnt.indexOffset4 = world.random.nextInt(entityPrimedtnt.indexOffset4 / 4) + entityPrimedtnt.indexOffset4 / 8;
         world.matchesEntity(entityPrimedtnt);
      }
   }

   public void applyWorldByWorld(World world, BlockPos blockPos, IBlockState blockState, EntityLivingBase livingEntity) {
      if (!world.state && (Boolean)blockState.getComparable(internalImmutableSetEntry)) {
         EntityPrimedtnt entityPrimedtnt = new EntityPrimedtnt(world, (double)((float)blockPos.getX() + 0.5F), (double)blockPos.getY(), (double)((float)blockPos.getZ() + 0.5F), livingEntity);
         world.matchesEntity(entityPrimedtnt);
         world.applyEntityByEntity(entityPrimedtnt, "game.tnt.primed", 1.0F, 1.0F);
      }
   }

   @Override
   public void processAction3(World world, BlockPos blockPos, IBlockState blockState) {
      super.processAction3(world, blockPos, blockState);
      if (world.matchesBlockPos7(blockPos)) {
         this.breakBlock(world, blockPos, blockState.resolveIBlockState(internalImmutableSetEntry, Boolean.TRUE));
         world.matchesBlockPos8(blockPos);
      }
   }

   @Override
   public BlockManager processAction11() {
      return new BlockManager(this, new IProperty[]{internalImmutableSetEntry});
   }

   @Override
   public int getIndex(IBlockState blockState) {
      return (((Boolean)blockState.getComparable(internalImmutableSetEntry)) ? 1 : 0);
   }

   public BlockTnt() {
      super(Material.material8);
      this.setIBlockState(this.blockManager.get().resolveIBlockState(internalImmutableSetEntry, Boolean.FALSE));
      this.processAction(ItemGroupManager.itemGroupManager9);
   }
}
