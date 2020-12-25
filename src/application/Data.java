package application;

import java.io.Serializable;

public class Data implements Serializable {
    private String element;

    public Data(String element) {
        this.element = element;
    }

    public String getElement() {
        return this.element;
    }
}