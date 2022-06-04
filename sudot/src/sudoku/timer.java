/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sudoku;

public class timer {

    private int horas;
    private int minutos;
    private int segundos;

    public timer(int horas, int minutos, int segundos) {
        this.horas = horas;
        this.minutos = minutos;
        this.segundos = segundos;
    }

    public timer(String CurrentTime) {
        String[] time = CurrentTime.split(":");
        horas = Integer.parseInt(time[0]);
        minutos = Integer.parseInt(time[1]);
        segundos = Integer.parseInt(time[2]);
    }

    public String getCurrentTime() {
        return horas + ":" + minutos + ":" + segundos;
    }

    public void oneSecondPassed() {
        segundos++;
        if (segundos == 60) {
            minutos++;
            segundos = 0;
            if (minutos == 60) {
                horas++;
                minutos = 0;
            }
        }
    }

}
