package net.augustus.settings.options;

import net.augustus.settings.RangeSetting;
import net.augustus.settings.SelectableOption;
import net.augustus.settings.BooleanValue;
import net.augustus.settings.StringValue;
import net.augustus.settings.DoubleValue;
import net.minecraft.network.play.server.S00PacketKeepAlive;
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

public enum DisablerIncomingPacketOption implements SelectableOption {
   S00PacketKeepAlive("S00PacketKeepAlive", S00PacketKeepAlive.class),
   S02PacketChat("S02PacketChat", S02PacketChat.class),
   S03PacketTimeUpdate("S03PacketTimeUpdate", S03PacketTimeUpdate.class),
   S04PacketEntityEquipment("S04PacketEntityEquipment", S04PacketEntityEquipment.class),
   S05PacketSpawnPosition("S05PacketSpawnPosition", S05PacketSpawnPosition.class),
   S06PacketUpdateHealth("S06PacketUpdateHealth", S06PacketUpdateHealth.class),
   S07PacketRespawn("S07PacketRespawn", S07PacketRespawn.class),
   S08PacketPlayerPosLook("S08PacketPlayerPosLook", S08PacketPlayerPosLook.class),
   S09PacketHeldItemChange("S09PacketHeldItemChange", S09PacketHeldItemChange.class),
   S0APacketUseBed("S0APacketUseBed", S0APacketUseBed.class),
   S0BPacketAnimation("S0BPacketAnimation", S0BPacketAnimation.class),
   S0CPacketSpawnPlayer("S0CPacketSpawnPlayer", S0CPacketSpawnPlayer.class),
   S0DPacketCollectItem("S0DPacketCollectItem", S0DPacketCollectItem.class),
   S0EPacketSpawnObject("S0EPacketSpawnObject", S0EPacketSpawnObject.class),
   S0FPacketSpawnMob("S0FPacketSpawnMob", S0FPacketSpawnMob.class),
   S10PacketSpawnPainting("S10PacketSpawnPainting", S10PacketSpawnPainting.class),
   S11PacketSpawnExperienceOrb("S11PacketSpawnExperienceOrb", S11PacketSpawnExperienceOrb.class),
   S12PacketEntityVelocity("S12PacketEntityVelocity", S12PacketEntityVelocity.class),
   S13PacketDestroyEntities("S13PacketDestroyEntities", S13PacketDestroyEntities.class),
   S14PacketEntity("S14PacketEntity", S14PacketEntity.class),
   S14PacketEntity$S15PacketEntityRelMove("S15PacketEntityRelMove", S14PacketEntity.S15PacketEntityRelMove.class),
   S14PacketEntity$S16PacketEntityLook("S16PacketEntityLook", S14PacketEntity.S16PacketEntityLook.class),
   S14PacketEntity$S17PacketEntityLookMove("S17PacketEntityLookMove", S14PacketEntity.S17PacketEntityLookMove.class),
   S18PacketEntityTeleport("S18PacketEntityTeleport", S18PacketEntityTeleport.class),
   S19PacketEntityHeadLook("S19PacketEntityHeadLook", S19PacketEntityHeadLook.class),
   S19PacketEntityStatus("S19PacketEntityStatus", S19PacketEntityStatus.class),
   S1BPacketEntityAttach("S1BPacketEntityAttach", S1BPacketEntityAttach.class),
   S1CPacketEntityMetadata("S1CPacketEntityMetadata", S1CPacketEntityMetadata.class),
   S1DPacketEntityEffect("S1DPacketEntityEffect", S1DPacketEntityEffect.class),
   S1EPacketRemoveEntityEffect("S1EPacketRemoveEntityEffect", S1EPacketRemoveEntityEffect.class),
   S1FPacketSetExperience("S1FPacketSetExperience", S1FPacketSetExperience.class),
   S20PacketEntityProperties("S20PacketEntityProperties", S20PacketEntityProperties.class),
   S21PacketChunkData("S21PacketChunkData", S21PacketChunkData.class),
   S22PacketMultiBlockChange("S22PacketMultiBlockChange", S22PacketMultiBlockChange.class),
   S23PacketBlockChange("S23PacketBlockChange", S23PacketBlockChange.class),
   S24PacketBlockAction("S24PacketBlockAction", S24PacketBlockAction.class),
   S25PacketBlockBreakAnim("S25PacketBlockBreakAnim", S25PacketBlockBreakAnim.class),
   S26PacketMapChunkBulk("S26PacketMapChunkBulk", S26PacketMapChunkBulk.class),
   S27PacketExplosion("S27PacketExplosion", S27PacketExplosion.class),
   S28PacketEffect("S28PacketEffect", S28PacketEffect.class),
   S29PacketSoundEffect("S29PacketSoundEffect", S29PacketSoundEffect.class),
   S2APacketParticles("S2APacketParticles", S2APacketParticles.class),
   S2BPacketChangeGameState("S2BPacketChangeGameState", S2BPacketChangeGameState.class),
   S2CPacketSpawnGlobalEntity("S2CPacketSpawnGlobalEntity", S2CPacketSpawnGlobalEntity.class),
   S2DPacketOpenWindow("S2DPacketOpenWindow", S2DPacketOpenWindow.class),
   S2EPacketCloseWindow("S2EPacketCloseWindow", S2EPacketCloseWindow.class),
   S2FPacketSetSlot("S2FPacketSetSlot", S2FPacketSetSlot.class),
   S30PacketWindowItems("S30PacketWindowItems", S30PacketWindowItems.class),
   S31PacketWindowProperty("S31PacketWindowProperty", S31PacketWindowProperty.class),
   S32PacketConfirmTransaction("S32PacketConfirmTransaction", S32PacketConfirmTransaction.class),
   S33PacketUpdateSign("S33PacketUpdateSign", S33PacketUpdateSign.class),
   S34PacketMaps("S34PacketMaps", S34PacketMaps.class),
   S35PacketUpdateTileEntity("S35PacketUpdateTileEntity", S35PacketUpdateTileEntity.class),
   S36PacketSignEditorOpen("S36PacketSignEditorOpen", S36PacketSignEditorOpen.class),
   S37PacketStatistics("S37PacketStatistics", S37PacketStatistics.class),
   S38PacketPlayerListItem("S38PacketPlayerListItem", S38PacketPlayerListItem.class),
   S39PacketPlayerAbilities("S39PacketPlayerAbilities", S39PacketPlayerAbilities.class),
   S3APacketTabComplete("S3APacketTabComplete", S3APacketTabComplete.class),
   S3BPacketScoreboardObjective("S3BPacketScoreboardObjective", S3BPacketScoreboardObjective.class),
   S3CPacketUpdateScore("S3CPacketUpdateScore", S3CPacketUpdateScore.class),
   S3DPacketDisplayScoreboard("S3DPacketDisplayScoreboard", S3DPacketDisplayScoreboard.class),
   S3EPacketTeams("S3EPacketTeams", S3EPacketTeams.class),
   S3FPacketCustomPayload("S3FPacketCustomPayload", S3FPacketCustomPayload.class),
   S40PacketDisconnect("S40PacketDisconnect", S40PacketDisconnect.class),
   S41PacketServerDifficulty("S41PacketServerDifficulty", S41PacketServerDifficulty.class),
   S42PacketCombatEvent("S42PacketCombatEvent", S42PacketCombatEvent.class),
   S43PacketCamera("S43PacketCamera", S43PacketCamera.class),
   S44PacketWorldBorder("S44PacketWorldBorder", S44PacketWorldBorder.class),
   S45PacketTitle("S45PacketTitle", S45PacketTitle.class),
   S46PacketSetCompressionLevel("S46PacketSetCompressionLevel", S46PacketSetCompressionLevel.class),
   S47PacketPlayerListHeaderFooter("S47PacketPlayerListHeaderFooter", S47PacketPlayerListHeaderFooter.class),
   S48PacketResourcePackSend("S48PacketResourcePackSend", S48PacketResourcePackSend.class),
   S49PacketUpdateEntityNBT("S49PacketUpdateEntityNBT", S49PacketUpdateEntityNBT.class);

private String name;
   private boolean selected;
   private Class packetClass;
   private DoubleValue intervalSetting;
   private BooleanValue invertIntervalSetting;
   private BooleanValue alwaysSetting;
   private StringValue cancelConditionSetting;
   private RangeSetting tickRangeSetting;
   private int packetCount = 0;
private DisablerIncomingPacketOption(String str, Class clazz) {
      this.name = str;
      this.packetClass = clazz;
   }

   @Override
   public String getName() {
      return this.name;
   }

   @Override
   public boolean isSelected() {
      return this.selected;
   }

   @Override
   public void setSelected(boolean flag) {
      this.selected = flag;
   }

   public static String getOptionName(DisablerIncomingPacketOption option) {
      return option.name;
   }

   public static DoubleValue setIntervalSetting(DisablerIncomingPacketOption option, DoubleValue stringValue) {
      return option.intervalSetting = stringValue;
   }

   public static BooleanValue setInvertIntervalSetting(DisablerIncomingPacketOption option, BooleanValue booleanValue) {
      return option.invertIntervalSetting = booleanValue;
   }

   public static BooleanValue setAlwaysSetting(DisablerIncomingPacketOption option, BooleanValue booleanValue) {
      return option.alwaysSetting = booleanValue;
   }

   public static RangeSetting setTickRangeSetting(DisablerIncomingPacketOption option, RangeSetting rangeSetting) {
      return option.tickRangeSetting = rangeSetting;
   }

   public static StringValue setCancelConditionSetting(DisablerIncomingPacketOption option, StringValue booleansSetting) {
      return option.cancelConditionSetting = booleansSetting;
   }

   public static Class getPacketClass(DisablerIncomingPacketOption option) {
      return option.packetClass;
   }

   public static int incrementPacketCount(DisablerIncomingPacketOption option) {
      return option.packetCount++;
   }

   public static boolean isOptionSelected(DisablerIncomingPacketOption option) {
      return option.selected;
   }

   public static StringValue getCancelConditionSetting(DisablerIncomingPacketOption option) {
      return option.cancelConditionSetting;
   }

   public static DoubleValue getIntervalSetting(DisablerIncomingPacketOption option) {
      return option.intervalSetting;
   }

   public static BooleanValue getInvertIntervalSetting(DisablerIncomingPacketOption option) {
      return option.invertIntervalSetting;
   }

   public static int getPacketCount(DisablerIncomingPacketOption option) {
      return option.packetCount;
   }

   public static BooleanValue getAlwaysSetting(DisablerIncomingPacketOption option) {
      return option.alwaysSetting;
   }

   public static RangeSetting getTickRangeSetting(DisablerIncomingPacketOption option) {
      return option.tickRangeSetting;
   }
}
