package net.minecraft.block;

import client.setting.MovementScaleProperty;
import client.util.DecayableManager;
import client.util.IBlockState;
import client.util.ImmutableSetEntry;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemGroupManager;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public abstract class GeometryParser extends CommonBlockAdapter {
   public static final ImmutableSetEntry immutableSetEntry2 = ImmutableSetEntry.resolveImmutableSetEntry("axis", MovementScaleProperty.class);

   @Override
   public void onBlockAdded(World world, BlockPos blockPos, IBlockState blockState) {
      if (world.matchesBlockPos(blockPos.add(-5, -5, -5), blockPos.add(5, 5, 5))) {
         for (BlockPos blockPos2 : (Iterable<BlockPos>) BlockPos.resolveIterable(blockPos.add(-4, -4, -4), blockPos.add(4, 4, 4))) {
            IBlockState blockState2 = world.getBlockState(blockPos2);
            if (blockState2.getBlock().getMaterial() == Material.material9 && !(Boolean)blockState2.getComparable(DecayableManager.internalImmutableSetEntry)) {
               world.matchesBlockPos(blockPos2, blockState2.resolveIBlockState(DecayableManager.internalImmutableSetEntry, Boolean.TRUE), 4);
            }
         }
      }
   }

   @Override
   public IBlockState onBlockPlaced(World world, BlockPos blockPos, EnumFacing enumFacing, float posX, float posY, float posZ, int count, EntityLivingBase livingEntity) {
      return super.onBlockPlaced(world, blockPos, enumFacing, posX, posY, posZ, count, livingEntity)
         .resolveIBlockState(immutableSetEntry2, MovementScaleProperty.ordinal(enumFacing.getEnumFacingXType()));
   }

   public GeometryParser() {
      super(Material.material);
      this.processAction(ItemGroupManager.itemGroupManager10);
      this.getBlock5(2.0F);
      this.processAction(stepManager19);
   }
}
