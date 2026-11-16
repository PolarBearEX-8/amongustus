package client.util;

import client.event.MinecraftPreUpdateDispatcherImpl;
import client.event.PreUpdateDispatcher;
import client.gui.AuxiliaryTexturesGuiIconsPngManagerAdapter;
import client.gui.ValueSliderControl;
import client.setting.FactoryOptionType;
import com.google.common.collect.Lists;
import java.util.List;
import net.minecraft.client.Minecraft;

public class PreUpdateDispatcherSplineCalculator extends SplineCalculator {
   public final List list = Lists.newArrayList();

   public PreUpdateDispatcherSplineCalculator(Minecraft mc, int count, int count2, int count3, int count4, int count5, FactoryOptionType[] factoryOptionTypeArray) {
      super(mc, count, count2, count3, count4, count5);
      this.enabled5 = false;

      for (int byteValue = 0; byteValue < factoryOptionTypeArray.length; byteValue += 2) {
         FactoryOptionType factoryOptionType = factoryOptionTypeArray[byteValue];
         FactoryOptionType factoryOptionType2 = byteValue < factoryOptionTypeArray.length - 1 ? factoryOptionTypeArray[byteValue + 1] : null;
         AuxiliaryTexturesGuiIconsPngManagerAdapter auxiliaryTexturesGuiIconsPngManagerAdapter = this.resolveAuxiliaryTexturesGuiIconsPng(mc, count / 2 - 155, 0, factoryOptionType);
         AuxiliaryTexturesGuiIconsPngManagerAdapter auxiliaryTexturesGuiIconsPngManagerAdapter2 = this.resolveAuxiliaryTexturesGuiIconsPng(mc, count / 2 - 155 + 160, 0, factoryOptionType2);
         this.list.add(new MinecraftPreUpdateDispatcherImpl(auxiliaryTexturesGuiIconsPngManagerAdapter, auxiliaryTexturesGuiIconsPngManagerAdapter2));
      }
   }

   @Override
   public int getCount20() {
      return 400;
   }

   @Override
   public int getCount22() {
      return super.getCount22() + 32;
   }

   @Override
   public PreUpdateDispatcher get(int count) {
      return this.getByIndex(count);
   }

   @Override
   public int size() {
      return this.list.size();
   }

   private AuxiliaryTexturesGuiIconsPngManagerAdapter resolveAuxiliaryTexturesGuiIconsPng(Minecraft mc, int count, int count2, FactoryOptionType factoryOptionType) {
      if (factoryOptionType == null) {
         return null;
      } else {
         int count3 = factoryOptionType.ordinalAsIndex();
         return (AuxiliaryTexturesGuiIconsPngManagerAdapter)(factoryOptionType.isEnabled()
            ? new DynamicFloatEntry(count3, count, count2, factoryOptionType)
            : new ValueSliderControl(count3, count, count2, factoryOptionType, mc.gameSettings.resolveTextByFactoryOptionTyp(factoryOptionType)));
      }
   }

   public MinecraftPreUpdateDispatcherImpl getByIndex(int count) {
      return (MinecraftPreUpdateDispatcherImpl)this.list.get(count);
   }
}
