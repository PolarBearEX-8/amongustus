package baritone.api.command.argument;

import baritone.api.command.datatypes.IDatatype;
import baritone.api.command.datatypes.IDatatypeFor;
import baritone.api.command.datatypes.IDatatypePost;
import baritone.api.command.exception.CommandException;
import baritone.api.command.exception.CommandInvalidTypeException;
import baritone.api.command.exception.CommandNotEnoughArgumentsException;
import baritone.api.command.exception.CommandTooManyArgumentsException;
import java.util.Deque;
import java.util.LinkedList;
import java.util.stream.Stream;

public interface IArgConsumer {
   LinkedList getArgs();

   Object peekDatatypeOrNull2(IDatatypePost datatypePost);

   Object peekAsOrNull(Class clazz, int n) throws CommandNotEnoughArgumentsException;

   void requireMin(int n) throws CommandNotEnoughArgumentsException;

   Object peekAsOrNull(Class clazz) throws CommandNotEnoughArgumentsException;

   ICommandArgument peek(int n) throws CommandNotEnoughArgumentsException;

   Object getDatatypeFor(IDatatypeFor datatypeFor) throws CommandInvalidTypeException, CommandNotEnoughArgumentsException;

   boolean hasAny();

   String peekString() throws CommandNotEnoughArgumentsException;

   Enum peekEnumOrNull(Class clazz) throws CommandNotEnoughArgumentsException;

   boolean hasExactly(int n);

   Object peekDatatypeForOrDefault(Class clazz, Object obj);

   Object peekDatatypePostOrDefault(IDatatypePost datatypePost, Object obj, Object obj2);

   String consumedString();

   boolean is(Class clazz, int n) throws CommandNotEnoughArgumentsException;

   Object getDatatypePost(IDatatypePost datatypePost, Object obj) throws CommandInvalidTypeException, CommandNotEnoughArgumentsException;

   boolean is(Class clazz) throws CommandNotEnoughArgumentsException;

   Object peekDatatypePostOrNull(IDatatypePost datatypePost, Object obj);

   Enum getEnum(Class clazz) throws CommandInvalidTypeException, CommandNotEnoughArgumentsException;

   ICommandArgument consumed();

   boolean hasConsumed();

   Object getAs(Class clazz) throws CommandInvalidTypeException, CommandNotEnoughArgumentsException;

   Object peekDatatypeForOrNull(Class clazz);

   boolean hasAtMost(int n);

   String rawRest();

   boolean hasExactlyOne();

   Object peekDatatype2(IDatatypeFor datatypeFor) throws CommandInvalidTypeException, CommandNotEnoughArgumentsException;

   Object peekDatatypeOrNull(IDatatypeFor datatypeFor);

   Object getAsOrDefault(Class clazz, Object obj) throws CommandNotEnoughArgumentsException;

   boolean hasAtMostOne();

   Enum peekEnum(Class clazz, int n) throws CommandInvalidTypeException, CommandNotEnoughArgumentsException;

   ICommandArgument get() throws CommandNotEnoughArgumentsException;

   Stream tabCompleteDatatype(IDatatype datatype);

   Object getDatatypeForOrNull(IDatatypeFor datatypeFor);

   Object getDatatypePostOrNull(IDatatypePost datatypePost, Object obj);

   void requireExactly(int n) throws CommandException;

   Object peekDatatypePost(IDatatypePost datatypePost, Object obj) throws CommandInvalidTypeException, CommandNotEnoughArgumentsException;

   void requireMax(int n) throws CommandTooManyArgumentsException;

   Enum peekEnum(Class clazz) throws CommandInvalidTypeException, CommandNotEnoughArgumentsException;

   ICommandArgument peek() throws CommandNotEnoughArgumentsException;

   Object peekAsOrDefault(Class clazz, Object obj) throws CommandNotEnoughArgumentsException;

   Enum getEnumOrDefault(Class clazz, Enum obj) throws CommandNotEnoughArgumentsException;

   String getString() throws CommandNotEnoughArgumentsException;

   Object peekDatatypeFor(Class clazz);

   String peekString(int n) throws CommandNotEnoughArgumentsException;

   Object peekAs(Class clazz, int n) throws CommandInvalidTypeException, CommandNotEnoughArgumentsException;

   Object peekDatatype(IDatatypePost datatypePost) throws CommandInvalidTypeException, CommandNotEnoughArgumentsException;

   Enum peekEnumOrNull(Class clazz, int n) throws CommandNotEnoughArgumentsException;

   Object peekAsOrDefault(Class clazz, Object obj, int n) throws CommandNotEnoughArgumentsException;

   Enum getEnumOrNull(Class clazz) throws CommandNotEnoughArgumentsException;

   IArgConsumer copy();

   boolean has(int n);

   Object getDatatypePostOrDefault(IDatatypePost datatypePost, Object obj, Object obj2);

   Object getDatatypeForOrDefault(IDatatypeFor datatypeFor, Object obj);

   Object peekDatatype(IDatatypePost datatypePost, Object obj) throws CommandInvalidTypeException, CommandNotEnoughArgumentsException;

   Deque getConsumed();

   Object getAsOrNull(Class clazz) throws CommandNotEnoughArgumentsException;

   Object peekAs(Class clazz) throws CommandInvalidTypeException, CommandNotEnoughArgumentsException;
}
