package mondrian.spi.impl;
import mondrian.spi.SegmentBody;
import mondrian.spi.SegmentCache;
import mondrian.spi.SegmentHeader;
import org.redisson.Redisson;
import org.redisson.api.RBucket;
import org.redisson.api.RKeys;
import org.redisson.api.RedissonClient;

import java.util.ArrayList;
import java.util.List;

class Segment {
    private static SegmentHeader head;
    private static SegmentBody body;

    public void setSegment(SegmentHeader head, SegmentBody body) {
        this.head = head;
        this.body = body;
    }

    public SegmentHeader getHeader() {
        return head;
    }

    public SegmentBody getBody() {
        return body;
    }
}

public class SegmentRedisCache implements SegmentCache {
    static RedissonClient redisson;
    private List<SegmentCache.SegmentCacheListener> listenersToSync = new ArrayList<SegmentCache.SegmentCacheListener>();

    public SegmentRedisCache() {
        this.redisson = Redisson.create();
    }

    public SegmentBody get(SegmentHeader head) {
        String key = head.getUniqueID().toString();
        RBucket<Segment> bucket = redisson.getBucket(key);
        return bucket.get().getBody();
    }

    public boolean put(SegmentHeader head, SegmentBody body) {
        Segment segment = new Segment();
        segment.setSegment(head, body);
        String key = head.getUniqueID().toString();
        RBucket<Segment> bucket = redisson.getBucket(key);
        bucket.set(segment);
        return true;
    }

    public boolean contains(SegmentHeader head) {
        String key = head.getUniqueID().toString();
        RBucket<Segment> bucket = redisson.getBucket(key);
        return bucket.get() != null;
    }

    public boolean remove(SegmentHeader head) {
        String key = head.getUniqueID().toString();
        RBucket<Segment> bucket = redisson.getBucket(key);
        return bucket.delete();
    }

    public List<SegmentHeader> getSegmentHeaders() {
        RKeys keys = redisson.getKeys();
        Iterable<String> matches = keys.getKeys();
        List<SegmentHeader> allSegmentHeaders = new ArrayList<SegmentHeader>();

        for (String match : matches) {
            RBucket<Segment> bucket = redisson.getBucket(match);
            allSegmentHeaders.add(bucket.get().getHeader());
        }

        return allSegmentHeaders;
    }

    public void tearDown() {
        //pass
    }

    public void addListener(SegmentCache.SegmentCacheListener listener) {
        listenersToSync.add(listener);
    }

    public void removeListener(SegmentCache.SegmentCacheListener listener) {
        listenersToSync.remove(listener);
    }

    public boolean supportsRichIndex() {
        return true;
    }

}
