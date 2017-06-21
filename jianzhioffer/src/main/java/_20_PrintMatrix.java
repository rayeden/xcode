import java.util.ArrayList;

/**
 * Created by Zetrov on 2016-12-27.
 */
public class _20_PrintMatrix {

    public static void main(String[] args) {
        int[][] matrix = {
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12},
                {13, 14, 15, 16}
        };

        int[][] matrix2 = {
                {1}, {2}, {3}, {4}, {5}
        };

        int[][] matrix3 = {
                {1, 2, 3, 4},
                {5, 6, 7, 8}
        };

        int[][] matrix4 = {
                {1,2,3,4,5}
        };

        ArrayList<Integer> list = printMatrix(matrix);
        System.out.println(list.toString());
        System.out.println();

        list = printMatrix(matrix2);
        System.out.println(list.toString());
        System.out.println();

        list = printMatrix(matrix3);
        System.out.println(list.toString());
        System.out.println();

        list = printMatrix(matrix4);
        System.out.println(list.toString());
        System.out.println();
    }


    public static ArrayList<Integer> printMatrix(int[][] matrix) {
        if (matrix == null)
            return null;
        ArrayList<Integer> list = new ArrayList<Integer>();
        int leftup_row = 0, leftup_col = 0;
        int rightdown_row = matrix.length - 1;
        int rightdown_col = matrix[0].length - 1;

        while (leftup_row <= rightdown_row && leftup_col <= rightdown_col) {

            //上方一行
            for (int i = leftup_col; i <= rightdown_col; ++i)
                list.add(matrix[leftup_row][i]);
            leftup_row++;

            //右侧一列
            if (leftup_row <= rightdown_row) {
                for (int j = leftup_row; j <= rightdown_row; ++j)
                    list.add(matrix[j][rightdown_col]);
            }
            rightdown_col--;

            //下方一行
            if (leftup_col <= rightdown_col && leftup_row <= rightdown_row) {
                for (int i = rightdown_col; i >= leftup_col; --i)
                    list.add(matrix[rightdown_row][i]);
            }
            rightdown_row--;

            //左侧一列
            if (leftup_row <= rightdown_row && leftup_col <= rightdown_col) {
                for (int j = rightdown_row; j >= leftup_row; --j)
                    list.add(matrix[j][leftup_col]);
            }
            leftup_col++;
        }
        return list;
    }
}
