package org.example;

import db.DataBase;

import java.sql.*;
import java.util.Random;
import java.util.Scanner;

import static db.DataBase.getOrigin;
import static db.DataBase.getTranslator;

public class VocabBuilder {
    static int min = 1;
    static int max = 4;


    private int questionNumber = 0;
    private boolean action1 = true;
    private boolean action = true;
    private int trueAnswer = 0;
    private int falseAnswer = 0;

    public void start() {
        while (true) {
            showMenu();
            int action = getAction();
            switch (action) {
                case 1:
                    create();
                    break;
                case 2:
                    test();
                    break;
                case 3:
                    listofWord();
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Wrong Action Mazgi.");
            }


        }
    }

    private void listofWord() {

    }

    private Integer getAction() {
        System.out.print("Enter Action: ");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }

    public void showMenu() {
        System.out.println("*** Vocabulary Builder ***");
        System.out.println("1. Create");
        System.out.println("2. Test");
        System.out.println("3. Show list of words");
        System.out.println("0. Exit");
        System.out.print("Enter action : ");
    }


    public static int create() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the new word: ");
        String origin = scanner.next();
        System.out.println("Enter the translate: ");
        String translate = scanner.next();

        Connection connection = DataBase.getConnection();
        try {
            String sql = "insert into word (origin,translator) " + " values ('%s','%s')";
            sql = String.format(sql, origin, translate);


            Statement statement = connection.createStatement();
            return statement.executeUpdate(sql);

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return 0;
    }


    public void test() {

        while (action1) {
            showTest();
            if (questionNumber == 5) {
                System.out.println("True answer: " + trueAnswer);
                System.out.println("False answer: " + falseAnswer);
                questionNumber = 0;
                trueAnswer = 0;
                falseAnswer = 0;
                return;
            }
        }


    }

    public void showTest() {
        questionNumber++;
        Random random1 = new Random();
        int int_random = random1.nextInt(max - min + 1) + min;
        int a = random1.nextInt(max - min + 1) + min;
        int b = random1.nextInt(max - min + 1) + min;
        int c = random1.nextInt(max - min + 1) + min;
        int d = random1.nextInt(max - min + 1) + min;
        if (a == b || a == c || a == d) {
            Random random2 = new Random();

            int a1 = random2.nextInt(max - min + 1) + min;
            a = a1;
            if (b == a || b == d || b == c) {

                int b1 = random2.nextInt(max - min + 1) + min;
                b = b1;
            }
            if (c == a || c == b || c == d) {


                int c1 = random2.nextInt(max - min + 1) + min;
                c = c1;
            }
            if (d == a || d == b || d == c) {


                int d1 = random2.nextInt(max - min + 1) + min;
                d = d1;
            }
        }
        if (b == a || b == d || b == c) {
            Random random2 = new Random();
            int b1 = random2.nextInt(max - min + 1) + min;
            b = b1;
            if (a == b || a == c || a == d) {

                int a1 = random2.nextInt(max - min + 1) + min;
                a = a1;
            }
            if (c == a || c == b || c == d) {


                int c1 = random2.nextInt(max - min + 1) + min;
                c = c1;
            }
            if (d == a || d == b || d == c) {


                int d1 = random2.nextInt(max - min + 1) + min;
                d = d1;
            }
        }
        if (c == a || c == b || c == d) {
            Random random2 = new Random();

            int c1 = random2.nextInt(max - min + 1) + min;
            c = c1;
            if (a == b || a == c || a == d) {

                int a1 = random2.nextInt(max - min + 1) + min;
                a = a1;
            }
            if (b == a || b == c || b == d) {


                int b1 = random2.nextInt(max - min + 1) + min;
                b = b1;
            }
            if (d == a || d == b || d == c) {


                int d1 = random2.nextInt(max - min + 1) + min;
                d = d1;
            }

        }
        if (d == a || d == b || d == c) {
            Random random2 = new Random();

            int d1 = random2.nextInt(max - min + 1) + min;
            d = d1;
            if (a == b || a == c || a == d) {

                int a1 = random2.nextInt(max - min + 1) + min;
                a = a1;
            }
            if (c == a || c == b || c == d) {


                int c1 = random2.nextInt(max - min + 1) + min;
                c = c1;
            }
            if (c == a || c == b || c == d) {


                int c1 = random2.nextInt(max - min + 1) + min;
                c = c1;
            }
        }
        System.out.println("******************************************");
        System.out.println("Question Number: " + questionNumber);
        System.out.println(getOrigin(int_random));
        System.out.println("a) " + getTranslator(a));
        System.out.println("b) " + getTranslator(b));
        System.out.println("c) " + getTranslator(c));
        System.out.println("d) " + getTranslator(d));

        while (action) {
            System.out.println("Enter Answer: ");
            Scanner scanner = new Scanner(System.in);
            String answer = scanner.next();

            int answerIndex = getIndexFromAnswerLetter(answer);// a - 1
            if (answerIndex == 0) {
                if (int_random == a) {
                    trueAnswer++;
                } else {
                    falseAnswer++;
                }

                max += 4;
                min += 4;
                return;
            }
            if (answerIndex == 1) {
                if (int_random == b) {
                    trueAnswer++;
                } else {
                    falseAnswer++;
                }

                max += 4;
                min += 4;
                return;
            }
            if (answerIndex == 2) {
                if (int_random == c) {
                    trueAnswer++;
                } else {
                    falseAnswer++;
                }

                max += 4;
                min += 4;
                return;
            }
            if (answerIndex == 3) {
                if (int_random == d) {
                    trueAnswer++;
                } else {
                    falseAnswer++;
                }

                max += 4;
                min += 4;
                return;
            }
            if (answerIndex == -1) {
                System.out.println("Mazgi.");
            }

        }

    }


    public int getIndexFromAnswerLetter(String answer) {
        switch (answer) {
            case "a":
                return 0;
            case "b":
                return 1;
            case "c":
                return 2;
            case "d":
                return 3;
        }
        return -1;
    }


}
