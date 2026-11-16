package net.minecraft.block;

import client.setting.BlockSolidType;
import client.setting.CombatConfigProperty;
import client.util.IBlockState;
import client.util.LvlManager;
import client.util.StatCraftItemManager;
import java.util.Random;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemGroupManager;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class BlockIce extends ExtendedBlockAdapter {
   @Override
   public void updateTick(World world, BlockPos blockPos, IBlockState blockState, Random random) {
      if (world.computeCount(CombatConfigProperty.BLOCK, blockPos.getX(), blockPos.getY(), blockPos.getZ()) > 11 - this.getIndexOffset()) {
         if (world.secondaryFloatArrayEntry.isEnabled2()) {
            world.matchesBlockPos8(blockPos);
         } else {
            this.processAction(world, blockPos, world.getBlockState(blockPos), 0);
            world.matchesBlockPos(blockPos, Blocks.blockLava2.getDefaultState());
         }
      }
   }

   @Override
   public BlockSolidType processAction3() {
      return BlockSolidType.TRANSLUCENT;
   }

   @Override
   public int processAction8() {
      return 0;
   }

   @Override
   public int processAction(Random random) {
      return 0;
   }

   @Override
   public void applyWorld(World world, EntityPlayer player, BlockPos blockPos, IBlockState blockState, TileEntity tileEntity) {
      player.applyDuplicateStatIdManagerByDuplicateStatIdM(StatCraftItemManager.duplicateStatIdManagerArray4[Block.getIdFromBlock(this)]);
      player.applyFloat4(0.025F);
      if (this.isActive() && LvlManager.matchesEntityLivingBase2(player)) {
         ItemStack stack = this.resolveItemStack(blockState);
         if (stack != null) {
            applyWorld(world, blockPos, stack);
         }
      } else {
         if (world.secondaryFloatArrayEntry.isEnabled2()) {
            world.matchesBlockPos8(blockPos);
            return;
         }

         int count = LvlManager.computeCountByEntityLivingBase(player);
         this.processAction(world, blockPos, blockState, count);
         Material material = world.getBlockState(blockPos.down()).getBlock().getMaterial();
         if (material.isActiveAsFlag() || material.isActive2()) {
            world.matchesBlockPos(blockPos, Blocks.blockFlowingLava.getDefaultState());
         }
      }
   }

   public BlockIce() {
      super(Material.material12, false);
      this.scaleFactor = 0.98F;
      this.processAction(true);
      this.processAction(ItemGroupManager.itemGroupManager10);
   }
}
