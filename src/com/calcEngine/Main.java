package com.calcEngine;

import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        if(args.length==0) {
            performCalculations();
        }else if(args.length == 3){
            performOperation(args);
        }else if(args.length==1 && args[0].equals("interactive")){
            executeInteractively();
        }else{
            System.out.println("Incorrect number of args given");
        }
    }

    static void performCalculations(){

        MathEquation[] equations = new MathEquation[4];
        equations[0] = new MathEquation(MathOperation.DIVIDE, 50.0d, 100.0d);
        equations[1] = new MathEquation(MathOperation.ADD, 92.0d, 25.0d);
        equations[2] = new MathEquation(MathOperation.MULTIPLY, 17.0d, 225.0d);
        equations[3] = new MathEquation(MathOperation.SUBTRACT, 3.0d, 11.0d);

        for(MathEquation equation: equations){
            equation.execute();
            System.out.println(equation);//When println see object it will automatically call toString() method
        }
        System.out.println("Average = " + MathEquation.getAvgResult());

        //useOverloads();
    }

    static void useOverloads(){
        MathEquation equationOverload = new MathEquation(MathOperation.DIVIDE);
        double leftDouble = 9.0d;
        double rigthDouble = 10.0d;

        equationOverload.execute(leftDouble, rigthDouble);
        System.out.println("Overload with Doubles " + equationOverload.getResult());

        int leftInt = 9;
        int rightInt = 4;
        equationOverload.execute(9, 4);
        System.out.println("Overload with ints : " + equationOverload.getResult());
    }

    static void executeInteractively(){
        /*
        We take input with System.in and use Scanner to maintain it
         */
        System.out.println("Enter an operation and two numbers: ");
        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.nextLine();
        String[] parts = userInput.split(" ");// What this does is if we have multiply three four it splits it via space and returns an array
        performOperation(parts);
    }

    private static void performOperation(String[] parts) {
        MathOperation opCode = MathOperation.valueOf(parts[0].toUpperCase());
        double leftVal = valueFromWord(parts[1]);
        double rightVal = valueFromWord(parts[2]);
        MathEquation equation = new MathEquation(opCode, leftVal, rightVal);
        equation.execute();
        System.out.println(equation);
    }

    static double valueFromWord(String word){
        String[] numberWords = {
            "zero", "one" ,"two" ,"three", "four", "five", "six", "seven" ,"eight" , "nine"
        };
        boolean isValueSet = false;
        double value = 0.0d;
        for(int i=0; i<numberWords.length; i++){
            if(word.equals(numberWords[i])){
                value = i;
                isValueSet = true;
                break;
            }
        }
        if(!isValueSet){
            value = Double.parseDouble(word);
        }
        return value;
    }
}
