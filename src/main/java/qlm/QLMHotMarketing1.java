package qlm;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;

public class QLMHotMarketing1 {
  private static final String fileUrl = "/Users/xiaoman/Downloads/144047638844506.txt";
  
    public static void main(String[] args) throws IOException {
        m3();
    }

    public static void m3() throws IOException {
        LinkedList<String> list = new LinkedList<String>();
        BufferedReader br = new BufferedReader(new FileReader(new File(fileUrl)));
        String str = "";
        while ((str = br.readLine()) != null) {
            list.add(str);
        }
        LinkedList<People> pList = new LinkedList<People>();
        String strs[];
        int id;
        int friendId;
        while (list.size() != 0) {
            str = list.removeFirst();
            strs = str.split(" ");
            id = Integer.parseInt(strs[0]);
            friendId = Integer.parseInt(strs[1]);
            People root = new People(id, friendId);
            find(root, list);
            pList.add(root);
        }
        System.out.println(pList.size());
    }

    public static void find(People p, LinkedList<String> list) {
        String str = "";
        int pId;
        int pFriendId;
        for (int i = 0; i < list.size(); i++) {
            str = list.removeFirst();
            pId = Integer.parseInt(str.split(" ")[0]);
            pFriendId = Integer.parseInt(str.split(" ")[1]);
            if (pId == p.getId()) {
                p.addFriend(pFriendId);
            } else if (pFriendId == p.getId()) {
                p.addFriend(pId);
            } else {
                list.addLast(str);
            }
        }
        if (p.hasFriend()) {
            for (People lp : p.getFriends()) {
                find(lp, list);
            }
        }
    }
}

class People {
    private int id;
    private People[] friends;

    public People() {
    }

    public People(int id) {
        this.id = id;
    }

    public People(int id, int... friends) {
        this.id = id;
        this.friends = new People[friends.length];
        for (int i = 0; i < friends.length; i++) {
            this.friends[i] = new People(friends[i]);
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public People[] getFriends() {
        return friends;
    }

    public void setFriends(People[] friends) {
        this.friends = friends;
    }

    public void addFriend(int friendId) {
        if (this.friends == null || this.friends.length <= 0) {
            this.friends = new People[] { new People(friendId) };
        } else {
            People[] dest = new People[this.friends.length + 1];
            System.arraycopy(this.friends, 0, dest, 0, this.friends.length);
            dest[dest.length - 1] = new People(friendId);
            this.friends = dest;
        }
    }

    public boolean hasFriend() {
        return this.friends != null && this.friends.length != 0;
    }

    @Override
    public String toString() {
        return "People [id=" + id + ", friends=" + Arrays.toString(friends)
                + "]";
    }

}
