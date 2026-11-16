package net.minecraft.client.multiplayer;

import augustus.modules.player.NoRotationSet;
import cc.baritone.api.SecondaryItemEntry;
import cc.baritone.utils.AStarResolver;
import cc.baritone.utils.AStarVerifier;
import cc.baritone.utils.ActiveListener;
import cc.baritone.utils.BreadthFirstProvider;
import cc.baritone.utils.BreadthFirstService;
import cc.baritone.utils.DepthFirstRegistry;
import cc.baritone.utils.Pokechu22WorldDownloaderManager;
import cc.baritone.utils.SecondaryOptionType;
import cc.baritone.utils.SettingsTxtManager;
import client.Client;
import client.ClientGlobals;
import client.event.AuxiliaryListener;
import client.event.EventPacket;
import client.event.ExtendedListener;
import client.event.LocalListener;
import client.event.PacketListener;
import client.event.WindowActionListener;
import client.gui.BaseGuiScreenAdapter;
import client.gui.CustomGuiScreenAdapter;
import client.gui.DefaultGuiScreenAdapter;
import client.gui.ExtraGuiScreenAdapter;
import client.gui.ManagedGuiScreenAdapter;
import client.gui.ScrollableContainer;
import client.gui.SpecialGuiScreenAdapter;
import client.module.Module;
import client.network.NetworkPlayerInfo;
import client.setting.AttackEffectsExplosionNormalType;
import client.setting.ClientSettingsBlackType;
import client.setting.ComplexOptionType;
import client.setting.DisablerSuccessfullyLoadedType;
import client.setting.GameSettings;
import client.setting.MovementBooleanProperty;
import client.setting.MovementDistanceProperty;
import client.setting.MovementFilterProperty;
import client.setting.MovementNumberProperty;
import client.setting.MovementStateProperty;
import client.setting.NetHandlerPlayClientXType;
import client.setting.StaffDetectorAddPlayerType;
import client.setting.SupplementalOptionType;
import client.setting.WorldSettingsGameType;
import client.util.AchievementTheEnd2Manager;
import client.util.AdvancedLongEntry;
import client.util.AdvancedMapEntry;
import client.util.AlertNotificationSound;
import client.util.AuxiliaryArrayListAdapter;
import client.util.AuxiliaryDuplicateStatIdManagerAdapter;
import client.util.AuxiliaryFutureCallbackAdapter;
import client.util.AuxiliaryIInventoryAdapter;
import client.util.BaseManagerStyleIChatComponentAdapter;
import client.util.ClientBrandHelper;
import client.util.ClientManager;
import client.util.DimensionStringEntry;
import client.util.DuplicateStatIdManager;
import client.util.ExternalStringEntry;
import client.util.FactoryStringEntry;
import client.util.FutureCallbackAdapter;
import client.util.HUIHRVManager;
import client.util.HealthHandler;
import client.util.IInventoryBridge;
import client.util.INetHandlerPlayClient;
import client.util.InternalFutureCallbackAdapter;
import client.util.InvalidOperationManager;
import client.util.LatencyManager;
import client.util.ListenerCoordinator;
import client.util.MinecraftAccessor;
import client.util.NativeDoubleEntry;
import client.util.OverworldManager;
import client.util.Packet;
import client.util.PacketBuffer;
import client.util.PrimaryLoggerEntry;
import client.util.RayTraceUtil;
import client.util.ScoreboardData;
import client.util.ServersManager;
import client.util.SessionTokenManager;
import client.util.ShortEntry;
import client.util.SkippingEntityWithIdManager;
import client.util.SlotManager;
import client.util.StandardIntArrayEntry;
import client.util.StringArrayCallback;
import client.util.SupplementalLongEntry;
import client.util.ThePlayerNameManager;
import client.util.TypeMAdapter;
import client.util.VanillaManager;
import client.via.ViaLoadingBase;
import client.via.ViaProtocolHelper;
import com.google.common.collect.Maps;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.MoreExecutors;
import com.mojang.authlib.GameProfile;
import com.viaversion.viaversion.api.protocol.version.ProtocolVersion;
import io.netty.buffer.Unpooled;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBreakState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityOtherPlayerMP;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.GuiChat;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.gui.GuiMultiplayer;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityArmorstand;
import net.minecraft.entity.EntityArrow;
import net.minecraft.entity.EntityBoat;
import net.minecraft.entity.EntityEndercrystal;
import net.minecraft.entity.EntityEyeofendersignal;
import net.minecraft.entity.EntityFallingsand;
import net.minecraft.entity.EntityFireball;
import net.minecraft.entity.EntityFireworksrocketentity;
import net.minecraft.entity.EntityGuardian;
import net.minecraft.entity.EntityItem;
import net.minecraft.entity.EntityItemframe;
import net.minecraft.entity.EntityLeashknot;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EntityMob;
import net.minecraft.entity.EntityPainting;
import net.minecraft.entity.EntityPhantomAdapter;
import net.minecraft.entity.EntityPrimedtnt;
import net.minecraft.entity.EntitySmallfireball;
import net.minecraft.entity.EntitySnowball;
import net.minecraft.entity.EntityThrownegg;
import net.minecraft.entity.EntityThrownenderpearl;
import net.minecraft.entity.EntityThrownexpbottle;
import net.minecraft.entity.EntityThrownpotion;
import net.minecraft.entity.EntityWitherskull;
import net.minecraft.entity.EntityXporb;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityFishHook;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.InventoryLargeChest;
import net.minecraft.inventory.InventoryPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFilledMap;
import net.minecraft.item.ItemGroupManager;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.client.C00PacketKeepAlive;
import net.minecraft.network.play.client.C06PacketPlayerPosLook;
import net.minecraft.network.play.client.C0FPacketConfirmTransaction;
import net.minecraft.network.play.client.C17PacketCustomPayload;
import net.minecraft.network.play.client.C19PacketResourcePackStatus;
import net.minecraft.network.play.server.S00PacketKeepAlive;
import net.minecraft.network.play.server.S01PacketJoinGame;
import net.minecraft.network.play.server.S02PacketChat;
import net.minecraft.network.play.server.S03PacketTimeUpdate;
import net.minecraft.network.play.server.S04PacketEntityEquipment;
import net.minecraft.network.play.server.S05PacketSpawnPosition;
import net.minecraft.network.play.server.S06PacketUpdateHealth;
import net.minecraft.network.play.server.S07PacketRespawn;
import net.minecraft.network.play.server.S08PacketPlayerPosLook;
import net.minecraft.network.play.server.S09PacketHeldItemChange;
import net.minecraft.network.play.server.S0APacketUseBed;
import net.minecraft.network.play.server.S0BPacketAnimation;
import net.minecraft.network.play.server.S0CPacketSpawnPlayer;
import net.minecraft.network.play.server.S0DPacketCollectItem;
import net.minecraft.network.play.server.S0EPacketSpawnObject;
import net.minecraft.network.play.server.S0FPacketSpawnMob;
import net.minecraft.network.play.server.S10PacketSpawnPainting;
import net.minecraft.network.play.server.S11PacketSpawnExperienceOrb;
import net.minecraft.network.play.server.S12PacketEntityVelocity;
import net.minecraft.network.play.server.S13PacketDestroyEntities;
import net.minecraft.network.play.server.S14PacketEntity;
import net.minecraft.network.play.server.S18PacketEntityTeleport;
import net.minecraft.network.play.server.S19PacketEntityHeadLook;
import net.minecraft.network.play.server.S19PacketEntityStatus;
import net.minecraft.network.play.server.S1BPacketEntityAttach;
import net.minecraft.network.play.server.S1CPacketEntityMetadata;
import net.minecraft.network.play.server.S1DPacketEntityEffect;
import net.minecraft.network.play.server.S1EPacketRemoveEntityEffect;
import net.minecraft.network.play.server.S1FPacketSetExperience;
import net.minecraft.network.play.server.S20PacketEntityProperties;
import net.minecraft.network.play.server.S21PacketChunkData;
import net.minecraft.network.play.server.S22PacketMultiBlockChange;
import net.minecraft.network.play.server.S23PacketBlockChange;
import net.minecraft.network.play.server.S24PacketBlockAction;
import net.minecraft.network.play.server.S25PacketBlockBreakAnim;
import net.minecraft.network.play.server.S26PacketMapChunkBulk;
import net.minecraft.network.play.server.S27PacketExplosion;
import net.minecraft.network.play.server.S28PacketEffect;
import net.minecraft.network.play.server.S29PacketSoundEffect;
import net.minecraft.network.play.server.S2APacketParticles;
import net.minecraft.network.play.server.S2BPacketChangeGameState;
import net.minecraft.network.play.server.S2CPacketSpawnGlobalEntity;
import net.minecraft.network.play.server.S2DPacketOpenWindow;
import net.minecraft.network.play.server.S2EPacketCloseWindow;
import net.minecraft.network.play.server.S2FPacketSetSlot;
import net.minecraft.network.play.server.S30PacketWindowItems;
import net.minecraft.network.play.server.S31PacketWindowProperty;
import net.minecraft.network.play.server.S32PacketConfirmTransaction;
import net.minecraft.network.play.server.S33PacketUpdateSign;
import net.minecraft.network.play.server.S34PacketMaps;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.network.play.server.S36PacketSignEditorOpen;
import net.minecraft.network.play.server.S37PacketStatistics;
import net.minecraft.network.play.server.S38PacketPlayerListItem;
import net.minecraft.network.play.server.S39PacketPlayerAbilities;
import net.minecraft.network.play.server.S3APacketTabComplete;
import net.minecraft.network.play.server.S3BPacketScoreboardObjective;
import net.minecraft.network.play.server.S3CPacketUpdateScore;
import net.minecraft.network.play.server.S3DPacketDisplayScoreboard;
import net.minecraft.network.play.server.S3EPacketTeams;
import net.minecraft.network.play.server.S3FPacketCustomPayload;
import net.minecraft.network.play.server.S40PacketDisconnect;
import net.minecraft.network.play.server.S41PacketServerDifficulty;
import net.minecraft.network.play.server.S42PacketCombatEvent;
import net.minecraft.network.play.server.S43PacketCamera;
import net.minecraft.network.play.server.S44PacketWorldBorder;
import net.minecraft.network.play.server.S45PacketTitle;
import net.minecraft.network.play.server.S46PacketSetCompressionLevel;
import net.minecraft.network.play.server.S47PacketPlayerListHeaderFooter;
import net.minecraft.network.play.server.S48PacketResourcePackSend;
import net.minecraft.network.play.server.S49PacketUpdateEntityNBT;
import net.minecraft.potion.PotionEffect;
import net.minecraft.scoreboard.ScorePlayerTeam;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityBanner;
import net.minecraft.tileentity.TileEntityBeacon;
import net.minecraft.tileentity.TileEntityControl;
import net.minecraft.tileentity.TileEntityFlowerpot;
import net.minecraft.tileentity.TileEntityMobspawner;
import net.minecraft.tileentity.TileEntitySign;
import net.minecraft.tileentity.TileEntitySkull;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.MathHelper;
import net.minecraft.util.StatCollector;
import net.minecraft.util.Vec3;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class NetHandlerPlayClient implements MinecraftAccessor, INetHandlerPlayClient {
   public static final Logger logger = LogManager.getLogger();
   public final NetworkManager networkManager;
   public final GameProfile gameProfile;
   public final GuiScreen guiScreen;
   public Minecraft mcClient;
   public WorldClient worldClient;
   public boolean state;
   public final Map entryMap = Maps.newConcurrentMap();
   public final Map entryMap2 = Maps.newConcurrentMap();
   public int indexOffset = 20;
   public boolean state1 = false;
   public final Random random = new Random();

   @Override
   public void applyS42PacketCombatEvent(S42PacketCombatEvent packet) {
      ClientManager.handlePacket2(packet, this, this.mcClient);
      this.worldClient.getEntityByID(packet.count);
      if (packet.movementStateProperty == MovementStateProperty.ENTITY_DIED
         && this.worldClient.getEntityByID(packet.count2) instanceof EntityPlayer
         && ((Module)Client.getClient().getModuleManager().baritone).isToggled()) {
         ActiveListener activeListener = SettingsTxtManager.getExtraListener().resolveActiveListener(this);
         if (activeListener != null) {
            activeListener.getCompositeListener().forEach();
         }
      }
   }

   public NetworkPlayerInfo getNetworkPlayerInfo(UUID uuid) {
      return (NetworkPlayerInfo)this.entryMap.get(uuid);
   }

   @Override
   public void applyS1FPacketSetExperience(S1FPacketSetExperience packet) {
      ClientManager.handlePacket2(packet, this, this.mcClient);
      this.mcClient.thePlayer.applyFloat(packet.getFactor(), packet.getCount2(), packet.getCount());
   }

   @Override
   public void applyS49PacketUpdateEntityNBT(S49PacketUpdateEntityNBT packet) {
      ClientManager.handlePacket2(packet, this, this.mcClient);
      Entity entity = packet.resolveEntity(this.worldClient);
      if (entity != null) {
         entity.applyNBTTagCompound4(packet.getNBTTagCompound());
      }
   }

   @Override
   public void applyS41PacketServerDifficulty(S41PacketServerDifficulty packet) {
      ClientManager.handlePacket2(packet, this, this.mcClient);
      this.mcClient.theWorld.getRandomSeedManager().setGameSettingsPeacefulType2(packet.getGameSettingsPeacefulType());
      this.mcClient.theWorld.getRandomSeedManager().setEnabled(packet.isEnabled());
   }

   @Override
   public void applyS3FPacketCustomPayload(S3FPacketCustomPayload packet) {
      ClientManager.handlePacket2(packet, this, this.mcClient);
      if ("MC|TrList".equals(packet.getLabelText())) {
         PacketBuffer packetBuffer = packet.getPacketBuffer();

         try {
            int count = packetBuffer.readInt();
            GuiScreen currentScreen = this.mcClient.currentScreen;
            if (currentScreen != null && currentScreen instanceof PrimaryLoggerEntry && count == this.mcClient.thePlayer.container2.indexOffset2) {
               LocalListener localListener = ((PrimaryLoggerEntry)currentScreen).getLocalListener();
               AuxiliaryArrayListAdapter auxiliaryArrayListAdapter = AuxiliaryArrayListAdapter.resolveAuxiliaryArrayListAdapter(packetBuffer);
               localListener.setAuxiliaryArrayListAdapter(auxiliaryArrayListAdapter);
            }
         } catch (IOException ioException) {
            logger.error("Couldn't load trade info", ioException);
         } finally {
            packetBuffer.release();
         }
      } else if ("MC|Brand".equals(packet.getLabelText())) {
         this.mcClient.thePlayer.setText2(packet.getPacketBuffer().resolveTextByIndex(32767));
      } else if ("MC|BOpen".equals(packet.getLabelText())) {
         ItemStack stack = this.mcClient.thePlayer.getHeldItem();
         if (stack != null && stack.getItem() == Items.writtenBook) {
            Minecraft mcClient2 = this.mcClient;
            SpecialGuiScreenAdapter specialGuiScreenAdapter = new SpecialGuiScreenAdapter(this.mcClient.thePlayer, stack, false);
            mcClient2.displayGuiScreen(specialGuiScreenAdapter);
         }
      }
   }

   @Override
   public void applyS19PacketEntityHeadLook(S19PacketEntityHeadLook packet) {
      ClientManager.handlePacket2(packet, this, this.mcClient);
      Entity entity = packet.resolveEntity(this.worldClient);
      if (entity != null) {
         float factor = (float)(packet.getByteFlag() * 360) / 256.0F;
         entity.setScaleFactor12(factor);
         if (entity instanceof EntityPlayerSP
            && ((Module)ClientGlobals.moduleManager.debug).isToggled()
            && ClientGlobals.moduleManager.debug.booleanSetting13 != null
            && ClientGlobals.moduleManager.debug.booleanSetting13.isEnabled()) {
            RayTraceUtil.applyString("WARNING!");
         }
      }
   }

   @Override
   public void applyS03PacketTimeUpdate(S03PacketTimeUpdate packet) {
      ClientManager.handlePacket2(packet, this, this.mcClient);
      this.mcClient.theWorld.applyTimeByTime(packet.getTimestamp());
      this.mcClient.theWorld.applyTime(packet.getTimestamp2());
   }

   @Override
   public void applyS2BPacketChangeGameState(S2BPacketChangeGameState packet) {
      ClientManager.handlePacket2(packet, this, this.mcClient);
      EntityPlayerSP player = this.mcClient.thePlayer;
      int index = packet.getIndex();
      float scaleFactor = packet.getScaleFactor();
      int count = MathHelper.floor_float(scaleFactor + 0.5F);
      if (index >= 0 && index < S2BPacketChangeGameState.stringArray.length && S2BPacketChangeGameState.stringArray[index] != null) {
         player.applyIChatComponentByIChatComponent(new BaseManagerStyleIChatComponentAdapter(S2BPacketChangeGameState.stringArray[index], new Object[0]));
      }

      if (index == 1) {
         this.worldClient.getRandomSeedManager().setEnabled4(true);
         this.worldClient.updateFloat(0.0F);
      } else if (index == 2) {
         this.worldClient.getRandomSeedManager().setEnabled4(false);
         this.worldClient.updateFloat(1.0F);
      } else if (index == 3) {
         this.mcClient.playerController.applyWorldSettingsGameType(WorldSettingsGameType.resolveWorldSettingsGameType(count));
      } else if (index == 4) {
         Minecraft mcClient2 = this.mcClient;
         ManagedGuiScreenAdapter managedGuiScreenAdapter = new ManagedGuiScreenAdapter();
         mcClient2.displayGuiScreen(managedGuiScreenAdapter);
      } else if (index == 5) {
         GameSettings gameSettings = this.mcClient.gameSettings;
         if (scaleFactor == 0.0F) {
            Minecraft mcClient3 = this.mcClient;
            ExtraGuiScreenAdapter extraGuiScreenAdapter = new ExtraGuiScreenAdapter();
            mcClient3.displayGuiScreen(extraGuiScreenAdapter);
         } else if (scaleFactor == 101.0F) {
            this.mcClient
               .ingameGUI
               .getInternalLoggerEntry()
               .applyIChatComponent(
                  new BaseManagerStyleIChatComponentAdapter(
                     "demo.help.movement",
                     new Object[]{
                        GameSettings.resolveText(gameSettings.keyBindForward.getKeyCode()),
                        GameSettings.resolveText(gameSettings.keyBindLeft.getKeyCode()),
                        GameSettings.resolveText(gameSettings.keyBindBack.getKeyCode()),
                        GameSettings.resolveText(gameSettings.keyBindRight.getKeyCode())
                     }
                  )
               );
         } else if (scaleFactor == 102.0F) {
            this.mcClient
               .ingameGUI
               .getInternalLoggerEntry()
               .applyIChatComponent(
                  new BaseManagerStyleIChatComponentAdapter("demo.help.jump", new Object[]{GameSettings.resolveText(gameSettings.keyBindJump.getKeyCode())})
               );
         } else if (scaleFactor == 103.0F) {
            this.mcClient
               .ingameGUI
               .getInternalLoggerEntry()
               .applyIChatComponent(
                  new BaseManagerStyleIChatComponentAdapter("demo.help.inventory", new Object[]{GameSettings.resolveText(gameSettings.keyBindInventory.getKeyCode())})
               );
         }
      } else if (index == 6) {
         this.worldClient.applyText(player.posX, player.posY + (double)player.getHeight2(), player.posZ, "random.successful_hit", 0.18F, 0.45F, false);
      } else if (index == 7) {
         this.worldClient.updateFloat(scaleFactor);
      } else if (index == 8) {
         this.worldClient.updateFloat2(scaleFactor);
      } else if (index == 10) {
         this.worldClient
            .applyAttackEffectsExplosionNorm(AttackEffectsExplosionNormalType.MOB_APPEARANCE, player.posX, player.posY, player.posZ, 0.0, 0.0, 0.0, new int[0]);
         this.worldClient.applyText(player.posX, player.posY, player.posZ, "mob.guardian.curse", 1.0F, 1.0F, false);
      }
   }

   @Override
   public void applyS09PacketHeldItemChange(S09PacketHeldItemChange packet) {
      ClientManager.handlePacket2(packet, this, this.mcClient);
      if (packet.getCount() >= 0 && packet.getCount() < InventoryPlayer.processAction6()) {
         this.mcClient.thePlayer.inventoryPlayer.currentItem = packet.getCount();
         SlotManager.indexOffset = packet.getCount();
      }
   }

   @Override
   public void applyS0CPacketSpawnPlayer(S0CPacketSpawnPlayer packet) {
      ClientManager.handlePacket2(packet, this, this.mcClient);
      double offset = (double)packet.getCount() / 32.0;
      double offset2 = (double)packet.getCount2() / 32.0;
      double offset3 = (double)packet.getCount3() / 32.0;
      float factor = (float)(packet.getByteFlag() * 360) / 256.0F;
      float factor2 = (float)(packet.getByteFlag2() * 360) / 256.0F;
      EntityOtherPlayerMP player = new EntityOtherPlayerMP(this.mcClient.theWorld, this.getNetworkPlayerInfo(packet.getUUID()).getGameProfile());
      player.prevPosX = player.lastTickPosX = (double)(player.indexOffset2 = packet.getCount());
      player.prevPosY = player.lastTickPosY = (double)(player.indexOffset3 = packet.getCount2());
      player.prevPosZ = player.lastTickPosZ = (double)(player.indexOffset = packet.getCount3());
      player.applyIndex(packet.getCount(), packet.getCount2(), packet.getCount3());
      int count = packet.getCount5();
      if (count == 0) {
         player.inventoryPlayer.mainInventory[player.inventoryPlayer.currentItem] = null;
      } else {
         player.inventoryPlayer.mainInventory[player.inventoryPlayer.currentItem] = new ItemStack(Item.processAction(count), 1, 0);
      }

      player.setLocationAndAngles(offset, offset2, offset3, factor, factor2);
      if (packet.getCount4() == Minecraft.getMinecraft().thePlayer.getEntityId()) {
         Client.getClient().getViaProtocolHelper().applyEntityPlayer(player, false);
      }

      this.worldClient.applyEntity10(packet.getCount4(), player);
      List list = packet.getList();
      if (list != null) {
         player.getDataWatcher().applyList(list);
      }
   }

   @Override
   public void applyS29PacketSoundEffect(S29PacketSoundEffect packet) {
      ClientManager.handlePacket2(packet, this, this.mcClient);
      this.mcClient
         .theWorld
         .applyText(packet.getDouble(), packet.getDouble2(), packet.getDouble3(), packet.getText(), packet.getFactor(), packet.getFactorAsFloat(), false);
   }

   @Override
   public void applyS1CPacketEntityMetadata(S1CPacketEntityMetadata packet) {
      ClientManager.handlePacket2(packet, this, this.mcClient);
      Entity entityByID = this.worldClient.getEntityByID(packet.getCount());
      if (entityByID != null && packet.getList() != null) {
         entityByID.getDataWatcher().applyList(packet.getList());
      }
   }

   @Override
   public void applyS25PacketBlockBreakAnim(S25PacketBlockBreakAnim packet) {
      ClientManager.handlePacket2(packet, this, this.mcClient);
      this.mcClient.theWorld.applyBlockPos(packet.getCount(), packet.getBlockPos(), packet.getCount2());
   }

   @Override
   public void applyS1EPacketRemoveEntityEffec(S1EPacketRemoveEntityEffect packet) {
      ClientManager.handlePacket2(packet, this, this.mcClient);
      Entity entityByID = this.worldClient.getEntityByID(packet.getCount2());
      if (entityByID instanceof EntityLivingBase) {
         ((EntityLivingBase)entityByID).applyIndex7(packet.getCount());
      }
   }

   @Override
   public void applyS18PacketEntityTeleport(S18PacketEntityTeleport packet) {
      ClientManager.handlePacket2(packet, this, this.mcClient);
      Entity entityByID = this.worldClient.getEntityByID(packet.getIndex());
      if (entityByID != null) {
         entityByID.indexOffset2 = packet.getCount3();
         entityByID.indexOffset3 = packet.getCount2();
         entityByID.indexOffset = packet.getCount();
         double offset = (double)entityByID.indexOffset2 / 32.0;
         double offset2 = (double)entityByID.indexOffset3 / 32.0;
         double offset3 = (double)entityByID.indexOffset / 32.0;
         float factor = (float)(packet.getByteFlag() * 360) / 256.0F;
         float factor2 = (float)(packet.getByteFlag2() * 360) / 256.0F;
         if (entityByID instanceof EntityPlayerSP
            && ((Module)ClientGlobals.moduleManager.debug).isToggled()
            && ClientGlobals.moduleManager.debug.booleanSetting13 != null
            && ClientGlobals.moduleManager.debug.booleanSetting13.isEnabled()) {
            RayTraceUtil.applyString("WARNING!");
         }

         if (Math.abs(entityByID.posX - offset) < 0.03125 && Math.abs(entityByID.posY - offset2) < 0.015625 && Math.abs(entityByID.posZ - offset3) < 0.03125) {
            entityByID.applyDouble(entityByID.posX, entityByID.posY, entityByID.posZ, factor, factor2, 3, true);
         } else {
            entityByID.applyDouble(offset, offset2, offset3, factor, factor2, 3, true);
         }

         entityByID.onGround = packet.isEnabled();
      }
   }

   public static NetworkManager resolveNetworkManager(NetHandlerPlayClient netHandlerPlayClient) {
      return netHandlerPlayClient.networkManager;
   }

   @Override
   public void applyS19PacketEntityStatus(S19PacketEntityStatus packet) {
      ClientManager.handlePacket2(packet, this, this.mcClient);
      Entity entity = packet.getEntity(this.worldClient);
      if (entity != null) {
         if (packet.processValue() == 21) {
            this.mcClient.getMcSoundHandler().applyISound2(new TypeMAdapter((EntityGuardian)entity));
         } else {
            entity.processByte(packet.processValue());
         }
      }
   }

   @Override
   public void applyS3EPacketTeams(S3EPacketTeams packet) {
      ClientManager.handlePacket2(packet, this, this.mcClient);
      ThePlayerNameManager thePlayerNameManager = this.worldClient.getThePlayerNameManager();
      ScorePlayerTeam scorePlayerTeam;
      if (packet.getCount2() == 0) {
         scorePlayerTeam = thePlayerNameManager.resolveScorePlayerTeam(packet.getLabelText5());
      } else {
         scorePlayerTeam = thePlayerNameManager.getByText(packet.getLabelText5());
      }

      if (packet.getCount2() == 0 || packet.getCount2() == 2) {
         scorePlayerTeam.applyText2(packet.getLabelText2());
         scorePlayerTeam.applyText(packet.getLabelText());
         scorePlayerTeam.applyTextByText(packet.getLabelText4());
         scorePlayerTeam.setClientSettingsBlackType(ClientSettingsBlackType.resolveClientSettingsBlackType(packet.getCount()));
         scorePlayerTeam.applyIndex(packet.getCount3());
         MovementBooleanProperty movementBooleanProperty = MovementBooleanProperty.get(packet.getLabelText3());
         if (movementBooleanProperty != null) {
            scorePlayerTeam.applyMovementBooleanPropertyByMovementBooleanP(movementBooleanProperty);
         }
      }

      if (packet.getCount2() == 0 || packet.getCount2() == 3) {
         for (String text : (Iterable<String>)(packet.getCollection())) {
            thePlayerNameManager.matchesText(text, packet.getLabelText5());
         }
      }

      if (packet.getCount2() == 4) {
         for (String text2 : (Iterable<String>)(packet.getCollection())) {
            thePlayerNameManager.applyText(text2, scorePlayerTeam);
         }
      }

      if (packet.getCount2() == 1) {
         thePlayerNameManager.applyScorePlayerTeam(scorePlayerTeam);
      }
   }

   @Override
   public void applyS05PacketSpawnPosition(S05PacketSpawnPosition packet) {
      ClientManager.handlePacket2(packet, this, this.mcClient);
      this.mcClient.thePlayer.applyBlockPos(packet.getBlockPos(), true);
      this.mcClient.theWorld.getRandomSeedManager().applyBlockPos(packet.getBlockPos());
   }

   @Override
   public void applyS0APacketUseBed(S0APacketUseBed packet) {
      ClientManager.handlePacket2(packet, this, this.mcClient);
      packet.resolveEntityPlayer(this.worldClient).resolveSplineSorter(packet.getBlockPos());
   }

   private void applyDefaultGuiScreenAdapter(DefaultGuiScreenAdapter defaultGuiScreenAdapter) {
      this.mcClient.displayGuiScreen(defaultGuiScreenAdapter);
   }

   @Override
   public void handleS12PacketEntityVelocity(S12PacketEntityVelocity packet) {
      ClientManager.handlePacket2(packet, this, this.mcClient);
      Entity entityByID = this.worldClient.getEntityByID(packet.getEntityId());
      if (entityByID != null) {
         double offset = packet.isOnGround1() ? (double)packet.getEntityId3() / 8000.0 : entityByID.motionX;
         double offset2 = packet.isOnGround() ? (double)packet.getEntityId2() / 8000.0 : entityByID.motionY;
         double offset3 = packet.isOnGround1() ? (double)packet.getEntityId4() / 8000.0 : entityByID.motionZ;
         entityByID.applyDouble2(offset, offset2, offset3);
      }
   }

   @Override
   public void applyS26PacketMapChunkBulk(S26PacketMapChunkBulk packet) {
      ClientManager.handlePacket2(packet, this, this.mcClient);

      for (int index = 0; index < packet.getCount(); index++) {
         int count = packet.computeCountByIndex(index);
         int count2 = packet.computeCount(index);
         this.worldClient.applyIndex(count, count2, true);
         this.worldClient.applyIndexByIndex(count << 4, 0, count2 << 4, (count << 4) + 15, 256, (count2 << 4) + 15);
         AlertNotificationSound loggerEntry = this.worldClient.getLoggerEntry2(count, count2);
         loggerEntry.applyByteArray(packet.resolveByteArray(index), packet.computeCount2(index), true);
         this.worldClient.applyIndex(count << 4, 0, count2 << 4, (count << 4) + 15, 256, (count2 << 4) + 15);
         if (!(this.worldClient.secondaryFloatArrayEntry instanceof OverworldManager)) {
            loggerEntry.updateState();
         }

         WindowActionListener.applyIndexByIndex(count, count2);
      }
   }

   @Override
   public void processAction(S08PacketPlayerPosLook packet) {
      ClientManager.handlePacket2(packet, this, this.mcClient);
      EntityPlayerSP player = this.mcClient.thePlayer;
      double coordinate = packet.getCoordinate();
      double coordinate2 = packet.getCoordinate1();
      double coordinate3 = packet.getCoordinate4();
      float yawRotation = packet.getYawRotation2();
      float yawRotation2 = packet.getYawRotation3();
      Vec3 vec = new Vec3(minecraft.thePlayer.motionX, minecraft.thePlayer.motionY, minecraft.thePlayer.motionZ);
      double offset = 0.0;
      double offset2 = 0.0;
      double offset3 = 0.0;
      double offset4 = 0.0;
      double offset5 = 0.0;
      double offset6 = 0.0;
      boolean flag = ViaLoadingBase.protocolVersion.newerThanOrEqualTo(ProtocolVersion.v1_17);
      if (packet.getEntrySet().contains(NetHandlerPlayClientXType.X)) {
         if (flag) {
            offset = vec.xCoord;
            offset4 = player.posX + coordinate3;
            player.lastTickPosX += coordinate3;
            player.prevPosX += coordinate3;
         } else {
            coordinate += player.posX;
         }
      } else if (flag) {
         offset = 0.0;
         offset4 = coordinate3;
         player.lastTickPosX = coordinate3;
         player.prevPosX = coordinate3;
      } else {
         player.motionX = 0.0;
      }

      if (packet.getEntrySet().contains(NetHandlerPlayClientXType.Y)) {
         if (flag) {
            offset2 = vec.yCoord;
            offset5 = player.posY + coordinate2;
            player.lastTickPosY += coordinate2;
            player.prevPosY += coordinate2;
         } else {
            coordinate2 += player.posY;
         }
      } else if (flag) {
         offset2 = 0.0;
         offset5 = coordinate2;
         player.lastTickPosY = coordinate2;
         player.prevPosY = coordinate2;
      } else {
         player.motionY = 0.0;
      }

      if (packet.getEntrySet().contains(NetHandlerPlayClientXType.Z)) {
         if (flag) {
            offset3 = vec.zCoord;
            offset6 = player.posZ + coordinate;
            player.lastTickPosZ += coordinate;
            player.prevPosZ += coordinate;
         } else {
            coordinate3 += player.posZ;
         }
      } else if (flag) {
         offset3 = 0.0;
         offset6 = coordinate;
         player.lastTickPosZ = coordinate;
         player.prevPosZ = coordinate;
      } else {
         player.motionZ = 0.0;
      }

      if (flag) {
         player.processDouble2(offset4, offset5, offset6);
         player.motionX = offset;
         player.motionY = offset2;
         player.motionZ = offset3;
      }

      if (packet.getEntrySet().contains(NetHandlerPlayClientXType.X_ROT)) {
         if (flag) {
            player.rotationPitch += yawRotation2;
            player.prevRotationPitch = player.rotationPitch;
         } else {
            yawRotation2 += Client.getClient().getRotationManager().hasRotations()
               ? player.rotationPitch
               : Client.getClient().getRotationManager().getRotation2().pitch;
         }
      } else if (flag) {
         player.rotationPitch = yawRotation2;
         player.prevRotationPitch = player.rotationPitch;
      }

      if (packet.getEntrySet().contains(NetHandlerPlayClientXType.Y_ROT)) {
         if (flag) {
            player.rotationYaw += yawRotation;
            player.prevRotationYaw = player.rotationYaw;
         } else {
            yawRotation += Client.getClient().getRotationManager().hasRotations() ? player.rotationYaw : Client.getClient().getRotationManager().getRotation2().yaw;
         }
      } else if (flag) {
         player.rotationYaw = yawRotation;
         player.prevRotationYaw = player.rotationYaw;
      }

      player.setLocationAndAngles(coordinate, coordinate2, coordinate3, yawRotation, yawRotation2);
      if (!((Module)Client.getClient().getModuleManager().noRotationSet).isToggled()
         || !Client.getClient().getModuleManager().noRotationSet.settingMode.getSelected().equalsIgnoreCase("Default")) {
         ViaProtocolHelper viaProtocolHelper = Client.getClient().getViaProtocolHelper();
         flag = NoRotationSet.checkS08PacketPlayerPosLook(packet);
         viaProtocolHelper.applyEntityPlayer(player, flag);
      }

      this.networkManager
         .handlePacket6(
            new C06PacketPlayerPosLook(
               player.posX, player.getBoundingBox2().minY + (double)packet.getYawRotation(), player.posZ, player.rotationYaw, player.rotationPitch, false
            )
         );
      if (!this.state) {
         this.mcClient.thePlayer.prevPosX = this.mcClient.thePlayer.posX;
         this.mcClient.thePlayer.prevPosY = this.mcClient.thePlayer.posY;
         this.mcClient.thePlayer.prevPosZ = this.mcClient.thePlayer.posZ;
         this.state = true;
         GuiScreen screen = null;
         this.mcClient.displayGuiScreen(screen);
      }
   }

   @Override
   public void applyS24PacketBlockAction(S24PacketBlockAction packet) {
      ClientManager.handlePacket2(packet, this, this.mcClient);
      this.mcClient.theWorld.applyBlockPosByBlockPos(packet.getBlockPos(), packet.getBlock(), packet.getCount2(), packet.getCount());
      SecondaryItemEntry.applyNetHandlerPlayClient(this, packet);
   }

   @Override
   public void handleS02PacketChat(S02PacketChat packet) {
      ClientManager.handlePacket2(packet, this, this.mcClient);
      if (packet.getByteIndex() == 2) {
         this.mcClient.ingameGUI.applyIChatComponent(packet.getChatComponent(), false);
      } else {
         this.mcClient.ingameGUI.getInternalLoggerEntry().applyIChatComponent(packet.getChatComponent());
      }

      SecondaryItemEntry.applyNetHandlerPlayClient(this, packet);
   }

   public NetworkPlayerInfo resolveNetworkPlayerInfo(String text) {
      for (NetworkPlayerInfo networkPlayerInfo : (Iterable<NetworkPlayerInfo>)(this.entryMap.values())) {
         if (networkPlayerInfo.getGameProfile().getName().equals(text)) {
            return networkPlayerInfo;
         }
      }

      return null;
   }

   @Override
   public void applyS46PacketSetCompressionLev(S46PacketSetCompressionLevel packet) {
      if (!this.networkManager.isActive()) {
         this.networkManager.applyIndex(packet.getCount());
      }
   }

   private static DepthFirstRegistry resolveDepthFirstRegistry(ShortEntry shortEntry) {
      return new DepthFirstRegistry(shortEntry.getBlockPos(), shortEntry.getIBlockState());
   }

   @Override
   public void applyS0EPacketSpawnObject(S0EPacketSpawnObject packet) {
      ClientManager.handlePacket2(packet, this, this.mcClient);
      double offset = (double)packet.getCount4() / 32.0;
      double offset2 = (double)packet.getCount6() / 32.0;
      double offset3 = (double)packet.getCount5() / 32.0;
      Object entry = null;
      if (packet.getCount3() == 10) {
         entry = EntityMinecart.resolveEntityMinecart(this.worldClient, offset, offset2, offset3, MovementNumberProperty.resolveMovementNumberProperty(packet.getCount()));
      } else if (packet.getCount3() == 90) {
         Entity entityByID = this.worldClient.getEntityByID(packet.getCount());
         if (entityByID instanceof EntityPlayer) {
            entry = new EntityFishHook(this.worldClient, offset, offset2, offset3, (EntityPlayer)entityByID);
         }

         packet.setCount(0);
      } else if (packet.getCount3() == 60) {
         entry = new EntityArrow(this.worldClient, offset, offset2, offset3);
      } else if (packet.getCount3() == 61) {
         entry = new EntitySnowball(this.worldClient, offset, offset2, offset3);
      } else if (packet.getCount3() == 71) {
         entry = new EntityItemframe(
            this.worldClient,
            new BlockPos(MathHelper.ceiling_double_int(offset), MathHelper.ceiling_double_int(offset2), MathHelper.ceiling_double_int(offset3)),
            EnumFacing.resolveEnumFacing(packet.getCount())
         );
         packet.setCount(0);
      } else if (packet.getCount3() == 77) {
         entry = new EntityLeashknot(
            this.worldClient, new BlockPos(MathHelper.ceiling_double_int(offset), MathHelper.ceiling_double_int(offset2), MathHelper.ceiling_double_int(offset3))
         );
         packet.setCount(0);
      } else if (packet.getCount3() == 65) {
         entry = new EntityThrownenderpearl(this.worldClient, offset, offset2, offset3);
      } else if (packet.getCount3() == 72) {
         entry = new EntityEyeofendersignal(this.worldClient, offset, offset2, offset3);
      } else if (packet.getCount3() == 76) {
         entry = new EntityFireworksrocketentity(this.worldClient, offset, offset2, offset3, (ItemStack)null);
      } else if (packet.getCount3() == 63) {
         entry = new EntityFireball(
            this.worldClient, offset, offset2, offset3, (double)packet.getCount7() / 8000.0, (double)packet.getCount8() / 8000.0, (double)packet.getCount10() / 8000.0
         );
         packet.setCount(0);
      } else if (packet.getCount3() == 64) {
         entry = new EntitySmallfireball(
            this.worldClient, offset, offset2, offset3, (double)packet.getCount7() / 8000.0, (double)packet.getCount8() / 8000.0, (double)packet.getCount10() / 8000.0
         );
         packet.setCount(0);
      } else if (packet.getCount3() == 66) {
         entry = new EntityWitherskull(
            this.worldClient, offset, offset2, offset3, (double)packet.getCount7() / 8000.0, (double)packet.getCount8() / 8000.0, (double)packet.getCount10() / 8000.0
         );
         packet.setCount(0);
      } else if (packet.getCount3() == 62) {
         entry = new EntityThrownegg(this.worldClient, offset, offset2, offset3);
      } else if (packet.getCount3() == 73) {
         entry = new EntityThrownpotion(this.worldClient, offset, offset2, offset3, packet.getCount());
         packet.setCount(0);
      } else if (packet.getCount3() == 75) {
         entry = new EntityThrownexpbottle(this.worldClient, offset, offset2, offset3);
         packet.setCount(0);
      } else if (packet.getCount3() == 1) {
         entry = new EntityBoat(this.worldClient, offset, offset2, offset3);
      } else if (packet.getCount3() == 50) {
         entry = new EntityPrimedtnt(this.worldClient, offset, offset2, offset3, (EntityLivingBase)null);
      } else if (packet.getCount3() == 78) {
         entry = new EntityArmorstand(this.worldClient, offset, offset2, offset3);
      } else if (packet.getCount3() == 51) {
         entry = new EntityEndercrystal(this.worldClient, offset, offset2, offset3);
      } else if (packet.getCount3() == 2) {
         entry = new EntityItem(this.worldClient, offset, offset2, offset3);
      } else if (packet.getCount3() == 70) {
         entry = new EntityFallingsand(this.worldClient, offset, offset2, offset3, Block.processAction2(packet.getCount() & 65535));
         packet.setCount(0);
      }

      if (entry != null) {
         ((Entity)entry).indexOffset2 = packet.getCount4();
         ((Entity)entry).indexOffset3 = packet.getCount6();
         ((Entity)entry).indexOffset = packet.getCount5();
         ((Entity)entry).rotationPitch = (float)(packet.getCount11() * 360) / 256.0F;
         ((Entity)entry).rotationYaw = (float)(packet.getCount2() * 360) / 256.0F;
         Entity[] entityArray = ((Entity)entry).getEntityArray();
         if (entityArray != null) {
            int count = packet.getCount9() - ((Entity)entry).getEntityId();

            for (int index = 0; index < entityArray.length; index++) {
               entityArray[index].setEntityId(entityArray[index].getEntityId() + count);
            }
         }

         ((Entity)entry).setEntityId(packet.getCount9());
         this.worldClient.applyEntity10(packet.getCount9(), (Entity)entry);
         if (packet.getCount() > 0) {
            if (packet.getCount3() == 60) {
               Entity entityByID2 = this.worldClient.getEntityByID(packet.getCount());
               if (entityByID2 instanceof EntityLivingBase && entry instanceof EntityArrow) {
                  ((EntityArrow)entry).entity2 = entityByID2;
               }
            }

            ((Entity)entry).applyDouble2((double)packet.getCount7() / 8000.0, (double)packet.getCount8() / 8000.0, (double)packet.getCount10() / 8000.0);
         }
      }
   }

   @Override
   public void applyS2APacketParticles(S2APacketParticles packet) {
      ClientManager.handlePacket2(packet, this, this.mcClient);
      if (packet.getIndex() == 0) {
         double offset = (double)(packet.getScaleFactor() * packet.getFactor2());
         double offset2 = (double)(packet.getScaleFactor() * packet.getFactor3());
         double offset3 = (double)(packet.getScaleFactor() * packet.getFactor4());

         try {
            this.worldClient
               .applyAttackEffectsExplosionNorm(
                  packet.getAttackEffectsExplosionNormalType(),
                  packet.isEnabled(),
                  packet.getValueAsDouble(),
                  packet.getValue2(),
                  packet.getValue(),
                  offset,
                  offset2,
                  offset3,
                  packet.getCountArray()
               );
         } catch (Throwable throwable) {
            logger.warn("Could not spawn particle effect " + packet.getAttackEffectsExplosionNormalType());
         }
      } else {
         for (int index = 0; index < packet.getIndex(); index++) {
            double offset4 = this.random.nextGaussian() * (double)packet.getFactor2();
            double offset5 = this.random.nextGaussian() * (double)packet.getFactor3();
            double offset6 = this.random.nextGaussian() * (double)packet.getFactor4();
            double offset7 = this.random.nextGaussian() * (double)packet.getScaleFactor();
            double offset8 = this.random.nextGaussian() * (double)packet.getScaleFactor();
            double offset9 = this.random.nextGaussian() * (double)packet.getScaleFactor();

            try {
               this.worldClient
                  .applyAttackEffectsExplosionNorm(
                     packet.getAttackEffectsExplosionNormalType(),
                     packet.isEnabled(),
                     packet.getValueAsDouble() + offset4,
                     packet.getValue2() + offset5,
                     packet.getValue() + offset6,
                     offset7,
                     offset8,
                     offset9,
                     packet.getCountArray()
                  );
            } catch (Throwable throwable2) {
               logger.warn("Could not spawn particle effect " + packet.getAttackEffectsExplosionNormalType());
               return;
            }
         }
      }
   }

   @Override
   public void applyS43PacketCamera(S43PacketCamera packet) {
      ClientManager.handlePacket2(packet, this, this.mcClient);
      Entity entity = packet.resolveEntity(this.worldClient);
      if (entity != null) {
         this.mcClient.applyEntityByEntity(entity);
      }
   }

   @Override
   public void applyS11PacketSpawnExperienceOr(S11PacketSpawnExperienceOrb packet) {
      ClientManager.handlePacket2(packet, this, this.mcClient);
      EntityXporb entityXporb = new EntityXporb(
         this.worldClient, (double)packet.getCount4() / 32.0, (double)packet.getCount5() / 32.0, (double)packet.getCount() / 32.0, packet.getCount3()
      );
      entityXporb.indexOffset2 = packet.getCount4();
      entityXporb.indexOffset3 = packet.getCount5();
      entityXporb.indexOffset = packet.getCount();
      entityXporb.rotationYaw = 0.0F;
      entityXporb.rotationPitch = 0.0F;
      entityXporb.setEntityId(packet.getCount2());
      this.worldClient.applyEntity10(packet.getCount2(), entityXporb);
   }

   @Override
   public void applyS31PacketWindowProperty(S31PacketWindowProperty packet) {
      ClientManager.handlePacket2(packet, this, this.mcClient);
      EntityPlayerSP player = this.mcClient.thePlayer;
      if (player.container2 != null && player.container2.indexOffset2 == packet.getCount2()) {
         player.container2.processAction2(packet.getCount3(), packet.getCount());
      }
   }

   @Override
   public void applyS21PacketChunkData(S21PacketChunkData packet) {
      ClientManager.handlePacket2(packet, this, this.mcClient);
      if (packet.isEnabled()) {
         if (packet.getCount() == 0) {
            this.worldClient.applyIndex(packet.getCount2(), packet.getCountAsIndex(), false);
            return;
         }

         this.worldClient.applyIndex(packet.getCount2(), packet.getCountAsIndex(), true);
      }

      this.worldClient
         .applyIndexByIndex(packet.getCount2() << 4, 0, packet.getCountAsIndex() << 4, (packet.getCount2() << 4) + 15, 256, (packet.getCountAsIndex() << 4) + 15);
      AlertNotificationSound loggerEntry = this.worldClient.getLoggerEntry2(packet.getCount2(), packet.getCountAsIndex());
      if (((Module)Client.getClient().getModuleManager().baritone).isToggled()) {
         ActiveListener activeListener = SettingsTxtManager.getExtraListener().resolveActiveListener(this);
         if (activeListener != null) {
            activeListener.getCompositeListener()
               .applyAStarResolver(
                  new AStarResolver(
                     SecondaryOptionType.PRE,
                     packet.isEnabled() ? BreadthFirstService.POPULATE_FULL : BreadthFirstService.POPULATE_PARTIAL,
                     packet.getCount2(),
                     packet.getCountAsIndex()
                  )
               );
         }
      }

      loggerEntry.applyByteArray(packet.getByteArray(), packet.getCount(), packet.isEnabled());
      this.worldClient.applyIndex(packet.getCount2() << 4, 0, packet.getCountAsIndex() << 4, (packet.getCount2() << 4) + 15, 256, (packet.getCountAsIndex() << 4) + 15);
      if (!packet.isEnabled() || !(this.worldClient.secondaryFloatArrayEntry instanceof OverworldManager)) {
         loggerEntry.updateState();
      }

      if (((Module)Client.getClient().getModuleManager().baritone).isToggled()) {
         ActiveListener activeListener2 = SettingsTxtManager.getExtraListener().resolveActiveListener(this);
         if (activeListener2 != null) {
            activeListener2.getCompositeListener()
               .applyAStarResolver(
                  new AStarResolver(
                     SecondaryOptionType.POST,
                     packet.isEnabled() ? BreadthFirstService.POPULATE_FULL : BreadthFirstService.POPULATE_PARTIAL,
                     packet.getCount2(),
                     packet.getCountAsIndex()
                  )
               );
         }
      }

      WindowActionListener.applyIndexByIndex(packet.getCount2(), packet.getCountAsIndex());
   }

   @Override
   public void applyS3DPacketDisplayScoreboard(S3DPacketDisplayScoreboard packet) {
      ClientManager.handlePacket2(packet, this, this.mcClient);
      ThePlayerNameManager thePlayerNameManager = this.worldClient.getThePlayerNameManager();
      if (packet.getLabelText().length() == 0) {
         thePlayerNameManager.applyScoreboardData(packet.getCount(), (ScoreboardData)null);
      } else {
         ScoreboardData scoreboardData = thePlayerNameManager.get(packet.getLabelText());
         thePlayerNameManager.applyScoreboardData(packet.getCount(), scoreboardData);
      }
   }

   @Override
   public void applyS34PacketMaps(S34PacketMaps packet) {
      ClientManager.handlePacket2(packet, this, this.mcClient);
      DimensionStringEntry dimensionStringEntry = ItemFilledMap.resolveDimensionStringEntry(packet.getCount4(), this.mcClient.theWorld);
      packet.applyDimensionStringEntry(dimensionStringEntry);
      this.mcClient.entityRenderer.getTexturesMapIconsManager().applyDimensionStringEntry(dimensionStringEntry);
      SecondaryItemEntry.applyNetHandlerPlayClient(this, packet);
   }

   @Override
   public void applyS39PacketPlayerAbilities(S39PacketPlayerAbilities packet) {
      ClientManager.handlePacket2(packet, this, this.mcClient);
      EntityPlayerSP player = this.mcClient.thePlayer;
      player.invulnerableManager.state = packet.isEnabled();
      player.invulnerableManager.enabled2 = packet.isEnabled3();
      player.invulnerableManager.enabled = packet.isEnabled4();
      player.invulnerableManager.state2 = packet.isEnabled2();
      player.invulnerableManager.setFactor(packet.getFactor());
      player.invulnerableManager.setFactor2(packet.getFactor2());
   }

   @Override
   public void applyS35PacketUpdateTileEntity(S35PacketUpdateTileEntity packet) {
      ClientManager.handlePacket2(packet, this, this.mcClient);
      if (this.mcClient.theWorld.checkBlockPos12(packet.getBlockPos())) {
         TileEntity tileEntity = this.mcClient.theWorld.resolveTileEntity(packet.getBlockPos());
         int count = packet.getCount();
         if (count == 1 && tileEntity instanceof TileEntityMobspawner
            || count == 2 && tileEntity instanceof TileEntityControl
            || count == 3 && tileEntity instanceof TileEntityBeacon
            || count == 4 && tileEntity instanceof TileEntitySkull
            || count == 5 && tileEntity instanceof TileEntityFlowerpot
            || count == 6 && tileEntity instanceof TileEntityBanner) {
            tileEntity.applyNBTTagCompoundByNBTTagCompound(packet.getNBTTagCompound());
         }
      }
   }

   @Override
   public void applyS00PacketKeepAlive(S00PacketKeepAlive packet) {
      this.handlePacket(new C00PacketKeepAlive(packet.getCount()));
   }

   @Override
   public void applyS3BPacketScoreboardObjecti(S3BPacketScoreboardObjective packet) {
      ClientManager.handlePacket2(packet, this, this.mcClient);
      ThePlayerNameManager thePlayerNameManager = this.worldClient.getThePlayerNameManager();
      if (packet.getCount() == 0) {
         ScoreboardData scoreboardData = thePlayerNameManager.resolveScoreboardData(packet.getLabelText(), HealthHandler.healthHandler3);
         scoreboardData.applyText(packet.getLabelText2());
         scoreboardData.applyTabListIntegerType(packet.getTabListIntegerType());
      } else {
         ScoreboardData scoreboardData2 = thePlayerNameManager.get(packet.getLabelText());
         if (packet.getCount() == 1) {
            thePlayerNameManager.applyScoreboardData3(scoreboardData2);
         } else if (packet.getCount() == 2) {
            scoreboardData2.applyText(packet.getLabelText2());
            scoreboardData2.applyTabListIntegerType(packet.getTabListIntegerType());
         }
      }
   }

   @Override
   public void applyS44PacketWorldBorder(S44PacketWorldBorder packet) {
      ClientManager.handlePacket2(packet, this, this.mcClient);
      packet.applyWorldInfo(this.worldClient.getInternalListEntry());
   }

   @Override
   public void applyS23PacketBlockChange(S23PacketBlockChange packet) {
      ClientManager.handlePacket2(packet, this, this.mcClient);
      this.worldClient.matchesBlockPosByBlockPos(packet.getBlockPos(), packet.getIBlockState());
      if (((Module)Client.getClient().getModuleManager().baritone).isToggled()) {
         ActiveListener activeListener = SettingsTxtManager.getExtraListener().resolveActiveListener(this);
         if (activeListener == null) {
            return;
         }

         AStarVerifier aStarVerifier = new AStarVerifier(packet.getBlockPos().getX() >> 4, packet.getBlockPos().getZ() >> 4);
         DepthFirstRegistry depthFirstRegistry = new DepthFirstRegistry(packet.getBlockPos(), packet.getIBlockState());
         activeListener.getCompositeListener().applyBreadthFirstProvider(new BreadthFirstProvider(aStarVerifier, Collections.singletonList(depthFirstRegistry)));
      }
   }

   @Override
   public void applyS13PacketDestroyEntities(S13PacketDestroyEntities packet) {
      ClientManager.handlePacket2(packet, this, this.mcClient);

      for (int index = 0; index < packet.getIntValueArray().length; index++) {
         this.worldClient.resolveEntity2(packet.getIntValueArray()[index]);
      }
   }

   public Map getEntryMap2() {
      return this.entryMap2;
   }

   @Override
   public void applyS2EPacketCloseWindow(S2EPacketCloseWindow packet) {
      ClientManager.handlePacket2(packet, this, this.mcClient);
      this.mcClient.thePlayer.updateState47();
   }

   @Override
   public void applyS2CPacketSpawnGlobalEntity(S2CPacketSpawnGlobalEntity packet) {
      ClientManager.handlePacket2(packet, this, this.mcClient);
      double offset = (double)packet.getCount3() / 32.0;
      double offset2 = (double)packet.getCount() / 32.0;
      double offset3 = (double)packet.getCount5() / 32.0;
      HUIHRVManager hUIHRVManager = null;
      if (packet.getCount2() == 1) {
         hUIHRVManager = new HUIHRVManager(this.worldClient, offset, offset2, offset3);
      }

      if (hUIHRVManager != null) {
         hUIHRVManager.indexOffset2 = packet.getCount3();
         hUIHRVManager.indexOffset3 = packet.getCount();
         hUIHRVManager.indexOffset = packet.getCount5();
         hUIHRVManager.rotationYaw = 0.0F;
         hUIHRVManager.rotationPitch = 0.0F;
         hUIHRVManager.setEntityId(packet.getCount4());
         this.worldClient.checkNoEntityCollision(hUIHRVManager);
      }
   }

   @Override
   public void applyS1DPacketEntityEffect(S1DPacketEntityEffect packet) {
      ClientManager.handlePacket2(packet, this, this.mcClient);
      Entity entityByID = this.worldClient.getEntityByID(packet.getIndex());
      if (entityByID instanceof EntityLivingBase) {
         PotionEffect potionEffect = new PotionEffect(packet.getByteFlag3(), packet.getCount(), packet.getByteFlag(), false, packet.isActive());
         potionEffect.setState(packet.isActiveAsFlag());
         ((EntityLivingBase)entityByID).applyPotionEffectByPotionEffect(potionEffect);
      }
   }

   @Override
   public void applyS36PacketSignEditorOpen(S36PacketSignEditorOpen packet) {
      ClientManager.handlePacket2(packet, this, this.mcClient);
      Object entry = this.worldClient.resolveTileEntity(packet.getBlockPos());
      if (!(entry instanceof TileEntitySign)) {
         entry = new TileEntitySign();
         ((TileEntity)entry).setWorld(this.worldClient);
         ((TileEntity)entry).setBlockPos(packet.getBlockPos());
      }

      this.mcClient.thePlayer.applyTileEntitySign((TileEntitySign)entry);
   }

   @Override
   public void applyS0FPacketSpawnMob(S0FPacketSpawnMob packet) {
      ClientManager.handlePacket2(packet, this, this.mcClient);
      double offset = (double)packet.getCount2() / 32.0;
      double offset2 = (double)packet.getCount5() / 32.0;
      double offset3 = (double)packet.getCount6() / 32.0;
      float factor = (float)(packet.getByteFlag2() * 360) / 256.0F;
      float factor2 = (float)(packet.getByteFlag3() * 360) / 256.0F;
      EntityLivingBase livingEntity = (EntityLivingBase)SkippingEntityWithIdManager.resolveEntity(packet.getCount7(), this.mcClient.theWorld);
      livingEntity.indexOffset2 = packet.getCount2();
      livingEntity.indexOffset3 = packet.getCount5();
      livingEntity.indexOffset = packet.getCount6();
      livingEntity.applyIndex(packet.getCount2(), packet.getCount5(), packet.getCount6());
      livingEntity.scaleFactor12 = (float)(packet.getByteFlag() * 360) / 256.0F;
      Entity[] entityArray = livingEntity.getEntityArray();
      if (entityArray != null) {
         int count = packet.getCount3() - livingEntity.getEntityId();

         for (Entity entity : entityArray) {
            entity.setEntityId(entity.getEntityId() + count);
         }
      }

      livingEntity.setEntityId(packet.getCount3());
      livingEntity.setLocationAndAngles(offset, offset2, offset3, factor, factor2);
      livingEntity.motionX = (double)((float)packet.getCount() / 8000.0F);
      livingEntity.motionY = (double)((float)packet.getCount8() / 8000.0F);
      livingEntity.motionZ = (double)((float)packet.getCount4() / 8000.0F);
      this.worldClient.applyEntity10(packet.getCount3(), livingEntity);
      List list = packet.getList();
      if (list != null) {
         livingEntity.getDataWatcher().applyList(list);
      }
   }

   @Override
   public void applyS20PacketEntityProperties(S20PacketEntityProperties packet) {
      ClientManager.handlePacket2(packet, this, this.mcClient);
      Entity entityByID = this.worldClient.getEntityByID(packet.getCount());
      if (entityByID != null) {
         if (!(entityByID instanceof EntityLivingBase)) {
            throw new IllegalStateException("Server tried to update attributes of a non-living entity (actually: " + entityByID + ")");
         }

         AdvancedMapEntry advancedMapEntry = ((EntityLivingBase)entityByID).getAdvancedMapEntry();

         for (ExternalStringEntry externalStringEntry : (Iterable<ExternalStringEntry>)(packet.getList())) {
            AuxiliaryListener auxiliaryListener = advancedMapEntry.get(externalStringEntry.getLabelText());
            if (auxiliaryListener == null) {
               auxiliaryListener = advancedMapEntry.resolveAuxiliaryListenerByExtendedListener(
                  new NativeDoubleEntry((ExtendedListener)null, externalStringEntry.getLabelText(), 0.0, Double.MIN_NORMAL, Double.MAX_VALUE)
               );
            }

            auxiliaryListener.applyDouble(externalStringEntry.getValue());
            auxiliaryListener.updateState();

            for (InvalidOperationManager invalidOperationManager : (Iterable<InvalidOperationManager>)(externalStringEntry.getCollection())) {
               auxiliaryListener.applyInvalidOperationManager(invalidOperationManager);
            }
         }
      }
   }

   @Override
   public void handleS07PacketRespawn(S07PacketRespawn packet) {
      ClientManager.handlePacket2(packet, this, this.mcClient);
      if (packet.getEntityId() != this.mcClient.thePlayer.count4) {
         this.state = false;
         ThePlayerNameManager thePlayerNameManager = this.worldClient.getThePlayerNameManager();
         this.worldClient = new WorldClient(
            this,
            new AdvancedLongEntry(0L, packet.getWorldSettingsGameType(), false, this.mcClient.theWorld.getRandomSeedManager().isEnabled2(), packet.getInfoManager()),
            packet.getEntityId(),
            packet.getGameSettingsPeacefulType(),
            this.mcClient.uNKNOWNManager
         );
         this.worldClient.setThePlayerNameManager2(thePlayerNameManager);
         WorldClient worldClient2 = this.worldClient;
         this.mcClient.loadWorld(worldClient2);
         this.mcClient.thePlayer.count4 = packet.getEntityId();
         Minecraft mcClient2 = this.mcClient;
         BaseGuiScreenAdapter baseGuiScreenAdapter = ClientGlobals.moduleManager.fixes.canExecute6() ? null : new BaseGuiScreenAdapter(this);
         mcClient2.displayGuiScreen(baseGuiScreenAdapter);
      }

      Minecraft mcClient3 = this.mcClient;
      int entityId = packet.getEntityId();
      mcClient3.applyIndex(entityId);
      this.mcClient.playerController.applyWorldSettingsGameType(packet.getWorldSettingsGameType());
   }

   @Override
   public void applyS1BPacketEntityAttach(S1BPacketEntityAttach packet) {
      ClientManager.handlePacket2(packet, this, this.mcClient);
      Object entityByID = this.worldClient.getEntityByID(packet.getCount2());
      Entity entityByID2 = this.worldClient.getEntityByID(packet.getCount());
      if (packet.getCount3() == 0) {
         boolean flag = false;
         if (packet.getCount2() == this.mcClient.thePlayer.getEntityId()) {
            entityByID = this.mcClient.thePlayer;
            if (entityByID2 instanceof EntityBoat) {
               ((EntityBoat)entityByID2).setEnabled13(false);
            }

            flag = ((Entity)entityByID).ridingEntity == null && entityByID2 != null;
         } else if (entityByID2 instanceof EntityBoat) {
            ((EntityBoat)entityByID2).setEnabled13(true);
         }

         if (entityByID == null) {
            return;
         }

         ((Entity)entityByID).setEntity8(entityByID2);
         if (flag) {
            GameSettings gameSettings = this.mcClient.gameSettings;
            this.mcClient
               .ingameGUI
               .applyText(StatCollector.getText("mount.onboard", new Object[]{GameSettings.resolveText(gameSettings.keyBindSneak.getKeyCode())}), false);
         }
      } else if (packet.getCount3() == 1 && entityByID instanceof EntityMob) {
         if (entityByID2 != null) {
            ((EntityMob)entityByID).applyEntity(entityByID2, false);
         } else {
            ((EntityMob)entityByID).applyFlag(false, false);
         }
      }
   }

   @Override
   public void applyS2DPacketOpenWindow(S2DPacketOpenWindow packet) {
      ClientManager.handlePacket2(packet, this, this.mcClient);
      EntityPlayerSP player = this.mcClient.thePlayer;
      if ("minecraft:container".equals(packet.getLabelText())) {
         player.applyIInventory(new InventoryLargeChest(packet.getIChatComponent(), packet.getCount3()));
         player.container2.indexOffset2 = packet.getCount();
      } else if ("minecraft:villager".equals(packet.getLabelText())) {
         player.applyLocalListener(new ListenerCoordinator(player, packet.getIChatComponent()));
         player.container2.indexOffset2 = packet.getCount();
      } else if ("EntityHorse".equals(packet.getLabelText())) {
         Entity entityByID = this.worldClient.getEntityByID(packet.getCount2());
         if (entityByID instanceof EntityHorse) {
            player.applyEntityHorse((EntityHorse)entityByID, new IInventoryBridge(packet.getIChatComponent(), packet.getCount3()));
            player.container2.indexOffset2 = packet.getCount();
         }
      } else if (!packet.isActive()) {
         player.applyListener63Extension(new FactoryStringEntry(packet.getLabelText(), packet.getIChatComponent()));
         player.container2.indexOffset2 = packet.getCount();
      } else {
         AuxiliaryIInventoryAdapter auxiliaryIInventoryAdapter = new AuxiliaryIInventoryAdapter(packet.getLabelText(), packet.getIChatComponent(), packet.getCount3());
         player.applyIInventory(auxiliaryIInventoryAdapter);
         player.container2.indexOffset2 = packet.getCount();
      }
   }

   public NetworkManager getNetworkManager() {
      return this.networkManager;
   }

   @Override
   public void applyS10PacketSpawnPainting(S10PacketSpawnPainting packet) {
      ClientManager.handlePacket2(packet, this, this.mcClient);
      EntityPainting entityPainting = new EntityPainting(this.worldClient, packet.getBlockPos(), packet.getEnumFacing(), packet.getLabelText());
      this.worldClient.applyEntity10(packet.getCount(), entityPainting);
   }

   @Override
   public void applyS48PacketResourcePackSend(S48PacketResourcePackSend packet) {
      String text = packet.getText2();
      String text2 = packet.getText();
      if (ViaLoadingBase.protocolVersion.olderThanOrEqualTo(ProtocolVersion.v1_8) || this.matchesText(text, text2)) {
         if (text.startsWith("level://")) {
            try {
               String text3 = URLDecoder.decode(text.substring("level://".length()), StandardCharsets.UTF_8.toString());
               File file = new File(this.mcClient.mcDataDir, "saves");
               File file2 = new File(file, text3);
               if (file2.isFile()) {
                  this.networkManager.handlePacket6(new C19PacketResourcePackStatus(text2, DisablerSuccessfullyLoadedType.ACCEPTED));
                  Futures.addCallback(
                     this.mcClient.getResourceLanguageManager().resolveListenableFuture(file2),
                     new AuxiliaryFutureCallbackAdapter(this, text2),
                     MoreExecutors.directExecutor()
                  );
                  return;
               }
            } catch (UnsupportedEncodingException unsupportedEncodingException) {
            }

            this.networkManager.handlePacket6(new C19PacketResourcePackStatus(text2, DisablerSuccessfullyLoadedType.FAILED_DOWNLOAD));
         } else {
            ServerData serverData = this.mcClient.getServerData();
            if (serverData != null && serverData.getComplexOptionType() == ComplexOptionType.ENABLED) {
               this.networkManager.handlePacket6(new C19PacketResourcePackStatus(text2, DisablerSuccessfullyLoadedType.ACCEPTED));
               Futures.addCallback(
                  this.mcClient.getResourceLanguageManager().resolveListenableFuture(text, text2),
                  new InternalFutureCallbackAdapter(this, text2),
                  MoreExecutors.directExecutor()
               );
            } else if (serverData != null && serverData.getComplexOptionType() != ComplexOptionType.PROMPT) {
               this.networkManager.handlePacket6(new C19PacketResourcePackStatus(text2, DisablerSuccessfullyLoadedType.DECLINED));
            } else {
               DefaultGuiScreenAdapter defaultGuiScreenAdapter = new DefaultGuiScreenAdapter(
                  (confirmed, id) -> this.applyText(text2, text, confirmed, id),
                  StatCollector.getText("multiplayer.texturePrompt.line1", new Object[0]),
                  StatCollector.getText("multiplayer.texturePrompt.line2", new Object[0]),
                  0
               );
               defaultGuiScreenAdapter.networkManager = this.networkManager;
               defaultGuiScreenAdapter.labelText = text;
               defaultGuiScreenAdapter.labelText2 = text2;
               Minecraft mcClient2 = this.mcClient;
               Runnable task = () -> this.applyDefaultGuiScreenAdapter(defaultGuiScreenAdapter);
               mcClient2.resolveListenableFuture(task);
            }
         }
      }
   }

   @Override
   public void applyS06PacketUpdateHealth(S06PacketUpdateHealth packet) {
      ClientManager.handlePacket2(packet, this, this.mcClient);
      this.mcClient.thePlayer.applyFloat5(packet.getScaleFactor());
      this.mcClient.thePlayer.getFoodLevelManager().setCount(packet.getCount());
      this.mcClient.thePlayer.getFoodLevelManager().setFactor(packet.getFactor());
   }

   public void handlePacket(Packet packet) {
      SupplementalOptionType supplementalOptionType = SupplementalOptionType.SEND;
      EventPacket packet2 = PacketListener.resolveEventPacket(packet, supplementalOptionType);
      if (!packet2.isCancelled()) {
         this.networkManager.handlePacket6(packet2.getPacket());
      }
   }

   @Override
   public void applyS14PacketEntity(S14PacketEntity packet) {
      ClientManager.handlePacket2(packet, this, this.mcClient);
      Entity entity = packet.resolveEntity(this.worldClient);
      if (entity != null) {
         entity.indexOffset2 = entity.indexOffset2 + packet.getByteFlag();
         entity.indexOffset3 = entity.indexOffset3 + packet.getByteFlag3();
         entity.indexOffset = entity.indexOffset + packet.getByteFlag4();
         double offset = (double)entity.indexOffset2 / 32.0;
         double offset2 = (double)entity.indexOffset3 / 32.0;
         double offset3 = (double)entity.indexOffset / 32.0;
         float yaw = entity.rotationYaw;
         float pitch = entity.rotationPitch;
         if (entity instanceof EntityPlayerSP
            && ((Module)ClientGlobals.moduleManager.debug).isToggled()
            && ClientGlobals.moduleManager.debug.booleanSetting13 != null
            && ClientGlobals.moduleManager.debug.booleanSetting13.isEnabled()) {
            RayTraceUtil.applyString("WARNING!");
         }

         if (entity instanceof EntityPlayerSP && !Client.getClient().getRotationManager().hasRotations()) {
            yaw = Client.getClient().getRotationManager().getRotation2().yaw;
            pitch = Client.getClient().getRotationManager().getRotation2().pitch;
         }

         yaw = packet.isEnabled() ? (float)(packet.getByteFlag2() * 360) / 256.0F : yaw;
         pitch = packet.isEnabled() ? (float)(packet.getByteFlag5() * 360) / 256.0F : pitch;
         entity.applyDouble(offset, offset2, offset3, yaw, pitch, 3, false);
         entity.onGround = packet.isEnabled2();
      }
   }

   @Override
   public void applyS3CPacketUpdateScore(S3CPacketUpdateScore packet) {
      ClientManager.handlePacket2(packet, this, this.mcClient);
      ThePlayerNameManager thePlayerNameManager = this.worldClient.getThePlayerNameManager();
      ScoreboardData scoreboardData = thePlayerNameManager.get(packet.getLabelText2());
      if (packet.getMovementDistanceProperty() == MovementDistanceProperty.CHANGE) {
         thePlayerNameManager.getComparatorEntry(packet.getLabelText(), scoreboardData).applyIndex(packet.getCount());
      } else if (packet.getMovementDistanceProperty() == MovementDistanceProperty.REMOVE) {
         if (ClientBrandHelper.isEmpty(packet.getLabelText2())) {
            thePlayerNameManager.applyText(packet.getLabelText(), (ScoreboardData)null);
         } else if (scoreboardData != null) {
            thePlayerNameManager.applyText(packet.getLabelText(), scoreboardData);
         }
      }
   }

   @Override
   public void applyS22PacketMultiBlockChange(S22PacketMultiBlockChange packet) {
      ClientManager.handlePacket2(packet, this, this.mcClient);

      for (ShortEntry shortEntry : packet.getShortEntryArray()) {
         this.worldClient.matchesBlockPosByBlockPos(shortEntry.getBlockPos(), shortEntry.getIBlockState());
      }

      if (((Module)Client.getClient().getModuleManager().baritone).isToggled()) {
         ActiveListener activeListener = SettingsTxtManager.getExtraListener().resolveActiveListener(this);
         if (activeListener == null) {
            return;
         }

         AStarVerifier aStarVerifier = new AStarVerifier(packet.getShortEntryArray()[0].getBlockPos());
         activeListener.getCompositeListener()
            .applyBreadthFirstProvider(
               new BreadthFirstProvider(
                  aStarVerifier, Arrays.stream(packet.getShortEntryArray()).map(NetHandlerPlayClient::resolveDepthFirstRegistry).collect(Collectors.toList())
               )
            );
      }
   }

   @Override
   public void applyS2FPacketSetSlot(S2FPacketSetSlot packet) {
      ClientManager.handlePacket2(packet, this, this.mcClient);
      EntityPlayerSP player = this.mcClient.thePlayer;
      if (packet.getCount() == -1) {
         player.inventoryPlayer.setItemStack(packet.getItemStack());
      } else {
         boolean flag = false;
         if (this.mcClient.currentScreen instanceof ScrollableContainer) {
            ScrollableContainer currentScreen = (ScrollableContainer)this.mcClient.currentScreen;
            flag = currentScreen.getCount19() != ItemGroupManager.itemGroupManager.getCount2();
         }

         if (packet.getCount() == 0 && packet.getCount2() >= 36 && packet.getCount2() < 45) {
            ItemStack stack = player.container.getSlot(packet.getCount2()).getStack();
            if (packet.getItemStack() != null && (stack == null || stack.animationsToGo < packet.getItemStack().animationsToGo)) {
               packet.getItemStack().itemDamage = 5;
            }

            player.container.putStackInSlot(packet.getCount2(), packet.getItemStack());
         } else if (packet.getCount() == player.container2.indexOffset2 && (packet.getCount() != 0 || !flag)) {
            player.container2.putStackInSlot(packet.getCount2(), packet.getItemStack());
         }
      }
   }

   @Override
   public void applyS0BPacketAnimation(S0BPacketAnimation packet) {
      ClientManager.handlePacket2(packet, this, this.mcClient);
      Entity entityByID = this.worldClient.getEntityByID(packet.getIndex());
      if (entityByID != null) {
         if (packet.getIndex2() == 0) {
            EntityLivingBase livingEntity = (EntityLivingBase)entityByID;
            livingEntity.respawnPlayer();
         } else if (packet.getIndex2() == 1) {
            entityByID.updateState16();
         } else if (packet.getIndex2() == 2) {
            EntityPlayer player = (EntityPlayer)entityByID;
            player.applyFlag(false, false, false);
         } else if (packet.getIndex2() == 4) {
            if (!((Module)Client.getClient().getModuleManager().attackEffects).isToggled()) {
               this.mcClient.effectRenderer.applyEntity3(entityByID, AttackEffectsExplosionNormalType.CRIT);
            }
         } else if (packet.getIndex2() == 5 && !((Module)Client.getClient().getModuleManager().attackEffects).isToggled()) {
            this.mcClient.effectRenderer.applyEntity3(entityByID, AttackEffectsExplosionNormalType.CRIT_MAGIC);
         }
      }
   }

   @Override
   public void applyS04PacketEntityEquipment(S04PacketEntityEquipment packet) {
      ClientManager.handlePacket2(packet, this, this.mcClient);
      Entity entityByID = this.worldClient.getEntityByID(packet.getCount2());
      if (entityByID != null) {
         entityByID.applyItemStack(packet.getCount(), packet.getItemStack());
      }
   }

   @Override
   public void applyS30PacketWindowItems(S30PacketWindowItems packet) {
      ClientManager.handlePacket2(packet, this, this.mcClient);
      EntityPlayerSP player = this.mcClient.thePlayer;
      if (packet.getCount() == 0) {
         player.container.applyItemStackArray(packet.getItemStackArray());
      } else if (packet.getCount() == player.container2.indexOffset2) {
         player.container2.applyItemStackArray(packet.getItemStackArray());
      }
   }

   private void applyText(String text, String text2, boolean flag, int count) {
      this.mcClient = Minecraft.getMinecraft();
      ServerData serverData = this.mcClient.getServerData();
      if (flag) {
         if (serverData != null) {
            serverData.setComplexOptionType(ComplexOptionType.ENABLED);
         }

         this.networkManager.handlePacket6(new C19PacketResourcePackStatus(text, DisablerSuccessfullyLoadedType.ACCEPTED));
         Futures.addCallback(
            this.mcClient.getResourceLanguageManager().resolveListenableFuture(text2, text),
            new FutureCallbackAdapter(this, text),
            MoreExecutors.directExecutor()
         );
      } else {
         if (serverData != null) {
            serverData.setComplexOptionType(ComplexOptionType.DISABLED);
         }

         this.networkManager.handlePacket6(new C19PacketResourcePackStatus(text, DisablerSuccessfullyLoadedType.DECLINED));
      }

      ServersManager.applyServerData(serverData);
      this.mcClient.displayGuiScreen((GuiScreen)null);
   }

   @Override
   public void applyS01PacketJoinGame(S01PacketJoinGame packet) {
      ClientManager.handlePacket2(packet, this, this.mcClient);
      Minecraft mcClient2 = this.mcClient;
      PlayerControllerMP playerControllerMP = new PlayerControllerMP(this.mcClient, this);
      mcClient2.playerController = playerControllerMP;
      this.worldClient = new WorldClient(
         this,
         new AdvancedLongEntry(0L, packet.getWorldSettingsGameType(), false, packet.isEnabled(), packet.getInfoManager()),
         packet.getCount3(),
         packet.getGameSettingsPeacefulType(),
         this.mcClient.uNKNOWNManager
      );
      this.mcClient.gameSettings.gameSettingsPeacefulType = packet.getGameSettingsPeacefulType();
      WorldClient worldClient2 = this.worldClient;
      this.mcClient.loadWorld(worldClient2);
      this.mcClient.thePlayer.count4 = packet.getCount3();
      mcClient2 = this.mcClient;
      BaseGuiScreenAdapter baseGuiScreenAdapter = ClientGlobals.moduleManager.fixes.canExecute6() ? null : new BaseGuiScreenAdapter(this);
      mcClient2.displayGuiScreen(baseGuiScreenAdapter);
      this.mcClient.thePlayer.setEntityId(packet.getCount2());
      this.indexOffset = packet.getCount();
      this.mcClient.thePlayer.setInWater2(packet.isEnabled2());
      this.mcClient.playerController.applyWorldSettingsGameType(packet.getWorldSettingsGameType());
      this.mcClient.gameSettings.updateState4();
      if (!minecraft.isFullScreen() && minecraft.getServerData() != null) {
         if (minecraft.getSession() != null) {
            minecraft.getSession().setEnabled(true);
         }

         SupplementalLongEntry.enabled = true;
      }

      this.networkManager.handlePacket6(new C17PacketCustomPayload("MC|Brand", new PacketBuffer(Unpooled.buffer()).getPacketBuffer(VanillaManager.getText())));
   }

   @Override
   public void applyIChatComponent(IChatComponent iChatComponent) {
      if (Pokechu22WorldDownloaderManager.enabled) {
         Pokechu22WorldDownloaderManager.updateState7();

         try {
            Thread.sleep(2000L);
         } catch (Exception exception) {
         }
      }

      WorldClient world = null;
      this.mcClient.loadWorld(world);
      if (this.guiScreen != null) {
         Minecraft mcClient2 = this.mcClient;
         CustomGuiScreenAdapter customGuiScreenAdapter = new CustomGuiScreenAdapter(this.guiScreen, "disconnect.lost", iChatComponent);
         mcClient2.displayGuiScreen(customGuiScreenAdapter);
      } else {
         Minecraft mcClient3 = this.mcClient;
         CustomGuiScreenAdapter customGuiScreenAdapter2 = new CustomGuiScreenAdapter(new GuiMultiplayer(new GuiMainMenu()), "disconnect.lost", iChatComponent);
         mcClient3.displayGuiScreen(customGuiScreenAdapter2);
      }
   }

   public GameProfile getGameProfile() {
      return this.gameProfile;
   }

   public void updateState() {
      this.worldClient = null;
   }

   @Override
   public void applyS3APacketTabComplete(S3APacketTabComplete packet) {
      ClientManager.handlePacket2(packet, this, this.mcClient);
      String[] stringArray = packet.getStringArray();
      if (this.mcClient.currentScreen instanceof GuiChat) {
         ((GuiChat)this.mcClient.currentScreen).applyTextArray(stringArray);
      }
   }

   @Override
   public void applyS38PacketPlayerListItem(S38PacketPlayerListItem packet) {
      ClientManager.handlePacket2(packet, this, this.mcClient);

      for (LatencyManager latencyManager : (Iterable<LatencyManager>)(packet.getItemList())) {
         if (packet.getStaffDetectorAddPlayerType() == StaffDetectorAddPlayerType.REMOVE_PLAYER) {
            UUID id = latencyManager.getGameProfile().getId();
            NetworkPlayerInfo networkPlayerInfo = (NetworkPlayerInfo)this.entryMap.get(id);
            this.entryMap.remove(id);
            if (networkPlayerInfo.getGameProfile() != null) {
               this.entryMap2.remove(networkPlayerInfo.getGameProfile().getName());
            }
         } else {
            NetworkPlayerInfo networkPlayerInfo2 = (NetworkPlayerInfo)this.entryMap.get(latencyManager.getGameProfile().getId());
            if (packet.getStaffDetectorAddPlayerType() == StaffDetectorAddPlayerType.ADD_PLAYER) {
               networkPlayerInfo2 = new NetworkPlayerInfo(latencyManager);
               GameProfile gameProfile2 = networkPlayerInfo2.getGameProfile();
               this.entryMap.put(gameProfile2.getId(), networkPlayerInfo2);
               this.entryMap2.put(gameProfile2.getName(), gameProfile2.getId().toString());
            }

            if (networkPlayerInfo2 != null) {
               switch (StandardIntArrayEntry.countArray[packet.getStaffDetectorAddPlayerType().ordinal()]) {
                  case 1:
                     networkPlayerInfo2.setWorldSettingsGameType(latencyManager.getWorldSettingsGameType());
                     networkPlayerInfo2.setIndexOffset3(latencyManager.getCount());
                     break;
                  case 2:
                     networkPlayerInfo2.setWorldSettingsGameType(latencyManager.getWorldSettingsGameType());
                     break;
                  case 3:
                     networkPlayerInfo2.setIndexOffset3(latencyManager.getCount());
                     break;
                  case 4:
                     networkPlayerInfo2.setChatComponent(latencyManager.getIChatComponent());
               }
            }
         }
      }
   }

   @Override
   public void applyS40PacketDisconnect(S40PacketDisconnect packet) {
      if (Pokechu22WorldDownloaderManager.enabled) {
         Pokechu22WorldDownloaderManager.updateState7();

         try {
            Thread.sleep(2000L);
         } catch (Exception exception) {
         }
      }

      if (minecraft.getSession() != null) {
         minecraft.getSession().setEnabled(false);
      }

      SessionTokenManager.updateState();
      this.networkManager.applyIChatComponent(packet.getIChatComponent());
   }

   @Override
   public void applyS47PacketPlayerListHeaderF(S47PacketPlayerListHeaderFooter packet) {
      this.mcClient.ingameGUI.getOrderingEntry().setIChatComponent(packet.getIChatComponent().getString2().length() == 0 ? null : packet.getIChatComponent());
      this.mcClient.ingameGUI.getOrderingEntry().setIChatComponent2(packet.getIChatComponent2().getString2().length() == 0 ? null : packet.getIChatComponent2());
   }

   @Override
   public void processAction(S32PacketConfirmTransaction packet) {
      ClientManager.handlePacket2(packet, this, this.mcClient);
      if (ViaLoadingBase.protocolVersion.newerThanOrEqualTo(ProtocolVersion.v1_17)) {
         this.handlePacket(new C0FPacketConfirmTransaction(packet.getIndex()));
      } else {
         Container container = null;
         EntityPlayerSP player = this.mcClient.thePlayer;
         if (packet.getIndex() == 0) {
            container = player.container;
         } else if (packet.getIndex() == player.container2.indexOffset2) {
            container = player.container2;
         }

         if (container != null && !packet.canExecute()) {
            this.handlePacket(new C0FPacketConfirmTransaction(packet.getIndex(), packet.processValue(), true));
         }
      }
   }

   private boolean matchesText(String text, String text2) {
      try {
         String scheme = new URI(text).getScheme();
         boolean flag = "level".equals(scheme);
         if (!"http".equals(scheme) && !"https".equals(scheme) && !flag) {
            throw new URISyntaxException(text, "Wrong protocol");
         } else if (flag && (text.contains("..") || !text.endsWith("/resources.zip"))) {
            throw new URISyntaxException(text, "Invalid levelstorage resourcepack path");
         } else {
            return true;
         }
      } catch (URISyntaxException uRISyntaxException) {
         this.networkManager.handlePacket6(new C19PacketResourcePackStatus(text2, DisablerSuccessfullyLoadedType.FAILED_DOWNLOAD));
         return false;
      }
   }

   @Override
   public void applyS37PacketStatistics(S37PacketStatistics packet) {
      ClientManager.handlePacket2(packet, this, this.mcClient);
      boolean flag = false;

      for (Entry entry : (Iterable<Entry>)(packet.getMap().entrySet())) {
         DuplicateStatIdManager key = (DuplicateStatIdManager)entry.getKey();
         int value = (Integer)entry.getValue();
         if (key.isActive() && value > 0) {
            if (this.state1 && this.mcClient.thePlayer.getStatFileWriter().computeCount(key) == 0) {
               AuxiliaryDuplicateStatIdManagerAdapter auxiliaryDuplicateStatIdManagerAdapter = (AuxiliaryDuplicateStatIdManagerAdapter)key;
               this.mcClient.guiAchievement.applyAuxiliaryDuplicateStatIdMa(auxiliaryDuplicateStatIdManagerAdapter);
               if (key == AchievementTheEnd2Manager.auxiliaryDuplicateStatIdManagerAdapter31) {
                  this.mcClient.gameSettings.state107 = false;
                  this.mcClient.gameSettings.updateState();
               }
            }

            flag = true;
         }

         this.mcClient.thePlayer.getStatFileWriter().applyEntityPlayer(this.mcClient.thePlayer, key, value);
      }

      if (!this.state1 && !flag && this.mcClient.gameSettings.state107) {
         this.mcClient.guiAchievement.applyAuxiliaryDuplicateStatIdMaByAuxiliaryDuplica(AchievementTheEnd2Manager.auxiliaryDuplicateStatIdManagerAdapter31);
      }

      this.state1 = true;
      if (this.mcClient.currentScreen instanceof StringArrayCallback) {
         ((StringArrayCallback)this.mcClient.currentScreen).updateState();
      }
   }

   @Override
   public void applyS28PacketEffect(S28PacketEffect packet) {
      ClientManager.handlePacket2(packet, this, this.mcClient);
      if (packet.isEnabled()) {
         this.mcClient.theWorld.applyBlockPos19(packet.getCount(), packet.getBlockPos(), packet.getCount2());
      } else {
         this.mcClient.theWorld.applyBlockPosByBlockPos(packet.getCount(), packet.getBlockPos(), packet.getCount2());
      }
   }

   public synchronized Map getEntryMap() {
      return this.entryMap;
   }

   @Override
   public void applyS33PacketUpdateSign(S33PacketUpdateSign packet) {
      ClientManager.handlePacket2(packet, this, this.mcClient);
      boolean flag = false;
      if (this.mcClient.theWorld.checkBlockPos12(packet.getBlockPos())) {
         TileEntity tileEntity = this.mcClient.theWorld.resolveTileEntity(packet.getBlockPos());
         if (tileEntity instanceof TileEntitySign) {
            TileEntitySign tileEntitySign = (TileEntitySign)tileEntity;
            flag = false;
            if (Client.getClient().getModuleManager().exploitFixer.settingSignCrash.isEnabled()) {
               for (IChatComponent iChatComponent : packet.getIChatComponentArray()) {
                  if (iChatComponent != null && iChatComponent.getString() != null && iChatComponent.getString().length() > 100) {
                     flag = true;
                  }
               }
            }

            if (tileEntitySign.isEnabled2() && !flag) {
               System.arraycopy(packet.getIChatComponentArray(), 0, tileEntitySign.iChatComponentArray, 0, 4);
               tileEntitySign.processAction5();
            }

            flag = true;
         }
      }

      if (!flag) {
         ;
      }
   }

   @Override
   public void applyS27PacketExplosion(S27PacketExplosion packet) {
      ClientManager.handlePacket2(packet, this, this.mcClient);
      new BlockBreakState(
            this.mcClient.theWorld, (Entity)null, packet.getCoordinate2(), packet.getCoordinate3(), packet.getCoordinate(), packet.getScaleFactor4(), packet.getList()
         )
         .setEnabled(true);
      if (packet.isEnabled2()) {
         this.mcClient.thePlayer.motionX = 0.0;
         this.mcClient.thePlayer.motionZ = 0.0;
      } else {
         this.mcClient.thePlayer.motionX = this.mcClient.thePlayer.motionX + (double)packet.getScaleFactor2();
         this.mcClient.thePlayer.motionZ = this.mcClient.thePlayer.motionZ + (double)packet.getScaleFactor();
      }

      if (packet.isEnabled()) {
         this.mcClient.thePlayer.motionY = 0.0;
      } else {
         this.mcClient.thePlayer.motionY = this.mcClient.thePlayer.motionY + (double)packet.getScaleFactor3();
      }
   }

   @Override
   public void applyS45PacketTitle(S45PacketTitle packet) {
      ClientManager.handlePacket2(packet, this, this.mcClient);
      MovementFilterProperty movementFilterProperty = packet.getMovementFilterProperty();
      String text = null;
      String text2 = null;
      String text3 = packet.getIChatComponent() != null ? packet.getIChatComponent().getString2() : "";
      switch (StandardIntArrayEntry.countArray2[movementFilterProperty.ordinal()]) {
         case 1:
            text = text3;
            break;
         case 2:
            text2 = text3;
            break;
         case 3:
            this.mcClient.ingameGUI.applyText("", "", -1, -1, -1);
            this.mcClient.ingameGUI.renderElement3();
            return;
      }

      this.mcClient.ingameGUI.applyText(text, text2, packet.getCount2(), packet.getCount(), packet.getCount3());
   }

   public void handlePacket2(Packet packet) {
      this.networkManager.handlePacket6(packet);
   }

   @Override
   public void applyS0DPacketCollectItem(S0DPacketCollectItem packet) {
      ClientManager.handlePacket2(packet, this, this.mcClient);
      Entity entityByID = this.worldClient.getEntityByID(packet.getCount2());
      Object entityByID2 = (EntityLivingBase)this.worldClient.getEntityByID(packet.getCount());
      if (entityByID2 == null) {
         entityByID2 = this.mcClient.thePlayer;
      }

      if (entityByID != null) {
         if (entityByID instanceof EntityXporb) {
            this.worldClient.applyEntityByEntity(entityByID, "random.orb", 0.2F, ((this.random.nextFloat() - this.random.nextFloat()) * 0.7F + 1.0F) * 2.0F);
         } else {
            this.worldClient.applyEntityByEntity(entityByID, "random.pop", 0.2F, ((this.random.nextFloat() - this.random.nextFloat()) * 0.7F + 1.0F) * 2.0F);
         }

         this.mcClient.effectRenderer.applyTertiaryEntityAdapter2(new EntityPhantomAdapter(this.worldClient, entityByID, (Entity)entityByID2, 0.5F));
         this.worldClient.resolveEntity2(packet.getCount2());
      }
   }

   public Collection getCollection() {
      return this.entryMap.values();
   }

   public NetHandlerPlayClient(Minecraft mc, GuiScreen screen, NetworkManager networkManager2, GameProfile gameProfile2) {
      this.mcClient = mc;
      this.guiScreen = screen;
      this.networkManager = networkManager2;
      this.gameProfile = gameProfile2;
   }
}
