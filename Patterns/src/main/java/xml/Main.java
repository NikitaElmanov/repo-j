package xml;

import generated.ExpenseT;
import generated.ItemListT;
import generated.ItemT;
import generated.ObjectFactory;
import generated.UserT;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.Marshaller;
import java.math.BigDecimal;

public class Main {

    public static void main(String[] args) throws Exception {
        ObjectFactory factory = new ObjectFactory();

        UserT user = factory.createUserT();
        user.setUserName("Sanaulla");
        ItemT item = factory.createItemT();
        item.setItemName("Seagate External HDD");
        item.setPurchasedOn("August 24, 2010");
        item.setAmount(new BigDecimal("6776.5"));

        ItemListT itemList = factory.createItemListT();
        itemList.getItem().add(item);

        ExpenseT expense = factory.createExpenseT();
        expense.setUser(user);
        expense.setItems(itemList);

        JAXBContext context = JAXBContext.newInstance(ExpenseT.class);
        JAXBElement<ExpenseT> element = factory.createExpenseReport(expense);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        marshaller.marshal(element, System.out);
    }

}
