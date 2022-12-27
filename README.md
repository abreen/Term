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

        :::java
        Function<String, String> func;
        func = Term.compose(Color.GREEN_BRIGHT, Background.BLUE, Mode.BOLD)
        System.out.print(func.apply("bold green text on a blue background"));
