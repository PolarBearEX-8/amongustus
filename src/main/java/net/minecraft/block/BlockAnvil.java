package net.minecraft.block;

import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ContainerRepair;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.IChatComponent;
import net.minecraft.world.IInteractionObject;
import java.util.List;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityFallingBlock;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockAnvil extends BlockFalling {
   public static final PropertyDirection FACING = PropertyDirection.create("facing", EnumFacing.Plane.HORIZONTAL);
   public static final PropertyInteger DAMAGE = PropertyInteger.create("damage", 0, 2);

   @Override
   public IBlockState onBlockPlaced(World world, BlockPos pos, EnumFacing facing, float f, float f2, float f3, int n, EntityLivingBase entity) {
      EnumFacing facing2 = entity.getHorizontalFacing().rotateY();
      return super.onBlockPlaced(world, pos, facing, f, f2, f3, n, entity).withProperty(FACING, facing2).withProperty(DAMAGE, n >> 2);
   }

   public BlockAnvil() {
      super(Material.anvil);
      this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH).withProperty(DAMAGE, 0));
      this.setLightOpacity(0);
      this.setCreativeTab(CreativeTabs.tabDecorations);
   }

   @Override
   public int getMetaFromState(IBlockState state) {
      int n = 0;
      n |= ((EnumFacing)state.getValue(FACING)).getHorizontalIndex();
      return n | (Integer)state.getValue(DAMAGE) << 2;
   }

   @Override
   public void setBlockBoundsBasedOnState(IBlockAccess world, int n, int n2, int n3) {
      if (((EnumFacing)world.getBlockState2(n, n2, n3).getValue(FACING)).getAxis() == EnumFacing.Axis.X) {
         this.setBlockBounds(0.0F, 0.0F, 0.125F, 1.0F, 1.0F, 0.875F);
      } else {
         this.setBlockBounds(0.125F, 0.0F, 0.0F, 0.875F, 1.0F, 1.0F);
      }
   }

   @Override
   public void onEndFalling(World world, BlockPos pos) {
      world.playAuxSFX(1022, pos, 0);
   }

   @Override
   public int damageDropped(IBlockState state) {
      return (Integer)state.getValue(DAMAGE);
   }

   @Override
   public boolean isOpaqueCube() {
      return false;
   }

   @Override
   public void getSubBlocks(Item item, CreativeTabs creativeTabs, List list) {
      list.add(new ItemStack(item, 1, 0));
      list.add(new ItemStack(item, 1, 1));
      list.add(new ItemStack(item, 1, 2));
   }

   @Override
   public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumFacing facing, float f, float f2, float f3) {
      if (!world.isRemote) {
         player.displayGui(new BlockAnvil.Anvil(world, pos));
      }

      return true;
   }

   @Override
   public IBlockState getStateFromMeta(int n) {
      return this.getDefaultState().withProperty(FACING, EnumFacing.getHorizontal(n & 3)).withProperty(DAMAGE, (n & 15) >> 2);
   }

   @Override
   public void onStartFalling(EntityFallingBlock entityFallingBlock) {
      entityFallingBlock.setHurtEntities(true);
   }

   @Override
   public boolean isFullCube() {
      return false;
   }

   @Override
   public boolean canBlockBeActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumFacing facing, float f, float f2, float f3) {
      return true;
   }

   @Override
   public boolean shouldSideBeRendered(IBlockAccess world, int n, int n2, int n3, EnumFacing facing) {
      return true;
   }

   @Override
   public IBlockState getStateForEntityRender(IBlockState state) {
      return this.getDefaultState().withProperty(FACING, EnumFacing.SOUTH);
   }

   @Override
   public BlockState createBlockState() {
      return new BlockState(this, new IProperty[]{FACING, DAMAGE});
   }


   public static class Anvil implements IInteractionObject {
      private final World world;
      private final BlockPos position;

      @Override
      public IChatComponent getDisplayName() {
         return new ChatComponentTranslation(Blocks.anvil.getUnlocalizedName() + ".name", new Object[0]);
      }

      @Override
      public String getName() {
         return "anvil";
      }

      public Anvil(World world2, BlockPos pos) {
         this.world = world2;
         this.position = pos;
      }

      @Override
      public Container createContainer(InventoryPlayer inventoryPlayer, EntityPlayer player) {
         return new ContainerRepair(inventoryPlayer, this.world, this.position, player);
      }

      @Override
      public String getGuiID() {
         return "minecraft:anvil";
      }

      @Override
      public boolean hasCustomName() {
         return false;
      }
   }
}
