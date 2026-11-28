package net.minecraft.block;

import java.util.List;
import java.util.Random;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public abstract class BlockWoodSlab extends BlockSlab {
   public static final PropertyEnum VARIANT;

   @Override
   public int damageDropped(IBlockState state) {
      return ((BlockPlanks.EnumType)state.getValue(VARIANT)).getMetadata();
   }

   @Override
   public Item getItemDropped(IBlockState state, Random rand, int n) {
      return Item.getItemFromBlock(Blocks.wooden_slab);
   }

   @Override
   public int getMetaFromState(IBlockState state) {
      int n = 0;
      n |= ((BlockPlanks.EnumType)state.getValue(VARIANT)).getMetadata();
      if (!this.isDouble() && state.getValue(HALF) == BlockSlab.EnumBlockHalf.TOP) {
         n |= 8;
      }

      return n;
   }

   @Override
   public String getUnlocalizedName(int n) {
      return super.getUnlocalizedName() + "." + BlockPlanks.EnumType.byMetadata(n).getUnlocalizedName();
   }

   static {
      VARIANT = PropertyEnum.create("variant", BlockPlanks.EnumType.class);
   }

   @Override
   public IBlockState getStateFromMeta(int n) {
      IBlockState state = this.getDefaultState().withProperty(VARIANT, BlockPlanks.EnumType.byMetadata(n & 7));
      if (!this.isDouble()) {
         state = state.withProperty(HALF, (n & 8) == 0 ? BlockSlab.EnumBlockHalf.BOTTOM : BlockSlab.EnumBlockHalf.TOP);
      }

      return state;
   }

   @Override
   public IProperty getVariantProperty() {
      return VARIANT;
   }

   @Override
   public void getSubBlocks(Item item, CreativeTabs creativeTabs, List list) {
      if (item != Item.getItemFromBlock(Blocks.double_wooden_slab)) {
         for (BlockPlanks.EnumType enumType : BlockPlanks.EnumType.values()) {
            list.add(new ItemStack(item, 1, enumType.getMetadata()));
         }
      }
   }

   public BlockWoodSlab() {
      super(Material.wood);
      IBlockState baseState = this.blockState.getBaseState();
      if (!this.isDouble()) {
         baseState = baseState.withProperty(HALF, BlockSlab.EnumBlockHalf.BOTTOM);
      }

      this.setDefaultState(baseState.withProperty(VARIANT, BlockPlanks.EnumType.OAK));
      this.setCreativeTab(CreativeTabs.tabBlock);
   }

   @Override
   public Object getVariant(ItemStack stack) {
      return BlockPlanks.EnumType.byMetadata(stack.getMetadata() & 7);
   }

   @Override
   public BlockState createBlockState() {
      return this.isDouble() ? new BlockState(this, new IProperty[]{VARIANT}) : new BlockState(this, new IProperty[]{HALF, VARIANT});
   }

   @Override
   public Item getItem(World world, BlockPos pos) {
      return Item.getItemFromBlock(Blocks.wooden_slab);
   }

   @Override
   public MapColor getMapColor(IBlockState state) {
      return ((BlockPlanks.EnumType)state.getValue(VARIANT)).getMapColor();
   }
}
