# Term.java

A simple utility class for ANSI terminals.

*   Nothing in this library prints anything. Instead, you call methods to get
    escape sequences that your code prints to the terminal.
*   The main two methods are `setMode()` and `unsetMode()`. Both methods return
    escape sequences in a `String`. For example,
    `System.out.print(Term.setMode(Mode.BOLD))` turns on bold colors.
*   Use `setModes()` and `unsetModes()` to set and unset multiple modes at a
    time, with the ability to mix graphics modes and colors. For example,
    `System.out.print(Term.setModes(Mode.ITALIC, Color.RED, Background.BLUE))`
    turns on italic, red text over a blue background.
*   Each mode and color has an associated helper method that takes a string and
    returns that string surrounded by the appropriate escape sequences. For
    example, `System.out.print(Term.magenta("foo") + "bar")` prints `foobar`
    but `foo` is printed in magenta text and `bar` is the default color.
*   Use `compose(Mode... modes)` to create your own helper function that takes
    a string and returns that string surrounded by escape sequences. The return
    value of `compose()` is a `Function<String, String>` which can be executed
    by calling its `apply()` method. For example:

    ```java
    Function<String, String> func;
    func = Term.compose(Color.GREEN_BRIGHT, Background.BLUE, Mode.BOLD)
    System.out.print(func.apply("bold green text on a blue background"));
    ```
*   This library supports 16 colors (8 normal and 8 bright colors). See below
    for the full list of color constants.

## Mode constants

```java
Mode.BOLD = new Mode(1);
Mode.DIM = new Mode(2);
Mode.ITALIC = new Mode(3);
Mode.UNDERLINE = new Mode(4);
Mode.BLINK = new Mode(5);
Mode.INVERT = new Mode(7);
Mode.HIDDEN = new Mode(8);
Mode.STRIKETHROUGH = new Mode(9);
```

## Color constants

```java
Color.DEFAULT = new Color(39);
Color.BLACK = new Color(30);
Color.RED = new Color(31);
Color.GREEN = new Color(32);
Color.YELLOW = new Color(33);
Color.BLUE = new Color(34);
Color.MAGENTA = new Color(35);
Color.CYAN = new Color(36);
Color.WHITE = new Color(37);
Color.BLACK_BRIGHT = new Color(90);
Color.RED_BRIGHT = new Color(91);
Color.GREEN_BRIGHT = new Color(92);
Color.YELLOW_BRIGHT = new Color(93);
Color.BLUE_BRIGHT = new Color(94);
Color.MAGENTA_BRIGHT = new Color(95);
Color.CYAN_BRIGHT = new Color(96);
Color.WHITE_BRIGHT = new Color(97);
```

```java
Background.DEFAULT = new Background(49);
Background.BLACK = new Background(40);
Background.RED = new Background(41);
Background.GREEN = new Background(42);
Background.YELLOW = new Background(43);
Background.BLUE = new Background(44);
Background.MAGENTA = new Background(45);
Background.CYAN = new Background(46);
Background.WHITE = new Background(47);
Background.BLACK_BRIGHT = new Background(100);
Background.RED_BRIGHT = new Background(101);
Background.GREEN_BRIGHT = new Background(102);
Background.YELLOW_BRIGHT = new Background(103);
Background.BLUE_BRIGHT = new Background(104);
Background.MAGENTA_BRIGHT = new Background(105);
Background.CYAN_BRIGHT = new Background(106);
Background.WHITE_BRIGHT = new Background(107);
```
