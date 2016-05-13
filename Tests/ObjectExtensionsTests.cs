using System.Collections.Generic;
using Roylance.Common;
using NUnit.Framework;

namespace Tests
{
	[TestFixture()]
	public class ObjectExtensionsTests
	{
		[Test()]
		public void SimpleTest()
		{
			// arrange
			var testKeys = new List<string>
			{
				"a",
				"b",
				"c",
				"d",
				"e",
				"f"
			};

			// act
			var items = testKeys.Permute();
			foreach (var item in items)
			{
				System.Console.WriteLine(string.Join(",", item));
			}

			// assert
			Assert.IsTrue(true);
		}
	}
}

