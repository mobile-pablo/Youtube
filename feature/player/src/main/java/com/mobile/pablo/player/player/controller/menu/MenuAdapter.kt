package com.mobile.pablo.player.player.controller.menu

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.pierfrancescosoffritti.androidyoutubeplayer.core.customui.R
import com.pierfrancescosoffritti.androidyoutubeplayer.core.customui.menu.MenuItem

/**
 *  Copied from https://github.com/PierfrancescoSoffritti/android-youtube-player
 *  only to make YoutubePlayerUiController.kt work (internal class)
 */
internal class MenuAdapter(
    private val context: Context,
    private val menuItems: List<MenuItem>
) :
    RecyclerView.Adapter<MenuAdapter.ViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.ayp_menu_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {
        holder.root.setOnClickListener(menuItems[position].onClickListener)
        holder.textView.text = menuItems[position].text
        menuItems[position].icon?.let {
            holder.textView.setCompoundDrawablesWithIntrinsicBounds(
                ContextCompat.getDrawable(context, it),
                null,
                null,
                null
            )
        }
    }

    override fun getItemCount(): Int {
        return menuItems.size
    }

    internal inner class ViewHolder(val root: View) : RecyclerView.ViewHolder(root) {
        val textView: TextView = root.findViewById(R.id.text)
    }
}
