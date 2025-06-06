import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class WarehouseTest {

    private Warehouse warehouse;
    private Product product;
    private Location location;

    @BeforeEach
    public void setUp() {
        location = new Location("Төв агуулах");
        warehouse = new Warehouse(1, location, 100);
        product = new Product(1, "Гар утас", "Электрон", 999999.9, 10, "123456789");
    }

    @Test
    public void testAddProduct() {
        warehouse.addProduct(product);
        assertEquals(1, warehouse.getProducts().size());
        assertEquals(10, warehouse.getProducts().get(0).getQuantity());
        assertEquals(1, warehouse.getStockMoves().size());
        assertEquals("Орсон", warehouse.getStockMoves().get(0).getMoveType());
    }

    @Test
    public void testRemoveProduct() {
        warehouse.addProduct(product);
        warehouse.removeProduct(product, 5);
        assertEquals(5, warehouse.getProducts().get(0).getQuantity());
        assertEquals(2, warehouse.getStockMoves().size());
        assertEquals("Гарсан", warehouse.getStockMoves().get(1).getMoveType());
    }

    @Test
    public void testTransferProduct() {
        Warehouse secondWarehouse = new Warehouse(2, new Location("Салбар агуулах"), 50);
        warehouse.addProduct(product);
        warehouse.transferProduct(product, 4, secondWarehouse);

        assertEquals(6, warehouse.getProducts().get(0).getQuantity());
        assertEquals(4, secondWarehouse.getProducts().get(0).getQuantity());

        assertEquals(3, warehouse.getStockMoves().size());
        assertEquals(2, secondWarehouse.getStockMoves().size());

        assertEquals("Шилжүүлсэн", warehouse.getStockMoves().get(2).getMoveType());
    }

    // --- ЭНД СТЕП 2-ЫН ӨРГӨТГӨЛТ ---
    @Test
    public void testAddProductWithInvalidQuantity() {
        Product invalidProduct = new Product(2, "Буруу тоо", "Категори", 1000, -5, "000000");
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            warehouse.addProduct(invalidProduct);
        });
        assertEquals("Нэмэх бүтээгдэхүүний тоо 0-ээс их байх ёстой.", exception.getMessage());
    }

    @Test
    public void testRemoveProductWithExcessQuantity() {
        warehouse.addProduct(product);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            warehouse.removeProduct(product, 20);
        });
        assertEquals("Агуулахад хүрэлцэхгүй тоо хасах гэж байна.", exception.getMessage());
    }

    @Test
    public void testRemoveProductWithNegativeQuantity() {
        warehouse.addProduct(product);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            warehouse.removeProduct(product, -1);
        });
        assertEquals("Хасах тоо 0-ээс их байх ёстой.", exception.getMessage());
    }
}
