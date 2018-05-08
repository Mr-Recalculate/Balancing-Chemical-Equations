import java.util.ArrayList;
import java.util.Scanner;

/**
 * Gabriel Young
 * AP CS A
 * 4/24/2018
 * Assignment # - Balancing Chemical Equations
 * Description:
 */
public class MainRunner {

    public static void main(String args[]) {
        Scanner input = new Scanner(System.in);
        System.out.println("Type in the Reactants");
        String reactants = input.next();
        reactants(reactants);
        System.out.println("Type in the Products");
        String products = input.next();
    }

    public static void reactants(String reactants) {
        System.out.println(compoundBuilder(reactants));
    }

    public static Compound compoundBuilder(String compound) {
        String a = "";
        int e = 0;
        ArrayList<Element> eleList = new ArrayList<>();
        Element temp = new Element(null, 0);
        int i = 0;
        while (i < compound.length()) {
            boolean check = true;
            int j = i;
                if ((Character.isUpperCase(compound.charAt(j)))) {
                    do {
                        if (Character.isUpperCase(compound.charAt(j+1)) || isInteger(compound.substring(j+1,j+2))) {
                            a = compound.substring(i, j+1);
                            check = false;
                        }
                        j++;
                        if (j > compound.length()) {
                            check = false;
                        }
                    } while (check);
                }
            int k = 0;
            do {
                if (isInteger(a.substring(k, k + 1))) {
                    e = Integer.parseInt(a.substring(k, k + 1));
                } else {
                    k++;
                }
            } while (k < a.length());
            temp.setName(a);
            temp.setAmount(e);
            eleList.add(temp);
            i += a.length();
        }
        return new Compound(eleList);
    }
    /**
    public static void products(String productNumber){
        for (int i = 0; i < productNumber; i++) {
            if (i == 0) {
                Compound product1 = compoundGenerator(input);
                i++;
            } else if (i == 1) {
                Compound product2 = compoundGenerator(input);
                i++;
            } else if (i == 2) {
                Compound product3 = compoundGenerator(input);
                i++;
            } else if (i == 3) {
                Compound product4 = compoundGenerator(input);
                i++;
            } else if (i == 4) {
                Compound product5 = compoundGenerator(input);
                i++;
            } else {
                System.out.println("I'm broken! Fix me!");
            }
        }
    }
    public static Compound compoundGenerator(Scanner input) {
        System.out.println("What is the compound's name?");
        String CompoundName = input.next();
        System.out.println("How many elements are in the compound?");
        int elementNumber = input.nextInt();
        Element element1 = new Element(null, -1);
        Element element2 = new Element(null, -1);
        Element element3 = new Element(null, -1);
        Element element4 = new Element(null, -1);
        Element element5 = new Element(null, -1);
        for (int i = 0; i <= elementNumber; i++) {
            System.out.print("What is the element's name?");
            String tempElementName = input.next();
            System.out.print("How many are in the compound?");
            int tempElementAmount = input.nextInt();
            if (i == 0) {
                element1.setName(tempElementName);
                element1.setAmount(tempElementAmount);
                i++;
            } else if (i == 1) {
                element2.setName(tempElementName);
                element2.setAmount(tempElementAmount);
                i++;
            } else if (i == 2) {
                element3.setName(tempElementName);
                element3.setAmount(tempElementAmount);
                i++;
            } else if (i == 3) {
                element4.setName(tempElementName);
                element4.setAmount(tempElementAmount);
                i++;
            } else if (i == 4) {
                element5.setName(tempElementName);
                element5.setAmount(tempElementAmount);
                i++;
            } else {
                System.out.println("Too many elements!");
            }
        }
        if (elementNumber == 2) {
            return new Compound(CompoundName, element1, element2);
        } else if (elementNumber == 3) {
            return new Compound(CompoundName, element1,element2,element3);
        } else if (elementNumber == 4) {
            return new Compound(CompoundName, element1, element2, element3, element4);
        } else if (elementNumber == 5) {
            return new Compound(CompoundName, element1, element2, element3, element4, element5);
        } else return null;

     }
    **/

    public static boolean isInteger(String s) {
        boolean isValidInteger = false;
        try {
            Integer.parseInt(s);
            // s is a valid integer
            isValidInteger = true;
        }
        catch (NumberFormatException ex) {
            // s is not an integer
        }
        return isValidInteger;
    }
}
