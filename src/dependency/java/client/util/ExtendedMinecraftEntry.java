package client.util;

import client.Client;
import client.gui.AuxiliaryTexturesGuiIconsPngManagerAdapter;
import client.gui.ClientScreen;
import client.module.Module;
import client.render.RenderPass;
import client.render.RenderUtils;
import client.render.WorldRenderer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiMultiplayer;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.MathHelper;
import org.lwjgl.input.Mouse;

public abstract class ExtendedMinecraftEntry {
   public final Minecraft mc;
   public int count9;
   public int count3;
   public int count10;
   public int count7;
   public int count11;
   public int count14;
   public final int count5;
   public int count;
   public int count8;
   public int count6;
   public int count2;
   public boolean enabled5 = true;
   public int count12 = -2;
   public float factor;
   public float factor2;
   public int count4 = -1;
   public long timestamp;
   public boolean enabled4 = true;
   public boolean enabled2 = true;
   public boolean enabled;
   public int count13;
   public boolean enabled3 = true;

   public void applyIndex2(int count15, int count16) {
      this.count = count15;
      this.count8 = count16;
   }

   private void applyRenderPass(RenderPass renderPass) {
      RenderUtils.processAction((float)this.count14, (float)this.count7, (float)this.count11, (float)(this.count7 + this.count3), Integer.MIN_VALUE);
      RenderUtils.processAction((float)this.count14, 0.0F, (float)this.count11, (float)this.count10, Integer.MIN_VALUE);
   }

   public abstract boolean checkIndex(int count15);

   public void applyIndex(int count15) {
      this.factor2 += (float)count15;
      this.updateState3();
      this.count12 = -2;
   }

   public void applyIndex(int count15, int count16) {
   }

   public void updateState() {
      if (this.checkIndexByIndex(this.count2)) {
         if (Mouse.getEventButton() == 0 && Mouse.getEventButtonState() && this.count2 >= this.count10 && this.count2 <= this.count7) {
            int count15 = (this.count9 - this.getCount20()) / 2;
            int count16 = (this.count9 + this.getCount20()) / 2;
            int count17 = this.count2 - this.count10 - this.count13 + (int)this.factor2 - 4;
            int count18 = count17 / this.count5;
            if (count18 < this.size() && this.count6 >= count15 && this.count6 <= count16 && count18 >= 0 && count17 >= 0) {
               this.applyIndex(count18, false, this.count6, this.count2);
               this.count4 = count18;
            } else if (this.count6 >= count15 && this.count6 <= count16 && count17 < 0) {
               this.applyIndexByIndex(this.count6 - count15, this.count2 - this.count10 + (int)this.factor2 - 4);
            }
         }

         if (!MouseUtils.checkCondition(0) || !this.isEnabled3()) {
            this.count12 = -1;
         } else if (this.count12 == -1) {
            boolean flag = true;
            if (this.count2 >= this.count10 && this.count2 <= this.count7) {
               int count19 = (this.count9 - this.getCount20()) / 2;
               int count20 = (this.count9 + this.getCount20()) / 2;
               int count21 = this.count2 - this.count10 - this.count13 + (int)this.factor2 - 4;
               int count22 = count21 / this.count5;
               if (count22 < this.size() && this.count6 >= count19 && this.count6 <= count20 && count22 >= 0 && count21 >= 0) {
                  boolean flag2 = count22 == this.count4 && Minecraft.getTimestamp() - this.timestamp < 250L;
                  this.applyIndex(count22, flag2, this.count6, this.count2);
                  this.count4 = count22;
                  this.timestamp = Minecraft.getTimestamp();
               } else if (this.count6 >= count19 && this.count6 <= count20 && count21 < 0) {
                  this.applyIndexByIndex(this.count6 - count19, this.count2 - this.count10 + (int)this.factor2 - 4);
                  flag = false;
               }

               int count23 = this.getCount22();
               int count24 = count23 + 6;
               if (this.count6 >= count23 && this.count6 <= count24) {
                  this.factor = -1.0F;
                  int count25 = this.getCount2();
                  if (count25 < 1) {
                     count25 = 1;
                  }

                  int intItem = MathHelper.getInt12(
                     (int)((float)((this.count7 - this.count10) * (this.count7 - this.count10)) / (float)this.getCount()), 32, this.count7 - this.count10 - 8
                  );
                  this.factor = this.factor / ((float)(this.count7 - this.count10 - intItem) / (float)count25);
               } else {
                  this.factor = 1.0F;
               }

               if (flag) {
                  this.count12 = this.count2;
               } else {
                  this.count12 = -2;
               }
            } else {
               this.count12 = -2;
            }
         } else if (this.count12 >= 0) {
            this.factor2 = this.factor2 - (float)(this.count2 - this.count12) * this.factor;
            this.count12 = this.count2;
         }

         int eventDWheel = Mouse.getEventDWheel();
         if (eventDWheel != 0) {
            if (eventDWheel > 0) {
               eventDWheel = -1;
            } else if (eventDWheel < 0) {
               eventDWheel = 1;
            }

            this.factor2 = this.factor2 + (float)(eventDWheel * this.count5 / 2);
         }
      }
   }

   public void applyIndex(int count15, int count16, int count17) {
   }

   public boolean checkIndexByIndex(int count15) {
      return count15 >= this.count10 && count15 <= this.count7 && this.count6 >= this.count14 && this.count6 <= this.count11;
   }

   public void applyIndex(int count15, int count16, int count17, int count18) {
      this.count9 = count15;
      this.count3 = count16;
      this.count10 = count17;
      this.count7 = count18;
      this.count14 = 0;
      this.count11 = count15;
   }

   public void updateState3() {
      this.factor2 = MathHelper.clamp_float(this.factor2, 0.0F, (float)this.getCount2());
   }

   public int getCount20() {
      return 220;
   }

   public abstract void applyIndex(int count15, int count16, int count17, int count18, int count19, int count20);

   public int getCount22() {
      return this.count9 / 2 + 124;
   }

   public int getCount() {
      return this.size() * this.count5 + this.count13;
   }

   public boolean isEnabled3() {
      return this.enabled3;
   }

   public abstract void updateState2();

   public abstract void applyIndex(int count15, boolean flag, int count16, int count17);

   public void applyTessellator(int count15, int count16, Tessellator tessellator) {
   }

   public int getCountAsIndex() {
      return (int)this.factor2;
   }

   public void applyFlag(boolean flag, int count15) {
      this.enabled = flag;
      this.count13 = count15;
      if (!flag) {
         this.count13 = 0;
      }
   }

   public void applyIndexByIndex(int count15, int count16, int count17, int count18) {
      int size = this.size();
      Tessellator tessellator = Tessellator.getTessellator();
      WorldRenderer worldRenderer = tessellator.getWorldRenderer();

      for (int index = 0; index < size; index++) {
         int count19 = count16 + index * this.count5 + this.count13;
         int count20 = this.count5 - 4;
         if (count19 > this.count7 || count19 + count20 < this.count10) {
            this.applyIndex(index, count15, count19);
         }

         if (this.enabled2 && this.checkIndex(index)) {
            int count21 = this.count14 + (this.count9 / 2 - this.getCount20() / 2);
            int count22 = this.count14 + this.count9 / 2 + this.getCount20() / 2;
            GlStateManager.color4f2(1.0F, 1.0F, 1.0F, 1.0F);
            GlStateManager.enableTexture2D();
            worldRenderer.updateAuxiliaryLoggerEntry(7, DefaultVertexFormats.auxiliaryLoggerEntry6);
            worldRenderer.getWorldRenderer((double)count21, (double)(count19 + count20 + 2), 0.0)
               .getWorldRenderer6(0.0, 1.0)
               .resolveWorldRenderer(128, 128, 128, 255)
               .updateState();
            worldRenderer.getWorldRenderer((double)count22, (double)(count19 + count20 + 2), 0.0)
               .getWorldRenderer6(1.0, 1.0)
               .resolveWorldRenderer(128, 128, 128, 255)
               .updateState();
            worldRenderer.getWorldRenderer((double)count22, (double)(count19 - 2), 0.0).getWorldRenderer6(1.0, 0.0).resolveWorldRenderer(128, 128, 128, 255).updateState();
            worldRenderer.getWorldRenderer((double)count21, (double)(count19 - 2), 0.0).getWorldRenderer6(0.0, 0.0).resolveWorldRenderer(128, 128, 128, 255).updateState();
            worldRenderer.getWorldRenderer((double)(count21 + 1), (double)(count19 + count20 + 1), 0.0)
               .getWorldRenderer6(0.0, 1.0)
               .resolveWorldRenderer(0, 0, 0, 255)
               .updateState();
            worldRenderer.getWorldRenderer((double)(count22 - 1), (double)(count19 + count20 + 1), 0.0)
               .getWorldRenderer6(1.0, 1.0)
               .resolveWorldRenderer(0, 0, 0, 255)
               .updateState();
            worldRenderer.getWorldRenderer((double)(count22 - 1), (double)(count19 - 1), 0.0).getWorldRenderer6(1.0, 0.0).resolveWorldRenderer(0, 0, 0, 255).updateState();
            worldRenderer.getWorldRenderer((double)(count21 + 1), (double)(count19 - 1), 0.0).getWorldRenderer6(0.0, 0.0).resolveWorldRenderer(0, 0, 0, 255).updateState();
            tessellator.updateState();
            GlStateManager.disableTexture2D();
         }

         if (!(this instanceof ClientManagerTracker) || count19 >= this.count10 - this.count5 && count19 <= this.count7) {
            this.applyIndex(index, count15, count19, count20, count17, count18);
         }
      }
   }

   public void applyIndexByIndex(int count15) {
      this.count14 = count15;
      this.count11 = count15 + this.count9;
   }

   public abstract int size();

   public void setEnabled3(boolean enabled6) {
      this.enabled3 = enabled6;
   }

   public void applyIndex(int count15, int count16, float factor3) {
      if (this.enabled4) {
         if (Minecraft.getMinecraft().currentScreen instanceof GuiMultiplayer) {
            int count17 = this.count14 + (this.count9 / 2 - this.getCount20() / 2);
            int count18 = this.count14 + this.count9 / 2 + this.getCount20() / 2;
            RenderUtils.processAction(count17 - 2, 0, count18 + 2, this.count3, Integer.MIN_VALUE);
         }

         this.count6 = count15;
         this.count2 = count16;
         this.updateState2();
         int count19 = this.getCount22();
         int count20 = count19 + 6;
         this.updateState3();
         GlStateManager.applyState3();
         GlStateManager.applyState20();
         Tessellator tessellator = Tessellator.getTessellator();
         WorldRenderer worldRenderer = tessellator.getWorldRenderer();
         float factor4 = 32.0F;
         if (this.mc.inGameHasFocus) {
            worldRenderer.updateAuxiliaryLoggerEntry(7, DefaultVertexFormats.auxiliaryLoggerEntry6);
            worldRenderer.getWorldRenderer((double)this.count14, (double)this.count7, 0.0)
               .getWorldRenderer6((double)((float)this.count14 / factor4), (double)((float)(this.count7 + (int)this.factor2) / factor4))
               .resolveWorldRenderer(32, 32, 32, 255)
               .updateState();
            worldRenderer.getWorldRenderer((double)this.count11, (double)this.count7, 0.0)
               .getWorldRenderer6((double)((float)this.count11 / factor4), (double)((float)(this.count7 + (int)this.factor2) / factor4))
               .resolveWorldRenderer(32, 32, 32, 255)
               .updateState();
            worldRenderer.getWorldRenderer((double)this.count11, (double)this.count10, 0.0)
               .getWorldRenderer6((double)((float)this.count11 / factor4), (double)((float)(this.count10 + (int)this.factor2) / factor4))
               .resolveWorldRenderer(32, 32, 32, 255)
               .updateState();
            worldRenderer.getWorldRenderer((double)this.count14, (double)this.count10, 0.0)
               .getWorldRenderer6((double)((float)this.count14 / factor4), (double)((float)(this.count10 + (int)this.factor2) / factor4))
               .resolveWorldRenderer(32, 32, 32, 255)
               .updateState();
            tessellator.updateState();
         }

         int count21 = this.count14 + this.count9 / 2 - this.getCount20() / 2 + 2;
         int count22 = this.count10 + 4 - (int)this.factor2;
         if (this.enabled) {
            this.applyTessellator(count21, count22, tessellator);
         }

         ClientScreen.applyIndex2(this.count14, this.count10, this.count11, this.count7);
         this.applyIndexByIndex(count21, count22, count15, count16);
         ClientScreen.glDisable();
         GlStateManager.enableDepth();
         GlStateManager.disableBlend();
         GlStateManager.tryBlendFuncSeparate(770, 771, 0, 1);
         GlStateManager.applyState10();
         GlStateManager.glShadeModel(7425);
         GlStateManager.enableTexture2D();
         worldRenderer.updateAuxiliaryLoggerEntry(7, DefaultVertexFormats.auxiliaryLoggerEntry6);
         worldRenderer.getWorldRenderer((double)this.count14, (double)(this.count10 + 4), 0.0)
            .getWorldRenderer6(0.0, 1.0)
            .resolveWorldRenderer(0, 0, 0, 0)
            .updateState();
         worldRenderer.getWorldRenderer((double)this.count11, (double)(this.count10 + 4), 0.0)
            .getWorldRenderer6(1.0, 1.0)
            .resolveWorldRenderer(0, 0, 0, 0)
            .updateState();
         worldRenderer.getWorldRenderer((double)this.count11, (double)this.count10, 0.0).getWorldRenderer6(1.0, 0.0).resolveWorldRenderer(0, 0, 0, 255).updateState();
         worldRenderer.getWorldRenderer((double)this.count14, (double)this.count10, 0.0).getWorldRenderer6(0.0, 0.0).resolveWorldRenderer(0, 0, 0, 255).updateState();
         tessellator.updateState();
         worldRenderer.updateAuxiliaryLoggerEntry(7, DefaultVertexFormats.auxiliaryLoggerEntry6);
         worldRenderer.getWorldRenderer((double)this.count14, (double)this.count7, 0.0).getWorldRenderer6(0.0, 1.0).resolveWorldRenderer(0, 0, 0, 255).updateState();
         worldRenderer.getWorldRenderer((double)this.count11, (double)this.count7, 0.0).getWorldRenderer6(1.0, 1.0).resolveWorldRenderer(0, 0, 0, 255).updateState();
         worldRenderer.getWorldRenderer((double)this.count11, (double)(this.count7 - 4), 0.0)
            .getWorldRenderer6(1.0, 0.0)
            .resolveWorldRenderer(0, 0, 0, 0)
            .updateState();
         worldRenderer.getWorldRenderer((double)this.count14, (double)(this.count7 - 4), 0.0)
            .getWorldRenderer6(0.0, 0.0)
            .resolveWorldRenderer(0, 0, 0, 0)
            .updateState();
         tessellator.updateState();
         int count23 = this.getCount2();
         if (count23 > 0) {
            int intItem = MathHelper.getInt12((this.count7 - this.count10) * (this.count7 - this.count10) / this.getCount(), 32, this.count7 - this.count10 - 8);
            int count24 = (int)this.factor2 * (this.count7 - this.count10 - intItem) / count23 + this.count10;
            if (count24 < this.count10) {
               count24 = this.count10;
            }

            worldRenderer.updateAuxiliaryLoggerEntry(7, DefaultVertexFormats.auxiliaryLoggerEntry6);
            worldRenderer.getWorldRenderer((double)count19, (double)this.count7, 0.0).getWorldRenderer6(0.0, 1.0).resolveWorldRenderer(0, 0, 0, 255).updateState();
            worldRenderer.getWorldRenderer((double)count20, (double)this.count7, 0.0).getWorldRenderer6(1.0, 1.0).resolveWorldRenderer(0, 0, 0, 255).updateState();
            worldRenderer.getWorldRenderer((double)count20, (double)this.count10, 0.0).getWorldRenderer6(1.0, 0.0).resolveWorldRenderer(0, 0, 0, 255).updateState();
            worldRenderer.getWorldRenderer((double)count19, (double)this.count10, 0.0).getWorldRenderer6(0.0, 0.0).resolveWorldRenderer(0, 0, 0, 255).updateState();
            tessellator.updateState();
            worldRenderer.updateAuxiliaryLoggerEntry(7, DefaultVertexFormats.auxiliaryLoggerEntry6);
            worldRenderer.getWorldRenderer((double)count19, (double)(count24 + intItem), 0.0)
               .getWorldRenderer6(0.0, 1.0)
               .resolveWorldRenderer(128, 128, 128, 255)
               .updateState();
            worldRenderer.getWorldRenderer((double)count20, (double)(count24 + intItem), 0.0)
               .getWorldRenderer6(1.0, 1.0)
               .resolveWorldRenderer(128, 128, 128, 255)
               .updateState();
            worldRenderer.getWorldRenderer((double)count20, (double)count24, 0.0).getWorldRenderer6(1.0, 0.0).resolveWorldRenderer(128, 128, 128, 255).updateState();
            worldRenderer.getWorldRenderer((double)count19, (double)count24, 0.0).getWorldRenderer6(0.0, 0.0).resolveWorldRenderer(128, 128, 128, 255).updateState();
            tessellator.updateState();
            worldRenderer.updateAuxiliaryLoggerEntry(7, DefaultVertexFormats.auxiliaryLoggerEntry6);
            worldRenderer.getWorldRenderer((double)count19, (double)(count24 + intItem - 1), 0.0)
               .getWorldRenderer6(0.0, 1.0)
               .resolveWorldRenderer(192, 192, 192, 255)
               .updateState();
            worldRenderer.getWorldRenderer((double)(count20 - 1), (double)(count24 + intItem - 1), 0.0)
               .getWorldRenderer6(1.0, 1.0)
               .resolveWorldRenderer(192, 192, 192, 255)
               .updateState();
            worldRenderer.getWorldRenderer((double)(count20 - 1), (double)count24, 0.0).getWorldRenderer6(1.0, 0.0).resolveWorldRenderer(192, 192, 192, 255).updateState();
            worldRenderer.getWorldRenderer((double)count19, (double)count24, 0.0).getWorldRenderer6(0.0, 0.0).resolveWorldRenderer(192, 192, 192, 255).updateState();
            tessellator.updateState();
         }

         MovementHandler.processAction6();
         MovementHandler.processAction8();
         MovementHandler.applyRunnableTask(false, this::applyRenderPass);
         GlStateManager.disableBlend();
         GlStateManager.tryBlendFuncSeparate(770, 771, 0, 1);
         GlStateManager.applyState10();
         GlStateManager.glShadeModel(7425);
         GlStateManager.enableTexture2D();
         this.applyIndex(count15, count16);
         GlStateManager.disableTexture2D();
         GlStateManager.glShadeModel(7424);
         GlStateManager.applyState14();
         GlStateManager.enableBlend();
         MovementHandler.applyScaledResolution(new ScaledResolution(this.mc), true);
         if (((Module)Client.getClient().getModuleManager().notifications).isToggled()) {
            Client.getClient().getModuleManager().notifications.processBoolean2(true);
         }
      }
   }

   public int computeCount(int count15, int count16) {
      int count17 = this.count14 + this.count9 / 2 - this.getCount20() / 2;
      int count18 = this.count14 + this.count9 / 2 + this.getCount20() / 2;
      int count19 = count16 - this.count10 - this.count13 + (int)this.factor2 - 4;
      int count20 = count19 / this.count5;
      return count15 < this.getCount22() && count15 >= count17 && count15 <= count18 && count20 >= 0 && count19 >= 0 && count20 < this.size() ? count20 : -1;
   }

   public void applyIndexByIndex(int count15, int count16) {
   }

   public int getCount5() {
      return this.count5;
   }

   public void applyAuxiliaryTexturesGuiIconsP(AuxiliaryTexturesGuiIconsPngManagerAdapter auxiliaryTexturesGuiIconsPngManagerAdapter) {
      if (auxiliaryTexturesGuiIconsPngManagerAdapter.enabled3) {
         if (auxiliaryTexturesGuiIconsPngManagerAdapter.count2 == this.count) {
            this.factor2 = this.factor2 - (float)(this.count5 * 2 / 3);
            this.count12 = -2;
            this.updateState3();
         } else if (auxiliaryTexturesGuiIconsPngManagerAdapter.count2 == this.count8) {
            this.factor2 = this.factor2 + (float)(this.count5 * 2 / 3);
            this.count12 = -2;
            this.updateState3();
         }
      }
   }

   public int getCount2() {
      return Math.max(0, this.getCount() - (this.count7 - this.count10 - 4));
   }

   public void setEnabled2(boolean enabled6) {
      this.enabled2 = enabled6;
   }

   public ExtendedMinecraftEntry(Minecraft mc2, int count15, int count16, int count17, int count18, int count19) {
      this.mc = mc2;
      this.count9 = count15;
      this.count3 = count16;
      this.count10 = count17;
      this.count7 = count18;
      this.count5 = count19;
      this.count14 = 0;
      this.count11 = count15;
   }
}
