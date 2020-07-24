package com.gerardbradshaw.mixup.ui.moreapps

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.gerardbradshaw.mixup.AppInfo
import com.gerardbradshaw.mixup.R

class AppListAdapter(private val context: Context, private var apps: ArrayList<AppInfo>) :
  RecyclerView.Adapter<AppListAdapter.AppInfoViewHolder>() {

  private val inflater = LayoutInflater.from(context)

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AppInfoViewHolder {
    val itemView = inflater.inflate(R.layout.list_item_app, parent, false)
    return AppInfoViewHolder(itemView)
  }

  override fun getItemCount(): Int {
    return apps.size
  }

  override fun onBindViewHolder(holder: AppInfoViewHolder, position: Int) {
    val app = apps[position]
    val resources = context.resources

    holder.titleView.text = resources.getString(app.titleRes)
    holder.descriptionView.text = resources.getString(app.descriptionRes)

    Glide
      .with(context)
      .load(app.iconRes)
      .into(holder.iconView)

    holder.itemView.setOnClickListener {
      val intent = Intent(Intent.ACTION_VIEW)
      intent.data = Uri.parse(resources.getString(app.urlRes))
      context.startActivity(intent)
    }
  }

  class AppInfoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val titleView: TextView = itemView.findViewById(R.id.app_title)
    val descriptionView: TextView = itemView.findViewById(R.id.app_description)
    val iconView: ImageView = itemView.findViewById(R.id.app_logo)
  }
}