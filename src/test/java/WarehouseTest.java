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
}
