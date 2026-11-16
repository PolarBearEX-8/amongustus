package client.util;

import client.event.PostMotionObserver;
import client.setting.CompositeOptionType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExtendedMapHolder {
   public Map map2 = new HashMap();
   public Map map = new HashMap();

   public PostMotionObserver resolvePostMotionObserver(Object entry) throws Exception {
      if (entry instanceof String) {
         String text = (String)entry;
         if (text.startsWith("!")) {
            String text2 = text.substring(1);
            return new TargetBuilder(this.resolvePostMotionObserver((Object)text2));
         }

         if (this.matches(text)) {
            return new ExtendedListenerController(Double.parseDouble(text));
         }

         if (this.matchesTargetByTarget(text)) {
            if (text.startsWith("-")) {
               String text3 = text.substring(1);
               InternalListenerRegistry internalListenerRegistry = this.get(text3);
               if (internalListenerRegistry != null) {
                  return new RayTraceAdapter(internalListenerRegistry);
               }
            } else {
               InternalListenerRegistry internalListenerRegistry2 = this.get(text);
               if (internalListenerRegistry2 != null) {
                  return internalListenerRegistry2;
               }
            }
         }
      } else if (entry instanceof List) {
         List list = (List)entry;
         return new GeometryAccumulator(this.resolvePostMotionObserver(list));
      }

      throw new Exception("Given object couldn't be converted to value! " + entry);
   }

   public boolean matchesText(String text) {
      return CompositeOptionType.OPERATORS.contains(text) || text.equals("?") || text.equals(":");
   }

   public CompositeOptionType resolveCompositeOptionType(String text) throws Exception {
      for (CompositeOptionType compositeOptionType : CompositeOptionType.values()) {
         if (compositeOptionType.sign.equals(text)) {
            return compositeOptionType;
         }
      }

      throw new Exception("There is no such operator '" + text + "'!");
   }

   public PostMotionObserver resolvePostMotionObserver(List list) throws Exception {
      PostMotionObserver postMotionObserver = this.resolvePostMotionObserverByList(list);
      if (postMotionObserver != null) {
         return postMotionObserver;
      } else {
         int size = list.size();
         if (size == 1) {
            Object entry = list.get(0);
            return this.resolvePostMotionObserver(entry);
         } else {
            if (size == 2) {
               Object entry2 = list.get(0);
               Object entry3 = list.get(1);
               if ((this.matchesTargetByTarget(entry2) || entry2.equals("-")) && entry3 instanceof List) {
                  String text = (String)entry2;
                  List list2 = (List)entry3;
                  String text2 = text;
                  return this.resolvePostMotionObserver(text2, list2);
               }
            }

            int count;
            int count2 = count = this.computeCountByList(list);

            while (count != -1) {
               int count3 = count - 1;
               int count4 = this.computeCountByList(list, count3);
               if (count4 != -1) {
                  String text3 = (String)list.get(count4);
                  CompositeOptionType compositeOptionType = this.resolveCompositeOptionType(text3);
                  String text4 = (String)list.get(count);
                  CompositeOptionType compositeOptionType2 = this.resolveCompositeOptionType(text4);
                  if (compositeOptionType2.value > compositeOptionType.value) {
                     List list3 = list.subList(0, count4);
                     PostMotionObserver postMotionObserver2 = this.resolvePostMotionObserver(list3);
                     List list4 = list.subList(count4 + 1, size);
                     PostMotionObserver postMotionObserver3 = this.resolvePostMotionObserver(list4);
                     return new RotationCounter(compositeOptionType, postMotionObserver2, postMotionObserver3);
                  }

                  if (compositeOptionType.value > compositeOptionType2.value) {
                     String text5 = (String)list.get(count2);
                     CompositeOptionType compositeOptionType3 = this.resolveCompositeOptionType(text5);
                     if (compositeOptionType3.value < compositeOptionType.value) {
                        List list5 = list.subList(0, count2);
                        PostMotionObserver postMotionObserver4 = this.resolvePostMotionObserver(list5);
                        List list6 = list.subList(count2 + 1, size);
                        PostMotionObserver postMotionObserver5 = this.resolvePostMotionObserver(list6);
                        return new RotationCounter(compositeOptionType3, postMotionObserver4, postMotionObserver5);
                     }

                     List list7 = list.subList(0, count);
                     PostMotionObserver postMotionObserver6 = this.resolvePostMotionObserver(list7);
                     List list8 = list.subList(count + 1, size);
                     PostMotionObserver postMotionObserver7 = this.resolvePostMotionObserver(list8);
                     return new RotationCounter(compositeOptionType2, postMotionObserver6, postMotionObserver7);
                  }
               }

               count = count4;
            }

            String text6 = (String)list.get(count2);
            CompositeOptionType compositeOptionType4 = this.resolveCompositeOptionType(text6);
            List list9 = list.subList(0, count2);
            PostMotionObserver postMotionObserver8 = this.resolvePostMotionObserver(list9);
            List list10 = list.subList(count2 + 1, size);
            return new RotationCounter(compositeOptionType4, postMotionObserver8, this.resolvePostMotionObserver(list10));
         }
      }
   }

   public boolean matchesTarget(Object entry) {
      if (entry instanceof String) {
         String text = (String)entry;
         if (this.matchesText(text)) {
            return true;
         }
      }

      return false;
   }

   public List resolveList(String[] textArray) {
        boolean flag = false;
        boolean flag2 = false;
      ArrayList list = new ArrayList();
      String text = "";
      int length = textArray.length;

      for (int index = 0; index < length; index++) {
         String text2;
         searchBlock: {
            text2 = textArray[index];
            if (index > 0) {
               String text3 = textArray[index - 1] + text2;
               if (this.matchesText(text3)) {
                  flag = true;
                  break searchBlock;
               }
            }

            flag = false;
         }

         boolean flag3 = flag;
         if (!this.matchesText(text2) && !flag3 && !text2.equals(",")) {
            if (text2.equals("(")) {
               if (!text.isEmpty()) {
                  list.add(text);
                  text = "";
               }

               int count3 = 1;

               for (int index2 = index + 1; index2 < length; index2++) {
                  String text4 = textArray[index2];
                  if (text4.equals("(")) {
                     count3++;
                  } else if (text4.equals(")")) {
                     count3--;
                  }

                  if (count3 == 0) {
                     String[] textArray2 = text.split("(?!^)");
                     list.add(this.resolveList(textArray2));
                     index = index2;
                     text = "";
                     break;
                  }

                  text = text + text4;
               }
            } else {
               text = text + text2;
            }
         } else {
            if (text2.equals("-")) {
               boolean flag4;
               guardBlock: {
                  int size = list.size();
                  flag4 = size == 0 && text.isEmpty();
                  if (size > 0) {
                     Object entry = list.get(size - 1);
                     Object entry2 = entry;
                     if ((this.matchesTarget(entry2) || list.get(size - 1).equals(",")) && text.isEmpty()) {
                        flag2 = true;
                        break guardBlock;
                     }
                  }

                  flag2 = false;
               }

               boolean flag5 = flag2;
               if (flag4 || flag5) {
                  text = text + text2;
                  continue;
               }
            }

            if (flag3) {
               text2 = textArray[index - 1] + text2;
               text = text.substring(0, text.length() - 1);
            }

            if (!text.isEmpty()) {
               list.add(text);
               text = "";
            }

            list.add(text2);
         }
      }

      if (!text.isEmpty()) {
         list.add(text);
      }

      return list;
   }

   public PostMotionObserver resolvePostMotionObserver(String text) throws Exception {
      String[] textArray = this.resolveTextArray(text);
      List list = this.resolveList(textArray);
      return this.resolvePostMotionObserver(list);
   }

   public InternalListenerRegistry get(String text) {
      return (InternalListenerRegistry)this.map2.get(text);
   }

   public String[] resolveTextArray(String text) throws Exception {
      if (!text.matches("^[\\w\\d\\s_+-/*%^&|<>=!?:.,()]+$")) {
         throw new Exception("Given expression '" + text + "' contains illegal characters!");
      } else {
         String text2 = text.replaceAll("\\s+", "");
         String[] textArray = text2.split("(?!^)");
         int count = 0;
         int count2 = 0;

         for (String text3 : textArray) {
            if (text3.equals("(")) {
               count++;
            } else if (text3.equals(")")) {
               count2++;
            }
         }

         if (count != count2) {
            throw new Exception("Given expression '" + text2 + "' has more uneven amount of parenthesis, there are " + count + " open and " + count2 + " closed!");
         } else {
            return textArray;
         }
      }
   }

   public void applyInternalListenerRegistry(InternalListenerRegistry internalListenerRegistry) {
      this.map2.put(internalListenerRegistry.getLabelText2(), internalListenerRegistry);
   }

   public boolean matches(String text) {
      return text.matches("^-?\\d+(\\.\\d+)?$");
   }

   public int computeCountByList(List list) {
      int count = list.size() - 1;
      return this.computeCountByList(list, count);
   }

   public int computeCountByList(List list, int count) {
      for (int index = count; index >= 0; index--) {
         Object entry = list.get(index);
         if (this.matchesTarget(entry)) {
            return index;
         }
      }

      return -1;
   }

   public PostMotionObserver resolvePostMotionObserver(String text, List list) throws Exception {
      if (text.equals("!")) {
         return new TargetBuilder(this.resolvePostMotionObserver(list));
      } else if (text.startsWith("!") && text.length() > 1) {
         String text2 = text.substring(1);
         return new TargetBuilder(this.resolvePostMotionObserver(text2, list));
      } else if (text.equals("-")) {
         return new RayTraceAdapter(this.resolvePostMotionObserver(list));
      } else if (text.startsWith("-") && text.length() > 1) {
         String text3 = text.substring(1);
         return new RayTraceAdapter(this.resolvePostMotionObserver(text3, list));
      } else if (!this.map.containsKey(text)) {
         throw new Exception("Function '" + text + "' couldn't be found!");
      } else {
         ArrayList list2 = new ArrayList();
         ArrayList list3 = new ArrayList();

         for (Object element : list) {
            if (element.equals(",")) {
               list2.add(this.resolvePostMotionObserver(list3));
               list3.clear();
            } else {
               list3.add(element);
            }
         }

         if (!list3.isEmpty()) {
            list2.add(this.resolvePostMotionObserver(list3));
         }

         return (LocalStringEntry)((Class)this.map.get(text))
            .getConstructor(PostMotionObserver[].class, String.class)
            .newInstance(list2.toArray(new PostMotionObserver[list2.size()]), text);
      }
   }

   public boolean matchesTargetByTarget(Object entry) {
      if (entry instanceof String) {
         String text = (String)entry;
         if (!this.matches(text)) {
            String text2 = (String)entry;
            if (!this.matchesText(text2)) {
               return true;
            }
         }
      }

      return false;
   }

   public int computeCount(List list) {
      return this.computeCount(list, 0);
   }

   public int computeCount(List list, int count) {
      int count2 = count;

      for (int index = list.size(); count2 < index; count2++) {
         Object entry = list.get(count2);
         if (this.matchesTarget(entry)) {
            return count2;
         }
      }

      return -1;
   }

   public PostMotionObserver resolvePostMotionObserverByList(List list) throws Exception {
      int count = -1;
      int count2 = 0;
      int count3 = -1;
      int count4 = 0;
      int size = list.size();

      for (int index = 0; index < size; index++) {
         Object entry = list.get(index);
         if (entry instanceof String) {
            if (entry.equals("?")) {
               if (count == -1) {
                  count = index;
               }

               count2++;
            } else if (entry.equals(":")) {
               if (count4 + 1 == count2 && count3 == -1) {
                  count3 = index;
               }

               count4++;
            }
         }
      }

      if (count2 == count4 && count > 0 && count + 1 < count3 && count3 < size - 1) {
         List list2 = list.subList(0, count);
         PostMotionObserver postMotionObserver = this.resolvePostMotionObserver(list2);
         List list3 = list.subList(count + 1, count3);
         PostMotionObserver postMotionObserver2 = this.resolvePostMotionObserver(list3);
         List list4 = list.subList(count3 + 1, size);
         return new TargetCoordinator(postMotionObserver, postMotionObserver2, this.resolvePostMotionObserver(list4));
      } else {
         return null;
      }
   }

   public ExtendedMapHolder() {
      InternalListenerRegistry internalListenerRegistry = new InternalListenerRegistry("PI", Math.PI);
      this.applyInternalListenerRegistry(internalListenerRegistry);
      InternalListenerRegistry internalListenerRegistry2 = new InternalListenerRegistry("E", Math.E);
      this.applyInternalListenerRegistry(internalListenerRegistry2);
      this.map.put("floor", DistanceWatcher.class);
      this.map.put("round", TargetAdapter.class);
      this.map.put("ceil", DistanceSorter.class);
      this.map.put("trunc", GlobalStringEntry.class);
      this.map.put("clamp", DistanceObserver.class);
      this.map.put("max", DistanceMonitor.class);
      this.map.put("min", DistanceRanker.class);
      this.map.put("abs", TargetPipeline.class);
      this.map.put("cos", DistanceAccumulator.class);
      this.map.put("sin", DistanceValidator.class);
      this.map.put("exp", DistanceChecker.class);
      this.map.put("ln", DistanceTracker.class);
      this.map.put("sqrt", CustomStringArrayEntry.class);
      this.map.put("mod", DistanceWriter.class);
      this.map.put("pow", DistanceFilter.class);
      this.map.put("lerp", DistanceCalculator.class);
      this.map.put("lerprotate", DistanceMatcher.class);
      this.map.put("random", SecondaryRandomEntry.class);
   }
}
