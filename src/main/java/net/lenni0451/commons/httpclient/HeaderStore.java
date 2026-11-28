package net.lenni0451.commons.httpclient;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import net.lenni0451.commons.httpclient.model.HttpHeader;

public abstract class HeaderStore<T extends HeaderStore<T>> {
   private final Map<String, List<String>> headers = new HashMap<>();

   public HeaderStore() {
   }

   public HeaderStore(Map<String, List<String>> headers) {
      headers.forEach((k, v) -> this.headers.put(k.toLowerCase(Locale.ROOT), new ArrayList<>(v)));
   }

   public Map<String, List<String>> getHeaders() {
      return Collections.unmodifiableMap(this.headers.entrySet().stream().collect(Collectors.toMap(Entry::getKey, e -> new ArrayList<>(e.getValue()))));
   }

   public List<String> getHeader(String name) {
      return this.headers.get(name.toLowerCase());
   }

   public Optional<String> getFirstHeader(String name) {
      List<String> values = this.headers.get(name.toLowerCase(Locale.ROOT));
      return values != null && !values.isEmpty() ? Optional.of(values.get(0)) : Optional.empty();
   }

   public Optional<String> getLastHeader(String name) {
      List<String> values = this.headers.get(name.toLowerCase(Locale.ROOT));
      return values != null && !values.isEmpty() ? Optional.of(values.get(values.size() - 1)) : Optional.empty();
   }

   public T appendHeader(String name, String value) {
      this.headers.computeIfAbsent(name.toLowerCase(Locale.ROOT), n -> new ArrayList<>()).add(value);
      return (T)this;
   }

   public T appendHeader(HttpHeader... headers) {
      for (HttpHeader header : headers) {
         this.appendHeader(header.getName(), header.getValue());
      }

      return (T)this;
   }

   public T appendHeader(Collection<HttpHeader> headers) {
      for (HttpHeader header : headers) {
         this.appendHeader(header.getName(), header.getValue());
      }

      return (T)this;
   }

   public T setHeader(String name, String value) {
      List<String> values = new ArrayList<>();
      values.add(value);
      this.headers.put(name.toLowerCase(Locale.ROOT), values);
      return (T)this;
   }

   public T setHeader(HttpHeader... headers) {
      for (HttpHeader h : headers) {
         this.setHeader(h.getName(), h.getValue());
      }

      return (T)this;
   }

   public T setHeader(Collection<HttpHeader> headers) {
      for (HttpHeader h : headers) {
         this.setHeader(h.getName(), h.getValue());
      }

      return (T)this;
   }

   public T removeHeader(String name) {
      this.headers.remove(name.toLowerCase(Locale.ROOT));
      return (T)this;
   }

   public T clearHeaders() {
      this.headers.clear();
      return (T)this;
   }

   public boolean hasHeader(String name) {
      return this.headers.containsKey(name.toLowerCase(Locale.ROOT));
   }

   public boolean hasHeader(String name, String value) {
      return this.headers.get(name.toLowerCase(Locale.ROOT)).contains(value);
   }

   public boolean hasHeader(HttpHeader header) {
      return this.hasHeader(header.getName(), header.getValue());
   }
}
