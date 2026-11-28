package net.minecraft.item;

import net.minecraft.block.material.Material;
import net.minecraft.dispenser.BehaviorDefaultDispenseItem;
import net.minecraft.dispenser.IBlockSource;
import net.minecraft.block.BlockDispenser;
import net.minecraft.block.BlockRailBase;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.dispenser.IBehaviorDispenseItem;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class ItemMinecart extends Item {
   private static final IBehaviorDispenseItem dispenserMinecartBehavior = new ItemMinecart.DefaultBehaviorDefaultDispenseItem();
   private final EntityMinecart.EnumMinecartType minecartType;

   public static EntityMinecart.EnumMinecartType accessMinecartType(ItemMinecart itemMinecart) {
      return itemMinecart.minecartType;
   }

   public ItemMinecart(EntityMinecart.EnumMinecartType enumMinecartType) {
      this.maxStackSize = 1;
      this.minecartType = enumMinecartType;
      this.setCreativeTab(CreativeTabs.tabTransport);
      BlockDispenser.dispenseBehaviorRegistry.putObject(this, dispenserMinecartBehavior);
   }

   @Override
   public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, BlockPos pos, EnumFacing facing, float f, float f2, float f3) {
      IBlockState blockState = world.getBlockState(pos);
      if (BlockRailBase.isRailBlock(blockState)) {
         if (!world.isRemote) {
            BlockRailBase.EnumRailDirection enumRailDirection = blockState.getBlock() instanceof BlockRailBase
               ? (BlockRailBase.EnumRailDirection)blockState.getValue(((BlockRailBase)blockState.getBlock()).getShapeProperty())
               : BlockRailBase.EnumRailDirection.NORTH_SOUTH;
            double d = 0.0;
            if (enumRailDirection.isAscending()) {
               d = 0.5;
            }

            EntityMinecart entityMinecart = EntityMinecart.createMinecart(
               world, (double)pos.getX() + 0.5, (double)pos.getY() + 0.0625 + d, (double)pos.getZ() + 0.5, this.minecartType
            );
            if (stack.hasDisplayName()) {
               entityMinecart.setCustomNameTag(stack.getDisplayName());
            }

            world.spawnEntityInWorld(entityMinecart);
         }

         stack.stackSize--;
         return true;
      } else {
         return false;
      }
   }


   public static final class DefaultBehaviorDefaultDispenseItem extends BehaviorDefaultDispenseItem {
      private final BehaviorDefaultDispenseItem behaviourDefaultDispenseItem = new BehaviorDefaultDispenseItem();

      @Override
      public void playDispenseSound(IBlockSource blockSource) {
         blockSource.getWorld().playAuxSFX(1000, blockSource.getBlockPos(), 0);
      }

      @Override
      public ItemStack dispenseStack(IBlockSource blockSource, ItemStack stack) {
         EnumFacing facing = BlockDispenser.getFacing(blockSource.getBlockMetadata());
         World world = blockSource.getWorld();
         double d = blockSource.getX() + (double)facing.getFrontOffsetX() * 1.125;
         double d2 = Math.floor(blockSource.getY()) + (double)facing.getFrontOffsetY();
         double d3 = blockSource.getZ() + (double)facing.getFrontOffsetZ() * 1.125;
         BlockPos pos = blockSource.getBlockPos().offset(facing);
         IBlockState blockState = world.getBlockState(pos);
         BlockRailBase.EnumRailDirection enumRailDirection = blockState.getBlock() instanceof BlockRailBase
            ? (BlockRailBase.EnumRailDirection)blockState.getValue(((BlockRailBase)blockState.getBlock()).getShapeProperty())
            : BlockRailBase.EnumRailDirection.NORTH_SOUTH;
         double d4;
         if (BlockRailBase.isRailBlock(blockState)) {
            if (enumRailDirection.isAscending()) {
               d4 = 0.6;
            } else {
               d4 = 0.1;
            }
         } else {
            if (blockState.getBlock().getMaterial() != Material.air || !BlockRailBase.isRailBlock(world.getBlockState(pos.down()))) {
               return this.behaviourDefaultDispenseItem.dispense(blockSource, stack);
            }

            IBlockState blockState2 = world.getBlockState(pos.down());
            BlockRailBase.EnumRailDirection enumRailDirection2 = blockState2.getBlock() instanceof BlockRailBase
               ? (BlockRailBase.EnumRailDirection)blockState2.getValue(((BlockRailBase)blockState2.getBlock()).getShapeProperty())
               : BlockRailBase.EnumRailDirection.NORTH_SOUTH;
            if (facing != EnumFacing.DOWN && enumRailDirection2.isAscending()) {
               d4 = -0.4;
            } else {
               d4 = -0.9;
            }
         }

         EntityMinecart entityMinecart = EntityMinecart.createMinecart(world, d, d2 + d4, d3, ItemMinecart.accessMinecartType((ItemMinecart)stack.getItem()));
         if (stack.hasDisplayName()) {
            entityMinecart.setCustomNameTag(stack.getDisplayName());
         }

         world.spawnEntityInWorld(entityMinecart);
         stack.splitStack(1);
         return stack;
      }
   }
}
