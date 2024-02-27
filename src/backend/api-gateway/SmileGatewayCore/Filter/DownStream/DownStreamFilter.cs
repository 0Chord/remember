using SmileGatewayCore.Http.Context;
using SmileGatewayCore.Instance;
using SmileGatewayCore.Instance.DownStream;

namespace SmileGatewayCore.Filter.Listner;


// 기본 필터(상속용)
public abstract class DownStreamFilter : IDownStreamFilterBase
{
    protected abstract void Working(Adapter adapter, HttpContext context);
    protected abstract void Worked(Adapter adapter, HttpContext context);

    public async Task InvokeAsync(Adapter adapter, HttpContext context, DownStreamDelegate next)
    {
        Working(adapter, context);

        await next(adapter, context);

        Worked(adapter, context);
    }
}