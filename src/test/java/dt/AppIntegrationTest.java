package dt;

import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class AppIntegrationTest {

    @Test
    void shallBeFilteredOut() {
        fail("Top-level test");
    }

    @Nested
    class ApiTests {
        @Test
        void nestedShallBeFilteredOut() {
            fail( "@Nested ending in Tests");
        }
    }

    @Nested
    class Bar {
        @Test
        void doubleNestedShallBeFilteredOut() {
            fail( "Just @Nested");
        }
    }
}
