using System;
using System.Collections.Generic;
using System.Linq;

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

		public static IEnumerable<IEnumerable<T>> Permute<T>(this IEnumerable<T> collection, double percentageToKeep = 0.66)
		{
			var maxCollectionSize = (int)Math.Ceiling(collection.Count() * percentageToKeep);

			if (collection.Count() > maxCollectionSize)
			{
				yield return collection;

				var permuteMethods = new PermuteMethods();
				foreach (var item in permuteMethods.PermuteInternal(collection, maxCollectionSize))
				{
					yield return item;
				}

				// collect me, gc!
				permuteMethods = null;
			}
		}

		class PermuteMethods
		{
			readonly HashSet<string> seenKeys = new HashSet<string>();

			internal IEnumerable<IEnumerable<T>> PermuteInternal<T>(IEnumerable<T> collection, int maxCollectionSize)
			{
				for (var i = 0; i < collection.Count(); i++)
				{
					var newList = collection.Where(item => !item.Equals(collection.ElementAt(i))).ToList();
					var key = string.Join("~", newList.OrderBy(item => item.GetHashCode()).Select(item => item.GetHashCode().ToString()));

					if (newList.Count > maxCollectionSize && !seenKeys.Contains(key))
					{
						this.seenKeys.Add(key);

						yield return newList;
						foreach (var subList in PermuteInternal(newList, maxCollectionSize))
						{
							if (subList.Count() > 0)
							{
								yield return subList;
							}
						}
					}
				}
			}
		}
	}
}

