using System.Net.Sockets;
using Microsoft.Extensions.ObjectPool;

namespace SmileGatewayCore.Instance.DownStream;

public class SocketPool
{
    private ObjectPool<Socket> _socketPool;

    public SocketPool()
    {
        _socketPool = new DefaultObjectPool<Socket>(new SocketPoolPolicy());
    }

    public SocketPool(IPooledObjectPolicy<Socket> policy)
    {
        _socketPool = new DefaultObjectPool<Socket>(policy);
    }

    public Socket RentSocket()
    {
        return _socketPool.Get();
    }

    public void ReturnSocket(Socket socket)
    {
        if(socket.Connected == true)
            socket.Disconnect(reuseSocket: true);
        _socketPool.Return(socket);
    }
}


class SocketPoolPolicy : IPooledObjectPolicy<Socket>
{
    public Socket Create()
    {
        Socket socket = new Socket(AddressFamily.InterNetwork, SocketType.Stream, ProtocolType.Tcp);
        socket.SetSocketOption(SocketOptionLevel.Socket, SocketOptionName.ReuseAddress, true);

        return socket;
    }

    public bool Return(Socket obj)
    {
        try
        {
            if (obj.Connected)
                obj.Disconnect(reuseSocket: true);
        }
        catch (System.Exception e)
        {
            System.Console.WriteLine(e.Message);
            return false;
        }

        return true;
    }
}