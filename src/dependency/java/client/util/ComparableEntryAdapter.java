package client.util;

import client.setting.RenderChoiceProperty;

public class ComparableEntryAdapter {
   public final AuxiliaryComparableAdapter auxiliaryComparableAdapter;
   public RenderChoiceProperty renderChoiceProperty;
   public final StaticFloatEntry staticFloatEntry = new StaticFloatEntry(0.0F, 0.0F, 0.0F);
   public IndirectFloatHolder[] indirectFloatHolderArray;

   public ComparableEntryAdapter(
      IndirectFloatHolder[] indirectFloatHolderArray2, float posX, float posX2, float posX3, float posX4, float posY, float posZ, Boolean flag, RenderChoiceProperty renderChoiceProperty2
   ) {
      this.renderChoiceProperty = renderChoiceProperty2;
      this.indirectFloatHolderArray = indirectFloatHolderArray2;
      float factor = posX + posX3;
      float factor2 = posX2 + posX4;
      posX /= posY;
      float factor3 = factor / posY;
      posX2 /= posZ;
      float factor4 = factor2 / posZ;
      if (flag != null && flag) {
         this.indirectFloatHolderArray[0] = indirectFloatHolderArray2[0].resolveIndirectFloatHolder(posX, posX2);
         this.indirectFloatHolderArray[1] = indirectFloatHolderArray2[1].resolveIndirectFloatHolder(factor3, posX2);
         this.indirectFloatHolderArray[2] = indirectFloatHolderArray2[2].resolveIndirectFloatHolder(factor3, factor4);
         this.indirectFloatHolderArray[3] = indirectFloatHolderArray2[3].resolveIndirectFloatHolder(posX, factor4);
      } else {
         this.indirectFloatHolderArray[0] = indirectFloatHolderArray2[0].resolveIndirectFloatHolder(factor3, posX2);
         this.indirectFloatHolderArray[1] = indirectFloatHolderArray2[1].resolveIndirectFloatHolder(posX, posX2);
         this.indirectFloatHolderArray[2] = indirectFloatHolderArray2[2].resolveIndirectFloatHolder(posX, factor4);
         this.indirectFloatHolderArray[3] = indirectFloatHolderArray2[3].resolveIndirectFloatHolder(factor3, factor4);
      }

      this.auxiliaryComparableAdapter = renderChoiceProperty2.getAuxiliaryComparableAdapter();
   }

   public ComparableEntryAdapter(IndirectFloatHolder[] indirectFloatHolderArray2, double[] offsetArray, double[] offsetArray2, float factor, float factor2, Boolean flag, RenderChoiceProperty renderChoiceProperty2) {
      this(indirectFloatHolderArray2, (float)offsetArray[0], (float)offsetArray[1], (float)offsetArray2[0], (float)offsetArray2[1], factor, factor2, flag, renderChoiceProperty2);
   }
}
