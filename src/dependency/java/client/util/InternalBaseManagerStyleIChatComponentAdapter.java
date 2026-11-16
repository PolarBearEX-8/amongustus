package client.util;

import net.minecraft.util.IChatComponent;

public class InternalBaseManagerStyleIChatComponentAdapter extends BaseManagerStyleIChatComponent {
   public final String labelText;

   public String getLabelTextAsText() {
      return this.labelText;
   }

   @Override
   public String getLabelText() {
      return this.labelText;
   }

   @Override
   public IChatComponent getIChatComponent() {
      return this.getInternalBaseManagerStyleICha();
   }

   public InternalBaseManagerStyleIChatComponentAdapter getInternalBaseManagerStyleICha() {
      InternalBaseManagerStyleIChatComponentAdapter internalBaseManagerStyleIChatComponentAdapter = new InternalBaseManagerStyleIChatComponentAdapter(this.labelText);
      internalBaseManagerStyleIChatComponentAdapter.getIChat(this.getStyleHasParentManager().getStyleHasParentManager2());

      for (IChatComponent iChatComponent : (Iterable<IChatComponent>)(Iterable)(this.getList())) {
         internalBaseManagerStyleIChatComponentAdapter.appendChatComponent(iChatComponent.getIChatComponent());
      }

      return internalBaseManagerStyleIChatComponentAdapter;
   }

   public InternalBaseManagerStyleIChatComponentAdapter(String text) {
      this.labelText = text;
   }

   @Override
   public boolean equals(Object other) {
      if (this == other) {
         return true;
      } else if (!(other instanceof InternalBaseManagerStyleIChatComponentAdapter)) {
         return false;
      } else {
         InternalBaseManagerStyleIChatComponentAdapter internalBaseManagerStyleIChatComponentAdapter = (InternalBaseManagerStyleIChatComponentAdapter)other;
         return this.labelText.equals(internalBaseManagerStyleIChatComponentAdapter.labelText) && super.equals(other);
      }
   }

   @Override
   public String toString() {
      return "SelectorComponent{pattern='" + this.labelText + '\'' + ", siblings=" + this.list + ", style=" + this.getStyleHasParentManager() + '}';
   }
}
