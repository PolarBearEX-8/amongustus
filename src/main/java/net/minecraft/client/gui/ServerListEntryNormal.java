package net.minecraft.client.gui;

import com.google.common.base.Charsets;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufInputStream;
import io.netty.buffer.Unpooled;
import io.netty.handler.codec.base64.Base64;
import java.awt.image.BufferedImage;
import java.net.UnknownHostException;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.function.Consumer;
import net.augustus.ui.multiplayer.ServerListHost;
import net.augustus.Augustus;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ServerData;
import net.minecraft.client.multiplayer.ServerList;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.client.renderer.texture.TextureUtil;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.ResourceLocation;
import org.apache.commons.lang3.Validate;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ServerListEntryNormal implements GuiListExtended.IGuiListEntry {
   private static final Logger LOGGER = LogManager.getLogger();
   private static final ThreadPoolExecutor EXECUTOR = new ScheduledThreadPoolExecutor(
      5, new ThreadFactoryBuilder().setNameFormat("Server Pinger #%d").setDaemon(true).build()
   );
   private static final ResourceLocation UNKNOWN_SERVER = new ResourceLocation("textures/misc/unknown_server.png");
   private static final ResourceLocation SERVER_SELECTION_BUTTONS = new ResourceLocation("textures/gui/server_selection.png");
   private final ServerListHost owner;
   private final Minecraft mc;
   private final ServerData serverData;
   private final ResourceLocation serverIcon;
   private String lastIconB64;
   private DynamicTexture icon;
   private long lastClickTime;
   private Consumer onPingComplete;

   public ServerData getServerData() {
      return this.serverData;
   }

   public void setOnPingComplete(Consumer consumer) {
      this.onPingComplete = consumer;
   }

   public ServerListEntryNormal(ServerListHost serverListHost, ServerData serverData2) {
      this.owner = serverListHost;
      this.serverData = serverData2;
      this.mc = Minecraft.getMinecraft();
      this.serverIcon = new ResourceLocation("servers/" + serverData2.serverIP + "/icon");
      this.icon = (DynamicTexture)this.mc.getTextureManager().getTexture(this.serverIcon);
   }

   @Override
   public void mouseReleased(int n, int n2, int n3, int n4, int n5, int n6) {
   }

   private ResourceLocation getAccountStatusIcon(String str) {
      return Minecraft.getMinecraft().getSession().getAccountEntry().isBannedOnServer(str)
         ? GuiMultiplayer.AUGUSTUS_TEXTURE2
         : (Minecraft.getMinecraft().getSession().getAccountEntry().isUnbannedOnServer(str) ? GuiMultiplayer.AUGUSTUS_TEXTURE : GuiMultiplayer.AUGUSTUS_TEXTURE3);
   }

   private boolean canJoin() {
      return true;
   }

   @Override
   public void setSelected(int n, int n2, int n3) {
   }

   private void prepareServerIcon() {
      if (this.serverData.getBase64EncodedIconData() == null) {
         this.mc.getTextureManager().deleteTexture(this.serverIcon);
         this.icon = null;
      } else {
         ByteBuf buf = Unpooled.copiedBuffer(this.serverData.getBase64EncodedIconData(), Charsets.UTF_8);
         ByteBuf buf2 = Base64.decode(buf);

         BufferedImage image;
         try {
            image = TextureUtil.readBufferedImage(new ByteBufInputStream(buf2));
            Validate.validState(image.getWidth() == 64, "Must be 64 pixels wide", new Object[0]);
            Validate.validState(image.getHeight() == 64, "Must be 64 pixels high", new Object[0]);
         } catch (Throwable e) {
            LOGGER.error("Invalid icon for server " + this.serverData.serverName + " (" + this.serverData.serverIP + ")", e);
            this.serverData.setBase64EncodedIconData((String)null);
            return;
         } finally {
            buf.release();
            buf2.release();
         }

         if (this.icon == null) {
            this.icon = new DynamicTexture(image.getWidth(), image.getHeight());
            this.mc.getTextureManager().loadTexture(this.serverIcon, this.icon);
         }

         try {
            image.getRGB(0, 0, image.getWidth(), image.getHeight(), this.icon.getTextureData(), 0, image.getWidth());
         } catch (Throwable e2) {
         }

         this.icon.updateDynamicTexture();
      }
   }

   @Override
   public void drawEntry(int n, int n2, int n3, int n4, int n5, int n6, int n7, boolean flag) {
      if (!this.serverData.pinged) {
         this.serverData.pinged = true;
         this.serverData.pingToServer = -2L;
         this.serverData.serverMOTD = "";
         this.serverData.populationInfo = "";
         if (Augustus.getInstance().getModuleManager().clientSettings.fastServerPining1.getValue()) {
            CompletableFuture.runAsync(() -> {
               try {
                  this.owner.getOldServerPinger().ping(this.serverData);
               } catch (UnknownHostException e) {
                  this.serverData.pingToServer = -1L;
                  this.serverData.serverMOTD = EnumChatFormatting.DARK_RED + "Can't resolve hostname";
               } catch (Exception e2) {
                  this.serverData.pingToServer = -1L;
                  this.serverData.serverMOTD = EnumChatFormatting.DARK_RED + "Can't connect to server.";
               }
            });
         } else {
            EXECUTOR.submit(() -> {
               try {
                  this.owner.getOldServerPinger().ping(this.serverData);
               } catch (UnknownHostException e3) {
                  this.serverData.pingToServer = -1L;
                  this.serverData.serverMOTD = EnumChatFormatting.DARK_RED + "Can't resolve hostname";
               } catch (Exception e4) {
                  this.serverData.pingToServer = -1L;
                  this.serverData.serverMOTD = EnumChatFormatting.DARK_RED + "Can't connect to server.";
               }
            });
         }
      }

      boolean flag2 = this.serverData.version > 47;
      boolean flag3 = this.serverData.version < 47;
      boolean flag4 = flag2 || flag3;
      this.mc.fontRendererObj.drawString(this.serverData.serverName, n2 + 32 + 3, n3 + 1, 16777215);
      List list = this.mc.fontRendererObj.listFormattedStringToWidth(this.serverData.serverMOTD, n4 - 32 - 2);

      for (int i = 0; i < Math.min(list.size(), 2); i++) {
         this.mc.fontRendererObj.drawString((String)list.get(i), n2 + 32 + 3, n3 + 12 + this.mc.fontRendererObj.FONT_HEIGHT * i, 8421504);
      }

      String str = flag4 ? EnumChatFormatting.DARK_RED + (this.serverData.gameVersion == null ? "1.8.8" : this.serverData.gameVersion) : this.serverData.populationInfo;
      int stringWidth = this.mc.fontRendererObj.getStringWidth(str);
      this.mc.fontRendererObj.drawString(str, n2 + n4 - stringWidth - 15 - 2, n3 + 1, 8421504);
      byte b = 0;
      String str2 = null;
      int n8;
      String str3;
      if (flag4) {
         n8 = 5;
         str3 = flag2 ? "Client out of date!" : "Server out of date!";
         str2 = this.serverData.playerList;
      } else if (this.serverData.pinged && this.serverData.pingToServer != -2L) {
         if (this.serverData.pingToServer < 0L) {
            n8 = 5;
         } else if (this.serverData.pingToServer < 150L) {
            n8 = 0;
         } else if (this.serverData.pingToServer < 300L) {
            n8 = 1;
         } else if (this.serverData.pingToServer < 600L) {
            n8 = 2;
         } else if (this.serverData.pingToServer < 1000L) {
            n8 = 3;
         } else {
            n8 = 4;
         }

         if (this.serverData.pingToServer < 0L) {
            str3 = "(no connection)";
         } else {
            str3 = this.serverData.pingToServer + "ms";
            str2 = this.serverData.playerList;
         }
      } else {
         b = 1;
         n8 = (int)(Minecraft.getSystemTime() / 100L + (long)(n * 2) & 7L);
         if (n8 > 4) {
            n8 = 8 - n8;
         }

         str3 = "Pinging...";
      }

      GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
      this.mc.getTextureManager().bindTexture(Gui.icons);
      Gui.drawModalRectWithCustomSizedTexture(n2 + n4 - 15, n3, (float)(b * 10), (float)(176 + n8 * 8), 10, 8, 256.0F, 256.0F);
      if (this.serverData.getBase64EncodedIconData() != null && !this.serverData.getBase64EncodedIconData().equals(this.lastIconB64)) {
         this.lastIconB64 = this.serverData.getBase64EncodedIconData();
         this.prepareServerIcon();
         ServerList serverList = this.owner.getServerList();
         if (serverList != null) {
            serverList.saveServerList();
         }
      }

      if (this.icon != null) {
         this.drawTextureAt(n2, n3, 30.0F, 30.0F, this.serverIcon);
      } else {
         this.drawTextureAt(n2, n3, 30.0F, 30.0F, UNKNOWN_SERVER);
      }

      int n9 = n6 - n2;
      int n10 = n7 - n3;
      if (n9 >= n4 - 15 && n9 <= n4 - 5 && n10 >= 0 && n10 <= 8) {
         if (this.serverData.gameVersion != null && !flag4) {
            str3 = str3 + "\n" + this.serverData.gameVersion;
         }

         this.owner.setHoveringText(str3);
      } else if (n9 >= n4 - stringWidth - 15 - 2 && n9 <= n4 - 15 - 2 && n10 >= 0 && n10 <= 8) {
         this.owner.setHoveringText(str2);
      }

      if (this.mc.gameSettings.touchscreen || flag) {
         this.mc.getTextureManager().bindTexture(SERVER_SELECTION_BUTTONS);
         Gui.drawRect(n2, n3, n2 + 32, n3 + 32, -1601138544);
         GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
         int n11 = n6 - n2;
         int n12 = n7 - n3;
         if (this.canJoin()) {
            if (n11 < 32 && n11 > 16) {
               Gui.drawModalRectWithCustomSizedTexture(n2, n3, 0.0F, 32.0F, 32, 32, 256.0F, 256.0F);
            } else {
               Gui.drawModalRectWithCustomSizedTexture(n2, n3, 0.0F, 0.0F, 32, 32, 256.0F, 256.0F);
            }
         }

         if (this.owner.canMoveUp(this, n)) {
            if (n11 < 16 && n12 < 16) {
               Gui.drawModalRectWithCustomSizedTexture(n2, n3, 96.0F, 32.0F, 32, 32, 256.0F, 256.0F);
            } else {
               Gui.drawModalRectWithCustomSizedTexture(n2, n3, 96.0F, 0.0F, 32, 32, 256.0F, 256.0F);
            }
         }

         if (this.owner.canMoveDown(this, n)) {
            if (n11 < 16 && n12 > 16) {
               Gui.drawModalRectWithCustomSizedTexture(n2, n3, 64.0F, 32.0F, 32, 32, 256.0F, 256.0F);
            } else {
               Gui.drawModalRectWithCustomSizedTexture(n2, n3, 64.0F, 0.0F, 32, 32, 256.0F, 256.0F);
            }
         }
      }

      if (this.onPingComplete != null && !this.serverData.populationInfo.isEmpty()) {
         this.onPingComplete.accept(this.serverData);
         this.onPingComplete = null;
      }

      if (Minecraft.getMinecraft() != null
         && Minecraft.getMinecraft().getSession() != null
         && this.serverData != null
         && Minecraft.getMinecraft().getSession().getAccountEntry() != null) {
         switch (Augustus.getInstance().getModuleManager().clientSettings.banStatistics.getSelectedValue()) {
            case "Icon":
               GlStateManager.color(1.0F, 1.0F, 1.0F, 0.6F);
               this.drawTextureAt(n2 + 4, n3 + 4, 22.0F, 22.0F, this.getAccountStatusIcon(this.serverData.serverIP));
               break;
            case "Left":
               GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
               this.drawTextureAt(n2 - 31, n3 + 4, 22.0F, 22.0F, this.getAccountStatusIcon(this.serverData.serverIP));
               break;
            case "Right":
               GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
               this.drawTextureAt(n2 + 314, n3 + 4, 22.0F, 22.0F, this.getAccountStatusIcon(this.serverData.serverIP));
         }
      }
   }

   @Override
   public boolean mousePressed(int n, int n2, int n3, int n4, int n5, int n6) {
      if (n5 <= 32) {
         if (n5 < 32 && n5 > 16 && this.canJoin()) {
            this.owner.selectServer(n);
            this.owner.connectToSelected();
            return true;
         }

         if (n5 < 16 && n6 < 16 && this.owner.canMoveUp(this, n)) {
            this.owner.moveServerUp(this, n, GuiScreen.isShiftKeyDown());
            return true;
         }

         if (n5 < 16 && n6 > 16 && this.owner.canMoveDown(this, n)) {
            this.owner.moveServerDown(this, n, GuiScreen.isShiftKeyDown());
            return true;
         }
      }

      this.owner.selectServer(n);
      if (Minecraft.getSystemTime() - this.lastClickTime < 250L) {
         this.owner.connectToSelected();
      }

      this.lastClickTime = Minecraft.getSystemTime();
      return false;
   }

   public void drawTextureAt(int n, int n2, float f, float f2, ResourceLocation location) {
      this.mc.getTextureManager().bindTexture(location);
      GlStateManager.enableBlend();
      Gui.drawModalRectWithCustomSizedTexture(n, n2, 0.0F, 0.0F, (int)f, (int)f2, f, f2);
      GlStateManager.disableBlend();
   }

}
