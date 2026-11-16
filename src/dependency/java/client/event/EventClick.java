package client.event;

import client.setting.MovementSpeedProperty;
import client.setting.SettingContainer;
import client.util.AnimationTimer;
import client.util.ClickHelper;
import client.util.MovingObjectType;
import client.util.SimpleIntArrayEntry;
import de.florianmichael.dietrichevents2.CancellableEvent;
import net.minecraft.client.Minecraft;

public class EventClick extends CancellableEvent {
   public static int indexOffset10 = 0;
   public int indexOffset12;
   public boolean state11;
   public boolean state8;
   public Runnable runnable;
   public Runnable runnable2;
   public static ClickHelper clickHelper = new ClickHelper(19);
   public boolean state1;
   public static final int count2 = 7;
   public int indexOffset;
   public static boolean enabled2 = false;
   public static final AnimationTimer animationTimer = new AnimationTimer();
   public boolean state6;
   public static boolean enabled = false;
   public final MovementSpeedProperty movementSpeedProperty;
   public boolean state9 = true;
   public boolean state3;
   public Runnable runnable3;
   public boolean state4;
   public boolean state5;
   public boolean state7;
   public boolean state;
   public boolean state2;
   public static int count = 0;

   public boolean isState7() {
      return this.state7;
   }

   public void applyClickListener(ClickListener clickListener) {
      switch (SimpleIntArrayEntry.countArray[this.movementSpeedProperty.ordinal()]) {
         case 1:
            clickListener.onClick(this);
            break;
         case 2:
            clickListener.updateState8();
      }
   }

   public void setState4(boolean state10) {
      this.state4 = state10;
   }

   public static boolean checkFlag(boolean flag) {
      if (enabled && flag) {
         Minecraft mc = Minecraft.getMinecraft();
         if (mc.currentScreen == null && mc.inGameHasFocus && mc.objectMouseOver != null && mc.objectMouseOver.typeOfHit == MovingObjectType.BLOCK) {
            int count3 = count;
            if (animationTimer.checkCondition(count3)) {
               count = SettingContainer.settingProvider.getInt2(1, 5);
               animationTimer.processAction3();
               enabled = false;
               return true;
            }
         } else {
            animationTimer.processAction3();
            enabled = false;
         }

         return mc.currentScreen == null && mc.inGameHasFocus;
      } else {
         animationTimer.processAction3();
         return false;
      }
   }

   public void setState2(boolean state10) {
      this.state2 = state10;
   }

   public boolean isState2() {
      return this.state2;
   }

   public void setState(boolean state10) {
      this.state = state10;
   }

   public boolean isState4() {
      return this.state4;
   }

   public void setRunnable(Runnable runnable4) {
      this.runnable = runnable4;
   }

   public void setState3(boolean state10) {
      this.state3 = state10;
   }

   public int getIndexOffset12() {
      return this.indexOffset12;
   }

   public boolean isState8() {
      return this.state8;
   }

   public void call(Object entry) {
      ClickListener clickListener = (ClickListener)entry;
      this.applyClickListener(clickListener);
   }

   public void setState8(boolean state10) {
      this.state8 = state10;
   }

   public boolean isState6() {
      return this.state6;
   }

   public Runnable getRunnable2() {
      return this.runnable2;
   }

   public void updateInt(int count3) {
      this.indexOffset += count3;
   }

   public void updateInt2(int count3) {
      this.indexOffset12 += count3;
   }

   public int getIndexOffset() {
      return this.indexOffset;
   }

   public void setState7(boolean state10) {
      this.state7 = state10;
   }

   public boolean isState() {
      return this.state;
   }

   public void updateState() {
      this.indexOffset = 0;
   }

   public boolean isState5() {
      return this.state5;
   }

   public void setState5(boolean state10) {
      this.state5 = state10;
   }

   public Runnable getRunnable() {
      return this.runnable;
   }

   public boolean isState3() {
      return this.state3;
   }

   public Runnable getRunnable3() {
      return this.runnable3;
   }

   public void setState11(boolean state10) {
      this.state11 = state10;
   }

   public void setState6(boolean state10) {
      this.state6 = state10;
   }

   public void setRunnable2(Runnable runnable4) {
      this.runnable2 = runnable4;
   }

   public boolean isState1() {
      return this.state1;
   }

   public void setRunnable3(Runnable runnable4) {
      this.runnable3 = runnable4;
   }

   public boolean isState9() {
      return this.state9;
   }

   public void setState1(boolean state10) {
      this.state1 = state10;
   }

   public boolean isState11() {
      return this.state11;
   }

   public void setState9(boolean state10) {
      this.state9 = state10;
   }

   public EventClick(MovementSpeedProperty movementSpeedProperty2, boolean flag, boolean flag2, Runnable task, Runnable task2) {
      this.indexOffset = 0;
      this.indexOffset12 = 0;
      this.state3 = false;
      this.state2 = false;
      this.state11 = false;
      this.state1 = false;
      this.state7 = false;
      this.state8 = false;
      this.state5 = false;
      this.state4 = false;
      this.state = false;
      this.state6 = false;
      this.movementSpeedProperty = movementSpeedProperty2;
      this.state11 = flag;
      this.state1 = flag2;
      this.runnable3 = task;
      this.runnable2 = task2;
      this.runnable = null;
   }

   public EventClick(MovementSpeedProperty movementSpeedProperty2) {
      this.indexOffset = 0;
      this.indexOffset12 = 0;
      this.state3 = false;
      this.state2 = false;
      this.state11 = false;
      this.state1 = false;
      this.state7 = false;
      this.state8 = false;
      this.state5 = false;
      this.state4 = false;
      this.state = false;
      this.state6 = false;
      this.movementSpeedProperty = movementSpeedProperty2;
   }
}
