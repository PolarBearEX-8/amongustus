package net.minecraft.block;

import java.util.Random;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class BlockOre extends Block {
   public BlockOre(MapColor mapColor) {
      super(Material.rock, mapColor);
      this.setCreativeTab(CreativeTabs.tabBlock);
   }

   @Override
   public int damageDropped(IBlockState state) {
      return this == Blocks.lapis_ore ? EnumDyeColor.BLUE.getDyeDamage() : 0;
   }

   public BlockOre() {
      this(Material.rock.getMaterialMapColor());
   }

   @Override
   public void dropBlockAsItemWithChance(World world, BlockPos pos, IBlockState state, float f, int n) {
      super.dropBlockAsItemWithChance(world, pos, state, f, n);
      if (this.getItemDropped(state, world.rand, n) != Item.getItemFromBlock(this)) {
         int n2 = 0;
         if (this == Blocks.coal_ore) {
            n2 = MathHelper.getRandomIntegerInRange(world.rand, 0, 2);
         } else if (this == Blocks.diamond_ore) {
            n2 = MathHelper.getRandomIntegerInRange(world.rand, 3, 7);
         } else if (this == Blocks.emerald_ore) {
            n2 = MathHelper.getRandomIntegerInRange(world.rand, 3, 7);
         } else if (this == Blocks.lapis_ore) {
            n2 = MathHelper.getRandomIntegerInRange(world.rand, 2, 5);
         } else if (this == Blocks.quartz_ore) {
            n2 = MathHelper.getRandomIntegerInRange(world.rand, 2, 5);
         }

         this.dropXpOnBlockBreak(world, pos, n2);
      }
   }

   @Override
   public int quantityDropped(Random rand) {
      return this == Blocks.lapis_ore ? 4 + rand.nextInt(5) : 1;
   }

   @Override
   public int getDamageValue(World world, BlockPos pos) {
      return 0;
   }

   @Override
   public Item getItemDropped(IBlockState state, Random rand, int n) {
      return this == Blocks.coal_ore
         ? Items.coal
         : (
            this == Blocks.diamond_ore
               ? Items.diamond
               : (
                  this == Blocks.lapis_ore
                     ? Items.dye
                     : (this == Blocks.emerald_ore ? Items.emerald : (this == Blocks.quartz_ore ? Items.quartz : Item.getItemFromBlock(this)))
               )
         );
   }

   @Override
   public int quantityDroppedWithBonus(int n, Random rand) {
      if (n > 0 && Item.getItemFromBlock(this) != this.getItemDropped((IBlockState)this.getBlockState().getValidStates().iterator().next(), rand, n)) {
         int n2 = rand.nextInt(n + 2) - 1;
         if (n2 < 0) {
            n2 = 0;
         }

         return this.quantityDropped(rand) * (n2 + 1);
      } else {
         return this.quantityDropped(rand);
      }
   }
}
