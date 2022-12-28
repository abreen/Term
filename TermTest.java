/*
 * TermTest.java
 *
 * Unit tests of the Term.java library.
 */

import java.util.function.Function;

public class TermTest {
    private static final String ESC = "\u001b";

    @Test
    public static void testResets() {
        assert Term.reset().equals(ESC + "[0m");
        assert Term.resetColor().equals(ESC + "[39m");
        assert Term.resetBackground().equals(ESC + "[49m");
    }

    @Test
    public static void testModes() {
        {
            String expected = ESC + "[1mtest" + ESC + "[22m";
            assert Term.bold("test").equals(expected);
            assert (Term.setMode(Mode.BOLD) + "test" + Term.unsetMode(Mode.BOLD))
                       .equals(expected);
        }
        {
            String expected = ESC + "[2mtest" + ESC + "[22m";
            assert Term.dim("test").equals(expected);
            assert (Term.setMode(Mode.DIM) + "test" + Term.unsetMode(Mode.DIM))
                       .equals(expected);
        }
        {
            String expected = ESC + "[3mtest" + ESC + "[23m";
            assert Term.italic("test").equals(expected);
            assert (Term.setMode(Mode.ITALIC) + "test" + Term.unsetMode(Mode.ITALIC))
                       .equals(expected);
        }
        {
            String expected = ESC + "[4mtest" + ESC + "[24m";
            assert Term.underline("test").equals(expected);
            assert (Term.setMode(Mode.UNDERLINE) +
                    "test" +
                    Term.unsetMode(Mode.UNDERLINE)).equals(expected);
        }
        {
            String expected = ESC + "[5mtest" + ESC + "[25m";
            assert Term.blink("test").equals(expected);
            assert (Term.setMode(Mode.BLINK) +
                    "test" +
                    Term.unsetMode(Mode.BLINK)).equals(expected);
        }
        {
            String expected = ESC + "[7mtest" + ESC + "[27m";
            assert Term.invert("test").equals(expected);
            assert (Term.setMode(Mode.INVERT) +
                    "test" +
                    Term.unsetMode(Mode.INVERT)).equals(expected);
        }
        {
            String expected = ESC + "[8mtest" + ESC + "[28m";
            assert Term.hidden("test").equals(expected);
            assert (Term.setMode(Mode.HIDDEN) +
                    "test" +
                    Term.unsetMode(Mode.HIDDEN)).equals(expected);
        }
        {
            String expected = ESC + "[9mtest" + ESC + "[29m";
            assert Term.strikethrough("test").equals(expected);
            assert (Term.setMode(Mode.STRIKETHROUGH) +
                    "test" +
                    Term.unsetMode(Mode.STRIKETHROUGH)).equals(expected);
        }
    }

    @Test
    public static void testColors() {
        assert Term.black("test").equals(ESC + "[30mtest" + ESC + "[39m");
        assert Term.red("test").equals(ESC + "[31mtest" + ESC + "[39m");
        assert Term.green("test").equals(ESC + "[32mtest" + ESC + "[39m");
        assert Term.yellow("test").equals(ESC + "[33mtest" + ESC + "[39m");
        assert Term.blue("test").equals(ESC + "[34mtest" + ESC + "[39m");
        assert Term.magenta("test").equals(ESC + "[35mtest" + ESC + "[39m");
        assert Term.cyan("test").equals(ESC + "[36mtest" + ESC + "[39m");
        assert Term.white("test").equals(ESC + "[37mtest" + ESC + "[39m");
    }

    @Test
    public static void testColorsWithSet() {
        {
            String expected = ESC + "[30mtest" + ESC + "[39m";
            assert (
                Term.setMode(Color.BLACK) + "test" + Term.unsetMode(Color.BLACK)
            ).equals(expected);
        }
        {
            String expected = ESC + "[31mtest" + ESC + "[39m";
            assert (
                Term.setMode(Color.RED) + "test" + Term.unsetMode(Color.RED)
            ).equals(expected);
        }
        {
            String expected = ESC + "[32mtest" + ESC + "[39m";
            assert (
                Term.setMode(Color.GREEN) + "test" + Term.unsetMode(Color.GREEN)
            ).equals(expected);
        }
        {
            String expected = ESC + "[33mtest" + ESC + "[39m";
            assert (
                Term.setMode(Color.YELLOW) + "test" + Term.unsetMode(Color.YELLOW)
            ).equals(expected);
        }
        {
            String expected = ESC + "[34mtest" + ESC + "[39m";
            assert (
                Term.setMode(Color.BLUE) + "test" + Term.unsetMode(Color.BLUE)
            ).equals(expected);
        }
        {
            String expected = ESC + "[35mtest" + ESC + "[39m";
            assert (
                Term.setMode(Color.MAGENTA) + "test" + Term.unsetMode(Color.MAGENTA)
            ).equals(expected);
        }
        {
            String expected = ESC + "[36mtest" + ESC + "[39m";
            assert (
                Term.setMode(Color.CYAN) + "test" + Term.unsetMode(Color.CYAN)
            ).equals(expected);
        }
        {
            String expected = ESC + "[37mtest" + ESC + "[39m";
            assert (
                Term.setMode(Color.WHITE) + "test" + Term.unsetMode(Color.WHITE)
            ).equals(expected);
        }
    }

    @Test
    public static void testBrightColors() {
        assert Term.brBlack("test").equals(ESC + "[90mtest" + ESC + "[39m");
        assert Term.brWhite("test").equals(ESC + "[97mtest" + ESC + "[39m");
        assert Term.brRed("test").equals(ESC + "[91mtest" + ESC + "[39m");
        assert Term.brGreen("test").equals(ESC + "[92mtest" + ESC + "[39m");
        assert Term.brBlue("test").equals(ESC + "[94mtest" + ESC + "[39m");
        assert Term.brCyan("test").equals(ESC + "[96mtest" + ESC + "[39m");
        assert Term.brMagenta("test").equals(ESC + "[95mtest" + ESC + "[39m");
        assert Term.brYellow("test").equals(ESC + "[93mtest" + ESC + "[39m");
    }

    @Test
    public static void testBackgroundColors() {
        assert Term.bgBlack("test").equals(ESC + "[40mtest" + ESC + "[49m");
        assert Term.bgWhite("test").equals(ESC + "[47mtest" + ESC + "[49m");
        assert Term.bgRed("test").equals(ESC + "[41mtest" + ESC + "[49m");
        assert Term.bgGreen("test").equals(ESC + "[42mtest" + ESC + "[49m");
        assert Term.bgBlue("test").equals(ESC + "[44mtest" + ESC + "[49m");
        assert Term.bgCyan("test").equals(ESC + "[46mtest" + ESC + "[49m");
        assert Term.bgMagenta("test").equals(ESC + "[45mtest" + ESC + "[49m");
        assert Term.bgYellow("test").equals(ESC + "[43mtest" + ESC + "[49m");
    }

    @Test
    public static void testBrightBackgroundColors() {
        assert Term.bgBrBlack("test").equals(ESC + "[100mtest" + ESC + "[49m");
        assert Term.bgBrWhite("test").equals(ESC + "[107mtest" + ESC + "[49m");
        assert Term.bgBrRed("test").equals(ESC + "[101mtest" + ESC + "[49m");
        assert Term.bgBrGreen("test").equals(ESC + "[102mtest" + ESC + "[49m");
        assert Term.bgBrBlue("test").equals(ESC + "[104mtest" + ESC + "[49m");
        assert Term.bgBrCyan("test").equals(ESC + "[106mtest" + ESC + "[49m");
        assert Term.bgBrMagenta("test").equals(ESC + "[105mtest" + ESC + "[49m");
        assert Term.bgBrYellow("test").equals(ESC + "[103mtest" + ESC + "[49m");
    }

    @Test
    public static void testCompose() {
        Function<String, String> style1 = Term.compose(Color.BLUE, Background.YELLOW);
        Function<String, String> style2 = Term.compose(
            Mode.BOLD, Mode.ITALIC, Color.WHITE_BRIGHT, Background.RED_BRIGHT
        );

        assert style1.apply("test").equals(
            ESC + "[34;43m" +
            "test" +
            ESC + "[39;49m"
        );
        assert style2.apply("test").equals(
            ESC + "[1;3;97;101m" +
            "test" +
            ESC + "[22;23;39;49m"
        );
    }

    @Test
    public static void testCursorCommands() {
        assert Term.cursorHome().equals(ESC + "[H");
        assert Term.cursorTo(12, 9).equals(ESC + "[12;9H");
        assert Term.cursorToColumn(3).equals(ESC + "[3G");
        assert Term.cursorSave().equals(ESC + "7");
        assert Term.cursorRestore().equals(ESC + "8");
    }

    @Test
    public static void testCursorArrowKeys() {
        assert Term.cursorUp().equals(ESC + "[A");
        assert Term.cursorUp(3).equals(ESC + "[3A");
        assert Term.cursorDown().equals(ESC + "[B");
        assert Term.cursorDown(5).equals(ESC + "[5B");
        assert Term.cursorRight().equals(ESC + "[C");
        assert Term.cursorRight(2).equals(ESC + "[2C");
        assert Term.cursorLeft().equals(ESC + "[D");
        assert Term.cursorLeft(6).equals(ESC + "[6D");
    }

    @Test
    public static void testCursorScrolls() {
        assert Term.cursorReturnDown(3).equals(ESC + "[3E");
        assert Term.cursorReturnUp(9).equals(ESC + "[9F");
        assert Term.cursorUpAndScroll().equals(ESC + "M");
    }

    @Test
    public static void testEraseCommands() {
        assert Term.eraseToEnd().equals(ESC + "[0J");
        assert Term.eraseToBeginning().equals(ESC + "[1J");
        assert Term.eraseScreen().equals(ESC + "[2J");
        assert Term.eraseToEndOfLine().equals(ESC + "[0K");
        assert Term.eraseToBeginningOfLine().equals(ESC + "[1K");
        assert Term.eraseLine().equals(ESC + "[2K");
    }

    @Test
    public static void testScreenCommands() {
        assert Term.saveScreen().equals(ESC + "[?47h");
        assert Term.restoreScreen().equals(ESC + "[?47l");
    }
}
