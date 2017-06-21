/**
 * Created by zetrov on 2016/12/4.
 *
 * 二维数组向右自增，向下自增，查找其中某数字是否存在
 */
public class _3_FindInMatrix {

    public static void main(String[] args) {
        int[][] matrix = {
                {1,2,8,9},
                {2,4,9,12},
                {4,7,10,13},
                {6,8,11,15}
        };

        int max_row = matrix.length-1;
        int max_col = matrix[0].length-1;

        System.out.println(findInMatrix(matrix, max_row, max_col, 6));
        System.out.println(Find(7, matrix));
    }

    public static boolean findInMatrix(int[][] matrix, int rows, int cols, int num){
        if(matrix == null || rows < 0 || cols < 0)
            return false;
        int row = 0;
        int col = cols;
        while(row <= rows && cols >= 0){
            //System.out.println(matrix[row][col]);
            if(matrix[row][col] > num){
                col--;
            }else if(matrix[row][col] < num) {
                row++;
            }else
                return true;
        }
        return false;
    }

    //通过
    public static boolean Find(int target, int [][] array) {
        int rows = array.length-1;
        int cols = array[0].length-1;
        if(rows == 0 || cols == 0 || array[0][0] > target)
            return false;

        //从矩阵左下角的元素开始查找，该元素为所在列最大值，所在行最小值
        int row = rows, col = 0;
        while(col <= cols && row >= 0){
            if(target < array[row][col])
                row--;
            else if(target > array[row][col])
                col++;
            else
                return true;
        }
        return false;
    }
}
