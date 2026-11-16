package client.util;

import client.event.PreMotionBroadcaster;
import client.setting.CombatDelayProperty;
import client.setting.MovementOptionProperty;
import java.util.EnumMap;

public class MainControllerManager implements PreMotionBroadcaster {
   public FunctionEntry functionEntry;
   public String labelText2;
   public CouldNotFindBoneHandlerAdapter couldNotFindBoneHandlerAdapter;
   public String labelText;
   public String labelText3;
   public CommonHashMapEntry commonHashMapEntry;
   public EnumMap enumMap = new EnumMap<>(CombatDelayProperty.class);

   @Override
   public void applySharedDoubleEntry(SharedDoubleEntry sharedDoubleEntry) {
      long keySalt = 0x17D2855B134L ^ 0x3FF34ED398D8L;
      int count = (int)((0x17D2855B134L ^ 0x3FF34ED398D8L) << 32 >>> 48);
      int count2 = (int)(keySalt << 48 >>> 48);
      int count3 = (int)((0x17D2855B134L ^ 0x6400DBCC31CCL) >>> 48);
      this.functionEntry = new FunctionEntry(this, this.labelText2, 5.0F, this::resolveMovementOptionProperty);
      String text = this.couldNotFindBoneHandlerAdapter.getExtendedHashMapHolder().getText();
      if (text != null) {
         this.labelText3 = text;
         this.labelText = text;
         FunctionEntry functionEntry2 = this.functionEntry;
         LocalListHolder localListHolder = new LocalListHolder();
         Boolean flag = true;
         LocalListHolder localListHolder2 = localListHolder.add(text, flag);
         functionEntry2.applyLocalListHolder(localListHolder2);

         for (CombatDelayProperty combatDelayProperty : CombatDelayProperty.VALUES) {
            EnumMap enumMap2 = this.enumMap;
            String labelText4 = combatDelayProperty.getLabelText();
            String text2 = labelText4;
            enumMap2.put(combatDelayProperty, this.matchesText(text2) ? combatDelayProperty.getLabelText() : this.labelText3);
         }
      }

      FunctionEntry functionEntry3 = this.functionEntry;
      sharedDoubleEntry.resolveFunctionEntry(functionEntry3);
   }

   private MovementOptionProperty resolveMovementOptionProperty(CompositeListEntry compositeListEntry) {
      return MovementOptionProperty.CONTINUE;
   }

   public CouldNotFindBoneHandlerAdapter getCouldNotFindBoneHandlerAdapter() {
      return this.couldNotFindBoneHandlerAdapter;
   }

   public void applyCombatDelayProperty(CombatDelayProperty combatDelayProperty) {
      long value = 0x3606DAC6A6FDL;
      String text = (String)this.enumMap.get(combatDelayProperty);
      if (!this.labelText.equals(text)) {
         String text2 = text == null ? this.labelText3 : text;
         if (this.matchesText(text2)) {
            this.labelText = text2;
            FunctionEntry functionEntry2 = this.functionEntry;
            LocalListHolder localListHolder = new LocalListHolder();
            Boolean flag = true;
            LocalListHolder localListHolder2 = localListHolder.add(text2, flag);
            functionEntry2.applyLocalListHolder(localListHolder2);
         }
      }
   }

   @Override
   public CommonHashMapEntry getCommonHashMapEntry() {
      return this.commonHashMapEntry;
   }

   public boolean isActive() {
      return this.labelText3 != null;
   }

   public boolean matchesText(String text) {
      return this.couldNotFindBoneHandlerAdapter.getExtendedHashMapHolder().get(text) != null;
   }

   public MainControllerManager(CouldNotFindBoneHandlerAdapter couldNotFindBoneHandlerAdapter2) {
      this.commonHashMapEntry = new CommonHashMapEntry(this);
      this.labelText2 = "mainController";
      this.couldNotFindBoneHandlerAdapter = couldNotFindBoneHandlerAdapter2;
   }
}
