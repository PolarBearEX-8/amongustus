package client.util;

import client.event.PassiveListener;
import java.util.Stack;

public class ListenerHandler implements PassiveListener {
   public Stack stack = new Stack();
   public EnhancedFloatEntry enhancedFloatEntry2;
   public Stack stack2 = new Stack();
   public EnhancedFloatEntry enhancedFloatEntry;
   public CommonFloatEntry commonFloatEntry;
   public CommonFloatEntry commonFloatEntry2 = new CommonFloatEntry();

   public EnhancedFloatEntry peekAsEnhancedFloatEnt() {
      return (EnhancedFloatEntry)this.stack2.peek();
   }

   public void applyListenerAdapter2(ListenerAdapter23 listenerAdapter) {
      float factor = listenerAdapter.factor5 / 16.0F;
      float factor2 = listenerAdapter.factor12 / 16.0F;
      float factor3 = listenerAdapter.factor7 / 16.0F;
      float factor4 = factor2;
      float factor5 = factor;
      this.applyFloatByFloat(factor5, factor4, factor3);
   }

   public CommonFloatEntry peek() {
      return (CommonFloatEntry)this.stack.peek();
   }

   public void applyListenerAdapter23(ListenerAdapter23 listenerAdapter) {
      byte byteValue = 0;
      int count = 931602938;
      short shortValue = 21836;
      float factor = -listenerAdapter.factor5 / 16.0F;
      float factor2 = -listenerAdapter.factor12 / 16.0F;
      float factor3 = -listenerAdapter.factor7 / 16.0F;
      float factor4 = factor2;
      float factor5 = factor;
      this.applyFloatByFloat(factor5, factor4, factor3);
   }

   public void applyComparableEntryArrayEntryA(ComparableEntryArrayEntryAdapter comparableEntryArrayEntryAdapter) {
      StaticFloatEntry staticFloatEntry = comparableEntryArrayEntryAdapter.staticFloatEntry2;
      this.commonFloatEntry2.updateState();
      CommonFloatEntry commonFloatEntry3 = this.commonFloatEntry;
      float factor = staticFloatEntry.getFactor();
      commonFloatEntry3.applyFloatByFloat(factor);
      CommonFloatEntry commonFloatEntry4 = this.commonFloatEntry;
      this.commonFloatEntry2.applyCommonFloatEntry(commonFloatEntry4);
      CommonFloatEntry commonFloatEntry5 = this.commonFloatEntry;
      float factor2 = staticFloatEntry.getFactor3();
      commonFloatEntry5.applyFloat3(factor2);
      CommonFloatEntry commonFloatEntry6 = this.commonFloatEntry;
      this.commonFloatEntry2.applyCommonFloatEntry(commonFloatEntry6);
      CommonFloatEntry commonFloatEntry7 = this.commonFloatEntry;
      float factor3 = staticFloatEntry.getFactor2();
      float factor4 = factor3;
      commonFloatEntry7.applyFloat2(factor4);
      CommonFloatEntry commonFloatEntry8 = this.commonFloatEntry;
      this.commonFloatEntry2.applyCommonFloatEntry(commonFloatEntry8);
      this.enhancedFloatEntry.updateState();
      EnhancedFloatEntry enhancedFloatEntry3 = this.enhancedFloatEntry2;
      factor3 = staticFloatEntry.getFactor();
      float factor5 = factor3;
      enhancedFloatEntry3.applyFloatByFloat(factor5);
      EnhancedFloatEntry enhancedFloatEntry4 = this.enhancedFloatEntry2;
      this.enhancedFloatEntry.applyEnhancedFloatEntry(enhancedFloatEntry4);
      EnhancedFloatEntry enhancedFloatEntry5 = this.enhancedFloatEntry2;
      float factor6 = staticFloatEntry.getFactor3();
      enhancedFloatEntry5.applyFloat3(factor6);
      EnhancedFloatEntry enhancedFloatEntry6 = this.enhancedFloatEntry2;
      this.enhancedFloatEntry.applyEnhancedFloatEntry(enhancedFloatEntry6);
      EnhancedFloatEntry enhancedFloatEntry7 = this.enhancedFloatEntry2;
      float factor7 = staticFloatEntry.getFactor2();
      enhancedFloatEntry7.applyFloat2(factor7);
      EnhancedFloatEntry enhancedFloatEntry8 = this.enhancedFloatEntry2;
      this.enhancedFloatEntry.applyEnhancedFloatEntry(enhancedFloatEntry8);
      CommonFloatEntry commonFloatEntry9 = (CommonFloatEntry)this.stack.peek();
      CommonFloatEntry commonFloatEntry10 = this.commonFloatEntry2;
      commonFloatEntry9.applyCommonFloatEntry(commonFloatEntry10);
      EnhancedFloatEntry enhancedFloatEntry9 = (EnhancedFloatEntry)this.stack2.peek();
      EnhancedFloatEntry enhancedFloatEntry10 = this.enhancedFloatEntry;
      enhancedFloatEntry9.applyEnhancedFloatEntry(enhancedFloatEntry10);
   }

   public void applyComparableEntryAdapter(ComparableEntryAdapter comparableEntryAdapter) {
      EnhancedFloatEntry enhancedFloatEntry3 = (EnhancedFloatEntry)this.stack2.peek();
      comparableEntryAdapter.staticFloatEntry.factor2 = enhancedFloatEntry3.factor5 * (float)comparableEntryAdapter.auxiliaryComparableAdapter.getCount()
         + enhancedFloatEntry3.factor * (float)comparableEntryAdapter.auxiliaryComparableAdapter.getCount2()
         + enhancedFloatEntry3.factor8 * (float)comparableEntryAdapter.auxiliaryComparableAdapter.getCount3();
      comparableEntryAdapter.staticFloatEntry.factor3 = enhancedFloatEntry3.factor3 * (float)comparableEntryAdapter.auxiliaryComparableAdapter.getCount()
         + enhancedFloatEntry3.factor6 * (float)comparableEntryAdapter.auxiliaryComparableAdapter.getCount2()
         + enhancedFloatEntry3.factor9 * (float)comparableEntryAdapter.auxiliaryComparableAdapter.getCount3();
      comparableEntryAdapter.staticFloatEntry.factor = enhancedFloatEntry3.factor2 * (float)comparableEntryAdapter.auxiliaryComparableAdapter.getCount()
         + enhancedFloatEntry3.factor7 * (float)comparableEntryAdapter.auxiliaryComparableAdapter.getCount2()
         + enhancedFloatEntry3.factor4 * (float)comparableEntryAdapter.auxiliaryComparableAdapter.getCount3();
   }

   @Override
   public void applyFloat(float factor) {
      this.commonFloatEntry2.updateState();
      this.commonFloatEntry2.applyFloatByFloat(factor);
      this.enhancedFloatEntry.updateState();
      this.enhancedFloatEntry.applyFloatByFloat(factor);
      CommonFloatEntry commonFloatEntry3 = (CommonFloatEntry)this.stack.peek();
      CommonFloatEntry commonFloatEntry4 = this.commonFloatEntry2;
      commonFloatEntry3.applyCommonFloatEntry(commonFloatEntry4);
      EnhancedFloatEntry enhancedFloatEntry3 = (EnhancedFloatEntry)this.stack2.peek();
      EnhancedFloatEntry enhancedFloatEntry4 = this.enhancedFloatEntry;
      enhancedFloatEntry3.applyEnhancedFloatEntry(enhancedFloatEntry4);
   }

   @Override
   public void applyFloat(float posX, float posY, float posZ) {
      if (posZ != 0.0F) {
         this.applyFloat(posX);
      }

      if (posY != 0.0F) {
         this.applyFloat2(posX);
      }

      if (posX != 0.0F) {
         this.applyFloatByFloat(posX);
      }
   }

   public void applyListenerAdapterByListenerAdapter2(ListenerAdapter23 listenerAdapter) {
      float factor = -listenerAdapter.getFactor4() / 16.0F;
      float factor2 = listenerAdapter.getFactor11() / 16.0F;
      float factor3 = listenerAdapter.getFactor10() / 16.0F;
      float factor4 = factor;
      this.applyFloatByFloat(factor4, factor3, factor2);
   }

   @Override
   public void updateState() {
      if (this.stack.size() == 1) {
         throw new IllegalStateException("A one level stack can't be popped!");
      } else {
         this.stack.pop();
         this.stack2.pop();
      }
   }

   public void applyComparableEntryArrayEntryAByComparableEntryA(ComparableEntryArrayEntryAdapter comparableEntryArrayEntryAdapter) {
      StaticFloatEntry staticFloatEntry = comparableEntryArrayEntryAdapter.staticFloatEntry3;
      float factor = staticFloatEntry.getFactor2() / 16.0F;
      float factor2 = staticFloatEntry.getFactor() / 16.0F;
      float factor3 = staticFloatEntry.getFactor3() / 16.0F;
      float factor4 = factor;
      this.applyFloatByFloat(factor4, factor3, factor2);
   }

   @Override
   public void applyFloat2(float posX, float posY, float posZ) {
      this.commonFloatEntry2.updateState();
      this.commonFloatEntry2.setFactor7(posX);
      this.commonFloatEntry2.setFactor16(posY);
      this.commonFloatEntry2.setFactor2(posZ);
      CommonFloatEntry commonFloatEntry3 = (CommonFloatEntry)this.stack.peek();
      CommonFloatEntry commonFloatEntry4 = this.commonFloatEntry2;
      commonFloatEntry3.applyCommonFloatEntry(commonFloatEntry4);
      if (posX < 0.0F || posY < 0.0F || posZ < 0.0F) {
         this.enhancedFloatEntry.updateState();
         float factor = posX < 0.0F ? -1.0F : 1.0F;
         this.enhancedFloatEntry.setFactor5(factor);
         float factor2 = posY < 0.0F ? -1.0F : 1.0F;
         this.enhancedFloatEntry.setFactor6(factor2);
         float factor3 = posZ < 0.0F ? -1.0F : 1.0F;
         this.enhancedFloatEntry.setFactor4(factor3);
         EnhancedFloatEntry enhancedFloatEntry3 = (EnhancedFloatEntry)this.stack2.peek();
         EnhancedFloatEntry enhancedFloatEntry4 = this.enhancedFloatEntry;
         enhancedFloatEntry3.applyEnhancedFloatEntry(enhancedFloatEntry4);
      }
   }

   @Override
   public void applyFloat2(float factor) {
      this.commonFloatEntry2.updateState();
      this.commonFloatEntry2.applyFloat3(factor);
      this.enhancedFloatEntry.updateState();
      this.enhancedFloatEntry.applyFloat3(factor);
      CommonFloatEntry commonFloatEntry3 = (CommonFloatEntry)this.stack.peek();
      CommonFloatEntry commonFloatEntry4 = this.commonFloatEntry2;
      commonFloatEntry3.applyCommonFloatEntry(commonFloatEntry4);
      EnhancedFloatEntry enhancedFloatEntry3 = (EnhancedFloatEntry)this.stack2.peek();
      EnhancedFloatEntry enhancedFloatEntry4 = this.enhancedFloatEntry;
      enhancedFloatEntry3.applyEnhancedFloatEntry(enhancedFloatEntry4);
   }

   public void applyListenerAdapter3(ListenerAdapter23 listenerAdapter) {
      float factor = listenerAdapter.getFactor9();
      float factor2 = listenerAdapter.getFactor2();
      float factor3 = listenerAdapter.getFactor();
      float factor4 = factor;
      this.applyFloat2(factor4, factor3, factor2);
   }

   public void applyListenerAdapter4(ListenerAdapter23 listenerAdapter) {
      if (listenerAdapter.getFactor3() != 0.0F) {
         float factor = listenerAdapter.getFactor3();
         this.applyFloat(factor);
      }

      if (listenerAdapter.getFactor6() != 0.0F) {
         float factor2 = listenerAdapter.getFactor6();
         this.applyFloat2(factor2);
      }

      if (listenerAdapter.getFactor8() != 0.0F) {
         float factor3 = listenerAdapter.getFactor8();
         this.applyFloatByFloat(factor3);
      }
   }

   public void applyComparableEntryArrayEntryA2(ComparableEntryArrayEntryAdapter comparableEntryArrayEntryAdapter) {
      short shortValue = 4231;
      short shortValue2 = -1610;
      short shortValue3 = 13793;
      StaticFloatEntry staticFloatEntry = comparableEntryArrayEntryAdapter.staticFloatEntry3;
      float factor = -staticFloatEntry.getFactor2() / 16.0F;
      float factor2 = -staticFloatEntry.getFactor() / 16.0F;
      float factor3 = -staticFloatEntry.getFactor3() / 16.0F;
      float factor4 = factor;
      this.applyFloatByFloat(factor4, factor3, factor2);
   }

   public void applyIndirectFloatHolder(IndirectFloatHolder indirectFloatHolder) {
      CommonFloatEntry commonFloatEntry3 = (CommonFloatEntry)this.stack.peek();
      indirectFloatHolder.staticFloatEntry.factor2 = commonFloatEntry3.factor7 * indirectFloatHolder.staticFloatEntry2.getFactor2()
         + commonFloatEntry3.factor5 * indirectFloatHolder.staticFloatEntry2.getFactor3()
         + commonFloatEntry3.factor10 * indirectFloatHolder.staticFloatEntry2.getFactor()
         + commonFloatEntry3.factor;
      indirectFloatHolder.staticFloatEntry.factor3 = commonFloatEntry3.factor11 * indirectFloatHolder.staticFloatEntry2.getFactor2()
         + commonFloatEntry3.factor16 * indirectFloatHolder.staticFloatEntry2.getFactor3()
         + commonFloatEntry3.factor15 * indirectFloatHolder.staticFloatEntry2.getFactor()
         + commonFloatEntry3.factor9;
      indirectFloatHolder.staticFloatEntry.factor = commonFloatEntry3.factor8 * indirectFloatHolder.staticFloatEntry2.getFactor2()
         + commonFloatEntry3.factor14 * indirectFloatHolder.staticFloatEntry2.getFactor3()
         + commonFloatEntry3.factor2 * indirectFloatHolder.staticFloatEntry2.getFactor()
         + commonFloatEntry3.factor12;
   }

   @Override
   public void applyFloatByFloat(float factor) {
      this.commonFloatEntry2.updateState();
      this.commonFloatEntry2.applyFloat2(factor);
      this.enhancedFloatEntry.updateState();
      this.enhancedFloatEntry.applyFloat2(factor);
      CommonFloatEntry commonFloatEntry3 = (CommonFloatEntry)this.stack.peek();
      CommonFloatEntry commonFloatEntry4 = this.commonFloatEntry2;
      commonFloatEntry3.applyCommonFloatEntry(commonFloatEntry4);
      EnhancedFloatEntry enhancedFloatEntry3 = (EnhancedFloatEntry)this.stack2.peek();
      EnhancedFloatEntry enhancedFloatEntry4 = this.enhancedFloatEntry;
      enhancedFloatEntry3.applyEnhancedFloatEntry(enhancedFloatEntry4);
   }

   @Override
   public void updateState2() {
      this.stack.add(new CommonFloatEntry((CommonFloatEntry)this.stack.peek()));
      this.stack2.add(new EnhancedFloatEntry((EnhancedFloatEntry)this.stack2.peek()));
   }

   @Override
   public void applyFloatByFloat(float posX, float posY, float posZ) {
      this.commonFloatEntry2.updateState();
      this.commonFloatEntry2.factor = posX;
      this.commonFloatEntry2.factor9 = posY;
      this.commonFloatEntry2.factor12 = posZ;
      CommonFloatEntry commonFloatEntry3 = (CommonFloatEntry)this.stack.peek();
      CommonFloatEntry commonFloatEntry4 = this.commonFloatEntry2;
      commonFloatEntry3.applyCommonFloatEntry(commonFloatEntry4);
   }

   public ListenerHandler() {
      this.enhancedFloatEntry = new EnhancedFloatEntry();
      this.commonFloatEntry = new CommonFloatEntry();
      this.enhancedFloatEntry2 = new EnhancedFloatEntry();
      CommonFloatEntry commonFloatEntry3 = new CommonFloatEntry();
      EnhancedFloatEntry enhancedFloatEntry3 = new EnhancedFloatEntry();
      commonFloatEntry3.updateState();
      enhancedFloatEntry3.updateState();
      this.stack.add(commonFloatEntry3);
      this.stack2.add(enhancedFloatEntry3);
   }
}
