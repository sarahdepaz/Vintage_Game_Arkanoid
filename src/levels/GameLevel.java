package levels;

import java.awt.Color;

import listeners.BallRemover;
import listeners.BlockRemover;
import listeners.ScoreTrackingListener;
import other.Counter;
import animation.Animation;
import animation.AnimationRunner;
import animation.CountdownAnimation;
import animation.KeyPressStoppableAnimation;
import animation.PauseScreen;
import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import elements.Ball;
import elements.Block;
import elements.Collidable;
import elements.LevelNameIndicator;
import elements.LivesIndicator;
import elements.Paddle;
import elements.ScoreIndicator;
import elements.Sprite;
import geometry.Rectangle;

/**
 * Class of a Game Level.
 *
 * @author sarah de paz
 */
public class GameLevel implements Animation {
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private GUI gui;
    private int width, height, framesPerSecond;
    private Counter ballCounter, blockCounter, scoreCounter, livesCounter;
    private Paddle gamePaddle;
    private AnimationRunner runner;
    private boolean running;
    private biuoop.KeyboardSensor keyboard;
    private LevelInformation levelInfo;

    /**
     * constructor function that create new game.
     *
     * @param levelInfo
     *            the LevelInformation to run
     * @param ar
     *            the animation runner of the game level
     * @param ks
     *            connect between the Keyboard to the paddle
     * @param livesCounter
     *            number of lives
     * @param scoreCounter
     *            the score
     * @param gui
     *            the gui of the game levels
     * @param frames
     *            number of frames
     */
    public GameLevel(LevelInformation levelInfo, AnimationRunner ar,
            KeyboardSensor ks, Counter livesCounter, Counter scoreCounter,
            GUI gui, int frames) {
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
        this.gui = gui;
        this.ballCounter = new Counter();
        this.blockCounter = new Counter();
        this.scoreCounter = scoreCounter;
        this.livesCounter = livesCounter;
        this.runner = ar;
        this.running = false;
        this.keyboard = ks;
        this.levelInfo = levelInfo;
        this.framesPerSecond = frames;
    }

    /**
     * function that set the width and height of the screen.
     *
     * @param w
     *            the width of the screen
     * @param h
     *            the height of the screen
     */
    public void setWidthAndHeight(int w, int h) {
        this.width = w;
        this.height = h;
    }

    /**
     * function that add collidable to the game.
     *
     * @param c
     *            a collidable to add to the game
     */
    public void addCollidable(Collidable c) {
        this.environment.addCollidable(c);
    }

    /**
     * function that add sprite to the game.
     *
     * @param s
     *            a sprite to add to the game
     */
    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);
    }

    /**
     * function that initialize a new game, create the Blocks, Ball and Paddle
     * and add them to the game.
     */
    public void initialize() {
        final int bordersSize = 20;

        // set background
        this.levelInfo.getBackground().addToGame(this);

        // set lives
        this.blockCounter.increase(this.levelInfo.numberOfBlocksToRemove());
        ScoreTrackingListener scoreTracking = new ScoreTrackingListener(
                scoreCounter);
        BlockRemover blockR = new BlockRemover(this, blockCounter);
        BallRemover ballR = new BallRemover(this, ballCounter);
        // score banner
        ScoreIndicator gameScore = new ScoreIndicator(new Rectangle(0, 0,
                width, bordersSize), Color.LIGHT_GRAY, scoreCounter);
        gameScore.addToGame(this);
        // lives banner
        LivesIndicator gameLives = new LivesIndicator(livesCounter);
        gameLives.addToGame(this);
        // level name
        LevelNameIndicator levelName = new LevelNameIndicator(
                this.levelInfo.levelName());
        levelName.addToGame(this);

        // border blocks
        Block border1 = new Block(0, bordersSize, bordersSize, this.height,
                Color.GRAY, -1);
        Block border2 = new Block(bordersSize, bordersSize, this.width
                - bordersSize, bordersSize, Color.GRAY, -1);
        Block border3 = new Block(this.width - bordersSize, bordersSize,
                bordersSize, this.height - bordersSize, Color.GRAY, -1);
        border1.addToGame(this);
        border2.addToGame(this);
        border3.addToGame(this);
        Block deathBlock = new Block(0, this.height, this.width, 0,
                Color.BLACK, -1);
        deathBlock.addHitListener(ballR);
        deathBlock.addToGame(this);

        // blocks
        Block block;
        for (int i = 0; i < this.levelInfo.blocks().size(); i++) {
            block = this.levelInfo.blocks().get(i);
            block.addHitListener(scoreTracking);
            block.addHitListener(blockR);
            block.addToGame(this);
        }
    }

    /**
     * function initialize the game and use our runner to run the current
     * animation which is one turn of the game.
     */
    public void playOneTurn() {
        this.initializePaddle();
        this.initializeBalls();
        // count down before turn starts.
        this.runner.run(new CountdownAnimation(0.67, 3, this.sprites));
        this.running = true;
        // use our runner to run the current animation -- which is one turn of
        // the game.
        this.runner.run(this);
    }

    /**
     * function that charge of the logic.
     *
     * @param d
     *            the sprites to draw to the screen
     * @param dt
     *            frames per second
     */
    public void doOneFrame(DrawSurface d, double dt) {
        if (this.keyboard.isPressed("p")) {
            this.runner.run(new KeyPressStoppableAnimation(this.keyboard,
                    KeyboardSensor.SPACE_KEY, new PauseScreen()));
            // if the user press p Pause the screen
        }
        if (this.ballCounter.getValue() == 0) {
            this.livesCounter.decrease(1);
            this.running = false;
            // if there are 0 balls end the game
        }
        if (this.blockCounter.getValue() == 0) {
            this.running = false;
            // if there are 0 blocks end the game
        }
        this.sprites.drawAllOn(d);
        this.sprites.notifyAllTimePassed(dt);
    }

    /**
     * function that charge of stopping condition.
     *
     * @return if the game should stop
     */
    public boolean shouldStop() {
        return !this.running;
    }

    /**
     * function that create and initialize the paddle.
     */
    public void initializePaddle() {
        if (this.gamePaddle == null) {
            final int paddleHeight = 15;
            // create new paddle
            Paddle newPaddle = new Paddle(this.gui, this.width / 2
                    - (this.levelInfo.paddleWidth() / 2), this.height - 25,
                    this.levelInfo.paddleWidth(), paddleHeight, Color.ORANGE,
                    this.levelInfo.paddleSpeed());
            newPaddle.setRange(20, width - 20);
            this.gamePaddle = newPaddle;
            newPaddle.addToGame(this);
        } else {
            // take paddle to middle
            this.gamePaddle.movePaddleToMiddle();
        }
    }

    /**
     * function that create and initialize the balls.
     */
    public void initializeBalls() {
        final int radius = 6, numberOfBalls = this.levelInfo.numberOfBalls();
        Ball ball;
        for (int i = 0; i < numberOfBalls; i++) {
            ball = new Ball(this.width / 2, this.height - 50, radius,
                    Color.WHITE, this.environment);
            ball.setVelocity(this.levelInfo.initialBallVelocities().get(i),
                    1 / (double) this.framesPerSecond);
            ball.addToGame(this);
        }
        // set ball counter
        this.ballCounter.increase(numberOfBalls);
    }

    /**
     * function that remove the Collidable from the collideable list.
     *
     * @param c
     *            the Collidable we remove
     */
    public void removeCollidable(Collidable c) {
        this.environment.removeCollidable(c);
    }

    /**
     * function that remove the Sprite from the Sprite list.
     *
     * @param s
     *            the Sprite we remove
     */
    public void removeSprite(Sprite s) {
        this.sprites.removeSprite(s);
    }

    /**
     * function that checks if remains blocks.
     *
     * @return if the blocks remain or not
     */
    public boolean isRemainBlocks() {
        return (this.blockCounter.getValue() > 0);
    }

    /**
     * function that checks if remains lives.
     *
     * @return if the lives remain or not
     */
    public boolean isRemainLives() {
        return (this.livesCounter.getValue() > 0);
    }
}