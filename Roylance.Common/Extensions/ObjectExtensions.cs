using System;

namespace Roylance.Common
{
	public static class ObjectExtensions
	{
		public static void CheckIfNull(this object item, string propertyName)
		{
			if (item == null)
			{
				throw new ArgumentNullException(propertyName);
			}
		}
	}
}

