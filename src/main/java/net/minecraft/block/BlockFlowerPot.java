package net.minecraft.block;

import net.minecraft.util.IStringSerializable;
import java.util.Random;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityFlowerPot;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumWorldBlockLayer;
import net.minecraft.util.StatCollector;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockFlowerPot extends BlockContainer {
   public static final PropertyInteger LEGACY_DATA = PropertyInteger.create("legacy_data", 0, 15);
   public static final PropertyEnum CONTENTS = PropertyEnum.create("contents", BlockFlowerPot.EnumFlowerType.class);

   @Override
   public IBlockState getActualState(IBlockState state, IBlockAccess world, int n, int n2, int n3) {
      BlockFlowerPot.EnumFlowerType empty = BlockFlowerPot.EnumFlowerType.EMPTY;
      TileEntity tileEntity = world.getTileEntity(new BlockPos(n, n2, n3));
      if (tileEntity instanceof TileEntityFlowerPot) {
         TileEntityFlowerPot tileEntityFlowerPot = (TileEntityFlowerPot)tileEntity;
         Item flowerPotItem = tileEntityFlowerPot.getFlowerPotItem();
         if (flowerPotItem instanceof ItemBlock) {
            int flowerPotData = tileEntityFlowerPot.getFlowerPotData();
            Block blockFromItem = Block.getBlockFromItem(flowerPotItem);
            if (blockFromItem == Blocks.sapling) {
               switch (BlockPlanks.EnumType.byMetadata(flowerPotData)) {
                  case OAK:
                     empty = BlockFlowerPot.EnumFlowerType.OAK_SAPLING;
                     break;
                  case SPRUCE:
                     empty = BlockFlowerPot.EnumFlowerType.SPRUCE_SAPLING;
                     break;
                  case BIRCH:
                     empty = BlockFlowerPot.EnumFlowerType.BIRCH_SAPLING;
                     break;
                  case JUNGLE:
                     empty = BlockFlowerPot.EnumFlowerType.JUNGLE_SAPLING;
                     break;
                  case ACACIA:
                     empty = BlockFlowerPot.EnumFlowerType.ACACIA_SAPLING;
                     break;
                  case DARK_OAK:
                     empty = BlockFlowerPot.EnumFlowerType.DARK_OAK_SAPLING;
                     break;
                  default:
                     empty = BlockFlowerPot.EnumFlowerType.EMPTY;
               }
            } else if (blockFromItem == Blocks.tallgrass) {
               switch (flowerPotData) {
                  case 0:
                     empty = BlockFlowerPot.EnumFlowerType.DEAD_BUSH;
                     break;
                  case 2:
                     empty = BlockFlowerPot.EnumFlowerType.FERN;
                     break;
                  default:
                     empty = BlockFlowerPot.EnumFlowerType.EMPTY;
               }
            } else if (blockFromItem == Blocks.yellow_flower) {
               empty = BlockFlowerPot.EnumFlowerType.DANDELION;
            } else if (blockFromItem == Blocks.red_flower) {
               switch (BlockFlower.EnumFlowerType.getType(BlockFlower.EnumFlowerColor.RED, flowerPotData)) {
                  case POPPY:
                     empty = BlockFlowerPot.EnumFlowerType.POPPY;
                     break;
                  case BLUE_ORCHID:
                     empty = BlockFlowerPot.EnumFlowerType.BLUE_ORCHID;
                     break;
                  case ALLIUM:
                     empty = BlockFlowerPot.EnumFlowerType.ALLIUM;
                     break;
                  case HOUSTONIA:
                     empty = BlockFlowerPot.EnumFlowerType.HOUSTONIA;
                     break;
                  case RED_TULIP:
                     empty = BlockFlowerPot.EnumFlowerType.RED_TULIP;
                     break;
                  case ORANGE_TULIP:
                     empty = BlockFlowerPot.EnumFlowerType.ORANGE_TULIP;
                     break;
                  case WHITE_TULIP:
                     empty = BlockFlowerPot.EnumFlowerType.WHITE_TULIP;
                     break;
                  case PINK_TULIP:
                     empty = BlockFlowerPot.EnumFlowerType.PINK_TULIP;
                     break;
                  case OXEYE_DAISY:
                     empty = BlockFlowerPot.EnumFlowerType.OXEYE_DAISY;
                     break;
                  default:
                     empty = BlockFlowerPot.EnumFlowerType.EMPTY;
               }
            } else if (blockFromItem == Blocks.red_mushroom) {
               empty = BlockFlowerPot.EnumFlowerType.MUSHROOM_RED;
            } else if (blockFromItem == Blocks.brown_mushroom) {
               empty = BlockFlowerPot.EnumFlowerType.MUSHROOM_BROWN;
            } else if (blockFromItem == Blocks.deadbush) {
               empty = BlockFlowerPot.EnumFlowerType.DEAD_BUSH;
            } else if (blockFromItem == Blocks.cactus) {
               empty = BlockFlowerPot.EnumFlowerType.CACTUS;
            }
         }
      }

      return state.withProperty(CONTENTS, empty);
   }

   @Override
   public void onNeighborBlockChange(World world, BlockPos pos, IBlockState state, Block block) {
      if (!World.doesBlockHaveSolidTopSurface(world, pos.getX(), pos.getY() - 1, pos.getZ())) {
         this.dropBlockAsItem(world, pos, state, 0);
         world.setBlockToAir(pos);
      }
   }

   public BlockFlowerPot() {
      super(Material.circuits);
      this.setDefaultState(this.blockState.getBaseState().withProperty(CONTENTS, BlockFlowerPot.EnumFlowerType.EMPTY).withProperty(LEGACY_DATA, 0));
      this.setBlockBoundsForItemRender();
   }

   @Override
   public void onBlockHarvested(World world, BlockPos pos, IBlockState state, EntityPlayer player) {
      super.onBlockHarvested(world, pos, state, player);
      if (player.capabilities.isCreativeMode) {
         TileEntityFlowerPot tileEntity = this.getTileEntity(world, pos);
         if (tileEntity != null) {
            tileEntity.setFlowerPotData((Item)null, 0);
         }
      }
   }

   private TileEntityFlowerPot getTileEntity(World world, BlockPos pos) {
      TileEntity tileEntity = world.getTileEntity(pos);
      return tileEntity instanceof TileEntityFlowerPot ? (TileEntityFlowerPot)tileEntity : null;
   }

   @Override
   public void breakBlock(World world, BlockPos pos, IBlockState state) {
      TileEntityFlowerPot tileEntity = this.getTileEntity(world, pos);
      if (tileEntity != null && tileEntity.getFlowerPotItem() != null) {
         spawnAsEntity(world, pos, new ItemStack(tileEntity.getFlowerPotItem(), 1, tileEntity.getFlowerPotData()));
      }

      super.breakBlock(world, pos, state);
   }

   @Override
   public int getRenderType() {
      return 3;
   }

   @Override
   public boolean isOpaqueCube() {
      return false;
   }

   @Override
   public boolean canPlaceBlockAt(World world, BlockPos pos) {
      return super.canPlaceBlockAt(world, pos) && World.doesBlockHaveSolidTopSurface(world, pos.getX(), pos.getY() - 1, pos.getZ());
   }

   @Override
   public int colorMultiplier(IBlockAccess world, BlockPos pos, int n) {
      TileEntity tileEntity = world.getTileEntity(pos);
      if (tileEntity instanceof TileEntityFlowerPot) {
         Item flowerPotItem = ((TileEntityFlowerPot)tileEntity).getFlowerPotItem();
         if (flowerPotItem instanceof ItemBlock) {
            return Block.getBlockFromItem(flowerPotItem).colorMultiplier(world, pos, n);
         }
      }

      return 16777215;
   }

   @Override
   public void setBlockBoundsForItemRender() {
      float f = 0.375F;
      float f2 = f / 2.0F;
      this.setBlockBounds(0.5F - f2, 0.0F, 0.5F - f2, 0.5F + f2, f, 0.5F + f2);
   }

   @Override
   public int getMetaFromState(IBlockState state) {
      return (Integer)state.getValue(LEGACY_DATA);
   }

   @Override
   public String getLocalizedName() {
      return StatCollector.translateToLocal("item.flowerPot.name");
   }

   @Override
   public BlockState createBlockState() {
      return new BlockState(this, new IProperty[]{CONTENTS, LEGACY_DATA});
   }

   @Override
   public int getDamageValue(World world, BlockPos pos) {
      TileEntityFlowerPot tileEntity = this.getTileEntity(world, pos);
      return tileEntity != null && tileEntity.getFlowerPotItem() != null ? tileEntity.getFlowerPotData() : 0;
   }

   @Override
   public TileEntity createNewTileEntity(World world, int n) {
      Object obj = null;
      int n2 = 0;
      switch (n) {
         case 1:
            obj = Blocks.red_flower;
            n2 = BlockFlower.EnumFlowerType.POPPY.getMeta();
            break;
         case 2:
            obj = Blocks.yellow_flower;
            break;
         case 3:
            obj = Blocks.sapling;
            n2 = BlockPlanks.EnumType.OAK.getMetadata();
            break;
         case 4:
            obj = Blocks.sapling;
            n2 = BlockPlanks.EnumType.SPRUCE.getMetadata();
            break;
         case 5:
            obj = Blocks.sapling;
            n2 = BlockPlanks.EnumType.BIRCH.getMetadata();
            break;
         case 6:
            obj = Blocks.sapling;
            n2 = BlockPlanks.EnumType.JUNGLE.getMetadata();
            break;
         case 7:
            obj = Blocks.red_mushroom;
            break;
         case 8:
            obj = Blocks.brown_mushroom;
            break;
         case 9:
            obj = Blocks.cactus;
            break;
         case 10:
            obj = Blocks.deadbush;
            break;
         case 11:
            obj = Blocks.tallgrass;
            n2 = BlockTallGrass.EnumType.FERN.getMeta();
            break;
         case 12:
            obj = Blocks.sapling;
            n2 = BlockPlanks.EnumType.ACACIA.getMetadata();
            break;
         case 13:
            obj = Blocks.sapling;
            n2 = BlockPlanks.EnumType.DARK_OAK.getMetadata();
      }

      return new TileEntityFlowerPot(Item.getItemFromBlock((Block)obj), n2);
   }

   @Override
   public Item getItem(World world, BlockPos pos) {
      TileEntityFlowerPot tileEntity = this.getTileEntity(world, pos);
      return tileEntity != null && tileEntity.getFlowerPotItem() != null ? tileEntity.getFlowerPotItem() : Items.flower_pot;
   }

   @Override
   public boolean isFlowerPot() {
      return true;
   }

   @Override
   public boolean canBlockBeActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumFacing facing, float f, float f2, float f3) {
      ItemStack currentItem = player.inventory.getCurrentItem();
      if (currentItem != null && currentItem.getItem() instanceof ItemBlock) {
         TileEntityFlowerPot tileEntity = this.getTileEntity(world, pos);
         if (tileEntity == null) {
            return false;
         } else if (tileEntity.getFlowerPotItem() != null) {
            return false;
         } else {
            return this.canNotContain(Block.getBlockFromItem(currentItem.getItem()), currentItem.getMetadata());
         }
      } else {
         return false;
      }
   }

   @Override
   public EnumWorldBlockLayer getBlockLayer() {
      return EnumWorldBlockLayer.CUTOUT;
   }

   private boolean canNotContain(Block block, int n) {
      return block != Blocks.yellow_flower
            && block != Blocks.red_flower
            && block != Blocks.cactus
            && block != Blocks.brown_mushroom
            && block != Blocks.red_mushroom
            && block != Blocks.sapling
            && block != Blocks.deadbush
         ? block == Blocks.tallgrass && n == BlockTallGrass.EnumType.FERN.getMeta()
         : true;
   }

   @Override
   public Item getItemDropped(IBlockState state, Random rand, int n) {
      return Items.flower_pot;
   }

   @Override
   public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumFacing facing, float f, float f2, float f3) {
      ItemStack currentItem = player.inventory.getCurrentItem();
      if (currentItem != null && currentItem.getItem() instanceof ItemBlock) {
         TileEntityFlowerPot tileEntity = this.getTileEntity(world, pos);
         if (tileEntity == null) {
            return false;
         } else if (tileEntity.getFlowerPotItem() != null) {
            return false;
         } else {
            if (!this.canNotContain(Block.getBlockFromItem(currentItem.getItem()), currentItem.getMetadata())) {
               return false;
            } else {
               tileEntity.setFlowerPotData(currentItem.getItem(), currentItem.getMetadata());
               tileEntity.markDirty();
               world.markBlockForUpdate(pos);
               player.triggerAchievement(StatList.flowerPotted);
               if (!player.capabilities.isCreativeMode && --currentItem.stackSize <= 0) {
                  player.inventory.setInventorySlotContents(player.inventory.currentItem, (ItemStack)null);
               }

               return true;
            }
         }
      } else {
         return false;
      }
   }

   @Override
   public boolean isFullCube() {
      return false;
   }

public enum EnumFlowerType implements IStringSerializable {
      EMPTY("empty"),
      POPPY("rose"),
      BLUE_ORCHID("blue_orchid"),
      ALLIUM("allium"),
      HOUSTONIA("houstonia"),
      RED_TULIP("red_tulip"),
      ORANGE_TULIP("orange_tulip"),
      WHITE_TULIP("white_tulip"),
      PINK_TULIP("pink_tulip"),
      OXEYE_DAISY("oxeye_daisy"),
      DANDELION("dandelion"),
      OAK_SAPLING("oak_sapling"),
      SPRUCE_SAPLING("spruce_sapling"),
      BIRCH_SAPLING("birch_sapling"),
      JUNGLE_SAPLING("jungle_sapling"),
      ACACIA_SAPLING("acacia_sapling"),
      DARK_OAK_SAPLING("dark_oak_sapling"),
      MUSHROOM_RED("mushroom_red"),
      MUSHROOM_BROWN("mushroom_brown"),
      DEAD_BUSH("dead_bush"),
      FERN("fern"),
      CACTUS("cactus");

   private final String name;
   private EnumFlowerType(String str) {
         this.name = str;
      }

      @Override
      public String toString() {
         return this.name;
      }

      @Override
      public String getName() {
         return this.name;
      }
   }
}
