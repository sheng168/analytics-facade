package shared.android;

import android.view.View;

abstract public class AbstractViewUpdater<T> {
	protected T item;

	public AbstractViewUpdater(View convertView) {
		findViews(convertView);
		convertView.setTag(this);
	}
	
	abstract public void findViews(View convertView);
//		{
//			text = (TextView) convertView.findViewById(R.id.text);
//			checkBox = (CheckBox) convertView.findViewById(R.id.checkBox);
//		}

	public void update(T item) {
		this.item = item;
	}
}