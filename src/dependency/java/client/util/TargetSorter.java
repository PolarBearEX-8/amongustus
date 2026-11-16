package client.util;

import client.event.IndirectListener;
import client.event.PreUpdateObserver;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class TargetSorter {
   public IndirectListener indirectListener;

   private static PreUpdateObserver resolvePreUpdateObserverByABCDEFGHIJKLMNOP(ABCDEFGHIJKLMNOPQREnController aBCDEFGHIJKLMNOPQREnController) throws AuxiliaryExceptionAdapter {
      float factor = BuildManager.computeFactor(aBCDEFGHIJKLMNOPQREnController.getLabelText(), Float.NaN);
      if (factor == Float.NaN) {
         throw new AuxiliaryExceptionAdapter("Invalid float value: " + aBCDEFGHIJKLMNOPQREnController);
      } else {
         return new AimSorterAimValidatorImpl(factor);
      }
   }

   public TargetSorter(IndirectListener indirectListener2) {
      this.indirectListener = indirectListener2;
   }

   private static Deque resolveDeque(Deque deque, ABCDEFGHIJKLMNOPQREnum aBCDEFGHIJKLMNOPQREnum, boolean flag) throws AuxiliaryExceptionAdapter {
      ArrayDeque arrayDeque = new ArrayDeque();
      int count = 0;
      Iterator iterator = deque.iterator();

      while (iterator.hasNext()) {
         ABCDEFGHIJKLMNOPQREnController aBCDEFGHIJKLMNOPQREnController = (ABCDEFGHIJKLMNOPQREnController)iterator.next();
         iterator.remove();
         if (count == 0 && aBCDEFGHIJKLMNOPQREnController.getABCDEFGHIJKLMNOPQREnum() == aBCDEFGHIJKLMNOPQREnum) {
            return arrayDeque;
         }

         arrayDeque.add(aBCDEFGHIJKLMNOPQREnController);
         if (aBCDEFGHIJKLMNOPQREnController.getABCDEFGHIJKLMNOPQREnum() == ABCDEFGHIJKLMNOPQREnum.BRACKET_OPEN) {
            count++;
         }

         if (aBCDEFGHIJKLMNOPQREnController.getABCDEFGHIJKLMNOPQREnum() == ABCDEFGHIJKLMNOPQREnum.BRACKET_CLOSE) {
            count--;
         }
      }

      if (flag) {
         throw new AuxiliaryExceptionAdapter("Missing end token: " + aBCDEFGHIJKLMNOPQREnum);
      } else {
         return arrayDeque;
      }
   }

   private static PreUpdateObserver resolvePreUpdateObserver(PlusMinusType plusMinusType, PreUpdateObserver[] preUpdateObserverArray) throws AuxiliaryExceptionAdapter {
      AimSorter[] aimSorterArray = plusMinusType.resolveAimSorterArray(preUpdateObserverArray);
      if (preUpdateObserverArray.length != aimSorterArray.length) {
         throw new AuxiliaryExceptionAdapter(
            "Invalid number of arguments, function: \"" + plusMinusType.getLabelText() + "\", count arguments: " + preUpdateObserverArray.length + ", should be: " + aimSorterArray.length
         );
      } else {
         for (int index = 0; index < preUpdateObserverArray.length; index++) {
            AimSorter bOOL = preUpdateObserverArray[index].getBOOL();
            AimSorter aimSorter = aimSorterArray[index];
            if (bOOL != aimSorter) {
               throw new AuxiliaryExceptionAdapter(
                  "Invalid argument type, function: \"" + plusMinusType.getLabelText() + "\", index: " + index + ", type: " + bOOL + ", should be: " + aimSorter
               );
            }
         }

         if (plusMinusType.getAimSorter() == AimSorter.FLOAT) {
            return new PreUpdateObserverAimValidator(plusMinusType, preUpdateObserverArray);
         } else if (plusMinusType.getAimSorter() == AimSorter.BOOL) {
            return new PreUpdateObserverTargetObserver(plusMinusType, preUpdateObserverArray);
         } else if (plusMinusType.getAimSorter() == AimSorter.FLOAT_ARRAY) {
            return new PlusMinusTypeDistanceBridge(plusMinusType, preUpdateObserverArray);
         } else {
            throw new AuxiliaryExceptionAdapter("Unknown function type: " + plusMinusType.getAimSorter() + ", function: " + plusMinusType.getLabelText());
         }
      }
   }

   private PreUpdateObserver resolvePreUpdateObserver(List list, List list2) throws AuxiliaryExceptionAdapter {
      if (list.size() != list2.size() + 1) {
         throw new AuxiliaryExceptionAdapter("Invalid infix expression, expressions: " + list.size() + ", operators: " + list2.size());
      } else if (list.size() == 1) {
         return (PreUpdateObserver)list.get(0);
      } else {
         int count = Integer.MAX_VALUE;
         int count2 = Integer.MIN_VALUE;

         for (PlusMinusType plusMinusType : (Iterable<PlusMinusType>)(Iterable<?>)list2) {
            count = Math.min(plusMinusType.getCount(), count);
            count2 = Math.max(plusMinusType.getCount(), count2);
         }

         if (count2 >= count && count2 - count <= 10) {
            while (count2 >= count) {
               this.applyList(list, list2, count2);
               count2--;
            }

            if (list.size() == 1 && list2.size() == 0) {
               return (PreUpdateObserver)list.get(0);
            } else {
               throw new AuxiliaryExceptionAdapter("Error merging operators, expressions: " + list.size() + ", operators: " + list2.size());
            }
         } else {
            throw new AuxiliaryExceptionAdapter("Invalid infix precedence, min: " + count + ", max: " + count2);
         }
      }
   }

   private void applyList(List list, List list2, int count) throws AuxiliaryExceptionAdapter {
      for (int index = 0; index < list2.size(); index++) {
         PlusMinusType plusMinusType = (PlusMinusType)list2.get(index);
         if (plusMinusType.getCount() == count) {
            list2.remove(index);
            PreUpdateObserver preUpdateObserver = (PreUpdateObserver)list.remove(index);
            PreUpdateObserver preUpdateObserver2 = (PreUpdateObserver)list.remove(index);
            PreUpdateObserver preUpdateObserver3 = resolvePreUpdateObserver(plusMinusType, new PreUpdateObserver[]{preUpdateObserver, preUpdateObserver2});
            list.add(index, preUpdateObserver3);
            index--;
         }
      }
   }

   public PreUpdateObserver resolvePreUpdateObserver(String text) throws AuxiliaryExceptionAdapter {
      try {
         ABCDEFGHIJKLMNOPQREnController[] aBCDEFGHIJKLMNOPQREnControllerArray = CharacterHelper.resolveABCDEFGHIJKLMNOPQREnControll(text);
         if (aBCDEFGHIJKLMNOPQREnControllerArray == null) {
            return null;
         } else {
            ArrayDeque arrayDeque = new ArrayDeque<>(Arrays.asList(aBCDEFGHIJKLMNOPQREnControllerArray));
            return this.resolvePreUpdateObserverByDeque(arrayDeque);
         }
      } catch (IOException ioException) {
         throw new AuxiliaryExceptionAdapter(ioException.getMessage(), ioException);
      }
   }

   private PreUpdateObserver resolvePreUpdateObserver(Deque deque) throws AuxiliaryExceptionAdapter {
      ABCDEFGHIJKLMNOPQREnController aBCDEFGHIJKLMNOPQREnController = (ABCDEFGHIJKLMNOPQREnController)deque.poll();
      applyTarget(aBCDEFGHIJKLMNOPQREnController, "Missing expression");
      switch (TargetSorterABCDEFGHIJKLMNOPQREnSwitchMap.countArray[aBCDEFGHIJKLMNOPQREnController.getABCDEFGHIJKLMNOPQREnum().ordinal()]) {
         case 1:
            return resolvePreUpdateObserverByABCDEFGHIJKLMNOP(aBCDEFGHIJKLMNOPQREnController);
         case 2:
            PlusMinusType plusMinusType = this.resolvePlusMinusType(aBCDEFGHIJKLMNOPQREnController, deque);
            if (plusMinusType != null) {
               return this.resolvePreUpdateObserver(plusMinusType, deque);
            }

            return this.resolvePreUpdateObserver(aBCDEFGHIJKLMNOPQREnController);
         case 3:
            return this.resolvePreUpdateObserver(aBCDEFGHIJKLMNOPQREnController, deque);
         case 4:
            PlusMinusType plusMinusType2 = PlusMinusType.resolvePlusMinusType(aBCDEFGHIJKLMNOPQREnController.getLabelText());
            applyTarget(plusMinusType2, "Invalid operator: " + aBCDEFGHIJKLMNOPQREnController);
            if (plusMinusType2 == PlusMinusType.PLUS) {
               return this.resolvePreUpdateObserver(deque);
            } else if (plusMinusType2 == PlusMinusType.MINUS) {
               PreUpdateObserver preUpdateObserver = this.resolvePreUpdateObserver(deque);
               return resolvePreUpdateObserver(PlusMinusType.NEG, new PreUpdateObserver[]{preUpdateObserver});
            } else if (plusMinusType2 == PlusMinusType.NOT) {
               PreUpdateObserver preUpdateObserver2 = this.resolvePreUpdateObserver(deque);
               return resolvePreUpdateObserver(PlusMinusType.NOT, new PreUpdateObserver[]{preUpdateObserver2});
            }
         default:
            throw new AuxiliaryExceptionAdapter("Invalid expression: " + aBCDEFGHIJKLMNOPQREnController);
      }
   }

   private PreUpdateObserver resolvePreUpdateObserverByDeque(Deque deque) throws AuxiliaryExceptionAdapter {
      if (deque.isEmpty()) {
         return null;
      } else {
         LinkedList list = new LinkedList();
         LinkedList list2 = new LinkedList();
         PreUpdateObserver preUpdateObserver = this.resolvePreUpdateObserver(deque);
         applyTarget(preUpdateObserver, "Missing expression");
         list.add(preUpdateObserver);

         while (true) {
            ABCDEFGHIJKLMNOPQREnController aBCDEFGHIJKLMNOPQREnController = (ABCDEFGHIJKLMNOPQREnController)deque.poll();
            if (aBCDEFGHIJKLMNOPQREnController == null) {
               return this.resolvePreUpdateObserverByList(list, list2);
            }

            if (aBCDEFGHIJKLMNOPQREnController.getABCDEFGHIJKLMNOPQREnum() != ABCDEFGHIJKLMNOPQREnum.OPERATOR) {
               throw new AuxiliaryExceptionAdapter("Invalid operator: " + aBCDEFGHIJKLMNOPQREnController);
            }

            PreUpdateObserver preUpdateObserver2 = this.resolvePreUpdateObserver(deque);
            applyTarget(preUpdateObserver2, "Missing expression");
            list2.add(aBCDEFGHIJKLMNOPQREnController);
            list.add(preUpdateObserver2);
         }
      }
   }

   private PreUpdateObserver[] resolvePreUpdateObserverArray(Deque deque) throws AuxiliaryExceptionAdapter {
      ArrayList list = new ArrayList();

      while (true) {
         Deque deque2 = resolveDeque(deque, ABCDEFGHIJKLMNOPQREnum.COMMA, false);
         PreUpdateObserver preUpdateObserver = this.resolvePreUpdateObserverByDeque(deque2);
         if (preUpdateObserver == null) {
            return ((PreUpdateObserver[])list.toArray(new PreUpdateObserver[list.size()]));
         }

         list.add(preUpdateObserver);
      }
   }

   private PreUpdateObserver resolvePreUpdateObserver(ABCDEFGHIJKLMNOPQREnController aBCDEFGHIJKLMNOPQREnController, Deque deque) throws AuxiliaryExceptionAdapter {
      Deque deque2 = resolveDeque(deque, ABCDEFGHIJKLMNOPQREnum.BRACKET_CLOSE, true);
      return this.resolvePreUpdateObserverByDeque(deque2);
   }

   private PlusMinusType resolvePlusMinusType(ABCDEFGHIJKLMNOPQREnController aBCDEFGHIJKLMNOPQREnController, Deque deque) throws AuxiliaryExceptionAdapter {
      ABCDEFGHIJKLMNOPQREnController aBCDEFGHIJKLMNOPQREnController2 = (ABCDEFGHIJKLMNOPQREnController)deque.peek();
      if (aBCDEFGHIJKLMNOPQREnController2 != null && aBCDEFGHIJKLMNOPQREnController2.getABCDEFGHIJKLMNOPQREnum() == ABCDEFGHIJKLMNOPQREnum.BRACKET_OPEN) {
         PlusMinusType plusMinusType = PlusMinusType.resolvePlusMinusType(aBCDEFGHIJKLMNOPQREnController.getLabelText());
         applyTarget(plusMinusType, "Unknown function: " + aBCDEFGHIJKLMNOPQREnController);
         return plusMinusType;
      } else {
         PlusMinusType plusMinusType2 = PlusMinusType.resolvePlusMinusType(aBCDEFGHIJKLMNOPQREnController.getLabelText());
         if (plusMinusType2 == null) {
            return null;
         } else if (plusMinusType2.computeCount(new PreUpdateObserver[0]) > 0) {
            throw new AuxiliaryExceptionAdapter("Missing arguments: " + plusMinusType2);
         } else {
            return plusMinusType2;
         }
      }
   }

   private PreUpdateObserver resolvePreUpdateObserverByList(List list, List list2) throws AuxiliaryExceptionAdapter {
      LinkedList list3 = new LinkedList();

      for (ABCDEFGHIJKLMNOPQREnController aBCDEFGHIJKLMNOPQREnController : (Iterable<ABCDEFGHIJKLMNOPQREnController>)(Iterable<?>)list2) {
         PlusMinusType plusMinusType = PlusMinusType.resolvePlusMinusType(aBCDEFGHIJKLMNOPQREnController.getLabelText());
         applyTarget(plusMinusType, "Invalid operator: " + aBCDEFGHIJKLMNOPQREnController);
         list3.add(plusMinusType);
      }

      return this.resolvePreUpdateObserver(list, list3);
   }

   private PreUpdateObserver resolvePreUpdateObserver(ABCDEFGHIJKLMNOPQREnController aBCDEFGHIJKLMNOPQREnController) throws AuxiliaryExceptionAdapter {
      if (this.indirectListener == null) {
         throw new AuxiliaryExceptionAdapter("Model variable not found: " + aBCDEFGHIJKLMNOPQREnController);
      } else {
         PreUpdateObserver preUpdateObserver = this.indirectListener.get(aBCDEFGHIJKLMNOPQREnController.getLabelText());
         if (preUpdateObserver == null) {
            throw new AuxiliaryExceptionAdapter("Model variable not found: " + aBCDEFGHIJKLMNOPQREnController);
         } else {
            return preUpdateObserver;
         }
      }
   }

   public TargetObserver resolveTargetObserver(String text) throws AuxiliaryExceptionAdapter {
      PreUpdateObserver preUpdateObserver = this.resolvePreUpdateObserver(text);
      if (!(preUpdateObserver instanceof TargetObserver)) {
         throw new AuxiliaryExceptionAdapter("Not a boolean expression: " + preUpdateObserver.getBOOL());
      } else {
         return (TargetObserver)preUpdateObserver;
      }
   }

   private static void applyTarget(Object entry, String text) throws AuxiliaryExceptionAdapter {
      if (entry == null) {
         throw new AuxiliaryExceptionAdapter(text);
      }
   }

   public AimValidator resolveAimValidator(String text) throws AuxiliaryExceptionAdapter {
      PreUpdateObserver preUpdateObserver = this.resolvePreUpdateObserver(text);
      if (!(preUpdateObserver instanceof AimValidator)) {
         throw new AuxiliaryExceptionAdapter("Not a float expression: " + preUpdateObserver.getBOOL());
      } else {
         return (AimValidator)preUpdateObserver;
      }
   }

   private PreUpdateObserver resolvePreUpdateObserver(PlusMinusType plusMinusType, Deque deque) throws AuxiliaryExceptionAdapter {
      if (plusMinusType.computeCount(new PreUpdateObserver[0]) == 0) {
         ABCDEFGHIJKLMNOPQREnController aBCDEFGHIJKLMNOPQREnController = (ABCDEFGHIJKLMNOPQREnController)deque.peek();
         if (aBCDEFGHIJKLMNOPQREnController == null || aBCDEFGHIJKLMNOPQREnController.getABCDEFGHIJKLMNOPQREnum() != ABCDEFGHIJKLMNOPQREnum.BRACKET_OPEN) {
            return resolvePreUpdateObserver(plusMinusType, new PreUpdateObserver[0]);
         }
      }

      ABCDEFGHIJKLMNOPQREnController aBCDEFGHIJKLMNOPQREnController2 = (ABCDEFGHIJKLMNOPQREnController)deque.poll();
      Deque deque2 = resolveDeque(deque, ABCDEFGHIJKLMNOPQREnum.BRACKET_CLOSE, true);
      PreUpdateObserver[] preUpdateObserverArray = this.resolvePreUpdateObserverArray(deque2);
      return resolvePreUpdateObserver(plusMinusType, preUpdateObserverArray);
   }
}
