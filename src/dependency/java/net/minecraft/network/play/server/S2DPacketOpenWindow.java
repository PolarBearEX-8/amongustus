package net.minecraft.network.play.server;

import client.util.INetHandler;
import client.util.INetHandlerPlayClient;
import client.util.Packet;
import client.util.PacketBuffer;
import java.io.IOException;
import net.minecraft.util.IChatComponent;

public class S2DPacketOpenWindow implements Packet {
   public int count;
   public String labelText;
   public IChatComponent iChatComponent;
   public int count3;
   public int count2;

   public void processPacket(INetHandlerPlayClient iNetHandlerPlayClient) {
      iNetHandlerPlayClient.applyS2DPacketOpenWindow(this);
   }

   public IChatComponent getIChatComponent() {
      return this.iChatComponent;
   }

   @Override
   public void processAction(PacketBuffer packet) throws IOException {
      this.count = packet.readUnsignedByte();
      this.labelText = packet.resolveTextByIndex(32);
      this.iChatComponent = packet.getChatComponent();
      this.count3 = packet.readUnsignedByte();
      if (this.labelText.equals("EntityHorse")) {
         this.count2 = packet.readInt();
      }
   }

   @Override
   public void processAction(INetHandler iNetHandler) {
      this.processPacket((INetHandlerPlayClient)iNetHandler);
   }

   public int getCount2() {
      return this.count2;
   }

   public String getLabelText() {
      return this.labelText;
   }

   @Override
   public void processAction2(PacketBuffer packet) throws IOException {
      packet.writeByte(this.count);
      packet.getPacketBuffer(this.labelText);
      packet.setChatComponent(this.iChatComponent);
      packet.writeByte(this.count3);
      if (this.labelText.equals("EntityHorse")) {
         packet.writeInt(this.count2);
      }
   }

   public int getCount3() {
      return this.count3;
   }

   public int getCount() {
      return this.count;
   }

   public boolean isActive() {
      return this.count3 > 0;
   }

   public S2DPacketOpenWindow(int count4, String text, IChatComponent iChatComponent2, int count5, int count6) {
      this(count4, text, iChatComponent2, count5);
      this.count2 = count6;
   }

   public S2DPacketOpenWindow() {
   }

   public S2DPacketOpenWindow(int count4, String text, IChatComponent iChatComponent2, int count5) {
      this.count = count4;
      this.labelText = text;
      this.iChatComponent = iChatComponent2;
      this.count3 = count5;
   }

   public S2DPacketOpenWindow(int count4, String text, IChatComponent iChatComponent2) {
      this(count4, text, iChatComponent2, 0);
   }
}
