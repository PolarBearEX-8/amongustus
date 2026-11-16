package cc.baritone.utils;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;

public class BreadthFirstValidator implements DepthFirstManager {
   public final GenericListener genericListener;
   public final HeuristicSampler heuristicSampler;
   public final LinkedList linkedList;
   public final Deque deque;

   @Override
   public Enum resolveEnum3(Class clazz) throws TertiaryClientManager, AuxiliaryClientManager {
      return this.getBreadthFirstSchedulerAsBreadthFirstSche().resolveEnum(clazz);
   }

   @Override
   public void applyIndex2(int count) throws AuxiliaryClientManager {
      if (this.linkedList.size() < count) {
         throw new AuxiliaryClientManager(count + this.deque.size());
      }
   }

   @Override
   public String getText() throws AuxiliaryClientManager {
      return this.getBreadthFirstSchedulerAsBreadthFirstSche().getLabelText();
   }

   @Override
   public boolean isActiveAsFlag() {
      return this.size2(1);
   }

   @Override
   public Object resolveTarget(Listener10Extension listener10Extension, Object entry, Object entry2) {
      java.util.ArrayList list = new java.util.ArrayList(this.linkedList);
      java.util.ArrayList list2 = new java.util.ArrayList(this.deque);

      try {
         return this.resolveTarget(listener10Extension, entry);
      } catch (Exception exception) {
         this.linkedList.clear();
         this.linkedList.addAll(list);
         this.deque.clear();
         this.deque.addAll(list2);
         return entry2;
      }
   }

   @Override
   public Enum resolveEnum2(Class clazz) throws TertiaryClientManager, AuxiliaryClientManager {
      return this.resolveEnum(clazz, 0);
   }

   @Override
   public Enum resolveEnum(Class clazz, int count) throws TertiaryClientManager, AuxiliaryClientManager {
      return this.resolveBreadthFirstScheduler(count).resolveEnum(clazz);
   }

   @Override
   public boolean matchesClass(Class clazz, int count) throws AuxiliaryClientManager {
      return this.resolveBreadthFirstScheduler(count).matchesClass(clazz);
   }

   @Override
   public Object resolveTarget(Class clazz, Object entry, int count) throws AuxiliaryClientManager {
      try {
         return this.resolveTargetByClass(clazz, count);
      } catch (TertiaryClientManager exception) {
         return entry;
      }
   }

   public BreadthFirstValidator getBreadthFirstValidator() {
      return new BreadthFirstValidator(this.genericListener, this.linkedList, this.deque);
   }

   @Override
   public BreadthFirstScheduler getBreadthFirstScheduler() {
      return (BreadthFirstScheduler)(this.deque.size() > 0 ? (BreadthFirstScheduler)this.deque.getLast() : BreadthFirstSampler.getEnumBreadthFirstScheduler());
   }

   @Override
   public Object resolveTargetByClass(Class clazz, Object entry) throws AuxiliaryClientManager {
      try {
         Object entry2 = this.getBreadthFirstScheduler2().resolveTarget(clazz);
         this.getBreadthFirstSchedulerAsBreadthFirstSche();
         return entry2;
      } catch (TertiaryClientManager exception) {
         return entry;
      }
   }

   @Override
   public BreadthFirstScheduler getBreadthFirstScheduler2() throws AuxiliaryClientManager {
      return this.resolveBreadthFirstScheduler(0);
   }

   @Override
   public Object resolveTarget2(Listener10Extension listener10Extension, Object entry) throws TertiaryClientManager, AuxiliaryClientManager {
      return this.getBreadthFirstValidator().resolveTarget(listener10Extension, entry);
   }

   @Override
   public boolean isEmpty() {
      return !this.deque.isEmpty();
   }

   @Override
   public Object resolveTarget(AuxiliaryListener10Extension auxiliaryListener10Extension) throws TertiaryClientManager, AuxiliaryClientManager {
      return this.getBreadthFirstValidator().resolveTarget2(auxiliaryListener10Extension);
   }

   @Override
   public Object resolveTargetByListener10Extens(Listener10Extension listener10Extension, Object entry, Object entry2) {
      return this.getBreadthFirstValidator().resolveTarget(listener10Extension, entry, entry2);
   }

   @Override
   public boolean size(int count) {
      return this.linkedList.size() >= count;
   }

   @Override
   public Enum resolveEnumByClass(Class clazz) throws AuxiliaryClientManager {
      return this.resolveEnum(clazz, null);
   }

   @Override
   public BreadthFirstScheduler resolveBreadthFirstScheduler(int count) throws AuxiliaryClientManager {
      this.applyIndex2(count + 1);
      return (BreadthFirstScheduler)this.linkedList.get(count);
   }

   @Override
   public Object resolveTarget(AuxiliaryListener10Extension auxiliaryListener10Extension, Object entry) {
      java.util.ArrayList list = new java.util.ArrayList(this.linkedList);
      java.util.ArrayList list2 = new java.util.ArrayList(this.deque);

      try {
         return this.resolveTarget2(auxiliaryListener10Extension);
      } catch (Exception exception) {
         this.linkedList.clear();
         this.linkedList.addAll(list);
         this.deque.clear();
         this.deque.addAll(list2);
         return entry;
      }
   }

   @Override
   public BreadthFirstScheduler getBreadthFirstSchedulerAsBreadthFirstSche() throws AuxiliaryClientManager {
      this.applyIndex2(1);
      BreadthFirstScheduler breadthFirstScheduler = (BreadthFirstScheduler)this.linkedList.removeFirst();
      this.deque.add(breadthFirstScheduler);
      return breadthFirstScheduler;
   }

   @Override
   public Object resolveTarget3(AuxiliaryListener10Extension auxiliaryListener10Extension) {
      return this.resolveTarget(auxiliaryListener10Extension, null);
   }

   @Override
   public Object resolveTarget2(Class clazz) {
      return this.getBreadthFirstValidator().resolveTarget2(clazz);
   }

   @Override
   public void applyIndexByIndex(int count) throws TooManyException {
      if (this.linkedList.size() > count) {
         throw new TooManyException(count + this.deque.size());
      }
   }

   @Override
   public Object resolveTargetByClass(Class clazz, int count) throws TertiaryClientManager, AuxiliaryClientManager {
      return this.resolveBreadthFirstScheduler(count).resolveTarget(clazz);
   }

   @Override
   public Enum resolveEnum(Class clazz, Enum enumItem) throws AuxiliaryClientManager {
      try {
         this.resolveEnum2(clazz);
         return this.resolveEnum3(clazz);
      } catch (TertiaryClientManager exception) {
         return enumItem;
      }
   }

   @Override
   public Enum resolveEnumByClass(Class clazz, int count) throws AuxiliaryClientManager {
      try {
         return this.resolveEnum(clazz, count);
      } catch (TertiaryClientManager exception) {
         return null;
      }
   }

   @Override
   public String getText2() {
      return this.getBreadthFirstScheduler().getLabelText();
   }

   @Override
   public DepthFirstManager getDepthFirstManager() {
      return this.getBreadthFirstValidator();
   }

   @Override
   public LinkedList getLinkedList() {
      return this.linkedList;
   }

   @Override
   public String getText3() {
      return this.linkedList.size() > 0 ? ((BreadthFirstScheduler)this.linkedList.getFirst()).getLabelText2() : "";
   }

   @Override
   public Object resolveTargetByClass(Class clazz) throws TertiaryClientManager, AuxiliaryClientManager {
      return this.resolveTargetByClass(clazz, 0);
   }

   public BreadthFirstValidator(GenericListener genericListener2, List list) {
      this(genericListener2, new LinkedList(list), new LinkedList());
   }

   @Override
   public boolean size2(int count) {
      return this.linkedList.size() == count;
   }

   @Override
   public Object resolveTargetByListener10Extens(Listener10Extension listener10Extension) throws TertiaryClientManager, AuxiliaryClientManager {
      return this.resolveTarget2(listener10Extension, null);
   }

   @Override
   public boolean sizeByIndex(int count) {
      return this.linkedList.size() <= count;
   }

   @Override
   public Object resolveTarget2(Class clazz, Object entry) throws AuxiliaryClientManager {
      return this.resolveTarget(clazz, entry, 0);
   }

   @Override
   public Object resolveTarget3(Class clazz) throws TertiaryClientManager, AuxiliaryClientManager {
      return this.getBreadthFirstSchedulerAsBreadthFirstSche().resolveTarget(clazz);
   }

   @Override
   public Object resolveTarget(Listener10Extension listener10Extension, Object entry) throws TertiaryClientManager, AuxiliaryClientManager {
      try {
         return listener10Extension.resolveTarget(this.heuristicSampler, entry);
      } catch (Exception exception) {
         throw new TertiaryClientManager(
            this.isActive() ? this.getBreadthFirstScheduler2() : this.getBreadthFirstScheduler(), listener10Extension.getClass().getSimpleName(), exception
         );
      }
   }

   @Override
   public Object resolveTarget3(Listener10Extension listener10Extension, Object entry) {
      return this.resolveTargetByListener10Extens(listener10Extension, entry, null);
   }

   @Override
   public boolean isActive() {
      return this.size(1);
   }

   @Override
   public Object resolveTargetByAuxiliaryListene(AuxiliaryListener10Extension auxiliaryListener10Extension) {
      return this.getBreadthFirstValidator().resolveTarget3(auxiliaryListener10Extension);
   }

   @Override
   public Object resolveTarget5(Class clazz) throws AuxiliaryClientManager {
      return this.resolveTarget(clazz, 0);
   }

   @Override
   public boolean matchesClass(Class clazz) throws AuxiliaryClientManager {
      return this.matchesClass(clazz, 0);
   }

   @Override
   public Object resolveTarget2(AuxiliaryListener10Extension auxiliaryListener10Extension) throws TertiaryClientManager, AuxiliaryClientManager {
      try {
         return auxiliaryListener10Extension.resolveTarget(this.heuristicSampler);
      } catch (Exception exception) {
         throw new TertiaryClientManager(
            this.isActive() ? this.getBreadthFirstScheduler2() : this.getBreadthFirstScheduler(), auxiliaryListener10Extension.getClass().getSimpleName(), exception
         );
      }
   }

   @Override
   public boolean isActive2() {
      return this.sizeByIndex(1);
   }

   @Override
   public Object resolveTarget4(Class clazz) throws AuxiliaryClientManager {
      return this.resolveTargetByClass(clazz, null);
   }

   public static GenericListener resolveGenericListener(BreadthFirstValidator breadthFirstValidator) {
      return breadthFirstValidator.genericListener;
   }

   private BreadthFirstValidator(GenericListener genericListener2, Deque deque2, Deque deque3) {
      this.genericListener = genericListener2;
      this.heuristicSampler = new BreadthFirstValidatoHeuristicSampler(this, null);
      this.linkedList = new LinkedList(deque2);
      this.deque = new LinkedList(deque3);
   }

   @Override
   public Stream resolveStream(BaseListener baseListener) {
      try {
         return baseListener.empty(this.heuristicSampler);
      } catch (ExceptionAdapter exception) {
      } catch (Exception exception2) {
         exception2.printStackTrace();
      }

      return Stream.empty();
   }

   @Override
   public Enum resolveEnum(Class clazz) throws AuxiliaryClientManager {
      return this.resolveEnumByClass(clazz, 0);
   }

   @Override
   public Deque getDeque() {
      return this.deque;
   }

   @Override
   public Object resolveTarget(Class clazz) {
      return this.resolveTarget(clazz, null);
   }

   @Override
   public Object resolveTarget4(Listener10Extension listener10Extension, Object entry) {
      return this.resolveTarget(listener10Extension, entry, null);
   }

   @Override
   public String resolveText(int count) throws AuxiliaryClientManager {
      return this.resolveBreadthFirstScheduler(count).getLabelText();
   }

   @Override
   public Object resolveTargetByListener10Extens(Listener10Extension listener10Extension, Object entry) throws TertiaryClientManager, AuxiliaryClientManager {
      return this.getBreadthFirstValidator().resolveTarget(listener10Extension, entry);
   }

   @Override
   public String getTextAsText() throws AuxiliaryClientManager {
      return this.resolveText(0);
   }

   @Override
   public Object resolveTarget(Class clazz, Object entry) {
      return this.getBreadthFirstValidator().resolveTarget(clazz, entry);
   }

   @Override
   public Object resolveTarget(Class clazz, int count) throws AuxiliaryClientManager {
      return this.resolveTarget(clazz, null, count);
   }

   @Override
   public Object resolveTarget(Listener10Extension listener10Extension) {
      return this.getBreadthFirstValidator().resolveTarget4(listener10Extension, null);
   }

   @Override
   public void applyIndex(int count) throws ExceptionAdapter {
      this.applyIndex2(count);
      this.applyIndexByIndex(count);
   }
}
