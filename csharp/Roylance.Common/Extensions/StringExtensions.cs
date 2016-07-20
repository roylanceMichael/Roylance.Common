using System.Reflection;
using System.IO;

namespace Roylance.Common
{
	public static class StringExtensions
	{
		public static string ReadResource(this string resourceLocation, Assembly assembly)
		{
			resourceLocation.CheckIfNull(nameof(resourceLocation));
			assembly.CheckIfNull(nameof(assembly));

			using (var d1Stream = assembly.GetManifestResourceStream(resourceLocation))
			{
				if (d1Stream == null)
				{
					return string.Empty;
				}

				using (var stringReader = new StreamReader(d1Stream))
				{
					return stringReader.ReadToEnd();
				}
			}
		}
	}
}

