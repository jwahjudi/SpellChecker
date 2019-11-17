import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Properties;

public class CheckerDriver
{
    public static String getStorageType(String property)
    {
        if(property.equals("tree"))
            return "tree";
        return "trie";
    }

    public static void store(Scanner file, Tree storage)
    {
        String line;
        while(file.hasNextLine())
        {
            line = file.nextLine();
            if(!line.trim().isEmpty())
                storage.insert(line);
        }
    }

    public static void main(String[] args) throws IOException
    {
        Scanner file = new Scanner(new File("english.0"));
        Scanner input = new Scanner(new File(args[0]));
        BufferedWriter output = new BufferedWriter(new FileWriter(args[1]));
        FileInputStream properties = new FileInputStream(new File("a1properties.txt"));
        Properties property = new Properties();
        property.load(properties);

        String storageType = getStorageType(property.getProperty("storage"));
        Tree storage;
        if(storageType == "tree")
        {
            storage = new BinaryHeap();
        }
        else
            storage = new Trie();
        store(file, storage);

        if(storageType == "trie")
        {
            String word;
            ArrayList<String>inputs = new ArrayList<String>();
            String sum = "";
            int i = 0;
            while(input.hasNextLine())
            {
                word = input.nextLine();
                inputs.add(word.trim());
                storage.checkWord(word.trim(), inputs);
                sum += inputs.get(i);
                sum += '\n';
                i++;
            }
            output.write(sum);
            output.close();
        }
    }
}

