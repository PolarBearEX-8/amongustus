package cc.baritone.utils;

import java.util.Deque;
import java.util.LinkedList;
import java.util.stream.Stream;

public interface DepthFirstManager {
   Object resolveTarget(Class clazz, int count) throws AuxiliaryClientManager;

   Object resolveTarget(Class clazz, Object entry);

   Enum resolveEnum3(Class clazz) throws TertiaryClientManager, AuxiliaryClientManager;

   String getTextAsText() throws AuxiliaryClientManager;

   void applyIndexByIndex(int count) throws TooManyException;

   String getText3();

   String getText2();

   BreadthFirstScheduler getBreadthFirstScheduler2() throws AuxiliaryClientManager;

   boolean matchesClass(Class clazz, int count) throws AuxiliaryClientManager;

   Object resolveTarget(Class clazz, Object entry, int count) throws AuxiliaryClientManager;

   Object resolveTargetByListener10Extens(Listener10Extension listener10Extension, Object entry) throws TertiaryClientManager, AuxiliaryClientManager;

   Enum resolveEnumByClass(Class clazz) throws AuxiliaryClientManager;

   Object resolveTarget3(Listener10Extension listener10Extension, Object entry);

   Object resolveTarget2(Class clazz);

   boolean size(int count);

   Enum resolveEnum(Class clazz) throws AuxiliaryClientManager;

   Object resolveTargetByListener10Extens(Listener10Extension listener10Extension) throws TertiaryClientManager, AuxiliaryClientManager;

   boolean isActive();

   Object resolveTargetByClass(Class clazz, int count) throws TertiaryClientManager, AuxiliaryClientManager;

   Enum resolveEnumByClass(Class clazz, int count) throws AuxiliaryClientManager;

   Enum resolveEnum(Class clazz, int count) throws TertiaryClientManager, AuxiliaryClientManager;

   boolean isActiveAsFlag();

   Deque getDeque();

   void applyIndex2(int count) throws AuxiliaryClientManager;

   Object resolveTarget5(Class clazz) throws AuxiliaryClientManager;

   DepthFirstManager getDepthFirstManager();

   String resolveText(int count) throws AuxiliaryClientManager;

   LinkedList getLinkedList();

   Object resolveTarget(Listener10Extension listener10Extension);

   Enum resolveEnum(Class clazz, Enum enumItem) throws AuxiliaryClientManager;

   boolean matchesClass(Class clazz) throws AuxiliaryClientManager;

   BreadthFirstScheduler resolveBreadthFirstScheduler(int count) throws AuxiliaryClientManager;

   BreadthFirstScheduler getBreadthFirstSchedulerAsBreadthFirstSche() throws AuxiliaryClientManager;

   Stream resolveStream(BaseListener baseListener);

   boolean isEmpty();

   boolean isActive2();

   Object resolveTargetByClass(Class clazz) throws TertiaryClientManager, AuxiliaryClientManager;

   Object resolveTarget(AuxiliaryListener10Extension auxiliaryListener10Extension, Object entry);

   Object resolveTarget4(Listener10Extension listener10Extension, Object entry);

   Object resolveTargetByAuxiliaryListene(AuxiliaryListener10Extension auxiliaryListener10Extension);

   Object resolveTarget2(AuxiliaryListener10Extension auxiliaryListener10Extension) throws TertiaryClientManager, AuxiliaryClientManager;

   Object resolveTarget2(Listener10Extension listener10Extension, Object entry) throws TertiaryClientManager, AuxiliaryClientManager;

   Object resolveTargetByClass(Class clazz, Object entry) throws AuxiliaryClientManager;

   Object resolveTarget(Listener10Extension listener10Extension, Object entry, Object entry2);

   String getText() throws AuxiliaryClientManager;

   Object resolveTarget3(Class clazz) throws TertiaryClientManager, AuxiliaryClientManager;

   BreadthFirstScheduler getBreadthFirstScheduler();

   Object resolveTarget(AuxiliaryListener10Extension auxiliaryListener10Extension) throws TertiaryClientManager, AuxiliaryClientManager;

   boolean sizeByIndex(int count);

   Object resolveTarget3(AuxiliaryListener10Extension auxiliaryListener10Extension);

   Object resolveTargetByListener10Extens(Listener10Extension listener10Extension, Object entry, Object entry2);

   Object resolveTarget4(Class clazz) throws AuxiliaryClientManager;

   Object resolveTarget2(Class clazz, Object entry) throws AuxiliaryClientManager;

   Enum resolveEnum2(Class clazz) throws TertiaryClientManager, AuxiliaryClientManager;

   boolean size2(int count);

   void applyIndex(int count) throws ExceptionAdapter;

   Object resolveTarget(Class clazz);

   Object resolveTarget(Listener10Extension listener10Extension, Object entry) throws TertiaryClientManager, AuxiliaryClientManager;
}
