package net.minecraft.server.management;

import net.minecraft.world.WorldSettings;
import net.minecraft.block.Block;
import net.minecraft.block.BlockChest;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.network.play.server.S23PacketBlockChange;
import net.minecraft.network.play.server.S38PacketPlayerListItem;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.ILockableContainer;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

public class ItemInWorldManager {
   public World theWorld;
   public EntityPlayerMP thisPlayerMP;
   private WorldSettings.GameType gameType = WorldSettings.GameType.NOT_SET;
   private boolean isDestroyingBlock;
   private int initialDamage;
   private BlockPos destroyPos = BlockPos.ORIGIN;
   private int curblockDamage;
   private boolean receivedFinishDiggingPacket;
   private BlockPos delayedDestroyPos = BlockPos.ORIGIN;
   private int initialBlockDamage;
   private int durabilityRemainingOnBlock = -1;

   public void updateBlockRemoving() {
      this.curblockDamage++;
      if (this.receivedFinishDiggingPacket) {
         int n = this.curblockDamage - this.initialBlockDamage;
         Block block = this.theWorld.getBlockState(this.delayedDestroyPos).getBlock();
         if (block.getMaterial() == Material.air) {
            this.receivedFinishDiggingPacket = false;
         } else {
            float f = block.getPlayerRelativeBlockHardness(this.thisPlayerMP, this.thisPlayerMP.entityWorld, this.delayedDestroyPos) * (float)(n + 1);
            int n2 = (int)(f * 10.0F);
            if (n2 != this.durabilityRemainingOnBlock) {
               this.theWorld.sendBlockBreakProgress(this.thisPlayerMP.getEntityId(), this.delayedDestroyPos, n2);
               this.durabilityRemainingOnBlock = n2;
            }

            if (f >= 1.0F) {
               this.receivedFinishDiggingPacket = false;
               this.tryHarvestBlock(this.delayedDestroyPos);
            }
         }
      } else if (this.isDestroyingBlock) {
         Block block2 = this.theWorld.getBlockState(this.destroyPos).getBlock();
         if (block2.getMaterial() == Material.air) {
            this.theWorld.sendBlockBreakProgress(this.thisPlayerMP.getEntityId(), this.destroyPos, -1);
            this.durabilityRemainingOnBlock = -1;
            this.isDestroyingBlock = false;
         } else {
            int n3 = this.curblockDamage - this.initialDamage;
            int n4 = (int)((block2.getPlayerRelativeBlockHardness(this.thisPlayerMP, this.thisPlayerMP.entityWorld, this.delayedDestroyPos) * (float)(n3 + 1)) * 10.0F);
            if (n4 != this.durabilityRemainingOnBlock) {
               this.theWorld.sendBlockBreakProgress(this.thisPlayerMP.getEntityId(), this.destroyPos, n4);
               this.durabilityRemainingOnBlock = n4;
            }
         }
      }
   }

   public ItemInWorldManager(World world) {
      this.theWorld = world;
   }

   private boolean removeBlock(BlockPos pos) {
      IBlockState blockState = this.theWorld.getBlockState(pos);
      blockState.getBlock().onBlockHarvested(this.theWorld, pos, blockState, this.thisPlayerMP);
      boolean flag = this.theWorld.setBlockToAir(pos);
      if (flag) {
         blockState.getBlock().onBlockDestroyedByPlayer(this.theWorld, pos, blockState);
      }

      return flag;
   }

   public void blockRemoving(BlockPos pos) {
      if (pos.equals(this.destroyPos)) {
         int n = this.curblockDamage - this.initialDamage;
         Block block = this.theWorld.getBlockState(pos).getBlock();
         if (block.getMaterial() != Material.air) {
            if ((block.getPlayerRelativeBlockHardness(this.thisPlayerMP, this.thisPlayerMP.entityWorld, pos) * (float)(n + 1)) >= 0.7F) {
               this.isDestroyingBlock = false;
               this.theWorld.sendBlockBreakProgress(this.thisPlayerMP.getEntityId(), pos, -1);
               this.tryHarvestBlock(pos);
            } else if (!this.receivedFinishDiggingPacket) {
               this.isDestroyingBlock = false;
               this.receivedFinishDiggingPacket = true;
               this.delayedDestroyPos = pos;
               this.initialBlockDamage = this.initialDamage;
            }
         }
      }
   }

   public WorldSettings.GameType getGameType() {
      return this.gameType;
   }

   public void cancelDestroyingBlock() {
      this.isDestroyingBlock = false;
      this.theWorld.sendBlockBreakProgress(this.thisPlayerMP.getEntityId(), this.destroyPos, -1);
   }

   public void setGameType(WorldSettings.GameType gameType2) {
      this.gameType = gameType2;
      gameType2.configurePlayerCapabilities(this.thisPlayerMP.capabilities);
      this.thisPlayerMP.sendPlayerAbilities();
      this.thisPlayerMP
         .mcServer
         .getConfigurationManager()
         .sendPacketToAllPlayers(new S38PacketPlayerListItem(S38PacketPlayerListItem.Action.UPDATE_GAME_MODE, new EntityPlayerMP[]{this.thisPlayerMP}));
   }

   public boolean tryUseItem(EntityPlayer player, World world, ItemStack stack) {
      if (this.gameType == WorldSettings.GameType.SPECTATOR) {
         return false;
      } else {
         int stackSize = stack.stackSize;
         int metadata = stack.getMetadata();
         ItemStack stack2 = stack.useItemRightClick(world, player);
         if (stack2 != stack || stack2 != null && (stack2.stackSize != stackSize || stack2.getMaxItemUseDuration() > 0 || stack2.getMetadata() != metadata)) {
            player.inventory.mainInventory[player.inventory.currentItem] = stack2;
            if (this.isCreative()) {
               stack2.stackSize = stackSize;
               if (stack2.isItemStackDamageable()) {
                  stack2.setItemDamage(metadata);
               }
            }

            if (stack2.stackSize == 0) {
               player.inventory.mainInventory[player.inventory.currentItem] = null;
            }

            if (!player.isUsingItem()) {
               ((EntityPlayerMP)player).sendContainerToPlayer(player.inventoryContainer);
            }

            return true;
         } else {
            return false;
         }
      }
   }

   public boolean survivalOrAdventure() {
      return this.gameType.isSurvivalOrAdventure();
   }

   public void setWorld(WorldServer world) {
      this.theWorld = world;
   }

   public boolean activateBlockOrUseItem(EntityPlayer player, World world, ItemStack stack, BlockPos pos, EnumFacing facing, float f, float f2, float f3) {
      if (this.gameType == WorldSettings.GameType.SPECTATOR) {
         TileEntity tileEntity = world.getTileEntity(pos);
         if (tileEntity instanceof ILockableContainer) {
            Block block = world.getBlockState(pos).getBlock();
            ILockableContainer lockableContainer = (ILockableContainer)tileEntity;
            if (lockableContainer instanceof TileEntityChest && block instanceof BlockChest) {
               lockableContainer = ((BlockChest)block).getLockableContainer(world, pos);
            }

            if (lockableContainer != null) {
               player.displayGUIChest(lockableContainer);
               return true;
            }
         } else if (tileEntity instanceof IInventory) {
            player.displayGUIChest((IInventory)tileEntity);
            return true;
         }

         return false;
      } else {
         if (!player.isSneaking() || player.getHeldItem() == null) {
            IBlockState blockState = world.getBlockState(pos);
            if (blockState.getBlock().onBlockActivated(world, pos, blockState, player, facing, f, f2, f3)) {
               return true;
            }
         }

         if (stack == null) {
            return false;
         } else if (this.isCreative()) {
            int metadata = stack.getMetadata();
            int stackSize = stack.stackSize;
            boolean flag = stack.onItemUse(player, world, pos, facing, f, f2, f3);
            stack.setItemDamage(metadata);
            stack.stackSize = stackSize;
            return flag;
         } else {
            return stack.onItemUse(player, world, pos, facing, f, f2, f3);
         }
      }
   }

   public void initializeGameType(WorldSettings.GameType gameType2) {
      if (this.gameType == WorldSettings.GameType.NOT_SET) {
         this.gameType = gameType2;
      }

      this.setGameType(this.gameType);
   }

   public boolean isCreative() {
      return this.gameType.isCreative();
   }

   public boolean tryHarvestBlock(BlockPos pos) {
      if (this.gameType.isCreative() && this.thisPlayerMP.getHeldItem() != null && this.thisPlayerMP.getHeldItem().getItem() instanceof ItemSword) {
         return false;
      } else {
         IBlockState blockState = this.theWorld.getBlockState(pos);
         TileEntity tileEntity = this.theWorld.getTileEntity(pos);
         if (this.gameType.isAdventure()) {
            if (this.gameType == WorldSettings.GameType.SPECTATOR) {
               return false;
            }

            if (!this.thisPlayerMP.isAllowEdit()) {
               ItemStack currentEquippedItem = this.thisPlayerMP.getCurrentEquippedItem();
               if (currentEquippedItem == null) {
                  return false;
               }

               if (!currentEquippedItem.canDestroy(blockState.getBlock())) {
                  return false;
               }
            }
         }

         this.theWorld.playAuxSFXAtEntity(this.thisPlayerMP, 2001, pos, Block.getStateId(blockState));
         boolean flag = this.removeBlock(pos);
         if (this.isCreative()) {
            this.thisPlayerMP.playerNetServerHandler.sendPacket(new S23PacketBlockChange(this.theWorld, pos));
         } else {
            ItemStack currentEquippedItem2 = this.thisPlayerMP.getCurrentEquippedItem();
            boolean flag2 = this.thisPlayerMP.canHarvestBlock(blockState.getBlock());
            if (currentEquippedItem2 != null) {
               currentEquippedItem2.onBlockDestroyed(this.theWorld, blockState.getBlock(), pos, this.thisPlayerMP);
               if (currentEquippedItem2.stackSize == 0) {
                  this.thisPlayerMP.destroyCurrentEquippedItem();
               }
            }

            if (flag && flag2) {
               blockState.getBlock().harvestBlock(this.theWorld, this.thisPlayerMP, pos, blockState, tileEntity);
            }
         }

         return flag;
      }
   }

   public void onBlockClicked(BlockPos pos, EnumFacing facing) {
      if (this.isCreative()) {
         if (!this.theWorld.extinguishFire((EntityPlayer)null, pos, facing)) {
            this.tryHarvestBlock(pos);
         }
      } else {
         Block block = this.theWorld.getBlockState(pos).getBlock();
         if (this.gameType.isAdventure()) {
            if (this.gameType == WorldSettings.GameType.SPECTATOR) {
               return;
            }

            if (!this.thisPlayerMP.isAllowEdit()) {
               ItemStack currentEquippedItem = this.thisPlayerMP.getCurrentEquippedItem();
               if (currentEquippedItem == null) {
                  return;
               }

               if (!currentEquippedItem.canDestroy(block)) {
                  return;
               }
            }
         }

         this.theWorld.extinguishFire((EntityPlayer)null, pos, facing);
         this.initialDamage = this.curblockDamage;
         float f = 1.0F;
         if (block.getMaterial() != Material.air) {
            block.onBlockClicked(this.theWorld, pos, this.thisPlayerMP);
            f = block.getPlayerRelativeBlockHardness(this.thisPlayerMP, this.thisPlayerMP.entityWorld, pos);
         }

         if (block.getMaterial() != Material.air && f >= 1.0F) {
            this.tryHarvestBlock(pos);
         } else {
            this.isDestroyingBlock = true;
            this.destroyPos = pos;
            int n = (int)(f * 10.0F);
            this.theWorld.sendBlockBreakProgress(this.thisPlayerMP.getEntityId(), pos, n);
            this.durabilityRemainingOnBlock = n;
         }
      }
   }
}
