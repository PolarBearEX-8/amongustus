package client.util;

import client.event.MinecraftPreUpdateDispatcher;
import client.event.PreUpdateDispatcher;
import client.gui.AuxiliaryTexturesGuiIconsPngManagerAdapter;
import client.gui.GuiYesAuxiliaryTexturesGuiIc;
import client.gui.InternalTexturesGuiIconsPngManagerAdapter;
import client.gui.InterpolationValidatAuxiliaryTexturesGuiIc;
import client.gui.TexturesGuiIconsPngManager;
import com.google.common.collect.Lists;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.item.AltItemArrayEntry;

public class AltItemArrayEntrySplineCalculator extends SplineCalculator {
   public final List list = Lists.newArrayList();
   public final AltItemArrayEntry altItemArrayEntry = new AltItemArrayEntry();
   public final List list2 = Lists.newArrayList();
   public final Controller20[][] controller20Array;
   public int count20;
   public InterpolationValidator interpolationValidator;
   public TexturesGuiIconsPngManager texturesGuiIconsPngManager;

   private InternalTexturesGuiIconsPngManagerAdapter resolveInternalTexturesGuiIconsPngM(int count, int count2, PredicateController20 predicateController) {
      InternalTexturesGuiIconsPngManagerAdapter internalTexturesGuiIconsPngManagerAdapter = new InternalTexturesGuiIconsPngManagerAdapter(
         predicateController.getCount(), this.mc.fontRendererObj, count, count2, 150, 20
      );
      internalTexturesGuiIconsPngManagerAdapter.applyText(predicateController.getLabelText());
      internalTexturesGuiIconsPngManagerAdapter.setInterpolationValidator(this.interpolationValidator);
      internalTexturesGuiIconsPngManagerAdapter.setEnabled3(predicateController.isEnabled());
      internalTexturesGuiIconsPngManagerAdapter.setPredicate(predicateController.getPredicate());
      return internalTexturesGuiIconsPngManagerAdapter;
   }

   public void applyChar(char character, int count) {
      if (this.texturesGuiIconsPngManager instanceof InternalTexturesGuiIconsPngManagerAdapter) {
         InternalTexturesGuiIconsPngManagerAdapter texturesGuiIconsPngManager2 = (InternalTexturesGuiIconsPngManagerAdapter)this.texturesGuiIconsPngManager;
         if (!GuiScreen.checkIndex3(count)) {
            if (count == 15) {
               texturesGuiIconsPngManager2.setEnabledByFlag(false);
               int count2 = this.list2.indexOf(this.texturesGuiIconsPngManager);
               if (GuiScreen.isActiveAsFlag()) {
                  if (count2 == 0) {
                     count2 = this.list2.size() - 1;
                  } else {
                     count2--;
                  }
               } else if (count2 == this.list2.size() - 1) {
                  count2 = 0;
               } else {
                  count2++;
               }

               this.texturesGuiIconsPngManager = (TexturesGuiIconsPngManager)this.list2.get(count2);
               InternalTexturesGuiIconsPngManagerAdapter texturesGuiIconsPngManager3 = (InternalTexturesGuiIconsPngManagerAdapter)this.texturesGuiIconsPngManager;
               texturesGuiIconsPngManager3.setEnabledByFlag(true);
               int count3 = texturesGuiIconsPngManager3.count10 + this.count5;
               int count4 = texturesGuiIconsPngManager3.count10;
               if (count3 > this.count7) {
                  this.factor2 = this.factor2 + (float)(count3 - this.count7);
               } else if (count4 < this.count10) {
                  this.factor2 = (float)count4;
               }
            } else {
               texturesGuiIconsPngManager2.checkChar(character, count);
            }
         } else {
            String[] textArray = GuiScreen.getText().split(";");
            int count5 = this.list2.indexOf(this.texturesGuiIconsPngManager);
            int count6 = count5;

            for (String text : textArray) {
               ((InternalTexturesGuiIconsPngManagerAdapter)this.list2.get(count6)).applyText(text);
               if (count6 == this.list2.size() - 1) {
                  count6 = 0;
               } else {
                  count6++;
               }

               if (count6 == count5) {
                  break;
               }
            }
         }
      }
   }

   public void updateState6() {
      if (this.count20 < this.controller20Array.length - 1) {
         this.applyIndex2(this.count20 + 1);
      }
   }

   public void updateState5() {
      if (this.count20 > 0) {
         this.applyIndex2(this.count20 - 1);
      }
   }

   private void updateState7() {
      this.list.clear();

      for (int byteValue = 0; byteValue < this.controller20Array[this.count20].length; byteValue += 2) {
         Controller20 controller = this.controller20Array[this.count20][byteValue];
         Controller20 controller2 = byteValue < this.controller20Array[this.count20].length - 1 ? this.controller20Array[this.count20][byteValue + 1] : null;
         TexturesGuiIconsPngManager texturesGuiIconsPngManager2 = (TexturesGuiIconsPngManager)this.altItemArrayEntry.resolveTargetByIndex(controller.getCount());
         TexturesGuiIconsPngManager texturesGuiIconsPngManager3 = controller2 != null ? (TexturesGuiIconsPngManager)this.altItemArrayEntry.resolveTargetByIndex(controller2.getCount()) : null;
         MinecraftPreUpdateDispatcher minecraftPreUpdateDispatcher = new MinecraftPreUpdateDispatcher(texturesGuiIconsPngManager2, texturesGuiIconsPngManager3);
         this.list.add(minecraftPreUpdateDispatcher);
      }
   }

   @Override
   public int size() {
      return this.list.size();
   }

   private void applyTexturesGuiIconsPngManager(TexturesGuiIconsPngManager texturesGuiIconsPngManager2, boolean flag) {
      if (texturesGuiIconsPngManager2 instanceof AuxiliaryTexturesGuiIconsPngManagerAdapter) {
         ((AuxiliaryTexturesGuiIconsPngManagerAdapter)texturesGuiIconsPngManager2).enabled2 = flag;
      } else if (texturesGuiIconsPngManager2 instanceof InternalTexturesGuiIconsPngManagerAdapter) {
         ((InternalTexturesGuiIconsPngManagerAdapter)texturesGuiIconsPngManager2).setEnabled3(flag);
      } else if (texturesGuiIconsPngManager2 instanceof GeometryDispatcher) {
         ((GeometryDispatcher)texturesGuiIconsPngManager2).enabled2 = flag;
      }
   }

   @Override
   public boolean checkIndex(int count, int count2, int count3) {
      boolean flag = super.checkIndex(count, count2, count3);
      int count4 = this.computeCount(count, count2);
      if (count4 >= 0) {
         MinecraftPreUpdateDispatcher byIndex = this.getByIndex(count4);
         if (this.texturesGuiIconsPngManager != MinecraftPreUpdateDispatcher.resolveTexturesGuiIconsPngManagerByMinecraftPreUpda(byIndex)
            && this.texturesGuiIconsPngManager != null
            && this.texturesGuiIconsPngManager instanceof InternalTexturesGuiIconsPngManagerAdapter) {
            ((InternalTexturesGuiIconsPngManagerAdapter)this.texturesGuiIconsPngManager).setEnabledByFlag(false);
         }

         this.texturesGuiIconsPngManager = MinecraftPreUpdateDispatcher.resolveTexturesGuiIconsPngManagerByMinecraftPreUpda(byIndex);
      }

      return flag;
   }

   public int getCount4() {
      return this.count20;
   }

   @Override
   public PreUpdateDispatcher get(int count) {
      return this.getByIndex(count);
   }

   @Override
   public int getCount20() {
      return 400;
   }

   private GuiYesAuxiliaryTexturesGuiIc resolveGuiYesAuxiliaryTexturesGuiIc(int count, int count2, Controller20Impl controller20Impl) {
      GuiYesAuxiliaryTexturesGuiIc guiYesAuxiliaryTexturesGuiIc = new GuiYesAuxiliaryTexturesGuiIc(
         this.interpolationValidator, controller20Impl.getCount(), count, count2, controller20Impl.getLabelText(), controller20Impl.isEnabled2()
      );
      guiYesAuxiliaryTexturesGuiIc.enabled2 = controller20Impl.isEnabled();
      return guiYesAuxiliaryTexturesGuiIc;
   }

   private void updateState4() {
      for (Controller20[] controller20Array2 : this.controller20Array) {
         for (int byteValue = 0; byteValue < controller20Array2.length; byteValue += 2) {
            Controller20 controller = controller20Array2[byteValue];
            Controller20 controller2 = byteValue < controller20Array2.length - 1 ? controller20Array2[byteValue + 1] : null;
            TexturesGuiIconsPngManager texturesGuiIconsPngManager2 = this.resolveTexturesGuiIconsPngManager(controller, 0, controller2 == null);
            TexturesGuiIconsPngManager texturesGuiIconsPngManager3 = this.resolveTexturesGuiIconsPngManager(controller2, 160, controller == null);
            MinecraftPreUpdateDispatcher minecraftPreUpdateDispatcher = new MinecraftPreUpdateDispatcher(texturesGuiIconsPngManager2, texturesGuiIconsPngManager3);
            this.list.add(minecraftPreUpdateDispatcher);
            if (controller != null && texturesGuiIconsPngManager2 != null) {
               this.altItemArrayEntry.applyTarget(controller.getCount(), texturesGuiIconsPngManager2);
               if (texturesGuiIconsPngManager2 instanceof InternalTexturesGuiIconsPngManagerAdapter) {
                  this.list2.add((InternalTexturesGuiIconsPngManagerAdapter)texturesGuiIconsPngManager2);
               }
            }

            if (controller2 != null && texturesGuiIconsPngManager3 != null) {
               this.altItemArrayEntry.applyTarget(controller2.getCount(), texturesGuiIconsPngManager3);
               if (texturesGuiIconsPngManager3 instanceof InternalTexturesGuiIconsPngManagerAdapter) {
                  this.list2.add((InternalTexturesGuiIconsPngManagerAdapter)texturesGuiIconsPngManager3);
               }
            }
         }
      }
   }

   @Override
   public int getCount22() {
      return super.getCount22() + 32;
   }

   private void applyIndex3(int count, int count2) {
      for (Controller20 controller : this.controller20Array[count]) {
         if (controller != null) {
            this.applyTexturesGuiIconsPngManager((TexturesGuiIconsPngManager)this.altItemArrayEntry.resolveTargetByIndex(controller.getCount()), false);
         }
      }

      for (Controller20 controller2 : this.controller20Array[count2]) {
         if (controller2 != null) {
            this.applyTexturesGuiIconsPngManager((TexturesGuiIconsPngManager)this.altItemArrayEntry.resolveTargetByIndex(controller2.getCount()), true);
         }
      }
   }

   public void applyIndex2(int count) {
      if (count != this.count20) {
         int count2 = this.count20;
         this.count20 = count;
         this.updateState7();
         this.applyIndex3(count2, count);
         this.factor2 = 0.0F;
      }
   }

   public MinecraftPreUpdateDispatcher getByIndex(int count) {
      return (MinecraftPreUpdateDispatcher)this.list.get(count);
   }

   public TexturesGuiIconsPngManager resolveTexturesGuiIconsPngManager(int count) {
      return (TexturesGuiIconsPngManager)this.altItemArrayEntry.resolveTargetByIndex(count);
   }

   private InterpolationValidatAuxiliaryTexturesGuiIc resolveInterpolationValidatAuxiliar(int count, int count2, Callback15Controller20 callback15Controller) {
      InterpolationValidatAuxiliaryTexturesGuiIc interpolationValidatAuxiliaryTexturesGuiIc = new InterpolationValidatAuxiliaryTexturesGuiIc(
         this.interpolationValidator,
         callback15Controller.getCount(),
         count,
         count2,
         callback15Controller.getLabelText(),
         callback15Controller.getFactor(),
         callback15Controller.getFactor2(),
         callback15Controller.getFactor3(),
         callback15Controller.getCallback15()
      );
      interpolationValidatAuxiliaryTexturesGuiIc.enabled2 = callback15Controller.isEnabled();
      return interpolationValidatAuxiliaryTexturesGuiIc;
   }

   public AltItemArrayEntrySplineCalculator(
      Minecraft mc, int count, int count2, int count3, int count4, int count5, InterpolationValidator interpolationValidator2, Controller20[][] controller20Matrix
   ) {
      super(mc, count, count2, count3, count4, count5);
      this.interpolationValidator = interpolationValidator2;
      this.controller20Array = controller20Matrix;
      this.enabled5 = false;
      this.updateState4();
      this.updateState7();
   }

   private TexturesGuiIconsPngManager resolveTexturesGuiIconsPngManager(Controller20 controller, int count, boolean flag) {
      return (TexturesGuiIconsPngManager)(controller instanceof Callback15Controller20
         ? this.resolveInterpolationValidatAuxiliar(this.count9 / 2 - 155 + count, 0, (Callback15Controller20)controller)
         : (
            controller instanceof Controller20Impl
               ? this.resolveGuiYesAuxiliaryTexturesGuiIc(this.count9 / 2 - 155 + count, 0, (Controller20Impl)controller)
               : (
                  controller instanceof PredicateController20
                     ? this.resolveInternalTexturesGuiIconsPngM(this.count9 / 2 - 155 + count, 0, (PredicateController20)controller)
                     : (
                        controller instanceof RuntimeComponentDelegate
                           ? this.resolveGeometryDispatcher(this.count9 / 2 - 155 + count, 0, (RuntimeComponentDelegate)controller, flag)
                           : null
                     )
               )
         ));
   }

   public TexturesGuiIconsPngManager getTexturesGuiIconsPngManager() {
      return this.texturesGuiIconsPngManager;
   }

   private GeometryDispatcher resolveGeometryDispatcher(int count, int count2, RuntimeComponentDelegate runtimeComponentDelegate, boolean flag) {
      GeometryDispatcher geometryDispatcher;
      if (flag) {
         geometryDispatcher = new GeometryDispatcher(this.mc.fontRendererObj, runtimeComponentDelegate.getCount(), count, count2, this.count9 - count * 2, 20, -1);
      } else {
         geometryDispatcher = new GeometryDispatcher(this.mc.fontRendererObj, runtimeComponentDelegate.getCount(), count, count2, 150, 20, -1);
      }

      geometryDispatcher.enabled2 = runtimeComponentDelegate.isActive();
      geometryDispatcher.applyText(runtimeComponentDelegate.getText());
      geometryDispatcher.getGeometryDispatcher();
      return geometryDispatcher;
   }

   public int getCount3() {
      return this.controller20Array.length;
   }

   public void setEnabled(boolean enabled) {
      for (MinecraftPreUpdateDispatcher minecraftPreUpdateDispatcher : (Iterable<MinecraftPreUpdateDispatcher>)(this.list)) {
         if (MinecraftPreUpdateDispatcher.resolveTexturesGuiIconsPngManager(minecraftPreUpdateDispatcher) instanceof AuxiliaryTexturesGuiIconsPngManagerAdapter) {
            ((AuxiliaryTexturesGuiIconsPngManagerAdapter)MinecraftPreUpdateDispatcher.resolveTexturesGuiIconsPngManager(minecraftPreUpdateDispatcher)).enabled3 = enabled;
         }

         if (MinecraftPreUpdateDispatcher.resolveTexturesGuiIconsPngManager2(minecraftPreUpdateDispatcher) instanceof AuxiliaryTexturesGuiIconsPngManagerAdapter) {
            ((AuxiliaryTexturesGuiIconsPngManagerAdapter)MinecraftPreUpdateDispatcher.resolveTexturesGuiIconsPngManager2(minecraftPreUpdateDispatcher)).enabled3 = enabled;
         }
      }
   }
}
