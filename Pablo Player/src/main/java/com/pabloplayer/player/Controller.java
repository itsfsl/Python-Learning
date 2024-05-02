package com.pabloplayer.player;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.net.URL;
import java.util.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.tag.FieldKey;
import org.jaudiotagger.tag.Tag;
import org.jaudiotagger.tag.images.Artwork;

public class Controller implements Initializable {

    @FXML
    private Pane pane;
    @FXML
    private ProgressBar progress_bar;
    @FXML
    private ImageView album_art_image_blurred, album_art_image, play_pause_button, prev_button, next_button, shuffle_button, repeat_button, app_logo;
    @FXML
    private Circle minimize_button, close_button;
    @FXML
    private Label title_label, artist_label, time_elapsed_label, total_time_label, app_name;
    @FXML
    public void exit_window() {
        Platform.exit();
        System.exit(0);
    }
    @FXML
    public void minimize_window() {

        Stage stage = (Stage) minimize_button.getScene().getWindow();
        stage.setIconified(true);
    }

    private Media media;
    private MediaPlayer mediaPlayer;

    private File directory;
    private File[] files;
    private int song_number;

    private ArrayList<File> songs;

    private boolean song_running;
    private boolean shuffle_songs;
    private boolean repeat_songs;
    private ArrayList<File> original_songs_order;

    private Timer timer;
    private TimerTask timer_task;

    private List getArtworkList;
    private Artwork getFirstArtwork;

    private String pause_button_white_dir = "/assets/icons/pause_button_white.png";
    private Image pause_button_white = new Image(getClass().getResourceAsStream(pause_button_white_dir));

    private String play_button_white_dir = "/assets/icons/play_button_white.png";
    private Image play_button_white = new Image(getClass().getResourceAsStream(play_button_white_dir));

    private String shuffle_button_selected_dir = "/assets/icons/shuffle_button_selected.png";
    private Image shuffle_button_selected = new Image(getClass().getResourceAsStream(shuffle_button_selected_dir));

    private String shuffle_button_white_dir = "/assets/icons/shuffle_button_white.png";
    private Image shuffle_button_white = new Image(getClass().getResourceAsStream(shuffle_button_white_dir));

    private String repeat_button_white_dir = "/assets/icons/repeat_button_white.png";
    private Image repeat_button_white = new Image(getClass().getResourceAsStream(repeat_button_white_dir));

    private String repeat_button_selected_dir = "/assets/icons/repeat_button_selected.png";
    private Image repeat_button_selected = new Image(getClass().getResourceAsStream(repeat_button_selected_dir));

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        song_number = 0;
        progress_bar.setProgress(0);
        repeat_songs = false;
        shuffle_songs = false;

        original_songs_order = new ArrayList<File>();

        directory = new File("F:\\Final Songs");
        files = directory.listFiles();
        songs = new ArrayList<File>();

        if (files != null) {
            for (File file : files) {
                songs.add(file);
                original_songs_order.add(file);
            }
        }

        media = new Media(songs.get(song_number).toURI().toString());
        mediaPlayer = new MediaPlayer(media);

        try {
            AudioFile audio_file = AudioFileIO.read(songs.get(song_number));
            Tag tag = audio_file.getTag();
            String artist = tag.getFirst(FieldKey.ARTIST);
            artist_label.setText(artist);
            String title = tag.getFirst(FieldKey.TITLE);
            title_label.setText(title);
            Artwork artwork = tag.getFirstArtwork();

            if (artwork != null) {
                byte[] imageData = artwork.getBinaryData();

                if (imageData != null) {
                    ByteArrayInputStream stream = new ByteArrayInputStream(imageData);
                    Image image = new Image(stream);
                    album_art_image.setImage(image);
                    album_art_image_blurred.setImage(image);
                }
            } else {
                System.out.println("No Artwork :(");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        //title_label.setText(songs.get(song_number).getName().substring(0, songs.get(song_number).getName().length() -4));

        mediaPlayer.setOnReady(() -> {
            double end_time_seconds = media.getDuration().toSeconds();
            long minutes = (long) (end_time_seconds / 60);
            long seconds = (long) (end_time_seconds % 60);
            String formattedTime = String.format("%02d:%02d", minutes, seconds);
            total_time_label.setText(formattedTime);
        });
    }

    public void play_pause_song() {

        if (!song_running) {
                begin_timer();
                mediaPlayer.play();
                play_pause_button.setImage(pause_button_white);
        } else {
            cancel_timer();
            mediaPlayer.pause();
            play_pause_button.setImage(play_button_white);
        }
    }

    public void prev_song() {

        if (song_number > 0) {
            song_number--;
            mediaPlayer.stop();

            if (song_running) {
                cancel_timer();
            }

            media = new Media(songs.get(song_number).toURI().toString());
            mediaPlayer = new MediaPlayer(media);

            try {
                AudioFile audio_file = AudioFileIO.read(songs.get(song_number));
                Tag tag = audio_file.getTag();
                String artist = tag.getFirst(FieldKey.ARTIST);
                artist_label.setText(artist);
                String title = tag.getFirst(FieldKey.TITLE);
                title_label.setText(title);
                Artwork artwork = tag.getFirstArtwork();

                if (artwork != null) {
                    byte[] imageData = artwork.getBinaryData();

                    if (imageData != null) {
                        ByteArrayInputStream stream = new ByteArrayInputStream(imageData);
                        Image image = new Image(stream);
                        album_art_image.setImage(image);
                        album_art_image_blurred.setImage(image);
                    }
                } else {
                    System.out.println("No Artwork :(");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            play_pause_song();
        } else {
            song_number = songs.size() - 1;

            mediaPlayer.stop();

            if (song_running) {
                cancel_timer();
            }

            media = new Media(songs.get(song_number).toURI().toString());
            mediaPlayer = new MediaPlayer(media);

            try {
                AudioFile audio_file = AudioFileIO.read(songs.get(song_number));
                Tag tag = audio_file.getTag();
                String artist = tag.getFirst(FieldKey.ARTIST);
                artist_label.setText(artist);
                String title = tag.getFirst(FieldKey.TITLE);
                title_label.setText(title);
                Artwork artwork = tag.getFirstArtwork();

                if (artwork != null) {
                    byte[] imageData = artwork.getBinaryData();

                    if (imageData != null) {
                        ByteArrayInputStream stream = new ByteArrayInputStream(imageData);
                        Image image = new Image(stream);
                        album_art_image.setImage(image);
                        album_art_image_blurred.setImage(image);
                    }
                } else {
                    System.out.println("No Artwork :(");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            play_pause_song();
        }
    }

    public void next_song() {

        if (song_number < songs.size() - 1) {
            song_number++;
            mediaPlayer.stop();

            if (song_running) {
                cancel_timer();
            }

            media = new Media(songs.get(song_number).toURI().toString());
            mediaPlayer = new MediaPlayer(media);

            try {
                AudioFile audio_file = AudioFileIO.read(songs.get(song_number));
                Tag tag = audio_file.getTag();
                String artist = tag.getFirst(FieldKey.ARTIST);
                artist_label.setText(artist);
                String title = tag.getFirst(FieldKey.TITLE);
                title_label.setText(title);
                Artwork artwork = tag.getFirstArtwork();

                if (artwork != null) {
                    byte[] imageData = artwork.getBinaryData();

                    if (imageData != null) {
                        ByteArrayInputStream stream = new ByteArrayInputStream(imageData);
                        Image image = new Image(stream);
                        album_art_image.setImage(image);
                        album_art_image_blurred.setImage(image);
                    }
                } else {
                    System.out.println("No Artwork :(");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            play_pause_song();
        } else {

            if (repeat_songs) {

                song_number = 0;

                mediaPlayer.stop();

                if (song_running) {
                    cancel_timer();
                }

                media = new Media(songs.get(song_number).toURI().toString());
                mediaPlayer = new MediaPlayer(media);

                try {
                    AudioFile audio_file = AudioFileIO.read(songs.get(song_number));
                    Tag tag = audio_file.getTag();
                    String artist = tag.getFirst(FieldKey.ARTIST);
                    artist_label.setText(artist);
                    String title = tag.getFirst(FieldKey.TITLE);
                    title_label.setText(title);
                    Artwork artwork = tag.getFirstArtwork();

                    if (artwork != null) {
                        byte[] imageData = artwork.getBinaryData();

                        if (imageData != null) {
                            ByteArrayInputStream stream = new ByteArrayInputStream(imageData);
                            Image image = new Image(stream);
                            album_art_image.setImage(image);
                            album_art_image_blurred.setImage(image);
                        }
                    } else {
                        System.out.println("No Artwork :(");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

                play_pause_song();
            } else {
                progress_bar.setProgress(0);
                play_pause_button.setImage(play_button_white);
                time_elapsed_label.setText("00:00");
            }
        }
    }

    public void shuffle_songs() {

        if (!shuffle_songs) {
            shuffle_songs = true;
            Collections.shuffle(songs);

            shuffle_button.setImage(shuffle_button_selected);
        } else {
            shuffle_songs = false;
            songs.clear();
            songs.addAll(original_songs_order);

            shuffle_button.setImage(shuffle_button_white);
        }
    }

    public void repeat_songs() {

        if (repeat_songs) {
            repeat_songs = false;

            repeat_button.setImage(repeat_button_white);
        } else {
            repeat_songs = true;

            repeat_button.setImage(repeat_button_selected);
        }
    }

    public void begin_timer() {

        mediaPlayer.setOnReady(() -> {
            double end_time_seconds = media.getDuration().toSeconds();
            long minutes = (long) (end_time_seconds / 60);
            long seconds = (long) (end_time_seconds % 60);
            String formattedTime = String.format("%02d:%02d", minutes, seconds);
            total_time_label.setText(formattedTime);
        });

        timer = new Timer();
        timer_task = new TimerTask() {
            @Override
            public void run() {

                song_running = true;
                Platform.runLater(() -> {

                    double current_time_seconds = mediaPlayer.getCurrentTime().toSeconds();
                    double end_time_seconds = media.getDuration().toSeconds();
                    long minutes = (long) (current_time_seconds / 60);
                    long seconds = (long) (current_time_seconds % 60);
                    String formattedTime = String.format("%02d:%02d", minutes, seconds);
                    progress_bar.setProgress(current_time_seconds / end_time_seconds);
                    time_elapsed_label.setText(formattedTime);

                    if (current_time_seconds / end_time_seconds == 1) {
                        cancel_timer();
                        next_song();
                    }
                });
            }
        };

        timer.scheduleAtFixedRate(timer_task, 0, 1);
    }

    public void cancel_timer() {

        song_running = false;
        timer.cancel();
    }
}