package net.minecraft.network.play.server;

import client.setting.StaffDetectorAddPlayerType;
import client.setting.WorldSettingsGameType;
import client.util.ExtraIntArrayEntry;
import client.util.INetHandler;
import client.util.INetHandlerPlayClient;
import client.util.LatencyManager;
import client.util.Packet;
import client.util.PacketBuffer;
import com.google.common.base.MoreObjects;
import com.google.common.collect.Lists;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import java.io.IOException;
import java.util.List;
import net.minecraft.entity.player.EntityPlayerAdapter;
import net.minecraft.util.IChatComponent;

public class S38PacketPlayerListItem implements Packet {
   public StaffDetectorAddPlayerType staffDetectorAddPlayerType;
   public final List list = Lists.newArrayList();

   public void processPacket(INetHandlerPlayClient iNetHandlerPlayClient) {
      iNetHandlerPlayClient.applyS38PacketPlayerListItem(this);
   }

   public StaffDetectorAddPlayerType getStaffDetectorAddPlayerType() {
      return this.staffDetectorAddPlayerType;
   }

   @Override
   public void processAction(PacketBuffer packet) throws IOException {
      this.staffDetectorAddPlayerType = (StaffDetectorAddPlayerType)packet.getEnum(StaffDetectorAddPlayerType.class);
      int intItem = packet.getInt3();

      for (int index = 0; index < intItem; index++) {
         GameProfile gameProfile = null;
         int count = 0;
         WorldSettingsGameType worldSettingsGameType = null;
         IChatComponent iChatComponent = null;
         switch (ExtraIntArrayEntry.countArray[this.staffDetectorAddPlayerType.ordinal()]) {
            case 1:
               gameProfile = new GameProfile(packet.getUUID(), packet.resolveTextByIndex(16));
               int intItem2 = packet.getInt3();
               int count2 = 0;

               for (; count2 < intItem2; count2++) {
                  String text = packet.resolveTextByIndex(32767);
                  String text2 = packet.resolveTextByIndex(32767);
                  if (packet.readBoolean()) {
                     gameProfile.getProperties().put(text, new Property(text, text2, packet.resolveTextByIndex(32767)));
                  } else {
                     gameProfile.getProperties().put(text, new Property(text, text2));
                  }
               }

               worldSettingsGameType = WorldSettingsGameType.resolveWorldSettingsGameType(packet.getInt3());
               count = packet.getInt3();
               if (packet.readBoolean()) {
                  iChatComponent = packet.getChatComponent();
               }
               break;
            case 2:
               gameProfile = new GameProfile(packet.getUUID(), (String)null);
               worldSettingsGameType = WorldSettingsGameType.resolveWorldSettingsGameType(packet.getInt3());
               break;
            case 3:
               gameProfile = new GameProfile(packet.getUUID(), (String)null);
               count = packet.getInt3();
               break;
            case 4:
               gameProfile = new GameProfile(packet.getUUID(), (String)null);
               if (packet.readBoolean()) {
                  iChatComponent = packet.getChatComponent();
               }
               break;
            case 5:
               gameProfile = new GameProfile(packet.getUUID(), (String)null);
         }

         this.list.add(new LatencyManager(this, gameProfile, count, worldSettingsGameType, iChatComponent));
      }
   }

   public List getItemList() {
      return this.list;
   }

   @Override
   public void processAction(INetHandler iNetHandler) {
      this.processPacket((INetHandlerPlayClient)iNetHandler);
   }

   @Override
   public void processAction2(PacketBuffer packet) throws IOException {
      packet.applyEnum(this.staffDetectorAddPlayerType);
      packet.updateInt(this.list.size());

      for (LatencyManager latencyManager : (Iterable<LatencyManager>)(Iterable)(this.list)) {
         switch (ExtraIntArrayEntry.countArray[this.staffDetectorAddPlayerType.ordinal()]) {
            case 1:
               packet.applyUUID(latencyManager.getGameProfile().getId());
               packet.getPacketBuffer(latencyManager.getGameProfile().getName());
               packet.updateInt(latencyManager.getGameProfile().getProperties().size());

               for (Property property : latencyManager.getGameProfile().getProperties().values()) {
                  packet.getPacketBuffer(property.getName());
                  packet.getPacketBuffer(property.getValue());
                  if (property.hasSignature()) {
                     packet.writeBoolean(true);
                     packet.getPacketBuffer(property.getSignature());
                  } else {
                     packet.writeBoolean(false);
                  }
               }

               packet.updateInt(latencyManager.getWorldSettingsGameType().getIndex());
               packet.updateInt(latencyManager.getCount());
               if (latencyManager.getIChatComponent() == null) {
                  packet.writeBoolean(false);
               } else {
                  packet.writeBoolean(true);
                  packet.setChatComponent(latencyManager.getIChatComponent());
               }
               break;
            case 2:
               packet.applyUUID(latencyManager.getGameProfile().getId());
               packet.updateInt(latencyManager.getWorldSettingsGameType().getIndex());
               break;
            case 3:
               packet.applyUUID(latencyManager.getGameProfile().getId());
               packet.updateInt(latencyManager.getCount());
               break;
            case 4:
               packet.applyUUID(latencyManager.getGameProfile().getId());
               if (latencyManager.getIChatComponent() == null) {
                  packet.writeBoolean(false);
               } else {
                  packet.writeBoolean(true);
                  packet.setChatComponent(latencyManager.getIChatComponent());
               }
               break;
            case 5:
               packet.applyUUID(latencyManager.getGameProfile().getId());
         }
      }
   }

   public S38PacketPlayerListItem(StaffDetectorAddPlayerType staffDetectorAddPlayerType2, EntityPlayerAdapter[] entityPlayerAdapterArray) {
      this.staffDetectorAddPlayerType = staffDetectorAddPlayerType2;

      for (EntityPlayerAdapter entityPlayerAdapter : entityPlayerAdapterArray) {
         this.list
            .add(new LatencyManager(this, entityPlayerAdapter.getGameProfile(), entityPlayerAdapter.count41, entityPlayerAdapter.internalWorldEntry.getWorldSettingsGameType(), entityPlayerAdapter.getIChatComponent()));
      }
   }

   public S38PacketPlayerListItem() {
   }

   public S38PacketPlayerListItem(StaffDetectorAddPlayerType staffDetectorAddPlayerType2, Iterable collection) {
      this.staffDetectorAddPlayerType = staffDetectorAddPlayerType2;

      for (EntityPlayerAdapter entityPlayerAdapter : (Iterable<EntityPlayerAdapter>)(Iterable)(collection)) {
         this.list
            .add(new LatencyManager(this, entityPlayerAdapter.getGameProfile(), entityPlayerAdapter.count41, entityPlayerAdapter.internalWorldEntry.getWorldSettingsGameType(), entityPlayerAdapter.getIChatComponent()));
      }
   }

   @Override
   public String toString() {
      return MoreObjects.toStringHelper(this).add("action", this.staffDetectorAddPlayerType).add("entries", this.list).toString();
   }
}
