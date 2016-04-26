package qlm;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

public class QLMHotMarketing {

  private static final String fileUrl = "/Users/xiaoman/Downloads/144047638844506.txt";
  private static BufferedReader br;

  private static void getResult() {
    List<Tree> trees = new ArrayList<Tree>();

    try {
      br = new BufferedReader(new FileReader(new File(fileUrl)));
      String content = "";
      do {
        content = br.readLine();
        if (StringUtils.isNotBlank(content)) {
          String[] list = content.split(" ");
          if (list != null) {
            int num1 = Integer.valueOf(list[0]);
            int num2 = Integer.valueOf(list[1]);
            addToList(trees, num1, num2);
          }
        }
      } while (StringUtils.isNoneBlank(content));

      System.out.println("result size:" + trees.size());
      Set<Integer> set = new HashSet<>();
      for (Tree tree : trees) {
        set.add(tree.getValue());
      }
      System.out.println(set.size());
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static void addToList(List<Tree> trees, int num1, int num2) {
    if (trees.size() > 0) {
      
      // 如果现有tree的头结点的值和num2相等，那么需要创建新的节点，指向原有tree
      for (int i = 0; i < trees.size(); i++) {
        Tree tree = trees.get(i);
        if (tree.getValue() == num2) {
          trees.set(i, new Tree(num1, tree));
          return;
        }
      }

      // 如果tree的子节点的值为num1，那么将num2加入树中
      for (int i = 0; i < trees.size(); i++) {
        Tree tree = trees.get(i);
        if (addNode(tree, num1, num2)) {
          return;
        }
      }

      // 如果以上两种情况都没有，那么也创建一个新树
      Tree t = new Tree(num1, num2);
      trees.add(t);

    } else {
      Tree t = new Tree(num1, num2);
      trees.add(t);
    }
  }

  public static boolean addNode(Tree tree, int num1, int num2) {
    if (tree != null) {
      if (tree.getValue() == num1) {
        Tree treeNum2 = new Tree(num2);

        if (CollectionUtils.isEmpty(tree.getNodes())) {
          List<Tree> nodes = new ArrayList<Tree>();
          nodes.add(treeNum2);
          tree.setNodes(nodes);
        } else {
          tree.getNodes().add(treeNum2);
        }
      } else {
        if (CollectionUtils.isEmpty(tree.getNodes())) {
          for (Tree nodeTree : tree.getNodes()) {
            return addNode(nodeTree, num1, num2);
          }
        }
        return false;
      }
    }

    return false;
  }

  public static void main(String[] args) {
    getResult();
  }
}

class Tree {
  public Tree(int value) {
    this.value = value;
  }

  public Tree(int num1, int num2) {
    this.value = num1;
    Tree node2 = new Tree(num2);
    if (CollectionUtils.isEmpty(this.nodes)) {
      List<Tree> nodes = new ArrayList<Tree>();
      nodes.add(node2);
      this.nodes = nodes;
    } else {
      this.nodes.add(node2);
    }
  }

  public Tree(int num1, Tree tree) {
    this.value = num1;
    if (CollectionUtils.isEmpty(this.nodes)) {
      List<Tree> nodes = new ArrayList<Tree>();
      nodes.add(tree);
      this.nodes = nodes;
    } else {
      this.nodes.add(tree);
    }
  }

  private int value;
  private List<Tree> nodes;

  public int getValue() {
    return value;
  }

  public void setValue(int value) {
    this.value = value;
  }

  public List<Tree> getNodes() {
    return nodes;
  }

  public void setNodes(List<Tree> nodes) {
    this.nodes = nodes;
  }
}
