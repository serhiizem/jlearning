package jlearning.words.controller;

import jlearning.words.service.DictionaryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class CleanupComponent {

    private final DictionaryService dictionaryService;

    /**
     * Every minute deletes all words in database.
     * <p>
     * It is needed because currently project supports only one user,
     * thus usages of application may create confusing overlapping data
     */
    @Scheduled(cron = "0 * * * * *")
    public void deleteAllWords() {
        try {
            dictionaryService.clearDb();
            log.info("Database was cleared of stale entries");
        } catch (Exception e) {
            log.error("", e);
        }
    }
}
