package net.minecraft.network.play.server;

import client.setting.DynamicOptionType;
import client.util.INetHandler;
import client.util.INetHandlerPlayClient;
import client.util.Packet;
import client.util.PacketBuffer;
import client.util.SmoothPredictor;
import java.io.IOException;
import net.minecraft.world.storage.WorldInfo;

public class S44PacketWorldBorder implements Packet {
   public DynamicOptionType dynamicOptionType;
   public int count2;
   public double value2;
   public double value;
   public double value4;
   public double value3;
   public long timestamp;
   public int count3;
   public int count;

   @Override
   public void processAction(PacketBuffer packet) throws IOException {
      this.dynamicOptionType = (DynamicOptionType)packet.getEnum(DynamicOptionType.class);
      switch (SmoothPredictor.countArray[this.dynamicOptionType.ordinal()]) {
         case 1:
            this.value4 = packet.readDouble();
            break;
         case 2:
            this.value3 = packet.readDouble();
            this.value4 = packet.readDouble();
            this.timestamp = packet.getTimestamp();
            break;
         case 3:
            this.value2 = packet.readDouble();
            this.value = packet.readDouble();
            break;
         case 4:
            this.count = packet.getInt3();
            break;
         case 5:
            this.count3 = packet.getInt3();
            break;
         case 6:
            this.value2 = packet.readDouble();
            this.value = packet.readDouble();
            this.value3 = packet.readDouble();
            this.value4 = packet.readDouble();
            this.timestamp = packet.getTimestamp();
            this.count2 = packet.getInt3();
            this.count = packet.getInt3();
            this.count3 = packet.getInt3();
      }
   }

   @Override
   public void processAction(INetHandler iNetHandler) {
      this.processPacket((INetHandlerPlayClient)iNetHandler);
   }

   public void processPacket(INetHandlerPlayClient iNetHandlerPlayClient) {
      iNetHandlerPlayClient.applyS44PacketWorldBorder(this);
   }

   @Override
   public void processAction2(PacketBuffer packet) throws IOException {
      packet.applyEnum(this.dynamicOptionType);
      switch (SmoothPredictor.countArray[this.dynamicOptionType.ordinal()]) {
         case 1:
            packet.writeDouble(this.value4);
            break;
         case 2:
            packet.writeDouble(this.value3);
            packet.writeDouble(this.value4);
            packet.applyTime(this.timestamp);
            break;
         case 3:
            packet.writeDouble(this.value2);
            packet.writeDouble(this.value);
            break;
         case 4:
            packet.updateInt(this.count);
            break;
         case 5:
            packet.updateInt(this.count3);
            break;
         case 6:
            packet.writeDouble(this.value2);
            packet.writeDouble(this.value);
            packet.writeDouble(this.value3);
            packet.writeDouble(this.value4);
            packet.applyTime(this.timestamp);
            packet.updateInt(this.count2);
            packet.updateInt(this.count);
            packet.updateInt(this.count3);
      }
   }

   public void applyWorldInfo(WorldInfo worldInfo) {
      switch (SmoothPredictor.countArray[this.dynamicOptionType.ordinal()]) {
         case 1:
            worldInfo.applyDouble(this.value4);
            break;
         case 2:
            worldInfo.applyDouble(this.value3, this.value4, this.timestamp);
            break;
         case 3:
            worldInfo.applyDouble(this.value2, this.value);
            break;
         case 4:
            worldInfo.applyIndex(this.count);
            break;
         case 5:
            worldInfo.applyIndexByIndex(this.count3);
            break;
         case 6:
            worldInfo.applyDouble(this.value2, this.value);
            if (this.timestamp > 0L) {
               worldInfo.applyDouble(this.value3, this.value4, this.timestamp);
            } else {
               worldInfo.applyDouble(this.value4);
            }

            worldInfo.setCount(this.count2);
            worldInfo.applyIndex(this.count);
            worldInfo.applyIndexByIndex(this.count3);
      }
   }

   public S44PacketWorldBorder() {
   }

   public S44PacketWorldBorder(WorldInfo worldInfo, DynamicOptionType dynamicOptionType2) {
      this.dynamicOptionType = dynamicOptionType2;
      this.value2 = worldInfo.getValue5();
      this.value = worldInfo.getValue6();
      this.value3 = worldInfo.getValue3();
      this.value4 = worldInfo.getValue7();
      this.timestamp = worldInfo.getTimestamp();
      this.count2 = worldInfo.getCount();
      this.count = worldInfo.getCount2();
      this.count3 = worldInfo.getCount3();
   }
}
