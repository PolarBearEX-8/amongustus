package client.util;

import client.setting.StaffDetectorAddPlayerType;
import client.setting.WorldSettingsGameType;
import net.minecraft.block.Block;
import net.minecraft.block.BlockTrappedChest;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerAdapter;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.network.play.server.S23PacketBlockChange;
import net.minecraft.network.play.server.S38PacketPlayerListItem;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

public class InternalWorldEntry {
   public World world;
   public EntityPlayerAdapter entityPlayerAdapter;
   public WorldSettingsGameType worldSettingsGameType = WorldSettingsGameType.NOT_SET;
   public boolean enabled;
   public int count;
   public BlockPos blockPos = BlockPos.origin;
   public int count2;
   public boolean enabled2;
   public BlockPos blockPos2 = BlockPos.origin;
   public int count3;
   public int count4 = -1;

   public void updateState2() {
      this.count2++;
      if (this.enabled2) {
         int count5 = this.count2 - this.count3;
         Block block = this.world.getBlockState(this.blockPos2).getBlock();
         if (block.getMaterial() == Material.coreItemEntry) {
            this.enabled2 = false;
         } else {
            float factor = block.getScaleFactor(this.entityPlayerAdapter, this.entityPlayerAdapter.worldObj, this.blockPos2) * (float)(count5 + 1);
            int count6 = (int)(factor * 10.0F);
            if (count6 != this.count4) {
               this.world.applyBlockPos(this.entityPlayerAdapter.getEntityId(), this.blockPos2, count6);
               this.count4 = count6;
            }

            if (factor >= 1.0F) {
               this.enabled2 = false;
               this.matchesBlockPosByBlockPos(this.blockPos2);
            }
         }
      } else if (this.enabled) {
         Block block2 = this.world.getBlockState(this.blockPos).getBlock();
         if (block2.getMaterial() == Material.coreItemEntry) {
            this.world.applyBlockPos(this.entityPlayerAdapter.getEntityId(), this.blockPos, -1);
            this.count4 = -1;
            this.enabled = false;
         } else {
            int count7 = this.count2 - this.count;
            int count8 = (int)(block2.getScaleFactor(this.entityPlayerAdapter, this.entityPlayerAdapter.worldObj, this.blockPos2) * (float)(count7 + 1) * 10.0F);
            if (count8 != this.count4) {
               this.world.applyBlockPos(this.entityPlayerAdapter.getEntityId(), this.blockPos, count8);
               this.count4 = count8;
            }
         }
      }
   }

   public InternalWorldEntry(World world2) {
      this.world = world2;
   }

   public void updateState() {
      this.enabled = false;
      this.world.applyBlockPos(this.entityPlayerAdapter.getEntityId(), this.blockPos, -1);
   }

   public boolean matchesBlockPosByBlockPos(BlockPos blockPos3) {
      if (this.worldSettingsGameType.canExecute()
         && this.entityPlayerAdapter.getItemStack() != null
         && this.entityPlayerAdapter.getItemStack().getItem() instanceof ItemSword) {
         return false;
      } else {
         IBlockState blockState = this.world.getBlockState(blockPos3);
         TileEntity tileEntity = this.world.resolveTileEntity(blockPos3);
         if (this.worldSettingsGameType.isActive()) {
            if (this.worldSettingsGameType == WorldSettingsGameType.SPECTATOR) {
               return false;
            }

            if (!this.entityPlayerAdapter.onReset()) {
               ItemStack stack = this.entityPlayerAdapter.getHeldItem();
               if (stack == null) {
                  return false;
               }

               if (!stack.matchesBlockByBlock(blockState.getBlock())) {
                  return false;
               }
            }
         }

         this.world.applyEntityPlayerByEntityPlayer(this.entityPlayerAdapter, 2001, blockPos3, Block.processAction5(blockState));
         boolean flag = this.matchesBlockPos(blockPos3);
         if (this.isActiveAsFlag()) {
            this.entityPlayerAdapter.auxiliaryListenerAdapter4.applyPacket(new S23PacketBlockChange(this.world, blockPos3));
         } else {
            ItemStack stack2 = this.entityPlayerAdapter.getHeldItem();
            boolean flag2 = this.entityPlayerAdapter.matchesBlock(blockState.getBlock());
            if (stack2 != null) {
               stack2.applyWorldByWorld(this.world, blockState.getBlock(), blockPos3, this.entityPlayerAdapter);
               if (stack2.animationsToGo == 0) {
                  this.entityPlayerAdapter.updateState45();
               }
            }

            if (flag && flag2) {
               blockState.getBlock().applyWorld(this.world, this.entityPlayerAdapter, blockPos3, blockState, tileEntity);
            }
         }

         return flag;
      }
   }

   private boolean matchesBlockPos(BlockPos blockPos3) {
      IBlockState blockState = this.world.getBlockState(blockPos3);
      blockState.getBlock().processAction(this.world, blockPos3, blockState, this.entityPlayerAdapter);
      boolean flag = this.world.matchesBlockPos8(blockPos3);
      if (flag) {
         blockState.getBlock().breakBlock(this.world, blockPos3, blockState);
      }

      return flag;
   }

   public boolean matchesEntityPlayer(EntityPlayer player, World world2, ItemStack stack, BlockPos blockPos3, EnumFacing enumFacing, float posX, float posY, float posZ) {
      if (this.worldSettingsGameType == WorldSettingsGameType.SPECTATOR) {
         TileEntity tileEntity = world2.resolveTileEntity(blockPos3);
         if (tileEntity instanceof IInventoryExtension) {
            Block block = world2.getBlockState(blockPos3).getBlock();
            IInventoryExtension iInventoryExtension = (IInventoryExtension)tileEntity;
            if (iInventoryExtension instanceof TileEntityChest && block instanceof BlockTrappedChest) {
               iInventoryExtension = ((BlockTrappedChest)block).resolveIInventoryExtension(world2, blockPos3);
            }

            if (iInventoryExtension != null) {
               player.applyIInventory(iInventoryExtension);
               return true;
            }
         } else if (tileEntity instanceof IInventory) {
            player.applyIInventory((IInventory)tileEntity);
            return true;
         }

         return false;
      } else {
         if (!player.isSprinting() || player.getItemStack() == null) {
            IBlockState blockState = world2.getBlockState(blockPos3);
            if (blockState.getBlock().onBlockActivated(world2, blockPos3, blockState, player, enumFacing, posX, posY, posZ)) {
               return true;
            }
         }

         if (stack == null) {
            return false;
         } else if (this.isActiveAsFlag()) {
            int indexOffset = stack.getIndexOffset();
            int animationsToGo = stack.animationsToGo;
            boolean flag = stack.matchesEntityPlayerByEntityPlayer(player, world2, blockPos3, enumFacing, posX, posY, posZ);
            stack.applyIndexByIndex(indexOffset);
            stack.animationsToGo = animationsToGo;
            return flag;
         } else {
            return stack.matchesEntityPlayerByEntityPlayer(player, world2, blockPos3, enumFacing, posX, posY, posZ);
         }
      }
   }

   public WorldSettingsGameType getWorldSettingsGameType() {
      return this.worldSettingsGameType;
   }

   public void applyWorldSettingsGameTypeByWorldSettingsGam(WorldSettingsGameType worldSettingsGameType2) {
      this.worldSettingsGameType = worldSettingsGameType2;
      worldSettingsGameType2.updateInvulnerableManager(this.entityPlayerAdapter.invulnerableManager);
      this.entityPlayerAdapter.updateState43();
      this.entityPlayerAdapter
         .vanillaRunnable
         .getMovingManager()
         .applyPacket(new S38PacketPlayerListItem(StaffDetectorAddPlayerType.UPDATE_GAME_MODE, new EntityPlayerAdapter[]{this.entityPlayerAdapter}));
   }

   public boolean isActive() {
      return this.worldSettingsGameType.canExecute2();
   }

   public void setWorld(WorldServer world2) {
      this.world = world2;
   }

   public boolean matchesEntityPlayer(EntityPlayer player, World world2, ItemStack stack) {
      if (this.worldSettingsGameType == WorldSettingsGameType.SPECTATOR) {
         return false;
      } else {
         int animationsToGo = stack.animationsToGo;
         int indexOffset = stack.getIndexOffset();
         ItemStack stack2 = stack.processAction2(world2, player);
         if (stack2 != stack || stack2 != null && (stack2.animationsToGo != animationsToGo || stack2.processAction6() > 0 || stack2.getIndexOffset() != indexOffset)) {
            player.inventoryPlayer.mainInventory[player.inventoryPlayer.currentItem] = stack2;
            if (this.isActiveAsFlag()) {
               stack2.animationsToGo = animationsToGo;
               if (stack2.isActive3()) {
                  stack2.applyIndexByIndex(indexOffset);
               }
            }

            if (stack2.animationsToGo == 0) {
               player.inventoryPlayer.mainInventory[player.inventoryPlayer.currentItem] = null;
            }

            if (!player.isUsingItem()) {
               ((EntityPlayerAdapter)player).applyContainer(player.container);
            }

            return true;
         } else {
            return false;
         }
      }
   }

   public void applyWorldSettingsGameType(WorldSettingsGameType worldSettingsGameType2) {
      if (this.worldSettingsGameType == WorldSettingsGameType.NOT_SET) {
         this.worldSettingsGameType = worldSettingsGameType2;
      }

      this.applyWorldSettingsGameTypeByWorldSettingsGam(this.worldSettingsGameType);
   }

   public boolean isActiveAsFlag() {
      return this.worldSettingsGameType.canExecute();
   }

   public void applyBlockPos(BlockPos blockPos3, EnumFacing enumFacing) {
      if (this.isActiveAsFlag()) {
         if (!this.world.matchesEntityPlayerByEntityPlayer((EntityPlayer)null, blockPos3, enumFacing)) {
            this.matchesBlockPosByBlockPos(blockPos3);
         }
      } else {
         Block block = this.world.getBlockState(blockPos3).getBlock();
         if (this.worldSettingsGameType.isActive()) {
            if (this.worldSettingsGameType == WorldSettingsGameType.SPECTATOR) {
               return;
            }

            if (!this.entityPlayerAdapter.onReset()) {
               ItemStack stack = this.entityPlayerAdapter.getHeldItem();
               if (stack == null) {
                  return;
               }

               if (!stack.matchesBlockByBlock(block)) {
                  return;
               }
            }
         }

         this.world.matchesEntityPlayerByEntityPlayer((EntityPlayer)null, blockPos3, enumFacing);
         this.count = this.count2;
         float factor = 1.0F;
         if (block.getMaterial() != Material.coreItemEntry) {
            block.onBlockClicked(this.world, blockPos3, this.entityPlayerAdapter);
            factor = block.getScaleFactor(this.entityPlayerAdapter, this.entityPlayerAdapter.worldObj, blockPos3);
         }

         if (block.getMaterial() != Material.coreItemEntry && factor >= 1.0F) {
            this.matchesBlockPosByBlockPos(blockPos3);
         } else {
            this.enabled = true;
            this.blockPos = blockPos3;
            int count5 = (int)(factor * 10.0F);
            this.world.applyBlockPos(this.entityPlayerAdapter.getEntityId(), blockPos3, count5);
            this.count4 = count5;
         }
      }
   }

   public void applyBlockPos(BlockPos blockPos3) {
      if (blockPos3.equals(this.blockPos)) {
         int count5 = this.count2 - this.count;
         Block block = this.world.getBlockState(blockPos3).getBlock();
         if (block.getMaterial() != Material.coreItemEntry) {
            if (block.getScaleFactor(this.entityPlayerAdapter, this.entityPlayerAdapter.worldObj, blockPos3) * (float)(count5 + 1) >= 0.7F) {
               this.enabled = false;
               this.world.applyBlockPos(this.entityPlayerAdapter.getEntityId(), blockPos3, -1);
               this.matchesBlockPosByBlockPos(blockPos3);
            } else if (!this.enabled2) {
               this.enabled = false;
               this.enabled2 = true;
               this.blockPos2 = blockPos3;
               this.count3 = this.count;
            }
         }
      }
   }
}
