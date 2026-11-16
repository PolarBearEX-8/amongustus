package net.minecraft.client.gui;

import client.event.MinecraftPreUpdateDispatcher;
import client.gui.AuxiliaryTexturesGuiIconsPngManagerAdapter;
import client.gui.EnhancedGuiScreenAdapter;
import client.gui.GuiYesAuxiliaryTexturesGuiIc;
import client.gui.InternalTexturesGuiIconsPngManagerAdapter;
import client.gui.InterpolationValidatAuxiliaryTexturesGuiIc;
import client.gui.TexturesGuiIconsPngManager;
import client.render.WorldRenderer;
import client.util.AltItemArrayEntrySplineCalculator;
import client.util.AuxiliaryGsonEntry;
import client.util.BiomeIDIsOutOfBoundsManager;
import client.util.Callback15;
import client.util.Callback15Controller20;
import client.util.Controller20;
import client.util.Controller20Impl;
import client.util.DefaultVertexFormats;
import client.util.InterpolationValidator;
import client.util.OptionsCustomizeTitlFilter;
import client.util.PredicateController20;
import client.util.RuntimeComponentDelegate;
import com.google.common.base.Predicate;
import com.google.common.primitives.Floats;
import java.io.IOException;
import java.util.Random;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.MathHelper;
import net.minecraft.util.StatCollector;

public class OptionsCustomizeTitleGuiScreen extends GuiScreen implements InterpolationValidator, Callback15 {
   public EnhancedGuiScreenAdapter enhancedGuiScreenAdapter;
   public String labelText = "Customize World Settings";
   public String labelText2 = "Page 1 of 3";
   public String labelText3 = "Basic Settings";
   public String[] stringArray = new String[4];
   public AltItemArrayEntrySplineCalculator altItemArrayEntrySplineCalculator;
   public AuxiliaryTexturesGuiIconsPngManagerAdapter auxiliaryTexturesGuiIconsPngManagerAdapter2;
   public AuxiliaryTexturesGuiIconsPngManagerAdapter auxiliaryTexturesGuiIconsPngManagerAdapter3;
   public AuxiliaryTexturesGuiIconsPngManagerAdapter auxiliaryTexturesGuiIconsPngManagerAdapter4;
   public AuxiliaryTexturesGuiIconsPngManagerAdapter auxiliaryTexturesGuiIconsPngManagerAdapter5;
   public AuxiliaryTexturesGuiIconsPngManagerAdapter auxiliaryTexturesGuiIconsPngManagerAdapter6;
   public AuxiliaryTexturesGuiIconsPngManagerAdapter auxiliaryTexturesGuiIconsPngManagerAdapter7;
   public AuxiliaryTexturesGuiIconsPngManagerAdapter auxiliaryTexturesGuiIconsPngManagerAdapter8;
   public AuxiliaryTexturesGuiIconsPngManagerAdapter auxiliaryTexturesGuiIconsPngManagerAdapter9;
   public boolean enabled6 = false;
   public int count2 = 0;
   public boolean enabled7 = false;
   public Predicate predicate = new OptionsCustomizeTitlFilter(this);
   public AuxiliaryGsonEntry auxiliaryGsonEntry = new AuxiliaryGsonEntry();
   public AuxiliaryGsonEntry auxiliaryGsonEntry2;
   public Random random = new Random();

   @Override
   public void updateState2() {
      int count = 0;
      int count3 = 0;
      if (this.altItemArrayEntrySplineCalculator != null) {
         count = this.altItemArrayEntrySplineCalculator.getCount4();
         count3 = this.altItemArrayEntrySplineCalculator.getCountAsIndex();
      }

      this.labelText = StatCollector.getText("options.customizeTitle", new Object[0]);
      this.list.clear();
      this.list
         .add(
            this.auxiliaryTexturesGuiIconsPngManagerAdapter5 = new AuxiliaryTexturesGuiIconsPngManagerAdapter(
               302, 20, 5, 80, 20, StatCollector.getText("createWorld.customize.custom.prev", new Object[0])
            )
         );
      this.list
         .add(
            this.auxiliaryTexturesGuiIconsPngManagerAdapter6 = new AuxiliaryTexturesGuiIconsPngManagerAdapter(
               303, this.width - 100, 5, 80, 20, StatCollector.getText("createWorld.customize.custom.next", new Object[0])
            )
         );
      this.list
         .add(
            this.auxiliaryTexturesGuiIconsPngManagerAdapter4 = new AuxiliaryTexturesGuiIconsPngManagerAdapter(
               304, this.width / 2 - 187, this.height - 27, 90, 20, StatCollector.getText("createWorld.customize.custom.defaults", new Object[0])
            )
         );
      this.list
         .add(
            this.auxiliaryTexturesGuiIconsPngManagerAdapter3 = new AuxiliaryTexturesGuiIconsPngManagerAdapter(
               301, this.width / 2 - 92, this.height - 27, 90, 20, StatCollector.getText("createWorld.customize.custom.randomize", new Object[0])
            )
         );
      this.list
         .add(
            this.auxiliaryTexturesGuiIconsPngManagerAdapter9 = new AuxiliaryTexturesGuiIconsPngManagerAdapter(
               305, this.width / 2 + 3, this.height - 27, 90, 20, StatCollector.getText("createWorld.customize.custom.presets", new Object[0])
            )
         );
      this.list
         .add(
            this.auxiliaryTexturesGuiIconsPngManagerAdapter2 = new AuxiliaryTexturesGuiIconsPngManagerAdapter(
               300, this.width / 2 + 98, this.height - 27, 90, 20, StatCollector.getText("gui.done", new Object[0])
            )
         );
      this.auxiliaryTexturesGuiIconsPngManagerAdapter4.enabled3 = this.enabled6;
      this.auxiliaryTexturesGuiIconsPngManagerAdapter7 = new AuxiliaryTexturesGuiIconsPngManagerAdapter(
         306, this.width / 2 - 55, 160, 50, 20, StatCollector.getText("gui.yes", new Object[0])
      );
      this.auxiliaryTexturesGuiIconsPngManagerAdapter7.enabled2 = false;
      this.list.add(this.auxiliaryTexturesGuiIconsPngManagerAdapter7);
      this.auxiliaryTexturesGuiIconsPngManagerAdapter8 = new AuxiliaryTexturesGuiIconsPngManagerAdapter(
         307, this.width / 2 + 5, 160, 50, 20, StatCollector.getText("gui.no", new Object[0])
      );
      this.auxiliaryTexturesGuiIconsPngManagerAdapter8.enabled2 = false;
      this.list.add(this.auxiliaryTexturesGuiIconsPngManagerAdapter8);
      if (this.count2 != 0) {
         this.auxiliaryTexturesGuiIconsPngManagerAdapter7.enabled2 = true;
         this.auxiliaryTexturesGuiIconsPngManagerAdapter8.enabled2 = true;
      }

      this.renderElement6();
      if (count != 0) {
         this.altItemArrayEntrySplineCalculator.applyIndex2(count);
         this.altItemArrayEntrySplineCalculator.applyIndex(count3);
         this.renderElement();
      }
   }

   public OptionsCustomizeTitleGuiScreen(GuiScreen screen, String text) {
      this.enhancedGuiScreenAdapter = (EnhancedGuiScreenAdapter)screen;
      this.applyText(text);
   }

   private void setEnabledByFlag(boolean enabledByFlag) {
      this.enabled6 = enabledByFlag;
      this.auxiliaryTexturesGuiIconsPngManagerAdapter4.enabled3 = enabledByFlag;
   }

   private void applyIndex(int count) {
      this.count2 = count;
      this.setEnabled(true);
   }

   public String getTextAsText() {
      return this.auxiliaryGsonEntry2.toString().replace("\n", "");
   }

   @Override
   public void processAction(int count, int count3, int count4) throws IOException {
      super.processAction(count, count3, count4);
      if (this.count2 == 0 && !this.enabled7) {
         this.altItemArrayEntrySplineCalculator.checkIndex(count, count3, count4);
      }
   }

   public void applyText(String text) {
      if (text != null && text.length() != 0) {
         this.auxiliaryGsonEntry2 = AuxiliaryGsonEntry.resolveAuxiliaryGsonEntry(text);
      } else {
         this.auxiliaryGsonEntry2 = new AuxiliaryGsonEntry();
      }
   }

   @Override
   public void applyChar(char character, int count) throws IOException {
      super.applyChar(character, count);
      if (this.count2 == 0) {
         switch (count) {
            case 200:
               this.applyFloat(1.0F);
               break;
            case 208:
               this.applyFloat(-1.0F);
               break;
            default:
               this.altItemArrayEntrySplineCalculator.applyChar(character, count);
         }
      }
   }

   @Override
   public void applyIndex(int count, int count3, float factor) {
      this.renderElement3();
      this.altItemArrayEntrySplineCalculator.applyIndex(count, count3, factor);
      applyFontRenderer4(this.fontRenderer, this.labelText, this.width / 2, 2, 16777215);
      applyFontRenderer4(this.fontRenderer, this.labelText2, this.width / 2, 12, 16777215);
      applyFontRenderer4(this.fontRenderer, this.labelText3, this.width / 2, 22, 16777215);
      super.applyIndex(count, count3, factor);
      if (this.count2 != 0) {
         processAction(0, 0, this.width, this.height, Integer.MIN_VALUE);
         this.applyIndex(this.width / 2 - 91, this.width / 2 + 90, 99, -2039584);
         this.applyIndex(this.width / 2 - 91, this.width / 2 + 90, 185, -6250336);
         this.applyIndexByIndex(this.width / 2 - 91, 99, 185, -2039584);
         this.applyIndexByIndex(this.width / 2 + 90, 99, 185, -6250336);
         GlStateManager.applyState3();
         GlStateManager.applyState20();
         Tessellator tessellator = Tessellator.getTessellator();
         WorldRenderer worldRenderer = tessellator.getWorldRenderer();
         this.mc.getTextureManager().applyResourceLocation3(resourceLocation);
         GlStateManager.color4f2(1.0F, 1.0F, 1.0F, 1.0F);
         worldRenderer.updateAuxiliaryLoggerEntry(7, DefaultVertexFormats.auxiliaryLoggerEntry6);
         worldRenderer.getWorldRenderer((double)(this.width / 2 - 90), 185.0, 0.0).getWorldRenderer6(0.0, 2.65625).resolveWorldRenderer(64, 64, 64, 64).updateState();
         worldRenderer.getWorldRenderer((double)(this.width / 2 + 90), 185.0, 0.0).getWorldRenderer6(5.625, 2.65625).resolveWorldRenderer(64, 64, 64, 64).updateState();
         worldRenderer.getWorldRenderer((double)(this.width / 2 + 90), 100.0, 0.0).getWorldRenderer6(5.625, 0.0).resolveWorldRenderer(64, 64, 64, 64).updateState();
         worldRenderer.getWorldRenderer((double)(this.width / 2 - 90), 100.0, 0.0).getWorldRenderer6(0.0, 0.0).resolveWorldRenderer(64, 64, 64, 64).updateState();
         tessellator.updateState();
         applyFontRenderer4(this.fontRenderer, StatCollector.getText("createWorld.customize.custom.confirmTitle", new Object[0]), this.width / 2, 105, 16777215);
         applyFontRenderer4(this.fontRenderer, StatCollector.getText("createWorld.customize.custom.confirm1", new Object[0]), this.width / 2, 125, 16777215);
         applyFontRenderer4(this.fontRenderer, StatCollector.getText("createWorld.customize.custom.confirm2", new Object[0]), this.width / 2, 135, 16777215);
         this.auxiliaryTexturesGuiIconsPngManagerAdapter7.applyMinecraftByMinecraft(this.mc, count, count3);
         this.auxiliaryTexturesGuiIconsPngManagerAdapter8.applyMinecraftByMinecraft(this.mc, count, count3);
      }
   }

   private void renderElement5() throws IOException {
      switch (this.count2) {
         case 300:
            this.applyAuxiliaryTexturesGuiIconsP((GuiYesAuxiliaryTexturesGuiIc)this.altItemArrayEntrySplineCalculator.resolveTexturesGuiIconsPngManager(300));
            break;
         case 304:
            this.renderElement7();
      }

      this.count2 = 0;
      this.enabled7 = true;
      this.setEnabled(false);
   }

   private void setEnabled(boolean enabled) {
      this.auxiliaryTexturesGuiIconsPngManagerAdapter7.enabled2 = enabled;
      this.auxiliaryTexturesGuiIconsPngManagerAdapter8.enabled2 = enabled;
      this.auxiliaryTexturesGuiIconsPngManagerAdapter3.enabled3 = !enabled;
      this.auxiliaryTexturesGuiIconsPngManagerAdapter2.enabled3 = !enabled;
      this.auxiliaryTexturesGuiIconsPngManagerAdapter5.enabled3 = !enabled;
      this.auxiliaryTexturesGuiIconsPngManagerAdapter6.enabled3 = !enabled;
      this.auxiliaryTexturesGuiIconsPngManagerAdapter4.enabled3 = this.enabled6 && !enabled;
      this.auxiliaryTexturesGuiIconsPngManagerAdapter9.enabled3 = !enabled;
      this.altItemArrayEntrySplineCalculator.setEnabled(!enabled);
   }

   @Override
   public String resolveText(int count, String text, float factor) {
      return text + ": " + this.resolveText(count, factor);
   }

   @Override
   public void applyAuxiliaryTexturesGuiIconsP(AuxiliaryTexturesGuiIconsPngManagerAdapter auxiliaryTexturesGuiIconsPngManagerAdapter) throws IOException {
      if (auxiliaryTexturesGuiIconsPngManagerAdapter.enabled3) {
         switch (auxiliaryTexturesGuiIconsPngManagerAdapter.count2) {
            case 300:
               this.enhancedGuiScreenAdapter.labelText = this.auxiliaryGsonEntry2.toString();
               EnhancedGuiScreenAdapter enhancedGuiScreenAdapter2 = this.enhancedGuiScreenAdapter;
               this.mc.displayGuiScreen(enhancedGuiScreenAdapter2);
               break;
            case 301:
               for (int index = 0; index < this.altItemArrayEntrySplineCalculator.size(); index++) {
                  MinecraftPreUpdateDispatcher byIndex = this.altItemArrayEntrySplineCalculator.getByIndex(index);
                  TexturesGuiIconsPngManager texturesGuiIconsPngManager = byIndex.getTexturesGuiIconsPngManager();
                  if (texturesGuiIconsPngManager instanceof AuxiliaryTexturesGuiIconsPngManagerAdapter) {
                     AuxiliaryTexturesGuiIconsPngManagerAdapter auxiliaryTexturesGuiIconsPngManagerAdapter10 = (AuxiliaryTexturesGuiIconsPngManagerAdapter)texturesGuiIconsPngManager;
                     if (auxiliaryTexturesGuiIconsPngManagerAdapter10 instanceof InterpolationValidatAuxiliaryTexturesGuiIc) {
                        float factor = ((InterpolationValidatAuxiliaryTexturesGuiIc)auxiliaryTexturesGuiIconsPngManagerAdapter10).getFactor2() * (0.75F + this.random.nextFloat() * 0.5F)
                           + (this.random.nextFloat() * 0.1F - 0.05F);
                        ((InterpolationValidatAuxiliaryTexturesGuiIc)auxiliaryTexturesGuiIconsPngManagerAdapter10).applyFloat(MathHelper.clamp_float(factor, 0.0F, 1.0F));
                     } else if (auxiliaryTexturesGuiIconsPngManagerAdapter10 instanceof GuiYesAuxiliaryTexturesGuiIc) {
                        ((GuiYesAuxiliaryTexturesGuiIc)auxiliaryTexturesGuiIconsPngManagerAdapter10).setEnabled(this.random.nextBoolean());
                     }
                  }

                  TexturesGuiIconsPngManager texturesGuiIconsPngManager2 = byIndex.getTexturesGuiIconsPngManager2();
                  if (texturesGuiIconsPngManager2 instanceof AuxiliaryTexturesGuiIconsPngManagerAdapter) {
                     AuxiliaryTexturesGuiIconsPngManagerAdapter auxiliaryTexturesGuiIconsPngManagerAdapter11 = (AuxiliaryTexturesGuiIconsPngManagerAdapter)texturesGuiIconsPngManager2;
                     if (auxiliaryTexturesGuiIconsPngManagerAdapter11 instanceof InterpolationValidatAuxiliaryTexturesGuiIc) {
                        float factor2 = ((InterpolationValidatAuxiliaryTexturesGuiIc)auxiliaryTexturesGuiIconsPngManagerAdapter11).getFactor2() * (0.75F + this.random.nextFloat() * 0.5F)
                           + (this.random.nextFloat() * 0.1F - 0.05F);
                        ((InterpolationValidatAuxiliaryTexturesGuiIc)auxiliaryTexturesGuiIconsPngManagerAdapter11).applyFloat(MathHelper.clamp_float(factor2, 0.0F, 1.0F));
                     } else if (auxiliaryTexturesGuiIconsPngManagerAdapter11 instanceof GuiYesAuxiliaryTexturesGuiIc) {
                        ((GuiYesAuxiliaryTexturesGuiIc)auxiliaryTexturesGuiIconsPngManagerAdapter11).setEnabled(this.random.nextBoolean());
                     }
                  }
               }

               return;
            case 302:
               this.altItemArrayEntrySplineCalculator.updateState5();
               this.renderElement();
               break;
            case 303:
               this.altItemArrayEntrySplineCalculator.updateState6();
               this.renderElement();
               break;
            case 304:
               if (this.enabled6) {
                  this.applyIndex(304);
               }
               break;
            case 305:
               Minecraft mc = this.mc;
               TexturesGuiGuiScreen texturesGuiGuiScreen = new TexturesGuiGuiScreen(this);
               mc.displayGuiScreen(texturesGuiGuiScreen);
               break;
            case 306:
               this.renderElement5();
               break;
            case 307:
               this.count2 = 0;
               this.renderElement5();
         }
      }
   }

   @Override
   public void updateState3() throws IOException {
      super.updateState3();
      this.altItemArrayEntrySplineCalculator.updateState();
   }

   @Override
   public void applyIndex(int count, boolean flag) {
      switch (count) {
         case 148:
            this.auxiliaryGsonEntry2.enabled = flag;
            break;
         case 149:
            this.auxiliaryGsonEntry2.enabled2 = flag;
            break;
         case 150:
            this.auxiliaryGsonEntry2.enabled3 = flag;
            break;
         case 151:
            this.auxiliaryGsonEntry2.enabled4 = flag;
            break;
         case 152:
            this.auxiliaryGsonEntry2.enabled5 = flag;
            break;
         case 153:
            this.auxiliaryGsonEntry2.enabled6 = flag;
            break;
         case 154:
            this.auxiliaryGsonEntry2.enabled7 = flag;
            break;
         case 155:
            this.auxiliaryGsonEntry2.enabled8 = flag;
            break;
         case 156:
            this.auxiliaryGsonEntry2.enabled9 = flag;
            break;
         case 161:
            this.auxiliaryGsonEntry2.enabled10 = flag;
            break;
         case 210:
            this.auxiliaryGsonEntry2.enabled11 = flag;
      }

      if (!this.auxiliaryGsonEntry2.equals(this.auxiliaryGsonEntry)) {
         this.setEnabledByFlag(true);
      }
   }

   private void applyFloat(float factor) {
      TexturesGuiIconsPngManager texturesGuiIconsPngManager = this.altItemArrayEntrySplineCalculator.getTexturesGuiIconsPngManager();
      if (texturesGuiIconsPngManager instanceof InternalTexturesGuiIconsPngManagerAdapter) {
         float factor2 = factor;
         if (GuiScreen.isActiveAsFlag()) {
            factor2 = factor * 0.1F;
            if (GuiScreen.isActive3()) {
               factor2 *= 0.1F;
            }
         } else if (GuiScreen.isActive3()) {
            factor2 = factor * 10.0F;
            if (GuiScreen.isActive2()) {
               factor2 *= 10.0F;
            }
         }

         InternalTexturesGuiIconsPngManagerAdapter internalTexturesGuiIconsPngManagerAdapter = (InternalTexturesGuiIconsPngManagerAdapter)texturesGuiIconsPngManager;
         Float factor3 = Floats.tryParse(internalTexturesGuiIconsPngManagerAdapter.getLabelText());
         if (factor3 != null) {
            Float factor4 = factor3 + factor2;
            int count = internalTexturesGuiIconsPngManagerAdapter.getCount12();
            String text = this.resolveText(internalTexturesGuiIconsPngManagerAdapter.getCount12(), factor4);
            internalTexturesGuiIconsPngManagerAdapter.applyText(text);
            this.applyText(count, text);
         }
      }
   }

   @Override
   public void applyIndex(int count, float factor) {
      switch (count) {
         case 100:
            this.auxiliaryGsonEntry2.factor = factor;
            break;
         case 101:
            this.auxiliaryGsonEntry2.factor2 = factor;
            break;
         case 102:
            this.auxiliaryGsonEntry2.factor3 = factor;
            break;
         case 103:
            this.auxiliaryGsonEntry2.factor4 = factor;
            break;
         case 104:
            this.auxiliaryGsonEntry2.factor5 = factor;
            break;
         case 105:
            this.auxiliaryGsonEntry2.factor6 = factor;
            break;
         case 106:
            this.auxiliaryGsonEntry2.factor7 = factor;
            break;
         case 107:
            this.auxiliaryGsonEntry2.factor8 = factor;
            break;
         case 108:
            this.auxiliaryGsonEntry2.factor9 = factor;
            break;
         case 109:
            this.auxiliaryGsonEntry2.factor10 = factor;
            break;
         case 110:
            this.auxiliaryGsonEntry2.factor11 = factor;
            break;
         case 111:
            this.auxiliaryGsonEntry2.factor12 = factor;
            break;
         case 112:
            this.auxiliaryGsonEntry2.factor13 = factor;
            break;
         case 113:
            this.auxiliaryGsonEntry2.factor14 = factor;
            break;
         case 114:
            this.auxiliaryGsonEntry2.factor15 = factor;
            break;
         case 115:
            this.auxiliaryGsonEntry2.factor16 = factor;
         case 116:
         case 117:
         case 118:
         case 119:
         case 120:
         case 121:
         case 122:
         case 123:
         case 124:
         case 125:
         case 126:
         case 127:
         case 128:
         case 129:
         case 130:
         case 131:
         case 132:
         case 133:
         case 134:
         case 135:
         case 136:
         case 137:
         case 138:
         case 139:
         case 140:
         case 141:
         case 142:
         case 143:
         case 144:
         case 145:
         case 146:
         case 147:
         case 148:
         case 149:
         case 150:
         case 151:
         case 152:
         case 153:
         case 154:
         case 155:
         case 156:
         case 161:
         case 188:
         default:
            break;
         case 157:
            this.auxiliaryGsonEntry2.count = (int)factor;
            break;
         case 158:
            this.auxiliaryGsonEntry2.count2 = (int)factor;
            break;
         case 159:
            this.auxiliaryGsonEntry2.count3 = (int)factor;
            break;
         case 160:
            this.auxiliaryGsonEntry2.count4 = (int)factor;
            break;
         case 162:
            this.auxiliaryGsonEntry2.count5 = (int)factor;
            break;
         case 163:
            this.auxiliaryGsonEntry2.count6 = (int)factor;
            break;
         case 164:
            this.auxiliaryGsonEntry2.count7 = (int)factor;
            break;
         case 165:
            this.auxiliaryGsonEntry2.count8 = (int)factor;
            break;
         case 166:
            this.auxiliaryGsonEntry2.count9 = (int)factor;
            break;
         case 167:
            this.auxiliaryGsonEntry2.count10 = (int)factor;
            break;
         case 168:
            this.auxiliaryGsonEntry2.count11 = (int)factor;
            break;
         case 169:
            this.auxiliaryGsonEntry2.count12 = (int)factor;
            break;
         case 170:
            this.auxiliaryGsonEntry2.count13 = (int)factor;
            break;
         case 171:
            this.auxiliaryGsonEntry2.count14 = (int)factor;
            break;
         case 172:
            this.auxiliaryGsonEntry2.count15 = (int)factor;
            break;
         case 173:
            this.auxiliaryGsonEntry2.count16 = (int)factor;
            break;
         case 174:
            this.auxiliaryGsonEntry2.count17 = (int)factor;
            break;
         case 175:
            this.auxiliaryGsonEntry2.count18 = (int)factor;
            break;
         case 176:
            this.auxiliaryGsonEntry2.count19 = (int)factor;
            break;
         case 177:
            this.auxiliaryGsonEntry2.count20 = (int)factor;
            break;
         case 178:
            this.auxiliaryGsonEntry2.count21 = (int)factor;
            break;
         case 179:
            this.auxiliaryGsonEntry2.count22 = (int)factor;
            break;
         case 180:
            this.auxiliaryGsonEntry2.count23 = (int)factor;
            break;
         case 181:
            this.auxiliaryGsonEntry2.count24 = (int)factor;
            break;
         case 182:
            this.auxiliaryGsonEntry2.count25 = (int)factor;
            break;
         case 183:
            this.auxiliaryGsonEntry2.count26 = (int)factor;
            break;
         case 184:
            this.auxiliaryGsonEntry2.count27 = (int)factor;
            break;
         case 185:
            this.auxiliaryGsonEntry2.count28 = (int)factor;
            break;
         case 186:
            this.auxiliaryGsonEntry2.count29 = (int)factor;
            break;
         case 187:
            this.auxiliaryGsonEntry2.count30 = (int)factor;
            break;
         case 189:
            this.auxiliaryGsonEntry2.count31 = (int)factor;
            break;
         case 190:
            this.auxiliaryGsonEntry2.count32 = (int)factor;
            break;
         case 191:
            this.auxiliaryGsonEntry2.count33 = (int)factor;
            break;
         case 192:
            this.auxiliaryGsonEntry2.count34 = (int)factor;
            break;
         case 193:
            this.auxiliaryGsonEntry2.count35 = (int)factor;
            break;
         case 194:
            this.auxiliaryGsonEntry2.count36 = (int)factor;
            break;
         case 195:
            this.auxiliaryGsonEntry2.count37 = (int)factor;
            break;
         case 196:
            this.auxiliaryGsonEntry2.count38 = (int)factor;
            break;
         case 197:
            this.auxiliaryGsonEntry2.count39 = (int)factor;
            break;
         case 198:
            this.auxiliaryGsonEntry2.count40 = (int)factor;
            break;
         case 199:
            this.auxiliaryGsonEntry2.count41 = (int)factor;
            break;
         case 200:
            this.auxiliaryGsonEntry2.count42 = (int)factor;
            break;
         case 201:
            this.auxiliaryGsonEntry2.count43 = (int)factor;
            break;
         case 202:
            this.auxiliaryGsonEntry2.count44 = (int)factor;
            break;
         case 203:
            this.auxiliaryGsonEntry2.count45 = (int)factor;
            break;
         case 204:
            this.auxiliaryGsonEntry2.count46 = (int)factor;
            break;
         case 205:
            this.auxiliaryGsonEntry2.count47 = (int)factor;
            break;
         case 206:
            this.auxiliaryGsonEntry2.count48 = (int)factor;
            break;
         case 207:
            this.auxiliaryGsonEntry2.count49 = (int)factor;
            break;
         case 208:
            this.auxiliaryGsonEntry2.count50 = (int)factor;
            break;
         case 209:
            this.auxiliaryGsonEntry2.count51 = (int)factor;
      }

      if (count >= 100 && count < 116) {
         TexturesGuiIconsPngManager texturesGuiIconsPngManager = this.altItemArrayEntrySplineCalculator.resolveTexturesGuiIconsPngManager(count - 100 + 132);
         if (texturesGuiIconsPngManager != null) {
            ((InternalTexturesGuiIconsPngManagerAdapter)texturesGuiIconsPngManager).applyText(this.resolveText(count, factor));
         }
      }

      if (!this.auxiliaryGsonEntry2.equals(this.auxiliaryGsonEntry)) {
         this.setEnabledByFlag(true);
      }
   }

   @Override
   public void applyIndex(int count, int count3, int count4) {
      super.applyIndex(count, count3, count4);
      if (this.enabled7) {
         this.enabled7 = false;
      } else if (this.count2 == 0) {
         this.altItemArrayEntrySplineCalculator.checkIndexByIndex(count, count3, count4);
      }
   }

   private void renderElement() {
      this.auxiliaryTexturesGuiIconsPngManagerAdapter5.enabled3 = this.altItemArrayEntrySplineCalculator.getCount4() != 0;
      this.auxiliaryTexturesGuiIconsPngManagerAdapter6.enabled3 = this.altItemArrayEntrySplineCalculator.getCount4()
         != this.altItemArrayEntrySplineCalculator.getCount3() - 1;
      this.labelText2 = StatCollector.getText(
         "book.pageIndicator", new Object[]{this.altItemArrayEntrySplineCalculator.getCount4() + 1, this.altItemArrayEntrySplineCalculator.getCount3()}
      );
      this.labelText3 = this.stringArray[this.altItemArrayEntrySplineCalculator.getCount4()];
      this.auxiliaryTexturesGuiIconsPngManagerAdapter3.enabled3 = this.altItemArrayEntrySplineCalculator.getCount4()
         != this.altItemArrayEntrySplineCalculator.getCount3() - 1;
   }

   @Override
   public void applyText(int count, String text) {
      float factor = 0.0F;

      try {
         factor = Float.parseFloat(text);
      } catch (NumberFormatException numberFormatException) {
      }

      float factor2 = 0.0F;
      switch (count) {
         case 132:
            factor2 = this.auxiliaryGsonEntry2.factor = MathHelper.clamp_float(factor, 1.0F, 5000.0F);
            break;
         case 133:
            factor2 = this.auxiliaryGsonEntry2.factor2 = MathHelper.clamp_float(factor, 1.0F, 5000.0F);
            break;
         case 134:
            factor2 = this.auxiliaryGsonEntry2.factor3 = MathHelper.clamp_float(factor, 1.0F, 5000.0F);
            break;
         case 135:
            factor2 = this.auxiliaryGsonEntry2.factor4 = MathHelper.clamp_float(factor, 1.0F, 2000.0F);
            break;
         case 136:
            factor2 = this.auxiliaryGsonEntry2.factor5 = MathHelper.clamp_float(factor, 1.0F, 2000.0F);
            break;
         case 137:
            factor2 = this.auxiliaryGsonEntry2.factor6 = MathHelper.clamp_float(factor, 0.01F, 20.0F);
            break;
         case 138:
            factor2 = this.auxiliaryGsonEntry2.factor7 = MathHelper.clamp_float(factor, 1.0F, 25.0F);
            break;
         case 139:
            factor2 = this.auxiliaryGsonEntry2.factor8 = MathHelper.clamp_float(factor, 1.0F, 6000.0F);
            break;
         case 140:
            factor2 = this.auxiliaryGsonEntry2.factor9 = MathHelper.clamp_float(factor, 1.0F, 6000.0F);
            break;
         case 141:
            factor2 = this.auxiliaryGsonEntry2.factor10 = MathHelper.clamp_float(factor, 0.01F, 50.0F);
            break;
         case 142:
            factor2 = this.auxiliaryGsonEntry2.factor11 = MathHelper.clamp_float(factor, 1.0F, 5000.0F);
            break;
         case 143:
            factor2 = this.auxiliaryGsonEntry2.factor12 = MathHelper.clamp_float(factor, 1.0F, 5000.0F);
            break;
         case 144:
            factor2 = this.auxiliaryGsonEntry2.factor13 = MathHelper.clamp_float(factor, 1.0F, 20.0F);
            break;
         case 145:
            factor2 = this.auxiliaryGsonEntry2.factor14 = MathHelper.clamp_float(factor, 0.0F, 20.0F);
            break;
         case 146:
            factor2 = this.auxiliaryGsonEntry2.factor15 = MathHelper.clamp_float(factor, 1.0F, 20.0F);
            break;
         case 147:
            factor2 = this.auxiliaryGsonEntry2.factor16 = MathHelper.clamp_float(factor, 0.0F, 20.0F);
      }

      if (factor2 != factor && factor != 0.0F) {
         ((InternalTexturesGuiIconsPngManagerAdapter)this.altItemArrayEntrySplineCalculator.resolveTexturesGuiIconsPngManager(count))
            .applyText(this.resolveText(count, factor2));
      }

      ((InterpolationValidatAuxiliaryTexturesGuiIc)this.altItemArrayEntrySplineCalculator.resolveTexturesGuiIconsPngManager(count - 132 + 100))
         .applyFloat(factor2, false);
      if (!this.auxiliaryGsonEntry2.equals(this.auxiliaryGsonEntry)) {
         this.setEnabledByFlag(true);
      }
   }

   private void renderElement7() {
      this.auxiliaryGsonEntry2.updateState();
      this.renderElement6();
      this.setEnabledByFlag(false);
   }

   private void renderElement6() {
      Controller20[] controller20Array = new Controller20[]{
         new Callback15Controller20(
            160,
            StatCollector.getText("createWorld.customize.custom.seaLevel", new Object[0]),
            true,
            this,
            1.0F,
            255.0F,
            (float)this.auxiliaryGsonEntry2.count4
         ),
         new Controller20Impl(148, StatCollector.getText("createWorld.customize.custom.useCaves", new Object[0]), true, this.auxiliaryGsonEntry2.enabled),
         new Controller20Impl(150, StatCollector.getText("createWorld.customize.custom.useStrongholds", new Object[0]), true, this.auxiliaryGsonEntry2.enabled3),
         new Controller20Impl(151, StatCollector.getText("createWorld.customize.custom.useVillages", new Object[0]), true, this.auxiliaryGsonEntry2.enabled4),
         new Controller20Impl(152, StatCollector.getText("createWorld.customize.custom.useMineShafts", new Object[0]), true, this.auxiliaryGsonEntry2.enabled5),
         new Controller20Impl(153, StatCollector.getText("createWorld.customize.custom.useTemples", new Object[0]), true, this.auxiliaryGsonEntry2.enabled6),
         new Controller20Impl(210, StatCollector.getText("createWorld.customize.custom.useMonuments", new Object[0]), true, this.auxiliaryGsonEntry2.enabled11),
         new Controller20Impl(154, StatCollector.getText("createWorld.customize.custom.useRavines", new Object[0]), true, this.auxiliaryGsonEntry2.enabled7),
         new Controller20Impl(149, StatCollector.getText("createWorld.customize.custom.useDungeons", new Object[0]), true, this.auxiliaryGsonEntry2.enabled2),
         new Callback15Controller20(
            157,
            StatCollector.getText("createWorld.customize.custom.dungeonChance", new Object[0]),
            true,
            this,
            1.0F,
            100.0F,
            (float)this.auxiliaryGsonEntry2.count
         ),
         new Controller20Impl(155, StatCollector.getText("createWorld.customize.custom.useWaterLakes", new Object[0]), true, this.auxiliaryGsonEntry2.enabled8),
         new Callback15Controller20(
            158,
            StatCollector.getText("createWorld.customize.custom.waterLakeChance", new Object[0]),
            true,
            this,
            1.0F,
            100.0F,
            (float)this.auxiliaryGsonEntry2.count2
         ),
         new Controller20Impl(156, StatCollector.getText("createWorld.customize.custom.useLavaLakes", new Object[0]), true, this.auxiliaryGsonEntry2.enabled9),
         new Callback15Controller20(
            159,
            StatCollector.getText("createWorld.customize.custom.lavaLakeChance", new Object[0]),
            true,
            this,
            10.0F,
            100.0F,
            (float)this.auxiliaryGsonEntry2.count3
         ),
         new Controller20Impl(161, StatCollector.getText("createWorld.customize.custom.useLavaOceans", new Object[0]), true, this.auxiliaryGsonEntry2.enabled10),
         new Callback15Controller20(
            162,
            StatCollector.getText("createWorld.customize.custom.fixedBiome", new Object[0]),
            true,
            this,
            -1.0F,
            37.0F,
            (float)this.auxiliaryGsonEntry2.count5
         ),
         new Callback15Controller20(
            163, StatCollector.getText("createWorld.customize.custom.biomeSize", new Object[0]), true, this, 1.0F, 8.0F, (float)this.auxiliaryGsonEntry2.count6
         ),
         new Callback15Controller20(
            164, StatCollector.getText("createWorld.customize.custom.riverSize", new Object[0]), true, this, 1.0F, 5.0F, (float)this.auxiliaryGsonEntry2.count7
         )
      };
      Controller20[] controller20Array2 = new Controller20[]{
         new RuntimeComponentDelegate(416, StatCollector.getText("tile.dirt.name", new Object[0]), false),
         null,
         new Callback15Controller20(
            165, StatCollector.getText("createWorld.customize.custom.size", new Object[0]), false, this, 1.0F, 50.0F, (float)this.auxiliaryGsonEntry2.count8
         ),
         new Callback15Controller20(
            166, StatCollector.getText("createWorld.customize.custom.count", new Object[0]), false, this, 0.0F, 40.0F, (float)this.auxiliaryGsonEntry2.count9
         ),
         new Callback15Controller20(
            167,
            StatCollector.getText("createWorld.customize.custom.minHeight", new Object[0]),
            false,
            this,
            0.0F,
            255.0F,
            (float)this.auxiliaryGsonEntry2.count10
         ),
         new Callback15Controller20(
            168,
            StatCollector.getText("createWorld.customize.custom.maxHeight", new Object[0]),
            false,
            this,
            0.0F,
            255.0F,
            (float)this.auxiliaryGsonEntry2.count11
         ),
         new RuntimeComponentDelegate(417, StatCollector.getText("tile.gravel.name", new Object[0]), false),
         null,
         new Callback15Controller20(
            169, StatCollector.getText("createWorld.customize.custom.size", new Object[0]), false, this, 1.0F, 50.0F, (float)this.auxiliaryGsonEntry2.count12
         ),
         new Callback15Controller20(
            170, StatCollector.getText("createWorld.customize.custom.count", new Object[0]), false, this, 0.0F, 40.0F, (float)this.auxiliaryGsonEntry2.count13
         ),
         new Callback15Controller20(
            171,
            StatCollector.getText("createWorld.customize.custom.minHeight", new Object[0]),
            false,
            this,
            0.0F,
            255.0F,
            (float)this.auxiliaryGsonEntry2.count14
         ),
         new Callback15Controller20(
            172,
            StatCollector.getText("createWorld.customize.custom.maxHeight", new Object[0]),
            false,
            this,
            0.0F,
            255.0F,
            (float)this.auxiliaryGsonEntry2.count15
         ),
         new RuntimeComponentDelegate(418, StatCollector.getText("tile.stone.granite.name", new Object[0]), false),
         null,
         new Callback15Controller20(
            173, StatCollector.getText("createWorld.customize.custom.size", new Object[0]), false, this, 1.0F, 50.0F, (float)this.auxiliaryGsonEntry2.count16
         ),
         new Callback15Controller20(
            174, StatCollector.getText("createWorld.customize.custom.count", new Object[0]), false, this, 0.0F, 40.0F, (float)this.auxiliaryGsonEntry2.count17
         ),
         new Callback15Controller20(
            175,
            StatCollector.getText("createWorld.customize.custom.minHeight", new Object[0]),
            false,
            this,
            0.0F,
            255.0F,
            (float)this.auxiliaryGsonEntry2.count18
         ),
         new Callback15Controller20(
            176,
            StatCollector.getText("createWorld.customize.custom.maxHeight", new Object[0]),
            false,
            this,
            0.0F,
            255.0F,
            (float)this.auxiliaryGsonEntry2.count19
         ),
         new RuntimeComponentDelegate(419, StatCollector.getText("tile.stone.diorite.name", new Object[0]), false),
         null,
         new Callback15Controller20(
            177, StatCollector.getText("createWorld.customize.custom.size", new Object[0]), false, this, 1.0F, 50.0F, (float)this.auxiliaryGsonEntry2.count20
         ),
         new Callback15Controller20(
            178, StatCollector.getText("createWorld.customize.custom.count", new Object[0]), false, this, 0.0F, 40.0F, (float)this.auxiliaryGsonEntry2.count21
         ),
         new Callback15Controller20(
            179,
            StatCollector.getText("createWorld.customize.custom.minHeight", new Object[0]),
            false,
            this,
            0.0F,
            255.0F,
            (float)this.auxiliaryGsonEntry2.count22
         ),
         new Callback15Controller20(
            180,
            StatCollector.getText("createWorld.customize.custom.maxHeight", new Object[0]),
            false,
            this,
            0.0F,
            255.0F,
            (float)this.auxiliaryGsonEntry2.count23
         ),
         new RuntimeComponentDelegate(420, StatCollector.getText("tile.stone.andesite.name", new Object[0]), false),
         null,
         new Callback15Controller20(
            181, StatCollector.getText("createWorld.customize.custom.size", new Object[0]), false, this, 1.0F, 50.0F, (float)this.auxiliaryGsonEntry2.count24
         ),
         new Callback15Controller20(
            182, StatCollector.getText("createWorld.customize.custom.count", new Object[0]), false, this, 0.0F, 40.0F, (float)this.auxiliaryGsonEntry2.count25
         ),
         new Callback15Controller20(
            183,
            StatCollector.getText("createWorld.customize.custom.minHeight", new Object[0]),
            false,
            this,
            0.0F,
            255.0F,
            (float)this.auxiliaryGsonEntry2.count26
         ),
         new Callback15Controller20(
            184,
            StatCollector.getText("createWorld.customize.custom.maxHeight", new Object[0]),
            false,
            this,
            0.0F,
            255.0F,
            (float)this.auxiliaryGsonEntry2.count27
         ),
         new RuntimeComponentDelegate(421, StatCollector.getText("tile.oreCoal.name", new Object[0]), false),
         null,
         new Callback15Controller20(
            185, StatCollector.getText("createWorld.customize.custom.size", new Object[0]), false, this, 1.0F, 50.0F, (float)this.auxiliaryGsonEntry2.count28
         ),
         new Callback15Controller20(
            186, StatCollector.getText("createWorld.customize.custom.count", new Object[0]), false, this, 0.0F, 40.0F, (float)this.auxiliaryGsonEntry2.count29
         ),
         new Callback15Controller20(
            187,
            StatCollector.getText("createWorld.customize.custom.minHeight", new Object[0]),
            false,
            this,
            0.0F,
            255.0F,
            (float)this.auxiliaryGsonEntry2.count30
         ),
         new Callback15Controller20(
            189,
            StatCollector.getText("createWorld.customize.custom.maxHeight", new Object[0]),
            false,
            this,
            0.0F,
            255.0F,
            (float)this.auxiliaryGsonEntry2.count31
         ),
         new RuntimeComponentDelegate(422, StatCollector.getText("tile.oreIron.name", new Object[0]), false),
         null,
         new Callback15Controller20(
            190, StatCollector.getText("createWorld.customize.custom.size", new Object[0]), false, this, 1.0F, 50.0F, (float)this.auxiliaryGsonEntry2.count32
         ),
         new Callback15Controller20(
            191, StatCollector.getText("createWorld.customize.custom.count", new Object[0]), false, this, 0.0F, 40.0F, (float)this.auxiliaryGsonEntry2.count33
         ),
         new Callback15Controller20(
            192,
            StatCollector.getText("createWorld.customize.custom.minHeight", new Object[0]),
            false,
            this,
            0.0F,
            255.0F,
            (float)this.auxiliaryGsonEntry2.count34
         ),
         new Callback15Controller20(
            193,
            StatCollector.getText("createWorld.customize.custom.maxHeight", new Object[0]),
            false,
            this,
            0.0F,
            255.0F,
            (float)this.auxiliaryGsonEntry2.count35
         ),
         new RuntimeComponentDelegate(423, StatCollector.getText("tile.oreGold.name", new Object[0]), false),
         null,
         new Callback15Controller20(
            194, StatCollector.getText("createWorld.customize.custom.size", new Object[0]), false, this, 1.0F, 50.0F, (float)this.auxiliaryGsonEntry2.count36
         ),
         new Callback15Controller20(
            195, StatCollector.getText("createWorld.customize.custom.count", new Object[0]), false, this, 0.0F, 40.0F, (float)this.auxiliaryGsonEntry2.count37
         ),
         new Callback15Controller20(
            196,
            StatCollector.getText("createWorld.customize.custom.minHeight", new Object[0]),
            false,
            this,
            0.0F,
            255.0F,
            (float)this.auxiliaryGsonEntry2.count38
         ),
         new Callback15Controller20(
            197,
            StatCollector.getText("createWorld.customize.custom.maxHeight", new Object[0]),
            false,
            this,
            0.0F,
            255.0F,
            (float)this.auxiliaryGsonEntry2.count39
         ),
         new RuntimeComponentDelegate(424, StatCollector.getText("tile.oreRedstone.name", new Object[0]), false),
         null,
         new Callback15Controller20(
            198, StatCollector.getText("createWorld.customize.custom.size", new Object[0]), false, this, 1.0F, 50.0F, (float)this.auxiliaryGsonEntry2.count40
         ),
         new Callback15Controller20(
            199, StatCollector.getText("createWorld.customize.custom.count", new Object[0]), false, this, 0.0F, 40.0F, (float)this.auxiliaryGsonEntry2.count41
         ),
         new Callback15Controller20(
            200,
            StatCollector.getText("createWorld.customize.custom.minHeight", new Object[0]),
            false,
            this,
            0.0F,
            255.0F,
            (float)this.auxiliaryGsonEntry2.count42
         ),
         new Callback15Controller20(
            201,
            StatCollector.getText("createWorld.customize.custom.maxHeight", new Object[0]),
            false,
            this,
            0.0F,
            255.0F,
            (float)this.auxiliaryGsonEntry2.count43
         ),
         new RuntimeComponentDelegate(425, StatCollector.getText("tile.oreDiamond.name", new Object[0]), false),
         null,
         new Callback15Controller20(
            202, StatCollector.getText("createWorld.customize.custom.size", new Object[0]), false, this, 1.0F, 50.0F, (float)this.auxiliaryGsonEntry2.count44
         ),
         new Callback15Controller20(
            203, StatCollector.getText("createWorld.customize.custom.count", new Object[0]), false, this, 0.0F, 40.0F, (float)this.auxiliaryGsonEntry2.count45
         ),
         new Callback15Controller20(
            204,
            StatCollector.getText("createWorld.customize.custom.minHeight", new Object[0]),
            false,
            this,
            0.0F,
            255.0F,
            (float)this.auxiliaryGsonEntry2.count46
         ),
         new Callback15Controller20(
            205,
            StatCollector.getText("createWorld.customize.custom.maxHeight", new Object[0]),
            false,
            this,
            0.0F,
            255.0F,
            (float)this.auxiliaryGsonEntry2.count47
         ),
         new RuntimeComponentDelegate(426, StatCollector.getText("tile.oreLapis.name", new Object[0]), false),
         null,
         new Callback15Controller20(
            206, StatCollector.getText("createWorld.customize.custom.size", new Object[0]), false, this, 1.0F, 50.0F, (float)this.auxiliaryGsonEntry2.count48
         ),
         new Callback15Controller20(
            207, StatCollector.getText("createWorld.customize.custom.count", new Object[0]), false, this, 0.0F, 40.0F, (float)this.auxiliaryGsonEntry2.count49
         ),
         new Callback15Controller20(
            208,
            StatCollector.getText("createWorld.customize.custom.center", new Object[0]),
            false,
            this,
            0.0F,
            255.0F,
            (float)this.auxiliaryGsonEntry2.count50
         ),
         new Callback15Controller20(
            209,
            StatCollector.getText("createWorld.customize.custom.spread", new Object[0]),
            false,
            this,
            0.0F,
            255.0F,
            (float)this.auxiliaryGsonEntry2.count51
         )
      };
      Controller20[] controller20Array3 = new Controller20[]{
         new Callback15Controller20(
            100,
            StatCollector.getText("createWorld.customize.custom.mainNoiseScaleX", new Object[0]),
            false,
            this,
            1.0F,
            5000.0F,
            this.auxiliaryGsonEntry2.factor
         ),
         new Callback15Controller20(
            101,
            StatCollector.getText("createWorld.customize.custom.mainNoiseScaleY", new Object[0]),
            false,
            this,
            1.0F,
            5000.0F,
            this.auxiliaryGsonEntry2.factor2
         ),
         new Callback15Controller20(
            102,
            StatCollector.getText("createWorld.customize.custom.mainNoiseScaleZ", new Object[0]),
            false,
            this,
            1.0F,
            5000.0F,
            this.auxiliaryGsonEntry2.factor3
         ),
         new Callback15Controller20(
            103,
            StatCollector.getText("createWorld.customize.custom.depthNoiseScaleX", new Object[0]),
            false,
            this,
            1.0F,
            2000.0F,
            this.auxiliaryGsonEntry2.factor4
         ),
         new Callback15Controller20(
            104,
            StatCollector.getText("createWorld.customize.custom.depthNoiseScaleZ", new Object[0]),
            false,
            this,
            1.0F,
            2000.0F,
            this.auxiliaryGsonEntry2.factor5
         ),
         new Callback15Controller20(
            105,
            StatCollector.getText("createWorld.customize.custom.depthNoiseScaleExponent", new Object[0]),
            false,
            this,
            0.01F,
            20.0F,
            this.auxiliaryGsonEntry2.factor6
         ),
         new Callback15Controller20(
            106, StatCollector.getText("createWorld.customize.custom.baseSize", new Object[0]), false, this, 1.0F, 25.0F, this.auxiliaryGsonEntry2.factor7
         ),
         new Callback15Controller20(
            107,
            StatCollector.getText("createWorld.customize.custom.coordinateScale", new Object[0]),
            false,
            this,
            1.0F,
            6000.0F,
            this.auxiliaryGsonEntry2.factor8
         ),
         new Callback15Controller20(
            108, StatCollector.getText("createWorld.customize.custom.heightScale", new Object[0]), false, this, 1.0F, 6000.0F, this.auxiliaryGsonEntry2.factor9
         ),
         new Callback15Controller20(
            109, StatCollector.getText("createWorld.customize.custom.stretchY", new Object[0]), false, this, 0.01F, 50.0F, this.auxiliaryGsonEntry2.factor10
         ),
         new Callback15Controller20(
            110,
            StatCollector.getText("createWorld.customize.custom.upperLimitScale", new Object[0]),
            false,
            this,
            1.0F,
            5000.0F,
            this.auxiliaryGsonEntry2.factor11
         ),
         new Callback15Controller20(
            111,
            StatCollector.getText("createWorld.customize.custom.lowerLimitScale", new Object[0]),
            false,
            this,
            1.0F,
            5000.0F,
            this.auxiliaryGsonEntry2.factor12
         ),
         new Callback15Controller20(
            112,
            StatCollector.getText("createWorld.customize.custom.biomeDepthWeight", new Object[0]),
            false,
            this,
            1.0F,
            20.0F,
            this.auxiliaryGsonEntry2.factor13
         ),
         new Callback15Controller20(
            113,
            StatCollector.getText("createWorld.customize.custom.biomeDepthOffset", new Object[0]),
            false,
            this,
            0.0F,
            20.0F,
            this.auxiliaryGsonEntry2.factor14
         ),
         new Callback15Controller20(
            114,
            StatCollector.getText("createWorld.customize.custom.biomeScaleWeight", new Object[0]),
            false,
            this,
            1.0F,
            20.0F,
            this.auxiliaryGsonEntry2.factor15
         ),
         new Callback15Controller20(
            115,
            StatCollector.getText("createWorld.customize.custom.biomeScaleOffset", new Object[0]),
            false,
            this,
            0.0F,
            20.0F,
            this.auxiliaryGsonEntry2.factor16
         )
      };
      Controller20[] controller20Array4 = new Controller20[]{
         new RuntimeComponentDelegate(400, StatCollector.getText("createWorld.customize.custom.mainNoiseScaleX", new Object[0]) + ":", false),
         new PredicateController20(132, String.format("%5.3f", this.auxiliaryGsonEntry2.factor), false, this.predicate),
         new RuntimeComponentDelegate(401, StatCollector.getText("createWorld.customize.custom.mainNoiseScaleY", new Object[0]) + ":", false),
         new PredicateController20(133, String.format("%5.3f", this.auxiliaryGsonEntry2.factor2), false, this.predicate),
         new RuntimeComponentDelegate(402, StatCollector.getText("createWorld.customize.custom.mainNoiseScaleZ", new Object[0]) + ":", false),
         new PredicateController20(134, String.format("%5.3f", this.auxiliaryGsonEntry2.factor3), false, this.predicate),
         new RuntimeComponentDelegate(403, StatCollector.getText("createWorld.customize.custom.depthNoiseScaleX", new Object[0]) + ":", false),
         new PredicateController20(135, String.format("%5.3f", this.auxiliaryGsonEntry2.factor4), false, this.predicate),
         new RuntimeComponentDelegate(404, StatCollector.getText("createWorld.customize.custom.depthNoiseScaleZ", new Object[0]) + ":", false),
         new PredicateController20(136, String.format("%5.3f", this.auxiliaryGsonEntry2.factor5), false, this.predicate),
         new RuntimeComponentDelegate(405, StatCollector.getText("createWorld.customize.custom.depthNoiseScaleExponent", new Object[0]) + ":", false),
         new PredicateController20(137, String.format("%2.3f", this.auxiliaryGsonEntry2.factor6), false, this.predicate),
         new RuntimeComponentDelegate(406, StatCollector.getText("createWorld.customize.custom.baseSize", new Object[0]) + ":", false),
         new PredicateController20(138, String.format("%2.3f", this.auxiliaryGsonEntry2.factor7), false, this.predicate),
         new RuntimeComponentDelegate(407, StatCollector.getText("createWorld.customize.custom.coordinateScale", new Object[0]) + ":", false),
         new PredicateController20(139, String.format("%5.3f", this.auxiliaryGsonEntry2.factor8), false, this.predicate),
         new RuntimeComponentDelegate(408, StatCollector.getText("createWorld.customize.custom.heightScale", new Object[0]) + ":", false),
         new PredicateController20(140, String.format("%5.3f", this.auxiliaryGsonEntry2.factor9), false, this.predicate),
         new RuntimeComponentDelegate(409, StatCollector.getText("createWorld.customize.custom.stretchY", new Object[0]) + ":", false),
         new PredicateController20(141, String.format("%2.3f", this.auxiliaryGsonEntry2.factor10), false, this.predicate),
         new RuntimeComponentDelegate(410, StatCollector.getText("createWorld.customize.custom.upperLimitScale", new Object[0]) + ":", false),
         new PredicateController20(142, String.format("%5.3f", this.auxiliaryGsonEntry2.factor11), false, this.predicate),
         new RuntimeComponentDelegate(411, StatCollector.getText("createWorld.customize.custom.lowerLimitScale", new Object[0]) + ":", false),
         new PredicateController20(143, String.format("%5.3f", this.auxiliaryGsonEntry2.factor12), false, this.predicate),
         new RuntimeComponentDelegate(412, StatCollector.getText("createWorld.customize.custom.biomeDepthWeight", new Object[0]) + ":", false),
         new PredicateController20(144, String.format("%2.3f", this.auxiliaryGsonEntry2.factor13), false, this.predicate),
         new RuntimeComponentDelegate(413, StatCollector.getText("createWorld.customize.custom.biomeDepthOffset", new Object[0]) + ":", false),
         new PredicateController20(145, String.format("%2.3f", this.auxiliaryGsonEntry2.factor14), false, this.predicate),
         new RuntimeComponentDelegate(414, StatCollector.getText("createWorld.customize.custom.biomeScaleWeight", new Object[0]) + ":", false),
         new PredicateController20(146, String.format("%2.3f", this.auxiliaryGsonEntry2.factor15), false, this.predicate),
         new RuntimeComponentDelegate(415, StatCollector.getText("createWorld.customize.custom.biomeScaleOffset", new Object[0]) + ":", false),
         new PredicateController20(147, String.format("%2.3f", this.auxiliaryGsonEntry2.factor16), false, this.predicate)
      };
      this.altItemArrayEntrySplineCalculator = new AltItemArrayEntrySplineCalculator(
         this.mc, this.width, this.height, 32, this.height - 32, 25, this, new Controller20[][]{controller20Array, controller20Array2, controller20Array3, controller20Array4}
      );

      for (int index = 0; index < 4; index++) {
         this.stringArray[index] = StatCollector.getText("createWorld.customize.custom.page" + index, new Object[0]);
      }

      this.renderElement();
   }

   private String resolveText(int count, float factor) {
      switch (count) {
         case 100:
         case 101:
         case 102:
         case 103:
         case 104:
         case 107:
         case 108:
         case 110:
         case 111:
         case 132:
         case 133:
         case 134:
         case 135:
         case 136:
         case 139:
         case 140:
         case 142:
         case 143:
            return String.format("%5.3f", factor);
         case 105:
         case 106:
         case 109:
         case 112:
         case 113:
         case 114:
         case 115:
         case 137:
         case 138:
         case 141:
         case 144:
         case 145:
         case 146:
         case 147:
            return String.format("%2.3f", factor);
         case 116:
         case 117:
         case 118:
         case 119:
         case 120:
         case 121:
         case 122:
         case 123:
         case 124:
         case 125:
         case 126:
         case 127:
         case 128:
         case 129:
         case 130:
         case 131:
         case 148:
         case 149:
         case 150:
         case 151:
         case 152:
         case 153:
         case 154:
         case 155:
         case 156:
         case 157:
         case 158:
         case 159:
         case 160:
         case 161:
         default:
            return String.format("%d", (int)factor);
         case 162:
            if (factor < 0.0F) {
               return StatCollector.getText("gui.all", new Object[0]);
            } else if ((int)factor >= BiomeIDIsOutOfBoundsManager.biomeIDIsOutOfBoundsManager38.count) {
               BiomeIDIsOutOfBoundsManager biomeIDIsOutOfBoundsManager = BiomeIDIsOutOfBoundsManager.getBiomeIDIsOutOfBoundsManagerArray()[(int)factor + 2];
               return biomeIDIsOutOfBoundsManager != null ? biomeIDIsOutOfBoundsManager.labelText : "?";
            } else {
               BiomeIDIsOutOfBoundsManager biomeIDIsOutOfBoundsManager2 = BiomeIDIsOutOfBoundsManager.getBiomeIDIsOutOfBoundsManagerArray()[(int)factor];
               return biomeIDIsOutOfBoundsManager2 != null ? biomeIDIsOutOfBoundsManager2.labelText : "?";
            }
      }
   }
}
