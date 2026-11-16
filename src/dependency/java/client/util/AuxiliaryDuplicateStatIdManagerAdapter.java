package client.util;

import client.event.PostUpdateSubscriber;
import client.setting.ClientSettingsBlackType;
import net.minecraft.block.Block;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IChatComponent;

public class AuxiliaryDuplicateStatIdManagerAdapter extends DuplicateStatIdManager {
   public final int count;
   public final int count2;
   public final AuxiliaryDuplicateStatIdManagerAdapter auxiliaryDuplicateStatIdManagerAdapter;
   public final String labelText2;
   public PostUpdateSubscriber postUpdateSubscriber;
   public final ItemStack itemStack;
   public boolean enabled3;

   public AuxiliaryDuplicateStatIdManagerAdapter getAuxiliaryDuplicateStatIdManaAsAuxiliaryDuplica() {
      this.enabled3 = true;
      return this;
   }

   @Override
   public DuplicateStatIdManager getDuplicateStatIdManagerAsDuplicateStatIdM() {
      return this.getAuxiliaryDuplicateStatIdMana();
   }

   public AuxiliaryDuplicateStatIdManagerAdapter withPostUpdateSubscriber(PostUpdateSubscriber postUpdateSubscriber2) {
      this.postUpdateSubscriber = postUpdateSubscriber2;
      return this;
   }

   public AuxiliaryDuplicateStatIdManagerAdapter getAuxiliaryDuplicateStatIdMana2() {
      this.enabled = true;
      return this;
   }

   public AuxiliaryDuplicateStatIdManagerAdapter resolveAuxiliaryDuplicateStatIdMana(Class clazz) {
      return (AuxiliaryDuplicateStatIdManagerAdapter)super.withMemberRef(clazz);
   }

   public String getText() {
      return this.postUpdateSubscriber != null ? this.postUpdateSubscriber.resolveText(I18n.getString2(this.labelText2)) : I18n.getString2(this.labelText2);
   }

   @Override
   public IChatComponent getIChatComponent() {
      IChatComponent iChatComponent = super.getIChatComponent();
      iChatComponent.getStyleHasParentManager().withClientSettingsBlackType(this.isEnabled3() ? ClientSettingsBlackType.DARK_PURPLE : ClientSettingsBlackType.GREEN);
      return iChatComponent;
   }

   @Override
   public boolean isActive() {
      return true;
   }

   @Override
   public DuplicateStatIdManager withMemberRef(Class clazz) {
      return this.resolveAuxiliaryDuplicateStatIdMana(clazz);
   }

   @Override
   public DuplicateStatIdManager getDuplicateStatIdManager() {
      return this.getAuxiliaryDuplicateStatIdMana2();
   }

   public AuxiliaryDuplicateStatIdManagerAdapter getAuxiliaryDuplicateStatIdMana() {
      super.getDuplicateStatIdManagerAsDuplicateStatIdM();
      AchievementTheEnd2Manager.list.add(this);
      return this;
   }

   public boolean isEnabled3() {
      return this.enabled3;
   }

   public AuxiliaryDuplicateStatIdManagerAdapter(String text, String text2, int count3, int count4, Item item, AuxiliaryDuplicateStatIdManagerAdapter auxiliaryDuplicateStatIdManagerAdapter2) {
      this(text, text2, count3, count4, new ItemStack(item), auxiliaryDuplicateStatIdManagerAdapter2);
   }

   public AuxiliaryDuplicateStatIdManagerAdapter(String text, String text2, int count3, int count4, ItemStack stack, AuxiliaryDuplicateStatIdManagerAdapter auxiliaryDuplicateStatIdManagerAdapter2) {
      super(text, new BaseManagerStyleIChatComponentAdapter("achievement." + text2, new Object[0]));
      this.itemStack = stack;
      this.labelText2 = "achievement." + text2 + ".desc";
      this.count = count3;
      this.count2 = count4;
      if (count3 < AchievementTheEnd2Manager.count3) {
         AchievementTheEnd2Manager.count3 = count3;
      }

      if (count4 < AchievementTheEnd2Manager.count4) {
         AchievementTheEnd2Manager.count4 = count4;
      }

      if (count3 > AchievementTheEnd2Manager.count2) {
         AchievementTheEnd2Manager.count2 = count3;
      }

      if (count4 > AchievementTheEnd2Manager.count) {
         AchievementTheEnd2Manager.count = count4;
      }

      this.auxiliaryDuplicateStatIdManagerAdapter = auxiliaryDuplicateStatIdManagerAdapter2;
   }

   public AuxiliaryDuplicateStatIdManagerAdapter(String text, String text2, int count3, int count4, Block block, AuxiliaryDuplicateStatIdManagerAdapter auxiliaryDuplicateStatIdManagerAdapter2) {
      this(text, text2, count3, count4, new ItemStack(block), auxiliaryDuplicateStatIdManagerAdapter2);
   }
}
