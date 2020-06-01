package com.ffm.backend.config.firebase;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.FileInputStream;
import java.io.IOException;

@Slf4j
@Service
public class FirebaseInitializer {

    private static final String CONFIG_PATH_ENV = "FIREBASE_CONFIG_PATH";

    @PostConstruct
    public void initialize() {
        try (FileInputStream firebaseConfig = loadFirebaseConfig()) {
            FirebaseOptions firebaseOptions = buildFirebaseOptions(firebaseConfig);
            FirebaseApp.initializeApp(firebaseOptions);
        } catch (Exception e) {
            log.warn("Unable to load firebase config. Notifications will not work!", e);
        }
    }

    private FileInputStream loadFirebaseConfig() throws IOException {
        String path = System.getenv(CONFIG_PATH_ENV);
        return new FileInputStream(path);
    }

    private FirebaseOptions buildFirebaseOptions(FileInputStream firebaseConfig) throws IOException {
        return new FirebaseOptions.Builder()
            .setCredentials(GoogleCredentials.fromStream(firebaseConfig))
            .build();
    }
}
