package cc.baritone.utils;

public interface PassiveListener extends HeuristicRecorder {
   void clear();

   void applyBaseCustom(BaseCustom baseCustom, boolean flag);

   boolean matchesBaseCustom(BaseCustom baseCustom);
}
