using ApiGatewayCore.Config;
using ApiGatewayCore.Instance.Upstream;

namespace ApiGatewayCore.Manager;

internal class ClusterManager
{
    public ListenerManager ListenerManager { get; set; } = null!;
    public Dictionary<string, Cluster> Clusters { get; set; } = new Dictionary<string, Cluster>();

    public void Init(List<ClusterConfig> model)
    {
        foreach (var config in model)
        {
            var cluster = new Cluster(config);
            cluster.Init();
            
            Clusters.Add(config.Name, cluster);
        }
    }
}