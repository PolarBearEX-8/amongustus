package augustus.modules.latency;

import client.ClientGlobals;
import client.event.EventBusManager;
import client.event.EventSendPacket;
import client.event.NetworkPacketListener;
import client.event.TickListener;
import client.module.Category;
import client.module.Module;
import client.network.CosmeticsPayload;
import client.network.LatencyPacket;
import client.setting.BooleanSetting;
import client.setting.ModeSetting;
import client.setting.ModuleOption;
import client.setting.MultiSelectSetting;
import client.setting.SettingGroup;
import client.util.ClientManager;
import client.util.Packet;
import client.util.QueuedNetworkPacket;
import client.util.SimulatedPlayer;
import com.google.common.collect.Queues;
import io.netty.util.concurrent.GenericFutureListener;
import java.awt.Color;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
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
import net.minecraft.util.Vec3;

public class PacketHandler extends Module implements NetworkPacketListener, TickListener {
    public static Map entryMap = new HashMap();
    public static Map entryMap2 = new HashMap();
    public static Map entryMap3 = new HashMap();
    public static Map entryMap4 = new HashMap();

    static {
        applyMap(entryMap);
        applyMap(entryMap2);
        applyMap2(entryMap3);
        applyMap2(entryMap4);
    }
    public Queue packetQueue;
    public HashMap entryMap5;
    public HashMap entryMap6;
    public Queue queue2;
    public BooleanSetting settingPingFix;
    public SettingGroup settingIncoming;
    public ModeSetting settingMacroDelayModes;
    public BooleanSetting settingRealTimeDamage;
    public ModeSetting settingEventToHandlePacket;
    public MultiSelectSetting settingIncomingPacketsToDelay;
    public SettingGroup settingAbortIncoming;
    public MultiSelectSetting settingIncomingPacketsToDelayAbort;
    public SettingGroup settingOutgoing;
    public MultiSelectSetting settingOutGoingPacketsToDelay;
    public SettingGroup settingAbortOutgoing;
    public MultiSelectSetting settingOutGoingPacketsToDelayAbort;
    public boolean expanded2;
    public boolean enabled2;
    public CopyOnWriteArrayList copyOnWriteArrayList;
    public int tickDelay;

    public PacketHandler() {
        super("PacketHandler", "PacketHandler", true, Color.red, Category.LATENCY);
        this.packetQueue = Queues.newConcurrentLinkedQueue();
        this.entryMap5 = new HashMap();
        this.entryMap6 = new HashMap();
        this.queue2 = Queues.newConcurrentLinkedQueue();
        this.settingPingFix = new BooleanSetting(this, "PingFix", false);
        this.settingIncoming = new SettingGroup(this, "Incoming", true);
        String[] textArray = new String[5];
        textArray[0] = "None";
        textArray[1] = "MacroDelay";
        textArray[2] = "OtherMacroDelay";
        textArray[3] = "ExperimentalMacroDelay";
        textArray[4] = "Sandwich";
        this.settingMacroDelayModes = new ModeSetting(this, "MacroDelayModes", "None", textArray, this.settingIncoming);
        this.settingRealTimeDamage = new BooleanSetting(this, "RealTimeDamage", true, this.settingIncoming);
        String[] textArray2 = new String[2];
        textArray2[0] = "Vanilla";
        textArray2[1] = "Tick";
        this.settingEventToHandlePacket = new ModeSetting(this, "EventToHandlePacket", "Vanilla", textArray2, this.settingIncoming);
        ModuleOption[] moduleOptionArray = getModuleOptionArray(entryMap);
        this.settingIncomingPacketsToDelay = new MultiSelectSetting(this, "IncomingPacketsToDelay", moduleOptionArray, this.settingIncoming).getMultiSelectSetting("PacketsToDelay");
        this.settingAbortIncoming = new SettingGroup(this, "AbortIncoming", this.settingIncoming);
        ModuleOption[] moduleOptionArray2 = getModuleOptionArray(entryMap2);
        this.settingIncomingPacketsToDelayAbort = new MultiSelectSetting(this, "IncomingPacketsToDelayAbort", moduleOptionArray2, this.settingAbortIncoming).getMultiSelectSetting("PacketsToAbortDelay");
        this.settingOutgoing = new SettingGroup(this, "Outgoing", true);
        ModuleOption[] moduleOptionArray3 = getModuleOptionArray(entryMap3);
        this.settingOutGoingPacketsToDelay = new MultiSelectSetting(this, "OutGoingPacketsToDelay", moduleOptionArray3, this.settingOutgoing).getMultiSelectSetting("PacketsToDelay");
        this.settingAbortOutgoing = new SettingGroup(this, "AbortOutgoing", this.settingOutgoing);
        ModuleOption[] moduleOptionArray4 = getModuleOptionArray(entryMap4);
        this.settingOutGoingPacketsToDelayAbort = new MultiSelectSetting(this, "OutGoingPacketsToDelayAbort", moduleOptionArray4, this.settingAbortOutgoing).getMultiSelectSetting("PacketsToAbortDelay");
        this.expanded2 = false;
        CopyOnWriteArrayList copyOnWriteArrayList2 = new CopyOnWriteArrayList();
        this.copyOnWriteArrayList = copyOnWriteArrayList2;
        this.tickDelay = 2;
        EventBusManager.getDietrichEvents2().subscribe(25, this, 2147483640);
        EventBusManager.getDietrichEvents2().subscribe(40, this);
        this.copyOnWriteArrayList.add(false);
    }

    @Override
    public void onEnable() {
    }

    @Override
    public void onDisable() {
    }

    @Override
    public void onPreDisable() {
    }

    @Override
    public void onNetworkPacket(EventSendPacket packet) {
        boolean state = packet.isState();
        this.enabled2 = state || packet.getIndexOffset() > 0;
    }

    @Override
    public void onRunTick() {
        if (minecraft.thePlayer.ticksExisted % 20 == 0) {
            CopyOnWriteArrayList copyOnWriteArrayList2 = this.copyOnWriteArrayList;
            boolean enabled = CosmeticsPayload.checkCondition(1);
            boolean enabled3;
            if (enabled && ThreadLocalRandom.current().nextInt(0, 100) < 20) {
                enabled3 = true;
            } else {
                enabled3 = false;
            }

            copyOnWriteArrayList2.set(0, enabled3);
        }
    }

    public void handlePacket(Packet packet) {
        if (minecraft.theWorld != null) {
            if (!(packet instanceof S14PacketEntity) && !(packet instanceof S18PacketEntityTeleport)) {
                return;
            }

            ClientManager.applyRunnable2(() -> handlePacket3(packet));
        }
    }

    public void processAction3(int index2) {
        if (!this.packetQueue.isEmpty()) {
            long time2 = System.currentTimeMillis();
            LinkedBlockingQueue linkedBlockingQueue = Queues.newLinkedBlockingQueue();

            for (Packet packet : (Iterable<Packet>) this.packetQueue) {
                long time3 = (Long)this.entryMap5.get(packet) + (long)index2;
                if (time2 > time3) {
                    if (this.checkPacket4(packet)) {
                        linkedBlockingQueue.add(packet);
                    } else {
                        for (boolean i = linkedBlockingQueue.isEmpty(); !i; i = linkedBlockingQueue.isEmpty()) {
                            Packet packet2 = (Packet)linkedBlockingQueue.poll();
                            this.handlePacket2(packet2);
                            this.packetQueue.remove(packet2);
                        }

                        this.handlePacket2(packet);
                        this.packetQueue.remove(packet);
                    }
                }
            }
        }
    }

    private Vec3 handlePacket(Packet packet, EntityLivingBase livingEntity) {
        return this.handlePacket4(packet) != livingEntity.getEntityId() ? null : (Vec3)livingEntity.entryMap.get(packet);
    }

    private static void applyMap(Map map) {
        applyMap3(map, new ModuleOption("S00PacketKeepAlive", S00PacketKeepAlive.class));
        applyMap3(map, new ModuleOption("S01PacketJoinGame", S01PacketJoinGame.class));
        applyMap3(map, new ModuleOption("S02PacketChat", S02PacketChat.class));
        applyMap3(map, new ModuleOption("S03PacketTimeUpdate", S03PacketTimeUpdate.class));
        applyMap3(map, new ModuleOption("S04PacketEntityEquipment", S04PacketEntityEquipment.class));
        applyMap3(map, new ModuleOption("S05PacketSpawnPosition", S05PacketSpawnPosition.class));
        applyMap3(map, new ModuleOption("S06PacketUpdateHealth", S06PacketUpdateHealth.class));
        applyMap3(map, new ModuleOption("S07PacketRespawn", S07PacketRespawn.class));
        applyMap3(map, new ModuleOption("S08PacketPlayerPosLook", S08PacketPlayerPosLook.class));
        applyMap3(map, new ModuleOption("S09PacketHeldItemChange", S09PacketHeldItemChange.class));
        applyMap3(map, new ModuleOption("S0APacketUseBed", S0APacketUseBed.class));
        applyMap3(map, new ModuleOption("S0BPacketAnimation", S0BPacketAnimation.class));
        applyMap3(map, new ModuleOption("S0CPacketSpawnPlayer", S0CPacketSpawnPlayer.class));
        applyMap3(map, new ModuleOption("S0DPacketCollectItem", S0DPacketCollectItem.class));
        applyMap3(map, new ModuleOption("S0EPacketSpawnObject", S0EPacketSpawnObject.class));
        applyMap3(map, new ModuleOption("S0FPacketSpawnMob", S0FPacketSpawnMob.class));
        applyMap3(map, new ModuleOption("S10PacketSpawnPainting", S10PacketSpawnPainting.class));
        applyMap3(map, new ModuleOption("S11PacketSpawnExperienceOrb", S11PacketSpawnExperienceOrb.class));
        applyMap3(map, new ModuleOption("S12PacketEntityVelocity", S12PacketEntityVelocity.class));
        applyMap3(map, new ModuleOption("S13PacketDestroyEntities", S13PacketDestroyEntities.class));
        applyMap3(map, new ModuleOption("S14PacketEntity", S14PacketEntity.class));
        applyMap3(map, new ModuleOption("S15PacketEntityRelMove", S15PacketEntityRelMove.class));
        applyMap3(map, new ModuleOption("S16PacketEntityLook", S16PacketEntityLook.class));
        applyMap3(map, new ModuleOption("S17PacketEntityLookMove", S17PacketEntityLookMove.class));
        applyMap3(map, new ModuleOption("S18PacketEntityTeleport", S18PacketEntityTeleport.class));
        applyMap3(map, new ModuleOption("S19PacketEntityHeadLook", S19PacketEntityHeadLook.class));
        applyMap3(map, new ModuleOption("S19PacketEntityStatus", S19PacketEntityStatus.class));
        applyMap3(map, new ModuleOption("S1BPacketEntityAttach", S1BPacketEntityAttach.class));
        applyMap3(map, new ModuleOption("S1CPacketEntityMetadata", S1CPacketEntityMetadata.class));
        applyMap3(map, new ModuleOption("S1DPacketEntityEffect", S1DPacketEntityEffect.class));
        applyMap3(map, new ModuleOption("S1EPacketRemoveEntityEffect", S1EPacketRemoveEntityEffect.class));
        applyMap3(map, new ModuleOption("S1FPacketSetExperience", S1FPacketSetExperience.class));
        applyMap3(map, new ModuleOption("S20PacketEntityProperties", S20PacketEntityProperties.class));
        applyMap3(map, new ModuleOption("S21PacketChunkData", S21PacketChunkData.class));
        applyMap3(map, new ModuleOption("S22PacketMultiBlockChange", S22PacketMultiBlockChange.class));
        applyMap3(map, new ModuleOption("S23PacketBlockChange", S23PacketBlockChange.class));
        applyMap3(map, new ModuleOption("S24PacketBlockAction", S24PacketBlockAction.class));
        applyMap3(map, new ModuleOption("S25PacketBlockBreakAnim", S25PacketBlockBreakAnim.class));
        applyMap3(map, new ModuleOption("S26PacketMapChunkBulk", S26PacketMapChunkBulk.class));
        applyMap3(map, new ModuleOption("S27PacketExplosion", S27PacketExplosion.class));
        applyMap3(map, new ModuleOption("S28PacketEffect", S28PacketEffect.class));
        applyMap3(map, new ModuleOption("S29PacketSoundEffect", S29PacketSoundEffect.class));
        applyMap3(map, new ModuleOption("S2APacketParticles", S2APacketParticles.class));
        applyMap3(map, new ModuleOption("S2BPacketChangeGameState", S2BPacketChangeGameState.class));
        applyMap3(map, new ModuleOption("S2CPacketSpawnGlobalEntity", S2CPacketSpawnGlobalEntity.class));
        applyMap3(map, new ModuleOption("S2DPacketOpenWindow", S2DPacketOpenWindow.class));
        applyMap3(map, new ModuleOption("S2EPacketCloseWindow", S2EPacketCloseWindow.class));
        applyMap3(map, new ModuleOption("S2FPacketSetSlot", S2FPacketSetSlot.class));
        applyMap3(map, new ModuleOption("S30PacketWindowItems", S30PacketWindowItems.class));
        applyMap3(map, new ModuleOption("S31PacketWindowProperty", S31PacketWindowProperty.class));
        applyMap3(map, new ModuleOption("S32PacketConfirmTransaction", S32PacketConfirmTransaction.class));
        applyMap3(map, new ModuleOption("S33PacketUpdateSign", S33PacketUpdateSign.class));
        applyMap3(map, new ModuleOption("S34PacketMaps", S34PacketMaps.class));
        applyMap3(map, new ModuleOption("S35PacketUpdateTileEntity", S35PacketUpdateTileEntity.class));
        applyMap3(map, new ModuleOption("S36PacketSignEditorOpen", S36PacketSignEditorOpen.class));
        applyMap3(map, new ModuleOption("S37PacketStatistics", S37PacketStatistics.class));
        applyMap3(map, new ModuleOption("S38PacketPlayerListItem", S38PacketPlayerListItem.class));
        applyMap3(map, new ModuleOption("S39PacketPlayerAbilities", S39PacketPlayerAbilities.class));
        applyMap3(map, new ModuleOption("S3APacketTabComplete", S3APacketTabComplete.class));
        applyMap3(map, new ModuleOption("S3BPacketScoreboardObjective", S3BPacketScoreboardObjective.class));
        applyMap3(map, new ModuleOption("S3CPacketUpdateScore", S3CPacketUpdateScore.class));
        applyMap3(map, new ModuleOption("S3DPacketDisplayScoreboard", S3DPacketDisplayScoreboard.class));
        applyMap3(map, new ModuleOption("S3EPacketTeams", S3EPacketTeams.class));
        applyMap3(map, new ModuleOption("S3FPacketCustomPayload", S3FPacketCustomPayload.class));
        applyMap3(map, new ModuleOption("S40PacketDisconnect", S40PacketDisconnect.class));
        applyMap3(map, new ModuleOption("S41PacketServerDifficulty", S41PacketServerDifficulty.class));
        applyMap3(map, new ModuleOption("S42PacketCombatEvent", S42PacketCombatEvent.class));
        applyMap3(map, new ModuleOption("S43PacketCamera", S43PacketCamera.class));
        applyMap3(map, new ModuleOption("S44PacketWorldBorder", S44PacketWorldBorder.class));
        applyMap3(map, new ModuleOption("S45PacketTitle", S45PacketTitle.class));
        applyMap3(map, new ModuleOption("S46PacketSetCompressionLevel", S46PacketSetCompressionLevel.class));
        applyMap3(map, new ModuleOption("S47PacketPlayerListHeaderFooter", S47PacketPlayerListHeaderFooter.class));
        applyMap3(map, new ModuleOption("S48PacketResourcePackSend", S48PacketResourcePackSend.class));
        applyMap3(map, new ModuleOption("S49PacketUpdateEntityNBT", S49PacketUpdateEntityNBT.class));
    }

    public boolean checkPacket(Packet packet) {
        if ((this.settingPingFix.isEnabled() || (Boolean)this.copyOnWriteArrayList.get(0)) && packet instanceof S00PacketKeepAlive) {
            return false;
        } else {
            if (this.settingRealTimeDamage.isEnabled()) {
                if (packet instanceof S19PacketEntityStatus) {
                    return false;
                }

                if (packet instanceof S06PacketUpdateHealth) {
                    return false;
                }

                if (packet instanceof S29PacketSoundEffect) {
                    return false;
                }
            }

            ModuleOption moduleOption = (ModuleOption)entryMap.get(packet.getClass());
            return moduleOption == null ? packet instanceof LatencyPacket : ModuleOption.checkModuleOption(moduleOption);
        }
    }

    private static void applyMap2(Map map) {
        applyMap3(map, new ModuleOption("C00PacketKeepAlive", C00PacketKeepAlive.class));
        applyMap3(map, new ModuleOption("C01PacketChatMessage", C01PacketChatMessage.class));
        applyMap3(map, new ModuleOption("C02PacketUseEntity", C02PacketUseEntity.class));
        applyMap3(map, new ModuleOption("C03PacketPlayer", C03PacketPlayer.class));
        applyMap3(map, new ModuleOption("C04PacketPlayerPosition", C04PacketPlayerPosition.class));
        applyMap3(map, new ModuleOption("C05PacketPlayerLook", C05PacketPlayerLook.class));
        applyMap3(map, new ModuleOption("C06PacketPlayerPosLook", C06PacketPlayerPosLook.class));
        applyMap3(map, new ModuleOption("C07PacketPlayerDigging", C07PacketPlayerDigging.class));
        applyMap3(map, new ModuleOption("C08PacketPlayerBlockPlacement", C08PacketPlayerBlockPlacement.class));
        applyMap3(map, new ModuleOption("C09PacketHeldItemChange", C09PacketHeldItemChange.class));
        applyMap3(map, new ModuleOption("C0APacketAnimation", C0APacketAnimation.class));
        applyMap3(map, new ModuleOption("C0BPacketEntityAction", C0BPacketEntityAction.class));
        applyMap3(map, new ModuleOption("C0CPacketInput", C0CPacketInput.class));
        applyMap3(map, new ModuleOption("C0DPacketCloseWindow", C0DPacketCloseWindow.class));
        applyMap3(map, new ModuleOption("C0EPacketClickWindow", C0EPacketClickWindow.class));
        applyMap3(map, new ModuleOption("C0FPacketConfirmTransaction", C0FPacketConfirmTransaction.class));
        applyMap3(map, new ModuleOption("C10PacketCreativeInventoryAction", C10PacketCreativeInventoryAction.class));
        applyMap3(map, new ModuleOption("C11PacketEnchantItem", C11PacketEnchantItem.class));
        applyMap3(map, new ModuleOption("C12PacketUpdateSign", C12PacketUpdateSign.class));
        applyMap3(map, new ModuleOption("C13PacketPlayerAbilities", C13PacketPlayerAbilities.class));
        applyMap3(map, new ModuleOption("C14PacketTabComplete", C14PacketTabComplete.class));
        applyMap3(map, new ModuleOption("C15PacketClientSettings", C15PacketClientSettings.class));
        applyMap3(map, new ModuleOption("C16PacketClientStatus", C16PacketClientStatus.class));
        applyMap3(map, new ModuleOption("C17PacketCustomPayload", C17PacketCustomPayload.class));
        applyMap3(map, new ModuleOption("C18PacketSpectate", C18PacketSpectate.class));
        applyMap3(map, new ModuleOption("C19PacketResourcePackStatus", C19PacketResourcePackStatus.class));
        applyMap3(map, new ModuleOption("CAnimateHandPacket", CAnimateHandPacket.class));
        applyMap3(map, new ModuleOption("CPacketPlayerTryUseItemOnBlock", CPacketPlayerTryUseItemOnBlock.class));
    }

    public void applyEntityLivingBase(double offset, EntityLivingBase livingEntity) {
        if (!this.packetQueue.isEmpty() && livingEntity != null && minecraft.thePlayer != null) {
            LinkedBlockingQueue linkedBlockingQueue = Queues.newLinkedBlockingQueue();
            Vec3 vec3 = minecraft.thePlayer.getVec35(1.0F);

            for (Packet packet : (Iterable<Packet>) this.packetQueue) {
                Vec3 vec = this.handlePacket(packet, livingEntity);
                if (vec == null) {
                    linkedBlockingQueue.add(packet);
                } else {
                    Vec3 vec2 = SimulatedPlayer.processAction(vec3, SimulatedPlayer.getAxisAlignedBB(livingEntity, vec));
                    if (vec3.getCoordinate4(vec2) >= offset) {
                        return;
                    }

                    for (boolean i = linkedBlockingQueue.isEmpty(); !i; i = linkedBlockingQueue.isEmpty()) {
                        Packet packet2 = (Packet)linkedBlockingQueue.poll();
                        this.handlePacket2(packet2);
                        this.packetQueue.remove(packet2);
                    }

                    this.handlePacket2(packet);
                    this.packetQueue.remove(packet);
                }
            }
        }
    }

    public void onSendPacket(EventSendPacket packet) {
        Packet packet2 = packet.getPacket();
        this.packetQueue.add(packet2);
        this.entryMap5.put(packet2, System.currentTimeMillis());
        if (packet2 instanceof S07PacketRespawn) {
            this.updateState();
        }
    }

    public boolean checkPacket2(Packet packet) {
        if (packet instanceof S07PacketRespawn) {
            return true;
        } else {
            ModuleOption moduleOption = (ModuleOption)entryMap2.get(packet.getClass());
            return moduleOption == null ? packet instanceof LatencyPacket : ModuleOption.checkModuleOption(moduleOption);
        }
    }

    public void applyQueue(Queue queue, ReentrantReadWriteLock reentrantReadWriteLock) {
        reentrantReadWriteLock.writeLock().lock();

        while (!this.queue2.isEmpty()) {
            QueuedNetworkPacket packet = (QueuedNetworkPacket)this.queue2.poll();
            Packet packet2 = packet.getPacket();
            queue.add(packet);
            this.entryMap6.remove(packet2);
        }

        reentrantReadWriteLock.writeLock().unlock();
    }

    public void updateState() {
        while (!this.packetQueue.isEmpty()) {
            this.handlePacket2((Packet)this.packetQueue.poll());
        }
    }

    public boolean checkPacket3(Packet packet) {
        if ((this.settingPingFix.isEnabled() || (Boolean)this.copyOnWriteArrayList.get(0)) && packet instanceof C00PacketKeepAlive) {
            return false;
        } else {
            ModuleOption moduleOption;
            return (moduleOption = (ModuleOption)entryMap3.get(packet.getClass())) != null ? ModuleOption.checkModuleOption(moduleOption) : packet instanceof LatencyPacket;
        }
    }

    private int handlePacket4(Packet packet) {
        if (packet instanceof S14PacketEntity) {
            return ((S14PacketEntity)packet).getIndex();
        } else {
            return packet instanceof S18PacketEntityTeleport ? ((S18PacketEntityTeleport)packet).getIndex() : -1;
        }
    }

    public boolean checkPacket4(Packet packet) {
        String selected = ClientGlobals.moduleManager.packetHandler.settingMacroDelayModes.getSelected();
        boolean enabled = !selected.equals("Sandwich");
        boolean enabled3;
        if (packet instanceof S32PacketConfirmTransaction) {
            this.tickDelay++;
            if (this.tickDelay >= 2) {
                enabled = true;
            }

            enabled3 = packet instanceof S14PacketEntity;
        } else {
            enabled3 = packet instanceof S14PacketEntity;
        }

        if (!enabled3) {
            enabled3 = packet instanceof S18PacketEntityTeleport;
        }

        if (enabled3) {
            this.tickDelay = 0;
        }

        boolean enabled4 = (Boolean)this.copyOnWriteArrayList.get(0);
        if (enabled && !enabled4) {
            if (!(packet instanceof S32PacketConfirmTransaction)) {
                boolean enabled5 = packet instanceof S00PacketKeepAlive;
                if (!enabled5) {
                    enabled5 = packet instanceof S48PacketResourcePackSend;
                }

                if (!enabled5) {
                    return false;
                }
            }

            String selected2 = ClientGlobals.moduleManager.packetHandler.settingMacroDelayModes.getSelected();
            if (selected2.equals("None")) {
                return false;
            } else {
                return Minecraft.getMinecraft().thePlayer == null ? false : Minecraft.getMinecraft().thePlayer.ticksExisted > 5;
            }
        } else {
            return false;
        }
    }

    private static void applyMap3(Map map, ModuleOption moduleOption) {
        map.put(ModuleOption.getClass(moduleOption), moduleOption);
    }

    public synchronized void setExpanded2(boolean enabled) {
        this.expanded2 = enabled;
    }

    public void onSendPacket2(EventSendPacket packet, int index) {
        if (!this.queue2.isEmpty()) {
            long time2 = System.currentTimeMillis();
            packet.getReentrantReadWriteLock().writeLock().lock();
            Iterator iterator = this.queue2.iterator();

            while (iterator.hasNext()) {
                QueuedNetworkPacket packet2 = (QueuedNetworkPacket)iterator.next();
                if (packet2 == null) {
                    this.queue2.remove(null);
                } else {
                    Packet packet3 = packet2.getPacket();
                    if (packet3 == null) {
                        this.entryMap6.remove(null);
                        this.queue2.remove(packet2);
                    } else {
                        Long time3 = (Long)this.entryMap6.get(packet3);
                        if (time3 == null) {
                            this.entryMap6.remove(null);
                            this.queue2.remove(packet2);
                        } else if (time2 > time3 + (long)index) {
                            packet.getQueue().add(packet2);
                            this.entryMap6.remove(packet3);
                            this.queue2.remove(packet2);
                        }
                    }
                }
            }

            packet.getReentrantReadWriteLock().writeLock().unlock();
        }
    }

    private void handlePacket2(Packet packet) {
        try {
            if (!(packet instanceof LatencyPacket)) {
                packet.processAction(minecraft.getNetHandlerPlayClient().getNetworkManager().getINetHandler());
                this.handlePacket(packet);
            }
        } catch (Exception e) {
        }

        this.entryMap5.remove(packet);
    }

    public boolean canExecute5() {
        return !this.queue2.isEmpty();
    }

    private static ModuleOption[] getModuleOptionArray(int index) {
        return new ModuleOption[index];
    }

    private static void handlePacket3(Packet packet) {
        if (minecraft.theWorld != null) {
            Entity entity;
            if (packet instanceof S14PacketEntity) {
                entity = minecraft.theWorld.getEntityByID(((S14PacketEntity)packet).getIndex());
            } else {
                entity = minecraft.theWorld.getEntityByID(((S18PacketEntityTeleport)packet).getIndex());
            }

            if (!(entity instanceof EntityLivingBase)) {
                return;
            }

            ((EntityLivingBase)entity).entryMap.remove(packet);
        }
    }

    private static ModuleOption[] getModuleOptionArray(Map map) {
        return Arrays.stream(map.values().toArray(new ModuleOption[0]))
        .sorted(Comparator.comparing(object -> ((ModuleOption)object).getText()))
        .toArray(index -> getModuleOptionArray(index));
    }

    public boolean canExecute6() {
        if (Minecraft.getMinecraft() == null) {
            return false;
        } else if (Minecraft.getMinecraft().theWorld == null) {
            return false;
        } else {
            return Minecraft.getMinecraft().thePlayer == null ? false : Minecraft.getMinecraft().thePlayer.ticksExisted > 5;
        }
    }

    public boolean checkPacket5(Packet packet) {
        ModuleOption moduleOption = (ModuleOption)entryMap4.get(packet.getClass());
        return moduleOption == null ? packet instanceof LatencyPacket : ModuleOption.checkModuleOption(moduleOption);
    }

    public void onSendPacket3(EventSendPacket packet) {
        Queue queue22 = this.queue2;
        Packet packet2 = packet.getPacket();
        GenericFutureListener[] genericFutureListenerArray = (GenericFutureListener[])null;
        QueuedNetworkPacket packetEntry = new QueuedNetworkPacket(packet2, genericFutureListenerArray);
        queue22.add(packetEntry);
        HashMap entryMap62 = this.entryMap6;
        Packet packet3 = packet.getPacket();
        Long time2 = System.currentTimeMillis();
        entryMap62.put(packet3, time2);
    }

    public synchronized boolean isExpanded2() {
        return this.expanded2;
    }

    public boolean isEnabled2() {
        return this.enabled2;
    }
}
