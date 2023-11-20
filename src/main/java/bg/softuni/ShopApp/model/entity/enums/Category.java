package bg.softuni.ShopApp.model.entity.enums;

public enum Category {
    VEHICLE("Превозни средства"),
    ELECTRONIC("Електроника"),
    ESTATE("Имоти"),
    CLOTHING("Облекло"),
    OTHER("Други");

    private String displayName;

    Category(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
