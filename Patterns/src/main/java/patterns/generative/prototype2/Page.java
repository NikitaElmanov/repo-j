package patterns.generative.prototype2;

public class Page implements CopyInteface {

    private int number;
    private int width;
    private String text;

    public Page(int number, int width, String text) {
        this.number = number;
        this.width = width;
        this.text = text;
    }

    @Override
    public Object copy() {
        Page page = new Page(number, width, text);
        return page;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "Page{" +
                "number=" + number +
                ", width=" + width +
                ", text='" + text + '\'' +
                '}';
    }
}
