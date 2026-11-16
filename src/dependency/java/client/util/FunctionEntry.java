package client.util;

import client.event.FactoryListener;
import client.event.PostMotionObserver;
import client.event.PostRenderSubscriber;
import client.event.PostUpdateConsumer;
import client.event.PostUpdateInterceptor;
import client.event.PreMotionBroadcaster;
import client.event.PreRenderFilter;
import client.setting.GenericOptionType;
import client.setting.MovementOptionProperty;
import client.setting.ProviderOptionType;
import client.setting.RenderValueProperty;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Function;
import java.util.stream.Collectors;
import net.minecraft.item.DefaultItemEntry;
import net.minecraft.item.DiamondTool;

public class FunctionEntry {
   public Function function;
   public boolean enabled;
   public PostUpdateConsumer postUpdateConsumer;
   public RenderValueProperty renderValueProperty = RenderValueProperty.Stopped;
   public double value3;
   public ProviderOptionType providerOptionType;
   public boolean enabled3 = false;
   public double value2;
   public SupplementalStringEntry supplementalStringEntry;
   public boolean enabled2;
   public final Set set;
   public final HashMap hashMap2;
   public final String labelText;
   public Queue queue;
   public PreMotionBroadcaster preMotionBroadcaster;
   public static final boolean enabled5 = !FunctionEntry.class.desiredAssertionStatus();
   public boolean enabled4;
   public static List list = new ArrayList();
   public PostRenderSubscriber postRenderSubscriber;
   public FactoryListener factoryListener;
   public final HashMap hashMap = new HashMap();
   public double value;
   public LocalListHolder localListHolder;
   public PreRenderFilter preRenderFilter;
   public boolean enabled6;

   private void applyDefaultItemEntry(double offset, double offset2, DefaultItemEntry defaultItemEntry, boolean flag) {
      double offset3 = offset;
      if (!enabled5 && this.supplementalStringEntry == null) {
         throw new AssertionError();
      } else {
         if (offset >= this.supplementalStringEntry.memberRef) {
            this.clear();
            if (!this.supplementalStringEntry.enabled) {
               if ((SupplementalStringEntry)this.queue.peek() == null) {
                  this.renderValueProperty = RenderValueProperty.Stopped;
                  return;
               }

               this.renderValueProperty = RenderValueProperty.Transitioning;
               this.enabled = true;
               this.supplementalStringEntry = (SupplementalStringEntry)this.queue.peek();
            } else {
               this.enabled = true;
               offset3 = this.computeValue(offset2);
            }
         }

         this.applyDefaultItemEntry(defaultItemEntry, offset3);

         for (DiamondTool diamondTool : (Iterable<DiamondTool>) (Iterable) (this.supplementalStringEntry.list3)) {
            CurveBuilder curveBuilder = (CurveBuilder)this.hashMap.get(diamondTool.labelText);
            if (curveBuilder == null) {
               if (flag) {
                  throw new RuntimeException("Could not find bone: " + diamondTool.labelText);
               }
            } else {
               SystemListEntry systemListEntry = diamondTool.systemListEntry;
               SystemListEntry systemListEntry2 = diamondTool.systemListEntry2;
               SystemListEntry systemListEntry3 = diamondTool.systemListEntry3;
               if (!systemListEntry.list.isEmpty()) {
                  GenericOptionType genericOptionType = GenericOptionType.X;
                  boolean flag2 = true;
                  List list2 = systemListEntry.list;
                  curveBuilder.vectorCounter6.add(this.resolveTickManager(list2, offset3, flag2, genericOptionType));
                  GenericOptionType genericOptionType2 = GenericOptionType.Y;
                  boolean flag3 = true;
                  List list3 = systemListEntry.list3;
                  curveBuilder.vectorCounter3.add(this.resolveTickManager(list3, offset3, flag3, genericOptionType2));
                  GenericOptionType genericOptionType3 = GenericOptionType.Z;
                  boolean flag4 = true;
                  List list4 = systemListEntry.list2;
                  curveBuilder.vectorCounter2.add(this.resolveTickManager(list4, offset3, flag4, genericOptionType3));
               }

               if (!systemListEntry2.list.isEmpty()) {
                  GenericOptionType genericOptionType4 = GenericOptionType.X;
                  boolean flag5 = false;
                  List list5 = systemListEntry2.list;
                  curveBuilder.vectorCounter5.add(this.resolveTickManager(list5, offset3, flag5, genericOptionType4));
                  GenericOptionType genericOptionType5 = GenericOptionType.Y;
                  boolean flag6 = false;
                  List list6 = systemListEntry2.list3;
                  curveBuilder.vectorCounter8.add(this.resolveTickManager(list6, offset3, flag6, genericOptionType5));
                  GenericOptionType genericOptionType6 = GenericOptionType.Z;
                  boolean flag7 = false;
                  List list7 = systemListEntry2.list2;
                  curveBuilder.vectorCounter.add(this.resolveTickManager(list7, offset3, flag7, genericOptionType6));
               }

               if (!systemListEntry3.list.isEmpty()) {
                  GenericOptionType genericOptionType7 = GenericOptionType.X;
                  boolean flag8 = false;
                  List list8 = systemListEntry3.list;
                  curveBuilder.vectorCounter4.add(this.resolveTickManager(list8, offset3, flag8, genericOptionType7));
                  GenericOptionType genericOptionType8 = GenericOptionType.Y;
                  boolean flag9 = false;
                  List list9 = systemListEntry3.list3;
                  curveBuilder.vectorCounter9.add(this.resolveTickManager(list9, offset3, flag9, genericOptionType8));
                  GenericOptionType genericOptionType9 = GenericOptionType.Z;
                  boolean flag10 = false;
                  List list10 = systemListEntry3.list2;
                  curveBuilder.vectorCounter7.add(this.resolveTickManager(list10, offset3, flag10, genericOptionType9));
               }
            }
         }

         if (this.preRenderFilter != null || this.factoryListener != null || this.postRenderSubscriber != null) {
            for (VectorCollector vectorCollector : (Iterable<VectorCollector>) (Iterable) (this.supplementalStringEntry.list2)) {
               if (!this.set.contains(vectorCollector) && offset3 >= vectorCollector.getMemberRef()) {
                  HitBoxEstimator hitBoxEstimator = new HitBoxEstimator(this.preMotionBroadcaster, offset3, (String)vectorCollector.getDataRef(), this);
                  this.preRenderFilter.applyHitBoxEstimator(hitBoxEstimator);
                  this.set.add(vectorCollector);
               }
            }

            for (VectorProfiler vectorProfiler : (Iterable<VectorProfiler>) (Iterable) (this.supplementalStringEntry.list4)) {
               if (!this.set.contains(vectorProfiler) && offset3 >= vectorProfiler.getMemberRef()) {
                  HitBoxProvider hitBoxProvider = new HitBoxProvider(this.preMotionBroadcaster, offset3, vectorProfiler.labelText3, vectorProfiler.labelText, vectorProfiler.labelText2, this);
                  this.factoryListener.applyHitBoxProvider(hitBoxProvider);
                  this.set.add(vectorProfiler);
               }
            }

            for (VectorCollector vectorCollector2 : (Iterable<VectorCollector>) (Iterable) (this.supplementalStringEntry.list)) {
               if (!this.set.contains(vectorCollector2) && offset3 >= vectorCollector2.getMemberRef()) {
                  HitBoxAggregator hitBoxAggregator = new HitBoxAggregator(this.preMotionBroadcaster, offset3, (String)vectorCollector2.getDataRef(), this);
                  this.postRenderSubscriber.applyHitBoxAggregator(hitBoxAggregator);
                  this.set.add(vectorCollector2);
               }
            }
         }

         if (this.value3 == 0.0 && this.enabled && this.renderValueProperty == RenderValueProperty.Transitioning) {
            this.supplementalStringEntry = (SupplementalStringEntry)this.queue.poll();
         }
      }
   }

   public void updateState() {
      this.localListHolder = new LocalListHolder();
   }

   public static void applyFunctionExtension(FunctionExtension functionExtension) {
      Objects.requireNonNull(functionExtension);
      list.remove(functionExtension);
   }

   private void clear() {
      this.set.clear();
   }

   public HashMap getHashMap() {
      return this.hashMap;
   }

   private CouldNotFindBoneHandler resolveCouldNotFindBoneHandler(PreMotionBroadcaster preMotionBroadcaster2) {
      Iterator iterator = list.iterator();

      while (iterator.hasNext()) {
         CouldNotFindBoneHandler couldNotFindBoneHandler = (CouldNotFindBoneHandler)((FunctionExtension)iterator.next()).apply(preMotionBroadcaster2);
         if (couldNotFindBoneHandler != null) {
            return couldNotFindBoneHandler;
         }
      }

      System.out.printf("Could not find suitable model for animatable of type %s. Did you register a Model Fetcher?%n", preMotionBroadcaster2.getClass());
      return null;
   }

   public void setPreRenderFilter(PreRenderFilter preRenderFilter2) {
      this.preRenderFilter = preRenderFilter2;
   }

   public String getLabelText() {
      return this.labelText;
   }

   private SupplementalDoubleEntry resolveSupplementalDoubleEntry(List list2, double offset) {
      double offset2 = 0.0;

      for (AdvancedListEntry advancedListEntry : (Iterable<AdvancedListEntry>) (Iterable) (list2)) {
         offset2 += advancedListEntry.getMemberRef();
         if (offset2 > offset) {
            double offset3 = offset - (offset2 - advancedListEntry.getMemberRef());
            return new SupplementalDoubleEntry(advancedListEntry, offset3);
         }
      }

      return new SupplementalDoubleEntry((AdvancedListEntry)list2.get(list2.size() - 1), offset);
   }

   private void applySupplementalStringEntry(SupplementalStringEntry supplementalStringEntry2, HashMap map) {
      for (org.apache.commons.lang3.tuple.Pair pair : (Iterable<org.apache.commons.lang3.tuple.Pair>) (Iterable) (map.values())) {
         if (supplementalStringEntry2 != null && supplementalStringEntry2.list3 != null && supplementalStringEntry2.list3.stream().anyMatch(tool -> matchesPair(pair, (DiamondTool)tool))) {
            this.hashMap2.put(((PostUpdateInterceptor)pair.getLeft()).getLabelText(), new SpecialFloatEntry((SpecialFloatEntry)pair.getRight()));
         }
      }
   }

   public double getValue2() {
      return this.value2;
   }

   public static void add(FunctionExtension functionExtension) {
      list.add(functionExtension);
   }

   private static boolean matchesDiamondTool(DiamondTool diamondTool, PostUpdateInterceptor postUpdateInterceptor) {
      return postUpdateInterceptor.getLabelText().equals(diamondTool.labelText);
   }

   public void applyCompositeListEntry(double offset, CompositeListEntry compositeListEntry, List list2, HashMap map, DefaultItemEntry defaultItemEntry, boolean flag) {
      double offset2 = offset / 20.0;
      String text = "query.life_time";
      defaultItemEntry.applyText(text, offset2);
      if (this.supplementalStringEntry != null) {
         PreMotionBroadcaster preMotionBroadcaster2 = this.preMotionBroadcaster;
         CouldNotFindBoneHandler couldNotFindBoneHandler = this.resolveCouldNotFindBoneHandler(preMotionBroadcaster2);
         if (couldNotFindBoneHandler != null) {
            PreMotionBroadcaster preMotionBroadcaster3 = this.preMotionBroadcaster;
            String labelText2 = this.supplementalStringEntry.labelText;
            SupplementalStringEntry supplementalStringEntry2 = couldNotFindBoneHandler.resolveSupplementalStringEntry(labelText2, preMotionBroadcaster3);
            if (supplementalStringEntry2 != null) {
               boolean enabled7 = this.supplementalStringEntry.enabled;
               this.supplementalStringEntry = supplementalStringEntry2;
               this.supplementalStringEntry.enabled = enabled7;
            }
         }
      }

      this.applyList(list2);
      double offset3 = this.computeValue(offset);
      if (this.renderValueProperty == RenderValueProperty.Transitioning && offset3 >= this.value3) {
         this.enabled = true;
         this.renderValueProperty = RenderValueProperty.Running;
         offset3 = this.computeValue(offset);
      }

      if (!enabled5 && !(offset3 >= 0.0)) {
         throw new AssertionError("GeckoLib: Tick was less than zero");
      } else {
         if (this.resolveMovementOptionProperty(compositeListEntry) != MovementOptionProperty.STOP && (this.supplementalStringEntry != null || this.queue.size() != 0)) {
            if (!this.enabled6 || !this.enabled && !this.enabled2) {
               if (this.supplementalStringEntry == null && this.queue.size() != 0) {
                  this.enabled = true;
                  this.renderValueProperty = RenderValueProperty.Transitioning;
                  this.enabled6 = true;
                  this.enabled4 = false;
                  offset3 = this.computeValue(offset);
               } else if (this.renderValueProperty != RenderValueProperty.Transitioning) {
                  this.renderValueProperty = RenderValueProperty.Running;
               }
            } else {
               this.enabled2 = false;
               offset3 = this.computeValue(offset);
            }

            if (this.renderValueProperty == RenderValueProperty.Transitioning) {
               if (offset3 == 0.0 || this.enabled3) {
                  this.enabled6 = false;
                  this.supplementalStringEntry = (SupplementalStringEntry)this.queue.poll();
                  this.clear();
                  SupplementalStringEntry supplementalStringEntry3 = this.supplementalStringEntry;
                  this.applySupplementalStringEntry(supplementalStringEntry3, map);
               }

               if (this.supplementalStringEntry != null) {
                  double offset4 = 0.0;
                  this.applyDefaultItemEntry(defaultItemEntry, offset4);

                  for (DiamondTool diamondTool : (Iterable<DiamondTool>) (Iterable) (this.supplementalStringEntry.list3)) {
                     CurveBuilder curveBuilder = (CurveBuilder)this.hashMap.get(diamondTool.labelText);
                     SpecialFloatEntry specialFloatEntry = (SpecialFloatEntry)this.hashMap2.get(diamondTool.labelText);
                     Optional optional = list2.stream().filter(interceptor -> matchesDiamondTool(diamondTool, (PostUpdateInterceptor)interceptor)).findFirst();
                     if (!optional.isPresent()) {
                        if (flag) {
                           throw new RuntimeException("Could not find bone: " + diamondTool.labelText);
                        }
                     } else {
                        SpecialFloatEntry specialFloatEntryAsSpecialFloatEntr = ((PostUpdateInterceptor)optional.get()).getSpecialFloatEntryAsSpecialFloatEntr();
                        if (!enabled5 && specialFloatEntry == null) {
                           throw new AssertionError("Bone snapshot was null");
                        }

                        SystemListEntry systemListEntry = diamondTool.systemListEntry;
                        SystemListEntry systemListEntry2 = diamondTool.systemListEntry2;
                        SystemListEntry systemListEntry3 = diamondTool.systemListEntry3;
                        if (!systemListEntry.list.isEmpty()) {
                           GenericOptionType genericOptionType = GenericOptionType.X;
                           boolean flag2 = true;
                           double offset5 = 0.0;
                           List list3 = systemListEntry.list;
                           TickManager tickManager = this.resolveTickManager(list3, offset5, flag2, genericOptionType);
                           GenericOptionType genericOptionType2 = GenericOptionType.Y;
                           boolean flag3 = true;
                           double offset6 = 0.0;
                           List list4 = systemListEntry.list3;
                           TickManager tickManager2 = this.resolveTickManager(list4, offset6, flag3, genericOptionType2);
                           GenericOptionType genericOptionType3 = GenericOptionType.Z;
                           boolean flag4 = true;
                           double offset7 = 0.0;
                           List list5 = systemListEntry.list2;
                           TickManager tickManager3 = this.resolveTickManager(list5, offset7, flag4, genericOptionType3);
                           curveBuilder.vectorCounter6
                              .add(
                                 new TickManager((AdvancedListEntry)null, offset3, this.value3, specialFloatEntry.factor9 - specialFloatEntryAsSpecialFloatEntr.factor9, tickManager.memberRef4.doubleValue())
                              );
                           curveBuilder.vectorCounter3
                              .add(
                                 new TickManager((AdvancedListEntry)null, offset3, this.value3, specialFloatEntry.factor6 - specialFloatEntryAsSpecialFloatEntr.factor6, tickManager2.memberRef4.doubleValue())
                              );
                           curveBuilder.vectorCounter2
                              .add(
                                 new TickManager((AdvancedListEntry)null, offset3, this.value3, specialFloatEntry.factor12 - specialFloatEntryAsSpecialFloatEntr.factor12, tickManager3.memberRef4.doubleValue())
                              );
                        }

                        if (!systemListEntry2.list.isEmpty()) {
                           GenericOptionType genericOptionType4 = GenericOptionType.X;
                           boolean flag5 = false;
                           double offset8 = 0.0;
                           List list6 = systemListEntry2.list;
                           TickManager tickManager4 = this.resolveTickManager(list6, offset8, flag5, genericOptionType4);
                           GenericOptionType genericOptionType5 = GenericOptionType.Y;
                           boolean flag6 = false;
                           double offset9 = 0.0;
                           List list7 = systemListEntry2.list3;
                           TickManager tickManager5 = this.resolveTickManager(list7, offset9, flag6, genericOptionType5);
                           GenericOptionType genericOptionType6 = GenericOptionType.Z;
                           boolean flag7 = false;
                           double offset10 = 0.0;
                           List list8 = systemListEntry2.list2;
                           TickManager tickManager6 = this.resolveTickManager(list8, offset10, flag7, genericOptionType6);
                           curveBuilder.vectorCounter5
                              .add(new TickManager((AdvancedListEntry)null, offset3, this.value3, specialFloatEntry.factor11, tickManager4.memberRef4.doubleValue()));
                           curveBuilder.vectorCounter8
                              .add(new TickManager((AdvancedListEntry)null, offset3, this.value3, specialFloatEntry.factor4, tickManager5.memberRef4.doubleValue()));
                           curveBuilder.vectorCounter
                              .add(new TickManager((AdvancedListEntry)null, offset3, this.value3, specialFloatEntry.factor7, tickManager6.memberRef4.doubleValue()));
                        }

                        if (!systemListEntry3.list.isEmpty()) {
                           GenericOptionType genericOptionType7 = GenericOptionType.X;
                           boolean flag8 = false;
                           double offset11 = 0.0;
                           List list9 = systemListEntry3.list;
                           TickManager tickManager7 = this.resolveTickManager(list9, offset11, flag8, genericOptionType7);
                           GenericOptionType genericOptionType8 = GenericOptionType.Y;
                           boolean flag9 = false;
                           double offset12 = 0.0;
                           List list10 = systemListEntry3.list3;
                           TickManager tickManager8 = this.resolveTickManager(list10, offset12, flag9, genericOptionType8);
                           GenericOptionType genericOptionType9 = GenericOptionType.Z;
                           boolean flag10 = false;
                           double offset13 = 0.0;
                           List list11 = systemListEntry3.list2;
                           TickManager tickManager9 = this.resolveTickManager(list11, offset13, flag10, genericOptionType9);
                           curveBuilder.vectorCounter4
                              .add(new TickManager((AdvancedListEntry)null, offset3, this.value3, specialFloatEntry.factor3, tickManager7.memberRef4.doubleValue()));
                           curveBuilder.vectorCounter9
                              .add(new TickManager((AdvancedListEntry)null, offset3, this.value3, specialFloatEntry.factor2, tickManager8.memberRef4.doubleValue()));
                           curveBuilder.vectorCounter7
                              .add(new TickManager((AdvancedListEntry)null, offset3, this.value3, specialFloatEntry.factor5, tickManager9.memberRef4.doubleValue()));
                        }
                     }
                  }
               }
            } else if (this.getRenderValueProperty() == RenderValueProperty.Running) {
               this.applyDefaultItemEntry(offset3, offset, defaultItemEntry, flag);
            }
         } else {
            this.renderValueProperty = RenderValueProperty.Stopped;
            this.enabled2 = true;
         }
      }
   }

   public void updateState2() {
      this.enabled4 = true;
   }

   public double computeValue(double offset) {
      if (this.enabled) {
         if (this.getRenderValueProperty() == RenderValueProperty.Transitioning) {
            this.value = offset;
         } else if (this.getRenderValueProperty() == RenderValueProperty.Running) {
            this.value = offset;
         }

         this.enabled = false;
         return 0.0;
      } else {
         return this.value2 * Math.max(offset - this.value, 0.0);
      }
   }

   public SupplementalStringEntry getSupplementalStringEntry() {
      return this.supplementalStringEntry;
   }

   public void applyLocalListHolder(LocalListHolder localListHolder2) {
      PreMotionBroadcaster preMotionBroadcaster2 = this.preMotionBroadcaster;
      CouldNotFindBoneHandler couldNotFindBoneHandler = this.resolveCouldNotFindBoneHandler(preMotionBroadcaster2);
      if (couldNotFindBoneHandler != null) {
         if (localListHolder2 != null && localListHolder2.getList().size() != 0) {
            if (!localListHolder2.getList().equals(this.localListHolder.getList()) || this.enabled4) {
               AtomicBoolean atomicBoolean = new AtomicBoolean(false);
               LinkedList list2 = (LinkedList)localListHolder2.getList().stream().map(entry -> this.resolveSupplementalStringEntry(couldNotFindBoneHandler, atomicBoolean, (PrimaryStringEntry)entry)).collect(Collectors.toCollection(LinkedList::new));
               if (atomicBoolean.get()) {
                  return;
               }

               this.queue = list2;
               this.localListHolder = localListHolder2;
               this.enabled = true;
               this.renderValueProperty = RenderValueProperty.Transitioning;
               this.enabled6 = true;
               this.enabled4 = false;
            }
         } else {
            this.renderValueProperty = RenderValueProperty.Stopped;
         }
      }
   }

   private void applyList(List list2) {
      this.hashMap.clear();

      for (PostUpdateInterceptor postUpdateInterceptor : (Iterable<PostUpdateInterceptor>) (Iterable) (list2)) {
         this.hashMap.put(postUpdateInterceptor.getLabelText(), new CurveBuilder(postUpdateInterceptor));
      }
   }

   public void setPostRenderSubscriber(PostRenderSubscriber postRenderSubscriber2) {
      this.postRenderSubscriber = postRenderSubscriber2;
   }

   private static boolean matchesPair(org.apache.commons.lang3.tuple.Pair pair, DiamondTool diamondTool) {
      return diamondTool.labelText.equals(((PostUpdateInterceptor)pair.getLeft()).getLabelText());
   }

   private void applyDefaultItemEntry(DefaultItemEntry defaultItemEntry, double offset) {
      double offset2 = offset / 20.0;
      String text = "query.anim_time";
      defaultItemEntry.applyText(text, offset2);
   }

   private SupplementalStringEntry resolveSupplementalStringEntry(CouldNotFindBoneHandler couldNotFindBoneHandler, AtomicBoolean atomicBoolean, PrimaryStringEntry primaryStringEntry) {
      PreMotionBroadcaster preMotionBroadcaster2 = this.preMotionBroadcaster;
      String labelText2 = primaryStringEntry.labelText;
      SupplementalStringEntry supplementalStringEntry2 = couldNotFindBoneHandler.resolveSupplementalStringEntry(labelText2, preMotionBroadcaster2);
      if (supplementalStringEntry2 == null) {
         System.out.printf("Could not load animation: %s. Is it missing?", primaryStringEntry.labelText);
         atomicBoolean.set(true);
      }

      if (supplementalStringEntry2 != null && primaryStringEntry.memberRef != null) {
         supplementalStringEntry2.enabled = primaryStringEntry.memberRef;
      }

      return supplementalStringEntry2;
   }

   public MovementOptionProperty resolveMovementOptionProperty(CompositeListEntry compositeListEntry) {
      return this.postUpdateConsumer.resolveMovementOptionProperty(compositeListEntry);
   }

   private TickManager resolveTickManager(List list2, double offset, boolean flag, GenericOptionType genericOptionType) {
      SupplementalDoubleEntry supplementalDoubleEntry = this.resolveSupplementalDoubleEntry(list2, offset);
      AdvancedListEntry advancedListEntry = supplementalDoubleEntry.advancedListEntry;
      double value4 = ((PostMotionObserver)advancedListEntry.getDataRef2()).getValue();
      double value5 = ((PostMotionObserver)advancedListEntry.getDataRef()).getValue();
      if (flag) {
         if (!(advancedListEntry.getDataRef2() instanceof AuxiliaryListenerController)) {
            value4 = Math.toRadians(value4);
            if (genericOptionType == GenericOptionType.X || genericOptionType == GenericOptionType.Y) {
               value4 *= -1.0;
            }
         }

         if (!(advancedListEntry.getDataRef() instanceof AuxiliaryListenerController)) {
            value5 = Math.toRadians(value5);
            if (genericOptionType == GenericOptionType.X || genericOptionType == GenericOptionType.Y) {
               value5 *= -1.0;
            }
         }
      }

      return new TickManager(advancedListEntry, supplementalDoubleEntry.value, advancedListEntry.getMemberRef(), value4, value5);
   }

   public RenderValueProperty getRenderValueProperty() {
      return this.renderValueProperty;
   }

   public void setFactoryListener(FactoryListener factoryListener2) {
      this.factoryListener = factoryListener2;
   }

   public void setValue2(double value4) {
      this.value2 = value4;
   }

   public FunctionEntry(PreMotionBroadcaster preMotionBroadcaster2, String text, float factor, ProviderOptionType providerOptionType2, PostUpdateConsumer postUpdateConsumer2) {
      this.queue = new LinkedList();
      this.localListHolder = new LocalListHolder();
      this.enabled = false;
      this.hashMap2 = new HashMap();
      this.enabled2 = false;
      this.enabled6 = false;
      this.enabled4 = false;
      this.value2 = 1.0;
      this.set = new HashSet();
      this.providerOptionType = ProviderOptionType.NONE;
      this.preMotionBroadcaster = preMotionBroadcaster2;
      this.labelText = text;
      this.value3 = (double)factor;
      this.providerOptionType = providerOptionType2;
      this.postUpdateConsumer = postUpdateConsumer2;
      this.value = 0.0;
   }

   public FunctionEntry(PreMotionBroadcaster preMotionBroadcaster2, String text, float factor, Function mapper, PostUpdateConsumer postUpdateConsumer2) {
      this.queue = new LinkedList();
      this.localListHolder = new LocalListHolder();
      this.enabled = false;
      this.hashMap2 = new HashMap();
      this.enabled2 = false;
      this.enabled6 = false;
      this.enabled4 = false;
      this.value2 = 1.0;
      this.set = new HashSet();
      this.providerOptionType = ProviderOptionType.NONE;
      this.preMotionBroadcaster = preMotionBroadcaster2;
      this.labelText = text;
      this.value3 = (double)factor;
      this.function = mapper;
      this.providerOptionType = ProviderOptionType.CUSTOM;
      this.postUpdateConsumer = postUpdateConsumer2;
      this.value = 0.0;
   }

   public FunctionEntry(PreMotionBroadcaster preMotionBroadcaster2, String text, float factor, PostUpdateConsumer postUpdateConsumer2) {
      this.queue = new LinkedList();
      this.localListHolder = new LocalListHolder();
      this.enabled = false;
      this.hashMap2 = new HashMap();
      this.enabled2 = false;
      this.enabled6 = false;
      this.enabled4 = false;
      this.value2 = 1.0;
      this.set = new HashSet();
      this.providerOptionType = ProviderOptionType.NONE;
      this.preMotionBroadcaster = preMotionBroadcaster2;
      this.labelText = text;
      this.value3 = (double)factor;
      this.postUpdateConsumer = postUpdateConsumer2;
      this.value = 0.0;
   }
}
