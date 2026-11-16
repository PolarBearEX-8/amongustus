package augustus.modes.movement;

import augustus.modules.movement.LongJump;
import client.ClientGlobals;
import client.event.ClickListener;
import client.event.EventClick;
import client.event.EventMove;
import client.event.MotionListener;
import client.event.MoveListener;
import client.event.TickListener;
import client.event.UpdateStateListener;
import client.module.Module;
import client.rotation.RotationManager;
import client.setting.SettingGroup;
import client.util.AuxiliaryMoveListenerAdapter;
import client.util.MoveUtil;
import client.util.SimulatedPlayer;
import client.util.SlotManager;
import java.util.function.BiFunction;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemStack;

public class LongJumpMatrixMode extends AuxiliaryMoveListenerAdapter implements ClickListener, UpdateStateListener, MotionListener, TickListener, MoveListener {
   public boolean enabled3 = true;
   public int count2 = 0;
   public int count3 = -1;

   public static BiFunction getBiFunction() {
      return ((item, group) -> new LongJumpMatrixMode((LongJump)item, (SettingGroup)group));
   }

   @Override
   public void updateState() {
      this.enabled3 = true;
      this.count2 = 0;
   }

   @Override
   public void onClick(EventClick event) {
      if (this.count3 != -1 && this.enabled3) {
         this.count2++;
         if (this.count2 < 4) {
            event.setState11(true);
         } else {
            this.enabled3 = false;
         }
      }

      if (!this.enabled3) {
         this.count2++;
      }
   }

   @Override
   public void clear() {
   }

   @Override
   public void onMove(EventMove event) {
      float factor = 0.0F;
      event.setScaleFactor7(factor);
      float factor2 = 0.0F;
      event.setScaleFactor2(factor2);
      if (!this.enabled3 && this.count2 > 14) {
         float factor3 = 1.0F;
         event.setScaleFactor7(factor3);
      }
   }

   private int getCount2() {
      for (int index = 36; index < minecraft.thePlayer.container.itemList2.size(); index++) {
         ItemStack stack = minecraft.thePlayer.container.getSlot(index).getStack();
         if (stack != null && stack.getItem() instanceof ItemBow) {
            return index - 36;
         }
      }

      return -1;
   }

   @Override
   public void updateState2() {
      super.updateState2();
      byte byteValue = 7;
      this.applyIndex(byteValue);
      byte byteValue2 = 40;
      this.applyIndex(byteValue2);
      byte byteValue3 = 43;
      this.applyIndex(byteValue3);
      byte byteValue4 = 34;
      this.applyIndex(byteValue4);
   }

   @Override
   public void onPostMotion() {
      this.count3 = -1;
      if (this.enabled3) {
         this.count3 = this.getCount2();
      }

      if (this.count3 >= 0) {
         int count5 = this.count3;
         byte byteValue = 7;
         SlotManager.applyModule3(this.module, byteValue, count5);
      }
   }

   @Override
   public void onPostUpdateState() {
      if (minecraft.thePlayer.hurtTime != 0) {
         if (minecraft.thePlayer.onGround) {
            minecraft.thePlayer.motionY = 0.42F;
         }

         if (minecraft.thePlayer.hurtTime == 8) {
            minecraft.thePlayer.secondaryFloatEntry.factor2 = minecraft.thePlayer.secondaryFloatEntry.moveForward;
            MoveUtil.applyDoubleByDouble(6.0);
         }

         if (minecraft.thePlayer.hurtTime == 1) {
            this.setEnabled(false);
         }
      }
   }

   @Override
   public void onPreTick() {
      if (this.enabled3) {
         RotationManager rotationManager = ClientGlobals.rotationManager;
         float floatItem = SimulatedPlayer.getFloat3(-90.0F, ClientGlobals.rotationManager.getRotation3().pitch);
         Module module = this.module;
         byte byteValue = -10;
         float factor = floatItem;
         rotationManager.applyModule(factor, byteValue, module);
      }
   }

   public LongJumpMatrixMode(LongJump longJump, SettingGroup settingGroup) {
      super(longJump, settingGroup, "Minemen");
   }
}
