package cc.baritone.utils;

import cc.baritone.api.INACTIVEHandlerAdapter;
import client.util.AimObserver;
import client.util.IBlockState;
import client.util.MovingObjectType;
import it.unimi.dsi.fastutil.longs.LongOpenHashSet;
import java.io.File;
import java.io.FileInputStream;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import net.minecraft.block.BlockAir;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.Blocks;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.util.Vec3i;

public final class BuilderPausedSchematicsHandler extends INACTIVEHandlerAdapter implements SchematicsHandler {
   public HashSet hashSet;
   public LongOpenHashSet longOpenHashSet;
   public String labelText;
   public InternalListener internalListener;
   public InternalListener internalListener2;
   public Vec3i vec3i;
   public int count2;
   public boolean enabled;
   public int count4;
   public int count;
   public List list;
   public int count3 = 0;

   private void applyList(List list2, List list3, ListEntry listEntry, BetterBlockPosXSYSZSBlockPos betterBlockPosXSYSZSBlockPos) {
      if (!list2.contains(betterBlockPosXSYSZSBlockPos.getBetterBlockPosXSYSZSBlockPos4()) && !list2.contains(betterBlockPosXSYSZSBlockPos.resolveBetterBlockPosXSYSZSBlockPos4(2))) {
         list3.add(this.resolveSecondaryListener(betterBlockPosXSYSZSBlockPos, listEntry));
      }
   }

   public static InternalListener resolveInternalListener(BuilderPausedSchematicsHandler builderPausedSchematicsHandler) {
      return builderPausedSchematicsHandler.internalListener2;
   }

   @Override
   public String getText() {
      return this.enabled ? "Builder Paused" : "Building " + this.labelText;
   }

   private boolean matchesListEntry(ListEntry listEntry) {
      if (this.hashSet == null) {
         this.hashSet = new HashSet();
         this.applyListEntryByListEntry(listEntry);
         if (this.hashSet.isEmpty()) {
            return false;
         }
      }

      this.applyListEntry(listEntry);
      if (this.hashSet.isEmpty()) {
         this.applyListEntryByListEntry(listEntry);
      }

      return !this.hashSet.isEmpty();
   }

   private List resolveList(int count5) {
      java.util.ArrayList list2 = new java.util.ArrayList();

      for (int index = 0; index < count5; index++) {
         ItemStack stack = this.staticListener.getEntityPlayerSP().inventoryPlayer.mainInventory[index];
         if (stack != null && stack.getItem() instanceof ItemBlock) {
            list2.add(
               ((ItemBlock)stack.getItem())
                  .getBlock()
                  .onBlockPlaced(
                     this.staticListener.getWorld(),
                     this.staticListener.getBetterBlockPosXSYSZSBlockPosAsBetterBlockPosXS(),
                     EnumFacing.UP,
                     (float)this.staticListener.getEntityPlayerSP().posX,
                     (float)this.staticListener.getEntityPlayerSP().posY,
                     (float)this.staticListener.getEntityPlayerSP().posZ,
                     stack.getItem().processAction2(stack.getIndexOffset()),
                     this.staticListener.getEntityPlayerSP()
                  )
            );
         } else {
            list2.add(Blocks.air.getDefaultState());
         }
      }

      return list2;
   }

   private SecondaryListener resolveSecondaryListener(BlockPos blockPos, ListEntry listEntry) {
      if (this.staticListener.getWorld().getBlockState(blockPos).getBlock() != Blocks.air) {
         return new DepthFirstController(blockPos);
      } else {
         boolean flag = this.staticListener.getWorld().getBlockState(blockPos.up()).getBlock() != Blocks.air;
         IBlockState blockState = this.staticListener.getWorld().getBlockState(blockPos);

         for (EnumFacing enumFacing : AStarPipeline.enumFacingArray) {
            if (AStarPredictor.matchesStaticListenerByStaticListener(this.staticListener, blockPos.offset(enumFacing))
               && this.staticListener
                  .getWorld()
                  .matchesBlock(ListEntry.resolveIBlockState(listEntry, blockPos.getX(), blockPos.getY(), blockPos.getZ(), blockState).getBlock(), blockPos, false, enumFacing, null, null)) {
               return new AStarInspector(blockPos, blockPos.offset(enumFacing), flag);
            }
         }

         return new DepthFirstController(blockPos);
      }
   }

   @Override
   public void updateState2() {
      this.enabled = false;
   }

   public static Vec3i resolveVec3i(BuilderPausedSchematicsHandler builderPausedSchematicsHandler) {
      return builderPausedSchematicsHandler.vec3i;
   }

   public Listener5Entry resolveListener5Entry(boolean flag, boolean flag2, int count5) {
      if (count5 > 1000) {
         return new Listener5Entry(null, HeuristicCoordinator.SET_GOAL_AND_PATH);
      } else {
         this.list = this.resolveList(36);
         if (this.baritoneManager.getMapEntry().matchesBaseCustom(BaseCustom.CLICK_LEFT)) {
            this.count2 = 5;
         } else {
            this.count2--;
         }

         this.baritoneManager.getMapEntry().clear();
         if (this.enabled) {
            return new Listener5Entry(null, HeuristicCoordinator.CANCEL_AND_SET_GOAL);
         } else {
            if ((Boolean)BaritoneManager.getSettings().buildInLayers.dataRef) {
               if (this.internalListener == null) {
                  this.internalListener = this.internalListener2;
               }

               InternalListener internalListener3 = this.internalListener;
               int count6;
               int count7;
               if ((Boolean)BaritoneManager.getSettings().layerOrder.dataRef) {
                  count6 = internalListener3.getCount2() - 1;
                  count7 = internalListener3.getCount2() - this.count4 * (Integer)BaritoneManager.getSettings().layerHeight.dataRef;
               } else {
                  count6 = this.count4 * (Integer)BaritoneManager.getSettings().layerHeight.dataRef - 1;
                  count7 = 0;
               }

               this.internalListener2 = new DepthFirstOptimizer(this, internalListener3, count7, count6);
            }

            ListEntry listEntry = new ListEntry(this);
            if (!this.matchesListEntry(listEntry)) {
               if ((Boolean)BaritoneManager.getSettings().buildInLayers.dataRef
                  && this.count4 * (Integer)BaritoneManager.getSettings().layerHeight.dataRef < this.count3) {
                  this.applyText("Starting layer " + this.count4);
                  this.count4++;
                  return this.resolveListener5Entry(flag, flag2, count5 + 1);
               } else {
                  Vec3i dataRef = (Vec3i)BaritoneManager.getSettings().buildRepeat.dataRef;
                  int dataRef2 = (Integer)BaritoneManager.getSettings().buildRepeatCount.dataRef;
                  this.count++;
                  if (dataRef.equals(new Vec3i(0, 0, 0)) || dataRef2 != -1 && this.count >= dataRef2) {
                     this.applyText("Done building");
                     if ((Boolean)BaritoneManager.getSettings().notificationOnBuildFinished.dataRef) {
                        this.applyText("Done building", false);
                     }

                     this.updateState();
                     return null;
                  } else {
                     this.count4 = 0;
                     this.vec3i = new BlockPos(this.vec3i).getBlockPos2(dataRef);
                     if (!(Boolean)BaritoneManager.getSettings().buildRepeatSneaky.dataRef) {
                        this.internalListener2.updateState();
                     }

                     this.applyText("Repeating build in vector " + dataRef + ", new origin is " + this.vec3i);
                     return this.resolveListener5Entry(flag, flag2, count5 + 1);
                  }
               }
            } else {
               if ((Boolean)BaritoneManager.getSettings().distanceTrim.dataRef) {
                  this.updateState4();
               }

               Optional optional = this.resolveOptional(listEntry);
               if (optional.isPresent() && flag2 && this.staticListener.getEntityPlayerSP().onGround) {
                  DefaultCustom dataRef3 = (DefaultCustom)((AimObserver)optional.get()).getDataRef();
                  BetterBlockPosXSYSZSBlockPos dataRef4 = (BetterBlockPosXSYSZSBlockPos)((AimObserver)optional.get()).getDataRef2();
                  this.baritoneManager.getTickListenerAdapter().applyDefaultCustom(dataRef3, true);
                  AStarPredictor.applyStaticListener(this.staticListener, listEntry.resolveIBlockState(dataRef4));
                  if (this.staticListener.getEntityPlayerSP().isSprinting()) {
                     this.baritoneManager.getMapEntry().applyBaseCustom(BaseCustom.SNEAK, true);
                  }

                  if (this.staticListener.matchesBlockPos(dataRef4) || this.staticListener.getDefaultCustom().matchesDefaultCustomByDefaultCustom(dataRef3)) {
                     this.baritoneManager.getMapEntry().applyBaseCustom(BaseCustom.CLICK_LEFT, true);
                  }

                  return new Listener5Entry(null, HeuristicCoordinator.CANCEL_AND_SET_GOAL);
               } else {
                  java.util.ArrayList list2 = new java.util.ArrayList();
                  Optional optional2 = this.resolveOptional(listEntry, list2);
                  if (optional2.isPresent() && flag2 && this.staticListener.getEntityPlayerSP().onGround && this.count2 <= 0) {
                     DefaultCustom defaultCustom = AStarEstimator.resolveDefaultCustom((AStarEstimator)optional2.get());
                     this.baritoneManager.getTickListenerAdapter().applyDefaultCustom(defaultCustom, true);
                     this.staticListener.getEntityPlayerSP().inventoryPlayer.currentItem = AStarEstimator.computeCount((AStarEstimator)optional2.get());
                     this.baritoneManager.getMapEntry().applyBaseCustom(BaseCustom.SNEAK, true);
                     if (this.staticListener.matchesBlockPos(AStarEstimator.resolveBlockPos((AStarEstimator)optional2.get()))
                           && this.staticListener.getMovingObjectPosition().sideHit.equals(AStarEstimator.resolveEnumFacing((AStarEstimator)optional2.get()))
                        || this.staticListener.getDefaultCustom().matchesDefaultCustomByDefaultCustom(defaultCustom)) {
                        this.baritoneManager.getMapEntry().applyBaseCustom(BaseCustom.CLICK_RIGHT, true);
                     }

                     return new Listener5Entry(null, HeuristicCoordinator.CANCEL_AND_SET_GOAL);
                  } else {
                     if ((Boolean)BaritoneManager.getSettings().allowInventory.dataRef) {
                        java.util.ArrayList list3 = new java.util.ArrayList();
                        java.util.ArrayList list4 = new java.util.ArrayList();

                        outerLoop:
                        for (IBlockState blockState : (Iterable<IBlockState>)(Iterable<?>)list2) {
                           for (int index = 0; index < 9; index++) {
                              if (this.matchesIBlockState((IBlockState)this.list.get(index), blockState, true)) {
                                 list3.add(index);
                                 continue outerLoop;
                              }
                           }

                           list4.add(blockState);
                        }

                        innerLoop:
                        for (int index2 = 9; index2 < 36; index2++) {
                           for (IBlockState blockState2 : (Iterable<IBlockState>)(Iterable<?>)list4) {
                              if (this.matchesIBlockState((IBlockState)this.list.get(index2), blockState2, true)) {
                                 if (!this.baritoneManager.getRememberingToMoveManager().matchesPredicate(index2, list3::contains)) {
                                    return new Listener5Entry(null, HeuristicCoordinator.REQUEST_PAUSE);
                                 }
                                 break innerLoop;
                              }
                           }
                        }
                     }

                     SecondaryListener secondaryListener = this.resolveSecondaryListener(listEntry, this.list.subList(0, 9));
                     if (secondaryListener == null) {
                        secondaryListener = this.resolveSecondaryListener(listEntry, this.list, true);
                        if (secondaryListener == null) {
                           if ((Boolean)BaritoneManager.getSettings().skipFailedLayers.dataRef
                              && (Boolean)BaritoneManager.getSettings().buildInLayers.dataRef
                              && this.count4 * (Integer)BaritoneManager.getSettings().layerHeight.dataRef < this.internalListener.getCount2()) {
                              this.applyText("Skipping layer that I cannot construct! Layer #" + this.count4);
                              this.count4++;
                              return this.resolveListener5Entry(flag, flag2, count5 + 1);
                           }

                           this.applyText("Unable to do it. Pausing. resume to resume, cancel to cancel");
                           this.enabled = true;
                           return new Listener5Entry(null, HeuristicCoordinator.REQUEST_PAUSE);
                        }
                     }

                     return new Listener5EntryAdapter(secondaryListener, HeuristicCoordinator.FORCE_REVALIDATE_GOAL_AND_PATH, listEntry);
                  }
               }
            }
         }
      }
   }

   private static String resolveText(Entry entry) {
      return String.format("%sx %s", entry.getValue(), entry.getKey());
   }

   public static List resolveList(BuilderPausedSchematicsHandler builderPausedSchematicsHandler, int count5) {
      return builderPausedSchematicsHandler.resolveList(count5);
   }

   private OptionalInt resolveOptionalInt(IBlockState blockState, MovingObjectPosition hitResult, DefaultCustom defaultCustom) {
      for (int index = 0; index < 9; index++) {
         ItemStack stack = this.staticListener.getEntityPlayerSP().inventoryPlayer.mainInventory[index];
         if (stack != null && stack.getItem() instanceof ItemBlock) {
            float yaw = this.staticListener.getEntityPlayerSP().rotationYaw;
            float pitch = this.staticListener.getEntityPlayerSP().rotationPitch;
            this.staticListener.getEntityPlayerSP().rotationYaw = defaultCustom.getFactor();
            this.staticListener.getEntityPlayerSP().rotationPitch = defaultCustom.getFactor2();
            IBlockState blockState2 = ((ItemBlock)stack.getItem())
               .getBlock()
               .onBlockPlaced(
                  this.staticListener.getWorld(),
                  hitResult.getBlockPos().offset(hitResult.sideHit),
                  hitResult.sideHit,
                  (float)hitResult.hitVec.xCoord - (float)hitResult.getBlockPos().getX(),
                  (float)hitResult.hitVec.yCoord - (float)hitResult.getBlockPos().getY(),
                  (float)hitResult.hitVec.zCoord - (float)hitResult.getBlockPos().getZ(),
                  stack.getItem().processAction2(stack.getIndexOffset()),
                  this.staticListener.getEntityPlayerSP()
               );
            this.staticListener.getEntityPlayerSP().rotationYaw = yaw;
            this.staticListener.getEntityPlayerSP().rotationPitch = pitch;
            if (this.matchesIBlockState(blockState2, blockState, true)) {
               return OptionalInt.of(index);
            }
         }
      }

      return OptionalInt.empty();
   }

   private SecondaryListener resolveSecondaryListener(ListEntry listEntry, List list2) {
      return this.resolveSecondaryListener(listEntry, list2, false);
   }

   private InternalListener resolveInternalListener(Vec3i vec, AStarRegistry aStarRegistry) {
      Object entry = aStarRegistry;
      if ((Boolean)BaritoneManager.getSettings().mapArtMode.dataRef) {
         entry = new BreadthFirstController(aStarRegistry);
      }

      if ((Boolean)BaritoneManager.getSettings().buildOnlySelection.dataRef) {
         entry = new AltCustom((InternalListener)entry, vec, this.baritoneManager.getListenerBridge().getManagedListenerArrayAsC44OArray());
      }

      return (InternalListener)entry;
   }

   private void updateState4() {
      HashSet set = new HashSet(this.hashSet);
      set.removeIf((item -> this.matchesBetterBlockPosXSYSZSBlockP((BetterBlockPosXSYSZSBlockPos)item)));
      if (!set.isEmpty()) {
         this.hashSet = set;
      }
   }

   private static void applyList(List list2, BetterBlockPosXSYSZSBlockPos betterBlockPosXSYSZSBlockPos) {
      list2.add(new DepthFirstResolver(betterBlockPosXSYSZSBlockPos.getBetterBlockPosXSYSZSBlockPos3()));
   }

   @Override
   public List getList() {
      return new java.util.ArrayList(this.list);
   }

   private boolean matchesIBlockState(IBlockState blockState, IBlockState blockState2, boolean flag) {
      if (blockState2 == null) {
         return true;
      } else if (blockState.getBlock() instanceof BlockLiquid && (Boolean)BaritoneManager.getSettings().okIfWater.dataRef) {
         return true;
      } else if (blockState.getBlock() instanceof BlockAir && ((List)BaritoneManager.getSettings().okIfAir.dataRef).contains(blockState2.getBlock())) {
         return true;
      } else if (blockState2.getBlock() instanceof BlockAir && ((List)BaritoneManager.getSettings().buildIgnoreBlocks.dataRef).contains(blockState.getBlock())) {
         return true;
      } else if (!(blockState.getBlock() instanceof BlockAir) && (Boolean)BaritoneManager.getSettings().buildIgnoreExisting.dataRef && !flag) {
         return true;
      } else if (((List)BaritoneManager.getSettings().buildSkipBlocks.dataRef).contains(blockState2.getBlock()) && !flag) {
         return true;
      } else if (((List)((Map)BaritoneManager.getSettings().buildValidSubstitutes.dataRef)
            .getOrDefault(blockState2.getBlock(), Collections.emptyList()))
            .contains(blockState.getBlock())
         && !flag) {
         return true;
      } else {
         return blockState.equals(blockState2) ? true : blockState.equals(blockState2);
      }
   }

   private void applyListEntry(ListEntry listEntry) {
      BetterBlockPosXSYSZSBlockPos betterBlockPosXSYSZSBlockPosAsBetterBlockPosXS = this.staticListener.getBetterBlockPosXSYSZSBlockPosAsBetterBlockPosXS();
      int dataRef = (Integer)BaritoneManager.getSettings().builderTickScanRadius.dataRef;

      for (int index = -dataRef; index <= dataRef; index++) {
         for (int index2 = -dataRef; index2 <= dataRef; index2++) {
            for (int index3 = -dataRef; index3 <= dataRef; index3++) {
               int count5 = betterBlockPosXSYSZSBlockPosAsBetterBlockPosXS.count9 + index;
               int count6 = betterBlockPosXSYSZSBlockPosAsBetterBlockPosXS.count12 + index2;
               int count7 = betterBlockPosXSYSZSBlockPosAsBetterBlockPosXS.count10 + index3;
               IBlockState blockState = ListEntry.resolveIBlockState(listEntry, count5, count6, count7, listEntry.heuristicManager.resolveIBlockState(count5, count6, count7));
               if (blockState != null) {
                  BetterBlockPosXSYSZSBlockPos betterBlockPosXSYSZSBlockPos = new BetterBlockPosXSYSZSBlockPos(count5, count6, count7);
                  if (this.matchesIBlockState(listEntry.heuristicManager.resolveIBlockState(count5, count6, count7), blockState, false)) {
                     this.hashSet.remove(betterBlockPosXSYSZSBlockPos);
                     this.longOpenHashSet.add(BetterBlockPosXSYSZSBlockPos.computeTimestamp(betterBlockPosXSYSZSBlockPos));
                  } else {
                     this.hashSet.add(betterBlockPosXSYSZSBlockPos);
                     this.longOpenHashSet.remove(BetterBlockPosXSYSZSBlockPos.computeTimestamp(betterBlockPosXSYSZSBlockPos));
                  }
               }
            }
         }
      }
   }

   @Override
   public boolean isEnabledAsFlag() {
      return this.enabled;
   }

   @Override
   public void applyText(String text, InternalListener internalListener3, Vec3i vec) {
      this.labelText = text;
      this.internalListener2 = internalListener3;
      this.internalListener = null;
      boolean flag = internalListener3 instanceof AltCustom;
      if (!((Map)BaritoneManager.getSettings().buildSubstitutes.dataRef).isEmpty()) {
         this.internalListener2 = new BreadthFirstProcessor(this.internalListener2, (Map)BaritoneManager.getSettings().buildSubstitutes.dataRef);
      }

      int coordX = vec.getX();
      int coordY = vec.getY();
      int coordZ = vec.getZ();
      if ((Boolean)BaritoneManager.getSettings().schematicOrientationX.dataRef) {
         coordX += internalListener3.getCount();
      }

      if ((Boolean)BaritoneManager.getSettings().schematicOrientationY.dataRef) {
         coordY += internalListener3.getCount2();
      }

      if ((Boolean)BaritoneManager.getSettings().schematicOrientationZ.dataRef) {
         coordZ += internalListener3.getCount3();
      }

      this.vec3i = new Vec3i(coordX, coordY, coordZ);
      this.enabled = false;
      this.count4 = (Integer)BaritoneManager.getSettings().startAtLayer.dataRef;
      this.count3 = internalListener3.getCount2();
      if ((Boolean)BaritoneManager.getSettings().buildOnlySelection.dataRef && flag) {
         if (this.baritoneManager.getListenerBridge().getManagedListenerArrayAsC44OArray().length == 0) {
            this.applyText("Poor little kitten forgot to set a selection while BuildOnlySelection is true");
            this.count3 = 0;
         } else if ((Boolean)BaritoneManager.getSettings().buildInLayers.dataRef) {
            OptionalInt optionalInt = Stream.of(this.baritoneManager.getListenerBridge().getManagedListenerArrayAsC44OArray())
               .mapToInt(BuilderPausedSchematicsHandler::computeCountByManagedListener)
               .min();
            OptionalInt optionalInt2 = Stream.of(this.baritoneManager.getListenerBridge().getManagedListenerArrayAsC44OArray())
               .mapToInt(BuilderPausedSchematicsHandler::computeCount)
               .max();
            if (optionalInt.isPresent() && optionalInt2.isPresent()) {
               int count5 = (Boolean)BaritoneManager.getSettings().layerOrder.dataRef ? coordY + internalListener3.getCount2() - optionalInt2.getAsInt() : optionalInt.getAsInt() - coordY;
               this.count3 = ((Boolean)BaritoneManager.getSettings().layerOrder.dataRef ? coordY + internalListener3.getCount2() - optionalInt.getAsInt() : optionalInt2.getAsInt() - coordY)
                  + 1;
               this.count4 = Math.max(this.count4, count5 / (Integer)BaritoneManager.getSettings().layerHeight.dataRef);
               this.applyTextByText(String.format("Schematic starts at y=%s with height %s", coordY, internalListener3.getCount2()));
               this.applyTextByText(String.format("Selection starts at y=%s and ends at y=%s", optionalInt.getAsInt(), optionalInt2.getAsInt()));
               this.applyTextByText(String.format("Considering relevant height %s - %s", count5, this.count3));
            }
         }
      }

      this.count = 0;
      this.longOpenHashSet = new LongOpenHashSet();
      this.hashSet = null;
   }

   @Override
   public void updateState() {
      this.hashSet = null;
      this.labelText = null;
      this.internalListener2 = null;
      this.internalListener = null;
      this.count4 = (Integer)BaritoneManager.getSettings().startAtLayer.dataRef;
      this.count = 0;
      this.enabled = false;
      this.longOpenHashSet = null;
   }

   private Optional resolveOptional(ListEntry listEntry) {
      BetterBlockPosXSYSZSBlockPos betterBlockPosXSYSZSBlockPosAsBetterBlockPosXS = this.staticListener.getBetterBlockPosXSYSZSBlockPosAsBetterBlockPosXS();
      BetterBlockPosXSYSZSBlockPos betterBlockPosXSYSZSBlockPos = this.baritoneManager.getAlreadyDoingItManager().getBetterBlockPosXSYSZSBlockPos();

      for (int index = -5; index <= 5; index++) {
         for (int index2 = (Boolean)BaritoneManager.getSettings().breakFromAbove.dataRef ? -1 : 0; index2 <= 5; index2++) {
            for (int index3 = -5; index3 <= 5; index3++) {
               int count5 = betterBlockPosXSYSZSBlockPosAsBetterBlockPosXS.count9 + index;
               int count6 = betterBlockPosXSYSZSBlockPosAsBetterBlockPosXS.count12 + index2;
               int count7 = betterBlockPosXSYSZSBlockPosAsBetterBlockPosXS.count10 + index3;
               if (index2 != -1 || count5 != betterBlockPosXSYSZSBlockPos.count9 || count7 != betterBlockPosXSYSZSBlockPos.count10) {
                  IBlockState blockState = ListEntry.resolveIBlockState(listEntry, count5, count6, count7, listEntry.heuristicManager.resolveIBlockState(count5, count6, count7));
                  if (blockState != null) {
                     IBlockState blockState2 = listEntry.heuristicManager.resolveIBlockState(count5, count6, count7);
                     if (blockState2.getBlock() != Blocks.air && !(blockState2.getBlock() instanceof BlockLiquid) && !this.matchesIBlockState(blockState2, blockState, false)) {
                        BetterBlockPosXSYSZSBlockPos betterBlockPosXSYSZSBlockPos2 = new BetterBlockPosXSYSZSBlockPos(count5, count6, count7);
                        Optional optional = BreadthFirstRecorder.resolveOptional(this.staticListener, betterBlockPosXSYSZSBlockPos2, this.staticListener.getDefaultListener().getValue());
                        if (optional.isPresent()) {
                           return Optional.of(new AimObserver(betterBlockPosXSYSZSBlockPos2, optional.get()));
                        }
                     }
                  }
               }
            }
         }
      }

      return Optional.empty();
   }

   private Optional resolveOptional(IBlockState blockState, int count5, int count6, int count7, HeuristicManager heuristicManager) {
      for (EnumFacing enumFacing : EnumFacing.values()) {
         BetterBlockPosXSYSZSBlockPos betterBlockPosXSYSZSBlockPos = new BetterBlockPosXSYSZSBlockPos(count5, count6, count7).resolveBetterBlockPosXSYSZSBlockPos(enumFacing);
         IBlockState blockState2 = heuristicManager.resolveIBlockState(betterBlockPosXSYSZSBlockPos);
         if (!AStarPredictor.matchesIBlockState2(betterBlockPosXSYSZSBlockPos.count9, betterBlockPosXSYSZSBlockPos.count12, betterBlockPosXSYSZSBlockPos.count10, blockState2, heuristicManager)
            && this.staticListener.getWorld().matchesBlock(blockState.getBlock(), new BetterBlockPosXSYSZSBlockPos(count5, count6, count7), false, enumFacing, null, null)) {
            AxisAlignedBB boundingBox = blockState2.getBlock().processAction(this.staticListener.getWorld(), betterBlockPosXSYSZSBlockPos);

            for (Vec3 vec : resolveVec3Array(enumFacing)) {
               double offset = (double)betterBlockPosXSYSZSBlockPos.count9
                  + (boundingBox.minX - (double)betterBlockPosXSYSZSBlockPos.count9) * vec.xCoord
                  + (boundingBox.maxX - (double)betterBlockPosXSYSZSBlockPos.count9) * (1.0 - vec.xCoord);
               double offset2 = (double)betterBlockPosXSYSZSBlockPos.count12
                  + (boundingBox.minY - (double)betterBlockPosXSYSZSBlockPos.count12) * vec.yCoord
                  + (boundingBox.maxY - (double)betterBlockPosXSYSZSBlockPos.count12) * (1.0 - vec.yCoord);
               double offset3 = (double)betterBlockPosXSYSZSBlockPos.count10
                  + (boundingBox.minZ - (double)betterBlockPosXSYSZSBlockPos.count10) * vec.zCoord
                  + (boundingBox.maxZ - (double)betterBlockPosXSYSZSBlockPos.count12) * (1.0 - vec.zCoord);
               DefaultCustom defaultCustom = BreadthFirstRecorder.resolveDefaultCustom(
                  CoreCustom.resolveVec3(this.staticListener.getEntityPlayerSP()), new Vec3(offset, offset2, offset3), this.staticListener.getDefaultCustom()
               );
               DefaultCustom defaultCustom2 = this.baritoneManager.getTickListenerAdapter().getStandardListener().resolveDefaultCustom(defaultCustom);
               MovingObjectPosition hitResult = CoreCustom.resolveMovingObjectPosition(
                  this.staticListener.getEntityPlayerSP(), defaultCustom2, this.staticListener.getDefaultListener().getValue(), true
               );
               if (hitResult != null && hitResult.typeOfHit == MovingObjectType.BLOCK && hitResult.getBlockPos().equals(betterBlockPosXSYSZSBlockPos) && hitResult.sideHit == enumFacing.getEnumFacing5()) {
                  OptionalInt optionalInt = this.resolveOptionalInt(blockState, hitResult, defaultCustom2);
                  if (optionalInt.isPresent()) {
                     return Optional.of(new AStarEstimator(optionalInt.getAsInt(), betterBlockPosXSYSZSBlockPos, enumFacing.getEnumFacing5(), defaultCustom));
                  }
               }
            }
         }
      }

      return Optional.empty();
   }

   private void applyListEntryByListEntry(ListEntry listEntry) {
      this.hashSet = new HashSet();

      for (int index = 0; index < this.internalListener2.getCount2(); index++) {
         for (int index2 = 0; index2 < this.internalListener2.getCount3(); index2++) {
            for (int index3 = 0; index3 < this.internalListener2.getCount(); index3++) {
               int count5 = index3 + this.vec3i.getX();
               int count6 = index + this.vec3i.getY();
               int count7 = index2 + this.vec3i.getZ();
               IBlockState blockState = listEntry.heuristicManager.resolveIBlockState(count5, count6, count7);
               if (this.internalListener2.matchesIBlockState(index3, index, index2, blockState)) {
                  if (listEntry.heuristicManager.checkIndex(count5, count7)) {
                     if (this.matchesIBlockState(
                        listEntry.heuristicManager.resolveIBlockState(count5, count6, count7),
                        this.internalListener2.resolveIBlockState(index3, index, index2, blockState, this.list),
                        false
                     )) {
                        this.longOpenHashSet.add(BetterBlockPosXSYSZSBlockPos.computeTimestampByIndex(count5, count6, count7));
                     } else {
                        this.hashSet.add(new BetterBlockPosXSYSZSBlockPos(count5, count6, count7));
                        this.longOpenHashSet.remove(BetterBlockPosXSYSZSBlockPos.computeTimestampByIndex(count5, count6, count7));
                        if (this.hashSet.size() > (Integer)BaritoneManager.getSettings().incorrectSize.dataRef) {
                           return;
                        }
                     }
                  } else if (!this.longOpenHashSet.contains(BetterBlockPosXSYSZSBlockPos.computeTimestampByIndex(count5, count6, count7))
                     && !((List)BaritoneManager.getSettings().buildSkipBlocks.dataRef)
                        .contains(this.internalListener2.resolveIBlockState(index3, index, index2, blockState, this.list).getBlock())) {
                     this.hashSet.add(new BetterBlockPosXSYSZSBlockPos(count5, count6, count7));
                     if (this.hashSet.size() > (Integer)BaritoneManager.getSettings().incorrectSize.dataRef) {
                        return;
                     }
                  }
               }
            }
         }
      }
   }

   @Override
   public boolean matchesText(String text, File file, Vec3i vec) {
      Optional optional = DiagonalNavigator.INSTANCE.resolveOptional(file);
      if (!optional.isPresent()) {
         return false;
      } else {
         AStarRegistry aStarRegistry;
         try {
            aStarRegistry = ((HeuristicStrategy)optional.get()).resolveAStarRegistry(new FileInputStream(file));
         } catch (Exception exception) {
            exception.printStackTrace();
            return false;
         }

         InternalListener internalListener3 = this.resolveInternalListener(vec, aStarRegistry);
         this.applyText(text, internalListener3, vec);
         return true;
      }
   }

   @Override
   public boolean isEnabled() {
      return this.internalListener2 != null;
   }

   private static int computeCount(ManagedListener managedListener) {
      return managedListener.getBetterBlockPosXSYSZSBlockPos4().count12;
   }

   private Optional resolveOptional(ListEntry listEntry, List list2) {
      BetterBlockPosXSYSZSBlockPos betterBlockPosXSYSZSBlockPosAsBetterBlockPosXS = this.staticListener.getBetterBlockPosXSYSZSBlockPosAsBetterBlockPosXS();

      for (int index = -5; index <= 5; index++) {
         for (int index2 = -5; index2 <= 1; index2++) {
            for (int index3 = -5; index3 <= 5; index3++) {
               int count5 = betterBlockPosXSYSZSBlockPosAsBetterBlockPosXS.count9 + index;
               int count6 = betterBlockPosXSYSZSBlockPosAsBetterBlockPosXS.count12 + index2;
               int count7 = betterBlockPosXSYSZSBlockPosAsBetterBlockPosXS.count10 + index3;
               IBlockState blockState = ListEntry.resolveIBlockState(listEntry, count5, count6, count7, listEntry.heuristicManager.resolveIBlockState(count5, count6, count7));
               if (blockState != null) {
                  IBlockState blockState2 = listEntry.heuristicManager.resolveIBlockState(count5, count6, count7);
                  if (AStarPredictor.matchesIBlockState2(count5, count6, count7, blockState2, listEntry.heuristicManager)
                     && !this.matchesIBlockState(blockState2, blockState, false)
                     && (index2 != 1 || listEntry.heuristicManager.resolveIBlockState(count5, count6 + 1, count7).getBlock() != Blocks.air)) {
                     list2.add(blockState);
                     Optional optional = this.resolveOptional(blockState, count5, count6, count7, listEntry.heuristicManager);
                     if (optional.isPresent()) {
                        return optional;
                     }
                  }
               }
            }
         }
      }

      return Optional.empty();
   }

   public IBlockState resolveIBlockState(int count5, int count6, int count7, IBlockState blockState) {
      if (!this.isEnabled()) {
         return null;
      } else if (!this.internalListener2.matchesIBlockState(count5 - this.vec3i.getX(), count6 - this.vec3i.getY(), count7 - this.vec3i.getZ(), blockState)) {
         return null;
      } else {
         IBlockState blockState2 = this.internalListener2
            .resolveIBlockState(count5 - this.vec3i.getX(), count6 - this.vec3i.getY(), count7 - this.vec3i.getZ(), blockState, this.list);
         return blockState2.getBlock() == Blocks.air ? null : blockState2;
      }
   }

   public static BaritoneManager resolveBaritoneManager(BuilderPausedSchematicsHandler builderPausedSchematicsHandler) {
      return builderPausedSchematicsHandler.baritoneManager;
   }

   @Override
   public void updateState3() {
      this.enabled = true;
   }

   @Override
   public Listener5Entry resolveListener5Entry(boolean flag, boolean flag2) {
      return this.resolveListener5Entry(flag, flag2, 0);
   }

   private static void applyListEntry(ListEntry listEntry, List list2, List list3, Map map, List list4, List list5, List list6, BetterBlockPosXSYSZSBlockPos betterBlockPosXSYSZSBlockPos) {
      IBlockState blockState = listEntry.heuristicManager.resolveIBlockState(betterBlockPosXSYSZSBlockPos);
      if (blockState.getBlock() instanceof BlockAir) {
         if (list2.contains(ListEntry.resolveIBlockState(listEntry, betterBlockPosXSYSZSBlockPos.count9, betterBlockPosXSYSZSBlockPos.count12, betterBlockPosXSYSZSBlockPos.count10, blockState))) {
            list3.add(betterBlockPosXSYSZSBlockPos);
         } else {
            IBlockState blockState2 = ListEntry.resolveIBlockState(listEntry, betterBlockPosXSYSZSBlockPos.count9, betterBlockPosXSYSZSBlockPos.count12, betterBlockPosXSYSZSBlockPos.count10, blockState);
            map.put(blockState2, 1 + (Integer)map.getOrDefault(blockState2, 0));
         }
      } else if (blockState.getBlock() instanceof BlockLiquid) {
         if (!AStarPredictor.matchesIBlockState(blockState)) {
            list4.add(betterBlockPosXSYSZSBlockPos);
         } else {
            list5.add(betterBlockPosXSYSZSBlockPos);
         }
      } else {
         list6.add(betterBlockPosXSYSZSBlockPos);
      }
   }

   public static boolean matchesBuilderPausedSchematicsHan(BuilderPausedSchematicsHandler builderPausedSchematicsHandler, IBlockState blockState, IBlockState blockState2, boolean flag) {
      return builderPausedSchematicsHandler.matchesIBlockState(blockState, blockState2, flag);
   }

   private void applyList(List list2, ListEntry listEntry, BetterBlockPosXSYSZSBlockPos betterBlockPosXSYSZSBlockPos) {
      list2.add(this.resolveSecondaryListenerByBlockPos(betterBlockPosXSYSZSBlockPos, listEntry));
   }

   private boolean matchesBetterBlockPosXSYSZSBlockP(BetterBlockPosXSYSZSBlockPos betterBlockPosXSYSZSBlockPos) {
      return betterBlockPosXSYSZSBlockPos.computeValue(
            this.staticListener.getEntityPlayerSP().posX, this.staticListener.getEntityPlayerSP().posY, this.staticListener.getEntityPlayerSP().posZ
         )
         > 200.0;
   }

   public static List resolveList(BuilderPausedSchematicsHandler builderPausedSchematicsHandler) {
      return builderPausedSchematicsHandler.list;
   }

   @Override
   public void applyBlockPos(BlockPos blockPos, BlockPos blockPos2) {
      BlockPos blockPos3 = new BlockPos(Math.min(blockPos.getX(), blockPos2.getX()), Math.min(blockPos.getY(), blockPos2.getY()), Math.min(blockPos.getZ(), blockPos2.getZ()));
      int count5 = Math.abs(blockPos.getX() - blockPos2.getX()) + 1;
      int count6 = Math.abs(blockPos.getY() - blockPos2.getY()) + 1;
      int count7 = Math.abs(blockPos.getZ() - blockPos2.getZ()) + 1;
      this.applyText("clear area", new HeuristicScanner(count5, count6, count7, Blocks.air.getDefaultState()), blockPos3);
   }

   private SecondaryListener resolveSecondaryListener(ListEntry listEntry, List list2, boolean flag) {
      java.util.ArrayList list3 = new java.util.ArrayList();
      java.util.ArrayList list4 = new java.util.ArrayList();
      java.util.ArrayList list5 = new java.util.ArrayList();
      java.util.ArrayList list6 = new java.util.ArrayList();
      HashMap map = new HashMap();
      this.hashSet.forEach(pos -> applyListEntry(listEntry, list2, list3, map, list5, list6, list4, (BetterBlockPosXSYSZSBlockPos)pos));
      java.util.ArrayList list7 = new java.util.ArrayList();
      list4.forEach(pos -> this.applyList(list7, listEntry, (BetterBlockPosXSYSZSBlockPos)pos));
      java.util.ArrayList list8 = new java.util.ArrayList();
      list3.forEach(pos -> this.applyList(list3, list8, listEntry, (BetterBlockPosXSYSZSBlockPos)pos));
      list5.forEach(pos -> applyList(list8, (BetterBlockPosXSYSZSBlockPos)pos));
      if (!list8.isEmpty()) {
         return new CustomHandler(
            new DepthFirstCoordinator(((SecondaryListener[])list8.toArray(new SecondaryListener[0]))), new DepthFirstCoordinator(((SecondaryListener[])list7.toArray(new SecondaryListener[0])))
         );
      } else if (list7.isEmpty()) {
         if (flag && !map.isEmpty()) {
            this.applyText("Missing materials for at least:");
            this.applyText((String)map.entrySet().stream().map(entry -> resolveText((Entry)entry)).collect(Collectors.joining("\n")));
         }

         if (flag && !list6.isEmpty()) {
            this.applyText("Unreplaceable liquids at at least:");
            this.applyText((String)list6.stream().map(pos -> resolveText((BetterBlockPosXSYSZSBlockPos)pos)).collect(Collectors.joining("\n")));
         }

         return null;
      } else {
         return new DepthFirstCoordinator(((SecondaryListener[])list7.toArray(new SecondaryListener[0])));
      }
   }

   private SecondaryListener resolveSecondaryListenerByBlockPos(BlockPos blockPos, ListEntry listEntry) {
      return (SecondaryListener)((Boolean)BaritoneManager.getSettings().goalBreakFromAbove.dataRef
            && listEntry.heuristicManager.resolveIBlockState(blockPos.up()).getBlock() instanceof BlockAir
            && listEntry.heuristicManager.resolveIBlockState(blockPos.resolveBlockPos2(2)).getBlock() instanceof BlockAir
         ? new CustomHandler(new AStarScanner(blockPos), new AStarEvaluator(this, blockPos.up()))
         : new AStarScanner(blockPos));
   }

   private static int computeCountByManagedListener(ManagedListener managedListener) {
      return managedListener.getBetterBlockPosXSYSZSBlockPos3().count12;
   }

   private static String resolveText(BetterBlockPosXSYSZSBlockPos betterBlockPosXSYSZSBlockPos) {
      return String.format("%s %s %s", betterBlockPosXSYSZSBlockPos.count9, betterBlockPosXSYSZSBlockPos.count12, betterBlockPosXSYSZSBlockPos.count10);
   }

   private static Vec3[] resolveVec3Array(EnumFacing enumFacing) {
      switch (DepthFirstPredictor.countArray[enumFacing.ordinal()]) {
         case 1:
            return new Vec3[]{new Vec3(0.5, 1.0, 0.5), new Vec3(0.1, 1.0, 0.5), new Vec3(0.9, 1.0, 0.5), new Vec3(0.5, 1.0, 0.1), new Vec3(0.5, 1.0, 0.9)};
         case 2:
            return new Vec3[]{new Vec3(0.5, 0.0, 0.5), new Vec3(0.1, 0.0, 0.5), new Vec3(0.9, 0.0, 0.5), new Vec3(0.5, 0.0, 0.1), new Vec3(0.5, 0.0, 0.9)};
         case 3:
         case 4:
         case 5:
         case 6:
            double offset = enumFacing.getInt2() == 0 ? 0.5 : (double)(1 + enumFacing.getInt2()) / 2.0;
            double offset2 = enumFacing.getInt() == 0 ? 0.5 : (double)(1 + enumFacing.getInt()) / 2.0;
            return new Vec3[]{new Vec3(offset, 0.25, offset2), new Vec3(offset, 0.75, offset2)};
         default:
            throw new IllegalStateException();
      }
   }

   public BuilderPausedSchematicsHandler(BaritoneManager baritoneManager) {
      super(baritoneManager);
   }
}
