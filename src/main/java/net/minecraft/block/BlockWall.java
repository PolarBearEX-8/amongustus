package net.minecraft.block;

import net.minecraft.util.IStringSerializable;
import java.util.List;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.StatCollector;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockWall extends Block {
   public static final PropertyBool UP = PropertyBool.create("up");
   public static final PropertyBool NORTH = PropertyBool.create("north");
   public static final PropertyBool EAST = PropertyBool.create("east");
   public static final PropertyBool SOUTH = PropertyBool.create("south");
   public static final PropertyBool WEST = PropertyBool.create("west");
   public static final PropertyEnum VARIANT = PropertyEnum.create("variant", BlockWall.EnumType.class);

   @Override
   public boolean isOpaqueCube() {
      return false;
   }

   @Override
   public IBlockState getActualState(IBlockState state, IBlockAccess world, int n, int n2, int n3) {
      return state.withProperty(UP, !world.isAirBlock(n, n2 + 1, n3))
         .withProperty(NORTH, this.canConnectTo(world, n, n2, n3 - 1))
         .withProperty(EAST, this.canConnectTo(world, n + 1, n2, n3))
         .withProperty(SOUTH, this.canConnectTo(world, n, n2, n3 + 1))
         .withProperty(WEST, this.canConnectTo(world, n - 1, n2, n3));
   }

   public boolean canConnectTo(IBlockAccess world, int n, int n2, int n3) {
      Block block = world.getBlockState2(n, n2, n3).getBlock();
      return block == Blocks.barrier
         ? false
         : (
            block != this && !(block instanceof BlockFenceGate)
               ? (block.blockMaterial.isOpaque() && block.isFullCube() ? block.blockMaterial != Material.gourd : false)
               : true
         );
   }

   @Override
   public String getLocalizedName() {
      return StatCollector.translateToLocal(this.getUnlocalizedName() + "." + BlockWall.EnumType.NORMAL.getUnlocalizedName() + ".name");
   }

   @Override
   public int getMetaFromState(IBlockState state) {
      return ((BlockWall.EnumType)state.getValue(VARIANT)).getMetadata();
   }

   public BlockWall(Block block) {
      super(block.blockMaterial);
      this.setDefaultState(
         this.blockState
            .getBaseState()
            .withProperty(UP, Boolean.FALSE)
            .withProperty(NORTH, Boolean.FALSE)
            .withProperty(EAST, Boolean.FALSE)
            .withProperty(SOUTH, Boolean.FALSE)
            .withProperty(WEST, Boolean.FALSE)
            .withProperty(VARIANT, BlockWall.EnumType.NORMAL)
      );
      this.setHardness(block.blockHardness);
      this.setResistance(block.blockResistance / 3.0F);
      this.setStepSound(block.stepSound);
      this.setCreativeTab(CreativeTabs.tabBlock);
   }

   @Override
   public boolean isFullCube() {
      return false;
   }

   @Override
   public boolean isPassable(IBlockAccess world, BlockPos pos) {
      return false;
   }

   @Override
   public AxisAlignedBB getCollisionBoundingBox(World world, IBlockState state, int n, int n2, int n3) {
      this.setBlockBoundsBasedOnState(world, n, n2, n3);
      this.maxY = 1.5;
      return super.getCollisionBoundingBox(world, state, n, n2, n3);
   }

   @Override
   public boolean shouldSideBeRendered(IBlockAccess world, int n, int n2, int n3, EnumFacing facing) {
      return facing != EnumFacing.DOWN || super.shouldSideBeRendered(world, n, n2, n3, facing);
   }

   @Override
   public void setBlockBoundsBasedOnState(IBlockAccess world, int n, int n2, int n3) {
      boolean flag = this.canConnectTo(world, n, n2, n3 - 1);
      boolean flag2 = this.canConnectTo(world, n, n2, n3 + 1);
      boolean flag3 = this.canConnectTo(world, n - 1, n2, n3);
      boolean flag4 = this.canConnectTo(world, n + 1, n2, n3);
      float f = 0.25F;
      float f2 = 0.75F;
      float f3 = 0.25F;
      float f4 = 0.75F;
      float f5 = 1.0F;
      if (flag) {
         f3 = 0.0F;
      }

      if (flag2) {
         f4 = 1.0F;
      }

      if (flag3) {
         f = 0.0F;
      }

      if (flag4) {
         f2 = 1.0F;
      }

      if (flag && flag2 && !flag3 && !flag4) {
         f5 = 0.8125F;
         f = 0.3125F;
         f2 = 0.6875F;
      } else if (!flag && !flag2 && flag3 && flag4) {
         f5 = 0.8125F;
         f3 = 0.3125F;
         f4 = 0.6875F;
      }

      this.setBlockBounds(f, 0.0F, f3, f2, f5, f4);
   }

   @Override
   public void getSubBlocks(Item item, CreativeTabs creativeTabs, List list) {
      for (BlockWall.EnumType enumType : BlockWall.EnumType.values()) {
         list.add(new ItemStack(item, 1, enumType.getMetadata()));
      }
   }

   @Override
   public BlockState createBlockState() {
      return new BlockState(this, new IProperty[]{UP, NORTH, EAST, WEST, SOUTH, VARIANT});
   }

   @Override
   public IBlockState getStateFromMeta(int n) {
      return this.getDefaultState().withProperty(VARIANT, BlockWall.EnumType.byMetadata(n));
   }

   @Override
   public int damageDropped(IBlockState state) {
      return ((BlockWall.EnumType)state.getValue(VARIANT)).getMetadata();
   }


   public enum EnumType implements IStringSerializable {
      NORMAL(0, "cobblestone", "normal"),
      MOSSY(1, "mossy_cobblestone", "mossy");

   private static final EnumType[] META_LOOKUP = new EnumType[values().length];
      private final int meta;
      private final String name;
      private String unlocalizedName;
   @Override
      public String getName() {
         return this.name;
      }

      public String getUnlocalizedName() {
         return this.unlocalizedName;
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

      private EnumType(int n, String str, String str2) {
         this.meta = n;
         this.name = str;
         this.unlocalizedName = str2;
      }

      static {
         for (EnumType enumType : values()) {
            META_LOOKUP[enumType.getMetadata()] = enumType;
         }
      }

      public int getMetadata() {
         return this.meta;
      }
   }
}
