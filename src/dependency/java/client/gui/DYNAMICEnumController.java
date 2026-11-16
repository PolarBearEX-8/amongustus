package client.gui;

import client.render.RenderUtils;
import client.util.DYNAMICEnum;
import client.util.Service19;
import client.util.Service19Impl;
import com.google.common.collect.Lists;
import java.util.Iterator;
import java.util.List;
import net.minecraft.client.Minecraft;

public class DYNAMICEnumController {
   public final boolean enabled;
   public int count;
   public int count2;
   public final int count3;
   public final List list;
   public final List list2;
   public int count4 = 0;
   public final DYNAMICEnum dYNAMICEnum;
   public final int count5;

   public void applyAuxiliaryTexturesGuiIconsP(AuxiliaryTexturesGuiIconsPngManagerAdapter auxiliaryTexturesGuiIconsPngManagerAdapter) {
      this.count = auxiliaryTexturesGuiIconsPngManagerAdapter.count2;
      int count6 = 0;
      byte byteValue = 0;

      while (true) {
         while (count6 >= this.list.size()) {
         }

         ((AuxiliaryTexturesGuiIconsPngManagerAdapter)this.list.get(count6)).enabled3 = count6 != byteValue;
         count6++;
      }
   }

   public boolean isEnabled() {
      return this.enabled;
   }

   public int getCount3() {
      return this.count3;
   }

   private static Service19[] resolveService19Array(String[] textArray) {
      Service19[] service19Array = new Service19[textArray.length];

      for (int index = 0; index < textArray.length; index++) {
         service19Array[index] = new Service19Impl(textArray[index]);
      }

      return service19Array;
   }

   public DYNAMICEnumController(int count6, int count7, int count8, int count9, DYNAMICEnum dYNAMICEnum2, boolean flag, Service19[] service19Array) {
      this.count = 0;
      this.count2 = count6;
      this.count3 = count7;
      this.dYNAMICEnum = dYNAMICEnum2;
      this.count5 = count8;
      this.enabled = flag;
      this.list2 = Lists.newArrayList();
      this.list = Lists.newArrayList();
      int count10 = this.count5 / service19Array.length;
      this.count4 = this.count5 - count10 * service19Array.length;

      for (int index = 0; index < service19Array.length; index++) {
         Service19 service = service19Array[index];
         this.list2.add(service);
         AuxiliaryTexturesGuiIconsPngManagerAdapter auxiliaryTexturesGuiIconsPngManagerAdapter;
         this.list.add(auxiliaryTexturesGuiIconsPngManagerAdapter = new AuxiliaryTexturesGuiIconsPngManagerAdapter(index, count6 + count10 * index, count7, count10, count9, service.getLabelText()));
         auxiliaryTexturesGuiIconsPngManagerAdapter.enabled3 = index != this.count;
      }
   }

   public void applyIndex(int count6, int count7, int count8) {
      if (count8 == 0 || count8 == 1) {
         Iterator iterator = this.list.iterator();

         while (iterator.hasNext()) {
            if (((AuxiliaryTexturesGuiIconsPngManagerAdapter)iterator.next()).matchesMinecraft(Minecraft.getMinecraft(), count6, count7)) {
               return;
            }
         }
      }
   }

   public void applyIndexByIndex(int count6, int count7, int count8) {
      if (count8 != 0) {
         this.count2 = count8;
      }

      Service19 service = (Service19)this.list2.get(this.count);
      int count9 = 0;

      for (AuxiliaryTexturesGuiIconsPngManagerAdapter auxiliaryTexturesGuiIconsPngManagerAdapter : (Iterable<AuxiliaryTexturesGuiIconsPngManagerAdapter>) (this.list)) {
         if (count8 != 0) {
            auxiliaryTexturesGuiIconsPngManagerAdapter.count = count8 + this.count5 / this.list2.size() * count9;
         }

         if (this.dYNAMICEnum.equals(DYNAMICEnum.STATIC)) {
            auxiliaryTexturesGuiIconsPngManagerAdapter.count3 = this.count3;
         } else {
            auxiliaryTexturesGuiIconsPngManagerAdapter.count3 = this.count3 - service.getCount();
         }

         auxiliaryTexturesGuiIconsPngManagerAdapter.applyMinecraftByMinecraft(Minecraft.getMinecraft(), count6, count7);
         count9++;
      }

      int count10;
      if (this.dYNAMICEnum.equals(DYNAMICEnum.STATIC)) {
         count10 = this.count3 + 22;
      } else {
         count10 = this.count3 + 22 - service.getCount();
      }

      if (this.enabled) {
         RenderUtils.processAction(this.count2, count10 - 2, this.count2 + this.count5 - this.count4, count10 + service.getCount(), Integer.MIN_VALUE);
      }

      int count11 = this.count2;
      service.applyIndex(count11, count10, count6, count7);
   }

   public void setCount(int count6) {
      this.count = count6;
   }

   public void applyIndex(int count6, int count7) {
      this.applyIndexByIndex(count6, count7, 0);
   }

   public DYNAMICEnum getDYNAMICEnum() {
      return this.dYNAMICEnum;
   }

   public int getCount5() {
      return this.count5;
   }

   public int getCount2() {
      return this.count2;
   }

   public DYNAMICEnumController(int count6, int count7, int count8, String[] textArray) {
      this(count6, count7, count8, 20, DYNAMICEnum.STATIC, false, resolveService19Array(textArray));
   }

   public int getCount() {
      return this.count;
   }

   public DYNAMICEnumController(int count6, int count7, int count8, int count9, String[] textArray) {
      this(count6, count7, count8, count9, DYNAMICEnum.STATIC, false, resolveService19Array(textArray));
   }
}
