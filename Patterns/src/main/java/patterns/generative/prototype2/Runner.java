package patterns.generative.prototype2;

public class Runner {
    public static void main(String[] args) {
        Page page = new Page(1, 100, "sjdfvnds \r\nsjdnfvdjnjkf\r\ndjfvbnf\r\ndfgdsfbngfd");
        System.out.println(page);

        //first way to create copy of top page
        Page pageCopy = (Page) page.copy();
        System.out.println("--------------------------------------------------------\r\n" + pageCopy);

        PageFactory pageFactory = new PageFactory();
        Page pageClone = pageFactory.makeCopy(page);
        System.out.println("--------------------------------------------------------\r\n" + pageClone);
    }
}
