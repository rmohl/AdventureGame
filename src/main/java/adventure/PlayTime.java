package adventure;

import java.time.Instant;
import java.time.Duration;

@FunctionalInterface
public interface PlayTime {
    Duration elapsedTime(Instant start, Instant end);
}
