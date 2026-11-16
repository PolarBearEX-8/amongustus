package net.minecraft.network.play.server;

import client.setting.MovementFilterProperty;
import client.util.INetHandler;
import client.util.INetHandlerPlayClient;
import client.util.Packet;
import client.util.PacketBuffer;
import java.io.IOException;
import net.minecraft.util.IChatComponent;

public class S45PacketTitle implements Packet {
   public MovementFilterProperty movementFilterProperty;
   public IChatComponent iChatComponent;
   public int count2;
   public int count;
   public int count3;

   public void processPacket(INetHandlerPlayClient iNetHandlerPlayClient) {
      iNetHandlerPlayClient.applyS45PacketTitle(this);
   }

   public IChatComponent getIChatComponent() {
      return this.iChatComponent;
   }

   @Override
   public void processAction(PacketBuffer packet) throws IOException {
      this.movementFilterProperty = (MovementFilterProperty)packet.getEnum(MovementFilterProperty.class);
      if (this.movementFilterProperty == MovementFilterProperty.TITLE || this.movementFilterProperty == MovementFilterProperty.SUBTITLE) {
         this.iChatComponent = packet.getChatComponent();
      }

      if (this.movementFilterProperty == MovementFilterProperty.TIMES) {
         this.count2 = packet.readInt();
         this.count = packet.readInt();
         this.count3 = packet.readInt();
      }
   }

   public int getCount2() {
      return this.count2;
   }

   @Override
   public void processAction(INetHandler iNetHandler) {
      this.processPacket((INetHandlerPlayClient)iNetHandler);
   }

   public MovementFilterProperty getMovementFilterProperty() {
      return this.movementFilterProperty;
   }

   public int getCount3() {
      return this.count3;
   }

   @Override
   public void processAction2(PacketBuffer packet) throws IOException {
      packet.applyEnum(this.movementFilterProperty);
      if (this.movementFilterProperty == MovementFilterProperty.TITLE || this.movementFilterProperty == MovementFilterProperty.SUBTITLE) {
         packet.setChatComponent(this.iChatComponent);
      }

      if (this.movementFilterProperty == MovementFilterProperty.TIMES) {
         packet.writeInt(this.count2);
         packet.writeInt(this.count);
         packet.writeInt(this.count3);
      }
   }

   public int getCount() {
      return this.count;
   }

   public S45PacketTitle(MovementFilterProperty movementFilterProperty2, IChatComponent iChatComponent2, int count4, int count5, int count6) {
      this.movementFilterProperty = movementFilterProperty2;
      this.iChatComponent = iChatComponent2;
      this.count2 = count4;
      this.count = count5;
      this.count3 = count6;
   }

   public S45PacketTitle(MovementFilterProperty movementFilterProperty2, IChatComponent iChatComponent2) {
      this(movementFilterProperty2, iChatComponent2, -1, -1, -1);
   }

   public S45PacketTitle() {
   }

   public S45PacketTitle(int count4, int count5, int count6) {
      this(MovementFilterProperty.TIMES, (IChatComponent)null, count4, count5, count6);
   }
}
