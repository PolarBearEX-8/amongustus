package baritone.command.defaults;

import baritone.Baritone;
import baritone.api.event.events.RenderEvent;
import baritone.api.event.listener.AbstractGameEventListener;
import baritone.utils.IRenderer;
import baritone.utils.schematic.StaticSchematic;
import java.awt.Color;
import java.util.HashSet;
import net.minecraft.util.AxisAlignedBB;
import baritone.api.IBaritone;
import baritone.api.command.Command;
import baritone.api.command.datatypes.ForAxis;
import baritone.api.command.datatypes.ForBlockOptionalMeta;
import baritone.api.command.datatypes.ForEnumFacing;
import baritone.api.command.datatypes.RelativeBlockPos;
import baritone.api.command.exception.CommandException;
import baritone.api.command.exception.CommandInvalidStateException;
import baritone.api.command.exception.CommandInvalidTypeException;
import baritone.api.command.helpers.TabCompleteHelper;
import baritone.api.schematic.CompositeSchematic;
import baritone.api.schematic.FillSchematic;
import baritone.api.schematic.ISchematic;
import baritone.api.schematic.MaskSchematic;
import baritone.api.schematic.ReplaceSchematic;
import baritone.api.schematic.ShellSchematic;
import baritone.api.schematic.WallsSchematic;
import baritone.api.selection.ISelection;
import baritone.api.selection.ISelectionManager;
import baritone.api.utils.BetterBlockPos;
import baritone.api.utils.BlockOptionalMeta;
import baritone.api.utils.BlockOptionalMetaLookup;
import baritone.api.command.argument.IArgConsumer;
import baritone.api.schematic.mask.shape.CylinderMask;
import baritone.api.schematic.mask.shape.SphereMask;
import baritone.utils.BlockStateInterface;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.UnaryOperator;
import java.util.stream.Stream;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.Vec3i;

public class SelCommand extends Command {
   private ISelectionManager manager = this.baritone.getSelectionManager();
   private BetterBlockPos pos1 = null;
   private ISchematic clipboard = null;
   private Vec3i clipboardOffset = null;

   @Override
   public Stream tabComplete(String str, IArgConsumer argConsumer) throws CommandException {
      if (argConsumer.hasExactlyOne()) {
         return new TabCompleteHelper().append2(SelCommand.Action.getAllNames()).filterPrefix(argConsumer.getString()).sortAlphabetically().getStream();
      } else {
         SelCommand.Action byName = SelCommand.Action.getByName(argConsumer.getString());
         if (byName != null) {
            if (byName != SelCommand.Action.POS1 && byName != SelCommand.Action.POS2) {
               if (byName.isFillAction()) {
                  if (argConsumer.hasExactlyOne() || byName == SelCommand.Action.REPLACE) {
                     while (argConsumer.has(2)) {
                        argConsumer.get();
                     }

                     return argConsumer.tabCompleteDatatype(ForBlockOptionalMeta.INSTANCE);
                  }

                  if (argConsumer.hasExactly(2) && (byName == SelCommand.Action.CYLINDER || byName == SelCommand.Action.HCYLINDER)) {
                     argConsumer.get();
                     return argConsumer.tabCompleteDatatype(ForAxis.INSTANCE);
                  }
               } else if (byName == SelCommand.Action.EXPAND || byName == SelCommand.Action.CONTRACT || byName == SelCommand.Action.SHIFT) {
                  if (argConsumer.hasExactlyOne()) {
                     return new TabCompleteHelper().append2(SelTransformTarget.getAllNames()).filterPrefix(argConsumer.getString()).sortAlphabetically().getStream();
                  }

                  if (SelTransformTarget.getByName(argConsumer.getString()) != null && argConsumer.hasExactlyOne()) {
                     return argConsumer.tabCompleteDatatype(ForEnumFacing.INSTANCE);
                  }
               }
            } else if (argConsumer.hasAtMost(3)) {
               return argConsumer.tabCompleteDatatype(RelativeBlockPos.INSTANCE);
            }
         }

         return Stream.empty();
      }
   }

   public static BetterBlockPos getPos1(SelCommand selCommand) {
      return selCommand.pos1;
   }

   @Override
   public void execute(String str, IArgConsumer argConsumer) throws CommandException {
      SelCommand.Action byName = SelCommand.Action.getByName(argConsumer.getString());
      if (byName == null) {
         throw new CommandInvalidTypeException(argConsumer.consumed(), "an action");
      } else {
         if (byName != SelCommand.Action.POS1 && byName != SelCommand.Action.POS2) {
            if (byName == SelCommand.Action.CLEAR) {
               argConsumer.requireMax(0);
               this.pos1 = null;
               this.logDirect2(String.format("Removed %d selections", this.manager.removeAllSelections().length));
            } else if (byName == SelCommand.Action.UNDO) {
               argConsumer.requireMax(0);
               if (this.pos1 != null) {
                  this.pos1 = null;
                  this.logDirect2("Undid pos1");
               } else {
                  ISelection[] selections = this.manager.getSelections();
                  if (selections.length < 1) {
                     throw new CommandInvalidStateException("Nothing to undo!");
                  }

                  this.pos1 = this.manager.removeSelection(selections[selections.length - 1]).pos1();
                  this.logDirect2("Undid pos2");
               }
            } else if (byName.isFillAction()) {
               BlockOptionalMeta blockOptionalMeta = byName == SelCommand.Action.CLEARAREA
                  ? new BlockOptionalMeta(Blocks.air)
                  : (BlockOptionalMeta)argConsumer.getDatatypeFor(ForBlockOptionalMeta.INSTANCE);
               EnumFacing.Axis axis;
               BlockOptionalMetaLookup blockOptionalMetaLookup;
               if (byName != SelCommand.Action.REPLACE) {
                  if (byName != SelCommand.Action.CYLINDER && byName != SelCommand.Action.HCYLINDER) {
                     argConsumer.requireMax(0);
                     blockOptionalMetaLookup = null;
                     axis = null;
                  } else {
                     argConsumer.requireMax(1);
                     axis = argConsumer.hasAny() ? (EnumFacing.Axis)argConsumer.getDatatypeFor(ForAxis.INSTANCE) : EnumFacing.Axis.Y;
                     blockOptionalMetaLookup = null;
                  }
               } else {
                  argConsumer.requireMin(1);
                  ArrayList list = new ArrayList();
                  list.add(blockOptionalMeta);

                  while (argConsumer.has(2)) {
                     list.add(argConsumer.getDatatypeFor(ForBlockOptionalMeta.INSTANCE));
                  }

                  blockOptionalMeta = (BlockOptionalMeta)argConsumer.getDatatypeFor(ForBlockOptionalMeta.INSTANCE);
                  blockOptionalMetaLookup = new BlockOptionalMetaLookup((BlockOptionalMeta[])list.toArray(new BlockOptionalMeta[0]));
                  axis = null;
               }

               ISelection[] selections2 = this.manager.getSelections();
               if (selections2.length == 0) {
                  throw new CommandInvalidStateException("No selections");
               }

               BetterBlockPos betterBlockPos = selections2[0].min();
               CompositeSchematic compositeSchematic = new CompositeSchematic(0, 0, 0);

               for (ISelection selection : selections2) {
                  BetterBlockPos betterBlockPos2 = selection.min();
                  betterBlockPos = new BetterBlockPos(Math.min(betterBlockPos.x2, betterBlockPos2.x2), Math.min(betterBlockPos.y2, betterBlockPos2.y2), Math.min(betterBlockPos.z2, betterBlockPos2.z2));
               }

               for (ISelection selection2 : selections2) {
                  Vec3i size = selection2.size();
                  BetterBlockPos betterBlockPos3 = selection2.min();
                  EnumFacing.Axis axis2 = axis;
                  BlockOptionalMetaLookup blockOptionalMetaLookup2 = blockOptionalMetaLookup;
                  UnaryOperator unaryOperator = obj -> {
                     int n = ((ISchematic)obj).widthX();
                     int n2 = ((ISchematic)obj).heightY();
                     int n3 = ((ISchematic)obj).lengthZ();
                     switch (byName) {
                        case WALLS:
                           return new WallsSchematic((ISchematic)obj);
                        case SHELL:
                           return new ShellSchematic((ISchematic)obj);
                        case REPLACE:
                           return new ReplaceSchematic((ISchematic)obj, blockOptionalMetaLookup2);
                        case SPHERE:
                           return MaskSchematic.create((ISchematic)obj, new SphereMask(n, n2, n3, true).compute());
                        case HSPHERE:
                           return MaskSchematic.create((ISchematic)obj, new SphereMask(n, n2, n3, false).compute());
                        case CYLINDER:
                           return MaskSchematic.create((ISchematic)obj, new CylinderMask(n, n2, n3, true, axis2).compute());
                        case HCYLINDER:
                           return MaskSchematic.create((ISchematic)obj, new CylinderMask(n, n2, n3, false, axis2).compute());
                        default:
                           return obj;
                     }
                  };
                  compositeSchematic.put((ISchematic)(unaryOperator.apply(new FillSchematic(size.getX(), size.getY(), size.getZ(), blockOptionalMeta))), betterBlockPos3.x2 - betterBlockPos.x2, betterBlockPos3.y2 - betterBlockPos.y2, betterBlockPos3.z2 - betterBlockPos.z2);
               }

               this.baritone.getBuilderProcess().build2("Fill", compositeSchematic, betterBlockPos);
               this.logDirect2("Filling now");
            } else if (byName == SelCommand.Action.COPY) {
               BetterBlockPos betterBlockPos4 = this.ctx.viewerPos();
               BetterBlockPos betterBlockPos5 = argConsumer.hasAny() ? (BetterBlockPos)argConsumer.getDatatypePost(RelativeBlockPos.INSTANCE, betterBlockPos4) : betterBlockPos4;
               argConsumer.requireMax(0);
               ISelection[] selections3 = this.manager.getSelections();
               if (selections3.length < 1) {
                  throw new CommandInvalidStateException("No selections");
               }

               BlockStateInterface blockStateInterface = new BlockStateInterface(this.ctx);
               BetterBlockPos betterBlockPos6 = selections3[0].min();
               CompositeSchematic compositeSchematic2 = new CompositeSchematic(0, 0, 0);

               for (ISelection selection3 : selections3) {
                  BetterBlockPos betterBlockPos7 = selection3.min();
                  betterBlockPos6 = new BetterBlockPos(Math.min(betterBlockPos6.x2, betterBlockPos7.x2), Math.min(betterBlockPos6.y2, betterBlockPos7.y2), Math.min(betterBlockPos6.z2, betterBlockPos7.z2));
               }

               for (ISelection selection4 : selections3) {
                  Vec3i size2 = selection4.size();
                  BetterBlockPos betterBlockPos8 = selection4.min();
                  IBlockState[][][] statesArrs = new IBlockState[size2.getX()][size2.getZ()][size2.getY()];

                  for (int j = 0; j < size2.getX(); j++) {
                     for (int k = 0; k < size2.getY(); k++) {
                        for (int m = 0; m < size2.getZ(); m++) {
                           statesArrs[j][m][k] = blockStateInterface.get0(betterBlockPos8.x2 + j, betterBlockPos8.y2 + k, betterBlockPos8.z2 + m);
                        }
                     }
                  }

                  compositeSchematic2.put(new SelCommand.DefaultStaticSchematic(this, statesArrs, size2), betterBlockPos8.x2 - betterBlockPos6.x2, betterBlockPos8.y2 - betterBlockPos6.y2, betterBlockPos8.z2 - betterBlockPos6.z2);
               }

               this.clipboard = compositeSchematic2;
               this.clipboardOffset = betterBlockPos6.subtract(betterBlockPos5);
               this.logDirect2("Selection copied");
            } else if (byName == SelCommand.Action.PASTE) {
               BetterBlockPos betterBlockPos9 = this.ctx.viewerPos();
               BetterBlockPos betterBlockPos10 = argConsumer.hasAny() ? (BetterBlockPos)argConsumer.getDatatypePost(RelativeBlockPos.INSTANCE, betterBlockPos9) : betterBlockPos9;
               argConsumer.requireMax(0);
               if (this.clipboard == null) {
                  throw new CommandInvalidStateException("You need to copy a selection first");
               }

               this.baritone.getBuilderProcess().build2("Fill", this.clipboard, betterBlockPos10.add(this.clipboardOffset));
               this.logDirect2("Building now");
            } else if (byName == SelCommand.Action.EXPAND || byName == SelCommand.Action.CONTRACT || byName == SelCommand.Action.SHIFT) {
               argConsumer.requireExactly(3);
               SelTransformTarget byName2 = SelTransformTarget.getByName(argConsumer.getString());
               if (byName2 == null) {
                  throw new CommandInvalidStateException("Invalid transform type");
               }

               EnumFacing datatypeFor = (EnumFacing)argConsumer.getDatatypeFor(ForEnumFacing.INSTANCE);
               int as = (Integer)argConsumer.getAs(Integer.class);
               ISelection[] selections4 = this.manager.getSelections();
               if (selections4.length < 1) {
                  throw new CommandInvalidStateException("No selections found");
               }

               selections4 = byName2.transform(selections4);

               for (ISelection selection5 : selections4) {
                  if (byName == SelCommand.Action.EXPAND) {
                     this.manager.expand(selection5, datatypeFor, as);
                  } else if (byName == SelCommand.Action.CONTRACT) {
                     this.manager.contract(selection5, datatypeFor, as);
                  } else {
                     this.manager.shift(selection5, datatypeFor, as);
                  }
               }

               this.logDirect2(String.format("Transformed %d selections", selections4.length));
            }
         } else {
            if (byName == SelCommand.Action.POS2 && this.pos1 == null) {
               throw new CommandInvalidStateException("Set pos1 first before using pos2");
            }

            BetterBlockPos betterBlockPos11 = this.ctx.viewerPos();
            BetterBlockPos betterBlockPos12 = argConsumer.hasAny() ? (BetterBlockPos)argConsumer.getDatatypePost(RelativeBlockPos.INSTANCE, betterBlockPos11) : betterBlockPos11;
            argConsumer.requireMax(0);
            if (byName == SelCommand.Action.POS1) {
               this.pos1 = betterBlockPos12;
               this.logDirect2("Position 1 has been set");
            } else {
               this.manager.addSelection(this.pos1, betterBlockPos12);
               this.pos1 = null;
               this.logDirect2("Selection added");
            }
         }
      }
   }

   @Override
   public String getShortDesc() {
      return "WorldEdit-like commands";
   }

   @Override
   public List getLongDesc() {
      return Arrays.asList(
         "The sel command allows you to manipulate Baritone's selections, similarly to WorldEdit.",
         "",
         "Using these selections, you can clear areas, fill them with blocks, or something else.",
         "",
         "The expand/contract/shift commands use a kind of selector to choose which selections to target. Supported ones are a/all, n/newest, and o/oldest.",
         "",
         "Usage:",
         "> sel pos1/p1/1 - Set position 1 to your current position.",
         "> sel pos1/p1/1 <x> <y> <z> - Set position 1 to a relative position.",
         "> sel pos2/p2/2 - Set position 2 to your current position.",
         "> sel pos2/p2/2 <x> <y> <z> - Set position 2 to a relative position.",
         "",
         "> sel clear/c - Clear the selection.",
         "> sel undo/u - Undo the last action (setting positions, creating selections, etc.)",
         "> sel set/fill/s/f [block] - Completely fill all selections with a block.",
         "> sel walls/w [block] - Fill in the walls of the selection with a specified block.",
         "> sel shell/shl [block] - The same as walls, but fills in a ceiling and floor too.",
         "> sel sphere/sph [block] - Fills the selection with a sphere bounded by the sides.",
         "> sel hsphere/hsph [block] - The same as sphere, but hollow.",
         "> sel cylinder/cyl [block] <axis> - Fills the selection with a cylinder bounded by the sides, oriented about the given axis. (default=y)",
         "> sel hcylinder/hcyl [block] <axis> - The same as cylinder, but hollow.",
         "> sel cleararea/ca - Basically 'set air'.",
         "> sel replace/r <blocks...> <with> - Replaces blocks with another block.",
         "> sel copy/cp <x> <y> <z> - Copy the selected area relative to the specified or your position.",
         "> sel paste/p <x> <y> <z> - Build the copied area relative to the specified or your position.",
         "",
         "> sel expand <target> <direction> <blocks> - Expand the targets.",
         "> sel contract <target> <direction> <blocks> - Contract the targets.",
         "> sel shift <target> <direction> <blocks> - Shift the targets (does not resize)."
      );
   }

   public SelCommand(IBaritone baritone2) {
      super(baritone2, new String[]{"sel", "selection", "s"});
      baritone2.getGameEventHandler().registerEventListener(new SelCommand.DefaultAbstractGameEventListener(this));
   }

public static class DefaultAbstractGameEventListener implements AbstractGameEventListener {
      public final SelCommand parent;

      @Override
      public void onRenderPass(RenderEvent renderEvent) {
         if ((Boolean)Baritone.settings().renderSelectionCorners.value && SelCommand.getPos1(this.parent) != null) {
            Color value = (Color)Baritone.settings().colorSelectionPos1.value;
            float value2 = (Float)Baritone.settings().selectionOpacity.value;
            float value3 = (Float)Baritone.settings().selectionLineWidth.value;
            boolean value4 = (Boolean)Baritone.settings().renderSelectionIgnoreDepth.value;
            IRenderer.startLines(value, value2, value3, value4);
            IRenderer.emitAABB(new AxisAlignedBB(SelCommand.getPos1(this.parent), SelCommand.getPos1(this.parent).add(1, 1, 1)));
            IRenderer.endLines(value4);
         }
      }

      public DefaultAbstractGameEventListener(SelCommand selCommand) {
         this.parent = selCommand;
      }
   }

   public static class DefaultStaticSchematic extends StaticSchematic {
      public final IBlockState[][][] blocks;
      public final Vec3i size;
      public final SelCommand parent;

      public DefaultStaticSchematic(SelCommand selCommand, IBlockState[][][] statesArrs, Vec3i vec) {
         this.parent = selCommand;
         this.blocks = statesArrs;
         this.size = vec;
         this.states = this.blocks;
         this.x = this.size.getX();
         this.y = this.size.getY();
         this.z = this.size.getZ();
      }
   }

public enum Action {
      POS1(new String[]{"pos1", "p1", "1"}),
      POS2(new String[]{"pos2", "p2", "2"}),
      CLEAR(new String[]{"clear", "c"}),
      UNDO(new String[]{"undo", "u"}),
      SET(new String[]{"set", "fill", "s", "f"}),
      WALLS(new String[]{"walls", "w"}),
      SHELL(new String[]{"shell", "shl"}),
      SPHERE(new String[]{"sphere", "sph"}),
      HSPHERE(new String[]{"hsphere", "hsph"}),
      CYLINDER(new String[]{"cylinder", "cyl"}),
      HCYLINDER(new String[]{"hcylinder", "hcyl"}),
      CLEARAREA(new String[]{"cleararea", "ca"}),
      REPLACE(new String[]{"replace", "r"}),
      EXPAND(new String[]{"expand", "ex"}),
      COPY(new String[]{"copy", "cp"}),
      PASTE(new String[]{"paste", "p"}),
      CONTRACT(new String[]{"contract", "ct"}),
      SHIFT(new String[]{"shift", "sh"});
      private final String[] names;
      public final boolean isFillAction() {
         return this == SET
            || this == WALLS
            || this == SHELL
            || this == SPHERE
            || this == HSPHERE
            || this == CYLINDER
            || this == HCYLINDER
            || this == CLEARAREA
            || this == REPLACE;
      }

      public static String[] getAllNames() {
         HashSet set = new HashSet();

         for (Action action : values()) {
            set.addAll(Arrays.asList(action.names));
         }

         return (String[])set.toArray(new String[0]);
      }

      private Action(String[] strings) {
         this.names = strings;
      }

      public static Action getByName(String str) {
         for (Action action : values()) {
            for (String str2 : action.names) {
               if (str2.equalsIgnoreCase(str)) {
                  return action;
               }
            }
         }

         return null;
      }

   }
}
