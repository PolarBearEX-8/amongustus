package net.minecraft.network.play.server;

import net.minecraft.scoreboard.Team;
import com.google.common.collect.Lists;
import java.io.IOException;
import java.util.Collection;
import net.minecraft.network.INetHandler;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;
import net.minecraft.scoreboard.ScorePlayerTeam;

public class S3EPacketTeams implements Packet {
   private String name = "";
   private String displayName = "";
   private String prefix = "";
   private String suffix = "";
   private String nameTagVisibility = Team.EnumVisible.ALWAYS.internalName;
   private int color = -1;
   private Collection players = Lists.newArrayList();
   private int action;
   private int friendlyFlags;

   public S3EPacketTeams() {
   }

   public String getSuffix() {
      return this.suffix;
   }

   @Override
   public void processPacket(INetHandler handler) {
      this.processPacket((INetHandlerPlayClient)handler);
   }

   public S3EPacketTeams(ScorePlayerTeam scorePlayerTeam, Collection items, int n) {
      if (n != 3 && n != 4) {
         throw new IllegalArgumentException("Method must be join or leave for player constructor");
      } else if (items != null && !items.isEmpty()) {
         this.action = n;
         this.name = scorePlayerTeam.getRegisteredName();
         this.players.addAll(items);
      } else {
         throw new IllegalArgumentException("Players cannot be null/empty");
      }
   }

   public int getColor() {
      return this.color;
   }

   public String getName() {
      return this.name;
   }

   public int getAction() {
      return this.action;
   }

   public String getPrefix() {
      return this.prefix;
   }

   public Collection getPlayers() {
      return this.players;
   }

   public void processPacket(INetHandlerPlayClient handler) {
      handler.handleTeams(this);
   }

   @Override
   public void readPacketData(PacketBuffer buf) throws IOException {
      this.name = buf.readStringFromBuffer(16);
      this.action = buf.readByte();
      if (this.action == 0 || this.action == 2) {
         this.displayName = buf.readStringFromBuffer(32);
         this.prefix = buf.readStringFromBuffer(16);
         this.suffix = buf.readStringFromBuffer(16);
         this.friendlyFlags = buf.readByte();
         this.nameTagVisibility = buf.readStringFromBuffer(32);
         this.color = buf.readByte();
      }

      if (this.action == 0 || this.action == 3 || this.action == 4) {
         int n = buf.readVarIntFromBuffer();

         for (int i = 0; i < n; i++) {
            this.players.add(buf.readStringFromBuffer(40));
         }
      }
   }

   public S3EPacketTeams(ScorePlayerTeam scorePlayerTeam, int n) {
      this.name = scorePlayerTeam.getRegisteredName();
      this.action = n;
      if (n == 0 || n == 2) {
         this.displayName = scorePlayerTeam.getTeamName();
         this.prefix = scorePlayerTeam.getColorPrefix();
         this.suffix = scorePlayerTeam.getColorSuffix();
         this.friendlyFlags = scorePlayerTeam.getFriendlyFlags();
         this.nameTagVisibility = scorePlayerTeam.getNameTagVisibility().internalName;
         this.color = scorePlayerTeam.getChatFormat().getColorIndex();
      }

      if (n == 0) {
         this.players.addAll(scorePlayerTeam.getMembershipCollection());
      }
   }

   public int getFriendlyFlags() {
      return this.friendlyFlags;
   }

   public String getDisplayName() {
      return this.displayName;
   }

   @Override
   public void writePacketData(PacketBuffer buf) throws IOException {
      buf.writeString(this.name);
      buf.writeByte(this.action);
      if (this.action == 0 || this.action == 2) {
         buf.writeString(this.displayName);
         buf.writeString(this.prefix);
         buf.writeString(this.suffix);
         buf.writeByte(this.friendlyFlags);
         buf.writeString(this.nameTagVisibility);
         buf.writeByte(this.color);
      }

      if (this.action == 0 || this.action == 3 || this.action == 4) {
         buf.writeVarIntToBuffer(this.players.size());

         for (String str : (Iterable<String>)(this.players)) {
            buf.writeString(str);
         }
      }
   }

   public String getNameTagVisibility() {
      return this.nameTagVisibility;
   }
}
