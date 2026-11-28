package net.augustus.events;

import net.minecraft.util.MovingObjectPosition;
import net.augustus.events.listeners.ClickListener;
import net.augustus.utils.TickCounter;
import net.augustus.utils.math.DefaultRandomGenerator;
import net.augustus.utils.math.StatisticalSampleBuffer;
import de.florianmichael.dietrichevents2.CancellableEvent;
import net.minecraft.client.Minecraft;

public class EventClick extends CancellableEvent<ClickListener> {
   private boolean breakingBlock;
   public static boolean wasBreakingBlock = false;
   private int leftClicks;
   private Runnable leftClickAction;
   private boolean overrideBlockBreak;
   private boolean leftMouseDown;
   private boolean ignoreVanillaClicks;
   public static final TickCounter tickCounter = new TickCounter();
   private boolean allowRightClick = true;
   public static int clicksThisTick = 0;
   public static final int ID = 7;
   private boolean allowMultiRightClick;
   private Runnable blockBreakAction;
   private Runnable rightClickAction;
   private boolean blockAllClicks;
   public static int blockBreakDelayTicks = 0;
   private final ClickButtonType buttonType;
   private boolean forceBreakBlock;
   public static StatisticalSampleBuffer recentClicksBuffer = new StatisticalSampleBuffer(19);
   private boolean delayBlockBreak;
   private int rightClicks;
   private boolean rightMouseDown;
   private boolean criticalHit;
   public static boolean pendingBlockBreakDelay = false;

   public void addLeftClicks(int n) {
      this.leftClicks += n;
   }

   public EventClick(ClickButtonType clickButtonType) {
      this.leftClicks = 0;
      this.rightClicks = 0;
      this.ignoreVanillaClicks = false;
      this.blockAllClicks = false;
      this.rightMouseDown = false;
      this.leftMouseDown = false;
      this.allowMultiRightClick = false;
      this.delayBlockBreak = false;
      this.overrideBlockBreak = false;
      this.breakingBlock = false;
      this.criticalHit = false;
      this.forceBreakBlock = false;
      this.buttonType = clickButtonType;
   }

   public void setDelayBlockBreak(boolean flag) {
      this.delayBlockBreak = flag;
   }

   @Override
   public void call(ClickListener listener) {
      switch (this.buttonType) {
         case LEFT:
            listener.onClick(this);
            break;
         case MID:
            listener.onMiddleClick();
      }
   }

   public static boolean shouldBreakBlockAfterDelay(boolean flag) {
      if (pendingBlockBreakDelay && flag) {
         Minecraft minecraft = Minecraft.getMinecraft();
         if (minecraft.currentScreen == null) {
            if (minecraft.inGameHasFocus && minecraft.objectMouseOver != null && minecraft.objectMouseOver.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK) {
               int n = blockBreakDelayTicks;
               if (tickCounter.hasReached(n)) {
                  blockBreakDelayTicks = DefaultRandomGenerator.instance.nextInt((byte)1, (byte)5);
                  tickCounter.reset();
                  pendingBlockBreakDelay = false;
                  return true;
               }

               return minecraft.currentScreen == null && minecraft.inGameHasFocus;
            }
         }

         tickCounter.reset();
         pendingBlockBreakDelay = false;
         return minecraft.currentScreen == null && minecraft.inGameHasFocus;
      } else {
         tickCounter.reset();
         return false;
      }
   }

   public boolean isLeftMouseDown() {
      return this.leftMouseDown;
   }

   public void setLeftClickAction(Runnable runnable) {
      this.leftClickAction = runnable;
   }


   public boolean isRightMouseDown() {
      return this.rightMouseDown;
   }

   public boolean isIgnoreVanillaClicks() {
      return this.ignoreVanillaClicks;
   }

   public boolean isAllowMultiRightClick() {
      return this.allowMultiRightClick;
   }

   public void setRightMouseDown(boolean flag) {
      this.rightMouseDown = flag;
   }

   public void setAllowMultiRightClick(boolean flag) {
      this.allowMultiRightClick = flag;
   }

   public boolean isForceBreakBlock() {
      return this.forceBreakBlock;
   }

   public boolean isBlockAllClicks() {
      return this.blockAllClicks;
   }

   public boolean isOverrideBlockBreak() {
      return this.overrideBlockBreak;
   }

   public boolean isAllowRightClick() {
      return this.allowRightClick;
   }

   public void setOverrideBlockBreak(boolean flag) {
      this.overrideBlockBreak = flag;
   }

   public Runnable getRightClickAction() {
      return this.rightClickAction;
   }

   public void setRightClickAction(Runnable runnable) {
      this.rightClickAction = runnable;
   }

   public void setAllowRightClick(boolean flag) {
      this.allowRightClick = flag;
   }

   public int getLeftClicks() {
      return this.leftClicks;
   }

   public void clearLeftClicks() {
      this.leftClicks = 0;
   }

   public void addRightClicks(int n) {
      this.rightClicks += n;
   }

   public void setForceBreakBlock(boolean flag) {
      this.forceBreakBlock = flag;
   }

   public void setBlockBreakAction(Runnable runnable) {
      this.blockBreakAction = runnable;
   }

   public Runnable getLeftClickAction() {
      return this.leftClickAction;
   }

   public void setIgnoreVanillaClicks(boolean flag) {
      this.ignoreVanillaClicks = flag;
   }

   public int getRightClicks() {
      return this.rightClicks;
   }

   public void setLeftMouseDown(boolean flag) {
      this.leftMouseDown = flag;
   }

   public void setCriticalHit(boolean flag) {
      this.criticalHit = flag;
   }

   public Runnable getBlockBreakAction() {
      return this.blockBreakAction;
   }

   public void setBreakingBlock(boolean flag) {
      this.breakingBlock = flag;
   }

   public boolean isBreakingBlock() {
      return this.breakingBlock;
   }

   public void setBlockAllClicks(boolean flag) {
      this.blockAllClicks = flag;
   }

   public EventClick(ClickButtonType clickButtonType, boolean flag, boolean flag2, Runnable runnable, Runnable runnable2) {
      this.leftClicks = 0;
      this.rightClicks = 0;
      this.ignoreVanillaClicks = false;
      this.blockAllClicks = false;
      this.rightMouseDown = false;
      this.leftMouseDown = false;
      this.allowMultiRightClick = false;
      this.delayBlockBreak = false;
      this.overrideBlockBreak = false;
      this.breakingBlock = false;
      this.criticalHit = false;
      this.forceBreakBlock = false;
      this.buttonType = clickButtonType;
      this.rightMouseDown = flag;
      this.leftMouseDown = flag2;
      this.leftClickAction = runnable;
      this.rightClickAction = runnable2;
      this.blockBreakAction = null;
   }

   public boolean isDelayBlockBreak() {
      return this.delayBlockBreak;
   }

   public boolean isCriticalHit() {
      return this.criticalHit;
   }
}
