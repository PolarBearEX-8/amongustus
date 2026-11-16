package client.network;

import client.util.BoldJsonSerializer;
import client.util.DampenerDeserializer;
import client.util.GameProfileArrayHolder;
import client.util.IChatComponentEntry;
import client.util.INetHandler;
import client.util.MaxJsonDeserializer;
import client.util.NameJsonSerializer;
import client.util.Packet;
import client.util.PacketBuffer;
import client.util.SmoothCalculator;
import client.util.StatusJsonDeserializer;
import client.util.StyleHasParentManager;
import client.util.TypeAdapterFactoryAdapter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.IOException;
import net.minecraft.util.ChatComponentSerializer;
import net.minecraft.util.IChatComponent;

public class CustomDataPacket implements Packet {
   public static final Gson gson = new GsonBuilder()
      .registerTypeAdapter(DampenerDeserializer.class, new NameJsonSerializer())
      .registerTypeAdapter(GameProfileArrayHolder.class, new MaxJsonDeserializer())
      .registerTypeAdapter(IChatComponentEntry.class, new StatusJsonDeserializer())
      .registerTypeHierarchyAdapter(IChatComponent.class, new ChatComponentSerializer())
      .registerTypeHierarchyAdapter(StyleHasParentManager.class, new BoldJsonSerializer())
      .registerTypeAdapterFactory(new TypeAdapterFactoryAdapter())
      .create();
   public IChatComponentEntry iChatComponentEntry;

   @Override
   public void processAction(PacketBuffer packet) throws IOException {
      this.iChatComponentEntry = (IChatComponentEntry)gson.fromJson(packet.resolveTextByIndex(32767), IChatComponentEntry.class);
   }

   @Override
   public void processAction(INetHandler iNetHandler) {
      this.applySmoothCalculator((SmoothCalculator)iNetHandler);
   }

   public void applySmoothCalculator(SmoothCalculator smoothCalculator) {
      smoothCalculator.applyCustomDataPacket(this);
   }

   @Override
   public void processAction2(PacketBuffer packet) throws IOException {
      packet.getPacketBuffer(gson.toJson(this.iChatComponentEntry));
   }

   public IChatComponentEntry getIChatComponentEntry() {
      return this.iChatComponentEntry;
   }

   public CustomDataPacket(IChatComponentEntry iChatComponentEntry2) {
      this.iChatComponentEntry = iChatComponentEntry2;
   }

   public CustomDataPacket() {
   }
}
