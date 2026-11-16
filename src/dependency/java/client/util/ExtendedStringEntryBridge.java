package client.util;

import client.ClientGlobals;

public class ExtendedStringEntryBridge extends AuxiliaryStringEntry {
   public void updateState2() {
      byte byteValue = 0;
      int count = 723094;
      int count2 = 13970578;
      String text = "§7" + this.getString() + " [§cset§7] [§2Password§7]";
      this.applyText(text);
      String text2 = "§7" + this.getString() + " [§cget§7] <custom/generated/private/private2>";
      this.applyText(text2);
   }

   @Override
   public void updateState() {
      String text = "§c" + this.getString() + "§7 (set or get password)";
      this.applyText(text);
   }

   @Override
   public void printStackTrace(String[] textArray, String text) {
      if (textArray.length > 1) {
         if (textArray[1].equalsIgnoreCase("set") && textArray.length >= 3) {
            String text2 = textArray[2];
            ClientGlobals.moduleManager.autoRegister.settingCustomPassword.setSelected(text2);
            String text3 = "Set CustomPassword to §2" + textArray[2];
            this.applyText(text3);
            return;
         }

         if (textArray[1].equalsIgnoreCase("get")) {
            if (textArray.length >= 3) {
               String text4 = textArray[2].toLowerCase();
               switch (text4) {
                  case "custom":
                     String text5 = "The CustomPassword is §2 " + ClientGlobals.moduleManager.autoRegister.settingCustomPassword.getSelected();
                     this.applyText(text5);
                     break;
                  case "generated":
                     StringBuilder builder = new StringBuilder()
                        .append("The generated passsword for ")
                        .append(MinecraftAccessor.minecraft.getSession().getText1())
                        .append(" is ");
                     String text6 = "generated";
                     String text7 = builder.append(ClientGlobals.moduleManager.autoRegister.getString3(text6, false)).toString();
                     this.applyText(text7);
                     break;
                  case "private":
                     StringBuilder builder2 = new StringBuilder()
                        .append("The private passsword for ")
                        .append(MinecraftAccessor.minecraft.getSession().getText1())
                        .append(" is ");
                     String text8 = "private";
                     String text9 = builder2.append(ClientGlobals.moduleManager.autoRegister.getString3(text8, false)).toString();
                     this.applyText(text9);
                     break;
                  case "private2":
                     StringBuilder builder3 = new StringBuilder()
                        .append("The private2 passsword for ")
                        .append(MinecraftAccessor.minecraft.getSession().getText1())
                        .append(" is ");
                     String text10 = "private2";
                     String text11 = builder3.append(ClientGlobals.moduleManager.autoRegister.getString3(text10, false)).toString();
                     this.applyText(text11);
                     break;
                  default:
                     this.updateState2();
               }

               return;
            }

            String text12 = "The CustomPassword is §2" + ClientGlobals.moduleManager.autoRegister.settingCustomPassword.getSelected();
            this.applyText(text12);
            return;
         }
      }

      this.updateState2();
   }

   public ExtendedStringEntryBridge() {
      super("autoregister");
   }
}
