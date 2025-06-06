import java.util.ArrayList;
import java.util.List;

/**
 * Агуулахыг төлөөлөх анги.
 * Бүтээгдэхүүн нэмэх, хасах, шилжүүлэх үйлдлүүдийг хариуцна.
 */
public class Warehouse {
    /** Агуулахын ID */
    private int id;

    /** Агуулахын байрлал */
    private Location location;

    /** Агуулахын багтаамж */
    private int capacity;

    /** Агуулахад байгаа бүтээгдэхүүний жагсаалт */
    private List<Product> products;

    /** Агуулах дээрх нөөцийн хөдөлгөөний түүх */
    private List<StockMove> stockMoves;

    /**
     * Шинэ агуулах үүсгэгч.
     *
     * @param id       Агуулахын дугаар
     * @param location Байршил
     * @param capacity Багтаамж
     */
    public Warehouse(int id, Location location, int capacity) {
        if (location == null) {
            throw new IllegalArgumentException("Агуулахын байршил хоосон байж болохгүй.");
        }
        if (capacity < 0) {
            throw new IllegalArgumentException("Агуулахын багтаамж сөрөг байж болохгүй.");
        }
        this.id = id;
        this.location = location;
        this.capacity = capacity;
        this.products = new ArrayList<>();
        this.stockMoves = new ArrayList<>();
    }

    /**
     * Агуулахад бүтээгдэхүүн нэмнэ.
     * Хэрэв бүтээгдэхүүн аль хэдийн байвал тоог нэмнэ.
     * Мөн нөөцийн хөдөлгөөнд "Орсон" гэж бүртгэнэ.
     *
     * @param product Нэмэгдэх бүтээгдэхүүн
     * @throws IllegalArgumentException хэрэв product нь хоосон эсвэл тоо сөрөг байвал
     */
    public void addProduct(Product product) {
        if (product == null) {
            throw new IllegalArgumentException("Бүтээгдэхүүн хоосон байж болохгүй.");
        }
        if (product.getQuantity() <= 0) {
            throw new IllegalArgumentException("Нэмэх бүтээгдэхүүний тоо 0-ээс их байх ёстой.");
        }

        for (Product p : products) {
            if (p.getId() == product.getId()) {
                p.setQuantity(p.getQuantity() + product.getQuantity());
                stockMoves.add(new StockMove(product, product.getQuantity(), "Орсон", null, location));
                return;
            }
        }
        products.add(product);
        stockMoves.add(new StockMove(product, product.getQuantity(), "Орсон", null, location));
    }

    /**
     * Агуулахаас бүтээгдэхүүн тоог хасна.
     * Хассан тоог "Гарсан" хөдөлгөөнөөр бүртгэнэ.
     *
     * @param product Хасах бүтээгдэхүүн
     * @param quantity Хасах тоо хэмжээ
     * @throws IllegalArgumentException хэрэв product хоосон эсвэл quantity сөрөг эсвэл агуулахад хүрэлцэхгүй тоо хасах гэж байвал
     */
    public void removeProduct(Product product, int quantity) {
        if (product == null) {
            throw new IllegalArgumentException("Бүтээгдэхүүн хоосон байж болохгүй.");
        }
        if (quantity <= 0) {
            throw new IllegalArgumentException("Хасах тоо 0-ээс их байх ёстой.");
        }

        for (Product p : products) {
            if (p.getId() == product.getId()) {
                if (p.getQuantity() < quantity) {
                    throw new IllegalArgumentException("Агуулахад хүрэлцэхгүй тоо хасах гэж байна.");
                }
                p.setQuantity(p.getQuantity() - quantity);
                stockMoves.add(new StockMove(product, quantity, "Гарсан", location, null));
                return;
            }
        }

        throw new IllegalArgumentException("Бүтээгдэхүүн агуулахад байхгүй байна.");
    }

    /**
     * Агуулахад байгаа бүтээгдэхүүний жагсаалтыг буцаана.
     *
     * @return бүтээгдэхүүний жагсаалт
     */
    public List<Product> getProducts() {
        return products;
    }

    /**
     * Агуулах дээр бүртгэлтэй нөөцийн хөдөлгөөнүүдийг буцаана.
     *
     * @return нөөцийн хөдөлгөөний жагсаалт
     */
    public List<StockMove> getStockMoves() {
        return stockMoves;
    }

    /**
     * Агуулахын байршлыг буцаана.
     *
     * @return байршил
     */
    public Location getLocation() {
        return location;
    }

    /**
     * Агуулахын дугаарыг буцаана.
     *
     * @return агуулахын ID
     */
    public int getId() {
        return id;
    }

    /**
     * Агуулахын багтаамжийг буцаана.
     *
     * @return багтаамж
     */
    public int getCapacity() {
        return capacity;
    }

    /**
     * Бүтээгдэхүүнийг нэг агуулахнаас нөгөө агуулах руу шилжүүлнэ.
     * Уг шилжүүлэг нь бүтээгдэхүүний тоог багасгаж, нэмэх үйлдлийг хийнэ.
     * Мөн нөөцийн хөдөлгөөнд "Шилжүүлсэн" гэж бүртгэнэ.
     *
     * @param product     Шилжүүлэх бүтээгдэхүүн
     * @param quantity    Шилжүүлэх тоо хэмжээ
     * @param toWarehouse Хүлээн авагч агуулах
     * @throws IllegalArgumentException хэрэв шилжүүлэх параметрүүд буруу бол
     */
    public void transferProduct(Product product, int quantity, Warehouse toWarehouse) {
        if (product == null) {
            throw new IllegalArgumentException("Шилжүүлэх бүтээгдэхүүн хоосон байж болохгүй.");
        }
        if (toWarehouse == null) {
            throw new IllegalArgumentException("Хүлээн авагч агуулах тодорхой байх ёстой.");
        }
        if (quantity <= 0) {
            throw new IllegalArgumentException("Шилжүүлэх тоо 0-ээс их байх ёстой.");
        }

        removeProduct(product, quantity);
        Product transferProduct = new Product(product.getId(), product.getName(), product.getCategory(),
                product.getPrice(), quantity, product.getBarcode());
        toWarehouse.addProduct(transferProduct);
        StockMove move = new StockMove(product, quantity, "Шилжүүлсэн", this.location, toWarehouse.getLocation());
        this.stockMoves.add(move);
        toWarehouse.stockMoves.add(move);
    }
}
