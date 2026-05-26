package game2048logic;

public class MatrixUtils {
    /**
     * 将给定的棋盘逆时针旋转 90 度。
     * 假设棋盘是方阵 (N x N)。
     */
    public static void rotateLeft(int[][] board) {
        int n = board.length;
        // 步骤 1：转置矩阵
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int temp = board[i][j];
                board[i][j] = board[j][i];
                board[j][i] = temp;
            }
        }
        // 步骤 2：反转每一列
        for (int j = 0; j < n; j++) {
            for (int i = 0; i < n / 2; i++) {
                int temp = board[i][j];
                board[i][j] = board[n - 1 - i][j];
                board[n - 1 - i][j] = temp;
            }
        }
    }

    /**
     * 将给定的棋盘顺时针旋转 90 度。
     * 假设棋盘是方阵 (N x N)。
     */
    public static void rotateRight(int[][] board) {
        int n = board.length;
        // 步骤 1：转置矩阵
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int temp = board[i][j];
                board[i][j] = board[j][i];
                board[j][i] = temp;
            }
        }
        // 步骤 2：反转每一行
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n / 2; j++) {
                int temp = board[i][j];
                board[i][j] = board[i][n - 1 - j];
                board[i][n - 1 - j] = temp;
            }
        }
    }
}
