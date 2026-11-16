package client.util;

import client.event.PassiveListener;
import client.render.WorldRenderer;
import java.util.ArrayList;
import java.util.List;
import net.labymod.ModelData;
import net.labymod.SettingOverlay;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.ResourceLocation;

public class DirectFloatHolder {
   public float factor3;
   public FailedToLoadMaskFromManagerEntryAdapter failedToLoadMaskFromManagerEntryAdapter;
   public static ListenerHandler listenerHandler = new ListenerHandler();
   public static final List list = new ArrayList();
   public float factor2;
   public float factor4;
   public ModelData modelData;
   public float factor;
   public AuxiliaryFloatEntryBridge auxiliaryFloatEntryBridge;
   public EnhancedListEntry[] enhancedListEntryArray;
   public CosmeticsItemData cosmeticsItemData;

   public void applyCosmeticsItemData(
      CosmeticsItemData cosmeticsItemData2,
      FailedToLoadMaskFromManagerEntryAdapter failedToLoadMaskFromManagerEntryAdapter2,
      EnhancedListEntry[] enhancedListEntryArray2,
      ModelData modelData2,
      AuxiliaryFloatEntryBridge auxiliaryFloatEntryBridge2,
      float posX,
      float posX2,
      float posY,
      float posZ
   ) {
      this.failedToLoadMaskFromManagerEntryAdapter = failedToLoadMaskFromManagerEntryAdapter2;
      this.enhancedListEntryArray = enhancedListEntryArray2;
      this.modelData = modelData2;
      this.cosmeticsItemData = cosmeticsItemData2;
      this.auxiliaryFloatEntryBridge = auxiliaryFloatEntryBridge2;
      this.factor2 = posX;
      this.factor3 = posX2;
      this.factor = posY;
      this.factor4 = posZ;
      GlStateManager.disableAlpha();
      GlStateManager.disableLighting();

      for (ListenerAdapter23 listenerAdapter : (Iterable<ListenerAdapter23>)(Iterable<?>)cosmeticsItemData2.getGenericListEntry().list) {
         float factor5 = 1.0F;
         float factor6 = 1.0F;
         float factor7 = 1.0F;
         float factor8 = 1.0F;
         this.applyListenerAdapter23(listenerAdapter, factor8, factor7, factor6, factor5);
      }

      GlStateManager.applyState8();
      GlStateManager.enableLighting();
   }

   private void applyWorldRenderer(WorldRenderer worldRenderer, ComparableEntryArrayEntryAdapter comparableEntryArrayEntryAdapter, float posX, float posX2, float posY, float posZ) {
      listenerHandler.applyComparableEntryArrayEntryAByComparableEntryA(comparableEntryArrayEntryAdapter);
      listenerHandler.applyComparableEntryArrayEntryA(comparableEntryArrayEntryAdapter);
      listenerHandler.applyComparableEntryArrayEntryA2(comparableEntryArrayEntryAdapter);

      for (ComparableEntryAdapter comparableEntryAdapter : comparableEntryArrayEntryAdapter.comparableEntryAdapterArray) {
         listenerHandler.applyComparableEntryAdapter(comparableEntryAdapter);
         StaticFloatEntry staticFloatEntry = comparableEntryAdapter.staticFloatEntry;
         if ((comparableEntryArrayEntryAdapter.staticFloatEntry.factor3 == 0.0F || comparableEntryArrayEntryAdapter.staticFloatEntry.factor == 0.0F) && staticFloatEntry.getFactor2() < 0.0F) {
            staticFloatEntry.factor2 *= -1.0F;
         }

         if ((comparableEntryArrayEntryAdapter.staticFloatEntry.factor2 == 0.0F || comparableEntryArrayEntryAdapter.staticFloatEntry.factor == 0.0F) && staticFloatEntry.getFactor3() < 0.0F) {
            staticFloatEntry.factor3 *= -1.0F;
         }

         if ((comparableEntryArrayEntryAdapter.staticFloatEntry.factor2 == 0.0F || comparableEntryArrayEntryAdapter.staticFloatEntry.factor3 == 0.0F) && staticFloatEntry.getFactor() < 0.0F) {
            staticFloatEntry.factor *= -1.0F;
         }

         for (IndirectFloatHolder indirectFloatHolder : comparableEntryAdapter.indirectFloatHolderArray) {
            listenerHandler.applyIndirectFloatHolder(indirectFloatHolder);
            StaticFloatEntry staticFloatEntry2 = indirectFloatHolder.staticFloatEntry;
            worldRenderer.getWorldRenderer((double)staticFloatEntry2.getFactor2(), (double)staticFloatEntry2.getFactor3(), (double)staticFloatEntry2.getFactor())
               .getWorldRenderer6((double)indirectFloatHolder.factor2, (double)indirectFloatHolder.factor)
               .resolveWorldRenderer(posX, posX2, posY, posZ)
               .resolveWorldRenderer(staticFloatEntry.getFactor2(), staticFloatEntry.getFactor3(), staticFloatEntry.getFactor())
               .updateState();
         }
      }
   }

   private void applyListenerAdapter23(ListenerAdapter23 listenerAdapter, float posX, float posX2, float posY, float posZ) {
      float factor5 = posX;
      float factor6 = posX2;
      float factor7 = posY;
      float factor8 = posZ;
      int count2 = listenerAdapter.count;
      if (count2 != -1) {
         EnhancedListEntry enhancedListEntry = this.enhancedListEntryArray[count2];
         SettingOverlay settingOverlay = this.modelData.model[count2] != null ? this.modelData.model[count2] : FailedToLoadMaskFromManagerEntryAdapter.settingOverlay;
         if (!enhancedListEntry.isEnabled() || !settingOverlay.visible) {
            return;
         }

         PassiveListener passiveListener = FailedToLoadMaskFromManagerEntryAdapter.passiveListener;
         passiveListener.updateState2();
         AuxiliaryFloatEntryBridge auxiliaryFloatEntryBridge2 = this.auxiliaryFloatEntryBridge;
         float factor9 = this.failedToLoadMaskFromManagerEntryAdapter.computeFactor(enhancedListEntry, auxiliaryFloatEntryBridge2, true, passiveListener);
         this.failedToLoadMaskFromManagerEntryAdapter.applyPrimaryFloatEntry(enhancedListEntry, factor9, settingOverlay, passiveListener);
         float factor10 = this.factor4;
         float factor11 = this.factor;
         float factor12 = this.factor3;
         float factor13 = this.factor2;
         this.failedToLoadMaskFromManagerEntryAdapter.applyStandardHashMapEntry(enhancedListEntry, factor13, factor12, factor11, factor10, settingOverlay, passiveListener);
         if (enhancedListEntry.isEmpty()) {
            FailedToLoadMaskFromManagerEntryAdapter failedToLoadMaskFromManagerEntryAdapter2 = this.failedToLoadMaskFromManagerEntryAdapter;
            List list2 = enhancedListEntry.getList();
            ModelData modelData2 = this.modelData;
            float factor14 = this.factor2;
            float factor15 = this.factor3;
            float factor16 = this.factor;
            float factor17 = this.factor4;
            String text = this.cosmeticsItemData.getText();
            float factor18 = factor17;
            float factor19 = factor16;
            float factor20 = factor15;
            float factor21 = factor14;
            Object entry = null;
            ModelData modelData3 = modelData2;
            List list3 = list2;
            failedToLoadMaskFromManagerEntryAdapter2.applyList(list3, modelData3, (AuxiliaryFloatEntryBridge)entry, factor21, factor20, factor19, factor18, text, false);
            Minecraft.getMinecraft()
               .getTextureManager()
               .applyResourceLocation3((ResourceLocation)FailedToLoadMaskFromManagerEntryAdapter.hashMap3.get(this.cosmeticsItemData.getLabelText()));
         }

         int count3 = settingOverlay.color != 0 ? settingOverlay.color : enhancedListEntry.getCount2();
         if (count3 == 1) {
            count3 = InterpolationParser.count;
         }

         factor5 = (float)(count3 >> 16 & 0xFF) / 255.0F;
         factor6 = (float)(count3 >> 8 & 0xFF) / 255.0F;
         factor7 = (float)(count3 & 0xFF) / 255.0F;
         factor8 = (float)(count3 >> 24 & 0xFF) / 255.0F;
         if (settingOverlay.illum) {
            GlStateManager.applyState3();
            Minecraft.getMinecraft().entityRenderer.updateState5();
            factor8 = (float)((int)((InternalFloatArrayEntry.computeFactorByFloat(this.factor2 / 20.0F) + 1.0F) / 2.0F * 64.0F) + 192) / 255.0F;
         }
      }

      listenerHandler.updateState2();
      listenerHandler.applyListenerAdapterByListenerAdapter2(listenerAdapter);
      listenerHandler.applyListenerAdapter2(listenerAdapter);
      listenerHandler.applyListenerAdapter4(listenerAdapter);
      listenerHandler.applyListenerAdapter3(listenerAdapter);
      listenerHandler.applyListenerAdapter23(listenerAdapter);
      if (!listenerAdapter.enabled) {
         WorldRenderer worldRenderer = Tessellator.getTessellator().getWorldRenderer();
         worldRenderer.updateAuxiliaryLoggerEntry(7, DefaultVertexFormats.auxiliaryLoggerEntry2);

         for (ComparableEntryArrayEntryAdapter comparableEntryArrayEntryAdapter : (Iterable<ComparableEntryArrayEntryAdapter>)(Iterable<?>)listenerAdapter.list) {
            listenerHandler.updateState2();
            this.applyWorldRenderer(worldRenderer, comparableEntryArrayEntryAdapter, factor5, factor6, factor7, factor8);
            listenerHandler.updateState();
         }

         Tessellator.getTessellator().updateState();

         for (ListenerAdapter23 listenerAdapter2 : (Iterable<ListenerAdapter23>)(Iterable<?>)listenerAdapter.list2) {
            this.applyListenerAdapter23(listenerAdapter2, factor5, factor6, factor7, factor8);
         }
      }

      listenerHandler.updateState();
      if (count2 != -1) {
         if ((this.modelData.model[count2] != null ? this.modelData.model[count2] : FailedToLoadMaskFromManagerEntryAdapter.settingOverlay).illum) {
            GlStateManager.applyState13();
            Minecraft.getMinecraft().entityRenderer.updateState7();
         }

         FailedToLoadMaskFromManagerEntryAdapter.passiveListener.updateState();
      }
   }

   public void applyInteger(Integer count, MainControllerManager mainControllerManager, float factor5) {
      CompositeListEntry compositeListEntry = new CompositeListEntry(mainControllerManager, 0.0F, 0.0F, factor5, false, list);
      mainControllerManager.getCouldNotFindBoneHandlerAdapter().applyPreMotionBroadcaster(mainControllerManager, count, compositeListEntry);
   }
}
