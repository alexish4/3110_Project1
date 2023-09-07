import java.util.Scanner;

public class proj1 {
    
    private int eIndex = 100; //initializing to random value that won't be reached
    private int decimalIndex = 100; //initializing to random value that won't be reached
    private int exponentValue = 0; 
    private int endingIndex = 100;
    private int tenthValue = 100;
    private int exponentTenthValue = 100;
    private int rememberSuffix = 0; 
    private int rememberE = 0;
    private int rememberSign = 0;
    private int rememberDecimalPoint = 0;
    private int rememberUnderscore = 0;
    private int countUnderScoreAfterE = 0;
    private int countUnderScoreBeforeE = 0;
    private int countUnderScore = 0;
    private boolean isNegative = false;
    //private double result = 0;
    
    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);
        String input;
        System.out.println("Enter Java Floating Point Literal(Enter 'q' to end program): ");
        do {
            System.out.print(">>> ");
            input = keyboard.nextLine();
            proj1 demo = new proj1();
            int index = 0;
            double result = 0;
            demo.state1(input, index, result);
        } while (!input.equals("q"));
        System.out.println("Program Ended");
    }
    public void state1(String input, int index, double result) {
        for (int i = 0; i < input.length(); i++) { //remembering the locations of variables
            if (input.charAt(i) == '.') {
                decimalIndex = i;
                rememberDecimalPoint++;
            }
            else if (input.charAt(i) == 'e' || input.charAt(i) == 'E') {
                eIndex = i;
                rememberE++;
            }
            else if (input.charAt(i) == 'f' || input.charAt(i) == 'F' || input.charAt(i) == 'd' || input.charAt(i) == 'D') {
                endingIndex = i;
                rememberSuffix++;
            }
            else if (input.charAt(i) == '_') 
                countUnderScore++;
            else if (input.charAt(i) == '+' || input.charAt(i) == '-') {
                rememberSign++;
            }
        }
        if (decimalIndex != 100 && rememberE != 0) { //There is decimal and e
            for (int i = 0; i < input.length(); i++) { //remembering the locations of variables
                if(index < eIndex && input.charAt(i) == '_')
                    countUnderScoreBeforeE++;
                else if(index > eIndex && input.charAt(i) == '_')
                    countUnderScoreAfterE++;
            }
            exponentTenthValue = (input.length() - 1) - eIndex - countUnderScoreAfterE - rememberSign - 1;
            tenthValue = decimalIndex - 1 - countUnderScoreBeforeE + rememberSuffix;
        }
        else if (decimalIndex == 100 && rememberE == 0) // there is no decimal and no e
            tenthValue = endingIndex - 1 - countUnderScore;
        else if (decimalIndex != 100 && rememberE == 0) //there is a decimal and no e
            tenthValue = decimalIndex - 1 - countUnderScore;
        else if (decimalIndex == 100 && rememberE != 0) { // there is e and no decimal point
            for (int i = 0; i < input.length(); i++) { //remembering the locations of variables
                if(index < eIndex && input.charAt(i) == '_')
                    countUnderScoreBeforeE++;
                else if(index > eIndex && input.charAt(i) == '_')
                    countUnderScoreAfterE++;
            }
            exponentTenthValue = (input.length() - 1 - rememberSuffix) - eIndex - 1 - countUnderScoreAfterE - rememberSign;
            tenthValue = eIndex - 1 - countUnderScoreBeforeE;
        }
        

        if (index < (input.length() - 1) ) {
            if (input.charAt(index) == '0' || input.charAt(index) == '1' || input.charAt(index) == '2' 
            || input.charAt(index) == '3' || input.charAt(index) == '4' || input.charAt(index) == '5' 
            || input.charAt(index) == '6' || input.charAt(index) == '7'
                || input.charAt(index) == '8' || input.charAt(index) == '9') {
                state2(input, index, result);
            }
            else if (input.charAt(index) == '.')
                state3(input, index, result);
            else
                System.out.println("Failed");
        }
        else if (index == input.length() - 1 && input.charAt(index) != 'q')
            System.out.println("Failed");
    } 
    public void state2(String input, int index, double result) {
        result += (input.charAt(index) - 48) * Math.pow(10, tenthValue);
        tenthValue--;
        
        if (index < (input.length() - 1) ) {
            if (input.charAt(index + 1) == '0' || input.charAt(index + 1) == '1' || input.charAt(index + 1) == '2' 
                || input.charAt(index + 1) == '3' || input.charAt(index + 1) == '4' || input.charAt(index + 1) == '5' 
                || input.charAt(index + 1) == '6' || input.charAt(index + 1) == '7'
                    || input.charAt(index + 1) == '8' || input.charAt(index + 1) == '9') {
                    state2(input, index + 1, result);

            }
            else if (input.charAt(index + 1) == '.')
                state4(input, index + 1, result);
            else if (input.charAt(index + 1) == 'f' || input.charAt(index + 1) == 'F' || input.charAt(index + 1) == 'd' 
                || input.charAt(index + 1) == 'D') 
                state9(input, index + 1, result);
            else if (input.charAt(index + 1) == '_')
                state10(input, index + 1, result);
            else if (input.charAt(index + 1) == 'e' || input.charAt(index + 1) == 'E')
                state6(input, index + 1, result);
            else 
                System.out.println("Failed");

        }
        else if (index == input.length() - 1)
            System.out.println("Failed");
    } //done with directing states
    public void state3(String input, int index, double result) {
        if (index < (input.length() - 1) ) {
            if (input.charAt(index + 1) == '0' || input.charAt(index + 1) == '1' || input.charAt(index + 1) == '2' 
                || input.charAt(index + 1) == '3' || input.charAt(index + 1) == '4' || input.charAt(index + 1) == '5' 
                || input.charAt(index + 1) == '6' || input.charAt(index + 1) == '7'
                    || input.charAt(index + 1) == '8' || input.charAt(index + 1) == '9') {
                state5(input, index + 1, result);
            }
            else 
                System.out.println("Failed at state 3");
        }
        else
            System.out.println("Failed at state 3");
    } //done with directing states
    public void state4(String input, int index, double result) {
        if (index < (input.length() - 1) ) {
            if (input.charAt(index + 1) == '0' || input.charAt(index + 1) == '1' || input.charAt(index + 1) == '2' 
                || input.charAt(index + 1) == '3' || input.charAt(index + 1) == '4' || input.charAt(index + 1) == '5' 
                || input.charAt(index + 1) == '6' || input.charAt(index + 1) == '7'
                    || input.charAt(index + 1) == '8' || input.charAt(index + 1) == '9') {
                state5(input, index + 1, result);
            }
            else if (input.charAt(index + 1) == 'f' || input.charAt(index + 1) == 'F' || input.charAt(index + 1) == 'd' 
                || input.charAt(index + 1) == 'D') 
                state9(input, index + 1, result);
            else 
                System.out.println("Failed at state 4");
            
        }
        else if(index == (input.length() - 1))
            System.out.println(result + " is result");
    } //done with directing states
    public void state5(String input, int index, double result) {
        result += (input.charAt(index) - 48) * Math.pow(10, tenthValue);
        tenthValue--;

        if (index < (input.length() - 1) ) {
            if (input.charAt(index + 1) == '0' || input.charAt(index + 1) == '1' || input.charAt(index + 1) == '2' 
                || input.charAt(index + 1) == '3' || input.charAt(index + 1) == '4' || input.charAt(index + 1) == '5' 
                || input.charAt(index + 1) == '6' || input.charAt(index + 1) == '7'
                    || input.charAt(index + 1) == '8' || input.charAt(index + 1) == '9') {
                state5(input, index + 1, result);
            }
            else if (input.charAt(index + 1) == 'e' || input.charAt(index + 1) == 'E') 
                state6(input, index + 1, result);
            else if (input.charAt(index + 1) == 'f' || input.charAt(index + 1) == 'F' || input.charAt(index + 1) == 'd' 
                || input.charAt(index + 1) == 'D') 
                state9(input, index + 1, result);
            else if (input.charAt(index + 1) == '_')
                state10(input, index + 1, result);
            else 
                System.out.println("Failed at state 5");
        }
        else if (index == input.length() - 1) {
            System.out.println(result + " is result");
        }
    } //done with directing
    public void state6(String input, int index, double result) {
        if (index < (input.length() - 1) ) {
            if (input.charAt(index + 1) == '0' || input.charAt(index + 1) == '1' || input.charAt(index + 1) == '2' 
                || input.charAt(index + 1) == '3' || input.charAt(index + 1) == '4' || input.charAt(index + 1) == '5' 
                || input.charAt(index + 1) == '6' || input.charAt(index + 1) == '7'
                    || input.charAt(index + 1) == '8' || input.charAt(index + 1) == '9') {
                state8(input, index + 1, result);
            }
            else if (input.charAt(index + 1) == '+' || input.charAt(index + 1) == '-') 
                state7(input, index + 1, result);
            else 
                System.out.println("Failed at state 6");
            
        }
        else if (index == input.length() - 1) {
            System.out.println("Failed at state 6");
        }
    } //done with directing
    public void state7(String input, int index, double result) {
        if (input.charAt(index) == '-')
            isNegative = true;
        if (index < (input.length() - 1) ) {
            if (input.charAt(index + 1) == '0' || input.charAt(index + 1) == '1' || input.charAt(index + 1) == '2' 
                || input.charAt(index + 1) == '3' || input.charAt(index + 1) == '4' || input.charAt(index + 1) == '5' 
                || input.charAt(index + 1) == '6' || input.charAt(index + 1) == '7'
                    || input.charAt(index + 1) == '8' || input.charAt(index + 1) == '9') {
                state8(input, index + 1, result);
            }
            else 
                System.out.println("Failed at state 7");
        }
        else if (index == input.length() - 1) {
            System.out.println("Failed at state 7");
        }
    } //done with directing
    public void state8(String input, int index, double result) {
        exponentValue += (input.charAt(index) - 48) * Math.pow(10, exponentTenthValue);
        if (exponentTenthValue > 0)
            exponentTenthValue--;
        if (index < (input.length() - 1) ) {
            if (input.charAt(index + 1) == '_')
                state10(input, index + 1, result);
            else if (input.charAt(index + 1) == '0' || input.charAt(index + 1) == '1' || input.charAt(index + 1) == '2' 
            || input.charAt(index + 1) == '3' || input.charAt(index + 1) == '4' || input.charAt(index + 1) == '5' 
            || input.charAt(index + 1) == '6' || input.charAt(index + 1) == '7'
                || input.charAt(index + 1) == '8' || input.charAt(index + 1) == '9') {
                state8(input, index + 1, result);
            }
            else if (input.charAt(index + 1) == 'f' || input.charAt(index + 1) == 'F' || input.charAt(index + 1) == 'd' 
                || input.charAt(index + 1) == 'D') 
                state9(input, index + 1, result);
            else 
                System.out.println("Failed at state 8");
        }
        else if (index == input.length() - 1) {
            if(isNegative)
                exponentValue *= -1;
            result *= Math.pow(10, exponentValue);
            System.out.println(result + " is result");
        }
    } //done with directing
    public void state9(String input, int index, double result) { //final state
        if(rememberE != 0) {
            result *= Math.pow(10, exponentValue);
            System.out.println(result + " is result");
        }
        
        if(index != (input.length() - 1) )
            System.out.println("Failed at state 9");
        else if (index == input.length() - 1) {
            System.out.println(result + " is result");
        }
    }
    public void state10(String input, int index, double result) {
        if (index < (input.length() - 1) ) {
            if ( (input.charAt(index + 1) == '0' || input.charAt(index + 1) == '1' || input.charAt(index + 1) == '2' 
                || input.charAt(index + 1) == '3' || input.charAt(index + 1) == '4' || input.charAt(index + 1) == '5' 
                || input.charAt(index + 1) == '6' || input.charAt(index + 1) == '7'
                    || input.charAt(index + 1) == '8' || input.charAt(index + 1) == '9') && decimalIndex == 100 ) {
                state2(input, index + 1, result);
            }
            else if (input.charAt(index + 1) == 'f' || input.charAt(index + 1) == 'F' || input.charAt(index + 1) == 'd' 
                || input.charAt(index + 1) == 'D') 
                state9(input, index + 1, result);
            else if (input.charAt(index + 1) == '_')
                state10(input, index + 1, result);
            else if ( (input.charAt(index + 1) == '0' || input.charAt(index + 1) == '1' || input.charAt(index + 1) == '2' 
            || input.charAt(index + 1) == '3' || input.charAt(index + 1) == '4' || input.charAt(index + 1) == '5' 
            || input.charAt(index + 1) == '6' || input.charAt(index + 1) == '7'
                || input.charAt(index + 1) == '8' || input.charAt(index + 1) == '9') && rememberE != 0 ) {
                state8(input, index + 1, result);
            }
            else if ( (input.charAt(index + 1) == '0' || input.charAt(index + 1) == '1' || input.charAt(index + 1) == '2' 
            || input.charAt(index + 1) == '3' || input.charAt(index + 1) == '4' || input.charAt(index + 1) == '5' 
            || input.charAt(index + 1) == '6' || input.charAt(index + 1) == '7'
                || input.charAt(index + 1) == '8' || input.charAt(index + 1) == '9') && decimalIndex != 100 ) {
                state5(input, index + 1, result);
            }
            else 
                System.out.println("Failed at state 7");
        }
        else if (index == input.length() - 1) {
            System.out.println("Failed at state 7");
        }
    }
}
