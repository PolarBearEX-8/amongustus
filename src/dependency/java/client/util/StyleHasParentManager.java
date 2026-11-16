package client.util;

import client.setting.ClientSettingsBlackType;

public class StyleHasParentManager {
   public StyleHasParentManager styleHasParentManager2;
   public ClientSettingsBlackType clientSettingsBlackType;
   public Boolean memberRef2;
   public Boolean memberRef5;
   public Boolean memberRef3;
   public Boolean memberRef;
   public Boolean memberRef4;
   public ClickEventActionManager clickEventActionManager;
   public HoverEventActionManager hoverEventActionManager;
   public String labelText;
   public static final StyleHasParentManager styleHasParentManager = new StyleROOTStyleHasParentManager();

   public StyleHasParentManager withMemberRef3(Boolean flag) {
      this.memberRef3 = flag;
      return this;
   }

   public static Boolean resolveBoolean3(StyleHasParentManager styleHasParentManager3, Boolean flag) {
      return styleHasParentManager3.memberRef2 = flag;
   }

   public static Boolean resolveBoolean2(StyleHasParentManager styleHasParentManager3) {
      return styleHasParentManager3.memberRef2;
   }

   public StyleHasParentManager withClientSettingsBlackType(ClientSettingsBlackType clientSettingsBlackType2) {
      this.clientSettingsBlackType = clientSettingsBlackType2;
      return this;
   }

   public boolean isActive3() {
      return this.memberRef5 == null ? this.getStyleHasParentManagerAsStyleHasParentMa().isActive3() : this.memberRef5;
   }

   private StyleHasParentManager getStyleHasParentManagerAsStyleHasParentMa() {
      return this.styleHasParentManager2 == null ? styleHasParentManager : this.styleHasParentManager2;
   }

   public HoverEventActionManager getHoverEventActionManager() {
      return this.hoverEventActionManager == null
         ? this.getStyleHasParentManagerAsStyleHasParentMa().getHoverEventActionManager()
         : this.hoverEventActionManager;
   }

   public static ClientSettingsBlackType resolveClientSettingsBlackType(StyleHasParentManager styleHasParentManager3, ClientSettingsBlackType clientSettingsBlackType2) {
      return styleHasParentManager3.clientSettingsBlackType = clientSettingsBlackType2;
   }

   public ClickEventActionManager getClickEventActionManager() {
      return this.clickEventActionManager == null
         ? this.getStyleHasParentManagerAsStyleHasParentMa().getClickEventActionManager()
         : this.clickEventActionManager;
   }

   public StyleHasParentManager getStyleHasParentManager() {
      StyleHasParentManager styleHasParentManager3 = new StyleHasParentManager();
      styleHasParentManager3.withMemberRef2(this.isActive2());
      styleHasParentManager3.withMemberRef5(this.isActive3());
      styleHasParentManager3.withMemberRef(this.isActive4());
      styleHasParentManager3.withMemberRef3(this.isActiveAsFlag());
      styleHasParentManager3.withMemberRef4(this.isActive5());
      styleHasParentManager3.withClientSettingsBlackType(this.getClientSettingsBlackType());
      styleHasParentManager3.getStyleHasParentManager(this.getClickEventActionManager());
      styleHasParentManager3.getStyleHasParentManager(this.getHoverEventActionManager());
      styleHasParentManager3.withLabelText(this.getTextAsText());
      return styleHasParentManager3;
   }

   public static Boolean resolveBooleanByStyleHasParentMa(StyleHasParentManager styleHasParentManager3, Boolean flag) {
      return styleHasParentManager3.memberRef = flag;
   }

   public static Boolean resolveBoolean(StyleHasParentManager styleHasParentManager3) {
      return styleHasParentManager3.memberRef4;
   }

   public static Boolean resolveBoolean(StyleHasParentManager styleHasParentManager3, Boolean flag) {
      return styleHasParentManager3.memberRef5 = flag;
   }

   public static ClickEventActionManager resolveClickEventActionManager(StyleHasParentManager styleHasParentManager3, ClickEventActionManager event) {
      return styleHasParentManager3.clickEventActionManager = event;
   }

   public boolean isActive() {
      return this.memberRef2 == null
         && this.memberRef5 == null
         && this.memberRef == null
         && this.memberRef3 == null
         && this.memberRef4 == null
         && this.clientSettingsBlackType == null
         && this.clickEventActionManager == null
         && this.hoverEventActionManager == null;
   }

   public String getText() {
      if (this.isActive()) {
         return this.styleHasParentManager2 != null ? this.styleHasParentManager2.getText() : "";
      } else {
         StringBuilder builder = new StringBuilder();
         if (this.getClientSettingsBlackType() != null) {
            builder.append(this.getClientSettingsBlackType());
         }

         if (this.isActive2()) {
            builder.append(ClientSettingsBlackType.BOLD);
         }

         if (this.isActive3()) {
            builder.append(ClientSettingsBlackType.ITALIC);
         }

         if (this.isActiveAsFlag()) {
            builder.append(ClientSettingsBlackType.UNDERLINE);
         }

         if (this.isActive5()) {
            builder.append(ClientSettingsBlackType.OBFUSCATED);
         }

         if (this.isActive4()) {
            builder.append(ClientSettingsBlackType.STRIKETHROUGH);
         }

         return builder.toString();
      }
   }

   public StyleHasParentManager withLabelText(String text) {
      this.labelText = text;
      return this;
   }

   public static String resolveText(StyleHasParentManager styleHasParentManager3) {
      return styleHasParentManager3.labelText;
   }

   public StyleHasParentManager withMemberRef5(Boolean flag) {
      this.memberRef5 = flag;
      return this;
   }

   public static Boolean resolveBoolean2(StyleHasParentManager styleHasParentManager3, Boolean flag) {
      return styleHasParentManager3.memberRef3 = flag;
   }

   public StyleHasParentManager getStyleHasParentManager(ClickEventActionManager event) {
      this.clickEventActionManager = event;
      return this;
   }

   public boolean isActiveAsFlag() {
      return this.memberRef3 == null ? this.getStyleHasParentManagerAsStyleHasParentMa().isActiveAsFlag() : this.memberRef3;
   }

   public static Boolean resolveBooleanByStyleHasParentMa(StyleHasParentManager styleHasParentManager3) {
      return styleHasParentManager3.memberRef;
   }

   public StyleHasParentManager withMemberRef(Boolean flag) {
      this.memberRef = flag;
      return this;
   }

   public static Boolean resolveBoolean4(StyleHasParentManager styleHasParentManager3) {
      return styleHasParentManager3.memberRef5;
   }

   public static Boolean resolveBoolean4(StyleHasParentManager styleHasParentManager3, Boolean flag) {
      return styleHasParentManager3.memberRef4 = flag;
   }

   public StyleHasParentManager getStyleHasParentManager2() {
      StyleHasParentManager styleHasParentManager3 = new StyleHasParentManager();
      styleHasParentManager3.memberRef2 = this.memberRef2;
      styleHasParentManager3.memberRef5 = this.memberRef5;
      styleHasParentManager3.memberRef = this.memberRef;
      styleHasParentManager3.memberRef3 = this.memberRef3;
      styleHasParentManager3.memberRef4 = this.memberRef4;
      styleHasParentManager3.clientSettingsBlackType = this.clientSettingsBlackType;
      styleHasParentManager3.clickEventActionManager = this.clickEventActionManager;
      styleHasParentManager3.hoverEventActionManager = this.hoverEventActionManager;
      styleHasParentManager3.styleHasParentManager2 = this.styleHasParentManager2;
      styleHasParentManager3.labelText = this.labelText;
      return styleHasParentManager3;
   }

   public boolean isActive5() {
      return this.memberRef4 == null ? this.getStyleHasParentManagerAsStyleHasParentMa().isActive5() : this.memberRef4;
   }

   public boolean isActive2() {
      return this.memberRef2 == null ? this.getStyleHasParentManagerAsStyleHasParentMa().isActive2() : this.memberRef2;
   }

   public static String resolveText(StyleHasParentManager styleHasParentManager3, String text) {
      return styleHasParentManager3.labelText = text;
   }

   public ClientSettingsBlackType getClientSettingsBlackType() {
      return this.clientSettingsBlackType == null
         ? this.getStyleHasParentManagerAsStyleHasParentMa().getClientSettingsBlackType()
         : this.clientSettingsBlackType;
   }

   public static HoverEventActionManager resolveHoverEventActionManager(StyleHasParentManager styleHasParentManager3, HoverEventActionManager event) {
      return styleHasParentManager3.hoverEventActionManager = event;
   }

   public String getTextAsText() {
      return this.labelText == null ? this.getStyleHasParentManagerAsStyleHasParentMa().getTextAsText() : this.labelText;
   }

   public StyleHasParentManager withMemberRef2(Boolean flag) {
      this.memberRef2 = flag;
      return this;
   }

   public static Boolean resolveBoolean3(StyleHasParentManager styleHasParentManager3) {
      return styleHasParentManager3.memberRef3;
   }

   public boolean isActive4() {
      return this.memberRef == null ? this.getStyleHasParentManagerAsStyleHasParentMa().isActive4() : this.memberRef;
   }

   public static HoverEventActionManager resolveHoverEventActionManager(StyleHasParentManager styleHasParentManager3) {
      return styleHasParentManager3.hoverEventActionManager;
   }

   public StyleHasParentManager withMemberRef4(Boolean flag) {
      this.memberRef4 = flag;
      return this;
   }

   public StyleHasParentManager getStyleHasParentManager(HoverEventActionManager event) {
      this.hoverEventActionManager = event;
      return this;
   }

   public static ClickEventActionManager resolveClickEventActionManager(StyleHasParentManager styleHasParentManager3) {
      return styleHasParentManager3.clickEventActionManager;
   }

   public StyleHasParentManager withStyleHasParentManager2(StyleHasParentManager styleHasParentManager3) {
      this.styleHasParentManager2 = styleHasParentManager3;
      return this;
   }

   public static ClientSettingsBlackType resolveClientSettingsBlackType(StyleHasParentManager styleHasParentManager3) {
      return styleHasParentManager3.clientSettingsBlackType;
   }

   @Override
   public boolean equals(Object other) {
      if (this == other) {
         return true;
      } else if (!(other instanceof StyleHasParentManager)) {
         return false;
      } else {
         StyleHasParentManager styleHasParentManager3 = (StyleHasParentManager)other;
         return this.isActive2() == styleHasParentManager3.isActive2()
            && this.getClientSettingsBlackType() == styleHasParentManager3.getClientSettingsBlackType()
            && this.isActive3() == styleHasParentManager3.isActive3()
            && this.isActive5() == styleHasParentManager3.isActive5()
            && this.isActive4() == styleHasParentManager3.isActive4()
            && this.isActiveAsFlag() == styleHasParentManager3.isActiveAsFlag()
            && (
               this.getClickEventActionManager() != null
                  ? this.getClickEventActionManager().equals(styleHasParentManager3.getClickEventActionManager())
                  : styleHasParentManager3.getClickEventActionManager() == null
            )
            && (
               this.getHoverEventActionManager() != null
                  ? this.getHoverEventActionManager().equals(styleHasParentManager3.getHoverEventActionManager())
                  : styleHasParentManager3.getHoverEventActionManager() == null
            )
            && (this.getTextAsText() != null ? this.getTextAsText().equals(styleHasParentManager3.getTextAsText()) : styleHasParentManager3.getTextAsText() == null);
      }
   }

   @Override
   public String toString() {
      return "Style{hasParent="
         + (this.styleHasParentManager2 != null)
         + ", color="
         + this.clientSettingsBlackType
         + ", bold="
         + this.memberRef2
         + ", italic="
         + this.memberRef5
         + ", underlined="
         + this.memberRef3
         + ", obfuscated="
         + this.memberRef4
         + ", clickEvent="
         + this.getClickEventActionManager()
         + ", hoverEvent="
         + this.getHoverEventActionManager()
         + ", insertion="
         + this.getTextAsText()
         + '}';
   }

   @Override
   public int hashCode() {
      int hash = this.clientSettingsBlackType.hashCode();
      int count = 31 * hash + this.memberRef2.hashCode();
      int count2 = 31 * count + this.memberRef5.hashCode();
      int count3 = 31 * count2 + this.memberRef3.hashCode();
      int count4 = 31 * count3 + this.memberRef.hashCode();
      int count5 = 31 * count4 + this.memberRef4.hashCode();
      int count6 = 31 * count5 + this.clickEventActionManager.hashCode();
      int count7 = 31 * count6 + this.hoverEventActionManager.hashCode();
      return 31 * count7 + this.labelText.hashCode();
   }
}
