using System;
using System.ComponentModel;
using System.Linq.Expressions;

namespace Roylance.Common
{
	public class ViewModelBase : INotifyPropertyChanged
	{
		public event PropertyChangedEventHandler PropertyChanged;

		protected void RaisePropertyChanged(Expression<Func<object>> propertyName)
		{
			this.RaisePropertyChanged(GetName(propertyName));
		}

		protected void RaisePropertyChanged(string propertyName)
		{
			if (this.PropertyChanged != null)
			{
				this.PropertyChanged(this, new PropertyChangedEventArgs(propertyName));
			}
		}

		//	http://stackoverflow.com/questions/671968/retrieving-property-name-from-lambda-expression - thank you!
		public static string GetName(Expression<Func<object>> exp)
		{
			var body = exp.Body as MemberExpression;
			if (body == null)
			{
				var ubody = (UnaryExpression)exp.Body;
				body = ubody.Operand as MemberExpression;
			}
			return body.Member.Name;
		}
	}
}

