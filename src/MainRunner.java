import java.lang.reflect.Array;
import java.util.*;

import org.jlinalg.LinSysSolver;
import org.jlinalg.Matrix;
import org.jlinalg.RingElement;
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
        String inputReactants = input.nextLine();
        ArrayList<Compound> reactants = reactants(inputReactants);
        Compound eleList = eleList(reactants);
        System.out.println("Type in the Products");
        String inputProducts = input.nextLine();
        ArrayList<Compound> products = products(inputProducts);
        String[] compounds = CompoundFormatter(inputReactants, inputProducts, reactants.size(), products.size());
        for (String s : compounds) {
            System.out.print(s + " ");
        }
        Rational[][] matrix = matrix(reactants, products,eleList);
        Rational[] vector = vector(products, eleList);
        //System.out.println(Arrays.deepToString(matrix));
        for (Rational i: vector) {
            System.out.print(i + " ");
        }
        System.out.println();
        Matrix<Rational> a = new Matrix<Rational>(matrix);
        Vector<Rational> b = new Vector<Rational>(vector);
        Vector<Rational> solution = LinSysSolver.solve(a,b);
        System.out.print("The Balanced equation is: ");
        Rational[] printSol = solutionFormatter(solution);
        int count = 0;
        for (String s : compounds) {
            if (!printSol[count].toString().equals("1")) {
                System.out.print(printSol[count].toString() + spaceFormatter(s) + " ");
            } else {
                System.out.print(spaceFormatter(s) + " ");
            }
                if (count == reactants.size()-1) {
                    System.out.print("= ");
                } else if (count != compounds.length-1) {
                    System.out.print("+ ");
                }
                count++;
            }
            /**
        System.out.print("= ");
        int count1 = reactants.size();
        for (Compound v: products) {
                System.out.print(printSol[count1].toString() + v + " ");
                count1++;
            }
        System.out.println();
        for (int j = 0; j < printSol.length; j++) {
            System.out.print(printSol[j] + " ");
        }
             **/
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
                    reactantList.add(compoundBuilder(reactants.substring(i, j)));
                    i += reactants.substring(i).indexOf("+") + 1;
                } else {
                    reactantList.add(compoundBuilder(reactants.substring(i)));
                    i = reactants.length();
                }
            }
        }
        //System.out.println(reactantList);
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
        //System.out.println(productList);
        return productList;
    }

    public static Compound compoundBuilder(String compound) {
        compound = spaceFormatter(compound);
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

    public static Rational[] solutionFormatter(Vector<Rational> solution) {
        int length = solution.length();
        Rational[] a = new Rational[length+1];
        for (int i = 0; i < solution.length(); i++) {
            a[i] = solution.getEntry(i+1);
        }
        Rational r1;
        r1 = Rational.FACTORY.get(1);
        a[length] = r1;
        for (int j = 1; j < a.length; j++) {
            if (!a[j].getDenominator().equals(r1.getDenominator())) {
                Rational multi = Rational.FACTORY.get(a[j].getDenominator().intValue());
                for (int k = 0; k < a.length; k++) {
                    a[k] = a[k].multiply(multi);
                }
            }
        }
        return a;
    }

    public static String spaceFormatter(String a) {
        while (a.substring(0,1).equals(" ")) {
            a = a.substring(1);
        }
        while (a.substring(a.length()-1).equals(" ")) {
            a = a.substring(0, a.length()-1);
        }
        return a;
    }

    public static String[] CompoundFormatter(String reactants, String products, int numR, int numP) {
        String[] formatted = new String[numR+numP];
        if (reactants.indexOf("+") == -1) {
            formatted[0] = reactants;
        } else {
            int i = 0;
            int k = 0;
            while (i < reactants.length()){
                if (reactants.substring(i).indexOf("+") != -1) {
                    int j = reactants.indexOf("+", i);
                    formatted[k] = spaceFormatter(reactants.substring(i,j));
                    i += reactants.substring(i).indexOf("+") + 1;
                    k++;
                } else {
                    spaceFormatter(formatted[k] = reactants.substring(i));
                    i = reactants.length();
                    k++;
                }
            }
        }
        if (products.indexOf("+") == -1) {
            formatted[0] = products;
        } else {
            int h = 0;
            int g = numR;
            while (h < products.length()){
                if (products.substring(h).indexOf("+") != -1) {
                    int f = products.indexOf("+", h);
                    formatted[g] = spaceFormatter(products.substring(h,f));
                    h += products.substring(h).indexOf("+") + 1;
                    g++;
                } else {
                    formatted[g] = spaceFormatter(products.substring(h));
                    h = products.length();
                    g++;
                }
            }
        }
        return formatted;
    }

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
