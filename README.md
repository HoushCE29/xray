# X-Ray
A little library for X-Raying through Java objects. This library is useful for testing, especially when dealing with 3rd party libraries.

## Usage
The X-Ray library consumes two arguments: an object to X-Ray, and a path to X-Ray through. The path argument is written in dotted notation.

```java
Object valueOfBaz = Xray.xray(myObject, "foo.bar.baz");
```

In this code snippet, the value is snagged from the path, starting from the object instance `myObject`. Assuming this is being used correctly, the following statements are all true:
  - `myObject`'s class (or some super-class) has a field named `foo`, or if `myObject` is a `java.util.Map`, then `foo` is a key.
  - `foo`'s class (or some super-class) has a field named `bar`, or if `foo` is a `java.util.Map`, then `bar` is a key.
  - `bar`'s class (or some super-class) has a field named `baz`, or if `bar` is a `java.util.Map`, then `baz` is a key.

Additionally, arrays and `java.util.Collection` can be handled with array notation (e.g. `myArray[index]`).
For example:
```java
Object value = Xray.xray(myObject, "foo.twoDimensionArray[1][2].something.list[9]");
```

## Features
### Not yet released
  - Basic dotted-notation parsing.
  - Support for fields.
  - Support for maps.
  - Basic array-notation parsing.
  - Support for arrays and collections.
