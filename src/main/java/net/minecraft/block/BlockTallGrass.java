package net.minecraft.block;

import net.minecraft.util.IStringSerializable;
import java.util.List;
import java.util.Random;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.world.ColorizerGrass;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockTallGrass extends BlockBush implements IGrowable {
   public static final PropertyEnum TYPE;

   @Override
   public boolean isReplaceable(World world, BlockPos pos) {
      return true;
   }

   @Override
   public IBlockState getStateFromMeta(int n) {
      return this.getDefaultState().withProperty(TYPE, BlockTallGrass.EnumType.byMetadata(n));
   }

   @Override
   public int getDamageValue(World world, BlockPos pos) {
      IBlockState blockState2 = world.getBlockState(pos);
      return blockState2.getBlock().getMetaFromState(blockState2);
   }

   @Override
   public int getMetaFromState(IBlockState state) {
      return ((BlockTallGrass.EnumType)state.getValue(TYPE)).getMeta();
   }

   @Override
   public int getBlockColor() {
      return ColorizerGrass.getGrassColor(0.5, 1.0);
   }

   @Override
   public Block.EnumOffsetType getOffsetType() {
      return Block.EnumOffsetType.XYZ;
   }

   @Override
   public boolean canUseBonemeal(World world, Random rand, BlockPos pos, IBlockState state) {
      return true;
   }

   @Override
   public void harvestBlock(World world, EntityPlayer player, BlockPos pos, IBlockState state, TileEntity tileEntity) {
      if (!world.isRemote && player.getCurrentEquippedItem() != null && player.getCurrentEquippedItem().getItem() == Items.shears) {
         player.triggerAchievement(StatList.mineBlockStatArray[Block.getIdFromBlock(this)]);
         spawnAsEntity(world, pos, new ItemStack(Blocks.tallgrass, 1, ((BlockTallGrass.EnumType)state.getValue(TYPE)).getMeta()));
      } else {
         super.harvestBlock(world, player, pos, state, tileEntity);
      }
   }

   @Override
   public boolean canBlockStay(World world, BlockPos pos, IBlockState state) {
      return this.canPlaceBlockOn(world.getBlockState(pos.down()).getBlock());
   }

   @Override
   public void getSubBlocks(Item item, CreativeTabs creativeTabs, List list) {
      for (int i = 1; i < 3; i++) {
         list.add(new ItemStack(item, 1, i));
      }
   }

   @Override
   public boolean canGrow(World world, BlockPos pos, IBlockState state, boolean flag) {
      return state.getValue(TYPE) != BlockTallGrass.EnumType.DEAD_BUSH;
   }

   static {
      TYPE = PropertyEnum.create("type", BlockTallGrass.EnumType.class);
   }

   @Override
   public int getRenderColor(IBlockState state) {
      if (state.getBlock() != this) {
         return super.getRenderColor(state);
      } else {
         return ((BlockTallGrass.EnumType)state.getValue(TYPE)) == BlockTallGrass.EnumType.DEAD_BUSH ? 16777215 : ColorizerGrass.getGrassColor(0.5, 1.0);
      }
   }

   @Override
   public void grow(World world, Random rand, BlockPos pos, IBlockState state) {
      BlockDoublePlant.EnumPlantType grass = BlockDoublePlant.EnumPlantType.GRASS;
      if (state.getValue(TYPE) == BlockTallGrass.EnumType.FERN) {
         grass = BlockDoublePlant.EnumPlantType.FERN;
      }

      if (Blocks.double_plant.canPlaceBlockAt(world, pos)) {
         Blocks.double_plant.placeAt(world, pos, grass, 2);
      }
   }

   @Override
   public int colorMultiplier(IBlockAccess world, BlockPos pos, int n) {
      return world.getBiomeGenForCoords(pos).getGrassColorAtPos(pos);
   }

   public BlockTallGrass() {
      super(Material.vine);
      this.setDefaultState(this.blockState.getBaseState().withProperty(TYPE, BlockTallGrass.EnumType.DEAD_BUSH));
      float f = 0.4F;
      this.setBlockBounds(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, 0.8F, 0.5F + f);
   }

   @Override
   public BlockState createBlockState() {
      return new BlockState(this, new IProperty[]{TYPE});
   }

   @Override
   public int quantityDroppedWithBonus(int n, Random rand) {
      return 1 + rand.nextInt(n * 2 + 1);
   }

   @Override
   public Item getItemDropped(IBlockState state, Random rand, int n) {
      return rand.nextInt(8) == 0 ? Items.wheat_seeds : null;
   }


   public enum EnumType implements IStringSerializable {
      DEAD_BUSH(0, "dead_bush"),
      GRASS(1, "tall_grass"),
      FERN(2, "fern");

   private static final EnumType[] META_LOOKUP = new EnumType[values().length];
      private final int meta;
      private final String name;
   private EnumType(int n, String str) {
         this.meta = n;
         this.name = str;
      }

      public static EnumType byMetadata(int n) {
         if (n < 0 || n >= META_LOOKUP.length) {
            n = 0;
         }

         return META_LOOKUP[n];
      }

      @Override
      public String toString() {
         return this.name;
      }

      static {
         for (EnumType enumType : values()) {
            META_LOOKUP[enumType.getMeta()] = enumType;
         }
      }

      @Override
      public String getName() {
         return this.name;
      }

      public int getMeta() {
         return this.meta;
      }
   }
}
