package cc.baritone.utils;

public interface GlobalListener extends HeuristicRecorder {
   void applyDefaultCustom(DefaultCustom defaultCustom, boolean flag);

   StandardListener getStandardListener();
}
