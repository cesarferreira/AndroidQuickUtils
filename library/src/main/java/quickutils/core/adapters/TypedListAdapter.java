package quickutils.core.adapters;

import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public abstract class TypedListAdapter<T> extends TypedBaseAdapter<T> {

    private final List<T> elements = new ArrayList<T>();

    @Override
    public int getCount() {
        return elements.size();
    }

    @Override
    public T getItem(int position) {
        return elements.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public abstract View getView(int position, View convertView, ViewGroup parent);

    public List<T> getElements() {
        return new ArrayList<T>(elements);
    }

    public void setElements(List<T> elements) {
        this.elements.clear();
        this.elements.addAll(elements);
        notifyDataSetChanged();
    }

    public void clearElements() {
        this.elements.clear();
        notifyDataSetChanged();
    }

    public void addElements(List<T> elements) {
        this.elements.addAll(elements);
        notifyDataSetChanged();
    }
}
