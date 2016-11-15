package zk.cache;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map.Entry;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.ChildData;
import org.apache.curator.framework.recipes.cache.TreeCache;
import org.apache.curator.framework.recipes.cache.TreeCacheEvent;
import org.apache.curator.framework.recipes.cache.TreeCacheListener;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.utils.CloseableUtils;
import org.apache.curator.utils.ZKPaths;
import org.apache.zookeeper.KeeperException;

import zk.discovery.ExampleServer;

/**
 * An example of the TreeCache. The example "harness" is a command processor
 * that allows adding/updating/removed nodes in a path. A TreeCache keeps a
 * cache of these changes and outputs when updates occurs.
 * 
 * 利用curator（zk）来实现配置分发，因为TreeCache是缓存到本地的，即使zk挂掉，也不会影响TreeCache的数据。
 * zk的配置中心对某一个项目（节点）下的配置进行操作（增/删/改），那么TreeCache会监听到，调整缓存中的值，写一个获取TreeCache的接口，就可以获取项目中的配置了
 */
public class TreeCacheExample
{
    private static final String     PATH = "/example/treecache";

    public static void main(String[] args) throws Exception
    {
//        TestingServer       server = new TestingServer();
        CuratorFramework    client = null;
        TreeCache   cache = null;
        try
        {
            client = CuratorFrameworkFactory.newClient("127.0.0.1:2181,127.0.0.1:2182,127.0.0.1:2183", new ExponentialBackoffRetry(1000, 3));
            client.start();

            // in this example we will cache data. Notice that this is optional.
            cache = new TreeCache(client, PATH);
            cache.start();

            processCommands(client, cache);
        }
        finally
        {
            CloseableUtils.closeQuietly(cache);
            CloseableUtils.closeQuietly(client);
//            CloseableUtils.closeQuietly(server);
        }
    }

    private static void addListener(TreeCache cache)
    {
        // a TreeCacheListener is optional. Here, it's used just to log changes
        TreeCacheListener listener = new TreeCacheListener()
        {
            @Override
            public void childEvent(CuratorFramework client, TreeCacheEvent event) throws Exception
            {
                switch ( event.getType() )
                {
                    case NODE_ADDED:
                    {
                        System.out.println("Node added: " + ZKPaths.getNodeFromPath(event.getData().getPath()));
                        break;
                    }

                    case NODE_UPDATED:
                    {
                        System.out.println("Node changed: " + ZKPaths.getNodeFromPath(event.getData().getPath()));
                        break;
                    }

                    case NODE_REMOVED:
                    {
                        System.out.println("Node removed: " + ZKPaths.getNodeFromPath(event.getData().getPath()));
                        break;
                    }
                    default:
                        break;
                }
            }
        };
        cache.getListenable().addListener(listener);
    }

    private static void processCommands(CuratorFramework client, TreeCache cache) throws Exception
    {
        // More scaffolding that does a simple command line processor

        printHelp();

        List<ExampleServer> servers = new ArrayList<>();
        try
        {
            addListener(cache);

            BufferedReader  in = new BufferedReader(new InputStreamReader(System.in));
            boolean         done = false;
            while ( !done )
            {
                System.out.print("> ");

                String      line = in.readLine();
                if ( line == null )
                {
                    break;
                }

                String      command = line.trim();
                String[]    parts = command.split("\\s");
                if ( parts.length == 0 )
                {
                    continue;
                }
                String      operation = parts[0];
                String      args[] = Arrays.copyOfRange(parts, 1, parts.length);

                if ( operation.equalsIgnoreCase("help") || operation.equalsIgnoreCase("?") )
                {
                    printHelp();
                }
                else if ( operation.equalsIgnoreCase("q") || operation.equalsIgnoreCase("quit") )
                {
                    done = true;
                }
                else if ( operation.equals("set") )
                {
                    setValue(client, command, args);
                }
                else if ( operation.equals("remove") )
                {
                    remove(client, command, args);
                }
                else if ( operation.equals("list") )
                {
                    list(cache);
                }

                Thread.sleep(1000); // just to allow the console output to catch up
            }
        }
        finally
        {
            for ( ExampleServer server : servers )
            {
                CloseableUtils.closeQuietly(server);
            }
        }
    }

    private static void list(TreeCache cache)
    {
        if ( cache.getCurrentChildren(PATH).size() == 0 )
        {
            System.out.println("* empty *");
        }
        else
        {
            for (Entry<String, ChildData> entry :  cache.getCurrentChildren(PATH).entrySet())
            {
                System.out.println("key:"+entry.getKey());
                System.out.println(new String(entry.getValue().getPath()) + " = " + new String(entry.getValue().getData()));
            }
        }
    }

    private static void remove(CuratorFramework client, String command, String[] args) throws Exception
    {
        if ( args.length != 1 )
        {
            System.err.println("syntax error (expected remove <path>): " + command);
            return;
        }

        String      name = args[0];
        if ( name.contains("/") )
        {
            System.err.println("Invalid node name" + name);
            return;
        }
        String      path = ZKPaths.makePath(PATH, name);

        try
        {
            client.delete().forPath(path);
        }
        catch ( KeeperException.NoNodeException e )
        {
            // ignore
        }
    }

    private static void setValue(CuratorFramework client, String command, String[] args) throws Exception
    {
        if ( args.length != 2 )
        {
            System.err.println("syntax error (expected set <path> <value>): " + command);
            return;
        }

        String      name = args[0];
        if ( name.contains("/") )
        {
            System.err.println("Invalid node name" + name);
            return;
        }
        String      path = ZKPaths.makePath(PATH, name);

        byte[]      bytes = args[1].getBytes();
        try
        {
            client.setData().forPath(path, bytes);
        }
        catch ( KeeperException.NoNodeException e )
        {
            client.create().creatingParentContainersIfNeeded().forPath(path, bytes);
        }
    }

    private static void printHelp()
    {
        System.out.println("An example of using TreeCache. This example is driven by entering commands at the prompt:\n");
        System.out.println("set <name> <value>: Adds or updates a node with the given name");
        System.out.println("remove <name>: Deletes the node with the given name");
        System.out.println("list: List the nodes/values in the cache");
        System.out.println("quit: Quit the example");
        System.out.println();
    }
}
