package cc.baritone.utils;

import client.gui.AuxiliaryTexturesGuiIconsPngManagerAdapter;
import com.google.common.collect.Multimap;
import java.io.IOException;
import java.util.Iterator;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;

public class DiagonalCalculator extends GuiScreen {
   public static final int count2 = 61;
   public static final int count3 = 32;
   public static final ResourceLocation resourceLocation5 = new ResourceLocation("wdl:textures/permission_widgets.png");
   public final GuiScreen guiScreen2;
   public AuxiliaryTexturesGuiIconsPngManagerAdapter auxiliaryTexturesGuiIconsPngManagerAdapter2;
   public float factor3;
   public float factor4;
   public static final int count4 = 8;
   public MOVINGPANNINGEnum mOVINGPANNINGEnum = MOVINGPANNINGEnum.PANNING;
   public boolean enabled6;
   public int count5;
   public int count6;
   public int count7;
   public int count8;
   public boolean enabled7;
   public int count9;
   public int count10;
   public static final int count11 = 769532;

   private int computeCountByIndex(int count) {
      return (int)(((float)count - this.factor3) * 8.0F + (float)(this.width / 2));
   }

   public static ResourceLocation getResourceLocation5() {
      return resourceLocation5;
   }

   @Override
   public void applyIndex(int count, int count12, int count13, long value) {
      if (this.enabled7) {
         int count14 = this.count9 - count;
         int count15 = this.count10 - count12;
         this.count9 = count;
         this.count10 = count12;
         this.factor3 += (float)count14 / 8.0F;
         this.factor4 += (float)count15 / 8.0F;
      }
   }

   private void applyAStarPlanner(AStarPlanner aStarPlanner, int count, int count12) {
      int count13 = (aStarPlanner.labelText.hashCode() ^ count) & 16777215;
      int count14 = this.computeCountByIndex(aStarPlanner.count2);
      int count15 = this.computeCount3(aStarPlanner.count);
      int count16 = this.computeCountByIndex(aStarPlanner.count3) + 8 - 1;
      int count17 = this.computeCount3(aStarPlanner.count4) + 8 - 1;
      processAction(count14, count15, count16, count17, count13 + (count12 << 24));
      int count18 = this.computeCount2(count13);
      this.applyIndexByIndex(count14, count15, count17, count18 + (count12 << 24));
      this.applyIndexByIndex(count16, count15, count17, count18 + (count12 << 24));
      this.applyIndex(count14, count16, count15, count18 + (count12 << 24));
      this.applyIndex(count14, count16, count17, count18 + (count12 << 24));
   }

   private int computeCount(int count) {
      return MathHelper.floor_float(((float)count - (float)(this.width / 2)) / 8.0F + this.factor3);
   }

   @Override
   public void applyIndex(int count, int count12, float factor) {
      CouldnOpenHelper.applyIndex(0, 0, this.height, this.width);
      if (this.mOVINGPANNINGEnum == MOVINGPANNINGEnum.REQUESTING) {
         int count13 = this.enabled6 ? this.count5 : this.computeCount(count);
         int count14 = this.enabled6 ? this.count6 : this.computeCount4(count12);
         int count15 = this.computeCount(count);
         int count16 = this.computeCount4(count12);
         AStarPlanner aStarPlanner = new AStarPlanner("", count13, count14, count15, count16);
         int count17 = 127 + (int)(Math.sin((double)Minecraft.getTimestamp() * Math.PI / 5000.0) * 64.0);
         this.applyAStarPlanner(aStarPlanner, 16777215, count17);
      }

      Iterator iterator = DownloadInGeneralManager.getMapAsMap().values().iterator();

      while (iterator.hasNext()) {
         for (AStarPlanner aStarPlanner2 : (Iterable<AStarPlanner>)(Iterable<?>)((Multimap)iterator.next()).values()) {
            this.applyAStarPlanner(aStarPlanner2, 769532, 255);
         }
      }

      for (AStarPlanner aStarPlanner3 : (Iterable<AStarPlanner>)(Iterable<?>)DownloadInGeneralManager.copyOf()) {
         int count18 = 127 + (int)(Math.sin((double)Minecraft.getTimestamp() * Math.PI / 5000.0) * 64.0);
         this.applyAStarPlanner(aStarPlanner3, 8421504, count18);
      }

      int count19 = (int)((Pokechu22WorldDownloaderManager.entityPlayerSP.posX / 16.0 - (double)this.factor3) * 8.0 + (double)(this.width / 2));
      int count20 = (int)((Pokechu22WorldDownloaderManager.entityPlayerSP.posZ / 16.0 - (double)this.factor4) * 8.0 + (double)(this.height / 2));
      this.applyIndex(count19 - 3, count19 + 3, count20, -1);
      this.applyIndexByIndex(count19, count20 - 4, count20 + 4, -1);
      CouldnOpenHelper.applyIndexByIndex(61, 32, 0, 0, this.height, this.width);
      applyFontRenderer4(this.fontRenderer, "Chunk overrides", this.width / 2, 8, 16777215);
      super.applyIndex(count, count12, factor);
      applyFontRenderer4(this.fontRenderer, "§c§lThis is a work in progress.", this.width / 2, this.height / 2, 16777215);
   }

   @Override
   public void processAction(int count, int count12, int count13) throws IOException {
      super.processAction(count, count12, count13);
      if (count12 > 61 && count12 < this.height - 32 && count13 == 0) {
         switch (DiagonalCalculatorMOVINGPANNINGEnumSwitchMap.countArray[this.mOVINGPANNINGEnum.ordinal()]) {
            case 1:
               this.enabled7 = true;
               this.count9 = count;
               this.count10 = count12;
               break;
            case 2:
               if (this.enabled6) {
                  this.count7 = this.computeCount(count);
                  this.count8 = this.computeCount4(count12);
                  DownloadInGeneralManager.add(new AStarPlanner("", this.count5, this.count6, this.count7, this.count8));
                  this.enabled6 = false;
               } else {
                  this.count5 = this.computeCount(count);
                  this.count6 = this.computeCount4(count12);
                  this.enabled6 = true;
               }

               this.mc.getMcSoundHandler().applyISound2(PositionedSoundRecord.createButtonPress(new ResourceLocation("gui.button.press"), 1.0F));
         }
      }
   }

   public static MOVINGPANNINGEnum resolveMOVINGPANNINGEnum(DiagonalCalculator diagonalCalculator) {
      return diagonalCalculator.mOVINGPANNINGEnum;
   }

   private int computeCount4(int count) {
      return MathHelper.floor_float(((float)count - (float)(this.height / 2)) / 8.0F + this.factor4);
   }

   @Override
   public void applyIndex(int count, int count12, int count13) {
      super.applyIndex(count, count12, count13);
      if (count13 == 0) {
         this.enabled7 = false;
      }
   }

   @Override
   public void updateState2() {
      this.list.add(new MOVINGPANNINGEnumAuxiliaryTexturesGuiIc(this, 0, this.width / 2 - 155, 18, MOVINGPANNINGEnum.PANNING));
      this.list.add(new MOVINGPANNINGEnumAuxiliaryTexturesGuiIc(this, 1, this.width / 2 - 130, 18, MOVINGPANNINGEnum.REQUESTING));
      this.list.add(new RuntimeComponent8(this, 2, this.width / 2 - 105, 18, MOVINGPANNINGEnum.ERASING));
      this.list.add(new DiagonalCalculatorMOVINGPANNINGEnumAuxil(this, 3, this.width / 2 - 80, 18, MOVINGPANNINGEnum.MOVING));
      this.list.add(new AuxiliaryTexturesGuiIconsPngManagerAdapter(4, this.width / 2 - 80, 18, 80, 20, "Send request"));
      this.auxiliaryTexturesGuiIconsPngManagerAdapter2 = new AuxiliaryTexturesGuiIconsPngManagerAdapter(
         6, this.width / 2 + 5, 18, 150, 20, "Start download in these ranges"
      );
      this.auxiliaryTexturesGuiIconsPngManagerAdapter2.enabled3 = DownloadInGeneralManager.isActive6();
      this.list.add(this.auxiliaryTexturesGuiIconsPngManagerAdapter2);
      this.list
         .add(new AuxiliaryTexturesGuiIconsPngManagerAdapter(100, this.width / 2 - 100, this.height - 29, StatCollector.getText("gui.done", new Object[0])));
      this.list
         .add(
            new AuxiliaryTexturesGuiIconsPngManagerAdapter(
               200, this.width / 2 - 155, 39, 100, 20, StatCollector.getText("wdl.gui.permissions.current", new Object[0])
            )
         );
      this.list
         .add(
            new AuxiliaryTexturesGuiIconsPngManagerAdapter(
               201, this.width / 2 - 50, 39, 100, 20, StatCollector.getText("wdl.gui.permissions.request", new Object[0])
            )
         );
      this.list
         .add(
            new AuxiliaryTexturesGuiIconsPngManagerAdapter(
               202, this.width / 2 + 55, 39, 100, 20, StatCollector.getText("wdl.gui.permissions.overrides", new Object[0])
            )
         );
   }

   public DiagonalCalculator(GuiScreen screen) {
      this.guiScreen2 = screen;
      if (Pokechu22WorldDownloaderManager.entityPlayerSP != null) {
         this.factor3 = (float)Pokechu22WorldDownloaderManager.entityPlayerSP.count7;
         this.factor4 = (float)Pokechu22WorldDownloaderManager.entityPlayerSP.count8;
      }
   }

   private int computeCount2(int count) {
      int count12 = count >> 16 & 0xFF;
      int count13 = count >> 8 & 0xFF;
      int count14 = count & 0xFF;
      int count15 = count12 / 2;
      int count16 = count13 / 2;
      int count17 = count14 / 2;
      return (count15 << 16) + (count16 << 8) + count17;
   }

   private int computeCount3(int count) {
      return (int)(((float)count - this.factor4) * 8.0F + (float)(this.height / 2));
   }

   @Override
   public void applyAuxiliaryTexturesGuiIconsP(AuxiliaryTexturesGuiIconsPngManagerAdapter auxiliaryTexturesGuiIconsPngManagerAdapter) throws IOException {
      if (auxiliaryTexturesGuiIconsPngManagerAdapter.count2 == 0) {
         this.mOVINGPANNINGEnum = MOVINGPANNINGEnum.PANNING;
      }

      if (auxiliaryTexturesGuiIconsPngManagerAdapter.count2 == 1) {
         this.mOVINGPANNINGEnum = MOVINGPANNINGEnum.REQUESTING;
         this.enabled6 = false;
      }

      if (auxiliaryTexturesGuiIconsPngManagerAdapter.count2 == 4) {
         DownloadInGeneralManager.updateState2();
      }

      if (auxiliaryTexturesGuiIconsPngManagerAdapter.count2 == 6) {
         if (!DownloadInGeneralManager.isActive6()) {
            auxiliaryTexturesGuiIconsPngManagerAdapter.enabled3 = false;
            return;
         }

         Pokechu22WorldDownloaderManager.updateState3();
      }

      if (auxiliaryTexturesGuiIconsPngManagerAdapter.count2 == 100) {
         GuiScreen guiScreen = this.guiScreen2;
         this.mc.displayGuiScreen(guiScreen);
      }

      if (auxiliaryTexturesGuiIconsPngManagerAdapter.count2 == 200) {
         Minecraft mc = this.mc;
         HeuristicProvider heuristicProvider = new HeuristicProvider(this.guiScreen2);
         mc.displayGuiScreen(heuristicProvider);
      }

      if (auxiliaryTexturesGuiIconsPngManagerAdapter.count2 == 201) {
         Minecraft mc2 = this.mc;
         LThisWorkGuiScreen lThisWorkGuiScreen = new LThisWorkGuiScreen(this.guiScreen2);
         mc2.displayGuiScreen(lThisWorkGuiScreen);
      }
   }
}
