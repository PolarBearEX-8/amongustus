package net.minecraft.client.gui;

import com.google.common.collect.Lists;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Random;
import net.minecraft.client.audio.MusicTicker;
import net.minecraft.client.audio.SoundHandler;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.network.play.client.C16PacketClientStatus;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.ResourceLocation;
import org.apache.commons.io.Charsets;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GuiWinGame extends GuiScreen {
   private static final Logger LOGGER2 = LogManager.getLogger();
   private static final ResourceLocation MINECRAFT_LOGO = new ResourceLocation("textures/gui/title/minecraft.png");
   private static final ResourceLocation VIGNETTE_TEXTURE = new ResourceLocation("textures/misc/vignette.png");
   private int time;
   private List lines;
   private int totalScrollLength;
   private float scrollSpeed = 0.5F;

   private void drawWinGameScreen(int n, int n2, float f) {
      Tessellator instance = Tessellator.getInstance();
      WorldRenderer worldRenderer = instance.getWorldRenderer();
      this.mc.getTextureManager().bindTexture(Gui.optionsBackground);
      worldRenderer.begin(7, DefaultVertexFormats.POSITION_TEX_COLOR);
      int width2 = this.width;
      float f2 = 0.0F - ((float)this.time + f) * 0.5F * this.scrollSpeed;
      float f3 = (float)this.height - ((float)this.time + f) * 0.5F * this.scrollSpeed;
      float f4 = 0.015625F;
      float f5 = ((float)this.time + f - 0.0F) * 0.02F;
      float f7 = (((float)(this.totalScrollLength + this.height + this.height + 24) / this.scrollSpeed) - 20.0F - ((float)this.time + f)) * 0.005F;
      if (f7 < f5) {
         f5 = f7;
      }

      if (f5 > 1.0F) {
         f5 = 1.0F;
      }

      f5 *= f5;
      f5 = f5 * 96.0F / 255.0F;
      worldRenderer.pos(0.0, (double)this.height, (double)this.zLevel).tex(0.0, (double)(f2 * f4)).color(f5, f5, f5, 1.0F).endVertex();
      worldRenderer.pos((double)width2, (double)this.height, (double)this.zLevel)
         .tex((double)((float)width2 * f4), (double)(f2 * f4))
         .color(f5, f5, f5, 1.0F)
         .endVertex();
      worldRenderer.pos((double)width2, 0.0, (double)this.zLevel).tex((double)((float)width2 * f4), (double)(f3 * f4)).color(f5, f5, f5, 1.0F).endVertex();
      worldRenderer.pos(0.0, 0.0, (double)this.zLevel).tex(0.0, (double)(f3 * f4)).color(f5, f5, f5, 1.0F).endVertex();
      instance.draw();
   }

   @Override
   public void drawScreen(int mouseX, int mouseY, float partialTicks) {
      this.drawWinGameScreen(mouseX, mouseY, partialTicks);
      Tessellator instance = Tessellator.getInstance();
      WorldRenderer worldRenderer = instance.getWorldRenderer();
      short s = 274;
      int n = this.width / 2 - s / 2;
      int n2 = this.height + 50;
      float f = -((float)this.time + partialTicks) * this.scrollSpeed;
      GlStateManager.pushMatrix();
      GlStateManager.translate(0.0F, f, 0.0F);
      this.mc.getTextureManager().bindTexture(MINECRAFT_LOGO);
      GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
      this.drawTexturedModalRect(n, n2, 0, 0, 155, 44);
      this.drawTexturedModalRect(n + 155, n2, 0, 45, 155, 44);
      int n3 = n2 + 200;

      for (int i = 0; i < this.lines.size(); i++) {
         if (i == this.lines.size() - 1) {
            float f2 = (float)n3 + f - (float)(this.height / 2 - 6);
            if (f2 < 0.0F) {
               GlStateManager.translate(0.0F, -f2, 0.0F);
            }
         }

         if ((float)n3 + f + 12.0F + 8.0F > 0.0F && (float)n3 + f < (float)this.height) {
            String str = (String)this.lines.get(i);
            if (str.startsWith("[C]")) {
               this.fontRendererObj
                  .drawStringWithShadow(
                     str.substring(3), (float)(n + (s - this.fontRendererObj.getStringWidth(str.substring(3))) / 2), (float)n3, 16777215
                  );
            } else {
               this.fontRendererObj.fontRandom.setSeed((long)i * 4238972211L + (long)(this.time / 4));
               this.fontRendererObj.drawStringWithShadow(str, (float)n, (float)n3, 16777215);
            }
         }

         n3 += 12;
      }

      GlStateManager.popMatrix();
      this.mc.getTextureManager().bindTexture(VIGNETTE_TEXTURE);
      GlStateManager.enableBlend();
      GlStateManager.blendFunc(0, 769);
      int width2 = this.width;
      int height2 = this.height;
      worldRenderer.begin(7, DefaultVertexFormats.POSITION_TEX_COLOR);
      worldRenderer.pos(0.0, (double)height2, (double)this.zLevel).tex(0.0, 1.0).color(1.0F, 1.0F, 1.0F, 1.0F).endVertex();
      worldRenderer.pos((double)width2, (double)height2, (double)this.zLevel).tex(1.0, 1.0).color(1.0F, 1.0F, 1.0F, 1.0F).endVertex();
      worldRenderer.pos((double)width2, 0.0, (double)this.zLevel).tex(1.0, 0.0).color(1.0F, 1.0F, 1.0F, 1.0F).endVertex();
      worldRenderer.pos(0.0, 0.0, (double)this.zLevel).tex(0.0, 0.0).color(1.0F, 1.0F, 1.0F, 1.0F).endVertex();
      instance.draw();
      GlStateManager.disableBlend();
      super.drawScreen(mouseX, mouseY, partialTicks);
   }

   @Override
   public void updateScreen() {
      MusicTicker mcMusicTicker = this.mc.getMcMusicTicker();
      SoundHandler soundHandler = this.mc.getSoundHandler();
      if (this.time == 0) {
         mcMusicTicker.stopMusic();
         mcMusicTicker.playMusic(MusicTicker.MusicType.CREDITS);
         soundHandler.resumeSounds();
      }

      soundHandler.update();
      this.time++;
      float f = (float)(this.totalScrollLength + this.height + this.height + 24) / this.scrollSpeed;
      if ((float)this.time > f) {
         this.sendRespawnPacket();
      }
   }

   @Override
   public void initGui() {
      if (this.lines == null) {
         this.lines = Lists.newArrayList();

         try {
            String str = "";
            String str2 = "" + EnumChatFormatting.WHITE + EnumChatFormatting.OBFUSCATED + EnumChatFormatting.GREEN + EnumChatFormatting.AQUA;
            short s = 274;
            InputStream inputStream = this.mc.getResourceManager().getResource(new ResourceLocation("texts/end.txt")).getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, Charsets.UTF_8));
            Random rand = new Random(8124371L);

            while ((str = reader.readLine()) != null) {
               str = str.replaceAll("PLAYERNAME", this.mc.getSession().getUsername());

               while (str.contains(str2)) {
                  int n = str.indexOf(str2);
                  Object obj = str.substring(0, n);
                  String str3 = str.substring(n + str2.length());
                  str = obj + EnumChatFormatting.WHITE.toString() + EnumChatFormatting.OBFUSCATED.toString() + "XXXXXXXX".substring(0, rand.nextInt(4) + 3) + str3;
               }

               this.lines.addAll(this.mc.fontRendererObj.listFormattedStringToWidth(str, s));
               this.lines.add("");
            }

            inputStream.close();

            for (int i = 0; i < 8; i++) {
               this.lines.add("");
            }

            inputStream = this.mc.getResourceManager().getResource(new ResourceLocation("texts/credits.txt")).getInputStream();
            reader = new BufferedReader(new InputStreamReader(inputStream, Charsets.UTF_8));

            while ((str = reader.readLine()) != null) {
               str = str.replaceAll("PLAYERNAME", this.mc.getSession().getUsername());
               str = str.replaceAll("\t", "    ");
               this.lines.addAll(this.mc.fontRendererObj.listFormattedStringToWidth(str, s));
               this.lines.add("");
            }

            inputStream.close();
            this.totalScrollLength = this.lines.size() * 12;
         } catch (Exception e) {
            LOGGER2.error("Couldn't load credits", e);
         }
      }
   }

   @Override
   public boolean doesGuiPauseGame() {
      return true;
   }

   @Override
   public void keyTyped(char typedChar, int keyCode) throws IOException {
      if (keyCode == 1) {
         this.sendRespawnPacket();
      }
   }

   private void sendRespawnPacket() {
      this.mc.thePlayer.sendQueue.addToSendQueue(new C16PacketClientStatus(C16PacketClientStatus.EnumState.PERFORM_RESPAWN));
      GuiScreen screen = (GuiScreen)null;
      this.mc.displayGuiScreen(screen);
   }
}
