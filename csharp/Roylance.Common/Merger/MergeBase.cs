﻿using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace Roylance.Common
{
	public class MergeBase
	{
		protected async static Task HandleCreateUpdateDelete<TKey, TValue>(
			Dictionary<TKey, TValue> fromDictionary,
			Dictionary<TKey, TValue> toDictionary,
			IMergeOperations<TKey> mergeOperations)
		{
			fromDictionary.CheckIfNull(nameof(fromDictionary));
			toDictionary.CheckIfNull(nameof(toDictionary));
			mergeOperations.CheckIfNull(nameof(mergeOperations));

			foreach (var fromKey in fromDictionary.Keys)
			{
				if (toDictionary.ContainsKey(fromKey))
				{
					await mergeOperations.Update(fromKey);
				}
				else
				{
					await mergeOperations.Insert(fromKey);
				}
			}

			foreach (var toKey in toDictionary.Keys.Where(toKey => !fromDictionary.ContainsKey(toKey)))
			{
				await mergeOperations.Delete(toKey);
			}
		}

		protected Task DefaultTask()
		{
			return Task.Factory.StartNew(() => {});
		}

		protected static void UpdateNewIds<T>(IEnumerable<T> entities) where T : class, IKeyedEntity
		{
			var newId = 0;
			foreach (var r in entities.Where(entity => entity.Id == 0))
			{
				r.Id = --newId;
			}
		}
	}
}

