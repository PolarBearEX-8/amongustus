package client.util;

import client.event.PostMotionFilter;
import client.setting.ClientSettingsBlackType;
import client.setting.DynamicOptionType;
import client.setting.StaffDetectorAddPlayerType;
import client.setting.WorldSettingsGameType;
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
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerAdapter;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
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
import net.minecraft.scoreboard.ScorePlayerTeam;
import net.minecraft.util.BlockPos;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraft.world.storage.WorldInfo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class MovingManager {
   public static final File file2 = new File("banned-players.json");
   public static final File file = new File("banned-ips.json");
   public static final File file3 = new File("ops.json");
   public static final File file4 = new File("whitelist.json");
   public static final Logger logger = LogManager.getLogger();
   public static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
   public final VanillaRunnable vanillaRunnable;
   public final List list = Lists.newArrayList();
   public final Map map = Maps.newHashMap();
   public final InternalLoggerEntryAdapter internalLoggerEntryAdapter = new InternalLoggerEntryAdapter(file2);
   public final LoggerEntryBridge loggerEntryBridge = new LoggerEntryBridge(file);
   public final ExtendedLoggerEntryAdapter extendedLoggerEntryAdapter = new ExtendedLoggerEntryAdapter(file3);
   public final AuxiliaryLoggerEntryAdapter auxiliaryLoggerEntryAdapter = new AuxiliaryLoggerEntryAdapter(file4);
   public final Map map2 = Maps.newHashMap();
   public PostMotionFilter postMotionFilter;
   public boolean enabled;
   public int count2;
   public int count3;
   public WorldSettingsGameType worldSettingsGameType;
   public boolean enabled2;
   public int count;

   public void applyEntityPlayerAdapter(EntityPlayerAdapter entityPlayerAdapter) {
      this.postMotionFilter.applyEntityPlayer(entityPlayerAdapter);
      StatFileWriterHandler statFileWriterHandler = (StatFileWriterHandler)this.map2.get(entityPlayerAdapter.getEntityUniqueID());
      if (statFileWriterHandler != null) {
         statFileWriterHandler.updateState2();
      }
   }

   public void applyIChatComponent(IChatComponent iChatComponent, boolean flag) {
      this.vanillaRunnable.applyIChatComponent(iChatComponent);
      byte byteValue = (byte)(flag ? 1 : 0);
      this.applyPacket(new S02PacketChat(iChatComponent, byteValue));
   }

   public void applyEntityPlayer2(EntityPlayer player, IChatComponent iChatComponent) {
      SecondaryClientManager secondaryClientManager = player.getSecondaryClientManager();
      if (secondaryClientManager == null) {
         this.applyIChatComponent(iChatComponent);
      } else {
         for (int index = 0; index < this.list.size(); index++) {
            EntityPlayerAdapter entityPlayerAdapter = (EntityPlayerAdapter)this.list.get(index);
            if (entityPlayerAdapter.getSecondaryClientManager() != secondaryClientManager) {
               entityPlayerAdapter.applyIChatComponent(iChatComponent);
            }
         }
      }
   }

   public void applyGameProfileByGameProfile(GameProfile gameProfile) {
      this.auxiliaryLoggerEntryAdapter.applyTargetStack(new TargetSystem(gameProfile));
   }

   public int getCount() {
      return ManagedLoggerEntry.computeCount(this.getCount3());
   }

   public void applyEntityPlayerAdapter2(EntityPlayerAdapter entityPlayerAdapter) {
      entityPlayerAdapter.getWorldServer().getManagedLoggerEntry().applyEntityPlayerAdapter(entityPlayerAdapter);
   }

   public void applyEntity(Entity entity, int count4, WorldServer worldServer, WorldServer worldServer2) {
      double posX = entity.posX;
      double posZ = entity.posZ;
      double offset = 8.0;
      float yaw = entity.rotationYaw;
      worldServer.uNKNOWNManager.applyTextByText("moving");
      double offset2;
      double offset3;
      if (entity.count4 == -1) {
         offset2 = MathHelper.getDouble7(posX / offset, worldServer2.getInternalListEntry().getValue9() + 16.0, worldServer2.getInternalListEntry().getValue2() - 16.0);
         offset3 = MathHelper.getDouble7(posZ / offset, worldServer2.getInternalListEntry().getValue() + 16.0, worldServer2.getInternalListEntry().getValueAsDouble() - 16.0);
         entity.applyDoubleByDouble(offset2, entity.posY, offset3, entity.rotationYaw, entity.rotationPitch);
         if (entity.canExecute()) {
            worldServer.applyEntity(entity, false);
         }
      } else if (entity.count4 == 0) {
         offset2 = MathHelper.getDouble7(posX * offset, worldServer2.getInternalListEntry().getValue9() + 16.0, worldServer2.getInternalListEntry().getValue2() - 16.0);
         offset3 = MathHelper.getDouble7(posZ * offset, worldServer2.getInternalListEntry().getValue() + 16.0, worldServer2.getInternalListEntry().getValueAsDouble() - 16.0);
         entity.applyDoubleByDouble(offset2, entity.posY, offset3, entity.rotationYaw, entity.rotationPitch);
         if (entity.canExecute()) {
            worldServer.applyEntity(entity, false);
         }
      } else {
         BlockPos blockPos;
         if (count4 == 1) {
            blockPos = worldServer2.getBlockPos();
         } else {
            blockPos = worldServer2.getBlockPosAsBlockPos();
         }

         offset2 = (double)blockPos.getX();
         entity.posY = (double)blockPos.getY();
         offset3 = (double)blockPos.getZ();
         entity.applyDoubleByDouble(offset2, entity.posY, offset3, 90.0F, 0.0F);
         if (entity.canExecute()) {
            worldServer.applyEntity(entity, false);
         }
      }

      worldServer.uNKNOWNManager.updateState();
      if (count4 != 1) {
         worldServer.uNKNOWNManager.applyTextByText("placing");
         double intItem = (double)MathHelper.getInt12((int)offset2, -29999872, 29999872);
         double intItem2 = (double)MathHelper.getInt12((int)offset3, -29999872, 29999872);
         if (entity.canExecute()) {
            entity.applyDoubleByDouble(intItem, entity.posY, intItem2, entity.rotationYaw, entity.rotationPitch);
            worldServer2.getWorldServerEntry().applyEntityByEntity(entity, yaw);
            worldServer2.matchesEntity(entity);
            worldServer2.applyEntity(entity, false);
         }

         worldServer.uNKNOWNManager.updateState();
      }

      entity.setWorldObj(worldServer2);
   }

   public List getList() {
      return this.list;
   }

   public int getCount2() {
      return this.count2;
   }

   public void updateState2() {
      if (++this.count > 600) {
         this.applyPacket(new S38PacketPlayerListItem(StaffDetectorAddPlayerType.UPDATE_LATENCY, this.list));
         this.count = 0;
      }
   }

   public void setEnabled2(boolean enabled3) {
      this.enabled2 = enabled3;
   }

   public String[] getTextArray() {
      return this.extendedLoggerEntryAdapter.getTextArray();
   }

   public void applyEntityPlayerAdapterByEntityPlayerAdap(EntityPlayerAdapter entityPlayerAdapter) {
      entityPlayerAdapter.applyDuplicateStatIdManagerByDuplicateStatIdM(StatCraftItemManager.duplicateStatIdManager44);
      this.applyEntityPlayerAdapter(entityPlayerAdapter);
      WorldServer worldServer = entityPlayerAdapter.getWorldServer();
      if (entityPlayerAdapter.ridingEntity != null) {
         worldServer.applyEntity4(entityPlayerAdapter.ridingEntity);
         logger.debug("removing player mount");
      }

      worldServer.spawnEntityInWorld(entityPlayerAdapter);
      worldServer.getManagedLoggerEntry().applyEntityPlayerAdapterByEntityPlayerAdap(entityPlayerAdapter);
      this.list.remove(entityPlayerAdapter);
      UUID entityUniqueID = entityPlayerAdapter.getEntityUniqueID();
      if ((EntityPlayerAdapter)this.map.get(entityUniqueID) == entityPlayerAdapter) {
         this.map.remove(entityUniqueID);
         this.map2.remove(entityUniqueID);
      }

      this.applyPacket(new S38PacketPlayerListItem(StaffDetectorAddPlayerType.REMOVE_PLAYER, new EntityPlayerAdapter[]{entityPlayerAdapter}));
   }

   public void applyEntityPlayerAdapter(EntityPlayerAdapter entityPlayerAdapter, WorldServer worldServer) {
      WorldInfo internalListEntry = this.vanillaRunnable.worldServerArray[0].getInternalListEntry();
      entityPlayerAdapter.auxiliaryListenerAdapter4.applyPacket(new S44PacketWorldBorder(internalListEntry, DynamicOptionType.INITIALIZE));
      entityPlayerAdapter.auxiliaryListenerAdapter4
         .applyPacket(new S03PacketTimeUpdate(worldServer.getTimestamp2(), worldServer.getTimestampAsTime(), worldServer.getDoFireTickManager().matchesText("doDaylightCycle")));
      if (worldServer.isActive()) {
         entityPlayerAdapter.auxiliaryListenerAdapter4.applyPacket(new S2BPacketChangeGameState(1, 0.0F));
         entityPlayerAdapter.auxiliaryListenerAdapter4.applyPacket(new S2BPacketChangeGameState(7, worldServer.getFloat6(1.0F)));
         entityPlayerAdapter.auxiliaryListenerAdapter4.applyPacket(new S2BPacketChangeGameState(8, worldServer.getFloat7(1.0F)));
      }
   }

   public void applyEntityPlayer(EntityPlayer player, IChatComponent iChatComponent) {
      SecondaryClientManager secondaryClientManager = player.getSecondaryClientManager();
      if (secondaryClientManager != null) {
         for (String text : (Iterable<String>) (secondaryClientManager.getCollection())) {
            EntityPlayerAdapter entityPlayerAdapter = this.resolveEntityPlayerAdapter(text);
            if (entityPlayerAdapter != null && entityPlayerAdapter != player) {
               entityPlayerAdapter.applyIChatComponent(iChatComponent);
            }
         }
      }
   }

   public List resolveList(String text) {
      ArrayList list2 = Lists.newArrayList();

      for (EntityPlayerAdapter entityPlayerAdapter : (Iterable<EntityPlayerAdapter>) (this.list)) {
         if (entityPlayerAdapter.getTextAsText().equals(text)) {
            list2.add(entityPlayerAdapter);
         }
      }

      return list2;
   }

   public void applyPacket(double posX, double posX2, double posY, double posZ, int count4, Packet packet) {
      this.applyEntityPlayer((EntityPlayer)null, posX, posX2, posY, posZ, count4, packet);
   }

   public boolean matchesGameProfile(GameProfile gameProfile) {
      return false;
   }

   public void applyGameProfile(GameProfile gameProfile) {
      this.auxiliaryLoggerEntryAdapter.applyTarget(gameProfile);
   }

   public InternalLoggerEntryAdapter getInternalLoggerEntryAdapter() {
      return this.internalLoggerEntryAdapter;
   }

   public int getCount3() {
      return this.count3;
   }

   public void updateState() {
   }

   public void setEnabled(boolean enabled3) {
      this.enabled = enabled3;
   }

   public EntityPlayerAdapter resolveEntityPlayerAdapter(GameProfile gameProfile) {
      UUID uuid = EntityPlayer.resolveUUID(gameProfile);
      ArrayList list2 = Lists.newArrayList();

      for (int index = 0; index < this.list.size(); index++) {
         EntityPlayerAdapter entityPlayerAdapter = (EntityPlayerAdapter)this.list.get(index);
         if (entityPlayerAdapter.getEntityUniqueID().equals(uuid)) {
            list2.add(entityPlayerAdapter);
         }
      }

      EntityPlayerAdapter entityPlayerAdapter2 = (EntityPlayerAdapter)this.map.get(gameProfile.getId());
      if (entityPlayerAdapter2 != null && !list2.contains(entityPlayerAdapter2)) {
         list2.add(entityPlayerAdapter2);
      }

      Iterator iterator = list2.iterator();

      while (iterator.hasNext()) {
         ((EntityPlayerAdapter)iterator.next()).auxiliaryListenerAdapter4.applyTextByText("You logged in from another location");
      }

      Object entry;
      if (this.vanillaRunnable.isEnabled11()) {
         entry = new WorldEntryAdapter(this.vanillaRunnable.resolveWorldServer(0));
      } else {
         entry = new InternalWorldEntry(this.vanillaRunnable.resolveWorldServer(0));
      }

      return new EntityPlayerAdapter(this.vanillaRunnable, this.vanillaRunnable.resolveWorldServer(0), gameProfile, (InternalWorldEntry)entry);
   }

   public GameProfile[] getGameProfileArray() {
      GameProfile[] gameProfileArray = new GameProfile[this.list.size()];

      for (int index = 0; index < this.list.size(); index++) {
         gameProfileArray[index] = ((EntityPlayerAdapter)this.list.get(index)).getGameProfile();
      }

      return gameProfileArray;
   }

   public void applyEntityPlayer(EntityPlayer player, double posX, double posX2, double posY, double posZ, int count4, Packet packet) {
      for (int index = 0; index < this.list.size(); index++) {
         EntityPlayerAdapter entityPlayerAdapter = (EntityPlayerAdapter)this.list.get(index);
         if (entityPlayerAdapter != player && entityPlayerAdapter.count4 == count4) {
            double offset = posX - entityPlayerAdapter.posX;
            double offset2 = posX2 - entityPlayerAdapter.posY;
            double offset3 = posY - entityPlayerAdapter.posZ;
            if (offset * offset + offset2 * offset2 + offset3 * offset3 < posZ * posZ) {
               entityPlayerAdapter.auxiliaryListenerAdapter4.applyPacket(packet);
            }
         }
      }
   }

   public String[] getTextArrayAsTextArray() {
      return this.vanillaRunnable.worldServerArray[0].getManagedListener().getPostMotionFilter().getTextArray();
   }

   public void applyEntityPlayerAdapterByEntityPlayerAdap(EntityPlayerAdapter entityPlayerAdapter, WorldServer worldServer) {
      WorldServer worldServer2 = entityPlayerAdapter.getWorldServer();
      if (worldServer != null) {
         worldServer.getManagedLoggerEntry().applyEntityPlayerAdapterByEntityPlayerAdap(entityPlayerAdapter);
      }

      worldServer2.getManagedLoggerEntry().applyEntityPlayerAdapter3(entityPlayerAdapter);
      worldServer2.listenerAdapter21.resolveAlertNotificationSoundByIndex((int)entityPlayerAdapter.posX >> 4, (int)entityPlayerAdapter.posZ >> 4);
   }

   public void applyNetworkManager(NetworkManager networkManager, EntityPlayerAdapter entityPlayerAdapter) {
      GameProfile gameProfile = entityPlayerAdapter.getGameProfile();
      YyyyMMDdHHMmSsZManager yyyyMMDdHHMmSsZManager = this.vanillaRunnable.getYyyyMMDdHHMmSsZManager();
      GameProfile gameProfile2 = yyyyMMDdHHMmSsZManager.resolveGameProfile(gameProfile.getId());
      String text = gameProfile2 == null ? gameProfile.getName() : gameProfile2.getName();
      yyyyMMDdHHMmSsZManager.applyGameProfile(gameProfile);
      NBTTagCompound tagCompound = this.resolveNBTTagCompound(entityPlayerAdapter);
      entityPlayerAdapter.setWorldObj(this.vanillaRunnable.resolveWorldServer(entityPlayerAdapter.count4));
      entityPlayerAdapter.internalWorldEntry.setWorld((WorldServer)entityPlayerAdapter.worldObj);
      String text2 = "local";
      if (networkManager.getSocketAddress() != null) {
         text2 = networkManager.getSocketAddress().toString();
      }

      logger.info(
         entityPlayerAdapter.getText()
            + "["
            + text2
            + "] logged in with entity id "
            + entityPlayerAdapter.getEntityId()
            + " at ("
            + entityPlayerAdapter.posX
            + ", "
            + entityPlayerAdapter.posY
            + ", "
            + entityPlayerAdapter.posZ
            + ")"
      );
      WorldServer worldServer = this.vanillaRunnable.resolveWorldServer(entityPlayerAdapter.count4);
      RandomSeedManager randomSeedManager = worldServer.getRandomSeedManager();
      BlockPos blockPos = worldServer.getBlockPos();
      this.applyEntityPlayerAdapterByEntityPlayerAdap(entityPlayerAdapter, (EntityPlayerAdapter)null, worldServer);
      AuxiliaryListenerAdapter4 auxiliaryListenerAdapter = new AuxiliaryListenerAdapter4(this.vanillaRunnable, networkManager, entityPlayerAdapter);
      auxiliaryListenerAdapter.applyPacket(
         new S01PacketJoinGame(
            entityPlayerAdapter.getEntityId(),
            entityPlayerAdapter.internalWorldEntry.getWorldSettingsGameType(),
            randomSeedManager.isEnabled2(),
            worldServer.secondaryFloatArrayEntry.getCountAsIndex(),
            worldServer.getGameSettingsPeacefulType(),
            this.getCount2(),
            randomSeedManager.getInfoManager(),
            worldServer.getDoFireTickManager().matchesText("reducedDebugInfo")
         )
      );
      auxiliaryListenerAdapter.applyPacket(new S3FPacketCustomPayload("MC|Brand", new PacketBuffer(Unpooled.buffer()).getPacketBuffer(this.getVanillaRunnable().getTextAsText())));
      auxiliaryListenerAdapter.applyPacket(new S41PacketServerDifficulty(randomSeedManager.getGameSettingsPeacefulType2(), randomSeedManager.isEnabled()));
      auxiliaryListenerAdapter.applyPacket(new S05PacketSpawnPosition(blockPos));
      auxiliaryListenerAdapter.applyPacket(new S39PacketPlayerAbilities(entityPlayerAdapter.invulnerableManager));
      auxiliaryListenerAdapter.applyPacket(new S09PacketHeldItemChange(entityPlayerAdapter.inventoryPlayer.currentItem));
      entityPlayerAdapter.getStatFileWriterHandler().updateState();
      entityPlayerAdapter.getStatFileWriterHandler().applyEntityPlayerAdapter(entityPlayerAdapter);
      this.applyThePlayerNameManagerAdapte((ThePlayerNameManagerAdapter)worldServer.getThePlayerNameManager(), entityPlayerAdapter);
      this.vanillaRunnable.updateState11();
      BaseManagerStyleIChatComponentAdapter baseManagerStyleIChatComponentAdapter;
      if (!entityPlayerAdapter.getText().equalsIgnoreCase(text)) {
         baseManagerStyleIChatComponentAdapter = new BaseManagerStyleIChatComponentAdapter("multiplayer.player.joined.renamed", new Object[]{entityPlayerAdapter.getChatComponent(), text});
      } else {
         baseManagerStyleIChatComponentAdapter = new BaseManagerStyleIChatComponentAdapter("multiplayer.player.joined", new Object[]{entityPlayerAdapter.getChatComponent()});
      }

      baseManagerStyleIChatComponentAdapter.getStyleHasParentManager().withClientSettingsBlackType(ClientSettingsBlackType.YELLOW);
      this.applyIChatComponent(baseManagerStyleIChatComponentAdapter);
      this.applyEntityPlayerAdapter3(entityPlayerAdapter);
      auxiliaryListenerAdapter.applyDouble(entityPlayerAdapter.posX, entityPlayerAdapter.posY, entityPlayerAdapter.posZ, entityPlayerAdapter.rotationYaw, entityPlayerAdapter.rotationPitch);
      this.applyEntityPlayerAdapter(entityPlayerAdapter, worldServer);
      if (this.vanillaRunnable.getLabelText6().length() > 0) {
         entityPlayerAdapter.applyText(this.vanillaRunnable.getLabelText6(), this.vanillaRunnable.getLabelText7());
      }

      for (PotionEffect potionEffect : (Iterable<PotionEffect>) (entityPlayerAdapter.getCollection())) {
         auxiliaryListenerAdapter.applyPacket(new S1DPacketEntityEffect(entityPlayerAdapter.getEntityId(), potionEffect));
      }

      entityPlayerAdapter.updateState53();
      if (tagCompound != null && tagCompound.matchesText("Riding", 10)) {
         Entity entity = SkippingEntityWithIdManager.resolveEntityByNBTTagCompound(tagCompound.getNBTTagCompound("Riding"), worldServer);
         if (entity != null) {
            entity.enabled10 = true;
            worldServer.matchesEntity(entity);
            entityPlayerAdapter.setEntity8(entity);
            entity.enabled10 = false;
         }
      }
   }

   public NBTTagCompound resolveNBTTagCompound(EntityPlayerAdapter entityPlayerAdapter) {
      NBTTagCompound nBTTagCompound = this.vanillaRunnable.worldServerArray[0].getRandomSeedManager().getNBTTagCompound();
      if (entityPlayerAdapter.getText().equals(this.vanillaRunnable.getLabelText3()) && nBTTagCompound != null) {
         entityPlayerAdapter.applyNBTTagCompound2(nBTTagCompound);
         logger.debug("loading single player");
      } else {
         nBTTagCompound = this.postMotionFilter.resolveNBTTagCompoundByEntityPlayer(entityPlayerAdapter);
      }

      return nBTTagCompound;
   }

   public EntityPlayerAdapter get(UUID uuid) {
      return (EntityPlayerAdapter)this.map.get(uuid);
   }

   private void applyEntityPlayerAdapterByEntityPlayerAdap(EntityPlayerAdapter entityPlayerAdapter, EntityPlayerAdapter entityPlayerAdapter2, World world) {
      if (entityPlayerAdapter2 != null) {
         entityPlayerAdapter.internalWorldEntry.applyWorldSettingsGameTypeByWorldSettingsGam(entityPlayerAdapter2.internalWorldEntry.getWorldSettingsGameType());
      } else if (this.worldSettingsGameType != null) {
         entityPlayerAdapter.internalWorldEntry.applyWorldSettingsGameTypeByWorldSettingsGam(this.worldSettingsGameType);
      }

      entityPlayerAdapter.internalWorldEntry.applyWorldSettingsGameType(world.getRandomSeedManager().getWorldSettingsGameType());
   }

   public void applyIChatComponent(IChatComponent iChatComponent) {
      this.applyIChatComponent(iChatComponent, true);
   }

   public void updateState4() {
      for (int index = 0; index < this.list.size(); index++) {
         this.applyEntityPlayerAdapter((EntityPlayerAdapter)this.list.get(index));
      }
   }

   public boolean matchesGameProfileByGameProfile(GameProfile gameProfile) {
      return this.extendedLoggerEntryAdapter.matchesTarget(gameProfile)
         || this.vanillaRunnable.isActive10()
            && this.vanillaRunnable.worldServerArray[0].getRandomSeedManager().isEnabled3()
            && this.vanillaRunnable.getLabelText3().equalsIgnoreCase(gameProfile.getName())
         || this.enabled2;
   }

   public void applyThePlayerNameManagerAdapte(ThePlayerNameManagerAdapter thePlayerNameManagerAdapter, EntityPlayerAdapter entityPlayerAdapter) {
      HashSet set = Sets.newHashSet();

      for (ScorePlayerTeam scorePlayerTeam : (Iterable<ScorePlayerTeam>) (thePlayerNameManagerAdapter.values())) {
         entityPlayerAdapter.auxiliaryListenerAdapter4.applyPacket(new S3EPacketTeams(scorePlayerTeam, 0));
      }

      for (int index = 0; index < 19; index++) {
         ScoreboardData customItemEntry = thePlayerNameManagerAdapter.getCustomItemEntry(index);
         if (customItemEntry != null && !set.contains(customItemEntry)) {
            for (Packet packet : (Iterable<Packet>) (thePlayerNameManagerAdapter.resolveList(customItemEntry))) {
               entityPlayerAdapter.auxiliaryListenerAdapter4.applyPacket(packet);
            }

            set.add(customItemEntry);
         }
      }
   }

   public void applyGameProfile3(GameProfile gameProfile) {
      this.extendedLoggerEntryAdapter.applyTarget(gameProfile);
   }

   public boolean matchesGameProfile2(GameProfile gameProfile) {
      return !this.enabled || this.extendedLoggerEntryAdapter.matchesTarget(gameProfile) || this.auxiliaryLoggerEntryAdapter.matchesTarget(gameProfile);
   }

   public void applyEntityPlayerAdapter4(EntityPlayerAdapter entityPlayerAdapter) {
      entityPlayerAdapter.applyContainer(entityPlayerAdapter.container);
      entityPlayerAdapter.updateState51();
      entityPlayerAdapter.auxiliaryListenerAdapter4.applyPacket(new S09PacketHeldItemChange(entityPlayerAdapter.inventoryPlayer.currentItem));
   }

   public void applyWorldServerArray(WorldServer[] worldServerArray) {
      this.postMotionFilter = worldServerArray[0].getManagedListener().getPostMotionFilter();
      worldServerArray[0].getInternalListEntry().add(new ListenerAdapter13(this));
   }

   public String resolveText(boolean flag) {
      String text = "";
      ArrayList list2 = Lists.newArrayList(this.list);

      for (int index = 0; index < list2.size(); index++) {
         if (index > 0) {
            text = text + ", ";
         }

         text = text + ((EntityPlayerAdapter)list2.get(index)).getText();
         if (flag) {
            text = text + " (" + ((EntityPlayerAdapter)list2.get(index)).getEntityUniqueID().toString() + ")";
         }
      }

      return text;
   }

   public void applyEntityPlayerAdapter3(EntityPlayerAdapter entityPlayerAdapter) {
      this.list.add(entityPlayerAdapter);
      this.map.put(entityPlayerAdapter.getEntityUniqueID(), entityPlayerAdapter);
      this.applyPacket(new S38PacketPlayerListItem(StaffDetectorAddPlayerType.ADD_PLAYER, new EntityPlayerAdapter[]{entityPlayerAdapter}));
      this.vanillaRunnable.resolveWorldServer(entityPlayerAdapter.count4).matchesEntity(entityPlayerAdapter);
      this.applyEntityPlayerAdapterByEntityPlayerAdap(entityPlayerAdapter, (WorldServer)null);

      for (int index = 0; index < this.list.size(); index++) {
         EntityPlayerAdapter entityPlayerAdapter2 = (EntityPlayerAdapter)this.list.get(index);
         entityPlayerAdapter.auxiliaryListenerAdapter4.applyPacket(new S38PacketPlayerListItem(StaffDetectorAddPlayerType.ADD_PLAYER, new EntityPlayerAdapter[]{entityPlayerAdapter2}));
      }
   }

   public EntityPlayerAdapter resolveEntityPlayerAdapter(EntityPlayerAdapter entityPlayerAdapter, int count4, boolean flag) {
      entityPlayerAdapter.getWorldServer().getSecondaryLoggerEntry().applyEntityPlayerAdapterByEntityPlayerAdap(entityPlayerAdapter);
      entityPlayerAdapter.getWorldServer().getSecondaryLoggerEntry().applyEntity2(entityPlayerAdapter);
      entityPlayerAdapter.getWorldServer().getManagedLoggerEntry().applyEntityPlayerAdapterByEntityPlayerAdap(entityPlayerAdapter);
      this.list.remove(entityPlayerAdapter);
      this.vanillaRunnable.resolveWorldServer(entityPlayerAdapter.count4).applyEntity4(entityPlayerAdapter);
      BlockPos blockPos = entityPlayerAdapter.getBlockPos3();
      boolean state = entityPlayerAdapter.isState12();
      entityPlayerAdapter.count4 = count4;
      Object entry;
      if (this.vanillaRunnable.isEnabled11()) {
         entry = new WorldEntryAdapter(this.vanillaRunnable.resolveWorldServer(entityPlayerAdapter.count4));
      } else {
         entry = new InternalWorldEntry(this.vanillaRunnable.resolveWorldServer(entityPlayerAdapter.count4));
      }

      EntityPlayerAdapter entityPlayerAdapter2 = new EntityPlayerAdapter(
         this.vanillaRunnable, this.vanillaRunnable.resolveWorldServer(entityPlayerAdapter.count4), entityPlayerAdapter.getGameProfile(), (InternalWorldEntry)entry
      );
      entityPlayerAdapter2.auxiliaryListenerAdapter4 = entityPlayerAdapter.auxiliaryListenerAdapter4;
      entityPlayerAdapter2.applyEntityPlayer(entityPlayerAdapter, flag);
      entityPlayerAdapter2.setEntityId(entityPlayerAdapter.getEntityId());
      entityPlayerAdapter2.applyEntity2(entityPlayerAdapter);
      WorldServer worldServer = this.vanillaRunnable.resolveWorldServer(entityPlayerAdapter.count4);
      this.applyEntityPlayerAdapterByEntityPlayerAdap(entityPlayerAdapter2, entityPlayerAdapter, worldServer);
      if (blockPos != null) {
         BlockPos blockPos2 = EntityPlayer.resolveBlockPos(this.vanillaRunnable.resolveWorldServer(entityPlayerAdapter.count4), blockPos, state);
         if (blockPos2 != null) {
            entityPlayerAdapter2.applyDoubleByDouble((double)((float)blockPos2.getX() + 0.5F), (double)((float)blockPos2.getY() + 0.1F), (double)((float)blockPos2.getZ() + 0.5F), 0.0F, 0.0F);
            entityPlayerAdapter2.applyBlockPos(blockPos, state);
         } else {
            entityPlayerAdapter2.auxiliaryListenerAdapter4.applyPacket(new S2BPacketChangeGameState(0, 0.0F));
         }
      }

      worldServer.listenerAdapter21.resolveAlertNotificationSoundByIndex((int)entityPlayerAdapter2.posX >> 4, (int)entityPlayerAdapter2.posZ >> 4);

      while (!worldServer.getList2(entityPlayerAdapter2, entityPlayerAdapter2.getBoundingBox2()).isEmpty() && entityPlayerAdapter2.posY < 256.0) {
         entityPlayerAdapter2.processDouble2(entityPlayerAdapter2.posX, entityPlayerAdapter2.posY + 1.0, entityPlayerAdapter2.posZ);
      }

      entityPlayerAdapter2.auxiliaryListenerAdapter4
         .applyPacket(
            new S07PacketRespawn(
               entityPlayerAdapter2.count4,
               entityPlayerAdapter2.worldObj.getGameSettingsPeacefulType(),
               entityPlayerAdapter2.worldObj.getRandomSeedManager().getInfoManager(),
               entityPlayerAdapter2.internalWorldEntry.getWorldSettingsGameType()
            )
         );
      BlockPos blockPos3 = worldServer.getBlockPos();
      entityPlayerAdapter2.auxiliaryListenerAdapter4.applyDouble(entityPlayerAdapter2.posX, entityPlayerAdapter2.posY, entityPlayerAdapter2.posZ, entityPlayerAdapter2.rotationYaw, entityPlayerAdapter2.rotationPitch);
      entityPlayerAdapter2.auxiliaryListenerAdapter4.applyPacket(new S05PacketSpawnPosition(blockPos3));
      entityPlayerAdapter2.auxiliaryListenerAdapter4.applyPacket(new S1FPacketSetExperience(entityPlayerAdapter2.factor39, entityPlayerAdapter2.count32, entityPlayerAdapter2.count25));
      this.applyEntityPlayerAdapter(entityPlayerAdapter2, worldServer);
      worldServer.getManagedLoggerEntry().applyEntityPlayerAdapter3(entityPlayerAdapter2);
      worldServer.matchesEntity(entityPlayerAdapter2);
      this.list.add(entityPlayerAdapter2);
      this.map.put(entityPlayerAdapter2.getEntityUniqueID(), entityPlayerAdapter2);
      entityPlayerAdapter2.updateState53();
      entityPlayerAdapter2.applyFloat(entityPlayerAdapter2.getFloat2());
      return entityPlayerAdapter2;
   }

   public EntityPlayerAdapter resolveEntityPlayerAdapter(String text) {
      for (EntityPlayerAdapter entityPlayerAdapter : (Iterable<EntityPlayerAdapter>) (this.list)) {
         if (entityPlayerAdapter.getText().equalsIgnoreCase(text)) {
            return entityPlayerAdapter;
         }
      }

      return null;
   }

   public void applyPacket(Packet packet) {
      for (int index = 0; index < this.list.size(); index++) {
         ((EntityPlayerAdapter)this.list.get(index)).auxiliaryListenerAdapter4.applyPacket(packet);
      }
   }

   public LoggerEntryBridge getLoggerEntryBridge() {
      return this.loggerEntryBridge;
   }

   public void applyIndex(int count4) {
      this.count3 = count4;
      if (this.vanillaRunnable.worldServerArray != null) {
         for (WorldServer worldServer : this.vanillaRunnable.worldServerArray) {
            if (worldServer != null) {
               worldServer.getManagedLoggerEntry().applyIndex(count4);
            }
         }
      }
   }

   public String[] getTextArray2() {
      String[] textArray = new String[this.list.size()];

      for (int index = 0; index < this.list.size(); index++) {
         textArray[index] = ((EntityPlayerAdapter)this.list.get(index)).getText();
      }

      return textArray;
   }

   public int size() {
      return this.list.size();
   }

   public NBTTagCompound getNBTTagCompound() {
      return null;
   }

   public void setWorldSettingsGameType(WorldSettingsGameType worldSettingsGameType2) {
      this.worldSettingsGameType = worldSettingsGameType2;
   }

   public void applyGameProfile2(GameProfile gameProfile) {
      this.extendedLoggerEntryAdapter
         .applyTargetStack(new TargetDirector(gameProfile, this.vanillaRunnable.getCount(), this.extendedLoggerEntryAdapter.matchesGameProfile(gameProfile)));
   }

   public void applyPacket(Packet packet, int count4) {
      for (int index = 0; index < this.list.size(); index++) {
         EntityPlayerAdapter entityPlayerAdapter = (EntityPlayerAdapter)this.list.get(index);
         if (entityPlayerAdapter.count4 == count4) {
            entityPlayerAdapter.auxiliaryListenerAdapter4.applyPacket(packet);
         }
      }
   }

   public VanillaRunnable getVanillaRunnable() {
      return this.vanillaRunnable;
   }

   public String[] getTextArray3() {
      return this.auxiliaryLoggerEntryAdapter.getTextArray();
   }

   public ExtendedLoggerEntryAdapter getExtendedLoggerEntryAdapter() {
      return this.extendedLoggerEntryAdapter;
   }

   public StatFileWriterHandler resolveStatFileWriterHandler(EntityPlayer player) {
      UUID entityUniqueID = player.getEntityUniqueID();
      StatFileWriterHandler statFileWriterHandler = entityUniqueID == null ? null : (StatFileWriterHandler)this.map2.get(entityUniqueID);
      if (statFileWriterHandler == null) {
         File file5 = new File(this.vanillaRunnable.resolveWorldServer(0).getManagedListener().getFile(), "stats");
         File file6 = new File(file5, entityUniqueID.toString() + ".json");
         if (!file6.exists()) {
            File file7 = new File(file5, player.getText() + ".json");
            if (file7.exists() && file7.isFile()) {
               file7.renameTo(file6);
            }
         }

         statFileWriterHandler = new StatFileWriterHandler(this.vanillaRunnable, file6);
         statFileWriterHandler.updateState3();
         this.map2.put(entityUniqueID, statFileWriterHandler);
      }

      return statFileWriterHandler;
   }

   public void applyEntityPlayerAdapter(EntityPlayerAdapter entityPlayerAdapter, int count4) {
      int count5 = entityPlayerAdapter.count4;
      WorldServer worldServer = this.vanillaRunnable.resolveWorldServer(entityPlayerAdapter.count4);
      entityPlayerAdapter.count4 = count4;
      WorldServer worldServer2 = this.vanillaRunnable.resolveWorldServer(entityPlayerAdapter.count4);
      entityPlayerAdapter.auxiliaryListenerAdapter4
         .applyPacket(
            new S07PacketRespawn(
               entityPlayerAdapter.count4,
               entityPlayerAdapter.worldObj.getGameSettingsPeacefulType(),
               entityPlayerAdapter.worldObj.getRandomSeedManager().getInfoManager(),
               entityPlayerAdapter.internalWorldEntry.getWorldSettingsGameType()
            )
         );
      worldServer.applyEntity4(entityPlayerAdapter);
      entityPlayerAdapter.isDead = false;
      this.applyEntity(entityPlayerAdapter, count5, worldServer, worldServer2);
      this.applyEntityPlayerAdapterByEntityPlayerAdap(entityPlayerAdapter, worldServer);
      entityPlayerAdapter.auxiliaryListenerAdapter4.applyDouble(entityPlayerAdapter.posX, entityPlayerAdapter.posY, entityPlayerAdapter.posZ, entityPlayerAdapter.rotationYaw, entityPlayerAdapter.rotationPitch);
      entityPlayerAdapter.internalWorldEntry.setWorld(worldServer2);
      this.applyEntityPlayerAdapter(entityPlayerAdapter, worldServer2);
      this.applyEntityPlayerAdapter4(entityPlayerAdapter);

      for (PotionEffect potionEffect : (Iterable<PotionEffect>) (entityPlayerAdapter.getCollection())) {
         entityPlayerAdapter.auxiliaryListenerAdapter4.applyPacket(new S1DPacketEntityEffect(entityPlayerAdapter.getEntityId(), potionEffect));
      }
   }

   public AuxiliaryLoggerEntryAdapter getAuxiliaryLoggerEntryAdapter() {
      return this.auxiliaryLoggerEntryAdapter;
   }

   public String resolveText(SocketAddress socketAddress, GameProfile gameProfile) {
      if (this.internalLoggerEntryAdapter.matchesGameProfile(gameProfile)) {
         SimpleDateFormatEntryAdapter simpleDateFormatEntryAdapter = (SimpleDateFormatEntryAdapter)this.internalLoggerEntryAdapter.resolveTargetStack(gameProfile);
         String text = "You are banned from this server!\nReason: " + simpleDateFormatEntryAdapter.getLabelText2();
         if (simpleDateFormatEntryAdapter.getDate2() != null) {
            text = text + "\nYour ban will be removed on " + simpleDateFormat.format(simpleDateFormatEntryAdapter.getDate2());
         }

         return text;
      } else if (!this.matchesGameProfile2(gameProfile)) {
         return "You are not white-listed on this server!";
      } else if (this.loggerEntryBridge.matchesSocketAddress(socketAddress)) {
         AuxiliarySimpleDateFormatEntryAdapter auxiliarySimpleDateFormatEntryAdapter = this.loggerEntryBridge.resolveAuxiliarySimpleDateFormatEnt(socketAddress);
         String text2 = "Your IP address is banned from this server!\nReason: " + auxiliarySimpleDateFormatEntryAdapter.getLabelText2();
         if (auxiliarySimpleDateFormatEntryAdapter.getDate2() != null) {
            text2 = text2 + "\nYour ban will be removed on " + simpleDateFormat.format(auxiliarySimpleDateFormatEntryAdapter.getDate2());
         }

         return text2;
      } else {
         return this.list.size() >= this.count2 && !this.matchesGameProfile(gameProfile) ? "The server is full!" : null;
      }
   }

   public void updateState3() {
      for (int index = 0; index < this.list.size(); index++) {
         ((EntityPlayerAdapter)this.list.get(index)).auxiliaryListenerAdapter4.applyTextByText("Server closed");
      }
   }

   public MovingManager(VanillaRunnable vanillaRunnable2) {
      this.vanillaRunnable = vanillaRunnable2;
      this.internalLoggerEntryAdapter.setEnabled(false);
      this.loggerEntryBridge.setEnabled(false);
      this.count2 = 8;
   }
}
