package net.minecraft.item;

import java.util.List;
import net.minecraft.block.BlockStandingSign;
import net.minecraft.block.BlockWallSign;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityBanner;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MathHelper;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

public class ItemBanner extends ItemBlock {

   public ItemBanner() {
      super(Blocks.standing_banner);
      this.maxStackSize = 16;
      this.setCreativeTab(CreativeTabs.tabDecorations);
      this.setHasSubtypes(true);
      this.setMaxDamage(0);
   }

   @Override
   public void getSubItems(Item item, CreativeTabs creativeTabs, List list) {
      for (EnumDyeColor enumDyeColor : EnumDyeColor.values()) {
         NBTTagCompound tag = new NBTTagCompound();
         TileEntityBanner.setBaseColorAndPatterns(tag, enumDyeColor.getDyeDamage(), (NBTTagList)null);
         NBTTagCompound tag2 = new NBTTagCompound();
         tag2.setTag("BlockEntityTag", tag);
         ItemStack stack = new ItemStack(item, 1, enumDyeColor.getDyeDamage());
         stack.setTagCompound(tag2);
         list.add(stack);
      }
   }

   @Override
   public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, BlockPos pos, EnumFacing facing, float f, float f2, float f3) {
      if (facing == EnumFacing.DOWN) {
         return false;
      } else if (!world.getBlockState(pos).getBlock().getMaterial().isSolid()) {
         return false;
      } else {
         pos = pos.offset(facing);
         if (!player.canPlayerEdit(pos, facing, stack)) {
            return false;
         } else if (!Blocks.standing_banner.canPlaceBlockAt(world, pos)) {
            return false;
         } else if (world.isRemote) {
            return true;
         } else {
            if (facing == EnumFacing.UP) {
               int n = MathHelper.floor_double((double)((player.rotationYaw + 180.0F) * 16.0F / 360.0F) + 0.5) & 15;
               world.setBlockState(pos, Blocks.standing_banner.getDefaultState().withProperty(BlockStandingSign.ROTATION, n), 3);
            } else {
               world.setBlockState(pos, Blocks.wall_banner.getDefaultState().withProperty(BlockWallSign.FACING, facing), 3);
            }

            stack.stackSize--;
            TileEntity tileEntity = world.getTileEntity(pos);
            if (tileEntity instanceof TileEntityBanner) {
               ((TileEntityBanner)tileEntity).setItemValues(stack);
            }

            return true;
         }
      }
   }

   @Override
   public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean flag) {
      NBTTagCompound subCompound = stack.getSubCompound("BlockEntityTag", false);
      if (subCompound != null && subCompound.hasKey("Patterns")) {
         NBTTagList tagList = subCompound.getTagList("Patterns", 10);

         for (int i = 0; i < tagList.tagCount() && i < 6; i++) {
            NBTTagCompound compoundTagAt = tagList.getCompoundTagAt(i);
            EnumDyeColor enumDyeColor = EnumDyeColor.byDyeDamage(compoundTagAt.getInteger("Color"));
            TileEntityBanner.EnumBannerPattern patternByID = TileEntityBanner.EnumBannerPattern.getPatternByID(compoundTagAt.getString("Pattern"));
            if (patternByID != null) {
               list.add(StatCollector.translateToLocal("item.banner." + patternByID.getPatternName() + "." + enumDyeColor.getUnlocalizedName()));
            }
         }
      }
   }

   @Override
   public int getColorFromItemStack(ItemStack stack, int n) {
      if (n == 0) {
         return 16777215;
      } else {
         return this.getBaseColor(stack).getMapColor().colorValue;
      }
   }

   private EnumDyeColor getBaseColor(ItemStack stack) {
      NBTTagCompound subCompound = stack.getSubCompound("BlockEntityTag", false);
      EnumDyeColor enumDyeColor = null;
      if (subCompound != null && subCompound.hasKey("Base")) {
         enumDyeColor = EnumDyeColor.byDyeDamage(subCompound.getInteger("Base"));
      } else {
         enumDyeColor = EnumDyeColor.byDyeDamage(stack.getMetadata());
      }

      return enumDyeColor;
   }

   @Override
   public CreativeTabs getCreativeTab() {
      return CreativeTabs.tabDecorations;
   }

   @Override
   public String getItemStackDisplayName(ItemStack stack) {
      String str = "item.banner.";
      str = str + this.getBaseColor(stack).getUnlocalizedName() + ".name";
      return StatCollector.translateToLocal(str);
   }
}
