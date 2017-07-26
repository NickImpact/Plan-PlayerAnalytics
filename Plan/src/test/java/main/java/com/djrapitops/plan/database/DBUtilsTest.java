/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test.java.main.java.com.djrapitops.plan.database;

import main.java.com.djrapitops.plan.database.Container;
import main.java.com.djrapitops.plan.database.DBUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

/**
 *
 * @author ristolah
 */
public class DBUtilsTest {

    public DBUtilsTest() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testSplitIntoBatches() {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 3000; i++) {
            list.add(i);
        }
        List<List<Integer>> result = DBUtils.splitIntoBatches(list);
        assertEquals(2, result.size());
        assertEquals(2048, result.get(0).size());
        assertEquals(952, result.get(1).size());
    }

    @Test
    public void testSplitIntoBatchesSingleBatch() {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 2048; i++) {
            list.add(i);
        }
        List<List<Integer>> result = DBUtils.splitIntoBatches(list);
        assertEquals(1, result.size());
        assertEquals(2048, result.get(0).size());
    }

    @Test
    public void testSplitIntoBatchesId() {
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 300; j++) {
                map.computeIfAbsent(i, k -> new ArrayList<>());
                map.get(i).add(j);
            }
        }
        List<List<Container<Integer>>> result = DBUtils.splitIntoBatchesId(map);
        assertEquals(2, result.size());
        assertEquals(2048, result.get(0).size());
        assertEquals(952, result.get(1).size());
    }

}