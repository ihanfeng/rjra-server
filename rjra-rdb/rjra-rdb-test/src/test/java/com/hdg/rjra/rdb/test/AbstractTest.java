package com.hdg.rjra.rdb.test;

import com.hdg.rjra.rdb.client.RdbDefaultClient;
import org.junit.After;
import org.junit.BeforeClass;

/**
 * Created by Rock on 2014/10/27.
 */
public abstract class AbstractTest {
    public static RdbDefaultClient rdbDefaultClient = null;

    @BeforeClass
    public static void setup() throws Exception {
//        defaultClient = new DefaultClient("172.30.10.122", 9091);

        rdbDefaultClient = new RdbDefaultClient("localhost", 9092);

        //rdbDefaultClient = new RdbDefaultClient("121.40.19.174", 9091);

//        defaultClient = new DefaultClient("172.30.50.10", 9091);

    }

    @After
    public void before() {
        System.out.println("测试输出");
    }
}
