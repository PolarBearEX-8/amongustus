package net.minecraft.block;

import java.util.List;
import java.util.Random;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityBrewingStand;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.EnumWorldBlockLayer;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

public class BlockBrewingStand extends BlockContainer {
   public static final PropertyBool[] HAS_BOTTLE = new PropertyBool[]{
      PropertyBool.create("has_bottle_0"), PropertyBool.create("has_bottle_1"), PropertyBool.create("has_bottle_2")
   };

   @Override
   public BlockState createBlockState() {
      return new BlockState(this, new IProperty[]{HAS_BOTTLE[0], HAS_BOTTLE[1], HAS_BOTTLE[2]});
   }

   @Override
   public IBlockState getStateFromMeta(int n) {
      IBlockState defaultState = this.getDefaultState();

      for (int i = 0; i < 3; i++) {
         defaultState = defaultState.withProperty(HAS_BOTTLE[i], (n & 1 << i) > 0);
      }

      return defaultState;
   }

   @Override
   public Item getItem(World world, BlockPos pos) {
      return Items.brewing_stand;
   }

   @Override
   public void breakBlock(World world, BlockPos pos, IBlockState state) {
      TileEntity tileEntity = world.getTileEntity(pos);
      if (tileEntity instanceof TileEntityBrewingStand) {
         InventoryHelper.dropInventoryItems(world, pos, (TileEntityBrewingStand)tileEntity);
      }

      super.breakBlock(world, pos, state);
   }

   @Override
   public void randomDisplayTick(World world, BlockPos pos, IBlockState state, Random rand) {
      double d = (double)((float)pos.getX() + 0.4F + rand.nextFloat() * 0.2F);
      double d2 = (double)((float)pos.getY() + 0.7F + rand.nextFloat() * 0.3F);
      double d3 = (double)((float)pos.getZ() + 0.4F + rand.nextFloat() * 0.2F);
      world.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, d, d2, d3, 0.0, 0.0, 0.0, new int[0]);
   }

   @Override
   public int getRenderType() {
      return 3;
   }

   @Override
   public boolean hasComparatorInputOverride() {
      return true;
   }

   @Override
   public Item getItemDropped(IBlockState state, Random rand, int n) {
      return Items.brewing_stand;
   }

   @Override
   public EnumWorldBlockLayer getBlockLayer() {
      return EnumWorldBlockLayer.CUTOUT;
   }

   public BlockBrewingStand() {
      super(Material.iron);
      this.setDefaultState(
         this.blockState
            .getBaseState()
            .withProperty(HAS_BOTTLE[0], Boolean.FALSE)
            .withProperty(HAS_BOTTLE[1], Boolean.FALSE)
            .withProperty(HAS_BOTTLE[2], Boolean.FALSE)
      );
   }

   @Override
   public boolean isOpaqueCube() {
      return false;
   }

   @Override
   public String getLocalizedName() {
      return StatCollector.translateToLocal("item.brewingStand.name");
   }

   @Override
   public void onBlockPlacedBy(World world, BlockPos pos, IBlockState state, EntityLivingBase entity, ItemStack stack) {
      if (stack.hasDisplayName()) {
         TileEntity tileEntity = world.getTileEntity(pos);
         if (tileEntity instanceof TileEntityBrewingStand) {
            ((TileEntityBrewingStand)tileEntity).setName(stack.getDisplayName());
         }
      }
   }

   @Override
   public int getMetaFromState(IBlockState state) {
      int n = 0;

      for (int i = 0; i < 3; i++) {
         if ((Boolean)state.getValue(HAS_BOTTLE[i])) {
            n |= 1 << i;
         }
      }

      return n;
   }

   @Override
   public TileEntity createNewTileEntity(World world, int n) {
      return new TileEntityBrewingStand();
   }

   @Override
   public void addCollisionBoxesToList(World world, BlockPos pos, IBlockState state, AxisAlignedBB box, List list, Entity entity) {
      this.setBlockBounds(0.4375F, 0.0F, 0.4375F, 0.5625F, 0.875F, 0.5625F);
      super.addCollisionBoxesToList(world, pos, state, box, list, entity);
      this.setBlockBoundsForItemRender();
      super.addCollisionBoxesToList(world, pos, state, box, list, entity);
   }

   @Override
   public boolean canBlockBeActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumFacing facing, float f, float f2, float f3) {
      return true;
   }

   @Override
   public boolean isFullCube() {
      return false;
   }

   @Override
   public int getComparatorInputOverride(World world, BlockPos pos) {
      return Container.calcRedstone(world.getTileEntity(pos));
   }

   @Override
   public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumFacing facing, float f, float f2, float f3) {
      if (!world.isRemote) {
         TileEntity tileEntity = world.getTileEntity(pos);
         if (tileEntity instanceof TileEntityBrewingStand) {
            player.displayGUIChest((TileEntityBrewingStand)tileEntity);
            player.triggerAchievement(StatList.brewingstandInteraction);
         }
      }

      return true;
   }

   @Override
   public void setBlockBoundsForItemRender() {
      this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.125F, 1.0F);
   }
}
