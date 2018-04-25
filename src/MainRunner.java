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
        Scanner input = new Scanner (System.in);
        System.out.println("How many reactants are there?");
        int reactantNumber = input.nextInt();
        for (int i = 0; i < reactantNumber; i++) {
            reactants(input, reactantNumber);
        }
        System.out.println("How many products are there?");
        int productNumber = input.nextInt();
        products(input, productNumber);
    }

    public static void reactants(Scanner input, int reactantNumber) {
        for (int i = 0; i < reactantNumber; i++) {
            if (i == 0) {
                Compound reactant1 = compoundGenerator(input);
                i++;
            } else if (i == 1) {
                Compound reactant2 = compoundGenerator(input);
                i++;
            } else if (i == 2) {
                Compound reactant3 = compoundGenerator(input);
                i++;
            } else if (i == 3) {
                Compound reactant4 = compoundGenerator(input);
                i++;
            } else if (i == 4) {
                Compound reactant5 = compoundGenerator(input);
                i++;
            } else {
                System.out.println("I'm broken! Fix me!");
            }
        }
    }

    public static void products(Scanner input, int productNumber){
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
            System.out.println("What is the element's name?");
            String tempElementName = input.next();
            System.out.println("How many are in the compound?");
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
}
