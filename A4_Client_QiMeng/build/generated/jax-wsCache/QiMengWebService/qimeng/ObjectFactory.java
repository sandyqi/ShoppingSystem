
package qimeng;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the qimeng package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _ReadTable_QNAME = new QName("http://qimeng/", "readTable");
    private final static QName _ExcuteSQL_QNAME = new QName("http://qimeng/", "excuteSQL");
    private final static QName _PlaceOrder_QNAME = new QName("http://qimeng/", "placeOrder");
    private final static QName _Hello_QNAME = new QName("http://qimeng/", "hello");
    private final static QName _ReadTableResponse_QNAME = new QName("http://qimeng/", "readTableResponse");
    private final static QName _ExcuteSQLResponse_QNAME = new QName("http://qimeng/", "excuteSQLResponse");
    private final static QName _HelloResponse_QNAME = new QName("http://qimeng/", "helloResponse");
    private final static QName _ListPurchases_QNAME = new QName("http://qimeng/", "listPurchases");
    private final static QName _ListPurchasesResponse_QNAME = new QName("http://qimeng/", "listPurchasesResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: qimeng
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ReadTableResponse }
     * 
     */
    public ReadTableResponse createReadTableResponse() {
        return new ReadTableResponse();
    }

    /**
     * Create an instance of {@link Hello }
     * 
     */
    public Hello createHello() {
        return new Hello();
    }

    /**
     * Create an instance of {@link ExcuteSQL }
     * 
     */
    public ExcuteSQL createExcuteSQL() {
        return new ExcuteSQL();
    }

    /**
     * Create an instance of {@link PlaceOrder }
     * 
     */
    public PlaceOrder createPlaceOrder() {
        return new PlaceOrder();
    }

    /**
     * Create an instance of {@link ListPurchasesResponse }
     * 
     */
    public ListPurchasesResponse createListPurchasesResponse() {
        return new ListPurchasesResponse();
    }

    /**
     * Create an instance of {@link ListPurchases }
     * 
     */
    public ListPurchases createListPurchases() {
        return new ListPurchases();
    }

    /**
     * Create an instance of {@link HelloResponse }
     * 
     */
    public HelloResponse createHelloResponse() {
        return new HelloResponse();
    }

    /**
     * Create an instance of {@link ExcuteSQLResponse }
     * 
     */
    public ExcuteSQLResponse createExcuteSQLResponse() {
        return new ExcuteSQLResponse();
    }

    /**
     * Create an instance of {@link ReadTable }
     * 
     */
    public ReadTable createReadTable() {
        return new ReadTable();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ReadTable }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://qimeng/", name = "readTable")
    public JAXBElement<ReadTable> createReadTable(ReadTable value) {
        return new JAXBElement<ReadTable>(_ReadTable_QNAME, ReadTable.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ExcuteSQL }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://qimeng/", name = "excuteSQL")
    public JAXBElement<ExcuteSQL> createExcuteSQL(ExcuteSQL value) {
        return new JAXBElement<ExcuteSQL>(_ExcuteSQL_QNAME, ExcuteSQL.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PlaceOrder }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://qimeng/", name = "placeOrder")
    public JAXBElement<PlaceOrder> createPlaceOrder(PlaceOrder value) {
        return new JAXBElement<PlaceOrder>(_PlaceOrder_QNAME, PlaceOrder.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Hello }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://qimeng/", name = "hello")
    public JAXBElement<Hello> createHello(Hello value) {
        return new JAXBElement<Hello>(_Hello_QNAME, Hello.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ReadTableResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://qimeng/", name = "readTableResponse")
    public JAXBElement<ReadTableResponse> createReadTableResponse(ReadTableResponse value) {
        return new JAXBElement<ReadTableResponse>(_ReadTableResponse_QNAME, ReadTableResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ExcuteSQLResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://qimeng/", name = "excuteSQLResponse")
    public JAXBElement<ExcuteSQLResponse> createExcuteSQLResponse(ExcuteSQLResponse value) {
        return new JAXBElement<ExcuteSQLResponse>(_ExcuteSQLResponse_QNAME, ExcuteSQLResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link HelloResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://qimeng/", name = "helloResponse")
    public JAXBElement<HelloResponse> createHelloResponse(HelloResponse value) {
        return new JAXBElement<HelloResponse>(_HelloResponse_QNAME, HelloResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ListPurchases }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://qimeng/", name = "listPurchases")
    public JAXBElement<ListPurchases> createListPurchases(ListPurchases value) {
        return new JAXBElement<ListPurchases>(_ListPurchases_QNAME, ListPurchases.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ListPurchasesResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://qimeng/", name = "listPurchasesResponse")
    public JAXBElement<ListPurchasesResponse> createListPurchasesResponse(ListPurchasesResponse value) {
        return new JAXBElement<ListPurchasesResponse>(_ListPurchasesResponse_QNAME, ListPurchasesResponse.class, null, value);
    }

}
