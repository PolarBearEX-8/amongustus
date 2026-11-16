package client.util;

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
   void applyS1FPacketSetExperience(S1FPacketSetExperience packet);

   void applyS42PacketCombatEvent(S42PacketCombatEvent packet);

   void applyS09PacketHeldItemChange(S09PacketHeldItemChange packet);

   void applyS29PacketSoundEffect(S29PacketSoundEffect packet);

   void applyS3FPacketCustomPayload(S3FPacketCustomPayload packet);

   void applyS2BPacketChangeGameState(S2BPacketChangeGameState packet);

   void applyS03PacketTimeUpdate(S03PacketTimeUpdate packet);

   void applyS1CPacketEntityMetadata(S1CPacketEntityMetadata packet);

   void applyS19PacketEntityHeadLook(S19PacketEntityHeadLook packet);

   void applyS41PacketServerDifficulty(S41PacketServerDifficulty packet);

   void applyS49PacketUpdateEntityNBT(S49PacketUpdateEntityNBT packet);

   void applyS0CPacketSpawnPlayer(S0CPacketSpawnPlayer packet);

   void applyS25PacketBlockBreakAnim(S25PacketBlockBreakAnim packet);

   void applyS18PacketEntityTeleport(S18PacketEntityTeleport packet);

   void applyS1EPacketRemoveEntityEffec(S1EPacketRemoveEntityEffect packet);

   void applyS19PacketEntityStatus(S19PacketEntityStatus packet);

   void applyS24PacketBlockAction(S24PacketBlockAction packet);

   void applyS3EPacketTeams(S3EPacketTeams packet);

   void applyS0APacketUseBed(S0APacketUseBed packet);

   void handleS12PacketEntityVelocity(S12PacketEntityVelocity packet);

   void applyS05PacketSpawnPosition(S05PacketSpawnPosition packet);

   void applyS46PacketSetCompressionLev(S46PacketSetCompressionLevel packet);

   void handleS02PacketChat(S02PacketChat packet);

   void applyS26PacketMapChunkBulk(S26PacketMapChunkBulk packet);

   void processAction(S08PacketPlayerPosLook packet);

   void applyS31PacketWindowProperty(S31PacketWindowProperty packet);

   void applyS3DPacketDisplayScoreboard(S3DPacketDisplayScoreboard packet);

   void applyS21PacketChunkData(S21PacketChunkData packet);

   void applyS35PacketUpdateTileEntity(S35PacketUpdateTileEntity packet);

   void applyS0EPacketSpawnObject(S0EPacketSpawnObject packet);

   void applyS43PacketCamera(S43PacketCamera packet);

   void applyS34PacketMaps(S34PacketMaps packet);

   void applyS11PacketSpawnExperienceOr(S11PacketSpawnExperienceOrb packet);

   void applyS39PacketPlayerAbilities(S39PacketPlayerAbilities packet);

   void applyS2APacketParticles(S2APacketParticles packet);

   void applyS20PacketEntityProperties(S20PacketEntityProperties packet);

   void applyS2CPacketSpawnGlobalEntity(S2CPacketSpawnGlobalEntity packet);

   void applyS00PacketKeepAlive(S00PacketKeepAlive packet);

   void applyS23PacketBlockChange(S23PacketBlockChange packet);

   void applyS0FPacketSpawnMob(S0FPacketSpawnMob packet);

   void applyS3BPacketScoreboardObjecti(S3BPacketScoreboardObjective packet);

   void applyS36PacketSignEditorOpen(S36PacketSignEditorOpen packet);

   void handleS07PacketRespawn(S07PacketRespawn packet);

   void applyS2EPacketCloseWindow(S2EPacketCloseWindow packet);

   void applyS44PacketWorldBorder(S44PacketWorldBorder packet);

   void applyS13PacketDestroyEntities(S13PacketDestroyEntities packet);

   void applyS1DPacketEntityEffect(S1DPacketEntityEffect packet);

   void applyS48PacketResourcePackSend(S48PacketResourcePackSend packet);

   void applyS2DPacketOpenWindow(S2DPacketOpenWindow packet);

   void applyS1BPacketEntityAttach(S1BPacketEntityAttach packet);

   void applyS10PacketSpawnPainting(S10PacketSpawnPainting packet);

   void applyS22PacketMultiBlockChange(S22PacketMultiBlockChange packet);

   void applyS04PacketEntityEquipment(S04PacketEntityEquipment packet);

   void applyS0BPacketAnimation(S0BPacketAnimation packet);

   void applyS01PacketJoinGame(S01PacketJoinGame packet);

   void applyS14PacketEntity(S14PacketEntity packet);

   void applyS2FPacketSetSlot(S2FPacketSetSlot packet);

   void applyS3CPacketUpdateScore(S3CPacketUpdateScore packet);

   void applyS06PacketUpdateHealth(S06PacketUpdateHealth packet);

   void applyS30PacketWindowItems(S30PacketWindowItems packet);

   void applyS3APacketTabComplete(S3APacketTabComplete packet);

   void applyS38PacketPlayerListItem(S38PacketPlayerListItem packet);

   void applyS47PacketPlayerListHeaderF(S47PacketPlayerListHeaderFooter packet);

   void processAction(S32PacketConfirmTransaction packet);

   void applyS40PacketDisconnect(S40PacketDisconnect packet);

   void applyS28PacketEffect(S28PacketEffect packet);

   void applyS33PacketUpdateSign(S33PacketUpdateSign packet);

   void applyS37PacketStatistics(S37PacketStatistics packet);

   void applyS27PacketExplosion(S27PacketExplosion packet);

   void applyS45PacketTitle(S45PacketTitle packet);

   void applyS0DPacketCollectItem(S0DPacketCollectItem packet);
}
