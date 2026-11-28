package net.minecraft.network;

import io.netty.util.concurrent.Future;
import java.util.concurrent.Callable;
import net.minecraft.entity.player.EntityPlayer;
import com.google.common.collect.Lists;
import com.google.common.primitives.Doubles;
import com.google.common.primitives.Floats;
import com.google.common.util.concurrent.Futures;
import io.netty.buffer.Unpooled;
import io.netty.util.concurrent.GenericFutureListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Set;
import net.minecraft.network.play.client.CPacketPlayerTryUseItemOnBlock;
import net.augustus.viaversion.packet.C0APacketSwingHand;
import net.minecraft.block.material.Material;
import net.minecraft.command.server.CommandBlockLogic;
import net.minecraft.crash.CrashReport;
import net.minecraft.crash.CrashReportCategory;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityMinecartCommandBlock;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Items;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ContainerBeacon;
import net.minecraft.inventory.ContainerMerchant;
import net.minecraft.inventory.ContainerRepair;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemEditableBook;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemWritableBook;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.network.play.INetHandlerPlayServer;
import net.minecraft.network.play.client.C00PacketKeepAlive;
import net.minecraft.network.play.client.C01PacketChatMessage;
import net.minecraft.network.play.client.C02PacketUseEntity;
import net.minecraft.network.play.client.C03PacketPlayer;
import net.minecraft.network.play.client.C07PacketPlayerDigging;
import net.minecraft.network.play.client.C08PacketPlayerBlockPlacement;
import net.minecraft.network.play.client.C09PacketHeldItemChange;
import net.minecraft.network.play.client.C0APacketAnimation;
import net.minecraft.network.play.client.C0BPacketEntityAction;
import net.minecraft.network.play.client.C0CPacketInput;
import net.minecraft.network.play.client.C0DPacketCloseWindow;
import net.minecraft.network.play.client.C0EPacketClickWindow;
import net.minecraft.network.play.client.C0FPacketConfirmTransaction;
import net.minecraft.network.play.client.C10PacketCreativeInventoryAction;
import net.minecraft.network.play.client.C11PacketEnchantItem;
import net.minecraft.network.play.client.C12PacketUpdateSign;
import net.minecraft.network.play.client.C13PacketPlayerAbilities;
import net.minecraft.network.play.client.C14PacketTabComplete;
import net.minecraft.network.play.client.C15PacketClientSettings;
import net.minecraft.network.play.client.C16PacketClientStatus;
import net.minecraft.network.play.client.C17PacketCustomPayload;
import net.minecraft.network.play.client.C18PacketSpectate;
import net.minecraft.network.play.client.C19PacketResourcePackStatus;
import net.minecraft.network.play.server.S00PacketKeepAlive;
import net.minecraft.network.play.server.S02PacketChat;
import net.minecraft.network.play.server.S07PacketRespawn;
import net.minecraft.network.play.server.S08PacketPlayerPosLook;
import net.minecraft.network.play.server.S18PacketEntityTeleport;
import net.minecraft.network.play.server.S23PacketBlockChange;
import net.minecraft.network.play.server.S2FPacketSetSlot;
import net.minecraft.network.play.server.S32PacketConfirmTransaction;
import net.minecraft.network.play.server.S3APacketTabComplete;
import net.minecraft.network.play.server.S40PacketDisconnect;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.management.UserListBansEntry;
import net.minecraft.stats.AchievementList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityCommandBlock;
import net.minecraft.tileentity.TileEntitySign;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatAllowedCharacters;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.ITickable;
import net.minecraft.util.IntHashMap;
import net.minecraft.util.ReportedException;
import net.minecraft.world.WorldServer;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class NetHandlerPlayServer implements INetHandlerPlayServer, ITickable {
   private static final Logger LOGGER = LogManager.getLogger();
   public final NetworkManager netManager;
   private final MinecraftServer serverController;
   public EntityPlayerMP playerEntity;
   private int networkTickCount;
   private int lastPositionUpdate;
   private int floatingTickCount;
   private boolean floating;
   private int keepAliveId;
   private long lastPingTime;
   private long lastSentPingPacket;
   private int chatSpamThresholdCount;
   private int itemDropThreshold;
   private IntHashMap pendingTransactions = new IntHashMap();
   private double lastPosX;
   private double lastPosY;
   private double lastPosZ;
   private boolean hasMoved = true;

   @Override
   public void processEnchantItem(C11PacketEnchantItem packet) {
      PacketThreadUtil.checkThreadAndEnqueue(packet, this, this.playerEntity.getServerForPlayer());
      this.playerEntity.markPlayerActive();
      if (this.playerEntity.openContainer.windowId == packet.getWindowId()
         && this.playerEntity.openContainer.getCanCraft(this.playerEntity)
         && !this.playerEntity.isSpectator()) {
         this.playerEntity.openContainer.enchantItem(this.playerEntity, packet.getButton());
         this.playerEntity.openContainer.detectAndSendChanges();
      }
   }

   @Override
   public void processClickWindow(C0EPacketClickWindow packet) {
      PacketThreadUtil.checkThreadAndEnqueue(packet, this, this.playerEntity.getServerForPlayer());
      this.playerEntity.markPlayerActive();
      if (this.playerEntity.openContainer.windowId == packet.getWindowId() && this.playerEntity.openContainer.getCanCraft(this.playerEntity)) {
         if (this.playerEntity.isSpectator()) {
            ArrayList list = Lists.newArrayList();

            for (int i = 0; i < this.playerEntity.openContainer.inventorySlots.size(); i++) {
               list.add(((Slot)this.playerEntity.openContainer.inventorySlots.get(i)).getStack());
            }

            this.playerEntity.updateCraftingInventory(this.playerEntity.openContainer, list);
         } else {
            ItemStack stack = this.playerEntity.openContainer.slotClick(packet.getSlotId(), packet.getUsedButton(), packet.getMode(), this.playerEntity);
            if (ItemStack.areItemStacksEqual(packet.getClickedItem(), stack)) {
               this.playerEntity.playerNetServerHandler.sendPacket(new S32PacketConfirmTransaction(packet.getWindowId(), packet.getActionNumber(), true));
               this.playerEntity.isChangingQuantityOnly = true;
               this.playerEntity.openContainer.detectAndSendChanges();
               this.playerEntity.updateHeldItem();
               this.playerEntity.isChangingQuantityOnly = false;
            } else {
               this.pendingTransactions.addKey(this.playerEntity.openContainer.windowId, packet.getActionNumber());
               this.playerEntity.playerNetServerHandler.sendPacket(new S32PacketConfirmTransaction(packet.getWindowId(), packet.getActionNumber(), false));
               this.playerEntity.openContainer.setCanCraft(this.playerEntity, false);
               ArrayList list2 = Lists.newArrayList();

               for (int j = 0; j < this.playerEntity.openContainer.inventorySlots.size(); j++) {
                  list2.add(((Slot)this.playerEntity.openContainer.inventorySlots.get(j)).getStack());
               }

               this.playerEntity.updateCraftingInventory(this.playerEntity.openContainer, list2);
            }
         }
      }
   }

   public NetworkManager getNetworkManager() {
      return this.netManager;
   }

   @Override
   public void processChatMessage(C01PacketChatMessage packet) {
      PacketThreadUtil.checkThreadAndEnqueue(packet, this, this.playerEntity.getServerForPlayer());
      if (this.playerEntity.getChatVisibility() == EntityPlayer.EnumChatVisibility.HIDDEN) {
         ChatComponentTranslation chatComponentTranslation = new ChatComponentTranslation("chat.cannotSend", new Object[0]);
         chatComponentTranslation.getChatStyle().setColor(EnumChatFormatting.RED);
         this.sendPacket(new S02PacketChat(chatComponentTranslation));
      } else {
         this.playerEntity.markPlayerActive();
         String message = packet.getMessage();
         message = StringUtils.normalizeSpace(message);

         for (int i = 0; i < message.length(); i++) {
            if (!ChatAllowedCharacters.isAllowedCharacter(message.charAt(i))) {
               this.kickPlayerFromServer("Illegal characters in chat");
               return;
            }
         }

         if (message.startsWith("/")) {
            this.handleSlashCommand(message);
         } else {
            ChatComponentTranslation chatComponentTranslation2 = new ChatComponentTranslation("chat.type.text", new Object[]{this.playerEntity.getDisplayName(), message});
            this.serverController.getConfigurationManager().sendChatMsgImpl(chatComponentTranslation2, false);
         }

         this.chatSpamThresholdCount += 20;
         if (this.chatSpamThresholdCount > 200 && !this.serverController.getConfigurationManager().canSendCommands(this.playerEntity.getGameProfile())) {
            this.kickPlayerFromServer("disconnect.spam");
         }
      }
   }

   private boolean isMovePlayerPacketInvalid(C03PacketPlayer packet) {
      return !Doubles.isFinite(packet.getPositionX())
         || !Doubles.isFinite(packet.getPositionY())
         || !Doubles.isFinite(packet.getPositionZ())
         || !Floats.isFinite(packet.getPitch())
         || !Floats.isFinite(packet.getYaw());
   }

   private void handleSlashCommand(String str) {
      this.serverController.getCommandManager().executeCommand(this.playerEntity, str);
   }

   @Override
   public void processCloseWindow(C0DPacketCloseWindow packet) {
      PacketThreadUtil.checkThreadAndEnqueue(packet, this, this.playerEntity.getServerForPlayer());
      this.playerEntity.closeContainer();
   }

   public void sendPacket(Packet packet) {
      if (packet instanceof S02PacketChat) {
         S02PacketChat packet2 = (S02PacketChat)packet;
         EntityPlayer.EnumChatVisibility chatVisibility = this.playerEntity.getChatVisibility();
         if (chatVisibility == EntityPlayer.EnumChatVisibility.HIDDEN) {
            return;
         }

         if (chatVisibility == EntityPlayer.EnumChatVisibility.SYSTEM && !packet2.isChat()) {
            return;
         }
      }

      try {
         this.netManager.sendPacket(packet);
      } catch (Throwable e) {
         CrashReport crashReport = CrashReport.makeCrashReport(e, "Sending packet");
         crashReport.makeCategory("Packet being sent").addCrashSectionCallable("Packet class", new NetHandlerPlayServer.ValueCallable(this, packet));
         throw new ReportedException(crashReport);
      }
   }

   @Override
   public void processHeldItemChange(C09PacketHeldItemChange packet) {
      PacketThreadUtil.checkThreadAndEnqueue(packet, this, this.playerEntity.getServerForPlayer());
      if (packet.getSlotId() >= 0 && packet.getSlotId() < InventoryPlayer.getHotbarSize()) {
         this.playerEntity.inventory.currentItem = packet.getSlotId();
         this.playerEntity.markPlayerActive();
      } else {
         LOGGER.warn(this.playerEntity.getName() + " tried to set an invalid carried item");
      }
   }

   public void kickPlayerFromServer(String str) {
      ChatComponentText chatComponentText = new ChatComponentText(str);
      this.netManager.sendPacket(new S40PacketDisconnect(chatComponentText), new NetHandlerPlayServer.DefaultGenericFutureListener(this, chatComponentText), new GenericFutureListener[0]);
      this.netManager.disableAutoRead();
      Futures.getUnchecked(this.serverController.addScheduledTask(this.netManager::checkDisconnected));
   }

   @Override
   public void processKeepAlive(C00PacketKeepAlive packet) {
      if (packet.getKey() == this.keepAliveId) {
         int n = (int)(this.currentTimeMillis() - this.lastPingTime);
         this.playerEntity.ping = (this.playerEntity.ping * 3 + n) / 4;
      }
   }

   @Override
   public void processUseEntity(C02PacketUseEntity packet) {
      PacketThreadUtil.checkThreadAndEnqueue(packet, this, this.playerEntity.getServerForPlayer());
      Entity entityFromWorld = packet.getEntityFromWorld(this.serverController.worldServerForDimension(this.playerEntity.dimension));
      this.playerEntity.markPlayerActive();
      if (entityFromWorld != null) {
         boolean flag = this.playerEntity.canEntityBeSeen(entityFromWorld);
         double d = 36.0;
         if (!flag) {
            d = 9.0;
         }

         if (this.playerEntity.getDistanceSqToEntity(entityFromWorld) < d) {
            if (packet.getAction() == C02PacketUseEntity.Action.INTERACT) {
               this.playerEntity.interactWith(entityFromWorld);
            } else if (packet.getAction() == C02PacketUseEntity.Action.INTERACT_AT) {
               entityFromWorld.interactAt(this.playerEntity, packet.getHitVec());
            } else if (packet.getAction() == C02PacketUseEntity.Action.ATTACK) {
               if (entityFromWorld instanceof EntityItem || entityFromWorld instanceof EntityXPOrb || entityFromWorld instanceof EntityArrow || entityFromWorld == this.playerEntity) {
                  this.kickPlayerFromServer("Attempting to attack an invalid entity");
                  this.serverController.logWarning("Player " + this.playerEntity.getName() + " tried to attack an invalid entity");
                  return;
               }

               this.playerEntity.attackTargetEntityWithCurrentItem(entityFromWorld);
            }
         }
      }
   }

   @Override
   public void processVanilla250Packet(C17PacketCustomPayload packet) {
      PacketThreadUtil.checkThreadAndEnqueue(packet, this, this.playerEntity.getServerForPlayer());
      if ("MC|BEdit".equals(packet.getChannelName())) {
         PacketBuffer buf = new PacketBuffer(Unpooled.wrappedBuffer(packet.getBufferData()));

         try {
            ItemStack stack = buf.readItemStackFromBuffer();
            if (stack == null) {
               return;
            }

            if (!ItemWritableBook.isNBTValid(stack.getTagCompound())) {
               throw new IOException("Invalid book tag!");
            }

            ItemStack currentItem = this.playerEntity.inventory.getCurrentItem();
            if (currentItem == null) {
               return;
            }

            if (stack.getItem() == Items.writable_book && stack.getItem() == currentItem.getItem()) {
               currentItem.setTagInfo("pages", stack.getTagCompound().getTagList("pages", 8));
            }
         } catch (Exception e) {
            LOGGER.error("Couldn't handle book info", e);
            return;
         } finally {
            buf.release();
         }
      } else if ("MC|BSign".equals(packet.getChannelName())) {
         PacketBuffer buf2 = new PacketBuffer(Unpooled.wrappedBuffer(packet.getBufferData()));

         try {
            ItemStack stack2 = buf2.readItemStackFromBuffer();
            if (stack2 == null) {
               return;
            }

            if (!ItemEditableBook.validBookTagContents(stack2.getTagCompound())) {
               throw new IOException("Invalid book tag!");
            }

            ItemStack currentItem2 = this.playerEntity.inventory.getCurrentItem();
            if (currentItem2 == null) {
               return;
            }

            if (stack2.getItem() == Items.written_book && currentItem2.getItem() == Items.writable_book) {
               currentItem2.setTagInfo("author", new NBTTagString(this.playerEntity.getName()));
               currentItem2.setTagInfo("title", new NBTTagString(stack2.getTagCompound().getString("title")));
               currentItem2.setTagInfo("pages", stack2.getTagCompound().getTagList("pages", 8));
               currentItem2.setItem(Items.written_book);
            }
         } catch (Exception e2) {
            LOGGER.error("Couldn't sign book", e2);
            return;
         } finally {
            buf2.release();
         }
      } else {
         if ("MC|TrSel".equals(packet.getChannelName())) {
            try {
               int n = packet.getBufferData().readInt();
               Container openContainer = this.playerEntity.openContainer;
               if (openContainer instanceof ContainerMerchant) {
                  ((ContainerMerchant)openContainer).setCurrentRecipeIndex(n);
               }
            } catch (Exception e3) {
               LOGGER.error("Couldn't select trade", e3);
            }
         } else if ("MC|AdvCdm".equals(packet.getChannelName())) {
            if (!this.serverController.isCommandBlockEnabled()) {
               this.playerEntity.addChatMessage(new ChatComponentTranslation("advMode.notEnabled", new Object[0]));
            } else if (this.playerEntity.canCommandSenderUseCommand(2, "") && this.playerEntity.capabilities.isCreativeMode) {
               PacketBuffer bufferData = packet.getBufferData();

               try {
                  byte b = bufferData.readByte();
                  CommandBlockLogic commandBlockLogic = null;
                  if (b == 0) {
                     TileEntity tileEntity = this.playerEntity.entityWorld.getTileEntity(new BlockPos(bufferData.readInt(), bufferData.readInt(), bufferData.readInt()));
                     if (tileEntity instanceof TileEntityCommandBlock) {
                        commandBlockLogic = ((TileEntityCommandBlock)tileEntity).getCommandBlockLogic();
                     }
                  } else if (b == 1) {
                     Entity entityByID = this.playerEntity.entityWorld.getEntityByID(bufferData.readInt());
                     if (entityByID instanceof EntityMinecartCommandBlock) {
                        commandBlockLogic = ((EntityMinecartCommandBlock)entityByID).getCommandBlockLogic();
                     }
                  }

                  String str = bufferData.readStringFromBuffer(bufferData.readableBytes());
                  boolean flag = bufferData.readBoolean();
                  if (commandBlockLogic != null) {
                     commandBlockLogic.setCommand(str);
                     commandBlockLogic.setTrackOutput(flag);
                     if (!flag) {
                        commandBlockLogic.setLastOutput((IChatComponent)null);
                     }

                     commandBlockLogic.updateCommand();
                     this.playerEntity.addChatMessage(new ChatComponentTranslation("advMode.setCommand.success", new Object[]{str}));
                  }
               } catch (Exception e4) {
                  LOGGER.error("Couldn't set command block", e4);
               } finally {
                  bufferData.release();
               }
            } else {
               this.playerEntity.addChatMessage(new ChatComponentTranslation("advMode.notAllowed", new Object[0]));
            }
         } else if ("MC|Beacon".equals(packet.getChannelName())) {
            if (this.playerEntity.openContainer instanceof ContainerBeacon) {
               try {
                  PacketBuffer bufferData2 = packet.getBufferData();
                  int n2 = bufferData2.readInt();
                  int n3 = bufferData2.readInt();
                  ContainerBeacon openContainer2 = (ContainerBeacon)this.playerEntity.openContainer;
                  Slot slot = openContainer2.getSlot(0);
                  if (slot.getHasStack()) {
                     slot.decrStackSize(1);
                     IInventory tileBeacon = openContainer2.getTileBeacon();
                     tileBeacon.setField(1, n2);
                     tileBeacon.setField(2, n3);
                     tileBeacon.markDirty();
                  }
               } catch (Exception e5) {
                  LOGGER.error("Couldn't set beacon", e5);
               }
            }
         } else if ("MC|ItemName".equals(packet.getChannelName()) && this.playerEntity.openContainer instanceof ContainerRepair) {
            ContainerRepair openContainer3 = (ContainerRepair)this.playerEntity.openContainer;
            if (packet.getBufferData() != null && packet.getBufferData().readableBytes() >= 1) {
               String str2 = ChatAllowedCharacters.filterAllowedCharacters(packet.getBufferData().readStringFromBuffer(32767));
               if (str2.length() <= 30) {
                  openContainer3.updateItemName(str2);
               }
            } else {
               openContainer3.updateItemName("");
            }
         }
      }
   }

   @Override
   public void processEntityAction(C0BPacketEntityAction packet) {
      PacketThreadUtil.checkThreadAndEnqueue(packet, this, this.playerEntity.getServerForPlayer());
      this.playerEntity.markPlayerActive();
      switch (packet.getAction()) {
         case START_SNEAKING:
            this.playerEntity.setSneaking(true);
            break;
         case STOP_SNEAKING:
            this.playerEntity.setSneaking(false);
            break;
         case START_SPRINTING:
            this.playerEntity.setSprinting(true);
            break;
         case STOP_SPRINTING:
            this.playerEntity.setSprinting(false);
            break;
         case STOP_SLEEPING:
            this.playerEntity.wakeUpPlayer(false, true, true);
            this.hasMoved = false;
            break;
         case RIDING_JUMP:
            if (this.playerEntity.ridingEntity instanceof EntityHorse) {
               ((EntityHorse)this.playerEntity.ridingEntity).setJumpPower(packet.getAuxData());
            }
            break;
         case OPEN_INVENTORY:
            if (this.playerEntity.ridingEntity instanceof EntityHorse) {
               ((EntityHorse)this.playerEntity.ridingEntity).openGUI(this.playerEntity);
            }
            break;
         default:
            throw new IllegalArgumentException("Invalid client command!");
      }
   }

   @Override
   public void processPlayer(C03PacketPlayer packet) {
      PacketThreadUtil.checkThreadAndEnqueue(packet, this, this.playerEntity.getServerForPlayer());
      if (this.isMovePlayerPacketInvalid(packet)) {
         this.kickPlayerFromServer("Invalid move packet received");
      } else {
         WorldServer world = this.serverController.worldServerForDimension(this.playerEntity.dimension);
         this.floating = true;
         if (!this.playerEntity.playerConqueredTheEnd) {
            double posX = this.playerEntity.posX;
            double posY = this.playerEntity.posY;
            double posZ = this.playerEntity.posZ;
            double d = 0.0;
            double d2 = packet.getPositionX() - this.lastPosX;
            double d3 = packet.getPositionY() - this.lastPosY;
            double d4 = packet.getPositionZ() - this.lastPosZ;
            if (packet.isMoving()) {
               d = d2 * d2 + d3 * d3 + d4 * d4;
               if (!this.hasMoved && d < 0.25) {
                  this.hasMoved = true;
               }
            }

            if (this.hasMoved) {
               this.lastPositionUpdate = this.networkTickCount;
               if (this.playerEntity.ridingEntity != null) {
                  float rotationYaw = this.playerEntity.rotationYaw;
                  float rotationPitch = this.playerEntity.rotationPitch;
                  this.playerEntity.ridingEntity.updateRiderPosition();
                  double posX2 = this.playerEntity.posX;
                  double posY2 = this.playerEntity.posY;
                  double posZ2 = this.playerEntity.posZ;
                  if (packet.getRotating()) {
                     rotationYaw = packet.getYaw();
                     rotationPitch = packet.getPitch();
                  }

                  this.playerEntity.onGround = packet.isOnGround();
                  this.playerEntity.onUpdateEntity();
                  this.playerEntity.setPositionAndRotation(posX2, posY2, posZ2, rotationYaw, rotationPitch);
                  if (this.playerEntity.ridingEntity != null) {
                     this.playerEntity.ridingEntity.updateRiderPosition();
                  }

                  this.serverController.getConfigurationManager().serverUpdateMountedMovingPlayer(this.playerEntity);
                  if (this.playerEntity.ridingEntity != null) {
                     if (d > 4.0) {
                        Entity ridingEntity = this.playerEntity.ridingEntity;
                        this.playerEntity.playerNetServerHandler.sendPacket(new S18PacketEntityTeleport(ridingEntity));
                        this.setPlayerLocation(
                           this.playerEntity.posX,
                           this.playerEntity.posY,
                           this.playerEntity.posZ,
                           this.playerEntity.rotationYaw,
                           this.playerEntity.rotationPitch
                        );
                     }

                     this.playerEntity.ridingEntity.isAirBorne = true;
                  }

                  if (this.hasMoved) {
                     this.lastPosX = this.playerEntity.posX;
                     this.lastPosY = this.playerEntity.posY;
                     this.lastPosZ = this.playerEntity.posZ;
                  }

                  world.updateEntity(this.playerEntity);
                  return;
               }

               if (this.playerEntity.isPlayerSleeping()) {
                  this.playerEntity.onUpdateEntity();
                  this.playerEntity
                     .setPositionAndRotation(this.lastPosX, this.lastPosY, this.lastPosZ, this.playerEntity.rotationYaw, this.playerEntity.rotationPitch);
                  world.updateEntity(this.playerEntity);
                  return;
               }

               double posY3 = this.playerEntity.posY;
               this.lastPosX = this.playerEntity.posX;
               this.lastPosY = this.playerEntity.posY;
               this.lastPosZ = this.playerEntity.posZ;
               double posX3 = this.playerEntity.posX;
               double posY4 = this.playerEntity.posY;
               double posZ3 = this.playerEntity.posZ;
               float rotationYaw2 = this.playerEntity.rotationYaw;
               float rotationPitch2 = this.playerEntity.rotationPitch;
               if (packet.isMoving() && packet.getPositionY() == -999.0) {
                  packet.setMoving(false);
               }

               if (packet.isMoving()) {
                  posX3 = packet.getPositionX();
                  posY4 = packet.getPositionY();
                  posZ3 = packet.getPositionZ();
                  if (Math.abs(packet.getPositionX()) > 3.0E7 || Math.abs(packet.getPositionZ()) > 3.0E7) {
                     this.kickPlayerFromServer("Illegal position");
                     return;
                  }
               }

               if (packet.getRotating()) {
                  rotationYaw2 = packet.getYaw();
                  rotationPitch2 = packet.getPitch();
               }

               this.playerEntity.onUpdateEntity();
               this.playerEntity.setPositionAndRotation(this.lastPosX, this.lastPosY, this.lastPosZ, rotationYaw2, rotationPitch2);
               if (!this.hasMoved) {
                  return;
               }

               double d5 = posX3 - this.playerEntity.posX;
               double d6 = posY4 - this.playerEntity.posY;
               double d7 = posZ3 - this.playerEntity.posZ;
               double d8 = this.playerEntity.motionX * this.playerEntity.motionX
                  + this.playerEntity.motionY * this.playerEntity.motionY
                  + this.playerEntity.motionZ * this.playerEntity.motionZ;
               double d9 = d5 * d5 + d6 * d6 + d7 * d7;
               if (d9 - d8 > 100.0
                  && (!this.serverController.isSinglePlayer() || !this.serverController.getServerOwner().equals(this.playerEntity.getName()))) {
                  LOGGER.warn(
                     this.playerEntity.getName()
                        + " moved too quickly! "
                        + d5
                        + ","
                        + d6
                        + ","
                        + d7
                        + " ("
                        + d5
                        + ", "
                        + d6
                        + ", "
                        + d7
                        + ")"
                  );
                  this.setPlayerLocation(this.lastPosX, this.lastPosY, this.lastPosZ, this.playerEntity.rotationYaw, this.playerEntity.rotationPitch);
                  return;
               }

               float f = 0.0625F;
               boolean isEmpty = world.getCollidingBoundingBoxes(
                     this.playerEntity, this.playerEntity.getEntityBoundingBox().contract((double)f, (double)f, (double)f)
                  )
                  .isEmpty();
               if (this.playerEntity.onGround && !packet.isOnGround() && d6 > 0.0) {
                  this.playerEntity.jump();
               }

               this.playerEntity.moveEntity(d5, d6, d7);
               this.playerEntity.onGround = packet.isOnGround();
               d5 = posX3 - this.playerEntity.posX;
               d6 = posY4 - this.playerEntity.posY;
               if (d6 > -0.5 || d6 < 0.5) {
                  d6 = 0.0;
               }

               d7 = posZ3 - this.playerEntity.posZ;
               d9 = d5 * d5 + d6 * d6 + d7 * d7;
               boolean flag = false;
               if (d9 > 0.0625 && !this.playerEntity.isPlayerSleeping() && !this.playerEntity.theItemInWorldManager.isCreative()) {
                  flag = true;
                  LOGGER.warn(this.playerEntity.getName() + " moved wrongly!");
               }

               this.playerEntity.setPositionAndRotation(posX3, posY4, posZ3, rotationYaw2, rotationPitch2);
               this.playerEntity.addMovementStat(this.playerEntity.posX - posX, this.playerEntity.posY - posY, this.playerEntity.posZ - posZ);
               if (!this.playerEntity.noClip) {
                  boolean isEmpty2 = world.getCollidingBoundingBoxes(
                        this.playerEntity, this.playerEntity.getEntityBoundingBox().contract((double)f, (double)f, (double)f)
                     )
                     .isEmpty();
                  if (isEmpty && (flag || !isEmpty2) && !this.playerEntity.isPlayerSleeping()) {
                     this.setPlayerLocation(this.lastPosX, this.lastPosY, this.lastPosZ, rotationYaw2, rotationPitch2);
                     return;
                  }
               }

               AxisAlignedBB box = this.playerEntity.getEntityBoundingBox().expand((double)f, (double)f, (double)f).addCoord(0.0, -0.55, 0.0);
               if (this.serverController.isFlightAllowed() || this.playerEntity.capabilities.allowFlying || world.checkBlockCollision(box)) {
                  this.floatingTickCount = 0;
               } else if (d6 >= -0.03125) {
                  this.floatingTickCount++;
                  if (this.floatingTickCount > 80) {
                     LOGGER.warn(this.playerEntity.getName() + " was kicked for floating too long!");
                     this.kickPlayerFromServer("Flying is not enabled on this server");
                     return;
                  }
               }

               this.playerEntity.onGround = packet.isOnGround();
               this.serverController.getConfigurationManager().serverUpdateMountedMovingPlayer(this.playerEntity);
               this.playerEntity.handleFalling(this.playerEntity.posY - posY3, packet.isOnGround());
            } else if (this.networkTickCount - this.lastPositionUpdate > 20) {
               this.setPlayerLocation(this.lastPosX, this.lastPosY, this.lastPosZ, this.playerEntity.rotationYaw, this.playerEntity.rotationPitch);
            }
         }
      }
   }

   public void setPlayerLocation(double d, double d2, double d3, float f, float f2, Set set) {
      this.hasMoved = false;
      this.lastPosX = d;
      this.lastPosY = d2;
      this.lastPosZ = d3;
      if (set.contains(S08PacketPlayerPosLook.EnumFlags.X)) {
         this.lastPosX = this.lastPosX + this.playerEntity.posX;
      }

      if (set.contains(S08PacketPlayerPosLook.EnumFlags.Y)) {
         this.lastPosY = this.lastPosY + this.playerEntity.posY;
      }

      if (set.contains(S08PacketPlayerPosLook.EnumFlags.Z)) {
         this.lastPosZ = this.lastPosZ + this.playerEntity.posZ;
      }

      float f3 = f;
      float f4 = f2;
      if (set.contains(S08PacketPlayerPosLook.EnumFlags.Y_ROT)) {
         f3 = f + this.playerEntity.rotationYaw;
      }

      if (set.contains(S08PacketPlayerPosLook.EnumFlags.X_ROT)) {
         f4 = f2 + this.playerEntity.rotationPitch;
      }

      this.playerEntity.setPositionAndRotation(this.lastPosX, this.lastPosY, this.lastPosZ, f3, f4);
      this.playerEntity.playerNetServerHandler.sendPacket(new S08PacketPlayerPosLook(d, d2, d3, f, f2, set));
   }

   @Override
   public void processPlayerDigging(C07PacketPlayerDigging packet) {
      PacketThreadUtil.checkThreadAndEnqueue(packet, this, this.playerEntity.getServerForPlayer());
      WorldServer world = this.serverController.worldServerForDimension(this.playerEntity.dimension);
      BlockPos position = packet.getPosition();
      this.playerEntity.markPlayerActive();
      switch (packet.getStatus()) {
         case DROP_ITEM:
            if (!this.playerEntity.isSpectator()) {
               this.playerEntity.dropOneItem(false);
            }

            return;
         case DROP_ALL_ITEMS:
            if (!this.playerEntity.isSpectator()) {
               this.playerEntity.dropOneItem(true);
            }

            return;
         case RELEASE_USE_ITEM:
            this.playerEntity.stopUsingItem();
            return;
         case START_DESTROY_BLOCK:
         case ABORT_DESTROY_BLOCK:
         case STOP_DESTROY_BLOCK:
            double d = this.playerEntity.posX - ((double)position.getX() + 0.5);
            double d2 = this.playerEntity.posY - ((double)position.getY() + 0.5) + 1.5;
            double d3 = this.playerEntity.posZ - ((double)position.getZ() + 0.5);
            if ((d * d + d2 * d2 + d3 * d3) > 36.0) {
               return;
            } else if (position.getY() >= this.serverController.getBuildLimit()) {
               return;
            } else {
               if (packet.getStatus() == C07PacketPlayerDigging.Action.START_DESTROY_BLOCK) {
                  if (!this.serverController.isBlockProtected(world, position, this.playerEntity) && world.getWorldBorder().contains(position)) {
                     this.playerEntity.theItemInWorldManager.onBlockClicked(position, packet.getFacing());
                  } else {
                     this.playerEntity.playerNetServerHandler.sendPacket(new S23PacketBlockChange(world, position));
                  }
               } else {
                  if (packet.getStatus() == C07PacketPlayerDigging.Action.STOP_DESTROY_BLOCK) {
                     this.playerEntity.theItemInWorldManager.blockRemoving(position);
                  } else if (packet.getStatus() == C07PacketPlayerDigging.Action.ABORT_DESTROY_BLOCK) {
                     this.playerEntity.theItemInWorldManager.cancelDestroyingBlock();
                  }

                  if (world.getBlockState(position).getBlock().getMaterial() != Material.air) {
                     this.playerEntity.playerNetServerHandler.sendPacket(new S23PacketBlockChange(world, position));
                  }
               }

               return;
            }
         default:
            throw new IllegalArgumentException("Invalid player action");
      }
   }

   @Override
   public void processInput(C0CPacketInput packet) {
      PacketThreadUtil.checkThreadAndEnqueue(packet, this, this.playerEntity.getServerForPlayer());
      this.playerEntity.setEntityActionState(packet.getStrafeSpeed(), packet.getForwardSpeed(), packet.isJumping(), packet.isSneaking());
   }

   @Override
   public void processPlayerBlockPlacement(C08PacketPlayerBlockPlacement packet) {
      PacketThreadUtil.checkThreadAndEnqueue(packet, this, this.playerEntity.getServerForPlayer());
      WorldServer world = this.serverController.worldServerForDimension(this.playerEntity.dimension);
      ItemStack currentItem = this.playerEntity.inventory.getCurrentItem();
      boolean flag = false;
      BlockPos position = packet.getPosition();
      EnumFacing front = EnumFacing.getFront(packet.getPlacedBlockDirection());
      this.playerEntity.markPlayerActive();
      if (packet.getPlacedBlockDirection() == 255) {
         if (currentItem == null) {
            return;
         }

         this.playerEntity.theItemInWorldManager.tryUseItem(this.playerEntity, world, currentItem);
      } else if (position.getY() < this.serverController.getBuildLimit() - 1 || front != EnumFacing.UP && position.getY() < this.serverController.getBuildLimit()) {
         if (this.hasMoved
            && this.playerEntity.getDistanceSq((double)position.getX() + 0.5, (double)position.getY() + 0.5, (double)position.getZ() + 0.5) < 64.0
            && !this.serverController.isBlockProtected(world, position, this.playerEntity)
            && world.getWorldBorder().contains(position)) {
            this.playerEntity
               .theItemInWorldManager
               .activateBlockOrUseItem(
                  this.playerEntity, world, currentItem, position, front, packet.getPlacedBlockOffsetX(), packet.getPlacedBlockOffsetY(), packet.getPlacedBlockOffsetZ()
               );
         }

         flag = true;
      } else {
         ChatComponentTranslation chatComponentTranslation = new ChatComponentTranslation("build.tooHigh", new Object[]{this.serverController.getBuildLimit()});
         chatComponentTranslation.getChatStyle().setColor(EnumChatFormatting.RED);
         this.playerEntity.playerNetServerHandler.sendPacket(new S02PacketChat(chatComponentTranslation));
         flag = true;
      }

      if (flag) {
         this.playerEntity.playerNetServerHandler.sendPacket(new S23PacketBlockChange(world, position));
         this.playerEntity.playerNetServerHandler.sendPacket(new S23PacketBlockChange(world, position.offset(front)));
      } else {
         currentItem = this.playerEntity.inventory.getCurrentItem();
         if (currentItem != null && currentItem.stackSize == 0) {
            this.playerEntity.inventory.mainInventory[this.playerEntity.inventory.currentItem] = null;
            currentItem = null;
         }

         if (currentItem == null || currentItem.getMaxItemUseDuration() == 0) {
            this.playerEntity.isChangingQuantityOnly = true;
            this.playerEntity.inventory.mainInventory[this.playerEntity.inventory.currentItem] = ItemStack.copyItemStack(
               this.playerEntity.inventory.mainInventory[this.playerEntity.inventory.currentItem]
            );
            Slot slotFromInventory = this.playerEntity.openContainer.getSlotFromInventory(this.playerEntity.inventory, this.playerEntity.inventory.currentItem);
            this.playerEntity.openContainer.detectAndSendChanges();
            this.playerEntity.isChangingQuantityOnly = false;
            if (!ItemStack.areItemStacksEqual(this.playerEntity.inventory.getCurrentItem(), packet.getStack())) {
               this.sendPacket(new S2FPacketSetSlot(this.playerEntity.openContainer.windowId, slotFromInventory.slotNumber, this.playerEntity.inventory.getCurrentItem()));
            }
         }
      }
   }

   @Override
   public void processClientSettings(C15PacketClientSettings packet) {
      PacketThreadUtil.checkThreadAndEnqueue(packet, this, this.playerEntity.getServerForPlayer());
      this.playerEntity.handleClientSettings(packet);
   }

   public NetHandlerPlayServer(MinecraftServer minecraftServer, NetworkManager networkManager, EntityPlayerMP entityPlayerMP) {
      this.serverController = minecraftServer;
      this.netManager = networkManager;
      networkManager.setNetHandler(this);
      this.playerEntity = entityPlayerMP;
      entityPlayerMP.playerNetServerHandler = this;
   }

   @Override
   public void handleResourcePackStatus(C19PacketResourcePackStatus packet) {
   }

   @Override
   public void handleAnimation(C0APacketAnimation packet) {
      PacketThreadUtil.checkThreadAndEnqueue(packet, this, this.playerEntity.getServerForPlayer());
      this.playerEntity.markPlayerActive();
      this.playerEntity.swingItem();
   }

   private long currentTimeMillis() {
      return System.nanoTime() / 1000000L;
   }

   @Override
   public void onDisconnect(IChatComponent component) {
      LOGGER.info(this.playerEntity.getName() + " lost connection: " + component);
      this.serverController.refreshStatusNextTick();
      ChatComponentTranslation chatComponentTranslation = new ChatComponentTranslation("multiplayer.player.left", new Object[]{this.playerEntity.getDisplayName()});
      chatComponentTranslation.getChatStyle().setColor(EnumChatFormatting.YELLOW);
      this.serverController.getConfigurationManager().sendChatMsg(chatComponentTranslation);
      this.playerEntity.mountEntityAndWakeUp();
      this.serverController.getConfigurationManager().playerLoggedOut(this.playerEntity);
      if (this.serverController.isSinglePlayer() && this.playerEntity.getName().equals(this.serverController.getServerOwner())) {
         LOGGER.info("Stopping singleplayer server as player logged out");
         this.serverController.initiateShutdown();
      }
   }

   @Override
   public void processCreativeInventoryAction(C10PacketCreativeInventoryAction packet) {
      PacketThreadUtil.checkThreadAndEnqueue(packet, this, this.playerEntity.getServerForPlayer());
      if (this.playerEntity.theItemInWorldManager.isCreative()) {
         boolean flag = packet.getSlotId() < 0;
         ItemStack stack = packet.getStack();
         if (stack != null && stack.hasTagCompound() && stack.getTagCompound().hasKey("BlockEntityTag", 10)) {
            NBTTagCompound compoundTag = stack.getTagCompound().getCompoundTag("BlockEntityTag");
            if (compoundTag.hasKey("x") && compoundTag.hasKey("y") && compoundTag.hasKey("z")) {
               BlockPos pos = new BlockPos(compoundTag.getInteger("x"), compoundTag.getInteger("y"), compoundTag.getInteger("z"));
               TileEntity tileEntity = this.playerEntity.entityWorld.getTileEntity(pos);
               if (tileEntity != null) {
                  NBTTagCompound tag = new NBTTagCompound();
                  tileEntity.writeToNBT(tag);
                  tag.removeTag("x");
                  tag.removeTag("y");
                  tag.removeTag("z");
                  stack.setTagInfo("BlockEntityTag", tag);
               }
            }
         }

         boolean flag2 = packet.getSlotId() >= 1 && packet.getSlotId() < 36 + InventoryPlayer.getHotbarSize();
         boolean flag3 = stack == null || stack.getItem() != null;
         boolean flag4 = stack == null || stack.getMetadata() >= 0 && stack.stackSize <= 64 && stack.stackSize > 0;
         if (flag2 && flag3 && flag4) {
            if (stack == null) {
               this.playerEntity.inventoryContainer.putStackInSlot(packet.getSlotId(), (ItemStack)null);
            } else {
               this.playerEntity.inventoryContainer.putStackInSlot(packet.getSlotId(), stack);
            }

            this.playerEntity.inventoryContainer.setCanCraft(this.playerEntity, true);
         } else if (flag && flag3 && flag4 && this.itemDropThreshold < 200) {
            this.itemDropThreshold += 20;
            EntityItem entityItem = this.playerEntity.dropPlayerItemWithRandomChoice(stack, true);
            if (entityItem != null) {
               entityItem.setAgeToCreativeDespawnTime();
            }
         }
      }
   }

   public void setPlayerLocation(double d, double d2, double d3, float f, float f2) {
      this.setPlayerLocation(d, d2, d3, f, f2, Collections.emptySet());
   }

   @Override
   public void processTabComplete(C14PacketTabComplete packet) {
      PacketThreadUtil.checkThreadAndEnqueue(packet, this, this.playerEntity.getServerForPlayer());
      ArrayList list = Lists.newArrayList();

      for (String str : (Iterable<String>)(this.serverController.getTabCompletions(this.playerEntity, packet.getMessage(), packet.getTargetBlock()))) {
         list.add(str);
      }

      this.playerEntity.playerNetServerHandler.sendPacket(new S3APacketTabComplete((String[])list.toArray(new String[list.size()])));
   }

   @Override
   public void handleSpectate(C18PacketSpectate packet) {
      PacketThreadUtil.checkThreadAndEnqueue(packet, this, this.playerEntity.getServerForPlayer());
      if (this.playerEntity.isSpectator()) {
         Entity entity = null;

         for (WorldServer world : this.serverController.worldServers) {
            if (world != null) {
               entity = packet.getEntity(world);
               if (entity != null) {
                  break;
               }
            }
         }

         if (entity != null) {
            this.playerEntity.setSpectatingEntity(this.playerEntity);
            this.playerEntity.mountEntity((Entity)null);
            if (entity.entityWorld != this.playerEntity.entityWorld) {
               WorldServer serverForPlayer = this.playerEntity.getServerForPlayer();
               WorldServer entityWorld = (WorldServer)entity.entityWorld;
               this.playerEntity.dimension = entity.dimension;
               this.sendPacket(
                  new S07PacketRespawn(
                     this.playerEntity.dimension,
                     serverForPlayer.getDifficulty(),
                     serverForPlayer.getWorldInfo().getTerrainType(),
                     this.playerEntity.theItemInWorldManager.getGameType()
                  )
               );
               serverForPlayer.removePlayerEntityDangerously(this.playerEntity);
               this.playerEntity.isDead = false;
               this.playerEntity.setLocationAndAngles(entity.posX, entity.posY, entity.posZ, entity.rotationYaw, entity.rotationPitch);
               if (this.playerEntity.isEntityAlive()) {
                  serverForPlayer.updateEntityWithOptionalForce(this.playerEntity, false);
                  entityWorld.spawnEntityInWorld(this.playerEntity);
                  entityWorld.updateEntityWithOptionalForce(this.playerEntity, false);
               }

               this.playerEntity.setWorld(entityWorld);
               this.serverController.getConfigurationManager().preparePlayer(this.playerEntity, serverForPlayer);
               this.playerEntity.setPositionAndUpdate(entity.posX, entity.posY, entity.posZ);
               this.playerEntity.theItemInWorldManager.setWorld(entityWorld);
               this.serverController.getConfigurationManager().updateTimeAndWeatherForPlayer(this.playerEntity, entityWorld);
               this.serverController.getConfigurationManager().syncPlayerInventory(this.playerEntity);
            } else {
               this.playerEntity.setPositionAndUpdate(entity.posX, entity.posY, entity.posZ);
            }
         }
      }
   }

   @Override
   public void processSwingHand(C0APacketSwingHand packet) {
      PacketThreadUtil.checkThreadAndEnqueue(packet, this, this.playerEntity.getServerForPlayer());
      this.playerEntity.markPlayerActive();
      this.playerEntity.swingItem();
   }

   @Override
   public void processPlayerAbilities(C13PacketPlayerAbilities packet) {
      PacketThreadUtil.checkThreadAndEnqueue(packet, this, this.playerEntity.getServerForPlayer());
      this.playerEntity.capabilities.isFlying = packet.isFlying() && this.playerEntity.capabilities.allowFlying;
   }

   @Override
   public void processClientStatus(C16PacketClientStatus packet) {
      PacketThreadUtil.checkThreadAndEnqueue(packet, this, this.playerEntity.getServerForPlayer());
      this.playerEntity.markPlayerActive();
      C16PacketClientStatus.EnumState status = packet.getStatus();
      switch (status) {
         case PERFORM_RESPAWN:
            if (this.playerEntity.playerConqueredTheEnd) {
               this.playerEntity = this.serverController.getConfigurationManager().recreatePlayerEntity(this.playerEntity, 0, true);
            } else if (this.playerEntity.getServerForPlayer().getWorldInfo().isHardcoreModeEnabled()) {
               if (this.serverController.isSinglePlayer() && this.playerEntity.getName().equals(this.serverController.getServerOwner())) {
                  this.playerEntity.playerNetServerHandler.kickPlayerFromServer("You have died. Game over, man, it's game over!");
                  this.serverController.deleteWorldAndStopServer();
               } else {
                  UserListBansEntry userListBansEntry = new UserListBansEntry(
                     this.playerEntity.getGameProfile(), (Date)null, "(You just lost the game)", (Date)null, "Death in Hardcore"
                  );
                  this.serverController.getConfigurationManager().getBannedPlayers().addEntry(userListBansEntry);
                  this.playerEntity.playerNetServerHandler.kickPlayerFromServer("You have died. Game over, man, it's game over!");
               }
            } else {
               if (this.playerEntity.getCurrentHealth() > 0.0F) {
                  return;
               }

               this.playerEntity = this.serverController.getConfigurationManager().recreatePlayerEntity(this.playerEntity, 0, false);
            }
            break;
         case REQUEST_STATS:
            this.playerEntity.getStatFile().sendStats(this.playerEntity);
            break;
         case OPEN_INVENTORY_ACHIEVEMENT:
            this.playerEntity.triggerAchievement(AchievementList.openInventory);
      }
   }

   @Override
   public void processTryUseItemOnBlock(CPacketPlayerTryUseItemOnBlock block) {
      PacketThreadUtil.checkThreadAndEnqueue(block, this, this.playerEntity.getServerForPlayer());
      WorldServer world = this.serverController.worldServerForDimension(this.playerEntity.dimension);
      ItemStack heldItem = this.playerEntity.getHeldItem();
      BlockPos position = block.getPosition();
      EnumFacing placedBlockDirection = block.getPlacedBlockDirection();
      this.playerEntity.markPlayerActive();
      if (position.getY() < this.serverController.getBuildLimit() - 1 || placedBlockDirection != EnumFacing.UP && position.getY() < this.serverController.getBuildLimit()) {
         if (this.playerEntity.getDistanceSq((double)position.getX() + 0.5, (double)position.getY() + 0.5, (double)position.getZ() + 0.5) < 64.0
            && !this.serverController.isBlockProtected(world, position, this.playerEntity)
            && world.getWorldBorder().contains(position)) {
            this.playerEntity.theItemInWorldManager.activateBlockOrUseItem(this.playerEntity, world, heldItem, position, placedBlockDirection, block.getFacingX(), block.getFacingY(), block.getFacingZ());
         }
      } else {
         ChatComponentTranslation chatComponentTranslation = new ChatComponentTranslation("build.tooHigh", new Object[]{this.serverController.getBuildLimit()});
         chatComponentTranslation.getChatStyle().setColor(EnumChatFormatting.RED);
         this.playerEntity.playerNetServerHandler.sendPacket(new S02PacketChat(chatComponentTranslation));
      }

      this.playerEntity.playerNetServerHandler.sendPacket(new S23PacketBlockChange(world, position));
      this.playerEntity.playerNetServerHandler.sendPacket(new S23PacketBlockChange(world, position.offset(placedBlockDirection)));
   }

   @Override
   public void update() {
      this.floating = false;
      this.networkTickCount++;
      this.serverController.theProfiler.startSection("keepAlive");
      if ((long)this.networkTickCount - this.lastSentPingPacket > 40L) {
         this.lastSentPingPacket = (long)this.networkTickCount;
         this.lastPingTime = this.currentTimeMillis();
         this.keepAliveId = (int)this.lastPingTime;
         this.sendPacket(new S00PacketKeepAlive(this.keepAliveId));
      }

      this.serverController.theProfiler.endSection();
      if (this.chatSpamThresholdCount > 0) {
         this.chatSpamThresholdCount--;
      }

      if (this.itemDropThreshold > 0) {
         this.itemDropThreshold--;
      }

      if (this.playerEntity.getLastActiveTime() > 0L
         && this.serverController.getMaxPlayerIdleMinutes() > 0
         && MinecraftServer.getCurrentTimeMillis() - this.playerEntity.getLastActiveTime()
            > (long)(this.serverController.getMaxPlayerIdleMinutes() * 1000 * 60)) {
         this.kickPlayerFromServer("You have been idle for too long!");
      }
   }

   @Override
   public void processConfirmTransaction(C0FPacketConfirmTransaction packet) {
      PacketThreadUtil.checkThreadAndEnqueue(packet, this, this.playerEntity.getServerForPlayer());
      Short s = (Short)this.pendingTransactions.lookup(this.playerEntity.openContainer.windowId);
      if (s != null
         && packet.getUid() == s
         && this.playerEntity.openContainer.windowId == packet.getWindowId()
         && !this.playerEntity.openContainer.getCanCraft(this.playerEntity)
         && !this.playerEntity.isSpectator()) {
         this.playerEntity.openContainer.setCanCraft(this.playerEntity, true);
      }
   }

   @Override
   public void processUpdateSign(C12PacketUpdateSign packet) {
      PacketThreadUtil.checkThreadAndEnqueue(packet, this, this.playerEntity.getServerForPlayer());
      this.playerEntity.markPlayerActive();
      WorldServer world = this.serverController.worldServerForDimension(this.playerEntity.dimension);
      BlockPos position = packet.getPosition();
      if (world.isBlockLoaded(position)) {
         TileEntity tileEntity = world.getTileEntity(position);
         if (!(tileEntity instanceof TileEntitySign)) {
            return;
         }

         TileEntitySign tileEntitySign = (TileEntitySign)tileEntity;
         if (!tileEntitySign.getIsEditable() || tileEntitySign.getPlayer() != this.playerEntity) {
            this.serverController.logWarning("Player " + this.playerEntity.getName() + " just tried to change non-editable sign");
            return;
         }

         IChatComponent[] lines = packet.getLines();

         for (int i = 0; i < lines.length; i++) {
            tileEntitySign.signText[i] = new ChatComponentText(EnumChatFormatting.getTextWithoutFormattingCodes(lines[i].getUnformattedText()));
         }

         tileEntitySign.markDirty();
         world.markBlockForUpdate(position);
      }
   }

public static class DefaultGenericFutureListener implements GenericFutureListener {
      public final ChatComponentText disconnectReason;
      public final NetHandlerPlayServer netHandler;

      public DefaultGenericFutureListener(NetHandlerPlayServer netHandlerPlayServer, ChatComponentText chatComponentText) {
         this.netHandler = netHandlerPlayServer;
         this.disconnectReason = chatComponentText;
      }

      public void operationComplete(Future future) throws Exception {
         this.netHandler.netManager.closeChannel(this.disconnectReason);
      }
   }

   public static class ValueCallable implements Callable {
      public final Packet packet;
      public final NetHandlerPlayServer netHandler;

      @Override
      public Object call() throws Exception {
         return this.callAsString();
      }

      public String callAsString() throws Exception {
         return this.packet.getClass().getCanonicalName();
      }

      public ValueCallable(NetHandlerPlayServer netHandlerPlayServer, Packet packet2) {
         this.netHandler = netHandlerPlayServer;
         this.packet = packet2;
      }
   }

}
