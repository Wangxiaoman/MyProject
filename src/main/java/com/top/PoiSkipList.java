package com.top;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.google.common.base.Objects;

/**
 * | 4 | --------------> |___| --------------------------------------> | | | 3 | -------------->
 * |___| --------------------------------------> | | | 2 | --------------> |___| -------------->
 * |____|----------------> | | | 1 | --------------> |___| --------------> |____|----------------> |
 * | | 0 | ---> | 3 | ---> | 5 | ---> | 7 | ---> | 12 | ---> | 13 | ---> | |
 *
 *
 */
public class PoiSkipList {

    private final int MAX_LEVEL = 10;

    // no tail, as tail will be always null
    private PoiNode head;

    private int size;

    // control the current max height, avoid a sudden boost in level
    private int currentMaxLevel;
    private Random seed = new Random();

    public PoiSkipList() {
        this.head = new PoiNode(0, MAX_LEVEL);
        this.currentMaxLevel = 1;
        this.size = 0;
    }

    public void insert(Integer value, int[] conditions) {

        // With a probability of 1/2, make the node a part of the lowest-level list only. With 1/4
        // probability, the
        // node will be a part of the lowest two lists. With 1/8 probability, the node will be a
        // part of three lists.
        // And so forth. Insert the node at the appropriate position in the lists that it is a part
        // of.
        // Max level is set to 10 now
        int level = flipAndIncrementLevel();

        PoiNode newNode = new PoiNode(value, level);

        PoiNode cur_walker = head;

        for (int i = currentMaxLevel - 1; i >= 0; i--) {
            // walk down the level until it find a range
            while (null != cur_walker.next[i]) {
                // when at bottom level, i is always 0, needs to find the right node to stop
                if (greaterThan(cur_walker.next[i].getPoiid(), value)) {
                    break;
                }
                cur_walker = cur_walker.next[i];
            }

            if (i <= level) {
                newNode.next[i] = cur_walker.next[i];
                cur_walker.next[i].before[i] = newNode.next[i];
                
                cur_walker.next[i] = newNode;
                newNode.before[i] = cur_walker.next[i];
            }
        }
        
        if(conditions != null) {
            for(int j=0; j< conditions.length ;j ++) {
                int condition = conditions[j];
                if(Objects.equal(condition, 1)) {
                    newNode.conditionsNext[j] = newNode.before[j].conditionsNext[j];
                    newNode.before[j].conditionsNext[j] = newNode;
                }
            }
        }

        size++;
    }

    public boolean contains(Integer poiid) {
        PoiNode cur_walker = head;
        for (int i = currentMaxLevel - 1; i >= 0; i--) {
            // walk down the level until it find a range
            while (null != cur_walker.next[i]) {
                // when at bottom level, i is always 0, needs to find the right node to stop
                if (greaterThan(cur_walker.next[i].getPoiid(), poiid)) {
                    break;
                }
                if (equalTo(cur_walker.next[i].getPoiid(), poiid)) {
                    return true;
                }
                cur_walker = cur_walker.next[i];
            }
        }

        return false;
    }

    public boolean delete(Integer poiid) {
        PoiNode cur_walker = head;
        boolean result = false;
        for (int i = currentMaxLevel - 1; i >= 0; i--) {
            // walk down the level until it find a range
            while (null != cur_walker.next[i]) {
                // when at bottom level, i is always 0, needs to find the right node to stop
                if (greaterThan(cur_walker.next[i].getPoiid(), poiid)) {
                    break;
                }
                if (equalTo(cur_walker.next[i].getPoiid(), poiid)) {
                    // ugly java ways of delete;
                    cur_walker.next[i] = cur_walker.next[i].next[i];
                    result = true;
                    size--;
                    // just like insert, delete one level is not enough, all levels needs to be
                    // removed;
                    break;
                }
                cur_walker = cur_walker.next[i];
            }
        }

        return result;
    }

    public int getSize() {
        return this.size;
    }

    public void levelPrint() {
        // find the start level
        PoiNode cur_walker = head;
        int start = MAX_LEVEL - 1;
        while (start > 0 && null == cur_walker.next[start]) {
            start--;
        }

        // collect all node
        cur_walker = head;
        List<PoiNode> ref = new ArrayList<>();
        while (null != cur_walker) {
            ref.add(cur_walker);
            cur_walker = cur_walker.next[0];
        }

        for (int i = 0; i <= start; i++) {

            cur_walker = head;
            cur_walker = cur_walker.next[i];
            System.out.print("Layer " + i + " | level " + MAX_LEVEL + " | head |");

            int levelIndex = 1;
            while (null != cur_walker) {


                if (i > 0) {
                    while (ref.get(levelIndex).getPoiid() != cur_walker.getPoiid()) {
                        levelIndex++;
                        System.out.print("--------------------------");
                    }
                    // TODO: dont know why ++, it just fucking make the shit right
                    levelIndex++;
                }

                System.out.print("---> " + cur_walker);
                cur_walker = cur_walker.next[i];
            }

            System.out.println();
        }
    }

    private boolean equalTo(Integer a, Integer b) {
        return a.compareTo(b) == 0;
    }

    private boolean greaterThan(Integer a, Integer b) {
        return a.compareTo(b) > 0;
    }

    private int flipAndIncrementLevel() {
        boolean head = true;
        int level = 0;

        for (int i = 0; i < MAX_LEVEL; i++) {
            head &= seed.nextBoolean();

            if (head) {
                level++;
                if (level == this.currentMaxLevel) {
                    currentMaxLevel++;
                    break;
                }
            } else {
                break;
            }
        }
        return level;
    }
}
