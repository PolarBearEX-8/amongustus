package net.minecraft.client.multiplayer;

import net.minecraft.world.WorldSettings;
import de.florianmichael.dietrichevents2.StateTypes;
import net.augustus.modules.world.FastBreak;
import net.augustus.events.listeners.BlockBreakListener;
import net.augustus.ClientManagers;
import net.augustus.events.listeners.BlockPlaceListener;
import net.augustus.events.BlockPlaceStage;
import net.augustus.events.EventBlockBreak;
import net.augustus.Augustus;
import net.augustus.events.listeners.AttackEntityListener;
import net.augustus.events.EventManager;
import net.augustus.utils.TimeHelper;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBed;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.network.play.client.C02PacketUseEntity;
import net.minecraft.network.play.client.C07PacketPlayerDigging;
import net.minecraft.network.play.client.C08PacketPlayerBlockPlacement;
import net.minecraft.network.play.client.C09PacketHeldItemChange;
import net.minecraft.network.play.client.C0EPacketClickWindow;
import net.minecraft.network.play.client.C10PacketCreativeInventoryAction;
import net.minecraft.network.play.client.C11PacketEnchantItem;
import net.minecraft.stats.StatFileWriter;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class PlayerControllerMP {
   private final Minecraft mc;
   private final NetHandlerPlayClient netClientHandler;
   public BlockPos currentBlock = new BlockPos(-1, -1, -1);
   private ItemStack currentItemHittingBlock;
   private float curBlockDamageMP;
   private float stepSoundTickCounter;
   private int blockHitDelay;
   public boolean isHittingBlock;
   private WorldSettings.GameType currentGameType = WorldSettings.GameType.SURVIVAL;
   public int currentPlayerItem;
   public final TimeHelper timeHelper = new TimeHelper();

   public boolean isPlayerRightClickingOnEntity(EntityPlayer player, Entity entity, MovingObjectPosition hit) {
      this.syncCurrentPlayItem();
      Vec3 vec = new Vec3(hit.hitVec.xCoord - entity.posX, hit.hitVec.yCoord - entity.posY, hit.hitVec.zCoord - entity.posZ);
      this.netClientHandler.addToSendQueue(new C02PacketUseEntity(entity, vec));
      return this.currentGameType != WorldSettings.GameType.SPECTATOR && entity.interactAt(player, vec);
   }

   public void flipPlayer(EntityPlayer player) {
      player.rotationYaw = -180.0F;
   }

   public boolean shouldDrawHUD() {
      return this.currentGameType.isSurvivalOrAdventure();
   }

   public boolean onPlayerRightClick(EntityPlayerSP player, WorldClient world, ItemStack stack, BlockPos pos, EnumFacing facing, Vec3 vec) {
      this.syncCurrentPlayItem();
      float f = (float)(vec.xCoord - (double)pos.getX());
      float f2 = (float)(vec.yCoord - (double)pos.getY());
      float f3 = (float)(vec.zCoord - (double)pos.getZ());
      boolean flag = false;
      if (!this.mc.theWorld.getWorldBorder().contains(pos)) {
         return false;
      } else {
         if (this.currentGameType != WorldSettings.GameType.SPECTATOR) {
            IBlockState blockState = world.getBlockState(pos);
            if ((!player.isSneaking() || player.getHeldItem() == null) && blockState.getBlock().onBlockActivated(world, pos, blockState, player, facing, f, f2, f3)) {
               flag = true;
            }

            if (!flag && stack != null && stack.getItem() instanceof ItemBlock) {
               ItemBlock item = (ItemBlock)stack.getItem();
               if ((!ClientManagers.moduleManager.fly.isToggled() || !ClientManagers.moduleManager.fly.mode.getSelectedValue().equals("AirPlace"))
                  && !item.canPlaceBlockOnSide(world, pos, facing, player, stack)) {
                  return false;
               }
            }
         }

         this.netClientHandler.addToSendQueue(new C08PacketPlayerBlockPlacement(pos, facing.getIndex(), stack, f, f2, f3));
         if (flag || this.currentGameType == WorldSettings.GameType.SPECTATOR) {
            BlockPlaceListener.postBlockPlace(BlockPlaceStage.POST);
            return true;
         } else if (stack == null) {
            return false;
         } else if (this.currentGameType.isCreative()) {
            int metadata = stack.getMetadata();
            int stackSize = stack.stackSize;
            boolean flag2 = stack.onItemUse(player, world, pos, facing, f, f2, f3);
            stack.setItemDamage(metadata);
            stack.stackSize = stackSize;
            if (flag2) {
               BlockPlaceListener.postBlockPlace(BlockPlaceStage.POST);
            }

            return flag2;
         } else {
            boolean flag3 = stack.onItemUse(player, world, pos, facing, f, f2, f3);
            if (flag3) {
               BlockPlaceListener.postBlockPlace(BlockPlaceStage.POST);
            }

            return flag3;
         }
      }
   }

   public boolean interactWithEntitySendPacket(EntityPlayer player, Entity entity) {
      this.syncCurrentPlayItem();
      this.netClientHandler.addToSendQueue(new C02PacketUseEntity(entity, C02PacketUseEntity.Action.INTERACT));
      return this.currentGameType != WorldSettings.GameType.SPECTATOR && player.interactWith(entity);
   }

   public void onStoppedUsingItem(EntityPlayer player) {
      this.syncCurrentPlayItem();
      this.netClientHandler
         .addToSendQueue(
            new C07PacketPlayerDigging(net.minecraft.network.play.client.C07PacketPlayerDigging.Action.RELEASE_USE_ITEM, BlockPos.ORIGIN, EnumFacing.DOWN)
         );
      player.stopUsingItem();
   }

   public void attackEntity(EntityPlayer player, Entity entity) {
      AttackEntityListener.onAttackEntity(entity, StateTypes.PRE);
      this.syncCurrentPlayItem();
      this.netClientHandler.addToSendQueue(new C02PacketUseEntity(entity, C02PacketUseEntity.Action.ATTACK));
      if (entity instanceof EntityLivingBase && ((EntityLivingBase)entity).hurtTime == 0 && System.currentTimeMillis() - entity.lastDamageAttackTime > 225L) {
         entity.lastDamageAttackTime = System.currentTimeMillis();
      }

      if (entity instanceof EntityLivingBase) {
         entity.lastAttackTime = System.currentTimeMillis();
      }

      AttackEntityListener.onAttackEntity(entity, StateTypes.POST);
      if (this.currentGameType != WorldSettings.GameType.SPECTATOR) {
         player.attackTargetEntityWithCurrentItem(entity);
         if (player instanceof EntityPlayerSP) {
            this.mc.thePlayer.resetTicksSinceLastSwing();
         }
      }
   }

   public PlayerControllerMP(Minecraft mc2, NetHandlerPlayClient netHandlerPlayClient) {
      this.mc = mc2;
      this.netClientHandler = netHandlerPlayClient;
   }

   public EntityPlayerSP createClientPlayer(World world, StatFileWriter statFileWriter) {
      return new EntityPlayerSP(this.mc, world, this.netClientHandler, statFileWriter);
   }

   public boolean sendUseItem(EntityPlayer player, World world, ItemStack stack) {
      if (this.currentGameType == WorldSettings.GameType.SPECTATOR) {
         return false;
      } else {
         this.syncCurrentPlayItem();
         this.netClientHandler.addToSendQueue(new C08PacketPlayerBlockPlacement(stack));
         int stackSize = stack.stackSize;
         ItemStack stack2 = stack.useItemRightClick(world, player);
         if (stack2 != null && (stack2 != stack || stack2 != null && stack2.stackSize != stackSize)) {
            player.inventory.mainInventory[player.inventory.currentItem] = stack2;
            if (stack2.stackSize == 0) {
               player.inventory.mainInventory[player.inventory.currentItem] = null;
            }

            return true;
         } else {
            return false;
         }
      }
   }

   public boolean onPlayerDestroyBlock(BlockPos pos, EnumFacing facing) {
      if (this.currentGameType.isAdventure()) {
         if (this.currentGameType == WorldSettings.GameType.SPECTATOR) {
            return false;
         }

         if (!this.mc.thePlayer.isAllowEdit()) {
            Block block = this.mc.theWorld.getBlockState(pos).getBlock();
            ItemStack currentEquippedItem = this.mc.thePlayer.getCurrentEquippedItem();
            if (currentEquippedItem == null) {
               return false;
            }

            if (!currentEquippedItem.canDestroy(block)) {
               return false;
            }
         }
      }

      if (this.currentGameType.isCreative() && this.mc.thePlayer.getHeldItem() != null && this.mc.thePlayer.getHeldItem().getItem() instanceof ItemSword) {
         return false;
      } else {
         WorldClient theWorld = this.mc.theWorld;
         IBlockState blockState = theWorld.getBlockState(pos);
         Block block2 = blockState.getBlock();
         if (block2.getMaterial() == Material.air) {
            return false;
         } else {
            theWorld.playAuxSFX(2001, pos, Block.getStateId(blockState));
            boolean flag = theWorld.setBlockToAir(pos);
            if (flag) {
               block2.onBlockDestroyedByPlayer(theWorld, pos, blockState);
            }

            this.currentBlock = new BlockPos(this.currentBlock.getX(), -1, this.currentBlock.getZ());
            if (!this.currentGameType.isCreative()) {
               ItemStack currentEquippedItem2 = this.mc.thePlayer.getCurrentEquippedItem();
               if (currentEquippedItem2 != null) {
                  currentEquippedItem2.onBlockDestroyed(theWorld, block2, pos, this.mc.thePlayer);
                  if (currentEquippedItem2.stackSize == 0) {
                     this.mc.thePlayer.destroyCurrentEquippedItem();
                  }
               }
            }

            return flag;
         }
      }
   }

   public void abortBlockBreaking(boolean flag) {
      if (flag) {
         this.netClientHandler
            .addToSendQueue(
               new C07PacketPlayerDigging(
                  net.minecraft.network.play.client.C07PacketPlayerDigging.Action.ABORT_DESTROY_BLOCK, this.currentBlock, EnumFacing.DOWN
               )
            );
         this.curBlockDamageMP = 0.0F;
         this.mc.theWorld.sendBlockBreakProgress(this.mc.thePlayer.getEntityId(), this.currentBlock, -1);
      }
   }

   public boolean clickBlockWithItem(BlockPos pos, EnumFacing facing, ItemStack stack) {
      if (this.currentGameType.isAdventure()) {
         if (this.currentGameType == WorldSettings.GameType.SPECTATOR) {
            return false;
         }

         if (!this.mc.thePlayer.isAllowEdit()) {
            Block block = this.mc.theWorld.getBlockState(pos).getBlock();
            if (stack == null) {
               return false;
            }

            if (!stack.canDestroy(block)) {
               return false;
            }
         }
      }

      if (!this.mc.theWorld.getWorldBorder().contains(pos)) {
         return false;
      } else {
         if (this.currentGameType.isCreative()) {
            this.netClientHandler
               .addToSendQueue(new C07PacketPlayerDigging(net.minecraft.network.play.client.C07PacketPlayerDigging.Action.START_DESTROY_BLOCK, pos, facing));
            clickBlockCreative(this.mc, this, pos, facing);
            this.resetBlockHitDelay();
         } else if (!this.isHittingBlock || !this.isHittingPosition(pos)) {
            if (this.isHittingBlock) {
               this.netClientHandler
                  .addToSendQueue(
                     new C07PacketPlayerDigging(net.minecraft.network.play.client.C07PacketPlayerDigging.Action.ABORT_DESTROY_BLOCK, this.currentBlock, facing)
                  );
            }

            this.netClientHandler
               .addToSendQueue(new C07PacketPlayerDigging(net.minecraft.network.play.client.C07PacketPlayerDigging.Action.START_DESTROY_BLOCK, pos, facing));
            Block block2 = this.mc.theWorld.getBlockState(pos).getBlock();
            boolean flag = block2.getMaterial() != Material.air;
            if (flag && this.curBlockDamageMP == 0.0F) {
               block2.onBlockClicked(this.mc.theWorld, pos, this.mc.thePlayer);
            }

            if (flag && block2.getPlayerRelativeBlockHardness(this.mc.thePlayer, this.mc.thePlayer.entityWorld, pos) >= 1.0F) {
               this.onPlayerDestroyBlock(pos, facing);
            } else {
               this.isHittingBlock = true;
               this.currentBlock = pos;
               this.currentItemHittingBlock = this.mc.thePlayer.getHeldItem();
               this.curBlockDamageMP = 0.0F;
               this.stepSoundTickCounter = 0.0F;
               this.mc.theWorld.sendBlockBreakProgress(this.mc.thePlayer.getEntityId(), this.currentBlock, (int)(this.curBlockDamageMP * 10.0F) - 1);
            }
         }

         return true;
      }
   }

   private void resetBlockHitDelay() {
      this.blockHitDelay = 5;
   }

   public void sendPacketDropItem(ItemStack stack) {
      if (this.currentGameType.isCreative() && stack != null) {
         this.netClientHandler.addToSendQueue(new C10PacketCreativeInventoryAction(-1, stack));
      }
   }

   private boolean isHittingPosition(BlockPos pos) {
      ItemStack heldItem = this.mc.thePlayer.getHeldItem();
      boolean flag = this.currentItemHittingBlock == null && heldItem == null;
      if (this.currentItemHittingBlock != null && heldItem != null) {
         flag = heldItem.getItem() == this.currentItemHittingBlock.getItem()
            && ItemStack.areItemStackTagsEqual(heldItem, this.currentItemHittingBlock)
            && (heldItem.isItemStackDamageable() || heldItem.getMetadata() == this.currentItemHittingBlock.getMetadata());
      }

      return pos.equals(this.currentBlock) && flag;
   }

   public WorldSettings.GameType getCurrentGameType() {
      return this.currentGameType;
   }

   public void updateController() {
      this.syncCurrentPlayItem();
      if (this.netClientHandler.getNetworkManager().isChannelOpen()) {
         this.netClientHandler.getNetworkManager().processReceivedPackets();
      } else {
         this.netClientHandler.getNetworkManager().checkDisconnected();
      }
   }

   private boolean checkBlockHitDelay(BlockPos pos) {
      boolean flag = this.blockHitDelay > 0;
      int blockHitDelay2 = this.blockHitDelay;
      if (flag) {
         this.blockHitDelay--;
      }

      if (ClientManagers.moduleManager.fastBreak.customBlockHitDelay.getValue()) {
         if (ClientManagers.moduleManager.fastBreak.shouldFastBreakBlock(this.mc.theWorld.getBlockState(pos).getBlock())) {
            if (blockHitDelay2 <= ((int)(5.0 - ClientManagers.moduleManager.fastBreak.blockHitDelay.getValue(true)))) {
               flag = false;
            }
         }
      }

      return flag;
   }

   public ItemStack windowClick(int n, int n2, int n3, int n4, EntityPlayer player) {
      short nextTransactionID = player.openContainer.getNextTransactionID(player.inventory);

      ItemStack stack;
      try {
         stack = player.openContainer.slotClick(n2, n3, n4, player);
      } catch (IndexOutOfBoundsException e) {
         EventManager.reportException(new RuntimeException(player.openContainer + " " + n + " " + n2 + " " + n3 + " " + n4, e));
         return null;
      }

      this.netClientHandler.addToSendQueue(new C0EPacketClickWindow(n, n2, n3, n4, stack, nextTransactionID));
      return stack;
   }

   public boolean gameIsSurvivalOrAdventure() {
      return this.currentGameType.isSurvivalOrAdventure();
   }

   public boolean isRidingHorse() {
      return this.mc.thePlayer.isRiding() && this.mc.thePlayer.ridingEntity instanceof EntityHorse;
   }

   public void setCurBlockDamageMP(float f) {
      this.curBlockDamageMP = f;
   }

   public boolean clickBlock(BlockPos pos, EnumFacing facing) {
      if (this.currentGameType.isAdventure()) {
         if (this.currentGameType == WorldSettings.GameType.SPECTATOR) {
            return false;
         }

         if (!this.mc.thePlayer.isAllowEdit()) {
            Block block = this.mc.theWorld.getBlockState(pos).getBlock();
            ItemStack currentEquippedItem = this.mc.thePlayer.getCurrentEquippedItem();
            if (currentEquippedItem == null) {
               return false;
            }

            if (!currentEquippedItem.canDestroy(block)) {
               return false;
            }
         }
      }

      if (!this.mc.theWorld.getWorldBorder().contains(pos)) {
         return false;
      } else {
         if (this.currentGameType.isCreative()) {
            this.netClientHandler
               .addToSendQueue(new C07PacketPlayerDigging(net.minecraft.network.play.client.C07PacketPlayerDigging.Action.START_DESTROY_BLOCK, pos, facing));
            clickBlockCreative(this.mc, this, pos, facing);
            this.resetBlockHitDelay();
         } else if (!this.isHittingBlock || !this.isHittingPosition(pos)) {
            if (this.isHittingBlock) {
               this.netClientHandler
                  .addToSendQueue(
                     new C07PacketPlayerDigging(net.minecraft.network.play.client.C07PacketPlayerDigging.Action.ABORT_DESTROY_BLOCK, this.currentBlock, facing)
                  );
            }

            this.netClientHandler
               .addToSendQueue(new C07PacketPlayerDigging(net.minecraft.network.play.client.C07PacketPlayerDigging.Action.START_DESTROY_BLOCK, pos, facing));
            Block block2 = this.mc.theWorld.getBlockState(pos).getBlock();
            boolean flag = block2.getMaterial() != Material.air;
            if (flag && this.curBlockDamageMP == 0.0F) {
               block2.onBlockClicked(this.mc.theWorld, pos, this.mc.thePlayer);
            }

            if (flag && block2.getPlayerRelativeBlockHardness(this.mc.thePlayer, this.mc.thePlayer.entityWorld, pos) >= 1.0F) {
               if (!BlockBreakListener.onBlockBreak().isCancelled()) {
                  this.onPlayerDestroyBlock(pos, facing);
               }
            } else {
               this.isHittingBlock = true;
               this.currentBlock = pos;
               this.currentItemHittingBlock = this.mc.thePlayer.getHeldItem();
               this.curBlockDamageMP = 0.0F;
               this.stepSoundTickCounter = 0.0F;
               this.mc.theWorld.sendBlockBreakProgress(this.mc.thePlayer.getEntityId(), this.currentBlock, (int)(this.curBlockDamageMP * 10.0F) - 1);
            }
         }

         return true;
      }
   }

   public void syncCurrentPlayItem() {
      int currentItem = this.mc.thePlayer.inventory.currentItem;
      if (currentItem != this.currentPlayerItem) {
         this.currentPlayerItem = currentItem;
         this.netClientHandler.addToSendQueue(new C09PacketHeldItemChange(this.currentPlayerItem));
         this.timeHelper.reset();
      }
   }

   public float getCurBlockDamageMP() {
      return this.curBlockDamageMP;
   }

   public boolean isNotCreative() {
      return !this.currentGameType.isCreative();
   }

   public static void clickBlockCreative(Minecraft mc2, PlayerControllerMP playerControllerMP, BlockPos pos, EnumFacing facing) {
      if (!mc2.theWorld.extinguishFire(mc2.thePlayer, pos, facing)) {
         playerControllerMP.onPlayerDestroyBlock(pos, facing);
      }
   }

   public boolean isSpectator() {
      return this.currentGameType == WorldSettings.GameType.SPECTATOR;
   }

   public void setPlayerCapabilities(EntityPlayer player) {
      this.currentGameType.configurePlayerCapabilities(player.capabilities);
   }

   public void sendSlotPacket(ItemStack stack, int n) {
      if (this.currentGameType.isCreative()) {
         this.netClientHandler.addToSendQueue(new C10PacketCreativeInventoryAction(n, stack));
      }
   }

   public boolean onPlayerDamageBlock(BlockPos pos, EnumFacing facing) {
      this.syncCurrentPlayItem();
      if (this.checkBlockHitDelay(pos)) {
         return true;
      } else if (this.currentGameType.isCreative() && this.mc.theWorld.getWorldBorder().contains(pos)) {
         this.resetBlockHitDelay();
         this.netClientHandler
            .addToSendQueue(new C07PacketPlayerDigging(net.minecraft.network.play.client.C07PacketPlayerDigging.Action.START_DESTROY_BLOCK, pos, facing));
         clickBlockCreative(this.mc, this, pos, facing);
         return true;
      } else if (this.isHittingPosition(pos)) {
         Block block = this.mc.theWorld.getBlockState(pos).getBlock();
         if (block.getMaterial() == Material.air) {
            this.isHittingBlock = false;
            return false;
         } else {
            ClientManagers.moduleManager.fastBreak.logDebugBreakSpeed(block, pos);
            if (ClientManagers.moduleManager.fastBreak.shouldFastBreakBlock(block)
               && this.curBlockDamageMP >= 0.0F
               && (!(block instanceof BlockBed) || !ClientManagers.moduleManager.fastBreak.ignoreBeds.getValue())
               && (!ClientManagers.moduleManager.fastBreak.onlyWhenBlink.getValue() || ClientManagers.moduleManager.packetHandler.hasQueuedOutgoingPackets() || ClientManagers.moduleManager.packetHandler.isDelayingIncoming())) {
               if (ClientManagers.moduleManager.fastBreak.isInstantBreak(block)) {
                  this.curBlockDamageMP = 2.0F;
               } else {
                  this.curBlockDamageMP = this.curBlockDamageMP + block.getPlayerRelativeBlockHardness(this.mc.thePlayer, this.mc.thePlayer.entityWorld, pos) * (float)ClientManagers.moduleManager.fastBreak.getBreakSpeedMultiplier(block);
               }
            } else {
               this.curBlockDamageMP = this.curBlockDamageMP + block.getPlayerRelativeBlockHardness(this.mc.thePlayer, this.mc.thePlayer.entityWorld, pos);
            }

            if (this.stepSoundTickCounter % 4.0F == 0.0F) {
               this.mc
                  .getSoundHandler()
                  .playSound(
                     new PositionedSoundRecord(
                        new ResourceLocation(block.stepSound.getStepSound()),
                        (block.stepSound.getVolume() + 1.0F) / 8.0F,
                        block.stepSound.getFrequency() * 0.5F,
                        (float)pos.getX() + 0.5F,
                        (float)pos.getY() + 0.5F,
                        (float)pos.getZ() + 0.5F
                     )
                  );
            }

            this.stepSoundTickCounter++;
            if (this.curBlockDamageMP >= 1.0F) {
               if (!BlockBreakListener.onBlockBreak().isCancelled()) {
                  this.isHittingBlock = false;
                  this.netClientHandler
                     .addToSendQueue(new C07PacketPlayerDigging(net.minecraft.network.play.client.C07PacketPlayerDigging.Action.STOP_DESTROY_BLOCK, pos, facing));
                  this.onPlayerDestroyBlock(pos, facing);
                  this.curBlockDamageMP = 0.0F;
                  this.stepSoundTickCounter = 0.0F;
                  this.resetBlockHitDelay();
               } else {
                  this.curBlockDamageMP = 0.0F;
               }
            }

            this.mc.theWorld.sendBlockBreakProgress(this.mc.thePlayer.getEntityId(), this.currentBlock, (int)(this.curBlockDamageMP * 10.0F) - 1);
            return true;
         }
      } else {
         return this.clickBlock(pos, facing);
      }
   }

   public void sendEnchantPacket(int n, int n2) {
      this.netClientHandler.addToSendQueue(new C11PacketEnchantItem(n, n2));
   }

   public void setGameType(WorldSettings.GameType gameType) {
      this.currentGameType = gameType;
      this.currentGameType.configurePlayerCapabilities(this.mc.thePlayer.capabilities);
   }

   public boolean isSpectatorMode() {
      return this.currentGameType == WorldSettings.GameType.SPECTATOR;
   }

   public boolean extendedReach() {
      return this.currentGameType.isCreative();
   }

   public void resetBlockRemoving() {
      if (this.isHittingBlock) {
         this.netClientHandler
            .addToSendQueue(
               new C07PacketPlayerDigging(
                  net.minecraft.network.play.client.C07PacketPlayerDigging.Action.ABORT_DESTROY_BLOCK, this.currentBlock, EnumFacing.DOWN
               )
            );
         this.isHittingBlock = false;
         this.curBlockDamageMP = 0.0F;
         this.mc.theWorld.sendBlockBreakProgress(this.mc.thePlayer.getEntityId(), this.currentBlock, -1);
      }
   }

   public boolean isInCreativeMode() {
      return this.currentGameType.isCreative();
   }

   public float getBlockReachDistance() {
      return this.currentGameType.isCreative() ? 5.0F : 4.5F;
   }

   public boolean onPlayerDamageBlockWithSlot(BlockPos pos, EnumFacing facing, int n) {
      this.syncCurrentPlayItem();
      if (this.checkBlockHitDelay(pos)) {
         return true;
      } else if (this.currentGameType.isCreative() && this.mc.theWorld.getWorldBorder().contains(pos)) {
         this.resetBlockHitDelay();
         this.netClientHandler
            .addToSendQueue(new C07PacketPlayerDigging(net.minecraft.network.play.client.C07PacketPlayerDigging.Action.START_DESTROY_BLOCK, pos, facing));
         clickBlockCreative(this.mc, this, pos, facing);
         return true;
      } else if (this.isHittingPosition(pos)) {
         Block block = this.mc.theWorld.getBlockState(pos).getBlock();
         if (block.getMaterial() == Material.air) {
            this.isHittingBlock = false;
            return false;
         } else {
            ClientManagers.moduleManager.fastBreak.logDebugBreakSpeed(block, pos);
            if (ClientManagers.moduleManager.fastBreak.shouldFastBreakBlock(block)
               && this.curBlockDamageMP >= 0.0F
               && (!(block instanceof BlockBed) || !ClientManagers.moduleManager.fastBreak.ignoreBeds.getValue())
               && (!ClientManagers.moduleManager.fastBreak.onlyWhenBlink.getValue() || ClientManagers.moduleManager.packetHandler.hasQueuedOutgoingPackets() || ClientManagers.moduleManager.packetHandler.isDelayingIncoming())) {
               if (ClientManagers.moduleManager.fastBreak.isInstantBreak(block)) {
                  this.curBlockDamageMP = 2.0F;
               } else {
                  this.curBlockDamageMP = this.curBlockDamageMP + block.getPlayerRelativeBlockHardness(this.mc.thePlayer, this.mc.thePlayer.entityWorld, pos) * (float)ClientManagers.moduleManager.fastBreak.getBreakSpeedMultiplier(block);
               }
            } else {
               this.curBlockDamageMP = this.curBlockDamageMP + block.getPlayerRelativeBlockHardness(this.mc.thePlayer, this.mc.thePlayer.entityWorld, pos);
            }

            if (this.stepSoundTickCounter % 4.0F == 0.0F) {
               this.mc
                  .getSoundHandler()
                  .playSound(
                     new PositionedSoundRecord(
                        new ResourceLocation(block.stepSound.getStepSound()),
                        (block.stepSound.getVolume() + 1.0F) / 8.0F,
                        block.stepSound.getFrequency() * 0.5F,
                        (float)pos.getX() + 0.5F,
                        (float)pos.getY() + 0.5F,
                        (float)pos.getZ() + 0.5F
                     )
                  );
            }

            this.stepSoundTickCounter++;
            if (this.curBlockDamageMP >= 1.0F) {
               if (!BlockBreakListener.onBlockBreak().isCancelled()) {
                  this.isHittingBlock = false;
                  this.netClientHandler
                     .addToSendQueue(new C07PacketPlayerDigging(net.minecraft.network.play.client.C07PacketPlayerDigging.Action.STOP_DESTROY_BLOCK, pos, facing));
                  this.onPlayerDestroyBlock(pos, facing);
                  this.curBlockDamageMP = 0.0F;
                  this.stepSoundTickCounter = 0.0F;
                  this.resetBlockHitDelay();
               } else {
                  this.curBlockDamageMP = 0.0F;
               }
            }

            this.mc.theWorld.sendBlockBreakProgress(this.mc.thePlayer.getEntityId(), this.currentBlock, (int)(this.curBlockDamageMP * 10.0F) - 1);
            return true;
         }
      } else {
         return this.clickBlock(pos, facing);
      }
   }

   public boolean isHittingBlock() {
      return this.isHittingBlock;
   }

}
