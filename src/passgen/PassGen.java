/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package passgen;

import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author Fionán
 */
public class PassGen {

    /**
     * @param args the command line arguments
     */
    public static Random rand = new Random();
    private boolean capitalReq;
    private boolean lowerCaseReq;
    private boolean numberReq;
    private int passLength;
    private char[] password;
    private boolean[] req;

    public PassGen(boolean capitalReq, boolean lowerCaseReq, boolean numberReq, int passLength) {
        this.capitalReq = capitalReq;
        this.lowerCaseReq = lowerCaseReq;
        this.numberReq = numberReq;
        this.passLength = passLength;

        password = new char[this.passLength];
        req = new boolean[3];

        req[0] = this.capitalReq;
        req[1] = this.lowerCaseReq;
        req[2] = this.numberReq;

        int x = 0;
        //get requirments
        if (req[0]) {
            password[x] = getCapital();
            x++;
        }
        if (req[1]) {
            password[x] = getLower();
            x++;
        }
        if (req[2]) {
            password[x] = getNum();
            x++;
        }

        //get remaining details
        for (int i = 0; i < password.length; i++) {
            int choise = rand.nextInt(3);
            if (password[i] == '\u0000') {

                switch (choise) {
                    case 0:
                        password[i] = getCapital();
                        break;
                    case 1:
                        password[i] = getLower();
                        break;
                    default:
                        password[i] = getNum();
                }

            }
        }

        shuffle(password);

    }

    public String getPassword() {
        String pass = "";
        for (int i = 0; i < this.password.length; i++) {
            pass += "" + password[i];

        }
        return pass;

    }

    public static char getNum() {
        int number = rand.nextInt(57 - 49) + 49;
        return (char) number;
    }

    public static char getCapital() {
        int number = rand.nextInt(90 - 65) + 65;
        return (char) number;
    }

    public static char getLower() {
        int number = rand.nextInt(122 - 97) + 97;
        return (char) number;
    }

    public static void shuffle(char[] password) {

        int cycles = rand.nextInt(100);

        for (int i = 0; i < cycles; i++) {

            int place1 = rand.nextInt(password.length);
            char c = password[place1];
            int place2 = rand.nextInt(password.length);
            char temp = password[place2];
            password[place2] = c;
            password[place1] = temp;

        }

    }

}
