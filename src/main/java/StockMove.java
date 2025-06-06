import java.time.LocalDateTime;

/**
 * Нөөцийн хөдөлгөөнийг төлөөлөх анги.
 * Энэ нь бүтээгдэхүүн нэмэгдэх, хасагдах эсвэл агуулах хооронд шилжих үйлдлүүдийг бүртгэнэ.
 */
public class StockMove {
    /** Давтагдахгүй ID үүсгэх дотоод тоолуур */
    private static int counter = 1;

    /** Хөдөлгөөний давтагдашгүй дугаар */
    private int id;

    /** Холбогдсон бүтээгдэхүүн */
    private Product product;

    /** Хөдөлгөөнд орсон тоо хэмжээ */
    private int quantity;

    /** Хөдөлгөөний төрөл (жишээ нь: "Орсон", "Гарсан", "Шилжүүлсэн") */
    private String moveType;

    /** Хөдөлгөөн үүссэн огноо, цаг */
    private LocalDateTime timestamp;

    /** Хаанаас нүүсэн байршил */
    private Location fromLocation;

    /** Хаашаа шилжсэн байршил */
    private Location toLocation;

    /**
     * Шинэ нөөцийн хөдөлгөөн үүсгэгч байгуулагч.
     *
     * @param product      Холбогдох бүтээгдэхүүн
     * @param quantity     Хөдөлгөөнд оролцож буй тоо хэмжээ
     * @param moveType     Хөдөлгөөний төрөл (жишээ: "Орсон", "Гарсан", "Шилжүүлсэн")
     * @param fromLocation Хаанаас хөдөлсөн байршил (null бол гадна эх үүсвэр)
     * @param toLocation   Хаашаа очсон байршил (null бол гадна руу гарсан)
     */
    public StockMove(Product product, int quantity, String moveType, Location fromLocation, Location toLocation) {
        this.id = counter++;
        this.product = product;
        this.quantity = quantity;
        this.moveType = moveType;
        this.timestamp = LocalDateTime.now();
        this.fromLocation = fromLocation;
        this.toLocation = toLocation;
    }

    /**
     * Холбогдсон бүтээгдэхүүнийг буцаана.
     *
     * @return бүтээгдэхүүн
     */
    public Product getProduct() {
        return product;
    }

    /**
     * Тоо ширхэгийг буцаана.
     *
     * @return тоо хэмжээ
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Хөдөлгөөний төрлийг буцаана.
     *
     * @return "Орсон", "Гарсан", "Шилжүүлсэн" гэх мэт
     */
    public String getMoveType() {
        return moveType;
    }

    /**
     * Хөдөлгөөн үүссэн хугацааг буцаана.
     *
     * @return огноо, цаг
     */
    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    /**
     * Хаанаас нүүсэн байршлыг буцаана.
     *
     * @return эхлэл байршил
     */
    public Location getFromLocation() {
        return fromLocation;
    }

    /**
     * Хаашаа нүүсэн байршлыг буцаана.
     *
     * @return очих байршил
     */
    public Location getToLocation() {
        return toLocation;
    }

    /**
     * Хөдөлгөөний дэлгэрэнгүй мэдээллийг текстээр буцаана.
     *
     * @return тэмдэгт мөр хэлбэртэй тайлбар
     */
    @Override
    public String toString() {
        String from = (fromLocation != null) ? fromLocation.toString() : "Гадна эх үүсвэр";
        String to = (toLocation != null) ? toLocation.toString() : "Гадна эх үүсвэр";
        return String.format("[%d] %s - %s, Тоо: %d, Огноо: %s, FROM: %s, TO: %s",
                id, moveType, product.getName(), quantity, timestamp, from, to);
    }
}
