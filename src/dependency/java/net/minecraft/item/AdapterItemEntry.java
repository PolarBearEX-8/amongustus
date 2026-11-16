package net.minecraft.item;

import client.event.PostUpdateInterceptor;
import client.event.PreMotionBroadcaster;
import client.setting.ProviderOptionType;
import client.util.CompositeListEntry;
import client.util.CouldNotFindBoneHandler;
import client.util.CurveBuilder;
import client.util.FunctionEntry;
import client.util.RotationObserver;
import client.util.SharedDoubleEntry;
import client.util.SpecialFloatEntry;
import client.util.TickManager;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Map.Entry;
import java.util.function.Function;
import org.apache.commons.lang3.tuple.Pair;

public class AdapterItemEntry {
   public boolean enabled = false;
   public final CouldNotFindBoneHandler couldNotFindBoneHandler;
   public double value;
   public Set set;
   public List list = new ArrayList();

   private static boolean matchesText(String text, PostUpdateInterceptor postUpdateInterceptor) {
      return postUpdateInterceptor.getLabelText().equals(text);
   }

   public void applyPreMotionBroadcaster(PreMotionBroadcaster preMotionBroadcaster, double offset) {
      this.couldNotFindBoneHandler.applyPreMotionBroadcaster(preMotionBroadcaster, offset);
   }

   public void applyPostUpdateInterceptor(PostUpdateInterceptor postUpdateInterceptor) {
      postUpdateInterceptor.updateState();
      this.list.add(postUpdateInterceptor);
   }

   private void applyHashMap(HashMap map) {
      for (PostUpdateInterceptor postUpdateInterceptor : (Iterable<PostUpdateInterceptor>)(Iterable)this.list) {
         if (!map.containsKey(postUpdateInterceptor.getLabelText())) {
            map.put(postUpdateInterceptor.getLabelText(), Pair.of(postUpdateInterceptor, new SpecialFloatEntry(postUpdateInterceptor.getSpecialFloatEntryAsSpecialFloatEntr())));
         }
      }
   }

   public PostUpdateInterceptor resolvePostUpdateInterceptor(String text) {
      return (PostUpdateInterceptor)this.list.stream().filter(item -> AdapterItemEntry.matchesText(text, (PostUpdateInterceptor)item)).findFirst().orElse(null);
   }

   private HashMap getHashMap() {
      HashMap map = new HashMap();

      for (PostUpdateInterceptor postUpdateInterceptor : (Iterable<PostUpdateInterceptor>)(Iterable)this.list) {
         map.put(postUpdateInterceptor.getLabelText(), new RotationObserver(false, false, false, postUpdateInterceptor));
      }

      return map;
   }

   public List getList() {
      return this.list;
   }

   public void clear() {
      this.list.clear();
   }

   public void applyPreMotionBroadcaster(PreMotionBroadcaster preMotionBroadcaster, Integer count, double offset, CompositeListEntry compositeListEntry, DefaultItemEntry defaultItemEntry, boolean flag) {
      if (offset != this.value) {
         this.set.clear();
      } else if (this.set.contains(count)) {
         return;
      }

      this.value = offset;
      this.set.add(count);
      SharedDoubleEntry sharedDoubleEntry = preMotionBroadcaster.getCommonHashMapEntry().get(count);
      HashMap hashMap = this.getHashMap();
      HashMap hashMap2 = sharedDoubleEntry.getHashMap();
      HashMap map = hashMap2;
      this.applyHashMap(map);
      HashMap hashMap3 = sharedDoubleEntry.getHashMap();

      for (FunctionEntry functionEntry : (Iterable<FunctionEntry>)(Iterable)sharedDoubleEntry.getHashMap2().values()) {
         if (this.enabled) {
            functionEntry.updateState2();
            functionEntry.getHashMap().clear();
         }

         functionEntry.enabled3 = sharedDoubleEntry.enabled2;
         compositeListEntry.setFunctionEntry(functionEntry);
         List list2 = this.list;
         functionEntry.applyCompositeListEntry(offset, compositeListEntry, list2, hashMap3, defaultItemEntry, flag);

         for (CurveBuilder curveBuilder : (Iterable<CurveBuilder>)(Iterable)functionEntry.getHashMap().values()) {
            PostUpdateInterceptor postUpdateInterceptor = curveBuilder.postUpdateInterceptor;
            SpecialFloatEntry right = (SpecialFloatEntry)((Pair)hashMap3.get(postUpdateInterceptor.getLabelText())).getRight();
            SpecialFloatEntry specialFloatEntryAsSpecialFloatEntr = postUpdateInterceptor.getSpecialFloatEntryAsSpecialFloatEntr();
            TickManager tickManager = (TickManager)curveBuilder.vectorCounter6.poll();
            TickManager tickManager2 = (TickManager)curveBuilder.vectorCounter3.poll();
            TickManager tickManager3 = (TickManager)curveBuilder.vectorCounter2.poll();
            TickManager tickManager4 = (TickManager)curveBuilder.vectorCounter5.poll();
            TickManager tickManager5 = (TickManager)curveBuilder.vectorCounter8.poll();
            TickManager tickManager6 = (TickManager)curveBuilder.vectorCounter.poll();
            TickManager tickManager7 = (TickManager)curveBuilder.vectorCounter4.poll();
            TickManager tickManager8 = (TickManager)curveBuilder.vectorCounter9.poll();
            TickManager tickManager9 = (TickManager)curveBuilder.vectorCounter7.poll();
            RotationObserver rotationObserver = (RotationObserver)hashMap.get(postUpdateInterceptor.getLabelText());
            if (rotationObserver != null) {
               if (tickManager != null && tickManager2 != null && tickManager3 != null) {
                  Function function = functionEntry.function;
                  ProviderOptionType providerOptionType = functionEntry.providerOptionType;
                  float factor = TickManagerHelper.computeFactor(tickManager, providerOptionType, function) + specialFloatEntryAsSpecialFloatEntr.factor9;
                  postUpdateInterceptor.setFactor8(factor);
                  Function function2 = functionEntry.function;
                  ProviderOptionType providerOptionType2 = functionEntry.providerOptionType;
                  float factor2 = TickManagerHelper.computeFactor(tickManager2, providerOptionType2, function2) + specialFloatEntryAsSpecialFloatEntr.factor6;
                  postUpdateInterceptor.setFactor6(factor2);
                  Function function3 = functionEntry.function;
                  ProviderOptionType providerOptionType3 = functionEntry.providerOptionType;
                  float factor3 = TickManagerHelper.computeFactor(tickManager3, providerOptionType3, function3) + specialFloatEntryAsSpecialFloatEntr.factor12;
                  postUpdateInterceptor.setFactor3(factor3);
                  right.factor9 = postUpdateInterceptor.getFactor8();
                  right.factor6 = postUpdateInterceptor.getFactor6();
                  right.factor12 = postUpdateInterceptor.getFactor3();
                  right.enabled3 = true;
                  rotationObserver.enabled = true;
               }

               if (tickManager4 != null && tickManager5 != null && tickManager6 != null) {
                  Function function4 = functionEntry.function;
                  ProviderOptionType providerOptionType4 = functionEntry.providerOptionType;
                  float factor4 = TickManagerHelper.computeFactor(tickManager4, providerOptionType4, function4);
                  postUpdateInterceptor.setFactor4(factor4);
                  Function function5 = functionEntry.function;
                  ProviderOptionType providerOptionType5 = functionEntry.providerOptionType;
                  float factor5 = TickManagerHelper.computeFactor(tickManager5, providerOptionType5, function5);
                  postUpdateInterceptor.setFactor10(factor5);
                  Function function6 = functionEntry.function;
                  ProviderOptionType providerOptionType6 = functionEntry.providerOptionType;
                  float factor6 = TickManagerHelper.computeFactor(tickManager6, providerOptionType6, function6);
                  postUpdateInterceptor.setFactor11(factor6);
                  right.factor11 = postUpdateInterceptor.getFactor4();
                  right.factor4 = postUpdateInterceptor.getFactor10();
                  right.factor7 = postUpdateInterceptor.getFactor11();
                  right.enabled2 = true;
                  rotationObserver.enabled2 = true;
               }

               if (tickManager7 != null && tickManager8 != null && tickManager9 != null) {
                  Function function7 = functionEntry.function;
                  ProviderOptionType providerOptionType7 = functionEntry.providerOptionType;
                  float factor7 = TickManagerHelper.computeFactor(tickManager7, providerOptionType7, function7);
                  postUpdateInterceptor.setFactor9(factor7);
                  Function function8 = functionEntry.function;
                  ProviderOptionType providerOptionType8 = functionEntry.providerOptionType;
                  float factor8 = TickManagerHelper.computeFactor(tickManager8, providerOptionType8, function8);
                  postUpdateInterceptor.setFactor(factor8);
                  Function function9 = functionEntry.function;
                  ProviderOptionType providerOptionType9 = functionEntry.providerOptionType;
                  float factor9 = TickManagerHelper.computeFactor(tickManager9, providerOptionType9, function9);
                  postUpdateInterceptor.setFactor2(factor9);
                  right.factor3 = postUpdateInterceptor.getFactor9();
                  right.factor2 = postUpdateInterceptor.getFactor();
                  right.factor5 = postUpdateInterceptor.getFactor2();
                  right.enabled = true;
                  rotationObserver.enabled3 = true;
               }
            }
         }
      }

      this.enabled = false;
      double value3 = sharedDoubleEntry.getValue();

      for (Entry entry : (Iterable<java.util.Map.Entry>)(Iterable)hashMap.entrySet()) {
         PostUpdateInterceptor postUpdateInterceptor2 = ((RotationObserver)entry.getValue()).postUpdateInterceptor;
         SpecialFloatEntry specialFloatEntryAsSpecialFloatEntr2 = postUpdateInterceptor2.getSpecialFloatEntryAsSpecialFloatEntr();
         SpecialFloatEntry right2 = (SpecialFloatEntry)((Pair)hashMap3.get(entry.getKey())).getRight();
         if (right2 == null) {
            if (flag) {
               throw new RuntimeException(
                  "Could not find save snapshot for bone: "
                     + ((RotationObserver)entry.getValue()).postUpdateInterceptor.getLabelText()
                     + ". Please don't add bones that are used in an animation at runtime."
               );
            }
         } else {
            if (!((RotationObserver)entry.getValue()).enabled) {
               if (right2.enabled3) {
                  right2.factor10 = (float)offset;
                  right2.enabled3 = false;
               }

               double offset2 = Math.min((offset - (double)right2.factor10) / value3, 1.0);
               float factor10 = TickManagerHelper.computeFactor(offset2, (double)right2.factor9, (double)specialFloatEntryAsSpecialFloatEntr2.factor9);
               postUpdateInterceptor2.setFactor8(factor10);
               float factor11 = TickManagerHelper.computeFactor(offset2, (double)right2.factor6, (double)specialFloatEntryAsSpecialFloatEntr2.factor6);
               postUpdateInterceptor2.setFactor6(factor11);
               float factor12 = TickManagerHelper.computeFactor(offset2, (double)right2.factor12, (double)specialFloatEntryAsSpecialFloatEntr2.factor12);
               postUpdateInterceptor2.setFactor3(factor12);
               if (offset2 >= 1.0) {
                  right2.factor9 = postUpdateInterceptor2.getFactor8();
                  right2.factor6 = postUpdateInterceptor2.getFactor6();
                  right2.factor12 = postUpdateInterceptor2.getFactor3();
               }
            }

            if (!((RotationObserver)entry.getValue()).enabled2) {
               if (right2.enabled2) {
                  right2.factor = (float)offset;
                  right2.enabled2 = false;
               }

               double offset3 = Math.min((offset - (double)right2.factor) / value3, 1.0);
               float factor13 = TickManagerHelper.computeFactor(offset3, (double)right2.factor11, (double)specialFloatEntryAsSpecialFloatEntr2.factor11);
               postUpdateInterceptor2.setFactor4(factor13);
               float factor14 = TickManagerHelper.computeFactor(offset3, (double)right2.factor4, (double)specialFloatEntryAsSpecialFloatEntr2.factor4);
               postUpdateInterceptor2.setFactor10(factor14);
               float factor15 = TickManagerHelper.computeFactor(offset3, (double)right2.factor7, (double)specialFloatEntryAsSpecialFloatEntr2.factor7);
               postUpdateInterceptor2.setFactor11(factor15);
               if (offset3 >= 1.0) {
                  right2.factor11 = postUpdateInterceptor2.getFactor4();
                  right2.factor4 = postUpdateInterceptor2.getFactor10();
                  right2.factor7 = postUpdateInterceptor2.getFactor11();
               }
            }

            if (!((RotationObserver)entry.getValue()).enabled3) {
               if (right2.enabled) {
                  right2.factor8 = (float)offset;
                  right2.enabled = false;
               }

               double offset4 = Math.min((offset - (double)right2.factor8) / value3, 1.0);
               float factor16 = TickManagerHelper.computeFactor(offset4, (double)right2.factor3, (double)specialFloatEntryAsSpecialFloatEntr2.factor3);
               postUpdateInterceptor2.setFactor9(factor16);
               float factor17 = TickManagerHelper.computeFactor(offset4, (double)right2.factor2, (double)specialFloatEntryAsSpecialFloatEntr2.factor2);
               postUpdateInterceptor2.setFactor(factor17);
               float factor18 = TickManagerHelper.computeFactor(offset4, (double)right2.factor5, (double)specialFloatEntryAsSpecialFloatEntr2.factor5);
               postUpdateInterceptor2.setFactor2(factor18);
               if (offset4 >= 1.0) {
                  right2.factor3 = postUpdateInterceptor2.getFactor9();
                  right2.factor2 = postUpdateInterceptor2.getFactor();
                  right2.factor5 = postUpdateInterceptor2.getFactor2();
               }
            }
         }
      }

      sharedDoubleEntry.enabled2 = false;
   }

   public AdapterItemEntry(CouldNotFindBoneHandler couldNotFindBoneHandler2) {
      this.value = -1.0;
      this.set = new HashSet();
      this.couldNotFindBoneHandler = couldNotFindBoneHandler2;
   }
}
