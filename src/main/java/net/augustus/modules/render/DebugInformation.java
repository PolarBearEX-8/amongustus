package net.augustus.modules.render;

import io.netty.channel.ChannelHandler;
import java.awt.Color;
import java.text.DateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import net.augustus.modules.Categorys;
import net.augustus.modules.Module;
import net.augustus.events.EventPacket;
import net.augustus.events.listeners.RenderListener;
import net.augustus.utils.network.NetworkStatsTrafficHandler;
import net.augustus.ClientManagers;
import net.augustus.settings.MultiOptionSetting;
import net.augustus.settings.ColorSetting;
import net.augustus.utils.TuplePair;
import net.augustus.utils.network.ServerPingUtil;
import net.augustus.render.RenderUtil;
import net.augustus.utils.ByteSizeFormatter;
import net.augustus.viaversion.packet.C0APacketSwingHand;
import net.augustus.events.listeners.TickListener;
import net.augustus.events.listeners.PacketListener;
import net.augustus.settings.options.DebugInfoElementOption;
import net.augustus.settings.BooleanValue;
import net.augustus.settings.DoubleValue;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.C03PacketPlayer;
import net.minecraft.network.play.client.C08PacketPlayerBlockPlacement;
import net.minecraft.network.play.client.C0APacketAnimation;
import net.minecraft.util.MathHelper;
import org.lwjgl.opengl.GL11;

public class DebugInformation extends Module implements RenderListener, PacketListener, TickListener {
   private final MultiOptionSetting debugInformation;
   private final DoubleValue scale;
   private final DoubleValue posX;
   private final DoubleValue posY;
   private final BooleanValue fontShadow;
   private final ColorSetting leftColor;
   private final ColorSetting rightColor;
   private final GregorianCalendar calendar;
   private final java.util.ArrayList clicksPerTickHistory;
   private final java.util.ArrayList speedHistory;
   private int clicksThisTick;
   public int cps;
   private double lastPosX;
   private double lastPosZ;
   public float blocksPerSecond;

   public DebugInformation() {
       super("DebugInformation", "DebugInformation", new Color(235, 122, 52), Categorys.RENDER);
      this.debugInformation = new MultiOptionSetting(this, "Debug information", DebugInfoElementOption.values());
      this.scale = new DoubleValue(this, "Scale", 2.0, 0.5, 4.0, 2);
      this.posX = new DoubleValue(this, "PosX", 0.0, 0.0, 100.0, 2);
      this.posY = new DoubleValue(this, "PosY", 0.0, 0.0, 100.0, 2);
      this.fontShadow = new BooleanValue(this, "FontShadow", true);
      this.leftColor = new ColorSetting(this, "LeftColor", Color.white);
      this.rightColor = new ColorSetting(this, "RightColor", Color.lightGray);
      this.calendar = new GregorianCalendar();
      this.clicksPerTickHistory = new java.util.ArrayList();
      this.speedHistory = new java.util.ArrayList();
      this.clicksThisTick = 0;
      this.cps = 0;
   }

   public void onEnable() {
   }

   public void onDisable() {
   }

   public void updateEventSubscriptions() {
      this.toggleEventSubscription(32);
      this.toggleEventSubscription(26);
      this.toggleEventSubscription(40);
   }

   public void onTick() {
      this.clicksPerTickHistory.add(this.clicksThisTick);

      while (this.clicksPerTickHistory.size() > 20) {
         this.clicksPerTickHistory.remove(0);
      }

      this.cps = 0;

      for (int n : (Iterable<Integer>)(this.clicksPerTickHistory)) {
         this.cps += n;
      }

      this.clicksThisTick = 0;
   }

   public void onPacketSend(EventPacket event) {
      Packet packet = event.getPacket();
      if ((packet instanceof C0APacketAnimation || packet instanceof C0APacketSwingHand || packet instanceof C08PacketPlayerBlockPlacement)
         && !mc.playerController.isHittingBlock()
         && (!(packet instanceof C08PacketPlayerBlockPlacement) || ((C08PacketPlayerBlockPlacement)packet).getPlacedBlockDirection() == 255)) {
         this.clicksThisTick++;
      }

      if (packet instanceof C03PacketPlayer) {
         if (!(packet instanceof C03PacketPlayer.C04PacketPlayerPosition) && !(packet instanceof C03PacketPlayer.C06PacketPlayerPosLook)) {
            this.updateBlocksPerSecond(0.0F);
         } else {
            C03PacketPlayer packet2 = (C03PacketPlayer)packet;
            double d = Math.abs(packet2.getPositionX() - this.lastPosX);
            this.updateBlocksPerSecond((float)(Math.hypot(Math.abs(packet2.getPositionZ() - this.lastPosZ), d) * 20.0));
            this.lastPosX = packet2.getPositionX();
            this.lastPosZ = packet2.getPositionZ();
         }
      }
   }

   public void onRender2DPost(float partialTicks, ScaledResolution sr) {
      GL11.glPushMatrix();
      GL11.glScaled((double)(1.0F / (float)sr.getScaleFactor()), (double)(1.0F / (float)sr.getScaleFactor()), 1.0);
      GL11.glTranslated(this.posX.getValue() / 100.0 * (double)mc.displayWidth, this.posY.getValue() / 100.0 * (double)mc.displayHeight, 0.0);
      GL11.glScaled(this.scale.getValue(), this.scale.getValue(), 1.0);
      this.renderDebugLines();
      GL11.glPopMatrix();
      RenderUtil.resetGlColorState();
   }

   private void updateBlocksPerSecond(float f) {
      this.speedHistory.add(f);

      while (this.speedHistory.size() > 5) {
         this.speedHistory.remove(0);
      }

      float f2 = 0.0F;

      for (float f3 : (Iterable<Float>)(this.speedHistory)) {
         f2 += f3;
      }

      this.blocksPerSecond = (float)((double)Math.round(f2 / (float)this.speedHistory.size() * 100.0F) / 100.0);
   }

   private void renderDebugLines() {
      java.util.ArrayList list = new java.util.ArrayList();
      if (DebugInfoElementOption.isOptionSelected(DebugInfoElementOption.FPS)) {
         String str = "FPS";
         list.add(new TuplePair(this.formatLabelWithWidth(str), Minecraft.getDebugFPS()));
      }

      if (DebugInfoElementOption.isOptionSelected(DebugInfoElementOption.PING)) {
         String str2 = "Ping";
         list.add(
            new TuplePair(
               this.formatLabelWithWidth(str2),
               ClientManagers.moduleManager.hotbar.otherPingCalculation.getValue()
                  ? ServerPingUtil.getPing()
                  : (
                     mc.getNetHandler() != null && mc.getNetHandler().getPlayerInfo(mc.thePlayer.getUniqueID()) != null
                        ? mc.getNetHandler().getPlayerInfo(mc.thePlayer.getUniqueID()).getResponseTime()
                        : 0
                  )
            )
         );
      }

      if (DebugInfoElementOption.isOptionSelected(DebugInfoElementOption.CPS)) {
         String str3 = "CPS";
         list.add(new TuplePair(this.formatLabelWithWidth(str3), this.cps));
      }

      if (DebugInfoElementOption.isOptionSelected(DebugInfoElementOption.POS_X)) {
         String str4 = "X";
         list.add(
            new TuplePair(
               this.formatLabelWithWidth(str4),
               ClientManagers.moduleManager.protector.isToggled()
                  ? MathHelper.floor_double(mc.thePlayer.posX) + ClientManagers.moduleManager.protector.getRandomXOffset()
                  : MathHelper.floor_double(mc.thePlayer.posX)
            )
         );
      }

      if (DebugInfoElementOption.isOptionSelected(DebugInfoElementOption.POS_Y)) {
         String str5 = "Y";
         list.add(new TuplePair(this.formatLabelWithWidth(str5), MathHelper.floor_double(mc.thePlayer.posY)));
      }

      if (DebugInfoElementOption.isOptionSelected(DebugInfoElementOption.POS_Z)) {
         String str6 = "Z";
         list.add(
            new TuplePair(
               this.formatLabelWithWidth(str6),
               ClientManagers.moduleManager.protector.isToggled()
                  ? MathHelper.floor_double(mc.thePlayer.posZ) + ClientManagers.moduleManager.protector.getRandomZOffset()
                  : MathHelper.floor_double(mc.thePlayer.posZ)
            )
         );
      }

      if (DebugInfoElementOption.isOptionSelected(DebugInfoElementOption.BPS)) {
         String str7 = "BPS";
         list.add(new TuplePair(this.formatLabelWithWidth(str7), this.blocksPerSecond));
      }

      ChannelHandler channelHandler = mc.getNetHandler().getNetworkManager().channel.pipeline().get(NetworkStatsTrafficHandler.class);
      if (channelHandler instanceof NetworkStatsTrafficHandler) {
         NetworkStatsTrafficHandler handler = (NetworkStatsTrafficHandler)channelHandler;
         if (DebugInfoElementOption.isOptionSelected(DebugInfoElementOption.RX)) {
            String str8 = "RX";
            list.add(new TuplePair(this.formatLabelWithWidth(str8), Math.round(handler.getIncomingPacketsPerSecond()) + "/s"));
         }

         if (DebugInfoElementOption.isOptionSelected(DebugInfoElementOption.TX)) {
            String str9 = "TX";
            list.add(new TuplePair(this.formatLabelWithWidth(str9), Math.round(handler.getOutgoingPacketsPerSecond()) + "/s"));
         }

         if (DebugInfoElementOption.isOptionSelected(DebugInfoElementOption.DOWNLOAD)) {
            String str10 = "Download";
            list.add(new TuplePair(this.formatLabelWithWidth(str10), ByteSizeFormatter.formatBytes(handler.getReadBytesPerSecond()) + "/s"));
         }

         if (DebugInfoElementOption.isOptionSelected(DebugInfoElementOption.UPLOAD)) {
            String str11 = "Upload";
            list.add(new TuplePair(this.formatLabelWithWidth(str11), ByteSizeFormatter.formatBytes(handler.getWriteBytesPerSecond()) + "/s"));
         }
      }

      Date date = null;
      if (DebugInfoElementOption.isOptionSelected(DebugInfoElementOption.DATE)) {
         String str12 = "Date";
         list.add(new TuplePair(this.formatLabelWithWidth(str12), DateFormat.getDateInstance(2).format(date = new Date())));
      }

      if (DebugInfoElementOption.isOptionSelected(DebugInfoElementOption.TIME)) {
         String str13 = "Time";
         list.add(new TuplePair(this.formatLabelWithWidth(str13), DateFormat.getTimeInstance(3).format(date == null ? new Date() : date)));
      }

      for (int i = 0; i < list.size(); i++) {
         TuplePair tuplePair = (TuplePair)list.get(i);
         TuplePair first = (TuplePair)tuplePair.getFirst();
         int n = i * mc.fontRendererObj.FONT_HEIGHT;
         String first2 = (String)first.getFirst();
         this.drawStyledString(first2, (byte)0, n, this.leftColor.getColorInt((byte)0));
         first2 = tuplePair.getSecond() + "";
         int second = (Integer)first.getSecond();
         this.drawStyledString(first2, second, n, this.rightColor.getColorInt((byte)0));
      }
   }

   private TuplePair formatLabelWithWidth(String str) {
      str = str + ": ";
      return new TuplePair(str, mc.fontRendererObj.getStringWidth(str));
   }

   private void drawStyledString(String str, int n, int n2, int n3) {
      if (this.fontShadow.getValue()) {
         mc.fontRendererObj.drawStringWithShadow(str, (float)n, (float)n2, n3);
      } else {
         mc.fontRendererObj.drawString(str, n, n2, n3);
      }
   }

}
