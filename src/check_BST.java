import java.util.*;
import java.util.stream.Collectors;

public class check_BST {

    public static void main(String[] args)
    {
        Scanner sc=new Scanner(System.in);
        int t=sc.nextInt();
        while (t>0)
        {
            HashMap<Integer, Node> m = new HashMap<>();
            int n=sc.nextInt();
            Node root=null;
            while (n>0)
            {
                int n1=sc.nextInt();
                int n2=sc.nextInt();
                char lr=sc.next().charAt(0);
                Node parent=m.get(n1);
                if(parent==null)
                {
                    parent=new Node(n1);
                    m.put(n1,parent);
                    if(root==null)
                        root=parent;
                }
                Node child=new Node(n2);
                if(lr=='L')
                    parent.left=child;
                else
                    parent.right=child;
                m.put(n2,child);
                n--;

            }
            GfG g=new GfG();
            List<Node> myRoot=new ArrayList<>();
            myRoot.add(root);
            System.out.println(g.isBST(myRoot, Integer.MIN_VALUE, Integer.MAX_VALUE));
            t--;
        }
    }
}

class Node
{
    private int data;
    Node left, right;
    Node(int item)
    {
        data = item;
        left = right = null;
    }

    int getData() {
        return data;
    }

    Node getLeft() {
        return left;
    }

    Node getRight() {
        return right;
    }
}


class GfG
{
    boolean isBST(List<Node> node, int min, int max)
    {
        if (node.stream().allMatch(Objects::isNull))
            return true;

        if(node.stream()
                .anyMatch(x -> x.getData()<min || x.getData()>max))
            return false;

        int data= node.stream().mapToInt(Node::getData).sum();

        return (isBST(node.stream()
                .map(Node::getLeft)
                .collect(Collectors.toList()), min, data-1) &&
                isBST(node.stream()
                        .map(Node::getRight)
                        .collect(Collectors.toList()), data+1, max));
    }
}