package com.example.lab_2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class LanguageAdapter(
    private val languages: List<ProgrammingLanguage>,
    private val onLanguageClick: OnLanguageClickListener
) : RecyclerView.Adapter<LanguageAdapter.LanguageViewHolder>() {

    class LanguageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val langImageView: ImageView = itemView.findViewById(R.id.imageView)
        val langNameTextView: TextView = itemView.findViewById(R.id.nameTextView)
        val langShortDescTextView: TextView = itemView.findViewById(R.id.shortDescTextView)
        val langLayout: LinearLayout = itemView.findViewById(R.id.languageLayout)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LanguageViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.language_item, parent, false)
        return LanguageViewHolder(view)
    }

    override fun onBindViewHolder(holder: LanguageViewHolder, position: Int) {
        val lang = languages[position]

        Glide.with(holder.langImageView.context).load(lang.url).into(holder.langImageView)

        holder.langNameTextView.text = lang.name
        holder.langShortDescTextView.text = lang.shortDescription

        holder.langLayout.setOnClickListener {
            onLanguageClick.onClick(lang)
        }
    }

    override fun getItemCount(): Int {
        return languages.size
    }
}
