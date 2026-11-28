package net.augustus.modules.latency;

import com.google.common.collect.Queues;
import java.awt.Color;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import net.augustus.modules.Categorys;
import net.augustus.modules.Module;
import net.minecraft.network.play.client.CPacketPlayerTryUseItemOnBlock;
import net.augustus.utils.network.PacketFlushMarker;
import net.augustus.utils.rotation.RotationUtil;
import net.augustus.ClientManagers;
import net.augustus.settings.MultiOptionSetting;
import net.augustus.events.EventPacketDelay;
import net.augustus.events.listeners.PacketDelayListener;
import net.augustus.modules.latency.helper.DelayedPacketEntry;
import net.augustus.modules.latency.helper.DelayedOutboundPacket;
import net.augustus.settings.options.PacketHandlerPacketOption;
import net.augustus.viaversion.packet.C0APacketSwingHand;
import net.augustus.events.listeners.TickListener;
import net.augustus.events.EventManager;
import net.augustus.settings.BooleanValue;
import net.augustus.settings.StringValue;
import net.augustus.settings.SettingGroup;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketThreadUtil;
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
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Vec3;

public class PacketHandler extends Module implements PacketDelayListener, TickListener {
   private static final Map INCOMING_DELAY_OPTIONS = new HashMap();
   private static final Map INCOMING_ABORT_OPTIONS = new HashMap();
   private static final Map OUTGOING_DELAY_OPTIONS = new HashMap();
   private static final Map OUTGOING_ABORT_OPTIONS = new HashMap();
   public Queue incomingPacketQueue;
   public Queue outgoingPacketQueue;
   private final BooleanValue pingFix;
   public final SettingGroup incoming;
   public final StringValue macroDelayModes;
   private final BooleanValue realTimeDamage;
   public final StringValue eventToHandlePacket;
   public final MultiOptionSetting incomingPacketsToDelay;
   private final SettingGroup abortIncoming;
   public final MultiOptionSetting incomingPacketsToDelayAbort;
   public final SettingGroup outgoing;
   public final MultiOptionSetting outGoingPacketsToDelay;
   private final SettingGroup abortOutgoing;
   public final MultiOptionSetting outGoingPacketsToDelayAbort;
   private boolean flushingPackets;
   private volatile boolean delayingIncoming;
   private volatile int consecutiveTransactionCount;

   public PacketHandler() {
       super("PacketHandler", "PacketHandler", true, Color.red, Categorys.LATENCY);
      this.incomingPacketQueue = Queues.newConcurrentLinkedQueue();
      this.outgoingPacketQueue = Queues.newConcurrentLinkedQueue();
      this.pingFix = new BooleanValue(this, "PingFix", false);
      this.incoming = new SettingGroup(this, "Incoming", true);
      this.macroDelayModes = new StringValue(
         this, "MacroDelayModes", "None", new String[]{"None", "MacroDelay", "OtherMacroDelay", "ExperimentalMacroDelay", "Sandwich"}, this.incoming
      );
      this.realTimeDamage = new BooleanValue(this, "RealTimeDamage", true, this.incoming);
      this.eventToHandlePacket = new StringValue(this, "EventToHandlePacket", "Vanilla", new String[]{"Vanilla", "Tick"}, this.incoming);
      this.incomingPacketsToDelay = new MultiOptionSetting(this, "IncomingPacketsToDelay", sortedPacketOptions(INCOMING_DELAY_OPTIONS), this.incoming).named("PacketsToDelay");
      this.abortIncoming = new SettingGroup(this, "AbortIncoming", this.incoming);
      this.incomingPacketsToDelayAbort = new MultiOptionSetting(this, "IncomingPacketsToDelayAbort", sortedPacketOptions(INCOMING_ABORT_OPTIONS), this.abortIncoming).named("PacketsToAbortDelay");
      this.outgoing = new SettingGroup(this, "Outgoing", true);
      this.outGoingPacketsToDelay = new MultiOptionSetting(this, "OutGoingPacketsToDelay", sortedPacketOptions(OUTGOING_DELAY_OPTIONS), this.outgoing).named("PacketsToDelay");
      this.abortOutgoing = new SettingGroup(this, "AbortOutgoing", this.outgoing);
      this.outGoingPacketsToDelayAbort = new MultiOptionSetting(this, "OutGoingPacketsToDelayAbort", sortedPacketOptions(OUTGOING_ABORT_OPTIONS), this.abortOutgoing).named("PacketsToAbortDelay");
      this.flushingPackets = false;
      this.consecutiveTransactionCount = 2;
      EventManager.getEventBus().subscribe(25, this, 2147483640);
      EventManager.getEventBus().subscribe(40, this);
   }

   @Override
   public void onEnable() {
   }

   @Override
   public void onDisable() {
   }

   @Override
   public void updateEventSubscriptions() {
   }

   @Override
   public void onTick() {
   }

   @Override
   public void onIncomingPacketDelay(EventPacketDelay event) {
      this.delayingIncoming = event.isDelayed() || event.getIncomingDelayMs() > 0;
   }

   public void enqueueIncomingPacket(EventPacketDelay event) {
      DelayedPacketEntry delayedPacketEntry = new DelayedPacketEntry(event.getPacket());
      this.incomingPacketQueue.add(delayedPacketEntry);
      if (event.getPacket() instanceof S07PacketRespawn) {
         this.flushAllIncomingPackets();
      }
   }

   public void enqueueOutgoingPacket(EventPacketDelay event) {
      this.outgoingPacketQueue.add(new DelayedOutboundPacket(event.getPacket(), null));
   }

   public boolean isDelayingIncoming() {
      return this.delayingIncoming;
   }

   public boolean hasQueuedOutgoingPackets() {
      return !this.outgoingPacketQueue.isEmpty();
   }

   public void flushAllIncomingPackets() {

      while (!this.incomingPacketQueue.isEmpty()) {
         this.processDelayedIncomingEntry((DelayedPacketEntry)this.incomingPacketQueue.poll());
      }
   }

   public void flushExpiredIncomingPackets(int n) {
      if (!this.incomingPacketQueue.isEmpty()) {
         LinkedBlockingQueue linkedBlockingQueue = Queues.newLinkedBlockingQueue();
         Iterator it = this.incomingPacketQueue.iterator();

         while (it.hasNext()) {
            DelayedPacketEntry delayedPacketEntry = (DelayedPacketEntry)it.next();
            if (delayedPacketEntry.isExpired((long)n)) {
               if (this.shouldHoldTransactionInMacroDelay(delayedPacketEntry.getPacket())) {
                  linkedBlockingQueue.add(delayedPacketEntry);
               } else {
                  while (!linkedBlockingQueue.isEmpty()) {
                     DelayedPacketEntry delayedPacketEntry2 = (DelayedPacketEntry)linkedBlockingQueue.poll();
                     this.processDelayedIncomingEntry(delayedPacketEntry2);
                     this.incomingPacketQueue.remove(delayedPacketEntry2);
                  }

                  this.processDelayedIncomingEntry(delayedPacketEntry);
                  it.remove();
               }
            }
         }
      }
   }

   public void flushIncomingUntilTargetRange(double d, EntityLivingBase entity) {
      EntityPlayerSP thePlayer = mc.thePlayer;
      if (!this.incomingPacketQueue.isEmpty() && entity != null && thePlayer != null) {
         LinkedBlockingQueue linkedBlockingQueue = Queues.newLinkedBlockingQueue();
         Vec3 positionEyes = thePlayer.getPositionEyes(1.0F);
         Iterator it = this.incomingPacketQueue.iterator();

         while (it.hasNext()) {
            DelayedPacketEntry delayedPacketEntry = (DelayedPacketEntry)it.next();
            Vec3 vec = this.getRecordedEntityPosForPacket(delayedPacketEntry.getPacket(), entity);
            if (vec == null) {
               linkedBlockingQueue.add(delayedPacketEntry);
            } else {
               if (!(positionEyes.distanceTo(RotationUtil.clampPointToBox(positionEyes, RotationUtil.createEntityBoxAt(entity, vec))) < d)) {
                  return;
               }

               while (!linkedBlockingQueue.isEmpty()) {
                  DelayedPacketEntry delayedPacketEntry2 = (DelayedPacketEntry)linkedBlockingQueue.poll();
                  this.processDelayedIncomingEntry(delayedPacketEntry2);
                  this.incomingPacketQueue.remove(delayedPacketEntry2);
               }

               this.processDelayedIncomingEntry(delayedPacketEntry);
               it.remove();
            }
         }
      }
   }

   private Vec3 getRecordedEntityPosForPacket(Packet packet, EntityLivingBase entity) {
      return this.extractEntityIdFromPacket(packet) != entity.getEntityId() ? null : (Vec3)entity.packetPositionMap.get(packet);
   }

   private int extractEntityIdFromPacket(Packet packet) {
      if (packet instanceof S14PacketEntity) {
         return ((S14PacketEntity)packet).getEntityId();
      } else {
         return packet instanceof S18PacketEntityTeleport ? ((S18PacketEntityTeleport)packet).getEntityId() : -1;
      }
   }

   public boolean shouldHoldTransactionInMacroDelay(Packet packet) {
      boolean flag = !ClientManagers.moduleManager.packetHandler.macroDelayModes.getSelectedValue().equals("Sandwich");
      if (packet instanceof S32PacketConfirmTransaction) {
         this.consecutiveTransactionCount++;
         if (this.consecutiveTransactionCount >= 2) {
            flag = true;
         }
      }

      if (packet instanceof S14PacketEntity || packet instanceof S18PacketEntityTeleport) {
         this.consecutiveTransactionCount = 0;
      }

      return flag
         && (packet instanceof S32PacketConfirmTransaction || packet instanceof S00PacketKeepAlive || packet instanceof S48PacketResourcePackSend)
         && !ClientManagers.moduleManager.packetHandler.macroDelayModes.getSelectedValue().equals("None")
         && Minecraft.getMinecraft().thePlayer != null
         && Minecraft.getMinecraft().thePlayer.ticksExisted > 5;
   }

   private void processDelayedIncomingEntry(DelayedPacketEntry delayedPacketEntry) {
      if (delayedPacketEntry != null) {
         Packet packet = delayedPacketEntry.getPacket();

         try {
            if (!(packet instanceof PacketFlushMarker)) {
               packet.processPacket(mc.getNetHandler().getNetworkManager().getNetHandler());
               this.cleanupTrackedEntityPacket(packet);
            }
         } catch (Exception e) {
         }
      }
   }

   public void cleanupTrackedEntityPacket(Packet packet) {
      if (mc.theWorld != null) {
         if (packet instanceof S14PacketEntity || packet instanceof S18PacketEntityTeleport) {
            PacketThreadUtil.runOnMainThreadImmediate(() -> {
               if (mc.theWorld != null) {
                  Entity entity;
                  if (packet instanceof S14PacketEntity) {
                     entity = mc.theWorld.getEntityByID(((S14PacketEntity)packet).getEntityId());
                  } else {
                     entity = mc.theWorld.getEntityByID(((S18PacketEntityTeleport)packet).getEntityId());
                  }

                  if (entity instanceof EntityLivingBase) {
                     ((EntityLivingBase)entity).packetPositionMap.remove(packet);
                  }
               }
            });
         }
      }
   }

   public void flushAllOutgoingPackets(Queue queue, ReentrantReadWriteLock reentrantReadWriteLock) {
      reentrantReadWriteLock.writeLock().lock();

      DelayedOutboundPacket packet;
      while ((packet = (DelayedOutboundPacket)this.outgoingPacketQueue.poll()) != null) {
         queue.add(packet.toInboundHandlerTuple());
      }

      reentrantReadWriteLock.writeLock().unlock();
   }

   public void flushExpiredOutgoingPackets(EventPacketDelay event, int n) {
      if (!this.outgoingPacketQueue.isEmpty()) {
         event.getQueueLock().writeLock().lock();
         Iterator it = this.outgoingPacketQueue.iterator();

         while (it.hasNext()) {
            DelayedOutboundPacket packet = (DelayedOutboundPacket)it.next();
            if (packet.isExpired((long)n)) {
               event.getOutboundQueue().add(packet.toInboundHandlerTuple());
               it.remove();
            }
         }

         event.getQueueLock().writeLock().unlock();
      }
   }

   public boolean shouldDelayIncomingPacket(Packet packet) {
      if (this.pingFix.getValue() && packet instanceof S00PacketKeepAlive) {
         return false;
      } else if (!this.realTimeDamage.getValue()
         || !(packet instanceof S19PacketEntityStatus) && !(packet instanceof S06PacketUpdateHealth) && !(packet instanceof S29PacketSoundEffect)) {
         PacketHandlerPacketOption option = (PacketHandlerPacketOption)INCOMING_DELAY_OPTIONS.get(packet.getClass());
         return option != null ? PacketHandlerPacketOption.isOptionSelected(option) : packet instanceof PacketFlushMarker;
      } else {
         return false;
      }
   }

   public boolean shouldDelayOutgoingPacket(Packet packet) {
      if (this.pingFix.getValue() && packet instanceof C00PacketKeepAlive) {
         return false;
      } else {
         PacketHandlerPacketOption option = (PacketHandlerPacketOption)OUTGOING_DELAY_OPTIONS.get(packet.getClass());
         return option != null ? PacketHandlerPacketOption.isOptionSelected(option) : packet instanceof PacketFlushMarker;
      }
   }

   public boolean shouldAbortIncomingDelay(Packet packet) {
      if (packet instanceof S07PacketRespawn) {
         return true;
      } else {
         PacketHandlerPacketOption option = (PacketHandlerPacketOption)INCOMING_ABORT_OPTIONS.get(packet.getClass());
         return option != null ? PacketHandlerPacketOption.isOptionSelected(option) : packet instanceof PacketFlushMarker;
      }
   }

   public boolean shouldAbortOutgoingDelay(Packet packet) {
      PacketHandlerPacketOption option = (PacketHandlerPacketOption)OUTGOING_ABORT_OPTIONS.get(packet.getClass());
      return option != null ? PacketHandlerPacketOption.isOptionSelected(option) : packet instanceof PacketFlushMarker;
   }

   public synchronized boolean isFlushingPackets() {
      return this.flushingPackets;
   }

   public synchronized void setFlushingPackets(boolean flag) {
      this.flushingPackets = flag;
   }

   public boolean isWorldAndPlayerReady() {
      return Minecraft.getMinecraft() != null
         && Minecraft.getMinecraft().theWorld != null
         && Minecraft.getMinecraft().thePlayer != null
         && Minecraft.getMinecraft().thePlayer.ticksExisted > 5;
   }

   private static void registerIncomingPacketOptions(Map map) {
      putPacketOption(map, new PacketHandlerPacketOption("S00PacketKeepAlive", S00PacketKeepAlive.class));
      putPacketOption(map, new PacketHandlerPacketOption("S01PacketJoinGame", S01PacketJoinGame.class));
      putPacketOption(map, new PacketHandlerPacketOption("S02PacketChat", S02PacketChat.class));
      putPacketOption(map, new PacketHandlerPacketOption("S03PacketTimeUpdate", S03PacketTimeUpdate.class));
      putPacketOption(map, new PacketHandlerPacketOption("S04PacketEntityEquipment", S04PacketEntityEquipment.class));
      putPacketOption(map, new PacketHandlerPacketOption("S05PacketSpawnPosition", S05PacketSpawnPosition.class));
      putPacketOption(map, new PacketHandlerPacketOption("S06PacketUpdateHealth", S06PacketUpdateHealth.class));
      putPacketOption(map, new PacketHandlerPacketOption("S07PacketRespawn", S07PacketRespawn.class));
      putPacketOption(map, new PacketHandlerPacketOption("S08PacketPlayerPosLook", S08PacketPlayerPosLook.class));
      putPacketOption(map, new PacketHandlerPacketOption("S09PacketHeldItemChange", S09PacketHeldItemChange.class));
      putPacketOption(map, new PacketHandlerPacketOption("S0APacketUseBed", S0APacketUseBed.class));
      putPacketOption(map, new PacketHandlerPacketOption("S0BPacketAnimation", S0BPacketAnimation.class));
      putPacketOption(map, new PacketHandlerPacketOption("S0CPacketSpawnPlayer", S0CPacketSpawnPlayer.class));
      putPacketOption(map, new PacketHandlerPacketOption("S0DPacketCollectItem", S0DPacketCollectItem.class));
      putPacketOption(map, new PacketHandlerPacketOption("S0EPacketSpawnObject", S0EPacketSpawnObject.class));
      putPacketOption(map, new PacketHandlerPacketOption("S0FPacketSpawnMob", S0FPacketSpawnMob.class));
      putPacketOption(map, new PacketHandlerPacketOption("S10PacketSpawnPainting", S10PacketSpawnPainting.class));
      putPacketOption(map, new PacketHandlerPacketOption("S11PacketSpawnExperienceOrb", S11PacketSpawnExperienceOrb.class));
      putPacketOption(map, new PacketHandlerPacketOption("S12PacketEntityVelocity", S12PacketEntityVelocity.class));
      putPacketOption(map, new PacketHandlerPacketOption("S13PacketDestroyEntities", S13PacketDestroyEntities.class));
      putPacketOption(map, new PacketHandlerPacketOption("S14PacketEntity", S14PacketEntity.class));
      putPacketOption(map, new PacketHandlerPacketOption("S15PacketEntityRelMove", S14PacketEntity.S15PacketEntityRelMove.class));
      putPacketOption(map, new PacketHandlerPacketOption("S16PacketEntityLook", S14PacketEntity.S16PacketEntityLook.class));
      putPacketOption(map, new PacketHandlerPacketOption("S17PacketEntityLookMove", S14PacketEntity.S17PacketEntityLookMove.class));
      putPacketOption(map, new PacketHandlerPacketOption("S18PacketEntityTeleport", S18PacketEntityTeleport.class));
      putPacketOption(map, new PacketHandlerPacketOption("S19PacketEntityHeadLook", S19PacketEntityHeadLook.class));
      putPacketOption(map, new PacketHandlerPacketOption("S19PacketEntityStatus", S19PacketEntityStatus.class));
      putPacketOption(map, new PacketHandlerPacketOption("S1BPacketEntityAttach", S1BPacketEntityAttach.class));
      putPacketOption(map, new PacketHandlerPacketOption("S1CPacketEntityMetadata", S1CPacketEntityMetadata.class));
      putPacketOption(map, new PacketHandlerPacketOption("S1DPacketEntityEffect", S1DPacketEntityEffect.class));
      putPacketOption(map, new PacketHandlerPacketOption("S1EPacketRemoveEntityEffect", S1EPacketRemoveEntityEffect.class));
      putPacketOption(map, new PacketHandlerPacketOption("S1FPacketSetExperience", S1FPacketSetExperience.class));
      putPacketOption(map, new PacketHandlerPacketOption("S20PacketEntityProperties", S20PacketEntityProperties.class));
      putPacketOption(map, new PacketHandlerPacketOption("S21PacketChunkData", S21PacketChunkData.class));
      putPacketOption(map, new PacketHandlerPacketOption("S22PacketMultiBlockChange", S22PacketMultiBlockChange.class));
      putPacketOption(map, new PacketHandlerPacketOption("S23PacketBlockChange", S23PacketBlockChange.class));
      putPacketOption(map, new PacketHandlerPacketOption("S24PacketBlockAction", S24PacketBlockAction.class));
      putPacketOption(map, new PacketHandlerPacketOption("S25PacketBlockBreakAnim", S25PacketBlockBreakAnim.class));
      putPacketOption(map, new PacketHandlerPacketOption("S26PacketMapChunkBulk", S26PacketMapChunkBulk.class));
      putPacketOption(map, new PacketHandlerPacketOption("S27PacketExplosion", S27PacketExplosion.class));
      putPacketOption(map, new PacketHandlerPacketOption("S28PacketEffect", S28PacketEffect.class));
      putPacketOption(map, new PacketHandlerPacketOption("S29PacketSoundEffect", S29PacketSoundEffect.class));
      putPacketOption(map, new PacketHandlerPacketOption("S2APacketParticles", S2APacketParticles.class));
      putPacketOption(map, new PacketHandlerPacketOption("S2BPacketChangeGameState", S2BPacketChangeGameState.class));
      putPacketOption(map, new PacketHandlerPacketOption("S2CPacketSpawnGlobalEntity", S2CPacketSpawnGlobalEntity.class));
      putPacketOption(map, new PacketHandlerPacketOption("S2DPacketOpenWindow", S2DPacketOpenWindow.class));
      putPacketOption(map, new PacketHandlerPacketOption("S2EPacketCloseWindow", S2EPacketCloseWindow.class));
      putPacketOption(map, new PacketHandlerPacketOption("S2FPacketSetSlot", S2FPacketSetSlot.class));
      putPacketOption(map, new PacketHandlerPacketOption("S30PacketWindowItems", S30PacketWindowItems.class));
      putPacketOption(map, new PacketHandlerPacketOption("S31PacketWindowProperty", S31PacketWindowProperty.class));
      putPacketOption(map, new PacketHandlerPacketOption("S32PacketConfirmTransaction", S32PacketConfirmTransaction.class));
      putPacketOption(map, new PacketHandlerPacketOption("S33PacketUpdateSign", S33PacketUpdateSign.class));
      putPacketOption(map, new PacketHandlerPacketOption("S34PacketMaps", S34PacketMaps.class));
      putPacketOption(map, new PacketHandlerPacketOption("S35PacketUpdateTileEntity", S35PacketUpdateTileEntity.class));
      putPacketOption(map, new PacketHandlerPacketOption("S36PacketSignEditorOpen", S36PacketSignEditorOpen.class));
      putPacketOption(map, new PacketHandlerPacketOption("S37PacketStatistics", S37PacketStatistics.class));
      putPacketOption(map, new PacketHandlerPacketOption("S38PacketPlayerListItem", S38PacketPlayerListItem.class));
      putPacketOption(map, new PacketHandlerPacketOption("S39PacketPlayerAbilities", S39PacketPlayerAbilities.class));
      putPacketOption(map, new PacketHandlerPacketOption("S3APacketTabComplete", S3APacketTabComplete.class));
      putPacketOption(map, new PacketHandlerPacketOption("S3BPacketScoreboardObjective", S3BPacketScoreboardObjective.class));
      putPacketOption(map, new PacketHandlerPacketOption("S3CPacketUpdateScore", S3CPacketUpdateScore.class));
      putPacketOption(map, new PacketHandlerPacketOption("S3DPacketDisplayScoreboard", S3DPacketDisplayScoreboard.class));
      putPacketOption(map, new PacketHandlerPacketOption("S3EPacketTeams", S3EPacketTeams.class));
      putPacketOption(map, new PacketHandlerPacketOption("S3FPacketCustomPayload", S3FPacketCustomPayload.class));
      putPacketOption(map, new PacketHandlerPacketOption("S40PacketDisconnect", S40PacketDisconnect.class));
      putPacketOption(map, new PacketHandlerPacketOption("S41PacketServerDifficulty", S41PacketServerDifficulty.class));
      putPacketOption(map, new PacketHandlerPacketOption("S42PacketCombatEvent", S42PacketCombatEvent.class));
      putPacketOption(map, new PacketHandlerPacketOption("S43PacketCamera", S43PacketCamera.class));
      putPacketOption(map, new PacketHandlerPacketOption("S44PacketWorldBorder", S44PacketWorldBorder.class));
      putPacketOption(map, new PacketHandlerPacketOption("S45PacketTitle", S45PacketTitle.class));
      putPacketOption(map, new PacketHandlerPacketOption("S46PacketSetCompressionLevel", S46PacketSetCompressionLevel.class));
      putPacketOption(map, new PacketHandlerPacketOption("S47PacketPlayerListHeaderFooter", S47PacketPlayerListHeaderFooter.class));
      putPacketOption(map, new PacketHandlerPacketOption("S48PacketResourcePackSend", S48PacketResourcePackSend.class));
      putPacketOption(map, new PacketHandlerPacketOption("S49PacketUpdateEntityNBT", S49PacketUpdateEntityNBT.class));
   }

   private static void registerOutgoingPacketOptions(Map map) {
      putPacketOption(map, new PacketHandlerPacketOption("C00PacketKeepAlive", C00PacketKeepAlive.class));
      putPacketOption(map, new PacketHandlerPacketOption("C01PacketChatMessage", C01PacketChatMessage.class));
      putPacketOption(map, new PacketHandlerPacketOption("C02PacketUseEntity", C02PacketUseEntity.class));
      putPacketOption(map, new PacketHandlerPacketOption("C03PacketPlayer", C03PacketPlayer.class));
      putPacketOption(map, new PacketHandlerPacketOption("C04PacketPlayerPosition", C03PacketPlayer.C04PacketPlayerPosition.class));
      putPacketOption(map, new PacketHandlerPacketOption("C05PacketPlayerLook", C03PacketPlayer.C05PacketPlayerLook.class));
      putPacketOption(map, new PacketHandlerPacketOption("C06PacketPlayerPosLook", C03PacketPlayer.C06PacketPlayerPosLook.class));
      putPacketOption(map, new PacketHandlerPacketOption("C07PacketPlayerDigging", C07PacketPlayerDigging.class));
      putPacketOption(map, new PacketHandlerPacketOption("C08PacketPlayerBlockPlacement", C08PacketPlayerBlockPlacement.class));
      putPacketOption(map, new PacketHandlerPacketOption("C09PacketHeldItemChange", C09PacketHeldItemChange.class));
      putPacketOption(map, new PacketHandlerPacketOption("C0APacketAnimation", C0APacketAnimation.class));
      putPacketOption(map, new PacketHandlerPacketOption("C0BPacketEntityAction", C0BPacketEntityAction.class));
      putPacketOption(map, new PacketHandlerPacketOption("C0CPacketInput", C0CPacketInput.class));
      putPacketOption(map, new PacketHandlerPacketOption("C0DPacketCloseWindow", C0DPacketCloseWindow.class));
      putPacketOption(map, new PacketHandlerPacketOption("C0EPacketClickWindow", C0EPacketClickWindow.class));
      putPacketOption(map, new PacketHandlerPacketOption("C0FPacketConfirmTransaction", C0FPacketConfirmTransaction.class));
      putPacketOption(map, new PacketHandlerPacketOption("C10PacketCreativeInventoryAction", C10PacketCreativeInventoryAction.class));
      putPacketOption(map, new PacketHandlerPacketOption("C11PacketEnchantItem", C11PacketEnchantItem.class));
      putPacketOption(map, new PacketHandlerPacketOption("C12PacketUpdateSign", C12PacketUpdateSign.class));
      putPacketOption(map, new PacketHandlerPacketOption("C13PacketPlayerAbilities", C13PacketPlayerAbilities.class));
      putPacketOption(map, new PacketHandlerPacketOption("C14PacketTabComplete", C14PacketTabComplete.class));
      putPacketOption(map, new PacketHandlerPacketOption("C15PacketClientSettings", C15PacketClientSettings.class));
      putPacketOption(map, new PacketHandlerPacketOption("C16PacketClientStatus", C16PacketClientStatus.class));
      putPacketOption(map, new PacketHandlerPacketOption("C17PacketCustomPayload", C17PacketCustomPayload.class));
      putPacketOption(map, new PacketHandlerPacketOption("C18PacketSpectate", C18PacketSpectate.class));
      putPacketOption(map, new PacketHandlerPacketOption("C19PacketResourcePackStatus", C19PacketResourcePackStatus.class));
      putPacketOption(map, new PacketHandlerPacketOption("CAnimateHandPacket", C0APacketSwingHand.class));
      putPacketOption(map, new PacketHandlerPacketOption("CPacketPlayerTryUseItemOnBlock", CPacketPlayerTryUseItemOnBlock.class));
   }

   private static void putPacketOption(Map map, PacketHandlerPacketOption option) {
      map.put(PacketHandlerPacketOption.getPacketClass(option), option);
   }

   private static PacketHandlerPacketOption[] sortedPacketOptions(Map map) {
      return Arrays.stream((PacketHandlerPacketOption[])map.values().toArray(new PacketHandlerPacketOption[0])).sorted(Comparator.comparing(PacketHandlerPacketOption::getName)).toArray(PacketHandlerPacketOption[]::new);
   }

   static {
      registerIncomingPacketOptions(INCOMING_DELAY_OPTIONS);
      registerIncomingPacketOptions(INCOMING_ABORT_OPTIONS);
      registerOutgoingPacketOptions(OUTGOING_DELAY_OPTIONS);
      registerOutgoingPacketOptions(OUTGOING_ABORT_OPTIONS);
   }

}
