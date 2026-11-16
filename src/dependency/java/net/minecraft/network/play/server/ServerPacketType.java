package net.minecraft.network.play.server;

import client.setting.ActionSetting;
import client.setting.BooleanSetting;
import client.setting.ModeSetting;
import client.setting.NamedOption;
import client.setting.NumberSetting;

public enum ServerPacketType implements NamedOption {
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
    S15PacketEntityRelMove("S15PacketEntityRelMove", S15PacketEntityRelMove.class),
    S16PacketEntityLook("S16PacketEntityLook", S16PacketEntityLook.class),
    S17PacketEntityLookMove("S17PacketEntityLookMove", S17PacketEntityLookMove.class),
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

   public String labelText;
   public boolean enabled;
   public Class memberRef;
   public NumberSetting numberSetting;
   public BooleanSetting booleanSetting;
   public ModeSetting modeSetting;
   public ActionSetting actionSetting;
   public int count = 0;
   public static final ServerPacketType[] ENUM_VALUES = new ServerPacketType[]{
      S00PacketKeepAlive,
      S02PacketChat,
      S03PacketTimeUpdate,
      S04PacketEntityEquipment,
      S05PacketSpawnPosition,
      S06PacketUpdateHealth,
      S07PacketRespawn,
      S08PacketPlayerPosLook,
      S09PacketHeldItemChange,
      S0APacketUseBed,
      S0BPacketAnimation,
      S0CPacketSpawnPlayer,
      S0DPacketCollectItem,
      S0EPacketSpawnObject,
      S0FPacketSpawnMob,
      S10PacketSpawnPainting,
      S11PacketSpawnExperienceOrb,
      S12PacketEntityVelocity,
      S13PacketDestroyEntities,
      S14PacketEntity,
      S15PacketEntityRelMove,
      S16PacketEntityLook,
      S17PacketEntityLookMove,
      S18PacketEntityTeleport,
      S19PacketEntityHeadLook,
      S19PacketEntityStatus,
      S1BPacketEntityAttach,
      S1CPacketEntityMetadata,
      S1DPacketEntityEffect,
      S1EPacketRemoveEntityEffect,
      S1FPacketSetExperience,
      S20PacketEntityProperties,
      S21PacketChunkData,
      S22PacketMultiBlockChange,
      S23PacketBlockChange,
      S24PacketBlockAction,
      S25PacketBlockBreakAnim,
      S26PacketMapChunkBulk,
      S27PacketExplosion,
      S28PacketEffect,
      S29PacketSoundEffect,
      S2APacketParticles,
      S2BPacketChangeGameState,
      S2CPacketSpawnGlobalEntity,
      S2DPacketOpenWindow,
      S2EPacketCloseWindow,
      S2FPacketSetSlot,
      S30PacketWindowItems,
      S31PacketWindowProperty,
      S32PacketConfirmTransaction,
      S33PacketUpdateSign,
      S34PacketMaps,
      S35PacketUpdateTileEntity,
      S36PacketSignEditorOpen,
      S37PacketStatistics,
      S38PacketPlayerListItem,
      S39PacketPlayerAbilities,
      S3APacketTabComplete,
      S3BPacketScoreboardObjective,
      S3CPacketUpdateScore,
      S3DPacketDisplayScoreboard,
      S3EPacketTeams,
      S3FPacketCustomPayload,
      S40PacketDisconnect,
      S41PacketServerDifficulty,
      S42PacketCombatEvent,
      S43PacketCamera,
      S44PacketWorldBorder,
      S45PacketTitle,
      S46PacketSetCompressionLevel,
      S47PacketPlayerListHeaderFooter,
      S48PacketResourcePackSend,
      S49PacketUpdateEntityNBT
   };

   public static BooleanSetting handlePacket(ServerPacketType packet) {
      return packet.booleanSetting;
   }

   public static Class handlePacket2(ServerPacketType packet) {
      return packet.memberRef;
   }

   public static int handlePacket3(ServerPacketType packet) {
      return packet.count++;
   }

   @Override
   public void setActive(boolean active) {
      this.enabled = active;
   }

   @Override
   public String getText() {
      return this.labelText;
   }

   public static NumberSetting handlePacket4(ServerPacketType packet) {
      return packet.numberSetting;
   }

   public static boolean checkServerPacketType(ServerPacketType packet) {
      return packet.enabled;
   }

   public static String handlePacket5(ServerPacketType packet) {
      return packet.labelText;
   }

   public static ModeSetting handlePacket6(ServerPacketType packet) {
      return packet.modeSetting;
   }

   public static ActionSetting resolveActionSetting(ServerPacketType packet, ActionSetting setting) {
      return packet.actionSetting = setting;
   }

   public static NumberSetting handlePacket(ServerPacketType packet, NumberSetting setting) {
      return packet.numberSetting = setting;
   }

   public static BooleanSetting handlePacket(ServerPacketType packet, BooleanSetting setting) {
      return packet.booleanSetting = setting;
   }

   public static int handlePacket7(ServerPacketType packet) {
      return packet.count;
   }

   public static ModeSetting handlePacket(ServerPacketType packet, ModeSetting setting) {
      return packet.modeSetting = setting;
   }

   public static ActionSetting handlePacket8(ServerPacketType packet) {
      return packet.actionSetting;
   }

   @Override
   public boolean isSelected() {
      return this.enabled;
   }

   private ServerPacketType(String text, Class clazz) {
      this.labelText = text;
      this.memberRef = clazz;
   }


}
