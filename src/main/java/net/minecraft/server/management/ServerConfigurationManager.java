package net.minecraft.server.management;

import net.minecraft.world.WorldSettings;
import net.minecraft.world.border.IBorderListener;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.mojang.authlib.GameProfile;
import io.netty.buffer.Unpooled;
import java.io.File;
import java.net.SocketAddress;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetHandlerPlayServer;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.server.S01PacketJoinGame;
import net.minecraft.network.play.server.S02PacketChat;
import net.minecraft.network.play.server.S03PacketTimeUpdate;
import net.minecraft.network.play.server.S05PacketSpawnPosition;
import net.minecraft.network.play.server.S07PacketRespawn;
import net.minecraft.network.play.server.S09PacketHeldItemChange;
import net.minecraft.network.play.server.S1DPacketEntityEffect;
import net.minecraft.network.play.server.S1FPacketSetExperience;
import net.minecraft.network.play.server.S2BPacketChangeGameState;
import net.minecraft.network.play.server.S38PacketPlayerListItem;
import net.minecraft.network.play.server.S39PacketPlayerAbilities;
import net.minecraft.network.play.server.S3EPacketTeams;
import net.minecraft.network.play.server.S3FPacketCustomPayload;
import net.minecraft.network.play.server.S41PacketServerDifficulty;
import net.minecraft.network.play.server.S44PacketWorldBorder;
import net.minecraft.potion.PotionEffect;
import net.minecraft.scoreboard.ScoreObjective;
import net.minecraft.scoreboard.ScorePlayerTeam;
import net.minecraft.scoreboard.ServerScoreboard;
import net.minecraft.scoreboard.Team;
import net.minecraft.server.MinecraftServer;
import net.minecraft.stats.StatList;
import net.minecraft.stats.StatisticsFile;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraft.world.border.WorldBorder;
import net.minecraft.world.demo.DemoWorldManager;
import net.minecraft.world.storage.IPlayerFileData;
import net.minecraft.world.storage.WorldInfo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class ServerConfigurationManager {
   public static final File FILE_PLAYERBANS = new File("banned-players.json");
   public static final File FILE_IPBANS = new File("banned-ips.json");
   public static final File FILE_OPS = new File("ops.json");
   public static final File FILE_WHITELIST = new File("whitelist.json");
   private static final Logger LOGGER = LogManager.getLogger();
   private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
   private final MinecraftServer mcServer;
   private final List playerEntityList = Lists.newArrayList();
   private final Map uuidToPlayerMap = Maps.newHashMap();
   private final UserListBans bannedPlayers = new UserListBans(FILE_PLAYERBANS);
   private final BanList bannedIPs = new BanList(FILE_IPBANS);
   private final UserListOps ops = new UserListOps(FILE_OPS);
   private final UserListWhitelist whiteListedPlayers = new UserListWhitelist(FILE_WHITELIST);
   private final Map playerStatFiles = Maps.newHashMap();
   private IPlayerFileData playerNBTManagerObj;
   private boolean whiteListEnforced;
   public int maxPlayers;
   private int viewDistance;
   private WorldSettings.GameType gameType;
   private boolean commandsAllowedForAll;
   private int playerPingIndex;

   public MinecraftServer getServerInstance() {
      return this.mcServer;
   }

   public void sendChatMsg(IChatComponent component) {
      this.sendChatMsgImpl(component, true);
   }

   public UserListWhitelist getWhitelistedPlayers() {
      return this.whiteListedPlayers;
   }

   public void initializeConnectionToPlayer(NetworkManager networkManager, EntityPlayerMP entityPlayerMP) {
      GameProfile gameProfile = entityPlayerMP.getGameProfile();
      PlayerProfileCache playerProfileCache = this.mcServer.getPlayerProfileCache();
      GameProfile profileByUUID = playerProfileCache.getProfileByUUID(gameProfile.getId());
      String str = profileByUUID == null ? gameProfile.getName() : profileByUUID.getName();
      playerProfileCache.addEntry(gameProfile);
      NBTTagCompound tag = this.readPlayerDataFromFile(entityPlayerMP);
      entityPlayerMP.setWorld(this.mcServer.worldServerForDimension(entityPlayerMP.dimension));
      entityPlayerMP.theItemInWorldManager.setWorld((WorldServer)entityPlayerMP.entityWorld);
      String str2 = "local";
      if (networkManager.getRemoteAddress() != null) {
         str2 = networkManager.getRemoteAddress().toString();
      }

      LOGGER.info(
         entityPlayerMP.getName() + "[" + str2 + "] logged in with entity id " + entityPlayerMP.getEntityId() + " at (" + entityPlayerMP.posX + ", " + entityPlayerMP.posY + ", " + entityPlayerMP.posZ + ")"
      );
      WorldServer world = this.mcServer.worldServerForDimension(entityPlayerMP.dimension);
      WorldInfo worldInfo = world.getWorldInfo();
      BlockPos spawnPoint = world.getSpawnPoint();
      this.setPlayerGameTypeBasedOnOther(entityPlayerMP, (EntityPlayerMP)null, world);
      NetHandlerPlayServer netHandlerPlayServer = new NetHandlerPlayServer(this.mcServer, networkManager, entityPlayerMP);
      netHandlerPlayServer.sendPacket(
         new S01PacketJoinGame(
            entityPlayerMP.getEntityId(),
            entityPlayerMP.theItemInWorldManager.getGameType(),
            worldInfo.isHardcoreModeEnabled(),
            world.provider.getDimensionId(),
            world.getDifficulty(),
            this.getMaxPlayers(),
            worldInfo.getTerrainType(),
            world.getGameRules().getBoolean("reducedDebugInfo")
         )
      );
      netHandlerPlayServer.sendPacket(new S3FPacketCustomPayload("MC|Brand", new PacketBuffer(Unpooled.buffer()).writeString(this.getServerInstance().getServerModName())));
      netHandlerPlayServer.sendPacket(new S41PacketServerDifficulty(worldInfo.getDifficulty(), worldInfo.isDifficultyLocked()));
      netHandlerPlayServer.sendPacket(new S05PacketSpawnPosition(spawnPoint));
      netHandlerPlayServer.sendPacket(new S39PacketPlayerAbilities(entityPlayerMP.capabilities));
      netHandlerPlayServer.sendPacket(new S09PacketHeldItemChange(entityPlayerMP.inventory.currentItem));
      entityPlayerMP.getStatFile().markAllDirty();
      entityPlayerMP.getStatFile().sendAchievements(entityPlayerMP);
      this.sendScoreboard((ServerScoreboard)world.getScoreboard(), entityPlayerMP);
      this.mcServer.refreshStatusNextTick();
      ChatComponentTranslation chatComponentTranslation;
      if (!entityPlayerMP.getName().equalsIgnoreCase(str)) {
         chatComponentTranslation = new ChatComponentTranslation("multiplayer.player.joined.renamed", new Object[]{entityPlayerMP.getDisplayName(), str});
      } else {
         chatComponentTranslation = new ChatComponentTranslation("multiplayer.player.joined", new Object[]{entityPlayerMP.getDisplayName()});
      }

      chatComponentTranslation.getChatStyle().setColor(EnumChatFormatting.YELLOW);
      this.sendChatMsg(chatComponentTranslation);
      this.playerLoggedIn(entityPlayerMP);
      netHandlerPlayServer.setPlayerLocation(entityPlayerMP.posX, entityPlayerMP.posY, entityPlayerMP.posZ, entityPlayerMP.rotationYaw, entityPlayerMP.rotationPitch);
      this.updateTimeAndWeatherForPlayer(entityPlayerMP, world);
      if (this.mcServer.getResourcePackUrl().length() > 0) {
         entityPlayerMP.loadResourcePack(this.mcServer.getResourcePackUrl(), this.mcServer.getResourcePackHash());
      }

      for (PotionEffect potionEffect : (Iterable<PotionEffect>)(entityPlayerMP.getActivePotionEffects())) {
         netHandlerPlayServer.sendPacket(new S1DPacketEntityEffect(entityPlayerMP.getEntityId(), potionEffect));
      }

      entityPlayerMP.addSelfToInternalCraftingInventory();
      if (tag != null && tag.hasKey("Riding", 10)) {
         Entity entity = EntityList.createEntityFromNBT(tag.getCompoundTag("Riding"), world);
         if (entity != null) {
            entity.forceSpawn = true;
            world.spawnEntityInWorld(entity);
            entityPlayerMP.mountEntity(entity);
            entity.forceSpawn = false;
         }
      }
   }

   public void sendPacketToAllPlayersInDimension(Packet packet, int n) {
      for (int i = 0; i < this.playerEntityList.size(); i++) {
         EntityPlayerMP entityPlayerMP = (EntityPlayerMP)this.playerEntityList.get(i);
         if (entityPlayerMP.dimension == n) {
            entityPlayerMP.playerNetServerHandler.sendPacket(packet);
         }
      }
   }

   public int getViewDistance() {
      return this.viewDistance;
   }

   public int getCurrentPlayerCount() {
      return this.playerEntityList.size();
   }

   public NBTTagCompound getHostPlayerData() {
      return null;
   }

   public EntityPlayerMP getPlayerByUUID(UUID uuid) {
      return (EntityPlayerMP)this.uuidToPlayerMap.get(uuid);
   }

   public String allowUserToConnect(SocketAddress socketAddress, GameProfile profile) {
      if (this.bannedPlayers.isBanned(profile)) {
         UserListBansEntry entry = (UserListBansEntry)this.bannedPlayers.getEntry(profile);
         String str = "You are banned from this server!\nReason: " + entry.getBanReason();
         if (entry.getBanEndDate() != null) {
            str = str + "\nYour ban will be removed on " + dateFormat.format(entry.getBanEndDate());
         }

         return str;
      } else if (!this.canJoin(profile)) {
         return "You are not white-listed on this server!";
      } else if (this.bannedIPs.isBanned(socketAddress)) {
         IPBanEntry banEntry = this.bannedIPs.getBanEntry(socketAddress);
         String str2 = "Your IP address is banned from this server!\nReason: " + banEntry.getBanReason();
         if (banEntry.getBanEndDate() != null) {
            str2 = str2 + "\nYour ban will be removed on " + dateFormat.format(banEntry.getBanEndDate());
         }

         return str2;
      } else {
         return this.playerEntityList.size() >= this.maxPlayers && !this.bypassesPlayerLimit(profile) ? "The server is full!" : null;
      }
   }

   public void sendChatMsgImpl(IChatComponent component, boolean flag) {
      this.mcServer.addChatMessage(component);
      byte b = (byte)(flag ? 1 : 0);
      this.sendPacketToAllPlayers(new S02PacketChat(component, b));
   }

   public void setGameType(WorldSettings.GameType gameType2) {
      this.gameType = gameType2;
   }

   public void syncPlayerInventory(EntityPlayerMP entityPlayerMP) {
      entityPlayerMP.sendContainerToPlayer(entityPlayerMP.inventoryContainer);
      entityPlayerMP.setPlayerHealthUpdated();
      entityPlayerMP.playerNetServerHandler.sendPacket(new S09PacketHeldItemChange(entityPlayerMP.inventory.currentItem));
   }

   public void onTick() {
      if (++this.playerPingIndex > 600) {
         this.sendPacketToAllPlayers(new S38PacketPlayerListItem(S38PacketPlayerListItem.Action.UPDATE_LATENCY, this.playerEntityList));
         this.playerPingIndex = 0;
      }
   }

   public void sendToAllNearExcept(EntityPlayer player, double d, double d2, double d3, double d4, int n, Packet packet) {
      for (int i = 0; i < this.playerEntityList.size(); i++) {
         EntityPlayerMP entityPlayerMP = (EntityPlayerMP)this.playerEntityList.get(i);
         if (entityPlayerMP != player && entityPlayerMP.dimension == n) {
            double d5 = d - entityPlayerMP.posX;
            double d6 = d2 - entityPlayerMP.posY;
            double d7 = d3 - entityPlayerMP.posZ;
            if (d5 * d5 + d6 * d6 + d7 * d7 < d4 * d4) {
               entityPlayerMP.playerNetServerHandler.sendPacket(packet);
            }
         }
      }
   }

   public void sendMessageToAllTeamMembers(EntityPlayer player, IChatComponent component) {
      Team team = player.getTeam();
      if (team != null) {
         for (String str : (Iterable<String>)(team.getMembershipCollection())) {
            EntityPlayerMP playerByUsername = this.getPlayerByUsername(str);
            if (playerByUsername != null && playerByUsername != player) {
               playerByUsername.addChatMessage(component);
            }
         }
      }
   }

   public void preparePlayer(EntityPlayerMP entityPlayerMP, WorldServer world) {
      WorldServer serverForPlayer = entityPlayerMP.getServerForPlayer();
      if (world != null) {
         world.getPlayerManager().removePlayer(entityPlayerMP);
      }

      serverForPlayer.getPlayerManager().addPlayer(entityPlayerMP);
      serverForPlayer.theChunkProviderServer.loadChunk((int)entityPlayerMP.posX >> 4, (int)entityPlayerMP.posZ >> 4);
   }

   public String[] getAvailablePlayerDat() {
      return this.mcServer.worldServers[0].getSaveHandler().getPlayerNBTManager().getAvailablePlayerDat();
   }

   public void writePlayerData(EntityPlayerMP entityPlayerMP) {
      this.playerNBTManagerObj.writePlayerData(entityPlayerMP);
      StatisticsFile statisticsFile = (StatisticsFile)this.playerStatFiles.get(entityPlayerMP.getUniqueID());
      if (statisticsFile != null) {
         statisticsFile.saveStatFile();
      }
   }

   public String[] getAllUsernames() {
      String[] strings = new String[this.playerEntityList.size()];

      for (int i = 0; i < this.playerEntityList.size(); i++) {
         strings[i] = ((EntityPlayerMP)this.playerEntityList.get(i)).getName();
      }

      return strings;
   }

   public void sendPacketToAllPlayers(Packet packet) {
      for (int i = 0; i < this.playerEntityList.size(); i++) {
         ((EntityPlayerMP)this.playerEntityList.get(i)).playerNetServerHandler.sendPacket(packet);
      }
   }

   public String[] getWhitelistedPlayerNames() {
      return this.whiteListedPlayers.getKeys();
   }

   public void addOp(GameProfile profile) {
      this.ops.addEntry(new UserListOpsEntry(profile, this.mcServer.getOpPermissionLevel(), this.ops.bypassesPlayerLimit(profile)));
   }

   public List getPlayersMatchingAddress(String str) {
      ArrayList list = Lists.newArrayList();

      for (EntityPlayerMP entityPlayerMP : (Iterable<EntityPlayerMP>)(this.playerEntityList)) {
         if (entityPlayerMP.getPlayerIP().equals(str)) {
            list.add(entityPlayerMP);
         }
      }

      return list;
   }

   public void serverUpdateMountedMovingPlayer(EntityPlayerMP entityPlayerMP) {
      entityPlayerMP.getServerForPlayer().getPlayerManager().updateMountedMovingPlayer(entityPlayerMP);
   }

   public void transferPlayerToDimension(EntityPlayerMP entityPlayerMP, int n) {
      int dimension = entityPlayerMP.dimension;
      WorldServer world = this.mcServer.worldServerForDimension(entityPlayerMP.dimension);
      entityPlayerMP.dimension = n;
      WorldServer world2 = this.mcServer.worldServerForDimension(entityPlayerMP.dimension);
      entityPlayerMP.playerNetServerHandler
         .sendPacket(
            new S07PacketRespawn(
               entityPlayerMP.dimension, entityPlayerMP.entityWorld.getDifficulty(), entityPlayerMP.entityWorld.getWorldInfo().getTerrainType(), entityPlayerMP.theItemInWorldManager.getGameType()
            )
         );
      world.removePlayerEntityDangerously(entityPlayerMP);
      entityPlayerMP.isDead = false;
      this.transferEntityToWorld(entityPlayerMP, dimension, world, world2);
      this.preparePlayer(entityPlayerMP, world);
      entityPlayerMP.playerNetServerHandler.setPlayerLocation(entityPlayerMP.posX, entityPlayerMP.posY, entityPlayerMP.posZ, entityPlayerMP.rotationYaw, entityPlayerMP.rotationPitch);
      entityPlayerMP.theItemInWorldManager.setWorld(world2);
      this.updateTimeAndWeatherForPlayer(entityPlayerMP, world2);
      this.syncPlayerInventory(entityPlayerMP);

      for (PotionEffect potionEffect : (Iterable<PotionEffect>)(entityPlayerMP.getActivePotionEffects())) {
         entityPlayerMP.playerNetServerHandler.sendPacket(new S1DPacketEntityEffect(entityPlayerMP.getEntityId(), potionEffect));
      }
   }

   public void loadWhiteList() {
   }

   public void playerLoggedOut(EntityPlayerMP entityPlayerMP) {
      entityPlayerMP.triggerAchievement(StatList.leaveGameStat);
      this.writePlayerData(entityPlayerMP);
      WorldServer serverForPlayer = entityPlayerMP.getServerForPlayer();
      if (entityPlayerMP.ridingEntity != null) {
         serverForPlayer.removePlayerEntityDangerously(entityPlayerMP.ridingEntity);
         LOGGER.debug("removing player mount");
      }

      serverForPlayer.removeEntity(entityPlayerMP);
      serverForPlayer.getPlayerManager().removePlayer(entityPlayerMP);
      this.playerEntityList.remove(entityPlayerMP);
      UUID uniqueID = entityPlayerMP.getUniqueID();
      if (((EntityPlayerMP)this.uuidToPlayerMap.get(uniqueID)) == entityPlayerMP) {
         this.uuidToPlayerMap.remove(uniqueID);
         this.playerStatFiles.remove(uniqueID);
      }

      this.sendPacketToAllPlayers(new S38PacketPlayerListItem(S38PacketPlayerListItem.Action.REMOVE_PLAYER, new EntityPlayerMP[]{entityPlayerMP}));
   }

   public String getFormattedListOfPlayers(boolean flag) {
      String str = "";
      ArrayList list = Lists.newArrayList(this.playerEntityList);

      for (int i = 0; i < list.size(); i++) {
         if (i > 0) {
            str = str + ", ";
         }

         str = str + ((EntityPlayerMP)list.get(i)).getName();
         if (flag) {
            str = str + " (" + ((EntityPlayerMP)list.get(i)).getUniqueID().toString() + ")";
         }
      }

      return str;
   }

   public GameProfile[] getAllProfiles() {
      GameProfile[] profiles = new GameProfile[this.playerEntityList.size()];

      for (int i = 0; i < this.playerEntityList.size(); i++) {
         profiles[i] = ((EntityPlayerMP)this.playerEntityList.get(i)).getGameProfile();
      }

      return profiles;
   }

   public void removeAllPlayers() {
      for (int i = 0; i < this.playerEntityList.size(); i++) {
         ((EntityPlayerMP)this.playerEntityList.get(i)).playerNetServerHandler.kickPlayerFromServer("Server closed");
      }
   }

   public EntityPlayerMP getPlayerByUsername(String str) {
      for (EntityPlayerMP entityPlayerMP : (Iterable<EntityPlayerMP>)(this.playerEntityList)) {
         if (entityPlayerMP.getName().equalsIgnoreCase(str)) {
            return entityPlayerMP;
         }
      }

      return null;
   }

   public UserListBans getBannedPlayers() {
      return this.bannedPlayers;
   }

   public EntityPlayerMP createPlayerForUser(GameProfile profile) {
      UUID uuid = EntityPlayer.getUUID(profile);
      ArrayList list = Lists.newArrayList();

      for (int i = 0; i < this.playerEntityList.size(); i++) {
         EntityPlayerMP entityPlayerMP = (EntityPlayerMP)this.playerEntityList.get(i);
         if (entityPlayerMP.getUniqueID().equals(uuid)) {
            list.add(entityPlayerMP);
         }
      }

      EntityPlayerMP entityPlayerMP2 = (EntityPlayerMP)this.uuidToPlayerMap.get(profile.getId());
      if (entityPlayerMP2 != null && !list.contains(entityPlayerMP2)) {
         list.add(entityPlayerMP2);
      }

      for (EntityPlayerMP entityPlayerMP3 : (Iterable<EntityPlayerMP>)(list)) {
         entityPlayerMP3.playerNetServerHandler.kickPlayerFromServer("You logged in from another location");
      }

      Object obj;
      if (this.mcServer.isDemo()) {
         obj = new DemoWorldManager(this.mcServer.worldServerForDimension(0));
      } else {
         obj = new ItemInWorldManager(this.mcServer.worldServerForDimension(0));
      }

      return new EntityPlayerMP(this.mcServer, this.mcServer.worldServerForDimension(0), profile, (ItemInWorldManager)obj);
   }

   public void setCommandsAllowedForAll(boolean flag) {
      this.commandsAllowedForAll = flag;
   }

   public boolean canSendCommands(GameProfile profile) {
      return this.ops.hasEntry(profile)
         || this.mcServer.isSinglePlayer()
            && this.mcServer.worldServers[0].getWorldInfo().areCommandsAllowed()
            && this.mcServer.getServerOwner().equalsIgnoreCase(profile.getName())
         || this.commandsAllowedForAll;
   }

   public boolean bypassesPlayerLimit(GameProfile profile) {
      return false;
   }

   public void setPlayerManager(WorldServer[] worlds) {
      this.playerNBTManagerObj = worlds[0].getSaveHandler().getPlayerNBTManager();
      worlds[0].getWorldBorder().addListener(new ServerConfigurationManager.DefaultBorderListener(this));
   }

   public void removePlayerFromWhitelist(GameProfile profile) {
      this.whiteListedPlayers.removeEntry(profile);
   }

   public boolean canJoin(GameProfile profile) {
      return !this.whiteListEnforced || this.ops.hasEntry(profile) || this.whiteListedPlayers.hasEntry(profile);
   }

   public void setWhiteListEnabled(boolean flag) {
      this.whiteListEnforced = flag;
   }

   public void transferEntityToWorld(Entity entity, int n, WorldServer world, WorldServer world2) {
      double posX = entity.posX;
      double posZ = entity.posZ;
      double d = 8.0;
      float rotationYaw = entity.rotationYaw;
      world.theProfiler.startSection("moving");
      if (entity.dimension == -1) {
         posX = MathHelper.clamp_double(posX / d, world2.getWorldBorder().minX() + 16.0, world2.getWorldBorder().maxX() - 16.0);
         posZ = MathHelper.clamp_double(posZ / d, world2.getWorldBorder().minZ() + 16.0, world2.getWorldBorder().maxZ() - 16.0);
         entity.setLocationAndAngles(posX, entity.posY, posZ, entity.rotationYaw, entity.rotationPitch);
         if (entity.isEntityAlive()) {
            world.updateEntityWithOptionalForce(entity, false);
         }
      } else if (entity.dimension == 0) {
         posX = MathHelper.clamp_double(posX * d, world2.getWorldBorder().minX() + 16.0, world2.getWorldBorder().maxX() - 16.0);
         posZ = MathHelper.clamp_double(posZ * d, world2.getWorldBorder().minZ() + 16.0, world2.getWorldBorder().maxZ() - 16.0);
         entity.setLocationAndAngles(posX, entity.posY, posZ, entity.rotationYaw, entity.rotationPitch);
         if (entity.isEntityAlive()) {
            world.updateEntityWithOptionalForce(entity, false);
         }
      } else {
         BlockPos pos;
         if (n == 1) {
            pos = world2.getSpawnPoint();
         } else {
            pos = world2.getSpawnCoordinate();
         }

         posX = (double)pos.getX();
         entity.posY = (double)pos.getY();
         posZ = (double)pos.getZ();
         entity.setLocationAndAngles(posX, entity.posY, posZ, 90.0F, 0.0F);
         if (entity.isEntityAlive()) {
            world.updateEntityWithOptionalForce(entity, false);
         }
      }

      world.theProfiler.endSection();
      if (n != 1) {
         world.theProfiler.startSection("placing");
         posX = (double)MathHelper.clamp_int((int)posX, -29999872, 29999872);
         posZ = (double)MathHelper.clamp_int((int)posZ, -29999872, 29999872);
         if (entity.isEntityAlive()) {
            entity.setLocationAndAngles(posX, entity.posY, posZ, entity.rotationYaw, entity.rotationPitch);
            world2.getDefaultTeleporter().placeInPortal(entity, rotationYaw);
            world2.spawnEntityInWorld(entity);
            world2.updateEntityWithOptionalForce(entity, false);
         }

         world.theProfiler.endSection();
      }

      entity.setWorld(world2);
   }

   private void setPlayerGameTypeBasedOnOther(EntityPlayerMP entityPlayerMP, EntityPlayerMP entityPlayerMP2, World world) {
      if (entityPlayerMP2 != null) {
         entityPlayerMP.theItemInWorldManager.setGameType(entityPlayerMP2.theItemInWorldManager.getGameType());
      } else if (this.gameType != null) {
         entityPlayerMP.theItemInWorldManager.setGameType(this.gameType);
      }

      entityPlayerMP.theItemInWorldManager.initializeGameType(world.getWorldInfo().getGameType());
   }

   public void sendToAllNear(double d, double d2, double d3, double d4, int n, Packet packet) {
      this.sendToAllNearExcept((EntityPlayer)null, d, d2, d3, d4, n, packet);
   }

   public StatisticsFile getPlayerStatsFile(EntityPlayer player) {
      UUID uniqueID = player.getUniqueID();
      StatisticsFile statisticsFile = uniqueID == null ? null : (StatisticsFile)this.playerStatFiles.get(uniqueID);
      if (statisticsFile == null) {
         File file = new File(this.mcServer.worldServerForDimension(0).getSaveHandler().getWorldDirectory(), "stats");
         File file2 = new File(file, uniqueID.toString() + ".json");
         if (!file2.exists()) {
            File file3 = new File(file, player.getName() + ".json");
            if (file3.exists() && file3.isFile()) {
               file3.renameTo(file2);
            }
         }

         statisticsFile = new StatisticsFile(this.mcServer, file2);
         statisticsFile.readStatFile();
         this.playerStatFiles.put(uniqueID, statisticsFile);
      }

      return statisticsFile;
   }

   public void sendMessageToTeamOrEvryPlayer(EntityPlayer player, IChatComponent component) {
      Team team = player.getTeam();
      if (team == null) {
         this.sendChatMsg(component);
      } else {
         for (int i = 0; i < this.playerEntityList.size(); i++) {
            EntityPlayerMP entityPlayerMP = (EntityPlayerMP)this.playerEntityList.get(i);
            if (entityPlayerMP.getTeam() != team) {
               entityPlayerMP.addChatMessage(component);
            }
         }
      }
   }

   public EntityPlayerMP recreatePlayerEntity(EntityPlayerMP entityPlayerMP, int n, boolean flag) {
      entityPlayerMP.getServerForPlayer().getEntityTracker().removePlayerFromTrackers(entityPlayerMP);
      entityPlayerMP.getServerForPlayer().getEntityTracker().untrackEntity(entityPlayerMP);
      entityPlayerMP.getServerForPlayer().getPlayerManager().removePlayer(entityPlayerMP);
      this.playerEntityList.remove(entityPlayerMP);
      this.mcServer.worldServerForDimension(entityPlayerMP.dimension).removePlayerEntityDangerously(entityPlayerMP);
      BlockPos bedLocation = entityPlayerMP.getBedLocation();
      boolean isSpawnForced = entityPlayerMP.isSpawnForced();
      entityPlayerMP.dimension = n;
      Object obj;
      if (this.mcServer.isDemo()) {
         obj = new DemoWorldManager(this.mcServer.worldServerForDimension(entityPlayerMP.dimension));
      } else {
         obj = new ItemInWorldManager(this.mcServer.worldServerForDimension(entityPlayerMP.dimension));
      }

      EntityPlayerMP entityPlayerMP2 = new EntityPlayerMP(
         this.mcServer, this.mcServer.worldServerForDimension(entityPlayerMP.dimension), entityPlayerMP.getGameProfile(), (ItemInWorldManager)obj
      );
      entityPlayerMP2.playerNetServerHandler = entityPlayerMP.playerNetServerHandler;
      entityPlayerMP2.clonePlayer(entityPlayerMP, flag);
      entityPlayerMP2.setEntityId(entityPlayerMP.getEntityId());
      entityPlayerMP2.copyCommandStats(entityPlayerMP);
      WorldServer world = this.mcServer.worldServerForDimension(entityPlayerMP.dimension);
      this.setPlayerGameTypeBasedOnOther(entityPlayerMP2, entityPlayerMP, world);
      if (bedLocation != null) {
         BlockPos bedSpawnLocation = EntityPlayer.getBedSpawnLocation(this.mcServer.worldServerForDimension(entityPlayerMP.dimension), bedLocation, isSpawnForced);
         if (bedSpawnLocation != null) {
            entityPlayerMP2.setLocationAndAngles((double)((float)bedSpawnLocation.getX() + 0.5F), (double)((float)bedSpawnLocation.getY() + 0.1F), (double)((float)bedSpawnLocation.getZ() + 0.5F), 0.0F, 0.0F);
            entityPlayerMP2.setSpawnPoint(bedLocation, isSpawnForced);
         } else {
            entityPlayerMP2.playerNetServerHandler.sendPacket(new S2BPacketChangeGameState(0, 0.0F));
         }
      }

      world.theChunkProviderServer.loadChunk((int)entityPlayerMP2.posX >> 4, (int)entityPlayerMP2.posZ >> 4);

      while (!world.getCollidingBoundingBoxes(entityPlayerMP2, entityPlayerMP2.getEntityBoundingBox()).isEmpty() && entityPlayerMP2.posY < 256.0) {
         entityPlayerMP2.setPosition(entityPlayerMP2.posX, entityPlayerMP2.posY + 1.0, entityPlayerMP2.posZ);
      }

      entityPlayerMP2.playerNetServerHandler
         .sendPacket(
            new S07PacketRespawn(
               entityPlayerMP2.dimension, entityPlayerMP2.entityWorld.getDifficulty(), entityPlayerMP2.entityWorld.getWorldInfo().getTerrainType(), entityPlayerMP2.theItemInWorldManager.getGameType()
            )
         );
      BlockPos spawnPoint = world.getSpawnPoint();
      entityPlayerMP2.playerNetServerHandler.setPlayerLocation(entityPlayerMP2.posX, entityPlayerMP2.posY, entityPlayerMP2.posZ, entityPlayerMP2.rotationYaw, entityPlayerMP2.rotationPitch);
      entityPlayerMP2.playerNetServerHandler.sendPacket(new S05PacketSpawnPosition(spawnPoint));
      entityPlayerMP2.playerNetServerHandler.sendPacket(new S1FPacketSetExperience(entityPlayerMP2.experience, entityPlayerMP2.experienceTotal, entityPlayerMP2.experienceLevel));
      this.updateTimeAndWeatherForPlayer(entityPlayerMP2, world);
      world.getPlayerManager().addPlayer(entityPlayerMP2);
      world.spawnEntityInWorld(entityPlayerMP2);
      this.playerEntityList.add(entityPlayerMP2);
      this.uuidToPlayerMap.put(entityPlayerMP2.getUniqueID(), entityPlayerMP2);
      entityPlayerMP2.addSelfToInternalCraftingInventory();
      entityPlayerMP2.setHealth(entityPlayerMP2.getCurrentHealth());
      return entityPlayerMP2;
   }

   public int getMaxPlayers() {
      return this.maxPlayers;
   }

   public void updateTimeAndWeatherForPlayer(EntityPlayerMP entityPlayerMP, WorldServer world) {
      WorldBorder worldBorder = this.mcServer.worldServers[0].getWorldBorder();
      entityPlayerMP.playerNetServerHandler.sendPacket(new S44PacketWorldBorder(worldBorder, S44PacketWorldBorder.Action.INITIALIZE));
      entityPlayerMP.playerNetServerHandler
         .sendPacket(new S03PacketTimeUpdate(world.getTotalWorldTime(), world.getWorldTime(), world.getGameRules().getBoolean("doDaylightCycle")));
      if (world.isRaining()) {
         entityPlayerMP.playerNetServerHandler.sendPacket(new S2BPacketChangeGameState(1, 0.0F));
         entityPlayerMP.playerNetServerHandler.sendPacket(new S2BPacketChangeGameState(7, world.getRainStrength(1.0F)));
         entityPlayerMP.playerNetServerHandler.sendPacket(new S2BPacketChangeGameState(8, world.getThunderStrength(1.0F)));
      }
   }

   public void addWhitelistedPlayer(GameProfile profile) {
      this.whiteListedPlayers.addEntry(new UserListWhitelistEntry(profile));
   }

   public BanList getBannedIPs() {
      return this.bannedIPs;
   }

   public NBTTagCompound readPlayerDataFromFile(EntityPlayerMP entityPlayerMP) {
      NBTTagCompound playerNBTTagCompound = this.mcServer.worldServers[0].getWorldInfo().getPlayerNBTTagCompound();
      NBTTagCompound tag;
      if (entityPlayerMP.getName().equals(this.mcServer.getServerOwner()) && playerNBTTagCompound != null) {
         entityPlayerMP.readFromNBT(playerNBTTagCompound);
         tag = playerNBTTagCompound;
         LOGGER.debug("loading single player");
      } else {
         tag = this.playerNBTManagerObj.readPlayerData(entityPlayerMP);
      }

      return tag;
   }

   public void setViewDistance(int n) {
      this.viewDistance = n;
      if (this.mcServer.worldServers != null) {
         for (WorldServer world : this.mcServer.worldServers) {
            if (world != null) {
               world.getPlayerManager().setPlayerViewRadius(n);
            }
         }
      }
   }

   public void sendScoreboard(ServerScoreboard serverScoreboard, EntityPlayerMP entityPlayerMP) {
      HashSet set = Sets.newHashSet();

      for (ScorePlayerTeam scorePlayerTeam : (Iterable<ScorePlayerTeam>)(serverScoreboard.getTeams())) {
         entityPlayerMP.playerNetServerHandler.sendPacket(new S3EPacketTeams(scorePlayerTeam, 0));
      }

      for (int i = 0; i < 19; i++) {
         ScoreObjective objectiveInDisplaySlot = serverScoreboard.getObjectiveInDisplaySlot(i);
         if (objectiveInDisplaySlot != null && !set.contains(objectiveInDisplaySlot)) {
            for (Packet packet : (Iterable<Packet>)(serverScoreboard.getCreatePackets(objectiveInDisplaySlot))) {
               entityPlayerMP.playerNetServerHandler.sendPacket(packet);
            }

            set.add(objectiveInDisplaySlot);
         }
      }
   }

   public void playerLoggedIn(EntityPlayerMP entityPlayerMP) {
      this.playerEntityList.add(entityPlayerMP);
      this.uuidToPlayerMap.put(entityPlayerMP.getUniqueID(), entityPlayerMP);
      this.sendPacketToAllPlayers(new S38PacketPlayerListItem(S38PacketPlayerListItem.Action.ADD_PLAYER, new EntityPlayerMP[]{entityPlayerMP}));
      this.mcServer.worldServerForDimension(entityPlayerMP.dimension).spawnEntityInWorld(entityPlayerMP);
      this.preparePlayer(entityPlayerMP, (WorldServer)null);

      for (int i = 0; i < this.playerEntityList.size(); i++) {
         EntityPlayerMP entityPlayerMP2 = (EntityPlayerMP)this.playerEntityList.get(i);
         entityPlayerMP.playerNetServerHandler.sendPacket(new S38PacketPlayerListItem(S38PacketPlayerListItem.Action.ADD_PLAYER, new EntityPlayerMP[]{entityPlayerMP2}));
      }
   }

   public List getPlayerList() {
      return this.playerEntityList;
   }

   public UserListOps getOppedPlayers() {
      return this.ops;
   }

   public int getEntityViewDistance() {
      return PlayerManager.getFurthestViewableBlock(this.getViewDistance());
   }

   public void saveAllPlayerData() {
      for (int i = 0; i < this.playerEntityList.size(); i++) {
         this.writePlayerData((EntityPlayerMP)this.playerEntityList.get(i));
      }
   }

   public void removeOp(GameProfile profile) {
      this.ops.removeEntry(profile);
   }

   public ServerConfigurationManager(MinecraftServer minecraftServer) {
      this.mcServer = minecraftServer;
      this.bannedPlayers.setLanServer(false);
      this.bannedIPs.setLanServer(false);
      this.maxPlayers = 8;
   }

   public String[] getOppedPlayerNames() {
      return this.ops.getKeys();
   }


   public static class DefaultBorderListener implements IBorderListener {
      public final ServerConfigurationManager configManager;

      @Override
      public void onWarningDistanceChanged(WorldBorder worldBorder, int n) {
         this.configManager.sendPacketToAllPlayers(new S44PacketWorldBorder(worldBorder, S44PacketWorldBorder.Action.SET_WARNING_BLOCKS));
      }

      @Override
      public void onCenterChanged(WorldBorder worldBorder, double d, double d2) {
         this.configManager.sendPacketToAllPlayers(new S44PacketWorldBorder(worldBorder, S44PacketWorldBorder.Action.SET_CENTER));
      }

      @Override
      public void onDamageAmountChanged(WorldBorder worldBorder, double d) {
      }

      @Override
      public void onDamageBufferChanged(WorldBorder worldBorder, double d) {
      }

      @Override
      public void onSizeChanged(WorldBorder worldBorder, double d) {
         this.configManager.sendPacketToAllPlayers(new S44PacketWorldBorder(worldBorder, S44PacketWorldBorder.Action.SET_SIZE));
      }

      @Override
      public void onTransitionStarted(WorldBorder worldBorder, double d, double d2, long l) {
         this.configManager.sendPacketToAllPlayers(new S44PacketWorldBorder(worldBorder, S44PacketWorldBorder.Action.LERP_SIZE));
      }

      @Override
      public void onWarningTimeChanged(WorldBorder worldBorder, int n) {
         this.configManager.sendPacketToAllPlayers(new S44PacketWorldBorder(worldBorder, S44PacketWorldBorder.Action.SET_WARNING_TIME));
      }

      public DefaultBorderListener(ServerConfigurationManager manager) {
         this.configManager = manager;
      }
   }
}
