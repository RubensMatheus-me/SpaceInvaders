package ifpr.paranavai.game.models;

import javax.persistence.*;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.ArrayList;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class GraphicElement {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_graphic_element")
    private Integer idGraphicElement;

    @Column(name = "position_x")
    protected int positionX;
    @Column(name = "position_y")
    protected int positionY;
    @Column(name = "deslocation_x")
    protected int deslocationX;
    @Column(name = "deslocation_y")
    protected int deslocationY;
    @Transient
    protected Image image;
    @Column(name = "height")
    protected int height;
    @Column(name = "width")
    protected int width;
    @Column(name = "width_image")
    protected int widthImage;
    @Column(name = "height_image")
    protected int heightImage;
    @Column(name = "is_visible")
    protected boolean isVisible;

    public GraphicElement() {
        isVisible = true;
    }
    public abstract void load();

    public abstract void update();

    public Rectangle getBounds() {
        return new Rectangle(positionX, positionY, width, height);
    }

    public int getPositionX() {
        return positionX;
    }

    public void setPositionX(int positionX) {
        this.positionX = positionX;
    }

    public int getPositionY() {
        return positionY;
    }

    public void setPositionY(int positionY) {
        this.positionY = positionY;
    }

    public int getDeslocationX() {
        return deslocationX;
    }

    public void setDeslocationX(int deslocationX) {
        this.deslocationX = deslocationX;
    }

    public int getDeslocationY() {
        return deslocationY;
    }

    public void setDeslocationY(int deslocationY) {
        this.deslocationY = deslocationY;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidht() {
        return width;
    }

    public void setWidht(int widht) {
        this.width = widht;
    }

    public  boolean isVisible() {
        return isVisible;
    }

    public void setVisible(boolean visible) {
        isVisible = visible;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getWidthImage() {
        return widthImage;
    }

    public void setWidthImage(int widthImage) {
        this.widthImage = widthImage;
    }

    public int getHeightImage() {
        return heightImage;
    }

    public void setHeightImage(int heightImage) {
        this.heightImage = heightImage;
    }

    public Integer getIdGraphicElement() {
        return idGraphicElement;
    }

    public void setIdGraphicElement(Integer idGraphicElement) {
        this.idGraphicElement = idGraphicElement;
    }
}
