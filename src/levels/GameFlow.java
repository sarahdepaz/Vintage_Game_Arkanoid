package levels;

import java.io.File;
import java.util.List;

import listeners.ScoreInfo;
import other.Counter;
import animation.AnimationRunner;
import animation.EndScreen;
import animation.HighScoresAnimation;
import animation.HighScoresTable;
import animation.KeyPressStoppableAnimation;
import biuoop.DialogManager;
import biuoop.GUI;
import biuoop.KeyboardSensor;

/**
 * Class of a GameFlow.
 *
 * @author sarah de paz
 */
public class GameFlow {
    private AnimationRunner ar;
    private KeyboardSensor ks;
    private Counter livesCounter;
    private Counter scoreCounter;
    private int width, height, framesPerSecond, originalLives;
    private GUI gui;

    /**
     * constructor function that create the game flow.
     *
     * @param ar
     *            the animation runner
     * @param ks
     *            the keyboard sensor
     * @param lives
     *            the lives of the player
     * @param width
     *            the width of the screen
     * @param height
     *            the height of the screen
     * @param gui
     *            connect between the Keyboard to the game
     * @param frames
     *            number of the frames
     */
    public GameFlow(AnimationRunner ar, KeyboardSensor ks, int lives,
            int width, int height, GUI gui, int frames) {
        this.ar = ar;
        this.ks = ks;
        this.originalLives = lives;
        this.livesCounter = new Counter(lives);
        this.scoreCounter = new Counter();
        this.width = width;
        this.height = height;
        this.gui = gui;
        this.framesPerSecond = frames;
    }

    /**
     * function that reset the game.
     *
     */
    public void resetGame() {
        this.livesCounter.setVal(this.originalLives);
        this.scoreCounter.setVal(0);
    }

    /**
     * function that run and moving from one level to the next.
     *
     * @param levels
     *            the levels of the game
     */
    public void runLevels(List<LevelInformation> levels) {
        boolean isWin = true;
        for (LevelInformation levelInfo : levels) {
            GameLevel level = new GameLevel(levelInfo, this.ar, this.ks,
                    this.livesCounter, this.scoreCounter, this.gui,
                    this.framesPerSecond);
            level.setWidthAndHeight(this.width, this.height);
            level.initialize();
            // initialize the level
            while ((level.isRemainLives()) && (level.isRemainBlocks())) {
                // while there are still lives and block
                level.playOneTurn();
            }
            if (this.livesCounter.getValue() == 0) {
                // if there are no lives
                isWin = false;
                break;
            }
            this.scoreCounter.increase(100);
            // if the plater wins increase the score in 100 points
        }
        // end screen
        this.ar.run(new KeyPressStoppableAnimation(this.ks,
                KeyboardSensor.SPACE_KEY, new EndScreen(isWin,
                        this.scoreCounter.getValue())));

        // high score
        // after end screen - show the high score screen
        File highScoresFile = new File(HighScoresTable.fileName());
        HighScoresTable highScores = HighScoresTable
                .loadFromFile(highScoresFile);
        if (highScores != null) {
            // if user got good score, add him to the high scores table!! :)
            if (highScores.size() + 1 > highScores.getRank(this.scoreCounter
                    .getValue())) {
                DialogManager dialogManager = this.gui.getDialogManager();
                String name = dialogManager.showQuestionDialog("Name",
                        "What is your name?", "");
                highScores
                        .add(new ScoreInfo(name, this.scoreCounter.getValue()));
                try {
                    highScores.save(highScoresFile);
                } catch (Exception e) {
                    System.out.println("score saving error");
                }
            }
        }
        this.ar.run(new KeyPressStoppableAnimation(this.ks,
                KeyboardSensor.SPACE_KEY, new HighScoresAnimation(highScores)));
        this.resetGame();
    }
}