package abstractions;

import entities.shampoos.BasicShampoo;

public interface Label {

    long getId();

    void setId(long id);

    String getTitle();

    void setTitle(String title);

    String getSubtitle();

    void setSubtitle(String subtitle);

    BasicShampoo getShampoo();

    void setShampoo(BasicShampoo shampoo);
}
