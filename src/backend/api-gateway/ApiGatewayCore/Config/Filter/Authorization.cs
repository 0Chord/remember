namespace ServerCore
{
    public class Authorization
    {
        public string Type { get; set; } = null!;
        public JwtValidator? jwtValidator { get; set; }
    }
}