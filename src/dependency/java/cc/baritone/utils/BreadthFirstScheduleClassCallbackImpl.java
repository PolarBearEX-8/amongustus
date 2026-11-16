package cc.baritone.utils;

public interface BreadthFirstScheduleClassCallbackImpl extends ClassCallback {
   Object resolveTarget(BreadthFirstScheduler breadthFirstScheduler, Object entry) throws Exception;

   Class getClass2();
}
