package mondrian.spi;

import junit.framework.TestCase;
import mondrian.rolap.CellKey;
import mondrian.spi.impl.SegmentRedisCache;
import mondrian.util.ByteString;
import mondrian.rolap.BitKey;

import java.util.*;

import static org.mockito.Mockito.mock;

public class SegmentRedisTestCase  extends TestCase{
    public void testPutGetSegmentBody() {
        SegmentHeader segmentHeader = new SegmentHeader(
                "dummySchemaName",
                new ByteString(new byte[]{}),
                "dummyCubeName",
                "dummyMeasureName",
                Collections.<SegmentColumn>emptyList(),
                Collections.<String>emptyList(),
                "dummyFactTable",
                BitKey.Factory.makeBitKey(3),
                Collections.<SegmentColumn>emptyList());
        TestSegmentBody segmentBody = new TestSegmentBody();
        segmentBody.setValueArray("PUT-GET test.");
        SegmentRedisCache redisCache = new SegmentRedisCache();
        redisCache.put(segmentHeader, segmentBody);
        assertEquals(1,1);

        SegmentBody jedisSegmentBody = redisCache.get(segmentHeader);

        assertEquals(jedisSegmentBody.getValueArray(), segmentBody.getValueArray());
        assertTrue(redisCache.contains(segmentHeader));
    }

    public void testContainsSegmentBody() {
        SegmentHeader segmentHeader = new SegmentHeader(
                "newSchemaName",
                new ByteString(new byte[]{}),
                "newCubeName",
                "dummyMeasureName",
                Collections.<SegmentColumn>emptyList(),
                Collections.<String>emptyList(),
                "dummyFactTable",
                BitKey.Factory.makeBitKey(3),
                Collections.<SegmentColumn>emptyList());
        SegmentRedisCache redisCache = new SegmentRedisCache();
        assertFalse(redisCache.contains(segmentHeader));
    }

    public void testDeleteSegmentBody() {
        SegmentHeader segmentHeader = new SegmentHeader(
                "deleteSchemaName",
                new ByteString(new byte[]{}),
                "deleteCubeName",
                "dummyMeasureName",
                Collections.<SegmentColumn>emptyList(),
                Collections.<String>emptyList(),
                "dummyFactTable",
                BitKey.Factory.makeBitKey(3),
                Collections.<SegmentColumn>emptyList());
        TestSegmentBody segmentBody = new TestSegmentBody();
        SegmentRedisCache redisCache = new SegmentRedisCache();
        redisCache.put(segmentHeader, segmentBody);

        assertTrue(redisCache.contains(segmentHeader));
        redisCache.remove(segmentHeader);
        assertFalse(redisCache.contains(segmentHeader));
    }

    public void testGetSegmentHeader() {
        SegmentHeader segmentHeaderOne = new SegmentHeader(
                "firstSchemaName",
                new ByteString(new byte[]{}),
                "deleteCubeName",
                "dummyMeasureName",
                Collections.<SegmentColumn>emptyList(),
                Collections.<String>emptyList(),
                "dummyFactTable",
                BitKey.Factory.makeBitKey(3),
                Collections.<SegmentColumn>emptyList());
        SegmentHeader segmentHeaderTwo = new SegmentHeader(
                "secondSchemaName",
                new ByteString(new byte[]{}),
                "deleteCubeName",
                "dummyMeasureName",
                Collections.<SegmentColumn>emptyList(),
                Collections.<String>emptyList(),
                "dummyFactTable",
                BitKey.Factory.makeBitKey(3),
                Collections.<SegmentColumn>emptyList());
        TestSegmentBody segmentBody = new TestSegmentBody();
        SegmentRedisCache redisCache = new SegmentRedisCache();
        redisCache.put(segmentHeaderOne, segmentBody);
        redisCache.put(segmentHeaderTwo, segmentBody);

        assertEquals(redisCache.getSegmentHeaders().get(0).cubeName, segmentHeaderOne.cubeName);
        assertEquals(redisCache.getSegmentHeaders().get(1).cubeName, segmentHeaderTwo.cubeName);
    }

    public void testSupportsRichIndex() {
        SegmentRedisCache redisCache = new SegmentRedisCache();
        assertTrue(redisCache.supportsRichIndex());
    }
}

class TestSegmentBody implements SegmentBody {
    private static String valueArray;

    public TestSegmentBody() {
        this.valueArray = "valueArray";
    }

    public Map<CellKey, Object> getValueMap() {
        return new HashMap<CellKey, Object>();
    }

    public Object getValueArray() {
        return valueArray;
    }

    public void setValueArray(String valueArray) {
        this.valueArray = valueArray;
    }

    public BitSet getNullValueIndicators() {
        return mock(BitSet.class);
    }

    public SortedSet<Comparable>[] getAxisValueSets() {
        SortedSet<Comparable> axisValueSets = new TreeSet<Comparable>();
        axisValueSets.add("AxisValueSets");
        SortedSet[] axisValueSortedSet = {axisValueSets};
        return axisValueSortedSet;
    }

    public boolean[] getNullAxisFlags() {
        boolean[] axisFlags = {false};
        return axisFlags;
    }
}
