package client.util;

import client.gui.AuxiliaryTexturesGuiIconsPngManagerAdapter;
import client.gui.ValueSliderControl;
import client.setting.AutoSoupPerformRespawnType;
import java.io.IOException;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.network.play.client.C16PacketClientStatus;
import net.minecraft.stats.StatFileWriter;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import org.lwjgl.input.Mouse;

public class SplineSystem extends GuiScreen implements StringArrayCallback {
   public static final int count2 = AchievementTheEnd2Manager.count3 * 24 - 112;
   public static final int count3 = AchievementTheEnd2Manager.count4 * 24 - 112;
   public static final int count4 = AchievementTheEnd2Manager.count2 * 24 - 77;
   public static final int count5 = AchievementTheEnd2Manager.count * 24 - 77;
   public static final ResourceLocation resourceLocation5 = new ResourceLocation("textures/gui/achievement/achievement_background.png");
   public int count6 = 256;
   public int count7 = 202;
   public int count8;
   public int count9;
   public float factor3 = 1.0F;
   public double value;
   public double value2;
   public double value3;
   public double value4;
   public double value5;
   public double value6;
   public int count10;
   public StatFileWriter statFileWriter;
   public boolean enabled6 = true;

   @Override
   public void updateState() {
      if (this.enabled6) {
         this.enabled6 = false;
      }
   }

   private ServiceStringEntry resolveServiceStringEntry(Block block) {
      return Minecraft.getMinecraft().getGoldenCrystal().getCommonMapEntry().resolveServiceStringEntry(block.getDefaultState());
   }

   public SplineSystem(GuiScreen screen, StatFileWriter statFileWriter2) {
      super(screen);
      this.statFileWriter = statFileWriter2;
      this.value = this.value3 = this.value5 = (double)(AchievementTheEnd2Manager.auxiliaryDuplicateStatIdManagerAdapter31.count * 24 - 141 / 2 - 12);
      this.value2 = this.value4 = this.value6 = (double)(AchievementTheEnd2Manager.auxiliaryDuplicateStatIdManagerAdapter31.count2 * 24 - 141 / 2);
   }

   public void updateState5() {
      int count = (this.width - this.count6) / 2;
      int count11 = (this.height - this.count7) / 2;
      this.fontRenderer.getIndex(StatCollector.getText("gui.achievements", new Object[0]), count + 15, count11 + 5, 4210752);
   }

   @Override
   public void updateState4() {
      if (!this.enabled6) {
         this.value = this.value3;
         this.value2 = this.value4;
         double offset = this.value5 - this.value3;
         double offset2 = this.value6 - this.value4;
         if (offset * offset + offset2 * offset2 < 4.0) {
            this.value3 += offset;
            this.value4 += offset2;
         } else {
            this.value3 += offset * 0.85;
            this.value4 += offset2 * 0.85;
         }
      }
   }

   public void applyIndexByIndex(int count, int count11, float factor) {
      int count12 = MathHelper.ceiling_double_int(this.value + (this.value3 - this.value) * (double)factor);
      int count13 = MathHelper.ceiling_double_int(this.value2 + (this.value4 - this.value2) * (double)factor);
      if (count12 < count2) {
         count12 = count2;
      }

      if (count13 < count3) {
         count13 = count3;
      }

      if (count12 >= count4) {
         count12 = count4 - 1;
      }

      if (count13 >= count5) {
         count13 = count5 - 1;
      }

      int count14 = (this.width - this.count6) / 2;
      int count15 = (this.height - this.count7) / 2;
      int count16 = count14 + 16;
      int count17 = count15 + 17;
      this.factor = 0.0F;
      GlStateManager.glDepthFunc(518);
      GlStateManager.pushMatrix();
      GlStateManager.translate((float)count16, (float)count17, -200.0F);
      GlStateManager.translate3f3(1.0F / this.factor3, 1.0F / this.factor3, 0.0F);
      GlStateManager.disableTexture2D();
      GlStateManager.applyState3();
      GlStateManager.disableLighting();
      GlStateManager.applyState6();
      int count18 = count12 + 288 >> 4;
      int count19 = count13 + 288 >> 4;
      int count20 = (count12 + 288) % 16;
      int count21 = (count13 + 288) % 16;
      Random random = new Random();
      float factor2 = 16.0F / this.factor3;
      float factor4 = 16.0F / this.factor3;

      for (int index = 0; (float)index * factor2 - (float)count21 < 155.0F; index++) {
         float factor5 = 0.6F - (float)(count19 + index) / 25.0F * 0.3F;
         GlStateManager.color4f2(factor5, factor5, factor5, 1.0F);

         for (int index2 = 0; (float)index2 * factor4 - (float)count20 < 224.0F; index2++) {
            random.setSeed((long)(this.mc.getSession().getText().hashCode() + count18 + index2 + (count19 + index) * 16));
            int count22 = random.nextInt(1 + count19 + index) + (count19 + index) / 2;
            ServiceStringEntry serviceStringEntry = this.resolveServiceStringEntry(Blocks.blockSand);
            if (count22 > 37 || count19 + index == 35) {
               Block block = Blocks.block95;
               serviceStringEntry = this.resolveServiceStringEntry(block);
            } else if (count22 == 22) {
               if (random.nextInt(2) == 0) {
                  serviceStringEntry = this.resolveServiceStringEntry(Blocks.block44);
               } else {
                  serviceStringEntry = this.resolveServiceStringEntry(Blocks.block25);
               }
            } else if (count22 == 10) {
               serviceStringEntry = this.resolveServiceStringEntry(Blocks.block103);
            } else if (count22 == 8) {
               serviceStringEntry = this.resolveServiceStringEntry(Blocks.block89);
            } else if (count22 > 4) {
               serviceStringEntry = this.resolveServiceStringEntry(Blocks.stone);
            } else if (count22 > 0) {
               serviceStringEntry = this.resolveServiceStringEntry(Blocks.block115);
            }

            this.mc.getTextureManager().applyResourceLocation3(ListenerAdapter27.resourceLocation);
            this.applyServiceStringEntry(index2 * 16 - count20, index * 16 - count21, serviceStringEntry, 16, 16);
         }
      }

      GlStateManager.disableDepth();
      GlStateManager.glDepthFunc(515);
      this.mc.getTextureManager().applyResourceLocation3(resourceLocation5);

      for (int index3 = 0; index3 < AchievementTheEnd2Manager.list.size(); index3++) {
         AuxiliaryDuplicateStatIdManagerAdapter auxiliaryDuplicateStatIdManagerAdapter = (AuxiliaryDuplicateStatIdManagerAdapter)AchievementTheEnd2Manager.list.get(index3);
         if (auxiliaryDuplicateStatIdManagerAdapter.auxiliaryDuplicateStatIdManagerAdapter != null) {
            int count23 = auxiliaryDuplicateStatIdManagerAdapter.count * 24 - count12 + 11;
            int count24 = auxiliaryDuplicateStatIdManagerAdapter.count2 * 24 - count13 + 11;
            int count25 = auxiliaryDuplicateStatIdManagerAdapter.auxiliaryDuplicateStatIdManagerAdapter.count * 24 - count12 + 11;
            int count26 = auxiliaryDuplicateStatIdManagerAdapter.auxiliaryDuplicateStatIdManagerAdapter.count2 * 24 - count13 + 11;
            boolean flag = this.statFileWriter.matchesAuxiliaryDuplicateStatIdMaByAuxiliaryDuplica(auxiliaryDuplicateStatIdManagerAdapter);
            boolean flag2 = this.statFileWriter.matchesAuxiliaryDuplicateStatIdMa(auxiliaryDuplicateStatIdManagerAdapter);
            if (this.statFileWriter.computeCount(auxiliaryDuplicateStatIdManagerAdapter) <= 4) {
               int count27 = -16777216;
               if (flag) {
                  count27 = -6250336;
               } else if (flag2) {
                  count27 = -16711936;
               }

               this.applyIndex(count23, count25, count24, count27);
               this.applyIndexByIndex(count25, count24, count26, count27);
               if (count23 > count25) {
                  this.processAction(count23 - 11 - 7, count24 - 5, 114, 234, 7, 11);
               } else if (count23 < count25) {
                  this.processAction(count23 + 11, count24 - 5, 107, 234, 7, 11);
               } else if (count24 > count26) {
                  this.processAction(count23 - 5, count24 - 11 - 7, 96, 234, 11, 7);
               } else if (count24 < count26) {
                  this.processAction(count23 - 5, count24 + 11, 96, 241, 11, 7);
               }
            }
         }
      }

      AuxiliaryDuplicateStatIdManagerAdapter auxiliaryDuplicateStatIdManagerAdapter2 = null;
      float factor6 = (float)(count - count16) * this.factor3;
      float factor7 = (float)(count11 - count17) * this.factor3;
      RenderHelper.enableGUIStandardItemLighting();
      GlStateManager.applyState3();
      GlStateManager.disableLighting();
      GlStateManager.applyState6();

      for (int index4 = 0; index4 < AchievementTheEnd2Manager.list.size(); index4++) {
         AuxiliaryDuplicateStatIdManagerAdapter auxiliaryDuplicateStatIdManagerAdapter3 = (AuxiliaryDuplicateStatIdManagerAdapter)AchievementTheEnd2Manager.list.get(index4);
         int count28 = auxiliaryDuplicateStatIdManagerAdapter3.count * 24 - count12;
         int count29 = auxiliaryDuplicateStatIdManagerAdapter3.count2 * 24 - count13;
         if (count28 >= -24 && count29 >= -24 && (float)count28 <= 224.0F * this.factor3 && (float)count29 <= 155.0F * this.factor3) {
            int count30 = this.statFileWriter.computeCount(auxiliaryDuplicateStatIdManagerAdapter3);
            if (this.statFileWriter.matchesAuxiliaryDuplicateStatIdMaByAuxiliaryDuplica(auxiliaryDuplicateStatIdManagerAdapter3)) {
               float factor8 = 0.75F;
               GlStateManager.color4f2(factor8, factor8, factor8, 1.0F);
            } else if (this.statFileWriter.matchesAuxiliaryDuplicateStatIdMa(auxiliaryDuplicateStatIdManagerAdapter3)) {
               float factor9 = 1.0F;
               GlStateManager.color4f2(factor9, factor9, factor9, 1.0F);
            } else if (count30 < 3) {
               float factor10 = 0.3F;
               GlStateManager.color4f2(factor10, factor10, factor10, 1.0F);
            } else if (count30 == 3) {
               float factor11 = 0.2F;
               GlStateManager.color4f2(factor11, factor11, factor11, 1.0F);
            } else {
               if (count30 != 4) {
                  continue;
               }

               float factor12 = 0.1F;
               GlStateManager.color4f2(factor12, factor12, factor12, 1.0F);
            }

            this.mc.getTextureManager().applyResourceLocation3(resourceLocation5);
            if (auxiliaryDuplicateStatIdManagerAdapter3.isEnabled3()) {
               this.processAction(count28 - 2, count29 - 2, 26, 202, 26, 26);
            } else {
               this.processAction(count28 - 2, count29 - 2, 0, 202, 26, 26);
            }

            if (!this.statFileWriter.matchesAuxiliaryDuplicateStatIdMa(auxiliaryDuplicateStatIdManagerAdapter3)) {
               float factor13 = 0.1F;
               GlStateManager.color4f2(factor13, factor13, factor13, 1.0F);
               this.renderItem.setEnabled2(false);
            }

            GlStateManager.applyState13();
            GlStateManager.enableLighting();
            this.renderItem.renderItemIntoGUI(auxiliaryDuplicateStatIdManagerAdapter3.itemStack, count28 + 3, count29 + 3);
            GlStateManager.glStateAction3(770, 771);
            GlStateManager.applyState3();
            if (!this.statFileWriter.matchesAuxiliaryDuplicateStatIdMa(auxiliaryDuplicateStatIdManagerAdapter3)) {
               this.renderItem.setEnabled2(true);
            }

            GlStateManager.color4f2(1.0F, 1.0F, 1.0F, 1.0F);
            if (factor6 >= (float)count28 && factor6 <= (float)(count28 + 22) && factor7 >= (float)count29 && factor7 <= (float)(count29 + 22)) {
               auxiliaryDuplicateStatIdManagerAdapter2 = auxiliaryDuplicateStatIdManagerAdapter3;
            }
         }
      }

      GlStateManager.enableDepth();
      GlStateManager.disableBlend();
      GlStateManager.popMatrix();
      GlStateManager.color4f2(1.0F, 1.0F, 1.0F, 1.0F);
      this.mc.getTextureManager().applyResourceLocation3(resourceLocation5);
      this.processAction(count14, count15, 0, 0, this.count6, this.count7);
      this.factor = 0.0F;
      GlStateManager.glDepthFunc(515);
      GlStateManager.enableDepth();
      GlStateManager.disableTexture2D();
      super.applyIndex(count, count11, factor);
      if (auxiliaryDuplicateStatIdManagerAdapter2 != null) {
         String string = auxiliaryDuplicateStatIdManagerAdapter2.getIChatComponent().getString();
         String text = auxiliaryDuplicateStatIdManagerAdapter2.getText();
         int count31 = count + 12;
         int count32 = count11 - 4;
         int count33 = this.statFileWriter.computeCount(auxiliaryDuplicateStatIdManagerAdapter2);
         if (this.statFileWriter.matchesAuxiliaryDuplicateStatIdMa(auxiliaryDuplicateStatIdManagerAdapter2)) {
            int count34 = Math.max(this.fontRenderer.getIndex(string), 120);
            int count35 = this.fontRenderer.computeCount(text, count34);
            if (this.statFileWriter.matchesAuxiliaryDuplicateStatIdMaByAuxiliaryDuplica(auxiliaryDuplicateStatIdManagerAdapter2)) {
               count35 += 12;
            }

            this.applyIndex(count31 - 3, count32 - 3, count31 + count34 + 3, count32 + count35 + 3 + 12, -1073741824, -1073741824);
            this.fontRenderer.applyText(text, count31, count32 + 12, count34, -6250336);
            if (this.statFileWriter.matchesAuxiliaryDuplicateStatIdMaByAuxiliaryDuplica(auxiliaryDuplicateStatIdManagerAdapter2)) {
               this.fontRenderer.getIndex2(StatCollector.getText("achievement.taken", new Object[0]), (float)count31, (float)(count32 + count35 + 4), -7302913);
            }
         } else if (count33 == 3) {
            string = StatCollector.getText("achievement.unknown", new Object[0]);
            int count36 = Math.max(this.fontRenderer.getIndex(string), 120);
            String string2 = new BaseManagerStyleIChatComponentAdapter(
                  "achievement.requires", new Object[]{auxiliaryDuplicateStatIdManagerAdapter2.auxiliaryDuplicateStatIdManagerAdapter.getIChatComponent()}
               )
               .getString();
            int count37 = this.fontRenderer.computeCount(string2, count36);
            this.applyIndex(count31 - 3, count32 - 3, count31 + count36 + 3, count32 + count37 + 12 + 3, -1073741824, -1073741824);
            this.fontRenderer.applyText(string2, count31, count32 + 12, count36, -9416624);
         } else if (count33 < 3) {
            int count38 = Math.max(this.fontRenderer.getIndex(string), 120);
            String string3 = new BaseManagerStyleIChatComponentAdapter(
                  "achievement.requires", new Object[]{auxiliaryDuplicateStatIdManagerAdapter2.auxiliaryDuplicateStatIdManagerAdapter.getIChatComponent()}
               )
               .getString();
            int count39 = this.fontRenderer.computeCount(string3, count38);
            this.applyIndex(count31 - 3, count32 - 3, count31 + count38 + 3, count32 + count39 + 12 + 3, -1073741824, -1073741824);
            this.fontRenderer.applyText(string3, count31, count32 + 12, count38, -9416624);
         } else {
            string = null;
         }

         if (string != null) {
            this.fontRenderer
               .getIndex2(
                  string,
                  (float)count31,
                  (float)count32,
                  this.statFileWriter.matchesAuxiliaryDuplicateStatIdMa(auxiliaryDuplicateStatIdManagerAdapter2) ? (auxiliaryDuplicateStatIdManagerAdapter2.isEnabled3() ? -128 : -1) : (auxiliaryDuplicateStatIdManagerAdapter2.isEnabled3() ? -8355776 : -8355712)
               );
         }
      }

      GlStateManager.disableDepth();
      GlStateManager.applyState13();
      RenderHelper.disableStandardItemLighting();
   }

   @Override
   public boolean isActive() {
      return !this.enabled6;
   }

   @Override
   public void applyAuxiliaryTexturesGuiIconsP(AuxiliaryTexturesGuiIconsPngManagerAdapter auxiliaryTexturesGuiIconsPngManagerAdapter) throws IOException {
      if (!this.enabled6 && auxiliaryTexturesGuiIconsPngManagerAdapter.count2 == 1) {
         GuiScreen guiScreen = this.guiScreen;
         this.mc.displayGuiScreen(guiScreen);
      }
   }

   @Override
   public void applyChar(char character, int count) throws IOException {
      if (count == this.mc.gameSettings.keyBindInventory.getKeyCode()) {
         GuiScreen screen = null;
         this.mc.displayGuiScreen(screen);
         this.mc.updateState21();
      } else {
         super.applyChar(character, count);
      }
   }

   @Override
   public void updateState2() {
      this.mc.getNetHandlerPlayClient().handlePacket(new C16PacketClientStatus(AutoSoupPerformRespawnType.REQUEST_STATS));
      this.list.clear();
      this.list.add(new ValueSliderControl(1, this.width / 2 + 24, this.height / 2 + 74, 80, 20, StatCollector.getText("gui.done", new Object[0])));
   }

   @Override
   public void applyIndex(int count, int count11, float factor) {
      if (this.enabled6) {
         this.renderElement3();
         applyFontRenderer4(this.fontRenderer, StatCollector.getText("multiplayer.downloadingStats", new Object[0]), this.width / 2, this.height / 2, 16777215);
         applyFontRenderer4(
            this.fontRenderer,
            stringArray[(int)(Minecraft.getTimestamp() / 150L % (long)stringArray.length)],
            this.width / 2,
            this.height / 2 + this.fontRenderer.indexOffset * 2,
            16777215
         );
      } else {
         if (MouseUtils.checkCondition(0)) {
            int count12 = (this.width - this.count6) / 2;
            int count13 = (this.height - this.count7) / 2;
            int count14 = count12 + 8;
            int count15 = count13 + 17;
            if ((this.count10 == 0 || this.count10 == 1) && count >= count14 && count < count14 + 224 && count11 >= count15 && count11 < count15 + 155) {
               if (this.count10 == 0) {
                  this.count10 = 1;
               } else {
                  this.value3 = this.value3 - (double)((float)(count - this.count8) * this.factor3);
                  this.value4 = this.value4 - (double)((float)(count11 - this.count9) * this.factor3);
                  this.value5 = this.value = this.value3;
                  this.value6 = this.value2 = this.value4;
               }

               this.count8 = count;
               this.count9 = count11;
            }
         } else {
            this.count10 = 0;
         }

         int dWheel = Mouse.getDWheel();
         float factor2 = this.factor3;
         if (dWheel < 0) {
            this.factor3 += 0.25F;
         } else if (dWheel > 0) {
            this.factor3 -= 0.25F;
         }

         this.factor3 = MathHelper.clamp_float(this.factor3, 1.0F, 2.0F);
         if (this.factor3 != factor2) {
            float factor4 = factor2 * (float)this.count6;
            float factor5 = factor2 * (float)this.count7;
            float factor6 = this.factor3 * (float)this.count6;
            float factor7 = this.factor3 * (float)this.count7;
            this.value3 -= (double)((factor6 - factor4) * 0.5F);
            this.value4 -= (double)((factor7 - factor5) * 0.5F);
            this.value5 = this.value = this.value3;
            this.value6 = this.value2 = this.value4;
         }

         if (this.value5 < (double)count2) {
            this.value5 = (double)count2;
         }

         if (this.value6 < (double)count3) {
            this.value6 = (double)count3;
         }

         if (this.value5 >= (double)count4) {
            this.value5 = (double)(count4 - 1);
         }

         if (this.value6 >= (double)count5) {
            this.value6 = (double)(count5 - 1);
         }

         this.renderElement3();
         this.applyIndexByIndex(count, count11, factor);
         GlStateManager.applyState3();
         GlStateManager.enableDepth();
         this.updateState5();
         GlStateManager.applyState13();
         GlStateManager.disableDepth();
      }
   }
}
