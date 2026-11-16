package client.util;

import client.event.PreMotionObserverImpl;
import client.event.PreUpdateFilter;
import client.gui.DefaultGuiScreenAdapter;
import java.util.ArrayList;
import java.util.Properties;
import net.minecraft.client.Minecraft;
import net.minecraft.util.StatCollector;

public class OptionsShadersExtendedMinecraftEntry extends ExtendedMinecraftEntry {
   public ArrayList arrayList;
   public int count15;
   public long timestamp2 = 0L;
   public final SplineDispatcher splineDispatcher;

   @Override
   public int getCount() {
      return this.size() * 18;
   }

   @Override
   public boolean checkIndex(int count) {
      return count == this.count15;
   }

   public void updateState4() {
      this.arrayList = PreEndHandManager.getArrayList();
      this.count15 = 0;
      int count = 0;

      for (int index = this.arrayList.size(); count < index; count++) {
         if (((String)this.arrayList.get(count)).equals(PreEndHandManager.labelText7)) {
            this.count15 = count;
            break;
         }
      }
   }

   @Override
   public void applyIndex(int count, boolean flag, int count2, int count3) {
      if (count != this.count15 || this.timestamp != this.timestamp2) {
         PreUpdateFilter preUpdateFilter = PreEndHandManager.resolvePreUpdateFilter((String)this.arrayList.get(count));
         if (this.matchesPreUpdateFilter(preUpdateFilter, count)) {
            this.applyIndex2(count);
         }
      }
   }

   @Override
   public void updateState2() {
   }

   private void applyIndex2(int count) {
      this.count15 = count;
      this.timestamp2 = this.timestamp;
      PreEndHandManager.applyText2((String)this.arrayList.get(count));
      PreEndHandManager.updateState77();
      this.splineDispatcher.updateState();
   }

   public int getCount15() {
      return this.count15;
   }

   @Override
   public void applyIndex(int count, int count2, int count3, int count4, int count5, int count6) {
      String text = (String)this.arrayList.get(count);
      if (text.equals("OFF")) {
         text = GeneratorDefaultManager.resolveText("of.options.shaders.packNone");
      } else if (text.equals("(internal)")) {
         text = GeneratorDefaultManager.resolveText("of.options.shaders.packDefault");
      }

      this.splineDispatcher.applyText(text, this.count9 / 2, count3 + 1, 14737632);
   }

   @Override
   public int size() {
      return this.arrayList.size();
   }

   @Override
   public int getCount22() {
      return this.count9 - 6;
   }

   public static void applyOptionsShadersExtendedMine(OptionsShadersExtendedMinecraftEntry optionsShadersExtendedMinecraftEntry, int count) {
      optionsShadersExtendedMinecraftEntry.applyIndex2(count);
   }

   @Override
   public int getCount20() {
      return this.count9 - 20;
   }

   public static Minecraft resolveMinecraft(OptionsShadersExtendedMinecraftEntry optionsShadersExtendedMinecraftEntry) {
      return optionsShadersExtendedMinecraftEntry.mc;
   }

   private boolean matchesPreUpdateFilter(PreUpdateFilter preUpdateFilter, int count) {
      if (preUpdateFilter == null) {
         return true;
      } else {
         Properties properties = AssetsMinecraftManager.resolveProperties(preUpdateFilter.getResourceAsStream("/shaders/shaders.properties"), "Shaders");
         if (properties == null) {
            return true;
         } else {
            String text = "version.1.8.9";
            String property = properties.getProperty(text);
            if (property == null) {
               return true;
            } else {
               String text2 = property.trim();
               if (BuildManager.computeCount("M5", text2) >= 0) {
                  return true;
               } else {
                  String text3 = ("HD_U_" + text2).replace('_', ' ');
                  String text4 = StatCollector.getText("of.message.shaders.nv1", new Object[]{text3});
                  String text5 = StatCollector.getText("of.message.shaders.nv2", new Object[0]);
                  PreMotionObserverImpl preMotionObserverImpl = new PreMotionObserverImpl(this, count);
                  DefaultGuiScreenAdapter defaultGuiScreenAdapter = new DefaultGuiScreenAdapter(preMotionObserverImpl, text4, text5, 0);
                  this.mc.displayGuiScreen(defaultGuiScreenAdapter);
                  return false;
               }
            }
         }
      }
   }

   public OptionsShadersExtendedMinecraftEntry(SplineDispatcher splineDispatcher2, int count, int count2, int count3, int count4, int count5) {
      super(splineDispatcher2.getMc(), count, count2, count3, count4, count5);
      this.splineDispatcher = splineDispatcher2;
      this.updateState4();
      this.factor2 = 0.0F;
      int count6 = this.count15 * count5;
      int count7 = (count4 - count3) / 2;
      if (count6 > count7) {
         this.applyIndex(count6 - count7);
      }
   }
}
