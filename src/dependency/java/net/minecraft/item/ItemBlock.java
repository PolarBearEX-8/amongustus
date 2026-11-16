package net.minecraft.item;

import client.Client;
import client.module.Module;
import client.util.IBlockState;
import client.util.VanillaRunnable;
import client.via.ViaLoadingBase;
import com.viaversion.viaversion.api.protocol.version.ProtocolVersion;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class ItemBlock extends Item {
   public final Block block;

   @Override
   public Item processAction(String text) {
      return this.resolveItemBlock(text);
   }

   @Override
   public String getString() {
      return this.block.getTextAsText();
   }

   @Override
   public void processAction(Item item, ItemGroupManager itemGroupManager, List list) {
      this.block.processAction(item, itemGroupManager, list);
   }

   @Override
   public ItemGroupManager getItemGroupManager() {
      return this.block.getItemGroupManager();
   }

   @Override
   public String getText(ItemStack stack) {
      return this.block.getTextAsText();
   }

   @Override
   public boolean processAction(ItemStack stack, EntityPlayer player, World world, BlockPos blockPos, EnumFacing enumFacing, float posX, float posY, float posZ) {
      BlockPos blockPos2 = blockPos;
      if (!world.getBlockState(blockPos).getBlock().processAction6(world, blockPos)) {
         blockPos2 = blockPos.offset(enumFacing);
      }

      if (stack.animationsToGo == 0) {
         return false;
      } else if (!player.matchesBlockPos(blockPos2, enumFacing, stack)) {
         return false;
      } else if ((
            !((Module)Client.getClient().getModuleManager().fly).isToggled()
               || !Client.getClient().getModuleManager().fly.settingMode.getSelected().equals("AirPlace")
         )
         && !world.matchesBlock(this.block, blockPos2, false, enumFacing, (Entity)null, stack)) {
         return false;
      } else {
         int count = this.processAction2(stack.getIndexOffset());
         IBlockState blockState = this.block.onBlockPlaced(world, blockPos2, enumFacing, posX, posY, posZ, count, player);
         if (world.matchesBlockPos(blockPos2, blockState, 3)) {
            IBlockState blockState2 = world.getBlockState(blockPos2);
            if (blockState2.getBlock() == this.block) {
               matchesWorld(world, player, blockPos2, stack);
               this.block.processAction(world, blockPos2, blockState2, player, stack);
            }

            if (ViaLoadingBase.protocolVersion.newerThanOrEqualTo(ProtocolVersion.v1_9)) {
               player.worldObj
                  .applyText(
                     (double)((float)blockPos2.getX() + 0.5F),
                     (double)((float)blockPos2.getY() + 0.5F),
                     (double)((float)blockPos2.getZ() + 0.5F),
                     this.block.stepManager20.getTextAsText(),
                     (this.block.stepManager20.getFactor2() + 1.0F) / 2.0F,
                     this.block.stepManager20.getFactor() * 0.8F,
                     false
                  );
            } else {
               world.applyText(
                  (double)((float)blockPos2.getX() + 0.5F),
                  (double)((float)blockPos2.getY() + 0.5F),
                  (double)((float)blockPos2.getZ() + 0.5F),
                  this.block.stepManager20.getTextAsText(),
                  (this.block.stepManager20.getFactor2() + 1.0F) / 2.0F,
                  this.block.stepManager20.getFactor() * 0.8F
               );
            }

            stack.animationsToGo--;
         }

         return true;
      }
   }

   public boolean matchesWorld(World world, BlockPos blockPos, EnumFacing enumFacing, EntityPlayer player, ItemStack stack) {
      BlockPos blockPos2 = blockPos;
      EnumFacing enumFacing2 = enumFacing;
      Block block2 = world.getBlockState(blockPos).getBlock();
      if (block2 == Blocks.block74) {
         enumFacing2 = EnumFacing.UP;
      } else if (!block2.processAction6(world, blockPos)) {
         blockPos2 = blockPos.offset(enumFacing);
      }

      return world.matchesBlock(this.block, blockPos2, false, enumFacing2, (Entity)null, stack);
   }

   public static boolean matchesWorld(World world, EntityPlayer player, BlockPos blockPos, ItemStack stack) {
      VanillaRunnable vanillaRunnable = VanillaRunnable.getVanillaRunnable();
      if (vanillaRunnable == null) {
         return false;
      } else {
         if (stack.canExecute() && stack.getStackTagCompound().matchesText("BlockEntityTag", 10)) {
            TileEntity tileEntity = world.resolveTileEntity(blockPos);
            if (tileEntity != null) {
               if (!world.state && tileEntity.isActiveAsFlag() && !vanillaRunnable.getMovingManager().matchesGameProfileByGameProfile(player.getGameProfile())) {
                  return false;
               }

               NBTTagCompound tagCompound = new NBTTagCompound();
               NBTTagCompound lISTManager = (NBTTagCompound)tagCompound.getLISTManager();
               tileEntity.applyNBTTagCompound(tagCompound);
               NBTTagCompound tagCompound2 = (NBTTagCompound)stack.getStackTagCompound().get("BlockEntityTag");
               tagCompound.applyNBTTagCompound(tagCompound2);
               tagCompound.put("x", blockPos.getX());
               tagCompound.put("y", blockPos.getY());
               tagCompound.put("z", blockPos.getZ());
               if (!tagCompound.equals(lISTManager)) {
                  tileEntity.applyNBTTagCompoundByNBTTagCompound(tagCompound);
                  tileEntity.processAction5();
                  return true;
               }
            }
         }

         return false;
      }
   }

   public ItemBlock resolveItemBlock(String text) {
      super.processAction(text);
      return this;
   }

   public Block getBlock() {
      return this.block;
   }

   public ItemBlock(Block block2) {
      this.block = block2;
   }
}
