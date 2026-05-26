package game2048logic;

public class MatrixUtils {
    /**
     * 将给定棋盘向左（逆时针）旋转90度。
     * 假设棋盘是方阵（N x N）。
     */
    public static void rotateLeft(int[][] board) {
        int n = board.length;
        // 第一步：转置矩阵
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int temp = board[i][j];
                board[i][j] = board[j][i];
                board[j][i] = temp;
            }
        }
        // 第二步：反转每一列
        for (int j = 0; j < n; j++) {
            for (int i = 0; i < n / 2; i++) {
                int temp = board[i][j];
                board[i][j] = board[n - 1 - i][j];
                board[n - 1 - i][j] = temp;
            }
        }
    }

    /**
     * 将给定棋盘向右（顺时针）旋转90度。
     * 假设棋盘是方阵（N x N）。
     */
    public static void rotateRight(int[][] board) {
        int n = board.length;
        // 第一步：转置矩阵
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int temp = board[i][j];
                board[i][j] = board[j][i];
                board[j][i] = temp;
            }
        }
        // 第二步：反转每一行
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n / 2; j++) {
                int temp = board[i][j];
                board[i][j] = board[i][n - 1 - j];
                board[i][n - 1 - j] = temp;
            }
        }
    }
}
