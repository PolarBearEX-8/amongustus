package client.util;

import client.event.PreUpdateConsumer;
import client.setting.AutoSoupPerformRespawnType;
import client.setting.ClientSettingsBlackType;
import client.setting.GameSettingsFullType;
import client.setting.NetHandlerPlayClientXType;
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
import net.minecraft.block.material.Material;
import net.minecraft.crash.CrashReport;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityArrow;
import net.minecraft.entity.EntityCaveSpiderAdapter;
import net.minecraft.entity.EntityItem;
import net.minecraft.entity.EntityXporb;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.player.EntityPlayerAdapter;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryPlayer;
import net.minecraft.inventory.RepairContainer;
import net.minecraft.inventory.Slot;
import net.minecraft.item.AltItemArrayEntry;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemWritableBook;
import net.minecraft.item.ItemWrittenBook;
import net.minecraft.item.Items;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
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
import net.minecraft.network.play.client.CAnimateHandPacket;
import net.minecraft.network.play.client.CPacketPlayerTryUseItemOnBlock;
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
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityControl;
import net.minecraft.tileentity.TileEntitySign;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.IChatComponent;
import net.minecraft.world.WorldServer;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AuxiliaryListenerAdapter4 implements PreUpdateConsumer, INetHandlerPlayServer {
   public static final Logger logger = LogManager.getLogger();
   public final NetworkManager networkManager;
   public final VanillaRunnable vanillaRunnable;
   public EntityPlayerAdapter entityPlayerAdapter;
   public int count;
   public int count2;
   public int count3;
   public boolean enabled;
   public int count4;
   public long timestamp;
   public long timestamp2;
   public int count5;
   public int count6;
   public AltItemArrayEntry altItemArrayEntry = new AltItemArrayEntry();
   public double value;
   public double value2;
   public double value3;
   public boolean enabled2 = true;

   @Override
   public void applyC0EPacketClickWindow(C0EPacketClickWindow packet) {
      ClientManager.handlePacket2(packet, this, this.entityPlayerAdapter.getWorldServer());
      this.entityPlayerAdapter.updateState48();
      if (this.entityPlayerAdapter.container2.indexOffset2 == packet.getCount() && this.entityPlayerAdapter.container2.contains(this.entityPlayerAdapter)) {
         if (this.entityPlayerAdapter.isActive23()) {
            ArrayList list = Lists.newArrayList();

            for (int index = 0; index < this.entityPlayerAdapter.container2.itemList2.size(); index++) {
               list.add(((Slot)this.entityPlayerAdapter.container2.itemList2.get(index)).getStack());
            }

            this.entityPlayerAdapter.applyContainer(this.entityPlayerAdapter.container2, list);
         } else {
            ItemStack stack = this.entityPlayerAdapter.container2.slotClick(packet.getCount3(), packet.getCount2(), packet.getCount4(), this.entityPlayerAdapter);
            if (ItemStack.matchesItemStack(packet.getItemStack(), stack)) {
               this.entityPlayerAdapter.auxiliaryListenerAdapter4.applyPacket(new S32PacketConfirmTransaction(packet.getCount(), packet.getShortCode(), true));
               this.entityPlayerAdapter.enabled29 = true;
               this.entityPlayerAdapter.container2.detectAndSendChanges();
               this.entityPlayerAdapter.updateState47();
               this.entityPlayerAdapter.enabled29 = false;
            } else {
               this.altItemArrayEntry.applyTarget(this.entityPlayerAdapter.container2.indexOffset2, packet.getShortCode());
               this.entityPlayerAdapter.auxiliaryListenerAdapter4.applyPacket(new S32PacketConfirmTransaction(packet.getCount(), packet.getShortCode(), false));
               this.entityPlayerAdapter.container2.applyEntityPlayer(this.entityPlayerAdapter, false);
               ArrayList list2 = Lists.newArrayList();

               for (int index2 = 0; index2 < this.entityPlayerAdapter.container2.itemList2.size(); index2++) {
                  list2.add(((Slot)this.entityPlayerAdapter.container2.itemList2.get(index2)).getStack());
               }

               this.entityPlayerAdapter.applyContainer(this.entityPlayerAdapter.container2, list2);
            }
         }
      }
   }

   @Override
   public void applyC10PacketCreativeInventory(C10PacketCreativeInventoryAction packet) {
      ClientManager.handlePacket2(packet, this, this.entityPlayerAdapter.getWorldServer());
      if (this.entityPlayerAdapter.internalWorldEntry.isActiveAsFlag()) {
         boolean flag = packet.getCount() < 0;
         ItemStack stack = packet.getItemStack();
         if (stack != null && stack.canExecute() && stack.getStackTagCompound().matchesText("BlockEntityTag", 10)) {
            NBTTagCompound nBTTagCompound = stack.getStackTagCompound().getNBTTagCompound("BlockEntityTag");
            if (nBTTagCompound.containsKey("x") && nBTTagCompound.containsKey("y") && nBTTagCompound.containsKey("z")) {
               BlockPos blockPos = new BlockPos(nBTTagCompound.computeCount("x"), nBTTagCompound.computeCount("y"), nBTTagCompound.computeCount("z"));
               TileEntity tileEntity = this.entityPlayerAdapter.worldObj.resolveTileEntity(blockPos);
               if (tileEntity != null) {
                  NBTTagCompound tagCompound = new NBTTagCompound();
                  tileEntity.applyNBTTagCompound(tagCompound);
                  tagCompound.remove("x");
                  tagCompound.remove("y");
                  tagCompound.remove("z");
                  stack.applyText("BlockEntityTag", tagCompound);
               }
            }
         }

         boolean flag2 = packet.getCount() >= 1 && packet.getCount() < 36 + InventoryPlayer.processAction6();
         boolean flag3 = stack == null || stack.getItem() != null;
         boolean flag4 = stack == null || stack.getIndexOffset() >= 0 && stack.animationsToGo <= 64 && stack.animationsToGo > 0;
         if (flag2 && flag3 && flag4) {
            if (stack == null) {
               this.entityPlayerAdapter.container.putStackInSlot(packet.getCount(), (ItemStack)null);
            } else {
               this.entityPlayerAdapter.container.putStackInSlot(packet.getCount(), stack);
            }

            this.entityPlayerAdapter.container.applyEntityPlayer(this.entityPlayerAdapter, true);
         } else if (flag && flag3 && flag4 && this.count6 < 200) {
            this.count6 += 20;
            EntityItem entityItem = this.entityPlayerAdapter.resolveEntityItem(stack, true);
            if (entityItem != null) {
               entityItem.updateState3();
            }
         }
      }
   }

   public void applyPacket(Packet packet) {
      if (packet instanceof S02PacketChat) {
         S02PacketChat packet2 = (S02PacketChat)packet;
         GameSettingsFullType gameSettingsFullType = this.entityPlayerAdapter.getGameSettingsFullType();
         if (gameSettingsFullType == GameSettingsFullType.HIDDEN) {
            return;
         }

         if (gameSettingsFullType == GameSettingsFullType.SYSTEM && !packet2.isActive()) {
            return;
         }
      }

      try {
         this.networkManager.handlePacket6(packet);
      } catch (Throwable throwable) {
         CrashReport crashReport = CrashReport.resolveCrashReport(throwable, "Sending packet");
         crashReport.resolveCrashReportCategory("Packet being sent").applyText("Packet class", new RuntimeComponent(this, packet));
         throw new SecondaryRuntimeExceptionAdapter(crashReport);
      }
   }

   private boolean matchesC03PacketPlayer(C03PacketPlayer packet) {
      return !Doubles.isFinite(packet.getCoordinate1())
         || !Doubles.isFinite(packet.getCoordinate())
         || !Doubles.isFinite(packet.getCoordinate3())
         || !Floats.isFinite(packet.getYawRotation())
         || !Floats.isFinite(packet.getYawRotation5());
   }

   @Override
   public void applyCPacketPlayerTryUseItemOnB(CPacketPlayerTryUseItemOnBlock packet) {
      ClientManager.handlePacket2(packet, this, this.entityPlayerAdapter.getWorldServer());
      WorldServer worldServer = this.vanillaRunnable.resolveWorldServer(this.entityPlayerAdapter.count4);
      ItemStack stack = this.entityPlayerAdapter.getItemStack();
      BlockPos blockPos = packet.getBlockPos();
      EnumFacing enumFacing = packet.getEnumFacing();
      this.entityPlayerAdapter.updateState48();
      if (blockPos.getY() < this.vanillaRunnable.getCount7() - 1 || enumFacing != EnumFacing.UP && blockPos.getY() < this.vanillaRunnable.getCount7()) {
         if (this.entityPlayerAdapter.getDouble3((double)blockPos.getX() + 0.5, (double)blockPos.getY() + 0.5, (double)blockPos.getZ() + 0.5) < 64.0
            && !this.vanillaRunnable.matchesWorld(worldServer, blockPos, this.entityPlayerAdapter)
            && worldServer.getInternalListEntry().checkBlockPos(blockPos)) {
            this.entityPlayerAdapter
               .internalWorldEntry
               .matchesEntityPlayer(this.entityPlayerAdapter, worldServer, stack, blockPos, enumFacing, packet.getFactor2(), packet.getFactor3(), packet.getFactor());
         }
      } else {
         BaseManagerStyleIChatComponentAdapter baseManagerStyleIChatComponentAdapter = new BaseManagerStyleIChatComponentAdapter("build.tooHigh", new Object[]{this.vanillaRunnable.getCount7()});
         baseManagerStyleIChatComponentAdapter.getStyleHasParentManager().withClientSettingsBlackType(ClientSettingsBlackType.RED);
         this.entityPlayerAdapter.auxiliaryListenerAdapter4.applyPacket(new S02PacketChat(baseManagerStyleIChatComponentAdapter));
      }

      this.entityPlayerAdapter.auxiliaryListenerAdapter4.applyPacket(new S23PacketBlockChange(worldServer, blockPos));
      this.entityPlayerAdapter.auxiliaryListenerAdapter4.applyPacket(new S23PacketBlockChange(worldServer, blockPos.offset(enumFacing)));
   }

   public NetworkManager getNetworkManager() {
      return this.networkManager;
   }

   @Override
   public void applyC0CPacketInput(C0CPacketInput packet) {
      ClientManager.handlePacket2(packet, this, this.entityPlayerAdapter.getWorldServer());
      this.entityPlayerAdapter.applyFloat(packet.getFactor(), packet.getFactor2(), packet.isEnabled(), packet.isEnabled2());
   }

   @Override
   public void applyC13PacketPlayerAbilities(C13PacketPlayerAbilities packet) {
      ClientManager.handlePacket2(packet, this, this.entityPlayerAdapter.getWorldServer());
      this.entityPlayerAdapter.invulnerableManager.state = packet.isEnabled4() && this.entityPlayerAdapter.invulnerableManager.state2;
   }

   @Override
   public void applyC15PacketClientSettings(C15PacketClientSettings packet) {
      ClientManager.handlePacket2(packet, this, this.entityPlayerAdapter.getWorldServer());
      this.entityPlayerAdapter.applyC15PacketClientSettings(packet);
   }

   @Override
   public void handleC08PacketPlayerBlockPlacement(C08PacketPlayerBlockPlacement packet) {
      ClientManager.handlePacket2(packet, this, this.entityPlayerAdapter.getWorldServer());
      WorldServer worldServer = this.vanillaRunnable.resolveWorldServer(this.entityPlayerAdapter.count4);
      ItemStack slot = this.entityPlayerAdapter.inventoryPlayer.getCurrentItem();
      boolean flag = false;
      BlockPos blockPos = packet.getBlockPos2();
      EnumFacing enumFacing = EnumFacing.getEnumFacing6(packet.getEntityId());
      this.entityPlayerAdapter.updateState48();
      if (packet.getEntityId() == 255) {
         if (slot == null) {
            return;
         }

         this.entityPlayerAdapter.internalWorldEntry.matchesEntityPlayer(this.entityPlayerAdapter, worldServer, slot);
      } else if (blockPos.getY() < this.vanillaRunnable.getCount7() - 1 || enumFacing != EnumFacing.UP && blockPos.getY() < this.vanillaRunnable.getCount7()) {
         if (this.enabled2
            && this.entityPlayerAdapter.getDouble3((double)blockPos.getX() + 0.5, (double)blockPos.getY() + 0.5, (double)blockPos.getZ() + 0.5) < 64.0
            && !this.vanillaRunnable.matchesWorld(worldServer, blockPos, this.entityPlayerAdapter)
            && worldServer.getInternalListEntry().checkBlockPos(blockPos)) {
            this.entityPlayerAdapter
               .internalWorldEntry
               .matchesEntityPlayer(this.entityPlayerAdapter, worldServer, slot, blockPos, enumFacing, packet.getYawRotation2(), packet.getYawRotation(), packet.getYawRotation1());
         }

         flag = true;
      } else {
         BaseManagerStyleIChatComponentAdapter baseManagerStyleIChatComponentAdapter = new BaseManagerStyleIChatComponentAdapter("build.tooHigh", new Object[]{this.vanillaRunnable.getCount7()});
         baseManagerStyleIChatComponentAdapter.getStyleHasParentManager().withClientSettingsBlackType(ClientSettingsBlackType.RED);
         this.entityPlayerAdapter.auxiliaryListenerAdapter4.applyPacket(new S02PacketChat(baseManagerStyleIChatComponentAdapter));
         flag = true;
      }

      if (flag) {
         this.entityPlayerAdapter.auxiliaryListenerAdapter4.applyPacket(new S23PacketBlockChange(worldServer, blockPos));
         this.entityPlayerAdapter.auxiliaryListenerAdapter4.applyPacket(new S23PacketBlockChange(worldServer, blockPos.offset(enumFacing)));
      } else {
         slot = this.entityPlayerAdapter.inventoryPlayer.getCurrentItem();
         if (slot != null && slot.animationsToGo == 0) {
            this.entityPlayerAdapter.inventoryPlayer.mainInventory[this.entityPlayerAdapter.inventoryPlayer.currentItem] = null;
            slot = null;
         }

         if (slot == null || slot.processAction6() == 0) {
            this.entityPlayerAdapter.enabled29 = true;
            this.entityPlayerAdapter.inventoryPlayer.mainInventory[this.entityPlayerAdapter.inventoryPlayer.currentItem] = ItemStack.resolveItemStack(
               this.entityPlayerAdapter.inventoryPlayer.mainInventory[this.entityPlayerAdapter.inventoryPlayer.currentItem]
            );
            Slot slot2 = this.entityPlayerAdapter
               .container2
               .resolveSlot(this.entityPlayerAdapter.inventoryPlayer, this.entityPlayerAdapter.inventoryPlayer.currentItem);
            this.entityPlayerAdapter.container2.detectAndSendChanges();
            this.entityPlayerAdapter.enabled29 = false;
            if (!ItemStack.matchesItemStack(this.entityPlayerAdapter.inventoryPlayer.getCurrentItem(), packet.getItemStack())) {
               this.applyPacket(
                  new S2FPacketSetSlot(
                     this.entityPlayerAdapter.container2.indexOffset2, slot2.indexOffset1, this.entityPlayerAdapter.inventoryPlayer.getCurrentItem()
                  )
               );
            }
         }
      }
   }

   public void applyTextByText(String text) {
      ChatComponentText chatComponentText = new ChatComponentText(text);
      this.networkManager.applyPacket(new S40PacketDisconnect(chatComponentText), new ChatComponentTextGenericFutureListener(this, chatComponentText), new GenericFutureListener[0]);
      this.networkManager.updateState4();
      Futures.getUnchecked(this.vanillaRunnable.resolveListenableFuture(this.networkManager::updateState3));
   }

   @Override
   public void applyC19PacketResourcePackStatu(C19PacketResourcePackStatus packet) {
   }

   @Override
   public void applyC14PacketTabComplete(C14PacketTabComplete packet) {
      ClientManager.handlePacket2(packet, this, this.entityPlayerAdapter.getWorldServer());
      ArrayList list = Lists.newArrayList();

      for (Object text_o : this.vanillaRunnable.resolveList(this.entityPlayerAdapter, packet.getLabelText(), packet.getBlockPos())) {
         String text = (String)text_o;
         list.add(text);
      }

      this.entityPlayerAdapter.auxiliaryListenerAdapter4.applyPacket(new S3APacketTabComplete(((String[])list.toArray(new String[list.size()]))));
   }

   @Override
   public void handleC03PacketPlayer(C03PacketPlayer packet) {
      ClientManager.handlePacket2(packet, this, this.entityPlayerAdapter.getWorldServer());
      if (this.matchesC03PacketPlayer(packet)) {
         this.applyTextByText("Invalid move packet received");
      } else {
         WorldServer worldServer = this.vanillaRunnable.resolveWorldServer(this.entityPlayerAdapter.count4);
         this.enabled = true;
         if (!this.entityPlayerAdapter.enabled26) {
            double value4 = this.entityPlayerAdapter.posX;
            double value5 = this.entityPlayerAdapter.posY;
            double value6 = this.entityPlayerAdapter.posZ;
            double offset = 0.0;
            double offset2 = packet.getCoordinate1() - this.value;
            double offset3 = packet.getCoordinate() - this.value2;
            double offset4 = packet.getCoordinate3() - this.value3;
            if (packet.isOnGround2()) {
               offset = offset2 * offset2 + offset3 * offset3 + offset4 * offset4;
               if (!this.enabled2 && offset < 0.25) {
                  this.enabled2 = true;
               }
            }

            if (this.enabled2) {
               this.count2 = this.count;
               if (this.entityPlayerAdapter.ridingEntity != null) {
                  float factor = this.entityPlayerAdapter.rotationYaw;
                  float factor2 = this.entityPlayerAdapter.rotationPitch;
                  this.entityPlayerAdapter.ridingEntity.updateState17();
                  offset2 = this.entityPlayerAdapter.posX;
                  offset3 = this.entityPlayerAdapter.posY;
                  offset4 = this.entityPlayerAdapter.posZ;
                  if (packet.isOnGround4()) {
                     factor = packet.getYawRotation5();
                     factor2 = packet.getYawRotation();
                  }

                  this.entityPlayerAdapter.onGround = packet.isOnGround();
                  this.entityPlayerAdapter.updateState46();
                  this.entityPlayerAdapter.setLocationAndAngles(offset2, offset3, offset4, factor, factor2);
                  if (this.entityPlayerAdapter.ridingEntity != null) {
                     this.entityPlayerAdapter.ridingEntity.updateState17();
                  }

                  this.vanillaRunnable.getMovingManager().applyEntityPlayerAdapter2(this.entityPlayerAdapter);
                  if (this.entityPlayerAdapter.ridingEntity != null) {
                     if (offset > 4.0) {
                        Entity entity = this.entityPlayerAdapter.ridingEntity;
                        this.entityPlayerAdapter.auxiliaryListenerAdapter4.applyPacket(new S18PacketEntityTeleport(entity));
                        this.applyDouble(
                           this.entityPlayerAdapter.posX,
                           this.entityPlayerAdapter.posY,
                           this.entityPlayerAdapter.posZ,
                           this.entityPlayerAdapter.rotationYaw,
                           this.entityPlayerAdapter.rotationPitch
                        );
                     }

                     this.entityPlayerAdapter.ridingEntity.enabled4 = true;
                  }

                  if (this.enabled2) {
                     this.value = this.entityPlayerAdapter.posX;
                     this.value2 = this.entityPlayerAdapter.posY;
                     this.value3 = this.entityPlayerAdapter.posZ;
                  }

                  worldServer.removeEntity(this.entityPlayerAdapter);
                  return;
               }

               if (this.entityPlayerAdapter.isState5()) {
                  this.entityPlayerAdapter.updateState46();
                  this.entityPlayerAdapter
                     .setLocationAndAngles(this.value, this.value2, this.value3, this.entityPlayerAdapter.rotationYaw, this.entityPlayerAdapter.rotationPitch);
                  worldServer.removeEntity(this.entityPlayerAdapter);
                  return;
               }

               offset = this.entityPlayerAdapter.posY;
               this.value = this.entityPlayerAdapter.posX;
               this.value2 = this.entityPlayerAdapter.posY;
               this.value3 = this.entityPlayerAdapter.posZ;
               offset2 = this.entityPlayerAdapter.posX;
               offset3 = this.entityPlayerAdapter.posY;
               offset4 = this.entityPlayerAdapter.posZ;
               float factor3 = this.entityPlayerAdapter.rotationYaw;
               float factor4 = this.entityPlayerAdapter.rotationPitch;
               if (packet.isOnGround2() && packet.getCoordinate() == -999.0) {
                  packet.setOnGround2(false);
               }

               if (packet.isOnGround2()) {
                  offset2 = packet.getCoordinate1();
                  offset3 = packet.getCoordinate();
                  offset4 = packet.getCoordinate3();
                  if (Math.abs(packet.getCoordinate1()) > 3.0E7 || Math.abs(packet.getCoordinate3()) > 3.0E7) {
                     this.applyTextByText("Illegal position");
                     return;
                  }
               }

               if (packet.isOnGround4()) {
                  factor3 = packet.getYawRotation5();
                  factor4 = packet.getYawRotation();
               }

               this.entityPlayerAdapter.updateState46();
               this.entityPlayerAdapter.setLocationAndAngles(this.value, this.value2, this.value3, factor3, factor4);
               if (!this.enabled2) {
                  return;
               }

               double offset5 = offset2 - this.entityPlayerAdapter.posX;
               double offset6 = offset3 - this.entityPlayerAdapter.posY;
               double offset7 = offset4 - this.entityPlayerAdapter.posZ;
               double offset8 = this.entityPlayerAdapter.motionX * this.entityPlayerAdapter.motionX
                  + this.entityPlayerAdapter.motionY * this.entityPlayerAdapter.motionY
                  + this.entityPlayerAdapter.motionZ * this.entityPlayerAdapter.motionZ;
               double offset9 = offset5 * offset5 + offset6 * offset6 + offset7 * offset7;
               if (offset9 - offset8 > 100.0
                  && (!this.vanillaRunnable.isActive10() || !this.vanillaRunnable.getLabelText3().equals(this.entityPlayerAdapter.getText()))) {
                  logger.warn(
                     this.entityPlayerAdapter.getText()
                        + " moved too quickly! "
                        + offset5
                        + ","
                        + offset6
                        + ","
                        + offset7
                        + " ("
                        + offset5
                        + ", "
                        + offset6
                        + ", "
                        + offset7
                        + ")"
                  );
                  this.applyDouble(this.value, this.value2, this.value3, this.entityPlayerAdapter.rotationYaw, this.entityPlayerAdapter.rotationPitch);
                  return;
               }

               float factor5 = 0.0625F;
               boolean empty = worldServer.getList2(
                     this.entityPlayerAdapter, this.entityPlayerAdapter.getBoundingBox2().resolveAxisAlignedBB((double)factor5, (double)factor5, (double)factor5)
                  )
                  .isEmpty();
               if (this.entityPlayerAdapter.onGround && !packet.isOnGround() && offset6 > 0.0) {
                  this.entityPlayerAdapter.closeScreen();
               }

               this.entityPlayerAdapter.applyDouble(offset5, offset6, offset7);
               this.entityPlayerAdapter.onGround = packet.isOnGround();
               offset5 = offset2 - this.entityPlayerAdapter.posX;
               offset6 = offset3 - this.entityPlayerAdapter.posY;
               if (offset6 > -0.5 || offset6 < 0.5) {
                  offset6 = 0.0;
               }

               offset7 = offset4 - this.entityPlayerAdapter.posZ;
               offset9 = offset5 * offset5 + offset6 * offset6 + offset7 * offset7;
               boolean flag = false;
               if (offset9 > 0.0625 && !this.entityPlayerAdapter.isState5() && !this.entityPlayerAdapter.internalWorldEntry.isActiveAsFlag()) {
                  flag = true;
                  logger.warn(this.entityPlayerAdapter.getText() + " moved wrongly!");
               }

               this.entityPlayerAdapter.setLocationAndAngles(offset2, offset3, offset4, factor3, factor4);
               this.entityPlayerAdapter
                  .applyDouble4(this.entityPlayerAdapter.posX - value4, this.entityPlayerAdapter.posY - value5, this.entityPlayerAdapter.posZ - value6);
               if (!this.entityPlayerAdapter.noClip) {
                  boolean empty2 = worldServer.getList2(
                        this.entityPlayerAdapter, this.entityPlayerAdapter.getBoundingBox2().resolveAxisAlignedBB((double)factor5, (double)factor5, (double)factor5)
                     )
                     .isEmpty();
                  if (empty && (flag || !empty2) && !this.entityPlayerAdapter.isState5()) {
                     this.applyDouble(this.value, this.value2, this.value3, factor3, factor4);
                     return;
                  }
               }

               AxisAlignedBB boundingBox = this.entityPlayerAdapter.getBoundingBox2().offset((double)factor5, (double)factor5, (double)factor5).contract(0.0, -0.55, 0.0);
               if (this.vanillaRunnable.isEnabled4() || this.entityPlayerAdapter.invulnerableManager.state2 || worldServer.matchesAxisAlignedBB3(boundingBox)) {
                  this.count3 = 0;
               } else if (offset6 >= -0.03125) {
                  this.count3++;
                  if (this.count3 > 80) {
                     logger.warn(this.entityPlayerAdapter.getText() + " was kicked for floating too long!");
                     this.applyTextByText("Flying is not enabled on this server");
                     return;
                  }
               }

               this.entityPlayerAdapter.onGround = packet.isOnGround();
               this.vanillaRunnable.getMovingManager().applyEntityPlayerAdapter2(this.entityPlayerAdapter);
               this.entityPlayerAdapter.applyDouble(this.entityPlayerAdapter.posY - offset, packet.isOnGround());
            } else if (this.count - this.count2 > 20) {
               this.applyDouble(this.value, this.value2, this.value3, this.entityPlayerAdapter.rotationYaw, this.entityPlayerAdapter.rotationPitch);
            }
         }
      }
   }

   public void applySet(double posX, double posY, double posZ, float factor, float factor2, Set set) {
      this.enabled2 = false;
      this.value = posX;
      this.value2 = posY;
      this.value3 = posZ;
      if (set.contains(NetHandlerPlayClientXType.X)) {
         this.value = this.value + this.entityPlayerAdapter.posX;
      }

      if (set.contains(NetHandlerPlayClientXType.Y)) {
         this.value2 = this.value2 + this.entityPlayerAdapter.posY;
      }

      if (set.contains(NetHandlerPlayClientXType.Z)) {
         this.value3 = this.value3 + this.entityPlayerAdapter.posZ;
      }

      float factor3 = factor;
      float factor4 = factor2;
      if (set.contains(NetHandlerPlayClientXType.Y_ROT)) {
         factor3 = factor + this.entityPlayerAdapter.rotationYaw;
      }

      if (set.contains(NetHandlerPlayClientXType.X_ROT)) {
         factor4 = factor2 + this.entityPlayerAdapter.rotationPitch;
      }

      this.entityPlayerAdapter.setLocationAndAngles(this.value, this.value2, this.value3, factor3, factor4);
      this.entityPlayerAdapter.auxiliaryListenerAdapter4.applyPacket(new S08PacketPlayerPosLook(posX, posY, posZ, factor, factor2, set));
   }

   @Override
   public void applyC11PacketEnchantItem(C11PacketEnchantItem packet) {
      ClientManager.handlePacket2(packet, this, this.entityPlayerAdapter.getWorldServer());
      this.entityPlayerAdapter.updateState48();
      if (this.entityPlayerAdapter.container2.indexOffset2 == packet.getCount2()
         && this.entityPlayerAdapter.container2.contains(this.entityPlayerAdapter)
         && !this.entityPlayerAdapter.isActive23()) {
         this.entityPlayerAdapter.container2.processAction(this.entityPlayerAdapter, packet.getCount());
         this.entityPlayerAdapter.container2.detectAndSendChanges();
      }
   }

   private void applyText(String text) {
      this.vanillaRunnable.getICommandManager().computeCount(this.entityPlayerAdapter, text);
   }

   @Override
   public void applyC17PacketCustomPayload(C17PacketCustomPayload packet) {
      ClientManager.handlePacket2(packet, this, this.entityPlayerAdapter.getWorldServer());
      if ("MC|BEdit".equals(packet.getText())) {
         PacketBuffer packet2 = new PacketBuffer(Unpooled.wrappedBuffer(packet.getPacketBufferAsPacketBuffer()));

         try {
            ItemStack stack = packet2.getItemStack();
            if (stack == null) {
               return;
            }

            if (!ItemWritableBook.matchesNBTTagCompound(stack.getStackTagCompound())) {
               throw new IOException("Invalid book tag!");
            }

            ItemStack slot = this.entityPlayerAdapter.inventoryPlayer.getCurrentItem();
            if (slot == null) {
               return;
            }

            if (stack.getItem() == Items.writableBook && stack.getItem() == slot.getItem()) {
               slot.applyText("pages", stack.getStackTagCompound().getExtendedLoggerEntry("pages", 8));
            }
         } catch (Exception exception) {
            logger.error("Couldn't handle book info", exception);
            return;
         } finally {
            packet2.release();
         }
      } else if ("MC|BSign".equals(packet.getText())) {
         PacketBuffer packet3 = new PacketBuffer(Unpooled.wrappedBuffer(packet.getPacketBufferAsPacketBuffer()));

         try {
            ItemStack stack2 = packet3.getItemStack();
            if (stack2 == null) {
               return;
            }

            if (!ItemWrittenBook.matchesNBTTagCompound(stack2.getStackTagCompound())) {
               throw new IOException("Invalid book tag!");
            }

            ItemStack slot2 = this.entityPlayerAdapter.inventoryPlayer.getCurrentItem();
            if (slot2 == null) {
               return;
            }

            if (stack2.getItem() == Items.writtenBook && slot2.getItem() == Items.writableBook) {
               slot2.applyText("author", new HandlerStringEntry(this.entityPlayerAdapter.getText()));
               slot2.applyText("title", new HandlerStringEntry(stack2.getStackTagCompound().resolveText("title")));
               slot2.applyText("pages", stack2.getStackTagCompound().getExtendedLoggerEntry("pages", 8));
               slot2.setItem(Items.writtenBook);
            }
         } catch (Exception exception2) {
            logger.error("Couldn't sign book", exception2);
            return;
         } finally {
            packet3.release();
         }
      } else {
         if ("MC|TrSel".equals(packet.getText())) {
            try {
               int count7 = packet.getPacketBufferAsPacketBuffer().readInt();
               Container container = this.entityPlayerAdapter.container2;
               if (container instanceof ExtendedContainerAdapter) {
                  ((ExtendedContainerAdapter)container).applyIndex(count7);
               }
            } catch (Exception exception3) {
               logger.error("Couldn't select trade", exception3);
            }
         } else if ("MC|AdvCdm".equals(packet.getText())) {
            if (!this.vanillaRunnable.isActive7()) {
               this.entityPlayerAdapter.applyIChatComponent(new BaseManagerStyleIChatComponentAdapter("advMode.notEnabled", new Object[0]));
            } else if (this.entityPlayerAdapter.matchesText(2, "") && this.entityPlayerAdapter.invulnerableManager.enabled2) {
               PacketBuffer packetBufferAsPacketBuffer = packet.getPacketBufferAsPacketBuffer();

               try {
                  byte byteValue = packetBufferAsPacketBuffer.readByte();
                  VectorDispatcher vectorDispatcher = null;
                  if (byteValue == 0) {
                     TileEntity tileEntity = this.entityPlayerAdapter.worldObj.resolveTileEntity(new BlockPos(packetBufferAsPacketBuffer.readInt(), packetBufferAsPacketBuffer.readInt(), packetBufferAsPacketBuffer.readInt()));
                     if (tileEntity instanceof TileEntityControl) {
                        vectorDispatcher = ((TileEntityControl)tileEntity).getVectorDispatcher();
                     }
                  } else if (byteValue == 1) {
                     Entity entityByID = this.entityPlayerAdapter.worldObj.getEntityByID(packetBufferAsPacketBuffer.readInt());
                     if (entityByID instanceof EntityCaveSpiderAdapter) {
                        vectorDispatcher = ((EntityCaveSpiderAdapter)entityByID).getVectorDispatcher();
                     }
                  }

                  String text = packetBufferAsPacketBuffer.resolveTextByIndex(packetBufferAsPacketBuffer.readableBytes());
                  boolean flag = packetBufferAsPacketBuffer.readBoolean();
                  if (vectorDispatcher != null) {
                     vectorDispatcher.applyText(text);
                     vectorDispatcher.setEnabled(flag);
                     if (!flag) {
                        vectorDispatcher.setIChatComponent((IChatComponent)null);
                     }

                     vectorDispatcher.updateState();
                     this.entityPlayerAdapter.applyIChatComponent(new BaseManagerStyleIChatComponentAdapter("advMode.setCommand.success", new Object[]{text}));
                  }
               } catch (Exception exception4) {
                  logger.error("Couldn't set command block", exception4);
               } finally {
                  packetBufferAsPacketBuffer.release();
               }
            } else {
               this.entityPlayerAdapter.applyIChatComponent(new BaseManagerStyleIChatComponentAdapter("advMode.notAllowed", new Object[0]));
            }
         } else if ("MC|Beacon".equals(packet.getText())) {
            if (this.entityPlayerAdapter.container2 instanceof BezierEvaluator) {
               try {
                  PacketBuffer packetBufferAsPacketBuffer2 = packet.getPacketBufferAsPacketBuffer();
                  int count8 = packetBufferAsPacketBuffer2.readInt();
                  int count9 = packetBufferAsPacketBuffer2.readInt();
                  BezierEvaluator container2 = (BezierEvaluator)this.entityPlayerAdapter.container2;
                  Slot slot3 = container2.getSlot(0);
                  if (slot3.isActive()) {
                     slot3.decrStackSize(1);
                     IInventory iInventory = container2.getIInventory();
                     iInventory.processAction(1, count8);
                     iInventory.processAction(2, count9);
                     iInventory.processAction5();
                  }
               } catch (Exception exception5) {
                  logger.error("Couldn't set beacon", exception5);
               }
            }
         } else if ("MC|ItemName".equals(packet.getText()) && this.entityPlayerAdapter.container2 instanceof RepairContainer) {
            RepairContainer container3 = (RepairContainer)this.entityPlayerAdapter.container2;
            if (packet.getPacketBufferAsPacketBuffer() != null && packet.getPacketBufferAsPacketBuffer().readableBytes() >= 1) {
               String text2 = CharArrayHolder.resolveText(packet.getPacketBufferAsPacketBuffer().resolveTextByIndex(32767));
               if (text2.length() <= 30) {
                  container3.applyText(text2);
               }
            } else {
               container3.applyText("");
            }
         }
      }
   }

   @Override
   public void applyC0BPacketEntityAction(C0BPacketEntityAction packet) {
      ClientManager.handlePacket2(packet, this, this.entityPlayerAdapter.getWorldServer());
      this.entityPlayerAdapter.updateState48();
      switch (PassiveIntArrayEntry.countArray2[packet.getEntityPlayerAction().ordinal()]) {
         case 1:
            this.entityPlayerAdapter.setEnabled2(true);
            break;
         case 2:
            this.entityPlayerAdapter.setEnabled2(false);
            break;
         case 3:
            this.entityPlayerAdapter.setState3(true);
            break;
         case 4:
            this.entityPlayerAdapter.setState3(false);
            break;
         case 5:
            this.entityPlayerAdapter.applyFlag(false, true, true);
            this.enabled2 = false;
            break;
         case 6:
            if (this.entityPlayerAdapter.ridingEntity instanceof EntityHorse) {
               ((EntityHorse)this.entityPlayerAdapter.ridingEntity).applyIndex11(packet.getCount());
            }
            break;
         case 7:
            if (this.entityPlayerAdapter.ridingEntity instanceof EntityHorse) {
               ((EntityHorse)this.entityPlayerAdapter.ridingEntity).applyEntityPlayer7(this.entityPlayerAdapter);
            }
            break;
         default:
            throw new IllegalArgumentException("Invalid client command!");
      }
   }

   @Override
   public void handleC02PacketUseEntity(C02PacketUseEntity packet) {
      ClientManager.handlePacket2(packet, this, this.entityPlayerAdapter.getWorldServer());
      WorldServer worldServer = this.vanillaRunnable.resolveWorldServer(this.entityPlayerAdapter.count4);
      Entity entity = packet.processAction(worldServer);
      this.entityPlayerAdapter.updateState48();
      if (entity != null) {
         boolean flag = this.entityPlayerAdapter.checkEntity4(entity);
         double offset = 36.0;
         if (!flag) {
            offset = 9.0;
         }

         if (this.entityPlayerAdapter.getCoordinate(entity) < offset) {
            if (packet.getUseEntityAction() == UseEntityAction.INTERACT) {
               this.entityPlayerAdapter.matchesEntity8(entity);
            } else if (packet.getUseEntityAction() == UseEntityAction.INTERACT_AT) {
               entity.matchesEntityPlayer(this.entityPlayerAdapter, packet.getPositionVec());
            } else if (packet.getUseEntityAction() == UseEntityAction.ATTACK) {
               if (entity instanceof EntityItem || entity instanceof EntityXporb || entity instanceof EntityArrow || entity == this.entityPlayerAdapter) {
                  this.applyTextByText("Attempting to attack an invalid entity");
                  this.vanillaRunnable.warn("Player " + this.entityPlayerAdapter.getText() + " tried to attack an invalid entity");
                  return;
               }

               this.entityPlayerAdapter.applyEntity18(entity);
            }
         }
      }
   }

   @Override
   public void handleC07PacketPlayerDigging(C07PacketPlayerDigging packet) {
      ClientManager.handlePacket2(packet, this, this.entityPlayerAdapter.getWorldServer());
      WorldServer worldServer = this.vanillaRunnable.resolveWorldServer(this.entityPlayerAdapter.count4);
      BlockPos blockPos = packet.getBlockPos();
      this.entityPlayerAdapter.updateState48();
      switch (PassiveIntArrayEntry.countArray[packet.getPlayerDiggingActionType().ordinal()]) {
         case 1:
            if (!this.entityPlayerAdapter.isActive23()) {
               this.entityPlayerAdapter.getEntityItem(false);
            }

            return;
         case 2:
            if (!this.entityPlayerAdapter.isActive23()) {
               this.entityPlayerAdapter.getEntityItem(true);
            }

            return;
         case 3:
            this.entityPlayerAdapter.updateState3();
            return;
         case 4:
         case 5:
         case 6:
            double offset = this.entityPlayerAdapter.posX - ((double)blockPos.getX() + 0.5);
            double offset2 = this.entityPlayerAdapter.posY - ((double)blockPos.getY() + 0.5) + 1.5;
            double offset3 = this.entityPlayerAdapter.posZ - ((double)blockPos.getZ() + 0.5);
            if (offset * offset + offset2 * offset2 + offset3 * offset3 > 36.0) {
               return;
            } else if (blockPos.getY() >= this.vanillaRunnable.getCount7()) {
               return;
            } else {
               if (packet.getPlayerDiggingActionType() == PlayerDiggingActionType.START_DESTROY_BLOCK) {
                  if (!this.vanillaRunnable.matchesWorld(worldServer, blockPos, this.entityPlayerAdapter) && worldServer.getInternalListEntry().checkBlockPos(blockPos)) {
                     this.entityPlayerAdapter.internalWorldEntry.applyBlockPos(blockPos, packet.getEnumFacing());
                  } else {
                     this.entityPlayerAdapter.auxiliaryListenerAdapter4.applyPacket(new S23PacketBlockChange(worldServer, blockPos));
                  }
               } else {
                  if (packet.getPlayerDiggingActionType() == PlayerDiggingActionType.STOP_DESTROY_BLOCK) {
                     this.entityPlayerAdapter.internalWorldEntry.applyBlockPos(blockPos);
                  } else if (packet.getPlayerDiggingActionType() == PlayerDiggingActionType.ABORT_DESTROY_BLOCK) {
                     this.entityPlayerAdapter.internalWorldEntry.updateState();
                  }

                  if (worldServer.getBlockState(blockPos).getBlock().getMaterial() != Material.coreItemEntry) {
                     this.entityPlayerAdapter.auxiliaryListenerAdapter4.applyPacket(new S23PacketBlockChange(worldServer, blockPos));
                  }
               }

               return;
            }
         default:
            throw new IllegalArgumentException("Invalid player action");
      }
   }

   @Override
   public void applyC09PacketHeldItemChange(C09PacketHeldItemChange packet) {
      ClientManager.handlePacket2(packet, this, this.entityPlayerAdapter.getWorldServer());
      if (packet.getCount() >= 0 && packet.getCount() < InventoryPlayer.processAction6()) {
         this.entityPlayerAdapter.inventoryPlayer.currentItem = packet.getCount();
         this.entityPlayerAdapter.updateState48();
      } else {
         logger.warn(this.entityPlayerAdapter.getText() + " tried to set an invalid carried item");
      }
   }

   private long nanoTime() {
      return System.nanoTime() / 1000000L;
   }

   @Override
   public void applyC16PacketClientStatus(C16PacketClientStatus packet) {
      ClientManager.handlePacket2(packet, this, this.entityPlayerAdapter.getWorldServer());
      this.entityPlayerAdapter.updateState48();
      AutoSoupPerformRespawnType autoSoupPerformRespawnType = packet.getAutoSoupPerformRespawnType();
      switch (PassiveIntArrayEntry.countArray3[autoSoupPerformRespawnType.ordinal()]) {
         case 1:
            if (this.entityPlayerAdapter.enabled26) {
               this.entityPlayerAdapter = this.vanillaRunnable.getMovingManager().resolveEntityPlayerAdapter(this.entityPlayerAdapter, 0, true);
            } else if (this.entityPlayerAdapter.getWorldServer().getRandomSeedManager().isEnabled2()) {
               if (this.vanillaRunnable.isActive10() && this.entityPlayerAdapter.getText().equals(this.vanillaRunnable.getLabelText3())) {
                  this.entityPlayerAdapter.auxiliaryListenerAdapter4.applyTextByText("You have died. Game over, man, it's game over!");
                  this.vanillaRunnable.updateState9();
               } else {
                  SimpleDateFormatEntryAdapter simpleDateFormatEntryAdapter = new SimpleDateFormatEntryAdapter(
                     this.entityPlayerAdapter.getGameProfile(), (Date)null, "(You just lost the game)", (Date)null, "Death in Hardcore"
                  );
                  this.vanillaRunnable.getMovingManager().getInternalLoggerEntryAdapter().applyTargetStack(simpleDateFormatEntryAdapter);
                  this.entityPlayerAdapter.auxiliaryListenerAdapter4.applyTextByText("You have died. Game over, man, it's game over!");
               }
            } else {
               if (this.entityPlayerAdapter.getFloat2() > 0.0F) {
                  return;
               }

               this.entityPlayerAdapter = this.vanillaRunnable.getMovingManager().resolveEntityPlayerAdapter(this.entityPlayerAdapter, 0, false);
            }
            break;
         case 2:
            this.entityPlayerAdapter.getStatFileWriterHandler().applyEntityPlayerAdapterByEntityPlayerAdap(this.entityPlayerAdapter);
            break;
         case 3:
            this.entityPlayerAdapter.applyDuplicateStatIdManagerByDuplicateStatIdM(AchievementTheEnd2Manager.auxiliaryDuplicateStatIdManagerAdapter31);
      }
   }

   @Override
   public void applyCAnimateHandPacket(CAnimateHandPacket packet) {
      ClientManager.handlePacket2(packet, this, this.entityPlayerAdapter.getWorldServer());
      this.entityPlayerAdapter.updateState48();
      this.entityPlayerAdapter.respawnPlayer();
   }

   public AuxiliaryListenerAdapter4(VanillaRunnable vanillaRunnable2, NetworkManager networkManager2, EntityPlayerAdapter entityPlayerAdapter2) {
      this.vanillaRunnable = vanillaRunnable2;
      this.networkManager = networkManager2;
      networkManager2.applyINetHandler(this);
      this.entityPlayerAdapter = entityPlayerAdapter2;
      entityPlayerAdapter2.auxiliaryListenerAdapter4 = this;
   }

   @Override
   public void applyIChatComponent(IChatComponent iChatComponent) {
      logger.info(this.entityPlayerAdapter.getText() + " lost connection: " + iChatComponent);
      this.vanillaRunnable.updateState11();
      BaseManagerStyleIChatComponentAdapter baseManagerStyleIChatComponentAdapter = new BaseManagerStyleIChatComponentAdapter(
         "multiplayer.player.left", new Object[]{this.entityPlayerAdapter.getChatComponent()}
      );
      baseManagerStyleIChatComponentAdapter.getStyleHasParentManager().withClientSettingsBlackType(ClientSettingsBlackType.YELLOW);
      this.vanillaRunnable.getMovingManager().applyIChatComponent(baseManagerStyleIChatComponentAdapter);
      this.entityPlayerAdapter.updateState6();
      this.vanillaRunnable.getMovingManager().applyEntityPlayerAdapterByEntityPlayerAdap(this.entityPlayerAdapter);
      if (this.vanillaRunnable.isActive10() && this.entityPlayerAdapter.getText().equals(this.vanillaRunnable.getLabelText3())) {
         logger.info("Stopping singleplayer server as player logged out");
         this.vanillaRunnable.updateState();
      }
   }

   @Override
   public void applyC01PacketChatMessage(C01PacketChatMessage packet) {
      ClientManager.handlePacket2(packet, this, this.entityPlayerAdapter.getWorldServer());
      if (this.entityPlayerAdapter.getGameSettingsFullType() == GameSettingsFullType.HIDDEN) {
         BaseManagerStyleIChatComponentAdapter baseManagerStyleIChatComponentAdapter = new BaseManagerStyleIChatComponentAdapter("chat.cannotSend", new Object[0]);
         baseManagerStyleIChatComponentAdapter.getStyleHasParentManager().withClientSettingsBlackType(ClientSettingsBlackType.RED);
         this.applyPacket(new S02PacketChat(baseManagerStyleIChatComponentAdapter));
      } else {
         this.entityPlayerAdapter.updateState48();
         String text = packet.getText();
         text = StringUtils.normalizeSpace(text);

         for (int index = 0; index < text.length(); index++) {
            if (!CharArrayHolder.checkChar(text.charAt(index))) {
               this.applyTextByText("Illegal characters in chat");
               return;
            }
         }

         if (text.startsWith("/")) {
            this.applyText(text);
         } else {
            BaseManagerStyleIChatComponentAdapter baseManagerStyleIChatComponentAdapter2 = new BaseManagerStyleIChatComponentAdapter(
               "chat.type.text", new Object[]{this.entityPlayerAdapter.getChatComponent(), text}
            );
            this.vanillaRunnable.getMovingManager().applyIChatComponent(baseManagerStyleIChatComponentAdapter2, false);
         }

         this.count5 += 20;
         if (this.count5 > 200 && !this.vanillaRunnable.getMovingManager().matchesGameProfileByGameProfile(this.entityPlayerAdapter.getGameProfile())) {
            this.applyTextByText("disconnect.spam");
         }
      }
   }

   @Override
   public void applyC0DPacketCloseWindow(C0DPacketCloseWindow packet) {
      ClientManager.handlePacket2(packet, this, this.entityPlayerAdapter.getWorldServer());
      this.entityPlayerAdapter.updateState50();
   }

   @Override
   public void applyC18PacketSpectate(C18PacketSpectate packet) {
      ClientManager.handlePacket2(packet, this, this.entityPlayerAdapter.getWorldServer());
      if (this.entityPlayerAdapter.isActive23()) {
         Entity entity = null;

         for (WorldServer worldServer : this.vanillaRunnable.worldServerArray) {
            if (worldServer != null) {
               entity = packet.resolveEntity(worldServer);
               if (entity != null) {
                  break;
               }
            }
         }

         if (entity != null) {
            this.entityPlayerAdapter.applyEntity32(this.entityPlayerAdapter);
            this.entityPlayerAdapter.setEntity8((Entity)null);
            if (entity.worldObj != this.entityPlayerAdapter.worldObj) {
               WorldServer worldServer2 = this.entityPlayerAdapter.getWorldServer();
               WorldServer worldObj = (WorldServer)entity.worldObj;
               this.entityPlayerAdapter.count4 = entity.count4;
               this.applyPacket(
                  new S07PacketRespawn(
                     this.entityPlayerAdapter.count4,
                     worldServer2.getGameSettingsPeacefulType(),
                     worldServer2.getRandomSeedManager().getInfoManager(),
                     this.entityPlayerAdapter.internalWorldEntry.getWorldSettingsGameType()
                  )
               );
               worldServer2.applyEntity4(this.entityPlayerAdapter);
               this.entityPlayerAdapter.isDead = false;
               this.entityPlayerAdapter.applyDoubleByDouble(entity.posX, entity.posY, entity.posZ, entity.rotationYaw, entity.rotationPitch);
               if (this.entityPlayerAdapter.canExecute()) {
                  worldServer2.applyEntity(this.entityPlayerAdapter, false);
                  worldObj.matchesEntity(this.entityPlayerAdapter);
                  worldObj.applyEntity(this.entityPlayerAdapter, false);
               }

               this.entityPlayerAdapter.setWorldObj(worldObj);
               this.vanillaRunnable.getMovingManager().applyEntityPlayerAdapterByEntityPlayerAdap(this.entityPlayerAdapter, worldServer2);
               this.entityPlayerAdapter.applyDoubleByDouble(entity.posX, entity.posY, entity.posZ);
               this.entityPlayerAdapter.internalWorldEntry.setWorld(worldObj);
               this.vanillaRunnable.getMovingManager().applyEntityPlayerAdapter(this.entityPlayerAdapter, worldObj);
               this.vanillaRunnable.getMovingManager().applyEntityPlayerAdapter4(this.entityPlayerAdapter);
            } else {
               this.entityPlayerAdapter.applyDoubleByDouble(entity.posX, entity.posY, entity.posZ);
            }
         }
      }
   }

   @Override
   public void updateState() {
      this.enabled = false;
      this.count++;
      this.vanillaRunnable.uNKNOWNManager.applyTextByText("keepAlive");
      if ((long)this.count - this.timestamp2 > 40L) {
         this.timestamp2 = (long)this.count;
         this.timestamp = this.nanoTime();
         this.count4 = (int)this.timestamp;
         this.applyPacket(new S00PacketKeepAlive(this.count4));
      }

      this.vanillaRunnable.uNKNOWNManager.updateState();
      if (this.count5 > 0) {
         this.count5--;
      }

      if (this.count6 > 0) {
         this.count6--;
      }

      if (this.entityPlayerAdapter.getTimestamp2() > 0L
         && this.vanillaRunnable.getCount4() > 0
         && VanillaRunnable.currentTimeMillis() - this.entityPlayerAdapter.getTimestamp2() > (long)(this.vanillaRunnable.getCount4() * 1000 * 60)) {
         this.applyTextByText("You have been idle for too long!");
      }
   }

   @Override
   public void applyC0FPacketConfirmTransactio(C0FPacketConfirmTransaction packet) {
      ClientManager.handlePacket2(packet, this, this.entityPlayerAdapter.getWorldServer());
      Short shortValue = (Short)this.altItemArrayEntry.resolveTargetByIndex(this.entityPlayerAdapter.container2.indexOffset2);
      if (shortValue != null
         && packet.getShortIndex() == shortValue
         && this.entityPlayerAdapter.container2.indexOffset2 == packet.getEntityId()
         && !this.entityPlayerAdapter.container2.contains(this.entityPlayerAdapter)
         && !this.entityPlayerAdapter.isActive23()) {
         this.entityPlayerAdapter.container2.applyEntityPlayer(this.entityPlayerAdapter, true);
      }
   }

   public void applyDouble(double posX, double posY, double posZ, float factor, float factor2) {
      this.applySet(posX, posY, posZ, factor, factor2, Collections.emptySet());
   }

   @Override
   public void applyC12PacketUpdateSign(C12PacketUpdateSign packet) {
      ClientManager.handlePacket2(packet, this, this.entityPlayerAdapter.getWorldServer());
      this.entityPlayerAdapter.updateState48();
      WorldServer worldServer = this.vanillaRunnable.resolveWorldServer(this.entityPlayerAdapter.count4);
      BlockPos blockPos = packet.getBlockPos();
      if (worldServer.checkBlockPos12(blockPos)) {
         TileEntity tileEntity = worldServer.resolveTileEntity(blockPos);
         if (!(tileEntity instanceof TileEntitySign)) {
            return;
         }

         TileEntitySign tileEntitySign = (TileEntitySign)tileEntity;
         if (!tileEntitySign.isEnabled2() || tileEntitySign.getEntityPlayerAsEntityPlayer() != this.entityPlayerAdapter) {
            this.vanillaRunnable.warn("Player " + this.entityPlayerAdapter.getText() + " just tried to change non-editable sign");
            return;
         }

         IChatComponent[] iChatComponentArray = packet.getIChatComponentArray();

         for (int index = 0; index < iChatComponentArray.length; index++) {
            tileEntitySign.iChatComponentArray[index] = new ChatComponentText(ClientSettingsBlackType.resolveText(iChatComponentArray[index].getString()));
         }

         tileEntitySign.processAction5();
         worldServer.applyBlockPos2(blockPos);
      }
   }

   @Override
   public void applyC00PacketKeepAlive(C00PacketKeepAlive packet) {
      if (packet.getCount() == this.count4) {
         int count7 = (int)(this.nanoTime() - this.timestamp);
         this.entityPlayerAdapter.count41 = (this.entityPlayerAdapter.count41 * 3 + count7) / 4;
      }
   }

   @Override
   public void applyC0APacketAnimation(C0APacketAnimation packet) {
      ClientManager.handlePacket2(packet, this, this.entityPlayerAdapter.getWorldServer());
      this.entityPlayerAdapter.updateState48();
      this.entityPlayerAdapter.respawnPlayer();
   }
}
