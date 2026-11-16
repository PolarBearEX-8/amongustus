package client.event;

import client.gui.AuxiliaryTexturesGuiIconsPngManagerAdapter;
import client.gui.ValueSliderControl;
import client.setting.FactoryOptionType;
import net.minecraft.client.Minecraft;

public class MinecraftPreUpdateDispatcherImpl implements PreUpdateDispatcher {
   public final Minecraft mc = Minecraft.getMinecraft();
   public final AuxiliaryTexturesGuiIconsPngManagerAdapter auxiliaryTexturesGuiIconsPngManagerAdapter;
   public final AuxiliaryTexturesGuiIconsPngManagerAdapter auxiliaryTexturesGuiIconsPngManagerAdapter2;

   public MinecraftPreUpdateDispatcherImpl(AuxiliaryTexturesGuiIconsPngManagerAdapter auxiliaryTexturesGuiIconsPngManagerAdapter3, AuxiliaryTexturesGuiIconsPngManagerAdapter auxiliaryTexturesGuiIconsPngManagerAdapter4) {
      this.auxiliaryTexturesGuiIconsPngManagerAdapter = auxiliaryTexturesGuiIconsPngManagerAdapter3;
      this.auxiliaryTexturesGuiIconsPngManagerAdapter2 = auxiliaryTexturesGuiIconsPngManagerAdapter4;
   }

   @Override
   public void applyIndex(int count, int count2, int count3) {
   }

   @Override
   public void applyIndex(int count, int count2, int count3, int count4, int count5, int count6) {
      if (this.auxiliaryTexturesGuiIconsPngManagerAdapter != null) {
         this.auxiliaryTexturesGuiIconsPngManagerAdapter.applyIndex(count2, count3);
      }

      if (this.auxiliaryTexturesGuiIconsPngManagerAdapter2 != null) {
         this.auxiliaryTexturesGuiIconsPngManagerAdapter2.applyIndex(count2, count3);
      }
   }

   @Override
   public void applyIndex(int count, int count2, int count3, int count4, int count5, int count6, int count7, boolean flag) {
      if (this.auxiliaryTexturesGuiIconsPngManagerAdapter != null) {
         this.auxiliaryTexturesGuiIconsPngManagerAdapter.count3 = count3;
         this.auxiliaryTexturesGuiIconsPngManagerAdapter.applyMinecraftByMinecraft(this.mc, count6, count7);
      }

      if (this.auxiliaryTexturesGuiIconsPngManagerAdapter2 != null) {
         this.auxiliaryTexturesGuiIconsPngManagerAdapter2.count3 = count3;
         this.auxiliaryTexturesGuiIconsPngManagerAdapter2.applyMinecraftByMinecraft(this.mc, count6, count7);
      }
   }

   @Override
   public boolean checkIndex(int count, int count2, int count3, int count4, int count5, int count6) {
      if (this.auxiliaryTexturesGuiIconsPngManagerAdapter.matchesMinecraft(this.mc, count2, count3)) {
         if (this.auxiliaryTexturesGuiIconsPngManagerAdapter instanceof ValueSliderControl) {
            this.mc
               .gameSettings
               .applyFactoryOptionTypeByFactoryOptionTyp(((ValueSliderControl)this.auxiliaryTexturesGuiIconsPngManagerAdapter).getFactoryOptionType(), 1);
            this.auxiliaryTexturesGuiIconsPngManagerAdapter.labelText = this.mc
               .gameSettings
               .resolveTextByFactoryOptionTyp(FactoryOptionType.resolveFactoryOptionType(this.auxiliaryTexturesGuiIconsPngManagerAdapter.count2));
         }

         return true;
      } else if (this.auxiliaryTexturesGuiIconsPngManagerAdapter2 != null
         && this.auxiliaryTexturesGuiIconsPngManagerAdapter2.matchesMinecraft(this.mc, count2, count3)) {
         if (this.auxiliaryTexturesGuiIconsPngManagerAdapter2 instanceof ValueSliderControl) {
            this.mc
               .gameSettings
               .applyFactoryOptionTypeByFactoryOptionTyp(((ValueSliderControl)this.auxiliaryTexturesGuiIconsPngManagerAdapter2).getFactoryOptionType(), 1);
            this.auxiliaryTexturesGuiIconsPngManagerAdapter2.labelText = this.mc
               .gameSettings
               .resolveTextByFactoryOptionTyp(FactoryOptionType.resolveFactoryOptionType(this.auxiliaryTexturesGuiIconsPngManagerAdapter2.count2));
         }

         return true;
      } else {
         return false;
      }
   }
}
