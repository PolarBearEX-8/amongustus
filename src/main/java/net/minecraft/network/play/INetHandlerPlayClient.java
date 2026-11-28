package net.minecraft.network.play;

import net.minecraft.network.INetHandler;
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

public interface INetHandlerPlayClient extends INetHandler {
   void handleServerDifficulty(S41PacketServerDifficulty packet);

   void handleTeams(S3EPacketTeams packet);

   void handleEntityHeadLook(S19PacketEntityHeadLook packet);

   void handleRespawn(S07PacketRespawn packet);

   void handlePlayerListItem(S38PacketPlayerListItem packet);

   void handleTitle(S45PacketTitle packet);

   void handleSpawnMob(S0FPacketSpawnMob packet);

   void handleEntityEquipment(S04PacketEntityEquipment packet);

   void handleJoinGame(S01PacketJoinGame packet);

   void handleSoundEffect(S29PacketSoundEffect packet);

   void handleCustomPayload(S3FPacketCustomPayload packet);

   void handleUpdateHealth(S06PacketUpdateHealth packet);

   void handleWindowItems(S30PacketWindowItems packet);

   void handleBlockBreakAnim(S25PacketBlockBreakAnim packet);

   void handleSpawnPainting(S10PacketSpawnPainting packet);

   void handleChangeGameState(S2BPacketChangeGameState packet);

   void handleEntityStatus(S19PacketEntityStatus packet);

   void handleUpdateTileEntity(S35PacketUpdateTileEntity packet);

   void handleUseBed(S0APacketUseBed packet);

   void handleEntityMovement(S14PacketEntity packet);

   void handleEntityMetadata(S1CPacketEntityMetadata packet);

   void handleUpdateSign(S33PacketUpdateSign packet);

   void handleEntityEffect(S1DPacketEntityEffect packet);

   void handleDestroyEntities(S13PacketDestroyEntities packet);

   void handleSpawnExperienceOrb(S11PacketSpawnExperienceOrb packet);

   void handleConfirmTransaction(S32PacketConfirmTransaction packet);

   void handleScoreboardObjective(S3BPacketScoreboardObjective packet);

   void handleCamera(S43PacketCamera packet);

   void handleCloseWindow(S2EPacketCloseWindow packet);

   void handleParticles(S2APacketParticles packet);

   void handleChat(S02PacketChat packet);

   void handleSpawnGlobalEntity(S2CPacketSpawnGlobalEntity packet);

   void handleMaps(S34PacketMaps packet);

   void handleEntityAttach(S1BPacketEntityAttach packet);

   void handlePlayerAbilities(S39PacketPlayerAbilities packet);

   void handleCollectItem(S0DPacketCollectItem packet);

   void handleSpawnPosition(S05PacketSpawnPosition packet);

   void handleMultiBlockChange(S22PacketMultiBlockChange packet);

   void handleSpawnPlayer(S0CPacketSpawnPlayer packet);

   void handleChunkData(S21PacketChunkData packet);

   void handleEffect(S28PacketEffect packet);

   void handlePlayerListHeaderFooter(S47PacketPlayerListHeaderFooter packet);

   void handleWindowProperty(S31PacketWindowProperty packet);

   void handleRemoveEntityEffect(S1EPacketRemoveEntityEffect packet);

   void handleHeldItemChange(S09PacketHeldItemChange packet);

   void handleEntityVelocity(S12PacketEntityVelocity packet);

   void handleBlockAction(S24PacketBlockAction packet);

   void handleSetExperience(S1FPacketSetExperience packet);

   void handleSetCompressionLevel(S46PacketSetCompressionLevel packet);

   void handleStatistics(S37PacketStatistics packet);

   void handleWorldBorder(S44PacketWorldBorder packet);

   void handleCombatEvent(S42PacketCombatEvent packet);

   void handleSetSlot(S2FPacketSetSlot packet);

   void handleEntityNBT(S49PacketUpdateEntityNBT packet);

   void handlePlayerPosLook(S08PacketPlayerPosLook packet);

   void handleOpenWindow(S2DPacketOpenWindow packet);

   void handleSpawnObject(S0EPacketSpawnObject packet);

   void handleBlockChange(S23PacketBlockChange packet);

   void handleUpdateScore(S3CPacketUpdateScore packet);

   void handleSignEditorOpen(S36PacketSignEditorOpen packet);

   void handleAnimation(S0BPacketAnimation packet);

   void handleDisconnect(S40PacketDisconnect packet);

   void handleEntityProperties(S20PacketEntityProperties packet);

   void handleTabComplete(S3APacketTabComplete packet);

   void handleDisplayScoreboard(S3DPacketDisplayScoreboard packet);

   void handleMapChunkBulk(S26PacketMapChunkBulk packet);

   void handleTimeUpdate(S03PacketTimeUpdate packet);

   void handleEntityTeleport(S18PacketEntityTeleport packet);

   void handleResourcePack(S48PacketResourcePackSend packet);

   void handleExplosion(S27PacketExplosion packet);

   void handleKeepAlive(S00PacketKeepAlive packet);
}
