package client.util;

import client.setting.MovementFactorProperty;
import java.util.Random;
import net.minecraft.block.AdvancedBlockAdapter;
import net.minecraft.block.material.Material;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityBanner;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class VMIBlockAdapter extends AdvancedBlockAdapter {
   public static final AutoWalkPath autoWalkPath = AutoWalkPath.resolveAutoWalkPath("facing", MovementFactorProperty.HORIZONTAL);
   public static final AuxiliaryImmutableSetEntry auxiliaryImmutableSetEntry = AuxiliaryImmutableSetEntry.resolveAuxiliaryImmutableSetEntry("rotation", 0, 15);

   @Override
   public void applyWorld3(World world, BlockPos blockPos, IBlockState blockState, float factor, int count) {
      TileEntity tileEntity = world.resolveTileEntity(blockPos);
      if (tileEntity instanceof TileEntityBanner) {
         ItemStack stack = new ItemStack(Items.banner, 1, ((TileEntityBanner)tileEntity).getCount2());
         NBTTagCompound tagCompound = new NBTTagCompound();
         tileEntity.applyNBTTagCompound(tagCompound);
         tagCompound.remove("x");
         tagCompound.remove("y");
         tagCompound.remove("z");
         tagCompound.remove("id");
         stack.applyText("BlockEntityTag", tagCompound);
         applyWorld(world, blockPos, stack);
      } else {
         super.applyWorld3(world, blockPos, blockState, factor, count);
      }
   }

   @Override
   public AxisAlignedBB processAction(World world, BlockPos blockPos) {
      this.processAction(world, blockPos.getX(), blockPos.getY(), blockPos.getZ());
      return super.processAction(world, blockPos);
   }

   @Override
   public Item processAction4(World world, BlockPos blockPos) {
      return Items.banner;
   }

   @Override
   public boolean matchesIBlockAccess(IBlockAccess iBlockAccess, int count, int count2, int count3) {
      return true;
   }

   @Override
   public Item processAction(IBlockState blockState, Random random, int count) {
      return Items.banner;
   }

   @Override
   public boolean matchesIBlockAccess(IBlockAccess iBlockAccess, BlockPos blockPos) {
      return true;
   }

   @Override
   public TileEntity resolveTileEntityByWorld(World world, int count) {
      return new TileEntityBanner();
   }

   @Override
   public boolean processAction7(World world, BlockPos blockPos) {
      return !this.matchesWorld3(world, blockPos) && super.processAction7(world, blockPos);
   }

   @Override
   public void applyWorld(World world, EntityPlayer player, BlockPos blockPos, IBlockState blockState, TileEntity tileEntity) {
      if (tileEntity instanceof TileEntityBanner) {
         TileEntityBanner tileEntityBanner = (TileEntityBanner)tileEntity;
         ItemStack stack = new ItemStack(Items.banner, 1, ((TileEntityBanner)tileEntity).getCount2());
         NBTTagCompound tagCompound = new NBTTagCompound();
         TileEntityBanner.applyNBTTagCompound(tagCompound, tileEntityBanner.getCount2(), tileEntityBanner.getNBTTagList());
         stack.applyText("BlockEntityTag", tagCompound);
         applyWorld(world, blockPos, stack);
      } else {
         super.applyWorld(world, player, blockPos, blockState, (TileEntity)null);
      }
   }

   @Override
   public AxisAlignedBB processAction(World world, IBlockState blockState, int count, int count2, int count3) {
      return null;
   }

   @Override
   public String getText() {
      return I18n.getString2("item.banner.white.name");
   }

   @Override
   public boolean processAction14() {
      return false;
   }

   @Override
   public boolean processAction15() {
      return false;
   }

   @Override
   public boolean isActiveAsFlag() {
      return true;
   }

   @Override
   public boolean processAction17() {
      return false;
   }

   public VMIBlockAdapter() {
      super(Material.material);
      float factor = 0.25F;
      float factor2 = 1.0F;
      this.setBlockBounds(0.5F - factor, 0.0F, 0.5F - factor, 0.5F + factor, factor2, 0.5F + factor);
   }
}
