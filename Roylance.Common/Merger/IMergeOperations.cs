using System;
using System.Threading.Tasks;

namespace Roylance.Common
{
	public interface IMergeOperations<in T>
	{
		Task Update(T key);

		Task Insert(T key);

		Task Delete(T key);
	}
}

