package net.minecraft.block;

import client.setting.CombatTimeoutProperty;
import client.setting.CustomOptionType;
import client.setting.DefaultOptionType;
import client.util.DampenerTransformer;
import client.util.IBlockState;
import client.util.ImmutableSetEntry;
import java.util.List;
import java.util.Random;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.entity.EntitySilverfish;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroupManager;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class BlockMonsterEgg extends Block {
   public static final ImmutableSetEntry immutableSetEntry = ImmutableSetEntry.resolveImmutableSetEntry("variant", DefaultOptionType.class);

   @Override
   public void applyWorld3(World world, BlockPos blockPos, IBlockState blockState, float factor, int count) {
      if (!world.state && world.getDoFireTickManager().matchesText("doTileDrops")) {
         EntitySilverfish entitySilverfish = new EntitySilverfish(world);
         entitySilverfish.applyDoubleByDouble((double)blockPos.getX() + 0.5, (double)blockPos.getY(), (double)blockPos.getZ() + 0.5, 0.0F, 0.0F);
         world.matchesEntity(entitySilverfish);
         entitySilverfish.updateState44();
      }
   }

   @Override
   public int processAction2(World world, BlockPos blockPos) {
      IBlockState blockState = world.getBlockState(blockPos);
      return blockState.getBlock().getIndex(blockState);
   }

   @Override
   public IBlockState processAction(int count) {
      return this.getDefaultState().resolveIBlockState(immutableSetEntry, DefaultOptionType.resolveDefaultOptionType(count));
   }

   @Override
   public void processAction(Item item, ItemGroupManager itemGroupManager, List list) {
      for (DefaultOptionType defaultOptionType : DefaultOptionType.values()) {
         list.add(new ItemStack(item, 1, defaultOptionType.getCount()));
      }
   }

   @Override
   public int processAction(Random random) {
      return 0;
   }

   public static boolean matchesIBlockState(IBlockState blockState) {
      Block block = blockState.getBlock();
      return blockState == Blocks.stone.getDefaultState().resolveIBlockState(BlockStone.immutableSetEntry, CustomOptionType.STONE)
         || block == Blocks.block65
         || block == Blocks.block42;
   }

   @Override
   public BlockManager processAction11() {
      return new BlockManager(this, new IProperty[]{immutableSetEntry});
   }

   @Override
   public int getIndex(IBlockState blockState) {
      return ((DefaultOptionType)blockState.getComparable(immutableSetEntry)).getCount();
   }

   @Override
   public ItemStack resolveItemStack(IBlockState blockState) {
      switch (DampenerTransformer.countArray[((DefaultOptionType)blockState.getComparable(immutableSetEntry)).ordinal()]) {
         case 1:
            return new ItemStack(Blocks.block65);
         case 2:
            return new ItemStack(Blocks.block42);
         case 3:
            return new ItemStack(Blocks.block42, 1, CombatTimeoutProperty.MOSSY.getCount());
         case 4:
            return new ItemStack(Blocks.block42, 1, CombatTimeoutProperty.CRACKED.getCount());
         case 5:
            return new ItemStack(Blocks.block42, 1, CombatTimeoutProperty.CHISELED.getCount());
         default:
            return new ItemStack(Blocks.stone);
      }
   }

   public BlockMonsterEgg() {
      super(Material.material27);
      this.setIBlockState(this.blockManager.get().resolveIBlockState(immutableSetEntry, DefaultOptionType.STONE));
      this.getBlock5(0.0F);
      this.processAction(ItemGroupManager.itemGroupManager8);
   }
}
