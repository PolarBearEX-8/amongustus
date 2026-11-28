package net.minecraft.item;

import net.minecraft.block.Block;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class ItemSlab extends ItemBlock {
   private final BlockSlab singleSlab;
   private final BlockSlab doubleSlab;

   @Override
   public boolean canPlaceBlockOnSide(World world, BlockPos pos, EnumFacing facing, EntityPlayer player, ItemStack stack) {
      IProperty variantProperty = this.singleSlab.getVariantProperty();
      Object variant = this.singleSlab.getVariant(stack);
      IBlockState blockState = world.getBlockState(pos);
      if (blockState.getBlock() == this.singleSlab) {
         boolean flag = blockState.getValue(BlockSlab.HALF) == BlockSlab.EnumBlockHalf.TOP;
         if ((facing == EnumFacing.UP && !flag || facing == EnumFacing.DOWN && flag) && variant == blockState.getValue(variantProperty)) {
            return true;
         }
      }

      IBlockState blockState2 = world.getBlockState(pos.offset(facing));
      return blockState2.getBlock() == this.singleSlab && variant == blockState2.getValue(variantProperty) ? true : super.canPlaceBlockOnSide(world, pos, facing, player, stack);
   }

   @Override
   public String getUnlocalizedName(ItemStack stack) {
      return this.singleSlab.getUnlocalizedName(stack.getMetadata());
   }

   public ItemSlab(Block block2, BlockSlab blockSlab, BlockSlab blockSlab2) {
      super(block2);
      this.singleSlab = blockSlab;
      this.doubleSlab = blockSlab2;
      this.setMaxDamage(0);
      this.setHasSubtypes(true);
   }

   private boolean tryPlace(ItemStack stack, World world, BlockPos pos, Object obj) {
      IBlockState blockState = world.getBlockState(pos);
      if (blockState.getBlock() == this.singleSlab) {
         Comparable value = blockState.getValue(this.singleSlab.getVariantProperty());
         if (value == obj) {
            IBlockState state = this.doubleSlab.getDefaultState().withProperty(this.singleSlab.getVariantProperty(), value);
            if (world.checkNoEntityCollision(this.doubleSlab.getCollisionBoundingBox(world, state, pos.getX(), pos.getY(), pos.getZ())) && world.setBlockState(pos, state, 3)) {
               world.playSoundEffect(
                  (double)((float)pos.getX() + 0.5F),
                  (double)((float)pos.getY() + 0.5F),
                  (double)((float)pos.getZ() + 0.5F),
                  this.doubleSlab.stepSound.getPlaceSound(),
                  (this.doubleSlab.stepSound.getVolume() + 1.0F) / 2.0F,
                  this.doubleSlab.stepSound.getFrequency() * 0.8F
               );
               stack.stackSize--;
            }

            return true;
         }
      }

      return false;
   }

   @Override
   public int getMetadata(int n) {
      return n;
   }

   @Override
   public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, BlockPos pos, EnumFacing facing, float f, float f2, float f3) {
      if (stack.stackSize == 0) {
         return false;
      } else if (!player.canPlayerEdit(pos.offset(facing), facing, stack)) {
         return false;
      } else {
         Object variant = this.singleSlab.getVariant(stack);
         IBlockState blockState = world.getBlockState(pos);
         if (blockState.getBlock() == this.singleSlab) {
            IProperty variantProperty = this.singleSlab.getVariantProperty();
            Comparable value = blockState.getValue(variantProperty);
            BlockSlab.EnumBlockHalf value2 = (BlockSlab.EnumBlockHalf)blockState.getValue(BlockSlab.HALF);
            if ((facing == EnumFacing.UP && value2 == BlockSlab.EnumBlockHalf.BOTTOM || facing == EnumFacing.DOWN && value2 == BlockSlab.EnumBlockHalf.TOP)
               && value == variant) {
               IBlockState state = this.doubleSlab.getDefaultState().withProperty(variantProperty, value);
               if (world.checkNoEntityCollision(this.doubleSlab.getCollisionBoundingBox(world, state, pos.getX(), pos.getY(), pos.getZ())) && world.setBlockState(pos, state, 3)) {
                  world.playSoundEffect(
                     (double)((float)pos.getX() + 0.5F),
                     (double)((float)pos.getY() + 0.5F),
                     (double)((float)pos.getZ() + 0.5F),
                     this.doubleSlab.stepSound.getPlaceSound(),
                     (this.doubleSlab.stepSound.getVolume() + 1.0F) / 2.0F,
                     this.doubleSlab.stepSound.getFrequency() * 0.8F
                  );
                  stack.stackSize--;
               }

               return true;
            }
         }

         return this.tryPlace(stack, world, pos.offset(facing), variant) ? true : super.onItemUse(stack, player, world, pos, facing, f, f2, f3);
      }
   }
}
