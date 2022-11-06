package dev.hypest.pis

import io.micronaut.runtime.EmbeddedApplication
import jakarta.inject.Inject

class ApplicationTest extends BaseTest {

    @Inject
    EmbeddedApplication<?> application

    void 'test it works'() {
        expect:
        application.running
    }
}
