package net.minecraft.network.play.server;

import net.minecraft.world.WorldSettings;
import java.io.IOException;
import net.minecraft.network.INetHandler;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.WorldType;

public class S07PacketRespawn implements Packet {
   private int dimensionID;
   private EnumDifficulty difficulty;
   private WorldSettings.GameType gameType;
   private WorldType worldType;

   public WorldSettings.GameType getGameType() {
      return this.gameType;
   }

   @Override
   public void writePacketData(PacketBuffer buf) throws IOException {
      buf.writeInt(this.dimensionID);
      buf.writeByte(this.difficulty.getDifficultyId());
      buf.writeByte(this.gameType.getID());
      buf.writeString(this.worldType.getWorldTypeName());
   }

   public int getDimensionID() {
      return this.dimensionID;
   }

   public S07PacketRespawn() {
   }

   public S07PacketRespawn(int n, EnumDifficulty enumDifficulty, WorldType worldType2, WorldSettings.GameType gameType2) {
      this.dimensionID = n;
      this.difficulty = enumDifficulty;
      this.gameType = gameType2;
      this.worldType = worldType2;
   }

   @Override
   public void readPacketData(PacketBuffer buf) throws IOException {
      this.dimensionID = buf.readInt();
      this.difficulty = EnumDifficulty.getDifficultyEnum(buf.readUnsignedByte());
      this.gameType = WorldSettings.GameType.getByID(buf.readUnsignedByte());
      this.worldType = WorldType.parseWorldType(buf.readStringFromBuffer(16));
      if (this.worldType == null) {
         this.worldType = WorldType.DEFAULT;
      }
   }

   public EnumDifficulty getDifficulty() {
      return this.difficulty;
   }

   public WorldType getWorldType() {
      return this.worldType;
   }

   public void processPacket(INetHandlerPlayClient handler) {
      handler.handleRespawn(this);
   }

   @Override
   public void processPacket(INetHandler handler) {
      this.processPacket((INetHandlerPlayClient)handler);
   }
}
