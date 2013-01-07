package shared.android;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

public abstract class AbstractViewUpdaterAdapter<T> extends ArrayAdapter<T> {
	private int layoutId;

	public AbstractViewUpdaterAdapter(Context context, int textViewResourceId,
			List<T> objects) {
		super(context, textViewResourceId, objects);
		layoutId = textViewResourceId;
	}

	abstract protected AbstractViewUpdater<T> createUpdater(View convertView);
	
	public View getView(int position, View convertView, ViewGroup parent) {
		// log.debug("getView {} {}", position, convertView);

		// A ViewHolder keeps references to children views to avoid
		// unneccessary calls
		// to findViewById() on each row.
		AbstractViewUpdater<T> updater;

		// When convertView is not null, we can reuse it directly, there
		// is no need
		// to reinflate it. We only inflate a new View when the
		// convertView supplied
		// by ListView is null.
		if (convertView == null) {
			convertView = LayoutInflater.from(this.getContext())
					.inflate(layoutId, null);

			// Creates a ViewHolder and store references to the two
			// children views
			// we want to bind data to.
			updater = createUpdater(convertView);
			convertView.setTag(updater);
		} else {
			// Get the ViewHolder back to get fast access to the
			// TextView
			// and the ImageView.
			updater = getUpdater(convertView);
		}

		updater.update(this.getItem(position));

		return convertView;
	}

	@SuppressWarnings("unchecked")
	private AbstractViewUpdater<T> getUpdater(View convertView) {
		return (AbstractViewUpdater<T>) convertView.getTag();
	}

}
