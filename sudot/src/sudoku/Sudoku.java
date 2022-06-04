/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sudoku;
 
public class Sudoku
{
    int[] solution[];
    int N; 
    int SRN;
    int K; 
    int matu [][];

    Sudoku(int N, int K)
    {
        this.N = N;
        this.K = K;

        Double SRNd = Math.sqrt(N);
        SRN = SRNd.intValue();
 
        solution= new int[N][N];
    }
 
    public void fillValues()
    {
        fillDiagonal();
 
        fillRemaining(0, SRN);
    }
 
    void fillDiagonal()
    {
 
        for (int i = 0; i<N; i=i+SRN)
 
            fillBox(i, i);
    }
 
    boolean unUsedInBox(int rowStart, int colStart, int num)
    {
        for (int i = 0; i<SRN; i++)
            for (int j = 0; j<SRN; j++)
                if (solution[rowStart+i][colStart+j]==num)
                    return false;
 
        return true;
    }

    void fillBox(int row,int col)
    {
        int num;
        for (int i=0; i<SRN; i++)
        {
            for (int j=0; j<SRN; j++)
            {
                do
                {
                    num = randomGenerator(N);
                }
                while (!unUsedInBox(row, col, num));
 
                solution[row+i][col+j] = num;
            }
        }
    }
 
    int randomGenerator(int num)
    {
        return (int) Math.floor((Math.random()*num+1));
    }
 
    boolean CheckIfSafe(int i,int j,int num)
    {
        return (unUsedInRow(i, num) &&
                unUsedInCol(j, num) &&
                unUsedInBox(i-i%SRN, j-j%SRN, num));
    }
 
    boolean unUsedInRow(int i,int num)
    {
        for (int j = 0; j<N; j++)
           if (solution[i][j] == num)
                return false;
        return true;
    }

    boolean unUsedInCol(int j,int num)
    {
        for (int i = 0; i<N; i++)
            if (solution[i][j] == num)
                return false;
        return true;
    }
 
    boolean fillRemaining(int i, int j)
    {
        //  System.out.println(i+" "+j);
        if (j>=N && i<N-1)
        {
            i = i + 1;
            j = 0;
        }
        if (i>=N && j>=N)
            return true;
 
        if (i < SRN)
        {
            if (j < SRN)
                j = SRN;
        }
        else if (i < N-SRN)
        {
            if (j==(int)(i/SRN)*SRN)
                j =  j + SRN;
        }
        else
        {
            if (j == N-SRN)
            {
                i = i + 1;
                j = 0;
                if (i>=N)
                    return true;
            }
        }
 
        for (int num = 0; num<=N; num++)
        {
            if (CheckIfSafe(i, j, num))
            {
                solution[i][j] = num;
                if (fillRemaining(i, j+1))
                    return true;
 
                solution[i][j] = 0;
            }
        }
        return false;
    }
 
    public void removeKDigits()
    {
        matu =solution;
        int count = K;
        while (count != 0)
        {
            int cellId = randomGenerator(N*N)-1;
 
            int i = (cellId/N);
            int j = cellId%16;
            if (j != 0)
                j = j - 1;
 
            if (matu[i][j] != 0)
            {
                count--;
                matu[i][j] = 0;
            }
        }
    }
      public int[][] printSudoku()
    {
        return this.matu;
    }
 

    public int[][] getSolution() {
        return this.solution;
    }

      public int[][] printSudoku1()
    {
        return this.matu;
    }
      

    public static void main(String[] args)
    {
        int N = 16, K = 192;
        Sudoku sudoku = new Sudoku(N, K);
        sudoku.fillValues();
        sudoku.printSudoku();
        sudoku.removeKDigits();
        sudoku.printSudoku1();
    }
}
