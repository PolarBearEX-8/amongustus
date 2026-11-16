package net.minecraft.network.play.server;

import client.setting.MovementBooleanProperty;
import client.util.INetHandler;
import client.util.INetHandlerPlayClient;
import client.util.Packet;
import client.util.PacketBuffer;
import com.google.common.collect.Lists;
import java.io.IOException;
import java.util.Collection;
import net.minecraft.scoreboard.ScorePlayerTeam;

public class S3EPacketTeams implements Packet {
   public String labelText5 = "";
   public String labelText2 = "";
   public String labelText = "";
   public String labelText4 = "";
   public String labelText3 = MovementBooleanProperty.ALWAYS.labelText;
   public int count = -1;
   public Collection collection = Lists.newArrayList();
   public int count2;
   public int count3;

   public String getLabelText4() {
      return this.labelText4;
   }

   public Collection getCollection() {
      return this.collection;
   }

   public String getLabelText2() {
      return this.labelText2;
   }

   public int getCount3() {
      return this.count3;
   }

   public String getLabelText() {
      return this.labelText;
   }

   @Override
   public void processAction(PacketBuffer packet) throws IOException {
      this.labelText5 = packet.resolveTextByIndex(16);
      this.count2 = packet.readByte();
      if (this.count2 == 0 || this.count2 == 2) {
         this.labelText2 = packet.resolveTextByIndex(32);
         this.labelText = packet.resolveTextByIndex(16);
         this.labelText4 = packet.resolveTextByIndex(16);
         this.count3 = packet.readByte();
         this.labelText3 = packet.resolveTextByIndex(32);
         this.count = packet.readByte();
      }

      if (this.count2 == 0 || this.count2 == 3 || this.count2 == 4) {
         int intItem = packet.getInt3();

         for (int index = 0; index < intItem; index++) {
            this.collection.add(packet.resolveTextByIndex(40));
         }
      }
   }

   public void processPacket(INetHandlerPlayClient iNetHandlerPlayClient) {
      iNetHandlerPlayClient.applyS3EPacketTeams(this);
   }

   @Override
   public void processAction(INetHandler iNetHandler) {
      this.processPacket((INetHandlerPlayClient)iNetHandler);
   }

   public String getLabelText3() {
      return this.labelText3;
   }

   @Override
   public void processAction2(PacketBuffer packet) throws IOException {
      packet.getPacketBuffer(this.labelText5);
      packet.writeByte(this.count2);
      if (this.count2 == 0 || this.count2 == 2) {
         packet.getPacketBuffer(this.labelText2);
         packet.getPacketBuffer(this.labelText);
         packet.getPacketBuffer(this.labelText4);
         packet.writeByte(this.count3);
         packet.getPacketBuffer(this.labelText3);
         packet.writeByte(this.count);
      }

      if (this.count2 == 0 || this.count2 == 3 || this.count2 == 4) {
         packet.updateInt(this.collection.size());

         for (String text : (Iterable<String>)(Iterable<?>)this.collection) {
            packet.getPacketBuffer(text);
         }
      }
   }

   public String getLabelText5() {
      return this.labelText5;
   }

   public int getCount() {
      return this.count;
   }

   public int getCount2() {
      return this.count2;
   }

   public S3EPacketTeams(ScorePlayerTeam scorePlayerTeam, Collection collection2, int count4) {
      if (count4 != 3 && count4 != 4) {
         throw new IllegalArgumentException("Method must be join or leave for player constructor");
      } else if (collection2 != null && !collection2.isEmpty()) {
         this.count2 = count4;
         this.labelText5 = scorePlayerTeam.getLabelText2();
         this.collection.addAll(collection2);
      } else {
         throw new IllegalArgumentException("Players cannot be null/empty");
      }
   }

   public S3EPacketTeams(ScorePlayerTeam scorePlayerTeam, int count4) {
      this.labelText5 = scorePlayerTeam.getLabelText2();
      this.count2 = count4;
      if (count4 == 0 || count4 == 2) {
         this.labelText2 = scorePlayerTeam.getText2();
         this.labelText = scorePlayerTeam.getText();
         this.labelText4 = scorePlayerTeam.getLabelText();
         this.count3 = scorePlayerTeam.getCount();
         this.labelText3 = scorePlayerTeam.getMovementBooleanProperty2().labelText;
         this.count = scorePlayerTeam.getClientSettingsBlackType().getIndex();
      }

      if (count4 == 0) {
         this.collection.addAll(scorePlayerTeam.getCollection());
      }
   }

   public S3EPacketTeams() {
   }
}
