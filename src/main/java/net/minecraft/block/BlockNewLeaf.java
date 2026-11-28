package net.minecraft.block;

import com.google.common.base.Predicate;
import java.util.List;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class BlockNewLeaf extends BlockLeaves {
   public static final PropertyEnum VARIANT;

   @Override
   public void dropApple(World world, BlockPos pos, IBlockState state, int n) {
      if (state.getValue(VARIANT) == BlockPlanks.EnumType.DARK_OAK && world.rand.nextInt(n) == 0) {
         spawnAsEntity(world, pos, new ItemStack(Items.apple, 1, 0));
      }
   }

   @Override
   public BlockPlanks.EnumType getWoodType(int n) {
      return BlockPlanks.EnumType.byMetadata((n & 3) + 4);
   }

   @Override
   public int damageDropped(IBlockState state) {
      return ((BlockPlanks.EnumType)state.getValue(VARIANT)).getMetadata();
   }

   @Override
   public IBlockState getStateFromMeta(int n) {
      return this.getDefaultState()
         .withProperty(VARIANT, this.getWoodType(n))
         .withProperty(DECAYABLE, (n & 4) == 0)
         .withProperty(CHECK_DECAY, (n & 8) > 0);
   }

   static {
      VARIANT = PropertyEnum.create("variant", BlockPlanks.EnumType.class, new BlockNewLeaf.FilterPredicate());
   }

   @Override
   public ItemStack createStackedBlock(IBlockState state) {
      return new ItemStack(Item.getItemFromBlock(this), 1, ((BlockPlanks.EnumType)state.getValue(VARIANT)).getMetadata() - 4);
   }

   @Override
   public int getDamageValue(World world, BlockPos pos) {
      IBlockState blockState2 = world.getBlockState(pos);
      return blockState2.getBlock().getMetaFromState(blockState2) & 3;
   }

   @Override
   public int getMetaFromState(IBlockState state) {
      int n = 0;
      n |= ((BlockPlanks.EnumType)state.getValue(VARIANT)).getMetadata() - 4;
      if (!(Boolean)state.getValue(DECAYABLE)) {
         n |= 4;
      }

      if ((Boolean)state.getValue(CHECK_DECAY)) {
         n |= 8;
      }

      return n;
   }

   @Override
   public void getSubBlocks(Item item, CreativeTabs creativeTabs, List list) {
      list.add(new ItemStack(item, 1, 0));
      list.add(new ItemStack(item, 1, 1));
   }

   @Override
   public void harvestBlock(World world, EntityPlayer player, BlockPos pos, IBlockState state, TileEntity tileEntity) {
      if (!world.isRemote && player.getCurrentEquippedItem() != null && player.getCurrentEquippedItem().getItem() == Items.shears) {
         player.triggerAchievement(StatList.mineBlockStatArray[Block.getIdFromBlock(this)]);
         spawnAsEntity(world, pos, new ItemStack(Item.getItemFromBlock(this), 1, ((BlockPlanks.EnumType)state.getValue(VARIANT)).getMetadata() - 4));
      } else {
         super.harvestBlock(world, player, pos, state, tileEntity);
      }
   }

   @Override
   public BlockState createBlockState() {
      return new BlockState(this, new IProperty[]{VARIANT, CHECK_DECAY, DECAYABLE});
   }

   public BlockNewLeaf() {
      this.setDefaultState(
         this.blockState
            .getBaseState()
            .withProperty(VARIANT, BlockPlanks.EnumType.ACACIA)
            .withProperty(CHECK_DECAY, Boolean.TRUE)
            .withProperty(DECAYABLE, Boolean.TRUE)
      );
   }


   public static final class FilterPredicate implements Predicate {
      public boolean apply(BlockPlanks.EnumType enumType) {
         return enumType.getMetadata() >= 4;
      }

      public boolean apply(Object obj) {
         return this.apply((BlockPlanks.EnumType)obj);
      }
   }
}
