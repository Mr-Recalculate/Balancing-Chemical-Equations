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

    public Compound() {
    }

    public void addElement(Element ele) {
        elements.add(ele);
    }
    public void setElement(int idx, Element ele) {
        elements.set(idx, ele);
    }

    public Element getElement(int idx) {
        return elements.get(idx);
    }

    @Override
    public String toString() {
        return elements.toString();
    }
}
