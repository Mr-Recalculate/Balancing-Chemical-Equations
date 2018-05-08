import java.util.ArrayList;
import java.util.List;

/**
 * Gabriel Young
 * AP CS A
 * 4/24/2018
 * Assignment # - Balancing Chemical Equations
 * Description:
 */
public class Compound {

    private List<Element> elements = new ArrayList<>();

    public Compound(ArrayList<Element> elements) {
        this.elements = elements;
    }
    public void addElement(Element ele) {
        elements.add(ele);
    }

    @Override
    public String toString() {
        return this.elements.toString();
    }
}
