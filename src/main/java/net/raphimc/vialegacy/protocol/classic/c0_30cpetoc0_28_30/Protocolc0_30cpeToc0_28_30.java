package net.raphimc.vialegacy.protocol.classic.c0_30cpetoc0_28_30;

import com.viaversion.viaversion.api.Via;
import com.viaversion.viaversion.api.connection.UserConnection;
import com.viaversion.viaversion.api.minecraft.BlockChangeRecord;
import com.viaversion.viaversion.api.minecraft.BlockChangeRecord1_8;
import com.viaversion.viaversion.api.minecraft.BlockPosition;
import com.viaversion.viaversion.api.platform.providers.ViaProviders;
import com.viaversion.viaversion.api.protocol.packet.PacketWrapper;
import com.viaversion.viaversion.api.protocol.remapper.PacketHandlers;
import com.viaversion.viaversion.api.type.Types;
import com.viaversion.viaversion.api.type.types.FixedByteArrayType;
import com.viaversion.viaversion.util.IdAndData;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import net.raphimc.vialegacy.ViaLegacy;
import net.raphimc.vialegacy.api.data.BlockList1_6;
import net.raphimc.vialegacy.api.model.ChunkCoord;
import net.raphimc.vialegacy.api.protocol.StatelessProtocol;
import net.raphimc.vialegacy.api.splitter.PreNettySplitter;
import net.raphimc.vialegacy.protocol.alpha.a1_0_15toa1_0_16_2.Protocola1_0_15Toa1_0_16_2;
import net.raphimc.vialegacy.protocol.alpha.a1_0_15toa1_0_16_2.packet.ClientboundPacketsa1_0_15;
import net.raphimc.vialegacy.protocol.classic.c0_28_30toa1_0_15.data.ClassicBlocks;
import net.raphimc.vialegacy.protocol.classic.c0_28_30toa1_0_15.model.ClassicLevel;
import net.raphimc.vialegacy.protocol.classic.c0_28_30toa1_0_15.packet.ClientboundPacketsc0_28;
import net.raphimc.vialegacy.protocol.classic.c0_28_30toa1_0_15.packet.ServerboundPacketsc0_28;
import net.raphimc.vialegacy.protocol.classic.c0_28_30toa1_0_15.storage.ClassicBlockRemapper;
import net.raphimc.vialegacy.protocol.classic.c0_28_30toa1_0_15.storage.ClassicLevelStorage;
import net.raphimc.vialegacy.protocol.classic.c0_28_30toa1_0_15.storage.ClassicOpLevelStorage;
import net.raphimc.vialegacy.protocol.classic.c0_28_30toa1_0_15.storage.ClassicProgressStorage;
import net.raphimc.vialegacy.protocol.classic.c0_28_30toa1_0_15.types.Typesc0_30;
import net.raphimc.vialegacy.protocol.classic.c0_30cpetoc0_28_30.data.ClassicProtocolExtension;
import net.raphimc.vialegacy.protocol.classic.c0_30cpetoc0_28_30.data.ExtendedClassicBlocks;
import net.raphimc.vialegacy.protocol.classic.c0_30cpetoc0_28_30.packet.ClientboundPacketsc0_30cpe;
import net.raphimc.vialegacy.protocol.classic.c0_30cpetoc0_28_30.packet.ServerboundPacketsc0_30cpe;
import net.raphimc.vialegacy.protocol.classic.c0_30cpetoc0_28_30.storage.ExtBlockPermissionsStorage;
import net.raphimc.vialegacy.protocol.classic.c0_30cpetoc0_28_30.storage.ExtensionProtocolMetadataStorage;
import net.raphimc.vialegacy.protocol.classic.c0_30cpetoc0_28_30.task.ClassicPingTask;
import net.raphimc.vialegacy.protocol.release.r1_1tor1_2_1_3.types.Types1_1;
import net.raphimc.vialegacy.protocol.release.r1_6_1tor1_6_2.Protocolr1_6_1Tor1_6_2;
import net.raphimc.vialegacy.protocol.release.r1_6_4tor1_7_2_5.packet.ClientboundPackets1_6_4;
import net.raphimc.vialegacy.protocol.release.r1_6_4tor1_7_2_5.types.Types1_6_4;

public class Protocolc0_30cpeToc0_28_30
   extends StatelessProtocol<ClientboundPacketsc0_30cpe, ClientboundPacketsc0_28, ServerboundPacketsc0_30cpe, ServerboundPacketsc0_28> {
   public Protocolc0_30cpeToc0_28_30() {
      super(ClientboundPacketsc0_30cpe.class, ClientboundPacketsc0_28.class, ServerboundPacketsc0_30cpe.class, ServerboundPacketsc0_28.class);
   }

   @Override
   protected void registerPackets() {
      this.registerClientbound(ClientboundPacketsc0_30cpe.LOGIN, packetWrapper -> {
         if (packetWrapper.user().getProtocolInfo().getPipeline().contains(Protocolr1_6_1Tor1_6_2.class)) {
            ExtensionProtocolMetadataStorage protocolMetadataStorage = packetWrapper.user().get(ExtensionProtocolMetadataStorage.class);
            PacketWrapper brand = PacketWrapper.create(ClientboundPackets1_6_4.CUSTOM_PAYLOAD, packetWrapper.user());
            brand.write(Types1_6_4.STRING, "MC|Brand");
            byte[] brandBytes = protocolMetadataStorage.getServerSoftwareName().getBytes(StandardCharsets.UTF_8);
            brand.write(Types.SHORT, (short)brandBytes.length);
            brand.write(Types.REMAINING_BYTES, brandBytes);
            packetWrapper.send(Protocolc0_30cpeToc0_28_30.class);
            brand.send(Protocolr1_6_1Tor1_6_2.class);
            packetWrapper.cancel();
         }
      });
      this.registerClientbound(ClientboundPacketsc0_30cpe.EXTENSION_PROTOCOL_INFO, null, packetWrapper2 -> {
         packetWrapper2.cancel();
         ExtensionProtocolMetadataStorage protocolMetadataStorage = packetWrapper2.user().get(ExtensionProtocolMetadataStorage.class);
         protocolMetadataStorage.setServerSoftwareName(packetWrapper2.read(Typesc0_30.STRING));
         protocolMetadataStorage.setExtensionCount(packetWrapper2.read(Types.SHORT));
         ClassicProgressStorage classicProgressStorage = packetWrapper2.user().get(ClassicProgressStorage.class);
         classicProgressStorage.progress = 0;
         classicProgressStorage.upperBound = protocolMetadataStorage.getExtensionCount();
         classicProgressStorage.status = "Receiving extension list...";
      });
      this.registerClientbound(ClientboundPacketsc0_30cpe.EXTENSION_PROTOCOL_ENTRY, null, packetWrapper3 -> {
         packetWrapper3.cancel();
         ExtensionProtocolMetadataStorage protocolMetadataStorage = packetWrapper3.user().get(ExtensionProtocolMetadataStorage.class);
         String extensionName = packetWrapper3.read(Typesc0_30.STRING);
         int extensionVersion = packetWrapper3.read(Types.INT);
         ClassicProtocolExtension extension = ClassicProtocolExtension.byName(extensionName);
         if (extension != null) {
            protocolMetadataStorage.addServerExtension(extension, extensionVersion);
         } else if (Via.getConfig().logOtherConversionWarnings()) {
            ViaLegacy.getPlatform().getLogger().warning("Received unknown classic protocol extension: (" + extensionName + " v" + extensionVersion + ")");
         }

         protocolMetadataStorage.incrementReceivedExtensions();
         ClassicProgressStorage classicProgressStorage = packetWrapper3.user().get(ClassicProgressStorage.class);
         classicProgressStorage.progress = protocolMetadataStorage.getReceivedExtensions();
         if (protocolMetadataStorage.getReceivedExtensions() >= protocolMetadataStorage.getExtensionCount()) {
            classicProgressStorage.status = "Sending extension list...";
            List supportedExtensions = new ArrayList<>();

            for (ClassicProtocolExtension protocolExtension : ClassicProtocolExtension.values()) {
               if (protocolExtension.isSupported()) {
                  supportedExtensions.add(protocolExtension);
               }
            }

            if (supportedExtensions.contains(ClassicProtocolExtension.BLOCK_PERMISSIONS)) {
               packetWrapper3.user().put(new ExtBlockPermissionsStorage());
            }

            PacketWrapper extensionProtocolInfo = PacketWrapper.create(ServerboundPacketsc0_30cpe.EXTENSION_PROTOCOL_INFO, packetWrapper3.user());
            extensionProtocolInfo.write(Typesc0_30.STRING, ViaLegacy.getPlatform().getCpeAppName());
            extensionProtocolInfo.write(Types.SHORT, (short)supportedExtensions.size());
            extensionProtocolInfo.sendToServer(Protocolc0_30cpeToc0_28_30.class);

            for (ClassicProtocolExtension protocolExtensionx : (Iterable<ClassicProtocolExtension>)(supportedExtensions)) {
               PacketWrapper extensionProtocolEntry = PacketWrapper.create(ServerboundPacketsc0_30cpe.EXTENSION_PROTOCOL_ENTRY, packetWrapper3.user());
               extensionProtocolEntry.write(Typesc0_30.STRING, protocolExtensionx.getName());
               extensionProtocolEntry.write(Types.INT, protocolExtensionx.getHighestSupportedVersion());
               extensionProtocolEntry.sendToServer(Protocolc0_30cpeToc0_28_30.class);
            }
         }
      });
      this.registerClientbound(ClientboundPacketsc0_30cpe.EXT_CUSTOM_BLOCKS_SUPPORT_LEVEL, null, packetWrapper4 -> {
         packetWrapper4.cancel();
         byte level = packetWrapper4.read(Types.BYTE);
         if (level != 1) {
            ViaLegacy.getPlatform().getLogger().info(new StringBuilder().append("Classic server supports CustomBlocks level ").append(level).toString());
         }

         PacketWrapper response = PacketWrapper.create(ServerboundPacketsc0_30cpe.EXT_CUSTOM_BLOCKS_SUPPORT_LEVEL, packetWrapper4.user());
         response.write(Types.BYTE, (byte)1);
         response.sendToServer(Protocolc0_30cpeToc0_28_30.class);
      });
      this.registerClientbound(ClientboundPacketsc0_30cpe.EXT_HACK_CONTROL, null, packetWrapper5 -> {
         packetWrapper5.cancel();
         ClassicOpLevelStorage opLevelStorage = packetWrapper5.user().get(ClassicOpLevelStorage.class);
         boolean flying = packetWrapper5.read(Types.BOOLEAN);
         boolean noClip = packetWrapper5.read(Types.BOOLEAN);
         boolean speed = packetWrapper5.read(Types.BOOLEAN);
         boolean respawn = packetWrapper5.read(Types.BOOLEAN);
         packetWrapper5.read(Types.BOOLEAN);
         packetWrapper5.read(Types.SHORT);
         opLevelStorage.updateHax(flying, noClip, speed, respawn);
      });
      this.registerClientbound(ClientboundPacketsc0_30cpe.EXT_SET_BLOCK_PERMISSION, null, packetWrapper6 -> {
         packetWrapper6.cancel();
         ExtBlockPermissionsStorage blockPermissionsStorage = packetWrapper6.user().get(ExtBlockPermissionsStorage.class);
         byte blockId = packetWrapper6.read(Types.BYTE);
         boolean canPlace = packetWrapper6.read(Types.BOOLEAN);
         boolean canDelete = packetWrapper6.read(Types.BOOLEAN);
         if (canPlace) {
            blockPermissionsStorage.addPlaceable(blockId);
         } else {
            blockPermissionsStorage.removePlaceable(blockId);
         }

         if (canDelete) {
            blockPermissionsStorage.addBreakable(blockId);
         } else {
            blockPermissionsStorage.removeBreakable(blockId);
         }
      });
      this.registerClientbound(
         ClientboundPacketsc0_30cpe.EXT_BULK_BLOCK_UPDATE,
         null,
         packetWrapper7 -> {
            packetWrapper7.cancel();
            ClassicLevelStorage levelStorage = packetWrapper7.user().get(ClassicLevelStorage.class);
            if (levelStorage != null && levelStorage.hasReceivedLevel()) {
               ClassicBlockRemapper remapper = packetWrapper7.user().get(ClassicBlockRemapper.class);
               ClassicLevel level = levelStorage.getClassicLevel();
               int count = packetWrapper7.read(Types.UNSIGNED_BYTE) + 1;
               byte[] indices = packetWrapper7.read(new FixedByteArrayType(1024));
               byte[] blocks = packetWrapper7.read(new FixedByteArrayType(256));
               if (packetWrapper7.user().getProtocolInfo().getPipeline().contains(Protocola1_0_15Toa1_0_16_2.class)) {
                  Map records = new HashMap<>();

                  for (int i = 0; i < count; i++) {
                     int index = (indices[i * 4] & 255) << 24 | (indices[i * 4 + 1] & 255) << 16 | (indices[i * 4 + 2] & 255) << 8 | indices[i * 4 + 3] & 255;
                     BlockPosition pos = new BlockPosition(
                        index % level.getSizeX(), index / level.getSizeX() / level.getSizeZ(), index / level.getSizeX() % level.getSizeZ()
                     );
                     byte blockId = blocks[i];
                     level.setBlock(pos, blockId);
                     if (levelStorage.isChunkLoaded(pos)) {
                        IdAndData mappedBlock = remapper.mapper().get(blockId);
                        ((List)records.computeIfAbsent(new ChunkCoord(pos.x() >> 4, pos.z() >> 4), obj -> new ArrayList<>()))
                           .add(new BlockChangeRecord1_8(pos.x() & 15, pos.y(), pos.z() & 15, mappedBlock.toRawData()));
                     }
                  }

                  for (Entry<ChunkCoord, List<BlockChangeRecord>> entry : (Iterable<Entry<ChunkCoord, List<BlockChangeRecord>>>)records.entrySet()) {
                     PacketWrapper multiBlockChange = PacketWrapper.create(ClientboundPacketsa1_0_15.CHUNK_BLOCKS_UPDATE, packetWrapper7.user());
                     multiBlockChange.write(Types.INT, entry.getKey().chunkX);
                     multiBlockChange.write(Types.INT, entry.getKey().chunkZ);
                     multiBlockChange.write(Types1_1.BLOCK_CHANGE_RECORD_ARRAY, entry.getValue().toArray(new BlockChangeRecord[0]));
                     multiBlockChange.send(Protocola1_0_15Toa1_0_16_2.class);
                  }
               }
            }
         }
      );
      this.registerClientbound(ClientboundPacketsc0_30cpe.EXT_TWO_WAY_PING, ClientboundPacketsc0_28.KEEP_ALIVE, packetWrapper8 -> {
         byte direction = packetWrapper8.read(Types.BYTE);
         short data = packetWrapper8.read(Types.SHORT);
         if (direction == 1) {
            PacketWrapper pingResponse = PacketWrapper.create(ServerboundPacketsc0_30cpe.EXT_TWO_WAY_PING, packetWrapper8.user());
            pingResponse.write(Types.BYTE, direction);
            pingResponse.write(Types.SHORT, data);
            pingResponse.sendToServer(Protocolc0_30cpeToc0_28_30.class);
         }
      });
      this.registerServerbound(ServerboundPacketsc0_28.LOGIN, new PacketHandlers() {
         @Override
         public void register() {
            this.map(Types.BYTE);
            this.map(Typesc0_30.STRING);
            this.map(Typesc0_30.STRING);
            this.map(Types.BYTE);
            this.handler(packetWrapper9 -> packetWrapper9.set(Types.BYTE, 1, (byte)66));
         }
      });
      this.registerServerbound(ServerboundPacketsc0_28.CHAT, new PacketHandlers() {
         @Override
         public void register() {
            this.map(Types.BYTE);
            this.map(Typesc0_30.STRING);
            this.handler(packetWrapper9 -> {
               ExtensionProtocolMetadataStorage protocolMetadata = packetWrapper9.user().get(ExtensionProtocolMetadataStorage.class);
               if (protocolMetadata.hasServerExtension(ClassicProtocolExtension.LONGER_MESSAGES, 1)) {
                  packetWrapper9.cancel();
                  String message = packetWrapper9.get(Typesc0_30.STRING, 0);

                  while (!message.isEmpty()) {
                     int pos = Math.min(message.length(), 64);
                     String msg = message.substring(0, pos);
                     message = message.substring(pos);
                     PacketWrapper chatMessage = PacketWrapper.create(ServerboundPacketsc0_30cpe.CHAT, packetWrapper9.user());
                     chatMessage.write(Types.BYTE, (byte)(!message.isEmpty() ? 1 : 0));
                     chatMessage.write(Typesc0_30.STRING, msg);
                     chatMessage.sendToServer(Protocolc0_30cpeToc0_28_30.class);
                  }
               }
            });
         }
      });
      this.registerServerbound(ServerboundPacketsc0_28.USE_ITEM_ON, new PacketHandlers() {
         @Override
         public void register() {
            this.map(Typesc0_30.BLOCK_POSITION);
            this.map(Types.BOOLEAN);
            this.map(Types.BYTE);
            this.handler(packetWrapper9 -> {
               if (packetWrapper9.user().has(ExtBlockPermissionsStorage.class)) {
                  ExtBlockPermissionsStorage blockPermissions = packetWrapper9.user().get(ExtBlockPermissionsStorage.class);
                  ClassicLevel level = packetWrapper9.user().get(ClassicLevelStorage.class).getClassicLevel();
                  BlockPosition position = packetWrapper9.get(Typesc0_30.BLOCK_POSITION, 0);
                  boolean placeBlock = packetWrapper9.get(Types.BOOLEAN, 0);
                  int blockId = packetWrapper9.get(Types.BYTE, 0);
                  int block = level.getBlock(position);
                  if (placeBlock && blockPermissions.isPlacingDenied(blockId) || !placeBlock && blockPermissions.isBreakingDenied(block)) {
                     packetWrapper9.cancel();
                     PacketWrapper chatMessage = PacketWrapper.create(ClientboundPacketsc0_30cpe.CHAT, packetWrapper9.user());
                     chatMessage.write(Types.BYTE, (byte)0);
                     chatMessage.write(Typesc0_30.STRING, "&cYou are not allowed to place/break this block");
                     chatMessage.send(Protocolc0_30cpeToc0_28_30.class);
                  } else {
                     block = placeBlock ? blockId : 0;
                     level.setBlock(position, block);
                  }

                  PacketWrapper blockChange = PacketWrapper.create(ClientboundPacketsc0_30cpe.BLOCK_UPDATE, packetWrapper9.user());
                  blockChange.write(Typesc0_30.BLOCK_POSITION, position);
                  blockChange.write(Types.BYTE, (byte)block);
                  blockChange.send(Protocolc0_30cpeToc0_28_30.class);
               }
            });
         }
      });
   }

   @Override
   public void register(ViaProviders viaProviders) {
      Via.getPlatform().runRepeatingSync(new ClassicPingTask(), 20L);
   }

   @Override
   public void init(UserConnection userConnection) {
      userConnection.put(new PreNettySplitter(Protocolc0_30cpeToc0_28_30.class, ClientboundPacketsc0_30cpe::getPacket));
      userConnection.put(new ExtensionProtocolMetadataStorage());
      userConnection.put(new ClassicOpLevelStorage(userConnection, true));
      ClassicBlockRemapper previousRemapper = userConnection.get(ClassicBlockRemapper.class);
      userConnection.put(
         new ClassicBlockRemapper(
            n -> {
               if (ClassicBlocks.MAPPING.containsKey(n)) {
                  return previousRemapper.mapper().get(n);
               } else {
                  ExtensionProtocolMetadataStorage extensionProtocol = userConnection.get(ExtensionProtocolMetadataStorage.class);
                  return extensionProtocol.hasServerExtension(ClassicProtocolExtension.CUSTOM_BLOCKS, 1)
                     ? ExtendedClassicBlocks.MAPPING.get(n)
                     : new IdAndData(BlockList1_6.stone.blockId(), 0);
               }
            },
            obj -> {
               if (ClassicBlocks.REVERSE_MAPPING.containsKey(obj)) {
                  return previousRemapper.reverseMapper().getInt(obj);
               } else {
                  ExtensionProtocolMetadataStorage extensionProtocol = userConnection.get(ExtensionProtocolMetadataStorage.class);
                  return extensionProtocol.hasServerExtension(ClassicProtocolExtension.CUSTOM_BLOCKS, 1)
                     ? ExtendedClassicBlocks.REVERSE_MAPPING.getInt(obj)
                     : 1;
               }
            }
         )
      );
   }
}
