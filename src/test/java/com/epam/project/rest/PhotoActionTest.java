package com.epam.project.rest;

import org.junit.Test;

/**
 * Created by StepLuch on 15.09.15.
 */
public class PhotoActionTest {

    @Test
    public void testGetImage() throws Exception {
        new PhotoAction().getImage("55f7c580acaf011d14068597");
    }
}