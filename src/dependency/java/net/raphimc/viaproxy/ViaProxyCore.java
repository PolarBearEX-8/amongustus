package net.raphimc.viaproxy;

import client.gui.DefaultGuiScreenAdapter;
import client.setting.CombatKeybindProperty;
import client.util.CustomPacketAdapter;
import client.util.Packet;
import client.util.PacketBuffer;
import client.util.PacketBufferHelper;
import client.util.SpecialPacketAdapter;
import client.util.StandardPacketAdapter;
import io.netty.buffer.Unpooled;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Base64;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.client.C17PacketCustomPayload;
import net.minecraft.network.play.server.S3FPacketCustomPayload;

public class ViaProxyCore {
   public static ViaProxyCore viaProxyCore = new ViaProxyCore();
   public final Minecraft mc = Minecraft.getMinecraft();
   public NetworkManager networkManager;
   public ByteArrayOutputStream byteArrayOutputStream = null;

   public boolean matchesText(String text) throws IOException {
      String[] textArray = text.split("\n");
      if (textArray.length == 3 && Arrays.equals(textArray[2].getBytes(), OamDataRecord.byteFlagArray)) {
         VarIntTooDataInputStream varIntTooDataInputStream = new VarIntTooDataInputStream(new ByteArrayInputStream(Base64.getDecoder().decode(textArray[1])));
         return this.matchesText(varIntTooDataInputStream.resolveText(20), varIntTooDataInputStream.getCount(), varIntTooDataInputStream);
      } else {
         return false;
      }
   }

   private void applyTooBigDataOutputStream(
      TooBigDataOutputStream tooBigDataOutputStream, String text, int count, ByteArrayOutputStream byteArrayOutputStream2, GuiScreen screen, boolean flag, int count2
   ) {
      try {
         if (flag) {
            tooBigDataOutputStream.writeBoolean(this.matchesTextByText(text));
            this.applyByteArray(count, byteArrayOutputStream2.toByteArray());
         } else {
            tooBigDataOutputStream.writeBoolean(false);
            this.applyByteArray(count, byteArrayOutputStream2.toByteArray());
         }
      } catch (Throwable throwable) {
         throwable.printStackTrace();
         this.networkManager.channel.close();
      }

      this.mc.displayGuiScreen(screen);
   }

   public boolean matchesText(String text, int count, VarIntTooDataInputStream varIntTooDataInputStream) throws IOException {
      if (text.equals("oam:join")) {
         String text2 = varIntTooDataInputStream.resolveText(64);
         this.applyText(text2, count);
         return true;
      } else {
         return false;
      }
   }

   public boolean matchesText(String text, byte[] byteValueArray) throws IOException {
      if (text.startsWith("oam:")) {
         VarIntTooDataInputStream varIntTooDataInputStream = new VarIntTooDataInputStream(new ByteArrayInputStream(byteValueArray));
         return this.matchesText(text, varIntTooDataInputStream.getCount(), varIntTooDataInputStream);
      } else {
         return false;
      }
   }

   private void applyText(String text, int count) {
      ByteArrayOutputStream byteArrayOutputStream2 = new ByteArrayOutputStream();
      TooBigDataOutputStream tooBigDataOutputStream = new TooBigDataOutputStream(byteArrayOutputStream2);
      if (((CombatKeybindProperty)this.networkManager.channel.attr(NetworkManager.attributeKey).get()).getCount3() == 2
         && this.networkManager.channel.remoteAddress() instanceof InetSocketAddress
         && ((InetSocketAddress)this.networkManager.channel.remoteAddress()).getAddress().isLoopbackAddress()) {
         try {
            tooBigDataOutputStream.writeBoolean(this.matchesTextByText(text));
            this.applyByteArray(count, byteArrayOutputStream2.toByteArray());
         } catch (Exception exception) {
            exception.printStackTrace();
            this.networkManager.channel.close();
         }
      } else {
         Minecraft mc2 = this.mc;
         Runnable task = () -> this.applyTooBigDataOutputStream(tooBigDataOutputStream, text, count, byteArrayOutputStream2);
         mc2.resolveListenableFuture(task);
      }
   }

   public boolean matchesNetworkManager(NetworkManager networkManager2, Packet packet) throws IOException {
      this.networkManager = networkManager2;
      if (packet instanceof S3FPacketCustomPayload) {
         S3FPacketCustomPayload packet2 = (S3FPacketCustomPayload)packet;
         String labelText = packet2.getLabelText();
         PacketBuffer packetBuffer = packet2.getPacketBuffer();
         packetBuffer.markReaderIndex();
         byte[] byteValueArray = new byte[packetBuffer.readableBytes()];
         packetBuffer.readBytes(byteValueArray);
         packetBuffer.resetReaderIndex();
         return this.matchesText(labelText, byteValueArray);
      } else if (packet instanceof SpecialPacketAdapter) {
         SpecialPacketAdapter packet3 = (SpecialPacketAdapter)packet;
         return this.matchesText(packet3.getIChatComponent().getString());
      } else if (packet instanceof StandardPacketAdapter) {
         StandardPacketAdapter packet4 = (StandardPacketAdapter)packet;
         return this.checkIndex(packet4.getCount());
      } else {
         return false;
      }
   }

   private void applyTooBigDataOutputStream(TooBigDataOutputStream tooBigDataOutputStream, String text, int count, ByteArrayOutputStream byteArrayOutputStream2) {
      GuiScreen currentScreen = this.mc.currentScreen;
      Minecraft mc2 = this.mc;
      DefaultGuiScreenAdapter defaultGuiScreenAdapter = new DefaultGuiScreenAdapter(
         (confirmed, id) -> this.applyTooBigDataOutputStream(tooBigDataOutputStream, text, count, byteArrayOutputStream2, currentScreen, confirmed, id),
         "Allow Open Auth Mod authentication?",
         "This will allow the proxy to authenticate as you on a Minecraft Server.",
         0
      );
      mc2.displayGuiScreen(defaultGuiScreenAdapter);
   }

   public boolean checkIndex(int count) throws IOException {
      if (count < 0) {
         if (this.byteArrayOutputStream == null && count == OamDataRecord.count) {
            this.byteArrayOutputStream = new ByteArrayOutputStream();
            return true;
         }

         if (this.byteArrayOutputStream != null && count == OamDataRecord.count) {
            VarIntTooDataInputStream varIntTooDataInputStream = new VarIntTooDataInputStream(new ByteArrayInputStream(this.byteArrayOutputStream.toByteArray()));
            this.byteArrayOutputStream = null;
            this.matchesText(varIntTooDataInputStream.resolveText(20), varIntTooDataInputStream.getCount(), varIntTooDataInputStream);
            return true;
         }

         if (this.byteArrayOutputStream != null) {
            this.byteArrayOutputStream.write(Helper.resolveByteArray(count));
            return true;
         }
      }

      return false;
   }

   public boolean matchesTextByText(String text) {
      try {
         this.mc.getMinecraftSessionService().joinServer(this.mc.getSession().getGameProfile(), this.mc.getSession().getText2(), text);
         return true;
      } catch (Throwable throwable) {
         return false;
      }
   }

   public void applyByteArray(int count, byte[] byteValueArray) {
      try {
         if (((CombatKeybindProperty)this.networkManager.channel.attr(NetworkManager.attributeKey).get()).getCount3() == 0) {
            PacketBuffer packet = new PacketBuffer(Unpooled.buffer());
            PacketBufferHelper.resolvePacketBuffer(packet, count);
            packet.writeBytes(byteValueArray);
            C17PacketCustomPayload packet2 = new C17PacketCustomPayload("oam:data", packet);
            this.networkManager.handlePacket6(packet2);
         } else {
            PacketBuffer packet3 = new PacketBuffer(Unpooled.buffer());
            PacketBufferHelper.resolvePacketBuffer(packet3, count);
            packet3.writeBytes(byteValueArray);
            byte[] byteValueArray2 = new byte[packet3.readableBytes()];
            packet3.readBytes(byteValueArray2);
            packet3.release();
            CustomPacketAdapter packet4 = new CustomPacketAdapter();
            packet4.byteFlagArray2 = byteValueArray2;
            packet4.byteFlagArray = "oam:data".getBytes(StandardCharsets.UTF_8);
            this.networkManager.handlePacket6(packet4);
         }
      } catch (Throwable throwable) {
         throwable.printStackTrace();
         this.networkManager.channel.close();
      }
   }
}
