module com.pabloplayer.player {
    requires javafx.controls;
    requires java.desktop;
    requires javafx.fxml;
    requires javafx.media;
    requires java.sql;
    requires jaudiotagger;

    opens com.pabloplayer.player to javafx.fxml;
    exports com.pabloplayer.player;
}