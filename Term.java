/*
 * Term.java
 *
 * A simple utility class for ANSI terminals.
 */

import java.util.*;
import java.util.function.*;
import java.util.stream.*;

public class Term {
    public static void main(String[] args) {
        colorTest(Color.COLORS, Background.BACKGROUNDS);
        modeTest();
        colorTest(Color.BRIGHT_COLORS, Background.BRIGHT_BACKGROUNDS);
    }

    private static void colorTest(Color[] colors, Background[] backgrounds) {
        int i = 0;
        for (Color c : colors) {
            for (Background b : backgrounds) {
                System.out.print(
                    Term.compose(b, c).apply(
                        String.format(" %x/%x ", c.getCode(), b.getCode())
                    )
                );

                if (++i % 8 == 0) {
                    System.out.println();
                }
            }
        }
    }

    private static void modeTest() {
        int i = 0;
        for (Mode m : Mode.NON_COLOR_MODES) {
            System.out.print(
                Term.compose(m).apply(
                    String.format(" mode%d ", m.getCode())
                )
            );

            if (++i % 8 == 0) {
                System.out.println();
            }
        }
    }

    private static final String ESC = "\u001b";
    private static final String CSI = ESC + "[";

    public static String setMode(Mode m) { return esc(Stream.of(m.getCode())); }
    public static String unsetMode(Mode m) { return esc(Stream.of(getUnsetCode(m))); }

    public static String setModes(Mode... modes) {
        return esc(Arrays.stream(modes).map(Mode::getCode));
    }

    public static String unsetModes(Mode... modes) {
        return esc(Arrays.stream(modes).map(Term::getUnsetCode));
    }

    private static String esc(Stream<Integer> codes) {
        return CSI + codes.map(Objects::toString).collect(Collectors.joining(";")) + "m";
    }

    private static int getUnsetCode(Mode m) {
        if (m instanceof Background) {
            return Background.DEFAULT.getCode();
        }
        if (m instanceof Color) {
            return Color.DEFAULT.getCode();
        }
        if (m == Mode.BOLD || m == Mode.DIM) {
            return 22;
        }
        return 20 + m.getCode();
    }

    public static String reset() { return CSI + 0 + "m"; }
    public static String resetColor() { return unsetMode(Color.RED); }
    public static String resetBackground() { return unsetMode(Background.RED); }

    public static String bold(String s) { return enclose(s, Mode.BOLD); }
    public static String dim(String s) { return enclose(s, Mode.DIM); }
    public static String italic(String s) { return enclose(s, Mode.ITALIC); }
    public static String underline(String s) { return enclose(s, Mode.UNDERLINE); }
    public static String blink(String s) { return enclose(s, Mode.BLINK); }
    public static String invert(String s) { return enclose(s, Mode.INVERT); }
    public static String hidden(String s) { return enclose(s, Mode.HIDDEN); }
    public static String strikethrough(String s) { return enclose(s, Mode.STRIKETHROUGH); }

    public static String black(String s) { return enclose(s, Color.BLACK); }
    public static String red(String s) { return enclose(s, Color.RED); }
    public static String green(String s) { return enclose(s, Color.GREEN); }
    public static String yellow(String s) { return enclose(s, Color.YELLOW); }
    public static String blue(String s) { return enclose(s, Color.BLUE); }
    public static String magenta(String s) { return enclose(s, Color.MAGENTA); }
    public static String cyan(String s) { return enclose(s, Color.CYAN); }
    public static String white(String s) { return enclose(s, Color.WHITE); }

    public static String brBlack(String s) { return enclose(s, Color.BLACK_BRIGHT); }
    public static String brRed(String s) { return enclose(s, Color.RED_BRIGHT); }
    public static String brGreen(String s) { return enclose(s, Color.GREEN_BRIGHT); }
    public static String brYellow(String s) { return enclose(s, Color.YELLOW_BRIGHT); }
    public static String brBlue(String s) { return enclose(s, Color.BLUE_BRIGHT); }
    public static String brMagenta(String s) { return enclose(s, Color.MAGENTA_BRIGHT); }
    public static String brCyan(String s) { return enclose(s, Color.CYAN_BRIGHT); }
    public static String brWhite(String s) { return enclose(s, Color.WHITE_BRIGHT); }

    public static String bgBlack(String s) { return enclose(s, Background.BLACK); }
    public static String bgRed(String s) { return enclose(s, Background.RED); }
    public static String bgGreen(String s) { return enclose(s, Background.GREEN); }
    public static String bgYellow(String s) { return enclose(s, Background.YELLOW); }
    public static String bgBlue(String s) { return enclose(s, Background.BLUE); }
    public static String bgMagenta(String s) { return enclose(s, Background.MAGENTA); }
    public static String bgCyan(String s) { return enclose(s, Background.CYAN); }
    public static String bgWhite(String s) { return enclose(s, Background.WHITE); }

    public static String bgBrBlack(String s) { return enclose(s, Background.BLACK_BRIGHT); }
    public static String bgBrRed(String s) { return enclose(s, Background.RED_BRIGHT); }
    public static String bgBrGreen(String s) { return enclose(s, Background.GREEN_BRIGHT); }
    public static String bgBrYellow(String s) { return enclose(s, Background.YELLOW_BRIGHT); }
    public static String bgBrBlue(String s) { return enclose(s, Background.BLUE_BRIGHT); }
    public static String bgBrMagenta(String s) { return enclose(s, Background.MAGENTA_BRIGHT); }
    public static String bgBrCyan(String s) { return enclose(s, Background.CYAN_BRIGHT); }
    public static String bgBrWhite(String s) { return enclose(s, Background.WHITE_BRIGHT); }

    private static String enclose(String s, Mode m) { return setMode(m) + s + unsetMode(m); }

    public static String cursorHome() { return CSI + "H"; }
    public static String cursorTo(int line, int column) { return CSI + line + ";" + column + "H"; }
    public static String cursorUp(int numLines) { return CSI + numLines + "A"; }
    public static String cursorDown(int numLines) { return CSI + numLines + "B"; }
    public static String cursorRight(int numColumns) { return CSI + numColumns + "C"; }
    public static String cursorLeft(int numColumns) { return CSI + numColumns + "D"; }
    public static String cursorDownLines(int numLines) { return CSI + numLines + "E"; }
    public static String cursorUpLines(int numLines) { return CSI + numLines + "F"; }
    public static String cursorToColumn(int column) { return CSI + column + "G"; }
    public static String cursorScrollUp() { return ESC + "M"; }
    public static String cursorSave() { return ESC + 7; }
    public static String cursorRestore() { return ESC + 8; }

    public static String eraseToEnd() { return CSI + "0J"; }
    public static String eraseToBeginning() { return CSI + "1J"; }
    public static String eraseScreen() { return CSI + "2J"; }
    public static String eraseToEndOfLine() { return CSI + "0K"; }
    public static String eraseToBeginningOfLine() { return CSI + "1K"; }
    public static String eraseLine() { return CSI + "2K"; }

    public static String saveScreen() { return CSI + "?47h"; }
    public static String restoreScreen() { return CSI + "?47l"; }

    public static Function<String, String> compose(Mode... modes) {
        return str -> setModes(modes) + str + unsetModes(modes);
    }
}

abstract class Code {
    private int code;
    public Code(int code) { this.code = code; }
    public int getCode() { return code; }
}

class Mode extends Code {
    public static final Mode BOLD = new Mode(1);
    public static final Mode DIM = new Mode(2);
    public static final Mode ITALIC = new Mode(3);
    public static final Mode UNDERLINE = new Mode(4);
    public static final Mode BLINK = new Mode(5);
    public static final Mode INVERT = new Mode(7);
    public static final Mode HIDDEN = new Mode(8);
    public static final Mode STRIKETHROUGH = new Mode(9);

    public Mode(int code) { super(code); }

    public static final Mode[] NON_COLOR_MODES = {
        BOLD, DIM, ITALIC, UNDERLINE, BLINK, INVERT, HIDDEN, STRIKETHROUGH
    };
}

class Color extends Mode {
    public static final Color DEFAULT = new Color(39);

    public static final Color BLACK = new Color(30);
    public static final Color RED = new Color(31);
    public static final Color GREEN = new Color(32);
    public static final Color YELLOW = new Color(33);
    public static final Color BLUE = new Color(34);
    public static final Color MAGENTA = new Color(35);
    public static final Color CYAN = new Color(36);
    public static final Color WHITE = new Color(37);

    public static final Color BLACK_BRIGHT = new Color(90);
    public static final Color RED_BRIGHT = new Color(91);
    public static final Color GREEN_BRIGHT = new Color(92);
    public static final Color YELLOW_BRIGHT = new Color(93);
    public static final Color BLUE_BRIGHT = new Color(94);
    public static final Color MAGENTA_BRIGHT = new Color(95);
    public static final Color CYAN_BRIGHT = new Color(96);
    public static final Color WHITE_BRIGHT = new Color(97);

    public Color(int code) { super(code); }

    public static final Color[] COLORS = {
        BLACK, RED, GREEN, YELLOW, BLUE, MAGENTA, CYAN, WHITE
    };

    public static final Color[] BRIGHT_COLORS = {
        BLACK_BRIGHT, RED_BRIGHT, GREEN_BRIGHT, YELLOW_BRIGHT, BLUE_BRIGHT,
        MAGENTA_BRIGHT, CYAN_BRIGHT, WHITE_BRIGHT
    };
}

class Background extends Color {
    public static final Background DEFAULT = new Background(49);

    public static final Background BLACK = new Background(40);
    public static final Background RED = new Background(41);
    public static final Background GREEN = new Background(42);
    public static final Background YELLOW = new Background(43);
    public static final Background BLUE = new Background(44);
    public static final Background MAGENTA = new Background(45);
    public static final Background CYAN = new Background(46);
    public static final Background WHITE = new Background(47);

    public static final Background BLACK_BRIGHT = new Background(100);
    public static final Background RED_BRIGHT = new Background(101);
    public static final Background GREEN_BRIGHT = new Background(102);
    public static final Background YELLOW_BRIGHT = new Background(103);
    public static final Background BLUE_BRIGHT = new Background(104);
    public static final Background MAGENTA_BRIGHT = new Background(105);
    public static final Background CYAN_BRIGHT = new Background(106);
    public static final Background WHITE_BRIGHT = new Background(107);

    public Background(int code) { super(code); }

    public static final Background[] BACKGROUNDS = {
        BLACK, RED, GREEN, YELLOW, BLUE, MAGENTA, CYAN, WHITE
    };

    public static final Background[] BRIGHT_BACKGROUNDS = {
        BLACK_BRIGHT, RED_BRIGHT, GREEN_BRIGHT, YELLOW_BRIGHT, BLUE_BRIGHT,
        MAGENTA_BRIGHT, CYAN_BRIGHT, WHITE_BRIGHT
    };
}
