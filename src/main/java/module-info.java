module com.example.rentify {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;
    requires java.sql;

    opens com.example.rentify to javafx.fxml;
    exports com.example.rentify;
    exports com.example.rentify.ViewController;
    opens com.example.rentify.ViewController to javafx.fxml;
}