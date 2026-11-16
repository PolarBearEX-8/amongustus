package cc.baritone.utils;

public interface InternalCustom {
   void updateState();

   void applyShortArray(short[] shortValueArray, int count, int count2) throws AuxiliaryExceptionAdapter;

   void updateState2();

   void applyBreadthFirstPlanner(BreadthFirstPlanner breadthFirstPlanner) throws AuxiliaryExceptionAdapter;

   boolean isEnabled();

   int getMicrosecondPosition();
}
