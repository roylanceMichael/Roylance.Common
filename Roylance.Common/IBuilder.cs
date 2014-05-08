namespace Roylance.Common
{
	public interface IBuilder<out T>
	{
		T Build();
	}
}
