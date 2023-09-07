import java.util.Scanner;

public class Project1 {
    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);
        String input;
        System.out.println("Enter Java Floating Point Literal(Enter 'q' to end program): ");
        do {
            System.out.print(">>> ");
            input = keyboard.nextLine();
            Project1 demo = new Project1();
            demo.convertToFloat(input);
        } while (!input.equals("q"));
        System.out.println("Program Ended");
    }
    public void convertToFloat(String input) {
        double result = 0;
        boolean test = true;
        int countDecimalPoint = 0;
        int countE = 0;
        int rememberE = -2;
        int rememberSuffix = 0;
        int signCount = 0;
        int countUnderScoreBeforeE = 0;
        int countUnderScoreAfterE = 0;
        int countUnderScore = 0; //when no e
        int countSuffix = 0;
        boolean exponentNegative = false;
        boolean isDouble = true; //by default will be double
        for (int i = 0; i < input.length(); i++) { //testing if accepted or not
            if (input.charAt(i) != '0' && input.charAt(i) != '1' && input.charAt(i) != '2'
            && input.charAt(i) != '3' && input.charAt(i) != '4' && input.charAt(i) != '5'
            && input.charAt(i) != '6' && input.charAt(i) != '7' && input.charAt(i) != '8'
            && input.charAt(i) != '9' && input.charAt(i) != '.' && input.charAt(i) != 'e'
            && input.charAt(i) != 'f' && input.charAt(i) != '-' && input.charAt(i) != '+'
            && input.charAt(i) != '_' && input.charAt(i) != 'F' && input.charAt(i) != 'd'
            && input.charAt(i) != 'D' && input.charAt(i) != 'E') //if anytime something not in the alphabet appears reject
                test = false; //If not accepted then false
            if (input.charAt(i) == 'e' || input.charAt(i) == 'E') { //marking so we only have one e
                countE++;
                rememberE = i;
                if (i == 0) //no e's in the beginning
                    test = false;
                if (i == input.length()-1) //no e's in the end
                    test = false;
                //if there isn't a digit before and after e then reject
                if(i > 0 && input.charAt(i-1) != '0' && input.charAt(i-1) != '1' && input.charAt(i-1) != '2'
                && input.charAt(i-1) != '3' && input.charAt(i-1) != '4' && input.charAt(i-1) != '5'
                && input.charAt(i-1) != '6' && input.charAt(i-1) != '7' && input.charAt(i-1) != '8'
                && input.charAt(i-1) != '9')
                    test = false;
                if(i != input.length() - 1 && input.charAt(i+1) != '0' && input.charAt(i+1) != '1' && input.charAt(i+1) != '2'
                && input.charAt(i+1) != '3' && input.charAt(i+1) != '4' && input.charAt(i+1) != '5'
                && input.charAt(i+1) != '6' && input.charAt(i+1) != '7' && input.charAt(i+1) != '8'
                && input.charAt(i+1) != '9' && input.charAt(i+1) != '+' && input.charAt(i+1) != '-') 
                    test = false;
            }
            if (input.charAt(i) == '_') {
                if (i == 0) //can't have _ in the beginning
                    test = false;
                if (i == input.length() - 1) //can't have _ in the end
                    test = false;
                if (i == 0 && i == input.length() - 1)
                    test = false;
                //Can't have e or . right before or right after .
                if (i > 0 && input.charAt(i - 1) == '.')
                    test = false;
                if (i < input.length() - 1 && input.charAt(i + 1) == '.')
                    test = false; 
                if (i > 0 && (input.charAt(i - 1) == 'e' || input.charAt(i - 1) == 'E'))
                    test = false;
                if (i < input.length() - 1 && (input.charAt(i + 1) == 'e' || input.charAt(i + 1) == 'E'))
                    test = false; 
                if (test) {
                    if (i < (input.length() - 1) && (input.charAt(i + 1) == 'f') || (input.charAt(i + 1) == 'F') 
                                        || (input.charAt(i + 1) == 'd') || (input.charAt(i + 1) == 'D')) //can't have suffix right after _
                        test = false; 
                }
                countUnderScore++;
                if (i < rememberE)
                    countUnderScoreBeforeE++;
                else
                    countUnderScoreAfterE++;
            }
            if (i == input.length()-1 && (input.charAt(i) == 'f' || input.charAt(i) == 'F'))
                isDouble = false;
            if((i == input.length()-1) && ((input.charAt(i) == 'f' || input.charAt(i) == 'F' || input.charAt(i) == 'd' 
            || input.charAt(i) == 'D'))) //counting suffix
                countSuffix++;
            if (input.charAt(i) == '.') //marking so we only have one .
                countDecimalPoint++;
            if (countE > 1 || countDecimalPoint > 1) //Once e or . occurs it can't occur again
                test = false;
            if((i != input.length()-1) && input.charAt(i) == 'f') { //if f is not at the end then false
                test = false;
                isDouble = false;
            }
            if((i != input.length()-1) && input.charAt(i) == 'F') { //if f is not at the end then false
                test = false;
                isDouble = false;
            }
            if (i == 0 && i == input.length() - 1 && input.charAt(i) == '.') //if . is by itself
                test = false;
            if((i != input.length()-1) && input.charAt(i) == 'd') //if d is not at the end then false
                test = false;
            if((i != input.length()-1) && input.charAt(i) == 'D') //if d is not at the end then false
                test = false;
            if((i == input.length()-1) && (input.charAt(i) != 'f' && input.charAt(i) != 'F' && input.charAt(i) != 'd' 
            && input.charAt(i) != 'D') && countDecimalPoint == 0 && countE == 0) //if no suffixes, no decimal, and no e then false
                test = false;
            if((i != rememberE + 1) && input.charAt(i) == '-') { //if there is a - that is not right after e then it is false
                test = false;
            }
            if((i != rememberE + 1) && input.charAt(i) == '+') { //if there is a + that is not right after e then it is false
                test = false;
            }
        }                           //end of testing if accepted
        if (!test && !input.equals("q")) { //if false and not quitting
            System.out.println("Rejected");
        }
        int rememberDecimalPoint = 0;
        double exponentValue = 0;
        int eExponentPlaces = 0;
        boolean eFound = false;
        //getting the value based on 
        if (test && countDecimalPoint == 1 && countE == 0) { // decimal point and no e
            for (int i = 0; i < input.length(); i++) {
                if (input.charAt(i) == '.') 
                    rememberDecimalPoint = i;
            }
            int tenthValue = rememberDecimalPoint - 1 - countUnderScore;
            for (int i = 0; i < input.length(); i++) {
                if (input.charAt(i) == '0' || input.charAt(i) == '1' || input.charAt(i) == '2'
                || input.charAt(i) == '3' || input.charAt(i) == '4' || input.charAt(i) == '5'
                || input.charAt(i) == '6' || input.charAt(i) == '7' || input.charAt(i) == '8'
                || input.charAt(i) == '9') {
                    result+= (input.charAt(i) - 48) * Math.pow(10, tenthValue);
                    rememberDecimalPoint--;
                    tenthValue = rememberDecimalPoint - 1 - countUnderScore;
                }
            }
        } //end of decimal point and no e statement
        else if(test && countDecimalPoint == 1 && countE == 1) { //both decimal point and e
            for (int i = 0; i < input.length(); i++) {
                if (input.charAt(i) == '.') 
                    rememberDecimalPoint = i;
                if (input.charAt(i) == 'e' || input.charAt(i) == 'E') {
                    eFound = true;
                    rememberE = i;
                    if (input.charAt(i+1) == '+' || input.charAt(i+1) == '-') {
                        signCount++;
                        if (input.charAt(i+1) == '-')
                            exponentNegative = true;
                    }
                    eExponentPlaces = (input.length() - 1 - countSuffix) - rememberE - 1 - countUnderScoreAfterE - signCount; //remembering how many exponent spaces not including sign, suffix, etc
                }
            }
            int tenthValue = rememberDecimalPoint - 1 - countUnderScoreBeforeE;
            for (int i = 0; i < input.length(); i++) {
                if (input.charAt(i) == '0' || input.charAt(i) == '1' || input.charAt(i) == '2'
                || input.charAt(i) == '3' || input.charAt(i) == '4' || input.charAt(i) == '5'
                || input.charAt(i) == '6' || input.charAt(i) == '7' || input.charAt(i) == '8'
                || input.charAt(i) == '9') {
                    if (i < rememberE) {
                        result+= (input.charAt(i) - 48) * Math.pow(10, tenthValue);
                        rememberDecimalPoint--;
                        tenthValue = rememberDecimalPoint - 1 - countUnderScoreBeforeE;
                    }
                    else {
                        exponentValue += (input.charAt(i) - 48) * Math.pow(10, eExponentPlaces);
                        eExponentPlaces--;
                    }
                }
            }
            if (exponentNegative)
                exponentValue *= -1;
            result *= Math.pow(10, exponentValue);
        } //end of both decimal point and e statement
        else if (test && countDecimalPoint == 0 && countE == 0) { //must end in f or d
            for (int i = 0; i < input.length(); i++) {
                if (input.charAt(i) == 'f' || input.charAt(i) == 'F' || input.charAt(i) == 'd' || input.charAt(i) == 'D')
                    rememberSuffix = i;
            }
            int tenthValue = rememberSuffix - 1 - countUnderScore;
            for (int i = 0; i < input.length(); i++) {
                if (input.charAt(i) == '0' || input.charAt(i) == '1' || input.charAt(i) == '2'
                || input.charAt(i) == '3' || input.charAt(i) == '4' || input.charAt(i) == '5'
                || input.charAt(i) == '6' || input.charAt(i) == '7' || input.charAt(i) == '8'
                || input.charAt(i) == '9') {
                    result+= (input.charAt(i) - 48) * Math.pow(10, tenthValue);
                    rememberSuffix--;
                    tenthValue = rememberSuffix - 1 - countUnderScore;
                }
            }
        } //end of only suffix statement
        else if (test && countDecimalPoint == 0 && countE == 1) { //accepts 1e1
            for (int i = 0; i < input.length(); i++) {
                if (input.charAt(i) == 'e' || input.charAt(i) == 'E') {
                    eFound = true;
                    rememberE = i; 
                    if (input.charAt(i+1) == '+' || input.charAt(i+1) == '-') {
                        signCount++;
                        if (input.charAt(i+1) == '-')
                            exponentNegative = true;
                    }
                    eExponentPlaces = (input.length() - 1 - countSuffix) - rememberE - 1 - countUnderScoreAfterE - signCount; //remembering how many exponent spaces
                }
            }
            int tenthValue = rememberE - 1 - countUnderScoreBeforeE;
            for (int i = 0; i < input.length(); i++) {
                if (input.charAt(i) == '0' || input.charAt(i) == '1' || input.charAt(i) == '2'
                || input.charAt(i) == '3' || input.charAt(i) == '4' || input.charAt(i) == '5'
                || input.charAt(i) == '6' || input.charAt(i) == '7' || input.charAt(i) == '8'
                || input.charAt(i) == '9') {
                    if (tenthValue >= 0) {
                        result+= (input.charAt(i) - 48) * Math.pow(10, tenthValue);
                        //rememberDecimalPoint--;
                        tenthValue --;
                    }
                    else {
                        exponentValue += (input.charAt(i) - 48) * Math.pow(10, eExponentPlaces);
                        eExponentPlaces--;
                    }
                }
            }
            if (exponentNegative)
                exponentValue *= -1;
            result *= Math.pow(10, exponentValue);
        } //end of no decimal and one e
        float finalResult = 0f;
        finalResult = (float)result; //converting from double to float
        if (test) { //if true and where we decide if double or not
            if (isDouble) 
                System.out.println(result);
            else
                System.out.println(finalResult);
        }
    }
}