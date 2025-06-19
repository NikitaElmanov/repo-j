package patterns.generative.prototype2;

public class Runner {
    public static void main(String[] args) {
        var page = new Page(1, 100, "sjdfvnds \r\nsjdnfvdjnjkf\r\ndjfvbnf\r\ndfgdsfbngfd");
        System.out.println(page);

        //first way to create copy of top page
        var pageCopy = (Page) page.copy();
        System.out.println("--------------------------------------------------------\r\n" + pageCopy);

        var pageFactory = new PageFactory();
        var pageClone = pageFactory.makeCopy(page);
        System.out.println("--------------------------------------------------------\r\n" + pageClone);
    }
}
