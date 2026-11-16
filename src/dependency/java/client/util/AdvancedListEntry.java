package client.util;

import client.setting.ProviderOptionType;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class AdvancedListEntry {
   public List list;
   public Object dataRef2;
   public ProviderOptionType providerOptionType = ProviderOptionType.Linear;
   public Double memberRef;
   public Object dataRef;

   public Object getDataRef2() {
      return this.dataRef2;
   }

   public Double getMemberRef() {
      return this.memberRef;
   }

   public void setMemberRef(Double memberRef2) {
      this.memberRef = memberRef2;
   }

   public Object getDataRef() {
      return this.dataRef;
   }

   public void setDataRef(Object dataRef3) {
      this.dataRef = dataRef3;
   }

   public void setDataRef2(Object dataRef3) {
      this.dataRef2 = dataRef3;
   }

   public AdvancedListEntry(Double offset, Object entry, Object entry2) {
      this.list = new ArrayList();
      this.memberRef = offset;
      this.dataRef2 = entry;
      this.dataRef = entry2;
   }

   public AdvancedListEntry(Double offset, Object entry, Object entry2, ProviderOptionType providerOptionType2) {
      this.list = new ArrayList();
      this.memberRef = offset;
      this.dataRef2 = entry;
      this.dataRef = entry2;
      this.providerOptionType = providerOptionType2;
   }

   public AdvancedListEntry(Double offset, Object entry, Object entry2, ProviderOptionType providerOptionType2, List list2) {
      this.list = new ArrayList();
      this.memberRef = offset;
      this.dataRef2 = entry;
      this.dataRef = entry2;
      this.providerOptionType = providerOptionType2;
      this.list = list2;
   }

   @Override
   public boolean equals(Object other) {
      return other instanceof AdvancedListEntry && this.hashCode() == other.hashCode();
   }

   @Override
   public int hashCode() {
      return Objects.hash(this.memberRef, this.dataRef2, this.dataRef);
   }
}
