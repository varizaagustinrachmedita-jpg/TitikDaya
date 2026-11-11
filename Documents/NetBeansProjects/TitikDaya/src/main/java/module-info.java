module com.mycompany.titikdaya {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.mycompany.titikdaya to javafx.fxml;
    exports com.mycompany.titikdaya;
}
