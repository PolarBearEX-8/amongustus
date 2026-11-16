package net.minecraft.network.play.server;

import client.setting.MovementDistanceProperty;
import client.util.ComparatorEntry;
import client.util.INetHandler;
import client.util.INetHandlerPlayClient;
import client.util.Packet;
import client.util.PacketBuffer;
import client.util.ScoreboardData;
import java.io.IOException;

public class S3CPacketUpdateScore implements Packet {
   public String labelText = "";
   public String labelText2 = "";
   public int count;
   public MovementDistanceProperty movementDistanceProperty;

   public void processPacket(INetHandlerPlayClient iNetHandlerPlayClient) {
      iNetHandlerPlayClient.applyS3CPacketUpdateScore(this);
   }

   public String getLabelText() {
      return this.labelText;
   }

   public MovementDistanceProperty getMovementDistanceProperty() {
      return this.movementDistanceProperty;
   }

   @Override
   public void processAction(PacketBuffer packet) throws IOException {
      this.labelText = packet.resolveText(40);
      this.movementDistanceProperty = (MovementDistanceProperty)packet.getEnum(MovementDistanceProperty.class);
      this.labelText2 = packet.resolveText(16);
      if (this.movementDistanceProperty != MovementDistanceProperty.REMOVE) {
         this.count = packet.getInt3();
      }
   }

   @Override
   public void processAction(INetHandler iNetHandler) {
      this.processPacket((INetHandlerPlayClient)iNetHandler);
   }

   @Override
   public void processAction2(PacketBuffer packet) throws IOException {
      packet.getPacketBuffer(this.labelText);
      packet.applyEnum(this.movementDistanceProperty);
      packet.getPacketBuffer(this.labelText2);
      if (this.movementDistanceProperty != MovementDistanceProperty.REMOVE) {
         packet.updateInt(this.count);
      }
   }

   public String getLabelText2() {
      return this.labelText2;
   }

   public int getCount() {
      return this.count;
   }

   public S3CPacketUpdateScore(String text) {
      this.labelText = text;
      this.labelText2 = "";
      this.count = 0;
      this.movementDistanceProperty = MovementDistanceProperty.REMOVE;
   }

   public S3CPacketUpdateScore() {
   }

   public S3CPacketUpdateScore(String text, ScoreboardData scoreboardData) {
      this.labelText = text;
      this.labelText2 = scoreboardData.getLabelText2();
      this.count = 0;
      this.movementDistanceProperty = MovementDistanceProperty.REMOVE;
   }

   public S3CPacketUpdateScore(ComparatorEntry comparatorEntry) {
      this.labelText = comparatorEntry.getLabelText();
      this.labelText2 = comparatorEntry.getScoreboardData().getLabelText2();
      this.count = comparatorEntry.getIndex();
      this.movementDistanceProperty = MovementDistanceProperty.CHANGE;
   }
}
