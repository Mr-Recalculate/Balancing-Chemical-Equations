/**
 * Gabriel Young
 * AP CS A
 * 4/24/2018
 * Assignment # - Balancing Chemical Equations
 * Description:
 */
public class Compound {
    private String name;
    private Element element1;
    private Element element2;
    private Element element3;
    private Element element4;
    private Element element5;
    public Compound(String name, Element ele1, Element ele2) {
        this.name = name;
        this.element1 = ele1;
        this.element2 = ele2;
    }

    public Compound(String name, Element ele1, Element ele2, Element ele3) {
        this.name = name;
        this.element1 = ele1;
        this.element2 = ele2;
        this.element3 = ele3;
    }
    public Compound(String name, Element ele1, Element ele2, Element ele3, Element ele4) {
        this.name = name;
        this.element1 = ele1;
        this.element2 = ele2;
        this.element3 = ele3;
        this.element4 = ele4;
    }
    public Compound(String name, Element ele1, Element ele2, Element ele3, Element ele4, Element ele5) {
        this.name = name;
        this.element1 = ele1;
        this.element2 = ele2;
        this.element3 = ele3;
        this.element4 = ele4;
        this.element5 = ele5;
    }

    public Element getElement1() {
        return this.element1;
    }
    public Element setElement1(Element ele) {
        return ele;
    }
    public Element getElement2() {
        return this.element2;
    }
    public Element setElement2(Element ele) {
        return ele;
    }
    public Element getElement3() {
        return this.element3;
    }
    public Element setElement3(Element ele) {
        return ele;
    }
    public Element getElement4() {
        return this.element4;
    }
    public Element setElement4(Element ele) {
        return ele;
    }
    public Element getElement5() {
        return this.element5;
    }
    public Element setElement5(Element ele) {
        return ele;
    }
}
