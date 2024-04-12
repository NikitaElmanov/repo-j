package patterns.generative.prototype2;

public class PageFactory {
    public Page makeCopy(Page page) {
        return (Page) page.copy();
    }
}
