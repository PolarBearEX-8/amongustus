package cc.baritone.utils;

import java.io.File;

public class StringEntry {
   public final String labelText;
   public final String labelText2;
   public final SpecialListener specialListener;

   public void setEnabled(boolean enabled) {
      Pokechu22WorldDownloaderManager.properties3.setProperty("Extensions." + this.labelText + ".enabled", Boolean.toString(enabled));
      Pokechu22WorldDownloaderManager.updateState8();
   }

   public void updateState() {
      this.setEnabled(!this.isActive());
   }

   public String getTextAsText() {
      if (this.specialListener instanceof AuxiliaryListener12Extension) {
         String text = ((AuxiliaryListener12Extension)this.specialListener).getText2();
         if (text != null && !text.isEmpty()) {
            return text;
         }
      }

      return this.labelText;
   }

   public String getText() {
      StringBuilder builder = new StringBuilder();
      builder.append("Id: ").append(this.labelText).append('\n');
      builder.append("Version: ").append(this.labelText2).append('\n');
      if (this.specialListener instanceof AuxiliaryListener12Extension) {
         AuxiliaryListener12Extension specialListener2 = (AuxiliaryListener12Extension)this.specialListener;
         String text = specialListener2.getText2();
         String textAsText = specialListener2.getTextAsText();
         String[] textArray = specialListener2.getTextArray();
         String text2 = specialListener2.getText();
         String text3 = specialListener2.getText3();
         if (text != null && !text.isEmpty()) {
            builder.append("Display name: ").append(text).append('\n');
         }

         if (textAsText != null && !textAsText.isEmpty()) {
            builder.append("Main author: ").append(textAsText).append('\n');
         }

         if (textArray != null && textArray.length > 0) {
            builder.append("Authors: ");

            for (int index = 0; index < textArray.length; index++) {
               if (!textArray[index].equals(textAsText)) {
                  if (index <= textArray.length - 2) {
                     builder.append(", ");
                  } else if (index == textArray.length - 1) {
                     builder.append(", and ");
                  } else {
                     builder.append('\n');
                  }
               }
            }
         }

         if (text2 != null && !text2.isEmpty()) {
            builder.append("URL: ").append(text2).append('\n');
         }

         if (text3 != null && !text3.isEmpty()) {
            builder.append("Description: \n").append(text3).append('\n');
         }
      }

      builder.append("Main class: ").append(this.specialListener.getClass().getName()).append('\n');
      builder.append("Containing file: ");

      try {
         String path = new File(this.specialListener.getClass().getProtectionDomain().getCodeSource().getLocation().toURI()).getPath();
         String property = System.getProperty("user.name");
         String text4 = path.replace(property, "<USERNAME>");
         builder.append(text4);
      } catch (Exception exception) {
         builder.append("Unknown (").append(exception).append(')');
      }

      builder.append('\n');
      Class[] interfaces = this.specialListener.getClass().getInterfaces();
      builder.append("Implemented interfaces (").append(interfaces.length).append(")\n");

      for (int index2 = 0; index2 < interfaces.length; index2++) {
         builder.append(index2).append(": ").append(interfaces[index2].getName()).append('\n');
      }

      builder.append("Superclass: ").append(this.specialListener.getClass().getSuperclass().getName()).append('\n');
      ClassLoader classLoader = this.specialListener.getClass().getClassLoader();
      builder.append("Classloader: ").append(classLoader);
      if (classLoader != null) {
         builder.append(" (").append(classLoader.getClass().getName()).append(')');
      }

      builder.append('\n');
      java.lang.annotation.Annotation[] annotations = this.specialListener.getClass().getAnnotations();
      builder.append("Annotations (").append(annotations.length).append(")\n");

      for (int index3 = 0; index3 < annotations.length; index3++) {
         builder.append(index3).append(": ").append(annotations[index3].toString()).append(" (").append(annotations[index3].annotationType().getName()).append(")\n");
      }

      return builder.toString();
   }

   public boolean isActive() {
      return Pokechu22WorldDownloaderManager.properties3.getProperty("Extensions." + this.labelText + ".enabled", "true").equals("true");
   }

   public StringEntry(String text, String text2, SpecialListener specialListener2, HeuristicDispatcher heuristicDispatcher) {
      this(text, text2, specialListener2);
   }

   private StringEntry(String text, String text2, SpecialListener specialListener2) {
      this.labelText = text;
      this.labelText2 = text2;
      this.specialListener = specialListener2;
   }

   @Override
   public String toString() {
      return this.labelText + "v" + this.labelText2 + " (" + this.specialListener.toString() + "/" + this.specialListener.getClass().getName() + ")";
   }
}
