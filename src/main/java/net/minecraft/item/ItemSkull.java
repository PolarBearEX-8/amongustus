package net.minecraft.item;

import com.mojang.authlib.GameProfile;
import java.util.List;
import java.util.UUID;
import net.minecraft.block.Block;
import net.minecraft.block.BlockSkull;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTUtil;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntitySkull;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MathHelper;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

public class ItemSkull extends Item {
   private static final String[] skullTypes = new String[]{"skeleton", "wither", "zombie", "char", "creeper"};

   @Override
   public String getItemStackDisplayName(ItemStack stack) {
      if (stack.getMetadata() == 3 && stack.hasTagCompound()) {
         if (stack.getTagCompound().hasKey("SkullOwner", 8)) {
            return StatCollector.translateToLocalFormatted("item.skull.player.name", new Object[]{stack.getTagCompound().getString("SkullOwner")});
         }

         if (stack.getTagCompound().hasKey("SkullOwner", 10)) {
            NBTTagCompound compoundTag = stack.getTagCompound().getCompoundTag("SkullOwner");
            if (compoundTag.hasKey("Name", 8)) {
               return StatCollector.translateToLocalFormatted("item.skull.player.name", new Object[]{compoundTag.getString("Name")});
            }
         }
      }

      return super.getItemStackDisplayName(stack);
   }

   @Override
   public String getUnlocalizedName(ItemStack stack) {
      int metadata = stack.getMetadata();
      if (metadata < 0 || metadata >= skullTypes.length) {
         metadata = 0;
      }

      return super.getUnlocalizedName() + "." + skullTypes[metadata];
   }

   public ItemSkull() {
      this.setCreativeTab(CreativeTabs.tabDecorations);
      this.setMaxDamage(0);
      this.setHasSubtypes(true);
   }

   @Override
   public void getSubItems(Item item, CreativeTabs creativeTabs, List list) {
      for (int i = 0; i < skullTypes.length; i++) {
         list.add(new ItemStack(item, 1, i));
      }
   }

   @Override
   public boolean updateItemStackNBT(NBTTagCompound tag) {
      super.updateItemStackNBT(tag);
      if (tag.hasKey("SkullOwner", 8) && tag.getString("SkullOwner").length() > 0) {
         GameProfile profile = new GameProfile((UUID)null, tag.getString("SkullOwner"));
         profile = TileEntitySkull.updateGameprofile(profile);
         tag.setTag("SkullOwner", NBTUtil.writeGameProfile(new NBTTagCompound(), profile));
         return true;
      } else {
         return false;
      }
   }

   @Override
   public int getMetadata(int n) {
      return n;
   }

   @Override
   public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, BlockPos pos, EnumFacing facing, float f, float f2, float f3) {
      if (facing == EnumFacing.DOWN) {
         return false;
      } else {
         if (!world.getBlockState(pos).getBlock().isReplaceable(world, pos)) {
            if (!world.getBlockState(pos).getBlock().getMaterial().isSolid()) {
               return false;
            }

            pos = pos.offset(facing);
         }

         if (!player.canPlayerEdit(pos, facing, stack)) {
            return false;
         } else if (!Blocks.skull.canPlaceBlockAt(world, pos)) {
            return false;
         } else {
            if (!world.isRemote) {
               world.setBlockState(pos, Blocks.skull.getDefaultState().withProperty(BlockSkull.FACING, facing), 3);
               int n = 0;
               if (facing == EnumFacing.UP) {
                  n = MathHelper.floor_double((double)(player.rotationYaw * 16.0F / 360.0F) + 0.5) & 15;
               }

               TileEntity tileEntity = world.getTileEntity(pos);
               if (tileEntity instanceof TileEntitySkull) {
                  TileEntitySkull tileEntitySkull = (TileEntitySkull)tileEntity;
                  if (stack.getMetadata() == 3) {
                     GameProfile profile = null;
                     if (stack.hasTagCompound()) {
                        NBTTagCompound tagCompound = stack.getTagCompound();
                        if (tagCompound.hasKey("SkullOwner", 10)) {
                           profile = NBTUtil.readGameProfileFromNBT(tagCompound.getCompoundTag("SkullOwner"));
                        } else if (tagCompound.hasKey("SkullOwner", 8) && tagCompound.getString("SkullOwner").length() > 0) {
                           profile = new GameProfile((UUID)null, tagCompound.getString("SkullOwner"));
                        }
                     }

                     tileEntitySkull.setPlayerProfile(profile);
                  } else {
                     tileEntitySkull.setType(stack.getMetadata());
                  }

                  tileEntitySkull.setSkullRotation(n);
                  Blocks.skull.checkWitherSpawn(world, pos, tileEntitySkull);
               }

               stack.stackSize--;
            }

            return true;
         }
      }
   }
}
