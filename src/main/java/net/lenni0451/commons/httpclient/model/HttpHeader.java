package net.lenni0451.commons.httpclient.model;

import java.util.Objects;
import javax.annotation.Nonnull;

public class HttpHeader {
   @Nonnull
   private final String name;
   @Nonnull
   private final String value;

   public HttpHeader(@Nonnull String name, @Nonnull String value) {
      this.name = name;
      this.value = value;
   }

   @Nonnull
   public String getName() {
      return this.name;
   }

   @Nonnull
   public String getValue() {
      return this.value;
   }

   @Override
   public String toString() {
      return "HttpHeader{name='" + this.name + '\'' + ", value='" + this.value + '\'' + '}';
   }

   @Override
   public boolean equals(Object o) {
      if (this == o) {
         return true;
      } else if (o != null && this.getClass() == o.getClass()) {
         HttpHeader that = (HttpHeader)o;
         return Objects.equals(this.name, that.name) && Objects.equals(this.value, that.value);
      } else {
         return false;
      }
   }

   @Override
   public int hashCode() {
      return Objects.hash(this.name, this.value);
   }
}
