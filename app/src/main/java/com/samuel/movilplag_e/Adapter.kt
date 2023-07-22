import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.samuel.movilplag_e.R
import com.samuel.movilplag_e.UserRobotDataClass

class MyAdapter(private val context: Context, private val items: List<UserRobotDataClass>) : BaseAdapter() {

    override fun getCount(): Int {
        return items.size
    }

    override fun getItem(position: Int): Any {
        return items[position]
    }

    override fun getItemId(position: Int): Long {
        // Utilizamos el código como ID
        return items[position].code.hashCode().toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View
        val viewHolder: ViewHolder

        if (convertView == null) {
            view = LayoutInflater.from(context).inflate(R.layout.robot_card, parent, false)
            viewHolder = ViewHolder(view)
            view.tag = viewHolder
        } else {
            view = convertView
            viewHolder = view.tag as ViewHolder
        }

        val currentItem = items[position]

        // Set the values of the TextViews using the data from the MyItem object
        viewHolder.nameTextView.text = currentItem.name
        viewHolder.wasteTextView.text = currentItem.waste.toString()
        viewHolder.xTextView.text = currentItem.locationX.toString()
        viewHolder.yTextView.text = currentItem.locationY.toString()
        viewHolder.codeTextView.text = currentItem.code

        return view
    }

    private class ViewHolder(view: View) {
        val nameTextView: TextView = view.findViewById(R.id.name_display)
        val wasteTextView: TextView = view.findViewById(R.id.waste_display)
        val xTextView: TextView = view.findViewById(R.id.x_display)
        val yTextView: TextView = view.findViewById(R.id.y_display)
        val codeTextView: TextView = view.findViewById(R.id.code_display)
    }
}
