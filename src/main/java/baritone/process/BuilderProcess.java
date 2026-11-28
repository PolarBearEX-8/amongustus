package baritone.process;

import baritone.api.pathing.goals.GoalGetToBlock;
import baritone.Baritone;
import baritone.api.pathing.goals.Goal;
import baritone.api.pathing.goals.GoalBlock;
import baritone.api.pathing.goals.GoalComposite;
import baritone.api.process.IBuilderProcess;
import baritone.api.process.PathingCommand;
import baritone.api.process.PathingCommandType;
import baritone.api.schematic.FillSchematic;
import baritone.api.schematic.ISchematic;
import baritone.api.schematic.IStaticSchematic;
import baritone.api.schematic.format.ISchematicFormat;
import baritone.api.utils.BetterBlockPos;
import baritone.api.utils.RayTraceUtils;
import baritone.api.utils.Rotation;
import baritone.api.utils.RotationUtils;
import baritone.api.utils.input.Input;
import baritone.utils.pathing.PathingCommandContext;
import baritone.api.schematic.SubstituteSchematic;
import baritone.utils.schematic.MapArtSchematic;
import baritone.utils.schematic.SelectionSchematic;
import baritone.pathing.movement.Movement;
import baritone.pathing.movement.MovementHelper;
import baritone.utils.BaritoneProcessHelper;
import baritone.utils.BlockStateInterface;
import baritone.utils.schematic.SchematicSystem;
import it.unimi.dsi.fastutil.longs.LongOpenHashSet;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import net.minecraft.block.BlockAir;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Tuple;
import net.minecraft.util.Vec3;
import net.minecraft.util.Vec3i;

public final class BuilderProcess extends BaritoneProcessHelper implements IBuilderProcess {
   private HashSet incorrectPositions;
   private LongOpenHashSet observedCompleted;
   private String name;
   private ISchematic realSchematic;
   private ISchematic schematic;
   private Vec3i origin;
   private int ticks;
   private boolean paused;
   private int layer;
   private int numRepeats;
   private List approxPlaceable;
   public int stopAtHeight = 0;

   public static Baritone getBaritone(BuilderProcess builderProcess) {
      return builderProcess.baritone;
   }

   @Override
   public void clearArea(BlockPos pos, BlockPos pos2) {
      BlockPos pos3 = new BlockPos(Math.min(pos.getX(), pos2.getX()), Math.min(pos.getY(), pos2.getY()), Math.min(pos.getZ(), pos2.getZ()));
      int n = Math.abs(pos.getX() - pos2.getX()) + 1;
      int n2 = Math.abs(pos.getY() - pos2.getY()) + 1;
      int n3 = Math.abs(pos.getZ() - pos2.getZ()) + 1;
      this.build2("clear area", new FillSchematic(n, n2, n3, Blocks.air.getDefaultState()), pos3);
   }

   private static Vec3[] aabbSideMultipliers(EnumFacing facing) {
      switch (facing) {
         case UP:
            return new Vec3[]{new Vec3(0.5, 1.0, 0.5), new Vec3(0.1, 1.0, 0.5), new Vec3(0.9, 1.0, 0.5), new Vec3(0.5, 1.0, 0.1), new Vec3(0.5, 1.0, 0.9)};
         case DOWN:
            return new Vec3[]{new Vec3(0.5, 0.0, 0.5), new Vec3(0.1, 0.0, 0.5), new Vec3(0.9, 0.0, 0.5), new Vec3(0.5, 0.0, 0.1), new Vec3(0.5, 0.0, 0.9)};
         case NORTH:
         case SOUTH:
         case EAST:
         case WEST:
            double d = facing.getFrontOffsetX() == 0 ? 0.5 : (double)(1 + facing.getFrontOffsetX()) / 2.0;
            double d2 = facing.getFrontOffsetZ() == 0 ? 0.5 : (double)(1 + facing.getFrontOffsetZ()) / 2.0;
            return new Vec3[]{new Vec3(d, 0.25, d2), new Vec3(d, 0.75, d2)};
         default:
            throw new IllegalStateException();
      }
   }

   public BuilderProcess(Baritone baritone2) {
      super(baritone2);
   }

   private ISchematic applyMapArtAndSelection(Vec3i vec, IStaticSchematic staticSchematic) {
      Object obj = staticSchematic;
      if ((Boolean)Baritone.settings().mapArtMode.value) {
         obj = new MapArtSchematic(staticSchematic);
      }

      if ((Boolean)Baritone.settings().buildOnlySelection.value) {
         obj = new SelectionSchematic((ISchematic)obj, vec, this.baritone.getSelectionManager2().getSelections());
      }

      return (ISchematic)obj;
   }

   private List approxPlaceable(int n) {
      ArrayList list = new ArrayList();

      for (int j = 0; j < n; j++) {
         ItemStack stack = this.ctx.player().inventory.mainInventory[j];
         if (stack != null && stack.getItem() instanceof ItemBlock) {
            list.add(
               ((ItemBlock)stack.getItem())
                  .getBlock()
                  .onBlockPlaced(
                     this.ctx.world(),
                     this.ctx.playerFeet(),
                     EnumFacing.UP,
                     (float)this.ctx.player().posX,
                     (float)this.ctx.player().posY,
                     (float)this.ctx.player().posZ,
                     stack.getItem().getMetadata(stack.getMetadata()),
                     this.ctx.player()
                  )
            );
         } else {
            list.add(Blocks.air.getDefaultState());
         }
      }

      return list;
   }

   private Goal assemble(BuilderCalculationContext context, List list, boolean flag) {
      ArrayList list2 = new ArrayList();
      ArrayList list3 = new ArrayList();
      ArrayList list4 = new ArrayList();
      ArrayList list5 = new ArrayList();
      HashMap map = new HashMap();
      this.incorrectPositions.forEach(obj -> {
         IBlockState state = context.bsi.get0((BlockPos)obj);
         if (state.getBlock() instanceof BlockAir) {
            if (list.contains(BuilderCalculationContext.getSchematicState(context, ((BetterBlockPos)obj).x2, ((BetterBlockPos)obj).y2, ((BetterBlockPos)obj).z2, state))) {
               list2.add(obj);
            } else {
               IBlockState schematicState = BuilderCalculationContext.getSchematicState(context, ((BetterBlockPos)obj).x2, ((BetterBlockPos)obj).y2, ((BetterBlockPos)obj).z2, state);
               map.put(schematicState, 1 + (Integer)map.getOrDefault(schematicState, 0));
            }
         } else if (state.getBlock() instanceof BlockLiquid) {
            if (!MovementHelper.possiblyFlowing(state)) {
               list4.add(obj);
            } else {
               list5.add(obj);
            }
         } else {
            list3.add(obj);
         }
      });
      ArrayList list6 = new ArrayList();
      list3.forEach(obj2 -> list6.add(this.breakGoal((BetterBlockPos)obj2, context)));
      ArrayList list7 = new ArrayList();
      list2.forEach(obj3 -> {
         if (!list2.contains(((BetterBlockPos)obj3).down2()) && !list2.contains(((BetterBlockPos)obj3).down2(2))) {
            list7.add(this.placementGoal((BetterBlockPos)obj3, context));
         }
      });
      list4.forEach(obj4 -> list7.add(new GoalBlock(((BetterBlockPos)obj4).up2())));
      if (!list7.isEmpty()) {
         return new JankyCompositeGoal(new GoalComposite((Goal[])list7.toArray(new Goal[0])), new GoalComposite((Goal[])list6.toArray(new Goal[0])));
      } else if (list6.isEmpty()) {
         if (flag && !map.isEmpty()) {
            this.logDirect2("Missing materials for at least:");
            this.logDirect2((String)map.entrySet().stream().map(obj5 -> String.format("%sx %s", ((java.util.Map.Entry)obj5).getValue(), ((java.util.Map.Entry)obj5).getKey())).collect(Collectors.joining("\n")));
         }

         if (flag && !list5.isEmpty()) {
            this.logDirect2("Unreplaceable liquids at at least:");
            this.logDirect2((String)list5.stream().map(obj6 -> String.format("%s %s %s", ((BetterBlockPos)obj6).x2, ((BetterBlockPos)obj6).y2, ((BetterBlockPos)obj6).z2)).collect(Collectors.joining("\n")));
         }

         return null;
      } else {
         return new GoalComposite((Goal[])list6.toArray(new Goal[0]));
      }
   }

   private boolean recalc(BuilderCalculationContext context) {
      if (this.incorrectPositions == null) {
         this.incorrectPositions = new HashSet();
         this.fullRecalc(context);
         if (this.incorrectPositions.isEmpty()) {
            return false;
         }
      }

      this.recalcNearby(context);
      if (this.incorrectPositions.isEmpty()) {
         this.fullRecalc(context);
      }

      return !this.incorrectPositions.isEmpty();
   }

   private void fullRecalc(BuilderCalculationContext context) {
      this.incorrectPositions = new HashSet();

      for (int j = 0; j < this.schematic.heightY(); j++) {
         for (int k = 0; k < this.schematic.lengthZ(); k++) {
            for (int m = 0; m < this.schematic.widthX(); m++) {
               int n = m + this.origin.getX();
               int n2 = j + this.origin.getY();
               int n3 = k + this.origin.getZ();
               IBlockState state = context.bsi.get0(n, n2, n3);
               if (this.schematic.inSchematic(m, j, k, state)) {
                  if (context.bsi.worldContainsLoadedChunk(n, n3)) {
                     if (this.valid(context.bsi.get0(n, n2, n3), this.schematic.desiredState(m, j, k, state, this.approxPlaceable), false)) {
                        this.observedCompleted.add(BetterBlockPos.longHash(n, n2, n3));
                     } else {
                        this.incorrectPositions.add(new BetterBlockPos(n, n2, n3));
                        this.observedCompleted.remove(BetterBlockPos.longHash(n, n2, n3));
                        if (this.incorrectPositions.size() > (Integer)Baritone.settings().incorrectSize.value) {
                           return;
                        }
                     }
                  } else if (!this.observedCompleted.contains(BetterBlockPos.longHash(n, n2, n3))
                     && !((List)Baritone.settings().buildSkipBlocks.value).contains(this.schematic.desiredState(m, j, k, state, this.approxPlaceable).getBlock())) {
                     this.incorrectPositions.add(new BetterBlockPos(n, n2, n3));
                     if (this.incorrectPositions.size() > (Integer)Baritone.settings().incorrectSize.value) {
                        return;
                     }
                  }
               }
            }
         }
      }
   }

   public static boolean checkValidBlockState(BuilderProcess builderProcess, IBlockState state, IBlockState state2, boolean flag) {
      return builderProcess.valid(state, state2, flag);
   }

   @Override
   public boolean isActive() {
      return this.schematic != null;
   }

   public static List getApproxPlaceable(BuilderProcess builderProcess, int n) {
      return builderProcess.approxPlaceable(n);
   }

   public IBlockState placeAt(int n, int n2, int n3, IBlockState state) {
      if (!this.isActive()) {
         return null;
      } else if (!this.schematic.inSchematic(n - this.origin.getX(), n2 - this.origin.getY(), n3 - this.origin.getZ(), state)) {
         return null;
      } else {
         IBlockState state2 = this.schematic.desiredState(n - this.origin.getX(), n2 - this.origin.getY(), n3 - this.origin.getZ(), state, this.approxPlaceable);
         return state2.getBlock() == Blocks.air ? null : state2;
      }
   }

   public static ISchematic getSchematic(BuilderProcess builderProcess) {
      return builderProcess.schematic;
   }

   public PathingCommand onTick(boolean flag, boolean flag2, int n) {
      if (n > 1000) {
         return new PathingCommand(null, PathingCommandType.SET_GOAL_AND_PATH);
      } else {
         this.approxPlaceable = this.approxPlaceable(36);
         if (this.baritone.getInputOverrideHandler2().isInputForcedDown(Input.CLICK_LEFT)) {
            this.ticks = 5;
         } else {
            this.ticks--;
         }

         this.baritone.getInputOverrideHandler2().clearAllKeys();
         if (this.paused) {
            return new PathingCommand(null, PathingCommandType.CANCEL_AND_SET_GOAL);
         } else {
            if ((Boolean)Baritone.settings().buildInLayers.value) {
               if (this.realSchematic == null) {
                  this.realSchematic = this.schematic;
               }

               ISchematic realSchematic2 = this.realSchematic;
               int n2;
               int n3;
               if ((Boolean)Baritone.settings().layerOrder.value) {
                  n3 = realSchematic2.heightY() - 1;
                  n2 = realSchematic2.heightY() - this.layer * (Integer)Baritone.settings().layerHeight.value;
               } else {
                  n3 = this.layer * (Integer)Baritone.settings().layerHeight.value - 1;
                  n2 = 0;
               }

               this.schematic = new BuilderProcess.DefaultSchematic(this, realSchematic2, n2, n3);
            }

            BuilderCalculationContext context = new BuilderCalculationContext(this);
            if (!this.recalc(context)) {
               if ((Boolean)Baritone.settings().buildInLayers.value && this.layer * (Integer)Baritone.settings().layerHeight.value < this.stopAtHeight) {
                  this.logDirect2("Starting layer " + this.layer);
                  this.layer++;
                  return this.onTick(flag, flag2, n + 1);
               } else {
                  Vec3i value = (Vec3i)Baritone.settings().buildRepeat.value;
                  int value2 = (Integer)Baritone.settings().buildRepeatCount.value;
                  this.numRepeats++;
                  if (value.equals(new Vec3i(0, 0, 0)) || value2 != -1 && this.numRepeats >= value2) {
                     this.logDirect2("Done building");
                     if ((Boolean)Baritone.settings().notificationOnBuildFinished.value) {
                        this.logNotification("Done building", false);
                     }

                     this.onLostControl();
                     return null;
                  } else {
                     this.layer = 0;
                     this.origin = new BlockPos(this.origin).add(value);
                     if (!(Boolean)Baritone.settings().buildRepeatSneaky.value) {
                        this.schematic.reset();
                     }

                     this.logDirect2("Repeating build in vector " + value + ", new origin is " + this.origin);
                     return this.onTick(flag, flag2, n + 1);
                  }
               }
            } else {
               if ((Boolean)Baritone.settings().distanceTrim.value) {
                  this.trim();
               }

               Optional optional = this.toBreakNearPlayer(context);
               if (optional.isPresent() && flag2 && this.ctx.player().onGround) {
                  Rotation second = (Rotation)((Tuple)optional.get()).getSecond();
                  BetterBlockPos first = (BetterBlockPos)((Tuple)optional.get()).getFirst();
                  this.baritone.getLookBehavior2().updateTarget(second, true);
                  MovementHelper.switchToBestToolFor(this.ctx, context.get(first));
                  if (this.ctx.player().isSneaking()) {
                     this.baritone.getInputOverrideHandler2().setInputForceState(Input.SNEAK, true);
                  }

                  if (this.ctx.isLookingAt(first) || this.ctx.playerRotations().isReallyCloseTo(second)) {
                     this.baritone.getInputOverrideHandler2().setInputForceState(Input.CLICK_LEFT, true);
                  }

                  return new PathingCommand(null, PathingCommandType.CANCEL_AND_SET_GOAL);
               } else {
                  ArrayList list = new ArrayList();
                  Optional optional2 = this.searchForPlaceables(context, list);
                  if (optional2.isPresent() && flag2 && this.ctx.player().onGround && this.ticks <= 0) {
                     Rotation rot = BuilderPlacement.getRot((BuilderPlacement)optional2.get());
                     this.baritone.getLookBehavior2().updateTarget(rot, true);
                     this.ctx.player().inventory.currentItem = BuilderPlacement.getHotbarSelection((BuilderPlacement)optional2.get());
                     this.baritone.getInputOverrideHandler2().setInputForceState(Input.SNEAK, true);
                     if (this.ctx.isLookingAt(BuilderPlacement.getPlaceAgainst((BuilderPlacement)optional2.get())) && this.ctx.objectMouseOver().sideHit.equals(BuilderPlacement.getSide((BuilderPlacement)optional2.get()))
                        || this.ctx.playerRotations().isReallyCloseTo(rot)) {
                        this.baritone.getInputOverrideHandler2().setInputForceState(Input.CLICK_RIGHT, true);
                     }

                     return new PathingCommand(null, PathingCommandType.CANCEL_AND_SET_GOAL);
                  } else {
                     if ((Boolean)Baritone.settings().allowInventory.value) {
                        ArrayList list2 = new ArrayList();
                        ArrayList list3 = new ArrayList();

                        outerLoop:
                        for (IBlockState state : (Iterable<IBlockState>)(list)) {
                           for (int j = 0; j < 9; j++) {
                              if (this.valid((IBlockState)this.approxPlaceable.get(j), state, true)) {
                                 list2.add(j);
                                 continue outerLoop;
                              }
                           }

                           list3.add(state);
                        }

                        outerLoop2:
                        for (int k = 9; k < 36; k++) {
                           for (IBlockState state2 : (Iterable<IBlockState>)(list3)) {
                              if (this.valid((IBlockState)this.approxPlaceable.get(k), state2, true)) {
                                 if (!this.baritone.getInventoryBehavior().swapWithHotBar(k, list2::contains)) {
                                    return new PathingCommand(null, PathingCommandType.REQUEST_PAUSE);
                                 }
                                 break outerLoop2;
                              }
                           }
                        }
                     }

                     Goal goal = this.assemble(context, this.approxPlaceable.subList(0, 9));
                     if (goal == null) {
                        goal = this.assemble(context, this.approxPlaceable, true);
                        if (goal == null) {
                           if ((Boolean)Baritone.settings().skipFailedLayers.value
                              && (Boolean)Baritone.settings().buildInLayers.value
                              && this.layer * (Integer)Baritone.settings().layerHeight.value < this.realSchematic.heightY()) {
                              this.logDirect2("Skipping layer that I cannot construct! Layer #" + this.layer);
                              this.layer++;
                              return this.onTick(flag, flag2, n + 1);
                           }

                           this.logDirect2("Unable to do it. Pausing. resume to resume, cancel to cancel");
                           this.paused = true;
                           return new PathingCommand(null, PathingCommandType.REQUEST_PAUSE);
                        }
                     }

                     return new PathingCommandContext(goal, PathingCommandType.FORCE_REVALIDATE_GOAL_AND_PATH, context);
                  }
               }
            }
         }
      }
   }

   @Override
   public void build2(String str, ISchematic schematic2, Vec3i vec) {
      this.name = str;
      this.schematic = schematic2;
      this.realSchematic = null;
      boolean flag = schematic2 instanceof SelectionSchematic;
      if (!((Map)Baritone.settings().buildSubstitutes.value).isEmpty()) {
         this.schematic = new SubstituteSchematic(this.schematic, (Map)Baritone.settings().buildSubstitutes.value);
      }

      int x = vec.getX();
      int y = vec.getY();
      int z = vec.getZ();
      if ((Boolean)Baritone.settings().schematicOrientationX.value) {
         x += schematic2.widthX();
      }

      if ((Boolean)Baritone.settings().schematicOrientationY.value) {
         y += schematic2.heightY();
      }

      if ((Boolean)Baritone.settings().schematicOrientationZ.value) {
         z += schematic2.lengthZ();
      }

      this.origin = new Vec3i(x, y, z);
      this.paused = false;
      this.layer = (Integer)Baritone.settings().startAtLayer.value;
      this.stopAtHeight = schematic2.heightY();
      if ((Boolean)Baritone.settings().buildOnlySelection.value && flag) {
         if (this.baritone.getSelectionManager2().getSelections().length == 0) {
            this.logDirect2("Poor little kitten forgot to set a selection while BuildOnlySelection is true");
            this.stopAtHeight = 0;
         } else if ((Boolean)Baritone.settings().buildInLayers.value) {
            OptionalInt optionalInt = Stream.of(this.baritone.getSelectionManager2().getSelections()).mapToInt(selection -> selection.min().y2).min();
            OptionalInt optionalInt2 = Stream.of(this.baritone.getSelectionManager2().getSelections()).mapToInt(selection2 -> selection2.max().y2).max();
            if (optionalInt.isPresent() && optionalInt2.isPresent()) {
               int n = (Boolean)Baritone.settings().layerOrder.value ? y + schematic2.heightY() - optionalInt2.getAsInt() : optionalInt.getAsInt() - y;
               this.stopAtHeight = ((Boolean)Baritone.settings().layerOrder.value ? y + schematic2.heightY() - optionalInt.getAsInt() : optionalInt2.getAsInt() - y) + 1;
               this.layer = Math.max(this.layer, n / (Integer)Baritone.settings().layerHeight.value);
               this.logDebug(String.format("Schematic starts at y=%s with height %s", y, schematic2.heightY()));
               this.logDebug(String.format("Selection starts at y=%s and ends at y=%s", optionalInt.getAsInt(), optionalInt2.getAsInt()));
               this.logDebug(String.format("Considering relevant height %s - %s", n, this.stopAtHeight));
            }
         }
      }

      this.numRepeats = 0;
      this.observedCompleted = new LongOpenHashSet();
      this.incorrectPositions = null;
   }

   private Optional searchForPlaceables(BuilderCalculationContext context, List list) {
      BetterBlockPos betterBlockPos = this.ctx.playerFeet();

      for (int j = -5; j <= 5; j++) {
         for (int k = -5; k <= 1; k++) {
            for (int m = -5; m <= 5; m++) {
               int n = betterBlockPos.x2 + j;
               int n2 = betterBlockPos.y2 + k;
               int n3 = betterBlockPos.z2 + m;
               IBlockState schematicState = BuilderCalculationContext.getSchematicState(context, n, n2, n3, context.bsi.get0(n, n2, n3));
               if (schematicState != null) {
                  IBlockState state = context.bsi.get0(n, n2, n3);
                  if (MovementHelper.isReplaceable(n, n2, n3, state, context.bsi)
                     && !this.valid(state, schematicState, false)
                     && (k != 1 || context.bsi.get0(n, n2 + 1, n3).getBlock() != Blocks.air)) {
                     list.add(schematicState);
                     Optional optional = this.possibleToPlace(schematicState, n, n2, n3, context.bsi);
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

   private Goal assemble(BuilderCalculationContext context, List list) {
      return this.assemble(context, list, false);
   }

   @Override
   public List getApproxPlaceable() {
      return new ArrayList(this.approxPlaceable);
   }

   @Override
   public boolean isPaused() {
      return this.paused;
   }

   private Goal breakGoal(BlockPos pos, BuilderCalculationContext context) {
      return (Goal)((Boolean)Baritone.settings().goalBreakFromAbove.value
            && context.bsi.get0(pos.up()).getBlock() instanceof BlockAir
            && context.bsi.get0(pos.up(2)).getBlock() instanceof BlockAir
         ? new JankyCompositeGoal(new GoalBreak(pos), new BuilderProcess.DefaultGoalGetToBlock(this, pos.up()))
         : new GoalBreak(pos));
   }

   private Optional toBreakNearPlayer(BuilderCalculationContext context) {
      BetterBlockPos betterBlockPos = this.ctx.playerFeet();
      BetterBlockPos betterBlockPos2 = this.baritone.getPathingBehavior2().pathStart();

      for (int j = -5; j <= 5; j++) {
         for (int k = (Boolean)Baritone.settings().breakFromAbove.value ? -1 : 0; k <= 5; k++) {
            for (int m = -5; m <= 5; m++) {
               int n = betterBlockPos.x2 + j;
               int n2 = betterBlockPos.y2 + k;
               int n3 = betterBlockPos.z2 + m;
               if (k != -1 || n != betterBlockPos2.x2 || n3 != betterBlockPos2.z2) {
                  IBlockState schematicState = BuilderCalculationContext.getSchematicState(context, n, n2, n3, context.bsi.get0(n, n2, n3));
                  if (schematicState != null) {
                     IBlockState state = context.bsi.get0(n, n2, n3);
                     if (state.getBlock() != Blocks.air && !(state.getBlock() instanceof BlockLiquid) && !this.valid(state, schematicState, false)) {
                        BetterBlockPos betterBlockPos3 = new BetterBlockPos(n, n2, n3);
                        Optional optional = RotationUtils.reachable3(this.ctx, betterBlockPos3, this.ctx.playerController().getBlockReachDistance());
                        if (optional.isPresent()) {
                           return Optional.of(new Tuple(betterBlockPos3, optional.get()));
                        }
                     }
                  }
               }
            }
         }
      }

      return Optional.empty();
   }

   @Override
   public PathingCommand onTick(boolean flag, boolean flag2) {
      return this.onTick(flag, flag2, 0);
   }

   @Override
   public void resume() {
      this.paused = false;
   }

   @Override
   public String displayName0() {
      return this.paused ? "Builder Paused" : "Building " + this.name;
   }

   private void trim() {
      HashSet set = new HashSet(this.incorrectPositions);
      set.removeIf(obj -> ((BlockPos)obj).distanceSq(this.ctx.player().posX, this.ctx.player().posY, this.ctx.player().posZ) > 200.0);
      if (!set.isEmpty()) {
         this.incorrectPositions = set;
      }
   }

   private OptionalInt hasAnyItemThatWouldPlace(IBlockState state, MovingObjectPosition hit, Rotation rotation) {
      for (int j = 0; j < 9; j++) {
         ItemStack stack = this.ctx.player().inventory.mainInventory[j];
         if (stack != null && stack.getItem() instanceof ItemBlock) {
            float rotationYaw = this.ctx.player().rotationYaw;
            float rotationPitch = this.ctx.player().rotationPitch;
            this.ctx.player().rotationYaw = rotation.getYaw();
            this.ctx.player().rotationPitch = rotation.getPitch();
            IBlockState state2 = ((ItemBlock)stack.getItem())
               .getBlock()
               .onBlockPlaced(
                  this.ctx.world(),
                  hit.getBlockPos().offset(hit.sideHit),
                  hit.sideHit,
                  (float)hit.hitVec.xCoord - (float)hit.getBlockPos().getX(),
                  (float)hit.hitVec.yCoord - (float)hit.getBlockPos().getY(),
                  (float)hit.hitVec.zCoord - (float)hit.getBlockPos().getZ(),
                  stack.getItem().getMetadata(stack.getMetadata()),
                  this.ctx.player()
               );
            this.ctx.player().rotationYaw = rotationYaw;
            this.ctx.player().rotationPitch = rotationPitch;
            if (this.valid(state2, state, true)) {
               return OptionalInt.of(j);
            }
         }
      }

      return OptionalInt.empty();
   }

   @Override
   public boolean build(String str, File file, Vec3i vec) {
      Optional byFile = SchematicSystem.INSTANCE.getByFile(file);
      if (!byFile.isPresent()) {
         return false;
      } else {
         IStaticSchematic staticSchematic;
         try {
            staticSchematic = ((ISchematicFormat)byFile.get()).parse(new FileInputStream(file));
         } catch (Exception e2) {
            e2.printStackTrace();
            return false;
         }

         this.build2(str, this.applyMapArtAndSelection(vec, staticSchematic), vec);
         return true;
      }
   }

   public static Vec3i getOrigin(BuilderProcess builderProcess) {
      return builderProcess.origin;
   }

   private void recalcNearby(BuilderCalculationContext context) {
      BetterBlockPos betterBlockPos = this.ctx.playerFeet();
      int value = (Integer)Baritone.settings().builderTickScanRadius.value;

      for (int j = -value; j <= value; j++) {
         for (int k = -value; k <= value; k++) {
            for (int m = -value; m <= value; m++) {
               int n = betterBlockPos.x2 + j;
               int n2 = betterBlockPos.y2 + k;
               int n3 = betterBlockPos.z2 + m;
               IBlockState schematicState = BuilderCalculationContext.getSchematicState(context, n, n2, n3, context.bsi.get0(n, n2, n3));
               if (schematicState != null) {
                  BetterBlockPos betterBlockPos2 = new BetterBlockPos(n, n2, n3);
                  if (this.valid(context.bsi.get0(n, n2, n3), schematicState, false)) {
                     this.incorrectPositions.remove(betterBlockPos2);
                     this.observedCompleted.add(BetterBlockPos.longHash(betterBlockPos2));
                  } else {
                     this.incorrectPositions.add(betterBlockPos2);
                     this.observedCompleted.remove(BetterBlockPos.longHash(betterBlockPos2));
                  }
               }
            }
         }
      }
   }

   private boolean valid(IBlockState state, IBlockState state2, boolean flag) {
      if (state2 == null) {
         return true;
      } else if (state.getBlock() instanceof BlockLiquid && (Boolean)Baritone.settings().okIfWater.value) {
         return true;
      } else if (state.getBlock() instanceof BlockAir && ((List)Baritone.settings().okIfAir.value).contains(state2.getBlock())) {
         return true;
      } else if (state2.getBlock() instanceof BlockAir && ((List)Baritone.settings().buildIgnoreBlocks.value).contains(state.getBlock())) {
         return true;
      } else if (!(state.getBlock() instanceof BlockAir) && (Boolean)Baritone.settings().buildIgnoreExisting.value && !flag) {
         return true;
      } else if (((List)Baritone.settings().buildSkipBlocks.value).contains(state2.getBlock()) && !flag) {
         return true;
      } else if (((List)((Map)Baritone.settings().buildValidSubstitutes.value).getOrDefault(state2.getBlock(), Collections.emptyList())).contains(state.getBlock()) && !flag) {
         return true;
      } else {
         return state.equals(state2) ? true : state.equals(state2);
      }
   }

   private Goal placementGoal(BlockPos pos, BuilderCalculationContext context) {
      if (this.ctx.world().getBlockState(pos).getBlock() != Blocks.air) {
         return new GoalPlace(pos);
      } else {
         boolean flag = this.ctx.world().getBlockState(pos.up()).getBlock() != Blocks.air;
         IBlockState blockState = this.ctx.world().getBlockState(pos);

         for (EnumFacing facing : Movement.HORIZONTALS_BUT_ALSO_DOWN_____SO_EVERY_DIRECTION_EXCEPT_UP) {
            if (MovementHelper.canPlaceAgainst(this.ctx, pos.offset(facing))
               && this.ctx
                  .world()
                  .canBlockBePlaced(BuilderCalculationContext.getSchematicState(context, pos.getX(), pos.getY(), pos.getZ(), blockState).getBlock(), pos, false, facing, null, null)) {
               return new GoalAdjacent(pos, pos.offset(facing), flag);
            }
         }

         return new GoalPlace(pos);
      }
   }

   @Override
   public void pause() {
      this.paused = true;
   }

   public static List getApproxPlaceableList(BuilderProcess builderProcess) {
      return builderProcess.approxPlaceable;
   }

   @Override
   public void onLostControl() {
      this.incorrectPositions = null;
      this.name = null;
      this.schematic = null;
      this.realSchematic = null;
      this.layer = (Integer)Baritone.settings().startAtLayer.value;
      this.numRepeats = 0;
      this.paused = false;
      this.observedCompleted = null;
   }

   private Optional possibleToPlace(IBlockState state, int n, int n2, int n3, BlockStateInterface blockStateInterface) {
      for (EnumFacing facing : EnumFacing.values()) {
         BetterBlockPos betterBlockPos = new BetterBlockPos(n, n2, n3).offset2(facing);
         IBlockState state2 = blockStateInterface.get0(betterBlockPos);
         if (!MovementHelper.isReplaceable(betterBlockPos.x2, betterBlockPos.y2, betterBlockPos.z2, state2, blockStateInterface)
            && this.ctx.world().canBlockBePlaced(state.getBlock(), new BetterBlockPos(n, n2, n3), false, facing, null, null)) {
            AxisAlignedBB selectedBoundingBox = state2.getBlock().getSelectedBoundingBox(this.ctx.world(), betterBlockPos);

            for (Vec3 vec : aabbSideMultipliers(facing)) {
               double d = (double)betterBlockPos.x2 + (selectedBoundingBox.minX - (double)betterBlockPos.x2) * vec.xCoord + (selectedBoundingBox.maxX - (double)betterBlockPos.x2) * (1.0 - vec.xCoord);
               double d2 = (double)betterBlockPos.y2 + (selectedBoundingBox.minY - (double)betterBlockPos.y2) * vec.yCoord + (selectedBoundingBox.maxY - (double)betterBlockPos.y2) * (1.0 - vec.yCoord);
               double d3 = (double)betterBlockPos.z2 + (selectedBoundingBox.minZ - (double)betterBlockPos.z2) * vec.zCoord + (selectedBoundingBox.maxZ - (double)betterBlockPos.y2) * (1.0 - vec.zCoord);
               Rotation rotation = RotationUtils.calcRotationFromVec3d(RayTraceUtils.inferSneakingEyePosition(this.ctx.player()), new Vec3(d, d2, d3), this.ctx.playerRotations());
               Rotation rotation2 = this.baritone.getLookBehavior2().getAimProcessor().peekRotation(rotation);
               MovingObjectPosition hit = RayTraceUtils.rayTraceTowards(this.ctx.player(), rotation2, this.ctx.playerController().getBlockReachDistance(), true);
               if (hit != null && hit.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK && hit.getBlockPos().equals(betterBlockPos) && hit.sideHit == facing.getOpposite()) {
                  OptionalInt optionalInt = this.hasAnyItemThatWouldPlace(state, hit, rotation2);
                  if (optionalInt.isPresent()) {
                     return Optional.of(new BuilderPlacement(optionalInt.getAsInt(), betterBlockPos, facing.getOpposite(), rotation));
                  }
               }
            }
         }
      }

      return Optional.empty();
   }

public static class DefaultSchematic implements ISchematic {
      public final ISchematic schematic;
      public final int minY;
      public final int maxY;
      public final BuilderProcess builderProcess;

      @Override
      public boolean inSchematic(int n, int n2, int n3, IBlockState state) {
         return ISchematic.super.inSchematic(n, n2, n3, state) && n2 >= this.minY && n2 <= this.maxY && this.schematic.inSchematic(n, n2, n3, state);
      }

      @Override
      public int lengthZ() {
         return this.schematic.lengthZ();
      }

      public DefaultSchematic(BuilderProcess builderProcess2, ISchematic schematic2, int n, int n2) {
         this.builderProcess = builderProcess2;
         this.schematic = schematic2;
         this.minY = n;
         this.maxY = n2;
      }

      @Override
      public IBlockState desiredState(int n, int n2, int n3, IBlockState state, List list) {
         return this.schematic.desiredState(n, n2, n3, state, BuilderProcess.getApproxPlaceableList(this.builderProcess));
      }

      @Override
      public int widthX() {
         return this.schematic.widthX();
      }

      @Override
      public int heightY() {
         return this.schematic.heightY();
      }

      @Override
      public void reset() {
         this.schematic.reset();
      }
   }

   public static class DefaultGoalGetToBlock extends GoalGetToBlock {
      public final BuilderProcess builderProcess;

      @Override
      public boolean isInGoal(int n, int n2, int n3) {
         return n2 <= this.y && (n != this.x || n2 != this.y || n3 != this.z) ? super.isInGoal(n, n2, n3) : false;
      }

      public DefaultGoalGetToBlock(BuilderProcess builderProcess2, BlockPos pos) {
         super(pos);
         this.builderProcess = builderProcess2;
      }
   }

}
