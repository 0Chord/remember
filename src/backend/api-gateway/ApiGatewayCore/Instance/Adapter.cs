using ApiGatewayCore.Config;

namespace ApiGatewayCore.Instance;

public class Adapter
{
    internal Authorization Authorization { get; set; } = null!;
    internal List<string>? DisallowHeaders { get; set; }
    internal AddressConfig Address { get; set; } = null!;
    internal Cluster Cluster { get; set; } = null!;

    internal Adapter(ListenerConfig config, Cluster cluster)
    {
        Authorization = config.Authorization;
        DisallowHeaders = config.DisallowHeaders;
        Address = config.Address;
        Cluster = cluster;
    }
}