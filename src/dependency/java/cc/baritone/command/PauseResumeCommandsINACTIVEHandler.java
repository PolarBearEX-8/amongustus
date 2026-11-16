package cc.baritone.command;

import cc.baritone.api.INACTIVEHandler;
import cc.baritone.utils.ActiveListener;
import cc.baritone.utils.HeuristicCoordinator;
import cc.baritone.utils.Listener5Entry;
import cc.baritone.utils.PauseManager;

public class PauseResumeCommandsINACTIVEHandler implements INACTIVEHandler {
   public final boolean[] enabledArray;
   public final ActiveListener activeListener;
   public final PauseManager pauseManager;

   @Override
   public String getText() {
      return "Pause/Resume Commands";
   }

   @Override
   public boolean isActive() {
      return true;
   }

   @Override
   public void updateState() {
   }

   @Override
   public double getValue() {
      return 0.0;
   }

   @Override
   public boolean isEnabled() {
      return this.enabledArray[0];
   }

   @Override
   public Listener5Entry resolveListener5Entry(boolean flag, boolean flag2) {
      this.activeListener.getPassiveListener().clear();
      return new Listener5Entry(null, HeuristicCoordinator.REQUEST_PAUSE);
   }

   public PauseResumeCommandsINACTIVEHandler(PauseManager pauseManager2, boolean[] flagArray, ActiveListener activeListener2) {
      this.pauseManager = pauseManager2;
      this.enabledArray = flagArray;
      this.activeListener = activeListener2;
   }
}
