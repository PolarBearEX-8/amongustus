package client.util;

import java.util.List;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.Entity;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;

public class AngleMonitor {
   public static IChatComponent resolveIChatComponentByICommandSender(ICommandSender iCommandSender, IChatComponent iChatComponent, Entity entity) throws InternalExceptionAdapter {
      Object entry;
      if (iChatComponent instanceof AuxiliaryBaseManagerStyleIChatComponentAdapter) {
         AuxiliaryBaseManagerStyleIChatComponentAdapter auxiliaryBaseManagerStyleIChatComponentAdapter = (AuxiliaryBaseManagerStyleIChatComponentAdapter)iChatComponent;
         String labelTextAsText = auxiliaryBaseManagerStyleIChatComponentAdapter.getLabelTextAsText();
         if (FovAggregator.matchesText(labelTextAsText)) {
            List list = FovAggregator.resolveList(iCommandSender, labelTextAsText, Entity.class);
            if (list.size() != 1) {
               throw new AimStack();
            }

            labelTextAsText = ((Entity)list.get(0)).getText();
         }

         entry = entity != null && labelTextAsText.equals("*")
            ? new AuxiliaryBaseManagerStyleIChatComponentAdapter(entity.getText(), auxiliaryBaseManagerStyleIChatComponentAdapter.getLabelText2())
            : new AuxiliaryBaseManagerStyleIChatComponentAdapter(labelTextAsText, auxiliaryBaseManagerStyleIChatComponentAdapter.getLabelText2());
         ((AuxiliaryBaseManagerStyleIChatComponentAdapter)entry).setLabelText3(auxiliaryBaseManagerStyleIChatComponentAdapter.getLabelText());
      } else if (iChatComponent instanceof InternalBaseManagerStyleIChatComponentAdapter) {
         String labelTextAsText2 = ((InternalBaseManagerStyleIChatComponentAdapter)iChatComponent).getLabelTextAsText();
         entry = FovAggregator.resolveIChatComponent(iCommandSender, labelTextAsText2);
         if (entry == null) {
            entry = new ChatComponentText("");
         }
      } else if (iChatComponent instanceof ChatComponentText) {
         entry = new ChatComponentText(((ChatComponentText)iChatComponent).getLabelTextAsText());
      } else {
         if (!(iChatComponent instanceof BaseManagerStyleIChatComponentAdapter)) {
            return iChatComponent;
         }

         Object[] objectArray = ((BaseManagerStyleIChatComponentAdapter)iChatComponent).getObjectArray();

         for (int index = 0; index < objectArray.length; index++) {
            Object entry2 = objectArray[index];
            if (entry2 instanceof IChatComponent) {
               objectArray[index] = resolveIChatComponentByICommandSender(iCommandSender, (IChatComponent)entry2, entity);
            }
         }

         entry = new BaseManagerStyleIChatComponentAdapter(((BaseManagerStyleIChatComponentAdapter)iChatComponent).getLabelTextAsText(), objectArray);
      }

      StyleHasParentManager styleHasParentManager = iChatComponent.getStyleHasParentManager();
      if (styleHasParentManager != null) {
         ((IChatComponent)entry).getIChat(styleHasParentManager.getStyleHasParentManager2());
      }

      for (IChatComponent iChatComponent2 : (Iterable<IChatComponent>)(Iterable)(iChatComponent.getList())) {
         ((IChatComponent)entry).appendChatComponent(resolveIChatComponentByICommandSender(iCommandSender, iChatComponent2, entity));
      }

      return (IChatComponent)entry;
   }
}
