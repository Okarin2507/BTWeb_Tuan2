package nhuttan.sg.model;

import java.io.Serializable;

public class Category implements Serializable {
    private static final long serialVersionUID = 1L;

    private int cateId;
    private String cateName;
    private String icons;
    private int userId; 

    public Category() {}

    public int getCateId() { return cateId; }
    public void setCateId(int cateId) { this.cateId = cateId; }

    public String getCateName() { return cateName; }
    public void setCateName(String cateName) { this.cateName = cateName; }

    public String getIcons() { return icons; }
    public void setIcons(String icons) { this.icons = icons; }

    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }
}