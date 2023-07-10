package com.automation.stepdef;

import com.automation.utils.ConfigReader;
import org.junit.Before;

public class Hooks {
    @Before
    public void setUp(){
        ConfigReader.initProperties();
    }
}
