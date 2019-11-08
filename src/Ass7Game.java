import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.List;

import levels.GameFlow;
import levels.LevelInformation;
import levels.LevelSpecificationReader;
import menu.Menu;
import menu.Task;
import animation.AnimationRunner;
import animation.HighScoresAnimation;
import animation.HighScoresTable;
import animation.KeyPressStoppableAnimation;
import animation.MenuAnimation;
import biuoop.GUI;
import biuoop.KeyboardSensor;

/**
 * Class Ass7Game to create a new game.
 *
 * @author sarab de paz
 */
public class Ass7Game {
    /**
     * creates a new game of the ball game.
     *
     * @param args
     *            nothing
     */
    public static void main(String[] args) {
        final int lives = 7, width = 800, height = 600, framesPerSecond = 60;

        // high score file create
        generateHighScores();

        // create new game, gui and animationRunner
        GUI gui = new GUI("Arkanoid", width, height);
        AnimationRunner ar = new AnimationRunner(gui, framesPerSecond);
        GameFlow game = new GameFlow(ar, gui.getKeyboardSensor(), lives, width,
                height, gui, framesPerSecond);
        // menu run
        Menu<Task<Void>> menu = generateMenu(gui, ar, game, args);
        runMenu(menu, ar, false);
    }

    /**
     * function that run the menu.
     *
     * @param menu
     *            the menu of the game
     * @param ar
     *            run the animation
     * @param sub
     *            a sub menu
     */
    private static void runMenu(Menu<Task<Void>> menu, AnimationRunner ar,
            boolean sub) {
        while (true) {
            ar.run(menu);
            Task<Void> task = menu.getStatus();
            if (task != null) {
                task.run();
                if (sub) {
                    break;
                }
            } else {
                Menu<Task<Void>> subMenu = menu.getSubMenu();
                if (subMenu != null) {
                    runMenu(subMenu, ar, true);
                }
            }
        }
    }

    /**
     * function that create the high scores table.
     */
    public static void generateHighScores() {
        File highScores = new File(HighScoresTable.fileName());
        try {
            HighScoresTable highScoresTable = null;
            if (!highScores.exists()) {
                highScoresTable = new HighScoresTable(
                        HighScoresTable.tableMaxSize());
                highScoresTable.save(highScores);
            }
        } catch (Exception e) {
            System.out.println("error file create");
        }
    }

    /**
     * function that create the menu.
     *
     * @param gui
     *            connect between the Keyboard to the paddle
     * @param ar
     *            run the animation
     * @param game
     *            the game
     * @return the menu of the game
     */
    public static Menu<Task<Void>> generateMenu(final GUI gui,
            final AnimationRunner ar, final GameFlow game, String[] args) {
        Menu<Task<Void>> menu = new MenuAnimation<Task<Void>>("Arkanoid",
                gui.getKeyboardSensor());
        menu.addSelection("h", "High Scores", new Task<Void>() {
            @Override
            public Void run() {
                File highScoresFile = new File(HighScoresTable.fileName());
                HighScoresTable highScores = HighScoresTable
                        .loadFromFile(highScoresFile);
                ar.run(new KeyPressStoppableAnimation(gui.getKeyboardSensor(),
                        KeyboardSensor.SPACE_KEY, new HighScoresAnimation(
                                highScores)));
                return null;
            }
        });
        menu.addSelection("q", "Quit", new Task<Void>() {
            @Override
            public Void run() {
                gui.close();
                System.exit(0);
                return null;
            }
        });
        Menu<Task<Void>> subMenu = new MenuAnimation<Task<Void>>("Play",
                gui.getKeyboardSensor());

        BufferedReader reader = null;
        try {
            // default path
            String levelsPath = "resources/level_sets.txt";
            // from the main
            if (args.length > 0) {
                levelsPath = args[0];
            }
            reader = new BufferedReader(
                    new InputStreamReader(
                            ClassLoader
                                    .getSystemResourceAsStream(levelsPath)));
            LevelSpecificationReader lsfr = new LevelSpecificationReader();
            String newLine = reader.readLine();
            while (newLine != null) {
                String symbol = newLine.split(":")[0];
                InputStreamReader stream = null;
                try {
                    stream = new InputStreamReader(
                            ClassLoader.getSystemResourceAsStream(reader
                                    .readLine()));
                    final List<LevelInformation> levels = lsfr
                            .fromReader(stream);
                    subMenu.addSelection(symbol, newLine.split(":")[1],
                            new Task<Void>() {
                                @Override
                                public Void run() {
                                    game.runLevels(levels);
                                    return null;
                                }
                            });
                } catch (Exception e) {
                    System.out.print("");
                }

                try {
                    stream.close();
                } catch (Exception e) {
                    System.out.print("");
                }
                newLine = reader.readLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            reader.close();
        } catch (Exception e) {
            System.out.print("");
        }
        menu.addSubMenu("s", "Play Game", subMenu);
        return menu;
    }
}
