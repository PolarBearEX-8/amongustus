package client.gui;

import client.render.WorldRenderer;
import client.setting.AutoSoupPerformRespawnType;
import client.setting.BaseOptionType;
import client.setting.ClientSettingsBlackType;
import client.util.DefaultVertexFormats;
import com.google.common.collect.Lists;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Random;
import net.minecraft.client.audio.MusicTicker;
import net.minecraft.client.audio.SoundHandler;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.network.play.client.C16PacketClientStatus;
import net.minecraft.util.ResourceLocation;
import org.apache.commons.io.Charsets;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ManagedGuiScreenAdapter extends GuiScreen {
   public static final Logger logger2 = LogManager.getLogger();
   public static final ResourceLocation resourceLocation5 = new ResourceLocation("textures/gui/title/minecraft.png");
   public static final ResourceLocation resourceLocation6 = new ResourceLocation("textures/misc/vignette.png");
   public int count2;
   public List list3;
   public int count3;
   public float factor3 = 0.5F;

   @Override
   public void updateState4() {
      MusicTicker musicTicker = this.mc.getMusicTicker();
      SoundHandler mcSoundHandler = this.mc.getMcSoundHandler();
      if (this.count2 == 0) {
         musicTicker.updateState2();
         musicTicker.applyBaseOptionType(BaseOptionType.CREDITS);
         mcSoundHandler.updateState3();
      }

      mcSoundHandler.updateState();
      this.count2++;
      float factor = (float)(this.count3 + this.height + this.height + 24) / this.factor3;
      if ((float)this.count2 > factor) {
         this.renderElement();
      }
   }

   @Override
   public boolean isActive() {
      return true;
   }

   @Override
   public void updateState2() {
      if (this.list3 == null) {
         this.list3 = Lists.newArrayList();

         try {
            String text = ""
               + ClientSettingsBlackType.WHITE
               + ClientSettingsBlackType.OBFUSCATED
               + ClientSettingsBlackType.GREEN
               + ClientSettingsBlackType.AQUA;
            InputStream inputStream = this.mc.getIResourceManager().resolveIResourceByResourceLocation(new ResourceLocation("texts/end.txt")).getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, Charsets.UTF_8));
            Random random = new Random(8124371L);

            String text2;
            while ((text2 = reader.readLine()) != null) {
               String text3 = text2.replaceAll("PLAYERNAME", this.mc.getSession().getText1());

               while (text3.contains(text)) {
                  int count = text3.indexOf(text);
                  String entry = text3.substring(0, count);
                  String text4 = text3.substring(count + text.length());
                  text3 = entry + ClientSettingsBlackType.WHITE + ClientSettingsBlackType.OBFUSCATED + "XXXXXXXX".substring(0, random.nextInt(4) + 3) + text4;
               }

               this.list3.addAll(this.mc.fontRendererObj.getList(text3, 274));
               this.list3.add("");
            }

            inputStream.close();

            for (int index = 0; index < 8; index++) {
               this.list3.add("");
            }

            InputStream inputStream2 = this.mc.getIResourceManager().resolveIResourceByResourceLocation(new ResourceLocation("texts/credits.txt")).getInputStream();
            BufferedReader reader2 = new BufferedReader(new InputStreamReader(inputStream2, Charsets.UTF_8));

            String text5;
            while ((text5 = reader2.readLine()) != null) {
               String text6 = text5.replaceAll("PLAYERNAME", this.mc.getSession().getText1()).replaceAll("\t", "    ");
               this.list3.addAll(this.mc.fontRendererObj.getList(text6, 274));
               this.list3.add("");
            }

            inputStream2.close();
            this.count3 = this.list3.size() * 12;
         } catch (Exception exception) {
            logger2.error("Couldn't load credits", exception);
         }
      }
   }

   private void applyIndexByIndex(int count, int count4, float factor) {
      Tessellator tessellator = Tessellator.getTessellator();
      WorldRenderer worldRenderer = tessellator.getWorldRenderer();
      this.mc.getTextureManager().applyResourceLocation3(TexturesGuiIconsPngManager.resourceLocation);
      worldRenderer.updateAuxiliaryLoggerEntry(7, DefaultVertexFormats.auxiliaryLoggerEntry6);
      int width = this.width;
      float factor2 = 0.0F - ((float)this.count2 + factor) * 0.5F * this.factor3;
      float factor4 = (float)this.height - ((float)this.count2 + factor) * 0.5F * this.factor3;
      float factor5 = 0.015625F;
      float factor6 = ((float)this.count2 + factor - 0.0F) * 0.02F;
      float factor7 = ((float)(this.count3 + this.height + this.height + 24) / this.factor3 - 20.0F - ((float)this.count2 + factor)) * 0.005F;
      if (factor7 < factor6) {
         factor6 = factor7;
      }

      if (factor6 > 1.0F) {
         factor6 = 1.0F;
      }

      float factor8 = factor6 * factor6 * 96.0F / 255.0F;
      worldRenderer.getWorldRenderer(0.0, (double)this.height, (double)this.factor)
         .getWorldRenderer6(0.0, (double)(factor2 * factor5))
         .resolveWorldRenderer(factor8, factor8, factor8, 1.0F)
         .updateState();
      worldRenderer.getWorldRenderer((double)width, (double)this.height, (double)this.factor)
         .getWorldRenderer6((double)((float)width * factor5), (double)(factor2 * factor5))
         .resolveWorldRenderer(factor8, factor8, factor8, 1.0F)
         .updateState();
      worldRenderer.getWorldRenderer((double)width, 0.0, (double)this.factor)
         .getWorldRenderer6((double)((float)width * factor5), (double)(factor4 * factor5))
         .resolveWorldRenderer(factor8, factor8, factor8, 1.0F)
         .updateState();
      worldRenderer.getWorldRenderer(0.0, 0.0, (double)this.factor)
         .getWorldRenderer6(0.0, (double)(factor4 * factor5))
         .resolveWorldRenderer(factor8, factor8, factor8, 1.0F)
         .updateState();
      tessellator.updateState();
   }

   @Override
   public void applyIndex(int count, int count4, float factor) {
      this.applyIndexByIndex(count, count4, factor);
      Tessellator tessellator = Tessellator.getTessellator();
      WorldRenderer worldRenderer = tessellator.getWorldRenderer();
      int count5 = this.width / 2 - 274 / 2;
      int count6 = this.height + 50;
      float factor2 = -((float)this.count2 + factor) * this.factor3;
      GlStateManager.pushMatrix();
      GlStateManager.translate(0.0F, factor2, 0.0F);
      this.mc.getTextureManager().applyResourceLocation3(resourceLocation5);
      GlStateManager.color4f2(1.0F, 1.0F, 1.0F, 1.0F);
      this.processAction(count5, count6, 0, 0, 155, 44);
      this.processAction(count5 + 155, count6, 0, 45, 155, 44);
      int count7 = count6 + 200;

      for (int index = 0; index < this.list3.size(); index++) {
         if (index == this.list3.size() - 1) {
            float factor4 = (float)count7 + factor2 - (float)(this.height / 2 - 6);
            if (factor4 < 0.0F) {
               GlStateManager.translate(0.0F, -factor4, 0.0F);
            }
         }

         if ((float)count7 + factor2 + 12.0F + 8.0F > 0.0F && (float)count7 + factor2 < (float)this.height) {
            String text = (String)this.list3.get(index);
            if (text.startsWith("[C]")) {
               this.fontRenderer
                  .getIndex2(text.substring(3), (float)(count5 + (274 - this.fontRenderer.getIndex(text.substring(3))) / 2), (float)count7, 16777215);
            } else {
               this.fontRenderer.random.setSeed((long)index * 4238972211L + (long)(this.count2 / 4));
               this.fontRenderer.getIndex2(text, (float)count5, (float)count7, 16777215);
            }
         }

         count7 += 12;
      }

      GlStateManager.popMatrix();
      this.mc.getTextureManager().applyResourceLocation3(resourceLocation6);
      GlStateManager.disableBlend();
      GlStateManager.glStateAction3(0, 769);
      int width = this.width;
      int height = this.height;
      worldRenderer.updateAuxiliaryLoggerEntry(7, DefaultVertexFormats.auxiliaryLoggerEntry6);
      worldRenderer.getWorldRenderer(0.0, (double)height, (double)this.factor).getWorldRenderer6(0.0, 1.0).resolveWorldRenderer(1.0F, 1.0F, 1.0F, 1.0F).updateState();
      worldRenderer.getWorldRenderer((double)width, (double)height, (double)this.factor)
         .getWorldRenderer6(1.0, 1.0)
         .resolveWorldRenderer(1.0F, 1.0F, 1.0F, 1.0F)
         .updateState();
      worldRenderer.getWorldRenderer((double)width, 0.0, (double)this.factor).getWorldRenderer6(1.0, 0.0).resolveWorldRenderer(1.0F, 1.0F, 1.0F, 1.0F).updateState();
      worldRenderer.getWorldRenderer(0.0, 0.0, (double)this.factor).getWorldRenderer6(0.0, 0.0).resolveWorldRenderer(1.0F, 1.0F, 1.0F, 1.0F).updateState();
      tessellator.updateState();
      GlStateManager.enableBlend();
      super.applyIndex(count, count4, factor);
   }

   private void renderElement() {
      this.mc.thePlayer.netHandlerPlayClient.handlePacket(new C16PacketClientStatus(AutoSoupPerformRespawnType.PERFORM_RESPAWN));
      GuiScreen screen = null;
      this.mc.displayGuiScreen(screen);
   }

   @Override
   public void applyChar(char character, int count) throws IOException {
      if (count == 1) {
         this.renderElement();
      }
   }
}
