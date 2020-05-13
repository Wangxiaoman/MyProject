package com.top;

public class PoiNode {

    // poi index
    public PoiNode[] next;
    public PoiNode[] before;
    
    // 各类条件，比如：
    // 第一层为4-1号的空房
    // 第二层为4-2号的空房
    // 第三层为有厕所
    // 第四层为有窗户
    public PoiNode[] conditionsNext;

    private int poiid;
    private int level;

    public PoiNode(int poiid, int level)
    {
        this.poiid = poiid;
        this.level = level;
        // 0 is the base level
        this.next = new PoiNode[level+1];
        this.before = null; 
    }

    public Integer getPoiid() {
        return this.poiid;
    }
    
    public Integer getLevel() {
        return this.level;
    }

}
