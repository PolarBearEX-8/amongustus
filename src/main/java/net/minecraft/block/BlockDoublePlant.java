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
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeColorHelper;

public class BlockDoublePlant extends BlockBush implements IGrowable {
   public static final PropertyEnum VARIANT = PropertyEnum.create("variant", BlockDoublePlant.EnumPlantType.class);
   public static final PropertyEnum HALF = PropertyEnum.create("half", BlockDoublePlant.EnumBlockHalf.class);
   public static final PropertyEnum FACING = BlockDirectional.FACING;

   @Override
   public void getSubBlocks(Item item, CreativeTabs creativeTabs, List list) {
      for (BlockDoublePlant.EnumPlantType enumPlantType : BlockDoublePlant.EnumPlantType.values()) {
         list.add(new ItemStack(item, 1, enumPlantType.getMeta()));
      }
   }

   @Override
   public Block.EnumOffsetType getOffsetType() {
      return Block.EnumOffsetType.XZ;
   }

   @Override
   public boolean canPlaceBlockAt(World world, BlockPos pos) {
      return super.canPlaceBlockAt(world, pos) && world.isAirBlock(pos.up());
   }

   @Override
   public boolean canBlockStay(World world, BlockPos pos, IBlockState state) {
      if (state.getValue(HALF) == BlockDoublePlant.EnumBlockHalf.UPPER) {
         return world.getBlockState(pos.down()).getBlock() == this;
      } else {
         IBlockState blockState2 = world.getBlockState(pos.up());
         return blockState2.getBlock() == this && super.canBlockStay(world, pos, blockState2);
      }
   }

   @Override
   public BlockState createBlockState() {
      return new BlockState(this, new IProperty[]{HALF, VARIANT, FACING});
   }

   public BlockDoublePlant() {
      super(Material.vine);
      this.setDefaultState(
         this.blockState
            .getBaseState()
            .withProperty(VARIANT, BlockDoublePlant.EnumPlantType.SUNFLOWER)
            .withProperty(HALF, BlockDoublePlant.EnumBlockHalf.LOWER)
            .withProperty(FACING, EnumFacing.NORTH)
      );
      this.setHardness(0.0F);
      this.setStepSound(soundTypeGrass);
      this.setUnlocalizedName("doublePlant");
   }

   @Override
   public Item getItemDropped(IBlockState state, Random rand, int n) {
      if (state.getValue(HALF) == BlockDoublePlant.EnumBlockHalf.UPPER) {
         return null;
      } else {
         BlockDoublePlant.EnumPlantType value = (BlockDoublePlant.EnumPlantType)state.getValue(VARIANT);
         return value == BlockDoublePlant.EnumPlantType.FERN
            ? null
            : (value == BlockDoublePlant.EnumPlantType.GRASS ? (rand.nextInt(8) == 0 ? Items.wheat_seeds : null) : Item.getItemFromBlock(this));
      }
   }

   public void placeAt(World world, BlockPos pos, BlockDoublePlant.EnumPlantType enumPlantType, int n) {
      world.setBlockState(pos, this.getDefaultState().withProperty(HALF, BlockDoublePlant.EnumBlockHalf.LOWER).withProperty(VARIANT, enumPlantType), n);
      world.setBlockState(pos.up(), this.getDefaultState().withProperty(HALF, BlockDoublePlant.EnumBlockHalf.UPPER), n);
   }

   @Override
   public void onBlockPlacedBy(World world, BlockPos pos, IBlockState state, EntityLivingBase entity, ItemStack stack) {
      world.setBlockState(pos.up(), this.getDefaultState().withProperty(HALF, BlockDoublePlant.EnumBlockHalf.UPPER), 2);
   }

   @Override
   public boolean canUseBonemeal(World world, Random rand, BlockPos pos, IBlockState state) {
      return true;
   }

   @Override
   public void harvestBlock(World world, EntityPlayer player, BlockPos pos, IBlockState state, TileEntity tileEntity) {
      if (world.isRemote
         || player.getCurrentEquippedItem() == null
         || player.getCurrentEquippedItem().getItem() != Items.shears
         || state.getValue(HALF) != BlockDoublePlant.EnumBlockHalf.LOWER
         || !this.onHarvest(world, pos, state, player)) {
         super.harvestBlock(world, player, pos, state, tileEntity);
      }
   }

   @Override
   public boolean canGrow(World world, BlockPos pos, IBlockState state, boolean flag) {
      BlockDoublePlant.EnumPlantType variant = this.getVariant(world, pos);
      return variant != BlockDoublePlant.EnumPlantType.GRASS && variant != BlockDoublePlant.EnumPlantType.FERN;
   }

   @Override
   public int getMetaFromState(IBlockState state) {
      return state.getValue(HALF) == BlockDoublePlant.EnumBlockHalf.UPPER
         ? 8 | ((EnumFacing)state.getValue(FACING)).getHorizontalIndex()
         : ((BlockDoublePlant.EnumPlantType)state.getValue(VARIANT)).getMeta();
   }

   @Override
   public boolean isReplaceable(World world, BlockPos pos) {
      IBlockState blockState2 = world.getBlockState(pos);
      if (blockState2.getBlock() != this) {
         return true;
      } else {
         BlockDoublePlant.EnumPlantType value = (BlockDoublePlant.EnumPlantType)this.getActualState(blockState2, world, pos.getX(), pos.getY(), pos.getZ()).getValue(VARIANT);
         return value == BlockDoublePlant.EnumPlantType.FERN || value == BlockDoublePlant.EnumPlantType.GRASS;
      }
   }

   private boolean onHarvest(World world, BlockPos pos, IBlockState state, EntityPlayer player) {
      BlockDoublePlant.EnumPlantType value = (BlockDoublePlant.EnumPlantType)state.getValue(VARIANT);
      if (value != BlockDoublePlant.EnumPlantType.FERN && value != BlockDoublePlant.EnumPlantType.GRASS) {
         return false;
      } else {
         player.triggerAchievement(StatList.mineBlockStatArray[Block.getIdFromBlock(this)]);
         int meta = (value == BlockDoublePlant.EnumPlantType.GRASS ? BlockTallGrass.EnumType.GRASS : BlockTallGrass.EnumType.FERN).getMeta();
         spawnAsEntity(world, pos, new ItemStack(Blocks.tallgrass, 2, meta));
         return true;
      }
   }

   @Override
   public void checkAndDropBlock(World world, BlockPos pos, IBlockState state) {
      if (!this.canBlockStay(world, pos, state)) {
         boolean flag = state.getValue(HALF) == BlockDoublePlant.EnumBlockHalf.UPPER;
         BlockPos pos2 = flag ? pos : pos.up();
         BlockPos pos3 = flag ? pos.down() : pos;
         Object obj = flag ? this : world.getBlockState(pos2).getBlock();
         Object obj2 = flag ? world.getBlockState(pos3).getBlock() : this;
         if (obj == this) {
            world.setBlockState(pos2, Blocks.air.getDefaultState(), 2);
         }

         if (obj2 == this) {
            world.setBlockState(pos3, Blocks.air.getDefaultState(), 3);
            if (!flag) {
               this.dropBlockAsItem(world, pos3, state, 0);
            }
         }
      }
   }

   @Override
   public void onBlockHarvested(World world, BlockPos pos, IBlockState state, EntityPlayer player) {
      if (state.getValue(HALF) == BlockDoublePlant.EnumBlockHalf.UPPER) {
         if (world.getBlockState(pos.down()).getBlock() == this) {
            if (!player.capabilities.isCreativeMode) {
               IBlockState blockState2 = world.getBlockState(pos.down());
               BlockDoublePlant.EnumPlantType value = (BlockDoublePlant.EnumPlantType)blockState2.getValue(VARIANT);
               if (value != BlockDoublePlant.EnumPlantType.FERN && value != BlockDoublePlant.EnumPlantType.GRASS) {
                  world.destroyBlock(pos.down(), true);
               } else if (!world.isRemote) {
                  if (player.getCurrentEquippedItem() != null && player.getCurrentEquippedItem().getItem() == Items.shears) {
                     this.onHarvest(world, pos, blockState2, player);
                     world.setBlockToAir(pos.down());
                  } else {
                     world.destroyBlock(pos.down(), true);
                  }
               } else {
                  world.setBlockToAir(pos.down());
               }
            } else {
               world.setBlockToAir(pos.down());
            }
         }
      } else if (player.capabilities.isCreativeMode && world.getBlockState(pos.up()).getBlock() == this) {
         world.setBlockState(pos.up(), Blocks.air.getDefaultState(), 2);
      }

      super.onBlockHarvested(world, pos, state, player);
   }

   @Override
   public void setBlockBoundsBasedOnState(IBlockAccess world, int n, int n2, int n3) {
      this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
   }

   public BlockDoublePlant.EnumPlantType getVariant(IBlockAccess world, BlockPos pos) {
      IBlockState blockState2 = world.getBlockState(pos);
      if (blockState2.getBlock() == this) {
         blockState2 = this.getActualState(blockState2, world, pos.getX(), pos.getY(), pos.getZ());
         return (BlockDoublePlant.EnumPlantType)blockState2.getValue(VARIANT);
      } else {
         return BlockDoublePlant.EnumPlantType.FERN;
      }
   }

   @Override
   public void grow(World world, Random rand, BlockPos pos, IBlockState state) {
      spawnAsEntity(world, pos, new ItemStack(this, 1, this.getVariant(world, pos).getMeta()));
   }

   @Override
   public int colorMultiplier(IBlockAccess world, BlockPos pos, int n) {
      BlockDoublePlant.EnumPlantType variant = this.getVariant(world, pos);
      return variant != BlockDoublePlant.EnumPlantType.GRASS && variant != BlockDoublePlant.EnumPlantType.FERN
         ? 16777215
         : BiomeColorHelper.getGrassColorAtPos(world, pos);
   }

   @Override
   public int getDamageValue(World world, BlockPos pos) {
      return this.getVariant(world, pos).getMeta();
   }

   @Override
   public IBlockState getStateFromMeta(int n) {
      return (n & 8) > 0
         ? this.getDefaultState().withProperty(HALF, BlockDoublePlant.EnumBlockHalf.UPPER)
         : this.getDefaultState()
            .withProperty(HALF, BlockDoublePlant.EnumBlockHalf.LOWER)
            .withProperty(VARIANT, BlockDoublePlant.EnumPlantType.byMetadata(n & 7));
   }

   @Override
   public IBlockState getActualState(IBlockState state, IBlockAccess world, int n, int n2, int n3) {
      if (state.getValue(HALF) == BlockDoublePlant.EnumBlockHalf.UPPER) {
         IBlockState blockState2 = world.getBlockState2(n, n2 - 1, n3);
         if (blockState2.getBlock() == this) {
            state = state.withProperty(VARIANT, blockState2.getValue(VARIANT));
         }
      }

      return state;
   }

   @Override
   public int damageDropped(IBlockState state) {
      return state.getValue(HALF) != BlockDoublePlant.EnumBlockHalf.UPPER && state.getValue(VARIANT) != BlockDoublePlant.EnumPlantType.GRASS
         ? ((BlockDoublePlant.EnumPlantType)state.getValue(VARIANT)).getMeta()
         : 0;
   }


   public enum EnumBlockHalf implements IStringSerializable {
      UPPER,
      LOWER;

   @Override
      public String getName() {
         return this == UPPER ? "upper" : "lower";
      }

      @Override
      public String toString() {
         return this.getName();
      }
   }

   public enum EnumPlantType implements IStringSerializable {
      SUNFLOWER(0, "sunflower"),
      SYRINGA(1, "syringa"),
      GRASS(2, "double_grass", "grass"),
      FERN(3, "double_fern", "fern"),
      ROSE(4, "double_rose", "rose"),
      PAEONIA(5, "paeonia");

   private static final EnumPlantType[] META_LOOKUP = new EnumPlantType[values().length];
      private final int meta;
      private final String name;
      private final String unlocalizedName;
   public static EnumPlantType byMetadata(int n) {
         if (n < 0 || n >= META_LOOKUP.length) {
            n = 0;
         }

         return META_LOOKUP[n];
      }

      public String getUnlocalizedName() {
         return this.unlocalizedName;
      }

      private EnumPlantType(int n, String str) {
         this(n, str, str);
      }

      @Override
      public String getName() {
         return this.name;
      }

      @Override
      public String toString() {
         return this.name;
      }

      public int getMeta() {
         return this.meta;
      }

      private EnumPlantType(int n, String str, String str2) {
         this.meta = n;
         this.name = str;
         this.unlocalizedName = str2;
      }

      static {
         for (EnumPlantType enumPlantType : values()) {
            META_LOOKUP[enumPlantType.getMeta()] = enumPlantType;
         }
      }
   }
}
