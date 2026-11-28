package wdl.api;

import wdl.WDL;
import java.io.File;
import java.lang.annotation.Annotation;

public class ModInfo {
   public final String id;
   public final String version;
   public final IWDLMod mod;

   public void setEnabled(boolean flag) {
      WDL.globalProps.setProperty("Extensions." + this.id + ".enabled", Boolean.toString(flag));
      WDL.saveGlobalProps();
   }

   @Override
   public String toString() {
      return this.id + "v" + this.version + " (" + this.mod.toString() + "/" + this.mod.getClass().getName() + ")";
   }

   public ModInfo(String str, String str2, IWDLMod wdlMod, WDLApiSyntheticMarker wdlApiSyntheticMarker) {
      this(str, str2, wdlMod);
   }

   public String getInfo() {
      StringBuilder sb = new StringBuilder();
      sb.append("Id: ").append(this.id).append('\n');
      sb.append("Version: ").append(this.version).append('\n');
      if (this.mod instanceof IWDLModDescripted) {
         IWDLModDescripted mod2 = (IWDLModDescripted)this.mod;
         String displayName = mod2.getDisplayName();
         String mainAuthor = mod2.getMainAuthor();
         String[] authors = mod2.getAuthors();
         String url = mod2.getURL();
         String description = mod2.getDescription();
         if (displayName != null && !displayName.isEmpty()) {
            sb.append("Display name: ").append(displayName).append('\n');
         }

         if (mainAuthor != null && !mainAuthor.isEmpty()) {
            sb.append("Main author: ").append(mainAuthor).append('\n');
         }

         if (authors != null && authors.length > 0) {
            sb.append("Authors: ");

            for (int i = 0; i < authors.length; i++) {
               if (!authors[i].equals(mainAuthor)) {
                  if (i <= authors.length - 2) {
                     sb.append(", ");
                  } else if (i == authors.length - 1) {
                     sb.append(", and ");
                  } else {
                     sb.append('\n');
                  }
               }
            }
         }

         if (url != null && !url.isEmpty()) {
            sb.append("URL: ").append(url).append('\n');
         }

         if (description != null && !description.isEmpty()) {
            sb.append("Description: \n").append(description).append('\n');
         }
      }

      sb.append("Main class: ").append(this.mod.getClass().getName()).append('\n');
      sb.append("Containing file: ");

      try {
         String path = new File(this.mod.getClass().getProtectionDomain().getCodeSource().getLocation().toURI()).getPath();
         path = path.replace(System.getProperty("user.name"), "<USERNAME>");
         sb.append(path);
      } catch (Exception e) {
         sb.append("Unknown (").append(e).append(')');
      }

      sb.append('\n');
      Class[] interfaces = this.mod.getClass().getInterfaces();
      sb.append("Implemented interfaces (").append(interfaces.length).append(")\n");

      for (int j = 0; j < interfaces.length; j++) {
         sb.append(j).append(": ").append(interfaces[j].getName()).append('\n');
      }

      sb.append("Superclass: ").append(this.mod.getClass().getSuperclass().getName()).append('\n');
      ClassLoader classLoader = this.mod.getClass().getClassLoader();
      sb.append("Classloader: ").append(classLoader);
      if (classLoader != null) {
         sb.append(" (").append(classLoader.getClass().getName()).append(')');
      }

      sb.append('\n');
      Annotation[] annotations = this.mod.getClass().getAnnotations();
      sb.append("Annotations (").append(annotations.length).append(")\n");

      for (int k = 0; k < annotations.length; k++) {
         sb.append(k).append(": ").append(annotations[k].toString()).append(" (").append(annotations[k].annotationType().getName()).append(")\n");
      }

      return sb.toString();
   }

   public boolean isEnabled() {
      return WDL.globalProps.getProperty("Extensions." + this.id + ".enabled", "true").equals("true");
   }

   private ModInfo(String str, String str2, IWDLMod wdlMod) {
      this.id = str;
      this.version = str2;
      this.mod = wdlMod;
   }

   public String getDisplayName() {
      if (this.mod instanceof IWDLModDescripted) {
         String displayName = ((IWDLModDescripted)this.mod).getDisplayName();
         if (displayName != null && !displayName.isEmpty()) {
            return displayName;
         }
      }

      return this.id;
   }

   public void toggleEnabled() {
      this.setEnabled(!this.isEnabled());
   }
}
