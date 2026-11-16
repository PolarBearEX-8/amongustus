package net.minecraft.client.multiplayer;

import augustus.modules.world.FastBreak;
import client.Client;
import client.ClientGlobals;
import client.event.AttackEntityListener;
import client.event.EventBusManager;
import client.event.EventConnectionCallback;
import client.event.EventPreListener;
import client.module.Module;
import client.setting.PhaseMode;
import client.setting.WorldSettingsGameType;
import client.util.IBlockState;
import client.util.PlayerDiggingActionType;
import client.util.TimeHelper;
import client.util.UseEntityAction;
import de.florianmichael.dietrichevents2.StateTypes;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBed;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.entity.EntityPlayerSP;
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
   public final Minecraft mcClient;
   public final NetHandlerPlayClient netHandlerPlayClient;
   public BlockPos blockPos = new BlockPos(-1, -1, -1);
   public ItemStack itemStack;
   public float scaleFactor;
   public float scaleFactor1;
   public int indexOffset2;
   public boolean state;
   public WorldSettingsGameType worldSettingsGameType = WorldSettingsGameType.SURVIVAL;
   public int indexOffset;
   public final TimeHelper timer = new TimeHelper();

   public void setScaleFactor(float scaleFactor2) {
      this.scaleFactor = scaleFactor2;
   }

   public void updateController() {
      int currentItem = this.mcClient.thePlayer.inventoryPlayer.currentItem;
      if (currentItem != this.indexOffset) {
         this.indexOffset = currentItem;
         this.netHandlerPlayClient.handlePacket(new C09PacketHeldItemChange(this.indexOffset));
         this.timer.reset();
      }
   }

   public boolean matchesBlockPos(BlockPos blockPos2, EnumFacing enumFacing) {
      searchBlock: {
         this.updateController();
         if (this.indexOffset2 > 0) {
            if (!ClientGlobals.moduleManager.fastBreak.settingNoBlockHitDelay.isEnabled()) {
               break searchBlock;
            }

            FastBreak fastBreak = ClientGlobals.moduleManager.fastBreak;
            Block block = this.mcClient.theWorld.getBlockState(blockPos2).getBlock();
            if (!fastBreak.checkBlock2(block)) {
               break searchBlock;
            }
         }

         if (this.worldSettingsGameType.canExecute() && this.mcClient.theWorld.getInternalListEntry().checkBlockPos(blockPos2)) {
            this.indexOffset2 = 5;
            this.netHandlerPlayClient.handlePacket(new C07PacketPlayerDigging(PlayerDiggingActionType.START_DESTROY_BLOCK, blockPos2, enumFacing));
            applyMinecraft(this.mcClient, this, blockPos2, enumFacing);
            return true;
         }

         if (this.matchesBlockPos(blockPos2)) {
            Block block2 = this.mcClient.theWorld.getBlockState(blockPos2).getBlock();
            if (block2.getMaterial() == Material.coreItemEntry) {
               this.state = false;
               return false;
            }

            ClientGlobals.moduleManager.fastBreak.applyBlock(block2, blockPos2);
            if (ClientGlobals.moduleManager.fastBreak.checkBlock2(block2)
               && this.scaleFactor >= 0.0F
               && (!(block2 instanceof BlockBed) || !ClientGlobals.moduleManager.fastBreak.settingIgnoreBeds.isEnabled())
               && (
                  !ClientGlobals.moduleManager.fastBreak.settingOnlyWhenBlink.isEnabled()
                     || ClientGlobals.moduleManager.packetHandler.canExecute5()
                     || ClientGlobals.moduleManager.packetHandler.isEnabled2()
               )) {
               if (ClientGlobals.moduleManager.fastBreak.checkBlock3(block2)) {
                  this.scaleFactor = 2.0F;
               } else {
                  this.scaleFactor = this.scaleFactor
                     + block2.getScaleFactor(this.mcClient.thePlayer, this.mcClient.thePlayer.worldObj, blockPos2)
                        * (float)ClientGlobals.moduleManager.fastBreak.getCoordinate(block2);
               }
            } else {
               this.scaleFactor = this.scaleFactor + block2.getScaleFactor(this.mcClient.thePlayer, this.mcClient.thePlayer.worldObj, blockPos2);
            }

            if (this.scaleFactor1 % 4.0F == 0.0F) {
               this.mcClient
                  .getMcSoundHandler()
                  .applyISound2(
                     new PositionedSoundRecord(
                        new ResourceLocation(block2.stepManager20.getText()),
                        (block2.stepManager20.getFactor2() + 1.0F) / 8.0F,
                        block2.stepManager20.getFactor() * 0.5F,
                        (float)blockPos2.getX() + 0.5F,
                        (float)blockPos2.getY() + 0.5F,
                        (float)blockPos2.getZ() + 0.5F
                     )
                  );
            }

            this.scaleFactor1++;
            if (this.scaleFactor >= 1.0F) {
               if (!EventPreListener.getEventPreAction().isCancelled()) {
                  this.state = false;
                  this.netHandlerPlayClient.handlePacket(new C07PacketPlayerDigging(PlayerDiggingActionType.STOP_DESTROY_BLOCK, blockPos2, enumFacing));
                  this.matchesBlockPosByBlockPos(blockPos2, enumFacing);
                  this.scaleFactor = 0.0F;
                  this.scaleFactor1 = 0.0F;
                  this.indexOffset2 = 5;
               } else {
                  this.scaleFactor = 0.0F;
               }
            }

            this.mcClient.theWorld.applyBlockPos(this.mcClient.thePlayer.getEntityId(), this.blockPos, (int)(this.scaleFactor * 10.0F) - 1);
            return true;
         }

         return this.onPlayerDamageBlock(blockPos2, enumFacing);
      }

      this.indexOffset2--;
      return true;
   }

   public boolean isActive2() {
      return this.worldSettingsGameType == WorldSettingsGameType.SPECTATOR;
   }

   public WorldSettingsGameType getWorldSettingsGameType() {
      return this.worldSettingsGameType;
   }

   private boolean matchesBlockPos(BlockPos blockPos2) {
      ItemStack stack = this.mcClient.thePlayer.getItemStack();
      boolean flag = this.itemStack == null && stack == null;
      if (this.itemStack != null && stack != null) {
         flag = stack.getItem() == this.itemStack.getItem()
            && ItemStack.matchesItemStack2(stack, this.itemStack)
            && (stack.isActive3() || stack.getIndexOffset() == this.itemStack.getIndexOffset());
      }

      return blockPos2.equals(this.blockPos) && flag;
   }

   public static void applyMinecraft(Minecraft mc, PlayerControllerMP playerControllerMP, BlockPos blockPos2, EnumFacing enumFacing) {
      if (!mc.theWorld.matchesEntityPlayerByEntityPlayer(mc.thePlayer, blockPos2, enumFacing)) {
         playerControllerMP.matchesBlockPosByBlockPos(blockPos2, enumFacing);
      }
   }

   public boolean onPlayerDamageBlock(BlockPos blockPos2, EnumFacing enumFacing) {
      if (this.worldSettingsGameType.isActive()) {
         if (this.worldSettingsGameType == WorldSettingsGameType.SPECTATOR) {
            return false;
         }

         if (!this.mcClient.thePlayer.onReset()) {
            Block block = this.mcClient.theWorld.getBlockState(blockPos2).getBlock();
            ItemStack stack = this.mcClient.thePlayer.getHeldItem();
            if (stack == null) {
               return false;
            }

            if (!stack.matchesBlockByBlock(block)) {
               return false;
            }
         }
      }

      if (!this.mcClient.theWorld.getInternalListEntry().checkBlockPos(blockPos2)) {
         return false;
      } else {
         if (this.worldSettingsGameType.canExecute()) {
            this.netHandlerPlayClient.handlePacket(new C07PacketPlayerDigging(PlayerDiggingActionType.START_DESTROY_BLOCK, blockPos2, enumFacing));
            applyMinecraft(this.mcClient, this, blockPos2, enumFacing);
            this.indexOffset2 = 5;
         } else if (!this.state || !this.matchesBlockPos(blockPos2)) {
            if (this.state) {
               this.netHandlerPlayClient.handlePacket(new C07PacketPlayerDigging(PlayerDiggingActionType.ABORT_DESTROY_BLOCK, this.blockPos, enumFacing));
            }

            this.netHandlerPlayClient.handlePacket(new C07PacketPlayerDigging(PlayerDiggingActionType.START_DESTROY_BLOCK, blockPos2, enumFacing));
            Block block2 = this.mcClient.theWorld.getBlockState(blockPos2).getBlock();
            boolean flag = block2.getMaterial() != Material.coreItemEntry;
            if (flag && this.scaleFactor == 0.0F) {
               block2.onBlockClicked(this.mcClient.theWorld, blockPos2, this.mcClient.thePlayer);
            }

            if (!flag || !(block2.getScaleFactor(this.mcClient.thePlayer, this.mcClient.thePlayer.worldObj, blockPos2) >= 1.0F)) {
               this.state = true;
               this.blockPos = blockPos2;
               this.itemStack = this.mcClient.thePlayer.getItemStack();
               this.scaleFactor = 0.0F;
               this.scaleFactor1 = 0.0F;
               this.mcClient.theWorld.applyBlockPos(this.mcClient.thePlayer.getEntityId(), this.blockPos, (int)(this.scaleFactor * 10.0F) - 1);
            } else if (!EventPreListener.getEventPreAction().isCancelled()) {
               this.matchesBlockPosByBlockPos(blockPos2, enumFacing);
            }
         }

         return true;
      }
   }

   public boolean isActive() {
      return this.worldSettingsGameType == WorldSettingsGameType.SPECTATOR;
   }

   public ItemStack windowClick(int count, int count2, int count3, int count4, EntityPlayer player) {
      short shortValue = player.container2.computeShortCode(player.inventoryPlayer);

      ItemStack stack;
      try {
         stack = player.container2.slotClick(count2, count3, count4, player);
      } catch (IndexOutOfBoundsException indexOutOfBoundsException) {
         EventBusManager.applyThrowable(new RuntimeException(player.container2 + " " + count + " " + count2 + " " + count3 + " " + count4, indexOutOfBoundsException));
         return null;
      }

      Client.getClient().getClass();
      this.netHandlerPlayClient.handlePacket(new C0EPacketClickWindow(count, count2, count3, count4, stack, shortValue));
      return stack;
   }

   public boolean processAction() {
      return this.worldSettingsGameType.canExecute2();
   }

   public boolean processAction2() {
      return this.worldSettingsGameType.canExecute();
   }

   public boolean matchesBlockPos(BlockPos blockPos2, EnumFacing enumFacing, ItemStack stack) {
      if (this.worldSettingsGameType.isActive()) {
         if (this.worldSettingsGameType == WorldSettingsGameType.SPECTATOR) {
            return false;
         }

         if (!this.mcClient.thePlayer.onReset()) {
            Block block = this.mcClient.theWorld.getBlockState(blockPos2).getBlock();
            if (stack == null) {
               return false;
            }

            if (!stack.matchesBlockByBlock(block)) {
               return false;
            }
         }
      }

      if (!this.mcClient.theWorld.getInternalListEntry().checkBlockPos(blockPos2)) {
         return false;
      } else {
         if (this.worldSettingsGameType.canExecute()) {
            this.netHandlerPlayClient.handlePacket(new C07PacketPlayerDigging(PlayerDiggingActionType.START_DESTROY_BLOCK, blockPos2, enumFacing));
            applyMinecraft(this.mcClient, this, blockPos2, enumFacing);
            this.indexOffset2 = 5;
         } else if (!this.state || !this.matchesBlockPos(blockPos2)) {
            if (this.state) {
               this.netHandlerPlayClient.handlePacket(new C07PacketPlayerDigging(PlayerDiggingActionType.ABORT_DESTROY_BLOCK, this.blockPos, enumFacing));
            }

            this.netHandlerPlayClient.handlePacket(new C07PacketPlayerDigging(PlayerDiggingActionType.START_DESTROY_BLOCK, blockPos2, enumFacing));
            Block block2 = this.mcClient.theWorld.getBlockState(blockPos2).getBlock();
            boolean flag = block2.getMaterial() != Material.coreItemEntry;
            if (flag && this.scaleFactor == 0.0F) {
               block2.onBlockClicked(this.mcClient.theWorld, blockPos2, this.mcClient.thePlayer);
            }

            if (flag && block2.getScaleFactor(this.mcClient.thePlayer, this.mcClient.thePlayer.worldObj, blockPos2) >= 1.0F) {
               this.matchesBlockPosByBlockPos(blockPos2, enumFacing);
            } else {
               this.state = true;
               this.blockPos = blockPos2;
               this.itemStack = this.mcClient.thePlayer.getItemStack();
               this.scaleFactor = 0.0F;
               this.scaleFactor1 = 0.0F;
               this.mcClient.theWorld.applyBlockPos(this.mcClient.thePlayer.getEntityId(), this.blockPos, (int)(this.scaleFactor * 10.0F) - 1);
            }
         }

         return true;
      }
   }

   public void applyItemStack(ItemStack stack, int count) {
      if (this.worldSettingsGameType.canExecute()) {
         this.netHandlerPlayClient.handlePacket(new C10PacketCreativeInventoryAction(count, stack));
      }
   }

   public boolean matchesBlockPosByBlockPos(BlockPos blockPos2, EnumFacing enumFacing) {
      if (this.worldSettingsGameType.isActive()) {
         if (this.worldSettingsGameType == WorldSettingsGameType.SPECTATOR) {
            return false;
         }

         if (!this.mcClient.thePlayer.onReset()) {
            Block block = this.mcClient.theWorld.getBlockState(blockPos2).getBlock();
            ItemStack stack = this.mcClient.thePlayer.getHeldItem();
            if (stack == null) {
               return false;
            }

            if (!stack.matchesBlockByBlock(block)) {
               return false;
            }
         }
      }

      if (this.worldSettingsGameType.canExecute()
         && this.mcClient.thePlayer.getItemStack() != null
         && this.mcClient.thePlayer.getItemStack().getItem() instanceof ItemSword) {
         return false;
      } else {
         WorldClient world = this.mcClient.theWorld;
         IBlockState blockState = world.getBlockState(blockPos2);
         Block block2 = blockState.getBlock();
         if (block2.getMaterial() == Material.coreItemEntry) {
            return false;
         } else {
            world.applyBlockPosByBlockPos(2001, blockPos2, Block.processAction5(blockState));
            boolean flag = world.matchesBlockPos8(blockPos2);
            if (flag) {
               block2.breakBlock(world, blockPos2, blockState);
            }

            this.blockPos = new BlockPos(this.blockPos.getX(), -1, this.blockPos.getZ());
            if (!this.worldSettingsGameType.canExecute()) {
               ItemStack stack2 = this.mcClient.thePlayer.getHeldItem();
               if (stack2 != null) {
                  stack2.applyWorldByWorld(world, block2, blockPos2, this.mcClient.thePlayer);
                  if (stack2.animationsToGo == 0) {
                     this.mcClient.thePlayer.updateState45();
                  }
               }
            }

            return flag;
         }
      }
   }

   public float getBlockReachDistance() {
      return this.worldSettingsGameType.canExecute() ? 5.0F : 4.5F;
   }

   public EntityPlayerSP processAction(World world, StatFileWriter statFileWriter) {
      return new EntityPlayerSP(this.mcClient, world, this.netHandlerPlayClient, statFileWriter);
   }

   public boolean matchesEntityPlayer(EntityPlayer player, Entity entity) {
      this.updateController();
      this.netHandlerPlayClient.handlePacket(new C02PacketUseEntity(entity, UseEntityAction.INTERACT));
      return this.worldSettingsGameType != WorldSettingsGameType.SPECTATOR && player.matchesEntity8(entity);
   }

   public boolean sendUseItem(EntityPlayer player, World world, ItemStack stack) {
      if (this.worldSettingsGameType == WorldSettingsGameType.SPECTATOR) {
         return false;
      } else {
         this.updateController();
         this.netHandlerPlayClient.handlePacket(new C08PacketPlayerBlockPlacement(stack));
         int animationsToGo = stack.animationsToGo;
         ItemStack stack2 = stack.processAction2(world, player);
         if (stack2 != null && (stack2 != stack || stack2 != null && stack2.animationsToGo != animationsToGo)) {
            player.inventoryPlayer.mainInventory[player.inventoryPlayer.currentItem] = stack2;
            if (stack2.animationsToGo == 0) {
               player.inventoryPlayer.mainInventory[player.inventoryPlayer.currentItem] = null;
            }

            return true;
         } else {
            return false;
         }
      }
   }

   public boolean isActiveAsFlag() {
      return this.mcClient.thePlayer.isSneaking() && this.mcClient.thePlayer.ridingEntity instanceof EntityHorse;
   }

   public boolean onPlayerRightClick(EntityPlayerSP player, WorldClient world, ItemStack stack, BlockPos blockPos2, EnumFacing enumFacing, Vec3 vec) {
      this.updateController();
      float factor = (float)(vec.xCoord - (double)blockPos2.getX());
      float factor2 = (float)(vec.yCoord - (double)blockPos2.getY());
      float factor3 = (float)(vec.zCoord - (double)blockPos2.getZ());
      boolean flag = false;
      if (!this.mcClient.theWorld.getInternalListEntry().checkBlockPos(blockPos2)) {
         return false;
      } else {
         if (this.worldSettingsGameType != WorldSettingsGameType.SPECTATOR) {
            IBlockState blockState = world.getBlockState(blockPos2);
            if ((!player.isSprinting() || player.getItemStack() == null) && blockState.getBlock().onBlockActivated(world, blockPos2, blockState, player, enumFacing, factor, factor2, factor3)) {
               flag = true;
            }

            if (!flag && stack != null && stack.getItem() instanceof ItemBlock) {
               ItemBlock item = (ItemBlock)stack.getItem();
               if ((!((Module)ClientGlobals.moduleManager.fly).isToggled() || !ClientGlobals.moduleManager.fly.settingMode.getSelected().equals("AirPlace"))
                  && !item.matchesWorld(world, blockPos2, enumFacing, player, stack)) {
                  return false;
               }
            }
         }

         this.netHandlerPlayClient.handlePacket(new C08PacketPlayerBlockPlacement(blockPos2, enumFacing.getIndexOffset1(), stack, factor, factor2, factor3));
         if (flag || this.worldSettingsGameType == WorldSettingsGameType.SPECTATOR) {
            EventConnectionCallback.resolveEventConnection(PhaseMode.POST);
            return true;
         } else if (stack == null) {
            return false;
         } else if (this.worldSettingsGameType.canExecute()) {
            int indexOffset3 = stack.getIndexOffset();
            int animationsToGo = stack.animationsToGo;
            flag = stack.matchesEntityPlayerByEntityPlayer(player, world, blockPos2, enumFacing, factor, factor2, factor3);
            stack.applyIndexByIndex(indexOffset3);
            stack.animationsToGo = animationsToGo;
            if (flag) {
               EventConnectionCallback.resolveEventConnection(PhaseMode.POST);
            }

            return flag;
         } else {
            boolean flag2 = stack.matchesEntityPlayerByEntityPlayer(player, world, blockPos2, enumFacing, factor, factor2, factor3);
            if (flag2) {
               EventConnectionCallback.resolveEventConnection(PhaseMode.POST);
            }

            return flag2;
         }
      }
   }

   public void processAction(int count, int count2) {
      this.netHandlerPlayClient.handlePacket(new C11PacketEnchantItem(count, count2));
   }

   public boolean processAction3() {
      return this.worldSettingsGameType.canExecute();
   }

   public void processAction(EntityPlayer player) {
      player.rotationYaw = -180.0F;
   }

   public float getScaleFactor() {
      return this.scaleFactor;
   }

   public boolean matchesBlockPos(BlockPos blockPos2, EnumFacing enumFacing, int count) {
      this.updateController();
      if (this.indexOffset2 > 0) {
         this.indexOffset2--;
         return true;
      } else if (this.worldSettingsGameType.canExecute() && this.mcClient.theWorld.getInternalListEntry().checkBlockPos(blockPos2)) {
         this.indexOffset2 = 5;
         this.netHandlerPlayClient.handlePacket(new C07PacketPlayerDigging(PlayerDiggingActionType.START_DESTROY_BLOCK, blockPos2, enumFacing));
         applyMinecraft(this.mcClient, this, blockPos2, enumFacing);
         return true;
      } else if (this.matchesBlockPos(blockPos2)) {
         Block block = this.mcClient.theWorld.getBlockState(blockPos2).getBlock();
         if (block.getMaterial() == Material.coreItemEntry) {
            this.state = false;
            return false;
         } else {
            ClientGlobals.moduleManager.fastBreak.applyBlock(block, blockPos2);
            if (ClientGlobals.moduleManager.fastBreak.checkBlock2(block)
               && this.scaleFactor >= 0.0F
               && (!(block instanceof BlockBed) || !ClientGlobals.moduleManager.fastBreak.settingIgnoreBeds.isEnabled())
               && (
                  !ClientGlobals.moduleManager.fastBreak.settingOnlyWhenBlink.isEnabled()
                     || ClientGlobals.moduleManager.packetHandler.canExecute5()
                     || ClientGlobals.moduleManager.packetHandler.isEnabled2()
               )) {
               if (ClientGlobals.moduleManager.fastBreak.checkBlock3(block)) {
                  this.scaleFactor = 2.0F;
               } else {
                  this.scaleFactor = this.scaleFactor
                     + block.getScaleFactor(this.mcClient.thePlayer, this.mcClient.thePlayer.worldObj, blockPos2)
                        * (float)ClientGlobals.moduleManager.fastBreak.getCoordinate(block);
               }
            } else {
               this.scaleFactor = this.scaleFactor + block.getScaleFactor(this.mcClient.thePlayer, this.mcClient.thePlayer.worldObj, blockPos2);
            }

            if (this.scaleFactor1 % 4.0F == 0.0F) {
               this.mcClient
                  .getMcSoundHandler()
                  .applyISound2(
                     new PositionedSoundRecord(
                        new ResourceLocation(block.stepManager20.getText()),
                        (block.stepManager20.getFactor2() + 1.0F) / 8.0F,
                        block.stepManager20.getFactor() * 0.5F,
                        (float)blockPos2.getX() + 0.5F,
                        (float)blockPos2.getY() + 0.5F,
                        (float)blockPos2.getZ() + 0.5F
                     )
                  );
            }

            this.scaleFactor1++;
            if (this.scaleFactor >= 1.0F) {
               if (!EventPreListener.getEventPreAction().isCancelled()) {
                  this.state = false;
                  this.netHandlerPlayClient.handlePacket(new C07PacketPlayerDigging(PlayerDiggingActionType.STOP_DESTROY_BLOCK, blockPos2, enumFacing));
                  this.matchesBlockPosByBlockPos(blockPos2, enumFacing);
                  this.scaleFactor = 0.0F;
                  this.scaleFactor1 = 0.0F;
                  this.indexOffset2 = 5;
               } else {
                  this.scaleFactor = 0.0F;
               }
            }

            this.mcClient.theWorld.applyBlockPos(this.mcClient.thePlayer.getEntityId(), this.blockPos, (int)(this.scaleFactor * 10.0F) - 1);
            return true;
         }
      } else {
         return this.onPlayerDamageBlock(blockPos2, enumFacing);
      }
   }

   public void applyItemStack(ItemStack stack) {
      if (this.worldSettingsGameType.canExecute() && stack != null) {
         this.netHandlerPlayClient.handlePacket(new C10PacketCreativeInventoryAction(-1, stack));
      }
   }

   public void setEnabled(boolean enabled) {
      if (enabled) {
         this.netHandlerPlayClient.handlePacket(new C07PacketPlayerDigging(PlayerDiggingActionType.ABORT_DESTROY_BLOCK, this.blockPos, EnumFacing.DOWN));
         this.scaleFactor = 0.0F;
         this.mcClient.theWorld.applyBlockPos(this.mcClient.thePlayer.getEntityId(), this.blockPos, -1);
      }
   }

   public void updateState2() {
      this.updateController();
      if (this.netHandlerPlayClient.getNetworkManager().isOpen()) {
         this.netHandlerPlayClient.getNetworkManager().updateState2();
      } else {
         this.netHandlerPlayClient.getNetworkManager().updateState3();
      }
   }

   public void applyWorldSettingsGameType(WorldSettingsGameType worldSettingsGameType2) {
      this.worldSettingsGameType = worldSettingsGameType2;
      this.worldSettingsGameType.updateInvulnerableManager(this.mcClient.thePlayer.invulnerableManager);
   }

   public boolean processAction4() {
      return this.worldSettingsGameType.canExecute2();
   }

   public boolean matchesEntityPlayer(EntityPlayer player, Entity entity, MovingObjectPosition hitResult) {
      this.updateController();
      Vec3 vec = new Vec3(hitResult.hitVec.xCoord - entity.posX, hitResult.hitVec.yCoord - entity.posY, hitResult.hitVec.zCoord - entity.posZ);
      this.netHandlerPlayClient.handlePacket(new C02PacketUseEntity(entity, vec));
      return this.worldSettingsGameType != WorldSettingsGameType.SPECTATOR && entity.matchesEntityPlayer(player, vec);
   }

   public void processAction2(EntityPlayer player) {
      this.updateController();
      this.netHandlerPlayClient.handlePacket(new C07PacketPlayerDigging(PlayerDiggingActionType.RELEASE_USE_ITEM, BlockPos.origin, EnumFacing.DOWN));
      player.updateState3();
   }

   public boolean isState() {
      return this.state;
   }

   public void updateState() {
      if (this.state) {
         this.netHandlerPlayClient.handlePacket(new C07PacketPlayerDigging(PlayerDiggingActionType.ABORT_DESTROY_BLOCK, this.blockPos, EnumFacing.DOWN));
         this.state = false;
         this.scaleFactor = 0.0F;
         this.mcClient.theWorld.applyBlockPos(this.mcClient.thePlayer.getEntityId(), this.blockPos, -1);
      }
   }

   public void processAction3(EntityPlayer player) {
      this.worldSettingsGameType.updateInvulnerableManager(player.invulnerableManager);
   }

   public void attackEntity(EntityPlayer player, Entity entity) {
      StateTypes stateTypes = StateTypes.PRE;
      AttackEntityListener.applyEntity(30872, 3167, entity, stateTypes, (char)40199);
      this.updateController();
      this.netHandlerPlayClient.handlePacket(new C02PacketUseEntity(entity, UseEntityAction.ATTACK));
      if (entity instanceof EntityLivingBase && ((EntityLivingBase)entity).hurtTime == 0 && System.currentTimeMillis() - entity.epochMillis > 225L) {
         entity.epochMillis = System.currentTimeMillis();
      }

      if (entity instanceof EntityLivingBase) {
         entity.timestamp = System.currentTimeMillis();
      }

      stateTypes = StateTypes.POST;
      AttackEntityListener.applyEntity(30872, 3167, entity, stateTypes, (char)40199);
      if (this.worldSettingsGameType != WorldSettingsGameType.SPECTATOR) {
         player.applyEntity18(entity);
         if (player instanceof EntityPlayerSP) {
            this.mcClient.thePlayer.updateState6();
         }
      }
   }

   public boolean canExecute() {
      return !this.worldSettingsGameType.canExecute();
   }

   public PlayerControllerMP(Minecraft mc, NetHandlerPlayClient netHandlerPlayClient2) {
      this.mcClient = mc;
      this.netHandlerPlayClient = netHandlerPlayClient2;
   }
}
