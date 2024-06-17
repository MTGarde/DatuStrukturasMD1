package org.martagarde.datastr.nodes;

public class MyNodeS<Ttype> {
    private Ttype element;
    private MyNodeS refrenceNext;
    //Ja vajag var uztaisit refrence uz ieprieksejo


    public Ttype getElement() {
        return element;
    }

    public void setElement(Ttype element) {
        this.element = element;
    }

    public MyNodeS getRefrenceNext() {
        return refrenceNext;
    }

    public void setRefrenceNext(MyNodeS refrenceNext) {
        this.refrenceNext = refrenceNext;
    }

    public MyNodeS(Ttype element){
        setElement(element);
    }

    public String toString() {
        return element + " -> ";
    }
}
