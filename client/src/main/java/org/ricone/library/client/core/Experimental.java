package org.ricone.library.client.core;

import java.lang.annotation.*;

/**
 *
 * This element has an experimental maturity. Use with caution.
 *
 * NOTE: The developers of this element are not responsible for any issues created.
 * Usage is not suggested for production environments.
 * Enjoy responsibly....
 */


@Documented //this annotation maybe helpful for your custom annotation
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER,
        ElementType.CONSTRUCTOR, ElementType.LOCAL_VARIABLE, ElementType.PACKAGE, 
        ElementType.ANNOTATION_TYPE, ElementType.TYPE_USE, ElementType.TYPE_PARAMETER
})
public @interface Experimental {}
