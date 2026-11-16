package client.util;

import client.setting.MovementConfigProperty;
import java.io.File;
import java.util.ArrayList;
import net.labymod.ModelConfig;

public class CosmeticsItemData extends PrimaryFloatEntry {
   public int count3;
   public final String labelText2;
   public ArrayList arrayList;
   public final String labelText;
   public float factor11;
   public final EnhancedListEntry[] enhancedListEntryArray;
   public final boolean enabled;
   public final int count2;
   public float[] factorArray2;
   public float factor9;
   public float factor10 = 1.0F;
   public ArrayList arrayList2;
   public float[] factorArray;
   public String labelText3;
   public transient MainControllerManager mainControllerManager;
   public final Integer integer;
   public transient GenericListEntry genericListEntry;
   public ModelConfig[] modelConfigArray;
   public float[] factorArray3;
   public float factor8;
   public final File file;

   public float[] getFactorArray() {
      return this.factorArray;
   }

   public int getCount2() {
      return this.count2;
   }

   public Integer getInteger() {
      return this.integer;
   }

   public void setFactorArray3(float[] factorArray4) {
      this.factorArray3 = factorArray4;
   }

   public boolean isEnabled() {
      return this.enabled;
   }

   public void setCount3(int count) {
      this.count3 = count;
   }

   public EnhancedListEntry[] getEnhancedListEntryArray() {
      return this.enhancedListEntryArray;
   }

   public int getCount3() {
      return this.count3;
   }

   public void setFactorArray2(float[] factorArray4) {
      this.factorArray2 = factorArray4;
   }

   public float getFactor8() {
      return this.factor8;
   }

   public void setFactor8(float factor) {
      this.factor8 = factor;
   }

   public ModelConfig[] getModelConfigArray() {
      return this.modelConfigArray;
   }

   public boolean isActive2() {
      return true;
   }

   public boolean isActiveAsFlag() {
      return this.modelConfigArray != null && this.modelConfigArray.length > 0;
   }

   public MainControllerManager getMainControllerManager() {
      return this.mainControllerManager;
   }

   public ArrayList getArrayList() {
      if (this.arrayList2 == null) {
         this.arrayList2 = new ArrayList();
      }

      return this.arrayList2;
   }

   public float[] getFactorArray3() {
      return this.factorArray3;
   }

   public boolean isActive3() {
      return this.arrayList2 != null;
   }

   public float getFactor10() {
      return this.factor10;
   }

   public float[] getFactorArray2() {
      return this.factorArray2;
   }

   public boolean isActive5() {
      return this.arrayList != null;
   }

   public ArrayList getArrayListAsArrayList() {
      if (this.arrayList == null) {
         this.arrayList = new ArrayList();
      }

      return this.arrayList;
   }

   public void applyExtendedHashMapHolder(ExtendedHashMapHolder extendedHashMapHolder) {
      this.mainControllerManager = new MainControllerManager(new CouldNotFindBoneHandlerBridge(this.genericListEntry, extendedHashMapHolder));
   }

   public String getText() {
      return this.labelText2;
   }

   public GenericListEntry getGenericListEntry() {
      return this.genericListEntry;
   }

   public boolean isActive6() {
      return this.enhancedListEntryArray != null && this.enhancedListEntryArray.length > 0;
   }

   public void setModelConfigArray(ModelConfig[] modelConfigArray2) {
      this.modelConfigArray = modelConfigArray2;
   }

   public void setFactor9(float factor) {
      this.factor9 = factor;
   }

   public String getLabelText() {
      return this.labelText;
   }

   public boolean isActive4() {
      return this.mainControllerManager != null;
   }

   public File getFile() {
      return this.file;
   }

   public void setFactorArray(float[] factorArray4) {
      this.factorArray = factorArray4;
   }

   public float getFactor9() {
      return this.factor9;
   }

   public String getLabelText3() {
      return this.labelText3;
   }

   public void setFactor10(float factor) {
      this.factor10 = factor;
   }

   public void setFactor11(float factor) {
      this.factor11 = factor;
   }

   public float getFactor11() {
      return this.factor11;
   }

   public CosmeticsItemData(
      String text, Integer count, int count4, int count5, boolean flag, String text2, GenericListEntry genericListEntry2, EnhancedListEntry[] enhancedListEntryArray2, ModelConfig[] modelConfigArray2, File file2
   ) {
      super(MovementConfigProperty.BODY);
      this.genericListEntry = genericListEntry2;
      this.labelText2 = text;
      this.integer = count;
      this.count3 = count4;
      this.count2 = count5;
      this.enabled = flag;
      this.labelText = text2;
      this.enhancedListEntryArray = enhancedListEntryArray2;
      this.modelConfigArray = modelConfigArray2;
      this.file = file2;
   }
}
