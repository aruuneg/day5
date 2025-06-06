/**
 * Байршлын мэдээллийг хадгалах ангилал.
 * Жишээлбэл: "Төв агуулах", "Салбар агуулах" гэх мэт.
 */
public class Location {
    /** Байршлын нэр */
    private String name;

    /**
     * Байршлын шинэ объект үүсгэх байгуулагч.
     *
     * @param name Байршлын нэр
     */
    public Location(String name) {
        this.name = name;
    }

    /**
     * Байршлын нэрийг буцаана.
     *
     * @return Байршлын нэр
     */
    public String getName() {
        return name;
    }

    /**
     * Байршлын нэрийг шинэчлэнэ.
     *
     * @param name Шинэ байршлын нэр
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Байршлын нэрийг тэмдэгт мөр хэлбэрээр буцаана.
     *
     * @return Байршлын нэр
     */
    @Override
    public String toString() {
        return name;
    }
}
