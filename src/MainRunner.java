import java.lang.reflect.Array;
import java.util.*;

import org.jlinalg.LinSysSolver;
import org.jlinalg.Matrix;
import org.jlinalg.Vector;
import org.jlinalg.rational.Rational;

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
        ArrayList<Compound> reactants = reactants(input.nextLine());
        Compound eleList = eleList(reactants);
        System.out.println("Type in the Products");
        ArrayList<Compound> products = products(input.nextLine());
        Rational[][] matrix = matrix(reactants, products,eleList);
        Rational[] vector = vector(products, eleList);
        System.out.println(Arrays.deepToString(matrix));
        for (Rational i: vector) {
            System.out.print(i + " ");
        }
        System.out.println();
        Matrix<Rational> a = new Matrix<Rational>(matrix);
        Vector<Rational> b = new Vector<Rational>(vector);
        Vector<Rational> solution = LinSysSolver.solve(a,b);
        System.out.println("The Balanced equation is:");
        System.out.println(solution + " 1");
    }

    public static ArrayList<Compound> reactants(String reactants) {
        ArrayList<Compound> reactantList = new ArrayList();
        if (reactants.indexOf("+") == -1){
            reactantList.add(compoundBuilder(reactants));
        } else {
            int i = 0;
            while (i < reactants.length()){
                if (reactants.substring(i).indexOf("+") != -1) {
                    int j = reactants.indexOf("+", i);
                    if (j == -1) {
                        throw new IllegalArgumentException("j is negative one");
                    }
                    reactantList.add(compoundBuilder(reactants.substring(i, j)));
                    i += reactants.substring(i).indexOf("+") + 1;
                } else {
                    reactantList.add(compoundBuilder(reactants.substring(i)));
                    i = reactants.length();
                }
            }
        }
        System.out.println(reactantList);
        return reactantList;
    }

    public static ArrayList<Compound> products(String products) {
        ArrayList<Compound> productList = new ArrayList();
        if (products.indexOf("+") == -1) {
            productList.add(compoundBuilder(products));
        } else {
            int i = 0;
            while (i < products.length()) {
                if (products.substring(i).indexOf("+") != -1) {
                    int j = products.indexOf("+", i);
                    if (j == -1) {
                        throw new IllegalArgumentException("j is negative one");
                    }
                    productList.add(compoundBuilder(products.substring(i, j)));
                    i += products.substring(i).indexOf("+") + 1;
                } else {
                    productList.add(compoundBuilder(products.substring(i)));
                    i = products.length();
                }
            }
        }
        System.out.println(productList);
        return productList;
    }

    public static Compound compoundBuilder(String compound) {
        while (compound.substring(0,1).equals(" ")) {
            compound = compound.substring(1);
        }
        while (compound.substring(compound.length()-1).equals(" ")) {
            compound = compound.substring(0, compound.length()-1);
        }
        if (compound.indexOf("(") > -1) {
            compound = paraFormatter(compound);
        }
        String eName = "";
        Compound eleList = new Compound();
        int eAmount;
        int i = 0;
        while (i < compound.length()) {
            eAmount = 0;
            int j = i+1;
            if (Character.isUpperCase(compound.charAt(i))) {
                if (i != compound.length()-1) {
                    if (Character.isLowerCase(compound.charAt(j)) || isInteger(compound.substring(j, j + 1))) {
                        if (i != compound.length() - 2) {
                            if (isInteger(compound.substring(j + 1, j + 2))) {
                                j += 2;
                            } else {
                                j++;
                            }
                        } else {
                            j++;
                        }
                    }
                }
                eName = compound.substring(i, j);
            }
            i += eName.length();
            int k = 0;
            do {
                if (isInteger(eName.substring(k, k + 1))) {
                    eAmount = Integer.parseInt(eName.substring(k, k + 1));
                    eName = eName.substring(0, eName.length()-1);
                    k++;
                } else {
                    k++;
                }
            } while (k < eName.length());
            if (eAmount == 0) {
                eAmount = 1;
            }
            eleList.addElement(new Element(eName, eAmount));
        }
        return eleList;
    }

    public static String paraFormatter(String compound) {
        int paraF = compound.indexOf("(");
        int paraB = compound.indexOf(")");
        int multiplier = 1;
        if (isInteger(compound.substring(paraB + 1, paraB + 2))) {
            multiplier = Integer.parseInt(compound.substring(paraB + 1, paraB + 2));
        }
        String formatted = "";
        if (paraF > 0) {
            formatted += compound.substring(0,paraF);
        }
        for (int i = paraF; i <= paraB+1-paraF; i++) {
            if (Character.isUpperCase(compound.charAt(i))) {
                if (isInteger(compound.substring(i+1, i+2))) {
                    formatted += compound.substring(i,i+1) + Integer.toString (Integer.parseInt(compound.substring(i+1,i+2))*multiplier);
                } else if (Character.isLowerCase(compound.charAt(i+1))) {
                    if (isInteger(compound.substring(i+2, i+3))) {
                        formatted += compound.substring(i,i+2) + Integer.toString(Integer.parseInt(compound.substring(i+2,i+3))*multiplier);
                    } else {
                        formatted += compound.substring(i,i+2) + Integer.toString(multiplier);
                    }
                } else {
                    formatted += compound.substring(i,i+1) + Integer.toString(multiplier);
                }

            }
        }
        if (paraB != compound.length()-1) {
            formatted += compound.substring(paraB+2);
        }
    return formatted;
    }

    public static Rational[][] matrix(ArrayList<Compound> reactants, ArrayList<Compound> products, Compound eleList) {
        int numCompounds = reactants.size()+ products.size();
        Rational[][] matrix = new Rational[eleList.length()][numCompounds-1];
        for (int i = 0; i < eleList.length(); i++) {
            for (int j = 0; j < reactants.size(); j++) {
                if (containsElement(reactants.get(j), eleList.getElement(i))) {
                    matrix[i][j] = Rational.FACTORY.get(reactants.get(j).getElement(eleList.getElement(i)).getAmount());
                } else {
                    matrix[i][j] = Rational.FACTORY.get(0);
                }
            }
        }
        for (int k = 0; k < eleList.length(); k++){
            for (int l = reactants.size(); l < numCompounds-1; l++) {
                if (containsElement(products.get(l-reactants.size()), eleList.getElement(k))) {
                    matrix[k][l] = Rational.FACTORY.get(-(products.get(l-reactants.size()).getElement(eleList.getElement(k)).getAmount()));
                } else {
                    matrix[k][l] = Rational.FACTORY.get(0);
                }
            }
        }
        return matrix;
    }

    public static int getMatrix(int[][] matrix, int a) {
        int count = 0;
        for (int[] i: matrix) {
            for (int j: i) {
                if (count == a) {
                    return j;
                }
                count++;
            }
        }
        return 0;
    }

    public static int getVector(int[] vector, int a) {
        int count = 0;
        for (int i: vector) {
            if (count == a) {
                return i;
            }
        }
        return 0;
    }

    public static Rational[] vector(ArrayList<Compound> products, Compound eleList) {
        Rational[] vector = new Rational[eleList.length()];
        for (int i = 0; i < eleList.length(); i++) {
            Element searchElement = eleList.getElement(i);
            Compound lastCompoundInEquation = products.get(products.size()-1);
            if (containsElement(lastCompoundInEquation, searchElement)) {
                vector[i] = Rational.FACTORY.get(lastCompoundInEquation.getElement(searchElement).getAmount());
            } else {
                vector[i] =Rational.FACTORY.get(0);
            }
        }
        return vector;
    }

    public static Compound eleList(ArrayList<Compound> a) {
        Compound eles = new Compound();
        for (Compound c : a) {
            for (int i = 0; i < c.length(); i++) {
                if (!containsElement(eles, c.getElement(i))) {
                    eles.addElement(c.getElement(i));
                }
            }
        }
        return eles;
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

    public static boolean containsElement(Compound a, Element b){
        for (int i = 0; i < a.length(); i++) {
            if (isSameElement(a.getElement(i), b)) {
                return true;
            }
        }
        return false;
    }
    public static boolean isSameElement(Element a, Element b) {
        if (a.getName().equals(b.getName())) {
            return true;
        }
        return false;
    }

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
