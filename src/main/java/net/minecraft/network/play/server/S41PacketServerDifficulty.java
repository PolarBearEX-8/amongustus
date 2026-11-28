package net.minecraft.network.play.server;

import java.io.IOException;
import net.minecraft.network.INetHandler;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;
import net.minecraft.world.EnumDifficulty;

public class S41PacketServerDifficulty implements Packet {
   private EnumDifficulty difficulty;
   private boolean difficultyLocked;

   public S41PacketServerDifficulty(EnumDifficulty enumDifficulty, boolean flag) {
      this.difficulty = enumDifficulty;
      this.difficultyLocked = flag;
   }

   @Override
   public void readPacketData(PacketBuffer buf) throws IOException {
      this.difficulty = EnumDifficulty.getDifficultyEnum(buf.readUnsignedByte());
   }

   public void processPacket(INetHandlerPlayClient handler) {
      handler.handleServerDifficulty(this);
   }

   @Override
   public void writePacketData(PacketBuffer buf) throws IOException {
      buf.writeByte(this.difficulty.getDifficultyId());
   }

   public boolean isDifficultyLocked() {
      return this.difficultyLocked;
   }

   public EnumDifficulty getDifficulty() {
      return this.difficulty;
   }

   public S41PacketServerDifficulty() {
   }

   @Override
   public void processPacket(INetHandler handler) {
      this.processPacket((INetHandlerPlayClient)handler);
   }
}
