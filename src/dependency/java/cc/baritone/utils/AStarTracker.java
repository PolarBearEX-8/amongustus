package cc.baritone.utils;

public interface AStarTracker {
   BreadthFirstScheduleClassCallback resolveBreadthFirstScheduleClassCal(Class clazz);

   DequeEntry getDequeEntry();

   Object resolveTarget(Class clazz, BreadthFirstScheduler breadthFirstScheduler) throws TertiaryClientManager;

   BreadthFirstScheduleClassCallbackImpl resolveBreadthFirstScheduleClassCal(Class clazz, Class clazz2);

   Object resolveTarget(Class clazz, Class clazz2, BreadthFirstScheduler breadthFirstScheduler, Object entry) throws TertiaryClientManager;
}
