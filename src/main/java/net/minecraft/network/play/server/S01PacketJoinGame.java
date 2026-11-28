package net.minecraft.network.play.server;

import net.minecraft.world.WorldSettings;
import java.io.IOException;
import net.minecraft.network.INetHandler;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.WorldType;

public class S01PacketJoinGame implements Packet {
   private int entityId;
   private boolean hardcoreMode;
   private WorldSettings.GameType gameType;
   private int dimension;
   private EnumDifficulty difficulty;
   private int maxPlayers;
   private WorldType worldType;
   private boolean reducedDebugInfo;

   public boolean isHardcoreMode() {
      return this.hardcoreMode;
   }

   public WorldType getWorldType() {
      return this.worldType;
   }

   public EnumDifficulty getDifficulty() {
      return this.difficulty;
   }

   @Override
   public void writePacketData(PacketBuffer buf) throws IOException {
      buf.writeInt(this.entityId);
      int id = this.gameType.getID();
      if (this.hardcoreMode) {
         id |= 8;
      }

      buf.writeByte(id);
      buf.writeByte(this.dimension);
      buf.writeByte(this.difficulty.getDifficultyId());
      buf.writeByte(this.maxPlayers);
      buf.writeString(this.worldType.getWorldTypeName());
      buf.writeBoolean(this.reducedDebugInfo);
   }

   public void processPacket(INetHandlerPlayClient handler) {
      handler.handleJoinGame(this);
   }

   public S01PacketJoinGame() {
   }

   public int getMaxPlayers() {
      return this.maxPlayers;
   }

   public WorldSettings.GameType getGameType() {
      return this.gameType;
   }

   public S01PacketJoinGame(int n, WorldSettings.GameType gameType2, boolean flag, int n2, EnumDifficulty enumDifficulty, int n3, WorldType worldType2, boolean flag2) {
      this.entityId = n;
      this.dimension = n2;
      this.difficulty = enumDifficulty;
      this.gameType = gameType2;
      this.maxPlayers = n3;
      this.hardcoreMode = flag;
      this.worldType = worldType2;
      this.reducedDebugInfo = flag2;
   }

   public int getDimension() {
      return this.dimension;
   }

   @Override
   public void processPacket(INetHandler handler) {
      this.processPacket((INetHandlerPlayClient)handler);
   }

   @Override
   public void readPacketData(PacketBuffer buf) throws IOException {
      this.entityId = buf.readInt();
      int n = buf.readUnsignedByte();
      this.hardcoreMode = (n & 8) == 8;
      n &= -9;
      this.gameType = WorldSettings.GameType.getByID(n);
      this.dimension = buf.readByte();
      this.difficulty = EnumDifficulty.getDifficultyEnum(buf.readUnsignedByte());
      this.maxPlayers = buf.readUnsignedByte();
      this.worldType = WorldType.parseWorldType(buf.readStringFromBuffer(16));
      if (this.worldType == null) {
         this.worldType = WorldType.DEFAULT;
      }

      this.reducedDebugInfo = buf.readBoolean();
   }

   public boolean isReducedDebugInfo() {
      return this.reducedDebugInfo;
   }

   public int getEntityId() {
      return this.entityId;
   }
}
