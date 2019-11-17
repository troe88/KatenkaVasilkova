package enums;

public enum NavBarItems {

    HOME("HOME"),
    CONTACT_FORM("CONTACT FORM"),
    SERVICE("SERVICE"),
    METALS_COLORS("METALS & COLORS");

    String name;

    NavBarItems(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
