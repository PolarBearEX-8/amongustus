package client.event;

import client.Client;
import client.gui.TexturesGuiIconsPngManager;
import client.setting.ClientSettingsBlackType;
import client.util.ServersManager;
import client.util.SpecialLoggerEntry;
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
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiMultiplayer;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.multiplayer.ServerData;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.util.ResourceLocation;
import org.apache.commons.lang3.Validate;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CanTResolveHostnameListener implements PreUpdateDispatcher {
   public static final Logger logger = LogManager.getLogger();
   public static final ThreadPoolExecutor threadPoolExecutor = new ScheduledThreadPoolExecutor(
      5, new ThreadFactoryBuilder().setNameFormat("Server Pinger #%d").setDaemon(true).build()
   );
   public static final ResourceLocation resourceLocation3 = new ResourceLocation("textures/misc/unknown_server.png");
   public static final ResourceLocation resourceLocation2 = new ResourceLocation("textures/gui/server_selection.png");
   public final PostRenderInterceptor postRenderInterceptor;
   public final Minecraft mc;
   public final ServerData serverData;
   public final ResourceLocation resourceLocation;
   public String labelText;
   public DynamicTexture dynamicTexture;
   public long timestamp;
   public Consumer consumer;

   public void applyResourceLocation(int count, int count2, float factor, float factor2, ResourceLocation location) {
      this.mc.getTextureManager().applyResourceLocation3(location);
      GlStateManager.disableBlend();
      TexturesGuiIconsPngManager.applyIndex(count, count2, 0.0F, 0.0F, (int)factor, (int)factor2, factor, factor2);
      GlStateManager.enableBlend();
   }

   private void updateState2() {
      try {
         this.postRenderInterceptor.getCancelledManager().applyServerData(this.serverData);
      } catch (UnknownHostException unknownHostException) {
         this.serverData.timestamp = -1L;
         this.serverData.labelText = ClientSettingsBlackType.DARK_RED + "Can't resolve hostname";
      } catch (Exception exception) {
         this.serverData.timestamp = -1L;
         this.serverData.labelText = ClientSettingsBlackType.DARK_RED + "Can't connect to server.";
      }
   }

   @Override
   public void applyIndex(int count, int count2, int count3, int count4, int count5, int count6) {
   }

   @Override
   public void applyIndex(int count, int count2, int count3, int count4, int count5, int count6, int count7, boolean flag) {
      if (!this.serverData.enabled) {
         this.serverData.enabled = true;
         this.serverData.timestamp = -2L;
         this.serverData.labelText = "";
         this.serverData.labelText5 = "";
         if (Client.getClient().getModuleManager().clientSettings.settingFastServerPining1.isEnabled()) {
            CompletableFuture.runAsync(this::updateState2);
         } else {
            threadPoolExecutor.submit(this::updateState3);
         }
      }

      boolean flag2 = this.serverData.count > 47;
      boolean flag3 = this.serverData.count < 47;
      flag3 = flag2 || flag3;
      this.mc.fontRendererObj.getIndex(this.serverData.labelText3, count2 + 32 + 3, count3 + 1, 16777215);
      List list = this.mc.fontRendererObj.getList(this.serverData.labelText, count4 - 32 - 2);

      for (int index = 0; index < Math.min(list.size(), 2); index++) {
         this.mc.fontRendererObj.getIndex((String)list.get(index), count2 + 32 + 3, count3 + 12 + this.mc.fontRendererObj.indexOffset * index, 8421504);
      }

      String text = flag3
         ? ClientSettingsBlackType.DARK_RED + (this.serverData.labelText4 == null ? "1.8.8" : this.serverData.labelText4)
         : this.serverData.labelText5;
      int index2 = this.mc.fontRendererObj.getIndex(text);
      this.mc.fontRendererObj.getIndex(text, count2 + count4 - index2 - 15 - 2, count3 + 1, 8421504);
      int count8 = 0;
      String text2 = null;
      int count9;
      String text3;
      if (flag3) {
         count9 = 5;
         text3 = flag2 ? "Client out of date!" : "Server out of date!";
         text2 = this.serverData.labelText6;
      } else if (this.serverData.enabled && this.serverData.timestamp != -2L) {
         if (this.serverData.timestamp < 0L) {
            count9 = 5;
         } else if (this.serverData.timestamp < 150L) {
            count9 = 0;
         } else if (this.serverData.timestamp < 300L) {
            count9 = 1;
         } else if (this.serverData.timestamp < 600L) {
            count9 = 2;
         } else if (this.serverData.timestamp < 1000L) {
            count9 = 3;
         } else {
            count9 = 4;
         }

         if (this.serverData.timestamp < 0L) {
            text3 = "(no connection)";
         } else {
            text3 = this.serverData.timestamp + "ms";
            text2 = this.serverData.labelText6;
         }
      } else {
         count8 = 1;
         count9 = (int)(Minecraft.getTimestamp() / 100L + (long)(count * 2) & 7L);
         if (count9 > 4) {
            count9 = 8 - count9;
         }

         text3 = "Pinging...";
      }

      GlStateManager.color4f2(1.0F, 1.0F, 1.0F, 1.0F);
      this.mc.getTextureManager().applyResourceLocation3(TexturesGuiIconsPngManager.resourceLocation2);
      TexturesGuiIconsPngManager.applyIndex(count2 + count4 - 15, count3, (float)(count8 * 10), (float)(176 + count9 * 8), 10, 8, 256.0F, 256.0F);
      if (this.serverData.getLabelText2() != null && !this.serverData.getLabelText2().equals(this.labelText)) {
         this.labelText = this.serverData.getLabelText2();
         this.updateState();
         ServersManager serversManager = this.postRenderInterceptor.getServersManager();
         if (serversManager != null) {
            serversManager.updateState();
         }
      }

      if (this.dynamicTexture != null) {
         this.applyResourceLocation(count2, count3, 30.0F, 30.0F, this.resourceLocation);
      } else {
         this.applyResourceLocation(count2, count3, 30.0F, 30.0F, resourceLocation3);
      }

      count8 = count6 - count2;
      count9 = count7 - count3;
      if (count8 >= count4 - 15 && count8 <= count4 - 5 && count9 >= 0 && count9 <= 8) {
         if (this.serverData.labelText4 != null && !flag3) {
            text3 = text3 + "\n" + this.serverData.labelText4;
         }

         this.postRenderInterceptor.setLabelText(text3);
      } else if (count8 >= count4 - index2 - 15 - 2 && count8 <= count4 - 15 - 2 && count9 >= 0 && count9 <= 8) {
         this.postRenderInterceptor.setLabelText(text2);
      }

      if (this.mc.gameSettings.state48 || flag) {
         this.mc.getTextureManager().applyResourceLocation3(resourceLocation2);
         TexturesGuiIconsPngManager.processAction(count2, count3, count2 + 32, count3 + 32, -1601138544);
         GlStateManager.color4f2(1.0F, 1.0F, 1.0F, 1.0F);
         index2 = count6 - count2;
         count8 = count7 - count3;
         if (this.isActive()) {
            if (index2 < 32 && index2 > 16) {
               TexturesGuiIconsPngManager.applyIndex(count2, count3, 0.0F, 32.0F, 32, 32, 256.0F, 256.0F);
            } else {
               TexturesGuiIconsPngManager.applyIndex(count2, count3, 0.0F, 0.0F, 32, 32, 256.0F, 256.0F);
            }
         }

         if (this.postRenderInterceptor.matchesCanTResolveHostnameListene(this, count)) {
            if (index2 < 16 && count8 < 16) {
               TexturesGuiIconsPngManager.applyIndex(count2, count3, 96.0F, 32.0F, 32, 32, 256.0F, 256.0F);
            } else {
               TexturesGuiIconsPngManager.applyIndex(count2, count3, 96.0F, 0.0F, 32, 32, 256.0F, 256.0F);
            }
         }

         if (this.postRenderInterceptor.matchesCanTResolveHostnameListeneByCanTResolveHostn(this, count)) {
            if (index2 < 16 && count8 > 16) {
               TexturesGuiIconsPngManager.applyIndex(count2, count3, 64.0F, 32.0F, 32, 32, 256.0F, 256.0F);
            } else {
               TexturesGuiIconsPngManager.applyIndex(count2, count3, 64.0F, 0.0F, 32, 32, 256.0F, 256.0F);
            }
         }
      }

      if (this.consumer != null && !this.serverData.labelText5.isEmpty()) {
         this.consumer.accept(this.serverData);
         this.consumer = null;
      }

      if (Minecraft.getMinecraft() != null
         && Minecraft.getMinecraft().getSession() != null
         && this.serverData != null
         && Minecraft.getMinecraft().getSession().getKeybindAnimationEntry() != null) {
         String selected = Client.getClient().getModuleManager().clientSettings.settingBanStatistics.getSelected();
         switch (selected) {
            case "Icon":
               GlStateManager.color4f2(1.0F, 1.0F, 1.0F, 0.6F);
               this.applyResourceLocation(count2 + 4, count3 + 4, 22.0F, 22.0F, this.resolveResourceLocation(this.serverData.text));
               break;
            case "Left":
               GlStateManager.color4f2(1.0F, 1.0F, 1.0F, 1.0F);
               this.applyResourceLocation(count2 - 31, count3 + 4, 22.0F, 22.0F, this.resolveResourceLocation(this.serverData.text));
               break;
            case "Right":
               GlStateManager.color4f2(1.0F, 1.0F, 1.0F, 1.0F);
               this.applyResourceLocation(count2 + 314, count3 + 4, 22.0F, 22.0F, this.resolveResourceLocation(this.serverData.text));
         }
      }
   }

   public ServerData getServerData() {
      return this.serverData;
   }

   public void setConsumer(Consumer consumer2) {
      this.consumer = consumer2;
   }

   @Override
   public boolean checkIndex(int count, int count2, int count3, int count4, int count5, int count6) {
      if (count5 <= 32) {
         if (count5 < 32 && count5 > 16 && this.isActive()) {
            this.postRenderInterceptor.applyIndex(count);
            this.postRenderInterceptor.renderElement();
            return true;
         }

         if (count5 < 16 && count6 < 16 && this.postRenderInterceptor.matchesCanTResolveHostnameListene(this, count)) {
            this.postRenderInterceptor.applyCanTResolveHostnameListene(this, count, GuiScreen.isActiveAsFlag());
            return true;
         }

         if (count5 < 16 && count6 > 16 && this.postRenderInterceptor.matchesCanTResolveHostnameListeneByCanTResolveHostn(this, count)) {
            this.postRenderInterceptor.applyCanTResolveHostnameListeneByCanTResolveHostn(this, count, GuiScreen.isActiveAsFlag());
            return true;
         }
      }

      this.postRenderInterceptor.applyIndex(count);
      if (Minecraft.getTimestamp() - this.timestamp < 250L) {
         this.postRenderInterceptor.renderElement();
      }

      this.timestamp = Minecraft.getTimestamp();
      return false;
   }

   private void updateState3() {
      try {
         this.postRenderInterceptor.getCancelledManager().applyServerData(this.serverData);
      } catch (UnknownHostException unknownHostException) {
         this.serverData.timestamp = -1L;
         this.serverData.labelText = ClientSettingsBlackType.DARK_RED + "Can't resolve hostname";
      } catch (Exception exception) {
         this.serverData.timestamp = -1L;
         this.serverData.labelText = ClientSettingsBlackType.DARK_RED + "Can't connect to server.";
      }
   }

   private boolean isActive() {
      return true;
   }

   private void updateState() {
      if (this.serverData.getLabelText2() == null) {
         this.mc.getTextureManager().applyResourceLocation2(this.resourceLocation);
         this.dynamicTexture = null;
      } else {
         ByteBuf byteBuf = Unpooled.copiedBuffer(this.serverData.getLabelText2(), Charsets.UTF_8);
         ByteBuf byteBuf2 = Base64.decode(byteBuf);

         BufferedImage bufferedImage;
         searchBlock: {
            try {
               bufferedImage = SpecialLoggerEntry.resolveBufferedImage(new ByteBufInputStream(byteBuf2));
               Validate.validState(bufferedImage.getWidth() == 64, "Must be 64 pixels wide", new Object[0]);
               Validate.validState(bufferedImage.getHeight() == 64, "Must be 64 pixels high", new Object[0]);
               break searchBlock;
            } catch (Throwable throwable) {
               logger.error("Invalid icon for server " + this.serverData.labelText3 + " (" + this.serverData.text + ")", throwable);
               this.serverData.setLabelText2((String)null);
            } finally {
               byteBuf.release();
               byteBuf2.release();
            }

            return;
         }

         if (this.dynamicTexture == null) {
            this.dynamicTexture = new DynamicTexture(bufferedImage.getWidth(), bufferedImage.getHeight());
            this.mc.getTextureManager().matchesResourceLocation(this.resourceLocation, this.dynamicTexture);
         }

         try {
            bufferedImage.getRGB(0, 0, bufferedImage.getWidth(), bufferedImage.getHeight(), this.dynamicTexture.getCountArray(), 0, bufferedImage.getWidth());
         } catch (Throwable throwable2) {
         }

         this.dynamicTexture.updateState2();
      }
   }

   private ResourceLocation resolveResourceLocation(String text) {
      return Minecraft.getMinecraft().getSession().getKeybindAnimationEntry().getByText(text)
         ? GuiMultiplayer.resourceLocation5
         : (Minecraft.getMinecraft().getSession().getKeybindAnimationEntry().get(text) ? GuiMultiplayer.resourceLocation7 : GuiMultiplayer.resourceLocation6);
   }

   @Override
   public void applyIndex(int count, int count2, int count3) {
   }

   public CanTResolveHostnameListener(PostRenderInterceptor postRenderInterceptor2, ServerData serverData2) {
      this.postRenderInterceptor = postRenderInterceptor2;
      this.serverData = serverData2;
      this.mc = Minecraft.getMinecraft();
      this.resourceLocation = new ResourceLocation("servers/" + serverData2.text + "/icon");
      this.dynamicTexture = (DynamicTexture)this.mc.getTextureManager().get(this.resourceLocation);
   }
}
