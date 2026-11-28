package net.minecraft.network.play.server;

import java.io.IOException;
import net.minecraft.network.INetHandler;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;
import net.minecraft.world.border.WorldBorder;

public class S44PacketWorldBorder implements Packet {
   private S44PacketWorldBorder.Action action;
   private int size;
   private double centerX;
   private double centerZ;
   private double targetSize;
   private double diameter;
   private long timeUntilTarget;
   private int warningTime;
   private int warningDistance;

   public void processPacket(INetHandlerPlayClient handler) {
      handler.handleWorldBorder(this);
   }

   public S44PacketWorldBorder() {
   }

   @Override
   public void writePacketData(PacketBuffer buf) throws IOException {
      buf.writeEnumValue(this.action);
      switch (this.action) {
         case SET_SIZE:
            buf.writeDouble(this.targetSize);
            break;
         case LERP_SIZE:
            buf.writeDouble(this.diameter);
            buf.writeDouble(this.targetSize);
            buf.writeVarLong(this.timeUntilTarget);
            break;
         case SET_CENTER:
            buf.writeDouble(this.centerX);
            buf.writeDouble(this.centerZ);
            break;
         case SET_WARNING_BLOCKS:
            buf.writeVarIntToBuffer(this.warningDistance);
            break;
         case SET_WARNING_TIME:
            buf.writeVarIntToBuffer(this.warningTime);
            break;
         case INITIALIZE:
            buf.writeDouble(this.centerX);
            buf.writeDouble(this.centerZ);
            buf.writeDouble(this.diameter);
            buf.writeDouble(this.targetSize);
            buf.writeVarLong(this.timeUntilTarget);
            buf.writeVarIntToBuffer(this.size);
            buf.writeVarIntToBuffer(this.warningDistance);
            buf.writeVarIntToBuffer(this.warningTime);
      }
   }

   @Override
   public void processPacket(INetHandler handler) {
      this.processPacket((INetHandlerPlayClient)handler);
   }

   public S44PacketWorldBorder(WorldBorder worldBorder, S44PacketWorldBorder.Action action2) {
      this.action = action2;
      this.centerX = worldBorder.getCenterX();
      this.centerZ = worldBorder.getCenterZ();
      this.diameter = worldBorder.getDiameter();
      this.targetSize = worldBorder.getTargetSize();
      this.timeUntilTarget = worldBorder.getTimeUntilTarget();
      this.size = worldBorder.getSize();
      this.warningDistance = worldBorder.getWarningDistance();
      this.warningTime = worldBorder.getWarningTime();
   }

   @Override
   public void readPacketData(PacketBuffer buf) throws IOException {
      this.action = (S44PacketWorldBorder.Action)buf.readEnumValue(S44PacketWorldBorder.Action.class);
      switch (this.action) {
         case SET_SIZE:
            this.targetSize = buf.readDouble();
            break;
         case LERP_SIZE:
            this.diameter = buf.readDouble();
            this.targetSize = buf.readDouble();
            this.timeUntilTarget = buf.readVarLong();
            break;
         case SET_CENTER:
            this.centerX = buf.readDouble();
            this.centerZ = buf.readDouble();
            break;
         case SET_WARNING_BLOCKS:
            this.warningDistance = buf.readVarIntFromBuffer();
            break;
         case SET_WARNING_TIME:
            this.warningTime = buf.readVarIntFromBuffer();
            break;
         case INITIALIZE:
            this.centerX = buf.readDouble();
            this.centerZ = buf.readDouble();
            this.diameter = buf.readDouble();
            this.targetSize = buf.readDouble();
            this.timeUntilTarget = buf.readVarLong();
            this.size = buf.readVarIntFromBuffer();
            this.warningDistance = buf.readVarIntFromBuffer();
            this.warningTime = buf.readVarIntFromBuffer();
      }
   }

   public void apply(WorldBorder worldBorder) {
      switch (this.action) {
         case SET_SIZE:
            worldBorder.setTransition(this.targetSize);
            break;
         case LERP_SIZE:
            worldBorder.setTransition(this.diameter, this.targetSize, this.timeUntilTarget);
            break;
         case SET_CENTER:
            worldBorder.setCenter(this.centerX, this.centerZ);
            break;
         case SET_WARNING_BLOCKS:
            worldBorder.setWarningDistance(this.warningDistance);
            break;
         case SET_WARNING_TIME:
            worldBorder.setWarningTime(this.warningTime);
            break;
         case INITIALIZE:
            worldBorder.setCenter(this.centerX, this.centerZ);
            if (this.timeUntilTarget > 0L) {
               worldBorder.setTransition(this.diameter, this.targetSize, this.timeUntilTarget);
            } else {
               worldBorder.setTransition(this.targetSize);
            }

            worldBorder.setSize(this.size);
            worldBorder.setWarningDistance(this.warningDistance);
            worldBorder.setWarningTime(this.warningTime);
      }
   }

public enum Action {
      SET_SIZE,
      LERP_SIZE,
      SET_CENTER,
      INITIALIZE,
      SET_WARNING_TIME,
      SET_WARNING_BLOCKS;
   }
}
