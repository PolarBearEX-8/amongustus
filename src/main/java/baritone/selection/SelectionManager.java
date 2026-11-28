package baritone.selection;

import baritone.Baritone;
import baritone.api.selection.ISelection;
import baritone.api.selection.ISelectionManager;
import baritone.api.utils.BetterBlockPos;
import java.util.LinkedList;
import java.util.ListIterator;
import net.minecraft.util.EnumFacing;

public class SelectionManager implements ISelectionManager {
   private final LinkedList selections = new LinkedList();
   private ISelection[] selectionsArr = new ISelection[0];

   public SelectionManager(Baritone baritone) {
      new SelectionRenderer(baritone, this);
   }

   @Override
   public synchronized ISelection getOnlySelection() {
      return this.selections.size() == 1 ? (ISelection)this.selections.peekFirst() : null;
   }

   @Override
   public synchronized ISelection expand(ISelection selection, EnumFacing facing, int n) {
      ListIterator listIterator = this.selections.listIterator();

      while (listIterator.hasNext()) {
         ISelection selection2 = (ISelection)listIterator.next();
         if (selection2 == selection) {
            listIterator.remove();
            listIterator.add(selection2.expand(facing, n));
            this.resetSelectionsArr();
            return (ISelection)listIterator.previous();
         }
      }

      return null;
   }

   @Override
   public synchronized ISelection[] removeAllSelections() {
      ISelection[] selections2 = this.getSelections();
      this.selections.clear();
      this.resetSelectionsArr();
      return selections2;
   }

   @Override
   public synchronized ISelection shift(ISelection selection, EnumFacing facing, int n) {
      ListIterator listIterator = this.selections.listIterator();

      while (listIterator.hasNext()) {
         ISelection selection2 = (ISelection)listIterator.next();
         if (selection2 == selection) {
            listIterator.remove();
            listIterator.add(selection2.shift(facing, n));
            this.resetSelectionsArr();
            return (ISelection)listIterator.previous();
         }
      }

      return null;
   }

   private void resetSelectionsArr() {
      this.selectionsArr = (ISelection[])this.selections.toArray(new ISelection[0]);
   }

   @Override
   public synchronized ISelection contract(ISelection selection, EnumFacing facing, int n) {
      ListIterator listIterator = this.selections.listIterator();

      while (listIterator.hasNext()) {
         ISelection selection2 = (ISelection)listIterator.next();
         if (selection2 == selection) {
            listIterator.remove();
            listIterator.add(selection2.contract(facing, n));
            this.resetSelectionsArr();
            return (ISelection)listIterator.previous();
         }
      }

      return null;
   }

   @Override
   public synchronized ISelection removeSelection(ISelection selection) {
      this.selections.remove(selection);
      this.resetSelectionsArr();
      return selection;
   }

   @Override
   public ISelection[] getSelections() {
      return this.selectionsArr;
   }

   @Override
   public ISelection getLastSelection() {
      return (ISelection)this.selections.peekLast();
   }

   @Override
   public ISelection addSelection(BetterBlockPos betterBlockPos, BetterBlockPos betterBlockPos2) {
      return this.addSelection(new Selection(betterBlockPos, betterBlockPos2));
   }

   @Override
   public synchronized ISelection addSelection(ISelection selection) {
      this.selections.add(selection);
      this.resetSelectionsArr();
      return selection;
   }
}
