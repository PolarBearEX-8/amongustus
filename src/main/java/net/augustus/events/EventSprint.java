package net.augustus.events;

import net.augustus.events.listeners.SprintListener;
import net.augustus.utils.MoveUtil;
import de.florianmichael.dietrichevents2.AbstractEvent;

public class EventSprint extends AbstractEvent<SprintListener> {
   private boolean overrideSprintKey;
   public static final int ID = 36;
   private boolean applyJumpBoost;
   private boolean keepSprintOnCollision;
   private boolean forceSprint = false;
   private boolean motionSprint = false;
   private boolean omniJumpSprint = false;
   private SprintEventType type;
   private boolean ignoreHunger = false;
   private boolean sprintKeyDown;

   public void setForceSprint(boolean flag) {
      if (MoveUtil.isMoving()) {
         this.forceSprint = flag;
      }
   }

   public boolean isApplyJumpBoost() {
      return this.applyJumpBoost;
   }

   public boolean isIgnoreHunger() {
      return this.ignoreHunger;
   }

   public boolean isOmniJumpSprint() {
      return this.omniJumpSprint;
   }

   public EventSprint() {
      this.keepSprintOnCollision = false;
      this.applyJumpBoost = true;
      this.sprintKeyDown = false;
      this.overrideSprintKey = false;
   }

   public void setKeepSprintOnCollision(boolean flag) {
      this.keepSprintOnCollision = flag;
   }

   public boolean isOverrideSprintKey() {
      return this.overrideSprintKey;
   }

   public void setApplyJumpBoost(boolean flag) {
      this.applyJumpBoost = flag;
   }

   public boolean isMotionSprint() {
      return this.motionSprint;
   }

   public void setIgnoreHunger(boolean flag) {
      this.ignoreHunger = flag;
   }

   public boolean isSprintKeyDown() {
      return this.sprintKeyDown;
   }

   public void setSprintKeyDown(boolean flag) {
      this.overrideSprintKey = true;
      this.sprintKeyDown = flag;
   }

   public boolean isForceSprint() {
      return this.forceSprint;
   }

   private void reset(SprintEventType sprintEventType) {
      this.type = sprintEventType;
      this.forceSprint = false;
      this.motionSprint = false;
      this.omniJumpSprint = false;
      this.ignoreHunger = false;
      this.keepSprintOnCollision = false;
      this.applyJumpBoost = true;
      this.sprintKeyDown = false;
      this.overrideSprintKey = false;
   }

   public static void reset(EventSprint event, SprintEventType sprintEventType) {
      event.reset(sprintEventType);
   }

   public void setOmniJumpSprint(boolean flag) {
      this.omniJumpSprint = flag;
   }

   @Override
   public void call(SprintListener listener) {
      switch (this.type) {
         case SPRINT:
            listener.onSprint(this);
            break;
         case JUMP_SPRINT:
            listener.onJumpSprint(this);
            break;
         case MOTION_SPRINT:
            listener.onMotionSprint(this);
      }
   }

   public boolean isKeepSprintOnCollision() {
      return this.keepSprintOnCollision;
   }

   public void setMotionSprint(boolean flag) {
      this.motionSprint = flag;
   }

}
