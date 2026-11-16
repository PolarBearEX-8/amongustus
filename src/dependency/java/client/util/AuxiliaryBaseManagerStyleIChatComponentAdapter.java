package client.util;

import net.minecraft.util.IChatComponent;

public class AuxiliaryBaseManagerStyleIChatComponentAdapter extends BaseManagerStyleIChatComponent {
   public final String labelText;
   public final String labelText2;
   public String labelText3 = "";

   @Override
   public String getLabelText() {
      VanillaRunnable vanillaRunnable = VanillaRunnable.getVanillaRunnable();
      if (vanillaRunnable != null && vanillaRunnable.onUpdate() && ClientBrandHelper.isEmpty(this.labelText3)) {
         ThePlayerNameManager thePlayerNameManager = vanillaRunnable.resolveWorldServer(0).getThePlayerNameManager();
         ScoreboardData scoreboardData = thePlayerNameManager.get(this.labelText2);
         if (thePlayerNameManager.matchesText(this.labelText, scoreboardData)) {
            ComparatorEntry comparatorEntry = thePlayerNameManager.getComparatorEntry(this.labelText, scoreboardData);
            this.setLabelText3(String.format("%d", comparatorEntry.getIndex()));
         } else {
            this.labelText3 = "";
         }
      }

      return this.labelText3;
   }

   public void setLabelText3(String labelText4) {
      this.labelText3 = labelText4;
   }

   public AuxiliaryBaseManagerStyleIChatComponentAdapter getAuxiliaryBaseManagerStyleICh() {
      AuxiliaryBaseManagerStyleIChatComponentAdapter auxiliaryBaseManagerStyleIChatComponentAdapter = new AuxiliaryBaseManagerStyleIChatComponentAdapter(this.labelText, this.labelText2);
      auxiliaryBaseManagerStyleIChatComponentAdapter.setLabelText3(this.labelText3);
      auxiliaryBaseManagerStyleIChatComponentAdapter.getIChat(this.getStyleHasParentManager().getStyleHasParentManager2());

      for (IChatComponent iChatComponent : (Iterable<IChatComponent>)(Iterable<?>)this.getList()) {
         auxiliaryBaseManagerStyleIChatComponentAdapter.appendChatComponent(iChatComponent.getIChatComponent());
      }

      return auxiliaryBaseManagerStyleIChatComponentAdapter;
   }

   public String getLabelTextAsText() {
      return this.labelText;
   }

   public String getLabelText2() {
      return this.labelText2;
   }

   @Override
   public IChatComponent getIChatComponent() {
      return this.getAuxiliaryBaseManagerStyleICh();
   }

   public AuxiliaryBaseManagerStyleIChatComponentAdapter(String text, String text2) {
      this.labelText = text;
      this.labelText2 = text2;
   }

   @Override
   public boolean equals(Object other) {
      if (this == other) {
         return true;
      } else if (!(other instanceof AuxiliaryBaseManagerStyleIChatComponentAdapter)) {
         return false;
      } else {
         AuxiliaryBaseManagerStyleIChatComponentAdapter auxiliaryBaseManagerStyleIChatComponentAdapter = (AuxiliaryBaseManagerStyleIChatComponentAdapter)other;
         return this.labelText.equals(auxiliaryBaseManagerStyleIChatComponentAdapter.labelText) && this.labelText2.equals(auxiliaryBaseManagerStyleIChatComponentAdapter.labelText2) && super.equals(other);
      }
   }

   @Override
   public String toString() {
      return "ScoreComponent{name='"
         + this.labelText
         + '\''
         + "objective='"
         + this.labelText2
         + '\''
         + ", siblings="
         + this.list
         + ", style="
         + this.getStyleHasParentManager()
         + '}';
   }
}
