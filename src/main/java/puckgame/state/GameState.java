package puckgame.state;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Marker;

import java.util.Arrays;

/**
 * Class representing the state of the game and the board.
 */
@Data
@Slf4j
public class GameState {

    /**
     * Integer value that represents the direction of Right.
     */
    private static final int RIGHT = 0;

    /**
     * Integer value that represents the direction of Left.
     */
    private static final int LEFT = 1;

    /**
     * Integer value that represents the direction of Up.
     */
    private static final int UP = 2;

    /**
     * Integer value that represents the direction of Down.
     */
    private static final int DOWN = 3;

    /**
     * Boolean value that stores if the logging is enabled or not.
     */
    @Setter(AccessLevel.PUBLIC)
    private boolean logEnabled = true;

    /**
     * Player object that stores the player with the blue pucks.
     */
    @Setter(AccessLevel.PUBLIC)
    private Player bluePlayer;

    /**
     * Player object that stores the player with the red pucks.
     */
    @Setter(AccessLevel.PUBLIC)
    private Player redPlayer;

    /**
     * Player object that stores the winner of the game.
     */
    @Getter(AccessLevel.PUBLIC)
    private Player winner;

    /**
     * Player object that stores the loser of the game.
     */
    @Getter(AccessLevel.PUBLIC)
    private Player loser;

    /**
     * 2D array representing the state of the game.
     *
     * The blue pucks are represented as 1's, the red pucks are represented as 2's, and the empty spaces as 0's.
     */
    @Getter(AccessLevel.PUBLIC)
    @Setter(AccessLevel.PUBLIC)
    private int [][] grid = {
            {1, 1, 1, 1, 1},
            {1, 0, 0, 0, 1},
            {0, 0, 0, 0, 0},
            {2, 0, 0, 0, 2},
            {2, 2, 2, 2, 2}
    };

    /**
     * Null arg constructor.
     */
    public GameState() {}

    /**
     * Constructor which takes a 2D array and set this array to the grid.
     *
     * @param grid 2D array that represents the state of the game and the board.
     */
    public GameState(int [][] grid) {
        this.grid = grid;
    }
    public static Marker print2D(int[][] mat)
    {
        // Loop through all rows
        for (int[] row : mat)

            // converting each row as string
            // and then printing in a separate line
            System.out.println(Arrays.toString(row));
        return null;
    }
    /**
     * A method that checks if the current move is enabled or not.
     *
     * @param currentPlayer The player who has to move at the current state.
     * @param row The row where the the current puck stands on the table.
     * @param col The column where the current puck stands on the table.
     * @param direction The direction where the player wants to move to.
     *
     * @return {@code true} if the player can move to the wanted direction, {@code false} otherwise.
     */
    public boolean isValidMove(Player currentPlayer, int row, int col, int direction) {

        boolean isValid = false;

        if (currentPlayer.getPlayerId() == 1 && grid[row][col] == 2 || !(currentPlayer.getPlayerId() == 1) && grid[row][col] == 1) {
            if(logEnabled) {
                log.info("You can not move, because it is {}'s turn!", currentPlayer.getName());
            }
            isValid = false;
        }

        else {
            try {
                if (currentPlayer.getPlayerId() == 1) {
                    switch (direction) {
                        case RIGHT: {
                            if (grid[row][col + 1] == 0) {
                                isValid = true;
                            } else {
                                if(logEnabled) {
                                    log.info("You can not move there, because the ({}, {}) field is not empty!", (col + 1) + 1, row + 1);
                                }
                            }
                            break;
                        }
                        case LEFT: {
                            if (grid[row][col - 1] == 0) {
                                isValid = true;
                            } else {
                                if(logEnabled) {
                                    log.info("You can not move there, because the ({}, {}) field is not empty!", (col + 1) + 1, row + 1);
                                }
                            }
                            break;
                        }
                        case UP: {
                            if (grid[row - 1][col] == 0) {
                                isValid = true;
                            } else {
                                if(logEnabled) {
                                    log.info("You can not move there, because the ({}, {}) field is not empty!", (col + 1) + 1, row + 1);
                                }
                            }
                            break;
                        }
                        case DOWN: {
                            if (grid[row + 1][col] == 0) {
                                isValid = true;
                            } else {
                                if(logEnabled) {
                                    log.info("You can not move there, because the ({}, {}) field is not empty!", (col + 1) + 1, row + 1);
                                }
                            }
                            break;
                        }
                        default:
                            if(logEnabled) {
                                log.info("Invalid direction, you can not move to that space!");
                            }
                            isValid = false;
                    }
                } else {
                    if (currentPlayer.getPlayerId() == 2) {
                        switch (direction) {
                            case RIGHT: {
                                if (grid[row][col + 1] == 0) {
                                    isValid = true;
                                } else {
                                    if(logEnabled) {
                                        log.info("You can not move there, because the ({}, {}) field is not empty!", (col + 1) + 1, row + 1);
                                    }
                                }
                                break;
                            }
                            case LEFT: {
                                if (grid[row][col - 1] == 0) {
                                    isValid = true;
                                } else {
                                    if(logEnabled) {
                                        log.info("You can not move there, because the ({}, {}) field is not empty!", (col - 1) + 1, row + 1);
                                    }
                                }
                                break;
                            }
                            case UP: {
                                if (grid[row - 1][col] == 0) {
                                    isValid = true;
                                } else {
                                    if(logEnabled) {
                                        log.info("You can not move there, because the ({}, {}) field is not empty!", col + 1, (row - 1) + 1);
                                    }
                                }
                                break;
                            }
                            case DOWN: {
                                if (grid[row + 1][col] == 0) {
                                    isValid = true;
                                } else {
                                    if(logEnabled) {
                                        log.info("You can not move there, because the ({}, {}) field is not empty!", col + 1, (row + 1) + 1);
                                    }
                                }
                                break;
                            }
                            default:
                                if(logEnabled) {
                                    log.info("Invalid direction, you can not move to that space!");
                                }
                                isValid = false;
                        }
                    }
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                if(logEnabled) {
                    log.warn("You can not move out of the field!");
                }
                isValid = false;
            }
        }
        return isValid;
    }

    public void move(Player currentPlayer, int row, int col, int direction) {

        if (isValidMove(currentPlayer, row, col, direction)) {
            switch (direction) {
                case RIGHT: {
                    grid[row][col+1] = grid[row][col];
                    grid[row][col] = 0;
                    log.info("{} moved from ({}, {}) to ({}, {}).", currentPlayer.getName(), col+1, row+1, (col+1)+1, row+1);
                    log.info(print2D(grid), currentPlayer.getName(), col+1, row+1, col+1, (row+1)+1);

                    break;
                }
                case LEFT: {
                    grid[row][col-1] = grid[row][col];
                    grid[row][col] = 0;
                    log.info("{} moved from ({}, {}) to ({}, {}).", currentPlayer.getName(), col+1, row+1, (col-1)+1, row+1);
                    log.info(print2D(grid), currentPlayer.getName(), col+1, row+1, col+1, (row+1)+1);

                    break;
                }
                case UP: {
                    grid[row-1][col] = grid[row][col];
                    grid[row][col] = 0;

                    log.info("{} moved from ({}, {}) to ({}, {}).", currentPlayer.getName(), col+1, row+1, col+1, (row-1)+1);
                    log.info(print2D(grid), currentPlayer.getName(), col+1, row+1, col+1, (row+1)+1);

                    break;
                }
                case DOWN: {
                    grid[row+1][col] = grid[row][col];
                    grid[row][col] = 0;
                    log.info("{} moved from ({}, {}) to ({}, {}).", currentPlayer.getName(), col+1, row+1, col+1, (row+1)+1);
                    log.info(print2D(grid), currentPlayer.getName(), col+1, row+1, col+1, (row+1)+1);

                    break;
                }

            }
        }

    }

    /**
     * A method that checks if the player with the red pucks has won the game.
     *
     * @return {@code true} if the player with the red pucks has won the game, {@code false} otherwise.
     */
    public boolean hasRedWon() {
        if(grid[0][0] == 2
                && grid[0][1] == 2
                && grid[0][2] == 2
                && grid[0][3] == 2
                && grid[0][4] == 2
                && grid[1][0] == 2
                && grid[1][4] == 2)
        {
            winner = redPlayer;
            loser = bluePlayer;
            return true;
        }
        else{
            return false;
        }

    }

    /**
     * A method that checks if the player with the blue pucks has won the game.
     *
     * @return {@code true} if the player with the blue pucks has won the game, {@code false} otherwise.
     */
    public boolean hasBlueWon() {

        if(grid[3][0] == 1
                && grid[3][4] == 1
                && grid[3][0] == 1
                && grid[3][1] == 1
                && grid[3][2] == 1
                && grid[3][3] == 1
                && grid[3][4] == 1)
        {
            winner = bluePlayer;
            loser = redPlayer;
            return true;
        }
        else{
            return false;
        }

    }

    public boolean isGameOver() {
        if (hasBlueWon() || hasRedWon()) {
            return true;
        }
        else {
            return false;
        }
    }


}
