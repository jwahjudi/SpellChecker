import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;

public class Trie implements Tree
{
    class Node
    {
        char data;
        Node letters[];
        boolean word;
        Node(char item)
        {
            data = item;
            word = false;
            letters = new Node[27];
            for(int i = 0; i < 27; i++)
                letters[i] = null;
        }
    }

    Node root;
    public Trie()
    {
        root = new Node(' ');
    }

    public boolean find(String item)
    {
        return find(item, root, 0);
    }

    private boolean find (String item, Node node, int i)
    {
        if(i == item.length() && node.word == true)
            return true;
        else if(i == item.length())
            return false;
        char letter = Character.toLowerCase(item.charAt(i));
        int ascii;
        if(letter == '\'')
            ascii = 26;
        else
            ascii = (int)letter - 97;
        if(node.letters[ascii] == null)
            return false;
        else if(node.letters[ascii].data == letter)
            return find(item, node.letters[ascii], i+1);
        return true;
    }

    public void insert(String item)
    {
        root = insert(root, item, 0);
    }

    Node insert(Node node, String item, int i)
    {
        if(i == item.length())
        {
            node.word = true;
            return node;
        }
        char letter = Character.toLowerCase(item.charAt(i));
        int ascii;
        if(letter == '\'')
            ascii = 26;
        else
            ascii = (int)letter - 97;
        if(node.letters[ascii] == null)
        {
            node.letters[ascii] = new Node(letter);
            insert(node.letters[ascii], item, i+1);
        }
        else if(node.letters[ascii].data == letter)
            insert(node.letters[ascii], item, i+1);
        return node;
    }

    public boolean checkMispelling(ArrayList<String> result, Node node, String alt, String item, int miss, int i)
    {
        if(miss == item.length()/2)
            return false;
        if(i == item.length() && find(alt))
        {
            result.add(alt);
            return true;
        }
        else if(i == item.length())
            return false;

        char letter = Character.toLowerCase(item.charAt(i));
        int ascii;
        if(letter == '\'')
            ascii = 26;
        else
            ascii = (int)letter - 97;
        if(node.letters[ascii] == null)
        {
            for(int j = 0; j < 27; j++)
            {
                if(node.letters[j] != null)
                {
                    char temp;
                    if(j == 27)
                        temp = '\'';
                    else
                    {
                        int rep = j + 97;
                        temp = (char)rep;
                    }
                    if(!checkMispelling(result, node.letters[j], alt.concat(String.valueOf(temp)), item,miss+1, i+1) && i+1==item.length())
                        return false;
                }
            }
        }
        else
            checkMispelling(result, node.letters[ascii], alt.concat(String.valueOf(item.charAt(i))), item, miss,i+1);
        return true;
    }

    public void checkWord(String item, ArrayList list)
    {
        if(find(item) == true)
            System.out.println(item + ": Word is in storage");
        else
        {
            String temp = "";
            ArrayList<String> alt = new ArrayList<String>();
            checkMispelling(alt, root, temp, item, 0, 0);
            Iterator i = alt.iterator();
            String sum = "";
            int count = 0;
            while (i.hasNext() && count != 2)
            {
                sum += i.next();
                sum += " ";
                count++;
            }
            list.set(list.indexOf(item), sum);
        }
    }
}
