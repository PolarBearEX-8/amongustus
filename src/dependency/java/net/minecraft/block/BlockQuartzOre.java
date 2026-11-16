package net.minecraft.block;

import client.setting.LocalOptionType;
import client.util.IBlockState;
import java.util.Random;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroupManager;
import net.minecraft.item.Items;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class BlockQuartzOre extends Block {
   @Override
   public void applyWorld3(World world, BlockPos blockPos, IBlockState blockState, float factor, int count) {
      super.applyWorld3(world, blockPos, blockState, factor, count);
      if (this.processAction(blockState, world.random, count) != Item.processAction2(this)) {
         int count2 = 0;
         if (this == Blocks.block89) {
            count2 = MathHelper.nextInt(world.random, 0, 2);
         } else if (this == Blocks.block44) {
            count2 = MathHelper.nextInt(world.random, 3, 7);
         } else if (this == Blocks.block70) {
            count2 = MathHelper.nextInt(world.random, 3, 7);
         } else if (this == Blocks.block47) {
            count2 = MathHelper.nextInt(world.random, 2, 5);
         } else if (this == Blocks.block4) {
            count2 = MathHelper.nextInt(world.random, 2, 5);
         }

         this.applyWorld(world, blockPos, count2);
      }
   }

   @Override
   public int processAction2(World world, BlockPos blockPos) {
      return 0;
   }

   @Override
   public Item processAction(IBlockState blockState, Random random, int count) {
      return this == Blocks.block89
         ? Items.coal
         : (
            this == Blocks.block44
               ? Items.diamond
               : (
                  this == Blocks.block47
                     ? Items.dye
                     : (this == Blocks.block70 ? Items.emerald : (this == Blocks.block4 ? Items.quartz : Item.processAction2(this)))
               )
         );
   }

   @Override
   public int processAction(Random random) {
      return this == Blocks.block47 ? 4 + random.nextInt(5) : 1;
   }

   @Override
   public int processAction(int count, Random random) {
      if (count > 0 && Item.processAction2(this) != this.processAction((IBlockState)this.getBlockManager().getImmutableList().iterator().next(), random, count)) {
         int count2 = random.nextInt(count + 2) - 1;
         if (count2 < 0) {
            count2 = 0;
         }

         return this.processAction(random) * (count2 + 1);
      } else {
         return this.processAction(random);
      }
   }

   @Override
   public int getMetaFromState(IBlockState blockState) {
      return this == Blocks.block47 ? LocalOptionType.BLUE.getCount2() : 0;
   }

   public BlockQuartzOre(MapColor mapColor) {
      super(Material.material4, mapColor);
      this.processAction(ItemGroupManager.itemGroupManager10);
   }

   public BlockQuartzOre() {
      this(Material.material4.getMapColor());
   }
}
