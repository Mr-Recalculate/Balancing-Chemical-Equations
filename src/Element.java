/**
 * Gabriel Young
 * AP CS A
 * 4/24/2018
 * Assignment # - Balancing Chemical Equations
 * Description:
 */
public class Element {
    private String name;
    private int amount;
    public Element(String name, int amount){
        this.name = name;
        this.amount = amount;
    }

    public String getName () {
        return this.name;
    }
    public String setName (String name) {
        return name;
    }
    public int getAmount () {
        return this.amount;
    }
    public int setAmount (int amount) {
        return amount;
    }

    @Override
    public String toString() {
        return (name + amount).toString();
    }
}
