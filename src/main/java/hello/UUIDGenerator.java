package hello;

import java.util.UUID;

@FunctionalInterface
public interface UUIDGenerator {

    UUID generate();
}