package baritone.command.argument;

import baritone.api.IBaritone;
import baritone.api.command.datatypes.IDatatype;
import baritone.api.command.datatypes.IDatatypeContext;
import baritone.api.command.datatypes.IDatatypeFor;
import baritone.api.command.datatypes.IDatatypePost;
import baritone.api.command.exception.CommandException;
import baritone.api.command.exception.CommandInvalidTypeException;
import baritone.api.command.exception.CommandNotEnoughArgumentsException;
import baritone.api.command.exception.CommandTooManyArgumentsException;
import baritone.api.command.manager.ICommandManager;
import baritone.api.command.argument.IArgConsumer;
import baritone.api.command.argument.ICommandArgument;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;

public class ArgConsumer implements IArgConsumer {
   private final ICommandManager manager;
   private final IDatatypeContext context;
   private final LinkedList args;
   private final Deque consumed;

   @Override
   public ICommandArgument consumed() {
      return (ICommandArgument)(this.consumed.size() > 0 ? (ICommandArgument)this.consumed.getLast() : CommandArguments.createUnknown());
   }

   @Override
   public boolean hasExactlyOne() {
      return this.hasExactly(1);
   }

   @Override
   public Object peekDatatypeOrNull2(IDatatypePost datatypePost) {
      return this.copy2().getDatatypePostOrNull(datatypePost, null);
   }

   @Override
   public Object getDatatypePostOrDefault(IDatatypePost datatypePost, Object obj, Object obj2) {
      ArrayList list = new ArrayList(this.args);
      ArrayList list2 = new ArrayList(this.consumed);

      try {
         return this.getDatatypePost(datatypePost, obj);
      } catch (Exception e) {
         this.args.clear();
         this.args.addAll(list);
         this.consumed.clear();
         this.consumed.addAll(list2);
         return obj2;
      }
   }

   @Override
   public String consumedString() {
      return this.consumed().getValue();
   }

   @Override
   public Object peekDatatype(IDatatypePost datatypePost) throws CommandInvalidTypeException, CommandNotEnoughArgumentsException {
      return this.peekDatatype(datatypePost, null);
   }

   @Override
   public String peekString() throws CommandNotEnoughArgumentsException {
      return this.peekString(0);
   }

   @Override
   public Object peekDatatype(IDatatypePost datatypePost, Object obj) throws CommandInvalidTypeException, CommandNotEnoughArgumentsException {
      return this.copy2().getDatatypePost(datatypePost, obj);
   }

   @Override
   public Enum getEnumOrNull(Class clazz) throws CommandNotEnoughArgumentsException {
      return this.getEnumOrDefault(clazz, null);
   }

   @Override
   public Object peekDatatypeForOrNull(Class clazz) {
      return this.peekDatatypeForOrDefault(clazz, null);
   }

   @Override
   public Object peekDatatypeForOrDefault(Class clazz, Object obj) {
      return this.copy2().peekDatatypeForOrDefault(clazz, obj);
   }

   @Override
   public boolean has(int n) {
      return this.args.size() >= n;
   }

   @Override
   public LinkedList getArgs() {
      return this.args;
   }

   @Override
   public boolean hasAtMost(int n) {
      return this.args.size() <= n;
   }

   @Override
   public boolean is(Class clazz, int n) throws CommandNotEnoughArgumentsException {
      return this.peek(n).is(clazz);
   }

   @Override
   public void requireMin(int n) throws CommandNotEnoughArgumentsException {
      if (this.args.size() < n) {
         throw new CommandNotEnoughArgumentsException(n + this.consumed.size());
      }
   }

   @Override
   public Enum getEnumOrDefault(Class clazz, Enum obj) throws CommandNotEnoughArgumentsException {
      try {
         this.peekEnum(clazz);
         return this.getEnum(clazz);
      } catch (CommandInvalidTypeException e) {
         return obj;
      }
   }

   @Override
   public Object getDatatypeForOrNull(IDatatypeFor datatypeFor) {
      return this.getDatatypeForOrDefault(datatypeFor, null);
   }

   @Override
   public Object getDatatypePostOrNull(IDatatypePost datatypePost, Object obj) {
      return this.getDatatypePostOrDefault(datatypePost, obj, null);
   }

   @Override
   public Object getDatatypeForOrDefault(IDatatypeFor datatypeFor, Object obj) {
      ArrayList list = new ArrayList(this.args);
      ArrayList list2 = new ArrayList(this.consumed);

      try {
         return this.getDatatypeFor(datatypeFor);
      } catch (Exception e) {
         this.args.clear();
         this.args.addAll(list);
         this.consumed.clear();
         this.consumed.addAll(list2);
         return obj;
      }
   }

   @Override
   public Object getAs(Class clazz) throws CommandInvalidTypeException, CommandNotEnoughArgumentsException {
      return this.get().getAs(clazz);
   }

   public static ICommandManager getManager(ArgConsumer argConsumer) {
      return argConsumer.manager;
   }

   @Override
   public Enum peekEnum(Class clazz) throws CommandInvalidTypeException, CommandNotEnoughArgumentsException {
      return this.peekEnum(clazz, 0);
   }

   @Override
   public String rawRest() {
      return this.args.size() > 0 ? ((ICommandArgument)this.args.getFirst()).getRawRest() : "";
   }

   private ArgConsumer(ICommandManager commandManager, Deque deque, Deque deque2) {
      this.manager = commandManager;
      this.context = new ArgConsumer.Context(this, null);
      this.args = new LinkedList(deque);
      this.consumed = new LinkedList(deque2);
   }

   @Override
   public Object getAsOrNull(Class clazz) throws CommandNotEnoughArgumentsException {
      return this.getAsOrDefault(clazz, null);
   }

   @Override
   public Enum peekEnumOrNull(Class clazz, int n) throws CommandNotEnoughArgumentsException {
      try {
         return this.peekEnum(clazz, n);
      } catch (CommandInvalidTypeException e) {
         return null;
      }
   }

   @Override
   public Object peekAs(Class clazz, int n) throws CommandInvalidTypeException, CommandNotEnoughArgumentsException {
      return this.peek(n).getAs(clazz);
   }

   @Override
   public Object peekDatatypeOrNull(IDatatypeFor datatypeFor) {
      return this.copy2().getDatatypeForOrNull(datatypeFor);
   }

   @Override
   public ICommandArgument peek() throws CommandNotEnoughArgumentsException {
      return this.peek(0);
   }

   @Override
   public boolean hasAtMostOne() {
      return this.hasAtMost(1);
   }

   @Override
   public void requireExactly(int n) throws CommandException {
      this.requireMin(n);
      this.requireMax(n);
   }

   @Override
   public Object getDatatypePost(IDatatypePost datatypePost, Object obj) throws CommandInvalidTypeException, CommandNotEnoughArgumentsException {
      try {
         return datatypePost.apply(this.context, obj);
      } catch (Exception e) {
         throw new CommandInvalidTypeException(this.hasAny() ? this.peek() : this.consumed(), datatypePost.getClass().getSimpleName(), e);
      }
   }

   @Override
   public Object peekDatatypeFor(Class clazz) {
      return this.copy2().peekDatatypeFor(clazz);
   }

   @Override
   public Object peekAsOrNull(Class clazz) throws CommandNotEnoughArgumentsException {
      return this.peekAsOrNull(clazz, 0);
   }

   @Override
   public Object peekAsOrDefault(Class clazz, Object obj, int n) throws CommandNotEnoughArgumentsException {
      try {
         return this.peekAs(clazz, n);
      } catch (CommandInvalidTypeException e) {
         return obj;
      }
   }

   @Override
   public String getString() throws CommandNotEnoughArgumentsException {
      return this.get().getValue();
   }

   @Override
   public Object peekAsOrDefault(Class clazz, Object obj) throws CommandNotEnoughArgumentsException {
      return this.peekAsOrDefault(clazz, obj, 0);
   }

   @Override
   public ICommandArgument peek(int n) throws CommandNotEnoughArgumentsException {
      this.requireMin(n + 1);
      return (ICommandArgument)this.args.get(n);
   }

   @Override
   public IArgConsumer copy() {
      return this.copy2();
   }

   @Override
   public void requireMax(int n) throws CommandTooManyArgumentsException {
      if (this.args.size() > n) {
         throw new CommandTooManyArgumentsException(n + this.consumed.size());
      }
   }

   public ArgConsumer(ICommandManager commandManager, List list) {
      this(commandManager, new LinkedList(list), new LinkedList());
   }

   @Override
   public Object peekAsOrNull(Class clazz, int n) throws CommandNotEnoughArgumentsException {
      return this.peekAsOrDefault(clazz, null, n);
   }

   @Override
   public boolean hasAny() {
      return this.has(1);
   }

   @Override
   public Object peekDatatypePostOrDefault(IDatatypePost datatypePost, Object obj, Object obj2) {
      return this.copy2().getDatatypePostOrDefault(datatypePost, obj, obj2);
   }

   @Override
   public boolean is(Class clazz) throws CommandNotEnoughArgumentsException {
      return this.is(clazz, 0);
   }

   @Override
   public Object peekDatatype2(IDatatypeFor datatypeFor) throws CommandInvalidTypeException, CommandNotEnoughArgumentsException {
      return this.copy2().getDatatypeFor(datatypeFor);
   }

   @Override
   public Enum getEnum(Class clazz) throws CommandInvalidTypeException, CommandNotEnoughArgumentsException {
      return this.get().getEnum(clazz);
   }

   @Override
   public Object getDatatypeFor(IDatatypeFor datatypeFor) throws CommandInvalidTypeException, CommandNotEnoughArgumentsException {
      try {
         return datatypeFor.get(this.context);
      } catch (Exception e) {
         throw new CommandInvalidTypeException(this.hasAny() ? this.peek() : this.consumed(), datatypeFor.getClass().getSimpleName(), e);
      }
   }

   @Override
   public Stream tabCompleteDatatype(IDatatype datatype) {
      try {
         return datatype.tabComplete(this.context);
      } catch (CommandException e) {
      } catch (Exception e2) {
         e2.printStackTrace();
      }

      return Stream.empty();
   }

   public ArgConsumer copy2() {
      return new ArgConsumer(this.manager, this.args, this.consumed);
   }

   @Override
   public String peekString(int n) throws CommandNotEnoughArgumentsException {
      return this.peek(n).getValue();
   }

   @Override
   public Enum peekEnum(Class clazz, int n) throws CommandInvalidTypeException, CommandNotEnoughArgumentsException {
      return this.peek(n).getEnum(clazz);
   }

   @Override
   public Deque getConsumed() {
      return this.consumed;
   }

   @Override
   public boolean hasExactly(int n) {
      return this.args.size() == n;
   }

   @Override
   public Object peekDatatypePost(IDatatypePost datatypePost, Object obj) throws CommandInvalidTypeException, CommandNotEnoughArgumentsException {
      return this.copy2().getDatatypePost(datatypePost, obj);
   }

   @Override
   public Enum peekEnumOrNull(Class clazz) throws CommandNotEnoughArgumentsException {
      return this.peekEnumOrNull(clazz, 0);
   }

   @Override
   public ICommandArgument get() throws CommandNotEnoughArgumentsException {
      this.requireMin(1);
      ICommandArgument commandArgument = (ICommandArgument)this.args.removeFirst();
      this.consumed.add(commandArgument);
      return commandArgument;
   }

   @Override
   public Object peekAs(Class clazz) throws CommandInvalidTypeException, CommandNotEnoughArgumentsException {
      return this.peekAs(clazz, 0);
   }

   @Override
   public boolean hasConsumed() {
      return !this.consumed.isEmpty();
   }

   @Override
   public Object peekDatatypePostOrNull(IDatatypePost datatypePost, Object obj) {
      return this.peekDatatypePostOrDefault(datatypePost, obj, null);
   }

   @Override
   public Object getAsOrDefault(Class clazz, Object obj) throws CommandNotEnoughArgumentsException {
      try {
         Object as = this.peek().getAs(clazz);
         this.get();
         return as;
      } catch (CommandInvalidTypeException e) {
         return obj;
      }
   }


   public static class InnerEntry {
   }

   public static final class Context implements IDatatypeContext {
      public final ArgConsumer consumer;

      public Context(ArgConsumer argConsumer, ArgConsumer.InnerEntry obj) {
         this(argConsumer);
      }

      @Override
      public final IBaritone getBaritone() {
         return ArgConsumer.getManager(this.consumer).getBaritone();
      }

      private Context(ArgConsumer argConsumer) {
         this.consumer = argConsumer;
      }

      @Override
      public IArgConsumer getConsumer() {
         return this.getConsumer2();
      }

      public final ArgConsumer getConsumer2() {
         return this.consumer;
      }
   }
}
