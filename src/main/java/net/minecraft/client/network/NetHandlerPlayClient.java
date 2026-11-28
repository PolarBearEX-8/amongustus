package net.minecraft.client.network;

import com.google.common.util.concurrent.FutureCallback;
import net.minecraft.network.play.client.C03PacketPlayer;
import net.minecraft.scoreboard.Team;
import baritone.api.BaritoneAPI;
import baritone.api.IBaritone;
import baritone.api.event.events.ChunkEvent;
import baritone.api.event.events.type.EventState;
import wdl.WDLHooks;
import baritone.api.utils.Pair;
import baritone.utils.ChunkPos;
import baritone.api.event.events.BlockChangeEvent;
import wdl.WDL;
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
import net.augustus.modules.player.NoRotationSet;
import net.augustus.events.EventPacket;
import net.augustus.ClientManagers;
import net.augustus.utils.ChatUtil;
import net.augustus.viaversion.ViaAugustus;
import net.augustus.events.listeners.ChunkLoadListener;
import net.augustus.Augustus;
import net.augustus.events.PacketStage;
import net.augustus.utils.rotation.YawPitchHelper;
import net.augustus.utils.player.SilentSlotManager;
import net.augustus.events.listeners.PacketListener;
import net.augustus.utils.interfaces.MC;
import net.minecraft.block.Block;
import net.minecraft.client.ClientBrandRetriever;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.GuardianSound;
import net.minecraft.client.entity.EntityOtherPlayerMP;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.GuiChat;
import net.minecraft.client.gui.GuiDisconnected;
import net.minecraft.client.gui.GuiDownloadTerrain;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.gui.GuiMerchant;
import net.minecraft.client.gui.GuiMultiplayer;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiScreenBook;
import net.minecraft.client.gui.GuiScreenDemo;
import net.minecraft.client.gui.GuiWinGame;
import net.minecraft.client.gui.GuiYesNo;
import net.minecraft.client.gui.IProgressMeter;
import net.minecraft.client.gui.inventory.GuiContainerCreative;
import net.minecraft.client.multiplayer.PlayerControllerMP;
import net.minecraft.client.multiplayer.ServerData;
import net.minecraft.client.multiplayer.ServerList;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.particle.EntityPickupFX;
import net.minecraft.client.player.inventory.ContainerLocalMenu;
import net.minecraft.client.player.inventory.LocalBlockIntercommunication;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLeashKnot;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IMerchant;
import net.minecraft.entity.NpcMerchant;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.BaseAttributeMap;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.ai.attributes.RangedAttribute;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.item.EntityArmorStand;
import net.minecraft.entity.item.EntityBoat;
import net.minecraft.entity.item.EntityEnderCrystal;
import net.minecraft.entity.item.EntityEnderEye;
import net.minecraft.entity.item.EntityEnderPearl;
import net.minecraft.entity.item.EntityExpBottle;
import net.minecraft.entity.item.EntityFallingBlock;
import net.minecraft.entity.item.EntityFireworkRocket;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.item.EntityItemFrame;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.entity.item.EntityPainting;
import net.minecraft.entity.item.EntityTNTPrimed;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.monster.EntityGuardian;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.entity.projectile.EntityEgg;
import net.minecraft.entity.projectile.EntityFishHook;
import net.minecraft.entity.projectile.EntityLargeFireball;
import net.minecraft.entity.projectile.EntityPotion;
import net.minecraft.entity.projectile.EntitySmallFireball;
import net.minecraft.entity.projectile.EntitySnowball;
import net.minecraft.entity.projectile.EntityWitherSkull;
import net.minecraft.init.Items;
import net.minecraft.inventory.AnimalChest;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.InventoryBasic;
import net.minecraft.item.Item;
import net.minecraft.item.ItemMap;
import net.minecraft.item.ItemStack;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.PacketThreadUtil;
import net.minecraft.network.play.INetHandlerPlayClient;
import net.minecraft.network.play.client.C00PacketKeepAlive;
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
import net.minecraft.scoreboard.IScoreObjectiveCriteria;
import net.minecraft.scoreboard.Score;
import net.minecraft.scoreboard.ScoreObjective;
import net.minecraft.scoreboard.ScorePlayerTeam;
import net.minecraft.scoreboard.Scoreboard;
import net.minecraft.stats.Achievement;
import net.minecraft.stats.AchievementList;
import net.minecraft.stats.StatBase;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityBanner;
import net.minecraft.tileentity.TileEntityBeacon;
import net.minecraft.tileentity.TileEntityCommandBlock;
import net.minecraft.tileentity.TileEntityFlowerPot;
import net.minecraft.tileentity.TileEntityMobSpawner;
import net.minecraft.tileentity.TileEntitySign;
import net.minecraft.tileentity.TileEntitySkull;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.MathHelper;
import net.minecraft.util.StringUtils;
import net.minecraft.util.Vec3;
import net.minecraft.village.MerchantRecipeList;
import net.minecraft.world.Explosion;
import net.minecraft.world.WorldProviderSurface;
import net.minecraft.world.WorldSettings;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.storage.MapData;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class NetHandlerPlayClient implements INetHandlerPlayClient, MC {
   private static final Logger LOGGER = LogManager.getLogger();
   private final NetworkManager netManager;
   private final GameProfile profile;
   private final GuiScreen guiScreenServer;
   private Minecraft gameController;
   private WorldClient clientWorldController;
   private boolean doneLoadingTerrain;
   private final Map playerInfoMap = Maps.newConcurrentMap();
   private final Map playerUuidMap = Maps.newConcurrentMap();
   public int currentServerMaxPlayers = 20;
   private boolean hasStatistics = false;
   private final Random avRandomizer = new Random();

   @Override
   public void handleConfirmTransaction(S32PacketConfirmTransaction packet) {
      PacketThreadUtil.checkThreadAndEnqueue(packet, this, this.gameController);
      if (ViaAugustus.targetProtocolVersion.newerThanOrEqualTo(ProtocolVersion.v1_17)) {
         this.addToSendQueue(new C0FPacketConfirmTransaction(packet.getWindowId()));
      } else {
         Container container = null;
         EntityPlayerSP thePlayer = this.gameController.thePlayer;
         if (packet.getWindowId() == 0) {
            container = thePlayer.inventoryContainer;
         } else if (packet.getWindowId() == thePlayer.openContainer.windowId) {
            container = thePlayer.openContainer;
         }

         if (container != null && !packet.isAccepted()) {
            this.addToSendQueue(new C0FPacketConfirmTransaction(packet.getWindowId(), packet.getActionNumber(), true));
         }
      }
   }

   public void addToSendQueueUnchecked(Packet packet) {
      this.netManager.sendPacket(packet);
   }

   private boolean validateResourcePackUrl(String str, String str2) {
      try {
         String scheme = new URI(str).getScheme();
         boolean flag = "level".equals(scheme);
         if (!"http".equals(scheme) && !"https".equals(scheme) && !flag) {
            throw new URISyntaxException(str, "Wrong protocol");
         } else if (flag && (str.contains("..") || !str.endsWith("/resources.zip"))) {
            throw new URISyntaxException(str, "Invalid levelstorage resourcepack path");
         } else {
            return true;
         }
      } catch (URISyntaxException e) {
         this.netManager.sendPacket(new C19PacketResourcePackStatus(str2, C19PacketResourcePackStatus.Action.FAILED_DOWNLOAD));
         return false;
      }
   }

   @Override
   public void handleTimeUpdate(S03PacketTimeUpdate packet) {
      PacketThreadUtil.checkThreadAndEnqueue(packet, this, this.gameController);
      this.gameController.theWorld.setTotalWorldTime(packet.getTotalWorldTime());
      this.gameController.theWorld.setWorldTime(packet.getWorldTime());
   }

   @Override
   public void handleResourcePack(S48PacketResourcePackSend packet) {
      String url = packet.getURL();
      String hash = packet.getHash();
      if (ViaAugustus.targetProtocolVersion.olderThanOrEqualTo(ProtocolVersion.v1_8) || this.validateResourcePackUrl(url, hash)) {
         if (url.startsWith("level://")) {
            try {
               String str = URLDecoder.decode(url.substring("level://".length()), StandardCharsets.UTF_8.toString());
               File file = new File(this.gameController.mcDataDir, "saves");
               File file2 = new File(file, str);
               if (file2.isFile()) {
                  this.netManager.sendPacket(new C19PacketResourcePackStatus(hash, C19PacketResourcePackStatus.Action.ACCEPTED));
                  Futures.addCallback(
                     this.gameController.getResourcePackRepository().setResourcePackInstance(file2),
                     new NetHandlerPlayClient.DefaultFutureCallback(this, hash),
                     MoreExecutors.directExecutor()
                  );
                  return;
               }
            } catch (UnsupportedEncodingException e) {
            }

            this.netManager.sendPacket(new C19PacketResourcePackStatus(hash, C19PacketResourcePackStatus.Action.FAILED_DOWNLOAD));
         } else {
            ServerData currentServerData = this.gameController.getCurrentServerData();
            if (currentServerData != null && currentServerData.getResourceMode() == ServerData.ServerResourceMode.ENABLED) {
               this.netManager.sendPacket(new C19PacketResourcePackStatus(hash, C19PacketResourcePackStatus.Action.ACCEPTED));
               Futures.addCallback(
                  this.gameController.getResourcePackRepository().downloadResourcePack(url, hash),
                  new NetHandlerPlayClient.DefaultFutureCallback2(this, hash),
                  MoreExecutors.directExecutor()
               );
            } else if (currentServerData != null && currentServerData.getResourceMode() != ServerData.ServerResourceMode.PROMPT) {
               this.netManager.sendPacket(new C19PacketResourcePackStatus(hash, C19PacketResourcePackStatus.Action.DECLINED));
            } else {
               GuiYesNo guiYesNo = new GuiYesNo(
                  (flag, n) -> {
                     this.gameController = Minecraft.getMinecraft();
                     ServerData currentServerData2 = this.gameController.getCurrentServerData();
                     if (flag) {
                        if (currentServerData2 != null) {
                           currentServerData2.setResourceMode(ServerData.ServerResourceMode.ENABLED);
                        }

                        this.netManager.sendPacket(new C19PacketResourcePackStatus(hash, C19PacketResourcePackStatus.Action.ACCEPTED));
                        Futures.addCallback(
                           this.gameController.getResourcePackRepository().downloadResourcePack(url, hash),
                           new NetHandlerPlayClient.DefaultFutureCallback3(this, hash),
                           MoreExecutors.directExecutor()
                        );
                     } else {
                        if (currentServerData2 != null) {
                           currentServerData2.setResourceMode(ServerData.ServerResourceMode.DISABLED);
                        }

                        this.netManager.sendPacket(new C19PacketResourcePackStatus(hash, C19PacketResourcePackStatus.Action.DECLINED));
                     }

                     ServerList.saveSingleServer(currentServerData2);
                     this.gameController.displayGuiScreen((GuiScreen)null);
                  },
                  I18n.format("multiplayer.texturePrompt.line1", new Object[0]),
                  I18n.format("multiplayer.texturePrompt.line2", new Object[0]),
                  0
               );
               guiYesNo.resourcePackNetManager = this.netManager;
               guiYesNo.resourcePackUrl = url;
               guiYesNo.resourcePackHash = hash;
               Minecraft gameController2 = this.gameController;
               Runnable runnable = () -> this.gameController.displayGuiScreen(guiYesNo);
               gameController2.addScheduledTask(runnable);
            }
         }
      }
   }

   @Override
   public void handleEntityEquipment(S04PacketEntityEquipment packet) {
      PacketThreadUtil.checkThreadAndEnqueue(packet, this, this.gameController);
      Entity entityByID = this.clientWorldController.getEntityByID(packet.getEntityID());
      if (entityByID != null) {
         entityByID.setCurrentItemOrArmor(packet.getEquipmentSlot(), packet.getItemStack());
      }
   }

   @Override
   public void handleEntityVelocity(S12PacketEntityVelocity packet) {
      PacketThreadUtil.checkThreadAndEnqueue(packet, this, this.gameController);
      Entity entityByID = this.clientWorldController.getEntityByID(packet.getEntityID());
      if (entityByID != null) {
         entityByID.setVelocity(packet.isApplyHorizontalVelocity() ? (double)packet.getMotionX() / 8000.0 : entityByID.motionX, packet.isApplyVerticalVelocity() ? (double)packet.getMotionY() / 8000.0 : entityByID.motionY, packet.isApplyHorizontalVelocity() ? (double)packet.getMotionZ() / 8000.0 : entityByID.motionZ);
      }
   }

   public void addToSendQueue(Packet packet) {
      EventPacket event = PacketListener.postPacket(packet, PacketStage.SEND);
      if (!event.isCancelled()) {
         this.netManager.sendPacket(event.getPacket());
      }
   }

   public Map getPlayerUuidMap() {
      return this.playerUuidMap;
   }

   @Override
   public void handleExplosion(S27PacketExplosion packet) {
      PacketThreadUtil.checkThreadAndEnqueue(packet, this, this.gameController);
      new Explosion(
         this.gameController.theWorld, (Entity)null, packet.getX(), packet.getY(), packet.getZ(), packet.getStrength(), packet.getAffectedBlockPositions()
      ).doExplosionB(true);
      if (packet.isResetHorizontalMotion()) {
         this.gameController.thePlayer.motionX = 0.0;
         this.gameController.thePlayer.motionZ = 0.0;
      } else {
         this.gameController.thePlayer.motionX = this.gameController.thePlayer.motionX + (double)packet.getMotionX();
         this.gameController.thePlayer.motionZ = this.gameController.thePlayer.motionZ + (double)packet.getMotionZ();
      }

      if (packet.isResetVerticalMotion()) {
         this.gameController.thePlayer.motionY = 0.0;
      } else {
         this.gameController.thePlayer.motionY = this.gameController.thePlayer.motionY + (double)packet.getMotionY();
      }
   }

   @Override
   public void handleDisplayScoreboard(S3DPacketDisplayScoreboard packet) {
      PacketThreadUtil.checkThreadAndEnqueue(packet, this, this.gameController);
      Scoreboard scoreboard = this.clientWorldController.getScoreboard();
      if (packet.getScoreName().length() == 0) {
         scoreboard.setObjectiveInDisplaySlot(packet.getPosition(), (ScoreObjective)null);
      } else {
         ScoreObjective objective = scoreboard.getObjective(packet.getScoreName());
         scoreboard.setObjectiveInDisplaySlot(packet.getPosition(), objective);
      }
   }

   @Override
   public void handleEntityMetadata(S1CPacketEntityMetadata packet) {
      PacketThreadUtil.checkThreadAndEnqueue(packet, this, this.gameController);
      Entity entityByID = this.clientWorldController.getEntityByID(packet.getEntityId());
      if (entityByID != null && packet.getDataManagerEntries() != null) {
         entityByID.getDataWatcher().updateWatchedObjectsFromList(packet.getDataManagerEntries());
      }
   }

   @Override
   public void handleSpawnObject(S0EPacketSpawnObject packet) {
      PacketThreadUtil.checkThreadAndEnqueue(packet, this, this.gameController);
      double d = (double)packet.getX() / 32.0;
      double d2 = (double)packet.getY() / 32.0;
      double d3 = (double)packet.getZ() / 32.0;
      Entity entity = null;
      if (packet.getType() == 10) {
         entity = EntityMinecart.createMinecart(this.clientWorldController, d, d2, d3, EntityMinecart.EnumMinecartType.byNetworkID(packet.getData()));
      } else if (packet.getType() == 90) {
         Entity entityByID = this.clientWorldController.getEntityByID(packet.getData());
         if (entityByID instanceof EntityPlayer) {
            entity = new EntityFishHook(this.clientWorldController, d, d2, d3, (EntityPlayer)entityByID);
         }

         packet.setData(0);
      } else if (packet.getType() == 60) {
         entity = new EntityArrow(this.clientWorldController, d, d2, d3);
      } else if (packet.getType() == 61) {
         entity = new EntitySnowball(this.clientWorldController, d, d2, d3);
      } else if (packet.getType() == 71) {
         entity = new EntityItemFrame(
            this.clientWorldController,
            new BlockPos(MathHelper.floor_double(d), MathHelper.floor_double(d2), MathHelper.floor_double(d3)),
            EnumFacing.getHorizontal(packet.getData())
         );
         packet.setData(0);
      } else if (packet.getType() == 77) {
         entity = new EntityLeashKnot(
            this.clientWorldController, new BlockPos(MathHelper.floor_double(d), MathHelper.floor_double(d2), MathHelper.floor_double(d3))
         );
         packet.setData(0);
      } else if (packet.getType() == 65) {
         entity = new EntityEnderPearl(this.clientWorldController, d, d2, d3);
      } else if (packet.getType() == 72) {
         entity = new EntityEnderEye(this.clientWorldController, d, d2, d3);
      } else if (packet.getType() == 76) {
         entity = new EntityFireworkRocket(this.clientWorldController, d, d2, d3, (ItemStack)null);
      } else if (packet.getType() == 63) {
         entity = new EntityLargeFireball(
            this.clientWorldController,
            d,
            d2,
            d3,
            (double)packet.getSpeedX() / 8000.0,
            (double)packet.getSpeedY() / 8000.0,
            (double)packet.getSpeedZ() / 8000.0
         );
         packet.setData(0);
      } else if (packet.getType() == 64) {
         entity = new EntitySmallFireball(
            this.clientWorldController,
            d,
            d2,
            d3,
            (double)packet.getSpeedX() / 8000.0,
            (double)packet.getSpeedY() / 8000.0,
            (double)packet.getSpeedZ() / 8000.0
         );
         packet.setData(0);
      } else if (packet.getType() == 66) {
         entity = new EntityWitherSkull(
            this.clientWorldController,
            d,
            d2,
            d3,
            (double)packet.getSpeedX() / 8000.0,
            (double)packet.getSpeedY() / 8000.0,
            (double)packet.getSpeedZ() / 8000.0
         );
         packet.setData(0);
      } else if (packet.getType() == 62) {
         entity = new EntityEgg(this.clientWorldController, d, d2, d3);
      } else if (packet.getType() == 73) {
         entity = new EntityPotion(this.clientWorldController, d, d2, d3, packet.getData());
         packet.setData(0);
      } else if (packet.getType() == 75) {
         entity = new EntityExpBottle(this.clientWorldController, d, d2, d3);
         packet.setData(0);
      } else if (packet.getType() == 1) {
         entity = new EntityBoat(this.clientWorldController, d, d2, d3);
      } else if (packet.getType() == 50) {
         entity = new EntityTNTPrimed(this.clientWorldController, d, d2, d3, (EntityLivingBase)null);
      } else if (packet.getType() == 78) {
         entity = new EntityArmorStand(this.clientWorldController, d, d2, d3);
      } else if (packet.getType() == 51) {
         entity = new EntityEnderCrystal(this.clientWorldController, d, d2, d3);
      } else if (packet.getType() == 2) {
         entity = new EntityItem(this.clientWorldController, d, d2, d3);
      } else if (packet.getType() == 70) {
         entity = new EntityFallingBlock(this.clientWorldController, d, d2, d3, Block.getStateById(packet.getData() & 65535));
         packet.setData(0);
      }

      if (entity != null) {
         ((Entity)entity).serverPosX = packet.getX();
         ((Entity)entity).serverPosY = packet.getY();
         ((Entity)entity).serverPosZ = packet.getZ();
         ((Entity)entity).rotationPitch = (float)(packet.getPitch() * 360) / 256.0F;
         ((Entity)entity).rotationYaw = (float)(packet.getYaw() * 360) / 256.0F;
         Entity[] parts = entity.getParts();
         if (parts != null) {
            int n = packet.getEntityID() - entity.getEntityId();

            for (int i = 0; i < parts.length; i++) {
               parts[i].setEntityId(parts[i].getEntityId() + n);
            }
         }

         entity.setEntityId(packet.getEntityID());
         this.clientWorldController.addEntityToWorld(packet.getEntityID(), (Entity)entity);
         if (packet.getData() > 0) {
            if (packet.getType() == 60) {
               Entity entityByID2 = this.clientWorldController.getEntityByID(packet.getData());
               if (entityByID2 instanceof EntityLivingBase && entity instanceof EntityArrow) {
                  ((EntityArrow)entity).shootingEntity = entityByID2;
               }
            }

            entity.setVelocity((double)packet.getSpeedX() / 8000.0, (double)packet.getSpeedY() / 8000.0, (double)packet.getSpeedZ() / 8000.0);
         }
      }
   }

   @Override
   public void handleChangeGameState(S2BPacketChangeGameState packet) {
      PacketThreadUtil.checkThreadAndEnqueue(packet, this, this.gameController);
      EntityPlayerSP thePlayer = this.gameController.thePlayer;
      int gameState = packet.getGameState();
      float value = packet.getValue();
      int n = MathHelper.floor_float(value + 0.5F);
      if (gameState >= 0 && gameState < S2BPacketChangeGameState.MESSAGE_NAMES.length && S2BPacketChangeGameState.MESSAGE_NAMES[gameState] != null) {
         thePlayer.addChatComponentMessage(new ChatComponentTranslation(S2BPacketChangeGameState.MESSAGE_NAMES[gameState], new Object[0]));
      }

      if (gameState == 1) {
         this.clientWorldController.getWorldInfo().setRaining(true);
         this.clientWorldController.setRainStrength(0.0F);
      } else if (gameState == 2) {
         this.clientWorldController.getWorldInfo().setRaining(false);
         this.clientWorldController.setRainStrength(1.0F);
      } else if (gameState == 3) {
         this.gameController.playerController.setGameType(WorldSettings.GameType.getByID(n));
      } else if (gameState == 4) {
         this.gameController.displayGuiScreen(new GuiWinGame());
      } else if (gameState == 5) {
         GameSettings gameSettings = this.gameController.gameSettings;
         if (value == 0.0F) {
            this.gameController.displayGuiScreen(new GuiScreenDemo());
         } else if (value == 101.0F) {
            this.gameController
               .ingameGUI
               .getChatGUI()
               .printChatMessage(
                  new ChatComponentTranslation(
                     "demo.help.movement",
                     new Object[]{
                        GameSettings.getKeyDisplayString(gameSettings.keyBindForward.getKeyCode()),
                        GameSettings.getKeyDisplayString(gameSettings.keyBindLeft.getKeyCode()),
                        GameSettings.getKeyDisplayString(gameSettings.keyBindBack.getKeyCode()),
                        GameSettings.getKeyDisplayString(gameSettings.keyBindRight.getKeyCode())
                     }
                  )
               );
         } else if (value == 102.0F) {
            this.gameController
               .ingameGUI
               .getChatGUI()
               .printChatMessage(new ChatComponentTranslation("demo.help.jump", new Object[]{GameSettings.getKeyDisplayString(gameSettings.keyBindJump.getKeyCode())}));
         } else if (value == 103.0F) {
            this.gameController
               .ingameGUI
               .getChatGUI()
               .printChatMessage(new ChatComponentTranslation("demo.help.inventory", new Object[]{GameSettings.getKeyDisplayString(gameSettings.keyBindInventory.getKeyCode())}));
         }
      } else if (gameState == 6) {
         this.clientWorldController.playSound(thePlayer.posX, thePlayer.posY + (double)thePlayer.getEyeHeight(), thePlayer.posZ, "random.successful_hit", 0.18F, 0.45F, false);
      } else if (gameState == 7) {
         this.clientWorldController.setRainStrength(value);
      } else if (gameState == 8) {
         this.clientWorldController.setThunderStrength(value);
      } else if (gameState == 10) {
         this.clientWorldController.spawnParticle(EnumParticleTypes.MOB_APPEARANCE, thePlayer.posX, thePlayer.posY, thePlayer.posZ, 0.0, 0.0, 0.0, new int[0]);
         this.clientWorldController.playSound(thePlayer.posX, thePlayer.posY, thePlayer.posZ, "mob.guardian.curse", 1.0F, 1.0F, false);
      }
   }

   @Override
   public void handleParticles(S2APacketParticles packet) {
      PacketThreadUtil.checkThreadAndEnqueue(packet, this, this.gameController);
      if (packet.getParticleCount() == 0) {
         double d = (double)(packet.getParticleSpeed() * packet.getXOffset());
         double d2 = (double)(packet.getParticleSpeed() * packet.getYOffset());
         double d3 = (double)(packet.getParticleSpeed() * packet.getZOffset());

         try {
            this.clientWorldController
               .spawnParticle(
                  packet.getParticleType(),
                  packet.isLongDistance(),
                  packet.getXCoordinate(),
                  packet.getYCoordinate(),
                  packet.getZCoordinate(),
                  d,
                  d2,
                  d3,
                  packet.getParticleArgs()
               );
         } catch (Throwable e) {
            LOGGER.warn("Could not spawn particle effect " + packet.getParticleType());
         }
      } else {
         for (int i = 0; i < packet.getParticleCount(); i++) {
            double d4 = this.avRandomizer.nextGaussian() * (double)packet.getXOffset();
            double d5 = this.avRandomizer.nextGaussian() * (double)packet.getYOffset();
            double d6 = this.avRandomizer.nextGaussian() * (double)packet.getZOffset();
            double d7 = this.avRandomizer.nextGaussian() * (double)packet.getParticleSpeed();
            double d8 = this.avRandomizer.nextGaussian() * (double)packet.getParticleSpeed();
            double d9 = this.avRandomizer.nextGaussian() * (double)packet.getParticleSpeed();

            try {
               this.clientWorldController
                  .spawnParticle(
                     packet.getParticleType(),
                     packet.isLongDistance(),
                     packet.getXCoordinate() + d4,
                     packet.getYCoordinate() + d5,
                     packet.getZCoordinate() + d6,
                     d7,
                     d8,
                     d9,
                     packet.getParticleArgs()
                  );
            } catch (Throwable e2) {
               LOGGER.warn("Could not spawn particle effect " + packet.getParticleType());
               return;
            }
         }
      }
   }

   @Override
   public void handleCustomPayload(S3FPacketCustomPayload packet) {
      PacketThreadUtil.checkThreadAndEnqueue(packet, this, this.gameController);
      if ("MC|TrList".equals(packet.getChannelName())) {
         PacketBuffer bufferData = packet.getBufferData();

         try {
            int n = bufferData.readInt();
            GuiScreen currentScreen = this.gameController.currentScreen;
            if (currentScreen != null && currentScreen instanceof GuiMerchant && n == this.gameController.thePlayer.openContainer.windowId) {
               ((GuiMerchant)currentScreen).getMerchant().setRecipes(MerchantRecipeList.readFromBuf(bufferData));
            }
         } catch (IOException e) {
            LOGGER.error("Couldn't load trade info", e);
         } finally {
            bufferData.release();
         }
      } else if ("MC|Brand".equals(packet.getChannelName())) {
         this.gameController.thePlayer.setClientBrand(packet.getBufferData().readStringFromBuffer(32767));
      } else if ("MC|BOpen".equals(packet.getChannelName())) {
         ItemStack currentEquippedItem = this.gameController.thePlayer.getCurrentEquippedItem();
         if (currentEquippedItem != null && currentEquippedItem.getItem() == Items.written_book) {
            this.gameController.displayGuiScreen(new GuiScreenBook(this.gameController.thePlayer, currentEquippedItem, false));
         }
      }
   }

   @Override
   public void handlePlayerListHeaderFooter(S47PacketPlayerListHeaderFooter packet) {
      this.gameController.ingameGUI.getTabList().setHeader(packet.getHeader().getFormattedText().length() == 0 ? null : packet.getHeader());
      this.gameController.ingameGUI.getTabList().setFooter(packet.getFooter().getFormattedText().length() == 0 ? null : packet.getFooter());
   }

   @Override
   public void handleSoundEffect(S29PacketSoundEffect packet) {
      PacketThreadUtil.checkThreadAndEnqueue(packet, this, this.gameController);
      this.gameController.theWorld.playSound(packet.getX(), packet.getY(), packet.getZ(), packet.getSoundName(), packet.getVolume(), packet.getPitch(), false);
   }

   @Override
   public void handleJoinGame(S01PacketJoinGame packet) {
      PacketThreadUtil.checkThreadAndEnqueue(packet, this, this.gameController);
      Minecraft gameController2 = this.gameController;
      gameController2.playerController = new PlayerControllerMP(this.gameController, this);
      this.clientWorldController = new WorldClient(
         this,
         new WorldSettings(0L, packet.getGameType(), false, packet.isHardcoreMode(), packet.getWorldType()),
         packet.getDimension(),
         packet.getDifficulty(),
         this.gameController.mcProfiler
      );
      this.gameController.gameSettings.difficulty = packet.getDifficulty();
      WorldClient clientWorldController2 = this.clientWorldController;
      this.gameController.loadWorld(clientWorldController2);
      this.gameController.thePlayer.dimension = packet.getDimension();
      gameController2 = this.gameController;
      gameController2.displayGuiScreen(ClientManagers.moduleManager.fixes.isNoTerrainDownloadEnabled() ? null : new GuiDownloadTerrain(this));
      this.gameController.thePlayer.setEntityId(packet.getEntityId());
      this.currentServerMaxPlayers = packet.getMaxPlayers();
      this.gameController.thePlayer.setReducedDebug(packet.isReducedDebugInfo());
      this.gameController.playerController.setGameType(packet.getGameType());
      this.gameController.gameSettings.sendSettingsToServer();
      if (!mc.isSingleplayer() && mc.getCurrentServerData() != null) {
         if (mc.getSession() != null) {
            mc.getSession().updatePlayTime(true);
         }
      }

      this.netManager
         .sendPacket(new C17PacketCustomPayload("MC|Brand", new PacketBuffer(Unpooled.buffer()).writeString(ClientBrandRetriever.getClientModName())));
   }

   @Override
   public void handleSpawnExperienceOrb(S11PacketSpawnExperienceOrb packet) {
      PacketThreadUtil.checkThreadAndEnqueue(packet, this, this.gameController);
      EntityXPOrb entityXPOrb = new EntityXPOrb(
         this.clientWorldController, (double)packet.getX() / 32.0, (double)packet.getY() / 32.0, (double)packet.getZ() / 32.0, packet.getXPValue()
      );
      entityXPOrb.serverPosX = packet.getX();
      entityXPOrb.serverPosY = packet.getY();
      entityXPOrb.serverPosZ = packet.getZ();
      entityXPOrb.rotationYaw = 0.0F;
      entityXPOrb.rotationPitch = 0.0F;
      entityXPOrb.setEntityId(packet.getEntityID());
      this.clientWorldController.addEntityToWorld(packet.getEntityID(), entityXPOrb);
   }

   @Override
   public void handleTitle(S45PacketTitle packet) {
      PacketThreadUtil.checkThreadAndEnqueue(packet, this, this.gameController);
      S45PacketTitle.Type type = packet.getType();
      String str = null;
      String str2 = null;
      String str3 = packet.getMessage() != null ? packet.getMessage().getFormattedText() : "";
      switch (type) {
         case TITLE:
            str = str3;
            break;
         case SUBTITLE:
            str2 = str3;
            break;
         case RESET:
            this.gameController.ingameGUI.displayTitle("", "", -1, -1, -1);
            this.gameController.ingameGUI.setDefaultTitlesTimes();
            return;
      }

      this.gameController.ingameGUI.displayTitle(str, str2, packet.getFadeInTime(), packet.getDisplayTime(), packet.getFadeOutTime());
   }

   public synchronized Map getPlayerInfoMap2() {
      return this.playerInfoMap;
   }

   @Override
   public void handleSpawnGlobalEntity(S2CPacketSpawnGlobalEntity packet) {
      PacketThreadUtil.checkThreadAndEnqueue(packet, this, this.gameController);
      double d = (double)packet.getX() / 32.0;
      double d2 = (double)packet.getY() / 32.0;
      double d3 = (double)packet.getZ() / 32.0;
      EntityLightningBolt entityLightningBolt = null;
      if (packet.getType() == 1) {
         entityLightningBolt = new EntityLightningBolt(this.clientWorldController, d, d2, d3);
      }

      if (entityLightningBolt != null) {
         entityLightningBolt.serverPosX = packet.getX();
         entityLightningBolt.serverPosY = packet.getY();
         entityLightningBolt.serverPosZ = packet.getZ();
         entityLightningBolt.rotationYaw = 0.0F;
         entityLightningBolt.rotationPitch = 0.0F;
         entityLightningBolt.setEntityId(packet.getEntityId());
         this.clientWorldController.addWeatherEffect(entityLightningBolt);
      }
   }

   @Override
   public void handleEffect(S28PacketEffect packet) {
      PacketThreadUtil.checkThreadAndEnqueue(packet, this, this.gameController);
      if (packet.isSoundServerwide()) {
         this.gameController.theWorld.playBroadcastSound(packet.getSoundType(), packet.getSoundPos(), packet.getSoundData());
      } else {
         this.gameController.theWorld.playAuxSFX(packet.getSoundType(), packet.getSoundPos(), packet.getSoundData());
      }
   }

   @Override
   public void handleDestroyEntities(S13PacketDestroyEntities packet) {
      PacketThreadUtil.checkThreadAndEnqueue(packet, this, this.gameController);

      for (int i = 0; i < packet.getEntityIDs().length; i++) {
         this.clientWorldController.removeEntityFromWorld(packet.getEntityIDs()[i]);
      }
   }

   @Override
   public void handleUpdateScore(S3CPacketUpdateScore packet) {
      PacketThreadUtil.checkThreadAndEnqueue(packet, this, this.gameController);
      Scoreboard scoreboard = this.clientWorldController.getScoreboard();
      ScoreObjective objective = scoreboard.getObjective(packet.getObjectiveName());
      if (packet.getScoreAction() == net.minecraft.network.play.server.S3CPacketUpdateScore.Action.CHANGE) {
         scoreboard.getValueFromObjective(packet.getPlayerName(), objective).setScorePoints(packet.getScoreValue());
      } else if (packet.getScoreAction() == net.minecraft.network.play.server.S3CPacketUpdateScore.Action.REMOVE) {
         if (StringUtils.isNullOrEmpty(packet.getObjectiveName())) {
            scoreboard.removeObjectiveFromEntity(packet.getPlayerName(), (ScoreObjective)null);
         } else if (objective != null) {
            scoreboard.removeObjectiveFromEntity(packet.getPlayerName(), objective);
         }
      }
   }

   @Override
   public void handleEntityAttach(S1BPacketEntityAttach packet) {
      PacketThreadUtil.checkThreadAndEnqueue(packet, this, this.gameController);
      Entity entityByID = this.clientWorldController.getEntityByID(packet.getEntityId());
      Entity entityByID2 = this.clientWorldController.getEntityByID(packet.getVehicleEntityId());
      if (packet.getLeash() == 0) {
         boolean flag = false;
         if (packet.getEntityId() == this.gameController.thePlayer.getEntityId()) {
            entityByID = this.gameController.thePlayer;
            if (entityByID2 instanceof EntityBoat) {
               ((EntityBoat)entityByID2).setIsBoatEmpty(false);
            }

            flag = ((Entity)entityByID).ridingEntity == null && entityByID2 != null;
         } else if (entityByID2 instanceof EntityBoat) {
            ((EntityBoat)entityByID2).setIsBoatEmpty(true);
         }

         if (entityByID == null) {
            return;
         }

         entityByID.mountEntity(entityByID2);
         if (flag) {
            GameSettings gameSettings = this.gameController.gameSettings;
            this.gameController
               .ingameGUI
               .setRecordPlaying(I18n.format("mount.onboard", new Object[]{GameSettings.getKeyDisplayString(gameSettings.keyBindSneak.getKeyCode())}), false);
         }
      } else if (packet.getLeash() == 1 && entityByID instanceof EntityLiving) {
         if (entityByID2 != null) {
            ((EntityLiving)entityByID).setLeashedToEntity(entityByID2, false);
         } else {
            ((EntityLiving)entityByID).clearLeashed(false, false);
         }
      }
   }

   public NetworkPlayerInfo getPlayerInfo(UUID uuid) {
      return (NetworkPlayerInfo)this.playerInfoMap.get(uuid);
   }

   @Override
   public void handleDisconnect(S40PacketDisconnect packet) {
      if (WDL.downloading) {
         WDL.stopDownload();

         try {
            Thread.sleep(2000L);
         } catch (Exception e) {
         }
      }

      if (mc.getSession() != null) {
         mc.getSession().updatePlayTime(false);
      }
      this.netManager.closeChannel(packet.getReason());
   }

   @Override
   public void handleMaps(S34PacketMaps packet) {
      PacketThreadUtil.checkThreadAndEnqueue(packet, this, this.gameController);
      MapData mapData = ItemMap.loadMapData(packet.getMapId(), this.gameController.theWorld);
      packet.setMapdataTo(mapData);
      this.gameController.entityRenderer.getMapItemRenderer().updateMapTexture(mapData);
      WDLHooks.onNHPCHandleMaps(this, packet);
   }

   @Override
   public void handlePlayerPosLook(S08PacketPlayerPosLook packet) {
      PacketThreadUtil.checkThreadAndEnqueue(packet, this, this.gameController);
      EntityPlayerSP thePlayer = this.gameController.thePlayer;
      double x = packet.getX();
      double y = packet.getY();
      double z = packet.getZ();
      float yaw = packet.getYaw();
      float pitch = packet.getPitch();
      Vec3 vec = new Vec3(mc.thePlayer.motionX, mc.thePlayer.motionY, mc.thePlayer.motionZ);
      double d = 0.0;
      double d2 = 0.0;
      double d3 = 0.0;
      double d4 = 0.0;
      double d5 = 0.0;
      double d6 = 0.0;
      boolean flag = ViaAugustus.targetProtocolVersion.newerThanOrEqualTo(ProtocolVersion.v1_17);
      if (packet.getFlags().contains(S08PacketPlayerPosLook.EnumFlags.X)) {
         if (flag) {
            d = vec.xCoord;
            d4 = thePlayer.posX + z;
            thePlayer.lastTickPosX += z;
            thePlayer.prevPosX += z;
         } else {
            x += thePlayer.posX;
         }
      } else if (flag) {
         d = 0.0;
         d4 = z;
         thePlayer.lastTickPosX = z;
         thePlayer.prevPosX = z;
      } else {
         thePlayer.motionX = 0.0;
      }

      if (packet.getFlags().contains(S08PacketPlayerPosLook.EnumFlags.Y)) {
         if (flag) {
            d2 = vec.yCoord;
            d5 = thePlayer.posY + y;
            thePlayer.lastTickPosY += y;
            thePlayer.prevPosY += y;
         } else {
            y += thePlayer.posY;
         }
      } else if (flag) {
         d2 = 0.0;
         d5 = y;
         thePlayer.lastTickPosY = y;
         thePlayer.prevPosY = y;
      } else {
         thePlayer.motionY = 0.0;
      }

      if (packet.getFlags().contains(S08PacketPlayerPosLook.EnumFlags.Z)) {
         if (flag) {
            d3 = vec.zCoord;
            d6 = thePlayer.posZ + x;
            thePlayer.lastTickPosZ += x;
            thePlayer.prevPosZ += x;
         } else {
            z += thePlayer.posZ;
         }
      } else if (flag) {
         d3 = 0.0;
         d6 = x;
         thePlayer.lastTickPosZ = x;
         thePlayer.prevPosZ = x;
      } else {
         thePlayer.motionZ = 0.0;
      }

      if (flag) {
         thePlayer.setPosition(d4, d5, d6);
         thePlayer.motionX = d;
         thePlayer.motionY = d2;
         thePlayer.motionZ = d3;
      }

      if (packet.getFlags().contains(S08PacketPlayerPosLook.EnumFlags.X_ROT)) {
         if (flag) {
            thePlayer.rotationPitch += pitch;
            thePlayer.prevRotationPitch = thePlayer.rotationPitch;
         } else {
            pitch += Augustus.getInstance().getRotationController().hasNoActiveOwner() ? thePlayer.rotationPitch : Augustus.getInstance().getRotationController().getCurrentRotation().pitch;
         }
      } else if (flag) {
         thePlayer.rotationPitch = pitch;
         thePlayer.prevRotationPitch = thePlayer.rotationPitch;
      }

      if (packet.getFlags().contains(S08PacketPlayerPosLook.EnumFlags.Y_ROT)) {
         if (flag) {
            thePlayer.rotationYaw += yaw;
            thePlayer.prevRotationYaw = thePlayer.rotationYaw;
         } else {
            yaw += Augustus.getInstance().getRotationController().hasNoActiveOwner() ? thePlayer.rotationYaw : Augustus.getInstance().getRotationController().getCurrentRotation().yaw;
         }
      } else if (flag) {
         thePlayer.rotationYaw = yaw;
         thePlayer.prevRotationYaw = thePlayer.rotationYaw;
      }

      thePlayer.setPositionAndRotation(x, y, z, yaw, pitch);
      if (!Augustus.getInstance().getModuleManager().noRotationSet.isToggled() || !Augustus.getInstance().getModuleManager().noRotationSet.mode.getSelectedValue().equalsIgnoreCase("Default")) {
         Augustus.getInstance().getYawPitchHelper().syncPlayerAngles(thePlayer, NoRotationSet.isRelativeZeroTeleport(packet));
      }

      this.netManager
         .sendPacket(
            new C03PacketPlayer.C06PacketPlayerPosLook(
               thePlayer.posX, thePlayer.getEntityBoundingBox().minY + (double)packet.getResponseYOffset(), thePlayer.posZ, thePlayer.rotationYaw, thePlayer.rotationPitch, false
            )
         );
      if (!this.doneLoadingTerrain) {
         this.gameController.thePlayer.prevPosX = this.gameController.thePlayer.posX;
         this.gameController.thePlayer.prevPosY = this.gameController.thePlayer.posY;
         this.gameController.thePlayer.prevPosZ = this.gameController.thePlayer.posZ;
         this.doneLoadingTerrain = true;
         GuiScreen screen = (GuiScreen)null;
         this.gameController.displayGuiScreen(screen);
      }
   }

   @Override
   public void handleSpawnMob(S0FPacketSpawnMob packet) {
      PacketThreadUtil.checkThreadAndEnqueue(packet, this, this.gameController);
      double d = (double)packet.getX() / 32.0;
      double d2 = (double)packet.getY() / 32.0;
      double d3 = (double)packet.getZ() / 32.0;
      float f = (float)(packet.getYaw() * 360) / 256.0F;
      float f2 = (float)(packet.getPitch() * 360) / 256.0F;
      EntityLivingBase entity = (EntityLivingBase)EntityList.createEntityByID(packet.getEntityType(), this.gameController.theWorld);
      entity.serverPosX = packet.getX();
      entity.serverPosY = packet.getY();
      entity.serverPosZ = packet.getZ();
      entity.setInitialServerPosition(packet.getX(), packet.getY(), packet.getZ());
      entity.rotationYawHead = (float)(packet.getHeadPitch() * 360) / 256.0F;
      Entity[] parts = entity.getParts();
      if (parts != null) {
         int n = packet.getEntityID() - entity.getEntityId();

         for (Entity entity2 : parts) {
            entity2.setEntityId(entity2.getEntityId() + n);
         }
      }

      entity.setEntityId(packet.getEntityID());
      entity.setPositionAndRotation(d, d2, d3, f, f2);
      entity.motionX = (double)((float)packet.getVelocityX() / 8000.0F);
      entity.motionY = (double)((float)packet.getVelocityY() / 8000.0F);
      entity.motionZ = (double)((float)packet.getVelocityZ() / 8000.0F);
      this.clientWorldController.addEntityToWorld(packet.getEntityID(), entity);
      List dataManagerEntries = packet.getDataManagerEntries();
      if (dataManagerEntries != null) {
         entity.getDataWatcher().updateWatchedObjectsFromList(dataManagerEntries);
      }
   }

   @Override
   public void handleEntityMovement(S14PacketEntity packet) {
      PacketThreadUtil.checkThreadAndEnqueue(packet, this, this.gameController);
      Entity entity = packet.getEntity(this.clientWorldController);
      if (entity != null) {
         entity.serverPosX = entity.serverPosX + packet.getPosX();
         entity.serverPosY = entity.serverPosY + packet.getPosY();
         entity.serverPosZ = entity.serverPosZ + packet.getPosZ();
         double d = (double)entity.serverPosX / 32.0;
         double d2 = (double)entity.serverPosY / 32.0;
         double d3 = (double)entity.serverPosZ / 32.0;
         float rotationYaw = entity.rotationYaw;
         float rotationPitch = entity.rotationPitch;
         if (entity instanceof EntityPlayerSP
            && ClientManagers.moduleManager.debug.isToggled()
            && ClientManagers.moduleManager.debug.devToggleG != null
            && ClientManagers.moduleManager.debug.devToggleG.getValue()) {
            ChatUtil.sendChat("WARNING!");
         }

         if (entity instanceof EntityPlayerSP && !Augustus.getInstance().getRotationController().hasNoActiveOwner()) {
            rotationYaw = Augustus.getInstance().getRotationController().getCurrentRotation().yaw;
            rotationPitch = Augustus.getInstance().getRotationController().getCurrentRotation().pitch;
         }

         entity.setPositionAndRotation2(d, d2, d3, packet.isRotating() ? (float)(packet.getYaw() * 360) / 256.0F : rotationYaw, packet.isRotating() ? (float)(packet.getPitch() * 360) / 256.0F : rotationPitch, 3, false);
         entity.onGround = packet.getOnGround();
      }
   }

   @Override
   public void handleCamera(S43PacketCamera packet) {
      PacketThreadUtil.checkThreadAndEnqueue(packet, this, this.gameController);
      Entity entity = packet.getEntity(this.clientWorldController);
      if (entity != null) {
         this.gameController.setRenderViewEntity(entity);
      }
   }

   public NetworkPlayerInfo getPlayerInfo(String str) {
      for (NetworkPlayerInfo networkPlayerInfo : (Iterable<NetworkPlayerInfo>)(this.playerInfoMap.values())) {
         if (networkPlayerInfo.getGameProfile().getName().equals(str)) {
            return networkPlayerInfo;
         }
      }

      return null;
   }

   @Override
   public void handleSpawnPainting(S10PacketSpawnPainting packet) {
      PacketThreadUtil.checkThreadAndEnqueue(packet, this, this.gameController);
      EntityPainting entityPainting = new EntityPainting(this.clientWorldController, packet.getPosition(), packet.getFacing(), packet.getTitle());
      this.clientWorldController.addEntityToWorld(packet.getEntityID(), entityPainting);
   }

   @Override
   public void handleUpdateSign(S33PacketUpdateSign packet) {
      PacketThreadUtil.checkThreadAndEnqueue(packet, this, this.gameController);
      boolean flag = false;
      if (this.gameController.theWorld.isBlockLoaded(packet.getPos())) {
         TileEntity tileEntity = this.gameController.theWorld.getTileEntity(packet.getPos());
         if (tileEntity instanceof TileEntitySign) {
            TileEntitySign tileEntitySign = (TileEntitySign)tileEntity;
            boolean flag2 = false;
            if (Augustus.getInstance().getModuleManager().exploitFixer.signCrash.getValue()) {
               for (IChatComponent component : packet.getLines()) {
                  if (component != null && component.getUnformattedText() != null && component.getUnformattedText().length() > 100) {
                     flag2 = true;
                  }
               }
            }

            if (tileEntitySign.getIsEditable() && !flag2) {
               System.arraycopy(packet.getLines(), 0, tileEntitySign.signText, 0, 4);
               tileEntitySign.markDirty();
            }

            flag = true;
         }
      }

      if (!flag && this.gameController.thePlayer != null) {
      }
   }

   @Override
   public void handleCombatEvent(S42PacketCombatEvent packet) {
      PacketThreadUtil.checkThreadAndEnqueue(packet, this, this.gameController);
      Entity entityByID = this.clientWorldController.getEntityByID(packet.entityId);
      if (packet.eventType == S42PacketCombatEvent.Event.ENTITY_DIED) {
         if (this.clientWorldController.getEntityByID(packet.playerId) instanceof EntityPlayer && Augustus.getInstance().getModuleManager().baritone.isToggled()) {
            IBaritone baritoneForConnection = BaritoneAPI.getProvider().getBaritoneForConnection(this);
            if (baritoneForConnection != null) {
               baritoneForConnection.getGameEventHandler().onPlayerDeath();
            }
         }
      }
   }

   @Override
   public void handleScoreboardObjective(S3BPacketScoreboardObjective packet) {
      PacketThreadUtil.checkThreadAndEnqueue(packet, this, this.gameController);
      Scoreboard scoreboard = this.clientWorldController.getScoreboard();
      if (packet.getAction() == 0) {
         ScoreObjective scoreObjective = scoreboard.addScoreObjective(packet.getObjectiveName(), IScoreObjectiveCriteria.DUMMY);
         scoreObjective.setDisplayName(packet.getObjectiveValue());
         scoreObjective.setRenderType(packet.getType());
      } else {
         ScoreObjective objective = scoreboard.getObjective(packet.getObjectiveName());
         if (packet.getAction() == 1) {
            scoreboard.removeObjective(objective);
         } else if (packet.getAction() == 2) {
            objective.setDisplayName(packet.getObjectiveValue());
            objective.setRenderType(packet.getType());
         }
      }
   }

   @Override
   public void handleSpawnPosition(S05PacketSpawnPosition packet) {
      PacketThreadUtil.checkThreadAndEnqueue(packet, this, this.gameController);
      this.gameController.thePlayer.setSpawnPoint(packet.getSpawnPos(), true);
      this.gameController.theWorld.getWorldInfo().setSpawn(packet.getSpawnPos());
   }

   @Override
   public void handleSetSlot(S2FPacketSetSlot packet) {
      PacketThreadUtil.checkThreadAndEnqueue(packet, this, this.gameController);
      EntityPlayerSP thePlayer = this.gameController.thePlayer;
      if (packet.getWindowId() == -1) {
         thePlayer.inventory.setItemStack(packet.getItem());
      } else {
         boolean flag = false;
         if (this.gameController.currentScreen instanceof GuiContainerCreative) {
            flag = ((GuiContainerCreative)this.gameController.currentScreen).getSelectedTabIndex() != CreativeTabs.tabInventory.getTabIndex();
         }

         if (packet.getWindowId() == 0 && packet.getSlot() >= 36 && packet.getSlot() < 45) {
            ItemStack stack = thePlayer.inventoryContainer.getSlot(packet.getSlot()).getStack();
            if (packet.getItem() != null && (stack == null || stack.stackSize < packet.getItem().stackSize)) {
               packet.getItem().animationsToGo = 5;
            }

            thePlayer.inventoryContainer.putStackInSlot(packet.getSlot(), packet.getItem());
         } else if (packet.getWindowId() == thePlayer.openContainer.windowId && (packet.getWindowId() != 0 || !flag)) {
            thePlayer.openContainer.putStackInSlot(packet.getSlot(), packet.getItem());
         }
      }
   }

   @Override
   public void onDisconnect(IChatComponent component) {
      if (WDL.downloading) {
         WDL.stopDownload();

         try {
            Thread.sleep(2000L);
         } catch (Exception e) {
         }
      }

      WorldClient world = (WorldClient)null;
      this.gameController.loadWorld(world);
      if (this.guiScreenServer != null) {
         this.gameController.displayGuiScreen(new GuiDisconnected(this.guiScreenServer, "disconnect.lost", component));
      } else {
         this.gameController.displayGuiScreen(new GuiDisconnected(new GuiMultiplayer(new GuiMainMenu()), "disconnect.lost", component));
      }
   }

   @Override
   public void handleTeams(S3EPacketTeams packet) {
      PacketThreadUtil.checkThreadAndEnqueue(packet, this, this.gameController);
      Scoreboard scoreboard = this.clientWorldController.getScoreboard();
      ScorePlayerTeam scorePlayerTeam;
      if (packet.getAction() == 0) {
         scorePlayerTeam = scoreboard.createTeam(packet.getName());
      } else {
         scorePlayerTeam = scoreboard.getTeam(packet.getName());
      }

      if (packet.getAction() == 0 || packet.getAction() == 2) {
         scorePlayerTeam.setTeamName(packet.getDisplayName());
         scorePlayerTeam.setNamePrefix(packet.getPrefix());
         scorePlayerTeam.setNameSuffix(packet.getSuffix());
         scorePlayerTeam.setChatFormat(EnumChatFormatting.fromColorIndex(packet.getColor()));
         scorePlayerTeam.setFriendlyFlags(packet.getFriendlyFlags());
         Team.EnumVisible byName = Team.EnumVisible.getByName(packet.getNameTagVisibility());
         if (byName != null) {
            scorePlayerTeam.setNameTagVisibility(byName);
         }
      }

      if (packet.getAction() == 0 || packet.getAction() == 3) {
         for (String str : (Iterable<String>)(packet.getPlayers())) {
            scoreboard.addPlayerToTeam(str, packet.getName());
         }
      }

      if (packet.getAction() == 4) {
         for (String str2 : (Iterable<String>)(packet.getPlayers())) {
            scoreboard.removePlayerFromTeam(str2, scorePlayerTeam);
         }
      }

      if (packet.getAction() == 1) {
         scoreboard.removeTeam(scorePlayerTeam);
      }
   }

   @Override
   public void handleSignEditorOpen(S36PacketSignEditorOpen packet) {
      PacketThreadUtil.checkThreadAndEnqueue(packet, this, this.gameController);
      TileEntity tileEntity = this.clientWorldController.getTileEntity(packet.getSignPosition());
      if (!(tileEntity instanceof TileEntitySign)) {
         tileEntity = new TileEntitySign();
         tileEntity.setWorldObj(this.clientWorldController);
         tileEntity.setPos(packet.getSignPosition());
      }

      this.gameController.thePlayer.openEditSign((TileEntitySign)tileEntity);
   }

   @Override
   public void handleEntityTeleport(S18PacketEntityTeleport packet) {
      PacketThreadUtil.checkThreadAndEnqueue(packet, this, this.gameController);
      Entity entityByID = this.clientWorldController.getEntityByID(packet.getEntityId());
      if (entityByID != null) {
         entityByID.serverPosX = packet.getX();
         entityByID.serverPosY = packet.getY();
         entityByID.serverPosZ = packet.getZ();
         double d = (double)entityByID.serverPosX / 32.0;
         double d2 = (double)entityByID.serverPosY / 32.0;
         double d3 = (double)entityByID.serverPosZ / 32.0;
         float f = (float)(packet.getYaw() * 360) / 256.0F;
         float f2 = (float)(packet.getPitch() * 360) / 256.0F;
         if (entityByID instanceof EntityPlayerSP
            && ClientManagers.moduleManager.debug.isToggled()
            && ClientManagers.moduleManager.debug.devToggleG != null
            && ClientManagers.moduleManager.debug.devToggleG.getValue()) {
            ChatUtil.sendChat("WARNING!");
         }

         if (Math.abs(entityByID.posX - d) < 0.03125 && Math.abs(entityByID.posY - d2) < 0.015625 && Math.abs(entityByID.posZ - d3) < 0.03125) {
            entityByID.setPositionAndRotation2(entityByID.posX, entityByID.posY, entityByID.posZ, f, f2, 3, true);
         } else {
            entityByID.setPositionAndRotation2(d, d2, d3, f, f2, 3, true);
         }

         entityByID.onGround = packet.getOnGround();
      }
   }

   @Override
   public void handleUseBed(S0APacketUseBed packet) {
      PacketThreadUtil.checkThreadAndEnqueue(packet, this, this.gameController);
      packet.getPlayer(this.clientWorldController).trySleep(packet.getBedPosition());
   }

   @Override
   public void handleRespawn(S07PacketRespawn packet) {
      PacketThreadUtil.checkThreadAndEnqueue(packet, this, this.gameController);
      if (packet.getDimensionID() != this.gameController.thePlayer.dimension) {
         this.doneLoadingTerrain = false;
         Scoreboard scoreboard = this.clientWorldController.getScoreboard();
         this.clientWorldController = new WorldClient(
            this,
            new WorldSettings(0L, packet.getGameType(), false, this.gameController.theWorld.getWorldInfo().isHardcoreModeEnabled(), packet.getWorldType()),
            packet.getDimensionID(),
            packet.getDifficulty(),
            this.gameController.mcProfiler
         );
         this.clientWorldController.setWorldScoreboard(scoreboard);
         WorldClient clientWorldController2 = this.clientWorldController;
         this.gameController.loadWorld(clientWorldController2);
         this.gameController.thePlayer.dimension = packet.getDimensionID();
         this.gameController.displayGuiScreen(ClientManagers.moduleManager.fixes.isNoTerrainDownloadEnabled() ? null : new GuiDownloadTerrain(this));
      }

      this.gameController.setDimensionAndSpawnPlayer(packet.getDimensionID());
      this.gameController.playerController.setGameType(packet.getGameType());
   }

   @Override
   public void handleRemoveEntityEffect(S1EPacketRemoveEntityEffect packet) {
      PacketThreadUtil.checkThreadAndEnqueue(packet, this, this.gameController);
      Entity entityByID = this.clientWorldController.getEntityByID(packet.getEntityId());
      if (entityByID instanceof EntityLivingBase) {
         ((EntityLivingBase)entityByID).removePotionEffectClient(packet.getEffectId());
      }
   }

   @Override
   public void handleSetCompressionLevel(S46PacketSetCompressionLevel packet) {
      if (!this.netManager.isLocalChannel()) {
         this.netManager.setCompressionTreshold(packet.getThreshold());
      }
   }

   @Override
   public void handleCloseWindow(S2EPacketCloseWindow packet) {
      PacketThreadUtil.checkThreadAndEnqueue(packet, this, this.gameController);
      this.gameController.thePlayer.closeScreenAndDropStack();
   }

   @Override
   public void handleMapChunkBulk(S26PacketMapChunkBulk packet) {
      PacketThreadUtil.checkThreadAndEnqueue(packet, this, this.gameController);

      for (int i = 0; i < packet.getChunkCount(); i++) {
         int chunkX = packet.getChunkX(i);
         int chunkZ = packet.getChunkZ(i);
         this.clientWorldController.doPreChunk(chunkX, chunkZ, true);
         this.clientWorldController.invalidateBlockReceiveRegion(chunkX << 4, 0, chunkZ << 4, (chunkX << 4) + 15, 256, (chunkZ << 4) + 15);
         Chunk chunkFromChunkCoords = this.clientWorldController.getChunkFromChunkCoords(chunkX, chunkZ);
         chunkFromChunkCoords.fillChunk(packet.getChunkBytes(i), packet.getChunkSize(i), true);
         this.clientWorldController.markBlockRangeForRenderUpdate(chunkX << 4, 0, chunkZ << 4, (chunkX << 4) + 15, 256, (chunkZ << 4) + 15);
         if (!(this.clientWorldController.provider instanceof WorldProviderSurface)) {
            chunkFromChunkCoords.resetRelightChecks();
         }

         ChunkLoadListener.onChunkLoad(chunkX, chunkZ);
      }
   }

   @Override
   public void handleUpdateHealth(S06PacketUpdateHealth packet) {
      PacketThreadUtil.checkThreadAndEnqueue(packet, this, this.gameController);
      this.gameController.thePlayer.setPlayerSPHealth(packet.getHealth());
      this.gameController.thePlayer.getFoodStats().setFoodLevel(packet.getFoodLevel());
      this.gameController.thePlayer.getFoodStats().setFoodSaturationLevel(packet.getSaturationLevel());
   }

   public void cleanup() {
      this.clientWorldController = null;
   }

   @Override
   public void handleEntityStatus(S19PacketEntityStatus packet) {
      PacketThreadUtil.checkThreadAndEnqueue(packet, this, this.gameController);
      Entity entity = packet.getEntity(this.clientWorldController);
      if (entity != null) {
         if (packet.getOpCode() == 21) {
            this.gameController.getSoundHandler().playSound(new GuardianSound((EntityGuardian)entity));
         } else {
            entity.handleStatusUpdate(packet.getOpCode());
         }
      }
   }

   @Override
   public void handleWorldBorder(S44PacketWorldBorder packet) {
      PacketThreadUtil.checkThreadAndEnqueue(packet, this, this.gameController);
      packet.apply(this.clientWorldController.getWorldBorder());
   }

   @Override
   public void handleChunkData(S21PacketChunkData packet) {
      PacketThreadUtil.checkThreadAndEnqueue(packet, this, this.gameController);
      if (packet.isFullChunk()) {
         if (packet.getExtractedSize() == 0) {
            this.clientWorldController.doPreChunk(packet.getChunkX(), packet.getChunkZ(), false);
            return;
         }

         this.clientWorldController.doPreChunk(packet.getChunkX(), packet.getChunkZ(), true);
      }

      this.clientWorldController
         .invalidateBlockReceiveRegion(packet.getChunkX() << 4, 0, packet.getChunkZ() << 4, (packet.getChunkX() << 4) + 15, 256, (packet.getChunkZ() << 4) + 15);
      Chunk chunkFromChunkCoords = this.clientWorldController.getChunkFromChunkCoords(packet.getChunkX(), packet.getChunkZ());
      if (Augustus.getInstance().getModuleManager().baritone.isToggled()) {
         IBaritone baritoneForConnection = BaritoneAPI.getProvider().getBaritoneForConnection(this);
         if (baritoneForConnection != null) {
            baritoneForConnection.getGameEventHandler()
               .onChunkEvent(
                  new ChunkEvent(
                     EventState.PRE,
                     packet.isFullChunk()
                        ? baritone.api.event.events.ChunkEvent.Type.POPULATE_FULL
                        : baritone.api.event.events.ChunkEvent.Type.POPULATE_PARTIAL,
                     packet.getChunkX(),
                     packet.getChunkZ()
                  )
               );
         }
      }

      chunkFromChunkCoords.fillChunk(packet.getExtractedDataBytes(), packet.getExtractedSize(), packet.isFullChunk());
      this.clientWorldController
         .markBlockRangeForRenderUpdate(packet.getChunkX() << 4, 0, packet.getChunkZ() << 4, (packet.getChunkX() << 4) + 15, 256, (packet.getChunkZ() << 4) + 15);
      if (!packet.isFullChunk() || !(this.clientWorldController.provider instanceof WorldProviderSurface)) {
         chunkFromChunkCoords.resetRelightChecks();
      }

      if (Augustus.getInstance().getModuleManager().baritone.isToggled()) {
         IBaritone baritoneForConnection2 = BaritoneAPI.getProvider().getBaritoneForConnection(this);
         if (baritoneForConnection2 != null) {
            baritoneForConnection2.getGameEventHandler()
               .onChunkEvent(
                  new ChunkEvent(
                     EventState.POST,
                     packet.isFullChunk()
                        ? baritone.api.event.events.ChunkEvent.Type.POPULATE_FULL
                        : baritone.api.event.events.ChunkEvent.Type.POPULATE_PARTIAL,
                     packet.getChunkX(),
                     packet.getChunkZ()
                  )
               );
         }
      }

      ChunkLoadListener.onChunkLoad(packet.getChunkX(), packet.getChunkZ());
   }

   @Override
   public void handleEntityNBT(S49PacketUpdateEntityNBT packet) {
      PacketThreadUtil.checkThreadAndEnqueue(packet, this, this.gameController);
      Entity entity = packet.getEntity(this.clientWorldController);
      if (entity != null) {
         entity.clientUpdateEntityNBT(packet.getTagCompound());
      }
   }

   @Override
   public void handleAnimation(S0BPacketAnimation packet) {
      PacketThreadUtil.checkThreadAndEnqueue(packet, this, this.gameController);
      Entity entityByID = this.clientWorldController.getEntityByID(packet.getEntityID());
      if (entityByID != null) {
         if (packet.getAnimationType() == 0) {
            ((EntityLivingBase)entityByID).swingItem();
         } else if (packet.getAnimationType() == 1) {
            entityByID.performHurtAnimation();
         } else if (packet.getAnimationType() == 2) {
            ((EntityPlayer)entityByID).wakeUpPlayer(false, false, false);
         } else if (packet.getAnimationType() == 4) {
            if (!Augustus.getInstance().getModuleManager().attackEffects.isToggled()) {
               this.gameController.effectRenderer.emitParticleAtEntity(entityByID, EnumParticleTypes.CRIT);
            }
         } else if (packet.getAnimationType() == 5 && !Augustus.getInstance().getModuleManager().attackEffects.isToggled()) {
            this.gameController.effectRenderer.emitParticleAtEntity(entityByID, EnumParticleTypes.CRIT_MAGIC);
         }
      }
   }

   @Override
   public void handleKeepAlive(S00PacketKeepAlive packet) {
      this.addToSendQueue(new C00PacketKeepAlive(packet.getId()));
   }

   public Collection getPlayerInfoMap() {
      return this.playerInfoMap.values();
   }

   public static NetworkManager accessNetManager(NetHandlerPlayClient netHandlerPlayClient) {
      return netHandlerPlayClient.netManager;
   }

   @Override
   public void handlePlayerListItem(S38PacketPlayerListItem packet) {
      PacketThreadUtil.checkThreadAndEnqueue(packet, this, this.gameController);

      for (S38PacketPlayerListItem.AddPlayerData addPlayerData : (Iterable<S38PacketPlayerListItem.AddPlayerData>)(packet.getPlayers())) {
         if (packet.getAction() == net.minecraft.network.play.server.S38PacketPlayerListItem.Action.REMOVE_PLAYER) {
            UUID id = addPlayerData.getProfile().getId();
            NetworkPlayerInfo networkPlayerInfo = (NetworkPlayerInfo)this.playerInfoMap.get(id);
            this.playerInfoMap.remove(id);
            if (networkPlayerInfo.getGameProfile() != null) {
               this.playerUuidMap.remove(networkPlayerInfo.getGameProfile().getName());
            }
         } else {
            NetworkPlayerInfo networkPlayerInfo2 = (NetworkPlayerInfo)this.playerInfoMap.get(addPlayerData.getProfile().getId());
            if (packet.getAction() == net.minecraft.network.play.server.S38PacketPlayerListItem.Action.ADD_PLAYER) {
               networkPlayerInfo2 = new NetworkPlayerInfo(addPlayerData);
               GameProfile gameProfile = networkPlayerInfo2.getGameProfile();
               this.playerInfoMap.put(gameProfile.getId(), networkPlayerInfo2);
               this.playerUuidMap.put(gameProfile.getName(), gameProfile.getId().toString());
            }

            if (networkPlayerInfo2 != null) {
               switch (packet.getAction()) {
                  case ADD_PLAYER:
                     networkPlayerInfo2.setGameType(addPlayerData.getGameMode());
                     networkPlayerInfo2.setResponseTime(addPlayerData.getPing());
                     break;
                  case UPDATE_GAME_MODE:
                     networkPlayerInfo2.setGameType(addPlayerData.getGameMode());
                     break;
                  case UPDATE_LATENCY:
                     networkPlayerInfo2.setResponseTime(addPlayerData.getPing());
                     break;
                  case UPDATE_DISPLAY_NAME:
                     networkPlayerInfo2.setDisplayName(addPlayerData.getDisplayName());
               }
            }
         }
      }
   }

   @Override
   public void handleHeldItemChange(S09PacketHeldItemChange packet) {
      PacketThreadUtil.checkThreadAndEnqueue(packet, this, this.gameController);
      if (packet.getHeldItemHotbarIndex() >= 0 && packet.getHeldItemHotbarIndex() < InventoryPlayer.getHotbarSize()) {
         this.gameController.thePlayer.inventory.currentItem = packet.getHeldItemHotbarIndex();
         SilentSlotManager.realHotbarSlot = packet.getHeldItemHotbarIndex();
      }
   }

   @Override
   public void handleOpenWindow(S2DPacketOpenWindow packet) {
      PacketThreadUtil.checkThreadAndEnqueue(packet, this, this.gameController);
      EntityPlayerSP thePlayer = this.gameController.thePlayer;
      if ("minecraft:container".equals(packet.getGuiId())) {
         thePlayer.displayGUIChest(new InventoryBasic(packet.getWindowTitle(), packet.getSlotCount()));
         thePlayer.openContainer.windowId = packet.getWindowId();
      } else if ("minecraft:villager".equals(packet.getGuiId())) {
         thePlayer.displayVillagerTradeGui(new NpcMerchant(thePlayer, packet.getWindowTitle()));
         thePlayer.openContainer.windowId = packet.getWindowId();
      } else if ("EntityHorse".equals(packet.getGuiId())) {
         Entity entityByID = this.clientWorldController.getEntityByID(packet.getEntityId());
         if (entityByID instanceof EntityHorse) {
            thePlayer.displayGUIHorse((EntityHorse)entityByID, new AnimalChest(packet.getWindowTitle(), packet.getSlotCount()));
            thePlayer.openContainer.windowId = packet.getWindowId();
         }
      } else if (!packet.hasSlots()) {
         thePlayer.displayGui(new LocalBlockIntercommunication(packet.getGuiId(), packet.getWindowTitle()));
         thePlayer.openContainer.windowId = packet.getWindowId();
      } else {
         thePlayer.displayGUIChest(new ContainerLocalMenu(packet.getGuiId(), packet.getWindowTitle(), packet.getSlotCount()));
         thePlayer.openContainer.windowId = packet.getWindowId();
      }
   }

   public GameProfile getGameProfile() {
      return this.profile;
   }

   public NetworkManager getNetworkManager() {
      return this.netManager;
   }

   @Override
   public void handleBlockBreakAnim(S25PacketBlockBreakAnim packet) {
      PacketThreadUtil.checkThreadAndEnqueue(packet, this, this.gameController);
      this.gameController.theWorld.sendBlockBreakProgress(packet.getBreakerId(), packet.getPosition(), packet.getProgress());
   }

   @Override
   public void handleSetExperience(S1FPacketSetExperience packet) {
      PacketThreadUtil.checkThreadAndEnqueue(packet, this, this.gameController);
      this.gameController.thePlayer.setXPStats(packet.getExperienceBar(), packet.getTotalExperience(), packet.getLevel());
   }

   @Override
   public void handleBlockAction(S24PacketBlockAction packet) {
      PacketThreadUtil.checkThreadAndEnqueue(packet, this, this.gameController);
      this.gameController.theWorld.addBlockEvent(packet.getBlockPosition(), packet.getBlockType(), packet.getData1(), packet.getData2());
      WDLHooks.onNHPCHandleBlockAction(this, packet);
   }

   @Override
   public void handleBlockChange(S23PacketBlockChange packet) {
      PacketThreadUtil.checkThreadAndEnqueue(packet, this, this.gameController);
      this.clientWorldController.invalidateRegionAndSetBlock(packet.getBlockPosition(), packet.getBlockState());
      if (Augustus.getInstance().getModuleManager().baritone.isToggled()) {
         IBaritone baritoneForConnection = BaritoneAPI.getProvider().getBaritoneForConnection(this);
         if (baritoneForConnection == null) {
            return;
         }

         ChunkPos chunkPos = new ChunkPos(packet.getBlockPosition().getX() >> 4, packet.getBlockPosition().getZ() >> 4);
         Pair pair = new Pair(packet.getBlockPosition(), packet.getBlockState());
         baritoneForConnection.getGameEventHandler().onBlockChange(new BlockChangeEvent(chunkPos, Collections.singletonList(pair)));
      }
   }

   @Override
   public void handleMultiBlockChange(S22PacketMultiBlockChange packet) {
      PacketThreadUtil.checkThreadAndEnqueue(packet, this, this.gameController);

      for (S22PacketMultiBlockChange.BlockUpdateData blockUpdateData : packet.getChangedBlocks()) {
         this.clientWorldController.invalidateRegionAndSetBlock(blockUpdateData.getPos(), blockUpdateData.getBlockState());
      }

      if (Augustus.getInstance().getModuleManager().baritone.isToggled()) {
         IBaritone baritoneForConnection = BaritoneAPI.getProvider().getBaritoneForConnection(this);
         if (baritoneForConnection == null) {
            return;
         }

         ChunkPos chunkPos = new ChunkPos(packet.getChangedBlocks()[0].getPos());
         baritoneForConnection.getGameEventHandler()
            .onBlockChange(
               new BlockChangeEvent(
                  chunkPos, Arrays.stream(packet.getChangedBlocks()).map(blockUpdateData2 -> new Pair(blockUpdateData2.getPos(), blockUpdateData2.getBlockState())).collect(Collectors.toList())
               )
            );
      }
   }

   @Override
   public void handleWindowItems(S30PacketWindowItems packet) {
      PacketThreadUtil.checkThreadAndEnqueue(packet, this, this.gameController);
      EntityPlayerSP thePlayer = this.gameController.thePlayer;
      if (packet.getWindowId() == 0) {
         thePlayer.inventoryContainer.putStacksInSlots(packet.getItemStacks());
      } else if (packet.getWindowId() == thePlayer.openContainer.windowId) {
         thePlayer.openContainer.putStacksInSlots(packet.getItemStacks());
      }
   }

   public NetHandlerPlayClient(Minecraft mc2, GuiScreen screen, NetworkManager networkManager, GameProfile profile2) {
      this.gameController = mc2;
      this.guiScreenServer = screen;
      this.netManager = networkManager;
      this.profile = profile2;
   }

   @Override
   public void handleServerDifficulty(S41PacketServerDifficulty packet) {
      PacketThreadUtil.checkThreadAndEnqueue(packet, this, this.gameController);
      this.gameController.theWorld.getWorldInfo().setDifficulty(packet.getDifficulty());
      this.gameController.theWorld.getWorldInfo().setDifficultyLocked(packet.isDifficultyLocked());
   }

   @Override
   public void handleChat(S02PacketChat packet) {
      PacketThreadUtil.checkThreadAndEnqueue(packet, this, this.gameController);
      if (packet.getType() == 2) {
         this.gameController.ingameGUI.setRecordPlaying(packet.getChatComponent(), false);
      } else {
         this.gameController.ingameGUI.getChatGUI().printChatMessage(packet.getChatComponent());
      }

      WDLHooks.onNHPCHandleChat(this, packet);
   }

   @Override
   public void handleTabComplete(S3APacketTabComplete packet) {
      PacketThreadUtil.checkThreadAndEnqueue(packet, this, this.gameController);
      String[] matches = packet.getMatches();
      if (this.gameController.currentScreen instanceof GuiChat) {
         ((GuiChat)this.gameController.currentScreen).onAutocompleteResponse(matches);
      }
   }

   @Override
   public void handleEntityProperties(S20PacketEntityProperties packet) {
      PacketThreadUtil.checkThreadAndEnqueue(packet, this, this.gameController);
      Entity entityByID = this.clientWorldController.getEntityByID(packet.getEntityId());
      if (entityByID != null) {
         if (!(entityByID instanceof EntityLivingBase)) {
            throw new IllegalStateException("Server tried to update attributes of a non-living entity (actually: " + entityByID + ")");
         }

         BaseAttributeMap attributeMap = ((EntityLivingBase)entityByID).getAttributeMap();

         for (S20PacketEntityProperties.Snapshot snapshot : (Iterable<S20PacketEntityProperties.Snapshot>)(packet.getSnapshots())) {
            IAttributeInstance attributeInstance = attributeMap.getAttributeInstanceByName(snapshot.getName());
            if (attributeInstance == null) {
               attributeInstance = attributeMap.registerAttribute(new RangedAttribute((IAttribute)null, snapshot.getName(), 0.0, Double.MIN_NORMAL, Double.MAX_VALUE));
            }

            attributeInstance.setBaseValue(snapshot.getBaseValue());
            attributeInstance.removeAllModifiers();

            for (AttributeModifier attributeModifier : (Iterable<AttributeModifier>)(snapshot.getModifiers())) {
               attributeInstance.applyModifier(attributeModifier);
            }
         }
      }
   }

   @Override
   public void handleSpawnPlayer(S0CPacketSpawnPlayer packet) {
      PacketThreadUtil.checkThreadAndEnqueue(packet, this, this.gameController);
      double d = (double)packet.getX() / 32.0;
      double d2 = (double)packet.getY() / 32.0;
      double d3 = (double)packet.getZ() / 32.0;
      float f = (float)(packet.getYaw() * 360) / 256.0F;
      float f2 = (float)(packet.getPitch() * 360) / 256.0F;
      EntityOtherPlayerMP entityOtherPlayerMP = new EntityOtherPlayerMP(this.gameController.theWorld, this.getPlayerInfo(packet.getPlayer()).getGameProfile());
      entityOtherPlayerMP.prevPosX = entityOtherPlayerMP.lastTickPosX = (double)(entityOtherPlayerMP.serverPosX = packet.getX());
      entityOtherPlayerMP.prevPosY = entityOtherPlayerMP.lastTickPosY = (double)(entityOtherPlayerMP.serverPosY = packet.getY());
      entityOtherPlayerMP.prevPosZ = entityOtherPlayerMP.lastTickPosZ = (double)(entityOtherPlayerMP.serverPosZ = packet.getZ());
      entityOtherPlayerMP.setInitialServerPosition(packet.getX(), packet.getY(), packet.getZ());
      int currentItemID = packet.getCurrentItemID();
      if (currentItemID == 0) {
         entityOtherPlayerMP.inventory.mainInventory[entityOtherPlayerMP.inventory.currentItem] = null;
      } else {
         entityOtherPlayerMP.inventory.mainInventory[entityOtherPlayerMP.inventory.currentItem] = new ItemStack(Item.getItemById(currentItemID), 1, 0);
      }

      entityOtherPlayerMP.setPositionAndRotation(d, d2, d3, f, f2);
      if (packet.getEntityID() == Minecraft.getMinecraft().thePlayer.getEntityId()) {
         Augustus.getInstance().getYawPitchHelper().syncPlayerAngles(entityOtherPlayerMP, false);
      }

      this.clientWorldController.addEntityToWorld(packet.getEntityID(), entityOtherPlayerMP);
      List dataManagerEntries = packet.getDataManagerEntries();
      if (dataManagerEntries != null) {
         entityOtherPlayerMP.getDataWatcher().updateWatchedObjectsFromList(dataManagerEntries);
      }
   }

   @Override
   public void handlePlayerAbilities(S39PacketPlayerAbilities packet) {
      PacketThreadUtil.checkThreadAndEnqueue(packet, this, this.gameController);
      EntityPlayerSP thePlayer = this.gameController.thePlayer;
      thePlayer.capabilities.isFlying = packet.isFlying();
      thePlayer.capabilities.isCreativeMode = packet.isCreativeMode();
      thePlayer.capabilities.disableDamage = packet.isInvulnerable();
      thePlayer.capabilities.allowFlying = packet.isAllowFlying();
      thePlayer.capabilities.setFlySpeed(packet.getFlySpeed());
      thePlayer.capabilities.setPlayerWalkSpeed(packet.getWalkSpeed());
   }

   @Override
   public void handleStatistics(S37PacketStatistics packet) {
      PacketThreadUtil.checkThreadAndEnqueue(packet, this, this.gameController);
      boolean flag = false;

      for (Entry entry : (Iterable<Entry>)(packet.getStatisticMap().entrySet())) {
         StatBase key = (StatBase)entry.getKey();
         int value = (Integer)entry.getValue();
         if (key.isAchievement() && value > 0) {
            if (this.hasStatistics && this.gameController.thePlayer.getStatFileWriter().readStat(key) == 0) {
               Achievement achievement = (Achievement)key;
               this.gameController.guiAchievement.displayAchievement(achievement);
               if (key == AchievementList.openInventory) {
                  this.gameController.gameSettings.showInventoryAchievementHint = false;
                  this.gameController.gameSettings.saveOptions();
               }
            }

            flag = true;
         }

         this.gameController.thePlayer.getStatFileWriter().unlockAchievement(this.gameController.thePlayer, key, value);
      }

      if (!this.hasStatistics && !flag && this.gameController.gameSettings.showInventoryAchievementHint) {
         this.gameController.guiAchievement.displayUnformattedAchievement(AchievementList.openInventory);
      }

      this.hasStatistics = true;
      if (this.gameController.currentScreen instanceof IProgressMeter) {
         ((IProgressMeter)this.gameController.currentScreen).doneLoading();
      }
   }

   @Override
   public void handleEntityHeadLook(S19PacketEntityHeadLook packet) {
      PacketThreadUtil.checkThreadAndEnqueue(packet, this, this.gameController);
      Entity entity = packet.getEntity(this.clientWorldController);
      if (entity != null) {
         entity.setRotationYawHead((float)(packet.getYaw() * 360) / 256.0F);
         if (entity instanceof EntityPlayerSP
            && ClientManagers.moduleManager.debug.isToggled()
            && ClientManagers.moduleManager.debug.devToggleG != null
            && ClientManagers.moduleManager.debug.devToggleG.getValue()) {
            ChatUtil.sendChat("WARNING!");
         }
      }
   }

   @Override
   public void handleUpdateTileEntity(S35PacketUpdateTileEntity packet) {
      PacketThreadUtil.checkThreadAndEnqueue(packet, this, this.gameController);
      if (this.gameController.theWorld.isBlockLoaded(packet.getPos())) {
         TileEntity tileEntity = this.gameController.theWorld.getTileEntity(packet.getPos());
         int tileEntityType = packet.getTileEntityType();
         if (tileEntityType == 1 && tileEntity instanceof TileEntityMobSpawner
            || tileEntityType == 2 && tileEntity instanceof TileEntityCommandBlock
            || tileEntityType == 3 && tileEntity instanceof TileEntityBeacon
            || tileEntityType == 4 && tileEntity instanceof TileEntitySkull
            || tileEntityType == 5 && tileEntity instanceof TileEntityFlowerPot
            || tileEntityType == 6 && tileEntity instanceof TileEntityBanner) {
            tileEntity.readFromNBT(packet.getNbtCompound());
         }
      }
   }

   @Override
   public void handleCollectItem(S0DPacketCollectItem packet) {
      PacketThreadUtil.checkThreadAndEnqueue(packet, this, this.gameController);
      Entity entityByID = this.clientWorldController.getEntityByID(packet.getCollectedItemEntityID());
      Object entityByID2 = (EntityLivingBase)this.clientWorldController.getEntityByID(packet.getEntityID());
      if (entityByID2 == null) {
         entityByID2 = this.gameController.thePlayer;
      }

      if (entityByID != null) {
         if (entityByID instanceof EntityXPOrb) {
            this.clientWorldController
               .playSoundAtEntity(entityByID, "random.orb", 0.2F, ((this.avRandomizer.nextFloat() - this.avRandomizer.nextFloat()) * 0.7F + 1.0F) * 2.0F);
         } else {
            this.clientWorldController
               .playSoundAtEntity(entityByID, "random.pop", 0.2F, ((this.avRandomizer.nextFloat() - this.avRandomizer.nextFloat()) * 0.7F + 1.0F) * 2.0F);
         }

         this.gameController.effectRenderer.addEffect(new EntityPickupFX(this.clientWorldController, entityByID, (Entity)entityByID2, 0.5F));
         this.clientWorldController.removeEntityFromWorld(packet.getCollectedItemEntityID());
      }
   }

   @Override
   public void handleEntityEffect(S1DPacketEntityEffect packet) {
      PacketThreadUtil.checkThreadAndEnqueue(packet, this, this.gameController);
      Entity entityByID = this.clientWorldController.getEntityByID(packet.getEntityId());
      if (entityByID instanceof EntityLivingBase) {
         PotionEffect potionEffect = new PotionEffect(packet.getEffectId(), packet.getDuration(), packet.getAmplifier(), false, packet.doesShowParticles());
         potionEffect.setPotionDurationMax(packet.isMaxDuration());
         ((EntityLivingBase)entityByID).addPotionEffect(potionEffect);
      }
   }

   @Override
   public void handleWindowProperty(S31PacketWindowProperty packet) {
      PacketThreadUtil.checkThreadAndEnqueue(packet, this, this.gameController);
      EntityPlayerSP thePlayer = this.gameController.thePlayer;
      if (thePlayer.openContainer != null && thePlayer.openContainer.windowId == packet.getWindowId()) {
         thePlayer.openContainer.updateProgressBar(packet.getVarIndex(), packet.getVarValue());
      }
   }

public static class DefaultFutureCallback implements FutureCallback {
      public final String hash;
      public final NetHandlerPlayClient netHandlerPlayClient;

      public void onFailure(Throwable t) {
         NetHandlerPlayClient.accessNetManager(this.netHandlerPlayClient).sendPacket(new C19PacketResourcePackStatus(this.hash, C19PacketResourcePackStatus.Action.FAILED_DOWNLOAD));
      }

      public void onSuccess(Object obj) {
         NetHandlerPlayClient.accessNetManager(this.netHandlerPlayClient).sendPacket(new C19PacketResourcePackStatus(this.hash, C19PacketResourcePackStatus.Action.SUCCESSFULLY_LOADED));
      }

      public DefaultFutureCallback(NetHandlerPlayClient netHandlerPlayClient2, String str) {
         this.netHandlerPlayClient = netHandlerPlayClient2;
         this.hash = str;
      }
   }

   public static class DefaultFutureCallback2 implements FutureCallback {
      public final String hash;
      public final NetHandlerPlayClient netHandlerPlayClient;

      public void onSuccess(Object obj) {
         NetHandlerPlayClient.accessNetManager(this.netHandlerPlayClient).sendPacket(new C19PacketResourcePackStatus(this.hash, C19PacketResourcePackStatus.Action.SUCCESSFULLY_LOADED));
      }

      public DefaultFutureCallback2(NetHandlerPlayClient netHandlerPlayClient2, String str) {
         this.netHandlerPlayClient = netHandlerPlayClient2;
         this.hash = str;
      }

      public void onFailure(Throwable t) {
         NetHandlerPlayClient.accessNetManager(this.netHandlerPlayClient).sendPacket(new C19PacketResourcePackStatus(this.hash, C19PacketResourcePackStatus.Action.FAILED_DOWNLOAD));
      }
   }

   public static class DefaultFutureCallback3 implements FutureCallback {
      public final String hash;
      public final NetHandlerPlayClient netHandlerPlayClient;

      public void onFailure(Throwable t) {
         NetHandlerPlayClient.accessNetManager(this.netHandlerPlayClient).sendPacket(new C19PacketResourcePackStatus(this.hash, C19PacketResourcePackStatus.Action.FAILED_DOWNLOAD));
      }

      public void onSuccess(Object obj) {
         NetHandlerPlayClient.accessNetManager(this.netHandlerPlayClient).sendPacket(new C19PacketResourcePackStatus(this.hash, C19PacketResourcePackStatus.Action.SUCCESSFULLY_LOADED));
      }

      public DefaultFutureCallback3(NetHandlerPlayClient netHandlerPlayClient2, String str) {
         this.netHandlerPlayClient = netHandlerPlayClient2;
         this.hash = str;
      }
   }

}
