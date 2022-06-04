/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sudoku;

public class GameBoard {

    private int[][] solution = new int[16][16];
    private int[][] initial = new int[16][16];
    private int[][] player;
    
    int dificuldade;
    int K;
    /*
     * @param dificuldade
     * @see Arrays
     */
    public GameBoard(int dificuldade) {
        int N = 16;  
        if(dificuldade==1){
            int max = 64;
            int min = 32;
            int range = max - min + 1;
            K = (int)(Math.random() * range) + min;
        }
        if(dificuldade==2){
            int max = 96;
            int min = 64;
            int range = max - min + 1;
            K = (int)(Math.random() * range) + min;
        }
        if(dificuldade==3){
            int max = 128;
            int min = 96;
            int range = max - min + 1;
            K = (int)(Math.random() * range) + min;
        }
        
        Sudoku sudokuGame = new Sudoku(N, K);
        sudokuGame.fillValues();
        
        int[][] valoresSolucao = sudokuGame.getSolution();
        
        sudokuGame.removeKDigits();
        int[][] valoresIniciais = sudokuGame.printSudoku1();
        
        for(int i = 0; i < 16; i++) {
            for (int j = 0; j < 16; j++) {
                this.solution[i][j] = valoresSolucao[i][j];
            }
        }

        for(int i = 0; i < 16; i++) {
            for (int j = 0; j < 16; j++) {
                this.initial[i][j] = valoresIniciais[i][j];
                
            }
        }

        player = new int[16][16];
    }

    /*
     * @return the solution array
     */
    public int[][] getSolution() {
        return solution;
    }

    /*
     * @return the initial filled-in numbers array
     */
    public int[][] getInitial() {
        return initial;
    }

    /*
     * @return the player array
     */
    public int[][] getPlayer() {
        return player;
    }

    /*
     * @param val the integer to insert in the player array
     * @param row location in array x
     * @param col location in array y
     */
    public void modifyPlayer(int val, int row, int col) {
        if (initial[row][col] == 0) {

            if (val >= -1 && val < 16) 
            {
                player[row][col] = val;
            } else 
            {
                System.out.println("Value passed to player falls out of range");
            }
        }

    }

    /*
     * @return true if player solution matches original solution, false if not
     */
    public boolean checkForSuccess() {
        for (int row = 0; row < 16; row++) {
            for (int col = 0; col < 16; col++) {

                if (initial[row][col] == 0) {

                    if (player[row][col] != solution[row][col]) {

                        return false;
                    }
                }

            }
        }
        return true;
    }

    /*
     * @return true if player solution is a correct one according to sudoku
     * rules
     */
    public boolean checkForSuccessGeneral() {
        int[][] combined = new int[16][16];
        for (int row = 0; row < 16; row++) {
            for (int col = 0; col < 16; col++) {
                if (initial[row][col] != 0) {
                    combined[row][col] = initial[row][col];
                } else {
                    combined[row][col] = player[row][col];
                }
            }
        }
        for (int row = 0; row < 16; row++) {
            int sum = 0;
            for (int col = 0; col < 16; col++) {
                sum = sum + combined[row][col];
            }
            if (sum != 136) {
                return false;
            }
        }

        for (int col = 0; col < 16; col++) { 
            int sum = 0;
            for (int row = 0; row < 16; row++) {
                sum = sum + combined[row][col];
            }
            if (sum != 136) {
                return false;
            }
        }

        for (int row_offset = 0; row_offset <16;  row_offset += 4) {
            for (int col_offset = 0; col_offset < 16; col_offset += 4) {
                int sum = 0;
                for (int row = 0; row < 4; row++) {

                    for (int col = 0; col < 4; col++) {
                        sum = sum + combined[row + row_offset][col + col_offset];
                    }
                }
                if (sum != 136) {
                    return false;
                }
            }
        }
        return true;
    }

}
