package net.minecraft.item;

import com.viaversion.viaversion.api.protocol.version.ProtocolVersion;
import java.util.List;
import net.augustus.viaversion.ViaAugustus;
import net.augustus.Augustus;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class ItemBlock extends Item {
   public final Block block;

   public ItemBlock(Block block2) {
      this.block = block2;
   }

   public CreativeTabs getCreativeTab() {
      return this.block.getCreativeTabToDisplayOn();
   }

   public String getUnlocalizedName() {
      return this.block.getUnlocalizedName();
   }

   public ItemBlock setBlockUnlocalizedName(String str) {
      super.setUnlocalizedName(str);
      return this;
   }

   public Item setUnlocalizedName(String str) {
      return this.setBlockUnlocalizedName(str);
   }

   public String getUnlocalizedName(ItemStack stack) {
      return this.block.getUnlocalizedName();
   }

   public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, BlockPos pos, EnumFacing facing, float f, float f2, float f3) {
      if (!world.getBlockState(pos).getBlock().isReplaceable(world, pos)) {
         pos = pos.offset(facing);
      }

      if (stack.stackSize == 0) {
         return false;
      } else if (!player.canPlayerEdit(pos, facing, stack)) {
         return false;
      } else if ((!Augustus.getInstance().getModuleManager().fly.isToggled() || !Augustus.getInstance().getModuleManager().fly.mode.getSelectedValue().equals("AirPlace"))
         && !world.canBlockBePlaced(this.block, pos, false, facing, (Entity)null, stack)) {
         return false;
      } else {
         int metadata = this.getMetadata(stack.getMetadata());
         IBlockState state = this.block.onBlockPlaced(world, pos, facing, f, f2, f3, metadata, player);
         if (world.setBlockState(pos, state, 3)) {
            state = world.getBlockState(pos);
            if (state.getBlock() == this.block) {
               setTileEntityNBT(world, player, pos, stack);
               this.block.onBlockPlacedBy(world, pos, state, player, stack);
            }

            if (ViaAugustus.targetProtocolVersion.newerThanOrEqualTo(ProtocolVersion.v1_9)) {
               player.entityWorld
                  .playSound(
                     (double)((float)pos.getX() + 0.5F),
                     (double)((float)pos.getY() + 0.5F),
                     (double)((float)pos.getZ() + 0.5F),
                     this.block.stepSound.getPlaceSound(),
                     (this.block.stepSound.getVolume() + 1.0F) / 2.0F,
                     this.block.stepSound.getFrequency() * 0.8F,
                     false
                  );
            } else {
               world.playSoundEffect(
                  (double)((float)pos.getX() + 0.5F),
                  (double)((float)pos.getY() + 0.5F),
                  (double)((float)pos.getZ() + 0.5F),
                  this.block.stepSound.getPlaceSound(),
                  (this.block.stepSound.getVolume() + 1.0F) / 2.0F,
                  this.block.stepSound.getFrequency() * 0.8F
               );
            }

            stack.stackSize--;
         }

         return true;
      }
   }

   public void getSubItems(Item item, CreativeTabs creativeTabs, List list) {
      this.block.getSubBlocks(item, creativeTabs, list);
   }

   public boolean canPlaceBlockOnSide(World world, BlockPos pos, EnumFacing facing, EntityPlayer player, ItemStack stack) {
      Block block2 = world.getBlockState(pos).getBlock();
      if (block2 == Blocks.snow_layer) {
         facing = EnumFacing.UP;
      } else if (!block2.isReplaceable(world, pos)) {
         pos = pos.offset(facing);
      }

      return world.canBlockBePlaced(this.block, pos, false, facing, (Entity)null, stack);
   }

   public static boolean setTileEntityNBT(World world, EntityPlayer player, BlockPos pos, ItemStack stack) {
      MinecraftServer server = MinecraftServer.getServer();
      if (server == null) {
         return false;
      } else {
         if (stack.hasTagCompound() && stack.getTagCompound().hasKey("BlockEntityTag", 10)) {
            TileEntity tileEntity = world.getTileEntity(pos);
            if (tileEntity != null) {
               if (!world.isRemote && tileEntity.onlyOpsCanSetNbt() && !server.getConfigurationManager().canSendCommands(player.getGameProfile())) {
                  return false;
               }

               NBTTagCompound tag = new NBTTagCompound();
               NBTTagCompound tag2 = (NBTTagCompound)tag.copy();
               tileEntity.writeToNBT(tag);
               tag.merge((NBTTagCompound)stack.getTagCompound().getTag("BlockEntityTag"));
               tag.setInteger("x", pos.getX());
               tag.setInteger("y", pos.getY());
               tag.setInteger("z", pos.getZ());
               if (!tag.equals(tag2)) {
                  tileEntity.readFromNBT(tag);
                  tileEntity.markDirty();
                  return true;
               }
            }
         }

         return false;
      }
   }

   public Block getBlock() {
      return this.block;
   }

}
