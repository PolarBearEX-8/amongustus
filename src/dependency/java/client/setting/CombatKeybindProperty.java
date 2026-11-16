package client.setting;

import client.network.CustomDataPacket;
import net.minecraft.network.play.client.C00PacketKeepAlive;
import net.minecraft.network.play.client.C01PacketChatMessage;
import net.minecraft.network.play.client.C02PacketUseEntity;
import net.minecraft.network.play.client.C03PacketPlayer;
import net.minecraft.network.play.client.C04PacketPlayerPosition;
import net.minecraft.network.play.client.C05PacketPlayerLook;
import net.minecraft.network.play.client.C06PacketPlayerPosLook;
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
import net.minecraft.network.play.server.S15PacketEntityRelMove;
import net.minecraft.network.play.server.S16PacketEntityLook;
import net.minecraft.network.play.server.S17PacketEntityLookMove;
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

import client.util.AdvancedPacketAdapter;
import client.util.BasePacketAdapter;
import client.util.CommonPacketAdapter;
import client.util.CustomPacketAdapter;
import client.util.ExtendedPacketHandler;
import client.util.ExtraPacketHandler;
import client.util.Packet;
import client.util.SecondaryPacketAdapter;
import client.util.SpecialPacketAdapter;
import client.util.StandardPacketAdapter;
import client.util.TertiaryPacketAdapter;
import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.google.common.collect.Maps;
import java.util.Map;
import org.apache.logging.log4j.LogManager;

public enum CombatKeybindProperty {
   HANDSHAKING(-1) {
         {
            this.onPacketServerboundType(EventPacketServerboundType.SERVERBOUND, BasePacketAdapter.class);
         }
   },
   PLAY(0) {
         {
            this.onPacketServerboundType(EventPacketServerboundType.CLIENTBOUND, S00PacketKeepAlive.class);
            this.onPacketServerboundType(EventPacketServerboundType.CLIENTBOUND, S01PacketJoinGame.class);
            this.onPacketServerboundType(EventPacketServerboundType.CLIENTBOUND, S02PacketChat.class);
            this.onPacketServerboundType(EventPacketServerboundType.CLIENTBOUND, S03PacketTimeUpdate.class);
            this.onPacketServerboundType(EventPacketServerboundType.CLIENTBOUND, S04PacketEntityEquipment.class);
            this.onPacketServerboundType(EventPacketServerboundType.CLIENTBOUND, S05PacketSpawnPosition.class);
            this.onPacketServerboundType(EventPacketServerboundType.CLIENTBOUND, S06PacketUpdateHealth.class);
            this.onPacketServerboundType(EventPacketServerboundType.CLIENTBOUND, S07PacketRespawn.class);
            this.onPacketServerboundType(EventPacketServerboundType.CLIENTBOUND, S08PacketPlayerPosLook.class);
            this.onPacketServerboundType(EventPacketServerboundType.CLIENTBOUND, S09PacketHeldItemChange.class);
            this.onPacketServerboundType(EventPacketServerboundType.CLIENTBOUND, S0APacketUseBed.class);
            this.onPacketServerboundType(EventPacketServerboundType.CLIENTBOUND, S0BPacketAnimation.class);
            this.onPacketServerboundType(EventPacketServerboundType.CLIENTBOUND, S0CPacketSpawnPlayer.class);
            this.onPacketServerboundType(EventPacketServerboundType.CLIENTBOUND, S0DPacketCollectItem.class);
            this.onPacketServerboundType(EventPacketServerboundType.CLIENTBOUND, S0EPacketSpawnObject.class);
            this.onPacketServerboundType(EventPacketServerboundType.CLIENTBOUND, S0FPacketSpawnMob.class);
            this.onPacketServerboundType(EventPacketServerboundType.CLIENTBOUND, S10PacketSpawnPainting.class);
            this.onPacketServerboundType(EventPacketServerboundType.CLIENTBOUND, S11PacketSpawnExperienceOrb.class);
            this.onPacketServerboundType(EventPacketServerboundType.CLIENTBOUND, S12PacketEntityVelocity.class);
            this.onPacketServerboundType(EventPacketServerboundType.CLIENTBOUND, S13PacketDestroyEntities.class);
            this.onPacketServerboundType(EventPacketServerboundType.CLIENTBOUND, S14PacketEntity.class);
            this.onPacketServerboundType(EventPacketServerboundType.CLIENTBOUND, S15PacketEntityRelMove.class);
            this.onPacketServerboundType(EventPacketServerboundType.CLIENTBOUND, S16PacketEntityLook.class);
            this.onPacketServerboundType(EventPacketServerboundType.CLIENTBOUND, S17PacketEntityLookMove.class);
            this.onPacketServerboundType(EventPacketServerboundType.CLIENTBOUND, S18PacketEntityTeleport.class);
            this.onPacketServerboundType(EventPacketServerboundType.CLIENTBOUND, S19PacketEntityHeadLook.class);
            this.onPacketServerboundType(EventPacketServerboundType.CLIENTBOUND, S19PacketEntityStatus.class);
            this.onPacketServerboundType(EventPacketServerboundType.CLIENTBOUND, S1BPacketEntityAttach.class);
            this.onPacketServerboundType(EventPacketServerboundType.CLIENTBOUND, S1CPacketEntityMetadata.class);
            this.onPacketServerboundType(EventPacketServerboundType.CLIENTBOUND, S1DPacketEntityEffect.class);
            this.onPacketServerboundType(EventPacketServerboundType.CLIENTBOUND, S1EPacketRemoveEntityEffect.class);
            this.onPacketServerboundType(EventPacketServerboundType.CLIENTBOUND, S1FPacketSetExperience.class);
            this.onPacketServerboundType(EventPacketServerboundType.CLIENTBOUND, S20PacketEntityProperties.class);
            this.onPacketServerboundType(EventPacketServerboundType.CLIENTBOUND, S21PacketChunkData.class);
            this.onPacketServerboundType(EventPacketServerboundType.CLIENTBOUND, S22PacketMultiBlockChange.class);
            this.onPacketServerboundType(EventPacketServerboundType.CLIENTBOUND, S23PacketBlockChange.class);
            this.onPacketServerboundType(EventPacketServerboundType.CLIENTBOUND, S24PacketBlockAction.class);
            this.onPacketServerboundType(EventPacketServerboundType.CLIENTBOUND, S25PacketBlockBreakAnim.class);
            this.onPacketServerboundType(EventPacketServerboundType.CLIENTBOUND, S26PacketMapChunkBulk.class);
            this.onPacketServerboundType(EventPacketServerboundType.CLIENTBOUND, S27PacketExplosion.class);
            this.onPacketServerboundType(EventPacketServerboundType.CLIENTBOUND, S28PacketEffect.class);
            this.onPacketServerboundType(EventPacketServerboundType.CLIENTBOUND, S29PacketSoundEffect.class);
            this.onPacketServerboundType(EventPacketServerboundType.CLIENTBOUND, S2APacketParticles.class);
            this.onPacketServerboundType(EventPacketServerboundType.CLIENTBOUND, S2BPacketChangeGameState.class);
            this.onPacketServerboundType(EventPacketServerboundType.CLIENTBOUND, S2CPacketSpawnGlobalEntity.class);
            this.onPacketServerboundType(EventPacketServerboundType.CLIENTBOUND, S2DPacketOpenWindow.class);
            this.onPacketServerboundType(EventPacketServerboundType.CLIENTBOUND, S2EPacketCloseWindow.class);
            this.onPacketServerboundType(EventPacketServerboundType.CLIENTBOUND, S2FPacketSetSlot.class);
            this.onPacketServerboundType(EventPacketServerboundType.CLIENTBOUND, S30PacketWindowItems.class);
            this.onPacketServerboundType(EventPacketServerboundType.CLIENTBOUND, S31PacketWindowProperty.class);
            this.onPacketServerboundType(EventPacketServerboundType.CLIENTBOUND, S32PacketConfirmTransaction.class);
            this.onPacketServerboundType(EventPacketServerboundType.CLIENTBOUND, S33PacketUpdateSign.class);
            this.onPacketServerboundType(EventPacketServerboundType.CLIENTBOUND, S34PacketMaps.class);
            this.onPacketServerboundType(EventPacketServerboundType.CLIENTBOUND, S35PacketUpdateTileEntity.class);
            this.onPacketServerboundType(EventPacketServerboundType.CLIENTBOUND, S36PacketSignEditorOpen.class);
            this.onPacketServerboundType(EventPacketServerboundType.CLIENTBOUND, S37PacketStatistics.class);
            this.onPacketServerboundType(EventPacketServerboundType.CLIENTBOUND, S38PacketPlayerListItem.class);
            this.onPacketServerboundType(EventPacketServerboundType.CLIENTBOUND, S39PacketPlayerAbilities.class);
            this.onPacketServerboundType(EventPacketServerboundType.CLIENTBOUND, S3APacketTabComplete.class);
            this.onPacketServerboundType(EventPacketServerboundType.CLIENTBOUND, S3BPacketScoreboardObjective.class);
            this.onPacketServerboundType(EventPacketServerboundType.CLIENTBOUND, S3CPacketUpdateScore.class);
            this.onPacketServerboundType(EventPacketServerboundType.CLIENTBOUND, S3DPacketDisplayScoreboard.class);
            this.onPacketServerboundType(EventPacketServerboundType.CLIENTBOUND, S3EPacketTeams.class);
            this.onPacketServerboundType(EventPacketServerboundType.CLIENTBOUND, S3FPacketCustomPayload.class);
            this.onPacketServerboundType(EventPacketServerboundType.CLIENTBOUND, S40PacketDisconnect.class);
            this.onPacketServerboundType(EventPacketServerboundType.CLIENTBOUND, S41PacketServerDifficulty.class);
            this.onPacketServerboundType(EventPacketServerboundType.CLIENTBOUND, S42PacketCombatEvent.class);
            this.onPacketServerboundType(EventPacketServerboundType.CLIENTBOUND, S43PacketCamera.class);
            this.onPacketServerboundType(EventPacketServerboundType.CLIENTBOUND, S44PacketWorldBorder.class);
            this.onPacketServerboundType(EventPacketServerboundType.CLIENTBOUND, S45PacketTitle.class);
            this.onPacketServerboundType(EventPacketServerboundType.CLIENTBOUND, S46PacketSetCompressionLevel.class);
            this.onPacketServerboundType(EventPacketServerboundType.CLIENTBOUND, S47PacketPlayerListHeaderFooter.class);
            this.onPacketServerboundType(EventPacketServerboundType.CLIENTBOUND, S48PacketResourcePackSend.class);
            this.onPacketServerboundType(EventPacketServerboundType.CLIENTBOUND, S49PacketUpdateEntityNBT.class);
            this.onPacketServerboundType(EventPacketServerboundType.SERVERBOUND, C00PacketKeepAlive.class);
            this.onPacketServerboundType(EventPacketServerboundType.SERVERBOUND, C01PacketChatMessage.class);
            this.onPacketServerboundType(EventPacketServerboundType.SERVERBOUND, C02PacketUseEntity.class);
            this.onPacketServerboundType(EventPacketServerboundType.SERVERBOUND, C03PacketPlayer.class);
            this.onPacketServerboundType(EventPacketServerboundType.SERVERBOUND, C04PacketPlayerPosition.class);
            this.onPacketServerboundType(EventPacketServerboundType.SERVERBOUND, C05PacketPlayerLook.class);
            this.onPacketServerboundType(EventPacketServerboundType.SERVERBOUND, C06PacketPlayerPosLook.class);
            this.onPacketServerboundType(EventPacketServerboundType.SERVERBOUND, C07PacketPlayerDigging.class);
            this.onPacketServerboundType(EventPacketServerboundType.SERVERBOUND, C08PacketPlayerBlockPlacement.class);
            this.onPacketServerboundType(EventPacketServerboundType.SERVERBOUND, C09PacketHeldItemChange.class);
            this.onPacketServerboundType(EventPacketServerboundType.SERVERBOUND, C0APacketAnimation.class);
            this.onPacketServerboundType(EventPacketServerboundType.SERVERBOUND, C0BPacketEntityAction.class);
            this.onPacketServerboundType(EventPacketServerboundType.SERVERBOUND, C0CPacketInput.class);
            this.onPacketServerboundType(EventPacketServerboundType.SERVERBOUND, C0DPacketCloseWindow.class);
            this.onPacketServerboundType(EventPacketServerboundType.SERVERBOUND, C0EPacketClickWindow.class);
            this.onPacketServerboundType(EventPacketServerboundType.SERVERBOUND, C0FPacketConfirmTransaction.class);
            this.onPacketServerboundType(EventPacketServerboundType.SERVERBOUND, C10PacketCreativeInventoryAction.class);
            this.onPacketServerboundType(EventPacketServerboundType.SERVERBOUND, C11PacketEnchantItem.class);
            this.onPacketServerboundType(EventPacketServerboundType.SERVERBOUND, C12PacketUpdateSign.class);
            this.onPacketServerboundType(EventPacketServerboundType.SERVERBOUND, C13PacketPlayerAbilities.class);
            this.onPacketServerboundType(EventPacketServerboundType.SERVERBOUND, C14PacketTabComplete.class);
            this.onPacketServerboundType(EventPacketServerboundType.SERVERBOUND, C15PacketClientSettings.class);
            this.onPacketServerboundType(EventPacketServerboundType.SERVERBOUND, C16PacketClientStatus.class);
            this.onPacketServerboundType(EventPacketServerboundType.SERVERBOUND, C17PacketCustomPayload.class);
            this.onPacketServerboundType(EventPacketServerboundType.SERVERBOUND, C18PacketSpectate.class);
            this.onPacketServerboundType(EventPacketServerboundType.SERVERBOUND, C19PacketResourcePackStatus.class);
            this.onPacketServerboundType(EventPacketServerboundType.SERVERBOUND, CAnimateHandPacket.class);
            this.onPacketServerboundType(EventPacketServerboundType.SERVERBOUND, CPacketPlayerTryUseItemOnBlock.class);
         }
   },
   STATUS(1) {
         {
            this.onPacketServerboundType(EventPacketServerboundType.SERVERBOUND, CommonPacketAdapter.class);
            this.onPacketServerboundType(EventPacketServerboundType.CLIENTBOUND, CustomDataPacket.class);
            this.onPacketServerboundType(EventPacketServerboundType.SERVERBOUND, ExtendedPacketHandler.class);
            this.onPacketServerboundType(EventPacketServerboundType.CLIENTBOUND, AdvancedPacketAdapter.class);
         }
   },
   LOGIN(2) {
         {
            this.onPacketServerboundType(EventPacketServerboundType.CLIENTBOUND, SpecialPacketAdapter.class);
            this.onPacketServerboundType(EventPacketServerboundType.CLIENTBOUND, TertiaryPacketAdapter.class);
            this.onPacketServerboundType(EventPacketServerboundType.CLIENTBOUND, SecondaryPacketAdapter.class);
            this.onPacketServerboundType(EventPacketServerboundType.CLIENTBOUND, StandardPacketAdapter.class);
            this.onPacketServerboundType(EventPacketServerboundType.SERVERBOUND, ExtraPacketHandler.class);
            this.onPacketServerboundType(EventPacketServerboundType.SERVERBOUND, CustomPacketAdapter.class);
         }
   };

   public static int count = -1;
   public static int count2 = 2;
   public static final CombatKeybindProperty[] ENUM_VALUES = new CombatKeybindProperty[4];
   public static final Map map = Maps.newHashMap();
   public final int count3;
   public final Map map2 = Maps.newEnumMap(EventPacketServerboundType.class);
   public static final CombatKeybindProperty[] ENUM_VALUES2 = values();

   public Integer onPacketServerboundType(EventPacketServerboundType packetType, Packet packet) {
      return (Integer)((BiMap)this.map2.get(packetType)).inverse().get(packet.getClass());
   }

   public CombatKeybindProperty onPacketServerboundType(EventPacketServerboundType packetType, Class packetClass) {
      BiMap mapEntry = (BiMap)this.map2.get(packetType);
      if (mapEntry == null) {
         mapEntry = HashBiMap.create();
         this.map2.put(packetType, mapEntry);
      }

      if (mapEntry.containsValue(packetClass)) {
         String message = packetType + " packet " + packetClass + " is already known to ID " + mapEntry.inverse().get(packetClass);
         LogManager.getLogger().fatal(message);
         throw new IllegalArgumentException(message);
      } else {
         mapEntry.put(mapEntry.size(), packetClass);
         return this;
      }
   }

   public Packet onPacketServerboundType(EventPacketServerboundType packetType, int packetId) throws InstantiationException, IllegalAccessException {
      Class packetClass = (Class)((BiMap)this.map2.get(packetType)).get(packetId);
      return packetClass == null ? null : (Packet)packetClass.newInstance();
   }

   public static CombatKeybindProperty resolveCombatKeybindProperty(int protocolId) {
      return protocolId >= count && protocolId <= count2 ? ENUM_VALUES[protocolId - count] : null;
   }

   public int getCount3() {
      return this.count3;
   }

   public static CombatKeybindProperty resolveCombatKeybindProperty(Packet packet) {
      return (CombatKeybindProperty)map.get(packet.getClass());
   }


   private CombatKeybindProperty(int protocolId) {
      this.count3 = protocolId;
   }

   static {
      int minId = -1;
      int maxId = 2;
      for (CombatKeybindProperty protocol : values()) {
         int protocolId = protocol.getCount3();
         if (protocolId < minId || protocolId > maxId) {
            throw new Error("Invalid protocol ID " + protocolId);
         }

         ENUM_VALUES[protocolId - minId] = protocol;

         for (Object directionObj : protocol.map2.keySet()) {
            EventPacketServerboundType direction = (EventPacketServerboundType)directionObj;
            for (Object clsObj : ((BiMap)protocol.map2.get(direction)).values()) {
               Class packetClass = (Class)clsObj;
               if (map.containsKey(packetClass) && map.get(packetClass) != protocol) {
                  throw new Error("Packet " + packetClass + " is already assigned to protocol " + map.get(packetClass) + " - can't reassign to " + protocol);
               }

               try {
                  packetClass.newInstance();
               } catch (Throwable throwable) {
                  throw new Error("Packet " + packetClass + " fails instantiation checks! " + packetClass);
               }

               map.put(packetClass, protocol);
            }
         }
      }
   }
}
