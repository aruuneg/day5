/**
 * Бараа бүтээгдэхүүний мэдээллийг хадгалах ангилал.
 * Жишээлбэл: нэр, ангилал, үнэ, тоо ширхэг, штрих код гэх мэт.
 */
public class Product {
    /** Барааны ID */
    private int id;
    
    /** Барааны нэр */
    private String name;
    
    /** Барааны ангилал (жишээ нь: "Электрон") */
    private String category;
    
    /** Барааны үнэ (төгрөгөөр) */
    private double price;
    
    /** Барааны тоо ширхэг */
    private int quantity;
    
    /** Барааны штрих код */
    private String barcode;

    /**
     * Бүтээгдэхүүний объект үүсгэх байгуулагч.
     *
     * @param id        Барааны ID
     * @param name      Барааны нэр
     * @param category  Барааны ангилал
     * @param price     Үнэ
     * @param quantity  Тоо ширхэг
     * @param barcode   Штрих код
     */
    public Product(int id, String name, String category, double price, int quantity, String barcode) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.price = price;
        this.quantity = quantity;
        this.barcode = barcode;
    }

    /**
     * Барааны ID-г буцаана.
     *
     * @return ID
     */
    public int getId() {
        return id;
    }

    /**
     * Барааны нэрийг буцаана.
     *
     * @return нэр
     */
    public String getName() {
        return name;
    }

    /**
     * Барааны ангиллыг буцаана.
     *
     * @return ангилал
     */
    public String getCategory() {
        return category;
    }

    /**
     * Барааны үнийг буцаана.
     *
     * @return үнэ
     */
    public double getPrice() {
        return price;
    }

    /**
     * Барааны тоо ширхэгийг буцаана.
     *
     * @return тоо ширхэг
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Барааны штрих кодыг буцаана.
     *
     * @return штрих код
     */
    public String getBarcode() {
        return barcode;
    }

    /**
     * Барааны тоо ширхэгийг шинэчлэнэ.
     *
     * @param quantity шинэ тоо ширхэг
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * Барааны мэдээллийг форматласан хэлбэрээр буцаана.
     *
     * @return тэмдэгт мөр хэлбэртэй мэдээлэл
     */
    @Override
    public String toString() {
        return String.format("[%d] %s (%s) - ₮%.1f, Тоо: %d", id, name, category, price, quantity);
    }
}
