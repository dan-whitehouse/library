import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.ricone.library.config.request2.ConfigPathBuilder;
import org.ricone.library.config.request2.ServicePath;
import org.ricone.library.config.response.model.Vendor;
import org.ricone.library.config.response.model.Vendors;
import org.ricone.library.exception.MissingArgumentException;
import org.springframework.http.HttpMethod;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Vendor Path Test")
class VendorPathTest {
    @Test
    void getVendorValidPath() {
        try {
            new ConfigPathBuilder(ServicePath.VENDOR, HttpMethod.GET).id("vendor_id").build();
        }
        catch (MissingArgumentException e) {
            fail(e.getMessage());
        }
    }

    @Test
    void getVendorsValidPath() {
        try {
            new ConfigPathBuilder(ServicePath.VENDORS, HttpMethod.GET).build();
        }
        catch (MissingArgumentException e) {
            fail(e.getMessage());
        }
    }

    @Test
    void postVendorValidPath() {
        //Expected to throw an UnsupportedOperationException as HttpMethod.POST is not allowed on ServicePath.VENDOR.
        assertThrows(UnsupportedOperationException.class, ()->  new ConfigPathBuilder(ServicePath.VENDOR, HttpMethod.POST).body(new Vendor()).build());
    }

    @Test
    void postVendorsValidPath() {
        try {
            new ConfigPathBuilder(ServicePath.VENDORS, HttpMethod.POST).body(new Vendors()).build();
        }
        catch (MissingArgumentException e) {
            fail(e.getMessage());
        }
    }

    @Test
    void putVendorValidPath() {
        try {
            new ConfigPathBuilder(ServicePath.VENDOR, HttpMethod.PUT).id("vendor_id").body(new Vendor()).build();
        }
        catch (MissingArgumentException e) {
            fail(e.getMessage());
        }
    }

    @Test
    void putVendorsValidPath() {
        try {
            new ConfigPathBuilder(ServicePath.VENDORS, HttpMethod.PUT).body(new Vendors()).build();
        }
        catch (MissingArgumentException e) {
            fail(e.getMessage());
        }
    }

    @Test
    void patchVendorValidPath() {
        try {
            new ConfigPathBuilder(ServicePath.VENDOR, HttpMethod.PATCH).id("vendor_id").body(new Vendor()).build();
        }
        catch (MissingArgumentException e) {
            fail(e.getMessage());
        }
    }

    @Test
    void patchVendorsValidPath() {
        try {
            new ConfigPathBuilder(ServicePath.VENDORS, HttpMethod.PATCH).body(new Vendors()).build();
        }
        catch (MissingArgumentException e) {
            fail(e.getMessage());
        }
    }

    @Test
    void headVendorValidPath() {
        try {
            new ConfigPathBuilder(ServicePath.VENDOR, HttpMethod.HEAD).id("vendor_id").build();
        }
        catch (MissingArgumentException e) {
            fail(e.getMessage());
        }
    }

    @Test
    void headVendorsValidPath() {
        //Expected to throw an UnsupportedOperationException as HttpMethod.HEAD is not allowed on ServicePath.VENDORS.
        assertThrows(UnsupportedOperationException.class, ()->  new ConfigPathBuilder(ServicePath.VENDORS, HttpMethod.HEAD).build());
    }

    @Test
    void deleteVendorValidPath() {
        try {
            new ConfigPathBuilder(ServicePath.VENDOR, HttpMethod.DELETE).id("vendor_id").build();
        }
        catch (MissingArgumentException e) {
            fail(e.getMessage());
        }
    }

    @Test
    void deleteVendorIllegalArgument() {
        //Expected to throw an IllegalArgumentException as the body method is not allowed when using HttpMethod.DELETE.
        assertThrows(IllegalArgumentException.class, ()-> new ConfigPathBuilder(ServicePath.VENDOR, HttpMethod.DELETE).body(new Vendors()).build());
    }

    @Test
    void deleteVendorsUnsupportedOperation() {
        //Expected to throw an UnsupportedOperationException as HttpMethod.DELETE is not allowed on ServicePath.VENDORS.
        assertThrows(UnsupportedOperationException.class, ()->  new ConfigPathBuilder(ServicePath.VENDORS, HttpMethod.DELETE).build());
    }

    @Test
    void optionsVendorValidPath() {
        //Expected to throw an UnsupportedOperationException as HttpMethod.OPTIONS is not allowed.
        assertThrows(UnsupportedOperationException.class, ()->  new ConfigPathBuilder(ServicePath.VENDOR, HttpMethod.OPTIONS).id("vendor_id").build());
    }

    @Test
    void optionsVendorsValidPath() {
        //Expected to throw an UnsupportedOperationException as HttpMethod.OPTIONS is not allowed.
        assertThrows(UnsupportedOperationException.class, ()->  new ConfigPathBuilder(ServicePath.VENDORS, HttpMethod.OPTIONS).build());
    }

    @Test
    void traceVendorValidPath() {
        //Expected to throw an UnsupportedOperationException as HttpMethod.TRACE is not allowed.
        assertThrows(UnsupportedOperationException.class, ()->  new ConfigPathBuilder(ServicePath.VENDOR, HttpMethod.TRACE).id("vendor_id").build());
    }

    @Test
    void traceVendorsValidPath() {
        //Expected to throw an UnsupportedOperationException as HttpMethod.TRACE is not allowed.
        assertThrows(UnsupportedOperationException.class, ()->  new ConfigPathBuilder(ServicePath.VENDORS, HttpMethod.TRACE).build());
    }
}
