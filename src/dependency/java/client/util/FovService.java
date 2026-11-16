package client.util;

import client.event.PreUpdateObserver;
import net.minecraft.tileentity.TileEntity;

public class FovService implements Listener61Extension {
   public ClassEntry classEntry;
   public PassiveFloatEntry passiveFloatEntry;
   public FovEngine[] fovEngineArray;
   public BoxesManager boxesManager;
   public BoxesManager boxesManager2;
   public PreUpdateObserverCallback preUpdateObserverCallback;

   @Override
   public BoxesManager resolveBoxesManager(String text) {
      if (text == null) {
         return null;
      } else if (text.indexOf(":") >= 0) {
         String[] textArray = BuildManager.resolveTextArray(text, ":");
         BoxesManager boxesManager3 = this.resolveBoxesManager(textArray[0]);

         for (int index = 1; index < textArray.length; index++) {
            String text2 = textArray[index];
            boxesManager3 = boxesManager3.resolveBoxesManagerByText(text2);
            if (boxesManager3 == null) {
               return null;
            }
         }

         return boxesManager3;
      } else if (this.boxesManager != null && text.equals("this")) {
         return this.boxesManager;
      } else if (this.boxesManager2 != null && text.equals("part")) {
         return this.boxesManager2;
      } else {
         BoxesManager boxesManager4 = this.classEntry.resolveBoxesManager(this.passiveFloatEntry, text);
         if (boxesManager4 != null) {
            return boxesManager4;
         } else {
            for (int index2 = 0; index2 < this.fovEngineArray.length; index2++) {
               BoxesManager boxesManager5 = this.fovEngineArray[index2].getBoxesManager();
               if (text.equals(boxesManager5.getLabelText2())) {
                  return boxesManager5;
               }

               BoxesManager boxesManager6 = boxesManager5.resolveBoxesManagerByText(text);
               if (boxesManager6 != null) {
                  return boxesManager6;
               }
            }

            return null;
         }
      }
   }

   public void setBoxesManager(BoxesManager boxesManager3) {
      this.boxesManager = boxesManager3;
   }

   public FovService(ClassEntry classEntry2, PassiveFloatEntry passiveFloatEntry2, FovEngine[] fovEngineArray2) {
      this.classEntry = classEntry2;
      this.passiveFloatEntry = passiveFloatEntry2;
      this.fovEngineArray = fovEngineArray2;
      Class memberRef = classEntry2.getMemberRef();
      if (TileEntity.class.isAssignableFrom(memberRef)) {
         this.preUpdateObserverCallback = new PreUpdateObserverPreUpdateObserverCallb();
      } else {
         this.preUpdateObserverCallback = new PreUpdateObserverPreUpdateObserverCallbImpl();
      }
   }

   public void setBoxesManager2(BoxesManager boxesManager3) {
      this.boxesManager2 = boxesManager3;
   }

   @Override
   public PreUpdateObserver get(String text) {
      AngleAggregator angleAggregator = this.resolveAngleAggregator(text);
      if (angleAggregator != null) {
         return angleAggregator;
      } else {
         PreUpdateObserver preUpdateObserver = this.preUpdateObserverCallback.resolvePreUpdateObserver(text);
         return preUpdateObserver != null ? preUpdateObserver : null;
      }
   }

   @Override
   public AngleAggregator resolveAngleAggregator(String text) {
      String[] textArray = BuildManager.resolveTextArray(text, ".");
      if (textArray.length != 2) {
         return null;
      } else {
         String text2 = textArray[0];
         String text3 = textArray[1];
         BoxesManager boxesManager3 = this.resolveBoxesManager(text2);
         if (boxesManager3 == null) {
            return null;
         } else {
            PosXPosYType posXPosYType = PosXPosYType.resolvePosXPosYType(text3);
            return posXPosYType == null ? null : new AngleAggregator(text, boxesManager3, posXPosYType);
         }
      }
   }
}
