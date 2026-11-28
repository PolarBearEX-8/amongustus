package net.minecraft.network.play.server;

import java.io.IOException;
import net.minecraft.network.INetHandler;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;
import net.minecraft.util.IChatComponent;

public class S45PacketTitle implements Packet {
   private S45PacketTitle.Type type;
   private IChatComponent message;
   private int fadeInTime;
   private int displayTime;
   private int fadeOutTime;

   public S45PacketTitle.Type getType() {
      return this.type;
   }

   public S45PacketTitle(int n, int n2, int n3) {
      this(S45PacketTitle.Type.TIMES, (IChatComponent)null, n, n2, n3);
   }

   public S45PacketTitle(S45PacketTitle.Type type2, IChatComponent component) {
      this(type2, component, -1, -1, -1);
   }

   public S45PacketTitle() {
   }

   public void processPacket(INetHandlerPlayClient handler) {
      handler.handleTitle(this);
   }

   @Override
   public void readPacketData(PacketBuffer buf) throws IOException {
      this.type = (S45PacketTitle.Type)buf.readEnumValue(S45PacketTitle.Type.class);
      if (this.type == S45PacketTitle.Type.TITLE || this.type == S45PacketTitle.Type.SUBTITLE) {
         this.message = buf.readChatComponent();
      }

      if (this.type == S45PacketTitle.Type.TIMES) {
         this.fadeInTime = buf.readInt();
         this.displayTime = buf.readInt();
         this.fadeOutTime = buf.readInt();
      }
   }

   public S45PacketTitle(S45PacketTitle.Type type2, IChatComponent component, int n, int n2, int n3) {
      this.type = type2;
      this.message = component;
      this.fadeInTime = n;
      this.displayTime = n2;
      this.fadeOutTime = n3;
   }

   public int getDisplayTime() {
      return this.displayTime;
   }

   public int getFadeInTime() {
      return this.fadeInTime;
   }

   @Override
   public void processPacket(INetHandler handler) {
      this.processPacket((INetHandlerPlayClient)handler);
   }

   @Override
   public void writePacketData(PacketBuffer buf) throws IOException {
      buf.writeEnumValue(this.type);
      if (this.type == S45PacketTitle.Type.TITLE || this.type == S45PacketTitle.Type.SUBTITLE) {
         buf.writeChatComponent(this.message);
      }

      if (this.type == S45PacketTitle.Type.TIMES) {
         buf.writeInt(this.fadeInTime);
         buf.writeInt(this.displayTime);
         buf.writeInt(this.fadeOutTime);
      }
   }

   public IChatComponent getMessage() {
      return this.message;
   }

   public int getFadeOutTime() {
      return this.fadeOutTime;
   }


   public enum Type {
      TITLE,
      SUBTITLE,
      TIMES,
      CLEAR,
      RESET;

   public static String[] getNames() {
         String[] strings = new String[values().length];
         int n = 0;

         for (Type type : values()) {
            strings[n++] = type.name().toLowerCase();
         }

         return strings;
      }

      public static Type byName(String str) {
         for (Type type : values()) {
            if (type.name().equalsIgnoreCase(str)) {
               return type;
            }
         }

         return TITLE;
      }
   }
}
